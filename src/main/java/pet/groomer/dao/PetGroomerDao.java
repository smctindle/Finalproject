package pet.groomer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pet.groomer.entity.PetGroomer;

public interface PetGroomerDao extends JpaRepository<PetGroomer, Long> {
	

}
