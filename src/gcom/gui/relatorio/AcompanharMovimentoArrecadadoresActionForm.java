package gcom.gui.relatorio;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0217] Inserir Resolução de Diretoria
 * @author Rafael Corrêa
 * @since 30/03/2006
 */
public class AcompanharMovimentoArrecadadoresActionForm  extends ActionForm {

	private static final long serialVersionUID = 1L;

	
	private String mesAnoReferencia;
	
	private String idArrecadador;
	
	private String nomeArrecadador;
	
	private String idFormaArrecadacao;
	
	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}

	/**
	 * @return Retorna o campo idArrecadador.
	 */
	public String getIdArrecadador() {
		return idArrecadador;
	}

	/**
	 * @param idArrecadador O idArrecadador a ser setado.
	 */
	public void setIdArrecadador(String idArrecadador) {
		this.idArrecadador = idArrecadador;
	}

	/**
	 * @return Retorna o campo idFormaArrecadacao.
	 */
	public String getIdFormaArrecadacao() {
		return idFormaArrecadacao;
	}

	/**
	 * @param idFormaArrecadacao O idFormaArrecadacao a ser setado.
	 */
	public void setIdFormaArrecadacao(String idFormaArrecadacao) {
		this.idFormaArrecadacao = idFormaArrecadacao;
	}

	/**
	 * @return Retorna o campo mesAnoReferencia.
	 */
	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}

	/**
	 * @param mesAnoReferencia O mesAnoReferencia a ser setado.
	 */
	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}

	/**
	 * @return Retorna o campo nomeArrecadador.
	 */
	public String getNomeArrecadador() {
		return nomeArrecadador;
	}

	/**
	 * @param nomeArrecadador O nomeArrecadador a ser setado.
	 */
	public void setNomeArrecadador(String nomeArrecadador) {
		this.nomeArrecadador = nomeArrecadador;
	}
	
	
}
