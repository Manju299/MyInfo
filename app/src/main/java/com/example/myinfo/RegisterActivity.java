package com.example.myinfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import org.jetbrains.annotations.NotNull;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText pass,email;
    private Button submit;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        submit = findViewById(R.id.submit);
        progressBar = findViewById(R.id.progressbar);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerNewUSer();
            }
        });


    }

    private void registerNewUSer(){
        progressBar.setVisibility(View.VISIBLE);
        String emailText,passText;
        emailText = email.getText().toString();
        passText = pass.getText().toString();

        if (emailText.isEmpty()){
            Toast.makeText(getApplicationContext(),"Enter the Email",Toast.LENGTH_LONG).show();
            return;
        }
        if (passText.isEmpty()) {
            Toast.makeText(getApplicationContext(),"Enter your Password",Toast.LENGTH_LONG).show();
            return;

        }

        mAuth
                .createUserWithEmailAndPassword(emailText,passText)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Registration Successful",Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            Intent intent;
                            intent = new Intent(RegisterActivity.this,
                                    MainActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Registration Failed"
                                    + " Please try again",Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);


                        }
                    }
                });


    }

}
