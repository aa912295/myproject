package com.training.jpa.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@Data
@ToString
public class GeographyJoinStore {
	
	private Long geographyID;
	
	private String regionName;
	
	private Long storeID;
	
	private String storeName;
	
	private Long sales;
	
	private LocalDateTime storeDate;
	
}
