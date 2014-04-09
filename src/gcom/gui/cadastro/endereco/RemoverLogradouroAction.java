package gcom.gui.cadastro.endereco;

import gcom.cadastro.endereco.Logradouro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;

import java.util.Collection;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * remove os logradouro(s) da base
 * 
 * @author compesa
 * @created 20 de Julho de 2005
 */
public class RemoverLogradouroAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;

		// Obtém os ids de remoção
		String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();

		Integer qtIds = ids.length;

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// [UC0107] Registrar Transação
		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_LOGRADOURO_REMOVER);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		Collection<UsuarioAcaoUsuarioHelper> acaoUsuarioHelper = new Vector();
		acaoUsuarioHelper.add(new UsuarioAcaoUsuarioHelper(usuario,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// mensagem de erro quando o usuário tenta excluir sem ter selecionado
		// nenhum
		// registro
		if (ids == null || ids.length == 0) {
			throw new ActionServletException(
					"atencao.registros.nao_selecionados");
		}

		// [UC0107] Registrar Transação
		/*
		 * logradouro.setOperacaoEfetuada(operacaoEfetuada);
		 * logradouro.adicionarUsuario(Usuario.USUARIO_TESTE,
		 * UsuarioAcao.USUARIO_ACAO_TESTE);
		 */
		fachada.removerLogradouro(ids, Logradouro.class.getName(),
				operacaoEfetuada, acaoUsuarioHelper);

		// Monta a página de sucesso
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			montarPaginaSucesso(httpServletRequest, qtIds.toString()
					+ " Logradouro(s) removido(s) com sucesso.",
					"Realizar outra Manutenção de Logradouro",
					"exibirManterLogradouroAction.do");
		}

		return retorno;
	}
}
