package com.example.smartbrta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class Admin_updateinfo extends AppCompatActivity {

    Button button;
    EditText reg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_updateinfo);


        button = findViewById(R.id.auiisearch);


        findViewById(R.id.auiisearch).setOnClickListener(new View.OnClickListener() {

            LinearLayout linearLayout, linearLayout2;

            @Override
            public void onClick(View view) {
                linearLayout = findViewById(R.id.auiivisible);
                linearLayout2 = findViewById(R.id.auiivisible2);


                linearLayout.setVisibility(View.VISIBLE);
               linearLayout2.setVisibility(View.GONE);



            }
        });



    }
}
