package br.com.usinasantafe.pom.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pom.model.pst.Entidade;

@DatabaseTable(tableName="tblogerrovar")
public class LogErroBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idLogErro;
    @DatabaseField
    private String exception;
    @DatabaseField
    private String dthr;
    @DatabaseField
    private Long dthrLong;
    @DatabaseField
    private Long status;

    public LogErroBean() {
    }

    public Long getIdLogErro() {
        return idLogErro;
    }

    public void setIdLogErro(Long idLogErro) {
        this.idLogErro = idLogErro;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getDthr() {
        return dthr;
    }

    public void setDthr(String dthr) {
        this.dthr = dthr;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getDthrLong() {
        return dthrLong;
    }

    public void setDthrLong(Long dthrLong) {
        this.dthrLong = dthrLong;
    }
}
