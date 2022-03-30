/**
 * 
 */
package com.tcs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.entity.StudentGrades;
import com.tcs.repository.StudentGradeRepository;

/**
 * @author springuser05
 *
 */
@Service
public class ProfessorService {
	
	@Autowired
	StudentGradeRepository studentGradeEntity;
	
	private static StudentGrades studentGradeEntityClass;
	{
		studentGradeEntityClass = new StudentGrades();
	}
	
	/**
	 * Add Grades to students.
	 * @Param course name
	 * @Throws
	 */
	public StudentGrades addGradesByProfessor(StudentGrades studentGrades, long id)
	{
		studentGrades.setProfessorid(id);
		studentGradeEntityClass = studentGradeEntity.save(studentGrades);
		return studentGradeEntityClass;
	}

}
