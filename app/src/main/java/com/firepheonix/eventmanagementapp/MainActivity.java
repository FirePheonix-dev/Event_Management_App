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

public class MainActivity extends AppCompatActivity {

    private Button LogIn_Button; // log in button
    private Button SignIn_Button; // sign up button
    EditText user_mail,user_password;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user_mail = (EditText) findViewById(R.id.user_mail);
        user_password= (EditText) findViewById(R.id.user_password);


        LogIn_Button =(Button) findViewById(R.id.LogIn_Button);
        firebaseAuth = FirebaseAuth.getInstance();
        LogIn_Button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String user_Email =  user_mail.getText().toString().trim();
                String user_Password = user_password.getText().toString().trim();

                if(TextUtils.isEmpty(user_Email)) {
                    Toast.makeText(MainActivity.this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(user_Password)){
                    Toast.makeText(MainActivity.this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(user_Password.length()<8){
                    Toast.makeText(MainActivity.this, "Please enter At Least 8 Letters ", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(user_Email, user_Password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(getApplicationContext(),DashboardActivitty.class));

                                } else {

                                    Toast.makeText(MainActivity.this, "LogIn Failed, Please enter Valid Email or Password", Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });

            }
        });


        SignIn_Button = (Button) findViewById(R.id.SignIn_Button);
        SignIn_Button.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view2) {
                openSignUpActivity();

            }
        });
    }



    private void openSignUpActivity() {
        Intent intent = new Intent(this,SignUpAcitivity.class );
        startActivity(intent);

    }
}
