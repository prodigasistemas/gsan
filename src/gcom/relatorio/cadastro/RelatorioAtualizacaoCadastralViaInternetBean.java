package gcom.relatorio.cadastro;

import gcom.relatorio.RelatorioBean;

/**
 * [UC1076] - Gerar Relatorio Atualizacao Cadastral Via Internet.
 * @author Daniel Alves
 * @date 29/09/2010 
 */
public class RelatorioAtualizacaoCadastralViaInternetBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	private String matricula;
	private String localidade;
	private String unidade;
	private String gerencia;
	private String alteracao;
	private String solicitante;
	private String dataConfirmacao;
	private String idCliente;
	
	public RelatorioAtualizacaoCadastralViaInternetBean(){}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getAlteracao() {
		return alteracao;
	}

	public void setAlteracao(String alteracao) {
		this.alteracao = alteracao;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getGerencia() {
		return gerencia;
	}

	public void setGerencia(String gerencia) {
		this.gerencia = gerencia;
	}

	public String getDataConfirmacao() {
		return dataConfirmacao;
	}

	public void setDataConfirmacao(String dataConfirmacao) {
		this.dataConfirmacao = dataConfirmacao;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	
	
}
