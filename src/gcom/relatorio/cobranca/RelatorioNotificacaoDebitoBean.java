package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;
import java.util.Date;

public class RelatorioNotificacaoDebitoBean implements RelatorioBean {

	private String idImovel;
	private String nomeCliente;
	private String inscricao;
	private String mesAno;
	private Date dataVencimento;
	private BigDecimal valor;
	private String codigoRota;
	private String sequencialRota;
	private Integer idDocumentoCobranca;
	private String endereco;
	private String representacaoNumericaCodBarraFormatada;
	private String representacaoNumericaCodBarraSemDigito;
	private String enderecoCorrespondencia;
	private String icEnderecoAlternativo;
	private String ruaComplementoCorrespondencia;
	private String cidadeEstadoCorrespondencia;
	private String bairroCorrespondencia;
	private String cepCorrespondencia;
	private Date dataEmissao;

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getRepresentacaoNumericaCodBarraFormatada() {
		return representacaoNumericaCodBarraFormatada;
	}

	public void setRepresentacaoNumericaCodBarraFormatada(String representacaoNumericaCodBarraFormatada) {
		this.representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarraFormatada;
	}

	public String getRepresentacaoNumericaCodBarraSemDigito() {
		return representacaoNumericaCodBarraSemDigito;
	}

	public void setRepresentacaoNumericaCodBarraSemDigito(String representacaoNumericaCodBarraSemDigito) {
		this.representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarraSemDigito;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(String codigoRota) {
		this.codigoRota = codigoRota;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Integer getIdDocumentoCobranca() {
		return idDocumentoCobranca;
	}

	public void setIdDocumentoCobranca(Integer idDocumentoCobranca) {
		this.idDocumentoCobranca = idDocumentoCobranca;
	}

	public String getSequencialRota() {
		return sequencialRota;
	}

	public void setSequencialRota(String sequencialRota) {
		this.sequencialRota = sequencialRota;
	}

	public String getEnderecoCorrespondencia() {
		return enderecoCorrespondencia;
	}

	public void setEnderecoCorrespondencia(String enderecoCorrespondencia) {
		this.enderecoCorrespondencia = enderecoCorrespondencia;
	}

	public String getIcEnderecoAlternativo() {
		return icEnderecoAlternativo;
	}

	public void setIcEnderecoAlternativo(String icEnderecoAlternativo) {
		this.icEnderecoAlternativo = icEnderecoAlternativo;
	}

	public String getRuaComplementoCorrespondencia() {
		return ruaComplementoCorrespondencia;
	}

	public void setRuaComplementoCorrespondencia(String ruaComplementoCorrespondencia) {
		this.ruaComplementoCorrespondencia = ruaComplementoCorrespondencia;
	}

	public String getCidadeEstadoCorrespondencia() {
		return cidadeEstadoCorrespondencia;
	}

	public void setCidadeEstadoCorrespondencia(String cidadeEstadoCorrespondencia) {
		this.cidadeEstadoCorrespondencia = cidadeEstadoCorrespondencia;
	}

	public String getBairroCorrespondencia() {
		return bairroCorrespondencia;
	}

	public void setBairroCorrespondencia(String bairroCorrespondencia) {
		this.bairroCorrespondencia = bairroCorrespondencia;
	}

	public String getCepCorrespondencia() {
		return cepCorrespondencia;
	}

	public void setCepCorrespondencia(String cepCorrespondencia) {
		this.cepCorrespondencia = cepCorrespondencia;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissaoDocumento) {
		this.dataEmissao = dataEmissaoDocumento;
	}
}
