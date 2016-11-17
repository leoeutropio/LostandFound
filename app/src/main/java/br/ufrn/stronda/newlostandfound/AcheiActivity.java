package br.ufrn.stronda.newlostandfound;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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

public class AcheiActivity extends AppCompatActivity {
    Spinner catspn,locspn;
    EditText descricao;
    Button confirmar;
    AcheiObjeto acheiObjeto;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achei);


        descricao = (EditText) findViewById(R.id.descricaoA);
        confirmar = (Button) findViewById(R.id.acheiconfimarBtn);
        catspn = (Spinner) findViewById(R.id.acheicategoriaSpn);
        locspn = (Spinner) findViewById(R.id.acheilocalizacaoSpn);


        ArrayAdapter adaptercat = ArrayAdapter.createFromResource(this, R.array.itens, R.layout.spinner_item);
        adaptercat.setDropDownViewResource(R.layout.spinner_dropdown_item);
        catspn.setAdapter(adaptercat);


        ArrayAdapter adapterloc = ArrayAdapter.createFromResource(this, R.array.local, R.layout.spinner_item);
        adapterloc.setDropDownViewResource(R.layout.spinner_dropdown_item);
        locspn.setAdapter(adapterloc);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
            Log.d("google", "onAuthStateChanged:signed_in:" + user.getUid());
            final String name = user.getDisplayName();
            final String email = user.getEmail();
            final String userid = user.getUid();
            confirmar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDatabase.child("Usuários").child(userid).child("nome").setValue(name);
                    mDatabase.child("Usuários").child(userid).child("email").setValue(email);
                    novoObjeto(descricao.getText().toString(),catspn.getSelectedItem().toString() ,locspn.getSelectedItem().toString(),userid);
                    Toast.makeText(getBaseContext(),"Cadastrado com sucesso",Toast.LENGTH_SHORT).show();
                    finish();
                }
            });


        }
        else {
            // User is signed out
            Log.d("google", "onAuthStateChanged:signed_out");
        }

    }

    public void cancelar(View view) {
        this.finish();
    }

    private void novoObjeto(String descricao, String categoria,String localizacao,String userId) {
        AcheiObjeto acheiObjeto = new AcheiObjeto(descricao,categoria,localizacao);
        mDatabase.child("Usuários").child(userId).child("Objetos").child("Achados").setValue(acheiObjeto);
    }


}
