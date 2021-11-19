package com.techmart.controller;

import com.techmart.model.Account;
import com.techmart.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class LoginController {
    @Autowired
    private AccountService accountService;

    @RequestMapping("/login/form")
    public String loginForm(Model model) {
        return "account/login";
    }

    @RequestMapping("/login/success")
    public String loginSuccess(Model model) {
        return "redirect:/index";
    }

    @RequestMapping("/login/error")
    public String loginError(Model model, @Param("username") String username) {
        try {
            Account account = accountService.findById(username);
                if(account.isStatus()){
                    model.addAttribute("message","Thông tin tài khoản không chính xác");
                }else{
                    model.addAttribute("message","Tài khoản chưa được xác thực");
                }

        }catch (Exception e){
            model.addAttribute("message","Tài khoản không tồn tại");
        }
//        model.addAttribute("message","Thông tin tài khoản không chính xác");
        return "account/login";
    }

    @RequestMapping("/unauthoried")
    public String unauthoried(Model model) {
        model.addAttribute("message","Không có quyền truy xuất !");
        return "account/login";
    }

    @RequestMapping("/logoff/success")
    public String logoffSuccess(Model model) {
        model.addAttribute("message","Bạn đã đăng xuất !");
        return "account/login";
    }
}
