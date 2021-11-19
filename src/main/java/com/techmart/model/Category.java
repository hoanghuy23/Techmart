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
@Table(name = "Category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    private String name;

    @Column(nullable = true)
    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category category;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<Category> categories = new ArrayList<Category>(0);

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.MERGE)
    private List<Product> products = new ArrayList<>(0);

}
