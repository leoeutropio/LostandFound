package br.ufrn.stronda.newlostandfound;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AcheiActivity extends AppCompatActivity implements View.OnClickListener {
    Spinner catspn,locspn;
    EditText descricao;
    Button confirmar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achei);

        descricao = (EditText) findViewById(R.id.descricaoA);
        confirmar = (Button) findViewById(R.id.acheiconfimarBtn);
        catspn = (Spinner) findViewById(R.id.acheicategoriaSpn);
        locspn = (Spinner) findViewById(R.id.acheilocalizacaoSpn);


        confirmar.setOnClickListener(this);

        ArrayAdapter adaptercat = ArrayAdapter.createFromResource(this, R.array.itens, R.layout.spinner_item);
        adaptercat.setDropDownViewResource(R.layout.spinner_dropdown_item);
        catspn.setAdapter(adaptercat);


        ArrayAdapter adapterloc = ArrayAdapter.createFromResource(this, R.array.local, R.layout.spinner_item);
        adapterloc.setDropDownViewResource(R.layout.spinner_dropdown_item);
        locspn.setAdapter(adapterloc);


    }

    public void cancelar(View view) {
        this.finish();
    }

    @Override
    public void onClick(View v) {
        if(v == confirmar){
            SharedPreferences prefs = getSharedPreferences("objetosA", Context.MODE_PRIVATE);
            SharedPreferences.Editor ed = prefs.edit();
            ed.putString("acheidescricao",descricao.getText().toString());
            ed.putString("acheicategoriaspn", catspn.getSelectedItem().toString());
            ed.putString("acheilocalizacaospn", locspn.getSelectedItem().toString());
            ed.apply();
            Toast.makeText(getBaseContext(),"Cadastrado com sucesso",Toast.LENGTH_SHORT).show();
            this.finish();
        }

    }
}
