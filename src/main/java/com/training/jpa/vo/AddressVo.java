package com.training.jpa.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
@ToString
public class AddressVo {
	
    private String city;

    private String area;

    private UserVo user;
}
