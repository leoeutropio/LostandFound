package br.ufrn.stronda.newlostandfound;

/**
 * Created by STRONDA on 17/11/2016.
 */

public class AcheiObjeto {

    public String descricao;
    public String categoria;
    public String localizacao;


    public AcheiObjeto() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public AcheiObjeto(String descricao, String categoria,String localizacao) {
        this.descricao = descricao;
        this.categoria = categoria;
        this.localizacao = localizacao;
    }



    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }



}
