package com.training.jpa.model;

import java.time.LocalDateTime;

public interface GeographyStore {
	
	Long getGeographyID();
	
	String getRegionName();
	
	Long getStoreID();
	
	String getStoreName();
	
	Long getSales();
	
	LocalDateTime getStoreDate();
	
}
