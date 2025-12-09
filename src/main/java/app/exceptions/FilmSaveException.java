package app.exceptions;

public class FilmSaveException extends RuntimeException {
    public FilmSaveException(String message) {
        super(message);
    }
}
