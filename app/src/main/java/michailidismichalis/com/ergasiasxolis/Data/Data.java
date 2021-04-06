package michailidismichalis.com.ergasiasxolis.Data;

import java.util.ArrayList;

import michailidismichalis.com.ergasiasxolis.NextMeal.FoodObject;

public class Data {
    private ArrayList<FoodObject> foodList;

    public Data(){
        this.foodList = new ArrayList<>();

        createData();
    }

    private void createData(){
        foodList.add(new FoodObject("1", "Meat", "Chicken Breast", "https://www.spendwithpennies.com/wp-content/uploads/2018/08/SpendWithPennies-Oven-Baked-Chicken-Breast-22-500x500.jpg", 169, 31, 4, 0));
    }

    public ArrayList<FoodObject> getFoodList() {
        return foodList;
    }
}
