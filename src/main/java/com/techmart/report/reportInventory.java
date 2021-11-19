package com.techmart.report;

import com.techmart.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class reportInventory implements Serializable {
    @Id
    private Category loai;
    private double sale;
    private long amount;
}
