package com.example.charging_station_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.ViewHolder>{

    ArrayList<data> mList;


    private static RecyclerViewClickListener listener ;


    public ShowAdapter(ArrayList<data> mList, RecyclerViewClickListener listener) {
        this.mList = mList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ouritem,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        data vacancy1 = mList.get(position);


        holder.name.setText("Station Name - "+vacancy1.getName());
        holder.address.setText("Address- "+vacancy1.getAddress());
        holder.number.setText("Number-"+vacancy1.getNumber());
        holder.tslot.setText("Total Slot-"+vacancy1.getTslot());
        holder.fslot.setText("Free Slot -"+vacancy1.getFslot());


        Glide.with(holder.imageurl.getContext()).load(vacancy1.getImageurl()).into(holder.imageurl);
    }



    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView name, address, number, tslot, fslot;
        ImageView imageurl;
//        Button btninsert;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.nametext);
            address = itemView.findViewById(R.id.coursetext);
            number = itemView.findViewById(R.id.emailtext);
            tslot = itemView.findViewById(R.id.text);
            fslot = itemView.findViewById(R.id.text1);

//            itemView.setOnClickListener((View.OnClickListener) this);

//            btninsert = itemView.findViewById(R.id.btninsert);

            imageurl = itemView.findViewById(R.id.img1);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
        listener.onClick(view,getAdapterPosition());
        }




    }
}
