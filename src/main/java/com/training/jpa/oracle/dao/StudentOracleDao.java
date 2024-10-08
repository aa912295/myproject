package com.training.jpa.oracle.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.training.jpa.oracle.entity.Student;

@Repository
public interface StudentOracleDao extends JpaRepository<Student, Long>{

	
}
