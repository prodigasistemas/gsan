package gcom.gui.seguranca.acesso.usuario;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuarioSituacao;
import gcom.seguranca.acesso.usuario.UsuarioSituacao;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Thiago Tenório
 * @date 04/04/2006
 */
public class ExibirManterSituacaoUsuarioAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("manterSituacaoUsuario");

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroUsuarioSituacao filtroUsuarioSituacao = new FiltroUsuarioSituacao();

		// Aciona o controle de paginação para que sejam pesquisados apenas
		// os registros que aparecem na página
		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroUsuarioSituacao, UsuarioSituacao.class.getName());
		Collection<UsuarioSituacao> colecaoSituacaoUsuario = (Collection) resultado
				.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");

		if (colecaoSituacaoUsuario == null || colecaoSituacaoUsuario.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela Situacao Usuario");

		}

		sessao.setAttribute("colecaoSituacaoUsuario", colecaoSituacaoUsuario);

		return retorno;
	}
}
