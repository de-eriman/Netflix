package app.repository;

import app.domain.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepository {
    private final Map<Long, Customer> userDataBase = new HashMap<>();
    private long maxId;

    public Customer save(Customer customer) {
        customer.setId(++maxId);
        userDataBase.put(maxId, customer);
        return customer;
    }

    public List<Customer> findAll() {
        // Как это работает:
        // 1. Метод values() возвращает нам коллекцию значений мапа (коллекцию покупателей).
        // 2. Эту коллекцию покупателей мы передаём в конструктор ArrayList.
        // 3. Создаётся ArrayList, состоящий из тех же покупателей, из которых состояла коллекция.
        return new ArrayList<>(userDataBase.values());
    }

    public Customer findById(Long id) {
        return userDataBase.get(id);
    }

    public void update(Long id, String newName) {
        Customer customer = userDataBase.get(id);
        if (customer != null) {
            customer.setName(newName);
        }
    }

    public void deleteById(Long id) {
        userDataBase.remove(id);
    }

}
