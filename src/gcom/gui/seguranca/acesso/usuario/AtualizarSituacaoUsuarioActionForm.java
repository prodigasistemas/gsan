package gcom.gui.seguranca.acesso.usuario;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0218] Manter Situacao Usuario
 * @author Thiago Tenório
 * @since 10/04/2006
 */
public class AtualizarSituacaoUsuarioActionForm  extends ActionForm {
	private String id;
	private static final long serialVersionUID = 1L;
	private String descricaoUsuarioSituacao;
	
	private String descricaoAbreviada;
	
	private String indicadorUso;
	
	private String indicadorUsoSistema;
	
	
	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}

	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public String getDescricaoUsuarioSituacao() {
		return descricaoUsuarioSituacao;
	}

	public void setDescricaoUsuarioSituacao(String descricaoUsuarioSituacao) {
		this.descricaoUsuarioSituacao = descricaoUsuarioSituacao;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getIndicadorUsoSistema() {
		return indicadorUsoSistema;
	}

	public void setIndicadorUsoSistema(String indicadorUsoSistema) {
		this.indicadorUsoSistema = indicadorUsoSistema;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}

