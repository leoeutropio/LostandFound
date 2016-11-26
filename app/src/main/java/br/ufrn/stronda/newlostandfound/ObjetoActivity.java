package br.ufrn.stronda.newlostandfound;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class ObjetoActivity extends AppCompatActivity {

    //ESSA ACTIVITY FOI IMPLEMENTADA SÓ PARA EXIBIR O OBJETO DO LISTVIEW, SERÁ MAIS ÚTIL
    //QUANDO IMPLEMENTARMOS O BANCO


    TextView descricao,localizacao,categoria;
    CircleImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objeto);
        Bundle extras;
        String newString,newString1,newString2,imagemstring;
        int imagemint=0;
        descricao = (TextView) findViewById(R.id.descricaoobjeto);
        localizacao = (TextView) findViewById(R.id.localizacaoobjeto);
        categoria = (TextView) findViewById(R.id.categoriaobjeto);
        image = (CircleImageView) findViewById(R.id.imagemObjeto);


        if (savedInstanceState == null) {
            extras = getIntent().getExtras();
            if(extras == null){
                newString= null;
                newString1=null;
                newString2=null;
                imagemstring = null;
                imagemint = 0;

            }
            else{
                newString= extras.getString("descricao");
                newString1= extras.getString("categoria");
                newString2=extras.getString("localizacao");
                imagemstring = extras.getString("imagem");
                imagemint = extras.getInt("imagemint");


                if(imagemstring!=null){
                Glide.with(this).load(imagemstring).into(image);
                }
                if (imagemint!=0){
                    image.setImageResource(imagemint);
                }

                descricao.setText(newString);
                categoria.setText(newString1);
                localizacao.setText(newString2);

                descricao.setAllCaps(true);
                categoria.setAllCaps(true);
                localizacao.setAllCaps(true);

            }
        }



    }
}
