package br.com.usinasantafe.pom.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pom.POMContext;
import br.com.usinasantafe.pom.R;
import br.com.usinasantafe.pom.model.bean.estaticas.RFuncaoAtivParBean;
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
        else if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 17L) {
            textViewHorimetro.setText("HORIMETRO/HODOMETRO FINAL");
        }
        else if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 18L) {
            textViewHorimetro.setText("HORIMETRO/HODOMETRO INICIAL");
        }
        else if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 26L) {
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
        else if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 17L) {
            LogProcessoDAO.getInstance().insertLogProcesso("else if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 17L) {\n" +
                    "            salvarBoletimFechado();", getLocalClassName());
            salvarBoletimFechado();
        }
        else if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 18L) {
            salvarBoletimAberto();
            LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                    "            salvarBoletimAberto();", getLocalClassName());
        }
        else if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 26L) {
            LogProcessoDAO.getInstance().insertLogProcesso("else if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 4L) {\n" +
                    "            salvarBoletimFechado();", getLocalClassName());
            salvarBoletimFechado();
        }
    }

    public void salvarBoletimAberto() {
        LogProcessoDAO.getInstance().insertLogProcesso("public void salvarBoletimAberto() {\n" +
                "pomContext.getConfigCTR().setHodometroInicialBolMMFert(horimetroNum,  getLongitude(), getLatitude());", getLocalClassName());
        pomContext.getConfigCTR().setHodometroInicialConfig(horimetroNum,  getLongitude(), getLatitude());
        if(pomContext.getConfigCTR().getEquip().getTipoEquip() == 1){
            LogProcessoDAO.getInstance().insertLogProcesso("if(pomContext.getConfigCTR().getEquip().getTipoEquip() == 1){\n" +
                    "List<RFuncaoAtivParBean> rFuncaoAtividadeList = pomContext.getMotoMecFertCTR().getFuncaoAtividadeList();", getLocalClassName());
            List<RFuncaoAtivParBean> rFuncaoAtividadeList = pomContext.getMotoMecFertCTR().getFuncaoAtividadeList(getLocalClassName());
            LogProcessoDAO.getInstance().insertLogProcesso("boolean implemento = false;\n" +
                    "            for (RFuncaoAtivParBean rFuncaoAtivParBean : rFuncaoAtividadeList) {\n" +
                    "                if(rFuncaoAtivParBean.getCodFuncao() == 3){\n" +
                    "                    implemento = true;\n" +
                    "                }\n" +
                    "            }\n" +
                    "            rFuncaoAtividadeList.clear();", getLocalClassName());
            boolean implemento = false;
            for (RFuncaoAtivParBean rFuncaoAtivParBean : rFuncaoAtividadeList) {
                if(rFuncaoAtivParBean.getCodFuncao() == 3){
                    implemento = true;
                }
            }
            rFuncaoAtividadeList.clear();
            if(implemento){
                LogProcessoDAO.getInstance().insertLogProcesso("if(implemento){\n" +
                        "pomContext.getMotoMecFertCTR().setContImplemento(1L);\n" +
                        "                Intent it = new Intent(HorimetroActivity.this, ImplementoActivity.class);", getLocalClassName());
                pomContext.getMotoMecFertCTR().setContImplemento(1L);
                Intent it = new Intent(HorimetroActivity.this, ImplementoActivity.class);
                startActivity(it);
                finish();
            }
            else{
                LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                        "pomContext.getConfigCTR().setHorimetroConfig(horimetroNum);\n" +
                        "                pomContext.getMotoMecFertCTR().salvarBolMMFertAberto();", getLocalClassName());
                pomContext.getConfigCTR().setHorimetroConfig(horimetroNum);
                pomContext.getMotoMecFertCTR().salvarBolMMFertAberto(getLocalClassName());
                if(pomContext.getCheckListCTR().verAberturaCheckList(pomContext.getConfigCTR().getConfig().getIdTurnoConfig())){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(pomContext.getCheckListCTR().verAberturaCheckList(pomContext.getConfigCTR().getConfig().getIdTurnoConfig())){\n" +
                            "pomContext.getMotoMecFertCTR().inserirParadaCheckList();", getLocalClassName());
                    pomContext.getMotoMecFertCTR().inserirParadaCheckList(getLocalClassName());
                    LogProcessoDAO.getInstance().insertLogProcesso("pomContext.getCheckListCTR().setPosCheckList(1);", getLocalClassName());
                    pomContext.getCheckListCTR().setPosCheckList(1);
                    LogProcessoDAO.getInstance().insertLogProcesso("pomContext.getCheckListCTR().createCabecAberto();", getLocalClassName());
                    pomContext.getCheckListCTR().createCabecAberto(getLocalClassName());
                    if (pomContext.getConfigCTR().getConfig().getAtualCheckList().equals(1L)) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if (pomContext.getConfigCTR().getConfig().getAtualCheckList().equals(1L)) {\n" +
                                "                        Intent it = new Intent(HorimetroActivity.this, PergAtualCheckListActivity.class);", getLocalClassName());
                        Intent it = new Intent(HorimetroActivity.this, PergAtualCheckListActivity.class);
                        startActivity(it);
                        finish();
                    }
                    else {
                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "                        Intent it = new Intent(HorimetroActivity.this, ItemCheckListActivity.class);", getLocalClassName());
                        Intent it = new Intent(HorimetroActivity.this, ItemCheckListActivity.class);
                        startActivity(it);
                        finish();
                    }
                }
                else{
                    LogProcessoDAO.getInstance().insertLogProcesso("else{", getLocalClassName());
                    if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 1L) {", getLocalClassName());
                        if(POMContext.aplic == 1){
                            LogProcessoDAO.getInstance().insertLogProcesso("if(POMContext.aplic == 1){\n" +
                                    "                Intent it = new Intent(HorimetroActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                            Intent it = new Intent(HorimetroActivity.this, MenuPrincPMMActivity.class);
                            startActivity(it);
                            finish();
                        }
                        else if(POMContext.aplic == 2){
                            LogProcessoDAO.getInstance().insertLogProcesso("else if(POMContext.aplic == 2){\n" +
                                    "                Intent it = new Intent(HorimetroActivity.this, MenuPrincECMActivity.class);", getLocalClassName());
                            Intent it = new Intent(HorimetroActivity.this, MenuPrincECMActivity.class);
                            startActivity(it);
                            finish();
                        }
                        else if(POMContext.aplic == 3){
                            LogProcessoDAO.getInstance().insertLogProcesso("else if(POMContext.aplic == 3){\n" +
                                    "                Intent it = new Intent(HorimetroActivity.this, MenuPrincPCOMPActivity.class);", getLocalClassName());
                            Intent it = new Intent(HorimetroActivity.this, MenuPrincPCOMPActivity.class);
                            startActivity(it);
                            finish();
                        }
                    }
                    else if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 18L) {
                        LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                                "                        Intent it = new Intent(HorimetroActivity.this, VerifOperadorActivity.class);", getLocalClassName());
                        Intent it = new Intent(HorimetroActivity.this, VerifOperadorActivity.class);
                        startActivity(it);
                        finish();
                    }
                }
            }
        }
        else{
            LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                    "            Intent it = new Intent(HorimetroActivity.this, EquipMBActivity.class);", getLocalClassName());
            Intent it = new Intent(HorimetroActivity.this, EquipMBActivity.class);
            startActivity(it);
            finish();
        }

    }

    public void salvarBoletimFechado() {
        LogProcessoDAO.getInstance().insertLogProcesso("public void salvarBoletimFechado() {\n" +
                "pomContext.getConfigCTR().setHorimetroConfig(" + horimetroNum + ");", getLocalClassName());
        pomContext.getConfigCTR().setHorimetroConfig(horimetroNum);
        LogProcessoDAO.getInstance().insertLogProcesso("pomContext.getConfigCTR().setHodometroFinalConfig(" + horimetroNum + ");", getLocalClassName());
        pomContext.getConfigCTR().setHodometroFinalConfig(horimetroNum);
        if(pomContext.getConfigCTR().getEquip().getTipoEquip() == 1){
            LogProcessoDAO.getInstance().insertLogProcesso("if(pomContext.getConfigCTR().getEquip().getTipoEquip() == 1){", getLocalClassName());
            if (pomContext.getMotoMecFertCTR().verRendMM()) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (pomContext.getMotoMecFertCTR().verRendMM()) {\n" +
                        "                pomContext.getMotoMecFertCTR().setContRend(1);\n" +
                        "                Intent it = new Intent(HorimetroActivity.this, RendimentoActivity.class);", getLocalClassName());
                pomContext.getMotoMecFertCTR().setContRend(1);
                Intent it = new Intent(HorimetroActivity.this, RendimentoActivity.class);
                startActivity(it);
                finish();
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "                pomContext.getMotoMecFertCTR().salvarBolMMFertFechado();", getLocalClassName());
                pomContext.getMotoMecFertCTR().salvarBolMMFertFechado(getLocalClassName());
                if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 4L) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 4L) {\n" +
                            "                    Intent it = new Intent(HorimetroActivity.this, TelaInicialActivity.class);", getLocalClassName());
                    Intent it = new Intent(HorimetroActivity.this, TelaInicialActivity.class);
                    startActivity(it);
                    finish();
                }
                else if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 17L) {
                    LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                            "                    pomContext.getConfigCTR().setPosicaoTela(18L);\n" +
                            "                    Intent it = new Intent(HorimetroActivity.this, OperadorActivity.class);", getLocalClassName());
                    pomContext.getConfigCTR().setPosicaoTela(18L);
                    Intent it = new Intent(HorimetroActivity.this, OperadorActivity.class);
                    startActivity(it);
                    finish();
                }
                else if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 26L) {
                    LogProcessoDAO.getInstance().insertLogProcesso("else if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 26L) {\n" +
                            "                    Intent it = new Intent(HorimetroActivity.this, TelaInicialActivity.class);", getLocalClassName());
                    Intent it = new Intent(HorimetroActivity.this, TelaInicialActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        }
        else{
            LogProcessoDAO.getInstance().insertLogProcesso("else{", getLocalClassName());
            if (pomContext.getMotoMecFertCTR().verRecolh()) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (pomContext.getMotoMecFertCTR().verRecolh()) {\n" +
                        "                pomContext.getMotoMecFertCTR().setContRecolh(1);\n" +
                        "                Intent it = new Intent(HorimetroActivity.this, RecolhimentoActivity.class);", getLocalClassName());
                pomContext.getMotoMecFertCTR().setContRecolh(1);
                Intent it = new Intent(HorimetroActivity.this, RecolhimentoActivity.class);
                startActivity(it);
                finish();
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "                pomContext.getMotoMecFertCTR().salvarBolMMFertAberto(getLocalClassName());\n" +
                        "                Intent it = new Intent(HorimetroActivity.this, TelaInicialActivity.class);", getLocalClassName());
                pomContext.getMotoMecFertCTR().salvarBolMMFertAberto(getLocalClassName());
                Intent it = new Intent(HorimetroActivity.this, TelaInicialActivity.class);
                startActivity(it);
                finish();
            }
        }

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
            LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
            if(POMContext.aplic == 1){
                LogProcessoDAO.getInstance().insertLogProcesso("if(POMContext.aplic == 1){\n" +
                        "                Intent it = new Intent(HorimetroActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                Intent it = new Intent(HorimetroActivity.this, MenuPrincPMMActivity.class);
                startActivity(it);
                finish();
            }
            else if(POMContext.aplic == 2){
                LogProcessoDAO.getInstance().insertLogProcesso("}\n" +
                        "            else if(POMContext.aplic == 2){\n" +
                        "                Intent it = new Intent(HorimetroActivity.this, MenuPrincECMActivity.class);", getLocalClassName());
                Intent it = new Intent(HorimetroActivity.this, MenuPrincECMActivity.class);
                startActivity(it);
                finish();
            }
            else if(POMContext.aplic == 3){
                LogProcessoDAO.getInstance().insertLogProcesso("}\n" +
                        "            else if(POMContext.aplic == 3){\n" +
                        "                Intent it = new Intent(HorimetroActivity.this, MenuPrincPCOMPActivity.class);", getLocalClassName());
                Intent it = new Intent(HorimetroActivity.this, MenuPrincPCOMPActivity.class);
                startActivity(it);
                finish();
            }
        }
    }

}
