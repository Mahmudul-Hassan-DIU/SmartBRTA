package com.example.smartbrta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class User_contactadmin extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_contactadmin);

        button = findViewById(R.id.cavisiblebtn);


        findViewById(R.id.cavisiblebtn).setOnClickListener(new View.OnClickListener() {

            LinearLayout linearLayout,linearLayout2;

            @Override
            public void onClick(View view) {
                linearLayout = findViewById(R.id.cavisible);
                linearLayout.setVisibility(View.VISIBLE);

                linearLayout2 = findViewById(R.id.cavisible2);
                linearLayout2.setVisibility(View.GONE);

            }
        });
    }
}
