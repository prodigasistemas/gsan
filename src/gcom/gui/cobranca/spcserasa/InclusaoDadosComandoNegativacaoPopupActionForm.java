package gcom.gui.cobranca.spcserasa;

import org.apache.struts.action.ActionForm;



/**
 * [UC 0653] Pesquisar Comando Negativação
 * 
 * @author Kássia Albuquerque
 * @date 14/11/2007
 */


public class InclusaoDadosComandoNegativacaoPopupActionForm extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	
	private String negativador;
	private String qtdInclusoes;
	private String valorTotalDebito;
	private String qtdItensIncluidos;
	
	/**
	 * @return Retorna o campo negativador.
	 */
	public String getNegativador() {
		return negativador;
	}
	/**
	 * @param negativador O negativador a ser setado.
	 */
	public void setNegativador(String negativador) {
		this.negativador = negativador;
	}
	/**
	 * @return Retorna o campo qtdInclusoes.
	 */
	public String getQtdInclusoes() {
		return qtdInclusoes;
	}
	/**
	 * @param qtdInclusoes O qtdInclusoes a ser setado.
	 */
	public void setQtdInclusoes(String qtdInclusoes) {
		this.qtdInclusoes = qtdInclusoes;
	}
	/**
	 * @return Retorna o campo qtdItensIncluidos.
	 */
	public String getQtdItensIncluidos() {
		return qtdItensIncluidos;
	}
	/**
	 * @param qtdItensIncluidos O qtdItensIncluidos a ser setado.
	 */
	public void setQtdItensIncluidos(String qtdItensIncluidos) {
		this.qtdItensIncluidos = qtdItensIncluidos;
	}
	/**
	 * @return Retorna o campo valorTotalDebito.
	 */
	public String getValorTotalDebito() {
		return valorTotalDebito;
	}
	/**
	 * @param valorTotalDebito O valorTotalDebito a ser setado.
	 */
	public void setValorTotalDebito(String valorTotalDebito) {
		this.valorTotalDebito = valorTotalDebito;
	}
	
	
	
	
	
	
    
}
