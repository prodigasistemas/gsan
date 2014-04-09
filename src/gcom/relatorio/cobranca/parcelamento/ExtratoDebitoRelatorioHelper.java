package gcom.relatorio.cobranca.parcelamento;

import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.CobrancaDocumentoItem;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.debito.DebitoACobrar;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;

public class ExtratoDebitoRelatorioHelper {
	
	private Collection<CobrancaDocumentoItem>  colecaoCobrancaDocumentoItemContas;
	
	private Collection<CobrancaDocumentoItem>  colecaoCobrancaDocumentoItemGuiasPagamento;
	
	private Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemDebitosACobrar;
	
	private Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemCreditoARealizar;

    
    /** Campo adicionado para utilização no caso de uso [UC0251] */
    private CobrancaDocumento documentoCobranca ;
	
	public CobrancaDocumento getDocumentoCobranca() {
        return documentoCobranca;
    }

    public void setDocumentoCobranca(CobrancaDocumento documentoCobranca) {
        this.documentoCobranca = documentoCobranca;
    }
    /** **********************************************************/


    public ExtratoDebitoRelatorioHelper() {
	}
	

	
	public ExtratoDebitoRelatorioHelper(Collection<CobrancaDocumentoItem>  colecaoCobrancaDocumentoItemContas, 
			Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemGuiasPagamento, 
			Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemDebitosACobrar,
			Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemCreditoARealizar) {
		
		this.colecaoCobrancaDocumentoItemContas = colecaoCobrancaDocumentoItemContas;
		this.colecaoCobrancaDocumentoItemGuiasPagamento = colecaoCobrancaDocumentoItemGuiasPagamento;
		this.colecaoCobrancaDocumentoItemDebitosACobrar = colecaoCobrancaDocumentoItemDebitosACobrar; 
		this.colecaoCobrancaDocumentoItemCreditoARealizar = colecaoCobrancaDocumentoItemCreditoARealizar;
		
	}

	public Collection<CobrancaDocumentoItem> getColecaoCobrancaDocumentoItemDebitosACobrar() {
		return colecaoCobrancaDocumentoItemDebitosACobrar;
	}

	public void setColecaoCobrancaDocumentoItemDebitosACobrar(
			Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemDebitosACobrar) {
		this.colecaoCobrancaDocumentoItemDebitosACobrar = colecaoCobrancaDocumentoItemDebitosACobrar;
	}

	public Collection<CobrancaDocumentoItem> getColecaoCobrancaDocumentoItemGuiasPagamento() {
		return colecaoCobrancaDocumentoItemGuiasPagamento;
	}

	public void setColecaoCobrancaDocumentoItemGuiasPagamento(
			Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemGuiasPagamento) {
		this.colecaoCobrancaDocumentoItemGuiasPagamento = colecaoCobrancaDocumentoItemGuiasPagamento;
	}

	public Collection<CobrancaDocumentoItem> getColecaoCobrancaDocumentoItemContas() {
		return colecaoCobrancaDocumentoItemContas;
	}

	public void setColecaoCobrancaDocumentoItemContas(
			Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemContas) {
		this.colecaoCobrancaDocumentoItemContas = colecaoCobrancaDocumentoItemContas;
	}

	 /*
     * Cálcula o valor total da conta (água + esgoto + débitos - creditos)
     * @author Vivianne Sousa
     * @created 12/09/2006
     */
    public BigDecimal getValorTotalConta(){
		
		BigDecimal retorno = new BigDecimal("0.00");
		
		Iterator iterator = this.getColecaoCobrancaDocumentoItemContas().iterator();
		
		while (iterator.hasNext()) {
			Conta conta = ((CobrancaDocumentoItem) iterator.next()).getContaGeral().getConta();
			
			if (conta != null){
				
				if (conta.getValorTotal() != null) {
					retorno = retorno.add(conta.getValorTotal());
				}
			}
			
		}
		
		retorno = retorno.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
		
		return retorno ;
	}
    
    /*
     * Cálcula o valor total das guias de pagamento(GPAG_VLDEBITO)
     * @author Vivianne Sousa
     * @created 12/09/2006
     */
    public BigDecimal getValorTotalGuiaPagamento(){
		
		BigDecimal retorno = new BigDecimal("0.00");
		
		Iterator iterator = this.getColecaoCobrancaDocumentoItemGuiasPagamento().iterator();
		
		while (iterator.hasNext()) {
			GuiaPagamento guiaPagamento = ((CobrancaDocumentoItem) iterator.next()).getGuiaPagamentoGeral().getGuiaPagamento();
			
			if (guiaPagamento.getValorDebito() != null){
				retorno = retorno.add(guiaPagamento.getValorDebito());
			}
		}
		
		retorno = retorno.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
		
		return retorno ;
	}
    
    
    /*
     * Calcula o valor total restante dos debitos a cobrar 
     * (DBAC_VLDEBITO - ((DBAC_VLDEBITO/DBAC_NNPRESTAODEBITO) * (DBAC_NNPRESTACAOCOBRADA + dbac_nnparcelabonus)))
     * @author Vivianne Sousa
     * @created 12/09/2006
     */
    public BigDecimal getValorTotalRestanteDebitosACobrar(){
		
		BigDecimal retorno = new BigDecimal("0.00");
		
		Iterator iterator = this.getColecaoCobrancaDocumentoItemDebitosACobrar().iterator();
		
		while (iterator.hasNext()) {
			
			
			CobrancaDocumentoItem cobrancaDocumentoItem = (CobrancaDocumentoItem) iterator.next();
			
			DebitoACobrar debitoACobrar = cobrancaDocumentoItem.getDebitoACobrarGeral().getDebitoACobrar();
			
			//ANTECIPAÇÃO DE PARCELAS
			if (cobrancaDocumentoItem.getNumeroParcelasAntecipadas() != null){
				retorno = retorno.add(cobrancaDocumentoItem.getValorItemCobrado());
			}
			else{
				retorno = retorno.add(debitoACobrar.getValorTotalComBonus());
			}
		}
		
		retorno = retorno.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
		
		return retorno ;
	}

	/**
	 * @return Retorna o campo colecaoCobrancaDocumentoItemCreditoARealizar.
	 */
	public Collection<CobrancaDocumentoItem> getColecaoCobrancaDocumentoItemCreditoARealizar() {
		return colecaoCobrancaDocumentoItemCreditoARealizar;
	}

	/**
	 * @param colecaoCobrancaDocumentoItemCreditoARealizar O colecaoCobrancaDocumentoItemCreditoARealizar a ser setado.
	 */
	public void setColecaoCobrancaDocumentoItemCreditoARealizar(
			Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemCreditoARealizar) {
		this.colecaoCobrancaDocumentoItemCreditoARealizar = colecaoCobrancaDocumentoItemCreditoARealizar;
	}   
	
}
