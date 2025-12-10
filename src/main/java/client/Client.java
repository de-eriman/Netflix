package client;

import app.controller.CustomerController;
import app.controller.FilmController;
import app.domain.Film;
import app.domain.Customer;

import java.util.List;
import java.util.Scanner;

/**
 * Client.java
 *
 * Короткое описание методов клиента:
 * - main(String[] args)
 *      Точка входа. Создаёт контроллеры и запускает главное меню приложения.
 *
 * - filmOperations()
 *      Меню и обработка команд для работы с фильмами:
 *      1) Сохранить фильм (save)
 *      2) Получить все активные фильмы (getAllActiveFilm)
 *      3) Получить активный фильм по id (getActive)
 *      4) Изменить рейтинг фильма (updateRating)
 *      5) Удалить фильм по id (deleteById)
 *      6) Удалить фильм по названию (deleteByTitle)
 *      7) Восстановить фильм по id (restoreById)
 *      8) Показать количество активных фильмов (getActiveProductsCount)
 *
 * - customerOperations()
 *      Меню и обработка команд для работы с покупателями:
 *      1) Сохранить покупателя (save)
 *      2) Получить всех активных покупателей (getAllActiveCustomers)
 *      3) Получить активного покупателя по id (getActiveCustomerById)
 *      4) Удалить покупателя по id (deleteById)
 *      5) Удалить покупателя по имени (deleteByName)
 *      6) Восстановить покупателя по id (restoreById)
 *      7) Показать количество активных покупателей (getActiveCustomersNumber)
 *
 * Использование методов контроллеров (проверка):
 *
 * FilmController:
 *  - save(name, rating, year, genre)           — используется (filmOperations -> "1").
 *  - getAllActiveFilm()                        — используется (filmOperations -> "2").
 *  - getActive(String id)                      — используется (filmOperations -> "3").
 *  - updateRating(String id, String rating)    — используется (filmOperations -> "4").
 *      ВАЖНО: в вашем FilmController обнаружена опечатка: там вызов
 *      Double.parseDouble(id) вместо Double.parseDouble(rating).
 *      Исправьте в контроллере, иначе операция будет падать.
 *  - deleteById(String id)                     — используется (filmOperations -> "5").
 *  - deleteByTitle(String title)               — используется (filmOperations -> "6").
 *  - restoreById(String id)                    — используется (filmOperations -> "7").
 *  - getActiveProductsCount()                  — используется (filmOperations -> "8").
 *
 * CustomerController:
 *  - save(String name)                         — используется (customerOperations -> "1").
 *  - getAllActiveCustomers()                   — используется (customerOperations -> "2").
 *  - getActiveCustomerById(String id)          — используется (customerOperations -> "3").
 *  - deleteById(String id)                     — используется (customerOperations -> "4").
 *  - deleteByName(String name)                 — используется (customerOperations -> "5").
 *  - restoreById(String id)                    — используется (customerOperations -> "6").
 *  - getActiveCustomersNumber()                — используется (customerOperations -> "7").
 *
 * Вывод: все публичные методы, перечисленные в предоставленных контроллерах,
 * используются из этого клиента. Если вы добавите новые методы в контроллеры —
 * стоит дополнить клиент соответствующими пунктами меню.
 */

public class Client {

    private static FilmController filmController;
    private static CustomerController customerController;
    private static Scanner scanner;

    public static void main(String[] args) {
        try {
            filmController = new FilmController();
            customerController = new CustomerController();
            scanner = new Scanner(System.in);
        } catch (Exception e) {
            System.err.println("Ошибка при создании контроллеров: " + e.getMessage());
            return;
        }

        while (true) {
            System.out.println("\n=== Главное меню ===");
            System.out.println("1 - Операции с фильмами");
            System.out.println("2 - Операции с покупателями");
            System.out.println("0 - Выход");
            System.out.print("Выберите опцию: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    filmOperations();
                    break;
                case "2":
                    customerOperations();
                    break;
                case "0":
                    System.out.println("Выход. До свидания!");
                    return;
                default:
                    System.out.println("Некорректный ввод!");
            }
        }
    }

