package br.com.usinasantafe.pom.model.dao;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import br.com.usinasantafe.pom.model.bean.AtualAplicBean;
import br.com.usinasantafe.pom.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pom.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pom.util.Tempo;

public class ConfigDAO {

    public ConfigDAO() {
    }

    public boolean hasElements(){
        ConfigBean configBean = new ConfigBean();
        return configBean.hasElements();
    }

    public ConfigBean getConfig(){
        ConfigBean configBean = new ConfigBean();
        List<ConfigBean> configList = configBean.all();
        configBean = configList.get(0);
        configList.clear();
        return configBean;
    }

    public boolean verSenha(String senha){
        ConfigBean configBean = new ConfigBean();
        List<ConfigBean> configList = configBean.get("senhaConfig", senha);
        boolean ret = configList.size() > 0;
        configList.clear();
        return ret;
    }

    public void salvarConfig(String senha){
        ConfigBean configBean = new ConfigBean();
        configBean.deleteAll();
        configBean.setUltTurnoCLConfig(0L);
        configBean.setDtUltCLConfig("");
        configBean.setDtServConfig("");
        configBean.setDifDthrConfig(0L);
        configBean.setVerRecInformativo(0L);
        configBean.setNroOSConfig(0L);
        configBean.setIdAtivConfig(0L);
        configBean.setUltParadaBolConfig(0L);
        configBean.setSenhaConfig(senha);
        configBean.setPosicaoTela(0L);
        configBean.setStatusRetVerif(0L);
        configBean.insert();
        configBean.commit();
    }

    public void setEquipConfig(EquipBean equipBean){
        ConfigBean configBean = getConfig();
        configBean.setEquipConfig(equipBean.getIdEquip());
        configBean.setHorimetroConfig(equipBean.getHorimetroEquip());
        configBean.update();
    }

    public void setStatusConConfig(Long status){
        ConfigBean configBean = getConfig();
        configBean.setStatusConConfig(status);
        configBean.update();
    }

    public void setNroOSConfig(Long nroOS){
        ConfigBean configBean = getConfig();
        configBean.setNroOSConfig(nroOS);
        configBean.update();
    }

    public void setAtivConfig(Long idAtiv){
        ConfigBean configBean = getConfig();
        configBean.setIdAtivConfig(idAtiv);
        configBean.update();
    }

    public void setUltParadaBolConfig(Long idParada){
        ConfigBean configBean = getConfig();
        configBean.setUltParadaBolConfig(idParada);
        configBean.update();
    }

    public void setHorimetroConfig(Double horimetro){
        ConfigBean configBean = getConfig();
        configBean.setHorimetroConfig(horimetro);
        configBean.update();
    }

    public void setCheckListConfig(Long idTurno){
        ConfigBean configBean = getConfig();
        configBean.setUltTurnoCLConfig(idTurno);
        configBean.setDtUltCLConfig(Tempo.getInstance().dtAtualString());
        configBean.update();
    }

    public void setDifDthrConfig(Long difDthr){
        ConfigBean configBean = getConfig();
        configBean.setDifDthrConfig(difDthr);
        configBean.update();
    }

    public void setPosicaoTela(Long posicaoTela){
        ConfigBean configBean = getConfig();
        configBean.setPosicaoTela(posicaoTela);
        configBean.update();
    }

    public void setStatusRetVerif(Long statusRetVerif){
        ConfigBean configBean = getConfig();
        configBean.setStatusRetVerif(statusRetVerif);
        configBean.update();
    }


    public void setMatricFuncConfig(Long matricFuncConfig) {
        ConfigBean configBean = getConfig();
        configBean.setMatricFuncConfig(matricFuncConfig);
        configBean.update();
    }

    public void setIdTurnoConfig(Long idTurnoConfig) {
        ConfigBean configBean = getConfig();
        configBean.setIdTurnoConfig(idTurnoConfig);
        configBean.update();
    }

    public void setHodometroInicialConfig(Double hodometroInicialBolMMFert, Double longitudeBolMMFert, Double latitudeBolMMFert) {
        ConfigBean configBean = getConfig();
        configBean.setHodometroInicialConfig(hodometroInicialBolMMFert, longitudeBolMMFert, latitudeBolMMFert);
        configBean.update();
    }

    public void setHodometroFinalConfig(Double hodometroFinalBolMMFert) {
        ConfigBean configBean = getConfig();
        configBean.setHodometroFinalConfig(hodometroFinalBolMMFert);
        configBean.update();
    }

    public AtualAplicBean recAtual(JSONArray jsonArray) throws JSONException {

        JSONObject objeto = jsonArray.getJSONObject(0);
        Gson gson = new Gson();
        AtualAplicBean atualAplicBean = gson.fromJson(objeto.toString(), AtualAplicBean.class);

        ConfigBean configBean = getConfig();
        configBean.setDtServConfig(atualAplicBean.getDthr());
        configBean.setAtualCheckList(atualAplicBean.getFlagAtualCheckList());
        configBean.update();

        return atualAplicBean;

    }

}
