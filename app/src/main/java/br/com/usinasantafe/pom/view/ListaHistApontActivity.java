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

        LogProcessoDAO.getInstance().insertLogProcesso("ListView historicoListView = findViewById(R.id.listaHistorico);\n" +
                "        AdapterListHistorico adapterListHistorico = new AdapterListHistorico(this, pomContext.getMecanicoCTR().apontMecanList());\n" +
                "        historicoListView.setAdapter(adapterListHistorico);", getLocalClassName());
        ListView historicoListView = findViewById(R.id.listaHistorico);
        AdapterListHistorico adapterListHistorico = new AdapterListHistorico(this, pomContext.getMecanicoCTR().apontMecanList());
        historicoListView.setAdapter(adapterListHistorico);

        buttonRetHistorico.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonRetHistorico.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "            Intent it = new Intent(ListaHistApontActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
            Intent it = new Intent(ListaHistApontActivity.this, MenuPrincActivity.class);
            startActivity(it);
            finish();
        });

    }

    public void onBackPressed()  {
    }

}
