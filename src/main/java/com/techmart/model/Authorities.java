package com.techmart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

import javax.persistence.*;

@SuppressWarnings("serial")
@Data
@Table(name = "Authorities", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"Username", "Roleid"})
})
@Entity
@NoArgsConstructor
public class Authorities implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "Username")
    private Account account;

    
    @ManyToOne
    @JoinColumn(name = "Roleid")
    private Role role;

    public Authorities(Account account, Role role) {
        this.account = account;
        this.role = role;
    }
}
