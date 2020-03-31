package com.saskenhp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.saskenhp.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

	@Query("FROM Employee WHERE firstName=?1")
	public Employee findByName(String firstName);

	@Query("FROM Employee e inner join e.role r where r.role=:role")
	public List<Employee> findAllDoctors(String role);

}
