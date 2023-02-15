package br.com.usinasantafe.pom.control;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pom.POMContext;
import br.com.usinasantafe.pom.model.bean.estaticas.AtividadeBean;
import br.com.usinasantafe.pom.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.pom.model.bean.estaticas.MotoMecBean;
import br.com.usinasantafe.pom.model.bean.estaticas.TurnoBean;
import br.com.usinasantafe.pom.model.bean.variaveis.ApontMMFertBean;
import br.com.usinasantafe.pom.model.bean.variaveis.BoletimMMFertBean;
import br.com.usinasantafe.pom.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pom.model.dao.ApontMMFertDAO;
import br.com.usinasantafe.pom.model.dao.ApontMecanDAO;
import br.com.usinasantafe.pom.model.dao.AtividadeDAO;
import br.com.usinasantafe.pom.model.dao.BoletimMMFertDAO;
import br.com.usinasantafe.pom.model.dao.FuncDAO;
import br.com.usinasantafe.pom.model.dao.LogErroDAO;
import br.com.usinasantafe.pom.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pom.model.dao.MotoMecDAO;
import br.com.usinasantafe.pom.model.dao.OSDAO;
import br.com.usinasantafe.pom.model.dao.TurnoDAO;
import br.com.usinasantafe.pom.util.AtualDadosServ;
import br.com.usinasantafe.pom.util.EnvioDadosServ;
import br.com.usinasantafe.pom.util.Tempo;

public class MotoMecFertCTR {

    private MotoMecBean motoMecBean;

    public MotoMecFertCTR() {
    }

    public boolean verBolAberto(){
        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        return boletimMMFertDAO.verBoletimMMFertAberto();
    }

    public BoletimMMFertBean getBoletimMMFertAberto(){
        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        return boletimMMFertDAO.getBoletimMMFertAberto();
    }

    public void salvarBolMMFertAberto(String activity){
        ConfigCTR configCTR = new ConfigCTR();
        ConfigBean configBean = configCTR.getConfig();
        LogProcessoDAO.getInstance().insertLogProcesso("boletimMMFertDAO.salvarBoletimMMFertAberto(configCTR.getConfig().getMatricFuncConfig()\n" +
                "                , configCTR.getEquip().getIdEquip()\n" +
                "                , configCTR.getConfig().getIdEquipBombaBolConfig()\n" +
                "                , configCTR.getConfig().getIdTurnoConfig()\n" +
                "                , configCTR.getConfig().getHodometroInicialConfig()\n" +
                "                , configCTR.getConfig().getLongitudeConfig()\n" +
                "                , configCTR.getConfig().getLatitudeConfig()\n" +
                "                , configCTR.getEquip().getTipoEquip()\n" +
                "                , configBean.getNroOSConfig()\n" +
                "                , configBean.getIdAtivConfig()\n" +
                "                , configBean.getStatusConConfig()\n" +
                "                , activity);", activity);
        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        boletimMMFertDAO.salvarBoletimMMFertAberto(configCTR.getConfig().getMatricFuncConfig()
                , configCTR.getEquip().getIdEquip()
                , configCTR.getConfig().getIdEquipBombaBolConfig()
                , configCTR.getConfig().getIdTurnoConfig()
                , configCTR.getConfig().getHodometroInicialConfig()
                , configCTR.getConfig().getLongitudeConfig()
                , configCTR.getConfig().getLatitudeConfig()
                , configCTR.getEquip().getTipoEquip()
                , configBean.getNroOSConfig()
                , configBean.getIdAtivConfig()
                , configBean.getStatusConConfig()
                , activity);
    }

    public void salvarBolMMFertFechado(String activity){

        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();

        ConfigCTR configCTR = new ConfigCTR();
        LogProcessoDAO.getInstance().insertLogProcesso("boletimMMFertDAO = new BoletimMMFertDAO();\n" +
                "        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();\n" +
                "        ConfigCTR configCTR = new ConfigCTR();", activity);
        configCTR.setUltParadaBolConfig(apontMMFertDAO.getUltApont(boletimMMFertDAO.getBoletimMMFertAberto().getIdBolMMFert()).getParadaApontMMFert());

        LogProcessoDAO.getInstance().insertLogProcesso("boletimMMFertDAO.salvarBoletimMMFertFechado(configCTR.getConfig().getHodometroFinalConfig(), activity);", activity);
        boletimMMFertDAO.salvarBoletimMMFertFechado(configCTR.getConfig().getHodometroFinalConfig(), activity);

        EnvioDadosServ.getInstance().envioDados(activity);

    }

    public boolean verEnvioApont() {
        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
        int qtde = apontMMFertDAO.apontEnvioList().size();
        return qtde > 0;
    }

    public boolean verEnvioBolFech() {
        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        return boletimMMFertDAO.verBoletimMMFertFechado();
    }

