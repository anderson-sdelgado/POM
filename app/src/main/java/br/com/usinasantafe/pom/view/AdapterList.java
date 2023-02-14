package br.com.usinasantafe.pom.view;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.usinasantafe.pom.R;

/**
 * Created by anderson on 19/10/2015.
 */
public class AdapterList extends BaseAdapter {

    private ArrayList<String> itens;
    private LayoutInflater layoutInflater;

    public AdapterList(Context context, ArrayList<String> itens) {

        this.itens = itens;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.activity_item_lista, null);
        TextView textView = convertView.findViewById(R.id.textViewItemList);
        textView.setText(itens.get(position));
        if(itens.get(position).equals("CERTIFICADO")
                || itens.get(position).equals("MOTIVOS PARADA")
                || itens.get(position).equals("TROCA MOTORISTA")
                || itens.get(position).equals("CARREG REST/AGUA")
                || itens.get(position).equals("VOLTAR AO TRABALHO")
                || itens.get(position).equals("TROCA DE FUNCAO")
                || itens.get(position).equals("VERIFICAR LEIRA")){
            textView.setTypeface(null, Typeface.BOLD);
        }
        return convertView;
    }
}
