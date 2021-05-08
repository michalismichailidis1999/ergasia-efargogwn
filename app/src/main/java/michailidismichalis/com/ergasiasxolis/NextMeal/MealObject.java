package michailidismichalis.com.ergasiasxolis.NextMeal;

import java.util.ArrayList;

public class MealObject {
    private String id;
    private ArrayList<FoodObject> foodList;

    public MealObject(){
        this.foodList = new ArrayList<>();
    }

    public MealObject(String id, ArrayList<FoodObject> foodList)
    {
        this.id = id;
        this.foodList = foodList;
    }



    public void addFood(FoodObject food){
        this.foodList.add(food);
    }

    public void removeFood(String foodId){
        for(int i = 0; i < foodList.size(); i++){
            if(foodList.get(i).getId().equals(foodId)){
                foodList.remove(i);
                break;
            }
        }
    }

    public ArrayList<FoodObject> getFoodList() {
        return foodList;
    }

    public String getId() {
        return id;
    }
}
