package gcom.gui.portal;

import gcom.cobranca.bean.ContaValoresHelper;

import java.util.Collection;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UCXXXX] Efetuar Parcelamento de Débitos Através da Loja Virtual
 * 
 * Form responsável por armazenar os dados para exibir na tela
 * a tela parcelamento_debitos_portal_efetuar.jsp e para executar
 * o parcelamento através do da action EfetuarParcelamentoDebitosPortalAction.java 
 * 
 * @author Diogo Peixoto
 * @since 28/06/2011
 */

public class EfetuarParcelamentoDebitosPortalActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	private String cpfCliente;
	private String enderecoImovel;
	private Collection<ContaValoresHelper> colecaoContaValores;
	private String matriculaImovel;
	private String inscricaoImovel;
	private String nomeCliente;
	private String dataParcelamento;
	private String indicadorRestabelecimento;
	private String indicadorContasRevisao;
	private String indicadorGuiasPagamento;
	private String indicadorAcrescimosImpotualidade;
	private String indicadorDebitosACobrar;
	private String indicadorCreditoARealizar;
	private String indicadorDividaAtiva;
	
	private String valorTotalContaValores;
	private String valorGuiasPagamento;
	private String valorAcrescimosImpontualidade;
	private String valorAtualizacaoMonetaria;
	private String valorJurosMora;
	private String valorMulta;
	private String valorDebitoACobrarServico;
	private String valorDebitoACobrarParcelamento;
	private String valorDebitoACobrarServicoLongoPrazo;
	private String valorDebitoACobrarServicoCurtoPrazo;
	private String valorDebitoACobrarParcelamentoLongoPrazo;
	private String valorDebitoACobrarParcelamentoCurtoPrazo;
	private String valorCreditoARealizar;
	private String valorDebitoTotalAtualizado;
	
	private String indicadorQuantidadeParcelas;
	private String valorEntradaInformado;
	private String descontoAcrescimosImpontualidade;
	private String descontoAntiguidadeDebito;
	private String descontoInatividadeLigacaoAgua;
	private String percentualDescontoAcrescimosImpontualidade;
	private String valorTotalDescontos;
	private String descontoSancoesRDEspecial;
	private String descontoTarifaSocialRDEspecial;
	private String valorDescontoPagamentoAVista;
	private String valorDesconto;
	private String valorPagamentoAVista;
	private String valorTotalImpostos;
	private String percentualDescontoAntiguidadeDebito;
	private String percentualDescontoInatividadeLigacaoAgua;
	private String parcelamentoPerfilId;
	private String valorMinimoPrestacao;
	
	private String resolucaoDiretoria;
	
	protected void limparForm(){
		this.cpfCliente = null;
		this.colecaoContaValores = null;
		this.valorEntradaInformado = null;
	}
	
	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public String getEnderecoImovel() {
		return enderecoImovel;
	}

	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}

	public Collection<ContaValoresHelper> getColecaoContaValores() {
		return colecaoContaValores;
	}

	public void setColecaoContaValores(
			Collection<ContaValoresHelper> colecaoContaValores) {
		this.colecaoContaValores = colecaoContaValores;
	}

	public String getValorTotalContaValores() {
		return valorTotalContaValores;
	}

	public void setValorTotalContaValores(String valorTotalContaValores) {
		this.valorTotalContaValores = valorTotalContaValores;
	}

	public String getValorGuiasPagamento() {
		return valorGuiasPagamento;
	}

	public void setValorGuiasPagamento(String valorGuiasPagamento) {
		this.valorGuiasPagamento = valorGuiasPagamento;
	}

	public String getValorAcrescimosImpontualidade() {
		return valorAcrescimosImpontualidade;
	}

	public void setValorAcrescimosImpontualidade(
			String valorAcrescimosImpontualidade) {
		this.valorAcrescimosImpontualidade = valorAcrescimosImpontualidade;
	}

	public String getValorAtualizacaoMonetaria() {
		return valorAtualizacaoMonetaria;
	}

	public void setValorAtualizacaoMonetaria(String valorAtualizacaoMonetaria) {
		this.valorAtualizacaoMonetaria = valorAtualizacaoMonetaria;
	}

	public String getValorJurosMora() {
		return valorJurosMora;
	}

	public void setValorJurosMora(String valorJurosMora) {
		this.valorJurosMora = valorJurosMora;
	}

	public String getValorMulta() {
		return valorMulta;
	}

	public void setValorMulta(String valorMulta) {
		this.valorMulta = valorMulta;
	}

	public String getValorDebitoACobrarServico() {
		return valorDebitoACobrarServico;
	}

	public void setValorDebitoACobrarServico(String valorDebitoACobrarServico) {
		this.valorDebitoACobrarServico = valorDebitoACobrarServico;
	}

	public String getValorDebitoACobrarParcelamento() {
		return valorDebitoACobrarParcelamento;
	}

	public void setValorDebitoACobrarParcelamento(
			String valorDebitoACobrarParcelamento) {
		this.valorDebitoACobrarParcelamento = valorDebitoACobrarParcelamento;
	}

	public String getValorDebitoACobrarServicoLongoPrazo() {
		return valorDebitoACobrarServicoLongoPrazo;
	}

	public void setValorDebitoACobrarServicoLongoPrazo(
			String valorDebitoACobrarServicoLongoPrazo) {
		this.valorDebitoACobrarServicoLongoPrazo = valorDebitoACobrarServicoLongoPrazo;
	}

	public String getValorDebitoACobrarServicoCurtoPrazo() {
		return valorDebitoACobrarServicoCurtoPrazo;
	}

	public void setValorDebitoACobrarServicoCurtoPrazo(
			String valorDebitoACobrarServicoCurtoPrazo) {
		this.valorDebitoACobrarServicoCurtoPrazo = valorDebitoACobrarServicoCurtoPrazo;
	}

	public String getValorDebitoACobrarParcelamentoLongoPrazo() {
		return valorDebitoACobrarParcelamentoLongoPrazo;
	}

	public void setValorDebitoACobrarParcelamentoLongoPrazo(
			String valorDebitoACobrarParcelamentoLongoPrazo) {
		this.valorDebitoACobrarParcelamentoLongoPrazo = valorDebitoACobrarParcelamentoLongoPrazo;
	}

	public String getValorDebitoACobrarParcelamentoCurtoPrazo() {
		return valorDebitoACobrarParcelamentoCurtoPrazo;
	}

	public void setValorDebitoACobrarParcelamentoCurtoPrazo(
			String valorDebitoACobrarParcelamentoCurtoPrazo) {
		this.valorDebitoACobrarParcelamentoCurtoPrazo = valorDebitoACobrarParcelamentoCurtoPrazo;
	}

	public String getValorCreditoARealizar() {
		return valorCreditoARealizar;
	}

	public void setValorCreditoARealizar(String valorCreditoARealizar) {
		this.valorCreditoARealizar = valorCreditoARealizar;
	}

	public String getValorDebitoTotalAtualizado() {
		return valorDebitoTotalAtualizado;
	}

	public void setValorDebitoTotalAtualizado(String valorDebitoTotalAtualizado) {
		this.valorDebitoTotalAtualizado = valorDebitoTotalAtualizado;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getIndicadorQuantidadeParcelas() {
		return indicadorQuantidadeParcelas;
	}

	public void setIndicadorQuantidadeParcelas(String indicadorQuantidadeParcelas) {
		this.indicadorQuantidadeParcelas = indicadorQuantidadeParcelas;
	}

	public String getValorEntradaInformado() {
		return valorEntradaInformado;
	}

	public void setValorEntradaInformado(String valorEntradaInformado) {
		this.valorEntradaInformado = valorEntradaInformado;
	}

	public String getDescontoAcrescimosImpontualidade() {
		return descontoAcrescimosImpontualidade;
	}

	public void setDescontoAcrescimosImpontualidade(
			String descontoAcrescimosImpontualidade) {
		this.descontoAcrescimosImpontualidade = descontoAcrescimosImpontualidade;
	}

	public String getDescontoAntiguidadeDebito() {
		return descontoAntiguidadeDebito;
	}

	public void setDescontoAntiguidadeDebito(String descontoAntiguidadeDebito) {
		this.descontoAntiguidadeDebito = descontoAntiguidadeDebito;
	}

	public String getDescontoInatividadeLigacaoAgua() {
		return descontoInatividadeLigacaoAgua;
	}

	public void setDescontoInatividadeLigacaoAgua(
			String descontoInatividadeLigacaoAgua) {
		this.descontoInatividadeLigacaoAgua = descontoInatividadeLigacaoAgua;
	}

	public String getPercentualDescontoAcrescimosImpontualidade() {
		return percentualDescontoAcrescimosImpontualidade;
	}

	public void setPercentualDescontoAcrescimosImpontualidade(
			String percentualDescontoAcrescimosImpontualidade) {
		this.percentualDescontoAcrescimosImpontualidade = percentualDescontoAcrescimosImpontualidade;
	}

	public String getValorTotalDescontos() {
		return valorTotalDescontos;
	}

	public void setValorTotalDescontos(String valorTotalDescontos) {
		this.valorTotalDescontos = valorTotalDescontos;
	}

	public String getDescontoSancoesRDEspecial() {
		return descontoSancoesRDEspecial;
	}

	public void setDescontoSancoesRDEspecial(String descontoSancoesRDEspecial) {
		this.descontoSancoesRDEspecial = descontoSancoesRDEspecial;
	}

	public String getDescontoTarifaSocialRDEspecial() {
		return descontoTarifaSocialRDEspecial;
	}

	public void setDescontoTarifaSocialRDEspecial(
			String descontoTarifaSocialRDEspecial) {
		this.descontoTarifaSocialRDEspecial = descontoTarifaSocialRDEspecial;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getValorDescontoPagamentoAVista() {
		return valorDescontoPagamentoAVista;
	}

	public void setValorDescontoPagamentoAVista(String valorDescontoPagamentoAVista) {
		this.valorDescontoPagamentoAVista = valorDescontoPagamentoAVista;
	}

	public String getValorPagamentoAVista() {
		return valorPagamentoAVista;
	}

	public void setValorPagamentoAVista(String valorPagamentoAVista) {
		this.valorPagamentoAVista = valorPagamentoAVista;
	}

	public String getValorTotalImpostos() {
		return valorTotalImpostos;
	}

	public void setValorTotalImpostos(String valorTotalImpostos) {
		this.valorTotalImpostos = valorTotalImpostos;
	}

	public String getPercentualDescontoAntiguidadeDebito() {
		return percentualDescontoAntiguidadeDebito;
	}

	public void setPercentualDescontoAntiguidadeDebito(
			String percentualDescontoAntiguidadeDebito) {
		this.percentualDescontoAntiguidadeDebito = percentualDescontoAntiguidadeDebito;
	}

	public String getPercentualDescontoInatividadeLigacaoAgua() {
		return percentualDescontoInatividadeLigacaoAgua;
	}

	public void setPercentualDescontoInatividadeLigacaoAgua(
			String percentualDescontoInatividadeLigacaoAgua) {
		this.percentualDescontoInatividadeLigacaoAgua = percentualDescontoInatividadeLigacaoAgua;
	}

	public String getParcelamentoPerfilId() {
		return parcelamentoPerfilId;
	}

	public void setParcelamentoPerfilId(String parcelamentoPerfilId) {
		this.parcelamentoPerfilId = parcelamentoPerfilId;
	}

	public String getValorMinimoPrestacao() {
		return valorMinimoPrestacao;
	}

	public void setValorMinimoPrestacao(String valorMinimoPrestacao) {
		this.valorMinimoPrestacao = valorMinimoPrestacao;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getDataParcelamento() {
		return dataParcelamento;
	}

	public void setDataParcelamento(String dataParcelamento) {
		this.dataParcelamento = dataParcelamento;
	}

	public String getIndicadorRestabelecimento() {
		return indicadorRestabelecimento;
	}

	public void setIndicadorRestabelecimento(String indicadorRestabelecimento) {
		this.indicadorRestabelecimento = indicadorRestabelecimento;
	}

	public String getIndicadorContasRevisao() {
		return indicadorContasRevisao;
	}

	public void setIndicadorContasRevisao(String indicadorContasRevisao) {
		this.indicadorContasRevisao = indicadorContasRevisao;
	}

	public String getIndicadorGuiasPagamento() {
		return indicadorGuiasPagamento;
	}

	public void setIndicadorGuiasPagamento(String indicadorGuiasPagamento) {
		this.indicadorGuiasPagamento = indicadorGuiasPagamento;
	}

	public String getIndicadorAcrescimosImpotualidade() {
		return indicadorAcrescimosImpotualidade;
	}

	public void setIndicadorAcrescimosImpotualidade(String indicadorAcrescimosImpotualidade) {
		this.indicadorAcrescimosImpotualidade = indicadorAcrescimosImpotualidade;
	}

	public String getIndicadorDebitosACobrar() {
		return indicadorDebitosACobrar;
	}

	public void setIndicadorDebitosACobrar(String indicadorDebitosACobrar) {
		this.indicadorDebitosACobrar = indicadorDebitosACobrar;
	}

	public String getIndicadorCreditoARealizar() {
		return indicadorCreditoARealizar;
	}

	public void setIndicadorCreditoARealizar(String indicadorCreditoARealizar) {
		this.indicadorCreditoARealizar = indicadorCreditoARealizar;
	}

	public String getIndicadorDividaAtiva() {
		return indicadorDividaAtiva;
	}

	public void setIndicadorDividaAtiva(String indicadorDividaAtiva) {
		this.indicadorDividaAtiva = indicadorDividaAtiva;
	}

	public String getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(String valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public String getResolucaoDiretoria() {
		return resolucaoDiretoria;
	}

	public void setResolucaoDiretoria(String resolucaoDiretoria) {
		this.resolucaoDiretoria = resolucaoDiretoria;
	}
}