package com.training.jpa.vo;

import java.util.List;
import javax.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
@ToString
public class GeographyVo {
	
	private long geographyID;
	
	private String regionName;
	
	@Min(value = 1, message = "StoreInfoVos greater than zero")
	private List<StoreInfoVo> storeInfoVos;
	
}
