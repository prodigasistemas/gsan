package gcom.gui.cobranca;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0243] Inserir Comando de Ação de Cobrança - Tipo de Comando Cronograma 
 * @author Rafael Santos
 * @since 24/01/2006
 */
public class InserirComandoAcaoCobrancaCronogramaActionForm  extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String referenciaCobranca;
	private String cobrancaGrupo;
	private String cobrancaAcao;
	private String cobrancaAtividade;
	private String rotaInicial;

	private String rotaFinal;
	
	
	
	public String getRotaFinal() {
		return rotaFinal;
	}

	public void setRotaFinal(String rotaFinal) {
		this.rotaFinal = rotaFinal;
	}

	public String getRotaInicial() {
		return rotaInicial;
	}

	public void setRotaInicial(String rotaInicial) {
		this.rotaInicial = rotaInicial;
	}

	/**
	 * @return Returns the acaoCobranca.
	 */
	public String getCobrancaAcao() {
		return cobrancaAcao;
	}

	/**
	 * @param acaoCobranca The acaoCobranca to set.
	 */
	public void setCobrancaAcao(String acaoCobranca) {
		this.cobrancaAcao = acaoCobranca;
	}

	/**
	 * @return Returns the atividadeCobranca.
	 */
	public String getCobrancaAtividade() {
		return cobrancaAtividade;
	}

	/**
	 * @param atividadeCobranca The atividadeCobranca to set.
	 */
	public void setCobrancaAtividade(String atividadeCobranca) {
		this.cobrancaAtividade = atividadeCobranca;
	}

	/**
	 * @return Returns the grupoCobranca.
	 */
	public String getCobrancaGrupo() {
		return cobrancaGrupo;
	}

	/**
	 * @param grupoCobranca The grupoCobranca to set.
	 */
	public void setCobrancaGrupo(String grupoCobranca) {
		this.cobrancaGrupo = grupoCobranca;
	}

	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {

		referenciaCobranca = null;
		cobrancaGrupo = null;
		cobrancaAcao = null;
		cobrancaAtividade = null;
		
	}

	/**
	 * @return Returns the referenciaCobranca.
	 */
	public String getReferenciaCobranca() {
		return referenciaCobranca;
	}

	/**
	 * @param referenciaCobranca The referenciaCobranca to set.
	 */
	public void setReferenciaCobranca(String referenciaCobranca) {
		this.referenciaCobranca = referenciaCobranca;
	}

}
