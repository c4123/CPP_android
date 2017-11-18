package com.example.minjeongkim.cpp_android;

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

    private EditText editTextEmail;
    private EditText editTextPassword ;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        editTextEmail = (EditText) findViewById(R.id.emailInput);
        editTextPassword = (EditText) findViewById(R.id.passwordInput);
        btnLogin = (Button) findViewById(R.id.lgnBtn);
        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                join(editTextEmail.getText().toString(),editTextPassword.getText().toString());
            }
        });

    }

        private void join(String email,String password){
            // [START sign_in_with_email]
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Toast.makeText(MainActivity.this, "login success", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();

                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(MainActivity.this, "login failed.",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }
}

