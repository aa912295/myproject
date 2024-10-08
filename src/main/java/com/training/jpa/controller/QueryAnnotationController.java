package com.training.jpa.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.jpa.model.GeographyStore;
import com.training.jpa.model.StoreGroupSalesList;
import com.training.jpa.model.StoreGroupSum;
import com.training.jpa.oracle.dao.StoreInfoQueryAnnotationDao;
import com.training.jpa.oracle.entity.StoreInfo;
import com.training.jpa.vo.StoreInfoVo;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/QueryAnnotationController")
public class QueryAnnotationController {
	
	@Autowired
	private StoreInfoQueryAnnotationDao storeInfoDao;
	
	@ApiOperation(value = "JPA Query Annotation JPQL default")
	@GetMapping(value = "/queryAllStoreInfo")
	public ResponseEntity<List<StoreInfo>> queryAllStoreInfo() {
		List<StoreInfo> storeInfos = storeInfoDao.queryAllStoreInfo();
		
		return ResponseEntity.ok(storeInfos);
	}
	
	@ApiOperation(value = "JPA Query Annotation JPQL Enhanced JPQL Syntax")
	@GetMapping(value = "/queryByGeographyID")
	public ResponseEntity<List<StoreInfo>> queryByGeographyID(@RequestParam long geographyID) {
		List<StoreInfo> storeInfos = storeInfoDao.queryByGeographyID(geographyID);
		
		return ResponseEntity.ok(storeInfos);
	}
	
	@ApiOperation(value = "JPA Query Annotation JPQL Named Parameters")
	@GetMapping(value = "/queryByParamGeographyID")
	public ResponseEntity<List<StoreInfo>> queryByParamGeographyID(@RequestParam long geographyID) {
		List<StoreInfo> storeInfos = storeInfoDao.queryByParamGeographyID(geographyID);
		
		return ResponseEntity.ok(storeInfos);
	}
	
	@ApiOperation(value = "JPA Query Annotation Native Queries")
	@GetMapping(value = "/queryNativeByGeographyID")
	public ResponseEntity<List<StoreInfo>> queryNativeByGeographyID(@RequestParam long geographyID) {
		List<StoreInfo> storeInfos = storeInfoDao.queryNativeByGeographyID(geographyID);
		
		return ResponseEntity.ok(storeInfos);
	}

	
	
	
	
	

	@ApiOperation(value = "Customizing the Result with Class Constructors")
	@GetMapping(value = "/queryStoreGroupSumSales")
	public ResponseEntity<List<StoreGroupSum>> queryStoreGroupSumSales() {
		List<StoreGroupSum> storeGroupSums = storeInfoDao.queryStoreGroupSumSales();

		return ResponseEntity.ok(storeGroupSums);
	}

	@ApiOperation(value = "Customizing the Result with Spring Data Projection")
	@GetMapping(value = "/queryGeographyJoinStore")
	public ResponseEntity<List<GeographyStore>> queryGeographyJoinStore() {
		List<GeographyStore> geographyStores = storeInfoDao.queryGeographyJoinStore();
		
		return ResponseEntity.ok(geographyStores);
	}
	
	@ApiOperation(value = "Customizing the Result with Spring Data Projection")
	@GetMapping(value = "/queryStoreGroupSalesList")
	public ResponseEntity<List<StoreGroupSalesList>> queryStoreGroupSalesList() {
		List<StoreGroupSalesList> storeGroupSalesList = storeInfoDao.queryStoreGroupSalesList();
		
		return ResponseEntity.ok(storeGroupSalesList);
	}
	
	
	
	
	
	
	
	@ApiOperation(value = "JPA Query Annotation Modifiable Queries INSERT")
	@PostMapping(value = "/insertStoreInfo")
	public ResponseEntity<Integer> insertStoreInfo(@RequestBody StoreInfoVo storeInfoVo) {
		/*
	    {
			"storeID": 16,
		    "storeName": "重慶店",
		    "sales": 5600,
		    "storeDate": "2023-11-16T11:16:18.785Z",
		    "geographyID": 1
		}
		*/
		int insertCount = storeInfoDao.insertStoreInfo(storeInfoVo.getStoreID(), storeInfoVo.getStoreName(),
				storeInfoVo.getSales(), storeInfoVo.getStoreDate(), storeInfoVo.getGeographyID());
		
		return ResponseEntity.ok(insertCount);
	}
	
	@ApiOperation(value = "JPA Query Annotation Modifiable Queries UPDATE")
	@PutMapping(value = "/updateStoreInfo")
	public ResponseEntity<Integer> updateStoreInfo(@RequestParam long sales, @RequestParam long geographyID) {

		int updateCount = storeInfoDao.updateStoreInfo(sales, geographyID);
		
		return ResponseEntity.ok(updateCount);
	}
	
	@ApiOperation(value = "JPA Query Annotation Modifiable Queries DELETE")
	@DeleteMapping(value = "/deleteStoreInfo")
	public ResponseEntity<Integer> deleteStoreInfo(@RequestParam long storeID) {

		int deleteCount = storeInfoDao.deleteStoreInfo(storeID);
		
		return ResponseEntity.ok(deleteCount);
	}	
	
}
