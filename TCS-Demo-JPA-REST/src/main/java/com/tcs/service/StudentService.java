package com.tcs.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcs.entity.AdminApproval;
import com.tcs.entity.Course;
import com.tcs.entity.FeePayment;
import com.tcs.entity.Grades;
import com.tcs.entity.Professor;
import com.tcs.entity.Student;
import com.tcs.entity.StudentCourseEmrollment;
import com.tcs.entity.StudentGrades;
import com.tcs.entity.UserManagement;
import com.tcs.repository.AdminApprovalRepository;
import com.tcs.repository.CourseRepository;
import com.tcs.repository.FeePaymentRepository;
import com.tcs.repository.GradeRepository;
import com.tcs.repository.ProfessorRepository;
import com.tcs.repository.StudentCourseEnrollmentRepository;
import com.tcs.repository.StudentGradeRepository;
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
	
	@Autowired
	StudentCourseEnrollmentRepository studentEnrollmentEntity;
	
	@Autowired
	CourseRepository courseEntity;
	
	@Autowired
	ProfessorRepository professorseEntity;
	
	@Autowired
	FeePaymentRepository feePaymentEntity;
	
	@Autowired
	StudentGradeRepository studentGradeEntity;
	
	@Autowired
	GradeRepository GradeEntity;
	
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
	private static StudentCourseEmrollment studentEnrollmentEntityClass;
	{
		studentEnrollmentEntityClass = new StudentCourseEmrollment();
	}
	private static Grades grade;
	{
		grade = new Grades();
	}
	private static List<Course> c;
	{
		c = new ArrayList<Course>();
	}
	private static List<StudentCourseEmrollment> studentEnrollmentEntityList;
	{
		studentEnrollmentEntityList = new ArrayList<StudentCourseEmrollment>();
	}
	private static List<Long> studentList;
	{
		studentList = new ArrayList<Long>();
	}
	private static List<Course> courseEntityList;
	{
		courseEntityList = new ArrayList<Course>();
	}
	private static List<Professor> professorEntityList;
	{
		professorEntityList = new ArrayList<Professor>();
	}
	private static List<Long> listOfProfessorid;
	{
		listOfProfessorid = new ArrayList<Long>();
	}
	private static FeePayment payments;
	{
		payments = new FeePayment();
	}
	private static List<String> gradeDetails;
	{
		gradeDetails = new ArrayList<String>();
	}
	private static List<StudentGrades> studentGradeEntityList;
	{
		studentGradeEntityList = new ArrayList<StudentGrades>();
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
	 * Student course enrollment
	 * @Param student enrollment entity object
	 * @Throws
	 */
	@Transactional
	public String courseRegistration(StudentCourseEmrollment courses, long id)
	{
		Date date = new Date();
		courses.setDateOfEnrollment(date);
		courses.setStudentId(id);
		studentList = studentEnrollmentEntity.FetchStudentsAssignedToCourses(courses.getCourseId());
		System.out.println(studentEnrollmentEntityList.size());
		if(studentEnrollmentEntity.FetchNumberOfCoursesAssignedToStudent(id) == 6)
		{
			return "Maximum courses enrolled";
		}
		else
		{
		if(courses.getOptional().equals("Yes"))
		{
			if(studentEnrollmentEntity.FetchCourseAssignedToStudent(id, courses.getCourseId())==0)
			{
			studentEnrollmentEntity.save(courses);
			return "Course Enrolled successfully as Optional";
			}
			else
				return "Course Already enrolled";
		}
		else
				{
					if(studentList.size()>=10)
						{
							return "Maximum Enrollments are completed. Please select the course as optional";
						}
					else
						{
							if(studentEnrollmentEntity.FetchCourseAssignedToStudent(id, courses.getCourseId())==0)
							{
							studentEnrollmentEntity.save(courses);
							return "Course Enrolled successfully";
							}
							else
								return "Course Already enrolled";
							
							
						}
				}
	}
	}
	
	
	/**
	 * Student course deletion
	 * @Param student enrollment entity object
	 * @Throws
	 */
	@Transactional
	public int courseDeletion(StudentCourseEmrollment courses, long id)
	{
		courses.setStudentId(id);
		studentEnrollmentEntity.delete(courses);
		return 1;
	}
	
	/**
	 * Fetches all the courses
	 * @Param 
	 * @Throws
	 */
	@Transactional
	public List<Course> getAllCourses()
	{
		courseEntityList= (List<Course>) courseEntity.findAll();
		return courseEntityList;
	}
	
	/**
	 * Fetches professor details for the courses
	 * @Param 
	 * @Throws
	 */
	@Transactional
	public List<Professor> getProfessorDetails(int courseid)
	{
		listOfProfessorid = courseEntity.getProfessorById(courseid);
		professorEntityList= (List<Professor>) professorseEntity.findAllById(listOfProfessorid);
		return professorEntityList;
	}

	
	/**
	 * Fetches professor details for the courses
	 * @Param 
	 * @Throws
	 */
	@Transactional
	public FeePayment addPaymentDetails(FeePayment payment)
	{
		payment = feePaymentEntity.save(payment);
		return payment;
	}
	
	/**
	 * Fetches grades for the courses
	 * @Param 
	 * @Throws
	 */
	@Transactional
	public List<String> getGradeDetails(long studentid, String sem)
	{
		//gradeDetails = studentGradeEntity.
		studentGradeEntityList	= studentGradeEntity.getGradeDetails(studentid, sem);
		for(StudentGrades sg: studentGradeEntityList)
		{
			String g= GradeEntity.getGradeDetails((int)sg.getGradeid());
			String c= courseEntity.getCourseName(sg.getCourseid());
			gradeDetails.add("You Have Scored "+g+" in "+c);
		}
		return gradeDetails;
	}

}
