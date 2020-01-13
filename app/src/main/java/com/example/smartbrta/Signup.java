package com.example.smartbrta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Signup extends AppCompatActivity {


    Spinner spinner1, spinner2, spinner3;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button bsignup;
    TextView tvsignin;
    EditText editTextreg, editTextname, editTextemail, editTextmobile, editTextemr, editTextadress, editTextbdate, editTextpass, editTextconfirm;

    DatabaseReference databaseOwner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        databaseOwner = FirebaseDatabase.getInstance().getReference("Owners");


        spinner1 = findViewById(R.id.suvehicle);
        spinner2 = findViewById(R.id.sudistrict);
        spinner3 = findViewById(R.id.sugender);

        bsignup = findViewById(R.id.susignup);
        tvsignin = findViewById(R.id.susignin);

        editTextreg = findViewById(R.id.suregnum);
        editTextname = findViewById(R.id.suname);
        editTextemail = findViewById(R.id.suemail);
        editTextmobile = findViewById(R.id.sumobile);
        editTextmobile = findViewById(R.id.sumobile);
        editTextemr = findViewById(R.id.suemsrgency);
        editTextadress = findViewById(R.id.suaddress);
        editTextbdate = findViewById(R.id.subirth);
        editTextpass = findViewById(R.id.supass);
        editTextconfirm = findViewById(R.id.suconfirm);


        bsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addOwner();

            }
        });

        tvsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(getApplicationContext(),Signin.class);
                startActivity(I);
            }
        });

    }
    private void addOwner() {
        final String address = editTextadress.getText().toString().trim();
        final String amount="0";
        String confirm = editTextconfirm.getText().toString().trim();
        final String bdate = editTextbdate.getText().toString().trim();
        final String district = spinner2.getSelectedItem().toString();
        final String email = editTextemail.getText().toString().trim();
        final String emergency = editTextemr.getText().toString().trim();
        final String fault = "Null";
        final String gender = spinner3.getSelectedItem().toString();
        final String mobile = editTextmobile.getText().toString().trim();
        final String name = editTextname.getText().toString().trim();
        final String paid = "0";
        final String password = editTextpass.getText().toString().trim();
        final String reg = editTextreg.getText().toString().trim();
        final String status="Null";
        final String vehicle = spinner1.getSelectedItem().toString();
        final String within = "N/A";

       if(!password.toLowerCase().equals(confirm.toLowerCase())){
           editTextconfirm.setError("Password didn't match!");

       }
           else if (!TextUtils.isEmpty(reg) && !TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(mobile) && !TextUtils.isEmpty(emergency) && !TextUtils.isEmpty(bdate) && !TextUtils.isEmpty(password)) {

           FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
                     if(task.isSuccessful()){

                         String id = FirebaseAuth.getInstance().getUid();

                         final  Owner owner = new Owner(id, address, amount, bdate, district, email, emergency, fault, gender, mobile, name, paid, password, reg, status, vehicle, within);
                         FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                         String Id = user.getUid();
                         databaseOwner.child(Id).setValue(owner);
                         databaseOwner.child(id).setValue(owner);
                         Toast.makeText(Signup.this, "Registration Successful !", Toast.LENGTH_SHORT).show();
                         Intent I = new Intent(getApplicationContext(),User.class);
                         startActivity(I);
                     }
                 }
             });
        }
        else {
            editTextreg.setError("Reg num required");
            editTextname.setError("Name required");
            editTextemail.setError("Email required");
            editTextmobile.setError("Mobile num required");
            editTextemr.setError("Emergency num required");
            editTextadress.setError("Address required");
            editTextbdate.setError("Birth date required");
            editTextpass.setError("Password required");
            editTextconfirm.setError("Confirm password required");
        }
    }


    //final Owner owner = new Owner(address, amount, bdate, district, email, emergency, fault, gender, FirebaseAuth.getInstance().getUid(), mobile, name, paid, password, reg, status, vehicle, within);

}


