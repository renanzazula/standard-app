package com.standard.entity;

import com.standard.enums.StatusPOSEnum;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity(name = "pos")
public @Data class PosEntity extends BaseAuditEntity  {

	private static final long serialVersionUID = -6612762288260227887L;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "openDate")
	private Date openDate;
	 
	@NotNull
	@Temporal(TemporalType.TIME)
	@Column(name = "openTime")
	private Date openTime;

	@Temporal(TemporalType.DATE)
	@Column(name = "closeDate")
	private Date closeDate;

	@Temporal(TemporalType.TIME)
	@Column(name = "closeTime")
	private Date closeTime;

	@NotNull
	@ColumnDefault(value = "0")
	@Column(name = "openAmount")
	private Double openAmount;

	@ColumnDefault(value = "0")
	@Column(name = "closeAmount")
	private Double closeAmount;

	@ColumnDefault(value = "0")
	@Column(name = "totalVendas")
	private Double totalOrders;

	@ColumnDefault(value = "0")
	@Column(name = "total")
	private Double total;

	@ColumnDefault(value = "0")
	@Column(name = "totalDiscount")
	private Double totalDiscount;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private StatusPOSEnum status;

 
}
