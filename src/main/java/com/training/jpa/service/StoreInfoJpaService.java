package com.training.jpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.training.jpa.oracle.dao.GeographyOracleDao;
import com.training.jpa.oracle.dao.StudentOracleDao;
import com.training.jpa.oracle.dao.UserOracleDao;
import com.training.jpa.oracle.entity.Address;
import com.training.jpa.oracle.entity.Course;
import com.training.jpa.oracle.entity.Geography;
import com.training.jpa.oracle.entity.StoreInfo;
import com.training.jpa.oracle.entity.Student;
import com.training.jpa.oracle.entity.User;
import com.training.jpa.vo.AddressVo;
import com.training.jpa.vo.CourseVo;
import com.training.jpa.vo.GeographyVo;
import com.training.jpa.vo.StoreInfoVo;
import com.training.jpa.vo.StudentVo;
import com.training.jpa.vo.UserVo;


@Service
public class StoreInfoJpaService {
	
	private static Logger logger = LoggerFactory.getLogger(StoreInfoJpaService.class);
	
	@Autowired
	private GeographyOracleDao geographyOracleDao; 
	
	@Autowired
	private UserOracleDao userOracleDao;
	
	@Autowired
	private StudentOracleDao studentOracleDao;
	
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<User> findAllUserAndAddress() {	
		
		List<User> users = userOracleDao.findAll();		
      
		return users;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public User saveUserandAddress(UserVo userVo) {		
		User user = User.builder().userName(userVo.getUserName()).build();
		
		AddressVo addressVo = userVo.getAddress();
		Address address = Address.builder()
						.city(addressVo.getCity())
						.area(addressVo.getArea())
						.user(user)
						.build();

		user.setAddress(address);
 
		userOracleDao.save(user);	
		
		return user;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Geography> findAllGeography() {	
		
		List<Geography> geographys = geographyOracleDao.findAll();		
      
		return geographys;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public Geography saveMappedByGeography(GeographyVo geographyVo) {
		Geography geography = Geography.builder()
				.geographyID(geographyVo.getGeographyID())
				.regionName(geographyVo.getRegionName()).build();
		
		List<StoreInfo> storeInfos = new ArrayList<>();
		for(StoreInfoVo storeInfoVo : geographyVo.getStoreInfoVos()){
			StoreInfo storeInfo = StoreInfo.builder()
					.storeID(storeInfoVo.getStoreID())
					.storeName(storeInfoVo.getStoreName())	
					.sales(storeInfoVo.getSales())
					.storeDate(storeInfoVo.getStoreDate())  
					.geographyID(storeInfoVo.getGeographyID())
					.build();
			storeInfos.add(storeInfo);
		}
		geography.setStoreInfos(storeInfos);
		
		geography = geographyOracleDao.save(geography);
		
		return geography;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public Geography updateMappedByGeography(GeographyVo geographyVo) {
		Geography dbGeography = null;
		Optional<Geography> optGeography = geographyOracleDao.findById(geographyVo.getGeographyID());
		if(optGeography.isPresent()){
			dbGeography = optGeography.get();
			List<StoreInfo> newStoreInfos = new ArrayList<>();
			List<StoreInfo> dbStoreInfos = dbGeography.getStoreInfos();
			for(StoreInfoVo storeinfoVo : geographyVo.getStoreInfoVos()){
				StoreInfo storeInfo = StoreInfo.builder().storeID(storeinfoVo.getStoreID()).build();
				if(dbStoreInfos.contains(storeInfo)) {
					storeInfo = dbStoreInfos.get(dbStoreInfos.indexOf(storeInfo));
				}
				storeInfo.setStoreName(storeinfoVo.getStoreName());
				storeInfo.setSales(storeinfoVo.getSales());
				storeInfo.setStoreDate(storeinfoVo.getStoreDate());				
				storeInfo.setGeographyID(geographyVo.getGeographyID());
				newStoreInfos.add(storeInfo);
			}
			dbStoreInfos.clear();
			dbStoreInfos.addAll(newStoreInfos);
		}
		
		return dbGeography;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Student> findAllStudentAndCourse() {
		List<Student> students = studentOracleDao.findAll();
		
		return students;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public Student addStudentAndCourse(StudentVo studentVo) {
		Student student = Student.builder()
				.studentID(studentVo.getStudentID())
				.name(studentVo.getName())
				.major(studentVo.getMajor())
				.classYear(studentVo.getClassYear())
				.build();
		
		List<Course> courses = new ArrayList<>();
		for(Course courseVo : studentVo.getCourses()){
			Course course = Course.builder()
					.courseID(courseVo.getCourseID())
					.name(courseVo.getName())
					.instructor(courseVo.getInstructor())
					.build();
			courses.add(course);
		}
		student.setCourses(courses);
		
		student = studentOracleDao.save(student);
		
		return student;
	}
}
