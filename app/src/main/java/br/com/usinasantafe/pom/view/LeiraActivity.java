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

public class LeiraActivity extends ActivityGeneric {

    private POMContext pomContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leira);

        pomContext = (POMContext) getApplication();

        Button buttonOkLeira = findViewById(R.id.buttonOkPadrao);
        Button buttonCancLeira = findViewById(R.id.buttonCancPadrao);
        Button buttonAtualPadrao = findViewById(R.id.buttonAtualPadrao);

        buttonAtualPadrao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualPadrao.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                AlertDialog.Builder alerta = new AlertDialog.Builder(LeiraActivity.this);\n" +
                        "                alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());

                AlertDialog.Builder alerta = new AlertDialog.Builder(LeiraActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        LogProcessoDAO.getInstance().insertLogProcesso("                alerta.setNegativeButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                                "                    @Override\n" +
                                "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName());

                        if (connectNetwork) {

                            LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                                    "                            progressBar = new ProgressDialog(LeiraActivity.this);\n" +
                                    "                            progressBar.setCancelable(true);\n" +
                                    "                            progressBar.setMessage(\"ATUALIZANDO ...\");\n" +
                                    "                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);\n" +
                                    "                            progressBar.setProgress(0);\n" +
                                    "                            progressBar.setMax(100);\n" +
                                    "                            progressBar.show();", getLocalClassName());

                            progressBar = new ProgressDialog(LeiraActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

                            LogProcessoDAO.getInstance().insertLogProcesso("pomContext.getMotoMecFertCTR().atualDados(LeiraActivity.this, LeiraActivity.class, progressBar, \"Leira\", 1, getLocalClassName());", getLocalClassName());
                            pomContext.getMotoMecFertCTR().atualDados(LeiraActivity.this, LeiraActivity.class, progressBar, "Leira", 1, getLocalClassName());

                        } else {

                            LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder(LeiraActivity.this);\n" +
                                    "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                            alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                                    "                            alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                    "                                @Override\n" +
                                    "                                public void onClick(DialogInterface dialog, int which) {\n" +
                                    "                                }\n" +
                                    "                            });\n" +
                                    "                            alerta.show();", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder(LeiraActivity.this);
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

                alerta.setPositiveButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"NÃO\", new DialogInterface.OnClickListener() {\n" +
                                "                    @Override\n" +
                                "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                    }
                });

                alerta.show();

            }

        });

        buttonOkLeira.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("rawtypes")
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonOkLeira.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @SuppressWarnings(\"rawtypes\")\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if (!editTextPadrao.getText().toString().equals("")) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (!editTextPadrao.getText().toString().equals(\"\")) {", getLocalClassName());
                    if (pomContext.getCompostoCTR().verLeira(Long.parseLong(editTextPadrao.getText().toString()))) {

                        LogProcessoDAO.getInstance().insertLogProcesso("if (pomContext.getCompostoCTR().verLeira(Long.parseLong(editTextPadrao.getText().toString()))){\n" +
                                " if (connectNetwork) {\n" +
                                "                            pomContext.getConfigCTR().setStatusConConfig(1L);\n" +
                                "                        }\n" +
                                "                        else{\n" +
                                "                            pomContext.getConfigCTR().setStatusConConfig(0L);\n" +
                                "                        }", getLocalClassName());

                        if (connectNetwork) {
                            pomContext.getConfigCTR().setStatusConConfig(1L);
                        }
                        else{
                            pomContext.getConfigCTR().setStatusConConfig(0L);
                        }

                        LogProcessoDAO.getInstance().insertLogProcesso("pomContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude(), getLocalClassName());", getLocalClassName());
                        pomContext.getMotoMecFertCTR().salvarApontLeira(getLongitude(), getLatitude(), getLocalClassName());

                        if (pomContext.getConfigCTR().getConfig().getFuncaoComposto() == 2L) {
                            LogProcessoDAO.getInstance().insertLogProcesso("if (pomContext.getConfigCTR().getConfig().getFuncaoCompostagem() == 2L) {\n" +
                                    "                            pomContext.getCompostoCTR().salvarLeiraDescarreg(Long.parseLong(" + editTextPadrao.getText().toString() + "));", getLocalClassName());
                            pomContext.getCompostoCTR().salvarLeiraDescarreg(Long.parseLong(editTextPadrao.getText().toString()));
                        } else if (pomContext.getConfigCTR().getConfig().getFuncaoComposto() == 3L) {
                            LogProcessoDAO.getInstance().insertLogProcesso("} else if (pomContext.getConfigCTR().getConfig().getFuncaoCompostagem() == 3L) {\n" +
                                    "                            pomContext.getCompostoCTR().abrirCarregComposto(Long.parseLong(" + editTextPadrao.getText().toString() + "));", getLocalClassName());
                            pomContext.getCompostoCTR().abrirCarregComposto(Long.parseLong(editTextPadrao.getText().toString()));
                        }

                        LogProcessoDAO.getInstance().insertLogProcesso("pomContext.getMotoMecFertCTR().fecharApont();\n" +
                                "                        Intent it = new Intent(LeiraActivity.this, MenuPrincPCOMPActivity.class);", getLocalClassName());
                        pomContext.getMotoMecFertCTR().fecharApont(getLocalClassName());
                        Intent it = new Intent(LeiraActivity.this, MenuPrincPCOMPActivity.class);
                        startActivity(it);
                        finish();

                    } else {

                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "AlertDialog.Builder alerta = new AlertDialog.Builder(LeiraActivity.this);\n" +
                                "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                                "                        alerta.setMessage(\"NUMERAÇÃO DO LEIRA INEXISTENTE! FAVOR VERIFICA A MESMA.\");\n" +
                                "                        alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                "                            @Override\n" +
                                "                            public void onClick(DialogInterface dialog, int which) {\n" +
                                "                            }\n" +
                                "                        });\n" +
                                "                        alerta.show();", getLocalClassName());
                        AlertDialog.Builder alerta = new AlertDialog.Builder(LeiraActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("NUMERAÇÃO DO LEIRA INEXISTENTE! FAVOR VERIFICA A MESMA.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        alerta.show();

                    }
                }

            }

        });

        buttonCancLeira.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonCancLeira.setOnClickListener(new View.OnClickListener() {\n" +
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

    public void onBackPressed() {
        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed() {\n" +
                "        Intent it = new Intent(LeiraActivity.this, MenuPrincPCOMPActivity.class);", getLocalClassName());
        Intent it = new Intent(LeiraActivity.this, MenuPrincPCOMPActivity.class);
        startActivity(it);
        finish();
    }

}