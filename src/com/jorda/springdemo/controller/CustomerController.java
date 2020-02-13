package com.jorda.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jorda.springdemo.entity.Customer;
import com.jorda.springdemo.service.ICustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model theModel) {

	List<Customer> customerList = this.customerService.getCustomers();

	theModel.addAttribute("customers", customerList);
	return "list-customers";
    }
}
