package com.example.myinfo;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.database.*;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DetailsPage extends AppCompatActivity {
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    public TextView fName, lName, fatherName, schoolName, cName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_page);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("UserInfo");
        fName = findViewById(R.id.fname);
        lName = findViewById(R.id.lname);
        fatherName = findViewById(R.id.fathername);
        schoolName = findViewById(R.id.schoolname);
        cName = findViewById(R.id.cname);

        getData();


    }

    private void getData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                if(snapshot != null){
                    HashMap<String,Object> map = (HashMap<String,Object>) snapshot.getValue();
                    Object name = map.get("firstName");
                    Object lname = map.get("lastName");
                    Object fname = map.get("fatherName");
                    Object sname = map.get("schoolName");
                    Object cname = map.get("collegeName");
                    fName.setText(""+name);
                    lName.setText(""+lname);
                    fatherName.setText(""+fname);
                    schoolName.setText(""+sname);
                    cName.setText(""+cname);
                }
            }

//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//            }
//        });
//        databaseReference.child("UserInfo").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//
//
//                    if(snapshot.exists()) {
//                        Map<String, String > map = (HashMap<String,String>) snapshot.getValue();
//                        String name = map.get("firstName");
//                        String lname = map.get("lastName");
//                        fName.setText(name);
//                        lName.setText(lname);
//                    }

//                String fname = snapshot.getValue(String.class);
//                fName.setText(fname);
//                String lname = snapshot.getValue(String.class);
//                lName.setText(lname);
//                String fathername = snapshot.getValue(String.class);
//                fatherName.setText(fathername);
//                String schoolname = snapshot.getValue(String.class);
//                schoolName.setText(schoolname);
//                String cname = snapshot.getValue(String.class);
//                cName.setText(cname);


            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Failed to Load",Toast.LENGTH_SHORT).show();

            }
        });
    }

}