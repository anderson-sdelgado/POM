package br.com.usinasantafe.pom.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.usinasantafe.pom.POMContext;
import br.com.usinasantafe.pom.R;
import br.com.usinasantafe.pom.model.dao.LogProcessoDAO;

public class SenhaActivity extends ActivityGeneric {

    private EditText editTextSenha;
    private POMContext pomContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senha);

        editTextSenha = findViewById(R.id.editTextSenha);
        Button buttonOkSenha = findViewById(R.id.buttonOkSenha);
        Button buttonCancSenha = findViewById(R.id.buttonCancSenha);

        pomContext = (POMContext) getApplication();

        buttonOkSenha.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("unchecked")
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("btOkSenha.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @SuppressWarnings(\"unchecked\")\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if (!pomContext.getConfigCTR().hasElemConfig()) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (!pomContext.getConfigCTR().hasElemConfig()) {\n" +
                            "Intent it = new Intent(SenhaActivity.this, ConfigActivity.class);", getLocalClassName());
                    Intent it = new Intent(SenhaActivity.this, ConfigActivity.class);
                    startActivity(it);
                    finish();
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
                    if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 11L){
                        LogProcessoDAO.getInstance().insertLogProcesso("if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 11L){", getLocalClassName());
                        if (pomContext.getConfigCTR().verSenha(editTextSenha.getText().toString())) {
                            LogProcessoDAO.getInstance().insertLogProcesso("if (pomContext.getConfigCTR().verSenha(editTextSenha.getText().toString())) {\n" +
                                    "Intent it = new Intent(SenhaActivity.this, ConfigActivity.class);", getLocalClassName());
                            Intent it = new Intent(SenhaActivity.this, ConfigActivity.class);
                            startActivity(it);
                            finish();

                        }
                    } else {
                        LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
                        if (editTextSenha.getText().toString().equals("fgbny946")) {
                            LogProcessoDAO.getInstance().insertLogProcesso("if (editTextSenha.getText().toString().equals(\"fgbny946\")) {\n" +
                                    "Intent it = new Intent(SenhaActivity.this, LogProcessoActivity.class);", getLocalClassName());
                            Intent it = new Intent(SenhaActivity.this, LogProcessoActivity.class);
                            startActivity(it);
                            finish();
                        }
                    }
                }


            }
        });

        buttonCancSenha.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(!pomContext.getConfigCTR().hasElemConfig()){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(!pomContext.getConfigCTR().getConfig().hasElements()){\n" +
                            "                    Intent it = new Intent(SenhaActivity.this, MenuInicialActivity.class);", getLocalClassName());
                    Intent it = new Intent(SenhaActivity.this, MenuInicialActivity.class);
                    startActivity(it);
                    finish();
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
                    if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 11L) {
                        LogProcessoDAO.getInstance().insertLogProcesso("btCancSenha.setOnClickListener(new View.OnClickListener() {\n" +
                                "            @Override\n" +
                                "            public void onClick(View v) {\n" +
                                "Intent it = new Intent(SenhaActivity.this, MenuInicialActivity.class);", getLocalClassName());
                        Intent it = new Intent(SenhaActivity.this, MenuInicialActivity.class);
                        startActivity(it);
                        finish();
                    } else {
                        LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
                        if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 12L){
                            LogProcessoDAO.getInstance().insertLogProcesso("if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 12L){\n" +
                                    "Intent it = new Intent(SenhaActivity.this, TelaInicialActivity.class);", getLocalClassName());
                            Intent it = new Intent(SenhaActivity.this, TelaInicialActivity.class);
                            startActivity(it);
                            finish();
                        }
                        else if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 23L){
                            LogProcessoDAO.getInstance().insertLogProcesso("else if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 23L){\n" +
                                    "Intent it = new Intent(SenhaActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                            Intent it = new Intent(SenhaActivity.this, MenuPrincActivity.class);
                            startActivity(it);
                            finish();
                        }
                    }
                }
            }

        });

    }

    public void onBackPressed()  {
    }

}
