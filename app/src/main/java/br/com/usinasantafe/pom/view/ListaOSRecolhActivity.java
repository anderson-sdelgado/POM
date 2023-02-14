package br.com.usinasantafe.pom.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import br.com.usinasantafe.pom.POMContext;
import br.com.usinasantafe.pom.R;
import br.com.usinasantafe.pom.model.dao.LogProcessoDAO;

public class ListaOSRecolhActivity extends ActivityGeneric {

    private POMContext pomContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_os_recolh);

        Button buttonRetRecMang = findViewById(R.id.buttonRetRecMang);

        pomContext = (POMContext) getApplication();

        LogProcessoDAO.getInstance().insertLogProcesso("ListView listaRecMang = findViewById(R.id.listaRecMang);\n" +
                "        AdapterListRecolh adapterListRecolh = new AdapterListRecolh(this, pomContext.getMotoMecFertCTR().recolhList());\n" +
                "        listaRecMang.setAdapter(adapterListRecolh);", getLocalClassName());

        ListView listaRecMang = findViewById(R.id.listaRecMang);
        AdapterListRecolh adapterListRecolh = new AdapterListRecolh(this, pomContext.getMotoMecFertCTR().recolhList());
        listaRecMang.setAdapter(adapterListRecolh);

        listaRecMang.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                LogProcessoDAO.getInstance().insertLogProcesso("listaRecMang.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                        "                                    long id) {\n" +
                        "                pomContext.getMotoMecFertCTR().setContRecolh(position);\n" +
                        "                Intent it = new Intent(ListaOSRecolhActivity.this, RecolhimentoActivity.class);", getLocalClassName());
                pomContext.getMotoMecFertCTR().setContRecolh(position);
                Intent it = new Intent(ListaOSRecolhActivity.this, RecolhimentoActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonRetRecMang.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonRetRecMang.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                Intent it = new Intent(ListaOSRecolhActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                Intent it = new Intent(ListaOSRecolhActivity.this, MenuPrincPMMActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed() {
    }

}
