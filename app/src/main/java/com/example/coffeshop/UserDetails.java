package com.example.coffeshop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserDetails extends AppCompatActivity {
    EditText phone;
    TextView tvName,tvSchool,tvDepartment,tvProgram,tvAge;
    String school,department,program,phoneNumber,name;
    int age;
    Button btnRegister,btnSearch;
    FirebaseDatabase db;
    DatabaseReference reference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_details);
        //linker to views
        tvName = findViewById(R.id.tv_full_name);
        tvSchool = findViewById(R.id.tv_school);
        tvDepartment = findViewById(R.id.tv_department);
        tvProgram = findViewById(R.id.tv_program);
        tvAge = findViewById(R.id.tv_age);

        phone = findViewById(R.id.ev_search);
        btnRegister = findViewById(R.id.bv_register);
        btnSearch = findViewById(R.id.bv_search);

        //accessing firebase
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("StudentsRecords");
      //read from database

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSearch.setText("Searching....");
                btnSearch.setEnabled(false);
                phoneNumber = phone.getText().toString().trim();

                if(phoneNumber.isEmpty()){
                    phone.setError("Please enter phone number!.....");
                    btnSearch.setText("Searching");
                    btnSearch.setEnabled(true);
                    return;
                }
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(phoneNumber)) {

                            name = snapshot.child(phoneNumber).child("Name").getValue(String.class);
                            school = snapshot.child(phoneNumber).child("School").getValue(String.class);
                            department = snapshot.child(phoneNumber).child("Department").getValue(String.class);
                            program = snapshot.child(phoneNumber).child("Program").getValue(String.class);
                            Integer age = snapshot.child(phoneNumber).child("Age").getValue(Integer.class);

                            btnSearch.setText("Success 😀✔");
                            btnSearch.setBackgroundColor(
                                    getResources().getColor(android.R.color.holo_green_dark)
                            );
                            btnSearch.setEnabled(true);

                            tvName.setText(name);
                            tvSchool.setText(school);
                            tvDepartment.setText(department);
                            tvProgram.setText(program);
                            tvAge.setText(age != null ? String.valueOf(age) : "N/A");

                        } else {
                            phone.setError("Student does not exist!");
                            btnSearch.setText("Failed 😟");
                            btnSearch.setBackgroundColor(
                                    getResources().getColor(android.R.color.holo_red_dark)
                            );
                            btnSearch.setEnabled(true);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        btnSearch.setText("Error");
                        btnSearch.setEnabled(true);
                    }
                });
            }
        });

        //register intent
        setRegister();

    }

    private void setRegister(){
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(getApplicationContext(),StudentAdmission.class);
                startActivity(register);
            }
        });
    }
}