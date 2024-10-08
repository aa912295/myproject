package com.training.jpa.vo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.training.jpa.oracle.entity.Student;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
@ToString
public class CourseVo {
	
    private Long courseID;

	private String name;
    
	private String instructor;	
	
    private List<Student> students;
}
