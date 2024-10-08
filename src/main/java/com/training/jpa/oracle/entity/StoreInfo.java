package com.training.jpa.oracle.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import com.fasterxml.jackson.annotation.JsonIgnore;

@SuperBuilder
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode(of = {"storeID"})
@Entity
@Table(name = "STORE_INFORMATION", schema="LOCAL")
public class StoreInfo {

	@Id
	@Column(name = "STORE_ID")
	private Long storeID;
	
	@Column(name = "STORE_NAME")
	private String storeName;
	
	@Column(name = "SALES")
	private Long sales;
	
	@Column(name = "STORE_DATE")
	private LocalDateTime storeDate;

	@Column(name = "GEOGRAPHY_ID")
	private Long geographyID;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER) 
	@JoinColumn(name = "GEOGRAPHY_ID", insertable = false, updatable = false)
	private Geography geography;
	
}
