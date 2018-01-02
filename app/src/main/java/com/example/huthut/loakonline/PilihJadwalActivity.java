package com.example.huthut.loakonline;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.huthut.loakonline.Class.Jadwal;
import com.example.huthut.loakonline.Class.Kategori;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PilihJadwalActivity extends AppCompatActivity implements OnMapReadyCallback {
    private Spinner spnHari;
    private Spinner spnWaktu;
    private Button btnCari;
    private String hari;
    private ArrayList<Jadwal> listJadwal;
    private JadwalAdapter adapter;

    private static final String[] INITIAL_PERMS={
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.READ_CONTACTS
    };
    private static final int INITIAL_REQUEST=1337;


    private GoogleMap mMap;
    private TextView markerText;
    private LinearLayout markerLayout;
    private LatLng center;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onStart() {
        super.onStart();
        if (!canAccessLocation()) {
            requestPermissions(INITIAL_PERMS, INITIAL_REQUEST);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_jadwal);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        markerText = (TextView) findViewById(R.id.locationMarkertext);
        markerLayout = (LinearLayout) findViewById(R.id.locationMarker);
        spnHari = (Spinner) findViewById(R.id.spnHari);
        spnWaktu = (Spinner) findViewById(R.id.spnWaktu);
        btnCari = (Button) findViewById(R.id.btnCari);
        listJadwal = new ArrayList<>();

        spinnerHari();
        spinnerWaktu();
        buttonCari();
        try {
            // Loading map
            initilizeMap();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void buttonCari(){
        btnCari.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Jadwal spiner = (Jadwal) spnWaktu.getSelectedItem();
                Bundle bundle = new Bundle();
                bundle.putString("id_jadwal", spiner.getId());
                bundle.putString("latitude", String.valueOf(center.latitude));
                bundle.putString("longitude", String.valueOf(center.longitude));
                bundle.putString("alamat", getCompleteAddressString(center.latitude, center.longitude));
                Intent intent = new Intent(PilihJadwalActivity.this, CariActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                //finish();
            }
        });
    }

    private void spinnerHari(){
        ArrayList<String> lables = new ArrayList<String>();
        lables.add("Minggu");
        lables.add("Senin");
        lables.add("Selasa");
        lables.add("Rabu");
        lables.add("Kamis");
        lables.add("Jumat");
        lables.add("Sabtu");
        spnHari.setAdapter(new ArrayAdapter<String>(PilihJadwalActivity.this, android.R.layout.simple_spinner_dropdown_item, lables));

        spnHari.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hari = parent.getItemAtPosition(position).toString();
                listJadwal.clear();
                Jadwal jad = new Jadwal("0", "", "", "Pilih Jadwal - ");
                listJadwal.add(jad);
                spnWaktu.setSelection(0);
                spinnerWaktu();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapter) {  }
        });
    }

    private void spinnerWaktu(){
        Response.Listener<String> responseListener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                JSONObject jsonResponse = null;
                boolean error = false;
                try {
                    jsonResponse = new JSONObject(response);
                    error = jsonResponse.getBoolean("error");

                    if (!error){
                        JSONObject result = new JSONObject(response);
                        JSONArray array  = result.getJSONArray("jadwal");

                        listJadwal.clear();
                        Jadwal jad = new Jadwal("0", "", "", "Pilih Jadwal - ");
                        listJadwal.add(jad);

                        for(int i=0;i<array.length();i++) {
                            JSONObject catObj = array.getJSONObject(i);
                            String id = catObj.getString("id_jadwal");
                            String hari2 = catObj.getString("hari");
                            String jamB = catObj.getString("jam_buka");
                            String jamT = catObj.getString("jam_tutup");
                            Jadwal jdw = new Jadwal(id, hari2, jamB, jamT);
                            listJadwal.add(jdw);
                        }

                        adapter = new JadwalAdapter(PilihJadwalActivity.this,
                                R.layout.support_simple_spinner_dropdown_item,
                                listJadwal);
                        spnWaktu.setAdapter(adapter);
                        spnWaktu.setSelection(0);
                    }
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        };

        JadwalRequest jdwRequest = new JadwalRequest(spnHari.getSelectedItem().toString(), responseListener);
        RequestQueue queue = Volley.newRequestQueue(PilihJadwalActivity.this);
        queue.add(jdwRequest);
    }

    private void initilizeMap() {
        if (mMap == null) {
            ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMapAsync(this);

            // check if map is created successfully or not
            if (mMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
            }
        } else {
            //Not in api-23, no need to prompt
            mMap.setMyLocationEnabled(true);
        }

        LatLng mLatLng = new LatLng(-6.594428, 106.805581);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(mLatLng).zoom(15f).build();

        mMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));
        mMap.clear();

        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                center = mMap.getCameraPosition().target;
                markerText.setText(" Set your Location ");
                mMap.clear();
                markerLayout.setVisibility(View.VISIBLE);

                TextView txtLokasi = (TextView) findViewById(R.id.txtLokasi);
                String alamat = getCompleteAddressString(center.latitude, center.longitude);
                txtLokasi.setText(alamat);
            }
        });
    }

    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                Log.w("My Current loction address", strReturnedAddress.toString());
            } else {
                Log.w("My Current loction address", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("My Current loction address", "Canont get Address!");
        }
        return strAdd;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean canAccessLocation() {
        return(hasPermission(android.Manifest.permission.ACCESS_FINE_LOCATION));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean hasPermission(String perm) {
        return(PackageManager.PERMISSION_GRANTED==checkSelfPermission(perm));
    }
}
