package br.com.usinasantafe.pom.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pom.POMContext;
import br.com.usinasantafe.pom.R;
import br.com.usinasantafe.pom.model.dao.LogProcessoDAO;

public class EquipActivity extends ActivityGeneric {

    private TextView textViewCodEquip;
    private TextView textViewDescEquip;
    private POMContext pmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip);

        pmmContext = (POMContext) getApplication();

        textViewCodEquip = findViewById(R.id.textViewCodEquip);
        textViewDescEquip = findViewById(R.id.textViewDescEquip);
        Button buttonOkEquip = findViewById(R.id.buttonOkEquip);
        Button buttonCancEquip = findViewById(R.id.buttonCancEquip);

        LogProcessoDAO.getInstance().insertLogProcesso("textViewCodEquip.setText(String.valueOf(" + pmmContext.getConfigCTR().getEquip().getNroEquip() + "));\n" +
                "        textViewDescEquip.setText(pmmContext.getConfigCTR().getEquip().getDescrClasseEquip());", getLocalClassName());
        textViewCodEquip.setText(String.valueOf(pmmContext.getConfigCTR().getEquip().getNroEquip()));
        textViewDescEquip.setText(pmmContext.getConfigCTR().getEquip().getDescrClasseEquip());

        buttonOkEquip.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("        buttonOkEquip.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "Intent it = new Intent(EquipActivity.this, ListaTurnoActivity.class);" + pmmContext.getConfigCTR().getEquip().getIdEquip() + ");", getLocalClassName());
            Intent it = new Intent(EquipActivity.this, ListaTurnoActivity.class);
            startActivity(it);
            finish();
        });

        buttonCancEquip.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonCancEquip.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                    Intent it = new Intent(EquipActivity.this, OperadorActivity.class);", getLocalClassName());
            Intent it = new Intent(EquipActivity.this, OperadorActivity.class);
            startActivity(it);
            finish();

        });

    }

    public void onBackPressed()  {
    }

}
