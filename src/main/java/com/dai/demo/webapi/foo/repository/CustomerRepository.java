package com.dai.demo.webapi.foo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.dai.demo.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	List<Customer> findByLastName(String lastName);

	@Query("from Customer where firstName=:#{#name}")
	List<Customer> findByMy(@Param("name") String name);

	/**
	 * @return
	 * will cause exception if get multiple<br/>
	 * return null if get 0
	 */
	@Query("from Customer where firstName=:#{#name}")
	Customer findOneByMy(@Param("name") String name);
}
