package com.developer.arsltech.poj_laib_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyAdapter extends ArrayAdapter<Employee> {

    Context context;
    List<Employee> arrayListEmployee;


    public MyAdapter(@NonNull Context context, List<Employee> arrayListEmployee) {
        super(context, R.layout.custom_list_item,arrayListEmployee);

        this.context = context;
        this.arrayListEmployee = arrayListEmployee;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item,null,true);

        TextView tvID = view.findViewById(R.id.txt_id);
        TextView tvName = view.findViewById(R.id.txt_name);
        TextView tvEmail = view.findViewById(R.id.txt_email);
        TextView tvContact = view.findViewById(R.id.txt_contact);
        TextView tvAddress = view.findViewById(R.id.txt_address);

        tvID.setText(arrayListEmployee.get(position).getId());
        tvName.setText(arrayListEmployee.get(position).getName());
        tvEmail.setText(arrayListEmployee.get(position).getEmail());
        tvContact.setText(arrayListEmployee.get(position).getContact());
        tvAddress.setText(arrayListEmployee.get(position).getAddress());

        return view;
    }
}