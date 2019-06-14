package br.senai.sp.chat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

import br.senai.sp.chat.Model.Usuario;
import br.senai.sp.chat.tasks.LogarUsuario;

public class MainActivity extends AppCompatActivity {

    private EditText txtNome;
    private EditText txtSenha;
    private Button btnLogin;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instanciando os objetos
        txtNome = findViewById(R.id.txt_nome);
        txtSenha = findViewById(R.id.txt_senha);
        btnLogin = findViewById(R.id.btn_login);
        btnCadastrar = findViewById(R.id.btn_novo_cadastro);

        //logar no chat
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = new Usuario();
                usuario.setNome(txtNome.getText().toString());
                usuario.setSenha(txtSenha.getText().toString());
                LogarUsuario logar = new LogarUsuario(usuario);
                logar.execute();

                try {
                    Usuario usuarioRetorno = (Usuario) logar.get();


                    if(usuarioRetorno.getCodUsuario() != 0){
                        Intent intentChat = new Intent(MainActivity.this, ChatActivity.class);
                        intentChat.putExtra("usuario", usuarioRetorno);
                        startActivity(intentChat);
                    }

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        //cadastrar
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCadastrar = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(intentCadastrar);
            }
        });

    }
}
