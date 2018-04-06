package com.healthcare.manojkulkarni.firebasefinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.healthcare.R;

public class FinalHospitalsDetails extends AppCompatActivity {

    String[] list = {"Agha khan Hospitals" , "National Hospitals" , "Indus Hospitals"};
    String[] contact = {"0300212121" , "0315413201" , "02134567889"};
    String[] address = {"Clifton" , "Johar More " , "ShahraeFaisal"};
    int pos = FinalHospitalsList.position;


    TextView Hname , Hcontact , Haddr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_hospitals_details);

        Hname = (TextView) findViewById(R.id.Hname);
        Hcontact = (TextView) findViewById(R.id.Hcontact);

        Haddr = (TextView) findViewById(R.id.Haddr);



        Hname.setText(list[pos]);
        Hcontact.setText(contact[pos]);
        Haddr.setText(address[pos]);
    }
}
