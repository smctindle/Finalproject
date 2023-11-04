package pet.groomer.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.groomer.entity.Customer;

@Data
@NoArgsConstructor
public class CustomersData {
	
	private Long customerId;
	private String customerName;
	private Long customerPhoneNumber;
	private String customerEmail;
	private String customerPet;

	public CustomersData(Customer customer) {
		customerId = customer.getCustomerId();
		customerName = customer.getCustomerName();
		customerPhoneNumber = customer.getCustomerPhoneNumber();
		customerEmail = customer.getCustomerEmail();
		customerPet = customer.getCustomerPet();
		
	}


}
