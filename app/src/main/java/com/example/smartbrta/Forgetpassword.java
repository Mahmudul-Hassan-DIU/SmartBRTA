package com.example.smartbrta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Forgetpassword extends AppCompatActivity{

    Button button;
    TextView tv;
    EditText emailet, mobileet;

    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);


        button = findViewById(R.id.rpretrieve);
        tv = findViewById(R.id.fshowpass);

        emailet = findViewById(R.id.femail);
        mobileet = findViewById(R.id.fmobile);


        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Owners");


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e = emailet.getText().toString();
                String m = mobileet.getText().toString();


                if(!TextUtils.isEmpty(e) && !TextUtils.isEmpty(m)){

                    uid=null;
                    Query myTopPostsQuery = databaseReference.orderByChild("email").equalTo(emailet.getText().toString());
                    myTopPostsQuery.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()){
                                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                                    uid = dataSnapshot1.getRef().getKey();
                                    String password1 = dataSnapshot1.child("password").getValue().toString();

                                    tv.setText("Your Password is: "+password1);
                                }

                            }
                            else {
                                Toast.makeText(Forgetpassword.this, "This Email or Mobile Didin't Matched ! ", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });


                }

                else {
                    emailet.setError("Empty not accepted!");
                    mobileet.setError("Empty not accepted!");
                }




            }
        });




    }
}
