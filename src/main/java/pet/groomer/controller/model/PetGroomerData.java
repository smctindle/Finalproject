package pet.groomer.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.groomer.entity.Customer;
import pet.groomer.entity.Employee;
import pet.groomer.entity.PetGroomer;

@Data
@NoArgsConstructor

public class PetGroomerData {
	
	private Long petGroomerId;
	private String petGroomerName;
	private Long petGroomerPhoneNumber;
	private String petGroomerAddress;
	
	private Set<EmployeeData> employees = new HashSet<>();
	private Set<CustomersData> customers = new HashSet<>();
	
	public PetGroomerData (PetGroomer petGroomer) {
		petGroomerId = petGroomer.getPetGroomerId();
		petGroomerName = petGroomer.getPetGroomerName();
		petGroomerPhoneNumber = petGroomer.getPetGroomerPhoneNumber();
		petGroomerAddress = petGroomer.getPetGroomerAddress();
		
		for (Customer customer: petGroomer.getCustomers()) {
			customers.add(new CustomersData(customer));
			
		}
		
		for (Employee employee: petGroomer.getEmployees()) {
			employees.add(new EmployeeData(employee));
			
			
		}
		
		
	
		
		
	}
	
	
	

}
