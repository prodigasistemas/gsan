package gcom.cobranca.bean;




/**
 * [UC 0653] Pesquisar Comando Negativação
 * 
 * @author Kássia Albuquerque
 * @date 22/11/2007
 */

public class TitularidadeCpfCnpjNegativacaoHelper {
	
	private static final long serialVersionUID = 1L;
	
	
    private String descricaoTipo;
    private Short numOrdemSelecao; 
    private Short indicadorCoincidente;
    
    
    
	
	public TitularidadeCpfCnpjNegativacaoHelper() {
	}

	public TitularidadeCpfCnpjNegativacaoHelper(
			 	String descricaoTipo,
			 	Short numOrdemSelecao, 
			 	Short indicadorCoincidente
			) {
		
		   	this.descricaoTipo=descricaoTipo;
		    this.numOrdemSelecao= numOrdemSelecao;
		    this.indicadorCoincidente = indicadorCoincidente;
		
	}

	/**
	 * @return Retorna o campo descricaoTipo.
	 */
	public String getDescricaoTipo() {
		return descricaoTipo;
	}

	/**
	 * @param descricaoTipo O descricaoTipo a ser setado.
	 */
	public void setDescricaoTipo(String descricaoTipo) {
		this.descricaoTipo = descricaoTipo;
	}

	/**
	 * @return Retorna o campo indicadorCoincidente.
	 */
	public Short getIndicadorCoincidente() {
		return indicadorCoincidente;
	}

	/**
	 * @param indicadorCoincidente O indicadorCoincidente a ser setado.
	 */
	public void setIndicadorCoincidente(Short indicadorCoincidente) {
		this.indicadorCoincidente = indicadorCoincidente;
	}

	/**
	 * @return Retorna o campo numOrdemSelecao.
	 */
	public Short getNumOrdemSelecao() {
		return numOrdemSelecao;
	}

	/**
	 * @param numOrdemSelecao O numOrdemSelecao a ser setado.
	 */
	public void setNumOrdemSelecao(Short numOrdemSelecao) {
		this.numOrdemSelecao = numOrdemSelecao;
	}

   


    
    
    
}
