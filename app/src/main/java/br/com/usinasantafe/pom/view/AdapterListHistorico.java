package br.com.usinasantafe.pom.view;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pom.R;
import br.com.usinasantafe.pom.control.MecanicoCTR;
import br.com.usinasantafe.pom.control.MotoMecFertCTR;
import br.com.usinasantafe.pom.model.bean.estaticas.AtividadeBean;
import br.com.usinasantafe.pom.model.bean.estaticas.ItemOSMecanBean;
import br.com.usinasantafe.pom.model.bean.variaveis.ApontMecanBean;

/**
 * Created by anderson on 08/03/2018.
 */

public class AdapterListHistorico extends BaseAdapter {

    private List itens;
    private LayoutInflater layoutInflater;
    private TextView textViewHistOS;
    private TextView textViewHistItem;
    private TextView textViewHistStatus;
    private TextView textViewHistHorarioInicial;
    private TextView textViewHistHorarioFinal;

    public AdapterListHistorico(Context context, List itens) {
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
    public View getView(int position, View view, ViewGroup parent) {

        view = layoutInflater.inflate(R.layout.activity_item_historico, null);
        textViewHistOS = view.findViewById(R.id.textViewHistOS);
        textViewHistItem = view.findViewById(R.id.textViewHistItem);
        textViewHistStatus = view.findViewById(R.id.textViewHistStatus);
        textViewHistHorarioInicial = view.findViewById(R.id.textViewHistHorarioInicial);
        textViewHistHorarioFinal = view.findViewById(R.id.textViewHistHorarioFinal);

        ApontMecanBean apontMecanBean  = (ApontMecanBean) itens.get(position);
        textViewHistOS.setText("NRO OS: " + apontMecanBean.getOsApontMecan());
        descrItem(apontMecanBean.getOsApontMecan(), apontMecanBean.getItemOSApontMecan());
        horarioInicial(apontMecanBean.getDthrInicialApontMecan());
        if(apontMecanBean.getStatusApontMecan() < 3){
            textViewHistStatus.setText("ABERTO");
            textViewHistStatus.setTextColor(Color.RED);
        }
        else{
            textViewHistStatus.setText("FECHADO");
            textViewHistStatus.setTextColor(Color.BLUE);
            horarioFinal(apontMecanBean.getDthrFinalApontMecan());
        }

        return view;
    }

    public void descrItem(Long nroOS, Long seqItemOS){
        MecanicoCTR mecanicoCTR = new MecanicoCTR();
        if(mecanicoCTR.verItemOSMecan(nroOS, seqItemOS)) {
            ItemOSMecanBean itemOSMecanBean = mecanicoCTR.getItemOSMecan(nroOS, seqItemOS);
            textViewHistItem.setText("ITEM: " + itemOSMecanBean.getSeqItemOS() + " - "
                    + mecanicoCTR.getServico(itemOSMecanBean.getIdServItemOS()).getDescrServico() + " - "
                    + mecanicoCTR.getComponente(itemOSMecanBean.getIdCompItemOS()).getCodComponente() + " - "
                    + mecanicoCTR.getComponente(itemOSMecanBean.getIdCompItemOS()).getDescrComponente());
        } else {
            textViewHistItem.setText("Item: " + seqItemOS);
        }
    }

    public void horarioInicial(String dataHora){
        textViewHistHorarioInicial.setText("HORÁRIO INICIAL: " + dataHora.substring(11));
    }

    public void horarioFinal(String dataHora){
        textViewHistHorarioFinal.setText("HORÁRIO FINAL: " + dataHora.substring(11));
    }

}
