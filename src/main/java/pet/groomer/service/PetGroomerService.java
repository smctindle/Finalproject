package pet.groomer.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pet.groomer.controller.model.CustomersData;
import pet.groomer.controller.model.EmployeeData;
import pet.groomer.controller.model.PetGroomerData;
import pet.groomer.dao.CustomerDao;
import pet.groomer.dao.EmployeeDao;
import pet.groomer.dao.PetGroomerDao;
import pet.groomer.entity.Customer;
import pet.groomer.entity.Employee;
import pet.groomer.entity.PetGroomer;
@Service
public class PetGroomerService {
	
	@Autowired
	private PetGroomerDao petgroomerDao;
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private CustomerDao customerDao;
	

	public PetGroomerData savePetGroomer(PetGroomerData petGroomerData) {
		Long petGroomerId = petGroomerData.getPetGroomerId();
		PetGroomer petGroomer = findOrCreatePetGroomer(petGroomerId);
		copyPetGroomerFields(petGroomer, petGroomerData);
		return new PetGroomerData(petgroomerDao.save(petGroomer));
	}

	private void copyPetGroomerFields(PetGroomer petGroomer, PetGroomerData petGroomerData) {
		petGroomer.setPetGroomerId(petGroomerData.getPetGroomerId());
		petGroomer.setPetGroomerAddress(petGroomerData.getPetGroomerAddress());
		petGroomer.setPetGroomerName(petGroomerData.getPetGroomerName());
		petGroomer.setPetGroomerPhoneNumber(petGroomerData.getPetGroomerPhoneNumber());
		
	}

	private PetGroomer findOrCreatePetGroomer(Long petGroomerId) {
		if(Objects.isNull(petGroomerId)) {
			return new PetGroomer();
		}else {
			return findPetGroomerById(petGroomerId);
		}
	}

	private PetGroomer findPetGroomerById(long petGroomerId) {
		return petgroomerDao.findById(petGroomerId).orElseThrow(
				() -> new NoSuchElementException("Pet Groomer with there Id=" + petGroomerId + " was not found"));
	}
	
	@Transactional(readOnly= false)
	public EmployeeData saveEmployee(Long petGroomerId, EmployeeData employeeData) {
		PetGroomer petGroomer = findPetGroomerById(petGroomerId);
		Long employeeId = employeeData.getEmployeeId();
		Employee employee = findOrCreateEmployee(petGroomerId, employeeId);
		copyEmployeeFields(employee, employeeData);
	employee.setPetGroomer(petGroomer);
	petGroomer.getEmployees().add(employee);
	Employee dbEmployee = (employeeDao.save(employee));
		return new EmployeeData(dbEmployee);
	}

	private void copyEmployeeFields(Employee employee, EmployeeData employeeData) {
		employee.setEmployeeId(employeeData.getEmployeeId());
		employee.setEmployeeName(employeeData.getEmployeeName());
		employee.setEmployeePhoneNumber(employeeData.getEmployeePhoneNumber());	
	}
	
	private Employee findEmployeeById(Long petGroomerId, Long employeeId) {
		Employee employee = employeeDao.findById(employeeId).orElseThrow(
				() -> new NoSuchElementException("No employee found"));
		if (employee.getPetGroomer().getPetGroomerId() != petGroomerId) {
			throw new IllegalArgumentException(" Employee with Id=" + employeeId + "was not found.");
	}
		return employee;
		}


	private Employee findOrCreateEmployee(Long petGroomerId, Long employeeId) {
		if(Objects.isNull(employeeId)) {
			return new Employee();
		}
		return findEmployeeById(petGroomerId, employeeId);
	}

	@Transactional(readOnly= false)
	public CustomersData saveCustomer(Long petGroomerId, CustomersData petGroomerCustomer) {
		PetGroomer petGroomer = findPetGroomerById(petGroomerId);
		Long customerId = petGroomerCustomer.getCustomerId();
		Customer customer = findOrCreateCustomer(petGroomerId, customerId);
		copyCustomerFileds(customer, petGroomerCustomer);
		customer.getPetGroomers().add(petGroomer);
		petGroomer.getCustomers().add(customer);
		return new CustomersData(customerDao.save(customer));
	}

	private void copyCustomerFileds(Customer customer, CustomersData petGroomerCustomer) {
		customer.setCustomerEmail(petGroomerCustomer.getCustomerEmail());
		customer.setCustomerId(petGroomerCustomer.getCustomerId());
		customer.setCustomerName(petGroomerCustomer.getCustomerName());
		customer.setCustomerPhoneNumber(petGroomerCustomer.getCustomerPhoneNumber());
		customer.setCustomerPet(petGroomerCustomer.getCustomerPet());
		
	}

	private Customer findOrCreateCustomer(Long petGroomerId, Long customerId) {
		if(Objects.isNull(customerId)) {
			return new Customer();
		}else {
			return findCustomerById(petGroomerId, customerId);
		}
		
	}

	private Customer findCustomerById(Long petGroomerId, Long customerId) {
		Customer customer = customerDao.findById(customerId).orElseThrow(
				() -> new NoSuchElementException("No customer found."));
		boolean found = false;
		for(PetGroomer petGroomer : customer.getPetGroomers()) {
			if(petGroomer.getPetGroomerId()==petGroomerId) {
				found = true;
				break;
			}
		}
		if (!found) {
			throw new IllegalArgumentException("Customer with Id=" + customerId + " was not found.");
		}
		return customer;
	}
	
	@Transactional(readOnly =  true)
	public List<PetGroomerData> reteievePetGroomers() {
		List<PetGroomer> petGroomers = petgroomerDao.findAll();
		List<PetGroomerData> result = new LinkedList<>();
		for(PetGroomer petGroomer: petGroomers) {
			PetGroomerData psd = new PetGroomerData(petGroomer);
			psd.getCustomers().clear();
			psd.getEmployees().clear();
			
			result.add(psd);
		}
		return result;
	}
	
	@Transactional(readOnly =  true)
	public PetGroomerData reteievePetGroomersById(Long petGroomerId) {
		PetGroomer petGroomer = findPetGroomerById(petGroomerId);
		
		return new PetGroomerData(petGroomer);
	}
	
	@Transactional(readOnly =  false)
	public void deletePetGroomersById(Long petGroomerId) {
		PetGroomer petGroomer = findPetGroomerById(petGroomerId);
		petgroomerDao.delete(petGroomer);
	}



}
