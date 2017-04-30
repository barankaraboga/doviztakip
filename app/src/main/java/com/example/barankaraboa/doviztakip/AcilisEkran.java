package com.example.barankaraboa.doviztakip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.barankaraboa.doviztakip.VeriAl.JsonParse;

/**
 * Created by Baran on 21.03.2016.
 */
public class AcilisEkran extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acilis_ekran);

        JsonParse start = new JsonParse();
        start.JsonAlma(AcilisEkran.this);


        Thread zamanlayici = new Thread() {

        public void run() {

        try {
            sleep(5000);
        }catch (InterruptedException e)
        {
            e.printStackTrace();
        }finally {
            Intent i = new Intent(getApplicationContext(),dovizTakip.class);
            startActivity(i);
        }


    } };
        zamanlayici.start();
    }
}

