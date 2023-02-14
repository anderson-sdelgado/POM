package br.com.usinasantafe.pom.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pom.POMContext;
import br.com.usinasantafe.pom.R;
import br.com.usinasantafe.pom.model.dao.LogProcessoDAO;

public class DesengCarretaActivity extends ActivityGeneric {

    private POMContext pomContext;
    private TextView textViewMsgDesengCarreta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deseng_carreta);

        pomContext = (POMContext) getApplication();
        textViewMsgDesengCarreta = findViewById(R.id.textViewMsgDesengCarreta);

        Button buttonSimDesengate = findViewById(R.id.buttonSimDesengate);
        Button buttonNaoDesengate = findViewById(R.id.buttonNaoDesengate);

        LogProcessoDAO.getInstance().insertLogProcesso("textViewMsgDesengCarreta.setText(pomContext.getMotoMecFertCTR().getDescrCarreta());", getLocalClassName());
        textViewMsgDesengCarreta.setText(pomContext.getMotoMecFertCTR().getDescrCarreta());

        buttonSimDesengate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonSimDesengate.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                pomContext.getMotoMecFertCTR().delCarreta();\n" +
                        "                if (connectNetwork) {\n" +
                        "                    pomContext.getConfigCTR().setStatusConConfig(1L);\n" +
                        "                }\n" +
                        "                else{\n" +
                        "                    pomContext.getConfigCTR().setStatusConConfig(0L);\n" +
                        "                }", getLocalClassName());

                pomContext.getMotoMecFertCTR().delCarreta();

                if (connectNetwork) {
                    pomContext.getConfigCTR().setStatusConConfig(1L);
                }
                else{
                    pomContext.getConfigCTR().setStatusConConfig(0L);
                }

                LogProcessoDAO.getInstance().insertLogProcesso("pomContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude());", getLocalClassName());
                pomContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude(), getLocalClassName());

                if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 19L){
                    LogProcessoDAO.getInstance().insertLogProcesso("if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 19L){\n" +
                            "                    Intent it = new Intent(DesengCarretaActivity.this, MenuPrincECMActivity.class);", getLocalClassName());
                    Intent it = new Intent(DesengCarretaActivity.this, MenuPrincECMActivity.class);
                    startActivity(it);
                    finish();
                }
                else{
                    LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                            "                    Intent it = new Intent(DesengCarretaActivity.this, MenuParadaECMActivity.class);", getLocalClassName());
                    Intent it = new Intent(DesengCarretaActivity.this, ListaParadaECMActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

        buttonNaoDesengate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonNaoDesengate.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 19L){
                    LogProcessoDAO.getInstance().insertLogProcesso("if (pomContext.getConfigCTR().getConfig().getPosicaoTela() == 19L){\n" +
                            "                    Intent it = new Intent(DesengCarretaActivity.this, MenuPrincECMActivity.class);", getLocalClassName());
                    Intent it = new Intent(DesengCarretaActivity.this, MenuPrincECMActivity.class);
                    startActivity(it);
                    finish();
                }
                else{
                    LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                            "                    Intent it = new Intent(DesengCarretaActivity.this, MenuParadaECMActivity.class);", getLocalClassName());
                    Intent it = new Intent(DesengCarretaActivity.this, ListaParadaECMActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });

    }
}