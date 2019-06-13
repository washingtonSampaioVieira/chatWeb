package br.senai.sp.chat.Model;

public class Mensagem {

    private int codMensagem;
    private String mensagem;

    public int getCodMensagem() {
        return codMensagem;
    }

    public void setCodMensagem(int codMensagem) {
        this.codMensagem = codMensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String toString() {
        return " >  " + mensagem;
    }
}
