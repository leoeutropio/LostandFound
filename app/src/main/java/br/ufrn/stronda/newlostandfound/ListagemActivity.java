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
    String adescric,acategoria,alocalizacao;
    String pdescric,pcategoria,plocalizacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem);


        //Associa o tab a classe e coloca para funcionar
        tabHost=(TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        //Adiciona um nome ao tab e disponibiliza na tela da activity
        TabHost.TabSpec abasa = tabHost.newTabSpec("Achados");
        abasa.setContent(R.id.Achados);
        abasa.setIndicator("Achados");
        tabHost.addTab(abasa);

        //Adiciona um nome ao tab e disponibiliza na tela da activity
        TabHost.TabSpec abasp = tabHost.newTabSpec("Perdidos");
        abasp.setContent(R.id.Perdidos);
        abasp.setIndicator("Perdidos");
        tabHost.addTab(abasp);

        //Obtém o listview para adicionar valores ao mesmo e depois disponibiliza na tela
        listaA = (ListView) findViewById(R.id.listObjetosAchados);
        ArrayAdapter arrayAdapterA = new ModeloAdapter(this, preencherInformacoes());
        listaA.setAdapter(arrayAdapterA);

        //Obtém o listview para adicionar valores ao mesmo e depois disponibiliza na tela
        listaP = (ListView) findViewById(R.id.listObjetosPerdidos);
        ArrayAdapter arrayAdapterP = new ModeloAdapter(this,preencherInformacoes1());
        listaP.setAdapter(arrayAdapterP);


        //Função para detectar o toque em um item do listView
        listaA.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), ObjetoActivity.class);
                startActivity(intent);
            }
        });

        //Faz as cores das abas ficarem roxo para a aba não selecionada e cinza para a aba selecionada selecionada
        //antes dos eventos de troca de abas
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


        //Faz com que as cores das abas fiquem roxo quando não está selecionada e cinza quando for selecionada durante
        //os eventos de troca de aba
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

    //Função teste só para encher o listView
    private ArrayList<Modelolista> preencherInformacoes() {
        ArrayList<Modelolista> descricao = new ArrayList<Modelolista>();

        Modelolista modelolista = new Modelolista(adescric,R.drawable.photoicon,alocalizacao,acategoria);
        descricao.add(modelolista);
        return descricao;

    }

    //Função teste só para encher o listView
    private ArrayList<Modelolista> preencherInformacoes1() {
        ArrayList<Modelolista> descricao = new ArrayList<Modelolista>();
        Modelolista modelolista = new Modelolista(pdescric,R.drawable.botaoachei,plocalizacao,pcategoria);
        descricao.add(modelolista);

        return descricao;
    }

}
