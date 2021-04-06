package michailidismichalis.com.ergasiasxolis.NextMeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.util.Util;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import michailidismichalis.com.ergasiasxolis.R;

public class SaveMealActivity extends AppCompatActivity {
    private EditText mealName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_meal);

        mealName = findViewById(R.id.mealName);

        Button saveMealButton = findViewById(R.id.saveMealButton);

        saveMealButton.setOnClickListener(v -> {saveMeal();});

        Button cancelSaveMealButton = findViewById(R.id.cancelSaveMealButton);

        cancelSaveMealButton.setOnClickListener(v -> {finish();});
    }

    private void saveMeal(){
        String name = mealName.getText().toString();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(this, "Meal name is required", Toast.LENGTH_LONG).show();
            return;
        }

        final ArrayList<FoodObject> foodList = (ArrayList<FoodObject>) getIntent().getSerializableExtra("foodList");

        DatabaseReference savedMealsDB = FirebaseDatabase.getInstance().getReference().child("savedMeals").child(FirebaseAuth.getInstance().getUid()).child(name);

        for(FoodObject fo: foodList){
            Map<String, String> map = new HashMap<>();

            map.put("name", fo.getName());
            map.put("photo", fo.getPhoto());
            map.put("category", fo.getCategory());
            map.put("kcals", fo.getKcals() + "");
            map.put("protein", fo.getProtein() + "");
            map.put("fat", fo.getFat() + "");
            map.put("carbs", fo.getCarbs() + "");

            savedMealsDB.push().setValue(map);
        }

        finish();
    }
}