package com.techmart.security;

import com.techmart.model.Account;
import com.techmart.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AccountService accountService;

    @Autowired
    BCryptPasswordEncoder pe;


    // Cung cấp dữ liệu đăng nhập
    protected  void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(username ->{
            try {
                Account user = accountService.findByUsername(username);
                if(user!=null){
                    String password = pe.encode(user.getPassword());
                    String[] roles = user.getAuthorities().stream()
                            .map(er -> er.getRole().getId())
                            .collect(Collectors.toList()).toArray(new String[0]);
                    return User.withUsername(username).password(password).roles(roles).build();
                }
                return User.withUsername(username).password("").roles("").build();
            }catch (NoSuchElementException e){
                throw new UsernameNotFoundException(username + "not found");
            }
        });
    }


    // Phân quyền sử dụng
    protected void configure(HttpSecurity http) throws Exception{

        http.csrf().disable();
        http.authorizeRequests()
//                .antMatchers("/order/**").authenticated()
//                .antMatchers("/admin/**").hasAnyRole("ADMIN","USER")
                //.antMatchers("/rest/authorities").hasRole("USER")
                .anyRequest().permitAll();

        http.formLogin()
                .loginPage("/login/form")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/login/success",false)
//                .failureUrl("/login/error");
                .usernameParameter("username")
                .failureHandler(new SimpleUrlAuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                                        AuthenticationException exception) throws IOException, ServletException {
                        String username = request.getParameter("username");
                        String error = exception.getMessage();
                        System.out.println("A failed login attempt with username: "
                                + username + ". Reason: " + error);

                        super.setDefaultFailureUrl("/login/error?username="+username);
                        super.onAuthenticationFailure(request, response, exception);
                    }
                });


        http.rememberMe()
                .tokenValiditySeconds(86400);

        http.exceptionHandling()
                .accessDeniedPage("/unauthoried");

        http.logout()
                .logoutUrl("/logoff")
                .logoutSuccessUrl("/logoff/success");
    }

    // Cơ chế mã hóa mật khẩu
    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Cho phép truy xuất REST API từ bên ngoài (domain khác)
    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers(HttpMethod.OPTIONS,"/**");
    }
}
