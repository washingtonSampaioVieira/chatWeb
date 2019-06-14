package br.senai.sp.chat.tasks;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONStringer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import br.senai.sp.chat.Model.Mensagem;

public class GravarMensagens extends AsyncTask{

    private Mensagem mensagem;

    public GravarMensagens(Mensagem mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        JSONStringer jsMensagem = new JSONStringer();

        try {


            jsMensagem.object();
            jsMensagem.key("mensagem").value(mensagem.getMensagem());
            jsMensagem.key("usuario").object().key("codUsuario").value(mensagem.getCodUsuario()).endObject();
            jsMensagem.endObject();

            URL url = new URL("http://10.107.144.36:8080/chat");

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            conexao.setRequestProperty("Content-Type", "application/json");
            conexao.setRequestProperty("Accept", "application/json");
            conexao.setRequestMethod("POST");

            conexao.setDoInput(true);

            PrintStream outputStream = new PrintStream(conexao.getOutputStream());
            outputStream.print(jsMensagem);

            conexao.connect();

            Scanner scanner = new Scanner(conexao.getInputStream());
            String resposta = scanner.nextLine();

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
