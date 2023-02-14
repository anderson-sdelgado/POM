package br.com.usinasantafe.pom.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pom.POMContext;
import br.com.usinasantafe.pom.R;
import br.com.usinasantafe.pom.model.dao.LogProcessoDAO;

public class VerifOperadorActivity extends ActivityGeneric {

    private POMContext pomContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verif_operador);

        pomContext = (POMContext) getApplication();

        Button buttonManterMotorista = findViewById(R.id.buttonManterMotorista);
        Button buttonAlterarMotorista = findViewById(R.id.buttonAlterarMotorista);
        TextView textViewCodMotorista = findViewById(R.id.textViewCodMotorista);
        TextView textViewNomeMotorista = findViewById(R.id.textViewNomeMotorista);

        textViewCodMotorista.setText(String.valueOf(pomContext.getMotoMecFertCTR().getMatricFunc().getMatricFunc()));
        textViewNomeMotorista.setText(pomContext.getMotoMecFertCTR().getMatricFunc().getNomeFunc());

        buttonManterMotorista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonManterMotorista.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                Intent it = new Intent(VerifOperadorActivity.this, MsgSaidaCampoActivity.class);", getLocalClassName());
                Intent it = new Intent(VerifOperadorActivity.this, MsgSaidaCampoActivity.class);
                startActivity(it);
                finish();

            }
        });

        buttonAlterarMotorista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonAlterarMotorista.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                AlertDialog.Builder alerta = new AlertDialog.Builder(VerifOperadorActivity.this);\n" +
                        "                alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                alerta.setMessage(\"AO REALIZAR A ALTERAÇÃO O PROCESSO ENCERRARÁ O BOLETIM ATUAL E UM NOVO SERÁ CRIADO. DESEJA REALMENTE ALTERAR O MOTORISTA?\");", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder(VerifOperadorActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("AO REALIZAR A ALTERAÇÃO O PROCESSO ENCERRARÁ O BOLETIM ATUAL E UM NOVO SERÁ CRIADO. DESEJA REALMENTE ALTERAR O MOTORISTA?");
                alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                                "                    @Override\n" +
                                "                    public void onClick(DialogInterface dialog, int which) {\n" +
                                "                        pomContext.getConfigCTR().setPosicaoTela(17L);\n" +
                                "                        pomContext.getMotoMecFertCTR().inserirParadaTrocaMotorista(getLocalClassName());\n" +
                                "                        Intent it = new Intent(VerifOperadorActivity.this, HorimetroActivity.class);", getLocalClassName());
                        pomContext.getConfigCTR().setPosicaoTela(17L);
                        pomContext.getMotoMecFertCTR().inserirParadaTrocaMotorista(getLocalClassName());
                        Intent it = new Intent(VerifOperadorActivity.this, HorimetroActivity.class);
                        startActivity(it);
                        finish();
                    }
                });

                alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"NÃO\", new DialogInterface.OnClickListener() {\n" +
                                "                    @Override\n" +
                                "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                    }
                });

                alerta.show();

            }
        });

    }

    public void onBackPressed()  {
    }

}