package gcom.relatorio.cobranca;

import java.util.List;

public class AvisoCorteDTO {

	private Integer imovel;
	private String cliente;
	private String inscricao;

	private Short codigoRota;
	private Integer sequencialRota;

	private Integer idDocumentoCobranca;
	private String dataEmissao;
	private String valorTotal;

	private String endereco;

	private String enderecoCorrespondencia;
	private String complementoCorrespondencia;
	private String cidadeEstadoCorrespondencia;
	private String bairroCorrespondencia;
	private String cepCorrespondencia;

	private String codigoBarras;
	private String codigoBarrasFormatado;

	private List<CobrancaDocumentoContaDTO> contas;

	public AvisoCorteDTO() {
		super();
	}

	public Integer getImovel() {
		return imovel;
	}

	public void setImovel(Integer imovel) {
		this.imovel = imovel;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public Short getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(Short codigoRota) {
		this.codigoRota = codigoRota;
	}

	public Integer getSequencialRota() {
		return sequencialRota;
	}

	public void setSequencialRota(Integer sequencialRota) {
		this.sequencialRota = sequencialRota;
	}

	public Integer getIdDocumentoCobranca() {
		return idDocumentoCobranca;
	}

	public void setIdDocumentoCobranca(Integer idDocumentoCobranca) {
		this.idDocumentoCobranca = idDocumentoCobranca;
	}

	public String getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEnderecoCorrespondencia() {
		return enderecoCorrespondencia;
	}

	public void setEnderecoCorrespondencia(String enderecoCorrespondencia) {
		this.enderecoCorrespondencia = enderecoCorrespondencia;
	}

	public String getComplementoCorrespondencia() {
		return complementoCorrespondencia;
	}

	public void setComplementoCorrespondencia(String complementoCorrespondencia) {
		this.complementoCorrespondencia = complementoCorrespondencia;
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

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getCodigoBarrasFormatado() {
		return codigoBarrasFormatado;
	}

	public void setCodigoBarrasFormatado(String codigoBarrasFormatado) {
		this.codigoBarrasFormatado = codigoBarrasFormatado;
	}

	public List<CobrancaDocumentoContaDTO> getContas() {
		return contas;
	}

	public void setContas(List<CobrancaDocumentoContaDTO> contas) {
		this.contas = contas;
	}
}