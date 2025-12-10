package app.controller;

import app.domain.Film;
import app.exceptions.FilmNotFoundException;
import app.exceptions.FilmUpdateException;
import app.service.FilmService;

import java.util.List;

public class FilmController {
    // Имитация базы данных

    private final FilmService service = FilmService.getInstance();

    public Film save(String name, String rating, String year, String genre) {
        Double ratingDouble = Double.parseDouble(rating);
        int yearInt = Integer.parseInt(year);

        Film film = new Film(name,ratingDouble,yearInt,genre);
        return service.save(film);
    }

    //    Сохранить продукт в базе данных (при сохранении продукт автоматически считается активным).

    public List<Film> getAllActiveFilm(){
        return service.getAllActiveFilm();
    }

    public Film getActive(String id){
        Long idLong = Long.parseLong(id);
        return service.getActiveFilmById(idLong);
    }

    public void updateRating(String id, String rating){
        Long idLong = Long.parseLong(id);
        Double ratingDouble = Double.parseDouble(id);
        service.updateRating(idLong, ratingDouble);
    }

    public void deleteById(String id){
        Long idLong = Long.parseLong(id);
        service.deleteById(idLong);
    }

    public void deleteByTitle(String title){
        service.deleteByTitle(title);
    }

    public void restoreById(String id){
        Long idLong = Long.parseLong(id);
        service.restoreById(idLong);
    }

    public int getActiveProductsCount(){
        return service.getAllActiveFilm().size();
    }

}
