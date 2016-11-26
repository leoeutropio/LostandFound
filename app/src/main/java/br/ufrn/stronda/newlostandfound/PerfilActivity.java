package br.ufrn.stronda.newlostandfound;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.vision.text.Text;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class PerfilActivity extends AppCompatActivity {
    CircleImageView imagem;
    TextView nome,emai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        nome = (TextView) findViewById(R.id.nomeperfil);
        emai = (TextView) findViewById(R.id.emailperfil);
        imagem = (CircleImageView) findViewById(R.id.fotoperfil);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

            Log.d("google", "onAuthStateChanged:signed_in:" + user.getUid());
            //Obtem os valores de nome,email e a imagem do usuário logado atualmente
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();
            //nome.setText(name);
            //Biblioteca externa que faz com que se pegue o URL
            //da foto que o google disponibiliza e coloque no local que escolher
            nome.setText("Nome: "+name);
            emai.setText("Email: "+email);
            Glide.with(this)
                    .load(photoUrl)
                    .into(imagem);

            nome.setAllCaps(true);
            emai.setAllCaps(true);
        }
    }
}
