package br.com.usinasantafe.pom.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.usinasantafe.pom.POMContext;
import br.com.usinasantafe.pom.R;
import br.com.usinasantafe.pom.model.bean.estaticas.AtividadeBean;
import br.com.usinasantafe.pom.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pom.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.pom.model.bean.estaticas.ItemCheckListBean;
import br.com.usinasantafe.pom.model.bean.estaticas.MotoMecBean;
import br.com.usinasantafe.pom.model.bean.estaticas.OSBean;
import br.com.usinasantafe.pom.model.bean.estaticas.ParadaBean;
import br.com.usinasantafe.pom.model.bean.estaticas.REquipAtivBean;
import br.com.usinasantafe.pom.model.bean.estaticas.ROSAtivBean;
import br.com.usinasantafe.pom.model.bean.estaticas.TurnoBean;
import br.com.usinasantafe.pom.model.bean.variaveis.ApontMMFertBean;
import br.com.usinasantafe.pom.model.bean.variaveis.BoletimMMFertBean;
import br.com.usinasantafe.pom.model.bean.variaveis.CabecCheckListBean;
import br.com.usinasantafe.pom.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pom.model.bean.variaveis.LogErroBean;
import br.com.usinasantafe.pom.model.bean.variaveis.RespItemCheckListBean;
import br.com.usinasantafe.pom.model.dao.LogProcessoDAO;

public class ConfigActivity extends ActivityGeneric {

    private ProgressDialog progressBar;
    private EditText editTextEquipConfig;
    private EditText editTextSenhaConfig;
    private POMContext pomContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        Button buttonSalvarConfig =  findViewById(R.id.buttonSalvarConfig);
        Button buttonCancConfig = findViewById(R.id.buttonCancConfig);
        Button buttonAtualizarBD = findViewById(R.id.buttonAtualizarBD);
        Button buttonLimparBD = findViewById(R.id.buttonLimparBD);
        editTextEquipConfig = findViewById(R.id.editTextEquipConfig);
        editTextSenhaConfig = findViewById(R.id.editTextSenhaConfig);

        pomContext = (POMContext) getApplication();

        if (pomContext.getConfigCTR().hasElemConfig()) {
            LogProcessoDAO.getInstance().insertLogProcesso("if (pomContext.getConfigCTR().hasElemConfig()) {", getLocalClassName());
            LogProcessoDAO.getInstance().insertLogProcesso("editTextEquipConfig.setText(String.valueOf(pomContext.getConfigCTR().getEquip().getNroEquip()));\n" +
                    "            editTextSenhaConfig.setText(pomContext.getConfigCTR().getConfig().getSenhaConfig());", getLocalClassName());
            editTextEquipConfig.setText(String.valueOf(pomContext.getConfigCTR().getEquip().getNroEquip()));
            editTextSenhaConfig.setText(pomContext.getConfigCTR().getConfig().getSenhaConfig());
        }

        buttonSalvarConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("        buttonSalvarConfig.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if(!editTextEquipConfig.getText().toString().equals("") &&
                        !editTextSenhaConfig.getText().toString().equals("")){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(!editTextEquipConfig.getText().toString().equals(\"\") &&\n" +
                            "                        !editTextSenhaConfig.getText().toString().equals(\"\")){", getLocalClassName());
                    LogProcessoDAO.getInstance().insertLogProcesso("progressBar = new ProgressDialog(v.getContext());\n" +
                            "                    progressBar.setCancelable(true);\n" +
                            "                    progressBar.setMessage(\"Pequisando o Equipamento...\");\n" +
                            "                    progressBar.show();", getLocalClassName());
                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("Pequisando o Equipamento...");
                    progressBar.show();

                    LogProcessoDAO.getInstance().insertLogProcesso("pomContext.getConfigCTR().salvarConfig(" + editTextSenhaConfig.getText().toString() + ");\n" +
                            "                    pomContext.getConfigCTR().verEquipConfig(" + editTextEquipConfig.getText().toString() + ", ConfigActivity.this ,TelaInicialActivity.class, progressBar);", getLocalClassName());
                    pomContext.getConfigCTR().salvarConfig(editTextSenhaConfig.getText().toString());
                    pomContext.getConfigCTR().verEquipConfig(editTextEquipConfig.getText().toString(), ConfigActivity.this , TelaInicialActivity.class, progressBar, getLocalClassName(), 1);

                }

            }
        });

        buttonCancConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("        buttonCancConfig.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                LogProcessoDAO.getInstance().insertLogProcesso("Intent it = new Intent(ConfigActivity.this, TelaInicialActivity.class);", getLocalClassName());
                Intent it = new Intent(ConfigActivity.this, TelaInicialActivity.class);
                startActivity(it);
                finish();

            }
        });

        buttonAtualizarBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("        buttonAtualizarBD.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n", getLocalClassName());

                if(connectNetwork){

                    LogProcessoDAO.getInstance().insertLogProcesso("if(connectNetwork){\n" +
                            "                    progressBar = new ProgressDialog(v.getContext());\n" +
                            "                    progressBar.setCancelable(true);\n" +
                            "                    progressBar.setMessage(\"ATUALIZANDO ...\");\n" +
                            "                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);\n" +
                            "                    progressBar.setProgress(0);\n" +
                            "                    progressBar.setMax(100);\n" +
                            "                    progressBar.show();", getLocalClassName());
                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    LogProcessoDAO.getInstance().insertLogProcesso("pomContext.getConfigCTR().atualTodasTabelas(ConfigActivity.this, progressBar);", getLocalClassName());
                    pomContext.getConfigCTR().atualTodasTabelas(ConfigActivity.this, progressBar, getLocalClassName());

                }
                else{

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);\n" +
                            "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                    alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                            "                    alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                            "                        @Override\n" +
                            "                        public void onClick(DialogInterface dialog, int which) {\n" +
                            "                    });\n" +
                            "                    alerta.show();", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alerta.show();
                }
            }
        });

        buttonLimparBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AtividadeBean atividadeBean = new AtividadeBean();
                atividadeBean.deleteAll();

                EquipBean equipBean = new EquipBean();
                equipBean.deleteAll();

                FuncBean funcBean = new FuncBean();
                funcBean.deleteAll();

                ItemCheckListBean itemCheckListBean = new ItemCheckListBean();
                itemCheckListBean.deleteAll();

                MotoMecBean motoMecBean = new MotoMecBean();
                motoMecBean.deleteAll();

                OSBean osBean = new OSBean();
                osBean.deleteAll();

                ParadaBean paradaBean = new ParadaBean();
                paradaBean.deleteAll();

                REquipAtivBean rEquipAtivBean = new REquipAtivBean();
                rEquipAtivBean.deleteAll();

                ROSAtivBean rosAtivBean = new ROSAtivBean();
                rosAtivBean.deleteAll();

                TurnoBean turnoBean = new TurnoBean();
                turnoBean.deleteAll();

                ApontMMFertBean apontMMFertBean = new ApontMMFertBean();
                apontMMFertBean.deleteAll();

                BoletimMMFertBean boletimMMFertBean = new BoletimMMFertBean();
                boletimMMFertBean.deleteAll();

                CabecCheckListBean cabecCLBean = new CabecCheckListBean();
                cabecCLBean.deleteAll();

                ConfigBean configBean = new ConfigBean();
                configBean.deleteAll();

                LogErroBean logErroBean = new LogErroBean();
                logErroBean.deleteAll();

                RespItemCheckListBean respItemCLBean = new RespItemCheckListBean();
                respItemCLBean.deleteAll();

                AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("TODOS OS DADOS FORAM APAGADOS!");
                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alerta.show();

            }
        });

    }

}
