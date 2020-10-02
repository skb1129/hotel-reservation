package com.dexter.service;

import com.dexter.model.Customer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

public class CustomerService {
    Collection<Customer> customers = new HashSet<>();

    public void addCustomer(String email, String firstName, String lastName) {
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
