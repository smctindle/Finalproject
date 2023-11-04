package pet.groomer.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.groomer.entity.Employee;

@Data
@NoArgsConstructor
public class EmployeeData {

	 Long employeeId;  
	 String employeeName;
	 Long employeePhoneNumber;
	 
	public EmployeeData(Employee employee) {
		employeeId = employee.getEmployeeId();
		employeeName = employee.getEmployeeName();
		employeePhoneNumber = employee.getEmployeePhoneNumber();
		
		
		
	}
	
	
}
