package br.com.usinasantafe.pom;

import android.app.Application;

import br.com.usinasantafe.pom.control.CheckListCTR;
import br.com.usinasantafe.pom.control.ConfigCTR;
import br.com.usinasantafe.pom.control.MecanicoCTR;
import br.com.usinasantafe.pom.control.MotoMecFertCTR;
import br.com.usinasantafe.pom.model.dao.LogErroDAO;

public class POMContext extends Application {

    private Thread.UncaughtExceptionHandler mDefaultExceptionHandler;

    private MotoMecFertCTR motoMecFertCTR;
    private CheckListCTR checkListCTR;
    private ConfigCTR configCTR;
    private MecanicoCTR mecanicoCTR;

    public static String versaoWS = "1.02";
    public static int aplic = 1; // 1 - PMM; 2 - ECM; 3 - PCOMP

    @Override
    public void onCreate() {
        super.onCreate();
        mDefaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(handler);
    }

    public MotoMecFertCTR getMotoMecFertCTR() {
        if (motoMecFertCTR == null)
            motoMecFertCTR = new MotoMecFertCTR();
        return motoMecFertCTR;
    }

    public CheckListCTR getCheckListCTR(){
        if (checkListCTR == null)
            checkListCTR = new CheckListCTR();
        return checkListCTR;
    }

    public ConfigCTR getConfigCTR(){
        if (configCTR == null)
            configCTR = new ConfigCTR();
        return configCTR;
    }

    public MecanicoCTR getMecanicoCTR(){
        if (mecanicoCTR == null)
            mecanicoCTR = new MecanicoCTR();
        return mecanicoCTR;
    }

    private Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
        public void uncaughtException(Thread thread, Throwable ex) {
            LogErroDAO.getInstance().insertLogErro(ex);
            mDefaultExceptionHandler.uncaughtException(thread, ex);
        }
    };

}
