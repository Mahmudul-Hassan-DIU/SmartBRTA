package com.example.smartbrta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Admin extends AppCompatActivity implements View.OnClickListener {


    LinearLayout l1, l2, l3, l5, l6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        l1 = findViewById(R.id.aui);
        l2 = findViewById(R.id.afc);
        l3 = findViewById(R.id.aupi);
        //l4 = findViewById(R.id.ainbox);
        l5 = findViewById(R.id.acr);
        l6 = findViewById(R.id.alo);

        l1.setOnClickListener(this);
        l2.setOnClickListener(this);
        l3.setOnClickListener(this);
       /// l4.setOnClickListener(this);
        l5.setOnClickListener(this);
        l6.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {

        try {

            if(v.getId() == R.id.aui){

                Intent I = new Intent(getApplicationContext(),HomeActivity.class);
               startActivity(I);
            }
            else if(v.getId() == R.id.afc){

                Intent I = new Intent(getApplicationContext(),HomeActivity_fc.class);
                startActivity(I);
            }
            else if(v.getId() == R.id.aupi){
                Intent I = new Intent(getApplicationContext(),Admin_updateinfo.class);
                startActivity(I);
            }
            else if(v.getId() == R.id.acr){

                Intent I = new Intent(getApplicationContext(),Admin_cancelreg.class);
                startActivity(I);
            }
            else if(v.getId() == R.id.alo){

                Intent I = new Intent(getApplicationContext(),Signin.class);
                startActivity(I);
            }
        }catch (Exception e){

        }



    }
}
