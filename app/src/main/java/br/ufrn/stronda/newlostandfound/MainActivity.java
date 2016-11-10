package br.ufrn.stronda.newlostandfound;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    Button login;
    EditText senha,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        login = (Button) findViewById(R.id.loginBtn);
        email = (EditText) findViewById(R.id.emailTxt);
        senha = (EditText) findViewById(R.id.passwordTxt);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this,MenuActivity.class);
                    startActivity(intent);
            }
        });


    }

    public void cadastro(View view) {
        Intent intent = new Intent(this,CadastroActivity.class);
        startActivity(intent);
    }

}
