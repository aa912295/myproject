package com.training.jpa.vo;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
@ToString
public class StoreInfoVo {

	private long storeID;	

	private String storeName;	

	private long sales;

	private LocalDateTime storeDate;

	private Long geographyID;
	
}
