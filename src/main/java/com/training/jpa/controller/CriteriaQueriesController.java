package com.training.jpa.controller;

import java.util.List;
import javax.persistence.criteria.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.training.jpa.model.GeographyJoinStore;
import com.training.jpa.model.StoreGroupSum;
import com.training.jpa.model.StoreNewSales;
import com.training.jpa.oracle.dao.CriteriaQueryDao;
import com.training.jpa.oracle.entity.StoreGroupSumMapping;
import com.training.jpa.oracle.entity.StoreInfo;
import com.training.jpa.vo.StoreInfoVo;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/CriteriaQueriesController")
public class CriteriaQueriesController {

	@Autowired
	private CriteriaQueryDao criteriaQueryDao;
	
	@ApiOperation(value = "Spring data jpa Criteria Queries : AND、OR")
	@PostMapping(value = "/findStoreByGeographyAndSalesAndStoreDate")
	public ResponseEntity<List<StoreInfo>> findStoreByGeographyAndSalesAndStoreDate(@RequestBody StoreInfoVo storeInfoVo) {
		/*
		{
		  "geographyID": 2,
		  "sales": 1500,
		  "storeDate": "2018-04-01T00:00:00"
		}
	    */
		List<StoreInfo> storeInfos = criteriaQueryDao.findStoreByGeographyAndSalesAndStoreDate(storeInfoVo);
		
		return ResponseEntity.ok(storeInfos);
	}
	
	@ApiOperation(value = "Spring data jpa Criteria Queries : CASE WHEN ELSE")
	@GetMapping(value = "/findStoreCaseWhenElse")
	public ResponseEntity<List<StoreNewSales>> findStoreCaseWhenElse() {
		List<StoreNewSales> storeNewSales = criteriaQueryDao.findStoreCaseWhenElse();
		
		return ResponseEntity.ok(storeNewSales);
	}
	
	
	@ApiOperation(value = "Spring data jpa Criteria Queries : GROUP BY、HAVING")
	@GetMapping(value = "/queryStoreGroupByAndHaving")
	public ResponseEntity<List<StoreGroupSum>> queryStoreGroupByAndHaving(@RequestParam long sales) {

		List<StoreGroupSum> storeGroupSums = criteriaQueryDao.queryStoreGroupByAndHaving(sales);
		
		return ResponseEntity.ok(storeGroupSums);
	}
	
	@ApiOperation(value = "Spring data jpa Criteria Queries : INNER JOIN、OUTER JOIN")
	@GetMapping(value = "/queryGeographyJoinStore")
	public ResponseEntity<List<GeographyJoinStore>> queryGeographyJoinStore(@RequestParam JoinType joinType) {
		List<GeographyJoinStore> geographyJoinStores = criteriaQueryDao.queryGeographyJoinStore(joinType);
		
		return ResponseEntity.ok(geographyJoinStores);
	}
	



}
