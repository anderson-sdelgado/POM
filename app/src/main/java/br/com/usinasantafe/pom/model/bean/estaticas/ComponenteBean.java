package br.com.usinasantafe.pom.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pom.model.pst.Entidade;

@DatabaseTable(tableName="tbcomponentemecanest")
public class ComponenteBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idComponente;
    @DatabaseField
    private String codComponente;
    @DatabaseField
    private String descrComponente;

    public ComponenteBean() {
    }

    public Long getIdComponente() {
        return idComponente;
    }

    public void setIdComponente(Long idComponente) {
        this.idComponente = idComponente;
    }

    public String getCodComponente() {
        return codComponente;
    }

    public void setCodComponente(String codComponente) {
        this.codComponente = codComponente;
    }

    public String getDescrComponente() {
        return descrComponente;
    }

    public void setDescrComponente(String descrComponente) {
        this.descrComponente = descrComponente;
    }
}
