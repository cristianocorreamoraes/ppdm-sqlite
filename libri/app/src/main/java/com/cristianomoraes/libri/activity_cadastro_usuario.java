package com.cristianomoraes.libri;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cristianomoraes.libri.database.SQLHelper;
import com.cristianomoraes.libri.helpers.DataFormata;

public class activity_cadastro_usuario extends AppCompatActivity {

    private EditText txtNome;
    private EditText txtSobrenome;
    private EditText txtEmail;
    private EditText txtLogin;
    private EditText txtSenha;
    private Button btnCadastrarUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        this.txtNome = findViewById(R.id.txtNome);
        this.txtSobrenome = findViewById(R.id.txtSobrenome);
        this.txtEmail = findViewById(R.id.txtEmail);
        this.txtLogin = findViewById(R.id.txtLogin);
        this.txtSenha = findViewById(R.id.txtSenha);
        this.btnCadastrarUsuario = findViewById(R.id.btnCadastrarUsuario);

        this.btnCadastrarUsuario.setOnClickListener(view->{

            if(!validate()){
                Toast.makeText(activity_cadastro_usuario.this, R.string.fields_message, Toast.LENGTH_LONG).show();
                return;
            }

            AlertDialog dialog = new AlertDialog.Builder(activity_cadastro_usuario.this)
                    .setTitle(getString(R.string.create_user_message_title))
                    .setMessage(R.string.create_user_message)
                    .setPositiveButton(R.string.save, (dialog1, which)->{

                        String snome = this.txtNome.getText().toString();
                        String ssobreNome = this.txtSobrenome.getText().toString();
                        String semail = this.txtSenha.getText().toString();
                        String slogin = this.txtLogin.getText().toString();
                        String ssenha = this.txtSenha.getText().toString();
                        DataFormata sdata = new DataFormata();

                        DataFormata df = new DataFormata();
                        Log.d("TESTE", df.getDateFormat());

                        int cod_usuario = SQLHelper.getInstance(activity_cadastro_usuario.this)
                                .addUser(snome, ssobreNome, semail, slogin, ssenha, sdata.getDateFormat());

                        if (cod_usuario > 0)

                            Toast.makeText(activity_cadastro_usuario.this, R.string.saved, Toast.LENGTH_LONG).show();

//                            Intent intent  = new Intent(activity_cadastro_usuario.this, activity_feed.class);
//                            intent.putExtra("cod_usuario", cod_usuario);
//                            startActivity(intent);

                    })
                    .setNegativeButton(R.string.cancel, (dialog1, which)->{

                    }).create();

            dialog.show();

        });

    }

    private boolean validate(){

        return(
                !txtNome.getText().toString().isEmpty() &&
                !txtSobrenome.getText().toString().isEmpty() &&
                !txtEmail.getText().toString().isEmpty() &&
                !txtLogin.getText().toString().isEmpty() &&
                !txtSenha.getText().toString().isEmpty()
                );
    }

}