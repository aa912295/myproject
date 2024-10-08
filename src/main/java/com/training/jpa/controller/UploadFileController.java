package com.training.jpa.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.training.jpa.vo.ReactBootstrapForm;
import com.training.jpa.vo.UploadFileVo;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping("/uploadFileController")
public class UploadFileController {

	@ApiOperation(value = "Spring boot form-data multipart MultipartFile")
	@PostMapping(value = "/uploadFile", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<String> uploadFile(@ModelAttribute UploadFileVo uploadFileVo) throws IOException {		

		MultipartFile file = uploadFileVo.getUploadFile();

		//String fileName = file.getOriginalFilename();

		String fileName = uploadFileVo.getFileName();
		
		Files.copy(file.getInputStream(), Paths.get("/home/VendingMachine/DrinksImage").resolve(fileName));
		
		return ResponseEntity.ok("http://localhost:8085/training/goodsImg/" + fileName);
	}
	
//	@ApiOperation(value = "Spring boot react-Bootstrap-form-data multipart MultipartFile")
//	@PostMapping(value = "/reactBootstrapForm", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
//	public ResponseEntity<ReactBootstrapForm> reactBootstrapForm(@ModelAttribute ReactBootstrapForm formData) 
//			throws IOException {		
//		 //複制檔案
//		MultipartFile file = formData.getUploadFile();
//		 //上傳檔案原始名稱
//		//String fileName = file.getOriginalFilename();
//		 //前端傳入檔案名稱
//		String fileName = formData.getFileName();
//		
//		Files.copy(file.getInputStream(), Paths.get("/home/VendingMachine/DrinksImage").resolve(fileName));
//		formData.setUploadFile(null);
//		
//		return ResponseEntity.ok(formData);
//	}
//	
}

