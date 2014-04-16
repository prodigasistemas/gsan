package gcom.gui.seguranca.acesso.usuario;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0298] Manter Abrangencia Usuario
 * @author Thiago Tenório
 * @since 10/04/2006
 */
public class AtualizarAbrangenciaUsuarioActionForm  extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String id;
	
	private String descricao;
	
	private String descricaoAbreviada;
	
	private String indicadorUso;
	
	private Integer usuarioAbrangenciaSuperior;
	
	
	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		id = null;
		descricao = null;
		descricaoAbreviada = null;
		indicadorUso = null;
		usuarioAbrangenciaSuperior = null;
		
	}

	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public String getDescricaoUsuarioSituacao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Integer getUsuarioAbrangenciaSuperior() {
		return usuarioAbrangenciaSuperior;
	}

	public void setUsuarioAbrangenciaSuperior(Integer indicadorUsoSistema) {
		this.usuarioAbrangenciaSuperior = indicadorUsoSistema;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}
}

