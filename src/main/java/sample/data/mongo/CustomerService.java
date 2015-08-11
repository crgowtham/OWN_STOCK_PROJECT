package sample.data.mongo;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@RequestMapping(method = RequestMethod.POST)
	public Map<String, Object> createBook(@RequestBody Map<String, Object> bookMap) {
		Customer testCutomer = null;
		this.customerRepository.deleteAll();

		// save a couple of customers
		this.customerRepository.save(new Customer("Alice", "Smith"));
		this.customerRepository.save(new Customer("Bob", "Smith"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : this.customerRepository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(this.customerRepository.findByFirstName("Alice"));

		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (Customer customer : this.customerRepository.findByLastName("Smith")) {
			testCutomer = customer;
			System.out.println(customer);
		}
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("message", "Book created successfully");
		response.put("book", testCutomer);
		return response;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{bookId}", produces = { "application/json",
			"application/xml" })
	public @ResponseBody Customer getBookDetails(@PathVariable("bookId") String bookId) {
		Customer testCutomer = null;
		this.customerRepository.deleteAll();

		// save a couple of customers
		this.customerRepository.save(new Customer("Alice", "Smith"));
		this.customerRepository.save(new Customer("Bob", "Smith"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : this.customerRepository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(this.customerRepository.findByFirstName("Alice"));

		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (Customer customer : this.customerRepository.findByLastName("Smith")) {
			testCutomer = customer;
			System.out.println(customer);
		}
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("message", "Book created successfully");
		response.put("book", testCutomer.toString());
		return testCutomer;
	}

}