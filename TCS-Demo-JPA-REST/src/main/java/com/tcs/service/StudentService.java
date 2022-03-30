package com.tcs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcs.entity.AdminApproval;
import com.tcs.entity.Course;
import com.tcs.entity.Student;
import com.tcs.repository.AdminApprovalRepository;
import com.tcs.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentEntity;
	
	@Autowired
	AdminApprovalRepository adminEntity;
	
	private static Student studentEntityClass;
	{
		studentEntityClass = new Student();
	}
	private static AdminApproval adminApprovalEntityClass;
	{
		adminApprovalEntityClass = new AdminApproval();
	}
	
	/**
	 * Student registration.
	 * @Param student entity object
	 * @Throws
	 */
	@Transactional
	public Student addStudent(Student addStudent)
	{
		studentEntityClass = studentEntity.save(addStudent);
		adminApprovalEntityClass.setStatus("Not Approved");
		adminApprovalEntityClass.setStudentid(addStudent.getId());
		adminEntity.save(adminApprovalEntityClass);
		return studentEntityClass;
	}

}
