package michailidismichalis.com.ergasiasxolis.progress;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;


import michailidismichalis.com.ergasiasxolis.NextMeal.FoodObject;

import michailidismichalis.com.ergasiasxolis.NextMeal.MealObject;

import michailidismichalis.com.ergasiasxolis.R;

public class ProgressActivity extends AppCompatActivity {
    private RecyclerView eatedmeals;
    private RecyclerView.Adapter EatedMealsAdapter;


    ArrayList<MealObject> Mealist;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);


        Mealist= new ArrayList<>();
        EatedMeals();
        initializeEatedMealRecyclerView();
        System.out.println("hiii");
        EatedMealsByDay();



    }







    private void EatedMeals(){
        DatabaseReference refDB = FirebaseDatabase.getInstance().getReference().child("meals").child(FirebaseAuth.getInstance().getUid());

        refDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<MealObject> meals = new ArrayList<>();

                if(snapshot.exists()){
                    for(DataSnapshot mealSnapshot: snapshot.getChildren()) {
                        ArrayList<FoodObject> foodList = new ArrayList<>();
                        for (DataSnapshot foodSnapshot : mealSnapshot.getChildren()) {
                            Iterator<DataSnapshot> iterator = foodSnapshot.getChildren().iterator();
                            System.out.println(foodSnapshot.getChildren());
                            String category = "", name = "", photo = "";
                            int carbs = 0, kcals = 0, fat = 0, protein = 0;

                            while (iterator.hasNext()) {
                                DataSnapshot foodValue = iterator.next();

                                switch (foodValue.getKey()){  //foodvalu.getKey=milliseconds
                                    case "kcals":
                                        kcals = Integer.parseInt(foodValue.getValue().toString());
                                        break;
                                    case "fat":
                                        fat = Integer.parseInt(foodValue.getValue().toString());
                                        break;
                                    case "protein":
                                        protein = Integer.parseInt(foodValue.getValue().toString());
                                        break;
                                    case "carbs":
                                        carbs = Integer.parseInt(foodValue.getValue().toString());
                                        break;
                                    case "category":
                                        category = foodValue.getValue().toString();
                                        break;
                                    case "name":
                                        name = foodValue.getValue().toString();
                                        break;

                                    default:
                                        break;
                                }

                            }

                            FoodObject fo = new FoodObject(
                                    foodSnapshot.getKey(),
                                    category,
                                    name,
                                    photo,
                                    kcals,
                                    protein,
                                    fat,
                                    carbs
                            );

                            foodList.add(fo);
                        }
                        //System.out.println(mealSnapshot.getKey());
                        meals.add(new MealObject(mealSnapshot.getKey(), foodList));

                    }

                    for(MealObject mo: meals){
                        Mealist.add(mo);
                    }
                    EatedMealsAdapter.notifyDataSetChanged();


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    private void initializeEatedMealRecyclerView(){
        eatedmeals = findViewById(R.id.eatedmeals);
        eatedmeals.setHasFixedSize(false);
        RecyclerView.LayoutManager EatedMealsLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        eatedmeals.setLayoutManager(EatedMealsLayoutManager);

        EatedMealsAdapter = new EatedMealsAdapter(this, R.layout.eated_meals_food, Mealist);
        eatedmeals.setAdapter(EatedMealsAdapter);
        System.out.println("Items on adapter are: " + EatedMealsAdapter.getItemCount());
    }


    private void EatedMealsByDay(){

        System.out.println("fff");
        for (MealObject mo: Mealist)
        {
            System.out.println(mo.getId());
        }

    }








}



