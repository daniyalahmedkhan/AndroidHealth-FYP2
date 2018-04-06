package com.healthcare.manojkulkarni.firebasefinal;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.healthcare.Adapter.DrawerAdapter;
import com.healthcare.LoginActivity;
import com.healthcare.R;

public class FinalMain extends AppCompatActivity implements  View.OnClickListener{

    String names[];
    DrawerLayout mDrawerLayout;
    ListView mDrawerList;
    ImageView Drawer_icon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_main);


        //// Side Drawer///


        names = new String[]{"My Profile" , "Hospitals" , "NearBY" , "SignOut"};
        int img[] = {R.drawable.user , R.drawable.user , R.drawable.user , R.drawable.user};
        //mPlanetTitles = getResources().getStringArray(R.array.planets_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.Left_Drawer_home);
        Drawer_icon = (ImageView) findViewById(R.id.Drawer_icon);

        mDrawerList.setFitsSystemWindows(true);

        mDrawerList.setAdapter(new DrawerAdapter(this, img, names));
        ViewGroup header = (ViewGroup) getLayoutInflater().inflate(R.layout.drawer_header, mDrawerList, false);


      //  ImageView proImage = (ImageView)header.findViewById(R.id.proImg);
        TextView proText = (TextView)header.findViewById(R.id.NAMETEXT);
        TextView proEmail = (TextView)header.findViewById(R.id.EDITTEXT);


     //   proImage.setVisibility(View.GONE);
        proText.setVisibility(View.GONE);
        proEmail.setVisibility(View.GONE);


        mDrawerList.addHeaderView(header, null, false);

        Drawer_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mDrawerLayout.isDrawerOpen(Gravity.LEFT))
                {
                    mDrawerLayout.closeDrawer(mDrawerList);


                }
                else {
                    mDrawerLayout.openDrawer(Gravity.LEFT);

                }

            }
        });
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        //////// Side Drawer End //////


    }



    @Override
    public void onClick(View view) {}


    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }





    public  void  selectItem(int pos){

        Intent i;

        switch (pos){

            case 1:


                i = new Intent(FinalMain.this, FinalProfile.class);
                startActivity(i);

                break;

            case 2:


                i = new Intent(FinalMain.this, FinalHospitalsList.class);
                startActivity(i);

                break;

            case 3:


                i = new Intent(FinalMain.this, FinalNearBy.class);
                startActivity(i);

                break;

            case 4:

                FirebaseAuth.getInstance().signOut();
                i = new Intent(FinalMain.this, FinalLogin.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);

                break;


            default:
                break;

        }


    }


}
