package michailidismichalis.com.ergasiasxolis.NextMeal;

public class FoodSnapshot {
    public String id, name, photo, category,kcals, protein, fat, carbs;

    public FoodSnapshot(String carbs, String category, String fat, String kcals, String name, String photo, String protein) {
        this.name = name;
        this.photo = photo;
        this.category = category;
        this.kcals = kcals;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }
}
