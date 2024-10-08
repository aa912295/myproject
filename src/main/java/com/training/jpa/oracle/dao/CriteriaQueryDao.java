package com.training.jpa.oracle.dao;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.persistence.Query;
import javax.persistence.criteria.Selection;
import org.springframework.stereotype.Repository;
import com.training.jpa.model.GeographyJoinStore;
import com.training.jpa.model.StoreGroupSum;
import com.training.jpa.model.StoreNewSales;
import com.training.jpa.oracle.entity.Geography;
import com.training.jpa.oracle.entity.StoreGroupSumMapping;
import com.training.jpa.oracle.entity.StoreInfo;
import com.training.jpa.vo.StoreInfoVo;


@Repository
public class CriteriaQueryDao {

	@PersistenceContext(name = "oracleEntityManager")
    private EntityManager entityManager;
	
	public List<StoreInfo> findStoreByGeographyAndSalesAndStoreDate(StoreInfoVo storeInfoVo) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<StoreInfo> cq = cb.createQuery(StoreInfo.class);
        Root<StoreInfo> storeInfo = cq.from(StoreInfo.class);
        
        // 商店地區編號 AND
        Predicate geographyID = cb.equal(storeInfo.get("geographyID"), storeInfoVo.getGeographyID());
        // 商店營業額 AND
        Predicate sales = cb.greaterThanOrEqualTo(storeInfo.get("sales"), storeInfoVo.getSales());
        // 商店營業日期  OR   
        Predicate storeDate = cb.greaterThan(storeInfo.get("storeDate"), storeInfoVo.getStoreDate());
        // 組合查尋條件
        Predicate restriction = cb.or(cb.and(geographyID, sales), storeDate);

        // 排序  ORDER BY
        Order order = cb.desc(storeInfo.get("sales"));        
        // 放入全部查詢條件
        // PS:select(storeInfo)可省略
        cq.select(storeInfo).where(restriction).orderBy(order);        
        
        // 執行查詢
        TypedQuery<StoreInfo> query = entityManager.createQuery(cq);
        /*
			SELECT * FROM STORE_INFORMATION
			WHERE GEOGRAPHY_ID = 2
			AND SALES >= 1500
			OR STORE_DATE > '2018-04-01 00:00:00'
			ORDER BY SALES DESC;
        */
        return query.getResultList();
	}
	
	public List<StoreNewSales> findStoreCaseWhenElse() {
		
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<StoreNewSales> cq = cb.createQuery(StoreNewSales.class);
        Root<StoreInfo> storeInfo = cq.from(StoreInfo.class);
        
        // 文章參考:
        // https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/criteria-api-case-expressions.html
        Selection<Long> newSales = cb.selectCase(storeInfo.get("storeName"))
        	.when("仁愛店", cb.prod(storeInfo.get("sales"), 2) )
        	.when("三和店", cb.prod(storeInfo.get("sales"), 1.5) )
        	.otherwise(storeInfo.get("sales")).as(Long.class).alias("newSales");

        cq.multiselect(
        	storeInfo.get("storeID"), storeInfo.get("storeName"), 
        	storeInfo.get("sales"), newSales
        );
        
        /*
			SELECT STORE_ID, STORE_NAME, SALES,
			   CASE STORE_NAME
			      WHEN '仁愛店' THEN SALES * 2
			      WHEN '三和店' THEN SALES * 1.5
			      ELSE SALES END NEW_SALES
			FROM STORE_INFORMATION
        */
        // 執行查詢
        TypedQuery<StoreNewSales> query = entityManager.createQuery(cq);

        return query.getResultList();
	}
	
	public List<StoreGroupSum> queryStoreGroupByAndHaving(long sales) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<StoreGroupSum> cq = cb.createQuery(StoreGroupSum.class);
        Root<StoreInfo> storeInfo = cq.from(StoreInfo.class);
        
        // 各別商店加總營業額 
        Expression<Long> sumSales = cb.sum(storeInfo.get("sales"));
        
        cq.multiselect(storeInfo.get("storeName"), sumSales)
          .groupBy(storeInfo.get("storeName"))
          .having(cb.greaterThan(sumSales, sales))
          .orderBy(cb.desc(sumSales));
        /* 
         SELECT STORE_NAME, SUM(SALES) SUM_SALES
         FROM STORE_INFORMATION
         GROUP BY STORE_NAME
         HAVING SUM(SALES) > 1000
         ORDER BY SUM(SALES) DESC
        */
        // 執行查詢
        TypedQuery<StoreGroupSum> query = entityManager.createQuery(cq);
        
        return query.getResultList();
	}
	
	public List<GeographyJoinStore> queryGeographyJoinStore(JoinType joinType) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<GeographyJoinStore> cq = cb.createQuery(GeographyJoinStore.class);
                
        Root<Geography> geography = cq.from(Geography.class);
        // Spring JPA 不支援 RIGHT JOIN
        Join<Geography, StoreInfo> join = geography.join("storeInfos", joinType);
        cq.multiselect(
	      	geography.get("geographyID"), geography.get("regionName"),
	      	join.get("storeID"), join.get("storeName"), 
	      	join.get("sales"), join.get("storeDate")	      	
        );
        
        // 執行查詢
        TypedQuery<GeographyJoinStore> query = entityManager.createQuery(cq);
        
        return query.getResultList();
	}

	
}
