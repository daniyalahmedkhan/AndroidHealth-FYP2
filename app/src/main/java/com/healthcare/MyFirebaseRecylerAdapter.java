package com.healthcare; //change the package name to your project's package name

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

class MyFirebaseRecylerAdapter extends FirebaseRecyclerAdapter<Hospital, MyFirebaseRecylerAdapter.HospitalViewHolder> {

    private Context mContext;
    private List<Map<String, ?>> dataSet;
    private static ImageView iv,popup;


    static RecyclerClickListener RecyclerClickListener;


    public MyFirebaseRecylerAdapter(Class<Hospital> modelClass, int modelLayout,
                                    Class<HospitalViewHolder> holder, DatabaseReference ref, Context context) {
        super(modelClass, modelLayout, holder, ref);
        this.mContext = context;
    }



    public void setRecyclerClickListener(RecyclerClickListener recyclerClickListener)
    {
        this.RecyclerClickListener=recyclerClickListener;

    }


    @Override
    protected void populateViewHolder(HospitalViewHolder hospitalViewHolder, Hospital hospital, int i) {

       hospitalViewHolder.title.setText(hospital.getName());
        hospitalViewHolder.description.setText(hospital.getDescription());
        Picasso.with(mContext).load(hospital.getUrl()).into(hospitalViewHolder.iv);





        //TODO: Populate viewHolder by setting the movie attributes to cardview fields
        //movieViewHolder.nameTV.setText(movie.getName());
    }

    //TODO: Populate ViewHolder and add listeners.
    public static  class HospitalViewHolder extends RecyclerView.ViewHolder {


        public TextView title,description;
        public ImageView iv,popup;

        public HospitalViewHolder(View itemView) {
            super(itemView);
            iv=(ImageView)itemView.findViewById(R.id.img);
            title=(TextView)itemView.findViewById(R.id.name);
            description=(TextView)itemView.findViewById(R.id.desc);
            //popup=(ImageView) itemView.findViewById(R.id.popup);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RecyclerClickListener.onItemClick(getAdapterPosition());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    RecyclerClickListener.onItemLongClick(v,getAdapterPosition());
                    return true;
                }
            });

            if(popup!=null)
            {
                popup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (RecyclerClickListener!=null)
                        {
                            RecyclerClickListener.onOverFlowMenuClick(v,getAdapterPosition());
                        }
                    }
                });

            }

        }
    }



}
