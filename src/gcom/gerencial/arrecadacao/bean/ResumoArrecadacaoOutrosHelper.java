package gcom.gerencial.arrecadacao.bean;

import java.math.BigDecimal;

/**
 * Classe bean para agrupamento dos historicos 
 * de consumo com as quebras solicitadas
 *  
 * @author Ivan Sérgio
 * @date 16/05/2007
 */

public class ResumoArrecadacaoOutrosHelper {
	
	private Integer idTipoFinanciamento;
	private Integer idLancamentoItemContabil;
	private BigDecimal valorDebitos = new BigDecimal(0);
	
	public ResumoArrecadacaoOutrosHelper(Integer idTipoFinanciamento, Integer idLancamentoItemContabil) {
		
		this.idTipoFinanciamento = idTipoFinanciamento;
		this.idLancamentoItemContabil = idLancamentoItemContabil;
	}

	public Integer getIdLancamentoItemContabil() {
		return idLancamentoItemContabil;
	}

	public void setIdLancamentoItemContabil(Integer idLancamentoItemContabil) {
		this.idLancamentoItemContabil = idLancamentoItemContabil;
	}

	public Integer getIdTipoFinanciamento() {
		return idTipoFinanciamento;
	}

	public void setIdTipoFinanciamento(Integer idTipoFinanciamento) {
		this.idTipoFinanciamento = idTipoFinanciamento;
	}

	public BigDecimal getValorDebitos() {
		return valorDebitos;
	}

	public void setValorDebitos(BigDecimal valorDebitos) {
		this.valorDebitos = valorDebitos;
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
		if (!(obj instanceof ResumoArrecadacaoOutrosHelper)){
			return false;			
		} else {
			ResumoArrecadacaoOutrosHelper resumoTemp = (ResumoArrecadacaoOutrosHelper) obj;
		  
		    // Verificamos se todas as propriedades que identificam o objeto sao iguais
			return
				propriedadesIguais(this.idTipoFinanciamento, resumoTemp.idTipoFinanciamento) &&
				propriedadesIguais(this.idLancamentoItemContabil, resumoTemp.idLancamentoItemContabil);
		}	
	}
}
