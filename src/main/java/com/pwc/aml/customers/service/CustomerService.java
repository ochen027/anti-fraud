package com.pwc.aml.customers.service;


import com.pwc.aml.customers.dao.ICustomerDAO;
import com.pwc.aml.customers.entity.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private ICustomerDAO customerDAO;

    @Override
    public void saveOrUpdateCustomer(Customers customer) {

        Customers target = customerDAO.findByCustId(customer.getCustomerId());

        if (target == null) {
            customerDAO.save(customer);
        } else {
            customerDAO.update(customer);
        }
    }

    @Override
    public Customers findByCustId(Customers customer) {
        return customerDAO.findByCustId(customer.getCustomerId());
    }

    @Override
    public Customers findByCustCtNo(Customers customer) {
        return customerDAO.findByCustCtNo(customer.getCustomerCertificateNumber());
    }


}
