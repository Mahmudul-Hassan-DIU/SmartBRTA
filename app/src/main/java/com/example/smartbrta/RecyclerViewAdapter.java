package com.example.smartbrta;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>  {

    private ArrayList<DataSetFire> dataSetFireArrayList = new ArrayList<>();
    Context ctx;

    public void setDataList(ArrayList<DataSetFire> dataSetFireArrayList, Context ctx) {
        this.dataSetFireArrayList = dataSetFireArrayList;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item,null);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, final int position) {

        DataSetFire dataSetFire = dataSetFireArrayList.get(position);

        if(dataSetFire.getGender().equals("Male"))
        {
            holder.profile.setImageResource(R.drawable.boy);
        }
        else {
            holder.profile.setImageResource(R.drawable.girl);
        }

        holder.name.setText(dataSetFire.getName());
        holder.reg.setText("Reg Num: "+dataSetFire.getReg());



        holder.profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataSetFire dataSetFire = dataSetFireArrayList.get(position);
                AlertDialog alertDialog = new AlertDialog();
                alertDialog.setDetails(dataSetFire.getName(),dataSetFire.getAddress(), dataSetFire.getVehicle(), dataSetFire.getMobile());
                alertDialog.show(((AppCompatActivity) ctx).getSupportFragmentManager(),"Dialog");
            }
        });





    }

    @Override
    public int getItemCount() {
        return dataSetFireArrayList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        public TextView reg,name,gender;
        public CircleImageView profile;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            reg = itemView.findViewById(R.id.reg);
            name = itemView.findViewById(R.id.name);
            profile = itemView.findViewById(R.id.profilepic);
            gender=itemView.findViewById(R.id.gender);
        }
    }

}
