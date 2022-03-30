/**
 * 
 */
package com.tcs.repository;

import org.springframework.data.repository.CrudRepository;

import com.tcs.entity.Course;
import com.tcs.entity.Grades;

/**
 * @author springuser05
 *
 */
public interface GradeRepository extends CrudRepository<Grades, Integer>{
	

}
