package gcom.gui.faturamento.autoinfracao;

import org.apache.struts.validator.ValidatorActionForm;

public class FiltrarAutoInfracaoActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	private String idImovel;

	private String descricaoImovel;

	private String idFuncionario;

	private String descricaoFuncionario;

	private String idFiscalizacaoSituacao;

	private String descricaoFiscalizacaoSituacao;

	private String idAutoInfracaoSituacao;

	private String descricaoAutoInfracaoSituacao;

	private String dataEmissaoInicial;

	private String dataEmissaoFinal;

	private String dataInicioRecursoInicial;

	private String dataInicioRecursoFinal;

	private String dataTerminoRecursoInicial;

	private String dataTerminoRecursoFinal;
	
	private String atualizar;
	
	

	public String getDataEmissaoFinal() {
		return dataEmissaoFinal;
	}

	public void setDataEmissaoFinal(String dataEmissaoFinal) {
		this.dataEmissaoFinal = dataEmissaoFinal;
	}

	public String getDataEmissaoInicial() {
		return dataEmissaoInicial;
	}

	public void setDataEmissaoInicial(String dataEmissaoInicial) {
		this.dataEmissaoInicial = dataEmissaoInicial;
	}

	public String getDataInicioRecursoFinal() {
		return dataInicioRecursoFinal;
	}

	public void setDataInicioRecursoFinal(String dataInicioRecursoFinal) {
		this.dataInicioRecursoFinal = dataInicioRecursoFinal;
	}

	public String getDataInicioRecursoInicial() {
		return dataInicioRecursoInicial;
	}

	public void setDataInicioRecursoInicial(String dataInicioRecursoInicial) {
		this.dataInicioRecursoInicial = dataInicioRecursoInicial;
	}

	public String getDataTerminoRecursoFinal() {
		return dataTerminoRecursoFinal;
	}

	public void setDataTerminoRecursoFinal(String dataTerminoRecursoFinal) {
		this.dataTerminoRecursoFinal = dataTerminoRecursoFinal;
	}

	public String getDataTerminoRecursoInicial() {
		return dataTerminoRecursoInicial;
	}

	public void setDataTerminoRecursoInicial(String dataTerminoRecursoInicial) {
		this.dataTerminoRecursoInicial = dataTerminoRecursoInicial;
	}

	public String getDescricaoAutoInfracaoSituacao() {
		return descricaoAutoInfracaoSituacao;
	}

	public void setDescricaoAutoInfracaoSituacao(
			String descricaoAutoInfracaoSituacao) {
		this.descricaoAutoInfracaoSituacao = descricaoAutoInfracaoSituacao;
	}

	public String getDescricaoFiscalizacaoSituacao() {
		return descricaoFiscalizacaoSituacao;
	}

	public void setDescricaoFiscalizacaoSituacao(
			String descricaoFiscalizacaoSituacao) {
		this.descricaoFiscalizacaoSituacao = descricaoFiscalizacaoSituacao;
	}

	public String getDescricaoFuncionario() {
		return descricaoFuncionario;
	}

	public void setDescricaoFuncionario(String descricaoFuncionario) {
		this.descricaoFuncionario = descricaoFuncionario;
	}

	public String getDescricaoImovel() {
		return descricaoImovel;
	}

	public void setDescricaoImovel(String descricaoImovel) {
		this.descricaoImovel = descricaoImovel;
	}

	public String getIdAutoInfracaoSituacao() {
		return idAutoInfracaoSituacao;
	}

	public void setIdAutoInfracaoSituacao(String idAutoInfracaoSituacao) {
		this.idAutoInfracaoSituacao = idAutoInfracaoSituacao;
	}

	public String getIdFiscalizacaoSituacao() {
		return idFiscalizacaoSituacao;
	}

	public void setIdFiscalizacaoSituacao(String idFiscalizacaoSituacao) {
		this.idFiscalizacaoSituacao = idFiscalizacaoSituacao;
	}

	public String getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getAtualizar() {
		return atualizar;
	}

	public void setAtualizar(String atualizar) {
		this.atualizar = atualizar;
	}

}
