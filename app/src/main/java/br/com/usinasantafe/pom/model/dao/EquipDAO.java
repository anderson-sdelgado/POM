package br.com.usinasantafe.pom.model.dao;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import br.com.usinasantafe.pom.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pom.model.bean.estaticas.REquipAtivBean;
import br.com.usinasantafe.pom.util.VerifDadosServ;

public class EquipDAO {

    public EquipDAO() {
    }

    public EquipBean getEquip(){
        EquipBean equipBean = new EquipBean();
        List<EquipBean> equipList = equipBean.all();
        equipBean = equipList.get(0);
        equipList.clear();
        if ((equipBean.getTipoEquipFert() == 1) || (equipBean.getTipoEquipFert() == 2)) {
            equipBean.setTipoEquip(2L);
        } else {
            equipBean.setTipoEquip(1L);
        }
        return equipBean;
    }

    public void verEquip(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog, String activity, int tipo){
        LogProcessoDAO.getInstance().insertLogProcesso("VerifDadosServ.getInstance().verifDados(dado, \"Equip\", telaAtual, telaProx, progressDialog, activity);", activity);
        VerifDadosServ.getInstance().verifDados(dado, "Equip", telaAtual, telaProx, progressDialog, activity, tipo);
    }

    public EquipBean recDadosEquip(JSONArray jsonArray) throws JSONException {

        EquipBean equipBean = new EquipBean();
        equipBean.deleteAll();

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject objeto = jsonArray.getJSONObject(i);
            Gson gson = new Gson();
            equipBean = gson.fromJson(objeto.toString(), EquipBean.class);
            equipBean.insert();

        }

        return equipBean;

    }

    public void recDadosREquipAtiv(JSONArray jsonArray) throws JSONException {

        REquipAtivBean rEquipAtivBean = new REquipAtivBean();
        rEquipAtivBean.deleteAll();

        for (int j = 0; j < jsonArray.length(); j++) {
            JSONObject objeto = jsonArray.getJSONObject(j);
            Gson gson = new Gson();
            REquipAtivBean rEquipAtiv = gson.fromJson(objeto.toString(), REquipAtivBean.class);
            rEquipAtiv.insert();
        }

    }

}
