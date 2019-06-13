package br.senai.sp.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import br.senai.sp.chat.Model.Mensagem;
import br.senai.sp.chat.tasks.CarregarMensagens;
import br.senai.sp.chat.tasks.GravarMensagens;

public class MainActivity extends AppCompatActivity {

    public static ListView listMensagens;
    private EditText txtMensagem;
    private Button btnEnviar;
    private Mensagem mensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // instanciando os elementos xml
        listMensagens = findViewById(R.id.list_chat);
        txtMensagem = findViewById(R.id.txt_mensagem);
        btnEnviar = findViewById(R.id.btn_enviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensagem = new Mensagem();
                mensagem.setMensagem(txtMensagem.getText().toString());
                GravarMensagens gravar = new GravarMensagens(mensagem);
                gravar.execute();
                onResume();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        CarregarMensagens carregar = new CarregarMensagens(this);
        carregar.execute();
        txtMensagem.setText("")
        ;
    }
}
