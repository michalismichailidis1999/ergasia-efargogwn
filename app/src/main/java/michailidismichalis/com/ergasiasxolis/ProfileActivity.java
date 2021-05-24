package michailidismichalis.com.ergasiasxolis;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private AlertDialog dialog;
    private Button SaveButton;

    EditText email;
    EditText weight;
    EditText height;
    EditText age;

    boolean isActual;
    EditText password;
    EditText confirmPassword;
   String myweight;
   String myheight;
   String myemail;
   String myage;

   String before;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        findViewById(R.id.CentralLayout).setVisibility(RelativeLayout.INVISIBLE);

        weight = findViewById(R.id.weight_field);

        height = findViewById(R.id.height_field);
        age = findViewById(R.id.age_field);
        email = findViewById(R.id.email_field);

        isActual = true;

        TextWatcher tw = new TextWatcher() {

            public void afterTextChanged(Editable s) {
               if(!before.equals("")) isActual =false;

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                before = s.toString();

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }
        };


        Button BackButton = findViewById(R.id.back_button);
        BackButton.setOnClickListener(v -> goToMenu());


        SaveButton = findViewById(R.id.save_button);

        SaveButton.setOnClickListener(

                v -> Save()

        );

        LoadData();
        weight.addTextChangedListener(tw);
        height.addTextChangedListener(tw);
        age.addTextChangedListener(tw);
        email.addTextChangedListener(tw);
    }



    public void LoadData()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference usersDB = FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getUid());

        usersDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot snapshot) {

               if(snapshot.hasChildren()){
                    for (DataSnapshot childSnapShot : snapshot.getChildren()) {
                        if(childSnapShot.getKey().equals("age"))  myage = ((childSnapShot.getValue().toString()));

                        if(childSnapShot.getKey().equals("email")) myemail = (childSnapShot.getValue().toString());
                        if(childSnapShot.getKey().equals("height")) myheight =  ((childSnapShot.getValue().toString()));
                        if(childSnapShot.getKey().equals("weight")) myweight = ((childSnapShot.getValue()).toString());

                    }


               }

                weight.setText(myweight);
                height.setText(myheight);
                age.setText(myage);
                email.setText(myemail);

                findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                findViewById(R.id.CentralLayout).setVisibility(RelativeLayout.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void Save(){

        DatabaseReference usersDB = FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getUid());

        if(weight.getText().toString()!=null) usersDB.child("weight").setValue(weight.getText().toString());
        if(height.getText().toString()!=null) usersDB.child("height").setValue(height.getText().toString());
        if(age.getText().toString()!=null) usersDB.child("age").setValue(age.getText().toString());
        if(email.getText().toString()!=null) usersDB.child("email").setValue(email.getText().toString());
        isActual = true;
        createConfirmDialog("Notification","Data updated successfully !",
                "OK", null);
        dialog.show();

    }
    private void createConfirmDialog(String title, String msg, String positiveText,
                                     String negativeText){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        //You have unsaved changes. Are you sure that you want to quit
        //Quit Cancel
        builder.setMessage(msg);
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (positiveText.equalsIgnoreCase("yes"))
                            finish();
                    }
                });
        builder.setNegativeButton(negativeText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        this.dialog = builder.create();
    }

    private void goToMenu() {
        Log.d("fd","3"+(isActual));
        if (isActual !=true ) //Check changes
        {
            createConfirmDialog("Warning","You have unsaved changes !" +
                        '\n'+ "Are you sure that you want to quit ?",
                //would you like to save them before exit ?
                "Yes", "No");
            dialog.show();
        }

        else
            finish();

    }

}