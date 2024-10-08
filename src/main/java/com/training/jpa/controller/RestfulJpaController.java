package com.training.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.jpa.oracle.entity.Geography;
import com.training.jpa.oracle.entity.StoreInfo;
import com.training.jpa.oracle.entity.Student;
import com.training.jpa.oracle.entity.User;
import com.training.jpa.service.StoreInfoJpaService;
import com.training.jpa.vo.GeographyVo;
import com.training.jpa.vo.StudentVo;
import com.training.jpa.vo.UserVo;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/restfulJpaController")
public class RestfulJpaController {
	
	@Autowired
	private StoreInfoJpaService storeInfoJpaService;
	
	@ApiOperation(value = "查詢所有使用者及區域 @雙向OneToOne")
	@GetMapping(value = "/findAllUserAndAddress")
	public ResponseEntity<List<User>> findAllUserAndAddress() {
		List<User> users = storeInfoJpaService.findAllUserAndAddress();
		
		return ResponseEntity.ok(users);
	}
	
	@ApiOperation(value = "新增使用者及區域 @雙向OneToOne")
	@PostMapping(value = "/saveUserAndAddress")
	public ResponseEntity<User> saveUserandAddress(@RequestBody UserVo userVo) {
		/*
			{
			  "userName": "蔡英文",
			  "address": {
			    "city": "台北市",
			    "area": "士林區"
			  }
			}
			
		*/
		User user = storeInfoJpaService.saveUserandAddress(userVo);
		
		return ResponseEntity.ok(user);
	}
	
	@ApiOperation(value = "查詢所有區域 @雙向OneToMany")
	@GetMapping(value = "/findAllGeography")
	public ResponseEntity<List<Geography>> findAllGeography() {
		List<Geography> geographys = storeInfoJpaService.findAllGeography();
		
		return ResponseEntity.ok(geographys);
	}
	
	@ApiOperation(value = "新增區域商店 @雙向OneToMany")
	@PostMapping(value = "/saveMappedByInverseGeography")
	public ResponseEntity<Geography> saveMappedByGeography(@RequestBody GeographyVo geographyVo) {
		/*
			{
			  "geographyID": 4,
			  "regionName": "土城區",
			  "storeInfoVos": [
			    {
			      "storeID": 14,
			      "storeName": "中華店",
			      "sales": 8000,
			      "storeDate": "2023-11-16T11:16:18.785Z"
			    },
			    {
			      "storeID": 15,
			      "storeName": "金城店",
			      "sales": 7000,
			      "storeDate": "2023-11-16T11:16:18.785Z"
			    },
			    {
			      "storeID": 16,
			      "storeName": "學府店",
			      "sales": 6000,
			      "storeDate": "2023-11-16T11:16:18.785Z"
			    }
			  ]
			}
		 */
		Geography geography = storeInfoJpaService.saveMappedByGeography(geographyVo);
		
		return ResponseEntity.ok(geography);
	}
	
	@ApiOperation(value = "更新區域商店 @雙向OneToMany @PutMapping")
	@PutMapping(value = "/updateMappedByGeography")
	public ResponseEntity<Geography> updateMappedByGeography(@RequestBody GeographyVo geographyVo) {
		/*
			{
			  "geographyID": 4,
			  "regionName": "土城區",
			  "storeInfoVos": [
			    {
			      "storeID": 14,
			      "storeName": "中華店",
			      "sales": 8800,
			      "storeDate": "2023-12-12T11:16:18.785Z"
			    },
			    {
			      "storeID": 15,
			      "storeName": "金城店",
			      "sales": 7700,
			      "storeDate": "2023-12-12T11:16:18.785Z"
			    },
			    {
			      "storeID": 17,
			      "storeName": "裕民店",
			      "sales": 6000,
			      "storeDate": "2023-11-11T11:16:18.785Z"
			    },
			    {
			      "storeID": 18,
			      "storeName": "中央店",
			      "sales": 6000,
			      "storeDate": "2023-11-11T11:16:18.785Z"
			    }
			  ]
			}
			
			更新結果：
			UPDATE 2筆 (14,15)
			INSERT 2筆 (17,18)
			DELETE 1筆 (16)
		 */
		Geography geography = storeInfoJpaService.updateMappedByGeography(geographyVo);
		
		return ResponseEntity.ok(geography);
	}
	
	@ApiOperation(value = "查詢所有學生以及課程 @ManyToMany")
	@GetMapping(value = "/findAllStudentAndCourse")
	public ResponseEntity<List<Student>> findAllStudentAndCourse() {
		List<Student> students = storeInfoJpaService.findAllStudentAndCourse();
		
		return ResponseEntity.ok(students);
	}
	
	@ApiOperation(value = "新增學生以及課程 @ManyToMany")
	@PostMapping(value = "/addStudentAndCourse")
	public ResponseEntity<Student> addStudentAndCourse(@RequestBody StudentVo studentVo) {
		/*
		{
			  "studentID": 7,
			  "name": "曹操",
			  "major": "機械工程",
			  "classYear": "大四",
			  "courses": [
			    {
			      "courseID": 1,
			      "name": "國文",
			      "instructor": "朱媽"
			    }
			  ]
			}
		 */

		Student student = storeInfoJpaService.addStudentAndCourse(studentVo);
		
		return ResponseEntity.ok(student);
	}
}
