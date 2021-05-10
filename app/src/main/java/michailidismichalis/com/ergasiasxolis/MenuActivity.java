package michailidismichalis.com.ergasiasxolis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import michailidismichalis.com.ergasiasxolis.NextMeal.NextMealActivity;
import michailidismichalis.com.ergasiasxolis.progress.ProgressActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button nextMealButton = findViewById(R.id.nextMealButton);

        nextMealButton.setOnClickListener(v -> {startActivity(new Intent(this, NextMealActivity.class));});


        Button checkProgressButton = findViewById(R.id.checkProgressButton);

        checkProgressButton.setOnClickListener(v -> {startActivity(new Intent(this, ProgressActivity.class));});

        Button profileButton = findViewById(R.id.profileButton);
        profileButton.setOnClickListener(v -> {startActivity(new Intent(this, ProfileActivity.class));});

        Button exitButton = findViewById(R.id.exitButton);

        exitButton.setOnClickListener(v -> {finish();});
    }
}