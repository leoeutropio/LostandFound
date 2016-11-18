package br.ufrn.stronda.newlostandfound;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ObjetoActivity extends AppCompatActivity {

    //ESSA ACTIVITY FOI IMPLEMENTADA SÓ PARA EXIBIR O OBJETO DO LISTVIEW, SERÁ MAIS ÚTIL
    //QUANDO IMPLEMENTARMOS O BANCO


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objeto);
    }
}
