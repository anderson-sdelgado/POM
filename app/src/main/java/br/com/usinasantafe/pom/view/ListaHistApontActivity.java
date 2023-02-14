package br.com.usinasantafe.pom.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import br.com.usinasantafe.pom.POMContext;
import br.com.usinasantafe.pom.R;
import br.com.usinasantafe.pom.model.dao.LogProcessoDAO;

public class ListaHistApontActivity extends ActivityGeneric {

    private POMContext pomContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_hist_apont);

        pomContext = (POMContext) getApplication();

        Button buttonRetHistorico = findViewById(R.id.buttonRetHistorico);

        LogProcessoDAO.getInstance().insertLogProcesso("ListView listaHistorico = findViewById(R.id.listaHistorico);\n" +
                "        AdapterListHistorico adapterListHistorico = new AdapterListHistorico(this, POMContext.getMotoMecFertCTR().apontList());\n" +
                "        listaHistorico.setAdapter(adapterListHistorico);", getLocalClassName());
        ListView listaHistorico = findViewById(R.id.listaHistorico);
        AdapterListHistorico adapterListHistorico = new AdapterListHistorico(this, pomContext.getMotoMecFertCTR().apontList());
        listaHistorico.setAdapter(adapterListHistorico);

        buttonRetHistorico.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonRetHistorico.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if(POMContext.aplic == 1){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(POMContext.aplic == 1){\n" +
                            "                    Intent it = new Intent(ListaHistApontActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                    Intent it = new Intent(ListaHistApontActivity.this, MenuPrincPMMActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(POMContext.aplic == 2){
                    LogProcessoDAO.getInstance().insertLogProcesso("else if(POMContext.aplic == 2){\n" +
                            "                    Intent it = new Intent(ListaHistApontActivity.this, MenuPrincECMActivity.class);", getLocalClassName());
                    Intent it = new Intent(ListaHistApontActivity.this, MenuPrincECMActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(POMContext.aplic == 3){
                    LogProcessoDAO.getInstance().insertLogProcesso("else if(POMContext.aplic == 3){\n" +
                            "                    Intent it = new Intent(ListaHistApontActivity.this, MenuPrincPCOMPActivity.class);", getLocalClassName());
                    Intent it = new Intent(ListaHistApontActivity.this, MenuPrincPCOMPActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });

    }

    public void onBackPressed()  {
    }

}
