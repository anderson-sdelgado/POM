package br.com.usinasantafe.pom.model.dao;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pom.model.bean.estaticas.ItemOSMecanBean;
import br.com.usinasantafe.pom.model.bean.variaveis.ApontMecanBean;
import br.com.usinasantafe.pom.model.pst.EspecificaPesquisa;

public class ItemOSMecanDAO {

    public ItemOSMecanDAO() {
    }

    public List<ItemOSMecanBean> itemOSMecanIdOSList(Long idOS){
        ItemOSMecanBean itemOSMecanBean = new ItemOSMecanBean();
        return itemOSMecanBean.getAndOrderBy("idOS", idOS,"seqItemOS",true);
    }

    public List<ItemOSMecanBean> itemOSMecanList(Long idOS, Long seqItemOS){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdOS(idOS));
        pesqArrayList.add(getPesqSeqItemOS(seqItemOS));
        ApontMecanBean apontMecanBean = new ApontMecanBean();
        return apontMecanBean.get(pesqArrayList);
    }

    public ItemOSMecanBean getItemOSMecan(Long idOS, Long seqItemOS){
        List<ItemOSMecanBean> itemOSMecanList = itemOSMecanList(idOS, seqItemOS);
        ItemOSMecanBean itemOSMecanBean = itemOSMecanList.get(0);
        itemOSMecanList.clear();
        return itemOSMecanBean;
    }

    public boolean verItemOSMecan(Long idOS, Long seqItemOS){
        List<ItemOSMecanBean> itemOSMecanList = itemOSMecanList(idOS, seqItemOS);
        boolean ret = (itemOSMecanList.size() > 0);
        itemOSMecanList.clear();
        return ret;
    }

    public void itemOSDelAll(){
        ItemOSMecanBean itemOSMecanBean = new ItemOSMecanBean();
        itemOSMecanBean.deleteAll();
    }


    public void recDadosItemOSMecan(JSONArray jsonArray) throws JSONException {

        itemOSDelAll();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject objeto = jsonArray.getJSONObject(i);
            Gson gson = new Gson();
            ItemOSMecanBean itemOSMecanBean = gson.fromJson(objeto.toString(), ItemOSMecanBean.class);
            itemOSMecanBean.insert();
        }

    }

    private EspecificaPesquisa getPesqIdOS(Long idOS){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idOS");
        pesquisa.setValor(idOS);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqSeqItemOS(Long seqItemOS){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("seqItemOS");
        pesquisa.setValor(seqItemOS);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
