package br.ufrn.stronda.newlostandfound;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.R.id.tabhost;

public class ListagemActivity extends AppCompatActivity {

    private ListView listaA,listaP;
    TabHost tabHost;
    TabHost.TabSpec abas;
    String adescric,acategoria,alocalizacao;
    String pdescric,pcategoria,plocalizacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem);

        SharedPreferences prefsa = getSharedPreferences("objetosA", Context.MODE_PRIVATE);
        SharedPreferences prefsp = getSharedPreferences("objetosP", Context.MODE_PRIVATE);

        adescric = prefsa.getString("acheidescricao","");
        acategoria = prefsa.getString("acheicategoriaspn","");
        alocalizacao = prefsa.getString("acheilocalizacaospn","");

        pdescric = prefsp.getString("perdidescricao","");
        pcategoria = prefsp.getString("perdicategoriaspn","");
        plocalizacao = prefsp.getString("perdilocalizacaospn","");

        tabHost=(TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        abas = tabHost.newTabSpec("Achados");
        abas.setContent(R.id.Achados);
        abas.setIndicator("Achados");
        tabHost.addTab(abas);

        TabHost.TabSpec abas = tabHost.newTabSpec("Perdidos");
        abas.setContent(R.id.Perdidos);
        abas.setIndicator("Perdidos");
        tabHost.addTab(abas);


        listaA = (ListView) findViewById(R.id.listObjetosAchados);
        ArrayAdapter arrayAdapterA = new ModeloAdapter(this, preencherInformacoes());
        listaA.setAdapter(arrayAdapterA);


        listaP = (ListView) findViewById(R.id.listObjetosPerdidos);
        ArrayAdapter arrayAdapterP = new ModeloAdapter(this,preencherInformacoes1());
        listaP.setAdapter(arrayAdapterP);


        listaA.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), ObjetoActivity.class);
                startActivity(intent);
            }
        });


        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#270A2B")); // unselected
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
            tv.setTextColor(Color.parseColor("#9c7b00"));
            tv.setTextSize(15);
        }

        tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#C0C0C0")); // selected
        TextView tv = (TextView) tabHost.getCurrentTabView().findViewById(android.R.id.title); //for Selected Tab
        tv.setTextColor(Color.parseColor("#9c7b00"));
        tv.setTextSize(15);



        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            @Override
            public void onTabChanged(String tabId) {

                for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
                    tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#270A2B")); // unselected
                    TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
                    tv.setTextColor(Color.parseColor("#9c7b00"));
                    tv.setTextSize(15);
                }

                tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#C0C0C0")); // selected
                TextView tv = (TextView) tabHost.getCurrentTabView().findViewById(android.R.id.title); //for Selected Tab
                tv.setTextColor(Color.parseColor("#9c7b00"));
                tv.setTextSize(15);

            }
        });
    }


    private ArrayList<Modelolista> preencherInformacoes() {
        ArrayList<Modelolista> descricao = new ArrayList<Modelolista>();

        Modelolista modelolista = new Modelolista(adescric,R.drawable.photoicon,alocalizacao,acategoria);
        descricao.add(modelolista);
        return descricao;

    }


    private ArrayList<Modelolista> preencherInformacoes1() {
        ArrayList<Modelolista> descricao = new ArrayList<Modelolista>();
        Modelolista modelolista = new Modelolista(pdescric,R.drawable.botaoachei,plocalizacao,pcategoria);
        descricao.add(modelolista);

        return descricao;
    }

}
