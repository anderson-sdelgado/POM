package br.com.usinasantafe.pom.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.usinasantafe.pom.POMContext;
import br.com.usinasantafe.pom.R;
import br.com.usinasantafe.pom.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pom.util.EnvioDadosServ;
import br.com.usinasantafe.pom.util.Tempo;

public class MenuPrincActivity extends ActivityGeneric {

    private POMContext pomContext;
    private ListView listViewAtiv;

    private TextView textViewProcessoNormal;
    private TextView textViewDataHora;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_princ);

        pomContext = (POMContext) getApplication();
        textViewProcessoNormal = findViewById(R.id.textViewProcessoNormal);
        textViewDataHora = findViewById(R.id.textViewDataHora);

        LogProcessoDAO.getInstance().insertLogProcesso("customHandler.postDelayed(updateTimerThread, 0);", getLocalClassName());
        customHandler.postDelayed(updateTimerThread, 0);

        if (Tempo.getInstance().verDthrServ(pomContext.getConfigCTR().getConfig().getDtServConfig())) {
            LogProcessoDAO.getInstance().insertLogProcesso("if (Tempo.getInstance().verDthrServ(pomContext.getConfigCTR().getConfig().getDtServConfig())) {\n" +
                    "            pomContext.getConfigCTR().setDifDthrConfig(0L);", getLocalClassName());
            pomContext.getConfigCTR().setDifDthrConfig(0L);
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("else {", getLocalClassName());
            if ((pomContext.getConfigCTR().getConfig().getDifDthrConfig() == 0) && (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 8L)) {
                LogProcessoDAO.getInstance().insertLogProcesso("if ((pomContext.getConfigCTR().getConfig().getDifDthrConfig() == 0) && (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 8L)) {\n" +
                        "                pomContext.getConfigCTR().setContDataHora(1);\n" +
                        "                pomContext.getConfigCTR().setPosicaoTela(5L);\n" +
                        "                Intent it = new Intent(MenuPrincPMMActivity.this, MsgDataHoraActivity.class);", getLocalClassName());
                pomContext.getConfigCTR().setContDataHora(1);
                pomContext.getConfigCTR().setPosicaoTela(5L);
                Intent it = new Intent(MenuPrincActivity.this, MsgDataHoraActivity.class);
                startActivity(it);
                finish();
            }
        }

        LogProcessoDAO.getInstance().insertLogProcesso("ArrayList<String> itens = new ArrayList<String>();\n" +
                "        itens.add(\"APONTAR MANUTENÇÃO\");\n" +
                "        itens.add(\"FINALIZAR MANUTENÇÃO\");\n" +
                "        itens.add(\"FINALIZAR BOLETIM\");\n" +
                "        itens.add(\"HISTORICO\");\n" +
                "        itens.add(\"REENVIO DE DADOS\");\n" +
                "        itens.add(\"DATA/HORA\");\n" +
                "        itens.add(\"LOG\");\n" +
                "        AdapterList adapterList = new AdapterList(this, itens);\n" +
                "        listViewAtiv = findViewById(R.id.listViewMenuPrinc);\n" +
                "        listViewAtiv.setAdapter(adapterList);", getLocalClassName());

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("APONTAR MANUTENÇÃO");
        itens.add("FINALIZAR MANUTENÇÃO");
        itens.add("FINALIZAR BOLETIM");
        itens.add("HISTORICO");
        itens.add("DATA/HORA");
        itens.add("LOG");

        AdapterList adapterList = new AdapterList(this, itens);
        listViewAtiv = findViewById(R.id.listViewMenuPrinc);
        listViewAtiv.setAdapter(adapterList);

        listViewAtiv.setOnItemClickListener((l, v, position, id) -> {

            LogProcessoDAO.getInstance().insertLogProcesso("listViewAtiv.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                    "                                    long id) {\n" +
                    "                TextView textView = v.findViewById(R.id.textViewItemList);\n" +
                    "                String text = textView.getText().toString();", getLocalClassName());
            TextView textView = v.findViewById(R.id.textViewItemList);
            String text = textView.getText().toString();

            if (text.equals("FINALIZAR BOLETIM")) {
                LogProcessoDAO.getInstance().insertLogProcesso("} else if (text.equals(\"FINALIZAR BOLETIM\")) {", getLocalClassName());
                if(!pomContext.getMecanicoCTR().verApontMecanAberto()) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if(!pomContext.getMecanicoCTR().verApontAberto()) {\n" +
                            "                            pomContext.getConfigCTR().setPosicaoTela(4L);\n" +
                            "                            customHandler.removeCallbacks(updateTimerThread);\n" +
                            "                            Intent it = new Intent(MenuPrincPMMActivity.this, HorimetroActivity.class);", getLocalClassName());
                    pomContext.getConfigCTR().setPosicaoTela(4L);
                    customHandler.removeCallbacks(updateTimerThread);
                    Intent it = new Intent(MenuPrincActivity.this, HorimetroActivity.class);
                    startActivity(it);
                    finish();
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                        Toast.makeText(MenuPrincPMMActivity.this, \"POR FAVOR, FINALIZE O APONTAMENTO DE MANUTENÇÃO PARA PODER FINALIZAR O BOLETIM.\",\n" +
                            "                                Toast.LENGTH_LONG).show();", getLocalClassName());
                    Toast.makeText(MenuPrincActivity.this, "POR FAVOR, FINALIZE O APONTAMENTO DE MANUTENÇÃO PARA PODER FINALIZAR O BOLETIM.",
                            Toast.LENGTH_LONG).show();
                }
            } else if (text.equals("HISTORICO")) {
                LogProcessoDAO.getInstance().insertLogProcesso("} else if (text.equals(\"HISTORICO\")) {\n" +
                        "                    Intent it = new Intent(MenuPrincPMMActivity.this, ListaHistApontActivity.class);", getLocalClassName());
                Intent it = new Intent(MenuPrincActivity.this, ListaHistApontActivity.class);
                startActivity(it);
                finish();
            } else if (text.equals("DATA/HORA")) {
                LogProcessoDAO.getInstance().insertLogProcesso("else if (text.equals(\"DATA/HORA\")) {", getLocalClassName());
                if(Tempo.getInstance().dif() == 0){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(Tempo.getInstance().dif() == 0){\n" +
                            "                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincPMMActivity.this);\n" +
                            "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                        alerta.setMessage(\"A DATA/HORA FOI ADQUIRIDA AUTOMATICAMENTO. O SISTEMA NÃO DEIXA ALTERA A MESMA.\");\n" +
                            "                        alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                            "                            @Override\n" +
                            "                            public void onClick(DialogInterface dialog, int which) {\n" +
                            "                            }\n" +
                            "                        });\n" +
                            "                        alerta.show();", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("A DATA/HORA FOI ADQUIRIDA AUTOMATICAMENTO. O SISTEMA NÃO DEIXA ALTERA A MESMA.");
                    alerta.setPositiveButton("OK", (dialog, which) -> {
                    });
                    alerta.show();
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                            "                        pomContext.getConfigCTR().setContDataHora(1);\n" +
                            "                        pomContext.getConfigCTR().setPosicaoTela(5L);\n" +
                            "                        Intent it = new Intent(MenuPrincPMMActivity.this, DataHoraActivity.class);", getLocalClassName());
                    pomContext.getConfigCTR().setContDataHora(1);
                    pomContext.getConfigCTR().setPosicaoTela(5L);
                    Intent it = new Intent(MenuPrincActivity.this, DataHoraActivity.class);
                    startActivity(it);
                    finish();
                }
            } else if (text.equals("LOG")) {
                LogProcessoDAO.getInstance().insertLogProcesso("else if (text.equals(\"LOG\")) {\n" +
                        "                    pomContext.getConfigCTR().setPosicaoTela(23L);\n" +
                        "                    Intent it = new Intent(MenuPrincPMMActivity.this, SenhaActivity.class);", getLocalClassName());
                pomContext.getConfigCTR().setPosicaoTela(23L);
                Intent it = new Intent(MenuPrincActivity.this, SenhaActivity.class);
                startActivity(it);
                finish();
            } else if (text.equals("APONTAR MANUTENÇÃO")) {
                LogProcessoDAO.getInstance().insertLogProcesso("else if (text.equals(\"APONTAR MANUTENÇÃO\")) {", getLocalClassName());
                if(!pomContext.getMecanicoCTR().verApontMecanAberto()) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if(!pomContext.getMecanicoCTR().verApontAberto()) {\n" +
                            "                        pomContext.getConfigCTR().setPosicaoTela(27L);\n" +
                            "                        Intent it = new Intent(MenuPrincPMMActivity.this, OSMecanActivity.class);", getLocalClassName());
                    pomContext.getConfigCTR().setPosicaoTela(27L);
                    Intent it = new Intent(MenuPrincActivity.this, OSMecanActivity.class);
                    startActivity(it);
                    finish();
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                        Toast.makeText(MenuPrincPMMActivity.this, \"POR FAVOR,  FINALIZE O APONTAMENTO DE MANUTENÇÃO PARA INICIAR OUTRO APONTAMENTO.\",\n" +
                            "                                Toast.LENGTH_LONG).show();", getLocalClassName());
                    Toast.makeText(MenuPrincActivity.this, "POR FAVOR, FINALIZE O APONTAMENTO DE MANUTENÇÃO PARA INICIAR OUTRO APONTAMENTO.",
                            Toast.LENGTH_LONG).show();
                }
            } else if (text.equals("FINALIZAR MANUTENÇÃO")) {
                LogProcessoDAO.getInstance().insertLogProcesso("else if (text.equals(\"FINALIZAR MANUTENÇÃO\")) {", getLocalClassName());
                if(pomContext.getMecanicoCTR().verApontMecanAberto()){

                    LogProcessoDAO.getInstance().insertLogProcesso("if(pomContext.getMecanicoCTR().verApontMecanAberto()){\n" +
                            "                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincPMMActivity.this);\n" +
                            "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                        alerta.setMessage(\"DESEJA REALMENTE FINALIZAR A MANUTENÇÃO?\");", getLocalClassName());

                    AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("DESEJA REALMENTE FINALIZAR A MANUTENÇÃO?");
                    alerta.setPositiveButton("SIM", (dialog, which) -> {
                        LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                                "                            @Override\n" +
                                "                            public void onClick(DialogInterface dialog, int which) {\n" +
                                "                                pomContext.getMecanicoCTR().finalizarApontMecan(getLocalClassName());", getLocalClassName());
                        pomContext.getMecanicoCTR().finalizarApontMecan(getLocalClassName());
                    });

                    alerta.setNegativeButton("NÃO", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"NÃO\", new DialogInterface.OnClickListener() {\n" +
                            "                            @Override\n" +
                            "                            public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));

                    alerta.show();

                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                        Toast.makeText(MenuPrincPMMActivity.this, \"POR FAVOR, INICIE UM APONTAMENTO MECANICO PARA INTERROMPER/FINALIZAR O MESMO.\",\n" +
                            "                                Toast.LENGTH_LONG).show();", getLocalClassName());
                    Toast.makeText(MenuPrincActivity.this, "POR FAVOR, INICIE UM APONTAMENTO MECANICO PARA INTERROMPER/FINALIZAR O MESMO.",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public void onBackPressed() {
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            LogProcessoDAO.getInstance().insertLogProcesso("    private Runnable updateTimerThread = new Runnable() {\n" +
                    "        public void run() {", getLocalClassName());
            if (EnvioDadosServ.status == 1) {
                textViewProcessoNormal.setTextColor(Color.YELLOW);
                textViewProcessoNormal.setText("Enviando e recebendo de dados...");
            } else if (EnvioDadosServ.status == 2) {
                textViewProcessoNormal.setTextColor(Color.RED);
                textViewProcessoNormal.setText("Existem dados para serem enviados e recebidos");
            } else {
                textViewProcessoNormal.setTextColor(Color.GREEN);
                textViewProcessoNormal.setText("Todos os Dados já foram enviados e recebidos");
            }

            textViewDataHora.setText(Tempo.getInstance().dthrAtualString());
            if(Tempo.getInstance().dif() == 0){
                textViewDataHora.setTextColor(Color.GREEN);
            }
            else{
                textViewDataHora.setTextColor(Color.RED);
            }

            LogProcessoDAO.getInstance().insertLogProcesso("if(EnvioDadosServ.status != 3){\n" +
                    "                customHandler.postDelayed(this, 10000);\n" +
                    "            }", getLocalClassName());
            if(EnvioDadosServ.status != 3){
                customHandler.postDelayed(this, 10000);
            }
        }
    };

}
