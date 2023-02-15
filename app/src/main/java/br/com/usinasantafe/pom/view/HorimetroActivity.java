package br.com.usinasantafe.pom.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pom.POMContext;
import br.com.usinasantafe.pom.R;
import br.com.usinasantafe.pom.model.dao.LogProcessoDAO;

public class HorimetroActivity extends ActivityGeneric {

    private POMContext pomContext;
    private Double horimetroNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horimetro);

        pomContext = (POMContext) getApplication();

        Button buttonOkHorimetro = findViewById(R.id.buttonOkPadrao);
        Button buttonCancHorimetro = findViewById(R.id.buttonCancPadrao);
        TextView textViewHorimetro = findViewById(R.id.textViewPadrao);

        if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {
            textViewHorimetro.setText("HORIMETRO/HODOMETRO INICIAL");
        }
        else if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 4L) {
            textViewHorimetro.setText("HORIMETRO/HODOMETRO FINAL");
        }

        buttonOkHorimetro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonOkHorimetro.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());

                if (!editTextPadrao.getText().toString().equals("")) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (!editTextPadrao.getText().toString().equals(\"\")) {\n" +
                            "                    String horimetro = editTextPadrao.getText().toString();\n" +
                            "                    horimetroNum = Double.valueOf(horimetro.replace(\",\", \".\"));", getLocalClassName());
                    String horimetro = editTextPadrao.getText().toString();
                    horimetroNum = Double.valueOf(horimetro.replace(",", "."));

                    if (horimetroNum >= pomContext.getConfigCTR().getConfig().getHorimetroConfig()) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if (horimetroNum >= pomContext.getConfigCTR().getConfig().getHorimetroConfig()) {\n" +
                                "                        verTela();", getLocalClassName());
                        verTela();
                    } else {
                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "AlertDialog.Builder alerta = new AlertDialog.Builder(HorimetroActivity.this);\n" +
                                "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                                "                        alerta.setMessage(\"O HORIMETRO DIGITADO \" + horimetroNum + \" É MENOR QUE O HORIMETRO ANTERIOR DA MAQUINA \" + pomContext.getConfigCTR().getConfig().getHorimetroConfig() + \". DESEJA MANTER ESSE VALOR?\");", getLocalClassName());
                        AlertDialog.Builder alerta = new AlertDialog.Builder(HorimetroActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("O HODÔMETRO REGISTRADO " + horimetroNum + " É MENOR QUE O ANTERIOR DE " + pomContext.getConfigCTR().getConfig().getHorimetroConfig() + ". DESEJA MANTÊ-LO?");
                        alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                                        "                            @Override\n" +
                                        "                            public void onClick(DialogInterface dialog, int which) {\n" +
                                        "                                verTela();", getLocalClassName());
                                verTela();
                            }

                        });

                        alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"NÃO\", new DialogInterface.OnClickListener() {", getLocalClassName());
                            }
                        });

                        alerta.show();

                    }

                }

            }
        });

        buttonCancHorimetro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonCancHorimetro.setOnClickListener(new View.OnClickListener() {\n" +
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

    public void verTela(){
        LogProcessoDAO.getInstance().insertLogProcesso("public void verTela(){", getLocalClassName());
        if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {
            LogProcessoDAO.getInstance().insertLogProcesso("if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {\n" +
                    "            salvarBoletimAberto();", getLocalClassName());
            salvarBoletimAberto();
        }
        else if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 4L) {
            LogProcessoDAO.getInstance().insertLogProcesso("else if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 4L) {\n" +
                    "            salvarBoletimFechado();", getLocalClassName());
            salvarBoletimFechado();
        }
    }

    public void salvarBoletimAberto() {
        LogProcessoDAO.getInstance().insertLogProcesso("public void salvarBoletimAberto() {\n" +
                "        pomContext.getConfigCTR().setHodometroInicialConfig(horimetroNum,  getLongitude(), getLatitude());\n" +
                "        pomContext.getConfigCTR().setHorimetroConfig(horimetroNum);\n" +
                "        pomContext.getMotoMecFertCTR().salvarBolMMFertAberto(getLocalClassName());", getLocalClassName());
        pomContext.getConfigCTR().setHodometroInicialConfig(horimetroNum,  getLongitude(), getLatitude());
        pomContext.getConfigCTR().setHorimetroConfig(horimetroNum);
        pomContext.getMotoMecFertCTR().salvarBolMMFertAberto(getLocalClassName());
        if (pomContext.getCheckListCTR().verAberturaCheckList(pomContext.getConfigCTR().getConfig().getIdTurnoConfig())){
            LogProcessoDAO.getInstance().insertLogProcesso("if(pomContext.getCheckListCTR().verAberturaCheckList(pomContext.getConfigCTR().getConfig().getIdTurnoConfig())){\n" +
                    "            pomContext.getCheckListCTR().setPosCheckList(1);\n" +
                    "            pomContext.getCheckListCTR().createCabecAberto(getLocalClassName());", getLocalClassName());
            pomContext.getCheckListCTR().setPosCheckList(1);
            pomContext.getCheckListCTR().createCabecAberto(getLocalClassName());
            if (pomContext.getConfigCTR().getConfig().getAtualCheckList().equals(1L)) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (pomContext.getConfigCTR().getConfig().getAtualCheckList().equals(1L)) {\n" +
                        "                        Intent it = new Intent(HorimetroActivity.this, PergAtualCheckListActivity.class);", getLocalClassName());
                Intent it = new Intent(HorimetroActivity.this, PergAtualCheckListActivity.class);
                startActivity(it);
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "                        Intent it = new Intent(HorimetroActivity.this, ItemCheckListActivity.class);", getLocalClassName());
                Intent it = new Intent(HorimetroActivity.this, ItemCheckListActivity.class);
                startActivity(it);
            }
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                    "                    Intent it = new Intent(HorimetroActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
            Intent it = new Intent(HorimetroActivity.this, MenuPrincActivity.class);
            startActivity(it);
        }
        finish();
    }

    public void salvarBoletimFechado() {
        LogProcessoDAO.getInstance().insertLogProcesso("public void salvarBoletimFechado() {\n" +
                "pomContext.getConfigCTR().setHorimetroConfig(" + horimetroNum + ");\n" +
                "        pomContext.getConfigCTR().setHodometroFinalConfig(horimetroNum);\n" +
                "        pomContext.getMotoMecFertCTR().salvarBolMMFertFechado(getLocalClassName());\n" +
                "        Intent it = new Intent(HorimetroActivity.this, TelaInicialActivity.class);", getLocalClassName());
        pomContext.getConfigCTR().setHorimetroConfig(horimetroNum);
        pomContext.getConfigCTR().setHodometroFinalConfig(horimetroNum);
        pomContext.getMotoMecFertCTR().salvarBolMMFertFechado(getLocalClassName());
        Intent it = new Intent(HorimetroActivity.this, TelaInicialActivity.class);
        startActivity(it);
        finish();

    }

    public void onBackPressed() {
        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed() {", getLocalClassName());
        if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {
            LogProcessoDAO.getInstance().insertLogProcesso("if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {\n" +
                    "            Intent it = new Intent(HorimetroActivity.this, ListaAtividadeActivity.class);", getLocalClassName());
            Intent it = new Intent(HorimetroActivity.this, ListaAtividadeActivity.class);
            startActivity(it);
            finish();
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                    "                Intent it = new Intent(HorimetroActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
            Intent it = new Intent(HorimetroActivity.this, MenuPrincActivity.class);
            startActivity(it);
            finish();
        }
    }

}
