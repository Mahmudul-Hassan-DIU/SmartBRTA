package com.example.smartbrta;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

public class FirebaseViewHolder extends RecyclerView.ViewHolder {


    public TextView reg,name,gender;
   public CircleImageView profile;

    public FirebaseViewHolder(@NonNull View itemView) {
        super(itemView);

        reg = itemView.findViewById(R.id.reg);
        name = itemView.findViewById(R.id.name);
        profile = itemView.findViewById(R.id.profilepic);
        gender=itemView.findViewById(R.id.gender);

    }

}
