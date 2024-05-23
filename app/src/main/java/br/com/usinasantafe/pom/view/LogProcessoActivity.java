package br.com.usinasantafe.pom.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import br.com.usinasantafe.pom.POMContext;
import br.com.usinasantafe.pom.R;
import br.com.usinasantafe.pom.model.dao.LogProcessoDAO;

public class LogProcessoActivity extends ActivityGeneric {

    private POMContext pomContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_processo);

        pomContext = (POMContext) getApplication();

        Button buttonAvancaLogProcesso = findViewById(R.id.buttonAvancaLogProcesso);
        Button buttonRetLogProcesso = findViewById(R.id.buttonRetLogProcesso);

        LogProcessoDAO.getInstance().insertLogProcesso("ListView listaHistorico = findViewById(R.id.listaHistorico);\n" +
                "        AdapterListHistorico adapterListHistorico = new AdapterListHistorico(this, pomContext.getConfigCTR().logProcessoList());\n" +
                "        listaHistorico.setAdapter(adapterListHistorico);", getLocalClassName());
        ListView listViewLogProcesso = findViewById(R.id.listViewLogProcesso);
        AdapterListProcesso adapterListProcesso = new AdapterListProcesso(this, pomContext.getConfigCTR().logProcessoList());
        listViewLogProcesso.setAdapter(adapterListProcesso);

        buttonAvancaLogProcesso.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonAvancaLogProcesso.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "Intent it = new Intent(LogProcessoActivity.this, LogBaseDadoActivity.class);", getLocalClassName());
            Intent it = new Intent(LogProcessoActivity.this, LogBaseDadoActivity.class);
            startActivity(it);
            finish();
        });

        buttonRetLogProcesso.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonRetLogProcesso.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 12L){
                LogProcessoDAO.getInstance().insertLogProcesso("if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 12L){\n" +
                        "Intent it = new Intent(LogProcessoActivity.this, TelaInicialActivity.class);", getLocalClassName());
                Intent it = new Intent(LogProcessoActivity.this, TelaInicialActivity.class);
                startActivity(it);
                finish();
            }
            else if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 23L){
                LogProcessoDAO.getInstance().insertLogProcesso("else if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 23L){\n" +
                        "Intent it = new Intent(LogProcessoActivity.this, MenuPrincPMMActivity.class);", getLocalClassName());
                Intent it = new Intent(LogProcessoActivity.this, MenuPrincActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed() {
    }

}