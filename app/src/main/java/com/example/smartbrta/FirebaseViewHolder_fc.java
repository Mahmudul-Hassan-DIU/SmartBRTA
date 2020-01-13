package com.example.smartbrta;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

public class FirebaseViewHolder_fc extends RecyclerView.ViewHolder {


    public TextView reg,name,gender,amount,status;
   public CircleImageView profile;
   public ImageView ok;


    public FirebaseViewHolder_fc(@NonNull View itemView) {
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
