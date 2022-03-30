/**
 * 
 */
package com.tcs.restcontroller;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.entity.Grades;
import com.tcs.entity.Professor;
import com.tcs.entity.Student;
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
	
	/**
	 * Register Student.
	 * @Param course name
	 * @Throws
	 */
	@RequestMapping(value = "/studentRegistration", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity<String> addStudent(@RequestBody Student student){
		System.out.println(student);
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

}
