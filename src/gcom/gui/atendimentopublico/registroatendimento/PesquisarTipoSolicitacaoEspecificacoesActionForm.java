package gcom.gui.atendimentopublico.registroatendimento;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author Thiago Tenório
 * @created 28 de Junho de 2004
 */
public class PesquisarTipoSolicitacaoEspecificacoesActionForm extends
		ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String descricao;

	private String solicitacaoTipoGrupo;

	private String indicadorFaltaAgua;

	/**
	 * @return Retorna o campo descricaoTipoSolicitacao.
	 */
	public String getDescricaoTipoSolicitacao() {
		return descricao;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		descricao = null;
		solicitacaoTipoGrupo = null;
		indicadorFaltaAgua = null;

	}

	/**
	 * @return Retorna o campo solicitacaoTipoGrupo.
	 */
	public String getSolicitacaoTipoGrupo() {
		return solicitacaoTipoGrupo;
	}

	/**
	 * @param solicitacaoTipoGrupo
	 *            O solicitacaoTipoGrupo a ser setado.
	 */
	public void setSolicitacaoTipoGrupo(String solicitacaoTipoGrupo) {
		this.solicitacaoTipoGrupo = solicitacaoTipoGrupo;
	}

	/**
	 * @return Retorna o campo indicadorFaltaAgua.
	 */
	public String getIndicadorFaltaAgua() {
		return indicadorFaltaAgua;
	}

	/**
	 * @param indicadorFaltaAgua
	 *            O indicadorFaltaAgua a ser setado.
	 */
	public void setIndicadorFaltaAgua(String indicadorFaltaAgua) {
		this.indicadorFaltaAgua = indicadorFaltaAgua;
	}

	/**
	 * @return Retorna o campo descricao.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            O descricao a ser setado.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
