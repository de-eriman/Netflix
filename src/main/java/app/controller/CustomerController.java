package app.controller;

import app.domain.Customer;
import app.domain.Film;
import app.service.CustomerService;

import java.util.Iterator;
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


    public void addFilmToCustomersLibrary(String customerIdS, String filmIdS){
        Long customerId = Long.parseLong(customerIdS);
        Long filmId = Long.parseLong(filmIdS);
        service.addFilmToCustomersLibrary(customerId, filmId);
    }

    public void removeProductFromCustomersCart (String customerIdS, String filmIdS){
        Long customerId = Long.parseLong(customerIdS);
        Long filmId = Long.parseLong(filmIdS);
        service.removeProductFromCustomersCart(customerId, filmId);
    }

    public void clearCustomersCart (String customerIdS){
        Long customerId = Long.parseLong(customerIdS);
        service.clearCustomersCart(customerId);
    }




}
