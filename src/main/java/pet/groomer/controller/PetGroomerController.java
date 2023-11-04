package pet.groomer.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet.groomer.controller.model.CustomersData;
import pet.groomer.controller.model.EmployeeData;
import pet.groomer.controller.model.PetGroomerData;
import pet.groomer.service.PetGroomerService;

@RestController
@RequestMapping("/pet_groomer")
@Slf4j
public class PetGroomerController {
	
	@Autowired 
	private PetGroomerService petGroomerService; 
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetGroomerData insertPetGroomer(@RequestBody PetGroomerData petGroomerData) {
		log.info("Creating PetGroomer ()", petGroomerData);
		return petGroomerService.savePetGroomer(petGroomerData);
		
		
	}
	
	@PutMapping("/{petGroomerId}")
	public PetGroomerData updatePetGroomerData(@PathVariable Long petGroomerId, @RequestBody PetGroomerData petGroomerData) {
		petGroomerData.setPetGroomerId(petGroomerId);
		log.info("update PetGroomer {}", petGroomerData);
		return petGroomerService.savePetGroomer(petGroomerData);
		
	}
	
	@PostMapping("/{petGroomerId}/employee")
	@ResponseStatus(code = HttpStatus.CREATED)
	public EmployeeData saveEmployee(@PathVariable Long petGroomerId, @RequestBody EmployeeData petGroomerEmployee) {
		log.info("Add PetGroomerEmployee {}", petGroomerEmployee);
		return petGroomerService.saveEmployee(petGroomerId,petGroomerEmployee);
	}
	
	@PostMapping("/{petGroomerId}/customer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public CustomersData saveCustomer(@PathVariable Long petGroomerId, @RequestBody CustomersData petGroomerCustomer) {
		log.info("Add PetGroomerCustomer {}", petGroomerCustomer);
		return petGroomerService.saveCustomer(petGroomerId,petGroomerCustomer);
	}
	
	@GetMapping
	public List<PetGroomerData>reteievePetGroomers() {
		log.info("Show all Groomers.");
		return petGroomerService.reteievePetGroomers();
		
	}
	
	@GetMapping("/{petGroomerId}")
	public PetGroomerData reteievePetGroomerById(@PathVariable Long petGroomerId) {
		log.info(" Reteieve groomers with there Id={ }", petGroomerId);
		return petGroomerService.reteievePetGroomersById(petGroomerId);
		
	}
	
	@DeleteMapping("/{petGroomerId}")
	public Map<String,String> deletePetGroomerById(@PathVariable Long petGroomerId) {
		log.info(" Delete groomers with there Id=" + petGroomerId);
		petGroomerService.deletePetGroomersById(petGroomerId);
		return Map.of("message", "Pet Groomer with Id=" + petGroomerId + "Deleted Successfully");
		
	}

}
