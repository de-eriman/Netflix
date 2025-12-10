package app.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer {
    private Long id;
    private String name;
    private boolean active;
    private final List<Film> library = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
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

    public List<Film> getLibrary() {
        return library;
    }



    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Customer {\n");
        sb.append("  id = ").append(id).append(",\n");
        sb.append("  name = '").append(name).append("',\n");
        sb.append("  active = ").append(active).append(",\n");
        sb.append("  library = [\n");

        for (Film film : library) {
            sb.append("    ").append(film).append("\n");
        }

        sb.append("  ]\n");
        sb.append("}");
        return sb.toString();
    }


}
