package gcom.gui.cobranca.spcserasa;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Classe que representa o form da pagina de resposta  
 * para pesquisa de comandos de negativação por critério
 * 
 * @author: Thiago Vieira
 * @date: 21/1/2007
 */

public class ConsultarComandoNegativacaoTipoMatriculaActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	private String idNegativador;
	private String identificacaoCI;
	private String tipoPesquisaIdentificacaoCI;
	private String idUsuarioResponsavel;
	private String nomeUsuarioResponsavel;
	private String usuarioOk;
	
	public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
		
		this.idNegativador = "";
		this.identificacaoCI = "";
		this.tipoPesquisaIdentificacaoCI = "";
		this.idUsuarioResponsavel = "";
		this.nomeUsuarioResponsavel = "";
		this.usuarioOk = "";
    }

	/**
	 * @return Retorna o campo identificacaoCI.
	 */
	public String getIdentificacaoCI() {
		return identificacaoCI;
	}

	/**
	 * @param identificacaoCI O identificacaoCI a ser setado.
	 */
	public void setIdentificacaoCI(String identificacaoCI) {
		this.identificacaoCI = identificacaoCI;
	}

	/**
	 * @return Retorna o campo idNegativador.
	 */
	public String getIdNegativador() {
		return idNegativador;
	}

	/**
	 * @param idNegativador O idNegativador a ser setado.
	 */
	public void setIdNegativador(String idNegativador) {
		this.idNegativador = idNegativador;
	}

	/**
	 * @return Retorna o campo idUsuarioResponsavel.
	 */
	public String getIdUsuarioResponsavel() {
		return idUsuarioResponsavel;
	}

	/**
	 * @param idUsuarioResponsavel O idUsuarioResponsavel a ser setado.
	 */
	public void setIdUsuarioResponsavel(String idUsuarioResponsavel) {
		this.idUsuarioResponsavel = idUsuarioResponsavel;
	}

	/**
	 * @return Retorna o campo tipoPesquisaIdentificacaoCI.
	 */
	public String getTipoPesquisaIdentificacaoCI() {
		return tipoPesquisaIdentificacaoCI;
	}

	/**
	 * @param tipoPesquisaIdentificacaoCI O tipoPesquisaIdentificacaoCI a ser setado.
	 */
	public void setTipoPesquisaIdentificacaoCI(String tipoPesquisaIdentificacaoCI) {
		this.tipoPesquisaIdentificacaoCI = tipoPesquisaIdentificacaoCI;
	}

	/**
	 * @return Retorna o campo nomeUsuarioResponsavel.
	 */
	public String getNomeUsuarioResponsavel() {
		return nomeUsuarioResponsavel;
	}

	/**
	 * @param nomeUsuarioResponsavel O nomeUsuarioResponsavel a ser setado.
	 */
	public void setNomeUsuarioResponsavel(String nomeUsuarioResponsavel) {
		this.nomeUsuarioResponsavel = nomeUsuarioResponsavel;
	}

	/**
	 * @return Retorna o campo usuarioOk.
	 */
	public String getUsuarioOk() {
		return usuarioOk;
	}

	/**
	 * @param usuarioOk O usuarioOk a ser setado.
	 */
	public void setUsuarioOk(String usuarioOk) {
		this.usuarioOk = usuarioOk;
	}

			
}
