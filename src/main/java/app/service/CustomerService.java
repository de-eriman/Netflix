package app.service;

import app.exceptions.CustomerSaveException;
import app.exceptions.CustomerNotFoundException;
import app.repository.CustomerRepository;

import app.domain.Customer;

import java.util.List;


public class CustomerService {

    private final CustomerRepository customerRepository = new CustomerRepository();
    private final FilmService filmService = FilmService.getInstance();


    //    Сохранить покупателя в базе данных (при сохранении покупатель автоматически считается активным).
    public Customer save(Customer customer) {
        if (customer == null) {
            throw new CustomerSaveException("Покупатель не может быть null");
        }
        String name = customer.getName();
        if (name == null || name.trim().isEmpty()) {
            throw new CustomerSaveException("Имя покупателя не должно быть пустым");
        }
        customer.setActive(true);
        return customerRepository.save(customer);
    }

    //    Вернуть всех покупателей из базы данных (активных).
    public List<Customer> getAllActiveCustomers() {
        return customerRepository.findAll()
                .stream()
                .filter(Customer::isActive)
                .toList();
    }

    //Вернуть одного покупателя из базы данных по его идентификатору (если он активен).
    public Customer getActiveCustomerById(Long id) {
        Customer customer = customerRepository.findById(id);

        if (customer == null || !customer.isActive()) {
            throw new CustomerNotFoundException(id);
        }
        return customer;
    }

    //    Удалить покупателя из базы данных по его идентификатору.
    public void deleteById(Long id) {
        Customer customer = getActiveCustomerById(id);
        customer.setActive(false);
    }

    //    Удалить покупателя из базы данных по его имени.
    public void deleteByName(String name) {
        getAllActiveCustomers()
                .stream()
                .filter(x -> x.getName().equals(name))
                .forEach(x -> x.setActive(false));
    }
    public void restoreById(Long id) {
        Customer customer = customerRepository.findById(id);
        if (customer == null) {
            throw new CustomerNotFoundException(id);
        }
        customer.setActive(true);
    }

    //    Вернуть общее количество покупателей в базе данных (активных).
    public int getActiveCustomersNumber() {
        return getAllActiveCustomers().size();
    }








}

