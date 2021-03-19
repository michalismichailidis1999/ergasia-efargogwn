package michailidismichalis.com.ergasiasxolis.Auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import michailidismichalis.com.ergasiasxolis.MainMenuActivity;
import michailidismichalis.com.ergasiasxolis.R;

public class LoginActivity extends AppCompatActivity {
    EditText email;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button goBackButton = findViewById(R.id.goBackButton);

        goBackButton.setOnClickListener(v -> finish());

        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> Login());

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }

    private void Login(){
        String myEmail = email.getText().toString();
        String myPassword = password.getText().toString();

        if(TextUtils.isEmpty(myEmail)){
            Toast.makeText(this, "Email is required", Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(myPassword)){
            Toast.makeText(this, "Password is required", Toast.LENGTH_LONG).show();
            return;
        }

        FirebaseAuth.getInstance().signInWithEmailAndPassword(myEmail, myPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(LoginActivity.this, "Email or password is incorrect!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}