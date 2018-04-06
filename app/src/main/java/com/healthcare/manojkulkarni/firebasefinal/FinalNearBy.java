package com.healthcare.manojkulkarni.firebasefinal;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.healthcare.Adapter.ListAdapterNear;
import com.healthcare.ModelClass.UserClass;
import com.healthcare.R;

import java.util.ArrayList;

public class FinalNearBy extends AppCompatActivity {

    ListView NearByList;

    DatabaseReference databaseReference;


    ArrayList Name , Contact , Address , LastDonate , BloodGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_near_by);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        Name = new ArrayList();
        Contact = new ArrayList();
        Address = new ArrayList();
        LastDonate = new ArrayList();
        BloodGroup = new ArrayList();

        NearByList = (ListView) findViewById(R.id.NearByList);



        GETUSER();


    }


    public  void GETUSER(){

        databaseReference.child("DATA").child("USERS_DATA").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){


                    UserClass userClass = dataSnapshot1.getValue(UserClass.class);

                    Name.add(userClass.getFirstName());
                    Contact.add(userClass.getContact());
                    Address.add(userClass.getAddress());
                    LastDonate.add(userClass.getDonate());
                    BloodGroup.add(userClass.getBloodgroup());



                }

                BindData();

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void  BindData(){


        ListAdapterNear adapterNear = new ListAdapterNear(FinalNearBy.this , Name , Contact , Address , LastDonate , BloodGroup);
        NearByList.setAdapter(adapterNear);

    }
}
