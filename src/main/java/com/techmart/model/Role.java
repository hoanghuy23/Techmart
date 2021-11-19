package com.techmart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable{
    @Id
    private String id;
    private String name;

    @JsonIgnore @OneToMany(mappedBy = "role")
    List<Authorities> authorities = new ArrayList<Authorities>();
}
