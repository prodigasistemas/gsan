package gcom.relatorio.faturamento;

import gcom.relatorio.RelatorioBean;

/**
 * [UC1198] Gerar Relatóo de Arrecadação das Multas de Autos de Infração Pendentes 
 * @author Hugo Azevedo
 * @date 12/07/2011
 */
public class RelatorioMultasAutosInfracaoPendentesBean implements RelatorioBean {
	
	private String idGrupoFaturamento;
	private String grupoFaturamento;
	private String idFuncionario;
	private String nomeFuncionario;
	private String idLocalidade;
	private String nomeLocalidade;
	private String rota;
	private String matriculaImovel;
	private String nomeCliente;
	private String endereco;
	private String autoInfracao;
	private String descricaoServico;
	private String dataAutuacao;

	
	public RelatorioMultasAutosInfracaoPendentesBean(){}

	
	public String getIdGrupoFaturamento(){
		return idGrupoFaturamento;
	}
	
	
	public void setIdGrupoFaturamento(String idGrupoFaturamento){
		this.idGrupoFaturamento = idGrupoFaturamento;
	}

	public String getGrupoFaturamento() {
		return grupoFaturamento;
	}


	public void setGrupoFaturamento(String grupoFaturamento) {
		this.grupoFaturamento = grupoFaturamento;
	}


	public String getIdFuncionario() {
		return idFuncionario;
	}


	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}


	public String getNomeFuncionario() {
		return nomeFuncionario;
	}


	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}


	public String getIdLocalidade() {
		return idLocalidade;
	}


	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}


	public String getNomeLocalidade() {
		return nomeLocalidade;
	}


	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}


	public String getRota() {
		return rota;
	}


	public void setRota(String rota) {
		this.rota = rota;
	}


	public String getMatriculaImovel() {
		return matriculaImovel;
	}


	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}


	public String getNomeCliente() {
		return nomeCliente;
	}


	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}


	public String getEndereco() {
		return endereco;
	}


	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	public String getAutoInfracao() {
		return autoInfracao;
	}


	public void setAutoInfracao(String autoInfracao) {
		this.autoInfracao = autoInfracao;
	}


	public String getDescricaoServico() {
		return descricaoServico;
	}


	public void setDescricaoServico(String descricaoServico) {
		this.descricaoServico = descricaoServico;
	}


	public String getDataAutuacao() {
		return dataAutuacao;
	}


	public void setDataAutuacao(String dataAutuacao) {
		this.dataAutuacao = dataAutuacao;
	}
	
	
	
	
}
