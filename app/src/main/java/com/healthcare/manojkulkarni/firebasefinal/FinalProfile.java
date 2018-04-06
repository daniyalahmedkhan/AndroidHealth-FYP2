package com.healthcare.manojkulkarni.firebasefinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.healthcare.ModelClass.UserClass;
import com.healthcare.R;

import org.w3c.dom.Text;

public class FinalProfile extends AppCompatActivity {



    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    TextView Name , Contact , addres , donate , bloodgroup;

    Button btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("DATA");

        Name = (TextView) findViewById(R.id.Name);

        Contact = (TextView) findViewById(R.id.Contact);

        addres = (TextView) findViewById(R.id.addres);

        donate = (TextView) findViewById(R.id.donate);

        bloodgroup = (TextView) findViewById(R.id.bloodgroup);


        btnEdit = (Button) findViewById(R.id.EditProfile);



        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(FinalProfile.this , FinalEditProfile.class);
                startActivity(intent);
                FinalProfile.this.finish();

            }
        });


            GETDATA();

    }


    public  void GETDATA(){


        databaseReference.child("USERS_DATA").child(FinalLogin.C_UID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                if (dataSnapshot.exists()){


                    try {


                        UserClass userClass = dataSnapshot.getValue(UserClass.class);



                        Name.setText(userClass.getFirstName());
                        Contact.setText(userClass.getContact());
                        addres.setText(userClass.getAddress());
                        donate.setText(userClass.getDonate());
                        bloodgroup.setText(userClass.getBloodgroup());

                    }catch (Exception e){


                    }


                }else{


                    Toast.makeText(FinalProfile.this, "Not Found Data", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
