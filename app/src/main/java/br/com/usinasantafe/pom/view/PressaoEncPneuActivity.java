package br.com.usinasantafe.pom.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.usinasantafe.pom.POMContext;
import br.com.usinasantafe.pom.R;
import br.com.usinasantafe.pom.model.dao.LogProcessoDAO;

public class PressaoEncPneuActivity extends ActivityGeneric {

    private POMContext pomContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressao_enc_pneu);

        pomContext = (POMContext) getApplication();

        Button buttonOkPressaoEnc = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancPressaoEnc = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkPressaoEnc.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("rawtypes")
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonOkPressaoEnc.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @SuppressWarnings(\"rawtypes\")\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if (!editTextPadrao.getText().toString().equals("")) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (!editTextPadrao.getText().toString().equals(\"\")) {\n" +
                            "                    Long qtde = Long.parseLong(editTextPadrao.getText().toString());", getLocalClassName());
                    Long qtde = Long.parseLong(editTextPadrao.getText().toString());
                    if (qtde < 1000) {

                        LogProcessoDAO.getInstance().insertLogProcesso("if (qtde < 1000) {\n" +
                                "                        pomContext.getMotoMecFertCTR().getItemMedPneuDAO().getItemMedPneuBean().setPressaoEncItemMedPneu(qtde);\n" +
                                "                        Intent it = new Intent(PressaoEncPneuActivity.this, PressaoColPneuActivity.class);", getLocalClassName());
                        pomContext.getMotoMecFertCTR().getItemMedPneuDAO().getItemMedPneuBean().setPressaoEncItemCalibPneu(qtde);
                        Intent it = new Intent(PressaoEncPneuActivity.this, PressaoColPneuActivity.class);
                        startActivity(it);
                        finish();

                    } else {

                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "                        AlertDialog.Builder alerta = new AlertDialog.Builder(PressaoEncPneuActivity.this);\n" +
                                "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                                "                        alerta.setMessage(\"VALOR DE CALIBRAGEM ACIMA DO PERMITIDO! FAVOR VERIFICA A MESMA.\");\n" +
                                "                        alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                "                            @Override\n" +
                                "                            public void onClick(DialogInterface dialog, int which) {\n" +
                                "                            }\n" +
                                "                        });\n" +
                                "                        alerta.show();", getLocalClassName());
                        AlertDialog.Builder alerta = new AlertDialog.Builder(PressaoEncPneuActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("VALOR DE CALIBRAGEM ACIMA DO PERMITIDO! FAVOR VERIFICA A MESMA.");
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

        buttonCancPressaoEnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonCancPressaoEnc.setOnClickListener(new View.OnClickListener() {\n" +
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

    @Override
    public void onBackPressed()  {
        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed()  {\n" +
                "        Intent it = new Intent(PressaoEncPneuActivity.this, PneuActivity.class);", getLocalClassName());
        Intent it = new Intent(PressaoEncPneuActivity.this, PneuActivity.class);
        startActivity(it);
        finish();
    }

}