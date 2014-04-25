package gcom.gui.cobranca.spcserasa;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Yara Taciane de Souza
 * @created 15/05/2008
 */

public class FiltrarNegativadorResultadoSimulacaoActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;

	private String idComando;
	
	private String descricaoComando;

	private String valorDebito;

	private String numeroCpf;

	private String numeroCnpj;

	private String idImovel;

	

	/**
	 * @return Retorna o campo descricaoComando.
	 */
	public String getDescricaoComando() {
		return descricaoComando;
	}

	/**
	 * @param descricaoComando O descricaoComando a ser setado.
	 */
	public void setDescricaoComando(String descricaoComando) {
		this.descricaoComando = descricaoComando;
	}

	/**
	 * @return Retorna o campo idComando.
	 */
	public String getIdComando() {
		return idComando;
	}

	/**
	 * @param idComando O idComando a ser setado.
	 */
	public void setIdComando(String idComando) {
		this.idComando = idComando;
	}

	/**
	 * @return Retorna o campo idImovel.
	 */
	public String getIdImovel() {
		return idImovel;
	}

	/**
	 * @param idImovel
	 *            O idImovel a ser setado.
	 */
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	/**
	 * @return Retorna o campo numeroCnpj.
	 */
	public String getNumeroCnpj() {
		return numeroCnpj;
	}

	/**
	 * @param numeroCnpj
	 *            O numeroCnpj a ser setado.
	 */
	public void setNumeroCnpj(String numeroCnpj) {
		this.numeroCnpj = numeroCnpj;
	}

	/**
	 * @return Retorna o campo numeroCpf.
	 */
	public String getNumeroCpf() {
		return numeroCpf;
	}

	/**
	 * @param numeroCpf
	 *            O numeroCpf a ser setado.
	 */
	public void setNumeroCpf(String numeroCpf) {
		this.numeroCpf = numeroCpf;
	}

	/**
	 * @return Retorna o campo valorDebito.
	 */
	public String getValorDebito() {
		return valorDebito;
	}

	/**
	 * @param valorDebito
	 *            O valorDebito a ser setado.
	 */
	public void setValorDebito(String valorDebito) {
		this.valorDebito = valorDebito;
	}
	
	
	

	public void reset(ActionMapping arg0, ServletRequest arg1) {
		// TODO Auto-generated method stub
		super.reset(arg0, arg1);

		this.idComando = "";
		
		this.descricaoComando = "";

		this.valorDebito = "";

		this.numeroCpf = "";

		this.numeroCnpj = "";

		this.idImovel = "";
	}

}
