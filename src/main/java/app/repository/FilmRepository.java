package app.repository;

import app.domain.Customer;
import app.domain.Film;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FilmRepository {

    // Имитация базы данных
    private final Map<Long, Film> filmDataBase = new HashMap<>();
    // Поле, которое учитывает, какой сейчас максимальный идентификатор продукта в базе данных
    private long maxId;

    // Операция Create. Сохранение нового продукта в базе данных.
    public Film save(Film Film) {
        Film.setId(++maxId);
        filmDataBase.put(maxId, Film);
        return Film;
    }

    // Операция Read. Чтение всех продуктов из базы данных.
    public List<Film> findAll() {
        return new ArrayList<>(filmDataBase.values());
    }

    public Film findById(Long id) {
        return filmDataBase.get(id);
    }

    public void updateRating(Long id, Double newRating) {
        Film film = filmDataBase.get(id);
        if (film != null) {
            film.setRating(newRating);
        }
    }

    public void deleteById(Long id) {
        filmDataBase.remove(id);
    }
}
