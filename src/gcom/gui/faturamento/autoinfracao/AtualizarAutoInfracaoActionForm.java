package gcom.gui.faturamento.autoinfracao;

import org.apache.struts.validator.ValidatorActionForm;

public class AtualizarAutoInfracaoActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	private String idAutoInfracao;
	
	private String idImovel;
	
	private String descricaoImovel;

	private String idFuncionario;

	private String descricaoFuncionario;

	private String idFiscalizacaoSituacao;

	private String idAutoInfracaoSituacao;

	private String dataEmissao;

	private String dataInicioRecurso;

	private String dataTerminoRecurso;
	
	private String observacao;
	
	private String idCliente;
	
	private String nomeCliente;
	
	private String quantidadeParcelas;

	/**
	 * @return Retorna o campo quantidadeParcelas.
	 */
	public String getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	/**
	 * @param quantidadeParcelas O quantidadeParcelas a ser setado.
	 */
	public void setQuantidadeParcelas(String quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}

	public String getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getDataInicioRecurso() {
		return dataInicioRecurso;
	}

	public void setDataInicioRecurso(String dataInicioRecurso) {
		this.dataInicioRecurso = dataInicioRecurso;
	}

	public String getDataTerminoRecurso() {
		return dataTerminoRecurso;
	}

	public void setDataTerminoRecurso(String dataTerminoRecurso) {
		this.dataTerminoRecurso = dataTerminoRecurso;
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

	public String getIdAutoInfracao() {
		return idAutoInfracao;
	}

	public void setIdAutoInfracao(String idAutoInfracao) {
		this.idAutoInfracao = idAutoInfracao;
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

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}


}
