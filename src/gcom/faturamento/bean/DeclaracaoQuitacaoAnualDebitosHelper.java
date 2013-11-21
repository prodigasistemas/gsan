package gcom.faturamento.bean;

import gcom.faturamento.ExtratoQuitacao;
import gcom.faturamento.ExtratoQuitacaoItem;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

public class DeclaracaoQuitacaoAnualDebitosHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer idImovel;
	private BigDecimal valorTotalContas;
	private Integer anoMesArrecadacao;
	private Collection<DeclaracaoQuitacaoAnualDebitosItemHelper> extratoQuitacaoItens;
	
	
	//Dados utilizados Para gera��o do txt
	private String nomeClienteUsuario;
	private String matriculaFormatada;
	private String inscricaoImovel;
	private String firma;
	private String idGrupo;
	private String endereco;
	private String enderecoDestinatario;
	private String bairro;
	private String municipio;
	private String uf;
	private String cep;
	private Collection<ExtratoQuitacaoItem> faturas;
	private ExtratoQuitacao extratoQuitacaoParaAtualizacao;
	private String codigoRota;
	private String seguencialRota;
	
	/*TODO : COSANPA
	 * inclus�o de novo atributo e respectivos get e set
	 * para atender ao mantis 201
	 * entrega em endere�o altivo	 *  */
	private short enderecoAlternativo;
	
	public DeclaracaoQuitacaoAnualDebitosHelper(
			Integer idImovel,
			BigDecimal valorTotalContas,
			Integer anoMesArrecadacao,
			Collection<DeclaracaoQuitacaoAnualDebitosItemHelper> extratoQuitacaoItens) {
		this.idImovel = idImovel;
		this.valorTotalContas = valorTotalContas;
		this.anoMesArrecadacao = anoMesArrecadacao;
		this.extratoQuitacaoItens = extratoQuitacaoItens;
	}

	public DeclaracaoQuitacaoAnualDebitosHelper(Integer idImovel,
			Integer anoMesArrecadacao) {
		this.idImovel = idImovel;
		this.anoMesArrecadacao = anoMesArrecadacao;
	}

	public DeclaracaoQuitacaoAnualDebitosHelper() {
		// TODO Auto-generated constructor stub
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public BigDecimal getValorTotalContas() {
		return valorTotalContas;
	}

	public void setValorTotalContas(BigDecimal valorTotalContas) {
		this.valorTotalContas = valorTotalContas;
	}

	public Integer getAnoMesArrecadacao() {
		return anoMesArrecadacao;
	}

	public void setAnoMesArrecadacao(Integer anoMesArrecadacao) {
		this.anoMesArrecadacao = anoMesArrecadacao;
	}

	public Collection<DeclaracaoQuitacaoAnualDebitosItemHelper> getExtratoQuitacaoItens() {
		return extratoQuitacaoItens;
	}

	public void setExtratoQuitacaoItens(
			Collection<DeclaracaoQuitacaoAnualDebitosItemHelper> extratoQuitacaoItens) {
		this.extratoQuitacaoItens = extratoQuitacaoItens;
	}

	public String getMatriculaFormatada() {
		return matriculaFormatada;
	}

	public void setMatriculaFormatada(String matriculaFormatada) {
		this.matriculaFormatada = matriculaFormatada;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getFirma() {
		return firma;
	}

	public void setFirma(String firma) {
		this.firma = firma;
	}

	public String getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(String idGrupo) {
		this.idGrupo = idGrupo;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNomeClienteUsuario() {
		return nomeClienteUsuario;
	}

	public void setNomeClienteUsuario(String nomeClienteUsuario) {
		this.nomeClienteUsuario = nomeClienteUsuario;
	}

	public Collection<ExtratoQuitacaoItem> getFaturas() {
		return faturas;
	}

	public void setFaturas(Collection<ExtratoQuitacaoItem> faturas) {
		this.faturas = faturas;
	}

	public ExtratoQuitacao getExtratoQuitacaoParaAtualizacao() {
		return extratoQuitacaoParaAtualizacao;
	}

	public void setExtratoQuitacaoParaAtualizacao(
			ExtratoQuitacao extratoQuitacaoParaAtualizacao) {
		this.extratoQuitacaoParaAtualizacao = extratoQuitacaoParaAtualizacao;
	}

	public String getEnderecoDestinatario() {
		return enderecoDestinatario;
	}

	public void setEnderecoDestinatario(String enderecoDestinatario) {
		this.enderecoDestinatario = enderecoDestinatario;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(String codigoRota) {
		this.codigoRota = codigoRota;
	}

	public String getSeguencialRota() {
		return seguencialRota;
	}

	public void setSeguencialRota(String seguencialRota) {
		this.seguencialRota = seguencialRota;
	}
	
	public short getEnderecoAlternativo() {
		return enderecoAlternativo;
	}

	public void setEnderecoAlternativo(short enderecoAlternativo) {
		this.enderecoAlternativo = enderecoAlternativo;
	}

}
