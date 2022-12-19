package com.example.loginregister20;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;


    private EditText emailT;
    private EditText passwordT;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();



        emailT = findViewById(R.id.emailText);
        passwordT = findViewById(R.id.passText);
    }

    public void login() {

        emailT = findViewById(R.id.email);
        passwordT = findViewById(R.id.password);

        String email = emailT.getText().toString();
        String password = passwordT.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(MainActivity.this, "login ok", Toast.LENGTH_SHORT).show();

                        } else {

                            Toast.makeText(MainActivity.this, "login failed", Toast.LENGTH_LONG).show();

                        }
                    }
                });

    }

    public void register() {

        emailT = findViewById(R.id.registerEmail);
        passwordT = findViewById(R.id.registerPassword);

        String email = emailT.getText().toString();
        String password = passwordT.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            Toast.makeText(MainActivity.this, "register ok", Toast.LENGTH_SHORT).show();

                        } else {

                            Toast.makeText(MainActivity.this, "register fail", Toast.LENGTH_LONG).show();

                        }
                    }
                });


    }
}