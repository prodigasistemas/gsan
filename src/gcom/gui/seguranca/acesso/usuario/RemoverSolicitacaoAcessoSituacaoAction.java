package gcom.gui.seguranca.acesso.usuario;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcessoSituacao;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoSituacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.filtro.ParametroSimplesIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RemoverSolicitacaoAcessoSituacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		HttpSession sessao = httpServletRequest.getSession(false);

		String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		Fachada fachada = Fachada.getInstancia();

		// mensagem de erro quando o usuário tenta excluir sem ter selecionado
		// nenhum registro
		if (ids == null || ids.length == 0) {
			throw new ActionServletException(
					"atencao.registros.nao_selecionados");
		}

		FiltroSolicitacaoAcessoSituacao filtroSolicitacaoAcessoSituacao = new FiltroSolicitacaoAcessoSituacao();

		Collection idsSolicitacaoAcessoSituacao = new ArrayList(ids.length);

		for (int i = 0; i < ids.length; i++) {
			idsSolicitacaoAcessoSituacao.add(new Integer(ids[i]));
		}

		filtroSolicitacaoAcessoSituacao
				.adicionarParametro(new ParametroSimplesIn(
						FiltroSolicitacaoAcessoSituacao.ID,
						idsSolicitacaoAcessoSituacao));

		Collection coletionSolicitacaoAcessoSituacao = fachada.pesquisar(
				filtroSolicitacaoAcessoSituacao,
				SolicitacaoAcessoSituacao.class.getName());

		Iterator itera = coletionSolicitacaoAcessoSituacao.iterator();

		while (itera.hasNext()) {

			SolicitacaoAcessoSituacao solicitacaoAcessoSituacao = (SolicitacaoAcessoSituacao) itera
					.next();

			// ------------ REGISTRAR TRANSAÇÃO ----------------
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_SOLICITACAO_ACESSO_SITUACAO_REMOVER,
					solicitacaoAcessoSituacao.getId(),
					solicitacaoAcessoSituacao.getId(),
					new UsuarioAcaoUsuarioHelper(usuarioLogado,
							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
			// ------------ REGISTRAR TRANSAÇÃO ----------------

			registradorOperacao.registrarOperacao(solicitacaoAcessoSituacao);

			fachada.remover(solicitacaoAcessoSituacao);

		}

		Integer idQt = ids.length;

		montarPaginaSucesso(httpServletRequest, idQt.toString()
				+ " Situacao Solicitacao Acesso removido(s) com sucesso.",
				"Realizar outra Manutenção Situacao Solicitacao Acesso",
				"exibirFiltrarSolicitacaoAcessoSituacaoAction.do?menu=sim");
		return retorno;

	}

}
