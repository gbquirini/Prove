package com.example.bui.geovolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button CB;
    private EditText ET;
    private EditText ET2;

    private String Url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CB = findViewById(R.id.CoorBn);
        ET = findViewById(R.id.name);
        ET2 = findViewById(R.id.name2);





        CB.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + ET.getText() + "," + ET2.getText() + "&key=AIzaSyBdcKcNjzL77gY3hU370aazEf7cgWYTYVM";

        RequestQueue queue; //= Volley.newRequestQueue(this);

        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        Network network = new BasicNetwork(new HurlStack());

        queue = new RequestQueue(cache, network);

        queue.start();



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, Url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(MainActivity.this, "ci siamo", Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

        Singleton.getInstance(this).addToRequestQueue(jsonObjectRequest);


    }
}
