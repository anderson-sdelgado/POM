package br.com.usinasantafe.pom.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.usinasantafe.pom.POMContext;
import br.com.usinasantafe.pom.R;
import br.com.usinasantafe.pom.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pom.util.VerifDadosServ;

public class OSActivity extends ActivityGeneric {

    private POMContext pmmContext;
    private ProgressDialog progressBar;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_os);

        pmmContext = (POMContext) getApplication();

        Button buttonOkOS = findViewById(R.id.buttonOkPadrao);
        Button buttonCancOS = findViewById(R.id.buttonCancPadrao);
        EditText editText = findViewById(R.id.editTextPadrao);

        LogProcessoDAO.getInstance().insertLogProcesso("editText.setText(\"\");", getLocalClassName());
        editText.setText("");

        buttonOkOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonOkOS.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());

                if (!editTextPadrao.getText().toString().equals("")) {

                    LogProcessoDAO.getInstance().insertLogProcesso(" if (!editTextPadrao.getText().toString().equals(\"\")) {\n" +
                            "Long nroOS = Long.parseLong(" + editTextPadrao.getText().toString() + ");\n" +
                            "                    pmmContext.getConfigCTR().setOsConfig(nroOS);", getLocalClassName());
                    Long nroOS = Long.parseLong(editTextPadrao.getText().toString());
                    pmmContext.getConfigCTR().setNroOSConfig(nroOS);

                    LogProcessoDAO.getInstance().insertLogProcesso("else {", getLocalClassName());
                    if (pmmContext.getConfigCTR().verOS(nroOS)) {

                        LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getConfigCTR().verROSAtiv(nroOS)) {\n" +
                                "if (connectNetwork) {\n" +
                                "                                pmmContext.getConfigCTR().setStatusConConfig(1L);\n" +
                                "                            }\n" +
                                "                            else{\n" +
                                "                                pmmContext.getConfigCTR().setStatusConConfig(0L);\n" +
                                "                            }", getLocalClassName());
                        if (connectNetwork) {
                            pmmContext.getConfigCTR().setStatusConConfig(1L);
                        }
                        else{
                            pmmContext.getConfigCTR().setStatusConConfig(0L);
                        }

                        LogProcessoDAO.getInstance().insertLogProcesso("Intent it = new Intent(OSActivity.this, ListaAtividadeActivity.class);", getLocalClassName());
                        Intent it = new Intent(OSActivity.this, ListaAtividadeActivity.class);
                        startActivity(it);
                        finish();

                    } else {

                        LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
                        if (connectNetwork) {

                            LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                                    "progressBar = new ProgressDialog(v.getContext());\n" +
                                    "                                progressBar.setCancelable(true);\n" +
                                    "                                progressBar.setMessage(\"PESQUISANDO OS...\");\n" +
                                    "                                progressBar.show();\n" +
                                    "                                customHandler.postDelayed(updateTimerThread, 10000);", getLocalClassName());
                            progressBar = new ProgressDialog(v.getContext());
                            progressBar.setCancelable(true);
                            progressBar.setMessage("PESQUISANDO OS...");
                            progressBar.show();

                            customHandler.postDelayed(updateTimerThread, 10000);

                            LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getMotoMecFertCTR().verOS(" + editTextPadrao.getText().toString() + "\n" +
                                    "                                        , OSActivity.this, ListaAtividadeActivity.class, progressBar);", getLocalClassName());
                            pmmContext.getMotoMecFertCTR().verOS(editTextPadrao.getText().toString()
                                    , OSActivity.this, ListaAtividadeActivity.class, progressBar, getLocalClassName());

                        } else {

                            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                    "pmmContext.getConfigCTR().setStatusConConfig(0L);\n" +
                                    "Intent it = new Intent(OSActivity.this, ListaAtividadeActivity.class);", getLocalClassName());

                            pmmContext.getConfigCTR().setStatusConConfig(0L);
                            Intent it = new Intent(OSActivity.this, ListaAtividadeActivity.class);
                            startActivity(it);
                            finish();

                        }

                    }

                }
            }
        });

        buttonCancOS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonCancOS.setOnClickListener(new View.OnClickListener() {\n" +
                        "\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "if (editTextPadrao.getText().toString().length() > 0) {\n" +
                        "                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));\n" +
                        "                }", getLocalClassName());
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed()  {
        LogProcessoDAO.getInstance().insertLogProcesso("onBackPressed()\n" +
                "Intent it = new Intent(OSActivity.this, ListaTurnoActivity.class);", getLocalClassName());
        Intent it = new Intent(OSActivity.this, ListaTurnoActivity.class);
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

                LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getConfigCTR().setStatusConConfig(0L);\n" +
                        "                Intent it = new Intent(OSActivity.this, ListaAtividadeActivity.class);", getLocalClassName());
                pmmContext.getConfigCTR().setStatusConConfig(0L);
                Intent it = new Intent(OSActivity.this, ListaAtividadeActivity.class);
                startActivity(it);
                finish();

            }

        }
    };

}
