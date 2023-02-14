package br.com.usinasantafe.pom.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import br.com.usinasantafe.pom.POMContext;
import br.com.usinasantafe.pom.R;
import br.com.usinasantafe.pom.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pom.util.VerifDadosServ;

public class OSMecanActivity extends ActivityGeneric {

    private POMContext pomContext;
    private ProgressDialog progressBar;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_os_mecan);

        pomContext = (POMContext) getApplication();

        Button buttonOkOS = findViewById(R.id.buttonOkPadrao);
        Button buttonCancOS = findViewById(R.id.buttonCancPadrao);

        buttonOkOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonOkOS.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());

                if (!editTextPadrao.getText().toString().equals("")) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (!editTextPadrao.getText().toString().equals(\"\")) {\n" +
                            "                    pomContext.getMecanicoCTR().getApontMecanDAO().setApontMecanBean();\n" +
                            "                    pomContext.getMecanicoCTR().getApontMecanDAO().getApontMecanBean().setOsApont(Long.parseLong(editTextPadrao.getText().toString()));\n", getLocalClassName());
                    pomContext.getMecanicoCTR().getApontMecanDAO().setApontMecanBean();
                    pomContext.getMecanicoCTR().getApontMecanDAO().getApontMecanBean().setOsApontMecan(Long.parseLong(editTextPadrao.getText().toString()));

                    try {

                        LogProcessoDAO.getInstance().insertLogProcesso("try {", getLocalClassName());

                        if(pomContext.getMecanicoCTR().verOSMecanBD(Long.parseLong(editTextPadrao.getText().toString()))) {

                            LogProcessoDAO.getInstance().insertLogProcesso("if(pomContext.getMecanicoCTR().verOSBD(Long.parseLong(editTextPadrao.getText().toString()))) {\n" +
                                    "                            Intent it = new Intent(OSMecanActivity.this, ListaItemOSMecanActivity.class);", getLocalClassName());
                            Intent it = new Intent(OSMecanActivity.this, ListaItemOSMecanActivity.class);
                            startActivity(it);
                            finish();

                        }
                        else{

                            LogProcessoDAO.getInstance().insertLogProcesso("}\n" +
                                    "                        else{", getLocalClassName());
                            if (connectNetwork) {

                                progressBar = new ProgressDialog(v.getContext());
                                progressBar.setCancelable(true);
                                progressBar.setMessage("Pequisando a OS...");
                                progressBar.show();

                                customHandler.postDelayed(updateTimerThread, 10000);

                                LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                                        "                                progressBar = new ProgressDialog(v.getContext());\n" +
                                        "                                progressBar.setCancelable(true);\n" +
                                        "                                progressBar.setMessage(\"Pequisando a OS...\");\n" +
                                        "                                progressBar.show();\n" +
                                        "                                customHandler.postDelayed(updateTimerThread, 10000);\n" +
                                        "                                pomContext.getMecanicoCTR().verOS(editTextPadrao.getText().toString()\n" +
                                        "                                        , OSMecanActivity.this, ListaItemOSMecanActivity.class, progressBar, getLocalClassName());", getLocalClassName());
                                pomContext.getMecanicoCTR().verOSMecan(editTextPadrao.getText().toString()
                                        , OSMecanActivity.this, ListaItemOSMecanActivity.class, progressBar);

                            } else {

                                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                        "                                Intent it = new Intent(OSMecanActivity.this, DigItemOSMecanActivity.class);", getLocalClassName());
                                Intent it = new Intent(OSMecanActivity.this, DigItemOSMecanActivity.class);
                                startActivity(it);
                                finish();

                            }

                        }

                    } catch (Exception e) {

                        LogProcessoDAO.getInstance().insertLogProcesso("} catch (Exception e) {", getLocalClassName());
                        AlertDialog.Builder alerta = new AlertDialog.Builder(OSMecanActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("O.S. INCORRETA OU INEXISTENTE! FAVOR VERIFICAR.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder(OSMecanActivity.this);\n" +
                                        "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                                        "                        alerta.setMessage(\"O.S. INCORRETA OU INEXISTENTE! FAVOR VERIFICAR.\");\n" +
                                        "                        alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                        "                            @Override\n" +
                                        "                            public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                            }
                        });

                        alerta.show();

                    }
                }
            }
        });

        buttonCancOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso(" buttonCancOS.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if (editTextPadrao.getText().toString().length() > 0) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (editTextPadrao.getText().toString().length() > 0) {\n" +
                            "                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));", getLocalClassName());
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed() {
        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed() {\n" +
                "        Intent it = new Intent(OSMecanActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
        Intent it = new Intent(OSMecanActivity.this, MenuPrincPMMActivity.class);
        startActivity(it);
        finish();
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            LogProcessoDAO.getInstance().insertLogProcesso("    private Runnable updateTimerThread = new Runnable() {\n" +
                    "        public void run() {", getLocalClassName());
            if(VerifDadosServ.status < 3) {

                LogProcessoDAO.getInstance().insertLogProcesso("VerifDadosServ.getInstance().cancel();\n" +
                        "VerifDadosServ.getInstance().cancel();", getLocalClassName());
                VerifDadosServ.getInstance().cancel();

                if (progressBar.isShowing()) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (progressBar.isShowing()) {\n" +
                            "progressBar.dismiss();", getLocalClassName());
                    progressBar.dismiss();
                }

                LogProcessoDAO.getInstance().insertLogProcesso("Intent it = new Intent(OSMecanActivity.this, DigItemOSMecanActivity.class);", getLocalClassName());
                Intent it = new Intent(OSMecanActivity.this, DigItemOSMecanActivity.class);
                startActivity(it);
                finish();

            }

        }
    };
}