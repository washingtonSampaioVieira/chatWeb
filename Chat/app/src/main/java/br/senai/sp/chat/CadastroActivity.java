package br.senai.sp.chat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.senai.sp.chat.Model.Usuario;
import br.senai.sp.chat.tasks.CadastrarUsuario;

public class CadastroActivity extends AppCompatActivity {

    private EditText txtNome;
    private EditText txtSenha;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        //instanciando as variaveis
        txtNome = findViewById(R.id.txt_nome);
        txtSenha = findViewById(R.id.txt_senha);
        btnCadastrar = findViewById(R.id.btn_cadastrar);


        //cadastrar
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = new Usuario();
                usuario.setNome(txtNome.getText().toString());
                usuario.setSenha(txtSenha.getText().toString());

                CadastrarUsuario cadastrar = new CadastrarUsuario(usuario);
                cadastrar.execute();

                Intent intentLogin = new Intent(CadastroActivity.this, MainActivity.class);
                startActivity(intentLogin);
                finish();
            }
        });
    }
}
