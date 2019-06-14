package br.senai.sp.chat.tasks;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.senai.sp.chat.ChatActivity;
import br.senai.sp.chat.Model.Mensagem;
import br.senai.sp.chat.Model.Usuario;

public class LogarUsuario extends AsyncTask{

    private ChatActivity chatActivity;
    private List<Usuario> listUsuarios;
    private ProgressDialog progressDialog;
    private ArrayAdapter<Mensagem> adapter;
    private String dados = "";
    private Usuario usuario;

    public LogarUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            URL url = new URL("http://10.107.144.36:8080/chat/usuarios/login/" + usuario.getNome() + "/" + usuario.getSenha());
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            InputStream inputStream = conexao.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String linha = "";

            while (linha != null){
                linha = bufferedReader.readLine();
                dados = dados + linha;
            }

            JSONObject jsonObject = new JSONObject(dados);

            usuario = new Usuario();
            usuario.setCodUsuario(jsonObject.getInt("codUsuario"));
            usuario.setNome(jsonObject.getString("nome"));
            usuario.setSenha(jsonObject.getString("senha"));




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return usuario;
    }
}
