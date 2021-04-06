package michailidismichalis.com.ergasiasxolis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import michailidismichalis.com.ergasiasxolis.Auth.LoginActivity;
import michailidismichalis.com.ergasiasxolis.Auth.RegisterUserInfoActivity;

public class InitialScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_screen);

        FirebaseApp.initializeApp(this);

        userIsLoggedIn();

        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> startActivity(new Intent(this, LoginActivity.class)));

        Button registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(v -> startActivity(new Intent(this, RegisterUserInfoActivity.class)));
    }

    private void userIsLoggedIn() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null){
            startActivity(new Intent(getApplicationContext(), MenuActivity.class));
            finish();
            return;
        }
    }
}