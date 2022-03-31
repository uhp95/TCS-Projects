/**
 * 
 */
package com.tcs.restcontroller;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.entity.Course;
import com.tcs.entity.FeePayment;
import com.tcs.entity.Grades;
import com.tcs.entity.Professor;
import com.tcs.entity.Student;
import com.tcs.entity.StudentCourseEmrollment;
import com.tcs.service.StudentService;

/**
 * @author springuser05
 *
 */
@RestController
@Controller
public class StudentController {
	
	@Autowired
	StudentService studentOperation;
	
	private static Student studentEntityClass;
	{
		studentEntityClass = new Student();
	}
	private static FeePayment feePaymentClass;
	{
		feePaymentClass = new FeePayment();
	}
	private static List<Course> course;
	{
		course = new ArrayList<Course>();
	}
	private static List<Professor> professor;
	{
		professor = new ArrayList<Professor>();
	}
	private static List<String> gradeDetails;
	{
		gradeDetails = new ArrayList<String>();
	}
	
	
	/**
	 * Register Student.
	 * @Param course name
	 * @Throws
	 */
	@RequestMapping(value = "/studentRegistration", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity<String> addStudent(@RequestBody Student student){
		
		studentEntityClass = studentOperation.addStudent(student);
		if(studentEntityClass.equals(null))
		{
			return new ResponseEntity<String>("Couldn't add details. please try again later.",HttpStatus.NOT_FOUND);
		}
		else
		{
		return new ResponseEntity<String>("Details added successfully",HttpStatus.OK);
		}
	}
	
	/**
	 * Course Registration by student.
	 * @Param student course enrollment object
	 * @Throws
	 */	
	@RequestMapping(value = "/studentCourseEnrollment/{id}", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity<String> courseRegistration(@RequestBody StudentCourseEmrollment courses,@PathVariable("id") long id){
		
		String response = studentOperation.courseRegistration(courses,id);
		return new ResponseEntity<String>(response,HttpStatus.OK);
		}
	
	/**
	 * Delete Courses by student.
	 * @Param student course enrollment object
	 * @Throws
	 */	
	@RequestMapping(value = "/studentCourseDeletion/{id}", method = RequestMethod.DELETE, produces= MediaType.APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity<String> courseDeletion(@RequestBody StudentCourseEmrollment courses,@PathVariable("id") long id){
		
		int response = studentOperation.courseDeletion(courses,id);
		if(response==0)
		{
		return new ResponseEntity<String>("Couldn't delete course. please try again",HttpStatus.NOT_FOUND);
		}
		else
			return new ResponseEntity<String>("Course dropped successfully",HttpStatus.OK);

}
	
	/**
	 * Fetches all the courses
	 * @Param 
	 * @Throws
	 */
	@RequestMapping(value = "/coursesDetail", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON)
	@ResponseBody
	public  List<Course> getAll() {
		course= studentOperation.getAllCourses();
		return course;

	}
	
	/**
	 * Fetches professor details for the courses
	 * @Param 
	 * @Throws
	 */
	@RequestMapping(value = "/professorDetailsForTheCourses/{id}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON)
	@ResponseBody
	public  ResponseEntity<List<Professor>> getProfessorDetails(@PathVariable("id") int id) {
		
		professor= studentOperation.getProfessorDetails(id);
		return new ResponseEntity( professor, HttpStatus.OK);

	}
	
	/**
	 * Payment details for the courses
	 * @Param 
	 * @Throws
	 */
	@RequestMapping(value = "/paymentDetails/{id}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON)
	@ResponseBody
	public  ResponseEntity<FeePayment> paymentDetails(@PathVariable("id") int id,@RequestBody FeePayment payment) {
		
		payment.setUserid(id);
		payment.setDateOfPayment(new Date());
		feePaymentClass= studentOperation.addPaymentDetails(payment);
		return new ResponseEntity( feePaymentClass, HttpStatus.OK);

	}
	
	/**
	 * Fetch Grade Details
	 * @Param 
	 * @Throws
	 */
	@RequestMapping(value = "/grades/{id}/{sem}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON)
	@ResponseBody
	public  ResponseEntity<String> getGradeDetails(@PathVariable("id") long studentid,@PathVariable("sem") String sem) {
		
		gradeDetails= studentOperation.getGradeDetails(studentid,sem);
		return new ResponseEntity( gradeDetails, HttpStatus.OK);

	}
	
}

