package com.jorda.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jorda.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements ICustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers() {
	Session session = this.sessionFactory.getCurrentSession();

	Query<Customer> theQuery = session.createQuery("from Customer", Customer.class);

	List<Customer> customersList = theQuery.getResultList();

	return customersList;
    }

}
