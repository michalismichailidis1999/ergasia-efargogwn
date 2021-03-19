package michailidismichalis.com.ergasiasxolis.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import michailidismichalis.com.ergasiasxolis.R;

public class RegisterUserInfoActivity extends AppCompatActivity {
    private EditText weight;
    private EditText height;
    private EditText age;
    private String mySex = "";
    private RadioButton female;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user_info);

        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        age = findViewById(R.id.age);

        Button goBackButton = findViewById(R.id.goBackButton);

        goBackButton.setOnClickListener(v -> {
            finish();
        });

        Button nextButton = findViewById(R.id.nextButton);

        nextButton.setOnClickListener(v -> ContinueRegistration());

        RadioGroup sexes = findViewById(R.id.sexes);

        sexes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.male:
                        mySex = "male";
                        break;
                    case R.id.female:
                        mySex = "female";
                    default:
                        break;
                }
            }
        });

        female = findViewById(R.id.female);
    }

    private void ContinueRegistration(){
        String myWeight = weight.getText().toString();
        String myHeight = height.getText().toString();
        String myAge = age.getText().toString();

        if(TextUtils.isEmpty(myWeight)){
            Toast.makeText(this, "Weight is required!", Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(myHeight)){
            Toast.makeText(this, "Height is required!", Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(myAge)){
            Toast.makeText(this, "Age is required!", Toast.LENGTH_LONG).show();
            return;
        }

        if(mySex.equals("")){
            Toast.makeText(this, "Sex is required!", Toast.LENGTH_LONG).show();
            return;
        }

        Intent intent = new Intent(this, RegisterUserCredentialsActivity.class);
        intent.putExtra("weight", myWeight);
        intent.putExtra("height", myHeight);
        intent.putExtra("age", myAge);
        intent.putExtra("sex", mySex);
        startActivity(intent);
    }
}