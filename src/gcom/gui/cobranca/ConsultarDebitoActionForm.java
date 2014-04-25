package gcom.gui.cobranca;

import org.apache.struts.action.ActionForm;

public class ConsultarDebitoActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String codigoImovel;
	
	private String inscricaoImovel;
	
	private String codigoImovelClone;

	private String codigoCliente;
	
	private String nomeCliente;
	
	private String codigoClienteClone;
	
	private String tipoRelacao;
	
	private String codigoClienteSuperior;
	
	private String nomeClienteSuperior;
	
	private String codigoClienteSuperiorClone;

	private String referenciaInicial;

	private String referenciaFinal;

	private String dataVencimentoInicial;

	private String dataVencimentoFinal;
	
	private String refInicial;
	
	private String refFinal;
	
	private String dtInicial;
	
	private String dtFinal;
	
	private String responsavel;
	
	
	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getCodigoImovel() {
		return codigoImovel;
	}

	public void setCodigoImovel(String codigoImovel) {
		this.codigoImovel = codigoImovel;
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

	public String getReferenciaFinal() {
		return referenciaFinal;
	}

	public void setReferenciaFinal(String referenciaFinal) {
		this.referenciaFinal = referenciaFinal;
	}

	public String getReferenciaInicial() {
		return referenciaInicial;
	}

	public void setReferenciaInicial(String referenciaInicial) {
		this.referenciaInicial = referenciaInicial;
	}

	public String getTipoRelacao() {
		return tipoRelacao;
	}

	public void setTipoRelacao(String tipoRelacao) {
		this.tipoRelacao = tipoRelacao;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getCodigoClienteClone() {
		return codigoClienteClone;
	}

	public void setCodigoClienteClone(String codigoClienteClone) {
		this.codigoClienteClone = codigoClienteClone;
	}

	public String getCodigoImovelClone() {
		return codigoImovelClone;
	}

	public void setCodigoImovelClone(String codigoImovelClone) {
		this.codigoImovelClone = codigoImovelClone;
	}

	public String getDtFinal() {
		return dtFinal;
	}

	public void setDtFinal(String dtFinal) {
		this.dtFinal = dtFinal;
	}

	public String getDtInicial() {
		return dtInicial;
	}

	public void setDtInicial(String dtInicial) {
		this.dtInicial = dtInicial;
	}

	public String getRefFinal() {
		return refFinal;
	}

	public void setRefFinal(String refFinal) {
		this.refFinal = refFinal;
	}

	public String getRefInicial() {
		return refInicial;
	}

	public void setRefInicial(String refInicial) {
		this.refInicial = refInicial;
	}

	/**
	 * @return Retorna o campo responsavel.
	 */
	public String getResponsavel() {
		return responsavel;
	}

	/**
	 * @param responsavel O responsavel a ser setado.
	 */
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
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

	/**
	 * @return Retorna o campo codigoClienteSuperiorClone.
	 */
	public String getCodigoClienteSuperiorClone() {
		return codigoClienteSuperiorClone;
	}

	/**
	 * @param codigoClienteSuperiorClone O codigoClienteSuperiorClone a ser setado.
	 */
	public void setCodigoClienteSuperiorClone(String codigoClienteSuperiorClone) {
		this.codigoClienteSuperiorClone = codigoClienteSuperiorClone;
	}

	/**
	 * @return Retorna o campo nomeClienteSuperior.
	 */
	public String getNomeClienteSuperior() {
		return nomeClienteSuperior;
	}

	/**
	 * @param nomeClienteSuperior O nomeClienteSuperior a ser setado.
	 */
	public void setNomeClienteSuperior(String nomeClienteSuperior) {
		this.nomeClienteSuperior = nomeClienteSuperior;
	}
	
}
