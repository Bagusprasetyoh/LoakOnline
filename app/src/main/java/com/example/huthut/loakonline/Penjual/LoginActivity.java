package com.example.huthut.loakonline.Penjual;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.huthut.loakonline.Pengepul.DaftarTransaksiActivity;
import com.example.huthut.loakonline.Pengepul.HomePengepulActivity;
import com.example.huthut.loakonline.R;
import com.example.huthut.loakonline.helper.SQLiteHandler;
import com.example.huthut.loakonline.helper.SessionManager;
import com.example.huthut.loakonline.Class.Status;

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
        /*if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(LoginActivity.this, DaftarBarangActivity.class);
            startActivity(intent);
            finish();
        }*/

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pDialog.setMessage("Logging in ...");
                showDialog();
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
                            hideDialog();
                            boolean error = false;
                            try {
                                JSONObject json = new JSONObject(response);
                                error = json.getBoolean("error");

                                if (!error) {
                                    JSONObject user = json.getJSONObject("user");
                                    String id = user.getString("uid");
                                    String username = user.getString("username");
                                    String status = user.getString("status");

                                    db.deleteUsers();
                                    session.setLogin(true);
                                    db.addUser(id, username);

                                    if(Integer.parseInt(status) == 1){
                                        Intent intent = new Intent(LoginActivity.this, DaftarBarangActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else if(Integer.parseInt(status) == 2){
                                        Intent intent = new Intent(LoginActivity.this, DaftarTransaksiActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }

                                } else {
                                    builder.setMessage("Gagal");
                                    builder.setNegativeButton("Retry", null);
                                    builder.show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplication(),"gagal",Toast.LENGTH_SHORT).show();
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
