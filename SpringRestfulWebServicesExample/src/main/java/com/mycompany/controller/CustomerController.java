package com.mycompany.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.mycompany.bean.Customer;
import com.mycompany.service.CustomerService;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	CustomerService customerService;

	//1 
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Customer> getAllCustomer() {
		logger.info(" Get all customer List....");
		return customerService.getAllCustomers();
	}
	
	//2
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Customer postCustomers(@RequestBody  Customer customer) {
		logger.info(" ADD Single Customers ...");
		Customer createdCustomerResponse  = customerService.addCustomer(customer);
		return createdCustomerResponse;
	}
	
	//3
	@RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") int customerId) {
		logger.info("Start getCustomerById....");
		logger.info("In getCustomerby Id Controller,Customer id " + customerId);
		Customer customer = customerService.getCustomerById(customerId);

		if (customerId <= 0) {
			logger.info("400 (BAD REQUEST) : " + customerId);
			return new ResponseEntity<Customer>(HttpStatus.BAD_REQUEST);
		}
		if (customer == null) {
			logger.info("customer is null in case 404 no found /Customer id is not found : " + customerId);
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	
	
	//4
	@RequestMapping(value = "/updateCustomer", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public Customer putCustomers(@RequestBody Customer customer) {
		logger.info(" Updated Customer ...");
		Customer updatedCustomerResponse  = customerService.updateCustomer(customer);
		return updatedCustomerResponse;
	}
	
	//5
	@RequestMapping(value = "/deleteCustomer/{customerId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public @ResponseBody void deleteCustomer(@PathVariable("customerId") int customerId) {
		logger.info("Start deleteCustomer....");
		customerService.deleteCustomer(customerId);
	}
}