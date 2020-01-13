package com.example.smartbrta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signin extends AppCompatActivity implements View.OnClickListener {


    Button button;
    TextView tv,un,pass,signup;
    EditText editText1,editText2;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        button = findViewById(R.id.login);
        signup = findViewById(R.id.signup);
        tv = findViewById(R.id.tv1);
        un = findViewById(R.id.et1);
        pass = findViewById(R.id.et2);

        editText1 = findViewById(R.id.et1);
        editText2 = findViewById(R.id.et2);
        progressBar = findViewById(R.id.progress);
        button.setOnClickListener(this);
        signup.setOnClickListener(this);

        tv.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {

        try {

            if (v.getId() == (R.id.login)) {
               String username = editText1.getText().toString();
                String password = editText2.getText().toString();
               if(username.equals("admin@gmail.com") && password.equals("admin")){
                      Intent I = new Intent(getApplicationContext(),Admin.class);
                startActivity(I);
               }
               else if(username.equals("police@gmail.com") && password.equals("police")){
                   Intent I = new Intent(getApplicationContext(),Police.class);
                   startActivity(I);
               }
               else {
                   v.setEnabled(false);
                   progressBar.setVisibility(View.VISIBLE);
                   FirebaseAuth.getInstance().signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if(task.isSuccessful()){
                               v.setEnabled(true);
                               progressBar.setVisibility(View.GONE);
                               Intent I = new Intent(getApplicationContext(),User.class);
                               startActivity(I);
                               finish();
                           }
                           else if(!task.isSuccessful()){
                               progressBar.setVisibility(View.GONE);
                               Toast.makeText(Signin.this, "Email or Password didn't match!", Toast.LENGTH_SHORT).show();
                               v.setEnabled(true);
                           }
                           else
                           {
                               progressBar.setVisibility(View.GONE);
                               v.setEnabled(true);
                           }
                       }
                   });
               }
            }

            else if (v.getId() == (R.id.signup)) {
                Intent I = new Intent(getApplicationContext(),Signup.class);
                startActivity(I);
            }

            else if (v.getId() == (R.id.tv1)) {

                Intent I = new Intent(getApplicationContext(),Forgetpassword.class);
                startActivity(I);
            }
            else {
                Toast.makeText(this, "Username or Password error", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            Toast.makeText(this, "Username or Password error", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()==null){
        }
        else {
            Intent I = new Intent(getApplicationContext(),User.class);
            startActivity(I);
            finish();
        }
    }
}
