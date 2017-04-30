package com.example.barankaraboa.doviztakip;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.barankaraboa.doviztakip.VeriAl.JsonParse;
import com.example.barankaraboa.doviztakip.VeriAl.degerCek;

/**
 * Created by Baran on 27.02.2016.
 */
public class dovizTakip extends Activity {

    public static TextView  guncelle,deger,dolarAr,artis;
    String kurlar [] = {"Amerikan Doları","Euro","İngiliz Sterlini","Kanada Doları","Danimarka Kronu", "İsveç Kronu","İsviçre Frangı"};
  //  ArrayList<String> kurlar2 = new ArrayList<String>();

    int images[] ={R.mipmap.dolar,R.mipmap.euro,R.mipmap.euro,R.mipmap.euro,R.mipmap.euro,R.mipmap.euro,R.mipmap.euro
            ,R.mipmap.euro,R.mipmap.euro,R.mipmap.euro,R.mipmap.dolar,R.mipmap.euro,R.mipmap.euro,R.mipmap.euro,R.mipmap.euro,R.mipmap.euro,R.mipmap.euro
            ,R.mipmap.euro,R.mipmap.euro,R.mipmap.euro,R.mipmap.dolar,R.mipmap.euro,R.mipmap.euro,R.mipmap.euro,R.mipmap.euro,R.mipmap.euro,R.mipmap.euro
            ,R.mipmap.euro,R.mipmap.euro,R.mipmap.euro,R.mipmap.dolar,R.mipmap.euro,R.mipmap.euro,R.mipmap.euro,R.mipmap.euro,R.mipmap.euro,R.mipmap.euro
            ,R.mipmap.euro,R.mipmap.euro,R.mipmap.euro,R.mipmap.dolar,R.mipmap.euro,R.mipmap.euro,R.mipmap.euro,R.mipmap.euro,R.mipmap.euro,R.mipmap.euro
            ,R.mipmap.euro,R.mipmap.euro,R.mipmap.euro,R.mipmap.dolar,R.mipmap.euro,R.mipmap.euro,R.mipmap.euro,R.mipmap.euro,R.mipmap.euro,R.mipmap.euro
            ,R.mipmap.euro,R.mipmap.euro,R.mipmap.euro,R.mipmap.euro,R.mipmap.euro,R.mipmap.euro,R.mipmap.euro,R.mipmap.euro,R.mipmap.euro};

    private boolean EuroDD=false;
     private TextView deneme ;
    public static EditText edt,edt2;
    public static Spinner spinner,spinner2;
    public static String day[]={"deneme","deneme2"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doviztakip_main);
        setTitle("Doviz Hesaplama");

        deger=(TextView)(findViewById(R.id.dAlis));
        spinner2=(Spinner)(findViewById(R.id.spinner2));
        edt= (EditText) findViewById(R.id.editText);
        edt2=(EditText)findViewById(R.id.editText2);
        spinner= (Spinner) findViewById(R.id.spinner);

        JsonParse start = new JsonParse();
        start.JsonAlma(dovizTakip.this);

        CustomAdapter adapter = new CustomAdapter(this,start.kurlarim, images);

        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter);


        final degerCek pars = new degerCek();
        pars.JsonAlma(dovizTakip.this);
        final String dolarim = pars.aDolar();
        final String euro=pars.aEuro();
        //deneme.setText(pars.Adolarim());
          // deger.setText(dolarim);



        //degerSaklayici tut=new degerSaklayici(dolarim,euro);
        // spinner doları vs burada


        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                double dolarim = Double.parseDouble(pars.aDolar());
                double edtAlinan = (s.length() == 0 ? 0.0 : Double.parseDouble(edt.getText().toString()));
                edt2.setText(String.valueOf(dolarim * edtAlinan));


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pozisyon, long id) {
             clockwise(view);
                switch (pozisyon) {
                    case 0:
                      //  deger.setText("Dolar  :" + dolarim + "$");
                        edt.setText(dolarim);

                        break;
                    case 1:

                        EuroDD = true;

                        edt.setText(euro);
                        break;
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pozisyon, long id) {
                clockwise(view);
                switch (pozisyon) {

                    case 0:edt2.setText(dolarim);
                        break;
                    case 1:edt2.setText(euro);
                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Action Bar öğelerindeki basılmaları idare edelim
        switch (item.getItemId()) {
            case R.id.Güncelle:
                Toast.makeText(dovizTakip.this, "Guncelle", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_ayarlar:
                Toast.makeText(dovizTakip.this,"Ayarlar",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void clockwise(View view){
        ImageView image = (ImageView)findViewById(R.id.chanceIcon);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animes);
        image.startAnimation(animation);
    }


}