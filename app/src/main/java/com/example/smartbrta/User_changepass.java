package com.example.smartbrta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class User_changepass extends AppCompatActivity {


    EditText pass,confirm;
    Button update;

    DatabaseReference databaseReference;
    public OnCompleteListener listener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_changepass);

        pass = findViewById(R.id.cppass);
        confirm = findViewById(R.id.cpconfirm);

        update = findViewById(R.id.cpchange);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = pass.getText().toString();
                String confirmm = confirm.getText().toString();

                if(!TextUtils.isEmpty(password) && !TextUtils.isEmpty(confirmm)){

                    if (password.toLowerCase().equals(confirmm.toLowerCase())) {

                        Map<String,Object> map = new HashMap<>();

                        map.put("password",password);

                        databaseReference = FirebaseDatabase.getInstance().getReference().child("Owners");
                        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                Toast.makeText(User_changepass.this, "Password Changed!", Toast.LENGTH_SHORT).show();

                                Intent I = new Intent(getApplicationContext(), User.class);
                                startActivity(I);
                            }
                        });
                    }
                    else {
                        confirm.setError("Password didn't matched !");

                    }
                }
                else{
                    pass.setError("Empty not accepted!");
                    confirm.setError("Empty not accepted!");

                }
            }
        });
    }
}