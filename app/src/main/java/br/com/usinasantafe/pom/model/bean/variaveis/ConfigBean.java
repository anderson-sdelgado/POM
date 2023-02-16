package br.com.usinasantafe.pom.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pom.model.pst.Entidade;

@DatabaseTable(tableName="tbconfigvar")
public class ConfigBean extends Entidade {
	
	private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idConfig;
	@DatabaseField
	private Long equipConfig;
    @DatabaseField
	private String senhaConfig;
	@DatabaseField
	private Long ultTurnoCLConfig;
    @DatabaseField
    private String dtUltCLConfig;
	@DatabaseField
	private String dtServConfig;
	@DatabaseField
	private Long difDthrConfig;
	@DatabaseField
	private Long matricFuncConfig;
	@DatabaseField
	private Long idTurnoConfig;
	@DatabaseField
	private Double hodometroInicialConfig;
	@DatabaseField
	private Double hodometroFinalConfig;
	@DatabaseField
	private Double longitudeConfig;
	@DatabaseField
	private Double latitudeConfig;
	@DatabaseField
	private Long nroOSConfig;
	@DatabaseField
	private Long idAtivConfig;
	@DatabaseField
	private Long ultParadaBolConfig;
	@DatabaseField
	private Double horimetroConfig;
	@DatabaseField
	private Long verRecInformativo; // 0 - Verificar Dados; 1- Dados Recebidos; 2 - Dados Visualizados
	@DatabaseField
	private Long statusConConfig;  // 0 - Offline; 1 - Online
	@DatabaseField
	private Long atualCheckList;
	@DatabaseField
	private Long statusRetVerif; // 0 - Não Verificando; 1 - Verificando
	@DatabaseField
	private Long posicaoTela;
	// 1 - Inicio do Boletim; PMM - ECM - PCOMP
	// 2 - Trabalhando Moto Mec;
	// 3 - Parada Moto Mec;
	// 4 - Finalizar Boletim Moto Mec;
	// 5 - Digitar Data e Hora
	// 6 - Trocar Transbordo;
	// 7 - Editar Rendimento;
	// 8 - Iniciar Apontamento(TRALHANDO/PARADO) Moto Mec
	// 9 - Recolhimento de Mangueira;
	// 10 - Trocar de Implemento
	// 11 - Senha de Configuração
	// 12 - Senha de Log
	// 13 - Buscar Ordem Carreg Insumo
	// 14 - Buscar Ordem Carreg Composto
	// 16 - Menu Certificado ECM
	// 17 - Trocar Motorista Fechamento de Boletim ECM
	// 18 - Abertura de Boletim ECM
	// 19 - Desengate no Menu MotoMec ECM
	// 20 - Engate no Menu MotoMec ECM
	// 21 - Desengate no Parada
	// 22 - Engate no Parada
	// 23 - Senha de Menu PMM
	// 24 - Senha de Menu ECM
	// 25 - Senha de Menu PCOMP
	// 26 - Finalizar
	// 27 - Inicia Apontamento Manutenção
	// 28 - Interroper/Finalizar Apontamento Manutenção
	// 29 - Troca Funcao PCOMP

	public ConfigBean() {
	}

	public Long getIdConfig() {
		return idConfig;
	}

	public void setIdConfig(Long idConfig) {
		this.idConfig = idConfig;
	}

	public Long getEquipConfig() {
		return equipConfig;
	}

	public void setEquipConfig(Long equipConfig) {
		this.equipConfig = equipConfig;
	}

	public String getSenhaConfig() {
		return senhaConfig;
	}

	public void setSenhaConfig(String senhaConfig) {
		this.senhaConfig = senhaConfig;
	}

	public Long getUltTurnoCLConfig() {
		return ultTurnoCLConfig;
	}

	public void setUltTurnoCLConfig(Long ultTurnoCLConfig) {
		this.ultTurnoCLConfig = ultTurnoCLConfig;
	}

	public String getDtUltCLConfig() {
		return dtUltCLConfig;
	}

	public void setDtUltCLConfig(String dtUltCLConfig) {
		this.dtUltCLConfig = dtUltCLConfig;
	}

	public String getDtServConfig() {
		return dtServConfig;
	}

	public void setDtServConfig(String dtServConfig) {
		this.dtServConfig = dtServConfig;
	}

	public Long getDifDthrConfig() {
		return difDthrConfig;
	}

	public void setDifDthrConfig(Long difDthrConfig) {
		this.difDthrConfig = difDthrConfig;
	}

	public Long getNroOSConfig() {
		return nroOSConfig;
	}

	public void setNroOSConfig(Long nroOSConfig) {
		this.nroOSConfig = nroOSConfig;
	}

	public Long getIdAtivConfig() {
		return idAtivConfig;
	}

	public void setIdAtivConfig(Long idAtivConfig) {
		this.idAtivConfig = idAtivConfig;
	}

	public Long getUltParadaBolConfig() {
		return ultParadaBolConfig;
	}

	public void setUltParadaBolConfig(Long ultParadaBolConfig) {
		this.ultParadaBolConfig = ultParadaBolConfig;
	}

	public Double getHorimetroConfig() {
		return horimetroConfig;
	}

	public void setHorimetroConfig(Double horimetroConfig) {
		this.horimetroConfig = horimetroConfig;
	}

	public Long getVerRecInformativo() {
		return verRecInformativo;
	}

	public void setVerRecInformativo(Long verRecInformativo) {
		this.verRecInformativo = verRecInformativo;
	}

	public Long getStatusConConfig() {
		return statusConConfig;
	}

	public void setStatusConConfig(Long statusConConfig) {
		this.statusConConfig = statusConConfig;
	}

	public Long getAtualCheckList() {
		return atualCheckList;
	}

	public void setAtualCheckList(Long atualCheckList) {
		this.atualCheckList = atualCheckList;
	}

	public Long getPosicaoTela() {
		return posicaoTela;
	}

	public void setPosicaoTela(Long posicaoTela) {
		this.posicaoTela = posicaoTela;
	}

	public Long getStatusRetVerif() {
		return statusRetVerif;
	}

	public void setStatusRetVerif(Long statusRetVerif) {
		this.statusRetVerif = statusRetVerif;
	}

	public Long getMatricFuncConfig() {
		return matricFuncConfig;
	}

	public void setMatricFuncConfig(Long matricFuncConfig) {
		this.matricFuncConfig = matricFuncConfig;
	}

	public Long getIdTurnoConfig() {
		return idTurnoConfig;
	}

	public void setIdTurnoConfig(Long idTurnoConfig) {
		this.idTurnoConfig = idTurnoConfig;
	}

	public Double getHodometroInicialConfig() {
		return hodometroInicialConfig;
	}

	public void setHodometroInicialConfig(Double hodometroInicialBolMMFert, Double longitudeBolMMFert, Double latitudeBolMMFert) {
		this.latitudeConfig = latitudeBolMMFert;
		this.longitudeConfig = longitudeBolMMFert;
		this.hodometroInicialConfig = hodometroInicialBolMMFert;
	}

	public Double getHodometroFinalConfig() {
		return hodometroFinalConfig;
	}

	public void setHodometroFinalConfig(Double hodometroFinalConfig) {
		this.hodometroFinalConfig = hodometroFinalConfig;
	}

	public Double getLongitudeConfig() {
		return longitudeConfig;
	}

	public Double getLatitudeConfig() {
		return latitudeConfig;
	}

}