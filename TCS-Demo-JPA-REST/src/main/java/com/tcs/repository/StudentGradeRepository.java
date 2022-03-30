/**
 * 
 */
package com.tcs.repository;

import org.springframework.data.repository.CrudRepository;

import com.tcs.entity.Professor;
import com.tcs.entity.StudentGrades;

/**
 * @author springuser05
 *
 */
public interface StudentGradeRepository extends CrudRepository<StudentGrades, Long>{

}
