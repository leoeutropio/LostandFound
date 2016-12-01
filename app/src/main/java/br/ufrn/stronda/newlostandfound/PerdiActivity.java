package br.ufrn.stronda.newlostandfound;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.R.attr.bitmap;

public class PerdiActivity extends AppCompatActivity {
    Spinner pcatspn,plocspn;
    EditText descricao;
    Button confirmar;

    private DatabaseReference mDatabase;
    private FirebaseStorage storage;
    private StorageReference storageRef;
    public static final int IMAGEM_INTERNA = 12;
    String pathImg;
    String idPerdido;
    byte[] data1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perdi);



        //Associando os elementos da tela a variáveis na classe
        descricao = (EditText) findViewById(R.id.descricaoP);
        confirmar = (Button) findViewById(R.id.pokBtn);
        pcatspn = (Spinner) findViewById(R.id.perdicategoriaSpn);
        plocspn = (Spinner) findViewById(R.id.perdilocalizacaoSpn);

        //Criando um arrayadapter para dispor os elementos no spinner de categoria
        ArrayAdapter adaptercata = ArrayAdapter.createFromResource(this, R.array.itens, R.layout.spinner_item);
        adaptercata.setDropDownViewResource(R.layout.spinner_dropdown_item);
        pcatspn.setAdapter(adaptercata);

        //Criando um arrayadapter para dispor os elementos no spinner de categoria
        ArrayAdapter adapterloca = ArrayAdapter.createFromResource(this, R.array.local, R.layout.spinner_item);
        adapterloca.setDropDownViewResource(R.layout.spinner_dropdown_item);
        plocspn.setAdapter(adapterloca);

        //Atribuindo a instância do banco de dados Firebase a variável do firebase na classe
        mDatabase = FirebaseDatabase.getInstance().getReference();
        storage= FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        //storageRef= storage.getReferenceFromUrl("gs://achados-e-perdidos-5f077.appspot.com/");



    }



    //Função para escolher uma imagem a partir da galeria do celular
    public void pegafoto(View view) {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");
        startActivityForResult(i,IMAGEM_INTERNA);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String userid = user.getUid();
        final String name = user.getDisplayName();
        final String email = user.getEmail();

        //Aqui é tratado a parte da galeria do celular
        if(requestCode == IMAGEM_INTERNA){
            if(resultCode==RESULT_OK){
                final Uri imagemSelecionada = data.getData();

                DatabaseReference novoNodeRef = mDatabase.child("Usuarios").child(user.getUid()).child("Objetos").child("Perdidos").push();
                idPerdido = novoNodeRef.getKey();

                String[] colunas = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(imagemSelecionada,colunas,null,null,null);
                cursor.moveToFirst();

                int indexColuna = cursor.getColumnIndex(colunas[0]);
                pathImg = cursor.getString(indexColuna);
                cursor.close();

                //Após o processo de escolher a imagem, ela é colocada dentro do
                //CircleImageView para ser exibida
                Bitmap bitmap = BitmapFactory.decodeFile(pathImg);
                CircleImageView iv = (CircleImageView) findViewById(R.id.imgvw);
                Glide.with(this)
                        .load(imagemSelecionada)
                        .into(iv);
                ;
                confirmar.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        mDatabase.child("Usuarios").child(userid).child("nome").setValue(name);
                        mDatabase.child("Usuarios").child(userid).child("email").setValue(email);

                        storageRef.child("Usuarios").child(userid).child("Objetos").child("Perdidos").child(idPerdido).putFile(imagemSelecionada).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                storageRef.child("Usuarios").child(userid).child("Objetos").child("Perdidos").child(idPerdido).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        novoObjeto(descricao.getText().toString(),pcatspn.getSelectedItem().toString() ,plocspn.getSelectedItem().toString(),userid,uri.toString(),user.getDisplayName(),user.getEmail());
                                        Toast.makeText(PerdiActivity.this,"Cadastrado com sucesso",Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                });
                            }
                        });


                    }
                });



            }
        }

        //Aqui é tratado a parte de tirar foto do celular
        /*if (data!=null){
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
        }*/

    }

    public void cancelar(View view) {
    this.finish();
    }

    //É chamada apenas para cadastrar um objeto no banco.
    private void novoObjeto(String descricao, String categoria,String localizacao,String userId, String imagem,String nome, String email) {
        PerdiObjeto perdiObjeto = new PerdiObjeto(descricao,categoria,localizacao,imagem,nome,email,idPerdido);
        //"setValue" coloca o valor que está no parâmetro, dentro do banco.
        mDatabase.child("Usuarios").child(userId).child("Objetos").child("Perdidos").child(idPerdido).setValue(perdiObjeto);
    }
}
