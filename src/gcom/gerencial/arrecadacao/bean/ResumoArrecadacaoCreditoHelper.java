package gcom.gerencial.arrecadacao.bean;

import java.math.BigDecimal;

/**
 * Classe bean para agrupamento dos historicos 
 * de consumo com as quebras solicitadas
 *  
 * @author Ivan Sérgio
 * @date 16/05/2007
 */

public class ResumoArrecadacaoCreditoHelper {
	private Integer idCreditoOrigem;
	private Integer idLancamentoItemContabil;
	private BigDecimal valorCredito = new BigDecimal(0);
	
	public ResumoArrecadacaoCreditoHelper(Integer idCreditoOrigem, Integer idLancamentoItemContabil) {
		// TODO Auto-generated constructor stub
		this.idCreditoOrigem = idCreditoOrigem;
		this.idLancamentoItemContabil = idLancamentoItemContabil;
	}

	public Integer getIdCreditoOrigem() {
		return idCreditoOrigem;
	}

	public void setIdCreditoOrigem(Integer idCreditoOrigem) {
		this.idCreditoOrigem = idCreditoOrigem;
	}

	public Integer getidLancamentoItemContabil() {
		return idLancamentoItemContabil;
	}

	public void setidLancamentoItemContabil(Integer idLancamentoItemContabil) {
		this.idLancamentoItemContabil = idLancamentoItemContabil;
	}

	public BigDecimal getValorCredito() {
		return valorCredito;
	}

	public void setValorCredito(BigDecimal valorCredito) {
		this.valorCredito = valorCredito;
	}
	
	/**
	 * Compara duas properiedades inteiras, comparando
	 * seus valores para descobrirmos se sao iguais
	 * @param pro1 Primeira propriedade
	 * @param pro2 Segunda propriedade
	 * @return Se iguais, retorna true
	 */
	private boolean propriedadesIguais( Integer pro1, Integer pro2 ){
	  if ( pro2 != null ){
		  if ( !pro2.equals( pro1 ) ){
			  return false;
		  }
	  } else if ( pro1 != null ){
		  return false;
	  }
	  
	  // Se chegou ate aqui quer dizer que as propriedades sao iguais
	  return true;
	}	
	
	
    /**
     * Compara dois objetos levando em consideracao apenas as propriedades
     * que identificam o agrupamento
     * 
     * @param obj Objeto a ser comparado com a instancia deste objeto 
     */
	public boolean equals(Object obj){
		if (!(obj instanceof ResumoArrecadacaoCreditoHelper)){
			return false;			
		} else {
			ResumoArrecadacaoCreditoHelper resumoTemp = (ResumoArrecadacaoCreditoHelper) obj;
		  
		    // Verificamos se todas as propriedades que identificam o objeto sao iguais
			return
				propriedadesIguais(this.idCreditoOrigem, resumoTemp.idCreditoOrigem) &&
				propriedadesIguais(this.idLancamentoItemContabil, resumoTemp.idLancamentoItemContabil);
		}	
	}
}
