package br.com.usinasantafe.pom.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pom.model.pst.Entidade;

/**
 * Created by anderson on 31/03/2017.
 */
@DatabaseTable(tableName="tbcabclvar")
public class CabecCheckListBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idCabCL;
    @DatabaseField
    private Long equipCabCL;
    @DatabaseField
    private String dtCabCL;
    @DatabaseField
    private Long funcCabCL;
    @DatabaseField
    private Long turnoCabCL;
    @DatabaseField
    private Long statusCabCL;  //1 - Aberto; 2 - Encerrado; 3 - Enviado
    @DatabaseField
    private Long dthrCabCLLong;

    public CabecCheckListBean() {
    }

    public Long getIdCabCL() {
        return idCabCL;
    }

    public Long getEquipCabCL() {
        return equipCabCL;
    }

    public void setEquipCabCL(Long equipCabCL) {
        this.equipCabCL = equipCabCL;
    }

    public String getDtCabCL() {
        return dtCabCL;
    }

    public void setDtCabCL(String dtCabCL) {
        this.dtCabCL = dtCabCL;
    }

    public Long getFuncCabCL() {
        return funcCabCL;
    }

    public void setFuncCabCL(Long funcCabCL) {
        this.funcCabCL = funcCabCL;
    }

    public Long getTurnoCabCL() {
        return turnoCabCL;
    }

    public void setTurnoCabCL(Long turnoCabCL) {
        this.turnoCabCL = turnoCabCL;
    }

    public Long getStatusCabCL() {
        return statusCabCL;
    }

    public void setStatusCabCL(Long statusCabCL) {
        this.statusCabCL = statusCabCL;
    }

    public Long getDthrCabCLLong() {
        return dthrCabCLLong;
    }

    public void setDthrCabCLLong(Long dthrCabCLLong) {
        this.dthrCabCLLong = dthrCabCLLong;
    }
}
