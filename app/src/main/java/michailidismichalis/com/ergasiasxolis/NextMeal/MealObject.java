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


    public int getTotallKcals(){
        int totallKcals=0;
        for (FoodObject Fo: foodList)
        {
            totallKcals+=Fo.getKcals();
        }
        return  totallKcals;
    }

    public int getTotalProtein(){
        int totalProtein=0;
        for (FoodObject Fo: foodList)
        {
            totalProtein+=Fo.getProtein();
        }
        return  totalProtein;
    }

    public int getTotalFat(){
        int totalFat=0;
        for (FoodObject Fo: foodList)
        {
            totalFat+=Fo.getFat();
        }
        return  totalFat;
    }

    public int getTotalCarbs(){
        int Totalcarbs=0;
        for (FoodObject Fo: foodList)
        {
            Totalcarbs+=Fo.getCarbs();
        }
        return  Totalcarbs;
    }






}
