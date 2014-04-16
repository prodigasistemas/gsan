package gcom.relatorio.faturamento.conta;

import gcom.relatorio.RelatorioBean;

/**
 * [UC] 
 * @author Vivianne Sousa
 * @date 27/01/2007
 */
public class RelatorioContasEmitidasBean implements RelatorioBean {
	
	private String codigoResponsavel;
	private String dataVencimento;
	private String endereco;
	private String esferaPoderDesc;
	private String esferaPoderId;
	private String grupoFaturamentoId;
	private String inscricao;
	private String localidade;
	private String matricula;
	private String mesAnoReferencia;
	private String nomeResponsavel;
	private String nomeUsuario;
	private String valor;
	private String numeroContasPorEsfera;
	private String valorTotalPorEsfera;
	
	public RelatorioContasEmitidasBean( String codigoResponsavel,
			String dataVencimento,String endereco,
			String esferaPoderDesc,String esferaPoderId,
			String grupoFaturamentoId,String inscricao,
			String localidade,String matricula,
			String mesAnoReferencia,String nomeResponsavel,
			String nomeUsuario,String valor,
			String numeroContasPorEsfera,
			String valorTotalPorEsfera) {
		
		this.codigoResponsavel = codigoResponsavel;
		this.dataVencimento = dataVencimento;
		this.endereco = endereco;
		this.esferaPoderDesc = esferaPoderDesc;
		this.esferaPoderId = esferaPoderId;
		this.grupoFaturamentoId = grupoFaturamentoId;
		this.inscricao = inscricao;
		this.localidade = localidade;
		this.matricula = matricula;
		this.mesAnoReferencia = mesAnoReferencia;
		this.nomeResponsavel = nomeResponsavel;
		this.nomeUsuario = nomeUsuario;
		this.valor = valor;
		this.numeroContasPorEsfera = numeroContasPorEsfera;
		this.valorTotalPorEsfera = valorTotalPorEsfera;
		
	}

	public String getCodigoResponsavel() {
		return codigoResponsavel;
	}

	public void setCodigoResponsavel(String codigoResponsavel) {
		this.codigoResponsavel = codigoResponsavel;
	}

	public String getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEsferaPoderDesc() {
		return esferaPoderDesc;
	}

	public void setEsferaPoderDesc(String esferaPoderDesc) {
		this.esferaPoderDesc = esferaPoderDesc;
	}

	public String getEsferaPoderId() {
		return esferaPoderId;
	}

	public void setEsferaPoderId(String esferaPoderId) {
		this.esferaPoderId = esferaPoderId;
	}

	public String getGrupoFaturamentoId() {
		return grupoFaturamentoId;
	}

	public void setGrupoFaturamentoId(String grupoFaturamentoId) {
		this.grupoFaturamentoId = grupoFaturamentoId;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}

	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getNumeroContasPorEsfera() {
		return numeroContasPorEsfera;
	}

	public void setNumeroContasPorEsfera(String numeroContasPorEsfera) {
		this.numeroContasPorEsfera = numeroContasPorEsfera;
	}

	public String getValorTotalPorEsfera() {
		return valorTotalPorEsfera;
	}

	public void setValorTotalPorEsfera(String valorTotalPorEsfera) {
		this.valorTotalPorEsfera = valorTotalPorEsfera;
	}

	

}
