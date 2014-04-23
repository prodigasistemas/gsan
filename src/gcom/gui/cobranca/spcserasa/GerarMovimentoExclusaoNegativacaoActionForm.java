package gcom.gui.cobranca.spcserasa;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Esta classe tem por finalidade gerar o formulário que receberá os parâmetros
 * para realização da inserção de um Geração da Movimentacao da Negativacao
 * 
 * @author Thiago Toscano 
 * @date 18/12/2006
 */
public class GerarMovimentoExclusaoNegativacaoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String opcao;
	private String[] negativadores;
	private Collection collNegativadores;

	private String codigoMovimento;
	private String descricaoMovimento;
	private Collection collMovimento;
	
	/**
	 * <Breve descrição sobre o caso de uso>
	 *
	 * <Identificador e nome do caso de uso>
	 *
	 * <Breve descrição sobre o subfluxo>
	 *
	 * <Identificador e nome do subfluxo>	
	 *
	 * <Breve descrição sobre o fluxo secundário>
	 *
	 * <Identificador e nome do fluxo secundário> 
	 *
	 * @author thiago
	 * @date 19/12/2007
	 *
	 * @param arg0
	 * @param arg1
	 * @return
	 */
	@Override
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		super.reset(arg0, arg1);

		
		opcao = "";
		negativadores = new String[0];
		collNegativadores = new ArrayList();

		codigoMovimento = "";
		descricaoMovimento = "";		
		collMovimento= new ArrayList();

	}
	
	/**
	 * @return Retorna o campo collMovimento.
	 */
	public Collection getCollMovimento() {
		return collMovimento;
	}

	/**
	 * @param collMovimento O collMovimento a ser setado.
	 */
	public void setCollMovimento(Collection collMovimento) {
		this.collMovimento = collMovimento;
	}


	/**
	 * @return Retorna o campo codigoMovimento.
	 */
	public String getCodigoMovimento() {
		return codigoMovimento;
	}
	/**
	 * @param codigoMovimento O codigoMovimento a ser setado.
	 */
	public void setCodigoMovimento(String codigoMovimento) {
		this.codigoMovimento = codigoMovimento;
	}
	/**
	 * @return Retorna o campo collNegativadores.
	 */
	public Collection getCollNegativadores() {
		return collNegativadores;
	}
	/**
	 * @param collNegativadores O collNegativadores a ser setado.
	 */
	public void setCollNegativadores(Collection collNegativadores) {
		this.collNegativadores = collNegativadores;
	}
	/**
	 * @return Retorna o campo descricaoMovimento.
	 */
	public String getDescricaoMovimento() {
		return descricaoMovimento;
	}
	/**
	 * @param descricaoMovimento O descricaoMovimento a ser setado.
	 */
	public void setDescricaoMovimento(String descricaoMovimento) {
		this.descricaoMovimento = descricaoMovimento;
	}
	/**
	 * @return Retorna o campo negativadores.
	 */
	public String[] getNegativadores() {
		return negativadores;
	}
	/**
	 * @param negativadores O negativadores a ser setado.
	 */
	public void setNegativadores(String[] negativadores) {
		this.negativadores = negativadores;
	}
	/**
	 * @return Retorna o campo opcao.
	 */
	public String getOpcao() {
		return opcao;
	}
	/**
	 * @param opcao O opcao a ser setado.
	 */
	public void setOpcao(String opcao) {
		this.opcao = opcao;
	}
}
