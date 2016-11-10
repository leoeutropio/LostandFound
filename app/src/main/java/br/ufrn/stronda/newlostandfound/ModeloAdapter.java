package br.ufrn.stronda.newlostandfound;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by STRONDA on 04/11/2016.
 */

public class ModeloAdapter extends ArrayAdapter<Modelolista> {
    private final Context context;
    private final ArrayList<Modelolista> elementos;

    public ModeloAdapter(Context context, ArrayList<Modelolista> elemento) {
        super(context, R.layout.objdes,elemento);
        this.context = context;
        this.elementos= elemento;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.objdes,parent,false);

        TextView descricaoTxt = (TextView) rowView.findViewById(R.id.descTxt);
        TextView localizacaoTxt = (TextView) rowView.findViewById(R.id.catTxt);
        TextView categoriaTxt = (TextView) rowView.findViewById(R.id.locTxt);
        CircleImageView imagem = (CircleImageView) rowView.findViewById(R.id.imgobj);

        descricaoTxt.setText(elementos.get(position).getNome());
        localizacaoTxt.setText(elementos.get(position).getCat());
        categoriaTxt.setText(elementos.get(position).getLoc());
        imagem.setImageResource(elementos.get(position).getImagem());


        return rowView;
    }

}
