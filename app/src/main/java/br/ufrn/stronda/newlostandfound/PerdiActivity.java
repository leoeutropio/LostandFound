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
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class PerdiActivity extends AppCompatActivity {
    Spinner pcatspn,plocspn;
    EditText descricao;
    Button confirmar;
    public static final int IMAGEM_INTERNA = 12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perdi);

        descricao = (EditText) findViewById(R.id.descricaoP);
        confirmar = (Button) findViewById(R.id.pokBtn);

        pcatspn = (Spinner) findViewById(R.id.perdicategoriaSpn);
        plocspn = (Spinner) findViewById(R.id.perdilocalizacaoSpn);

        ArrayAdapter adaptercata = ArrayAdapter.createFromResource(this, R.array.itens, R.layout.spinner_item);
        adaptercata.setDropDownViewResource(R.layout.spinner_dropdown_item);
        pcatspn.setAdapter(adaptercata);

        ArrayAdapter adapterloca = ArrayAdapter.createFromResource(this, R.array.local, R.layout.spinner_item);
        adapterloca.setDropDownViewResource(R.layout.spinner_dropdown_item);
        plocspn.setAdapter(adapterloca);

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences("objetosP", Context.MODE_PRIVATE);
                SharedPreferences.Editor ed = prefs.edit();
                ed.putString("perdidescricao",descricao.getText().toString());
                ed.putString("perdicategoriaspn", pcatspn.getSelectedItem().toString());
                ed.putString("perdilocalizacaospn", plocspn.getSelectedItem().toString());
                ed.apply();
                Toast.makeText(getBaseContext(),"Cadastrado com sucesso",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }




    public void tirarfoto(View view) {
        Intent i = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(i,0);
    }

    public void pegafoto(View view) {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");
        startActivityForResult(i,IMAGEM_INTERNA);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == IMAGEM_INTERNA){
            if(resultCode==RESULT_OK){
                Uri imagemSelecionada = data.getData();

                String[] colunas = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(imagemSelecionada,colunas,null,null,null);
                cursor.moveToFirst();

                int indexColuna = cursor.getColumnIndex(colunas[0]);
                String pathImg = cursor.getString(indexColuna);
                cursor.close();

                Bitmap bitmap = BitmapFactory.decodeFile(pathImg);
                CircleImageView iv = (CircleImageView) findViewById(R.id.imgvw);
                iv.setImageBitmap(bitmap);
            }
        }

        if (data!=null){
            Bundle bundle = data.getExtras();
            if(bundle != null){
                Bitmap img = (Bitmap) bundle.get("data");
                CircleImageView iv = (CircleImageView) findViewById(R.id.imgvw);
                iv.setImageBitmap(img);
            }
        }


    }

    public void cancelar(View view) {
    this.finish();
    }
}
