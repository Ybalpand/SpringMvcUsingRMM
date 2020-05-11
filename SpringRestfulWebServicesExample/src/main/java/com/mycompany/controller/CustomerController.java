package com.mycompany.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.mycompany.bean.Customer;
import com.mycompany.bean.Gender;
import com.mycompany.constants.DateFormate;
import com.mycompany.service.CustomerService;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@ExceptionHandler(Exception.class)  
	// 1
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Customer> getAllCustomer() {
		System.out.println(" Get all customer List....");
		return customerService.getAllCustomers();
	}

	// 2
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Customer postCustomers(@RequestBody Customer customer) {
		System.out.println(" ADD Single Customers ...");
		Customer createdCustomerResponse = customerService.addCustomer(customer);
		return createdCustomerResponse;
	}

	// 3
	@RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") int customerId) {
		Customer customer = customerService.getCustomerById(customerId);
		if (customerId <= 0) {
			System.out.println("400 (BAD REQUEST) : " + customerId);
			return new ResponseEntity<Customer>(HttpStatus.BAD_REQUEST);
		}
		if (customer == null) {
			System.out.println("customer is null in case 404 no found /Customer id is not found : " + customerId);
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	// 4
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public Customer putCustomers(@RequestBody Customer customer) {
		System.out.println(" Updated Customer ...");
		Customer updatedCustomerResponse = customerService.updateCustomer(customer);
		return updatedCustomerResponse;
	}

	// 5
	@RequestMapping(value = "/{customerId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public @ResponseBody void deleteCustomer(@PathVariable("customerId") int customerId) {
		System.out.println("Start deleteCustomer....");
		customerService.deleteCustomer(customerId);
	}

	// 6
	@RequestMapping(value = "/gender/{gender}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Customer> getCustomerByGender(@PathVariable("gender") String gender) {
		System.out.println(gender.toString());
		Gender genderParam = Gender.valueOf(gender);
		System.out.println(" Get customerId with Gender...." + genderParam.toString());
		return customerService.getCustomerbyGender(genderParam);
	}

	// 7
	@RequestMapping(value = "/dob/{dateOfBirth}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Customer> getCustomerByDOB(@PathVariable("dateOfBirth") String dateOfBirth) throws ParseException {
		System.out.println(dateOfBirth.toString());
		System.out.println(" Get customerId with Gender....");
		Date dob = DateFormate.typecastToDate(dateOfBirth);
		return customerService.getCustomerbyDob(dob);
	}
	
	
	// 8 GenderWithDob
	@RequestMapping(value = "/{gender}/{dateOfBirth}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Customer> getCustomerByGenderWithDOB(@PathVariable("gender") String gender, @PathVariable("dateOfBirth") String dateOfBirth) throws ParseException {
		System.out.println("Enter in Get Customer by gender and date of birth");
		Gender genderParam = Gender.valueOf(gender);		
		Date dob = DateFormate.typecastToDate(dateOfBirth);		
		return customerService.getCustomerByGenderWithDob(genderParam, dob);
		
	}
}















