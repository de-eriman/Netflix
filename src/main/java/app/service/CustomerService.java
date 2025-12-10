package app.service;

import app.exceptions.CustomerSaveException;
import app.exceptions.CustomerNotFoundException;
import app.repository.CustomerRepository;

import app.domain.Customer;
import app.domain.Film;

import java.util.Iterator;
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



    //    Добавить товар в корзину покупателя по их идентификаторам (если оба активны)
    public void addFilmToCustomersLibrary(Long customerId, Long filmId) {
        Customer customer = getActiveCustomerById(customerId);
        Film film = filmService.getActiveFilmById(filmId);
        customer.getLibrary().add(film);
    }

    //    Удалить товар из корзины покупателя по их идентификаторам
    public void removeProductFromCustomersCart(Long customerId, Long filmId) {
        // Подход 1. Удаление всех продуктов одного наименования из корзины.
//        Customer customer = getActiveCustomerById(customerId);
//        customer.getCart().removeIf(x -> x.getId().equals(filmId));

        // Подход 2. Удаление только одного продукта нужного наименования.
        Customer customer = getActiveCustomerById(customerId);
        List<Film> cart = customer.getLibrary();
        Iterator<Film> iterator = cart.iterator();

        while (iterator.hasNext()) {
            Film film = iterator.next();
            if (film.getId().equals(filmId)) {
                iterator.remove();
                break;
            }
        }
    }

    //    Полностью очистить корзину покупателя по его идентификатору (если он активен)
    public void clearCustomersCart(Long id) {
        Customer customer = getActiveCustomerById(id);
        customer.getLibrary().clear();
    }


}

