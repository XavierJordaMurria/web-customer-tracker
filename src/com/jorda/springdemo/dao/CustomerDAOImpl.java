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
	session.saveOrUpdate(theCustomer);
    }

    @Override
    public Customer getCustomer(int theId) {
	Session session = this.sessionFactory.getCurrentSession();
	Customer customer = session.get(Customer.class, theId);
	return customer;
    }

    @Override
    public void deleteCustomer(int theId) {
	Session session = this.sessionFactory.getCurrentSession();
	Query theQuery = session.createQuery("delete from Customer where id=:customerId");
	theQuery.setParameter("customerId", theId);
	theQuery.executeUpdate();
    }

    @Override
    public List<Customer> searchCustomers(String theSearchName) {
	Session currentSession = sessionFactory.getCurrentSession();
	Query theQuery = null;

	// only search by name if theSearchName is not empty
	if (theSearchName != null && theSearchName.trim().length() > 0) {
	    // search for firstName or lastName ... case insensitive
	    theQuery = currentSession.createQuery(
		    "from Customer where lower(firstName) like :theName or lower(lastName) like :theName",
		    Customer.class);
	    theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

	} else {
	    // theSearchName is empty ... so just get all customers
	    theQuery = currentSession.createQuery("from Customer", Customer.class);
	}

	// execute query and get result list
	List<Customer> customers = theQuery.getResultList();

	// return the results
	return customers;

    }
}
