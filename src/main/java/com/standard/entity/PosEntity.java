package com.standard.entity;

import com.standard.enums.StatusPOSEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "pos")
public class PosEntity extends BaseAuditEntity  {

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

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		PosEntity posEntity = (PosEntity) o;
		return Objects.equals(openDate, posEntity.openDate) && Objects.equals(openTime, posEntity.openTime) && Objects.equals(closeDate, posEntity.closeDate) && Objects.equals(
				closeTime, posEntity.closeTime) && Objects.equals(openAmount, posEntity.openAmount) && Objects.equals(closeAmount, posEntity.closeAmount) && Objects.equals(
				totalOrders, posEntity.totalOrders) && Objects.equals(total, posEntity.total) && Objects.equals(totalDiscount,
				posEntity.totalDiscount) && status == posEntity.status;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(super.hashCode(), openDate, openTime, closeDate, closeTime, openAmount, closeAmount, totalOrders, total, totalDiscount, status);
	}
}