    public String dadosEnvioBolAbertoMMFert(){

        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        String dadosEnvioBoletim = boletimMMFertDAO.dadosEnvioBoletimMMFertAberto();

        ArrayList<Long> idBoletimArrayList = boletimMMFertDAO.idBoletimArrayList(boletimMMFertDAO.boletimMMFertAbertoList());

        ApontMecanDAO apontMecanDAO = new ApontMecanDAO();
        String dadosEnvioApontMecan = apontMecanDAO.dadosEnvioApontMecan(apontMecanDAO.apontMecanEnvioList(idBoletimArrayList));

        idBoletimArrayList.clear();

        return dadosEnvioBoletim + "_" + dadosEnvioApontMecan;
    }

    public String dadosEnvioBolFechadoMMFert(){

        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        ArrayList<Long> idBoletimArrayList = boletimMMFertDAO.idBoletimArrayList(boletimMMFertDAO.boletimMMFertFechadoList());
        String dadosEnvioBoletim = boletimMMFertDAO.dadosEnvioBoletimMMFertFechado();

        ApontMecanDAO apontMecanDAO = new ApontMecanDAO();
        String dadosEnvioApontMecan = apontMecanDAO.dadosEnvioApontMecan(apontMecanDAO.apontMecanEnvioList(idBoletimArrayList));

        idBoletimArrayList.clear();

        return dadosEnvioBoletim + "_" + dadosEnvioApontMecan;
    }

    public void updateBolAberto(String result, String activity){

        try {

            String[] retorno = result.split("_");

            BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
            boletimMMFertDAO.updateBoletimMMFertAberto(retorno[1]);

            ApontMecanDAO apontMecanDAO = new ApontMecanDAO();
            ArrayList<Long> idApontMecanArrayList = apontMecanDAO.idApontMecanArrayList(retorno[7]);
            apontMecanDAO.updateApontMecan(idApontMecanArrayList);

            EnvioDadosServ.getInstance().envioDados(activity);

        }
        catch (Exception e){
            EnvioDadosServ.status = 1;
            LogErroDAO.getInstance().insertLogErro(e);
        }

    }

    public void updateBolFechado(String result, String activity){

        try {

            String[] retorno = result.split("_");

            BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
            ArrayList<BoletimMMFertBean> boletimArrayList = boletimMMFertDAO.boletimMMFertArrayList(retorno[1]);
            boletimMMFertDAO.updateBoletimMMFertEnvio(boletimArrayList);

            ApontMecanDAO apontMecanDAO = new ApontMecanDAO();
            ArrayList<Long> idApontMecanArrayList = apontMecanDAO.idApontMecanArrayList(retorno[7]);
            apontMecanDAO.updateApontMecan(idApontMecanArrayList);
            EnvioDadosServ.getInstance().envioDados(activity);

        }
        catch (Exception e){
            EnvioDadosServ.status = 1;
            LogErroDAO.getInstance().insertLogErro(e);
        }

    }

