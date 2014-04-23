package gcom.gui.cobranca.spcserasa;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ConsultarDadosInclusoesNegativacaoComandoActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String idSelecionado;
	
	private String negativador;
	
	private String quantidadeInclusoes;
	
	private String valorTotalDebito;
	
	private String quantidadeItensIncluidos;
	
	private Collection collNegativadorMovimentoReg;

    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    	
    	this.idSelecionado = "";
    	this.negativador = "";
    	this.quantidadeInclusoes = "";
    	this.valorTotalDebito = "";
    	this.quantidadeItensIncluidos = "";
    	this.collNegativadorMovimentoReg = new ArrayList();
    }

	/**
	 * @return Retorna o campo idSelecionado.
	 */
	public String getIdSelecionado() {
		return idSelecionado;
	}

	/**
	 * @param idSelecionado O idSelecionado a ser setado.
	 */
	public void setIdSelecionado(String idSelecionado) {
		this.idSelecionado = idSelecionado;
	}

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
	 * @return Retorna o campo quantidadeInclusoes.
	 */
	public String getQuantidadeInclusoes() {
		return quantidadeInclusoes;
	}

	/**
	 * @param quantidadeInclusoes O quantidadeInclusoes a ser setado.
	 */
	public void setQuantidadeInclusoes(String quantidadeInclusoes) {
		this.quantidadeInclusoes = quantidadeInclusoes;
	}

	/**
	 * @return Retorna o campo quantidadeItensIncluidos.
	 */
	public String getQuantidadeItensIncluidos() {
		return quantidadeItensIncluidos;
	}

	/**
	 * @param quantidadeItensIncluidos O quantidadeItensIncluidos a ser setado.
	 */
	public void setQuantidadeItensIncluidos(String quantidadeItensIncluidos) {
		this.quantidadeItensIncluidos = quantidadeItensIncluidos;
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

	/**
	 * @return Retorna o campo collNegativadorMovimentoReg.
	 */
	public Collection getCollNegativadorMovimentoReg() {
		return collNegativadorMovimentoReg;
	}

	/**
	 * @param collNegativadorMovimentoReg O collNegativadorMovimentoReg a ser setado.
	 */
	public void setCollNegativadorMovimentoReg(
			Collection collNegativadorMovimentoReg) {
		this.collNegativadorMovimentoReg = collNegativadorMovimentoReg;
	}


}
