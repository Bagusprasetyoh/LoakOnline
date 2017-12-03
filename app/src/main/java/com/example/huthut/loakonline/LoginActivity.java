package com.example.huthut.loakonline;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.huthut.loakonline.helper.AppConfig;
import com.example.huthut.loakonline.helper.AppController;
import com.example.huthut.loakonline.helper.SQLiteHandler;
import com.example.huthut.loakonline.helper.SessionManager;
import com.example.huthut.loakonline.helper.Status;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private AlertDialog.Builder builder;
    private EditText inputUsername;
    private EditText inputPassword;
    private Spinner spinner;
    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;
    private ArrayList<Status> listStatus;
    private SpinnerAdapter adapter;
    private int cekStatus;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        spinner = (Spinner) findViewById(R.id.status);
        GetStatus();

        inputUsername = (EditText) findViewById(R.id.edtUsername);
        inputPassword = (EditText) findViewById(R.id.edtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // SQLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // Session manager
        session = new SessionManager(getApplicationContext());

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = inputUsername.getText().toString();
                final String password = inputPassword.getText().toString();
                final Status status = (Status) spinner.getSelectedItem();

                if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
                    builder.setMessage("Harap diisi");
                    builder.setNegativeButton("OK", null);
                    builder.show();
                }else {
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            boolean error = false;
                            try {
                                JSONObject json = new JSONObject(response);
                                error = json.getBoolean("error");

                                if (!error) {
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(getApplication(),"berhasil",Toast.LENGTH_SHORT).show();
                                    finish();

                                } else {
                                    builder.setMessage("Gagal");
                                    builder.setNegativeButton("Retry", null);
                                    builder.show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                //Toast.makeText(getApplication(),"gagal",Toast.LENGTH_SHORT).show();
                            }

                        }
                    };

                    LoginRequest LogRequest = new LoginRequest(username, password, String.valueOf(status.getId()), responseListener);
                    RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                    queue.add(LogRequest);
                }
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for(int i=0;i<listStatus.size();i++){
                    if(parent.getItemAtPosition(position).toString().equals(listStatus.get(i).getName())){
                        cekStatus = listStatus.get(i).getId();
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapter) {  }
        });
    }

    private void GetStatus() {
        listStatus = new ArrayList<>();
        listStatus.add(new Status(1,"Peloak"));
        listStatus.add(new Status(2,"Pengepul"));
        listStatus.add(new Status(3,"Pengusaha"));
        builder = new AlertDialog.Builder(LoginActivity.this);
        adapter = new SpinnerAdapter(LoginActivity.this,
                android.R.layout.simple_spinner_item,
                listStatus);

        spinner.setAdapter(adapter);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }}
