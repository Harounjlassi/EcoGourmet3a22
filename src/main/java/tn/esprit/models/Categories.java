package tn.esprit.models;

public class Categories {
    private int id;
    private String name;
    private String description;

    public Categories() {
    }

    public Categories(int id, String name, String discription) {
        this.id = id;
        this.name = name;
        this.description = discription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "categories{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", discription='" + description + '\'' +
                '}';
    }
}
