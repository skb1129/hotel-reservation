package com.dexter.service;

import com.dexter.model.Customer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

public class CustomerService {
    private static CustomerService customerService = null;

    private CustomerService() {
    }

    public static CustomerService getInstance() {
        if (null == customerService) {
            customerService = new CustomerService();
        }
        return customerService;
    }

    Collection<Customer> customers = new HashSet<>();

    public void addCustomer(String email, String firstName, String lastName) throws IllegalArgumentException {
        Customer customer = new Customer(firstName, lastName, email);
        customers.add(customer);
    }

    public Customer getCustomer(String email) {
        Optional<Customer> customer = customers.stream().filter(c -> email.equals(c.getEmail())).findFirst();
        return customer.orElse(null);
    }

    public Collection<Customer> getAllCustomers() {
        return customers;
    }
}
