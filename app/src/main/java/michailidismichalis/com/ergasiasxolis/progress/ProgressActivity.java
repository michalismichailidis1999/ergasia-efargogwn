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

import michailidismichalis.com.ergasiasxolis.NextMeal.AddedMealAdapter;
import michailidismichalis.com.ergasiasxolis.NextMeal.FoodObject;
import michailidismichalis.com.ergasiasxolis.NextMeal.MealAdapter;
import michailidismichalis.com.ergasiasxolis.NextMeal.MealObject;
import michailidismichalis.com.ergasiasxolis.NextMeal.SavedMealAdapter;
import michailidismichalis.com.ergasiasxolis.R;

public class ProgressActivity extends AppCompatActivity {
    private RecyclerView eatedMeals;
    private RecyclerView.Adapter EatedMealsAdapter;


    ArrayList<MealObject> Mealist;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);


        Mealist= new ArrayList<>();
        fetchMeals();
        initializeSavedMealRecyclerView();
        System.out.println("hiii");

        //MealHistory.setVisibility(View.GONE);
        //Mealist.add(new MealObjectEated(11/05/24,"Steak",290,25,13,20));

    }







    private void fetchMeals(){
        DatabaseReference refDB = FirebaseDatabase.getInstance().getReference().child("meals").child(FirebaseAuth.getInstance().getUid());

        refDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<MealObject> meals = new ArrayList<>();

                if(snapshot.exists()){
                    for(DataSnapshot mealSnapshot: snapshot.getChildren()) {
                        ArrayList<FoodObject> foodList = new ArrayList<>();
                        System.out.println("00000");
                        for (DataSnapshot foodSnapshot : mealSnapshot.getChildren()) {
                            Iterator<DataSnapshot> iterator = foodSnapshot.getChildren().iterator();

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
                                    case "photo":
                                        photo = foodValue.getValue().toString();
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



    private void initializeSavedMealRecyclerView(){
        eatedMeals = findViewById(R.id.eatedmeals);
        eatedMeals.setHasFixedSize(false);
        RecyclerView.LayoutManager savedMealsLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        eatedMeals.setLayoutManager(savedMealsLayoutManager);

        EatedMealsAdapter = new SavedMealAdapter(this, R.layout.saved_meal_food, Mealist);
        eatedMeals.setAdapter(EatedMealsAdapter);
        System.out.println("Items on adapter are: " + EatedMealsAdapter.getItemCount());
    }


}



