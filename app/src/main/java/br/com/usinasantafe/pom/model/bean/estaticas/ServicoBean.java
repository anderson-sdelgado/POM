package br.com.usinasantafe.pom.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pom.model.pst.Entidade;

@DatabaseTable(tableName="tbservicomecanest")
public class ServicoBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idServico;
    @DatabaseField
    private Long codServico;
    @DatabaseField
    private String descrServico;

    public ServicoBean() {
    }

    public Long getIdServico() {
        return idServico;
    }

    public void setIdServico(Long idServico) {
        this.idServico = idServico;
    }

    public Long getCodServico() {
        return codServico;
    }

    public void setCodServico(Long codServico) {
        this.codServico = codServico;
    }

    public String getDescrServico() {
        return descrServico;
    }

    public void setDescrServico(String descrServico) {
        this.descrServico = descrServico;
    }
}
