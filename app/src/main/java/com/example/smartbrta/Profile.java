package com.example.smartbrta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularimageview.CircularImageView;

import de.hdodenhof.circleimageview.CircleImageView;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Profile extends AppCompatActivity {

    DatabaseReference databaseReference;
    ValueEventListener listener;
    //public  CircularImageView imageTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Owners");
        listener = databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String name = dataSnapshot.child("name").getValue().toString();
                    String address = dataSnapshot.child("address").getValue().toString();
                    String fine = dataSnapshot.child("amount").getValue().toString();
                    String date = dataSnapshot.child("within").getValue().toString();
                    String vehicle = dataSnapshot.child("vehicle").getValue().toString();
                    String reg = dataSnapshot.child("reg").getValue().toString();
                    String gender = dataSnapshot.child("gender").getValue().toString();
                    String district = dataSnapshot.child("district").getValue().toString();
                    String mobile = dataSnapshot.child("mobile").getValue().toString();
                    String emr = dataSnapshot.child("emergency").getValue().toString();
                    String bdate = dataSnapshot.child("bdate").getValue().toString();

                    TextView nameTv = (TextView)findViewById(R.id.uname);
                    TextView addressTv = (TextView)findViewById(R.id.uaddress);
                    TextView fineTv = (TextView)findViewById(R.id.ufine);
                    TextView dateTv = (TextView)findViewById(R.id.udate);
                    TextView vehicleTv = (TextView)findViewById(R.id.uvehicle);
                    TextView regTv = (TextView)findViewById(R.id.ureg);
                    TextView genderTv = (TextView)findViewById(R.id.ugender);
                    TextView districtTv = (TextView)findViewById(R.id.udistrict);
                    TextView mobileTv = (TextView)findViewById(R.id.umobile);
                    TextView emrTv = (TextView)findViewById(R.id.uemr);
                    TextView bdateTv = (TextView)findViewById(R.id.ubdate);
                    CircularImageView imageTv = findViewById(R.id.uprofile);

                    nameTv.setText(name);
                    addressTv.setText(address);
                    fineTv.setText(fine+" Tk");
                    dateTv.setText(date);
                    vehicleTv.setText(vehicle);
                    regTv.setText(reg);
                    genderTv.setText(gender);
                    districtTv.setText(district);
                    mobileTv.setText(mobile);
                    emrTv.setText(emr);
                    bdateTv.setText(bdate);


                    if (genderTv.getText().toString().equals("Male"))
                    {
                        imageTv.setImageResource(R.drawable.boy);
                    }
                    else{
                        imageTv.setImageResource(R.drawable.girl);
                    }
                }



            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseReference.removeEventListener(listener);
    }
}
