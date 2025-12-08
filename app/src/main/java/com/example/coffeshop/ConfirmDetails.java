package com.example.coffeshop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ConfirmDetails extends AppCompatActivity {

    TextView tvName,tvSchool,tvDepartment,tvProgram,tvPhone,tvAge;
    String firstName,lastName,school,department,program,phone,name;
    Button btnCancel,btnConfirm;
    int getAge;
    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_confirm_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

       tvName = findViewById(R.id.tv_full_name);
       tvSchool = findViewById(R.id.tv_school);
       tvDepartment = findViewById(R.id.tv_department);
       tvProgram = findViewById(R.id.tv_program);
       tvPhone = findViewById(R.id.tv_phone);
       tvAge = findViewById(R.id.tv_age);
       btnCancel = findViewById(R.id.bv_cancel);
       btnConfirm = findViewById(R.id.bv_confirm);



       Intent getInfo = getIntent();

       firstName = getInfo.getStringExtra("FirstName");
       lastName = getInfo.getStringExtra("LastName");
       school = getInfo.getStringExtra("School");
       department = getInfo.getStringExtra("Department");
       program = getInfo.getStringExtra("Program");
       phone = getInfo.getStringExtra("Phone");
        getAge = getInfo.getIntExtra("Age",0);

       name = firstName + " "+lastName;

       tvName.setText(String.format("Name: %s",name));
       tvSchool.setText(String.format("School: %s",school));
       tvDepartment.setText(String.format("Department: %s",department));
       tvProgram.setText(String.format("Program: %s",program));
       tvPhone.setText(String.format("Phone: %s",phone));
       tvAge.setText(String.format("Age: %s",getAge));



     setBtnCancel();

      btnConfirm.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              sendConfirmMail();
          }
      });


    }

    private void sendConfirmMail() {
        String message = "Please find the below student admission details for processing"+  "\n"+ "Name: "+name+"\n"
                          + "School: " + school + "\n"
                           + "Department: "+ department + "\n"
                           + "Program: " + program + "\n"
                           + "Phone: " + phone + "\n"
                            + "Age: " + getAge;

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL,new String[]{"mudaidalewis@gmail.com","cynthiakwamboka576@gmail.com","studentmngs@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,"User Registration Details");
        emailIntent.putExtra(Intent.EXTRA_TEXT,message);

        startActivity(Intent.createChooser(emailIntent,"Choose an email"));

    }

    private void setBtnCancel() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}