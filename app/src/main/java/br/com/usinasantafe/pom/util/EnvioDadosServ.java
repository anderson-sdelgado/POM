package br.com.usinasantafe.pom.util;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import br.com.usinasantafe.pom.control.CheckListCTR;
import br.com.usinasantafe.pom.control.MecanicoCTR;
import br.com.usinasantafe.pom.control.MotoMecFertCTR;
import br.com.usinasantafe.pom.model.dao.LogErroDAO;
import br.com.usinasantafe.pom.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pom.util.conHttp.PostCadGenerico;
import br.com.usinasantafe.pom.util.conHttp.UrlsConexaoHttp;
import br.com.usinasantafe.pom.view.ActivityGeneric;

public class EnvioDadosServ {

    private static EnvioDadosServ instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;
    public static int status; //1 - Existe Dados para Enviar; 2 - Enviado; 3 - Todos os Dados Foram Enviados;

    public EnvioDadosServ() {
        urlsConexaoHttp = new UrlsConexaoHttp();
    }

    public static EnvioDadosServ getInstance() {
        if (instance == null) {
            instance = new EnvioDadosServ();
        }
        return instance;
    }

    ////////////////////////////////// ENVIAR DADOS ///////////////////////////////////////////////

    public void enviarChecklist(String activity) {

        LogProcessoDAO.getInstance().insertLogProcesso("CheckListCTR checkListCTR = new CheckListCTR();\n" +
                "        envio(urlsConexaoHttp.getsInserirCheckList(), checkListCTR.dadosEnvio(), activity);", activity);
        CheckListCTR checkListCTR = new CheckListCTR();
        envio(urlsConexaoHttp.getsInserirCheckList(), checkListCTR.dadosEnvio(), activity);

    }

    public void enviarBolFechadoMMFert(String activity) {

        LogProcessoDAO.getInstance().insertLogProcesso("MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();\n" +
                "        envio(urlsConexaoHttp.getsInsertBolFechadoMMFert(), motoMecFertCTR.dadosEnvioBolFechadoMMFert(), activity);", activity);
        MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();
        envio(urlsConexaoHttp.getsInsertBolFechadoMMFert(), motoMecFertCTR.dadosEnvioBolFechadoMMFert(), activity);

    }

    public void enviarBolAbertoMMFert(String activity) {

        LogProcessoDAO.getInstance().insertLogProcesso("MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();\n" +
                "        envio(urlsConexaoHttp.getsInsertBolAbertoMMFert(), motoMecFertCTR.dadosEnvioBolAbertoMMFert(), activity);", activity);
        MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();
        envio(urlsConexaoHttp.getsInsertBolAbertoMMFert(), motoMecFertCTR.dadosEnvioBolAbertoMMFert(), activity);

    }

    public void envio(String url, String dados, String activity){

        String[] strings = {url, activity};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        Log.i("PMM", "URL = " + url + " - Dados de Envio = " + dados);
        LogProcessoDAO.getInstance().insertLogProcesso("postCadGenerico.execute('" + url + "'); - Dados de Envio = " + dados, activity);
        PostCadGenerico postCadGenerico = new PostCadGenerico();
        postCadGenerico.setParametrosPost(parametrosPost);
        postCadGenerico.execute(strings);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////VERIFICAÇÃO DE DADOS/////////////////////////////////////////

    public boolean verifChecklist() {
        CheckListCTR checkListCTR = new CheckListCTR();
        return checkListCTR.verEnvioDados();
    }

    public boolean verifBolAbertoEnviarMMFert() {
        MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();
        return motoMecFertCTR.verEnvioBolAbertoEnviar();
    }

    public boolean verifBolFechadoMMFert() {
        MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();
        return motoMecFertCTR.verEnvioBolFechado();
    }

    public boolean verifApontMMMovLeiraMecanFert() {
        MecanicoCTR mecanicoCTR = new MecanicoCTR();
        return mecanicoCTR.verApontMecanNEnviado();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////MECANISMO DE ENVIO/////////////////////////////////////////

    public void envioDados(String activity) {
        LogProcessoDAO.getInstance().insertLogProcesso("public void envioDados(String activity) {\n" +
                "        status = 1;", activity);
        status = 1;
        if(ActivityGeneric.connectNetwork) {
            LogProcessoDAO.getInstance().insertLogProcesso("if(ActivityGeneric.connectNetwork) {\n" +
                    "            status = 2;", activity);
            status = 2;
            if (verifChecklist()) {
                LogProcessoDAO.getInstance().insertLogProcesso("verifChecklist()\n" +
                         "enviarChecklist()", activity);
                enviarChecklist(activity);
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("} else {", activity);
                if (verifBolFechadoMMFert()) {
                    LogProcessoDAO.getInstance().insertLogProcesso("verifBolFechadoMMFert()\n" +
                            "enviarBolFechadoMMFert()", activity);
                    enviarBolFechadoMMFert(activity);
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {", activity);
                    if (verifBolAbertoEnviarMMFert() || verifApontMMMovLeiraMecanFert()) {
                        LogProcessoDAO.getInstance().insertLogProcesso("verifApontMMMovLeiraFert()\n" +
                                "enviarBolAbertoMMFert()", activity);
                        enviarBolAbertoMMFert(activity);
                    } else {
                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "                                status = 3;", activity);
                        status = 3;
                    }
                }
            }
        }
    }

    public boolean verifDadosEnvio() {
        if ((!verifBolAbertoEnviarMMFert())
                && (!verifBolFechadoMMFert())
                && (!verifApontMMMovLeiraMecanFert())
                && (!verifChecklist())){
            return false;
        } else {
            return true;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////MECANISMO DE ENVIO/////////////////////////////////////////

    public void recDados(String result, String activity){
        LogProcessoDAO.getInstance().insertLogProcesso("public void recDados(String " + result + ", String activity){", activity);
        if(result.trim().startsWith("GRAVOU-CHECKLIST")){
            LogProcessoDAO.getInstance().insertLogProcesso("if(result.trim().startsWith(\"GRAVOU-CHECKLIST\")){\n" +
                    "            CheckListCTR checkListCTR = new CheckListCTR();\n" +
                    "checkListCTR.delChecklist(activity)", activity);
            CheckListCTR checkListCTR = new CheckListCTR();
            checkListCTR.updateRecebChecklist(activity);
        }
        else if (result.trim().startsWith("BOLABERTOMM")) {
            LogProcessoDAO.getInstance().insertLogProcesso("else if (result.trim().startsWith(\"BOLABERTOMM\")) {\n" +
                    "            MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();\n" +
                    "motoMecFertCTR.updBolAberto(result)", activity);
            MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();
            motoMecFertCTR.updateBolAberto(result, activity);
        }
        else if (result.trim().startsWith("BOLFECHADOMM")) {
            LogProcessoDAO.getInstance().insertLogProcesso("else if (result.trim().startsWith(\"BOLFECHADOMM\")) {\n" +
                    "            MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();\n" +
                    "motoMecFertCTR.updateBolFechado(result)", activity);
            MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();
            motoMecFertCTR.updateBolFechado(result, activity);
        }
        else {
            LogProcessoDAO.getInstance().insertLogProcesso("else {\n" +
                    "            status = 1;", activity);
            status = 1;
            LogErroDAO.getInstance().insertLogErro(result);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

}