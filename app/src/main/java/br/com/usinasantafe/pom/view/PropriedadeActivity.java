package br.com.usinasantafe.pom.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.usinasantafe.pom.POMContext;
import br.com.usinasantafe.pom.R;
import br.com.usinasantafe.pom.model.dao.LogProcessoDAO;

public class PropriedadeActivity extends ActivityGeneric {

    private POMContext pomContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propriedade);

        pomContext = (POMContext) getApplication();

        Button buttonOkPropriedade = findViewById(R.id.buttonOkPadrao);
        Button buttonCancPropriedade = findViewById(R.id.buttonCancPadrao);
        Button buttonAtualPropriedade = findViewById(R.id.buttonAtualPadrao);

        buttonAtualPropriedade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualPropriedade.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());

                if (connectNetwork) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                            "                    progressBar = new ProgressDialog(PropriedadeActivity.this);\n" +
                            "                    progressBar.setCancelable(true);\n" +
                            "                    progressBar.setMessage(\"ATUALIZANDO ...\");\n" +
                            "                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);\n" +
                            "                    progressBar.setProgress(0);\n" +
                            "                    progressBar.setMax(100);\n" +
                            "                    progressBar.show();", getLocalClassName());

                    progressBar = new ProgressDialog(PropriedadeActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    LogProcessoDAO.getInstance().insertLogProcesso("pomContext.getMotoMecFertCTR().atualDados(PropriedadeActivity.this, PropriedadeActivity.class, progressBar, \"Propriedade\", 1, getLocalClassName());", getLocalClassName());
                    pomContext.getMotoMecFertCTR().atualDados(PropriedadeActivity.this, PropriedadeActivity.class, progressBar, "OS", 1, getLocalClassName());

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("else {\n" +
                            "                    AlertDialog.Builder alerta = new AlertDialog.Builder(PropriedadeActivity.this);\n" +
                            "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                    alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(PropriedadeActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                    "                        @Override\n" +
                                    "                        public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                        }
                    });

                    alerta.show();

                }

            }
        });

        buttonOkPropriedade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonOkPropriedade.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if (!editTextPadrao.getText().toString().equals("")) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (!editTextPadrao.getText().toString().equals(\"\")) {\n" +
                            "                    pomContext.getConfigCTR().setIdPropriedade(pomContext.getConfigCTR().getCodPropriedade(Long.parseLong(editTextPadrao.getText().toString())).getIdPropriedade());", getLocalClassName());
                    pomContext.getConfigCTR().setCodPropriedade(editTextPadrao.getText().toString());

                    if (pomContext.getConfigCTR().verPropriedade(Long.parseLong(editTextPadrao.getText().toString()))) {

                        LogProcessoDAO.getInstance().insertLogProcesso("if (pomContext.getConfigCTR().verPropriedade(Long.parseLong(editTextPadrao.getText().toString()))) {\n" +
                                "Intent it = new Intent(PropriedadeActivity.this, MsgPropriedadeActivity.class);", getLocalClassName());
                        Intent it = new Intent(PropriedadeActivity.this, MsgPropriedadeActivity.class);
                        startActivity(it);
                        finish();

                    } else {

                        LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
                        if (connectNetwork) {

                            LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                                    "                    progressBar = new ProgressDialog(PropriedadeActivity.this);\n" +
                                    "                    progressBar.setCancelable(true);\n" +
                                    "                    progressBar.setMessage(\"ATUALIZANDO ...\");\n" +
                                    "                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);\n" +
                                    "                    progressBar.setProgress(0);\n" +
                                    "                    progressBar.setMax(100);\n" +
                                    "                    progressBar.show();", getLocalClassName());

                            progressBar = new ProgressDialog(PropriedadeActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

                            LogProcessoDAO.getInstance().insertLogProcesso("pomContext.getMotoMecFertCTR().atualDados(PropriedadeActivity.this, PropriedadeActivity.class, progressBar, \"Propriedade\", 3, getLocalClassName(), MsgPropriedadeActivity.class, editTextPadrao.getText().toString());", getLocalClassName());
                            pomContext.getMotoMecFertCTR().atualDados(PropriedadeActivity.this, PropriedadeActivity.class, progressBar, "Propriedade", 3, getLocalClassName(), MsgPropriedadeActivity.class, editTextPadrao.getText().toString());

                        } else {

                            LogProcessoDAO.getInstance().insertLogProcesso("else {\n" +
                                    "                    AlertDialog.Builder alerta = new AlertDialog.Builder(PropriedadeActivity.this);\n" +
                                    "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                    alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder(PropriedadeActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                            "                        @Override\n" +
                                            "                        public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                                }
                            });

                            alerta.show();

                        }

                    }

                }

            }
        });

        buttonCancPropriedade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonCancPropriedade.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                if (editTextPadrao.getText().toString().length() > 0) {\n" +
                        "                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));\n" +
                        "                }", getLocalClassName());
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed()  {
        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed()  {\n" +
                "        Intent it = new Intent(PropriedadeActivity.this, FrenteActivity.class);", getLocalClassName());
        Intent it = new Intent(PropriedadeActivity.this, FrenteActivity.class);
        startActivity(it);
        finish();
    }
}