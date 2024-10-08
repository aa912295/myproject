package com.training.jpa.oracle.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
@Entity
@Table(name = "COURSE")
public class Course {

    @Id
    @Column(name = "COURSE_ID")
    private Long courseID;

	@Column(name = "NAME")
	private String name;
    
	@Column(name = "INSTRUCTOR")
	private String instructor;	
	
	@JsonIgnore
    @ManyToMany(mappedBy = "courses")
    private List<Student> students;

}
