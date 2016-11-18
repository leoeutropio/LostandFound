package br.ufrn.stronda.newlostandfound;

/**
 * Created by STRONDA on 17/11/2016.
 *
 * Objeto criado para facilitar a inserção dos dados no banco de dados
 */

public class PerdiObjeto {

    public String descricao;
    public String categoria;
    public String localizacao;


        public PerdiObjeto(){

        }

        public PerdiObjeto(String descricao, String categoria,String localizacao){
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
