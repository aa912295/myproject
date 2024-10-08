package com.training.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@Data
@ToString
public class StoreGroupSum {
	
	private String storeName;
	
	private long sumSales;
}
