package com.firepheonix.eventmanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpAcitivity extends AppCompatActivity {

   EditText name, fatherName, courseName, scholarNum, email, mobileNum, password, confirmPassword;
   Button signUpButton;

   FirebaseAuth firebaseAuth;
   DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_acitivity);

        name            = (EditText) findViewById(R.id.name);
        fatherName      = (EditText) findViewById(R.id.fatherName);
        courseName      = (EditText) findViewById(R.id.courseName);
        scholarNum      = (EditText) findViewById(R.id.scholarNum);
        email           = (EditText) findViewById(R.id.user_mail);
        mobileNum       = (EditText) findViewById(R.id.mobileNum);
        password        = (EditText) findViewById(R.id.password);
        confirmPassword = (EditText) findViewById(R.id.confirmPassword);
        signUpButton    = (Button) findViewById(R.id.signUpButton);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Student");

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view3) {
                final String full_Name        = name.getText().toString().trim();
                final String father_Name      = fatherName.getText().toString().trim();
                final String course_Name      = courseName.getText().toString().trim();
                final String scholar_Num     = scholarNum.getText().toString().trim();
                final String e_mail           = email.getText().toString().trim();
                final String mobile_Num       = mobileNum.getText().toString().trim();
                final String pass_word        = password.getText().toString().trim();
                String confirm_Password = confirmPassword.getText().toString().trim();



                if(TextUtils.isEmpty(full_Name)){
                    Toast.makeText(SignUpAcitivity.this, "Please Enter Your Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(father_Name)){
                    Toast.makeText(SignUpAcitivity.this, "Please Enter Your Father's Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(course_Name)){
                    Toast.makeText(SignUpAcitivity.this, "Please Enter Your Course Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(scholar_Num)){
                    Toast.makeText(SignUpAcitivity.this, "Please Enter Your Scholar Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(e_mail)){
                    Toast.makeText(SignUpAcitivity.this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(mobile_Num)){
                    Toast.makeText(SignUpAcitivity.this, "Please Enter Your Mobile Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(pass_word)){
                    Toast.makeText(SignUpAcitivity.this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(confirm_Password)){
                    Toast.makeText(SignUpAcitivity.this, "Please Re-Enter Your Password ", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Parameters For Password

                if(pass_word.length()<8){
                    Toast.makeText(SignUpAcitivity.this, "Password At Least Contain 8 Letters", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(pass_word.equals(confirm_Password)){

                    firebaseAuth.createUserWithEmailAndPassword(e_mail, pass_word).addOnCompleteListener(SignUpAcitivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        Student information = new Student(

                                                full_Name,
                                                father_Name,
                                                course_Name,
                                                scholar_Num,
                                                e_mail,
                                                mobile_Num,
                                                pass_word

                                        );

                                        FirebaseDatabase.getInstance().getReference("Student")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                Toast.makeText(SignUpAcitivity.this, "Successful!", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));

                                            }
                                        });

                                    } else {

                                    }

                                    // ...
                                }
                            });


                }




            }
        });

    }
}
