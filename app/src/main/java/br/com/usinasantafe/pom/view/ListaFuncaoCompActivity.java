package br.com.usinasantafe.pom.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.usinasantafe.pom.POMContext;
import br.com.usinasantafe.pom.R;
import br.com.usinasantafe.pom.model.dao.LogProcessoDAO;

public class ListaFuncaoCompActivity extends ActivityGeneric {

    private ListView funcaoCompListView;
    private POMContext pmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_funcao_comp);

        pmmContext = (POMContext) getApplication();

        Button buttonRetFuncao = findViewById(R.id.buttonRetFuncao);

        LogProcessoDAO.getInstance().insertLogProcesso("ArrayList<String> itens = new ArrayList<>();\n" +
                "        itens.add(\"CARREG. TORTA/CINZA\");\n" +
                "        itens.add(\"CARREG. COMPOSTO\");\n" +
                "        AdapterList adapterList = new AdapterList(this, itens);\n" +
                "        funcaoCompListView = findViewById(R.id.listaFuncao);\n" +
                "        funcaoCompListView.setAdapter(adapterList);", getLocalClassName());

        ArrayList<String> itens = new ArrayList<>();

        itens.add("CARREG. TORTA/CINZA");
        itens.add("CARREG. COMPOSTO");

        AdapterList adapterList = new AdapterList(this, itens);
        funcaoCompListView = findViewById(R.id.listaFuncao);
        funcaoCompListView.setAdapter(adapterList);
        funcaoCompListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                LogProcessoDAO.getInstance().insertLogProcesso("listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                        "                                    long id) {\n" +
                        "                TextView textView = v.findViewById(R.id.textViewItemList);\n" +
                        "                String text = textView.getText().toString();", getLocalClassName());
                TextView textView = v.findViewById(R.id.textViewItemList);
                String text = textView.getText().toString();

                if (text.equals("CARREG. TORTA/CINZA")) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (text.equals(\"CARREG. TORTA/CINZA\")) {\n" +
                            "                    pmmContext.getConfigCTR().setFuncaoComposto(2L);", getLocalClassName());
                    pmmContext.getConfigCTR().setFuncaoComposto(2L);
                } else if (text.equals("CARREG. COMPOSTO")) {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else if (text.equals(\"CARREG. COMPOSTO\")) {\n" +
                            "                    pmmContext.getConfigCTR().setFuncaoComposto(3L);", getLocalClassName());
                    pmmContext.getConfigCTR().setFuncaoComposto(3L);
                }

                if(pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 1L){\n" +
                            "                    Intent it = new Intent(ListaFuncaoCompActivity.this, OSActivity.class);", getLocalClassName());
                    Intent it = new Intent(ListaFuncaoCompActivity.this, OSActivity.class);
                    startActivity(it);
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    Intent it = new Intent(ListaFuncaoCompActivity.this, MenuPrincPCOMPActivity.class);", getLocalClassName());
                    Intent it = new Intent(ListaFuncaoCompActivity.this, MenuPrincPCOMPActivity.class);
                    startActivity(it);
                }
                finish();

            }

        });

        buttonRetFuncao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonRetFuncao.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                Intent it = new Intent(ListaFuncaoCompActivity.this, ListaTurnoActivity.class);", getLocalClassName());
                Intent it = new Intent(ListaFuncaoCompActivity.this, ListaTurnoActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

}
