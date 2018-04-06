package com.healthcare.manojkulkarni.firebasefinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.healthcare.R;

public class FinalHospitalsList extends AppCompatActivity {


    String[] list = {"Agha khan Hospitals" , "National Hospitals" , "Indus Hospitals"};
    ListView ls;
    public static int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_hospitals_list);
        ls = (ListView) findViewById(R.id.HospitalList);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(FinalHospitalsList.this , android.R.layout.simple_list_item_1 , list);
        ls.setAdapter(adapter);

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                position = 0;
                position = i;

                Intent intent = new Intent(FinalHospitalsList.this , FinalHospitalsDetails.class);
                startActivity(intent);
            }
        });
    }
}
