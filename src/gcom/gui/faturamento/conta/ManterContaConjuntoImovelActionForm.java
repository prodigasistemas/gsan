package gcom.gui.faturamento.conta;

import org.apache.struts.action.ActionForm;

public class ManterContaConjuntoImovelActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String inscricaoInicial;
	private String inscricaoFinal;
	private String quatidadeImovel;
	private String mesAnoConta;
	private String quatidadeConta;
	private String codigoCliente;
	private String nomeCliente;
	private String codigoClienteSuperior;
	
	private String dataVencimentoInicial;
	private String dataVencimentoFinal;
	
	private String idGrupoFaturamento;
	private String mesAnoContaFinal;
	
	private String indicadorContaPaga;
	private String somenteDebitoAutomatico;
	
	
	/**
	 * @return Retorna o campo indicadorContaPaga.
	 */
	public String getIndicadorContaPaga() {
		return indicadorContaPaga;
	}
	/**
	 * @param indicadorContaPaga O indicadorContaPaga a ser setado.
	 */
	public void setIndicadorContaPaga(String indicadorContaPaga) {
		this.indicadorContaPaga = indicadorContaPaga;
	}
	public String getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}
	public void setIdGrupoFaturamento(String idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
	}
	/**
	 * @return Retorna o campo codigoCliente.
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}
	/**
	 * @param codigoCliente O codigoCliente a ser setado.
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	/**
	 * @return Retorna o campo nomeCliente.
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}
	/**
	 * @param nomeCliente O nomeCliente a ser setado.
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	/**
	 * @return Retorna o campo inscricaoFinal.
	 */
	public String getInscricaoFinal() {
		return inscricaoFinal;
	}
	/**
	 * @param inscricaoFinal O inscricaoFinal a ser setado.
	 */
	public void setInscricaoFinal(String inscricaoFinal) {
		this.inscricaoFinal = inscricaoFinal;
	}
	/**
	 * @return Retorna o campo inscricaoInicial.
	 */
	public String getInscricaoInicial() {
		return inscricaoInicial;
	}
	/**
	 * @param inscricaoInicial O inscricaoInicial a ser setado.
	 */
	public void setInscricaoInicial(String inscricaoInicial) {
		this.inscricaoInicial = inscricaoInicial;
	}
	/**
	 * @return Retorna o campo mesAnoConta.
	 */
	public String getMesAnoConta() {
		return mesAnoConta;
	}
	/**
	 * @param mesAnoConta O mesAnoConta a ser setado.
	 */
	public void setMesAnoConta(String mesAnoConta) {
		this.mesAnoConta = mesAnoConta;
	}
	/**
	 * @return Retorna o campo quatidadeConta.
	 */
	public String getQuatidadeConta() {
		return quatidadeConta;
	}
	/**
	 * @param quatidadeConta O quatidadeConta a ser setado.
	 */
	public void setQuatidadeConta(String quatidadeConta) {
		this.quatidadeConta = quatidadeConta;
	}
	/**
	 * @return Retorna o campo quatidadeImovel.
	 */
	public String getQuatidadeImovel() {
		return quatidadeImovel;
	}
	/**
	 * @param quatidadeImovel O quatidadeImovel a ser setado.
	 */
	public void setQuatidadeImovel(String quatidadeImovel) {
		this.quatidadeImovel = quatidadeImovel;
	}
	public String getDataVencimentoFinal() {
		return dataVencimentoFinal;
	}
	public void setDataVencimentoFinal(String dataVencimentoFinal) {
		this.dataVencimentoFinal = dataVencimentoFinal;
	}
	public String getDataVencimentoInicial() {
		return dataVencimentoInicial;
	}
	public void setDataVencimentoInicial(String dataVencimentoInicial) {
		this.dataVencimentoInicial = dataVencimentoInicial;
	}
	public String getMesAnoContaFinal() {
		return mesAnoContaFinal;
	}
	public void setMesAnoContaFinal(String mesAnoContaFinal) {
		this.mesAnoContaFinal = mesAnoContaFinal;
	}
	/**
	 * @return Retorna o campo codigoClienteSuperior.
	 */
	public String getCodigoClienteSuperior() {
		return codigoClienteSuperior;
	}
	/**
	 * @param codigoClienteSuperior O codigoClienteSuperior a ser setado.
	 */
	public void setCodigoClienteSuperior(String codigoClienteSuperior) {
		this.codigoClienteSuperior = codigoClienteSuperior;
	}
	
	
	public String getSomenteDebitoAutomatico() {
		return somenteDebitoAutomatico;
	}
	
	public void setSomenteDebitoAutomatico(String somenteDebitoAutomatico) {
		this.somenteDebitoAutomatico = somenteDebitoAutomatico;
	}
	
}
