
package br.com.usinasantafe.pom.model.bean.estaticas;

/**
 *
 * @author anderson
 */

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pom.model.pst.Entidade;

@DatabaseTable(tableName="tbfrenteest")
public class FrenteBean extends Entidade {
	
	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
	private Long idFrente;
	@DatabaseField
    private Long codFrente;
	@DatabaseField
    private String descrFrente;

    public FrenteBean() {
    }

	public Long getIdFrente() {
		return idFrente;
	}

	public void setIdFrente(Long idFrente) {
		this.idFrente = idFrente;
	}

	public Long getCodFrente() {
		return codFrente;
	}

	public void setCodFrente(Long codFrente) {
		this.codFrente = codFrente;
	}

	public String getDescrFrente() {
		return descrFrente;
	}

	public void setDescrFrente(String descrFrente) {
		this.descrFrente = descrFrente;
	}
}
