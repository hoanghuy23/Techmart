package com.techmart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String name;
        private int quantity;
        private String description;
        private boolean active;
        private boolean status;
        private double price;

        @Temporal(TemporalType.DATE)
        @Column(name = "createDate")
        private Date createDate  = new Date();

        private Date modifiedDate;
        private String createdBy;
        private String modifiedBy;

        private String manufacturer ;
        private String condition;
        private String insurance;
        private String color;
        private String size;
        private String battery;
        private String weight;
        private String otherFunction;
        private String screen;
        private String resolution;
        private String pinConnection;
        private String material;
        private String usedTime;
        private String model;
        private String audio;
        private String security;
        private String soundProofing;
        private String keyCaps;
        private String switchh;
        private String accessory;
        private String cpuEngine;
        private String ram;
        private String hardDrive;
        private String graphicsCard;
        private String commPort;
        private String keyboard;
        private String LAN;
        private String bluetooth;
        private String webcam;
        private String opeSys;
        private String wifi;
        private String memoryCard;
        private String sensor;
        private String speed;
        private String usbData;
        private String compatible;
        private String aspectRatio;
        private String pixelSize;
        private String contrast;
        private String brightness;
        private String scanFrequency;
        private String origin;
        private String mainboard;
        private String cases;
        private String ssd;
        private String psu;

        @ManyToOne
        @JoinColumn(name = "categoryId")
        private Category category;

        private int views;
        private boolean isFeatured;

        @OneToMany(cascade = CascadeType.MERGE,mappedBy = "product")
        private List<Photo> photos = new ArrayList<>(0);

        @OneToMany(cascade = CascadeType.MERGE,mappedBy = "product")
        private List<InvoiceDetail> invoiceDetails;

}
