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

import br.senai.sp.chat.MainActivity;
import br.senai.sp.chat.Model.Mensagem;
import br.senai.sp.chat.R;

public class CarregarMensagens extends AsyncTask {
    private MainActivity mainActivity;
    private List<Mensagem> listMensagem;
    private ProgressDialog progressDialog;
    private ArrayAdapter<Mensagem> adapter;
    private String dados = "";

    public CarregarMensagens(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(mainActivity);
        progressDialog.setTitle("Carregando dados.");
        progressDialog.setMessage("Carregando... Aguarde.");
        progressDialog.show();
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        try {
            URL url = new URL("http://10.107.134.3:8080/chat");
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            InputStream inputStream = conexao.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String linha = "";

            while (linha != null){
                linha = bufferedReader.readLine();
                dados = dados + linha;
            }

            Log.d("",dados);
            JSONArray jsonArray = new JSONArray(dados);
            listMensagem = new ArrayList<>();
            Mensagem mensagem;

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jo = (JSONObject) jsonArray.get(i);
                mensagem = new Mensagem();
                mensagem.setCodMensagem(jo.getInt("codMensagem"));
                mensagem.setMensagem(jo.getString("mensagem"));
                listMensagem.add(mensagem);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        adapter = new ArrayAdapter<>(mainActivity, android.R.layout.simple_list_item_1,listMensagem);
        MainActivity.listMensagens.setAdapter(adapter);

        progressDialog.dismiss();

    }
}


