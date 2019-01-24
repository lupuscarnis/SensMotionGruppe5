package com.example.nicolai.sensmotiongruppe5.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nicolai.sensmotiongruppe5.Achievements;
import com.example.nicolai.sensmotiongruppe5.R;

import java.util.ArrayList;

public class achiAdapter extends RecyclerView.Adapter<achiAdapter.MyViewHolder> {
        private ArrayList<Achievements> item;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public static class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView aImage;
            TextView achiName;
            TextView achiDes;
            TextView date;
            RelativeLayout parent_Layout;



            public MyViewHolder(View itemView) {
                super(itemView);
                achiName = itemView.findViewById(R.id.achiName);
                achiDes = itemView.findViewById(R.id.desText);
                aImage = itemView.findViewById(R.id.achiLogo);
                date = itemView.findViewById(R.id.date);
                parent_Layout = itemView.findViewById(R.id.parent_layout);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public achiAdapter(ArrayList<Achievements> myDataset) {

            this.item = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public achiAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycle_achievements, parent, false);
            MyViewHolder vh = new MyViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {





            if (item.get(position).getCompleted()){
                holder.aImage.setImageResource(R.drawable.achi);
                holder.achiDes.setText(item.get(position).getDoneDes());
                holder.date.setText(item.get(position).getDate());
            }
            if (!item.get(position).getCompleted()){
                holder.aImage.setImageResource(R.drawable.achinot);
                holder.achiDes.setText(item.get(position).getNotDes());
                holder.date.setText("");
            }
            holder.achiName.setText(item.get(position).getName());
        }


        @Override
        public int getItemCount() {
            return item.size();
        }
    }


