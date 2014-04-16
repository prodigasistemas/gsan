package gcom.cobranca.bean;

import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;


/**
 * Débitos de um imóvel ou de um cliente
 * @author Rafael Santos
 * @since 04/01/2006
 */
public class ObterDebitoImovelOuClienteHelper {
	
	/**
	 * Contas em Débito e Valores de:
	 * Valor Pago
	 * Valor da multa
	 * Valor dos juros de mora
	 * Valor da atualização monetária	 
	 * */
	private Collection<ContaValoresHelper> colecaoContasValores;
	
	/**
	 * Contas em Débito e Valores Para exibição na aba de Imóvel de:
	 * Valor Pago
	 * Valor da multa
	 * Valor dos juros de mora
	 * Valor da atualização monetária	 
	 * */
	private Collection<ContaValoresHelper> colecaoContasValoresImovel;

	/**
	 * Coleção de Debitos a Cobrar
	 */
	private Collection<DebitoACobrar> colecaoDebitoACobrar;
	/**
	 * Coleção de Creditos a Realizar
	 */
	private Collection<CreditoARealizar> colecaoCreditoARealizar;
	
	/**
	 * Guias de Pagamento e Valores de:
	 * Valor Pago
	 * Valor da multa
	 * Valor dos juros de mora
	 * Valor da atualização monetária	 
	 * */
	private Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamentoValores;
	
	
	//----------------------------------------------------------
	//[UC0630] - Solicitar Emissão do Extrato de Débitos
	//Vivianne Sousa - 21/08/2007
	/**
	 * Coleção de Debitos/Creditos do Parcelamento
	 */
	private Collection<DebitoCreditoParcelamentoHelper> colecaoDebitoCreditoParcelamentoHelper;

	//----------------------------------------------------------
	

	/*
	 * Coleção de Notas Promissorias?
	 */
	//private Collection<NotaPromissoria> colecaoNotasPromissorias;

	/**
	 * Constructor
	 */
	public ObterDebitoImovelOuClienteHelper() {
		
	}
	
	/**
	 * @param colecaoContasValores
	 * @param colecaoDebitoACobrar
	 * @param colecaoCreditoARealizar
	 * @param colecaoGuiasPagamentoValores
	 */
	public ObterDebitoImovelOuClienteHelper(Collection<ContaValoresHelper> colecaoContasValores, Collection<ContaValoresHelper> colecaoContasValoresImovel,
		Collection<DebitoACobrar> colecaoDebitoACobrar, Collection<CreditoARealizar> colecaoCreditoARealizar, Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamentoValores) {
		this.colecaoContasValores = colecaoContasValores;
		this.colecaoContasValoresImovel = colecaoContasValoresImovel;
		this.colecaoDebitoACobrar = colecaoDebitoACobrar;
		this.colecaoCreditoARealizar = colecaoCreditoARealizar;
		this.colecaoGuiasPagamentoValores = colecaoGuiasPagamentoValores;
	}

	public ObterDebitoImovelOuClienteHelper(Collection<ContaValoresHelper> colecaoContasValores, Collection<DebitoACobrar> colecaoDebitoACobrar, Collection<CreditoARealizar> colecaoCreditoARealizar, Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamentoValores) {
		this.colecaoContasValores = colecaoContasValores;
		this.colecaoDebitoACobrar = colecaoDebitoACobrar;
		this.colecaoCreditoARealizar = colecaoCreditoARealizar;
		this.colecaoGuiasPagamentoValores = colecaoGuiasPagamentoValores;
	}

	public ObterDebitoImovelOuClienteHelper(Collection<ContaValoresHelper> colecaoContasValores,
			Collection<ContaValoresHelper> colecaoContasValoresImovel,
			Collection<DebitoACobrar> colecaoDebitoACobrar, 
			Collection<CreditoARealizar> colecaoCreditoARealizar, 
			Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamentoValores,
			Collection<DebitoCreditoParcelamentoHelper> colecaoDebitoCreditoParcelamentoHelper) {
		
		this.colecaoContasValores = colecaoContasValores;
		this.colecaoContasValoresImovel = colecaoContasValoresImovel;
		this.colecaoDebitoACobrar = colecaoDebitoACobrar;
		this.colecaoCreditoARealizar = colecaoCreditoARealizar;
		this.colecaoGuiasPagamentoValores = colecaoGuiasPagamentoValores;
		this.colecaoDebitoCreditoParcelamentoHelper = colecaoDebitoCreditoParcelamentoHelper;
	}

	
	/**
	 * @return Returns the colecaoContasValores.
	 */
	public Collection<ContaValoresHelper> getColecaoContasValores() {
		return colecaoContasValores;
	}

