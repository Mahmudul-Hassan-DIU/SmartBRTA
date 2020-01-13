package com.example.smartbrta;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class User extends AppCompatActivity implements View.OnClickListener {
    LinearLayout l1, l2, l3, l4, l5, l6;
    DatabaseReference databaseReference;
    ValueEventListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        l1 = findViewById(R.id.uvp);
        l2 = findViewById(R.id.upf);
        l3 = findViewById(R.id.uup);
        l4 = findViewById(R.id.uca);
        l5 = findViewById(R.id.ucp);
        l6 = findViewById(R.id.ulo);

        l1.setOnClickListener(this);
        l2.setOnClickListener(this);
        l3.setOnClickListener(this);
        l4.setOnClickListener(this);
        l5.setOnClickListener(this);
        l6.setOnClickListener(this);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Owners");
        listener = databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String name = dataSnapshot.child("name").getValue().toString();
                    String reg = dataSnapshot.child("reg").getValue().toString();
                    String fine = dataSnapshot.child("amount").getValue().toString();
                    String date = dataSnapshot.child("within").getValue().toString();
                    TextView nameTv = (TextView)findViewById(R.id.name);
                    TextView reqTv = (TextView)findViewById(R.id.reg);
                    TextView fineTv = (TextView)findViewById(R.id.fine);
                    TextView datetv = (TextView)findViewById(R.id.date);

                    nameTv.setText(name);
                    reqTv.setText("Reg Num: "+reg);
                    fineTv.setText(fine+" Tk");
                    datetv.setText(date);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
//        Map<String,Object> map = new HashMap<>();
//        map.put("name","New Name");
//        map.put("bdate","new date");

        //Update Child Value
//        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//
//            }
//        });


    }

    @Override
    public void onClick(View v) {
        try {
            if(v.getId() == R.id.uvp){
                Intent I = new Intent(getApplicationContext(),Profile.class);
                startActivity(I);
            }
            else if(v.getId() == R.id.upf){
                Intent I = new Intent(getApplicationContext(),User_payfine.class);
                startActivity(I);
            }
            else if(v.getId() == R.id.uup){
                Intent I = new Intent(getApplicationContext(),User_updateprofile.class);
                startActivity(I);
            }
            else if(v.getId() == R.id.uca){
                Intent I = new Intent(getApplicationContext(),User_contactadmin.class);
                startActivity(I);
            }
            else if(v.getId() == R.id.ucp){
                Intent I = new Intent(getApplicationContext(),User_changepass.class);
                startActivity(I);
            }
            else if(v.getId() == R.id.ulo){

                FirebaseAuth.getInstance().signOut();
                Intent I = new Intent(getApplicationContext(),Signin.class);
                startActivity(I);
                finish();
            }
        }catch (Exception e){

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseReference.removeEventListener(listener);
    }
}
