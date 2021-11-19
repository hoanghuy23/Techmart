package com.techmart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private boolean status;

    @Temporal(TemporalType.DATE)
    @Column(name = "createDate")
    private Date createDate  = new Date();

    private Date modifiedDate;
    private String createdBy;
    private String modifiedBy;
    private boolean isMain;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "userDetailId", nullable = false)
    private UserDetail userDetail;

}
