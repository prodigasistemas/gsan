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

/** Gerar e Emitir o Relatório de Débito
 * @author Adriana Muniz
 * @date 14 /12/2011
 * Classe utilizada no relatório de débitos
 */
public class RelatorioDebitoHelper {
	
	private Collection colecaoContas;
	
	private Collection colecaoGuiasPagamento;
	
	private Collection colecaoDebitosACobrar;
	
	private Collection colecaoCreditoARealizar;
	
	private Integer numeroParcelasAntecipadasDebitos;
	
	private Integer numeroParcelasAntecipadasCreditos;
	
	public RelatorioDebitoHelper() {
	}

	public RelatorioDebitoHelper(Collection colecaoContas,
			Collection colecaoGuiasPagamento, Collection colecaoDebitosACobrar,
			Collection colecaoCreditoARealizar) {
		super();
		this.colecaoContas = colecaoContas;
		this.colecaoGuiasPagamento = colecaoGuiasPagamento;
		this.colecaoDebitosACobrar = colecaoDebitosACobrar;
		this.colecaoCreditoARealizar = colecaoCreditoARealizar;
	}

	public RelatorioDebitoHelper(Collection colecaoContas,
			Collection colecaoGuiasPagamento, Collection colecaoDebitosACobrar,
			Collection colecaoCreditoARealizar,
			int numeroParcelasAntecipadasDebitos,
			int numeroParcelasAntecipadasCreditos) {
		super();
		this.colecaoContas = colecaoContas;
		this.colecaoGuiasPagamento = colecaoGuiasPagamento;
		this.colecaoDebitosACobrar = colecaoDebitosACobrar;
		this.colecaoCreditoARealizar = colecaoCreditoARealizar;
		this.numeroParcelasAntecipadasDebitos = numeroParcelasAntecipadasDebitos;
		this.numeroParcelasAntecipadasCreditos = numeroParcelasAntecipadasCreditos;
	}

	public Collection getColecaoContas() {
		return colecaoContas;
	}

	public void setColecaoContas(Collection colecaoContas) {
		this.colecaoContas = colecaoContas;
	}

	public Collection getColecaoGuiasPagamento() {
		return colecaoGuiasPagamento;
	}

	public void setColecaoGuiasPagamento(Collection colecaoGuiasPagamento) {
		this.colecaoGuiasPagamento = colecaoGuiasPagamento;
	}

	public Collection getColecaoDebitosACobrar() {
		return colecaoDebitosACobrar;
	}

	public void setColecaoDebitosACobrar(Collection colecaoDebitosACobrar) {
		this.colecaoDebitosACobrar = colecaoDebitosACobrar;
	}

	public Collection getColecaoCreditoARealizar() {
		return colecaoCreditoARealizar;
	}

	public void setColecaoCreditoARealizar(Collection colecaoCreditoARealizar) {
		this.colecaoCreditoARealizar = colecaoCreditoARealizar;
	}
	
    public BigDecimal getValorTotalConta(){
		
		BigDecimal retorno = new BigDecimal("0.00");
		
		Iterator iterator = this.getColecaoContas().iterator();
		
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

	public int getNumeroParcelasAntecipadasDebitos() {
		return numeroParcelasAntecipadasDebitos;
	}

	public void setNumeroParcelasAntecipadasDebitos(
			Integer numeroParcelasAntecipadasDebitos) {
		this.numeroParcelasAntecipadasDebitos = numeroParcelasAntecipadasDebitos;
	}

	public int getNumeroParcelasAntecipadasCreditos() {
		return numeroParcelasAntecipadasCreditos;
	}

	public void setNumeroParcelasAntecipadasCreditos(
			Integer numeroParcelasAntecipadasCreditos) {
		this.numeroParcelasAntecipadasCreditos = numeroParcelasAntecipadasCreditos;
	}

/*	 
     * Cálcula o valor total da conta (água + esgoto + débitos - creditos)
     * @author Vivianne Sousa
     * @created 12/09/2006
     
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
    
    
     * Cálcula o valor total das guias de pagamento(GPAG_VLDEBITO)
     * @author Vivianne Sousa
     * @created 12/09/2006
     
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
    
    
    
     * Calcula o valor total restante dos debitos a cobrar 
     * (DBAC_VLDEBITO - ((DBAC_VLDEBITO/DBAC_NNPRESTAODEBITO) * (DBAC_NNPRESTACAOCOBRADA + dbac_nnparcelabonus)))
     * @author Vivianne Sousa
     * @created 12/09/2006
     
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
	}*/
	
}
