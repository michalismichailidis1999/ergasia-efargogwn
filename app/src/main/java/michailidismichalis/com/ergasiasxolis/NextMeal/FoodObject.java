package michailidismichalis.com.ergasiasxolis.NextMeal;

import java.io.Serializable;

public class FoodObject implements Serializable {
    private String id, name, photo, category;
    private int kcals, protein, fat, carbs;

    public void setId(String id) {
        this.id = id;
    }

    public FoodObject(){}

    public FoodObject(String id, String category, String name, String photo, int kcals, int protein, int fat, int carbs) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.photo = photo;
        this.kcals = kcals;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getKcals() {
        return kcals;
    }

    public String getCategory() {
        return category;
    }

    public int getProtein() {
        return protein;
    }

    public int getFat() {
        return fat;
    }

    public int getCarbs() {
        return carbs;
    }

    public String getPhoto() {
        return photo;
    }
}
