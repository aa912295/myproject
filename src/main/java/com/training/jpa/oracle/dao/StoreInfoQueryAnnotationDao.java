package com.training.jpa.oracle.dao;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.training.jpa.model.GeographyStore;
import com.training.jpa.model.StoreGroupSalesList;
import com.training.jpa.model.StoreGroupSum;
import com.training.jpa.oracle.entity.StoreInfo;

@Repository
public interface StoreInfoQueryAnnotationDao extends JpaRepository<StoreInfo, Long>{
	
	@Query("SELECT s FROM StoreInfo s WHERE s.storeID IS NOT NULL")
	List<StoreInfo> queryAllStoreInfo();

	// Enhanced JPQL Syntax
	@Query("SELECT s FROM StoreInfo s WHERE s.geographyID = ?1")
	List<StoreInfo> queryByGeographyID(long geographyID);
	
	// Named Parameters
	@Query("SELECT s FROM StoreInfo s WHERE s.geographyID = :geographyID")
	List<StoreInfo> queryByParamGeographyID(@Param("geographyID") long geographyID);	
	
	// Native Queries
	@Query(value = "SELECT * FROM STORE_INFORMATION S WHERE S.GEOGRAPHY_ID = ?1", nativeQuery = true)
	List<StoreInfo> queryNativeByGeographyID(long geographyID);
	
	
	
	
	

	@Query(
		"SELECT new com.training.jpa.model.StoreGroupSum ( " +
		"	storeName, SUM(sales) " +
		") " +
		"FROM StoreInfo " +
		"GROUP BY storeName " +
		"ORDER BY SUM(sales) DESC"
	)
	List<StoreGroupSum> queryStoreGroupSumSales();
			
	@Query(
		"SELECT G.geographyID AS geographyID, G.regionName AS regionName, " +
		"S.storeID AS storeID, S.storeName AS storeName, S.sales AS sales, S.storeDate AS storeDate " +
		"FROM Geography G INNER JOIN StoreInfo S " +
//		"FROM Geography G LEFT JOIN StoreInfo S " +
//		"FROM Geography G RIGHT JOIN StoreInfo S " +
//		"FROM Geography G FULL JOIN StoreInfo S " +
		"ON G.geographyID = S.geographyID"
	)
	List<GeographyStore> queryGeographyJoinStore();
	
	@Query(value = 
			"SELECT STORE_NAME AS storeName, " +
			"  LISTAGG(SALES, '/') WITHIN GROUP (ORDER BY SALES) AS listSales " +
			"FROM STORE_INFORMATION " +
			"GROUP BY STORE_NAME",
		nativeQuery = true)
	List<StoreGroupSalesList> queryStoreGroupSalesList();
	
	
	
	
	
	

	// 新增只能使用 nativeQuery SQL不支援 JPQL
	@Transactional
	@Modifying	
	@Query(
		value = "INSERT INTO STORE_INFORMATION (STORE_ID, STORE_NAME, SALES, STORE_DATE, GEOGRAPHY_ID) " +
				"VALUES (?1, ?2, ?3, ?4, ?5)",
		nativeQuery = true
	)
	int insertStoreInfo(long storeID, String storeName, long sales, LocalDateTime storeDate, long geographyID);
	
	@Transactional
	@Modifying
	@Query(
		value = "UPDATE StoreInfo SET sales = ?1 WHERE geographyID = ?2"
	)
	int updateStoreInfo(long sales, long geographyID);
		
	@Transactional
	@Modifying
	@Query(
		value = "DELETE FROM StoreInfo WHERE storeID = ?1"
	)
	int deleteStoreInfo(long storeID);
	
}
