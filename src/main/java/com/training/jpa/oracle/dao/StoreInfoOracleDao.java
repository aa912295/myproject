package com.training.jpa.oracle.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.training.jpa.oracle.entity.StoreInfo;
import java.lang.String;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import java.lang.Long;

@Repository
public interface StoreInfoOracleDao extends JpaRepository<StoreInfo, Long>{

	List<StoreInfo> findByStoreNameAndGeographyID(String storename, long geographyID);
	
	List<StoreInfo> findByStoreNameOrGeographyID(String storename, long geographyID);
	
	List<StoreInfo> findByStoreNameEquals(String storename);
	
	List<StoreInfo> findByStoreNameNot(String storename);
	
	List<StoreInfo> findByStoreNameLike(String storename);
	
	List<StoreInfo> findByStoreNameStartingWith(String storename);
	
	List<StoreInfo> findByStoreNameNotLike(String storename);
	
	List<StoreInfo> findBySalesGreaterThanEqualAndSalesLessThanEqual(long startSales, long endSales);
	
	List<StoreInfo> findByStoreDateBetween(LocalDateTime startDate, LocalDateTime endDate);
	
	List<StoreInfo> findByStoreIDIn(Collection<Long> storeid);
	
	List<StoreInfo> findByStoreNameIgnoreCase(String storename);
	
	List<StoreInfo> findByStoreIDIsNotNullOrderByStoreIDDesc();
	
	List<StoreInfo> findByStoreIDIsNotNull(Pageable pageable);
	
	Optional<StoreInfo> findFirstByStoreNameOrderByStoreID(String storename);

}
