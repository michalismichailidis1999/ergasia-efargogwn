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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import michailidismichalis.com.ergasiasxolis.MenuActivity;
import michailidismichalis.com.ergasiasxolis.R;

public class RegisterUserCredentialsActivity extends AppCompatActivity {
    EditText email;
    EditText password;
    EditText confirmPassword;

    String weight;
    String height;
    String age;
    String sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user_credentials);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);

        Button goBackButton = findViewById(R.id.goBackButton);

        goBackButton.setOnClickListener(v -> {
            finish();
        });

        Button registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(v -> Register());

        weight = getIntent().getStringExtra("weight");
        height = getIntent().getStringExtra("height");
        age = getIntent().getStringExtra("age");
        sex = getIntent().getStringExtra("sex");
    }

    public void createUser(String myEmail, String myPassword){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(myEmail, myPassword)
        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if(user != null){
                        DatabaseReference usersDB = FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid());

                        int myWeight = Integer.parseInt(getIntent().getStringExtra("weight"));
                        int myHeight = Integer.parseInt(getIntent().getStringExtra("weight"));
                        int myAge = Integer.parseInt(getIntent().getStringExtra("weight"));
                        String mySex = getIntent().getStringExtra("sex");

                        UserObject userObj = new UserObject(user.getEmail(), mySex, myWeight, myHeight, myAge);
                        usersDB.setValue(userObj);

                        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(RegisterUserCredentialsActivity.this, "Something went wrong!", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(RegisterUserCredentialsActivity.this, "Email is already taken!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void Register(){
        String myEmail = email.getText().toString();
        String myPassword = password.getText().toString();
        String myConfirmPassword = confirmPassword.getText().toString();

        if(TextUtils.isEmpty(myEmail)){
            Toast.makeText(this, "Email is required!", Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(myPassword)){
            Toast.makeText(this, "Password is required!", Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(myConfirmPassword)){
            Toast.makeText(this, "Please confirm your password!", Toast.LENGTH_LONG).show();
            return;
        }

        if(!TextUtils.isEmpty(myPassword) && !TextUtils.isEmpty(myConfirmPassword) && !myPassword.equals(myConfirmPassword)){
            Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_LONG).show();
            return;
        }

        createUser(myEmail, myPassword);
    }
}