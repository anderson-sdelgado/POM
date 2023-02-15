package br.com.usinasantafe.pom.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

import br.com.usinasantafe.pom.POMContext;
import br.com.usinasantafe.pom.R;
import br.com.usinasantafe.pom.model.bean.variaveis.LogErroBean;
import br.com.usinasantafe.pom.model.bean.variaveis.LogProcessoBean;
import br.com.usinasantafe.pom.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pom.util.EnvioDadosServ;
import br.com.usinasantafe.pom.util.VerifDadosServ;

public class TelaInicialActivity extends ActivityGeneric {

    private POMContext pomContext;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        pomContext = (POMContext) getApplication();
        customHandler.postDelayed(excluirBDThread, 0);

//        String dados = pomContext.getMotoMecFertCTR().dadosEnvioBolFechadoMMFert();
//        Log.i("Envio", dados);
//
//        logErro();

    }

    private Runnable excluirBDThread = new Runnable() {

        public void run() {

            LogProcessoDAO.getInstance().insertLogProcesso("clearBD();", getLocalClassName());
            clearBD();

            if(EnvioDadosServ.getInstance().verifDadosEnvio()){
                LogProcessoDAO.getInstance().insertLogProcesso("EnvioDadosServ.getInstance().verifDadosEnvio()", getLocalClassName());
                if(connectNetwork){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(connectNetwork){\n" +
                            "EnvioDadosServ.getInstance().envioDados()", getLocalClassName());
                    EnvioDadosServ.getInstance().envioDados(getLocalClassName());
                }
                else{
                    LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                            "                EnvioDadosServ.status = 1;", getLocalClassName());
                    EnvioDadosServ.status = 1;
                }
            }
            else{
                LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                        "            EnvioDadosServ.status = 3;", getLocalClassName());
                EnvioDadosServ.status = 3;
            }

            LogProcessoDAO.getInstance().insertLogProcesso("VerifDadosServ.status = 3;", getLocalClassName());
            VerifDadosServ.status = 3;

            LogProcessoDAO.getInstance().insertLogProcesso("atualizarAplic()", getLocalClassName());
            atualizarAplic();

        }
    };

    public void clearBD() {
        LogProcessoDAO.getInstance().insertLogProcesso("pomContext.getCheckListCTR().deleteChecklist();\n" +
                "        pomContext.getMotoMecFertCTR().deleteBolEnviado();\n" +
                "        pomContext.getConfigCTR().deleteLogs();", getLocalClassName());
        pomContext.getCheckListCTR().deleteChecklist();
        pomContext.getMotoMecFertCTR().deleteBolEnviado();
        pomContext.getConfigCTR().deleteLogs();
        if(POMContext.aplic == 1){
            LogProcessoDAO.getInstance().insertLogProcesso("pomContext.getConfigCTR().osDelAll();\n" +
                    "            pomContext.getConfigCTR().rOSAtivDelAll();", getLocalClassName());
            pomContext.getConfigCTR().osDelAll();
            pomContext.getConfigCTR().rOSAtivDelAll();
        }
    }

    public void atualizarAplic(){
        LogProcessoDAO.getInstance().insertLogProcesso("public void atualizarAplic(){", getLocalClassName());
        if (connectNetwork) {
            LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {", getLocalClassName());
            if (pomContext.getConfigCTR().hasElemConfig()) {
                LogProcessoDAO.getInstance().insertLogProcesso("pomContext.getConfigCTR().hasElemConfig()\n" +
                        "                customHandler.postDelayed(updateTimerThread, 10000);", getLocalClassName());
                customHandler.postDelayed(encerraAtualThread, 10000);
                LogProcessoDAO.getInstance().insertLogProcesso("pomContext.getConfigCTR().verAtualAplic(pomContext.versaoAplic, this, getLocalClassName());", getLocalClassName());
//                pomContext.getConfigCTR().verAtualAplic(BuildConfig.VERSION_NAME, this, getLocalClassName());
            }
            else{
                LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                        "                VerifDadosServ.status = 3;\n" +
                        "goMenuInicial();", getLocalClassName());
                VerifDadosServ.status = 3;
                goMenuInicial();
            }
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                    "                VerifDadosServ.status = 3;\n" +
                    "goMenuInicial();", getLocalClassName());
            VerifDadosServ.status = 3;
            goMenuInicial();
        }
    }

    private Runnable encerraAtualThread = new Runnable() {

        public void run() {
            LogProcessoDAO.getInstance().insertLogProcesso("    private Runnable updateTimerThread = new Runnable() {\n" +
                    "        public void run() {", getLocalClassName());
            LogProcessoDAO.getInstance().insertLogProcesso("verifEnvio();", getLocalClassName());
            if(VerifDadosServ.status < 3) {
                LogProcessoDAO.getInstance().insertLogProcesso("if(VerifDadosServ.status < 3) {\n" +
                        "VerifDadosServ.getInstance().cancel();", getLocalClassName());
                VerifDadosServ.getInstance().cancel();
            }
            LogProcessoDAO.getInstance().insertLogProcesso("goMenuInicial();", getLocalClassName());
            goMenuInicial();
        }
    };

    public void goMenuInicial(){

        LogProcessoDAO.getInstance().insertLogProcesso("customHandler.removeCallbacks(updateTimerThread);", getLocalClassName());
        customHandler.removeCallbacks(encerraAtualThread);
        if(pomContext.getMotoMecFertCTR().verBolAberto()){
            LogProcessoDAO.getInstance().insertLogProcesso("if(pomContext.getMotoMecFertCTR().verBolAberto()){", getLocalClassName());
            if(!pomContext.getCheckListCTR().verCabecAberto()){
                LogProcessoDAO.getInstance().insertLogProcesso("if(!pomContext.getCheckListCTR().verCabecAberto()){\n" +
                        "Intent it = new Intent(TelaInicialActivity.this, MenuPrincPMMActivity.class)", getLocalClassName());
                pomContext.getConfigCTR().setPosicaoTela(8L);
                Intent it = new Intent(TelaInicialActivity.this, MenuPrincActivity.class);
                startActivity(it);
            }
            else {
                LogProcessoDAO.getInstance().insertLogProcesso("else {\n" +
                        "pomContext.getCheckListCTR().clearRespCabecAberto();\n" +
                        "                pomContext.getCheckListCTR().setPosCheckList(1);\n" +
                        "                Intent it = new Intent(TelaInicialActivity.this, ItemCheckListActivity.class);", getLocalClassName());
                pomContext.getCheckListCTR().clearRespCabecAberto();
                pomContext.getCheckListCTR().setPosCheckList(1);
                Intent it = new Intent(TelaInicialActivity.this, ItemCheckListActivity.class);
                startActivity(it);
            }
        }
        else{
            LogProcessoDAO.getInstance().insertLogProcesso("}\n" +
                    "        else{\n" +
                    "Intent it = new Intent(TelaInicialActivity.this, MenuInicialActivity.class);", getLocalClassName());
            Intent it = new Intent(TelaInicialActivity.this, MenuInicialActivity.class);
            startActivity(it);
        }
        finish();

    }

//    public void logProcesso(){
//        LogProcessoBean logProcessoBean = new LogProcessoBean();
//        List<LogProcessoBean> logProcessoList = logProcessoBean.orderBy("idLogProcesso", false);
//        for(LogProcessoBean logProcessoBeanBD : logProcessoList){
//            Log.i("PMM", dadosProcesso(logProcessoBeanBD));
//        }
//    }
//
//    private String dadosProcesso(LogProcessoBean logProcessoBean){
//        Gson gsonCabec = new Gson();
//        return gsonCabec.toJsonTree(logProcessoBean, logProcessoBean.getClass()).toString();
//    }
//
//    public void logErro(){
//        LogErroBean logErroBean = new LogErroBean();
//        List<LogErroBean> logErroList = logErroBean.orderBy("idLogErro", false);
//        Log.i("PMM", "Log Erro");
//        for(LogErroBean logErroBeanBD : logErroList){
//            Log.i("PMM", dadosErro(logErroBeanBD));
//        }
//    }
//
//    private String dadosErro(LogErroBean logErroBean){
//        Gson gsonCabec = new Gson();
//        return gsonCabec.toJsonTree(logErroBean, logErroBean.getClass()).toString();
//    }
//
}