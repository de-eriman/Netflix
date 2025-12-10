package app.service;

import app.domain.Film;
import app.exceptions.FilmSaveException;
import app.exceptions.FilmNotFoundException;
import app.exceptions.FilmUpdateException;
import app.repository.FilmRepository;

import java.util.List;

public class FilmService {

    private static FilmService instance;
    private final FilmRepository repository = new FilmRepository();

    private FilmService() {
    }
    
    public static FilmService getInstance() {
        if (instance == null) {
            instance = new FilmService();
        }
        return instance;
    }
    
    //    Сохранить продукт в базе данных (при сохранении продукт автоматически считается активным).
    public Film save(Film film) {
        if (film == null) {
            throw new FilmSaveException("Продукт не может быть null");
        }

        String title = film.getName();
        Double rating = film.getRating();
        int year = film.getYear();
        String genre = film.getGenre();

        if (title == null || title.trim().isEmpty() ||
                genre == null || genre.trim().isEmpty() ||
                rating == null
        ) {
            throw new FilmSaveException("Поля не должны быть пустыми");
        }
        film.setActive(true);
        return repository.save(film);
    }

    //    Вернуть все продукты из базы данных (активные).
    public List<Film> getAllActiveFilm() {
        return repository.findAll()
                .stream()
                .filter(Film::isActive)
                .toList();
    }


    //    Вернуть один продукт из базы данных по его идентификатору (если он активен).
    public Film getActiveFilmById(Long id) {
        Film product = repository.findById(id);
        if (product == null || !product.isActive()) {
            throw new FilmNotFoundException(id);
        }
        return product;
    }


    //    Изменить один продукт в базе данных по его идентификатору.
    public void updateRating(Long id, Double newRating) {
        if (newRating == null) {
            throw new FilmUpdateException("newRating == null");
        }
        repository.updateRating(id, newRating);
    }

    //    Удалить продукт из базы данных по его идентификатору.
    public void deleteById(Long id) {
        Film film = getActiveFilmById(id);
        film.setActive(false);
    }


    //    Удалить продукт из базы данных по его наименованию.
    public void deleteByTitle(String title) {
        getAllActiveFilm()
                .stream()
                .filter(x -> x.getName().equals(title))
                .forEach(x -> x.setActive(false));

    //    Изменить рейтинг фильма в базе данных по его идентификатору.
    }
    
    //    Восстановить удалённый продукт в базе данных по его идентификатору.
    public void restoreById(Long id) {
        Film film = repository.findById(id);
        if (film == null) {
            throw new FilmNotFoundException(id);
        }
        film.setActive(true);
    }

    //    Вернуть общее количество продуктов в базе данных (активных).
    public int getActiveProductsCount() {
        return getAllActiveFilm().size();
    }


}
