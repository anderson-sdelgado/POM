package br.com.usinasantafe.pom.model.pst;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import br.com.usinasantafe.pom.model.bean.estaticas.AtividadeBean;
import br.com.usinasantafe.pom.model.bean.estaticas.ComponenteBean;
import br.com.usinasantafe.pom.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pom.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.pom.model.bean.estaticas.ItemCheckListBean;
import br.com.usinasantafe.pom.model.bean.estaticas.ItemOSMecanBean;
import br.com.usinasantafe.pom.model.bean.estaticas.OSBean;
import br.com.usinasantafe.pom.model.bean.estaticas.ParadaBean;
import br.com.usinasantafe.pom.model.bean.estaticas.REquipAtivBean;
import br.com.usinasantafe.pom.model.bean.estaticas.ROSAtivBean;
import br.com.usinasantafe.pom.model.bean.estaticas.ServicoBean;
import br.com.usinasantafe.pom.model.bean.estaticas.TurnoBean;
import br.com.usinasantafe.pom.model.bean.variaveis.ApontMecanBean;
import br.com.usinasantafe.pom.model.bean.variaveis.BoletimMMFertBean;
import br.com.usinasantafe.pom.model.bean.variaveis.CabecCheckListBean;
import br.com.usinasantafe.pom.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pom.model.bean.variaveis.LogErroBean;
import br.com.usinasantafe.pom.model.bean.variaveis.LogProcessoBean;
import br.com.usinasantafe.pom.model.bean.variaveis.RespItemCheckListBean;
import br.com.usinasantafe.pom.model.dao.LogErroDAO;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	public static final String FORCA_DB_NAME = "pom_db";
	public static final int FORCA_BD_VERSION = 1;

	private static DatabaseHelper instance;
	
	public static DatabaseHelper getInstance(){
		return instance;
	}
	
	public DatabaseHelper(Context context) {
		super(context, FORCA_DB_NAME, null, FORCA_BD_VERSION);
		instance = this;
	}

	@Override
	public void close() {
		super.close();
		instance = null;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource cs) {
		
		try{
			createAllInitial(cs);
		}
		catch(Exception e){
			Log.e(DatabaseHelper.class.getName(),
					"Erro criando banco de dados...",
					e);
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db,
			ConnectionSource cs,
			int oldVersion,
			int newVersion) {

		if((oldVersion == 1) && (newVersion == 2)){
			//Versão 5.05
			dropAllInitial(cs);
			createAllInitial(cs);
		}

	}

	public void dropAllInitial(ConnectionSource cs){

		try {

			TableUtils.dropTable(cs, AtividadeBean.class, true);
			TableUtils.dropTable(cs, ComponenteBean.class, true);
			TableUtils.dropTable(cs, EquipBean.class, true);
			TableUtils.dropTable(cs, FuncBean.class, true);
			TableUtils.dropTable(cs, ItemCheckListBean.class, true);
			TableUtils.dropTable(cs, ItemOSMecanBean.class, true);
			TableUtils.dropTable(cs, OSBean.class, true);
			TableUtils.dropTable(cs, ParadaBean.class, true);
			TableUtils.dropTable(cs, REquipAtivBean.class, true);
			TableUtils.dropTable(cs, ROSAtivBean.class, true);
			TableUtils.dropTable(cs, ServicoBean.class, true);
			TableUtils.dropTable(cs, TurnoBean.class, true);

			TableUtils.dropTable(cs, ApontMecanBean.class, true);
			TableUtils.dropTable(cs, BoletimMMFertBean.class, true);
			TableUtils.dropTable(cs, CabecCheckListBean.class, true);
			TableUtils.dropTable(cs, ConfigBean.class, true);
			TableUtils.dropTable(cs, LogErroBean.class, true);
			TableUtils.dropTable(cs, LogProcessoBean.class, true);
			TableUtils.dropTable(cs, RespItemCheckListBean.class, true);

		} catch (Exception e) {
			LogErroDAO.getInstance().insertLogErro(e);
			Log.e(DatabaseHelper.class.getName(), "Erro atualizando banco de dados...", e);
		}

	}

	public void createAllInitial(ConnectionSource cs){

		try {

			TableUtils.createTable(cs, AtividadeBean.class);
			TableUtils.createTable(cs, ComponenteBean.class);
			TableUtils.createTable(cs, EquipBean.class);
			TableUtils.createTable(cs, FuncBean.class);
			TableUtils.createTable(cs, ItemCheckListBean.class);
			TableUtils.createTable(cs, ItemOSMecanBean.class);
			TableUtils.createTable(cs, OSBean.class);
			TableUtils.createTable(cs, ParadaBean.class);
			TableUtils.createTable(cs, REquipAtivBean.class);
			TableUtils.createTable(cs, ROSAtivBean.class);
			TableUtils.createTable(cs, ServicoBean.class);
			TableUtils.createTable(cs, TurnoBean.class);

			TableUtils.createTable(cs, ApontMecanBean.class);
			TableUtils.createTable(cs, BoletimMMFertBean.class);
			TableUtils.createTable(cs, CabecCheckListBean.class);
			TableUtils.createTable(cs, ConfigBean.class);
			TableUtils.createTable(cs, LogErroBean.class);
			TableUtils.createTable(cs, LogProcessoBean.class);
			TableUtils.createTable(cs, RespItemCheckListBean.class);

		} catch (Exception e) {
			LogErroDAO.getInstance().insertLogErro(e);
			Log.e(DatabaseHelper.class.getName(), "Erro atualizando banco de dados...", e);
		}

	}

}
