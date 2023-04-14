package com.example.myinfo;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
    public EditText email, pass;
    public Button submit;
    private FirebaseAuth mAuth;

    private TextView newRegister;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.emailtext);
        pass = findViewById(R.id.passtext);
        submit = findViewById(R.id.button);
        newRegister = findViewById(R.id.newRegister);
        progressBar = findViewById(R.id.progressBar);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logInOnClick();
            }
        });

        newRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewUser();
            }
        });


    }

    public void logInOnClick() {
        progressBar.setVisibility(View.VISIBLE);

        String emailText,passText;
        emailText = email.getText().toString();
        passText =pass.getText().toString();
        if(TextUtils.isEmpty(emailText)){
            Toast.makeText(getApplicationContext(),"Enter valid password",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(passText)){
            Toast.makeText(getApplicationContext(),"Enter valid password",Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth
                .signInWithEmailAndPassword(emailText,passText)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Login successful" ,Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            Intent intent = new Intent(MainActivity.this,Home.class);
                            startActivity(intent);

                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Enter valid Username Email and Password",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
//                if ((email.getText().toString().equals("manjunath@gmail.com")) && (pass.getText().toString().equals("1234"))){
//                    Toast.makeText(getApplicationContext(),"hello " + email.getText().toString(),Toast.LENGTH_LONG).show();
//                }
//                else {
//                    Toast.makeText(getApplicationContext(),"Better luck next time",Toast.LENGTH_LONG).show();
//                }

    }
    public void addNewUser(){
        Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
        startActivity(intent);

    }



}
