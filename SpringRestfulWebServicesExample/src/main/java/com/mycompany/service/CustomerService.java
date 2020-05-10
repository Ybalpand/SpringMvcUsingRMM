package com.mycompany.service;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.mycompany.bean.Customer;
import com.mycompany.bean.Document;
import com.mycompany.bean.Gender;
import com.mycompany.constants.DateFormate;

@Service
public class CustomerService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

	static HashMap<Integer, Customer> customerIdMap = getCustomerIdMap();

	public CustomerService() {
		super();
		if (customerIdMap == null) {
			customerIdMap = new HashMap<Integer, Customer>();
			List<Document> docList = new ArrayList<Document>();
			System.out.println(new Date());
			Document doc1 = new Document(1, "AdharCard", new Date(), "Pune");
			Document doc2 = new Document(2, "ElectionID", new Date(), "Nagpur");
			Document doc3 = new Document(3, "PanCard", new Date(), "Mumbai");
			docList.add(doc1);
			docList.add(doc2);
			docList.add(doc3);

			customerIdMap.put(1, new Customer(1, "A", DateFormate.getCurrentDate(), Gender.MALE, docList));
			customerIdMap.put(2, new Customer(2, "B", DateFormate.getCurrentDate(), Gender.FEMALE, docList));
			customerIdMap.put(3, new Customer(3, "C", DateFormate.getCurrentDate(), Gender.FEMALE, docList));

		}

	}

	public List<Customer> getAllCustomers() {
		List<Customer> listOfCustomers = new ArrayList(customerIdMap.values());
		return listOfCustomers;
	}

	public Customer getCustomerById(int customerId) {
		System.out.println("Rceived customer id " + customerId);
		Customer customerById = customerIdMap.get(customerId);
		System.out.println(customerById);
		return customerById;
	}

	public Customer addCustomer(Customer customer) {
		customerIdMap.put(customer.getCustomerId(), customer);
		System.out.println("add new customer");
		Customer custmerobj = customerIdMap.get(customer.getCustomerId());
		System.out.println("return new customer with customer id " + custmerobj.getCustomerId());
		return custmerobj;
	}

	public boolean addCustomer(List<Customer> customers) {
		try {
			for (Customer cust : customers) {
				customerIdMap.put(cust.getCustomerId(), cust);
				System.out.println("add new customer  : " + cust.getCustomerId());
			}
			return true;
		} catch (Exception e) {
			logger.info("throw Exception : " + e.getMessage());
			return false;
		}

	}

	private static HashMap<Integer, Customer> getCustomerIdMap() {
		return customerIdMap;
	}

	public Customer updateCustomer(Customer customer) {
		if (customerIdMap.containsKey(customer.getCustomerId())) {
			customerIdMap.put(customer.getCustomerId(), customer);
			Customer custmerobj = customerIdMap.get(customer.getCustomerId());
			return custmerobj;
		} else {
			return null;
		}

	}

	public void deleteCustomer(int customerId) {
		customerIdMap.remove(customerId);
	}

	public List<Customer> getCustomerbyGender(Gender gender) {
		System.out.println("Gender Match list ");
		Iterator<Map.Entry<Integer, Customer>> customerEntries = customerIdMap.entrySet().iterator();
		List<Customer> customerList = new ArrayList<Customer>();
		while (customerEntries.hasNext()) {
			Map.Entry<Integer, Customer> entry = customerEntries.next();
			Customer c = entry.getValue();
			if (c.getGender() == gender) {

				customerList.add(c);
			}
		}
		return customerList;
	}

	public List<Customer> getCustomerbyDOB(Date dateofBirth) throws ParseException {
		System.out.println("DOB Mathch list ");
		Iterator<Map.Entry<Integer, Customer>> customerEntries = customerIdMap.entrySet().iterator();
		List<Customer> customerList = new ArrayList<Customer>();
		while (customerEntries.hasNext()) {
			Map.Entry<Integer, Customer> entry = customerEntries.next();

			Customer cust = (Customer) entry.getValue();

			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
			String strdate1 = sdf.format(cust.getDateOfBirth());
			String strdate2 = sdf.format(dateofBirth);
			if (strdate1.equals(strdate2)) {
				System.out.println("Date Matched");
				customerList.add(cust);
			}

		}
		return customerList;
	}

	public List<Customer> getCustomerByGenderWithDOB(Gender gender, Date dateofBirth) throws ParseException {
		System.out.println("DOB Mathch list ");
		Iterator<Map.Entry<Integer, Customer>> customerEntries = customerIdMap.entrySet().iterator();
		List<Customer> customerList = new ArrayList<Customer>();
		while (customerEntries.hasNext()) {
			Map.Entry<Integer, Customer> entry = customerEntries.next();

			Customer cust = (Customer) entry.getValue();
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
			String strdate1 = sdf.format(cust.getDateOfBirth());

			String strdate2 = sdf.format(dateofBirth);

			if (strdate1.equals(strdate2)) {

				if (cust.getGender() == gender) {
					customerList.add(cust);
				}
			}

		}
		return customerList;
	}
}