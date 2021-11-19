package com.techmart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Invoice")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "UserdetailId")
	private UserDetail userDetail;

	private boolean status;

	@Temporal(TemporalType.DATE)
	@Column(name = "createDate")
	private Date createDate  = new Date();

	private Date modifiedDate;
	private String createdBy;
	private String modifiedBy;

	@JsonIgnore
	@OneToMany(mappedBy = "invoice")
	List<InvoiceDetail> invoiceDetails;
}
