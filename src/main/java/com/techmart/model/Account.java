package com.techmart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Account")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account implements Serializable{

    @Id
    private String username;

    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "account" , fetch = FetchType.EAGER)
    List<Authorities> authorities = new ArrayList<>();

    private boolean status; 

    @OneToOne(optional = false)
    @JoinColumn(name = "info_id", referencedColumnName = "id")
    private UserDetail info;
    
    @Column(name = "vetification_code")
    private String vetificationCode;

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", status=" + status +
                '}';
    }
}
