package entity;

import java.util.List;

public class Receipe {
    int id;
    String name;
    String description;
    List<Item> ingredients;
    public Receipe(int id, String name, String description, List<Item> ingredients){
        this.id = id;
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Receipe(" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ingredients=" + ingredients +
                ')';
    }
}
