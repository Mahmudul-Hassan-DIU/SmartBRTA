package com.example.smartbrta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class User_payfine extends AppCompatActivity {
    LinearLayout b,r;
    EditText tk;
    Button confirm;
    ImageView bk, ro;


    DatabaseReference databaseReference;
    public OnCompleteListener listener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_payfine);

        b = findViewById(R.id.b);
        r = findViewById(R.id.r);
        tk = findViewById(R.id.pfamount);
        confirm = findViewById(R.id.pfconfirm);
        bk = findViewById(R.id.pfvisibleb);
        ro = findViewById(R.id.pfvisibler);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.b){
                    //Toast.makeText(User_payfine.this, "Amount Paid Successfully!", Toast.LENGTH_SHORT).show();
                    tk.setVisibility(View.VISIBLE);
                    bk.setVisibility(View.VISIBLE);
                    confirm.setVisibility(View.VISIBLE);
                }
            }
        });



        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.r){
                    tk.setVisibility(View.VISIBLE);
                    ro.setVisibility(View.VISIBLE);
                    confirm.setVisibility(View.VISIBLE);

                }
            }
        });


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fine = tk.getText().toString();


                if(!TextUtils.isEmpty(fine)){

                    Map<String,Object> map = new HashMap<>();

                    map.put("status","Paid");
                    map.put("paid",fine);

                    databaseReference = FirebaseDatabase.getInstance().getReference().child("Owners");
                    databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            Toast.makeText(User_payfine.this, "Amount Paid Successfully", Toast.LENGTH_SHORT).show();
                            Intent I = new Intent(getApplicationContext(), User.class);
                            startActivity(I);
                        }
                    });
                }

                else{
                    tk.setError("Empty not accepted!");

                }






            }
        });


    }
}
