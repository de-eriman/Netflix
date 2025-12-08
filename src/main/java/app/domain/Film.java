package app.domain;

import java.util.Objects;

public class Film {
    private Long id;
    private String name;
    private boolean active;
    private Double rating;

    public Film(String name, boolean active, Double rating) {
        this.name = name;
        this.active = active;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return Objects.equals(id, film.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Films{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", active=" + active +
                ", rating=" + rating +
                '}';
    }
}
