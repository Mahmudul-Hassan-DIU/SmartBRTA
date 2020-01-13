package com.example.smartbrta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class Police extends AppCompatActivity implements View.OnClickListener {

    LinearLayout l1, l2, l3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police);

        l1 = findViewById(R.id.pui);
        l2 = findViewById(R.id.pmf);
        l3 = findViewById(R.id.plo);

        l1.setOnClickListener(this);
        l2.setOnClickListener(this);
        l3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        try {

            if(v.getId() == R.id.pui){
                Intent I = new Intent(getApplicationContext(),HomeActivity_p.class);
                startActivity(I);
            }

            else if(v.getId() == R.id.pmf){
                Intent I = new Intent(getApplicationContext(),Police_makefine.class);
                startActivity(I);
            }

            else if(v.getId() == R.id.plo){
                Intent I = new Intent(getApplicationContext(),Signin.class);
                startActivity(I);
            }
        }catch (Exception e){
        }
    }
}
