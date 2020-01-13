package com.example.smartbrta;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Admin_cancelreg extends AppCompatActivity {
    Button s,d;
    EditText r;
    TextView n,a;
    private String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_cancelreg);

        final LinearLayout linearLayout;

        s = findViewById(R.id.acrsearch);
        d = findViewById(R.id.acrdelete);
        r = findViewById(R.id.acrreg);
        n = findViewById(R.id.acrname);
        a = findViewById(R.id.acraddress);



        linearLayout = findViewById(R.id.acrvisible);




        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Owners");

        s.setOnClickListener(new View.OnClickListener() {
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
                            Toast.makeText(Admin_cancelreg.this, "No User Found", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });

        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //User Delete
                if(uid!=null){
                    databaseReference.child(uid).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(Admin_cancelreg.this, "Account Deleted", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
