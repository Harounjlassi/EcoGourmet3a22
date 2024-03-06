package tn.esprit.models;

public class Categories {
    private int category_id ;
    private String name;
    private String description;

    public Categories() {
    }

    public Categories(int category_id, String name, String description) {
        this.category_id  = category_id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return category_id ;
    }

    public void setId(int id) {
        this.category_id  = id;
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
                "id=" + category_id  +
                ", name='" + name + '\'' +
                ", discription='" + description + '\'' +
                '}';
    }
}
