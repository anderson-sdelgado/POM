package br.com.usinasantafe.pom.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pom.POMContext;
import br.com.usinasantafe.pom.R;
import br.com.usinasantafe.pom.model.bean.estaticas.ItemCheckListBean;
import br.com.usinasantafe.pom.model.bean.variaveis.RespItemCheckListBean;
import br.com.usinasantafe.pom.model.dao.LogProcessoDAO;

public class ItemCheckListActivity extends ActivityGeneric {

    private POMContext pomContext;
    private TextView textViewItemChecklist;
    private List itemCheckListList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_checklist);

        pomContext = (POMContext) getApplication();

        textViewItemChecklist = findViewById(R.id.textViewItemChecklist);
        Button buttonConforme = findViewById(R.id.buttonConforme);
        Button buttonNaoConforme = findViewById(R.id.buttonNaoConforme);
        Button buttonReparo = findViewById(R.id.buttonReparo);
        Button buttonCancChecklist = findViewById(R.id.buttonCancChecklist);

        LogProcessoDAO.getInstance().insertLogProcesso("        itemCheckListList = pomContext.getCheckListCTR().getItemList();\n" +
                "        ItemCheckListBean itemCheckListBean = (ItemCheckListBean) itemCheckListList.get(pomContext.getCheckListCTR().getPosCheckList() - 1);\n" +
                "        textViewItemChecklist.setText(pomContext.getCheckListCTR().getPosCheckList() + \" - \" + itemCheckListBean.getDescrItemCheckList());", getLocalClassName());

        itemCheckListList = pomContext.getCheckListCTR().getItemList();
        ItemCheckListBean itemCheckListBean = (ItemCheckListBean) itemCheckListList.get(pomContext.getCheckListCTR().getPosCheckList() - 1);
        textViewItemChecklist.setText(pomContext.getCheckListCTR().getPosCheckList() + " - " + itemCheckListBean.getDescrItemCheckList());

        buttonConforme.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonConforme.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                proximaTela(1L);", getLocalClassName());
            proximaTela(1L);
        });

        buttonNaoConforme.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonNaoConforme.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                proximaTela(2L);", getLocalClassName());
            proximaTela(2L);
        });

        buttonReparo.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonReparo.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                proximaTela(3L);", getLocalClassName());
            proximaTela(3L);
        });

        buttonCancChecklist.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonCancChecklist.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                retornoTela();", getLocalClassName());
            retornoTela();
        });

    }

    public void proximaTela(Long opcao){

        LogProcessoDAO.getInstance().insertLogProcesso("ItemCheckListBean itemCheckListBean = (ItemCheckListBean) itemCheckListList.get(pomContext.getCheckListCTR().getPosCheckList() - 1);", getLocalClassName());

        ItemCheckListBean itemCheckListBean = (ItemCheckListBean) itemCheckListList.get(pomContext.getCheckListCTR().getPosCheckList() - 1);
        LogProcessoDAO.getInstance().insertLogProcesso("ItemCheckListBean itemCheckListBean = (ItemCheckListBean) itemCheckListList.get(pomContext.getCheckListCTR().getPosCheckList() - 1);\n" +
                "        RespItemCheckListBean respItemCheckListBean = new RespItemCheckListBean();\n" +
                "        respItemCheckListBean.setIdItBDItCL(" + itemCheckListBean.getIdItemCheckList() + ");\n" +
                "        respItemCheckListBean.setOpItCL(" + opcao + ");", getLocalClassName());

        RespItemCheckListBean respItemCheckListBean = new RespItemCheckListBean();
        respItemCheckListBean.setIdItBDItCL(itemCheckListBean.getIdItemCheckList());
        respItemCheckListBean.setOpItCL(opcao);

        if(pomContext.getCheckListCTR().verCabecAberto()) {
            LogProcessoDAO.getInstance().insertLogProcesso("if(pomContext.getCheckListCTR().verCabecAberto()) {\n" +
                    "            pomContext.getCheckListCTR().setRespCheckList(respItemCheckListBean);", getLocalClassName());
            pomContext.getCheckListCTR().setRespCheckList(respItemCheckListBean);
            if (pomContext.getCheckListCTR().qtdeItemCheckList() == pomContext.getCheckListCTR().getPosCheckList()) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (pomContext.getCheckListCTR().qtdeItemCheckList() == pomContext.getCheckListCTR().getPosCheckList()) {\n" +
                        "                pomContext.getConfigCTR().setCheckListConfig(pomContext.getConfigCTR().getConfig().getIdTurnoConfig());\n" +
                        "                pomContext.getCheckListCTR().salvarBolFechado();\n" +
                        "                Intent it = new Intent(ItemCheckListActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                pomContext.getConfigCTR().setCheckListConfig(pomContext.getConfigCTR().getConfig().getIdTurnoConfig());
                pomContext.getCheckListCTR().salvarBolFechado(getLocalClassName());
                Intent it = new Intent(ItemCheckListActivity.this, MenuPrincActivity.class);
                startActivity(it);
                finish();
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("pomContext.getCheckListCTR().setPosCheckList(pomContext.getCheckListCTR().getPosCheckList() + 1);\n" +
                        "                itemCheckListBean = (ItemCheckListBean) itemCheckListList.get(pomContext.getCheckListCTR().getPosCheckList() - 1);\n" +
                        "                textViewItemChecklist.setText(pomContext.getCheckListCTR().getPosCheckList() + \" - \" + itemCheckListBean.getDescrItemCheckList());", getLocalClassName());
                pomContext.getCheckListCTR().setPosCheckList(pomContext.getCheckListCTR().getPosCheckList() + 1);
                itemCheckListBean = (ItemCheckListBean) itemCheckListList.get(pomContext.getCheckListCTR().getPosCheckList() - 1);
                textViewItemChecklist.setText(pomContext.getCheckListCTR().getPosCheckList() + " - " + itemCheckListBean.getDescrItemCheckList());
            }
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                    "                Intent it = new Intent(ItemCheckListActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
            Intent it = new Intent(ItemCheckListActivity.this, MenuPrincActivity.class);
            startActivity(it);
            finish();
        }

    }

    public void retornoTela(){
        LogProcessoDAO.getInstance().insertLogProcesso("public void retornoTela(){", getLocalClassName());
        if(pomContext.getCheckListCTR().getPosCheckList() > 1){
            LogProcessoDAO.getInstance().insertLogProcesso("if(pomContext.getCheckListCTR().getPosCheckList() > 1){\n" +
                    "            pomContext.getCheckListCTR().setPosCheckList(pomContext.getCheckListCTR().getPosCheckList() - 1);\n" +
                    "            ItemCheckListBean itemCheckListBean = (ItemCheckListBean) itemCheckListList.get(pomContext.getCheckListCTR().getPosCheckList() - 1);\n" +
                    "            textViewItemChecklist.setText(pomContext.getCheckListCTR().getPosCheckList() + \" - \" + itemCheckListBean.getDescrItemCheckList());", getLocalClassName());
            pomContext.getCheckListCTR().setPosCheckList(pomContext.getCheckListCTR().getPosCheckList() - 1);
            ItemCheckListBean itemCheckListBean = (ItemCheckListBean) itemCheckListList.get(pomContext.getCheckListCTR().getPosCheckList() - 1);
            textViewItemChecklist.setText(pomContext.getCheckListCTR().getPosCheckList() + " - " + itemCheckListBean.getDescrItemCheckList());
        }
    }

    public void onBackPressed()  {
    }

}
