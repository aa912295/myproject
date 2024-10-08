package com.training.jpa.oracle.entity;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import lombok.Data;

@SqlResultSetMapping(
    name = "StoreGroupSumMapping",
    entities={
        @EntityResult(
	        entityClass = com.training.jpa.oracle.entity.StoreGroupSumMapping.class,
	        fields = {
	        	@FieldResult(name="storeName", column="STORE_NAME"),
	            @FieldResult(name="sumSales",  column="SUM_SALES")
	        }
        )
    }
)
@Data
@Entity
public class StoreGroupSumMapping {
	
	@Id
	private String storeName;
	
	private long sumSales;
	
}
