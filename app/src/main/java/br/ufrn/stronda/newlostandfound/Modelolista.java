package br.ufrn.stronda.newlostandfound;

/**
 * Created by STRONDA on 04/11/2016.
 *
 * A classe ModeloAdapter extende ela pra fazer um ArrayList desse tipo
 */

public class Modelolista {
        private String nome,loc,cat;
        private int imagem;


        public Modelolista(String nom, int image,String loc, String cat){
            this.loc = loc;
            this.cat = cat;
            this.nome = nom;
            this.imagem = image;
        }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
}
