package entity;

import java.util.List;

public class Receipe {
    private int id;
    private String name;
    private String description;
    private List<Item> ingredients;
    public Receipe(int id, String name, String description, List<Item> ingredients){
        this.id = id;
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Item> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Item> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(Item ingredient){
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(Item ingredient){
        this.ingredients.remove(ingredient);
    }

}
