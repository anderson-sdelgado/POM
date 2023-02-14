package br.com.usinasantafe.pom.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pom.model.pst.Entidade;

@DatabaseTable(tableName="tbpropriedadeest")
public class PropriedadeBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idPropriedade;
    @DatabaseField
    private Long codPropriedade;
    @DatabaseField
    private String descrPropriedade;

    public PropriedadeBean() {
    }

    public Long getIdPropriedade() {
        return idPropriedade;
    }

    public void setIdPropriedade(Long idPropriedade) {
        this.idPropriedade = idPropriedade;
    }

    public String getDescrPropriedade() {
        return descrPropriedade;
    }

    public void setDescrPropriedade(String descrPropriedade) {
        this.descrPropriedade = descrPropriedade;
    }

    public Long getCodPropriedade() {
        return codPropriedade;
    }

    public void setCodPropriedade(Long codPropriedade) {
        this.codPropriedade = codPropriedade;
    }

}