    private static void filmOperations() {
        while (true) {
            try {
                System.out.println("\n--- Операции с фильмами ---");
                System.out.println("1 - Сохранить фильм");
                System.out.println("2 - Получить все активные фильмы");
                System.out.println("3 - Получить активный фильм по id");
                System.out.println("4 - Изменить рейтинг фильма");
                System.out.println("5 - Удалить фильм по id");
                System.out.println("6 - Удалить фильм по названию");
                System.out.println("7 - Восстановить фильм по id");
                System.out.println("8 - Количество активных фильмов");
                System.out.println("0 - Назад");
                System.out.print("Выберите опцию: ");

                String input = scanner.nextLine().trim();
                String id, title, rating, year, genre;

                switch (input) {
                    case "1":
                        System.out.print("Название: ");
                        title = scanner.nextLine();
                        System.out.print("Рейтинг (например 8.5): ");
                        rating = scanner.nextLine();
                        System.out.print("Год (например 2020): ");
                        year = scanner.nextLine();
                        System.out.print("Жанр: ");
                        genre = scanner.nextLine();
                        Film saved = filmController.save(title, rating, year, genre);
                        System.out.println("Сохранено: " + saved);
                        break;
                    case "2":
                        List<Film> films = filmController.getAllActiveFilm();
                        if (films == null || films.isEmpty()) {
                            System.out.println("Список пуст.");
                        } else {
                            films.forEach(System.out::println);
                        }
                        break;
                    case "3":
                        System.out.print("Введите id: ");
                        id = scanner.nextLine();
                        Film f = filmController.getActive(id);
                        System.out.println(f);
                        break;
                    case "4":
                        System.out.print("Введите id фильма: ");
                        id = scanner.nextLine();
                        System.out.print("Введите новый рейтинг (например 7.3): ");
                        rating = scanner.nextLine();
                        // ВАЖНО: в вашем FilmController есть опечатка — там парсится id вместо rating.
                        // Если операция падает — исправьте контроллер на Double.parseDouble(rating).
                        filmController.updateRating(id, rating);
                        System.out.println("Запрос на изменение рейтинга отправлен.");
                        break;
                    case "5":
                        System.out.print("Введите id: ");
                        id = scanner.nextLine();
                        filmController.deleteById(id);
                        System.out.println("Фильм удалён (помечен как неактивный).");
                        break;
                    case "6":
                        System.out.print("Введите название: ");
                        title = scanner.nextLine();
                        filmController.deleteByTitle(title);
                        System.out.println("Фильм(ы) с названием \"" + title + "\" удалены (помечены как неактивные).");
                        break;
                    case "7":
                        System.out.print("Введите id: ");
                        id = scanner.nextLine();
                        filmController.restoreById(id);
                        System.out.println("Фильм восстановлен (если такой был).");
                        break;
                    case "8":
                        System.out.println("Активных фильмов: " + filmController.getActiveProductsCount());
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("Некорректный ввод!");
                }
            } catch (Exception e) {
                System.err.println("Ошибка: " + e.getMessage());
            }
        }
    }

    private static void customerOperations() {
        while (true) {
            try {
                System.out.println("\n--- Операции с покупателями ---");
                System.out.println("1 - Сохранить покупателя");
                System.out.println("2 - Получить всех активных покупателей");
                System.out.println("3 - Получить активного покупателя по id");
                System.out.println("4 - Удалить покупателя по id");
                System.out.println("5 - Удалить покупателя по имени");
                System.out.println("6 - Восстановить покупателя по id");
                System.out.println("7 - Количество активных покупателей");
                System.out.println("8 - Добавить фильм в библиотеку покупателя (addFilmToCustomersLibrary)");
                System.out.println("9 - Удалить фильм из библиотеки покупателя (removeProductFromCustomersCart)");
                System.out.println("10 - Очистить библиотеку покупателя (clearCustomersCart)");
                System.out.println("0 - Назад");
                System.out.print("Выберите опцию: ");

                String input = scanner.nextLine().trim();
                String id, name, customerId, filmId;

                switch (input) {
                    case "1":
                        System.out.print("Введите имя покупателя: ");
                        name = scanner.nextLine();
                        Customer saved = customerController.save(name);
                        System.out.println("Сохранено: " + saved);
                        break;

                    case "2":
                        List<Customer> customers = customerController.getAllActiveCustomers();
                        if (customers == null || customers.isEmpty()) {
                            System.out.println("Список пуст.");
                        } else {
                            customers.forEach(System.out::println);
                        }
                        break;

                    case "3":
                        System.out.print("Введите id: ");
                        id = scanner.nextLine();
                        Customer c = customerController.getActiveCustomerById(id);
                        System.out.println(c);
                        break;

                    case "4":
                        System.out.print("Введите id: ");
                        id = scanner.nextLine();
                        customerController.deleteById(id);
                        System.out.println("Покупатель удалён (помечен как неактивный).");
                        break;

                    case "5":
                        System.out.print("Введите имя покупателя: ");
                        name = scanner.nextLine();
                        customerController.deleteByName(name);
                        System.out.println("Покупатель(и) с именем \"" + name + "\" удалены (помечены как неактивные).");
                        break;

                    case "6":
                        System.out.print("Введите id: ");
                        id = scanner.nextLine();
                        customerController.restoreById(id);
                        System.out.println("Покупатель восстановлен (если такой был).");
                        break;

                    case "7":
                        System.out.println("Активных покупателей: " + customerController.getActiveCustomersNumber());
                        break;

                    case "8":
                        System.out.print("Введите id покупателя: ");
                        customerId = scanner.nextLine();
                        System.out.print("Введите id фильма: ");
                        filmId = scanner.nextLine();
                        customerController.addFilmToCustomersLibrary(customerId, filmId);
                        System.out.println("Фильм добавлен в библиотеку покупателя.");
                        break;

                    case "9":
                        System.out.print("Введите id покупателя: ");
                        customerId = scanner.nextLine();
                        System.out.print("Введите id фильма: ");
                        filmId = scanner.nextLine();
                        customerController.removeProductFromCustomersCart(customerId, filmId);
                        System.out.println("Фильм удалён из библиотеки покупателя.");
                        break;

                    case "10":
                        System.out.print("Введите id покупателя: ");
                        customerId = scanner.nextLine();
                        customerController.clearCustomersCart(customerId);
                        System.out.println("Библиотека покупателя очищена.");
                        break;

                    case "0":
                        return;

                    default:
                        System.out.println("Некорректный ввод!");
                }
            } catch (Exception e) {
                System.err.println("Ошибка: " + e.getMessage());
            }
        }
    }

}
