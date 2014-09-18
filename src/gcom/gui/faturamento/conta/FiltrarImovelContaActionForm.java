package gcom.gui.faturamento.conta;

import org.apache.struts.action.ActionForm;

public class FiltrarImovelContaActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String codigoCliente;	
	private String nomeCliente;
	private String tipoRelacao; 
	private String codigoClienteSuperior;
	private String nomeClienteSuperior;
	
	private String localidadeOrigemID;
	private String nomeLocalidadeOrigem;
	private String setorComercialOrigemCD;
	private String setorComercialOrigemID;
	private String nomeSetorComercialOrigem;
	private String quadraOrigemNM;
	private String quadraOrigemID;
	private String quadraMensagemOrigem;
	private String loteOrigem;
	private String subLoteOrigem;
	
	private String localidadeDestinoID;
	private String nomeLocalidadeDestino;
	private String setorComercialDestinoCD;
	private String setorComercialDestinoID;
	private String nomeSetorComercialDestino;
	private String quadraDestinoNM;
	private String quadraDestinoID;
	private String quadraMensagemDestino;
	private String loteDestino;
	private String subLoteDestino;
	
	private String codigoRotaOrigem;
	private String codigoRotaDestino;
	
	private String sequencialRotaOrigem;
	private String sequencialRotaDestino;
	
	private String idFaturamentoGrupo;
	private String[] banco;
	/**
	 * @autor: Adriana Muniz
	 * @date: 24/11/2011
	 * Acréscimo de atributo esfera de poder no filtro da consulta
	 * */
	private Integer[] esferaPoder;
	
	
	public String getIdFaturamentoGrupo() {
		return idFaturamentoGrupo;
	}

	public void setIdFaturamentoGrupo(String idFaturamentoGrupo) {
		this.idFaturamentoGrupo = idFaturamentoGrupo;
	}

	public String getCodigoRotaDestino() {
		return codigoRotaDestino;
	}

	public void setCodigoRotaDestino(String codigoRotaDestino) {
		this.codigoRotaDestino = codigoRotaDestino;
	}

	public String getCodigoRotaOrigem() {
		return codigoRotaOrigem;
	}

	public void setCodigoRotaOrigem(String codigoRotaOrigem) {
		this.codigoRotaOrigem = codigoRotaOrigem;
	}

	public String getSequencialRotaDestino() {
		return sequencialRotaDestino;
	}

	public void setSequencialRotaDestino(String sequencialRotaDestino) {
		this.sequencialRotaDestino = sequencialRotaDestino;
	}

	public String getSequencialRotaOrigem() {
		return sequencialRotaOrigem;
	}

	public void setSequencialRotaOrigem(String sequencialRotaOrigem) {
		this.sequencialRotaOrigem = sequencialRotaOrigem;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	/**
	 * @return Retorna o campo localidadeDestinoID.
	 */
	public String getLocalidadeDestinoID() {
		return localidadeDestinoID;
	}

	/**
	 * @param localidadeDestinoID O localidadeDestinoID a ser setado.
	 */
	public void setLocalidadeDestinoID(String localidadeDestinoID) {
		this.localidadeDestinoID = localidadeDestinoID;
	}

	/**
	 * @return Retorna o campo localidadeOrigemID.
	 */
	public String getLocalidadeOrigemID() {
		return localidadeOrigemID;
	}

	/**
	 * @param localidadeOrigemID O localidadeOrigemID a ser setado.
	 */
	public void setLocalidadeOrigemID(String localidadeOrigemID) {
		this.localidadeOrigemID = localidadeOrigemID;
	}

	/**
	 * @return Retorna o campo loteDestino.
	 */
	public String getLoteDestino() {
		return loteDestino;
	}

	/**
	 * @param loteDestino O loteDestino a ser setado.
	 */
	public void setLoteDestino(String loteDestino) {
		this.loteDestino = loteDestino;
	}

	/**
	 * @return Retorna o campo loteOrigem.
	 */
	public String getLoteOrigem() {
		return loteOrigem;
	}

	/**
	 * @param loteOrigem O loteOrigem a ser setado.
	 */
	public void setLoteOrigem(String loteOrigem) {
		this.loteOrigem = loteOrigem;
	}

	/**
	 * @return Retorna o campo nomeLocalidadeDestino.
	 */
	public String getNomeLocalidadeDestino() {
		return nomeLocalidadeDestino;
	}

	/**
	 * @param nomeLocalidadeDestino O nomeLocalidadeDestino a ser setado.
	 */
	public void setNomeLocalidadeDestino(String nomeLocalidadeDestino) {
		this.nomeLocalidadeDestino = nomeLocalidadeDestino;
	}

	/**
	 * @return Retorna o campo nomeLocalidadeOrigem.
	 */
	public String getNomeLocalidadeOrigem() {
		return nomeLocalidadeOrigem;
	}

	/**
	 * @param nomeLocalidadeOrigem O nomeLocalidadeOrigem a ser setado.
	 */
	public void setNomeLocalidadeOrigem(String nomeLocalidadeOrigem) {
		this.nomeLocalidadeOrigem = nomeLocalidadeOrigem;
	}

	/**
	 * @return Retorna o campo nomeSetorComercialDestino.
	 */
	public String getNomeSetorComercialDestino() {
		return nomeSetorComercialDestino;
	}

	/**
	 * @param nomeSetorComercialDestino O nomeSetorComercialDestino a ser setado.
	 */
	public void setNomeSetorComercialDestino(String nomeSetorComercialDestino) {
		this.nomeSetorComercialDestino = nomeSetorComercialDestino;
	}

	/**
	 * @return Retorna o campo nomeSetorComercialOrigem.
	 */
	public String getNomeSetorComercialOrigem() {
		return nomeSetorComercialOrigem;
	}

	/**
	 * @param nomeSetorComercialOrigem O nomeSetorComercialOrigem a ser setado.
	 */
	public void setNomeSetorComercialOrigem(String nomeSetorComercialOrigem) {
		this.nomeSetorComercialOrigem = nomeSetorComercialOrigem;
	}

	/**
	 * @return Retorna o campo quadraDestinoID.
	 */
	public String getQuadraDestinoID() {
		return quadraDestinoID;
	}

	/**
	 * @param quadraDestinoID O quadraDestinoID a ser setado.
	 */
	public void setQuadraDestinoID(String quadraDestinoID) {
		this.quadraDestinoID = quadraDestinoID;
	}

	/**
	 * @return Retorna o campo quadraDestinoNM.
	 */
	public String getQuadraDestinoNM() {
		return quadraDestinoNM;
	}

	/**
	 * @param quadraDestinoNM O quadraDestinoNM a ser setado.
	 */
	public void setQuadraDestinoNM(String quadraDestinoNM) {
		this.quadraDestinoNM = quadraDestinoNM;
	}

	/**
	 * @return Retorna o campo quadraMensagemDestino.
	 */
	public String getQuadraMensagemDestino() {
		return quadraMensagemDestino;
	}

	/**
	 * @param quadraMensagemDestino O quadraMensagemDestino a ser setado.
	 */
	public void setQuadraMensagemDestino(String quadraMensagemDestino) {
		this.quadraMensagemDestino = quadraMensagemDestino;
	}

	/**
	 * @return Retorna o campo quadraMensagemOrigem.
	 */
	public String getQuadraMensagemOrigem() {
		return quadraMensagemOrigem;
	}

	/**
	 * @param quadraMensagemOrigem O quadraMensagemOrigem a ser setado.
	 */
	public void setQuadraMensagemOrigem(String quadraMensagemOrigem) {
		this.quadraMensagemOrigem = quadraMensagemOrigem;
	}

	/**
	 * @return Retorna o campo quadraOrigemID.
	 */
	public String getQuadraOrigemID() {
		return quadraOrigemID;
	}

	/**
	 * @param quadraOrigemID O quadraOrigemID a ser setado.
	 */
	public void setQuadraOrigemID(String quadraOrigemID) {
		this.quadraOrigemID = quadraOrigemID;
	}

	/**
	 * @return Retorna o campo quadraOrigemNM.
	 */
	public String getQuadraOrigemNM() {
		return quadraOrigemNM;
	}

	/**
	 * @param quadraOrigemNM O quadraOrigemNM a ser setado.
	 */
	public void setQuadraOrigemNM(String quadraOrigemNM) {
		this.quadraOrigemNM = quadraOrigemNM;
	}

	/**
	 * @return Retorna o campo setorComercialDestinoCD.
	 */
	public String getSetorComercialDestinoCD() {
		return setorComercialDestinoCD;
	}

	/**
	 * @param setorComercialDestinoCD O setorComercialDestinoCD a ser setado.
	 */
	public void setSetorComercialDestinoCD(String setorComercialDestinoCD) {
		this.setorComercialDestinoCD = setorComercialDestinoCD;
	}

	/**
	 * @return Retorna o campo setorComercialDestinoID.
	 */
	public String getSetorComercialDestinoID() {
		return setorComercialDestinoID;
	}

	/**
	 * @param setorComercialDestinoID O setorComercialDestinoID a ser setado.
	 */
	public void setSetorComercialDestinoID(String setorComercialDestinoID) {
		this.setorComercialDestinoID = setorComercialDestinoID;
	}

	/**
	 * @return Retorna o campo setorComercialOrigemCD.
	 */
	public String getSetorComercialOrigemCD() {
		return setorComercialOrigemCD;
	}

	/**
	 * @param setorComercialOrigemCD O setorComercialOrigemCD a ser setado.
	 */
	public void setSetorComercialOrigemCD(String setorComercialOrigemCD) {
		this.setorComercialOrigemCD = setorComercialOrigemCD;
	}

	/**
	 * @return Retorna o campo setorComercialOrigemID.
	 */
	public String getSetorComercialOrigemID() {
		return setorComercialOrigemID;
	}

	/**
	 * @param setorComercialOrigemID O setorComercialOrigemID a ser setado.
	 */
	public void setSetorComercialOrigemID(String setorComercialOrigemID) {
		this.setorComercialOrigemID = setorComercialOrigemID;
	}

	/**
	 * @return Retorna o campo subLoteDestino.
	 */
	public String getSubLoteDestino() {
		return subLoteDestino;
	}

	/**
	 * @param subLoteDestino O subLoteDestino a ser setado.
	 */
	public void setSubLoteDestino(String subLoteDestino) {
		this.subLoteDestino = subLoteDestino;
	}

	/**
	 * @return Retorna o campo subLoteOrige.
	 */
	public String getSubLoteOrigem() {
		return subLoteOrigem;
	}

	/**
	 * @param subLoteOrige O subLoteOrige a ser setado.
	 */
	public void setSubLoteOrigem(String subLoteOrigem) {
		this.subLoteOrigem = subLoteOrigem;
	}

	/**
	 * @return Retorna o campo tipoRelacao.
	 */
	public String getTipoRelacao() {
		return tipoRelacao;
	}

	/**
	 * @param tipoRelacao O tipoRelacao a ser setado.
	 */
	public void setTipoRelacao(String tipoRelacao) {
		this.tipoRelacao = tipoRelacao;
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

	public String[] getBanco() {
		return banco;
	}

	public void setBanco(String banco[]) {
		this.banco = banco;
	}
	
	public Integer[] getEsferaPoder() {
		return esferaPoder;
	}

	public void setEsferaPoder(Integer[] esferaPoder) {
		this.esferaPoder = esferaPoder;
	}

}
