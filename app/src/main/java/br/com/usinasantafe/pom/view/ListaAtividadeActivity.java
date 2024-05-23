package br.com.usinasantafe.pom.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.usinasantafe.pom.POMContext;
import br.com.usinasantafe.pom.R;
import br.com.usinasantafe.pom.model.bean.estaticas.AtividadeBean;
import br.com.usinasantafe.pom.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pom.util.VerifDadosServ;

public class ListaAtividadeActivity extends ActivityGeneric {

    private ListView atividadeListView;
    private POMContext pmmContext;
    private ProgressDialog progressBar;
    private ArrayList ativArrayList;
    private Long nroOS = 0L;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_atividade);

        pmmContext = (POMContext) getApplication();

        Button buttonAtualAtividade = findViewById(R.id.buttonAtualAtividade);
        Button buttonRetAtividade = findViewById(R.id.buttonRetAtividade);
        TextView textViewTituloAtividade = findViewById(R.id.textViewTituloAtividade);

        LogProcessoDAO.getInstance().insertLogProcesso("nroOS =  pmmContext.getConfigCTR().getConfig().getNroOSConfig();", getLocalClassName());
        nroOS =  pmmContext.getConfigCTR().getConfig().getNroOSConfig();

        buttonAtualAtividade.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualAtividade.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            if (connectNetwork) {

                LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                        "                    progressBar = new ProgressDialog(v.getContext());\n" +
                        "                    progressBar.setCancelable(true);\n" +
                        "                    progressBar.setMessage(\"Atualizando Atividades...\");\n" +
                        "                    progressBar.show();\n" +
                        "                    customHandler.postDelayed(updateTimerThread, 10000);", getLocalClassName());
                progressBar = new ProgressDialog(v.getContext());
                progressBar.setCancelable(true);
                progressBar.setMessage("Atualizando Atividades...");
                progressBar.show();

                customHandler.postDelayed(updateTimerThread, 10000);

                LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getMotoMecFertCTR().verAtiv(String.valueOf(" + nroOS + "), ListaAtividadeActivity.this, ListaAtividadeActivity.class, progressBar);", getLocalClassName());
                pmmContext.getMotoMecFertCTR().verAtiv(nroOS, ListaAtividadeActivity.this, ListaAtividadeActivity.class, progressBar);

            } else {

                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "AlertDialog.Builder alerta = new AlertDialog.Builder(ListaAtividadeActivity.this);\n" +
                        "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                    alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                        "                    alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                        "                        @Override\n" +
                        "                        public void onClick(DialogInterface dialog, int which) {\n" +
                        "                        }\n" +
                        "                    });\n" +
                        "                    alerta.show();", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaAtividadeActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                alerta.setPositiveButton("OK", (dialog, which) -> {
                });
                alerta.show();

            }

        });

        buttonRetAtividade.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonRetAtividade.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                Intent it = new Intent(ListaAtividadeActivity.this, OSActivity.class);", getLocalClassName());
            Intent it = new Intent(ListaAtividadeActivity.this, OSActivity.class);
            startActivity(it);
            finish();
        });

        LogProcessoDAO.getInstance().insertLogProcesso("textViewTituloAtividade.setText(\"ATIVIDADE PRINCIPAL\");\n" +
                "ativArrayList = pmmContext.getMotoMecFertCTR().getAtivArrayList("+ nroOS +");", getLocalClassName());
        textViewTituloAtividade.setText("ATIVIDADE PRINCIPAL");
        ativArrayList = pmmContext.getMotoMecFertCTR().getAtivArrayList(nroOS, getLocalClassName());

        LogProcessoDAO.getInstance().insertLogProcesso("ArrayList<String> itens = new ArrayList<String>();\n" +
                "        for (int i = 0; i < ativArrayList.size(); i++) {\n" +
                "            AtividadeBean atividadeBean = (AtividadeBean) ativArrayList.get(i);\n" +
                "            itens.add(atividadeBean.getCodAtiv() + \" - \" + atividadeBean.getDescrAtiv());\n" +
                "        }", getLocalClassName());
        ArrayList<String> itens = new ArrayList<>();
        for (int i = 0; i < ativArrayList.size(); i++) {
            AtividadeBean atividadeBean = (AtividadeBean) ativArrayList.get(i);
            itens.add(atividadeBean.getCodAtiv() + " - " + atividadeBean.getDescrAtiv());
        }

        LogProcessoDAO.getInstance().insertLogProcesso("AdapterList adapterList = new AdapterList(this, itens);", getLocalClassName());
        AdapterList adapterList = new AdapterList(this, itens);
        atividadeListView = findViewById(R.id.listAtividade);
        atividadeListView.setAdapter(adapterList);

        atividadeListView.setOnItemClickListener((l, v, position, id) -> {

            LogProcessoDAO.getInstance().insertLogProcesso("atividadeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                    "                                    long id) {", getLocalClassName());

            if(ativArrayList.size() == 0){

                LogProcessoDAO.getInstance().insertLogProcesso("if(ativArrayList.size() == 0){\n" +
                        "AlertDialog.Builder alerta = new AlertDialog.Builder(ListaAtividadeActivity.this);\n" +
                        "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                    alerta.setMessage(\"FALHA NA SELEÇÃO DE ATIVIDADE. POR FAVOR, SELECIONE NOVAMENTE.\");\n" +
                        "                    alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                        "                        @Override\n" +
                        "                        public void onClick(DialogInterface dialog, int which) {\n" +
                        "                            Intent it = new Intent(ListaAtividadeActivity.this, ListaAtividadeActivity.class);\n" +
                        "                            startActivity(it);\n" +
                        "                            finish();\n" +
                        "                        }\n" +
                        "                    });\n" +
                        "                    alerta.show();", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaAtividadeActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("FALHA NA SELEÇÃO DE ATIVIDADE. POR FAVOR, SELECIONE NOVAMENTE.");
                alerta.setPositiveButton("OK", (dialog, which) -> {
                    Intent it = new Intent(ListaAtividadeActivity.this, ListaAtividadeActivity.class);
                    startActivity(it);
                    finish();
                });
                alerta.show();

            } else {

                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "                    AtividadeBean atividadeBean = (AtividadeBean) ativArrayList.get(position);\n" +
                        "                    ativArrayList.clear();\n" +
                        "                    pmmContext.getConfigCTR().setAtivConfig(atividadeBean.getIdAtiv());\n" +
                        "                    Intent it = new Intent(ListaAtividadeActivity.this, HorimetroActivity.class);", getLocalClassName());
                AtividadeBean atividadeBean = (AtividadeBean) ativArrayList.get(position);
                ativArrayList.clear();
                pmmContext.getConfigCTR().setAtivConfig(atividadeBean.getIdAtiv());
                Intent it = new Intent(ListaAtividadeActivity.this, HorimetroActivity.class);
                startActivity(it);
                finish();

            }
        });

    }

    public void onBackPressed() {
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            LogProcessoDAO.getInstance().insertLogProcesso("    private Runnable updateTimerThread = new Runnable() {\n" +
                    "        public void run() {", getLocalClassName());
            if(VerifDadosServ.status < 3) {

                LogProcessoDAO.getInstance().insertLogProcesso("if(VerifDadosServ.status < 3) {\n" +
                        "VerifDadosServ.getInstance().cancel();", getLocalClassName());
                VerifDadosServ.getInstance().cancel();

                if (progressBar.isShowing()) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (progressBar.isShowing()) {\n" +
                            "                    progressBar.dismiss();", getLocalClassName());
                    progressBar.dismiss();
                }

                LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder(ListaAtividadeActivity.this);\n" +
                        "                alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                alerta.setMessage(\"FALHA DE PESQUISA DE ATIVIDADE! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.\");\n" +
                        "                alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                        "                    @Override\n" +
                        "                    public void onClick(DialogInterface dialog, int which) {\n" +
                        "                    }\n" +
                        "                });\n" +
                        "                alerta.show();", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaAtividadeActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("FALHA DE PESQUISA DE ATIVIDADE! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.");
                alerta.setPositiveButton("OK", (dialog, which) -> {});
                alerta.show();

            }

        }
    };

}
