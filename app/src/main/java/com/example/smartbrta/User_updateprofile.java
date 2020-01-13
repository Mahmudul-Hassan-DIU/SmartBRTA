package com.example.smartbrta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class User_updateprofile extends AppCompatActivity {

    EditText uname,uaddress,umobile,uemr;
    Button update;

    DatabaseReference databaseReference;
    public OnCompleteListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_updateprofile);

        uname = findViewById(R.id.upname);
        uaddress = findViewById(R.id.upaddress);
        umobile = findViewById(R.id.upmobile);
        uemr = findViewById(R.id.upemrgency);

        update = findViewById(R.id.upupdate);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = uname.getText().toString();
                String address = uaddress.getText().toString();
                String mobile = umobile.getText().toString();
                String emergency = uemr.getText().toString();

                if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(address) && !TextUtils.isEmpty(mobile) && !TextUtils.isEmpty(emergency)){

                    Map<String,Object> map = new HashMap<>();

                    map.put("name",name);
                    map.put("address",address);
                    map.put("mobile",mobile);
                    map.put("emergency",emergency);

                    databaseReference = FirebaseDatabase.getInstance().getReference().child("Owners");
                    databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            Toast.makeText(User_updateprofile.this, "Profile Updated !", Toast.LENGTH_SHORT).show();
                            Intent I = new Intent(getApplicationContext(), User.class);
                            startActivity(I);
                        }
                    });
                }

                else{
                    uname.setError("Empty not accepted!");
                    uaddress.setError("Empty not accepted!");
                    umobile.setError("Empty not accepted!");
                    uemr.setError("Empty not accepted!");
                }
            }
        });
    }

}
