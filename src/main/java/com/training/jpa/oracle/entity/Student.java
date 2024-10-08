package com.training.jpa.oracle.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
@Entity
@Table(name = "STUDENT")
public class Student {

    @Id
    @Column(name = "STUDENT_ID")
    private Long studentID;
    
	@Column(name = "NAME")
	private String name;
    
	@Column(name = "MAJOR")
	private String major;
	
	@Column(name = "CLASSYEAR")
	private String classYear;
	
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
		name = "STUDENT_COURSE",
		joinColumns = @JoinColumn(name = "STUDENT_ID"), 
		inverseJoinColumns = @JoinColumn(name = "COURSE_ID")
	)
    private List<Course> courses;

}
