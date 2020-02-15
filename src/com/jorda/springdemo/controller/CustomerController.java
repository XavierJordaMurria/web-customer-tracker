package com.jorda.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("showFormForAdd")
    public String showFormForAdd(Model theModel) {

	Customer theCustomer = new Customer();

	theModel.addAttribute("customer", theCustomer);
	return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {

	this.customerService.saveCustomer(theCustomer);

	return "redirect:/customer/list";
    }

    @GetMapping("showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {
	Customer theCustomer = this.customerService.getCustomer(theId);

	theModel.addAttribute("customer", theCustomer);

	return "customer-form";
    }
}