	/**
	 * @param colecaoContasValores The colecaoContasValores to set.
	 */
	public void setColecaoContasValores(
			Collection<ContaValoresHelper> colecaoContasValores) {
		this.colecaoContasValores = colecaoContasValores;
	}

	/**
	 * @return Returns the colecaoCreditoARealizar.
	 */
	public Collection<CreditoARealizar> getColecaoCreditoARealizar() {
		return colecaoCreditoARealizar;
	}

	/**
	 * @param colecaoCreditoARealizar The colecaoCreditoARealizar to set.
	 */
	public void setColecaoCreditoARealizar(
			Collection<CreditoARealizar> colecaoCreditoARealizar) {
		this.colecaoCreditoARealizar = colecaoCreditoARealizar;
	}

	/**
	 * @return Returns the colecaoDebitoACobrar.
	 */
	public Collection<DebitoACobrar> getColecaoDebitoACobrar() {
		return colecaoDebitoACobrar;
	}

	/**
	 * @param colecaoDebitoACobrar The colecaoDebitoACobrar to set.
	 */
	public void setColecaoDebitoACobrar(
			Collection<DebitoACobrar> colecaoDebitoACobrar) {
		this.colecaoDebitoACobrar = colecaoDebitoACobrar;
	}

	/**
	 * @return Returns the colecaoGuiasPagamentoValores.
	 */
	public Collection<GuiaPagamentoValoresHelper> getColecaoGuiasPagamentoValores() {
		return colecaoGuiasPagamentoValores;
	}

	/**
	 * @param colecaoGuiasPagamentoValores The colecaoGuiasPagamentoValores to set.
	 */
	public void setColecaoGuiasPagamentoValores(
			Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamentoValores) {
		this.colecaoGuiasPagamentoValores = colecaoGuiasPagamentoValores;
	}

	/**
	 * @return Retorna o campo colecaoContasValoresImovel.
	 */
	public Collection<ContaValoresHelper> getColecaoContasValoresImovel() {
		return colecaoContasValoresImovel;
	}

	/**
	 * @param colecaoContasValoresImovel O colecaoContasValoresImovel a ser setado.
	 */
	public void setColecaoContasValoresImovel(
			Collection<ContaValoresHelper> colecaoContasValoresImovel) {
		this.colecaoContasValoresImovel = colecaoContasValoresImovel;
	}

	public Collection<DebitoCreditoParcelamentoHelper> getColecaoDebitoCreditoParcelamentoHelper() {
		return colecaoDebitoCreditoParcelamentoHelper;
	}

	public void setColecaoDebitoCreditoParcelamentoHelper(
			Collection<DebitoCreditoParcelamentoHelper> colecaoDebitoCreditoParcelamentoHelper) {
		this.colecaoDebitoCreditoParcelamentoHelper = colecaoDebitoCreditoParcelamentoHelper;
	}

	public BigDecimal obterValorImpostosDasContas(Collection colecaoContas){
		
		BigDecimal valorTotalImpostos = BigDecimal.ZERO;
		
		if (colecaoContas != null && !colecaoContas.isEmpty()) {
			Iterator contas = colecaoContas.iterator();

			while (contas.hasNext()) {
				ContaValoresHelper contaValoresHelper = (ContaValoresHelper) contas.next();
				
				if (contaValoresHelper.getConta().getValorImposto() != null) {
					valorTotalImpostos = valorTotalImpostos.add(contaValoresHelper.getConta().getValorImposto());
				}
			}
		}
		return valorTotalImpostos;
	}
}
