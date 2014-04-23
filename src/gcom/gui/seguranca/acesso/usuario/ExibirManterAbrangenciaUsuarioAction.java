package gcom.gui.seguranca.acesso.usuario;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroAbrangenciaUsuario;
import gcom.seguranca.acesso.usuario.UsuarioAbrangencia;

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
public class ExibirManterAbrangenciaUsuarioAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("manterAbrangenciaUsuario");

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroAbrangenciaUsuario filtroAbrangenciaUsuario = new FiltroAbrangenciaUsuario();
		filtroAbrangenciaUsuario.adicionarCaminhoParaCarregamentoEntidade("usuarioAbrangenciaSuperior");

		// Aciona o controle de paginação para que sejam pesquisados apenas
		// os registros que aparecem na página
		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroAbrangenciaUsuario, UsuarioAbrangencia.class.getName());
		Collection<UsuarioAbrangencia> colecaoAbrangenciaUsuario = (Collection) resultado
				.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");

		if (colecaoAbrangenciaUsuario == null || colecaoAbrangenciaUsuario.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela Situacao Usuario");

		}

		sessao.setAttribute("colecaoAbrangenciaUsuario", colecaoAbrangenciaUsuario);

		return retorno;
	}
}
