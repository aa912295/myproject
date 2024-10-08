package com.training.jpa.vo;

import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

@Data
public class UploadFileVo {
	
	private String fileName;
	
	private MultipartFile uploadFile;
	
}
