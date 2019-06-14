package br.senai.sp.chat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

import br.senai.sp.chat.Model.Mensagem;
import br.senai.sp.chat.Model.Usuario;
import br.senai.sp.chat.tasks.CarregarMensagens;
import br.senai.sp.chat.tasks.GravarMensagens;

public class ChatActivity extends AppCompatActivity {

    public static ListView listMensagens;
    private EditText txtMensagem;
    private Button btnEnviar;
    private Mensagem mensagem;
    private ArrayAdapter<Mensagem> adapter;
    private ArrayList<Mensagem> listMensagem;
    private Timer timer;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Intent intent = getIntent();
        usuario = (Usuario) intent.getSerializableExtra("usuario");

        // instanciando os elementos xml
        listMensagens = findViewById(R.id.list_chat);
        txtMensagem = findViewById(R.id.txt_mensagem);
        btnEnviar = findViewById(R.id.btn_enviar);

        //enviar mensagem
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensagem = new Mensagem();
                mensagem.setMensagem(txtMensagem.getText().toString());
                GravarMensagens gravar = new GravarMensagens(mensagem);
                gravar.execute();
                txtMensagem.setText("");
                onResume();
            }
        });

        //temporizador para atualizar
        int delay = 10000;   // delay de 10 seg.
        int interval = 5000;  // intervalo de 5 seg.
        timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                CarregarMensagens carregar = new CarregarMensagens(ChatActivity.this);
                carregar.execute();
            }
        }, delay, interval);


    }

    @Override
    protected void onResume() {
        super.onResume();

        CarregarMensagens carregar = new CarregarMensagens(this);
        carregar.execute();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        timer.cancel();
        finish();
    }
}
