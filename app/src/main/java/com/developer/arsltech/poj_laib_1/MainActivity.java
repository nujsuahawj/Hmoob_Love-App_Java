package com.developer.arsltech.poj_laib_1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
//    EditText txtName,txtpass;
    TextView txt_login,txt_reate;
    Button btn_login;

    private static final String apiurl="https://nriavtushlubplogteb.000webhostapp.com/andriod/login_maker.php";
    EditText t1,t2;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        txtName     = findViewById(R.id.username);
//        txtpass    = findViewById(R.id.password);

        txt_login  = findViewById(R.id.txtloginshow);
        btn_login  = findViewById(R.id.btnlogin);
        txt_reate = findViewById(R.id.txtcreateshow);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login();
            }
        });
    }

    private void login() {

        t1 = (EditText)findViewById(R.id.username);
        t2 = (EditText)findViewById(R.id.password);
        tv=(TextView)findViewById(R.id.tv);

        final String name = t1.getText().toString().trim();
        final String pass = t2.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        if(name.isEmpty()){
            Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(pass.isEmpty()){
            Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        else{
            String qry="?t1="+t1.getText().toString().trim()+"&t2="+t2.getText().toString().trim();

            @SuppressWarnings("deprecation")
            class dbprocess extends AsyncTask<String,Void,String>
            {
                @Override
                protected  void onPostExecute(String data)
                {
                    if(data.equals("found"))
                    {
                        SharedPreferences sp=getSharedPreferences("credentials",MODE_PRIVATE);
                        SharedPreferences.Editor editor=sp.edit();
                        editor.putString("uname",t1.getText().toString());
                        editor.commit();
                        startActivity(new Intent(getApplicationContext(),dashboard.class));
                    }
                    else
                    {
                        t1.setText("");
                        t2.setText("");
                        tv.setTextColor(Color.parseColor("#8B0000"));
                        tv.setText(data);
                    }
                }
                @Override
                protected String doInBackground(String... params)
                {
                    String furl=params[0];

                    try
                    {
                        URL url=new URL(furl);
                        HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                        BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));

                        return br.readLine();

                    }catch (Exception ex)
                    {
                        return ex.getMessage();
                    }
                }
            }
            dbprocess obj=new dbprocess();
            obj.execute(apiurl+qry);

        }

    }

    public void checklogoutmsg(View view)
    {
        tv=(TextView)findViewById(R.id.tv);

        SharedPreferences sp=getSharedPreferences("credentials",MODE_PRIVATE);
        if(sp.contains("msg"))
        {
            tv.setText(sp.getString("msg", ""));
            SharedPreferences.Editor ed=sp.edit();
            ed.remove("msg");
            ed.commit();
        }
    }

    public void register_nuj(View view) {
        startActivity(new Intent(getApplicationContext(),register.class));
    }

}