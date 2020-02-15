package com.jorda.springdemo.service;

import java.util.List;

import com.jorda.springdemo.entity.Customer;

public interface ICustomerService {
    public List<Customer> getCustomers();

    public void saveCustomer(Customer theCustomer);

    public Customer getCustomer(int theId);
}
