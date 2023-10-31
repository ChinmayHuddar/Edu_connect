package com.example.edu_connect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    Button signupbutton, loginbtn;
    TextInputLayout email_var,username_var, password_var;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        signupbutton = findViewById(R.id.signup);
        loginbtn = findViewById(R.id.loginbutton);
        //username_var = findViewById(R.id.username_text_field_design);
        password_var = findViewById(R.id.password_input_field);
        email_var = findViewById(R.id.email_field);

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser()!= null)
        {
            finish();
            return;
        }


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                authenticateUser();
            }
        });

        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), signup.class);
                startActivity(intent);
            }
        });
    }
    public void authenticateUser()
    {
        String email_ = email_var.getEditText().getText().toString();
        String password_ = password_var.getEditText().getText().toString();

        if(email_.isEmpty() || password_.isEmpty()){
            Toast.makeText(this, "Pls fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email_, password_)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            showMainActivity();

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(login.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void showMainActivity()
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

}