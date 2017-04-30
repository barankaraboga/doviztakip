package com.example.barankaraboa.doviztakip;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;


public class artisTakip extends Fragment {

    private static String urlA = "http://www.doviz.com/api/v1/currencies/all/latest";
    private static TextView dolarArtisMiktar;
    private static String artisGom;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View vvc = inflater.inflate(R.layout.doviz_artis, container, false);

        artisVeri(urlA);

        dolarArtisMiktar = (TextView) vvc.findViewById(R.id.dolarArtan);
        dolarArtisMiktar.setText("Deneme Ok !");

         return vvc;



    }

    public void artisVeri(String url) {

        JsonArrayRequest req = new JsonArrayRequest(urlA, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray cevap) {

                for (int i = 0; i < 2 ; i++) {
                    try {

                        JSONObject obje = cevap.getJSONObject(i);

                       // dolarArtisMiktar.setText(obje.getString("selling"));
                        if (Objects.equals(obje.getString("code"), "USD")) {
                            artisGom = obje.getString("change_rate");
                            dolarArtisMiktar.setText(artisGom.substring(0,8));
                            Toast.makeText(getActivity(), "Guncellendi", Toast.LENGTH_SHORT).show();
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError hata) {
                VolleyLog.d("Hata : " + hata);
            }


        });

        Volley.newRequestQueue(getActivity()).add(req);

    }

}
