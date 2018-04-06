package com.healthcare.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.healthcare.R;

import java.util.ArrayList;

/**
 * Created by daniyalahmed on 4/6/2018.
 */

public class ListAdapterNear extends BaseAdapter {


    public final ArrayList<String> name;
    public final ArrayList<String> contact;
    public final ArrayList<String> address;
    public final ArrayList<String> lastdonate;
    public final ArrayList<String> bloodgroup;




    public Context mContext;


    public ListAdapterNear(Context context , ArrayList<String> name, ArrayList<String> contact, ArrayList<String> address, ArrayList<String> lastdonate, ArrayList<String> bloodgroup) {

        this.name = name;
        this.contact = contact;
        this.address = address;
        this.lastdonate = lastdonate;
        this.bloodgroup = bloodgroup;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return name.size(); }

    @Override
    public Object getItem(int i) {

        return null;
    }

    @Override
    public long getItemId(int i) {

        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        ViewHodler obj;

        if (view == null) {

            obj = new ViewHodler();


            view = inflater.inflate(R.layout.custom_list_adapter, null);
            obj.name = (TextView) view.findViewById(R.id.NameNear);
            obj.bloodgroup = (TextView) view.findViewById(R.id.BloodNear);
            obj.lastdonate = (TextView) view.findViewById(R.id.LastDonateNear);
            obj.contact = (TextView) view.findViewById(R.id.ContactNear);
            obj.address = (TextView) view.findViewById(R.id.AddNear);




            obj.name.setText(name.get(i));
            obj.bloodgroup.setText(bloodgroup.get(i));
            obj.lastdonate.setText(lastdonate.get(i));
            obj.contact.setText(contact.get(i));
            obj.address.setText(address.get(i));




            view.setTag(obj);


        } else {
            //gridView = (View) view;
            obj = (ViewHodler)view.getTag();
        }



        return view;
    }


    public  static  class  ViewHodler{

        TextView name, bloodgroup, lastdonate, contact , address;




    }

}
