/**
 * 
 */
package com.tcs.restcontroller;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.entity.Student;
import com.tcs.entity.StudentGrades;
import com.tcs.repository.StudentGradeRepository;
import com.tcs.repository.StudentRepository;
import com.tcs.service.ProfessorService;

/**
 * @author springuser05
 *
 */
@RestController
public class ProfessorController {
	
	@Autowired
	ProfessorService professorOperation;
	
	private static StudentGrades studentGradeEntityClass;
	{
		studentGradeEntityClass = new StudentGrades();
	}
	
	/**
	 * Add Grades to students.
	 * @Param course name
	 * @Throws
	 */
	@RequestMapping(value = "/addGradesByProfessor/{id}", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity<String> addGradesByProfessor(@PathVariable("id") long id,@RequestBody StudentGrades studentGrades){
	
		studentGradeEntityClass=professorOperation.addGradesByProfessor(studentGrades,id);
		if(studentGradeEntityClass.equals(null))
		{
			return new ResponseEntity<String>("Couldn't add details. please try again later.",HttpStatus.NOT_FOUND);
		}
		else
		{
		return new ResponseEntity<String>("Details added successfully",HttpStatus.OK);
		}
	}

}
