package com.android.lucas.webserviceexamplevolley.cdp;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lucas on 19/05/17.
 */

public class CustomJsonArrayRequest extends Request<JSONArray> {

    private Response.Listener<JSONArray> response;
    private Map<String, String> params;

    public CustomJsonArrayRequest(int method, String url, Map<String, String> params, Response.Listener<JSONArray> response, Response.ErrorListener listener) {
        super(url, listener);
        this.params = params;
        this.response = response;
    }

    public CustomJsonArrayRequest(String url, Map<String, String> params, Response.Listener<JSONArray> response, Response.ErrorListener listener) {
        super(url, listener);
        this.params = params;
        this.response = response;
    }

    public Map<String, String> getParams() throws AuthFailureError {
        return params;
    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> header = new HashMap<String, String>();
        header.put("apikey", "Essa e minha API KEY: json array");
        return(header);
    }

    public Priority getPriority() {
        return(Priority.NORMAL);
    }

    @Override
    protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
        try {
            String js = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return(Response.success(new JSONArray(js), HttpHeaderParser.parseCacheHeaders(response)));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void deliverResponse(JSONArray response) {
        this.response.onResponse(response);
    }
}
