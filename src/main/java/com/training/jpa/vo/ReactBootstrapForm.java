package com.training.jpa.vo;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

@Data
public class ReactBootstrapForm {	
	private String email;
	private String password;
	private String address;
	private String city;
	private int state;
	private String zip;
	private String radio;
	private List<String> checkbox;
	private String date;
	private String time;
	private String dateTimeLocal;	
	private String fileName;	
	private MultipartFile uploadFile;	
}
