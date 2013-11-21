package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

/**
 * Descrição da classe
 * Classe responsável pela descricão dos beans do relatório
 * 
 * @author Anderson Italo
 * @date 09/10/2009
 */
public class RelatorioComandosAcaoCobrancaEventualBean implements RelatorioBean {
	
	private String acao;
	private String atividade;
	private String titulo;
	private String dataComando;
	private String dataPrevista;
	private String dataRealizacao;
	private String qtdDocs;
	private String valor;
	private String qtdItens;
	private String dataEncerramentoRealizada;
	
	private String idLocalidadeInicial;
	private String idLocalidadeFinal;
	private String nomeLocalidadeInicial;
	private String nomeLocalidadeFinal;
	private String idSetorComercialInicial;
	private String idSetorComercialFinal;
	
	public RelatorioComandosAcaoCobrancaEventualBean() {}
	
	public String getAcao() {
		return acao;
	}
	
	public void setAcao(String acao) {
		this.acao = acao;
	}
	
	public String getAtividade() {
		return atividade;
	}
	
	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}
	
	public String getDataComando() {
		return dataComando;
	}
	
	public void setDataComando(String dataComando) {
		this.dataComando = dataComando;
	}
	
	public String getDataPrevista() {
		return dataPrevista;
	}
	
	public void setDataPrevista(String dataPrevista) {
		this.dataPrevista = dataPrevista;
	}
	
	public String getDataRealizacao() {
		return dataRealizacao;
	}
	
	public void setDataRealizacao(String dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}
	
	public String getQtdDocs() {
		return qtdDocs;
	}
	
	public void setQtdDocs(String qtdDocs) {
		this.qtdDocs = qtdDocs;
	}
	
	public String getQtdItens() {
		return qtdItens;
	}
	
	public void setQtdItens(String qtdItens) {
		this.qtdItens = qtdItens;
	}
	
	public String getValor() {
		return valor;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getDataEncerramentoRealizada() {
		return dataEncerramentoRealizada;
	}

	public void setDataEncerramentoRealizada(String dataEncerramentoRealizada) {
		this.dataEncerramentoRealizada = dataEncerramentoRealizada;
	}

	/**
	 * @return Returns the idLocalidadeFinal.
	 */
	public String getIdLocalidadeFinal() {
		return idLocalidadeFinal;
	}

	/**
	 * @param idLocalidadeFinal The idLocalidadeFinal to set.
	 */
	public void setIdLocalidadeFinal(String idLocalidadeFinal) {
		this.idLocalidadeFinal = idLocalidadeFinal;
	}

	/**
	 * @return Returns the idLocalidadeInicial.
	 */
	public String getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}

	/**
	 * @param idLocalidadeInicial The idLocalidadeInicial to set.
	 */
	public void setIdLocalidadeInicial(String idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}

	/**
	 * @return Returns the idSetorComercialFinal.
	 */
	public String getIdSetorComercialFinal() {
		return idSetorComercialFinal;
	}

	/**
	 * @param idSetorComercialFinal The idSetorComercialFinal to set.
	 */
	public void setIdSetorComercialFinal(String idSetorComercialFinal) {
		this.idSetorComercialFinal = idSetorComercialFinal;
	}

	/**
	 * @return Returns the idSetorComercialInicial.
	 */
	public String getIdSetorComercialInicial() {
		return idSetorComercialInicial;
	}

	/**
	 * @param idSetorComercialInicial The idSetorComercialInicial to set.
	 */
	public void setIdSetorComercialInicial(String idSetorComercialInicial) {
		this.idSetorComercialInicial = idSetorComercialInicial;
	}

	/**
	 * @return Returns the nomeLocalidadeFinal.
	 */
	public String getNomeLocalidadeFinal() {
		return nomeLocalidadeFinal;
	}

	/**
	 * @param nomeLocalidadeFinal The nomeLocalidadeFinal to set.
	 */
	public void setNomeLocalidadeFinal(String nomeLocalidadeFinal) {
		this.nomeLocalidadeFinal = nomeLocalidadeFinal;
	}

	/**
	 * @return Returns the nomeLocalidadeInicial.
	 */
	public String getNomeLocalidadeInicial() {
		return nomeLocalidadeInicial;
	}

	/**
	 * @param nomeLocalidadeInicial The nomeLocalidadeInicial to set.
	 */
	public void setNomeLocalidadeInicial(String nomeLocalidadeInicial) {
		this.nomeLocalidadeInicial = nomeLocalidadeInicial;
	}
	
}
