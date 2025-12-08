package com.example.coffeshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StudentAdmission extends AppCompatActivity {
    EditText firstName,lastName,school,department,program,phone,age;
    String getFirstName,getLastName,getSchool,getDepartment,getProgram,getPhone,getAge;
     Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student_admission);
        //Link edit text views
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        school = findViewById(R.id.school);
        department = findViewById(R.id.department);
        program = findViewById(R.id.program);
        register = findViewById(R.id.bv_register);
        phone = findViewById(R.id.phone);
        age = findViewById(R.id.age);
        //set onclick listener

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFirstName = firstName.getText().toString().trim();
                getLastName = lastName.getText().toString().trim();
                getSchool = school.getText().toString().trim();
                getDepartment = department.getText().toString().trim();
                getProgram = program.getText().toString().trim();
                getPhone = phone.getText().toString().trim();
                 getAge = age.getText().toString().trim();






                //Notify the user that all field are to be filled!
                if(getFirstName.isEmpty() || getLastName.isEmpty() || getSchool.isEmpty() || getDepartment.isEmpty() || getProgram.isEmpty() || getPhone.isEmpty()   ){
                    Toast toast = Toast.makeText(getApplicationContext(),"All Fields must be filled!",Toast.LENGTH_SHORT);

                    toast.show();
                      return;
                }

               int userAge = Integer.parseInt(getAge);


               Intent registerStudent = new Intent(StudentAdmission.this,ConfirmDetails.class);
                registerStudent.putExtra("FirstName",getFirstName);
                registerStudent.putExtra("LastName",getLastName);
                registerStudent.putExtra("School",getSchool);
                registerStudent.putExtra("Department",getDepartment);
                registerStudent.putExtra("Program",getProgram);
                registerStudent.putExtra("Phone",getPhone);
                registerStudent.putExtra("Age",userAge);


               startActivity(registerStudent);

            }
        });


    }
}