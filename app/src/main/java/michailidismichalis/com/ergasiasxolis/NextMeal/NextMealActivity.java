package michailidismichalis.com.ergasiasxolis.NextMeal;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import michailidismichalis.com.ergasiasxolis.Data.Data;
import michailidismichalis.com.ergasiasxolis.R;

public class NextMealActivity extends AppCompatActivity {
    private RecyclerView addedMeals;
    private static RecyclerView.Adapter addedMealsAdapter;
    private RecyclerView meals;
    private RecyclerView.Adapter mealsAdapter;
    private Data data;
    private static ArrayList<FoodObject> addedMealsList;
    private Spinner categories;
    private static ArrayList<FoodObject> mealList;
    private AlertDialog dialog;
    private Button createMealButton;
    private static  MealObject myMeal;
    private static Map<String, Boolean> existingMeals;
    private static TextView totalKcals;
    private static TextView totalProtein;
    private static TextView totalFat;
    private static TextView totalCarbs;
    private static ArrayList<MealObject> savedMealsList;
    private RecyclerView savedMeals;
    private RecyclerView.Adapter savedMealsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_meal);

        data = new Data();
        existingMeals = new HashMap<>();

        addedMealsList = new ArrayList<>();
        mealList = new ArrayList<>();
        savedMealsList = new ArrayList<>();

        myMeal = new MealObject();

        totalKcals = findViewById(R.id.totalKcals);
        totalProtein = findViewById(R.id.totalProtein);
        totalFat = findViewById(R.id.totalFat);
        totalCarbs = findViewById(R.id.totalCarbs);

        TextView createMealsOption = findViewById(R.id.createMealOption);
        TextView savedMealsOption = findViewById(R.id.savedMealsOption);

        LinearLayout categoryOptionsLayout = findViewById(R.id.categoryOptionLayout);

        createMealsOption.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                createMealsOption.setBackground(getDrawable(R.drawable.text_border_selected));
                createMealsOption.setTextColor(Color.WHITE);
                savedMealsOption.setBackground(getDrawable(R.drawable.text_border));
                savedMealsOption.setTextColor(Color.BLACK);

                categoryOptionsLayout.setVisibility(View.VISIBLE);
                meals.setVisibility(View.VISIBLE);
                savedMeals.setVisibility(View.GONE);
            }
        });

        savedMealsOption.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                savedMealsOption.setBackground(getDrawable(R.drawable.text_border_selected));
                savedMealsOption.setTextColor(Color.WHITE);
                createMealsOption.setBackground(getDrawable(R.drawable.text_border));
                createMealsOption.setTextColor(Color.BLACK);

                savedMeals.setVisibility(View.VISIBLE);
                meals.setVisibility(View.GONE);
                categoryOptionsLayout.setVisibility(View.GONE);
            }
        });

        ArrayAdapter<CharSequence> categoriesAdapter = ArrayAdapter.createFromResource(this, R.array.meal_categories, R.layout.support_simple_spinner_dropdown_item);
        categories = findViewById(R.id.cateogries);
        categories.setAdapter(categoriesAdapter);
        categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setMealList(categoriesAdapter.getItem(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fetchSavedMeals();

        createMealButton = findViewById(R.id.createMealButton);
        createMealButton.setOnClickListener(v -> showConfirmDialog(dialog));

        initializeAddedMealRecyclerView();
        initializeMealRecyclerView();
        initializeSavedMealRecyclerView();

        createConfirmDialog();
        setMealList("Meat");
    }

    public static void deleteAddedMeal(int position){
        myMeal.removeFood(mealList.get(position).getId());
        addedMealsList.remove(position);
        addedMealsAdapter.notifyDataSetChanged();
    }

    public static void addMeal(int position){
        for(FoodObject fo: addedMealsList){
            if(mealList.get(position).getId() == fo.getId()){
                return;
            }
        }

        myMeal.addFood(mealList.get(position));
        addedMealsList.add(mealList.get(position));
        addedMealsAdapter.notifyDataSetChanged();

        int kcals = 0;
        int protein = 0;
        int fat = 0;
        int carbs = 0;
        for(FoodObject fo: addedMealsList){
            kcals += fo.getKcals();
            protein += fo.getProtein();
            fat += fo.getFat();
            carbs += fo.getCarbs();
        }

        totalKcals.setText("Total Kcals: " + kcals);
        totalProtein.setText("Total Protein: " + protein);
        totalFat.setText("Total Fat: " + fat);
        totalCarbs.setText("Total Carbs: " + carbs);
    }

    private void initializeAddedMealRecyclerView(){
        addedMeals = findViewById(R.id.addedMeals);
        addedMeals.setNestedScrollingEnabled(false);
        addedMeals.setHasFixedSize(false);
        RecyclerView.LayoutManager addedMealsLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        addedMeals.setLayoutManager(addedMealsLayoutManager);

        addedMealsAdapter = new AddedMealAdapter(addedMealsList);
        addedMeals.setAdapter(addedMealsAdapter);
    }

    private void initializeMealRecyclerView(){
        meals = findViewById(R.id.meals);
        meals.setNestedScrollingEnabled(false);
        meals.setHasFixedSize(false);
        RecyclerView.LayoutManager mealsLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        meals.setLayoutManager(mealsLayoutManager);

        mealsAdapter = new MealAdapter(this, mealList);
        meals.setAdapter(mealsAdapter);
    }

    private void initializeSavedMealRecyclerView(){
        savedMeals = findViewById(R.id.savedMeals);
        //savedMeals.setNestedScrollingEnabled(false);
        savedMeals.setHasFixedSize(false);
        RecyclerView.LayoutManager savedMealsLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        savedMeals.setLayoutManager(savedMealsLayoutManager);

        savedMealsAdapter = new SavedMealAdapter(this, R.layout.saved_meal_food, savedMealsList);
        savedMeals.setAdapter(savedMealsAdapter);
        System.out.println("Items on adapter are: " + savedMealsAdapter.getItemCount());
    }

    private void setMealList(String category){
        ArrayList<FoodObject> foodsToRemove = new ArrayList<>();

        for(FoodObject fo: mealList){
            foodsToRemove.add(fo);
        }

        for(FoodObject fo: foodsToRemove){
            mealList.remove(fo);
            existingMeals.remove(fo.getId());
        }

        for(FoodObject fo: data.getFoodList()){
            if(fo.getCategory().equals(category) && existingMeals.get(fo.getId()) == null){
                mealList.add(fo);
                existingMeals.put(fo.getId(), true);
            }
        }

        mealsAdapter.notifyDataSetChanged();
    }

    private void fetchSavedMeals(){
        DatabaseReference refDB = FirebaseDatabase.getInstance().getReference().child("savedMeals").child(FirebaseAuth.getInstance().getUid());

        refDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<MealObject> meals = new ArrayList<>();

                if(snapshot.exists()){
                    for(DataSnapshot savedMealSnapshot: snapshot.getChildren()){
                        ArrayList<FoodObject> foodList = new ArrayList<>();

                        Map<String, FoodObject> foodSnapshot = (Map<String, FoodObject>) savedMealSnapshot.getValue();


                        for (Map.Entry<String,FoodObject> entry : foodSnapshot.entrySet()){
//                            System.out.println(""+ entry.getKey() +
//                                    entry.getValue());

//                            FoodObject fo = new FoodObject(
//                                    entry.getKey(),
//                                    entry.getValue().getCategory(),
//                                    entry.getValue().getName(),
//                                    entry.getValue().getPhoto(),
//                                    entry.getValue().getKcals(),
//                                    entry.getValue().getProtein(),
//                                    entry.getValue().getFat(),
//                                    entry.getValue().getCarbs()
//                            );
//
//                            foodList.add(fo);
                        }

//                        meals.add(new MealObject(savedMealSnapshot.getKey(), foodList));
                    }

                    savedMealsList = meals;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void createConfirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Confirm");
        builder.setMessage("Do you want to save this meal?");
        builder.setPositiveButton("Save Meal",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), SaveMealActivity.class);
                        intent.putExtra("foodList", addedMealsList);

                        startActivity(intent);

                        finish();
                    }
                });
        builder.setNegativeButton("Don't Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        this.dialog = builder.create();
    }

    private void showConfirmDialog(AlertDialog dialog){
            dialog.show();
    }
}