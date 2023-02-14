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
import java.util.List;

import br.com.usinasantafe.pom.POMContext;
import br.com.usinasantafe.pom.R;
import br.com.usinasantafe.pom.model.bean.estaticas.RFuncaoAtivParBean;
import br.com.usinasantafe.pom.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pom.util.EnvioDadosServ;
import br.com.usinasantafe.pom.util.Tempo;

public class MenuPrincPMMActivity extends ActivityGeneric {

    private POMContext pomContext;
    private ListView listViewAtiv;
    private ProgressDialog progressBar;

    private TextView textViewProcessoNormal;
    private TextView textViewDataHora;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_princ_pmm);

        pomContext = (POMContext) getApplication();
        textViewProcessoNormal = findViewById(R.id.textViewProcessoNormal);
        textViewDataHora = findViewById(R.id.textViewDataHora);

        LogProcessoDAO.getInstance().insertLogProcesso("customHandler.postDelayed(updateTimerThread, 0);", getLocalClassName());
        customHandler.postDelayed(updateTimerThread, 0);

        if (Tempo.getInstance().verDthrServ(pomContext.getConfigCTR().getConfig().getDtServConfig())) {
            LogProcessoDAO.getInstance().insertLogProcesso("if (Tempo.getInstance().verDthrServ(pomContext.getConfigCTR().getConfig().getDtServConfig())) {\n" +
                    "            pomContext.getConfigCTR().setDifDthrConfig(0L);", getLocalClassName());
            pomContext.getConfigCTR().setDifDthrConfig(0L);
        }
        else {
            LogProcessoDAO.getInstance().insertLogProcesso("else {", getLocalClassName());
            if ((pomContext.getConfigCTR().getConfig().getDifDthrConfig() == 0) && (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 8L)) {
                LogProcessoDAO.getInstance().insertLogProcesso("if ((pomContext.getConfigCTR().getConfig().getDifDthrConfig() == 0) && (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 8L)) {\n" +
                        "                pomContext.getConfigCTR().setContDataHora(1);\n" +
                        "                pomContext.getConfigCTR().setPosicaoTela(5L);\n" +
                        "                Intent it = new Intent(MenuPrincPMMActivity.this, MsgDataHoraActivity.class);", getLocalClassName());
                pomContext.getConfigCTR().setContDataHora(1);
                pomContext.getConfigCTR().setPosicaoTela(5L);
                Intent it = new Intent(MenuPrincPMMActivity.this, MsgDataHoraActivity.class);
                startActivity(it);
                finish();
            }
        }

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("TRABALHANDO");
        itens.add("PARADO");

        if(pomContext.getConfigCTR().getEquip().getTipoEquip() == 1){

            LogProcessoDAO.getInstance().insertLogProcesso("if(pomContext.getConfigCTR().getEquip().getTipoEquip() == 1){\n" +
                    "            List<RFuncaoAtivParBean> rFuncaoAtividadeList = pomContext.getMotoMecFertCTR().getFuncaoAtividadeList(getLocalClassName());", getLocalClassName());
            List<RFuncaoAtivParBean> rFuncaoAtividadeList = pomContext.getMotoMecFertCTR().getFuncaoAtividadeList(getLocalClassName());

            for (RFuncaoAtivParBean rFuncaoAtivParBean : rFuncaoAtividadeList) {

                LogProcessoDAO.getInstance().insertLogProcesso("for (RFuncaoAtivParBean rFuncaoAtivParBean : rFuncaoAtividadeList) {\n" +
                        "if(" + rFuncaoAtivParBean.getCodFuncao() + " == 2){\n" +
                        "                    itens.add(\"NOVO TRANSBORDO\");\n" +
                        "                }\n" +
                        "                if(" + rFuncaoAtivParBean.getCodFuncao() + " == 1){\n" +
                        "                    itens.add(\"RENDIMENTO\");\n" +
                        "                }\n" +
                        "                if(" + rFuncaoAtivParBean.getCodFuncao() + " == 3){\n" +
                        "                    itens.add(\"TROCAR IMPLEMENTO\");\n" +
                        "                }\n" +
                        "                if(" + rFuncaoAtivParBean.getCodFuncao() + " == 5){\n" +
                        "                    if(pomContext.getConfigCTR().getOS().getTipoOS() == 1){\n" +
                        "                        itens.add(\"COMPOSTAGEM\");\n" +
                        "                    }\n" +
                        "                }", getLocalClassName());

                if(rFuncaoAtivParBean.getCodFuncao() == 1){
                    itens.add("RENDIMENTO");
                }
                if(rFuncaoAtivParBean.getCodFuncao() == 2){
                    itens.add("NOVO TRANSBORDO");
                }
                if(rFuncaoAtivParBean.getCodFuncao() == 3){
                    itens.add("TROCAR IMPLEMENTO");
                }
//                if(rFuncaoAtivParBean.getCodFuncao() == 5){
//                    itens.add("COMPOSTAGEM");
//                }
            }
            rFuncaoAtividadeList.clear();
        }
        else{
            LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                    "            itens.add(\"RECOLHIMENTO MANGUEIRA\");", getLocalClassName());
            itens.add("RECOLHIMENTO MANGUEIRA");
        }

        LogProcessoDAO.getInstance().insertLogProcesso("itens.add(\"APONTAR MANUTENÇÃO\");\n" +
                "        itens.add(\"FINALIZAR MANUTENÇÃO\");\n" +
                "        itens.add(\"FINALIZAR BOLETIM\");\n" +
                "        itens.add(\"HISTORICO\");\n" +
                "        itens.add(\"REENVIO DE DADOS\");\n" +
                "        itens.add(\"DATA/HORA\");\n" +
                "        itens.add(\"LOG\");\n" +
                "        AdapterList adapterList = new AdapterList(this, itens);\n" +
                "        listViewAtiv = (ListView) findViewById(R.id.listViewMenuPrinc);\n" +
                "        listViewAtiv.setAdapter(adapterList);", getLocalClassName());

        LogProcessoDAO.getInstance().insertLogProcesso("if(pomContext.getConfigCTR().getEquip().getFlagApontMecan() == 1){\n" +
                "            itens.add(\"APONTAR MANUTENÇÃO\");\n" +
                "            itens.add(\"FINALIZAR MANUTENÇÃO\");\n" +
                "        }", getLocalClassName());
        if(pomContext.getConfigCTR().getEquip().getFlagApontMecan() == 1){
            itens.add("APONTAR MANUTENÇÃO");
            itens.add("FINALIZAR MANUTENÇÃO");
        }

        LogProcessoDAO.getInstance().insertLogProcesso("itens.add(\"FINALIZAR BOLETIM\");\n" +
                "        itens.add(\"HISTORICO\");\n" +
                "        itens.add(\"REENVIO DE DADOS\");\n" +
                "        itens.add(\"DATA/HORA\");\n" +
                "        itens.add(\"LOG\");\n" +
                "        AdapterList adapterList = new AdapterList(this, itens);\n" +
                "        listViewAtiv = (ListView) findViewById(R.id.listViewMenuPrinc);\n" +
                "        listViewAtiv.setAdapter(adapterList);", getLocalClassName());

        itens.add("FINALIZAR BOLETIM");
        itens.add("HISTORICO");
        itens.add("REENVIO DE DADOS");
        itens.add("DATA/HORA");
        itens.add("LOG");

        AdapterList adapterList = new AdapterList(this, itens);
        listViewAtiv = findViewById(R.id.listViewMenuPrinc);
        listViewAtiv.setAdapter(adapterList);

        listViewAtiv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                LogProcessoDAO.getInstance().insertLogProcesso("listViewAtiv.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                        "                                    long id) {\n" +
                        "                TextView textView = v.findViewById(R.id.textViewItemList);\n" +
                        "                String text = textView.getText().toString();", getLocalClassName());
                TextView textView = v.findViewById(R.id.textViewItemList);
                String text = textView.getText().toString();

                if (text.equals("TRABALHANDO")) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (text.equals(\"TRABALHANDO\")) {", getLocalClassName());
                    if(!pomContext.getMecanicoCTR().verApontMecanAberto()) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if(!pomContext.getMecanicoCTR().verApontAberto()) {", getLocalClassName());
                        if (!pomContext.getMotoMecFertCTR().verDataHoraInsApontMMFert()) {
                            LogProcessoDAO.getInstance().insertLogProcesso("if (!pomContext.getMotoMecFertCTR().verDataHoraInsApontMMFert()) {\n" +
                                    "                            pomContext.getConfigCTR().setPosicaoTela(2L);\n" +
                                    "                            customHandler.removeCallbacks(updateTimerThread);\n" +
                                    "                            Intent it = new Intent(MenuPrincPMMActivity.this, OSActivity.class);", getLocalClassName());
                            pomContext.getConfigCTR().setPosicaoTela(2L);
                            customHandler.removeCallbacks(updateTimerThread);
                            Intent it = new Intent(MenuPrincPMMActivity.this, OSActivity.class);
                            startActivity(it);
                            finish();
                        } else {
                            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                    "                            Toast.makeText(MenuPrincPMMActivity.this, \"POR FAVOR, AGUARDE UM MINUTO ANTES DE REALIZAR UM NOVO APONTAMENTO.\",\n" +
                                    "                                    Toast.LENGTH_LONG).show();", getLocalClassName());
                            Toast.makeText(MenuPrincPMMActivity.this, "POR FAVOR, AGUARDE UM MINUTO ANTES DE REALIZAR UM NOVO APONTAMENTO.",
                                    Toast.LENGTH_LONG).show();
                        }
                    } else {
                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "                        Toast.makeText(MenuPrincPMMActivity.this, \"POR FAVOR, FINALIZE O APONTAMENTO DE MANUTENÇÃO PARA INICIAR OUTRO APONTAMENTO.\",\n" +
                                "                                Toast.LENGTH_LONG).show();", getLocalClassName());
                        Toast.makeText(MenuPrincPMMActivity.this, "POR FAVOR, FINALIZE O APONTAMENTO DE MANUTENÇÃO PARA INICIAR OUTRO APONTAMENTO.",
                                Toast.LENGTH_LONG).show();
                    }
                } else if (text.equals("PARADO")) {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else if (text.equals(\"PARADO\")) {", getLocalClassName());
                    if(!pomContext.getMecanicoCTR().verApontMecanAberto()) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if(!pomContext.getMecanicoCTR().verApontAberto()) {", getLocalClassName());
                        if (!pomContext.getMotoMecFertCTR().verDataHoraInsApontMMFert()) {
                            LogProcessoDAO.getInstance().insertLogProcesso("if (!pomContext.getMotoMecFertCTR().verDataHoraInsApontMMFert()) {\n" +
                                    "                            pomContext.getConfigCTR().setPosicaoTela(3L);\n" +
                                    "                            customHandler.removeCallbacks(updateTimerThread);\n" +
                                    "                            Intent it = new Intent(MenuPrincPMMActivity.this, OSActivity.class);", getLocalClassName());
                            pomContext.getConfigCTR().setPosicaoTela(3L);
                            customHandler.removeCallbacks(updateTimerThread);
                            Intent it = new Intent(MenuPrincPMMActivity.this, OSActivity.class);
                            startActivity(it);
                            finish();
                        } else {
                            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                    "                            Toast.makeText(MenuPrincPMMActivity.this, \"POR FAVOR, AGUARDE UM MINUTO ANTES DE REALIZAR UM NOVO APONTAMENTO.\",\n" +
                                    "                                    Toast.LENGTH_LONG).show();", getLocalClassName());
                            Toast.makeText(MenuPrincPMMActivity.this, "POR FAVOR, AGUARDE UM MINUTO ANTES DE REALIZAR UM NOVO APONTAMENTO.",
                                    Toast.LENGTH_LONG).show();
                        }
                    } else {
                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "                        Toast.makeText(MenuPrincPMMActivity.this, \"POR FAVOR, FINALIZE O APONTAMENTO DE MANUTENÇÃO PARA INICIAR OUTRO APONTAMENTO.\",\n" +
                                "                                Toast.LENGTH_LONG).show();", getLocalClassName());
                        Toast.makeText(MenuPrincPMMActivity.this, "POR FAVOR, FINALIZE O APONTAMENTO DE MANUTENÇÃO PARA INICIAR OUTRO APONTAMENTO.",
                                Toast.LENGTH_LONG).show();
                    }
                } else if (text.equals("FINALIZAR BOLETIM")) {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else if (text.equals(\"FINALIZAR BOLETIM\")) {", getLocalClassName());
                    if(!pomContext.getMecanicoCTR().verApontMecanAberto()) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if(!pomContext.getMecanicoCTR().verApontAberto()) {", getLocalClassName());
                        if (pomContext.getMotoMecFertCTR().hasApontBolAberto()) {
                            LogProcessoDAO.getInstance().insertLogProcesso("if (pomContext.getMotoMecFertCTR().hasApontBolAberto()) {\n" +
                                    "                            pomContext.getConfigCTR().setPosicaoTela(4L);\n" +
                                    "                            customHandler.removeCallbacks(updateTimerThread);\n" +
                                    "                            Intent it = new Intent(MenuPrincPMMActivity.this, HorimetroActivity.class);", getLocalClassName());
                            pomContext.getConfigCTR().setPosicaoTela(4L);
                            customHandler.removeCallbacks(updateTimerThread);
                            Intent it = new Intent(MenuPrincPMMActivity.this, HorimetroActivity.class);
                            startActivity(it);
                            finish();
                        } else {
                            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                    "                            Toast.makeText(MenuPrincPMMActivity.this, \"POR FAVOR! INSIRA OS APONTAMENTOS AO BOLETIM!\",\n" +
                                    "                                    Toast.LENGTH_LONG).show();", getLocalClassName());
                            Toast.makeText(MenuPrincPMMActivity.this, "POR FAVOR! INSIRA OS APONTAMENTOS AO BOLETIM!",
                                    Toast.LENGTH_LONG).show();
                        }
                    } else {
                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "                        Toast.makeText(MenuPrincPMMActivity.this, \"POR FAVOR, FINALIZE O APONTAMENTO DE MANUTENÇÃO PARA INICIAR OUTRO APONTAMENTO.\",\n" +
                                "                                Toast.LENGTH_LONG).show();", getLocalClassName());
                        Toast.makeText(MenuPrincPMMActivity.this, "POR FAVOR, FINALIZE O APONTAMENTO DE MANUTENÇÃO PARA INICIAR OUTRO APONTAMENTO.",
                                Toast.LENGTH_LONG).show();
                    }
                } else if (text.equals("HISTORICO")) {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else if (text.equals(\"HISTORICO\")) {\n" +
                            "                    Intent it = new Intent(MenuPrincPMMActivity.this, ListaHistApontActivity.class);", getLocalClassName());
                    Intent it = new Intent(MenuPrincPMMActivity.this, ListaHistApontActivity.class);
                    startActivity(it);
                    finish();
                } else if (text.equals("NOVO TRANSBORDO")) {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else if (text.equals(\"NOVO TRANSBORDO\")) {", getLocalClassName());
                    if(!pomContext.getMecanicoCTR().verApontMecanAberto()) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if(!pomContext.getMecanicoCTR().verApontAberto()) {\n" +
                                "                        int ver = pomContext.getMotoMecFertCTR().verTrocaTransb();", getLocalClassName());
                        int ver = pomContext.getMotoMecFertCTR().verTrocaTransb();
                        if (ver == 0) {
                            pomContext.getConfigCTR().setPosicaoTela(6L);
                            customHandler.removeCallbacks(updateTimerThread);
                            LogProcessoDAO.getInstance().insertLogProcesso("if (ver == 0) {\n" +
                                    "                            pomContext.getConfigCTR().setPosicaoTela(6L);\n" +
                                    "                            customHandler.removeCallbacks(updateTimerThread);\n" +
                                    "                            Intent it = new Intent(MenuPrincPMMActivity.this, TransbordoActivity.class);", getLocalClassName());
                            Intent it = new Intent(MenuPrincPMMActivity.this, TransbordoActivity.class);
                            startActivity(it);
                            finish();
                        } else if (ver == 1) {
                            LogProcessoDAO.getInstance().insertLogProcesso("} else if (ver == 1) {\n" +
                                    "                            Toast.makeText(MenuPrincPMMActivity.this, \"POR FAVOR! APONTE UMA ATIVIDADE ANTES DE TROCAR DE TRANSBORDO.\",\n" +
                                    "                                    Toast.LENGTH_LONG).show();", getLocalClassName());
                            Toast.makeText(MenuPrincPMMActivity.this, "POR FAVOR! APONTE UMA ATIVIDADE ANTES DE TROCAR DE TRANSBORDO.",
                                    Toast.LENGTH_LONG).show();
                        } else if (ver == 2) {
                            LogProcessoDAO.getInstance().insertLogProcesso("} else if (ver == 2) {\n" +
                                    "                        Toast.makeText(MenuPrincPMMActivity.this, \"POR FAVOR! ESPERE 10 MINUTO PARA TROCAR DE TRANSBORDO.\",\n" +
                                    "                                Toast.LENGTH_LONG).show();", getLocalClassName());
                            Toast.makeText(MenuPrincPMMActivity.this, "POR FAVOR! ESPERE 10 MINUTO PARA TROCAR DE TRANSBORDO.",
                                    Toast.LENGTH_LONG).show();
                        }
                    } else {
                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "                        Toast.makeText(MenuPrincPMMActivity.this, \"POR FAVOR, FINALIZE O APONTAMENTO DE MANUTENÇÃO PARA INICIAR OUTRO APONTAMENTO.\",\n" +
                                "                                Toast.LENGTH_LONG).show();", getLocalClassName());
                        Toast.makeText(MenuPrincPMMActivity.this, "POR FAVOR, FINALIZE O APONTAMENTO DE MANUTENÇÃO PARA INICIAR OUTRO APONTAMENTO.",
                                Toast.LENGTH_LONG).show();
                    }
                } else if (text.equals("RENDIMENTO")) {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else if (text.equals(\"RENDIMENTO\")) {\n" +
                            "                    pomContext.getConfigCTR().setPosicaoTela(7L);\n" +
                            "                    customHandler.removeCallbacks(updateTimerThread);\n" +
                            "                    Intent it = new Intent(MenuPrincPMMActivity.this, ListaOSRendActivity.class);", getLocalClassName());
                    pomContext.getConfigCTR().setPosicaoTela(7L);
                    customHandler.removeCallbacks(updateTimerThread);
                    Intent it = new Intent(MenuPrincPMMActivity.this, ListaOSRendActivity.class);
                    startActivity(it);
                    finish();
                } else if (text.equals("ATUALIZAR DADOS")) {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else if (text.equals(\"ATUALIZAR DADOS\")) {", getLocalClassName());
                    if (connectNetwork) {

                        LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                                "                        progressBar = new ProgressDialog(v.getContext());\n" +
                                "                        progressBar.setCancelable(true);\n" +
                                "                        progressBar.setMessage(\"ATUALIZANDO ...\");\n" +
                                "                        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);\n" +
                                "                        progressBar.setProgress(0);\n" +
                                "                        progressBar.setMax(100);\n" +
                                "                        progressBar.show();", getLocalClassName());

                        progressBar = new ProgressDialog(v.getContext());
                        progressBar.setCancelable(true);
                        progressBar.setMessage("ATUALIZANDO ...");
                        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        progressBar.setProgress(0);
                        progressBar.setMax(100);
                        progressBar.show();

                        LogProcessoDAO.getInstance().insertLogProcesso("pomContext.getConfigCTR().atualTodasTabelas(MenuPrincPMMActivity.this, progressBar, null);", getLocalClassName());
                        pomContext.getConfigCTR().atualTodasTabelas(MenuPrincPMMActivity.this, progressBar, getLocalClassName());

                    } else {

                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincPMMActivity.this);\n" +
                                "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                                "                        alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                                "                        alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                "                            @Override\n" +
                                "                            public void onClick(DialogInterface dialog, int which) {\n" +
                                "                            }\n" +
                                "                        });\n" +
                                "                        alerta.show();", getLocalClassName());
                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincPMMActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        alerta.show();
                    }

                } else if (text.equals("RECOLHIMENTO MANGUEIRA")) {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else if (text.equals(\"RECOLHIMENTO MANGUEIRA\")) {\n" +
                            "                    pomContext.getConfigCTR().setPosicaoTela(9L);\n" +
                            "                    customHandler.removeCallbacks(updateTimerThread);\n" +
                            "                    Intent it = new Intent(MenuPrincPMMActivity.this, ListaOSRecolhActivity.class);", getLocalClassName());
                    pomContext.getConfigCTR().setPosicaoTela(9L);
                    customHandler.removeCallbacks(updateTimerThread);
                    Intent it = new Intent(MenuPrincPMMActivity.this, ListaOSRecolhActivity.class);
                    startActivity(it);
                    finish();
                } else if (text.equals("TROCAR IMPLEMENTO")) {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else if (text.equals(\"TROCAR IMPLEMENTO\")) {", getLocalClassName());
                    if(!pomContext.getMecanicoCTR().verApontMecanAberto()) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if(!pomContext.getMecanicoCTR().verApontAberto()) {", getLocalClassName());
                        if (pomContext.getMotoMecFertCTR().hasApontBolAberto()) {
                            LogProcessoDAO.getInstance().insertLogProcesso("if (pomContext.getMotoMecFertCTR().hasApontBolAberto()) {", getLocalClassName());
                            if (!pomContext.getMotoMecFertCTR().verDataHoraInsApontMMFert()) {
                                LogProcessoDAO.getInstance().insertLogProcesso("if (!pomContext.getMotoMecFertCTR().verDataHoraInsApontMMFert()) {\n" +
                                        "                                pomContext.getMotoMecFertCTR().inserirParadaImplemento(getLocalClassName());\n" +
                                        "                                pomContext.getConfigCTR().setPosicaoTela(10L);\n" +
                                        "                                pomContext.getMotoMecFertCTR().setContImplemento(1L);\n" +
                                        "                                customHandler.removeCallbacks(updateTimerThread);\n" +
                                        "                                Intent it = new Intent(MenuPrincPMMActivity.this, ImplementoActivity.class);", getLocalClassName());
                                pomContext.getMotoMecFertCTR().inserirParadaImplemento(getLocalClassName());
                                pomContext.getConfigCTR().setPosicaoTela(10L);
                                pomContext.getMotoMecFertCTR().setContImplemento(1L);
                                customHandler.removeCallbacks(updateTimerThread);
                                Intent it = new Intent(MenuPrincPMMActivity.this, ImplementoActivity.class);
                                startActivity(it);
                                finish();
                            } else {
                                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                        "                        if (pomContext.getMotoMecFertCTR().verDataHoraInsApontMMFert()) {\n" +
                                        "                            Toast.makeText(MenuPrincPMMActivity.this, \"POR FAVOR, AGUARDE UM MINUTO ANTES DE REALIZAR UM NOVO APONTAMENTO.\",\n" +
                                        "                                    Toast.LENGTH_LONG).show();", getLocalClassName());
                                Toast.makeText(MenuPrincPMMActivity.this, "POR FAVOR, AGUARDE UM MINUTO ANTES DE REALIZAR UM NOVO APONTAMENTO.",
                                        Toast.LENGTH_LONG).show();
                            }
                        } else {
                            LogProcessoDAO.getInstance().insertLogProcesso("if (!pomContext.getMotoMecFertCTR().hasApontBolAberto()) {\n" +
                                    "                        Toast.makeText(MenuPrincPMMActivity.this, \"POR FAVOR! FAÇA ALGUM APONTAMENTO ANTES DE REALIZAR A TROCA DO(S) IMPLEMENTO(S)!\",\n" +
                                    "                                Toast.LENGTH_LONG).show();", getLocalClassName());
                            Toast.makeText(MenuPrincPMMActivity.this, "POR FAVOR! FAÇA ALGUM APONTAMENTO ANTES DE REALIZAR A TROCA DO(S) IMPLEMENTO(S)!",
                                    Toast.LENGTH_LONG).show();
                        }
                    } else {
                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "                        Toast.makeText(MenuPrincPMMActivity.this, \"POR FAVOR, FINALIZE O APONTAMENTO DE MANUTENÇÃO PARA INICIAR OUTRO APONTAMENTO.\",\n" +
                                "                                Toast.LENGTH_LONG).show();", getLocalClassName());
                        Toast.makeText(MenuPrincPMMActivity.this, "POR FAVOR, FINALIZE O APONTAMENTO DE MANUTENÇÃO PARA INICIAR OUTRO APONTAMENTO.",
                                Toast.LENGTH_LONG).show();
                    }
                }
                else if (text.equals("COMPOSTAGEM")) {
                    LogProcessoDAO.getInstance().insertLogProcesso("else if (text.equals(\"COMPOSTAGEM\")) {", getLocalClassName());
                    if(!pomContext.getMecanicoCTR().verApontMecanAberto()) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if(!pomContext.getMecanicoCTR().verApontAberto()) {", getLocalClassName());
                        if (!pomContext.getMotoMecFertCTR().verDataHoraInsMovLeira()) {
                            LogProcessoDAO.getInstance().insertLogProcesso("if (!pomContext.getMotoMecFertCTR().verDataHoraInsMovLeira()) {\n" +
                                    "                            Intent it = new Intent(MenuPrincPMMActivity.this, ListaTipoMovLeiraActivity.class);", getLocalClassName());
                            Intent it = new Intent(MenuPrincPMMActivity.this, ListaTipoMovLeiraActivity.class);
                            startActivity(it);
                            finish();
                        } else {
                            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                    "                            Toast.makeText(MenuPrincPMMActivity.this, \"POR FAVOR, AGUARDE UM MINUTO ANTES DE REALIZAR UM NOVO APONTAMENTO.\",\n" +
                                    "                                    Toast.LENGTH_LONG).show();", getLocalClassName());
                            Toast.makeText(MenuPrincPMMActivity.this, "POR FAVOR, AGUARDE UM MINUTO ANTES DE REALIZAR UM NOVO APONTAMENTO.",
                                    Toast.LENGTH_LONG).show();
                        }
                    } else {
                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "                        Toast.makeText(MenuPrincPMMActivity.this, \"POR FAVOR, FINALIZE O APONTAMENTO DE MANUTENÇÃO PARA INICIAR OUTRO APONTAMENTO.\",\n" +
                                "                                Toast.LENGTH_LONG).show();", getLocalClassName());
                        Toast.makeText(MenuPrincPMMActivity.this, "POR FAVOR, FINALIZE O APONTAMENTO DE MANUTENÇÃO PARA INICIAR OUTRO APONTAMENTO.",
                                Toast.LENGTH_LONG).show();
                    }
                }
                else if (text.equals("DATA/HORA")) {
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
                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincPMMActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("A DATA/HORA FOI ADQUIRIDA AUTOMATICAMENTO. O SISTEMA NÃO DEIXA ALTERA A MESMA.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        alerta.show();
                    }
                    else{
                        LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                                "                        pomContext.getConfigCTR().setContDataHora(1);\n" +
                                "                        pomContext.getConfigCTR().setPosicaoTela(5L);\n" +
                                "                        Intent it = new Intent(MenuPrincPMMActivity.this, DataHoraActivity.class);", getLocalClassName());
                        pomContext.getConfigCTR().setContDataHora(1);
                        pomContext.getConfigCTR().setPosicaoTela(5L);
                        Intent it = new Intent(MenuPrincPMMActivity.this, DataHoraActivity.class);
                        startActivity(it);
                        finish();
                    }
                }
                else if (text.equals("LOG")) {
                    LogProcessoDAO.getInstance().insertLogProcesso("else if (text.equals(\"LOG\")) {\n" +
                            "                    pomContext.getConfigCTR().setPosicaoTela(23L);\n" +
                            "                    Intent it = new Intent(MenuPrincPMMActivity.this, SenhaActivity.class);", getLocalClassName());
                    pomContext.getConfigCTR().setPosicaoTela(23L);
                    Intent it = new Intent(MenuPrincPMMActivity.this, SenhaActivity.class);
                    startActivity(it);
                    finish();
                }
                else if (text.equals("APONTAR MANUTENÇÃO")) {
                    LogProcessoDAO.getInstance().insertLogProcesso("else if (text.equals(\"APONTAR MANUTENÇÃO\")) {", getLocalClassName());
                    if(pomContext.getMotoMecFertCTR().verUltApontAtiv()){
                        LogProcessoDAO.getInstance().insertLogProcesso("if(pomContext.getMotoMecFertCTR().verUltApontAtiv()){", getLocalClassName());
                        if(!pomContext.getMecanicoCTR().verApontMecanAberto()) {
                            LogProcessoDAO.getInstance().insertLogProcesso("if(!pomContext.getMecanicoCTR().verApontAberto()) {\n" +
                                    "                        pomContext.getConfigCTR().setPosicaoTela(27L);\n" +
                                    "                        Intent it = new Intent(MenuPrincPMMActivity.this, OSMecanActivity.class);", getLocalClassName());
                            pomContext.getConfigCTR().setPosicaoTela(27L);
                            Intent it = new Intent(MenuPrincPMMActivity.this, OSMecanActivity.class);
                            startActivity(it);
                            finish();
                        } else {
                            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                    "                        Toast.makeText(MenuPrincPMMActivity.this, \"POR FAVOR,  FINALIZE O APONTAMENTO DE MANUTENÇÃO PARA INICIAR OUTRO APONTAMENTO.\",\n" +
                                    "                                Toast.LENGTH_LONG).show();", getLocalClassName());
                            Toast.makeText(MenuPrincPMMActivity.this, "POR FAVOR, FINALIZE O APONTAMENTO DE MANUTENÇÃO PARA INICIAR OUTRO APONTAMENTO.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                                "                        Toast.makeText(MenuPrincPMMActivity.this, \"POR FAVOR, REALIZE UMA PARADA PARA REALIZAR UM APONTAMENTO DE MANUTENÇÃO.\",\n" +
                                "                                Toast.LENGTH_LONG).show();", getLocalClassName());
                        Toast.makeText(MenuPrincPMMActivity.this, "POR FAVOR, REALIZE UMA PARADA PARA REALIZAR UM APONTAMENTO DE MANUTENÇÃO.",
                                Toast.LENGTH_LONG).show();
                    }
                }
                else if (text.equals("FINALIZAR MANUTENÇÃO")) {
                    LogProcessoDAO.getInstance().insertLogProcesso("else if (text.equals(\"FINALIZAR MANUTENÇÃO\")) {", getLocalClassName());
                    if(pomContext.getMecanicoCTR().verApontMecanAberto()){

                        LogProcessoDAO.getInstance().insertLogProcesso("if(pomContext.getMecanicoCTR().verApontMecanAberto()){\n" +
                                "                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincPMMActivity.this);\n" +
                                "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                                "                        alerta.setMessage(\"DESEJA REALMENTE FINALIZAR A MANUTENÇÃO?\");", getLocalClassName());

                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincPMMActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("DESEJA REALMENTE FINALIZAR A MANUTENÇÃO?");
                        alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                                        "                            @Override\n" +
                                        "                            public void onClick(DialogInterface dialog, int which) {\n" +
                                        "                                pomContext.getMecanicoCTR().finalizarApontMecan(getLocalClassName());", getLocalClassName());
                                pomContext.getMecanicoCTR().finalizarApontMecan(getLocalClassName());
                            }
                        });

                        alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"NÃO\", new DialogInterface.OnClickListener() {\n" +
                                        "                            @Override\n" +
                                        "                            public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                            }
                        });

                        alerta.show();

                    } else {
                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "                        Toast.makeText(MenuPrincPMMActivity.this, \"POR FAVOR, INICIE UM APONTAMENTO MECANICO PARA INTERROMPER/FINALIZAR O MESMO.\",\n" +
                                "                                Toast.LENGTH_LONG).show();", getLocalClassName());
                        Toast.makeText(MenuPrincPMMActivity.this, "POR FAVOR, INICIE UM APONTAMENTO MECANICO PARA INTERROMPER/FINALIZAR O MESMO.",
                                Toast.LENGTH_LONG).show();
                    }

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
