package com.example.smartbrta;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter_fc extends RecyclerView.Adapter<RecyclerViewAdapter_fc.RecyclerViewHolder>  {

    private ArrayList<DataSetFire> dataSetFireArrayList = new ArrayList<>();

    Context context;

    private String uid;

    public void setDataList(ArrayList<DataSetFire> dataSetFireArrayList) {
        this.dataSetFireArrayList = dataSetFireArrayList;
    }
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_fc,null);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder, final int position) {

        DataSetFire dataSetFire = dataSetFireArrayList.get(position);


        if(dataSetFire.getGender().equals("Male"))
        {
            holder.profile.setImageResource(R.drawable.boy);
        }
        else if(dataSetFire.getGender().equals("Female")){
            holder.profile.setImageResource(R.drawable.girl);
        }


        holder.name.setText(dataSetFire.getName());
        holder.reg.setText("Reg Num: "+dataSetFire.getReg());
        holder.amount.setText("Amount: "+dataSetFire.getAmount()+" Tk");
        holder.status.setText("Status: "+dataSetFire.getStatus());


      if(dataSetFire.getStatus().equals("Paid")){
            holder.ok.setVisibility(View.VISIBLE);
        }

      holder.ok.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              DataSetFire dataSetFire = dataSetFireArrayList.get(position);
              DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Owners").child(dataSetFire.getId());

              Map<String,Object> map = new HashMap<>();

              map.put("amount","0");
              map.put("fault","Null");
              map.put("paid","0");
              map.put("status","Null");
              map.put("within","N/A");


              databaseReference.updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                  @Override
                  public void onComplete(@NonNull Task<Void> task) {

                      if(task.isSuccessful()){
                          dataSetFireArrayList.remove(position);
                          notifyItemRemoved(position);

                      }
                  }
              });
          }
      });
    }


    @Override
    public int getItemCount() {
        return dataSetFireArrayList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        public TextView reg,name,gender,amount,status;
        public CircleImageView profile;
        public ImageView ok;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            reg = itemView.findViewById(R.id.reg);
            name = itemView.findViewById(R.id.name);
            profile = itemView.findViewById(R.id.profilepic);
            gender=itemView.findViewById(R.id.gender);
            amount=itemView.findViewById(R.id.amount);
            status=itemView.findViewById(R.id.status);
            ok=itemView.findViewById(R.id.ok);
        }
    }
}
