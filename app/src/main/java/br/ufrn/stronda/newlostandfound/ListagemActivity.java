package br.ufrn.stronda.newlostandfound;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListagemActivity extends AppCompatActivity {

    ArrayAdapter arrayAdapterA,arrayAdapterP;
    private ListView listaA,listaP;
    TabHost tabHost;
    CircleImageView imagem;
    String desca,loca,cata,descp,locp,catp;
    String  imagema;
    String nome,email;


    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem);

        imagem = (CircleImageView) findViewById(R.id.imgobj);

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


        //Obtém o listview para adicionar valores ao mesmo e depois disponibiliza na tela
        listaP = (ListView) findViewById(R.id.listObjetosPerdidos);





        //Função para detectar o toque em um item do listView


        listaP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(view.getContext(), ObjetoActivity.class);
                startActivity(intent);
            }
        });

        //Faz as cores das abas ficarem roxo para a aba não selecionada e cinza para a aba selecionada selecionada
        //antes dos eventos de troca de abas
        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#290884")); // unselected
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
            tv.setTextColor(Color.parseColor("#FFFFFF"));
            tv.setTextSize(15);
        }

        tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#1F046B")); // selected
        TextView tv = (TextView) tabHost.getCurrentTabView().findViewById(android.R.id.title); //for Selected Tab
        tv.setTextColor(Color.parseColor("#FFFFFF"));
        tv.setTextSize(15);


        //Faz com que as cores das abas fiquem roxo quando não está selecionada e cinza quando for selecionada durante
        //os eventos de troca de aba
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            @Override
            public void onTabChanged(String tabId) {

                for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
                    tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#290884")); // unselected
                    TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
                    tv.setTextColor(Color.parseColor("#FFFFFF"));
                    tv.setTextSize(15);
                }

                tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#1F046B")); // selected
                TextView tv = (TextView) tabHost.getCurrentTabView().findViewById(android.R.id.title); //for Selected Tab
                tv.setTextColor(Color.parseColor("#FFFFFF"));
                tv.setTextSize(15);

            }
        });




    }

    @Override
    protected void onStart() {
        super.onStart();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String userid = user.getUid();


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Usuarios");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final ArrayList<AcheiObjeto> objetos = new ArrayList<AcheiObjeto>();

                for( DataSnapshot dsp : dataSnapshot.getChildren() ){

                    for (DataSnapshot dspc : dsp.child("Objetos").child("Achados").getChildren()){

                            AcheiObjeto o = new AcheiObjeto();
                            o.setDescricao(dspc.child("descricao").getValue().toString());
                            o.setCategoria(dspc.child("categoria").getValue().toString());
                            o.setLocalizacao(dspc.child("localizacao").getValue().toString());

                            Log.d("ID", dspc.getKey());
                            Log.d("DESCRICAO", o.getDescricao());
                            Log.d("CATEGORIA", o.getCategoria());
                            Log.d("LOCALIZACAO", o.getLocalizacao());

                            objetos.add(o);
                    }

                }
                arrayAdapterA = new ModeloAdapter(ListagemActivity.this,objetos);
                listaA.setAdapter(arrayAdapterA);

                listaA.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        desca = objetos.get(position).getDescricao();
                        cata = objetos.get(position).getCategoria();
                        loca = objetos.get(position).getLocalizacao();
                        Intent intent = new Intent(view.getContext(), ObjetoActivity.class);
                        intent.putExtra("descricao",desca);
                        intent.putExtra("categoria",cata);
                        intent.putExtra("localizacao",loca);
                        intent.putExtra("imagemint",R.drawable.general);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference().child("Usuarios");
        ref1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final ArrayList<PerdiObjeto> objetosp = new ArrayList<PerdiObjeto>();

                for( DataSnapshot dsp1 : dataSnapshot.getChildren() ){

                    for (DataSnapshot dspc1 : dsp1.child("Objetos").child("Perdidos").getChildren()){

                            PerdiObjeto o = new PerdiObjeto();
                            o.setDescricao(dspc1.child("descricao").getValue().toString());
                            o.setCategoria(dspc1.child("categoria").getValue().toString());
                            o.setLocalizacao(dspc1.child("localizacao").getValue().toString());
                            o.setImagem(dspc1.child("imagem").getValue().toString());

                            imagema = dspc1.child("imagem").getValue().toString();
                            Log.d("ID",dspc1.getKey());

                            Log.d("DESCRICAO", o.getDescricao());
                            Log.d("CATEGORIA", o.getCategoria());
                            Log.d("LOCALIZACAO", o.getLocalizacao());

                            objetosp.add(o);
                    }

                }
                arrayAdapterP = new PerdiAdapter(ListagemActivity.this,objetosp);
                listaP.setAdapter(arrayAdapterP);
                listaP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        descp = objetosp.get(position).getDescricao();
                        catp = objetosp.get(position).getCategoria();
                        locp = objetosp.get(position).getLocalizacao();

                        Intent intent = new Intent(view.getContext(), ObjetoActivity.class);
                        intent.putExtra("descricao",descp);
                        intent.putExtra("categoria",catp);
                        intent.putExtra("localizacao",locp);
                        intent.putExtra("imagem",imagema);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


}
