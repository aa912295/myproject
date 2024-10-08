package com.training.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@Data
@ToString
public class StoreNewSales {
	
	private Long storeID;
	
	private String storeName;
	
	private Long sales;
	
	private Long newSales;
}
