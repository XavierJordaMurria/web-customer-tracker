package com.jorda.springdemo.dao;

import java.util.List;

import com.jorda.springdemo.entity.Customer;

public interface ICustomerDAO {
    public List<Customer> getCustomers();

    public void saveCustomer(Customer theCustomer);

    public Customer getCustomer(int theId);

    public void deleteCustomer(int theId);

    public List<Customer> searchCustomers(String theSearchName);
}
