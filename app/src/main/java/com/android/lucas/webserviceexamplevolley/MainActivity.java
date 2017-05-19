package com.android.lucas.webserviceexamplevolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.lucas.webserviceexamplevolley.cdp.CustomJsonArrayRequest;
import com.android.lucas.webserviceexamplevolley.cdp.CustomJsonObjectRequest;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RequestQueue rq;
    private EditText etEmail;
    private EditText etPassword;
    private String url;
    private String urlEstados;
    private Map<String, String> params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        url = "https://maps.googleapis.com/maps/api/geocode/json?address=Uberaba&components=country:BR&key=YOUR_API_KEY_HERE"; //google api
        rq = Volley.newRequestQueue(MainActivity.this);
    }

    public void callByJsonObjectRequest(View view) {

        params = new HashMap<String, String>();
        CustomJsonObjectRequest request = new CustomJsonObjectRequest(Request.Method.POST,
                url,
                params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Script", "Sucess: " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error: " + error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        request.setTag("tag");
        rq.add(request);
    }

    //Exemplo de JsonArray
    /*public void callByJsonArrayRequest(View view) {
        params = new HashMap<String, String>();
        CustomJsonArrayRequest request = new CustomJsonArrayRequest(Request.Method.POST,
                url,
                params,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Script", "Sucess: " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error: " + error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        request.setTag("tag");
        rq.add(request);
    }*/


    public void callByStringRequest(View view) {

        StringRequest request = new StringRequest(Request.Method.POST,
            url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("Script", "Sucess: " + response);
                }
        },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, "Error: " + error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> header = new HashMap<String, String>();
                header.put("apikey", "Essa e minha API KEY: sdvkjbsdjvkbskdv");
                return(header);

            }

            @Override
            public Priority getPriority() {
                return(Priority.NORMAL);
            }

        };
        request.setTag("tag");
        rq.add(request);
    }

    @Override
    public void onStop() {
        super.onStop();
        rq.cancelAll("tag");
    }

}
