package br.com.usinasantafe.pom.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pom.model.pst.Entidade;

@DatabaseTable(tableName="tbbocalest")
public class BocalBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idBocal;
    @DatabaseField
    private Long codBocal;
    @DatabaseField
    private String descrBocal;

    public BocalBean() {
    }

    public Long getIdBocal() {
        return idBocal;
    }

    public void setIdBocal(Long idBocal) {
        this.idBocal = idBocal;
    }

    public Long getCodBocal() {
        return codBocal;
    }

    public void setCodBocal(Long codBocal) {
        this.codBocal = codBocal;
    }

    public String getDescrBocal() {
        return descrBocal;
    }

    public void setDescrBocal(String descrBocal) {
        this.descrBocal = descrBocal;
    }

}
