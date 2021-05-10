package michailidismichalis.com.ergasiasxolis.progress;



import java.io.Serializable;

public class FoodObject2 implements Serializable {
    private final String id;
    private final int kcals, protein, fat, carbs;

    public FoodObject2(String id, int kcals, int protein, int fat, int carbs) {
        this.id = id;
        this.kcals = kcals;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }

    public String getId2() {
        return id;
    }
    public int getKcals2() {
        return kcals;
    }
    public int getProtein2() {
        return protein;
    }

    public int getFat2() {
        return fat;
    }

    public int getCarbs2() {
        return carbs;
    }

}
