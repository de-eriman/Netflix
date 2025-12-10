package app.controller;

import app.domain.Customer;
import app.service.CustomerService;

import java.util.List;

public class CustomerController {
    private final CustomerService service = new CustomerService();


    public Customer save(String name) {
        Customer customer = new Customer(name);
        return service.save(customer);
    }

    public List<Customer> getAllActiveCustomers() {
        return service.getAllActiveCustomers();
    }


    public Customer getActiveCustomerById(String id) {
        Long numberId = Long.parseLong(id);
        return service.getActiveCustomerById(numberId);
    }

    ;

    public void deleteById(String id) {
        Long numberId = Long.parseLong(id);
        service.deleteById(numberId);
    }

    public void deleteByName(String name) {

        service.deleteByName(name);
    }
    public void restoreById(String id) {
        Long numberId = Long.parseLong(id);
        service.restoreById(numberId);
    }

    public int getActiveCustomersNumber() {
        return service.getActiveCustomersNumber();
    }



}
