package gcom.relatorio.cadastro.imovel;

import gcom.relatorio.RelatorioBean;

/**
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Rafael Corrêa
 * @created 11 de Novembro de 2005
 * @version 1.0
 */

public class RelatorioImovelEnderecoBean implements RelatorioBean  {

	//cabeçalho
	private String idGerenciaRegional;
	private String nomeGerenciaRegional;
	private String idLocalidade;
	private String nomeLocalidade;
	private String idSetorComercial;
	private String nomeSetorComercial;

	//linha
	private String endereco;
	private String matricula;
	private String nomeCliente;
	private String quadraLoteSubLote;
	private String categoria;
	private String economias;
	private String situacaoAgua;
	private String situacaoEsgoto;
	private String indicadorMedidoEsgoto;
	private String percentualEsgoto;
	private String consumoMinimoEsgoto;
	private String quadraLoteSubLoteRotaSegRota;
	
		
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getConsumoMinimoEsgoto() {
		return consumoMinimoEsgoto;
	}
	public void setConsumoMinimoEsgoto(String consumoMinimoEsgoto) {
		this.consumoMinimoEsgoto = consumoMinimoEsgoto;
	}
	public String getEconomias() {
		return economias;
	}
	public void setEconomias(String economias) {
		this.economias = economias;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}
	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}
	public String getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public String getIdSetorComercial() {
		return idSetorComercial;
	}
	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}
	public String getIndicadorMedidoEsgoto() {
		return indicadorMedidoEsgoto;
	}
	public void setIndicadorMedidoEsgoto(String indicadorMedidoEsgoto) {
		this.indicadorMedidoEsgoto = indicadorMedidoEsgoto;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}
	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}
	public String getNomeSetorComercial() {
		return nomeSetorComercial;
	}
	public void setNomeSetorComercial(String nomeSetorComercial) {
		this.nomeSetorComercial = nomeSetorComercial;
	}
	public String getPercentualEsgoto() {
		return percentualEsgoto;
	}
	public void setPercentualEsgoto(String percentualEsgoto) {
		this.percentualEsgoto = percentualEsgoto;
	}
	public String getQuadraLoteSubLote() {
		return quadraLoteSubLote;
	}
	public void setQuadraLoteSubLote(String quadraLoteSubLote) {
		this.quadraLoteSubLote = quadraLoteSubLote;
	}
	public String getSituacaoAgua() {
		return situacaoAgua;
	}
	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}
	public String getSituacaoEsgoto() {
		return situacaoEsgoto;
	}
	public void setSituacaoEsgoto(String situacaoEsgoto) {
		this.situacaoEsgoto = situacaoEsgoto;
	}
	/**
	 * @return Returns the quadraLoteSubLoteRotaSegRota.
	 */
	public String getQuadraLoteSubLoteRotaSegRota() {
		return quadraLoteSubLoteRotaSegRota;
	}
	/**
	 * @param quadraLoteSubLoteRotaSegRota The quadraLoteSubLoteRotaSegRota to set.
	 */
	public void setQuadraLoteSubLoteRotaSegRota(String quadraLoteSubLoteRotaSegRota) {
		this.quadraLoteSubLoteRotaSegRota = quadraLoteSubLoteRotaSegRota;
	}
	
}
