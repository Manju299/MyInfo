package com.example.myinfo;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.database.*;
import org.jetbrains.annotations.NotNull;

import java.net.NoRouteToHostException;

public class Home extends AppCompatActivity {
    private EditText fname,lname,fathername,schoolname,cname;
    private ImageView pic,picAdhar,picPan;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    UserData userData;
    private TextView onSubmit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        fathername = findViewById(R.id.fathername);
        schoolname = findViewById(R.id.schoolname);
        cname = findViewById(R.id.cname);
        pic = findViewById(R.id.pic);
        picAdhar = findViewById(R.id.picAdhar);
        picPan = findViewById(R.id.picPan);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("UserInfo");
        userData = new UserData();
        onSubmit = findViewById(R.id.onSubmit);

        onSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName,lastName,fatherName,schoolName,cName;
                firstName = fname.getText().toString();
                lastName = lname.getText().toString();
                fatherName = fathername.getText().toString();
                schoolName = schoolname.getText().toString();
                cName = cname.getText().toString();
                if(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(fatherName) &&
                        TextUtils.isEmpty(schoolName) && TextUtils.isEmpty(cName)){
                    Toast.makeText(getApplicationContext(),"Please enter all data",Toast.LENGTH_LONG).show();
                }
                else {
                    uploadDetails(firstName,lastName,fatherName,schoolName,cName);
                }
            }
        });



    }
    public void uploadDetails(String firstName,String lastName,String fatherName,String schoolName,String cName){
        userData.setFirstName(firstName);
        userData.setLastName(lastName);
        userData.setFatherName(fatherName);
        userData.setSchoolName(schoolName);
        userData.setCollegeName(cName);

//        DatabaseReference dataRef = databaseReference.push();
//        dataRef.setValue(userData, new DatabaseReference.CompletionListener() {
//            @Override
//            public void onComplete(@Nullable @org.jetbrains.annotations.Nullable DatabaseError error, @NonNull @NotNull DatabaseReference ref) {
//                if (error !=null){
//                    Toast.makeText(Home.this,"failed" ,Toast.LENGTH_LONG).show();
//                }
//                else {
//                    Toast.makeText(Home.this,"Successful",Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(Home.this,DetailsPage.class);
//                    startActivity(intent);
//                }
//            }
//        });
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                databaseReference.setValue(userData);
                Toast.makeText(Home.this,"Data added successfully",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Home.this,DetailsPage.class);
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(Home.this,"Failed to Add Data" ,Toast.LENGTH_LONG).show();

            }
        });




    }
}