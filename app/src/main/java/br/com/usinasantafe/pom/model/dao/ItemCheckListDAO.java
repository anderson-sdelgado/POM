package br.com.usinasantafe.pom.model.dao;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import br.com.usinasantafe.pom.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pom.model.bean.estaticas.ItemCheckListBean;
import br.com.usinasantafe.pom.util.VerifDadosServ;

public class ItemCheckListDAO {

    public ItemCheckListDAO() {
    }

    public void atualCheckList(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog, String activity){
        VerifDadosServ.getInstance().verifDados(dado, "CheckList", telaAtual, telaProx, progressDialog, activity);
    }

    public void recDadosCheckList(JSONArray jsonArray) throws JSONException {

        ItemCheckListBean itemCheckListBean = new ItemCheckListBean();
        itemCheckListBean.deleteAll();

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject objeto = jsonArray.getJSONObject(i);
            Gson gson = new Gson();
            ItemCheckListBean itemCheckList = gson.fromJson(objeto.toString(), ItemCheckListBean.class);
            itemCheckList.insert();

        }

    }

    public int qtdeItem(Long idChecklist){
        ItemCheckListBean itemCheckListBean = new ItemCheckListBean();
        return itemCheckListBean.get("idCheckList", idChecklist).size();
    }

    public List<ItemCheckListBean> getItemList(EquipBean equipBean){
        ItemCheckListBean itemCheckListBean = new ItemCheckListBean();
        List<ItemCheckListBean> itemCheckListList = itemCheckListBean.getAndOrderBy("idCheckList", equipBean.getIdCheckList(), "idItemCheckList", true);
        return itemCheckListList;
    }

}
