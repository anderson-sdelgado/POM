package br.com.usinasantafe.pom.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.usinasantafe.pom.POMContext;
import br.com.usinasantafe.pom.R;
import br.com.usinasantafe.pom.model.bean.variaveis.RendMMBean;
import br.com.usinasantafe.pom.model.dao.LogProcessoDAO;

public class RendimentoActivity extends ActivityGeneric {

    private POMContext pomContext;
    private RendMMBean rendMMBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rendimento);

        pomContext = (POMContext) getApplication();

        Button buttonOkRendimento = findViewById(R.id.buttonOkPadrao);
        Button buttonCancRendimento = findViewById(R.id.buttonCancPadrao);
        TextView textViewRendimento = findViewById(R.id.textViewPadrao);
        EditText editText = findViewById(R.id.editTextPadrao);

        int cont;

        LogProcessoDAO.getInstance().insertLogProcesso("int cont;\n" +
                "if(pomContext.getConfigCTR().getConfig().getPosicaoTela() == 4L){\n" +
                "            cont = pomContext.getMotoMecFertCTR().getContRend() - 1;\n" +
                "        }\n" +
                "        else{\n" +
                "            cont = pomContext.getMotoMecFertCTR().getPosRend();\n" +
                "        }", getLocalClassName());
        if(pomContext.getConfigCTR().getConfig().getPosicaoTela() == 4L){
            cont = pomContext.getMotoMecFertCTR().getContRend() - 1;
        }
        else{
            cont = pomContext.getMotoMecFertCTR().getPosRend();
        }

        LogProcessoDAO.getInstance().insertLogProcesso("rendMMBean =  pomContext.getMotoMecFertCTR().getRend(cont);", getLocalClassName());
        rendMMBean =  pomContext.getMotoMecFertCTR().getRend(cont);

        textViewRendimento.setText("OS " + rendMMBean.getNroOSRendMM() +" \nRENDIMENTO :");

        LogProcessoDAO.getInstance().insertLogProcesso("if(rendMMBean.getValorRendMM() > 0){\n" +
                "            editText.setText(String.valueOf(rendMMBean.getValorRendMM()).replace(\".\", \",\"));\n" +
                "        }\n" +
                "        else{\n" +
                "            editText.setText(\"\");\n" +
                "        }", getLocalClassName());
        if(rendMMBean.getValorRendMM() > 0){
            editText.setText(String.valueOf(rendMMBean.getValorRendMM()).replace(".", ","));
        }
        else{
            editText.setText("");
        }

        buttonOkRendimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonOkRendimento.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if (!editTextPadrao.getText().toString().equals("")) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (!editTextPadrao.getText().toString().equals(\"\")) {\n" +
                            "                    verifRend();", getLocalClassName());
                    verifRend();
                }
                else{
                    if(pomContext.getConfigCTR().getConfig().getPosicaoTela() == 7L){
                        LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                                "                    if(pomContext.getConfigCTR().getConfig().getPosicaoTela() == 7L){\n" +
                                "                        Intent it = new Intent(RendimentoActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                        Intent it = new Intent(RendimentoActivity.this, MenuPrincPMMActivity.class);
                        startActivity(it);
                        finish();
                    }
                }
            }
        });

        buttonCancRendimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonCancRendimento.setOnClickListener(new View.OnClickListener() {\n" +
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
        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed()  {", getLocalClassName());
        if(pomContext.getConfigCTR().getConfig().getPosicaoTela() == 4L){
            LogProcessoDAO.getInstance().insertLogProcesso("if(pomContext.getConfigCTR().getConfig().getPosicaoTela() == 4L){", getLocalClassName());
            if(pomContext.getMotoMecFertCTR().getPosRend() > 1){
                LogProcessoDAO.getInstance().insertLogProcesso("if(pomContext.getMotoMecFertCTR().getPosRend() > 1){\n" +
                        "                pomContext.getMotoMecFertCTR().setPosRend(pomContext.getMotoMecFertCTR().getPosRend() - 1);\n" +
                        "                Intent it = new Intent(RendimentoActivity.this, RendimentoActivity.class);", getLocalClassName());
                pomContext.getMotoMecFertCTR().setPosRend(pomContext.getMotoMecFertCTR().getPosRend() - 1);
                Intent it = new Intent(RendimentoActivity.this, RendimentoActivity.class);
                startActivity(it);
                finish();
            }
            else{
                LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                        "                Intent it = new Intent(RendimentoActivity.this, HorimetroActivity.class);", getLocalClassName());
                Intent it = new Intent(RendimentoActivity.this, HorimetroActivity.class);
                startActivity(it);
                finish();
            }
        }
        else {
            LogProcessoDAO.getInstance().insertLogProcesso("else {\n" +
                    "            Intent it = new Intent(RendimentoActivity.this, ListaOSRendActivity.class);", getLocalClassName());
            Intent it = new Intent(RendimentoActivity.this, ListaOSRendActivity.class);
            startActivity(it);
            finish();
        }
    }

    public void verTela(Double rendNum){

        LogProcessoDAO.getInstance().insertLogProcesso("rendMMBean.setValorRendMM(" + rendNum + ");\n" +
                "        pomContext.getMotoMecFertCTR().atualRend(" + rendMMBean + ");", getLocalClassName());
        rendMMBean.setValorRendMM(rendNum);
        pomContext.getMotoMecFertCTR().atualRend(rendMMBean);

        if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 4L) {
            LogProcessoDAO.getInstance().insertLogProcesso("if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 4L) {", getLocalClassName());
            if (pomContext.getMotoMecFertCTR().qtdeRend() == pomContext.getMotoMecFertCTR().getContRend()) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (pomContext.getMotoMecFertCTR().qtdeRend() == pomContext.getMotoMecFertCTR().getContRend()) {\n" +
                        "                pomContext.getMotoMecFertCTR().salvarBolMMFertFechado();", getLocalClassName());
                pomContext.getMotoMecFertCTR().salvarBolMMFertFechado(getLocalClassName());
                LogProcessoDAO.getInstance().insertLogProcesso("Intent it = new Intent(RendimentoActivity.this, TelaInicialActivity.class);", getLocalClassName());
                Intent it = new Intent(RendimentoActivity.this, TelaInicialActivity.class);
                startActivity(it);
                finish();
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "pomContext.getMotoMecFertCTR().setContRend(pomContext.getMotoMecFertCTR().getContRend() + 1);\n" +
                        "                Intent it = new Intent(RendimentoActivity.this, RendimentoActivity.class);", getLocalClassName());
                pomContext.getMotoMecFertCTR().setContRend(pomContext.getMotoMecFertCTR().getContRend() + 1);
                Intent it = new Intent(RendimentoActivity.this, RendimentoActivity.class);
                startActivity(it);
                finish();
            }

        }
        else {
            LogProcessoDAO.getInstance().insertLogProcesso("else {\n" +
                    "Intent it = new Intent(RendimentoActivity.this, ListaOSRendActivity.class);", getLocalClassName());
            Intent it = new Intent(RendimentoActivity.this, ListaOSRendActivity.class);
            startActivity(it);
            finish();
        }
    }

    public void verifRend(){
        LogProcessoDAO.getInstance().insertLogProcesso("String rend = editTextPadrao.getText().toString();\n" +
                "        Double rendNum = Double.valueOf(rend.replace(\",\", \".\"));", getLocalClassName());
        String rend = editTextPadrao.getText().toString();
        Double rendNum = Double.valueOf(rend.replace(",", "."));
        if (rendNum <= pomContext.getMotoMecFertCTR().rendOS(rendMMBean.getNroOSRendMM())) {
            LogProcessoDAO.getInstance().insertLogProcesso("if (rendNum <= pomContext.getMotoMecFertCTR().rendOS(rendMMBean.getNroOSRendMM())) {\n" +
                    "            verTela(rendNum);", getLocalClassName());
            verTela(rendNum);
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                    "            AlertDialog.Builder alerta = new AlertDialog.Builder(RendimentoActivity.this);\n" +
                    "            alerta.setTitle(\"ATENCAO\");\n" +
                    "            alerta.setMessage(\"VALOR INFORMADO MAIS ALTO DO QUE O PERMITIDO PRA OS. VALOR VERIFIQUE O VALOR DIGITADO.\");\n" +
                    "            alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                    "                @Override\n" +
                    "                public void onClick(DialogInterface dialog, int which) {\n" +
                    "                }\n" +
                    "            });\n" +
                    "            alerta.show();", getLocalClassName());
            AlertDialog.Builder alerta = new AlertDialog.Builder(RendimentoActivity.this);
            alerta.setTitle("ATENCAO");
            alerta.setMessage("VALOR INFORMADO MAIS ALTO DO QUE O PERMITIDO PRA OS. VALOR VERIFIQUE O VALOR DIGITADO.");
            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alerta.show();
        }
    }

}
