package com.training.jpa.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.training.jpa.oracle.dao.StoreInfoOracleDao;
import com.training.jpa.oracle.entity.StoreInfo;

@Service
public class StoreInfoJpaQueryMethodsService {
	
	private static Logger logger = LoggerFactory.getLogger(StoreInfoJpaService.class);
	
	@Autowired
	private StoreInfoOracleDao storeInfoOracleDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<StoreInfo> findByStoreNameAndGeographyID(String storename, long geographyID) {	
		
		List<StoreInfo> storeInfos = storeInfoOracleDao.findByStoreNameAndGeographyID(storename, geographyID);		
      
		return storeInfos;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<StoreInfo> findByStoreNameOrGeographyID(String storename, long geographyID) {	
		
		List<StoreInfo> storeInfos = storeInfoOracleDao.findByStoreNameOrGeographyID(storename, geographyID);		
      
		return storeInfos;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<StoreInfo> findByStoreNameEquals(String storename) {
		List<StoreInfo> storeInfos = storeInfoOracleDao.findByStoreNameEquals(storename);		
	      
		return storeInfos;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<StoreInfo> findByStoreNameNot(String storename) {
		List<StoreInfo> storeInfos = storeInfoOracleDao.findByStoreNameNot(storename);		
	      
		return storeInfos;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<StoreInfo> findByStoreNameLike(String storename) {
		List<StoreInfo> storeInfos = storeInfoOracleDao.findByStoreNameLike(storename);		
	      
		return storeInfos;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<StoreInfo> findByStoreNameNotLike(String storename) {
		List<StoreInfo> storeInfos = storeInfoOracleDao.findByStoreNameNotLike(storename);		
	      
		return storeInfos;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<StoreInfo> findByStoreNameStartingWith(String storename) {
		List<StoreInfo> storeInfos = storeInfoOracleDao.findByStoreNameStartingWith(storename);		
	      
		return storeInfos;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<StoreInfo> findBySalesGreaterThanEqualAndSalesLessThanEqual(long startSales, long endSales) {
		List<StoreInfo> storeInfos = storeInfoOracleDao.findBySalesGreaterThanEqualAndSalesLessThanEqual(startSales, endSales);		
	      
		return storeInfos;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<StoreInfo> findByStoreDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
		List<StoreInfo> storeInfos = storeInfoOracleDao.findByStoreDateBetween(startDate, endDate);
				
		return storeInfos;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<StoreInfo> findByStoreIDIn(List<Long> storeIDs) {
		List<StoreInfo> storeInfos = storeInfoOracleDao.findByStoreIDIn(storeIDs);
		
		return storeInfos;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<StoreInfo> findByStoreNameIgnoreCase(String storename) {
		List<StoreInfo> storeInfos = storeInfoOracleDao.findByStoreNameIgnoreCase(storename);
		
		return storeInfos;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<StoreInfo> findByStoreIDIsNotNullOrderByStoreIDDesc() {
		List<StoreInfo> storeInfos = storeInfoOracleDao.findByStoreIDIsNotNullOrderByStoreIDDesc();
		
		return storeInfos;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Optional<StoreInfo> findFirstByStoreNameOrderByStoreID(String storename) {
		Optional<StoreInfo> optStoreInfo = storeInfoOracleDao.findFirstByStoreNameOrderByStoreID(storename);
		
		return optStoreInfo;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<StoreInfo> findByStoreIDIsNotNull(Pageable pageable) {
		List<StoreInfo> storeInfos = storeInfoOracleDao.findByStoreIDIsNotNull(pageable);
		
		return storeInfos;
	}
	

}
