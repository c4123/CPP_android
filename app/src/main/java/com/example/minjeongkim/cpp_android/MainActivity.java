package com.example.minjeongkim.cpp_android;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.view.View.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    Boolean lgnFlag = false;
    Boolean root = false;
    private EditText editTextEmail;
    private EditText editTextPassword ;
    private Button btnLogin;
    private Button btnConnect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        editTextEmail = (EditText) findViewById(R.id.emailInput);
        editTextPassword = (EditText) findViewById(R.id.passwordInput);
        btnLogin = (Button) findViewById(R.id.lgnBtn);
        btnConnect=(Button) findViewById(R.id.connectBtn);
        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                login(editTextEmail.getText().toString(),editTextPassword.getText().toString());
            }
        });
        btnConnect.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                connect();
            }
        });

    }
    private void activityChange(boolean lgnFlag,boolean root){
        if(lgnFlag) {
            if(!root) {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
            else{ // root 계정 로그인 intent/ activity 추가해야함. 

            }
        }
        else
        {
            System.out.println("can not change activity");
        }
    }
        private void login(String email,String password){
            // [START sign_in_with_email]

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Toast.makeText(MainActivity.this, "login success", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                if(user.getEmail().toString()=="root@gmail.com")
                                    root=true;
                                lgnFlag=true;
                                activityChange(lgnFlag,root);
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(MainActivity.this, "login failed.",
                                        Toast.LENGTH_SHORT).show();
                                lgnFlag=false;
                            }
                        }
                    });

        }
        private void connect(){
            Intent intent = new Intent(this, BluetoothActivity.class);
            startActivity(intent);
            finish();
        }
}

