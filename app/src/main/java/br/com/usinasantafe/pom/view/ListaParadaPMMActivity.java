package br.com.usinasantafe.pom.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pom.POMContext;
import br.com.usinasantafe.pom.R;
import br.com.usinasantafe.pom.model.bean.estaticas.ParadaBean;
import br.com.usinasantafe.pom.model.bean.estaticas.RFuncaoAtivParBean;
import br.com.usinasantafe.pom.model.dao.LogProcessoDAO;

public class ListaParadaPMMActivity extends ActivityGeneric {

    private ListView paradaListView;
    private POMContext pomContext;
    private List<ParadaBean> paradaList;
    private ProgressDialog progressBar;
    private ArrayAdapter<String> adapter;
    private String paradaString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_parada_pmm);

        pomContext = (POMContext) getApplication();

        Button buttonAtualParada = findViewById(R.id.buttonAtualParada);
        Button buttonRetMenuParada = findViewById(R.id.buttonRetMenuParada);
        EditText editPesqListParada = findViewById(R.id.editPesqListParada);

        LogProcessoDAO.getInstance().insertLogProcesso("paradaList = pomContext.getMotoMecFertCTR().getListParada();", getLocalClassName());
        paradaList = pomContext.getMotoMecFertCTR().getListParada();

        LogProcessoDAO.getInstance().insertLogProcesso("String itens[] = new String[paradaList.size()];\n" +
                "        for (int i = 0; i < paradaList.size(); i++) {\n" +
                "            ParadaBean paradaBean = (ParadaBean) paradaList.get(i);\n" +
                "            itens[i] = paradaBean.getCodParada() + \" - \" + paradaBean.getDescrParada();\n" +
                "        }", getLocalClassName());
        String itens[] = new String[paradaList.size()];

        for (int i = 0; i < paradaList.size(); i++) {
            ParadaBean paradaBean = paradaList.get(i);
            itens[i] = paradaBean.getCodParada() + " - " + paradaBean.getDescrParada();
        }

        LogProcessoDAO.getInstance().insertLogProcesso("adapter = new ArrayAdapter<String>(this, R.layout.activity_item_lista, R.id.textViewItemList, itens);\n" +
                "        paradaListView = findViewById(R.id.listViewMotParada);\n" +
                "        paradaListView.setAdapter(adapter);", getLocalClassName());
        adapter = new ArrayAdapter<String>(this, R.layout.activity_item_lista, R.id.textViewItemList, itens);
        paradaListView = findViewById(R.id.listViewMotParada);
        paradaListView.setAdapter(adapter);

        editPesqListParada.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                ListaParadaPMMActivity.this.adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {

            }
        });

        buttonAtualParada.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualParada.setOnClickListener(new View.OnClickListener() {\n" +
                        "\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if (connectNetwork) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                            "                    progressBar = new ProgressDialog(ListaParadaActivity.this);\n" +
                            "                    progressBar.setCancelable(true);\n" +
                            "                    progressBar.setMessage(\"ATUALIZANDO ...\");\n" +
                            "                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);\n" +
                            "                    progressBar.setProgress(0);\n" +
                            "                    progressBar.setMax(100);\n" +
                            "                    progressBar.show();", getLocalClassName());

                    progressBar = new ProgressDialog(ListaParadaPMMActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    LogProcessoDAO.getInstance().insertLogProcesso("pomContext.getMotoMecFertCTR().atualDados(ListaParadaActivity.this, ListaParadaActivity.class, progressBar, \"Parada\", 1, getLocalClassName());", getLocalClassName());
                    pomContext.getMotoMecFertCTR().atualDados(ListaParadaPMMActivity.this, ListaParadaPMMActivity.class, progressBar, "Parada", 1, getLocalClassName());

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder(ListaParadaActivity.this);\n" +
                            "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                    alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                            "                    alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                            "                        @Override\n" +
                            "                        public void onClick(DialogInterface dialog, int which) {\n" +
                            "                        }\n" +
                            "                    });\n" +
                            "                    alerta.show();", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaParadaPMMActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alerta.show();

                }

            }
        });

        buttonRetMenuParada.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonRetMenuParada.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                Intent it = new Intent(ListaParadaActivity.this, ListaAtividadeActivity.class);", getLocalClassName());
                Intent it = new Intent(ListaParadaPMMActivity.this, ListaAtividadeActivity.class);
                startActivity(it);
                finish();

            }
        });

        paradaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                LogProcessoDAO.getInstance().insertLogProcesso("paradaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                        "                                    long id) {\n" +
                        "                TextView textView = (TextView) v.findViewById(R.id.textViewItemList);\n" +
                        "                paradaString = textView.getText().toString();\n" +
                        "                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaParadaActivity.this);\n" +
                        "                alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                String label = \"DESEJA REALMENTE REALIZAR A PARADA '\" + paradaString + \"' ?\";\n" +
                        "                alerta.setMessage(label);", getLocalClassName());

                TextView textView = v.findViewById(R.id.textViewItemList);
                paradaString = textView.getText().toString();

                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaParadaPMMActivity.this);
                alerta.setTitle("ATENÇÃO");
                String label = "DESEJA REALMENTE REALIZAR A PARADA '" + paradaString + "' ?";
                alerta.setMessage(label);
                alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                                "                    @Override\n" +
                                "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                        if (pomContext.getMotoMecFertCTR().verDataHoraInsApontMMFert()) {
                            LogProcessoDAO.getInstance().insertLogProcesso("if (pomContext.getMotoMecFertCTR().verDataHoraInsApontMMFert()) {\n" +
                                    "                            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaParadaActivity.this);\n" +
                                    "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                            alerta.setMessage(\"POR FAVOR, AGUARDE UM MINUTO ANTES DE REALIZAR UM NOVO APONTAMENTO.\");", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaParadaPMMActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("POR FAVOR, AGUARDE UM MINUTO ANTES DE REALIZAR UM NOVO APONTAMENTO.");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                            "                                @Override\n" +
                                            "                                public void onClick(DialogInterface dialog, int which) {\n" +
                                            "                                    Intent it = new Intent(ListaParadaActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                                    Intent it = new Intent(ListaParadaPMMActivity.this, MenuPrincPMMActivity.class);
                                    startActivity(it);
                                    finish();
                                }
                            });
                            alerta.show();

                        } else {
                            LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
                            if (pomContext.getMotoMecFertCTR().verifBackupApont(pomContext.getMotoMecFertCTR().getParadaBean(paradaString).getIdParada())) {
                                LogProcessoDAO.getInstance().insertLogProcesso("if (pomContext.getMotoMecFertCTR().verifBackupApont(pomContext.getMotoMecFertCTR().getParadaBean(paradaString).getIdParada())) {\n" +
                                        "                                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaParadaActivity.this);\n" +
                                        "                                alerta.setTitle(\"ATENÇÃO\");\n" +
                                        "                                alerta.setMessage(\"PARADA JÁ APONTADA PARA O EQUIPAMENTO!\");", getLocalClassName());
                                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaParadaPMMActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage("PARADA JÁ APONTADA PARA O EQUIPAMENTO!");
                                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                                "                                    @Override\n" +
                                                "                                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                                    }
                                });
                                alerta.show();
                            } else {

                                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                        "                                pomContext.getConfigCTR().clearDadosFert();\n" +
                                        "                                List<RFuncaoAtivParBean> rFuncaoAtivParList = pomContext.getMotoMecFertCTR().getFuncaoParadaList(pomContext.getMotoMecFertCTR().getParadaBean(paradaString).getIdParada(), getLocalClassName());\n" +
                                        "                                boolean calibragem = false;\n" +
                                        "                                for (int i = 0; i < rFuncaoAtivParList.size(); i++) {\n" +
                                        "                                    RFuncaoAtivParBean rFuncaoAtivParBean = rFuncaoAtivParList.get(i);\n" +
                                        "                                    if (rFuncaoAtivParBean.getCodFuncao() == 3) {\n" +
                                        "                                        calibragem = true;\n" +
                                        "                                    }\n" +
                                        "                                }\n" +
                                        "                                rFuncaoAtivParList.clear();", getLocalClassName());
                                pomContext.getConfigCTR().clearDadosFert();
                                List<RFuncaoAtivParBean> rFuncaoAtivParList = pomContext.getMotoMecFertCTR().getFuncaoParadaList(pomContext.getMotoMecFertCTR().getParadaBean(paradaString).getIdParada(), getLocalClassName());
                                boolean calibragem = false;
                                for (int i = 0; i < rFuncaoAtivParList.size(); i++) {
                                    RFuncaoAtivParBean rFuncaoAtivParBean = rFuncaoAtivParList.get(i);
                                    if (rFuncaoAtivParBean.getCodFuncao() == 3) {
                                        calibragem = true;
                                    }
                                }
                                rFuncaoAtivParList.clear();

                                if(calibragem){
                                    LogProcessoDAO.getInstance().insertLogProcesso("if(calibragem){\n" +
                                            "                                    pomContext.getMotoMecFertCTR().salvarParadaPneu(pomContext.getMotoMecFertCTR().getParadaBean(paradaString).getIdParada(), 0L, getLongitude(), getLatitude(), getLocalClassName());\n" +
                                            "                                    pomContext.getMotoMecFertCTR().salvarBoletimPneu();\n" +
                                            "                                    Intent it = new Intent(ListaParadaPMMActivity.this, ListaPosPneuActivity.class);", getLocalClassName());
                                    pomContext.getMotoMecFertCTR().salvarParadaCalibragem(pomContext.getMotoMecFertCTR().getParadaBean(paradaString).getIdParada(), 0L, getLongitude(), getLatitude(), getLocalClassName());
                                    pomContext.getMotoMecFertCTR().salvarBoletimPneu();
                                    Intent it = new Intent(ListaParadaPMMActivity.this, ListaPosPneuActivity.class);
                                    startActivity(it);
                                    finish();
                                } else {
                                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                            "                                    pomContext.getMotoMecFertCTR().salvarApont(pomContext.getMotoMecFertCTR().getParadaBean(paradaString).getIdParada(), 0L, getLongitude(), getLatitude(), getLocalClassName());\n" +
                                            "                                    Intent it = new Intent(ListaParadaPMMActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                                    pomContext.getMotoMecFertCTR().salvarApont(pomContext.getMotoMecFertCTR().getParadaBean(paradaString).getIdParada(), 0L, getLongitude(), getLatitude(), getLocalClassName());
                                    Intent it = new Intent(ListaParadaPMMActivity.this, MenuPrincPMMActivity.class);
                                    startActivity(it);
                                    finish();
                                }

                            }

                        }

                        paradaList.clear();

                    }

                });

                alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"NÃO\", new DialogInterface.OnClickListener() {\n" +
                                "                    @Override\n" +
                                "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                    }

                });

                alerta.show();

            }

        });

    }

    public void onBackPressed() {
    }

}
