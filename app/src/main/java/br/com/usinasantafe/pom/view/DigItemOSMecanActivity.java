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

public class DigItemOSMecanActivity extends ActivityGeneric {

    private POMContext pomContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dig_item_os_mecan);

        Button buttonOkItemOSDig = findViewById(R.id.buttonOkPadrao);
        Button buttonCancItemOSDig = findViewById(R.id.buttonCancPadrao);

        pomContext = (POMContext) getApplication();

        buttonOkItemOSDig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonOkItemOSDig.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if (!editTextPadrao.getText().toString().equals("")) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (!editTextPadrao.getText().toString().equals(\"\")) {", getLocalClassName());
                    if(Long.parseLong(editTextPadrao.getText().toString()) < 1000){

                        LogProcessoDAO.getInstance().insertLogProcesso("if(Long.parseLong(editTextPadrao.getText().toString()) < 1000){\n" +
                                "                        pomContext.getMecanicoCTR().salvarApont(Long.parseLong(editTextPadrao.getText().toString()), getLocalClassName());\n" +
                                "                        Intent it = new Intent(DigItemOSMecanActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                        pomContext.getMecanicoCTR().salvarApontMecan(Long.parseLong(editTextPadrao.getText().toString()), getLocalClassName());
                        Intent it = new Intent(DigItemOSMecanActivity.this, MenuPrincPMMActivity.class);
                        startActivity(it);
                        finish();

                    }
                    else{
                        LogProcessoDAO.getInstance().insertLogProcesso("}\n" +
                                "                    else{", getLocalClassName());
                        AlertDialog.Builder alerta = new AlertDialog.Builder(  DigItemOSMecanActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("VALOR ACIMA DO QUE O PERMITIDO. POR FAVOR, VERIFIQUE O VALOR!");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder(  DigItemOSMecanActivity.this);\n" +
                                        "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                                        "                        alerta.setMessage(\"VALOR ACIMA DO QUE O PERMITIDO. POR FAVOR, VERIFIQUE O VALOR!\");\n" +
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

        buttonCancItemOSDig.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonCancItemOSDig.setOnClickListener(new View.OnClickListener() {\n" +
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
                "        Intent it = new Intent(DigItemOSMecanActivity.this, OSMecanActivity.class);", getLocalClassName());
        Intent it = new Intent(DigItemOSMecanActivity.this, OSMecanActivity.class);
        startActivity(it);
        finish();
    }

}