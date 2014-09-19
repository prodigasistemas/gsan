package gcom.gerencial.faturamento.bean;

import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * Classe responsável por ajudar o caso de uso Gerar Resumo Faturamento Agua Esgoto 
 *
 * @author Marcio Roberto
 * @date 06/07/2007
 */
public class ResumoFaturamentoCreditosSetores {

	private Integer idSetorComercial;


    /**
     * Compara dois objetos levando em consideracao apenas as propriedades
     * que identificam o agrupamento
     * 
     * @param obj Objeto a ser comparado com a instancia deste objeto 
     */
	public boolean equals(Object obj) {
        if ((this == obj)) {
            return true;
        }
        if (!(obj instanceof ResumoFaturamentoCreditosSetores)) {
            return false;
        }
        ResumoFaturamentoCreditosSetores resumoTemp = (ResumoFaturamentoCreditosSetores) obj;

        return new EqualsBuilder()
        .append(this.getIdSetorComercial(), resumoTemp.getIdSetorComercial())
        .isEquals();
    }	
	
	/**
	 * @return Retorna o campo idSetorComercial.
	 */
	public Integer getIdSetorComercial() {
		return idSetorComercial;
	}

	/**
	 * @param idSetorComercial O idSetorComercial a ser setado.
	 */
	public void setIdSetorComercial(Integer idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	public int hashCode() {
		String retorno =  
		this.getIdSetorComercial() + "sdf";
		return retorno.hashCode();
	}
	
	public ResumoFaturamentoCreditosSetores(Integer idSetorComercial) {
		super();
		
		
		this.idSetorComercial = idSetorComercial;
	}
}
