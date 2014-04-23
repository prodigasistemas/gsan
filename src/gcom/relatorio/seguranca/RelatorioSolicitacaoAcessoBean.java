package gcom.relatorio.seguranca;

import gcom.relatorio.RelatorioBean;

/**
 * classe responsável por criar o relatório Solicitacao de Acesso
 * 
 * [UC1040] Gerar Relatório Solicitacao de Acesso
 * 
 * @author Hugo Leonardo
 *
 * @date 13/07/2010
 */
public class RelatorioSolicitacaoAcessoBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	private String matricula;
	private String idFuncionarioSolicitante; 
	private String nomeFuncionarioSolicitante;
	private String idFuncionarioSuperior; 
	private String nomeFuncionarioSuperior;
	private String nomeUsuario; 
	private String cpf; 
	private String idLotacao; 
	private String nomeLotacao;
	private String dataInicial; 
	private String dataFinal; 
	private String dataSolicitacao;
	private String dataAutorizacao;
	private String situacaoAcesso;
	
	public RelatorioSolicitacaoAcessoBean(){
		
	}

	public RelatorioSolicitacaoAcessoBean(String matricula, String idFuncionarioSolicitante, 
			String nomeFuncionarioSolicitante, String idFuncionarioSuperior, 
			String nomeFuncionarioSuperior, String nomeUsuario, String cpf, 
			String idLotacao, String nomeLotacao, String dataInicial, String dataFinal, 
			String dataSolicitacao, String dataAutorizacao, String situacaoAcesso) {
		
		this.matricula = matricula;
		this.idFuncionarioSolicitante = idFuncionarioSolicitante;
		this.nomeFuncionarioSolicitante = nomeFuncionarioSolicitante;
		this.idFuncionarioSuperior = idFuncionarioSuperior;
		this.nomeFuncionarioSuperior = nomeFuncionarioSuperior;
		this.nomeUsuario = nomeUsuario;
		this.cpf = cpf;
		this.idLotacao = idLotacao;
		this.nomeLotacao = nomeLotacao;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.dataSolicitacao = dataSolicitacao;
		this.dataAutorizacao = dataAutorizacao;
		this.situacaoAcesso = situacaoAcesso;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public String getIdFuncionarioSolicitante() {
		return idFuncionarioSolicitante;
	}

	public void setIdFuncionarioSolicitante(String idFuncionarioSolicitante) {
		this.idFuncionarioSolicitante = idFuncionarioSolicitante;
	}

	public String getIdFuncionarioSuperior() {
		return idFuncionarioSuperior;
	}

	public void setIdFuncionarioSuperior(String idFuncionarioSuperior) {
		this.idFuncionarioSuperior = idFuncionarioSuperior;
	}

	public String getIdLotacao() {
		return idLotacao;
	}

	public void setIdLotacao(String idLotacao) {
		this.idLotacao = idLotacao;
	}

	public String getNomeFuncionarioSolicitante() {
		return nomeFuncionarioSolicitante;
	}

	public void setNomeFuncionarioSolicitante(String nomeFuncionarioSolicitante) {
		this.nomeFuncionarioSolicitante = nomeFuncionarioSolicitante;
	}

	public String getNomeFuncionarioSuperior() {
		return nomeFuncionarioSuperior;
	}

	public void setNomeFuncionarioSuperior(String nomeFuncionarioSuperior) {
		this.nomeFuncionarioSuperior = nomeFuncionarioSuperior;
	}

	public String getNomeLotacao() {
		return nomeLotacao;
	}

	public void setNomeLotacao(String nomeLotacao) {
		this.nomeLotacao = nomeLotacao;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(String dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public String getDataAutorizacao() {
		return dataAutorizacao;
	}

	public void setDataAutorizacao(String dataAutorizacao) {
		this.dataAutorizacao = dataAutorizacao;
	}

	public String getSituacaoAcesso() {
		return situacaoAcesso;
	}

	public void setSituacaoAcesso(String situacaoAcesso) {
		this.situacaoAcesso = situacaoAcesso;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
}