    public void deleteBolEnviado(){

        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        ArrayList<BoletimMMFertBean> boletimMMFertArrayList = boletimMMFertDAO.boletimExcluirArrayList();

        for (BoletimMMFertBean boletimMMFertBean : boletimMMFertArrayList) {

            ApontMecanDAO apontMecanDAO = new ApontMecanDAO();
            apontMecanDAO.deleteApontMecan(boletimMMFertBean.getIdBolMMFert());

            boletimMMFertDAO.deleteBoletimMMFert(boletimMMFertBean.getIdBolMMFert());

        }

        boletimMMFertArrayList.clear();

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////// ATIVIDADES ///////////////////////////////////////////////

    public AtividadeBean getAtividade(Long idAtiv){
        AtividadeDAO atividadeDAO = new AtividadeDAO();
        return atividadeDAO.getAtividade(idAtiv);
    }

    public ArrayList getAtivArrayList(Long nroOS, String activity){
        ConfigCTR configCTR = new ConfigCTR();
        OSDAO osDAO = new OSDAO();
        AtividadeDAO atividadeDAO = new AtividadeDAO();
        Long idOS = 0L;
        if(osDAO.verOS(nroOS)){
            idOS = osDAO.getOS(nroOS).getIdOS();
        }
        LogProcessoDAO.getInstance().insertLogProcesso("Long idOS = 0L;\n" +
                "        if(osDAO.verOS(nroOS)){\n" +
                "            idOS = osDAO.getOS(nroOS).getIdOS();\n" +
                "        }\n" +
                "        return atividadeDAO.retAtivArrayList(configCTR.getEquip().getIdEquip(), idOS);", activity);
        return atividadeDAO.retAtivArrayList(configCTR.getEquip().getIdEquip(), idOS);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////// FUNCIONARIOS ///////////////////////////////////////////////

    public boolean hasElemFunc(){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.hasElements();
    }

    public FuncBean getMatricFunc(){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.getFunc(getBoletimMMFertAberto().getMatricFuncBolMMFert());
    }

    public boolean verFunc(Long matricFunc){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.verFunc(matricFunc);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////// TURNO /////////////////////////////////////////////////////

    public List<TurnoBean> getTurnoCodList(String activity){
        ConfigCTR configCTR = new ConfigCTR();
        TurnoDAO turnoDAO = new TurnoDAO();
        LogProcessoDAO.getInstance().insertLogProcesso("return turnoDAO.getTurnoCodList(configCTR.getEquip().getCodTurno());", activity);
        return turnoDAO.getTurnoCodList(configCTR.getEquip().getCodTurno());
    }

    public TurnoBean getTurnoId(Long idTurno){
        TurnoDAO turnoDAO = new TurnoDAO();
        return turnoDAO.getTurnoId(idTurno);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////// MOTOMEC ////////////////////////////////////////////

    public void setMotoMecBean(MotoMecBean motoMecBean) {
        this.motoMecBean = motoMecBean;
    }

    public List<MotoMecBean> motoMecList() {
        ConfigCTR configCTR = new ConfigCTR();
        MotoMecDAO motoMecDAO = new MotoMecDAO();
        return motoMecDAO.motoMecList((POMContext.aplic == 2) ? 1L : configCTR.getConfig().getFuncaoComposto());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////// ATUALIZAÇÃO DE DADOS POR CLASSE /////////////////////////////////

    public void atualDados(Context telaAtual, Class telaProx, ProgressDialog progressDialog, String tipoAtual, int tipoReceb, String activity) {
        LogProcessoDAO.getInstance().insertLogProcesso("ArrayList classeArrayList = classeAtual(tipoAtual, activity);\n" +
                "        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, classeArrayList, tipoReceb, activity);", activity);
        ArrayList classeArrayList = classeAtual(tipoAtual, activity);
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, classeArrayList, tipoReceb, activity);
    }

    public ArrayList<String> classeAtual(String tipoAtual, String activity){
        ArrayList<String> classeArrayList = new ArrayList();
        switch (tipoAtual) {
            case "Leira":
                classeArrayList.add("LeiraBean");
                break;
            case "Operador":
                classeArrayList.add("FuncBean");
                break;
            case "Turno":
                classeArrayList.add("TurnoBean");
                break;
            case "EquipSeg":
                classeArrayList.add("EquipSegBean");
                break;
            case "Parada":
                classeArrayList.add("RAtivParadaBean");
                classeArrayList.add("ParadaBean");
                break;
            case "Bocal":
                classeArrayList.add("BocalBean");
                break;
            case "Pressao":
                classeArrayList.add("PressaoBocalBean");
                break;
            case "Frente":
                classeArrayList.add("FrenteBean");
                break;
            case "Propriedade":
                classeArrayList.add("OSBean");
                classeArrayList.add("PropriedadeBean");
                break;
            case "OS":
                classeArrayList.add("OSBean");
                classeArrayList.add("AtividadeBean");
                classeArrayList.add("EquipSegBean");
                classeArrayList.add("FrenteBean");
                classeArrayList.add("MotoMecBean");
                classeArrayList.add("PropriedadeBean");
                break;
        }
        return classeArrayList;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////// VERIFICAÇÃO E ATUALIZAÇÃO DE DADOS ////////////////////////////

    public void verOS(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog, String activity){
        OSDAO osDAO = new OSDAO();
        osDAO.verOS(dado, telaAtual, telaProx, progressDialog, activity);
    }

    public void verAtiv(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ConfigCTR configCTR = new ConfigCTR();
        AtividadeDAO atividadeDAO = new AtividadeDAO();
        atividadeDAO.verAtiv(dado + "_" + configCTR.getEquip().getNroEquip(), telaAtual, telaProx, progressDialog);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////APONTAMENTO /////////////////////////////////////////////

    public List<ApontMMFertBean> apontList(){
        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
        return apontMMFertDAO.apontMMFertList(getBoletimMMFertAberto().getIdBolMMFert());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////// VERIFICAÇÃO APONT /////////////////////////////////////////////

    public boolean hasApontBolAberto(){
        ApontMMFertDAO apontMMDAO = new ApontMMFertDAO();
        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        return apontMMDAO.hasApontBol(boletimMMFertDAO.getBoletimMMFertAberto().getIdBolMMFert());
    }

    public boolean verUltApontAtiv() {
        ApontMMFertDAO apontMMDAO = new ApontMMFertDAO();
        BoletimMMFertDAO boletimMMFertDAO = new BoletimMMFertDAO();
        return apontMMDAO.verUltApontAtiv(boletimMMFertDAO.getBoletimMMFertAberto().getIdBolMMFert());
    }

    public boolean verifBackupApont(Long idParada) {
        ConfigCTR configCTR = new ConfigCTR();
        BoletimMMFertDAO boletimMMDAO = new BoletimMMFertDAO();
        ApontMMFertDAO apontMMFertDAO = new ApontMMFertDAO();
        return apontMMFertDAO.verifBackupApont(boletimMMDAO.getBoletimMMFertAberto().getIdBolMMFert(), configCTR.getConfig().getNroOSConfig()
                                                , configCTR.getConfig().getIdAtivConfig(), idParada);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

}
