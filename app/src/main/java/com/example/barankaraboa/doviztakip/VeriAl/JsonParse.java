package com.example.barankaraboa.doviztakip.VeriAl;

import android.app.Activity;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class JsonParse extends Activity {

    public static String Adolar;
    public static String Aeuro;

    public ArrayList<String> kurlarim = new ArrayList<String>();

    RequestQueue rq;

    public  void JsonAlma(final Activity activity) {
    //     String url="http://www.doviz.gen.tr/doviz_json.asp?version=1.2.0";
       String url2 ="http://www.doviz.com/api/v1/currencies/all/latest";



        String coluad = "";;
        JsonArrayRequest degerAl = new JsonArrayRequest(url2, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray res) {
                for(int i =0 ;i<res.length() ; i++) {
                    try {

                        JSONObject gelen = res.getJSONObject(i);
                        kurlarim.add(gelen.getString("full_name"));
                        Toast.makeText(activity.getApplicationContext(),"kur: "+gelen.getString("full_name"),Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                VolleyLog.d("Hata : ",volleyError);
            }
        });

      Volley.newRequestQueue(activity.getApplicationContext()).add(degerAl);

    }


}
