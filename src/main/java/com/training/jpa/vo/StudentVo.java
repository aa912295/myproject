package com.training.jpa.vo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.training.jpa.oracle.entity.Course;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
@ToString
public class StudentVo {
	
    private Long studentID;
    
	private String name;
    
	private String major;
	
	private String classYear;
	
    private List<Course> courses;
}
