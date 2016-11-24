package br.ufrn.stronda.newlostandfound;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Comment;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class AcheiActivity extends AppCompatActivity {
    Spinner catspn,locspn;
    EditText descricao;
    Button confirmar;
    AcheiObjeto acheiObjeto;
    byte[] data1;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achei);

        //Associando os elementos da tela a variáveis na classe
        descricao = (EditText) findViewById(R.id.descricaoA);
        confirmar = (Button) findViewById(R.id.acheiconfimarBtn);
        catspn = (Spinner) findViewById(R.id.acheicategoriaSpn);
        locspn = (Spinner) findViewById(R.id.acheilocalizacaoSpn);


        //Criando um arrayadapter para dispor os elementos no spinner de categoria
        ArrayAdapter adaptercat = ArrayAdapter.createFromResource(this, R.array.itens, R.layout.spinner_item);
        adaptercat.setDropDownViewResource(R.layout.spinner_dropdown_item);
        catspn.setAdapter(adaptercat);

        //Criando um arrayadapter para dispor os elementos no spinner de categoria
        ArrayAdapter adapterloc = ArrayAdapter.createFromResource(this, R.array.local, R.layout.spinner_item);
        adapterloc.setDropDownViewResource(R.layout.spinner_dropdown_item);
        locspn.setAdapter(adapterloc);

        //Atribuindo a instância do banco de dados Firebase a variável do firebase na classe
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Obtendo o usuário que está logado atualmente no sistema e atribuindo a uma variável
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            //Possui um usuário logado no sistema
            Log.d("google", "onAuthStateChanged:signed_in:" + user.getUid());
            //Obtem os valores de nome,email e o id do usuário logado atualmente
            final String name = user.getDisplayName();
            final String email = user.getEmail();
            final String userid = user.getUid();

            //função vai executar quando o botão confirmar for clicado, pega os valores que estão nos spinners
            // e no campo de texto e vai cadastrar no banco de dados com as tags que estão abaixo, cada child é um nó
            // na tabela do banco.

            confirmar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDatabase.child("Usuarios").child(userid).child("nome").setValue(name);
                    mDatabase.child("Usuarios").child(userid).child("email").setValue(email);
                    //chama a função para cadastrar no banco
                    novoObjeto(descricao.getText().toString(),catspn.getSelectedItem().toString() ,locspn.getSelectedItem().toString(),userid);
                    //após cadastrar, gera um toast para informar que foi cadastrado no banco
                    Toast.makeText(getBaseContext(),"Cadastrado com sucesso",Toast.LENGTH_SHORT).show();
                    finish();
                }
            });

        }
        else {
            Log.d("google", "onAuthStateChanged:signed_out");
        }

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Aqui é tratado a parte de tirar foto do celular
        if (data!=null){
            Bundle bundle = data.getExtras();
            if(bundle != null){
                //Após o processo de tirar a foto, ela é colocada dentro do
                //CircleImageView para ser exibida
                Bitmap img = (Bitmap) bundle.get("data");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                img.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                data1 = baos.toByteArray();
                CircleImageView iv = (CircleImageView) findViewById(R.id.imgvw);
                iv.setImageBitmap(img);
            }
        }


    }



    public void cancelar(View view) {
        this.finish();
    }


    //É chamada apenas para cadastrar um objeto no banco.
    private void novoObjeto(String descricao, String categoria,String localizacao,String userId) {
        AcheiObjeto acheiObjeto = new AcheiObjeto(descricao,categoria,localizacao);
        //"setValue" coloca o valor que está no parâmetro, dentro do banco.
        mDatabase.child("Usuarios").child(userId).child("Objetos").child("Achados").push().setValue(acheiObjeto);
    }


}
