package com.healthcare.manojkulkarni.firebasefinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.healthcare.R;

public class FinalLogin extends AppCompatActivity {

    ProgressDialog progressDialog;

    Button buttonSignup , login;

    FirebaseAuth firebaseAuth;

    EditText email , pass;

    public static String C_UID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_login);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait..");
        progressDialog.setMessage("Logining.... ");
        progressDialog.setCancelable(false);

        firebaseAuth = FirebaseAuth.getInstance();
        buttonSignup = (Button) findViewById(R.id.buttonSignup);
        login = (Button) findViewById(R.id.login);

        email = (EditText) findViewById(R.id.edit_text_email);
        pass = (EditText)  findViewById(R.id.edit_text_password);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!(email.getText().toString().isEmpty() || pass.getText().toString().isEmpty())){


                    SIGNIN();
                }else {


                    Toast.makeText(FinalLogin.this, "Please Enter Email && PASS correctly", Toast.LENGTH_SHORT).show();
                }

            }
        });

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(FinalLogin.this , FinalRegister.class);
                startActivity(i);
            }
        });
    }


    public  void SIGNIN(){


        progressDialog.show();
        String E = email.getText().toString().trim();
        String P = pass.getText().toString().trim();



        firebaseAuth.signInWithEmailAndPassword(E , P ).addOnCompleteListener(FinalLogin.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {



            }
        }).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                progressDialog.dismiss();
                C_UID = firebaseAuth.getCurrentUser().getUid();
                Intent intent = new Intent(FinalLogin.this , FinalMain.class);
                startActivity(intent);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                progressDialog.dismiss();

                Toast.makeText(FinalLogin.this, "Failed to Login. ", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
