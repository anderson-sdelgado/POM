package br.com.usinasantafe.pom.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pom.POMContext;
import br.com.usinasantafe.pom.R;
import br.com.usinasantafe.pom.model.bean.estaticas.PropriedadeBean;
import br.com.usinasantafe.pom.model.dao.LogProcessoDAO;

public class MsgPropriedadeActivity extends ActivityGeneric {

    private POMContext pomContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_propriedade);

        pomContext = (POMContext) getApplication();

        Button buttonMsgPropriedadeOk = findViewById(R.id.buttonMsgPropriedadeOk);
        Button buttonMsgPropriedadeCanc = findViewById(R.id.buttonMsgPropriedadeCanc);
        TextView textViewMsgDescrPropriedade = findViewById(R.id.textViewMsgDescrPropriedade);

        LogProcessoDAO.getInstance().insertLogProcesso("PropriedadeBean propriedadeBean = pomContext.getConfigCTR().getPropriedade();", getLocalClassName());
        PropriedadeBean propriedadeBean = pomContext.getConfigCTR().getPropriedade();
        textViewMsgDescrPropriedade.setText(propriedadeBean.getCodPropriedade() + " - " + propriedadeBean.getDescrPropriedade());

        buttonMsgPropriedadeOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonMsgPropriedadeOk.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                pomContext.getConfigCTR().setFrentePropriedade();\n" +
                        "                pomContext.getConfigCTR().clearOSAtiv();\n" +
                        "                pomContext.getCecCTR().salvarPrecCECAberto();", getLocalClassName());

                pomContext.getConfigCTR().setFrentePropriedade();
                pomContext.getConfigCTR().clearOSAtiv();

                pomContext.getCecCTR().salvarPrecCECAberto();

                LogProcessoDAO.getInstance().insertLogProcesso("Intent it = new Intent(MsgPropriedadeActivity.this, ListaAtividadeActivity.class);", getLocalClassName());
                Intent it = new Intent(MsgPropriedadeActivity.this, ListaAtividadeActivity.class);
                startActivity(it);
                finish();

            }
        });

        buttonMsgPropriedadeCanc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonMsgPropriedadeCanc.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                Intent it = new Intent(MsgPropriedadeActivity.this, PropriedadeActivity.class);", getLocalClassName());
                Intent it = new Intent(MsgPropriedadeActivity.this, PropriedadeActivity.class);
                startActivity(it);
                finish();

            }
        });

    }

    public void onBackPressed()  {
    }
}