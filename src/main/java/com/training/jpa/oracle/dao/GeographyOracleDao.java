package com.training.jpa.oracle.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.training.jpa.oracle.entity.Geography;

@Repository
public interface GeographyOracleDao extends JpaRepository<Geography, Long>{

	
}
