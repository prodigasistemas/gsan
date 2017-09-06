package gcom.cobranca.cobrancaporresultado;

import gcom.util.Util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ArquivoTextoNegociacaoCobrancaEmpresaHelper implements Serializable {

	private static final long serialVersionUID = -5644538483971160431L;

	private int tipoNegociacao;
	private Integer idNegociacao;
	private Date dataNegociacao;
	private Date dataVencimentoNegociacao;
	private BigDecimal valorDivida;
	private BigDecimal valorDescontos;
	private BigDecimal valorEntrada;
	private BigDecimal valorParcela;
	private Integer quantidadePrestacoes;
	private Integer idImovel;
	private Integer idCliente;
	private String nomeCliente;
	private String cpfCliente;
	
	private List<Integer> idsContas;

	public ArquivoTextoNegociacaoCobrancaEmpresaHelper() {
	}
	
	public ArquivoTextoNegociacaoCobrancaEmpresaHelper(int tipoNegociacao, Integer idNegociacao, Date dataNegociacao, Date dataVencimentoNegociacao, BigDecimal valorDivida,
			BigDecimal valorDescontos, BigDecimal valorEntrada, BigDecimal valorParcela, Integer quantidadePrestacoes) {
		this.tipoNegociacao = tipoNegociacao;
		this.idNegociacao = idNegociacao;
		this.dataNegociacao = dataNegociacao;
		this.dataVencimentoNegociacao = dataVencimentoNegociacao;
		this.valorDivida = valorDivida;
		this.valorDescontos = valorDescontos;
		this.valorEntrada = valorEntrada;
		this.valorParcela = valorParcela;
		this.quantidadePrestacoes = quantidadePrestacoes;
	}
	
	public int getTipoNegociacao() {
		return tipoNegociacao;
	}

	public void setTipoNegociacao(int tipoNegociacao) {
		this.tipoNegociacao = tipoNegociacao;
	}

	public Integer getIdNegociacao() {
		return idNegociacao;
	}

	public void setIdNegociacao(Integer idNegociacao) {
		this.idNegociacao = idNegociacao;
	}

	public Date getDataNegociacao() {
		return dataNegociacao;
	}

	public void setDataNegociacao(Date dataNegociacao) {
		this.dataNegociacao = dataNegociacao;
	}

	public Date getDataVencimentoNegociacao() {
		return dataVencimentoNegociacao;
	}

	public void setDataVencimentoNegociacao(Date dataVencimentoNegociacao) {
		this.dataVencimentoNegociacao = dataVencimentoNegociacao;
	}

	public BigDecimal getValorDivida() {
		return valorDivida;
	}

	public void setValorDivida(BigDecimal valorDivida) {
		this.valorDivida = valorDivida;
	}

	public BigDecimal getValorDescontos() {
		return valorDescontos;
	}

	public void setValorDescontos(BigDecimal valorDescontos) {
		this.valorDescontos = valorDescontos;
	}

	public BigDecimal getValorEntrada() {
		return valorEntrada;
	}

	public void setValorEntrada(BigDecimal valorEntrada) {
		this.valorEntrada = valorEntrada;
	}

	public BigDecimal getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(BigDecimal valorParcela) {
		this.valorParcela = valorParcela;
	}

	public Integer getQuantidadePrestacoes() {
		return quantidadePrestacoes;
	}

	public void setQuantidadePrestacoes(Integer quantidadePrestacoes) {
		this.quantidadePrestacoes = quantidadePrestacoes;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public List<Integer> getIdsContas() {
		return idsContas;
	}

	public void setIdsContas(List<Integer> idsContas) {
		this.idsContas = idsContas;
	}
	
	public StringBuilder getArquivoTextoNegociacoes() {
		StringBuilder arquivoTxt = new StringBuilder();
		
		arquivoTxt.append(1 + ";");
        arquivoTxt.append(this.tipoNegociacao + ";");
        arquivoTxt.append(this.idNegociacao != null ? this.idNegociacao : "").append(";");
        arquivoTxt.append(this.dataNegociacao != null ? Util.formatarData(this.dataNegociacao) : "").append(";");
        arquivoTxt.append(this.dataVencimentoNegociacao != null ? Util.formatarData(this.dataVencimentoNegociacao) : "").append(";");
        arquivoTxt.append(this.valorDivida != null ? Util.formatarBigDecimalComPonto(this.valorDivida) : "").append(";");
        arquivoTxt.append(this.valorDescontos != null ? Util.formatarBigDecimalComPonto(this.valorDescontos) : "").append(";");
        arquivoTxt.append(this.valorEntrada != null ? Util.formatarBigDecimalComPonto(this.valorEntrada) : "").append(";");
        arquivoTxt.append(this.valorParcela != null ? Util.formatarBigDecimalComPonto(this.valorParcela) : "").append(";");
        arquivoTxt.append(this.quantidadePrestacoes != null ? this.quantidadePrestacoes : "").append(";");
        arquivoTxt.append(this.idImovel != null ? this.idImovel : "").append(";");
        arquivoTxt.append(this.idCliente != null ? this.idCliente : "").append(";");
        arquivoTxt.append(this.nomeCliente != null ? this.nomeCliente : "").append(";");
        arquivoTxt.append(this.cpfCliente != null ? this.cpfCliente : "").append(";");
        arquivoTxt.append(System.getProperty("line.separator"));

        arquivoTxt.append(buildArquivoContasNegociadas());
        
        return arquivoTxt;
    }
	
	private StringBuilder buildArquivoContasNegociadas() {
		StringBuilder arquivoTxt = new StringBuilder();
		
		arquivoTxt.append(2 + ";");
		
		for (Integer idConta : this.idsContas) {
			arquivoTxt.append(idConta + ";");
		}
		
		arquivoTxt.append(System.getProperty("line.separator"));
		
		return arquivoTxt;
	}
	
}
