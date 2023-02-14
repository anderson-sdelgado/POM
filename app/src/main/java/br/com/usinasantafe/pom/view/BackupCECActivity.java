package br.com.usinasantafe.pom.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pom.POMContext;
import br.com.usinasantafe.pom.R;
import br.com.usinasantafe.pom.model.bean.variaveis.CECBean;
import br.com.usinasantafe.pom.model.dao.LogProcessoDAO;

public class BackupCECActivity extends ActivityGeneric {

    private int contador;
    private List<CECBean> cecList;
    private TextView textViewBkpBoletim;
    private POMContext pomContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backup_cec);

        pomContext = (POMContext) getApplication();

        textViewBkpBoletim = findViewById(R.id.textViewBkpBoletim);
        Button buttonAntBkpBoletim = findViewById(R.id.buttonAntBkpBoletim);
        Button buttonProxBkpBoletim = findViewById(R.id.buttonProxBkpBoletim);
        Button buttonRetornarBkpBoletim = findViewById(R.id.buttonRetornarBkpBoletim);

        LogProcessoDAO.getInstance().insertLogProcesso("cecList = pomContext.getCecCTR().cecListDesc();\n" +
                "        contador = cecList.size() - 1;\n" +
                "        CECBean cecBean = cecList.get(contador);\n" +
                "        textViewBkpBoletim.setText(visBoletim(cecBean));", getLocalClassName());

        cecList = pomContext.getCecCTR().cecListDesc();
        contador = cecList.size() - 1;
        CECBean cecBean = cecList.get(contador);
        textViewBkpBoletim.setText(visBoletim(cecBean));

        buttonAntBkpBoletim.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("if(contador < cecList.size() - 1){\n" +
                        "                    contador = contador + 1;\n" +
                        "                }\n" +
                        "                CECBean cecBean = cecList.get(contador);\n" +
                        "                textViewBkpBoletim.setText(visBoletim(cecBean));", getLocalClassName());

                if(contador < cecList.size() - 1){
                    contador = contador + 1;
                }

                CECBean cecBean = cecList.get(contador);
                textViewBkpBoletim.setText(visBoletim(cecBean));

            }
        });

        buttonProxBkpBoletim.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("if(contador > 0){\n" +
                        "                    contador = contador - 1;\n" +
                        "                }\n" +
                        "                CECBean cecBean = cecList.get(contador);\n" +
                        "                textViewBkpBoletim.setText(visBoletim(cecBean));", getLocalClassName());

                if(contador > 0){
                    contador = contador - 1;
                }

                CECBean cecBean = cecList.get(contador);
                textViewBkpBoletim.setText(visBoletim(cecBean));

            }
        });

        buttonRetornarBkpBoletim.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonRetornarBkpBoletim.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                Intent it = new Intent(BackupCECActivity.this, MenuCertifActivity.class);", getLocalClassName());
                Intent it = new Intent(BackupCECActivity.this, MenuCertifActivity.class);
                startActivity(it);
                finish();

            }
        });

    }

    public String visBoletim(CECBean cecBean){

        String retorno = "";

        int analisar = (int) cecBean.getPossuiSorteioCEC().longValue();

        LogProcessoDAO.getInstance().insertLogProcesso("    public String visBoletim(CECBean cecBean){\n" +
                "        String retorno = \"\";\n" +
                "        int analisar = (int) cecBean.getPossuiSorteioCEC().longValue();", getLocalClassName());

        if(analisar == 0){

            retorno = retorno + "NÃO FOI SORTEADO \n";
            retorno = retorno + "PARA ANALISE! \n";
            retorno = retorno + "PESO LIQ:  "  + cecBean.getPesoLiquidoCEC() + "\n";
            retorno = retorno + "---------------- \n";
            retorno = retorno + "" + cecBean.getDthrEntradaCEC() + " \n";

        }
        else if(analisar == 1){

            if((cecBean.getUnidadeSorteada1CEC() != 0) &&
                    (cecBean.getUnidadeSorteada2CEC() != 0) &&
                    (cecBean.getUnidadeSorteada3CEC() != 0)){

                retorno = retorno + "Cargas Sorteadas \n";
                retorno = retorno + "" + cecBean.getUnidadeSorteada1CEC() + "       " + cecBean.getUnidadeSorteada2CEC() + " \n";
                retorno = retorno + "CEC: " + cecBean.getCecSorteado1CEC() + "  " + cecBean.getCecSorteado2CEC() + " \n";
                retorno = retorno + "Frente: " + cecBean.getCodFrenteCEC() + " \n";
                retorno = retorno + "Peso Liq:  "  + cecBean.getPesoLiquidoCEC() + " \n";
                retorno = retorno + "" + cecBean.getDthrEntradaCEC() + " \n";

            }
            else if((cecBean.getUnidadeSorteada1CEC() == 0) &&
                    (cecBean.getUnidadeSorteada2CEC() != 0) &&
                    (cecBean.getUnidadeSorteada3CEC() != 0)){

                retorno = retorno + "Cargas Sorteadas \n";
                retorno = retorno + "" + cecBean.getUnidadeSorteada2CEC() + "       " + cecBean.getUnidadeSorteada3CEC() + " \n";
                retorno = retorno + "CEC: " + cecBean.getCecSorteado2CEC() + "  " + cecBean.getCecSorteado3CEC() + " \n";
                retorno = retorno + "Frente: " + cecBean.getCodFrenteCEC() + " \n";
                retorno = retorno + "Peso Liq:  "  + cecBean.getPesoLiquidoCEC() + " \n";
                retorno = retorno + "" + cecBean.getDthrEntradaCEC() + " \n";

            }
            else if((cecBean.getUnidadeSorteada1CEC() != 0) &&
                    (cecBean.getUnidadeSorteada2CEC() == 0) &&
                    (cecBean.getUnidadeSorteada3CEC() != 0)){

                retorno = retorno + "Cargas Sorteadas \n";
                retorno = retorno + "" + cecBean.getUnidadeSorteada1CEC() + "       " + cecBean.getUnidadeSorteada3CEC() + " \n";
                retorno = retorno + "CEC: " + cecBean.getCecSorteado1CEC() + "  " + cecBean.getCecSorteado3CEC() + " \n";
                retorno = retorno + "Frente: " + cecBean.getCodFrenteCEC() + " \n";
                retorno = retorno + "Peso Liq:  "  + cecBean.getPesoLiquidoCEC() + " \n";
                retorno = retorno + "" + cecBean.getDthrEntradaCEC() + " \n";

            }
            else if((cecBean.getUnidadeSorteada1CEC() != 0) &&
                    (cecBean.getUnidadeSorteada2CEC() != 0) &&
                    (cecBean.getUnidadeSorteada3CEC() == 0)){

                retorno = retorno + "Cargas Sorteadas \n";
                retorno = retorno + "" + cecBean.getUnidadeSorteada1CEC() + "       " + cecBean.getUnidadeSorteada2CEC() + " \n";
                retorno = retorno + "CEC: " + cecBean.getCecSorteado1CEC() + "  " + cecBean.getCecSorteado2CEC() + " \n";
                retorno = retorno + "Frente: " + cecBean.getCodFrenteCEC() + " \n";
                retorno = retorno + "Peso Liq:  "  + cecBean.getPesoLiquidoCEC() + " \n";
                retorno = retorno + "" + cecBean.getDthrEntradaCEC() + " \n";

            }
            else if((cecBean.getUnidadeSorteada1CEC() != 0) &&
                    (cecBean.getUnidadeSorteada2CEC() == 0) &&
                    (cecBean.getUnidadeSorteada3CEC() == 0)){

                retorno = retorno + "Cargas Sorteadas \n";
                retorno = retorno + ""+ cecBean.getUnidadeSorteada1CEC() + " \n";
                retorno = retorno + "CEC: " + cecBean.getCecSorteado1CEC() + " \n";
                retorno = retorno + "Frente: " + cecBean.getCodFrenteCEC() + " \n";
                retorno = retorno + "Peso Liq:  "  + cecBean.getPesoLiquidoCEC() + " \n";
                retorno = retorno + "" + cecBean.getDthrEntradaCEC() + " \n";

            }
            else if((cecBean.getUnidadeSorteada1CEC() == 0) &&
                    (cecBean.getUnidadeSorteada2CEC() != 0) &&
                    (cecBean.getUnidadeSorteada3CEC() == 0)){

                retorno = retorno + "Cargas Sorteadas \n";
                retorno = retorno + ""+ cecBean.getUnidadeSorteada2CEC() + " \n";
                retorno = retorno + "CEC: " + cecBean.getCecSorteado2CEC() + " \n";
                retorno = retorno + "Frente: " + cecBean.getCodFrenteCEC() + " \n";
                retorno = retorno + "Peso Liq:  "  + cecBean.getPesoLiquidoCEC() + " \n";
                retorno = retorno + "" + cecBean.getDthrEntradaCEC() + " \n";

            }
            else if((cecBean.getUnidadeSorteada1CEC() == 0) &&
                    (cecBean.getUnidadeSorteada2CEC() == 0) &&
                    (cecBean.getUnidadeSorteada3CEC() != 0)){
                
                retorno = retorno + "Cargas Sorteadas \n";
                retorno = retorno + ""+ cecBean.getUnidadeSorteada3CEC() + " \n";
                retorno = retorno + "CEC: " + cecBean.getCecSorteado3CEC() + " \n";
                retorno = retorno + "Frente: " + cecBean.getCodFrenteCEC() + " \n";
                retorno = retorno + "Peso Liq:  "  + cecBean.getPesoLiquidoCEC() + " \n";
                retorno = retorno + "" + cecBean.getDthrEntradaCEC() + " \n";

            }

        }

        return retorno;

    }

    public void onBackPressed()  {
    }

}