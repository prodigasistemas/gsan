package gcom.cobranca.bean;

import gcom.cobranca.parcelamento.Parcelamento;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * [UC0630] - Solicitar Emissão do Extrato de Débitos
 * @author Vivianne Sousa
 * @since 21/08/2007
 */
public class DebitoCreditoParcelamentoHelper {

	private Parcelamento parcelamento;
	/**
	 * Coleção de Debitos a Cobrar do Parcelamento
	 */
	private Collection<DebitoACobrar> colecaoDebitoACobrarParcelamento;
	/**
	 * Coleção de Creditos a Realizar do Parcelamento
	 */
	private Collection<CreditoARealizar> colecaoCreditoARealizarParcelamento;
	
	private BigDecimal valorTotalDebito;
	
	private BigDecimal valorTotalCredito;
	
	private Short numeroPrestacaoCobradas;
	
	private boolean antecipacaoParcela;
	
	private Integer quantidadeAntecipacaoParcelas;
	
	public Collection<CreditoARealizar> getColecaoCreditoARealizarParcelamento() {
		return colecaoCreditoARealizarParcelamento;
	}

	public void setColecaoCreditoARealizarParcelamento(
			Collection<CreditoARealizar> colecaoCreditoARealizarParcelamento) {
		this.colecaoCreditoARealizarParcelamento = colecaoCreditoARealizarParcelamento;
	}

	public Collection<DebitoACobrar> getColecaoDebitoACobrarParcelamento() {
		return colecaoDebitoACobrarParcelamento;
	}

	public void setColecaoDebitoACobrarParcelamento(
			Collection<DebitoACobrar> colecaoDebitoACobrarParcelamento) {
		this.colecaoDebitoACobrarParcelamento = colecaoDebitoACobrarParcelamento;
	}

	public Parcelamento getParcelamento() {
		return parcelamento;
	}

	public void setParcelamento(Parcelamento parcelamento) {
		this.parcelamento = parcelamento;
	}

	public BigDecimal getValorTotalCredito() {
		return valorTotalCredito;
	}

	public void setValorTotalCredito(BigDecimal valorTotalCredito) {
		this.valorTotalCredito = valorTotalCredito;
	}

	public BigDecimal getValorTotalDebito() {
		return valorTotalDebito;
	}

	public void setValorTotalDebito(BigDecimal valorTotalDebito) {
		this.valorTotalDebito = valorTotalDebito;
	}
	
	
	public BigDecimal getValorTotal() {
		
		BigDecimal valorTotal = BigDecimal.ZERO;
		BigDecimal valorTotalDebito = BigDecimal.ZERO;
		BigDecimal valorTotalCredito = BigDecimal.ZERO;
		
		if(this.valorTotalDebito != null){
			valorTotalDebito = this.valorTotalDebito;
		}
		
		if(this.valorTotalCredito != null){
			valorTotalCredito = this.valorTotalCredito;
		}
		
		valorTotal = valorTotalDebito.subtract(valorTotalCredito);
		
		return valorTotal;
	}

	public Short getNumeroPrestacaoCobradas() {
		return numeroPrestacaoCobradas;
	}

	public void setNumeroPrestacaoCobradas(Short numeroPrestacaoCobradas) {
		this.numeroPrestacaoCobradas = numeroPrestacaoCobradas;
	}
	
	public String getPrestacaoFormatada(){
        String prestacaoFormatada = "";
        
        if(getParcelamento() != null && getNumeroPrestacaoCobradas() != null){
            prestacaoFormatada = getNumeroPrestacaoCobradas() + " / " 
            + getParcelamento().getNumeroPrestacoes().toString();
        }
        
        return  prestacaoFormatada ;
    }
	
	public short getNumeroPrestacaoRestante() {
        
		short retorno = getParcelamento().getNumeroPrestacoes();
        
        if (getNumeroPrestacaoCobradas() != null){
            retorno = Short.parseShort(""+ (retorno - getNumeroPrestacaoCobradas().shortValue()));
        }
             
        return retorno;
    }

	public boolean isAntecipacaoParcela() {
		return antecipacaoParcela;
	}

	public void setAntecipacaoParcela(boolean antecipacaoParcela) {
		this.antecipacaoParcela = antecipacaoParcela;
	}

	public Integer getQuantidadeAntecipacaoParcelas() {
		return quantidadeAntecipacaoParcelas;
	}

	public void setQuantidadeAntecipacaoParcelas(
			Integer quantidadeAntecipacaoParcelas) {
		this.quantidadeAntecipacaoParcelas = quantidadeAntecipacaoParcelas;
	}
	
	public BigDecimal getValorAntecipacaoParcela(){
    	
    	BigDecimal valorAntecipacaoParcela = BigDecimal.ZERO;
    	
    	if (this.getParcelamento() != null && this.getQuantidadeAntecipacaoParcelas() != null){
    		
    		BigDecimal valorJaPago = BigDecimal.ZERO;
    		
    		if (this.getNumeroPrestacaoCobradas() != null && this.getNumeroPrestacaoCobradas().intValue() > 0){
    			valorJaPago = this.getParcelamento().getValorPrestacao().multiply(BigDecimal.valueOf(this.getNumeroPrestacaoCobradas()));
    		}
    		
    		BigDecimal valorNaoPagoComJuros = this.getParcelamento().getValorParcelado().subtract(valorJaPago);
    		
    		BigDecimal valorJurosPorParcela =  this.getParcelamento().getValorJurosParcelamento().divide(
    		BigDecimal.valueOf(this.getParcelamento().getNumeroPrestacoes()),2,BigDecimal.ROUND_DOWN);
    		
    		int qtdPrestacoesRestantes = this.getParcelamento().getNumeroPrestacoes().intValue() - this.getNumeroPrestacaoCobradas().intValue();
    		
    		BigDecimal valorJurosNaoPago = valorJurosPorParcela.multiply(BigDecimal.valueOf(qtdPrestacoesRestantes));
    		
    		BigDecimal valorRestanteParcelamentoSemJuros = valorNaoPagoComJuros.subtract(valorJurosNaoPago);
    		
    		BigDecimal valorParcelaSemJuros = valorRestanteParcelamentoSemJuros.divide(new BigDecimal(qtdPrestacoesRestantes),2,BigDecimal.ROUND_DOWN);
    		
    		valorAntecipacaoParcela = valorParcelaSemJuros.multiply(new BigDecimal(this.getQuantidadeAntecipacaoParcelas().intValue()));
    	}
    	
    	return valorAntecipacaoParcela;
    }
}
