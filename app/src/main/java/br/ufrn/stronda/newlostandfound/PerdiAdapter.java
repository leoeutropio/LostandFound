package br.ufrn.stronda.newlostandfound;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by STRONDA on 22/11/2016.
 */

public class PerdiAdapter extends ArrayAdapter<PerdiObjeto> {
    private final Context context;
    private final ArrayList<PerdiObjeto> elementos;

    public PerdiAdapter(Context context, ArrayList<PerdiObjeto> elemento) {
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

        descricaoTxt.setText(elementos.get(position).getDescricao());
        localizacaoTxt.setText(elementos.get(position).getCategoria());
        categoriaTxt.setText(elementos.get(position).getLocalizacao());

        return rowView;
    }
}
