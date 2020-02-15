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

	Query<Customer> theQuery = session.createQuery("from Customer order by lastName", Customer.class);

	List<Customer> customersList = theQuery.getResultList();

	return customersList;
    }

    @Override
    public void saveCustomer(Customer theCustomer) {
	Session session = this.sessionFactory.getCurrentSession();
	session.save(theCustomer);
    }

    @Override
    public Customer getCustomer(int theId) {
	Session session = this.sessionFactory.getCurrentSession();
	Customer customer = session.get(Customer.class, theId);
	return customer;
    }
}
