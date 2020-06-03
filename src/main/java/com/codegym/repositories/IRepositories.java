package com.codegym.repositories;

import com.codegym.model.Customer;

import java.util.List;

public interface IRepositories {
    List<Customer> getAllCustomer();
    void insertCustomer(Customer customer);
}
