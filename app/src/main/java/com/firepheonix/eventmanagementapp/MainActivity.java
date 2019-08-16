package com.firepheonix.eventmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button LogIn_Button; // log in button
    private Button SignIn_Button; // sign up button


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogIn_Button =(Button) findViewById(R.id.LogIn_Button);
        LogIn_Button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                openLogInActivityActivity();

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

    private void openLogInActivityActivity() {
        Intent intent = new Intent(this,LoginActivity.class );
        startActivity(intent);

    }

    private void openSignUpActivity() {
        Intent intent2 = new Intent(this,SignUpAcitivity.class );
        startActivity(intent2);

    }
}
