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

	@Query("FROM Employee e left outer join employee_role er inner join Role r on e.empId=er.employee_Id and r.role='DOC'")
	public List<Employee> findAllDoctors();

}
