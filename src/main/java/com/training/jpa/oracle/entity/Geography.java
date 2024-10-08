package com.training.jpa.oracle.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
@ToString(exclude = {"storeInfos"})
@Entity
@Table(name = "GEOGRAPHY", schema="LOCAL")
public class Geography {
	
	@Id
	@Column(name = "GEOGRAPHY_ID")
	private Long geographyID;
	
	@Column(name = "REGION_NAME")
	private String regionName;
	
	@OneToMany(
		mappedBy = "geography",
		cascade = {CascadeType.ALL}, orphanRemoval = true,
		fetch = FetchType.LAZY
	)
	@OrderBy(value = "storeID")
	private List<StoreInfo> storeInfos;

	
}
