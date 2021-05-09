package com.developer.arsltech.poj_laib_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    TextView tvid,tvname,tvemail,tvcontact,tvaddress;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        //Initializing Views
        tvid = findViewById(R.id.txtid);
        tvname = findViewById(R.id.txtname);
        tvemail = findViewById(R.id.txtemail);
        tvcontact = findViewById(R.id.txcontact);
        tvaddress = findViewById(R.id.txtaddress);

        Intent intent =getIntent();
        position = intent.getExtras().getInt("position");

        tvid.setText("ID: "+showacount.employeeArrayList.get(position).getId());
        tvname.setText("Name: "+showacount.employeeArrayList.get(position).getName());
        tvemail.setText("Email: "+showacount.employeeArrayList.get(position).getEmail());
        tvcontact.setText("Contact: "+showacount.employeeArrayList.get(position).getContact());
        tvaddress.setText("Address: "+showacount.employeeArrayList.get(position).getAddress());

    }
}
