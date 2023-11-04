package pet.groomer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pet.groomer.entity.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Long> {

}
