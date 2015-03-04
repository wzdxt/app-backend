package com.dai.demo.foo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dai.demo.entity.Customer;
import com.dai.demo.repository.CustomerRepository;
import com.dai.demo.service.FooService;

/**
 * Root resource (exposed at "myresource" path)
 */
@Component
@Path("myresource")
public class MyResource {
	@Autowired
	@Qualifier("fooServiceImpl2")
	private FooService s;

	@Autowired
	private CustomerRepository repository;

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to the client as "text/plain" media type.
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		StringBuffer sb = new StringBuffer();
		System.out.println(s.get());
		sb.append(s.get()).append("\n");

		// save a couple of customers
		// repository.save(new Customer("Jack", "Bauer"));
		// repository.save(new Customer("Chloe", "O'Brian"));
		// repository.save(new Customer("Kim", "Bauer"));
		// repository.save(new Customer("David", "Palmer"));
		// repository.save(new Customer("Michelle", "Dessler"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : repository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual customer by ID
		Customer customer = repository.findOne(1L);
		System.out.println("Customer found with findOne(1L):");
		System.out.println("--------------------------------");
		System.out.println(customer);
		System.out.println();

		// fetch customers by last name
		System.out.println("Customer found with findByLastName('Bauer'):");
		System.out.println("--------------------------------------------");
		for (Customer bauer : repository.findByLastName("Bauer")) {
			System.out.println(bauer);
		}
		System.out.println();

		// my test
		System.out.println("my test");
		System.out.println("--------------------------------------------");
		for (Customer m : repository.findByMy("Jack")) {
			System.out.println(m);
			sb.append(m).append("\n");
		}
		System.out.println(repository.findOneByMy("Kimm"));
		System.out.println();

		return sb.toString() + "succeed";
	}

}
