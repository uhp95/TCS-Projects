/**
 * 
 */
package com.tcs.repository;

import org.springframework.data.repository.CrudRepository;

import com.tcs.entity.Course;
import com.tcs.entity.UserManagement;

/**
 * @author springuser05
 *
 */
public interface UserManagementRepository extends CrudRepository<UserManagement, Long>{
	

}
