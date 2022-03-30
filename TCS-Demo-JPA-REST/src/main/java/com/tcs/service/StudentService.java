package com.tcs.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcs.entity.AdminApproval;
import com.tcs.entity.Course;
import com.tcs.entity.Student;
import com.tcs.entity.UserManagement;
import com.tcs.repository.AdminApprovalRepository;
import com.tcs.repository.StudentRepository;
import com.tcs.repository.UserManagementRepository;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentEntity;
	
	@Autowired
	AdminApprovalRepository adminEntity;
	
	@Autowired
	UserManagementRepository userEntity;
	
	private static Student studentEntityClass;
	{
		studentEntityClass = new Student();
	}
	private static AdminApproval adminApprovalEntityClass;
	{
		adminApprovalEntityClass = new AdminApproval();
	}
	private static UserManagement userManagementEntityClass;
	{
		userManagementEntityClass = new UserManagement();
	}
	/**
	 * Student registration.
	 * @Param student entity object
	 * @Throws
	 */
	@Transactional
	public Student addStudent(Student addStudent)
	{
		Date date = new Date();
		studentEntityClass = studentEntity.save(addStudent);
		adminApprovalEntityClass.setStatus("Not Approved");
		adminApprovalEntityClass.setStudentid(addStudent.getId());
		adminEntity.save(adminApprovalEntityClass);
		userManagementEntityClass.setRoleid(3);
		userManagementEntityClass.setStatus("In Active");
		userManagementEntityClass.setUserid(addStudent.getId());
		userManagementEntityClass.setLogintime(date);
		userEntity.save(userManagementEntityClass);
		return studentEntityClass;
	}
	
	/**
	 * Student setting username and password
	 * @Param student entity object
	 * @Throws
	 */
	
}
