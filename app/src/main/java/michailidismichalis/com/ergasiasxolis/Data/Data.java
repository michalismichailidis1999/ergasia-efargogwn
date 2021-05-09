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
        foodList.add(new FoodObject("2", "Meat", "Rise", "https://www.healthweb.gr/wp-content/uploads/2020/12/r2.jpg", 169, 31, 4, 0));
        foodList.add(new FoodObject("3", "Meat", "Potatos", "https://5.imimg.com/data5/CL/OX/SB/GLADMIN-2063743/selection-020-500x500.png", 180, 1, 4, 40));
        foodList.add(new FoodObject("4", "Meat", "Steak", "https://www.siakos.gr/340-large_default/strip-loin-steak-moscharisio-ellados.jpg", 169, 31, 4, 0));
    }

    public ArrayList<FoodObject> getFoodList() {
        return foodList;
    }
}
