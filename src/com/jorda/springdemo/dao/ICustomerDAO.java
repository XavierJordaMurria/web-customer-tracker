package com.jorda.springdemo.dao;

import java.util.List;

import com.jorda.springdemo.entity.Customer;

public interface ICustomerDAO {
    public List<Customer> getCustomers();
}
