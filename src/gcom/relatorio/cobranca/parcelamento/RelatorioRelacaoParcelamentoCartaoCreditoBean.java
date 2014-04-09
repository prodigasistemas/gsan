package gcom.relatorio.cobranca.parcelamento;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;
/**
 * [UC0998] Gerar Relação de Parcelamento - Visão Cartão de Crédito
 * 
 * Bean que preencherá o relatorio
 * 
 * @author Hugo Amorim
 * @date 11/06/2010
 *
 */
public class RelatorioRelacaoParcelamentoCartaoCreditoBean implements RelatorioBean {
	
	private String nome;
	private String matricula;
	private String dataPrimeiroVencimento;
	private BigDecimal valorParcela;
	private BigDecimal valorDebito;
	private String dataParcelamento;
	private String dataConfirmacaoCartaoCredito;
	private String usuarioConfirmacaoCartaoCredito;
	private String dataConfirmacaoCartaoCreditoOperadora;
	private String idGerencia;
	private String idLocalidade;
	private String idMunicipio;
	private String descricaoGerencia;
	private String descricaoLocalidade;
	private String descricaoMunicipio;
	private String quantidadeParcelas;
	private BigDecimal valorConfirmado;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getDataPrimeiroVencimento() {
		return dataPrimeiroVencimento;
	}
	public void setDataPrimeiroVencimento(String dataPrimeiroVencimento) {
		this.dataPrimeiroVencimento = dataPrimeiroVencimento;
	}
	public BigDecimal getValorParcela() {
		return valorParcela;
	}
	public void setValorParcela(BigDecimal valorParcela) {
		this.valorParcela = valorParcela;
	}
	public String getDataParcelamento() {
		return dataParcelamento;
	}
	public void setDataParcelamento(String dataParcelamento) {
		this.dataParcelamento = dataParcelamento;
	}
	public String getDataConfirmacaoCartaoCredito() {
		return dataConfirmacaoCartaoCredito;
	}
	public void setDataConfirmacaoCartaoCredito(String dataConfirmacaoCartaoCredito) {
		this.dataConfirmacaoCartaoCredito = dataConfirmacaoCartaoCredito;
	}
	public String getIdGerencia() {
		return idGerencia;
	}
	public void setIdGerencia(String idGerencia) {
		this.idGerencia = idGerencia;
	}
	public String getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public String getDescricaoGerencia() {
		return descricaoGerencia;
	}
	public void setDescricaoGerencia(String descricaoGerencia) {
		this.descricaoGerencia = descricaoGerencia;
	}
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}
	public BigDecimal getValorDebito() {
		return valorDebito;
	}
	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
	}
	public String getQuantidadeParcelas() {
		return quantidadeParcelas;
	}
	public void setQuantidadeParcelas(String quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}
	public String getUsuarioConfirmacaoCartaoCredito() {
		return usuarioConfirmacaoCartaoCredito;
	}
	public void setUsuarioConfirmacaoCartaoCredito(
			String usuarioConfirmacaoCartaoCredito) {
		this.usuarioConfirmacaoCartaoCredito = usuarioConfirmacaoCartaoCredito;
	}
	public String getDataConfirmacaoCartaoCreditoOperadora() {
		return dataConfirmacaoCartaoCreditoOperadora;
	}
	public void setDataConfirmacaoCartaoCreditoOperadora(
			String dataConfirmacaoCartaoCreditoOperadora) {
		this.dataConfirmacaoCartaoCreditoOperadora = dataConfirmacaoCartaoCreditoOperadora;
	}
	public BigDecimal getValorConfirmado() {
		return valorConfirmado;
	}
	public void setValorConfirmado(BigDecimal valorConfirmado) {
		this.valorConfirmado = valorConfirmado;
	}
	public String getIdMunicipio() {
		return idMunicipio;
	}
	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	public String getDescricaoMunicipio() {
		return descricaoMunicipio;
	}
	public void setDescricaoMunicipio(String descricaoMunicipio) {
		this.descricaoMunicipio = descricaoMunicipio;
	}
}
