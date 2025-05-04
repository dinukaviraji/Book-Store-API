
package com.bookstore.bookstoreapi.dao;

import com.bookstore.bookstoreapi.model.Customer;
import com.bookstore.bookstoreapi.storage.DataStorage;
import java.util.Collection;



public class CustomerDAO {
    
    public Customer addCustomer(Customer customer) {
        customer.setId(DataStorage.customerIdCounter++);
        DataStorage.customers.put(customer.getId(), customer);
        return customer;
    }

    public Collection<Customer> getAllCustomers() {
        return DataStorage.customers.values();
    }

    public Customer getCustomerById(int id) {
        return DataStorage.customers.get(id);
    }

    public Customer updateCustomer(int id, Customer updatedCustomer) {
        updatedCustomer.setId(id);
        DataStorage.customers.put(id, updatedCustomer);
        return updatedCustomer;
    }

    public void deleteCustomer(int id) {
        DataStorage.customers.remove(id);
    }

    public boolean exists(int id) {
        return DataStorage.customers.containsKey(id);
    }    
    
}
