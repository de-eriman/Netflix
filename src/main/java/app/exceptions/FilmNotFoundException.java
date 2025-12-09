package app.exceptions;

public class FilmNotFoundException extends RuntimeException {
    public FilmNotFoundException(Long id) {
        super(String.format("Продукт с идентификатором %d не найден", id));
    }
}
