package br.ufrn.stronda.newlostandfound;

/**
 * Created by STRONDA on 17/11/2016.
 *
 * Objeto criado para facilitar a inserção dos dados no banco de dados
 */


public class AcheiObjeto {

    public String descricao;
    public String categoria;
    public String localizacao;
    public String nome;
    public String email;
    public String key;

    public AcheiObjeto() {
    }

    public AcheiObjeto(String descricao, String categoria,String localizacao, String nome, String email,String key) {
        this.descricao = descricao;
        this.categoria = categoria;
        this.localizacao = localizacao;
        this.nome = nome;
        this.email = email;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
