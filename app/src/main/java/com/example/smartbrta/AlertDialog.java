package com.example.smartbrta;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class AlertDialog extends AppCompatDialogFragment {

    public String name;
    public String address;
    public String vehicle;
    public String phone;

    public void setDetails(String name, String address, String vehicle, String phone) {
        this.name = name;
        this.address = address;
        this.vehicle = vehicle;
        this.phone = phone;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_layout,null);

        builder.setView(view);
        builder.setCancelable(true);
        builder.setTitle(null);

        TextView tvName = view.findViewById(R.id.dname);
        TextView tvAddress = view.findViewById(R.id.daddress);
        TextView tvVehicle = view.findViewById(R.id.dvehicle);
        TextView tvPhone = view.findViewById(R.id.dphone);

        tvName.setText(name);
        tvAddress.setText(address);
        tvVehicle.setText(vehicle);
        tvPhone.setText(phone);

        isCancelable();
        return builder.create();
    }
}
