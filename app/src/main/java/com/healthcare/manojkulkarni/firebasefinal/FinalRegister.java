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
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.healthcare.ModelClass.UserClass;
import com.healthcare.R;

public class FinalRegister extends AppCompatActivity{


    ProgressDialog progressDialog;

    EditText email , pass , firstname , lastname , contact , address , bloodgrouop;
    Button register;
    String UserID;


    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReferencel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_final_register);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReferencel = FirebaseDatabase.getInstance().getReference("DATA");

        register = (Button) findViewById(R.id.account);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.pass);
        firstname = (EditText) findViewById(R.id.first_name);

        lastname = (EditText) findViewById(R.id.last_name);

        contact = (EditText) findViewById(R.id.contact);

        address = (EditText) findViewById(R.id.address);

        bloodgrouop= (EditText) findViewById(R.id.bloodgroup);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait...");
        progressDialog.setMessage("Creating User and Saving.....");
        progressDialog.setCancelable(false);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!(email.getText().toString().isEmpty() || pass.getText().toString().isEmpty() || firstname.getText().toString().isEmpty()
                || lastname.getText().toString().isEmpty() || contact.getText().toString().isEmpty() || address.getText().toString().isEmpty() || bloodgrouop.getText().toString().isEmpty())){

                    // if not empty field call the function to create user account and also save in database fields
                    CREATEUSER();

                }else {

                    Toast.makeText(FinalRegister.this, "Please Enter Fields.. ", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }


    public   void CREATEUSER(){


        progressDialog.show();
        final String E = email.getText().toString().trim();
        final String P = pass.getText().toString().trim();


        firebaseAuth.createUserWithEmailAndPassword(E , P).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

            }
        }).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                UserID = firebaseAuth.getCurrentUser().getUid();


                UserClass userClass = new UserClass(E , P , UserID ,  firstname.getText().toString() , lastname.getText().toString() , contact.getText().toString() , address.getText().toString() , bloodgrouop.getText().toString() , "Unspecified");


                databaseReferencel.child("USERS_DATA").child(UserID).setValue(userClass, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                        if (databaseReference.equals(databaseError)){

                            progressDialog.dismiss();
                            Toast.makeText(FinalRegister.this , "Error in Saving" , Toast.LENGTH_SHORT).show();

                        }else {


                            progressDialog.dismiss();
                            Intent intent = new Intent(FinalRegister.this , FinalLogin.class);
                            startActivity(intent);


                        }


                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                progressDialog.dismiss();

                Toast.makeText(FinalRegister.this, "Failed to Create User.", Toast.LENGTH_SHORT).show();

            }
        });


    }
}
