package pet.groomer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pet.groomer.entity.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {

}
