package br.com.usinasantafe.pom.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pom.POMContext;
import br.com.usinasantafe.pom.R;
import br.com.usinasantafe.pom.model.bean.estaticas.MotoMecBean;
import br.com.usinasantafe.pom.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pom.util.Tempo;

public class MenuPrincECMActivity extends ActivityGeneric {

    private ListView motoMecListView;
    private POMContext pomContext;
    private TextView textViewMotorista;
    private TextView textViewCarreta;
    private TextView textViewUltimaViagem;
    private TextView textViewPropriedade;
    private TextView textViewStatus;
    private ProgressDialog progressBar;
    private List<MotoMecBean> motoMecList;
    private int posicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_princ_ecm);

        pomContext = (POMContext) getApplication();

        Button buttonRetMotoMec = findViewById(R.id.buttonRetMotoMec);
        Button buttonParadaMotoMec = findViewById(R.id.buttonParadaMotoMec);
        Button buttonLogMotoMec = findViewById(R.id.buttonLogMotoMec);
        textViewMotorista = findViewById(R.id.textViewMotorista);
        textViewCarreta = findViewById(R.id.textViewCarreta);
        textViewUltimaViagem = findViewById(R.id.textViewUltimaViagem);
        textViewPropriedade = findViewById(R.id.textViewPropriedade);
        textViewStatus = findViewById(R.id.textViewStatus);

        textViewMotorista.setText(pomContext.getMotoMecFertCTR().getMatricFunc().getMatricFunc() + " - " + pomContext.getMotoMecFertCTR().getMatricFunc().getNomeFunc());
        textViewCarreta.setText(pomContext.getMotoMecFertCTR().getDescrCarreta());
        textViewUltimaViagem.setText(pomContext.getCecCTR().getDataSaidaUlt());
        textViewPropriedade.setText(pomContext.getConfigCTR().getMsgPropriedade());
        textViewStatus.setText(pomContext.getMotoMecFertCTR().getUltApont());

        if(pomContext.getConfigCTR().getConfig().getPosicaoTela() == 1L){
            LogProcessoDAO.getInstance().insertLogProcesso("if(pomContext.getConfigCTR().getConfig().getPosicaoTela() == 1L){\n" +
                    "            pomContext.getMotoMecFertCTR().inserirApontBolAnterior(getLocalClassName());", getLocalClassName());
            pomContext.getMotoMecFertCTR().inserirApontBolAnterior(getLocalClassName());
        }

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
                        "                Intent it = new Intent(MenuPrincECMActivity.this, MsgDataHoraActivity.class);", getLocalClassName());
                pomContext.getConfigCTR().setContDataHora(1);
                pomContext.getConfigCTR().setPosicaoTela(5L);
                Intent it = new Intent(MenuPrincECMActivity.this, MsgDataHoraActivity.class);
                startActivity(it);
                finish();
            }
        }

        LogProcessoDAO.getInstance().insertLogProcesso("ArrayList<String> motoMecArrayList = new ArrayList<String>();\n" +
                "        motoMecList = pomContext.getMotoMecFertCTR().motoMecList();\n" +
                "        for (MotoMecBean motoMecBean : motoMecList) {\n" +
                "            motoMecArrayList.add(motoMecBean.getDescrOperMotoMec());\n" +
                "        }\n" +
                "        AdapterList adapterList = new AdapterList(this, motoMecArrayList);\n" +
                "        motoMecListView = findViewById(R.id.motoMecListView);\n" +
                "        motoMecListView.setAdapter(adapterList);", getLocalClassName());
        ArrayList<String> motoMecArrayList = new ArrayList<String>();
        motoMecList = pomContext.getMotoMecFertCTR().motoMecList();
        for (MotoMecBean motoMecBean : motoMecList) {
            motoMecArrayList.add(motoMecBean.getDescrOperMotoMec());
        }

        AdapterList adapterList = new AdapterList(this, motoMecArrayList);
        motoMecListView = findViewById(R.id.motoMecListView);
        motoMecListView.setAdapter(adapterList);

        motoMecListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                LogProcessoDAO.getInstance().insertLogProcesso("motoMecListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                        "                                    long id) {\n" +
                        "                posicao = position;\n" +
                        "                MotoMecBean motoMecBean = motoMecList.get(position);\n" +
                        "                pomContext.getMotoMecFertCTR().setMotoMecBean(motoMecBean);", getLocalClassName());
                posicao = position;
                MotoMecBean motoMecBean = motoMecList.get(position);
                pomContext.getMotoMecFertCTR().setMotoMecBean(motoMecBean);

                if (pomContext.getMotoMecFertCTR().verDataHoraInsApontMMFert()) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (pomContext.getMotoMecFertCTR().verDataHoraInsApontMMFert()) {\n" +
                            "                    Toast.makeText(MenuPrincECMActivity.this, \"POR FAVOR, AGUARDE UM MINUTO ANTES DE REALIZAR UM NOVO APONTAMENTO.\",\n" +
                            "                            Toast.LENGTH_LONG).show();", getLocalClassName());
                    Toast.makeText(MenuPrincECMActivity.this, "POR FAVOR, AGUARDE UM MINUTO ANTES DE REALIZAR UM NOVO APONTAMENTO.",
                            Toast.LENGTH_LONG).show();
                }
                else {

                    LogProcessoDAO.getInstance().insertLogProcesso("else {", getLocalClassName());
                    if (pomContext.getMotoMecFertCTR().verifBackupApont()
                        && (motoMecBean.getCodFuncaoOperMotoMec() != 6)) {

                        LogProcessoDAO.getInstance().insertLogProcesso("if (pomContext.getMotoMecFertCTR().verifBackupApont()) {\n" +
                                "                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);\n" +
                                "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                                "                        alerta.setMessage(\"OPERAÇÃO JÁ APONTADA PARA O EQUIPAMENTO!\");\n" +
                                "                        alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                "                            @Override\n" +
                                "                            public void onClick(DialogInterface dialog, int which) {\n" +
                                "                            }\n" +
                                "                        });\n" +
                                "                        alerta.show();", getLocalClassName());
                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("OPERAÇÃO JÁ APONTADA PARA O EQUIPAMENTO!");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        alerta.show();

                    } else {

                        LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
                        if ((motoMecBean.getCodFuncaoOperMotoMec() == 1)
                                || (motoMecBean.getCodFuncaoOperMotoMec() == 11)
                                || (motoMecBean.getCodFuncaoOperMotoMec() == 12)) {  // ATIVIDADES NORMAIS

                            LogProcessoDAO.getInstance().insertLogProcesso("if ((motoMecBean.getCodFuncaoOperMotoMec() == 1)\n" +
                                    "                                || (motoMecBean.getCodFuncaoOperMotoMec() == 11)\n" +
                                    "                                || (motoMecBean.getCodFuncaoOperMotoMec() == 12)) {  // ATIVIDADES NORMAIS\n" +
                                    "                            AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);\n" +
                                    "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                            alerta.setMessage(\"INÍCIO DE ATIVIDADE: \" + motoMecBean.getDescrOperMotoMec());", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("INÍCIO DE ATIVIDADE: " + motoMecBean.getDescrOperMotoMec());
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                            "                                @Override\n" +
                                            "                                public void onClick(DialogInterface dialog, int which) {\n" +
                                            "                                    ConnectNetwork connectNetwork = new ConnectNetwork();\n" +
                                            "                                    if (connectNetwork) {\n" +
                                            "                                        pomContext.getConfigCTR().setStatusConConfig(1L);\n" +
                                            "                                    } else {\n" +
                                            "                                        pomContext.getConfigCTR().setStatusConConfig(0L);\n" +
                                            "                                    }", getLocalClassName());
                                    if (connectNetwork) {
                                        pomContext.getConfigCTR().setStatusConConfig(1L);
                                    } else {
                                        pomContext.getConfigCTR().setStatusConConfig(0L);
                                    }

                                    LogProcessoDAO.getInstance().insertLogProcesso("pomContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude(), getLocalClassName());\n" +
                                            "                                    motoMecListView.setSelection(posicao + 1);\n" +
                                            "textViewStatus.setText(pomContext.getMotoMecFertCTR().getUltApont());", getLocalClassName());
                                    pomContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude(), getLocalClassName());
                                    motoMecListView.setSelection(posicao + 1);

                                    textViewStatus.setText(pomContext.getMotoMecFertCTR().getUltApont());

                                }

                            });

                            alerta.show();

                        } else if (motoMecBean.getCodFuncaoOperMotoMec() == 4) { // CERTIFICADO

                            LogProcessoDAO.getInstance().insertLogProcesso("} else if (motoMecBean.getCodFuncaoOperMotoMec() == 4) { // CERTIFICADO\n" +
                                    "                            pomContext.getConfigCTR().setPosicaoTela(16L);\n" +
                                    "                            Intent it = new Intent(MenuPrincECMActivity.this, MenuCertifActivity.class);", getLocalClassName());
                            pomContext.getConfigCTR().setPosicaoTela(16L);
                            Intent it = new Intent(MenuPrincECMActivity.this, MenuCertifActivity.class);
                            startActivity(it);
                            finish();

                        } else if (motoMecBean.getCodFuncaoOperMotoMec() == 2) { // SAIDA DA USINA

                            LogProcessoDAO.getInstance().insertLogProcesso("} else if (motoMecBean.getCodFuncaoOperMotoMec() == 2) { // SAIDA DA USINA", getLocalClassName());
                            if (pomContext.getCecCTR().verPreCECAberto()) {

                                String mensagem = "O HORÁRIO DE SAÍDA DA USINA JÁ FOI INSERIDO ANTERIORMENTE. " +
                                        "POR FAVOR TERMINE DE FAZER O APONTAMENTO OU REENVIE OS APONTAMENTOS JÁ PRONTOS.";

                                LogProcessoDAO.getInstance().insertLogProcesso("if (pomContext.getCecCTR().verPreCECAberto()) {\n" +
                                        "                                String mensagem = \"O HORÁRIO DE SAÍDA DA USINA JÁ FOI INSERIDO ANTERIORMENTE. \" +\n" +
                                        "                                        \"POR FAVOR TERMINE DE FAZER O APONTAMENTO OU REENVIE OS APONTAMENTOS JÁ PRONTOS.\";\n" +
                                        "                                AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);\n" +
                                        "                                alerta.setTitle(\"ATENÇÃO\");\n" +
                                        "                                alerta.setMessage(mensagem);", getLocalClassName());
                                AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage(mensagem);
                                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);\n" +
                                                "                                alerta.setTitle(\"ATENÇÃO\");\n" +
                                                "                                alerta.setMessage(mensagem);\n" +
                                                "                                alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                                "                                    @Override\n" +
                                                "                                    public void onClick(DialogInterface dialog, int which) {\n" +
                                                "                                        motoMecListView.setSelection(posicao + 1);", getLocalClassName());
                                        motoMecListView.setSelection(posicao + 1);
                                    }
                                });
                                alerta.show();

                            } else {

                                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                        "                                pomContext.getConfigCTR().setPosicaoTela(2L);\n" +
                                        "                                Intent it = new Intent(MenuPrincECMActivity.this, FrenteActivity.class);", getLocalClassName());
                                pomContext.getConfigCTR().setPosicaoTela(2L);
                                Intent it = new Intent(MenuPrincECMActivity.this, FrenteActivity.class);
                                startActivity(it);
                                finish();

                            }

                        } else if (motoMecBean.getCodFuncaoOperMotoMec() == 3) { // CHEGADA CAMPO
                            LogProcessoDAO.getInstance().insertLogProcesso("} else if (motoMecBean.getCodFuncaoOperMotoMec() == 3) { // CHEGADA CAMPO\n" +
                                    "                            String mensagem = \"\";", getLocalClassName());
                            String mensagem = "";
                            if (!pomContext.getCecCTR().verPreCECAberto()) {
                                LogProcessoDAO.getInstance().insertLogProcesso("if (!pomContext.getCecCTR().verPreCECAberto()) {\n" +
                                        "                                mensagem = \"É NECESSÁRIO A INSERÇÃO DO HORÁRIO DE SAÍDA DA USINA.\";", getLocalClassName());
                                mensagem = "É NECESSÁRIO A INSERÇÃO DO HORÁRIO DE SAÍDA DA USINA.";
                            } else {
                                LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
                                if (pomContext.getCecCTR().getDataChegCampo().equals("")) {
                                    LogProcessoDAO.getInstance().insertLogProcesso("if (pomContext.getCecCTR().getDataChegCampo().equals(\"\")) {\n" +
                                            "                                    mensagem = \"INÍCIO DE ATIVIDADE: \" + motoMecBean.getDescrOperMotoMec();", getLocalClassName());
                                    mensagem = "INÍCIO DE ATIVIDADE: " + motoMecBean.getDescrOperMotoMec();
                                } else {
                                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                            "                                    mensagem = \"O HORÁRIO DE CHEGADA AO CAMPO JÁ FOI INSERIDO ANTERIORMENTE. \" +\n" +
                                            "                                            \"POR FAVOR TERMINEI DE FAZER O APONTAMENTO OU REENVIE OS APONTAMENTOS JÁ PRONTOS.\";", getLocalClassName());
                                    mensagem = "O HORÁRIO DE CHEGADA AO CAMPO JÁ FOI INSERIDO ANTERIORMENTE. " +
                                            "POR FAVOR TERMINEI DE FAZER O APONTAMENTO OU REENVIE OS APONTAMENTOS JÁ PRONTOS.";
                                }
                            }

                            LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);\n" +
                                    "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                            alerta.setMessage(mensagem);", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage(mensagem);
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                            "                                @Override\n" +
                                            "                                public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                                    if (pomContext.getCecCTR().verPreCECAberto()) {
                                        LogProcessoDAO.getInstance().insertLogProcesso("if (pomContext.getCecCTR().verPreCECAberto()) {", getLocalClassName());
                                        if (pomContext.getCecCTR().getDataChegCampo().equals("")) {
                                            LogProcessoDAO.getInstance().insertLogProcesso("if (pomContext.getCecCTR().getDataChegCampo().equals(\"\")) {\n" +
                                                    "                                            pomContext.getCecCTR().setDataChegCampo();\n" +
                                                    "                                            if (connectNetwork) {\n" +
                                                    "                                                pomContext.getConfigCTR().setStatusConConfig(1L);\n" +
                                                    "                                            } else {\n" +
                                                    "                                                pomContext.getConfigCTR().setStatusConConfig(0L);\n" +
                                                    "                                            }", getLocalClassName());
                                            pomContext.getCecCTR().setDataChegCampo();
                                            if (connectNetwork) {
                                                pomContext.getConfigCTR().setStatusConConfig(1L);
                                            } else {
                                                pomContext.getConfigCTR().setStatusConConfig(0L);
                                            }
                                            LogProcessoDAO.getInstance().insertLogProcesso("pomContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude(), getLocalClassName());\n" +
                                                    "textViewStatus.setText(pomContext.getMotoMecFertCTR().getUltApont());", getLocalClassName());
                                            pomContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude(), getLocalClassName());
                                            textViewStatus.setText(pomContext.getMotoMecFertCTR().getUltApont());
                                        }
                                        motoMecListView.setSelection(posicao + 1);
                                    }
                                }
                            });

                            alerta.show();

                        } else if (motoMecBean.getCodFuncaoOperMotoMec() == 5) { // RETORNO PRA USINA

                            LogProcessoDAO.getInstance().insertLogProcesso("if ((motoMecBean.getCodFuncaoOperMotoMec() == 1)\n" +
                                    "                                || (motoMecBean.getCodFuncaoOperMotoMec() == 11)\n" +
                                    "                                || (motoMecBean.getCodFuncaoOperMotoMec() == 12)) {  // ATIVIDADES NORMAIS\n" +
                                    "                            AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);\n" +
                                    "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                            alerta.setMessage(\"INÍCIO DE ATIVIDADE: \" + motoMecBean.getDescrOperMotoMec());", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("INÍCIO DE ATIVIDADE: " + motoMecBean.getDescrOperMotoMec());
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    LogProcessoDAO.getInstance().insertLogProcesso("else {\n" +
                                            "if (connectNetwork) {\n" +
                                            "                        pomContext.getConfigCTR().setStatusConConfig(1L);\n" +
                                            "                    }\n" +
                                            "                    else{\n" +
                                            "                        pomContext.getConfigCTR().setStatusConConfig(0L);\n" +
                                            "                    }", getLocalClassName());
                                    if (connectNetwork) {
                                        pomContext.getConfigCTR().setStatusConConfig(1L);
                                    }
                                    else{
                                        pomContext.getConfigCTR().setStatusConConfig(0L);
                                    }

                                    LogProcessoDAO.getInstance().insertLogProcesso("pomContext.getCecCTR().delPreCECAberto();\n" +
                                            "                                    pomContext.getConfigCTR().setPosicaoTela(2L);\n" +
                                            "                                    Intent it = new Intent(MenuPrincECMActivity.this, OSActivity.class);", getLocalClassName());
                                    pomContext.getCecCTR().delPreCECAberto();
                                    pomContext.getConfigCTR().setPosicaoTela(2L);
                                    Intent it = new Intent(MenuPrincECMActivity.this, OSActivity.class);
                                    startActivity(it);
                                    finish();

                                }

                            });

                            alerta.show();

                        } else if (motoMecBean.getCodFuncaoOperMotoMec() == 6) { // PESAGEM

                            LogProcessoDAO.getInstance().insertLogProcesso("} else if (motoMecBean.getCodFuncaoOperMotoMec() == 6) { ", getLocalClassName());
                            if(!pomContext.getMotoMecFertCTR().verifBackupApont()){

                                LogProcessoDAO.getInstance().insertLogProcesso("if(!pomContext.getMotoMecFertCTR().verifBackupApont()){\n" +
                                        "                            if (connectNetwork) {\n" +
                                        "                                pomContext.getConfigCTR().setStatusConConfig(1L);\n" +
                                        "                            } else {\n" +
                                        "                                pomContext.getConfigCTR().setStatusConConfig(0L);\n" +
                                        "                            }", getLocalClassName());
                                if (connectNetwork) {
                                    pomContext.getConfigCTR().setStatusConConfig(1L);
                                } else {
                                    pomContext.getConfigCTR().setStatusConConfig(0L);
                                }

                                LogProcessoDAO.getInstance().insertLogProcesso("pomContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude(), getLocalClassName());\n" +
                                        "textViewStatus.setText(pomContext.getMotoMecFertCTR().getUltApont());", getLocalClassName());
                                pomContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude(), getLocalClassName());
                                textViewStatus.setText(pomContext.getMotoMecFertCTR().getUltApont());

                            }

                            LogProcessoDAO.getInstance().insertLogProcesso("progressBar = new ProgressDialog(v.getContext());\n" +
                                    "                            progressBar.setCancelable(true);\n" +
                                    "                            progressBar.setMessage(\"BUSCANDO BOLETIM...\");\n" +
                                    "                            progressBar.show();\n" +
                                    "                            pomContext.getCecCTR().delPreCECAberto();\n" +
                                    "                            pomContext.getCecCTR().verCEC(MenuPrincECMActivity.this, CECActivity.class, progressBar);", getLocalClassName());
                            progressBar = new ProgressDialog(v.getContext());
                            progressBar.setCancelable(true);
                            progressBar.setMessage("BUSCANDO BOLETIM...");
                            progressBar.show();

                            pomContext.getCecCTR().delPreCECAberto();
                            pomContext.getCecCTR().verCEC(MenuPrincECMActivity.this, CECActivity.class, progressBar);

                        } else if ((motoMecBean.getCodFuncaoOperMotoMec() == 8)
                                || (motoMecBean.getCodFuncaoOperMotoMec() == 19)) { // DESENGATE

                            LogProcessoDAO.getInstance().insertLogProcesso("} else if ((motoMecBean.getCodFuncaoOperMotoMec() == 8)\n" +
                                    "                                || (motoMecBean.getCodFuncaoOperMotoMec() == 19)) { // DESENGATE", getLocalClassName());
                            if (pomContext.getMotoMecFertCTR().hasElemCarreta()) {

                                LogProcessoDAO.getInstance().insertLogProcesso("if (pomContext.getMotoMecFertCTR().hasElemCarreta()) {\n" +
                                        "                                AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);\n" +
                                        "                                alerta.setTitle(\"ATENÇÃO\");\n" +
                                        "                                alerta.setMessage(\"DESEJA REALMENTE DESENGATAR AS CARRETAS?\");", getLocalClassName());
                                AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage("DESEJA REALMENTE DESENGATAR AS CARRETAS?");
                                alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                                                "                                    @Override\n" +
                                                "                                    public void onClick(DialogInterface dialog, int which) {\n" +
                                                "                                        pomContext.getConfigCTR().setPosicaoTela(19L);\n" +
                                                "                                        Intent it = new Intent(MenuPrincECMActivity.this, DesengCarretaActivity.class);", getLocalClassName());
                                        pomContext.getConfigCTR().setPosicaoTela(19L);
                                        Intent it = new Intent(MenuPrincECMActivity.this, DesengCarretaActivity.class);
                                        startActivity(it);
                                        finish();
                                    }
                                });

                                alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"NÃO\", new DialogInterface.OnClickListener() {\n" +
                                                "                                    @Override\n" +
                                                "                                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                                    }
                                });

                                alerta.show();

                            } else {

                                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                        "                                AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);\n" +
                                        "                                alerta.setTitle(\"ATENÇÃO\");\n" +
                                        "                                alerta.setMessage(\"POR FAVOR! ENGATE CARRETA(S) PARA DEPOIS DESENGATÁ-LAS.\");\n" +
                                        "                                alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                        "                                    @Override\n" +
                                        "                                    public void onClick(DialogInterface dialog, int which) {\n" +
                                        "                                    }\n" +
                                        "                                });\n" +
                                        "                                alerta.show();", getLocalClassName());
                                AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage("POR FAVOR! ENGATE CARRETA(S) PARA DEPOIS DESENGATÁ-LAS.");
                                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                                alerta.show();

                            }

                        } else if ((motoMecBean.getCodFuncaoOperMotoMec() == 9)
                                || (motoMecBean.getCodFuncaoOperMotoMec() == 20)) { // ENGATE

                            LogProcessoDAO.getInstance().insertLogProcesso("} else if ((motoMecBean.getCodFuncaoOperMotoMec() == 9)\n" +
                                    "                                || (motoMecBean.getCodFuncaoOperMotoMec() == 20)) {", getLocalClassName());
                            if (!pomContext.getMotoMecFertCTR().hasElemCarreta()) {

                                LogProcessoDAO.getInstance().insertLogProcesso("if (!pomContext.getMotoMecFertCTR().hasElemCarreta()) {\n" +
                                        "                                AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);\n" +
                                        "                                alerta.setTitle(\"ATENÇÃO\");\n" +
                                        "                                alerta.setMessage(\"DESEJA REALMENTE ENGATAR AS CARRETAS?\");", getLocalClassName());
                                AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage("DESEJA REALMENTE ENGATAR AS CARRETAS?");
                                alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                                                "                                    @Override\n" +
                                                "                                    public void onClick(DialogInterface dialog, int which) {\n" +
                                                "                                        pomContext.getConfigCTR().setPosicaoTela(20L);\n" +
                                                "                                        Intent it = new Intent(MenuPrincECMActivity.this, MsgNumCarretaActivity.class);", getLocalClassName());
                                        pomContext.getConfigCTR().setPosicaoTela(20L);
                                        Intent it = new Intent(MenuPrincECMActivity.this, MsgNumCarretaActivity.class);
                                        startActivity(it);
                                        finish();
                                    }
                                });

                                alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"NÃO\", new DialogInterface.OnClickListener() {\n" +
                                                "                                    @Override\n" +
                                                "                                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                                    }
                                });

                                alerta.show();

                            } else {

                                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                        "                                AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);\n" +
                                        "                                alerta.setTitle(\"ATENÇÃO\");\n" +
                                        "                                alerta.setMessage(\"POR FAVOR! DESENGATE CARRETA(S) PARA DEPOIS ENGATÁ-LAS.\");\n" +
                                        "                                alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                        "                                    @Override\n" +
                                        "                                    public void onClick(DialogInterface dialog, int which) {\n" +
                                        "                                    }\n" +
                                        "                                });\n" +
                                        "                                alerta.show();", getLocalClassName());
                                AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage("POR FAVOR! DESENGATE CARRETA(S) PARA DEPOIS ENGATÁ-LAS.");
                                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                alerta.show();

                            }

                        }

                    }

                }



            }

        });

        buttonRetMotoMec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonRetMotoMec.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if (!pomContext.getMotoMecFertCTR().hasApontBolAberto()) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (!pomContext.getMotoMecFertCTR().hasApontBolAberto()) {\n" +
                            "                    Toast.makeText(MenuPrincECMActivity.this, \"POR FAVOR! INSIRA OS APONTAMENTOS AO BOLETIM!\",\n" +
                            "                            Toast.LENGTH_LONG).show();", getLocalClassName());
                    Toast.makeText(MenuPrincECMActivity.this, "POR FAVOR! INSIRA OS APONTAMENTOS AO BOLETIM!",
                            Toast.LENGTH_LONG).show();
                }
                else {

                    LogProcessoDAO.getInstance().insertLogProcesso("else {\n" +
                            "                    AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);\n" +
                            "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                    alerta.setMessage(\"DESEJA REALMENTE ENCERRA O BOLETIM?\");", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(MenuPrincECMActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("DESEJA REALMENTE ENCERRA O BOLETIM?");
                    alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                                    "                        @Override\n" +
                                    "                        public void onClick(DialogInterface dialog, int which) {\n" +
                                    "                            pomContext.getConfigCTR().setPosicaoTela(4L);\n" +
                                    "                            Intent it = new Intent(MenuPrincECMActivity.this, HorimetroActivity.class);", getLocalClassName());
                            pomContext.getConfigCTR().setPosicaoTela(4L);
                            Intent it = new Intent(MenuPrincECMActivity.this, HorimetroActivity.class);
                            startActivity(it);
                            finish();
                        }
                    });

                    alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"NÃO\", new DialogInterface.OnClickListener() {\n" +
                                    "                        @Override\n" +
                                    "                        public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                        }
                    });

                    alerta.show();

                }

            }
        });

        buttonParadaMotoMec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonParadaMotoMec.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                Intent it = new Intent(MenuPrincECMActivity.this, MenuParadaECMActivity.class);", getLocalClassName());
                Intent it = new Intent(MenuPrincECMActivity.this, ListaParadaECMActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonLogMotoMec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonLogMotoMec.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                pomContext.getConfigCTR().setPosicaoTela(24L);\n" +
                        "                Intent it = new Intent(MenuPrincECMActivity.this, SenhaActivity.class);", getLocalClassName());
                pomContext.getConfigCTR().setPosicaoTela(24L);
                Intent it = new Intent(MenuPrincECMActivity.this, SenhaActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed() {
    }

}