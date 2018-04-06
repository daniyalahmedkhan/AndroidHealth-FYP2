package com.healthcare;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by manojkulkarni on 4/15/17.
 */

public class BloodBankData {

    List<Map<String,?>> bloodBankList;
    DatabaseReference mRef4;
    MyFirebaseRecyclerAdapter2 myFirebaseRecyclerAdapter2;
    Context mContext2;

    public void setAdapter(MyFirebaseRecyclerAdapter2 mAdapter) {
        myFirebaseRecyclerAdapter2 = mAdapter;
    }

    public void removeItemFromServer(Map<String,?> bloodBank){
        if(bloodBank!=null){
            String id = (String)bloodBank.get("id");
            mRef4.child(id).removeValue();
        }
    }

    public void addItemToServer(Map<String,?> bloodBank){
        if(bloodBank!=null){
            String id = (String) bloodBank.get("id");
            mRef4.child(id).setValue(bloodBank);
        }
    }

    public DatabaseReference getFireBaseRef(){
        return mRef4;
    }
    public void setContext(Context context){mContext2 = context;}

    public List<Map<String, ?>> getBloodBankList() {
        return bloodBankList;
    }

    public int getSize(){
        return bloodBankList.size();
    }

    public HashMap getItem(int i){
        if (i >=0 && i < bloodBankList.size()){
            return (HashMap) bloodBankList.get(i);
        } else return null;
    }


    public void onItemRemovedFromCloud(HashMap item){
        int position = -1;
        String id=(String)item.get("id");
        for(int i=0;i<bloodBankList.size();i++){
            HashMap bloodBank = (HashMap)bloodBankList.get(i);
            String mid = (String) bloodBank.get("id");
            if(mid.equals(id)){
                position= i;
                break;
            }
        }
        if(position != -1){
            bloodBankList.remove(position);
            Toast.makeText(mContext2, "Item Removed:" + id, Toast.LENGTH_SHORT).show();

        }
    }

    public void onItemAddedToCloud(HashMap item){
        int insertPosition = 0;
        String id=(String)item.get("id");
        for(int i=0;i<bloodBankList.size();i++){
            HashMap bloodBank = (HashMap)bloodBankList.get(i);
            String mid = (String)bloodBank.get("contact");
            if(mid.equals(id)){
                return;
            }
            if(mid.compareTo(id)<0){
                insertPosition=i+1;
            }else{
                break;
            }
        }
        bloodBankList.add(insertPosition,item);
        // Toast.makeText(mContext, "Item added:" + id, Toast.LENGTH_SHORT).show();

    }

    public void onItemUpdatedToCloud(HashMap item){
        String id=(String)item.get("id");
        for(int i=0;i<bloodBankList.size();i++){
            HashMap bloodBank = (HashMap)bloodBankList.get(i);
            String mid = (String)bloodBank.get("id");
            if(mid.equals(id)){
                bloodBankList.remove(i);
                bloodBankList.add(i,item);
                //Log.d("My Test: NotifyChanged",id);

                break;
            }
        }

    }
    public void initializeDataFromCloud() {
        bloodBankList.clear();
        mRef4.addChildEventListener(new com.google.firebase.database.ChildEventListener() {
            @Override
            public void onChildAdded(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {
                Log.d("MyTest: OnChildAdded", dataSnapshot.toString());
                HashMap<String,String> bloodBank = (HashMap<String,String>)dataSnapshot.getValue();
                onItemAddedToCloud(bloodBank);
            }

            @Override
            public void onChildChanged(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {
                Log.d("MyTest: OnChildChanged", dataSnapshot.toString());
                HashMap<String,String> bloodBank = (HashMap<String,String>)dataSnapshot.getValue();
                onItemUpdatedToCloud(bloodBank);
            }

            @Override
            public void onChildRemoved(com.google.firebase.database.DataSnapshot dataSnapshot) {
                Log.d("MyTest: OnChildRemoved", dataSnapshot.toString());
                HashMap<String,String> bloodBank = (HashMap<String,String>)dataSnapshot.getValue();
                onItemRemovedFromCloud(bloodBank);
            }

            @Override
            public void onChildMoved(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public BloodBankData(){

        bloodBankList = new ArrayList<Map<String,?>>();
        mRef4 = FirebaseDatabase.getInstance().getReference().child("bloodbankdata").getRef();
        myFirebaseRecyclerAdapter2 = null;
        mContext2 = null;

    }


    public int findFirst(String query){

        for(int i=0;i<bloodBankList.size();i++){
            HashMap hm = (HashMap)getBloodBankList().get(i);
            if(((String)hm.get("name")).toLowerCase().contains(query.toLowerCase())){
                return i;
            }
        }
        return 0;

    }

}