package gcom.spcserasa.bean;


/**
 * Classe responsável por ajudar o caso de uso [UC0671] Gerar Movimento de Inclusão Negativação 
 *
 * @author Marcio Roberto
 * @date 21/11/2007
 */
public class NegativacaoMovimentoHelper {

	private Integer idNegativacaoMovimento;

	/**
	 * Construtor  
	 * @param idNegativacaoMovimento
	 */
	public NegativacaoMovimentoHelper( 
			Integer idNegativacaoMovimento
			){
		this.idNegativacaoMovimento = idNegativacaoMovimento;
	}
	/**
	 * Construtor  
	 */
	public NegativacaoMovimentoHelper(){ 
	}
	public Integer getIdNegativacaoMovimento() {
		return idNegativacaoMovimento;
	}
	public void setIdNegativacaoMovimento(Integer idNegativacaoMovimento) {
		this.idNegativacaoMovimento = idNegativacaoMovimento;
	}
	
	
	
}
