package br.com.usinasantafe.pom.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pom.POMContext;
import br.com.usinasantafe.pom.R;
import br.com.usinasantafe.pom.model.bean.estaticas.ProdutoBean;
import br.com.usinasantafe.pom.model.dao.LogProcessoDAO;

public class ProdutoActivity extends ActivityGeneric {

    public static final int REQUEST_CODE = 0;
    private TextView txtResult;
    private ProdutoBean produtoBean;
    private POMContext pomContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        pomContext = (POMContext) getApplication();
        txtResult = findViewById(R.id.txResult);

        Button buttonOkOS = findViewById(R.id.buttonOkProd);
        Button buttonCancOS = findViewById(R.id.buttonCancProd);
        Button btnCapturaBarra = findViewById(R.id.btnCapturaBarra);

        buttonOkOS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonOkOS.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());

                if(!txtResult.getText().equals("PRODUTO:")) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if(!txtResult.getText().equals(\"PRODUTO:\")) {\n" +
                            "                    if (connectNetwork) {\n" +
                            "                        pomContext.getConfigCTR().setStatusConConfig(1L);\n" +
                            "                    }\n" +
                            "                    else{\n" +
                            "                        pomContext.getConfigCTR().setStatusConConfig(0L);\n" +
                            "                    }", getLocalClassName());

                    if (connectNetwork) {
                        pomContext.getConfigCTR().setStatusConConfig(1L);
                    }
                    else{
                        pomContext.getConfigCTR().setStatusConConfig(0L);
                    }

                    LogProcessoDAO.getInstance().insertLogProcesso("pomContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude(), getLocalClassName());\n" +
                            "                    pomContext.getConfigCTR().setPosFluxoCarregComposto(2L);\n" +
                            "                    pomContext.getCompostoCTR().abrirCarregInsumo(produtoBean);\n" +
                            "                    Intent it = new Intent(ProdutoActivity.this, MenuPrincPCOMPActivity.class);", getLocalClassName());
                    pomContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude(), getLocalClassName());
                    pomContext.getCompostoCTR().abrirCarregInsumo(produtoBean);

                    Intent it = new Intent(ProdutoActivity.this, MenuPrincPCOMPActivity.class);
                    startActivity(it);
                    finish();

                }

            }
        });

        buttonCancOS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonCancOS.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                Intent it = new Intent(ProdutoActivity.this, MenuPrincPCOMPActivity.class);", getLocalClassName());
                Intent it = new Intent(ProdutoActivity.this, MenuPrincPCOMPActivity.class);
                startActivity(it);
                finish();

            }
        });

        btnCapturaBarra.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

//                LogProcessoDAO.getInstance().insertLogProcesso("btnCapturaBarra.setOnClickListener(new View.OnClickListener() {\n" +
//                        "            @Override\n" +
//                        "            public void onClick(View v) {\n" +
//                        "                Intent it = new Intent(ProdutoActivity.this, br.com.usinasantafe.pom.zxing.CaptureActivity.class);", getLocalClassName());
//                Intent it = new Intent(ProdutoActivity.this, br.com.usinasantafe.pom.zxing.CaptureActivity.class);
//                startActivityForResult(it, REQUEST_CODE);

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if(REQUEST_CODE == requestCode && RESULT_OK == resultCode){
            LogProcessoDAO.getInstance().insertLogProcesso("if(REQUEST_CODE == requestCode && RESULT_OK == resultCode){\n" +
                    "            String cod = data.getStringExtra(\"SCAN_RESULT\");", getLocalClassName());
            String cod = data.getStringExtra("SCAN_RESULT");
            if (pomContext.getCompostoCTR().verProduto(cod)) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (pomContext.getCompostoCTR().verProduto(cod)) {\n" +
                        "                produtoBean = pomContext.getCompostoCTR().getProduto(cod);\n" +
                        "                txtResult.setText(\"PRODUTO: \" + produtoBean.getCodProduto() + \"\\n\" + produtoBean.getDescProduto());", getLocalClassName());
                produtoBean = pomContext.getCompostoCTR().getProduto(cod);
                txtResult.setText("PRODUTO: " + produtoBean.getCodProduto() + "\n" + produtoBean.getDescProduto());
            }

        }

    }

    public void onBackPressed()  {
    }

}