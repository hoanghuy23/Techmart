package com.techmart.model;

import lombok.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "UserDetail")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDetail implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fullName;
    private String phone;
    private String email; 
    private boolean gender;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthday;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.MERGE,mappedBy = "userDetail")
    private List<Invoice> invoices = new ArrayList<>(0);
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.MERGE,mappedBy = "userDetail")
    private List<Address> addresses = new ArrayList<>(0);
}
