package gcom.gui.faturamento.bean;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Classe que irá auxiliar no formato de entrada da pesquisa 
 * de registro de atendimento
 *
 * @author Rafael Pinto
 * @date 30/04/2007
 */
public class FiltrarImovelInserirManterContaHelper {
	
	private Integer localidadeOrigemID = null;
	private Integer setorComercialOrigemID = null;
	private Integer quadraOrigemID = null; 
	private Short loteOrigem = null;
	private Short subLoteOrigem = null;
	private Integer localidadeDestinoID = null;
	private Integer setorComercialDestinoID = null;
	private Integer quadraDestinoID = null;	
	private Short loteDestino = null;
	private Short subLoteDestino = null; 
	private String[] quadras = null;
	
	private Integer codigoRotaOrigem = null;
	private Integer codigoRotaDestino = null;
	private Integer sequencialRotaOrigem = null;
	private Integer sequencialRotaDestino = null;
	
	private Collection colecaoQuadraSelecionada = null;
	
	private Integer idGrupoFaturamento;
	
	private Boolean verificarImovelPerfilBloqueado = false; 
	
	/**TODO:COSANPA
	 * @autor: Adriana Muniz
	 * @date: 24/11/2011
	 * Acréscimo de atributo esfera de poder no filtro da consulta
	 * */
	private Integer[] esferasPoder = null;
	
	public Integer[] getEsferasPoder() {
		return esferasPoder;
	}

	public void setEsferasPoder(Integer[] esferasPoder) {
		this.esferasPoder = esferasPoder;
	}

	/**
	 * @return Retorna o campo verificarImovelPerfilBloqueado.
	 */
	public Boolean getVerificarImovelPerfilBloqueado() {
		return verificarImovelPerfilBloqueado;
	}

	/**
	 * @param verificarImovelPerfilBloqueado O verificarImovelPerfilBloqueado a ser setado.
	 */
	public void setVerificarImovelPerfilBloqueado(
			Boolean verificarImovelPerfilBloqueado) {
		this.verificarImovelPerfilBloqueado = verificarImovelPerfilBloqueado;
	}

	public Collection getColecaoQuadraSelecionada() {
		
		if(this.quadras != null && !this.quadras.equals("")){
			
			colecaoQuadraSelecionada = new ArrayList();
			
			for (int i = 0; i < this.quadras.length; i++) {

				int idQuadra = Integer.parseInt(this.quadras[i]);
				
				colecaoQuadraSelecionada.add(idQuadra);
			}
		}		
		
		return colecaoQuadraSelecionada;	
	}

	public void setColecaoQuadraSelecionada(Collection colecaoQuadraSelecionada) {
		this.colecaoQuadraSelecionada = colecaoQuadraSelecionada;
	}

	public FiltrarImovelInserirManterContaHelper() { }
	
	public Integer getLocalidadeDestinoID() {
		return localidadeDestinoID;
	}

	public void setLocalidadeDestinoID(Integer localidadeDestinoID) {
		this.localidadeDestinoID = localidadeDestinoID;
	}

	public Integer getLocalidadeOrigemID() {
		return localidadeOrigemID;
	}

	public void setLocalidadeOrigemID(Integer localidadeOrigemID) {
		this.localidadeOrigemID = localidadeOrigemID;
	}

	public Short getLoteDestino() {
		return loteDestino;
	}

	public void setLoteDestino(Short loteDestino) {
		this.loteDestino = loteDestino;
	}

	public Short getLoteOrigem() {
		return loteOrigem;
	}

	public void setLoteOrigem(Short loteOrigem) {
		this.loteOrigem = loteOrigem;
	}

	public Integer getQuadraDestinoID() {
		return quadraDestinoID;
	}

	public void setQuadraDestinoID(Integer quadraDestinoID) {
		this.quadraDestinoID = quadraDestinoID;
	}

	public Integer getQuadraOrigemID() {
		return quadraOrigemID;
	}

	public void setQuadraOrigemID(Integer quadraOrigemID) {
		this.quadraOrigemID = quadraOrigemID;
	}

	public String[] getQuadras() {
		return quadras;
	}

	public void setQuadras(String[] quadras) {
		this.quadras = quadras;
	}

	public Integer getSetorComercialDestinoID() {
		return setorComercialDestinoID;
	}

	public void setSetorComercialDestinoID(Integer setorComercialDestinoID) {
		this.setorComercialDestinoID = setorComercialDestinoID;
	}

	public Integer getSetorComercialOrigemID() {
		return setorComercialOrigemID;
	}

	public void setSetorComercialOrigemID(Integer setorComercialOrigemID) {
		this.setorComercialOrigemID = setorComercialOrigemID;
	}

	public Short getSubLoteDestino() {
		return subLoteDestino;
	}

	public void setSubLoteDestino(Short subLoteDestino) {
		this.subLoteDestino = subLoteDestino;
	}

	public Short getSubLoteOrigem() {
		return subLoteOrigem;
	}

	public void setSubLoteOrigem(Short subLoteOrigem) {
		this.subLoteOrigem = subLoteOrigem;
	}

	public Integer getCodigoRotaDestino() {
		return codigoRotaDestino;
	}

	public void setCodigoRotaDestino(Integer codigoRotaDestino) {
		this.codigoRotaDestino = codigoRotaDestino;
	}

	public Integer getCodigoRotaOrigem() {
		return codigoRotaOrigem;
	}

	public void setCodigoRotaOrigem(Integer codigoRotaOrigem) {
		this.codigoRotaOrigem = codigoRotaOrigem;
	}

	public Integer getSequencialRotaDestino() {
		return sequencialRotaDestino;
	}

	public void setSequencialRotaDestino(Integer sequencialRotaDestino) {
		this.sequencialRotaDestino = sequencialRotaDestino;
	}

	public Integer getSequencialRotaOrigem() {
		return sequencialRotaOrigem;
	}

	public void setSequencialRotaOrigem(Integer sequencialRotaOrigem) {
		this.sequencialRotaOrigem = sequencialRotaOrigem;
	}

	public Integer getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}

	public void setIdGrupoFaturamento(Integer idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
	}
	
	

}
