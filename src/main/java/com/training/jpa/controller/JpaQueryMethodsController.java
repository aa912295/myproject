package com.training.jpa.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.training.jpa.oracle.entity.StoreInfo;
import com.training.jpa.service.StoreInfoJpaQueryMethodsService;
import com.training.jpa.vo.DateRang;
import com.training.jpa.vo.StoreInfoVo;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/JpaQueryMethodsController")
public class JpaQueryMethodsController {

	@Autowired
	private StoreInfoJpaQueryMethodsService storeInfoJpaQueryMethodsService; 
	
	@ApiOperation(value = "JPA Query Methods And")
	@GetMapping(value = "/findByStoreNameAndGeographyID")
	public ResponseEntity<List<StoreInfo>> findByStoreNameAndGeographyID(
			@RequestParam String storename, @RequestParam long geographyID) {

		List<StoreInfo> storeInfos = storeInfoJpaQueryMethodsService.findByStoreNameAndGeographyID(storename, geographyID);
		
		return ResponseEntity.ok(storeInfos);
	}
	
	@ApiOperation(value = "JPA Query Methods Or")
	@GetMapping(value = "/findByStoreNameOrGeographyID")
	public ResponseEntity<List<StoreInfo>> findByStoreNameOrGeographyID(
				@RequestParam String storename, @RequestParam long geographyID) {

		List<StoreInfo> storeInfos = storeInfoJpaQueryMethodsService.findByStoreNameOrGeographyID(storename, geographyID);
		
		return ResponseEntity.ok(storeInfos);
	}
	
	@ApiOperation(value = "JPA Query Methods Equals")
	@GetMapping(value = "/findByStoreNameEquals")
	public ResponseEntity<List<StoreInfo>> findByStoreNameEquals(@RequestParam String storename) {

		List<StoreInfo> storeInfos = storeInfoJpaQueryMethodsService.findByStoreNameEquals(storename);
		
		return ResponseEntity.ok(storeInfos);
	}
	
	@ApiOperation(value = "JPA Query Methods NotEquals")
	@GetMapping(value = "/findByStoreNameNot")
	public ResponseEntity<List<StoreInfo>> findByStoreNameNot(@RequestParam String storename) {

		List<StoreInfo> storeInfos = storeInfoJpaQueryMethodsService.findByStoreNameNot(storename);
		
		return ResponseEntity.ok(storeInfos);
	}
	
	@ApiOperation(value = "JPA Query Methods Like")
	@GetMapping(value = "/findByStoreNameLike")
	public ResponseEntity<List<StoreInfo>> findByStoreNameLike(@RequestParam String storename) {

		List<StoreInfo> storeInfos = storeInfoJpaQueryMethodsService.findByStoreNameLike(storename);
		
		return ResponseEntity.ok(storeInfos);
	}
	
	@ApiOperation(value = "JPA Query Methods StartingWith Like")//EndingWith Containing
	@GetMapping(value = "/findByStoreNameStartingWithLike")
	public ResponseEntity<List<StoreInfo>> findByStoreNameStartingWith(@RequestParam String storename) {

		List<StoreInfo> storeInfos = storeInfoJpaQueryMethodsService.findByStoreNameStartingWith(storename);
		
		return ResponseEntity.ok(storeInfos);
	}
	
	@ApiOperation(value = "JPA Query Methods Not Like")
	@GetMapping(value = "/findByStoreNameNotLike")
	public ResponseEntity<List<StoreInfo>> findByStoreNameNotLike(@RequestParam String storename) {

		List<StoreInfo> storeInfos = storeInfoJpaQueryMethodsService.findByStoreNameNotLike(storename);
		
		return ResponseEntity.ok(storeInfos);
	}
	
	@ApiOperation(value = "JPA Query Methods GreaterThan、LessThan")
	@GetMapping(value = "/findBySalesGreaterThanEqualAndSalesLessThanEqual")
	public ResponseEntity<List<StoreInfo>> findBySalesGreaterThanEqualAndSalesLessThanEqual(
			@RequestParam long startSales, @RequestParam long endSales) {

		List<StoreInfo> storeInfos = storeInfoJpaQueryMethodsService.findBySalesGreaterThanEqualAndSalesLessThanEqual(startSales, endSales);
		
		return ResponseEntity.ok(storeInfos);
	}
	
	
	@ApiOperation(value = "JPA Query Methods Between")
	@PostMapping(value = "/findByStoreDateBetween")
	public ResponseEntity<List<StoreInfo>> findByStoreDateBetween(@RequestBody DateRang dateRang) {
		/*
			{
			  "startDate": "2022-03-01T00:00:00",
			  "endDate": "2023-05-30T00:00:00"
			}
		 */
		List<StoreInfo> storeInfos = storeInfoJpaQueryMethodsService.findByStoreDateBetween(dateRang.getStartDate(), dateRang.getEndDate());
		
		return ResponseEntity.ok(storeInfos);
	}
	
	
	@ApiOperation(value = "JPA Query Methods IN")
	@GetMapping(value = "/findByStoreIDIn")
	public ResponseEntity<List<StoreInfo>> findByStoreIDIn(@RequestParam List<Long> storeIDs) {
		List<StoreInfo> storeInfos = storeInfoJpaQueryMethodsService.findByStoreIDIn(storeIDs);
				
		return ResponseEntity.ok(storeInfos);
	}
	
	@ApiOperation(value = "JPA Query Methods IgnoreCase")
	@GetMapping(value = "/findByStoreNameIgnoreCase")
	public ResponseEntity<List<StoreInfo>> findByStoreNameIgnoreCase(@RequestParam String storename) {

		List<StoreInfo> storeInfos = storeInfoJpaQueryMethodsService.findByStoreNameIgnoreCase(storename);
				
		return ResponseEntity.ok(storeInfos);
	}
	
	@ApiOperation(value = "JPA Query Methods Order by asc desc")
	@GetMapping(value = "/findOrderByStoreIDDesc")
	public ResponseEntity<List<StoreInfo>> findOrderByStoreIDDesc() {
		List<StoreInfo> storeInfos = storeInfoJpaQueryMethodsService.findByStoreIDIsNotNullOrderByStoreIDDesc();
				
		return ResponseEntity.ok(storeInfos);
	}
	
	@ApiOperation(value = "JPA Query Methods Pagination and Sorting")
	@GetMapping(value = "/findStorePageable")
	public ResponseEntity<List<StoreInfo>> findStorePageable() {
		// PageRequest.of(int page, int size, Sort sort)
		// page 頁數從零開始代表第一頁
		
		Pageable pageable = PageRequest.of(0, 3, 
			Sort.by("sales").descending()
			.and(Sort.by("storeID").ascending())
		);
		List<StoreInfo> storeInfos = storeInfoJpaQueryMethodsService.findByStoreIDIsNotNull(pageable);
				
		return ResponseEntity.ok(storeInfos);
	}
	
	@ApiOperation(value = "JPA Query Methods Find First")
	@GetMapping(value = "/findFirstByStoreNameOrderByStoreID")
	public ResponseEntity<StoreInfo> findFirstByStoreNameOrderByStoreID(@RequestParam String storename) {
		Optional<StoreInfo> optStoreInfo = storeInfoJpaQueryMethodsService.findFirstByStoreNameOrderByStoreID(storename);	

		return ResponseEntity.ok(optStoreInfo.get());
	}

}
