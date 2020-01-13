package com.example.smartbrta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Police_makefine extends AppCompatActivity{

    TextView datetv, n,a;
    EditText r,amount;
    Button search,button;
    Spinner spinner;

    private String uid;

    private DatePickerDialog.OnDateSetListener dateSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_makefine);

        datetv = findViewById(R.id.mkdate);
        n = findViewById(R.id.mkname);
        a = findViewById(R.id.mkaddress);
        r = findViewById(R.id.mkreg);
        amount = findViewById(R.id.mkamount);
        search = findViewById(R.id.mksearch);
        button= findViewById(R.id.mkconfirm);
        spinner = findViewById(R.id.mkfaults);



        datetv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog =new DatePickerDialog(
                        Police_makefine.this,R.style.Theme_AppCompat_DayNight_Dialog_MinWidth,dateSetListener,year,month,day);

                dialog.show();


            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String date = ""+ day + "-" + (month+1) + "-" + year;
                datetv.setText(date);


            }
        };

        final LinearLayout linearLayout;
        linearLayout = findViewById(R.id.mkvisible);

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Owners");


       search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                uid=null;
                Query myTopPostsQuery = databaseReference.orderByChild("reg").equalTo(r.getText().toString());
                myTopPostsQuery.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            linearLayout.setVisibility(View.VISIBLE);
                            for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                                uid = dataSnapshot1.getRef().getKey();
                                String name = dataSnapshot1.child("name").getValue().toString();
                                String address = dataSnapshot1.child("address").getValue().toString();

                                n.setText(name);
                                a.setText(address);
                            }
                        }
                        else {
                            Toast.makeText(Police_makefine.this, "No User Found", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
        });
    }
    });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(uid!=null){
                    String fault = spinner.getSelectedItem().toString();
                    String fine = amount.getText().toString();
                    String date = datetv.getText().toString();

                    if(!TextUtils.isEmpty(fine)){

                            Map<String,Object> map = new HashMap<>();

                            map.put("fault",fault);
                            map.put("amount",fine);
                            map.put("within",date);
                            map.put("status","Due");


                            databaseReference.child(uid).updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(Police_makefine.this, "Owner get Fined!", Toast.LENGTH_SHORT).show();
                                    Intent I = new Intent(getApplicationContext(),Police.class);
                                    startActivity(I);
                                }
                            });
                        }
                    }
                    else{
                        amount.setError("Empty not accepted!");
                        datetv.setError("Empty not accepted!");

                    }
            }
        });

    }



}