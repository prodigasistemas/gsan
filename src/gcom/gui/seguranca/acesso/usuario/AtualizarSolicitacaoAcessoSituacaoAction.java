package gcom.gui.seguranca.acesso.usuario;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoSituacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <<Atualizar Solicitação Acesso Situação>>
 * 
 * @author: Wallace Thierre
 * @Data: 08/11/2010
 * 
 */

public class AtualizarSolicitacaoAcessoSituacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		AtualizarSolicitacaoAcessoSituacaoActionForm form = (AtualizarSolicitacaoAcessoSituacaoActionForm) actionForm;

		SolicitacaoAcessoSituacao solicitacaoAcessoSituacao = (SolicitacaoAcessoSituacao) sessao
				.getAttribute("atualizarSolicitacaoAcessoSituacao");

		// Atualizar o objeto com os dados do form.
		solicitacaoAcessoSituacao.setDescricao(form.getDescricao());
		solicitacaoAcessoSituacao.setIndicadorUso(new Short(form
				.getIndicadorUso()));
		solicitacaoAcessoSituacao.setCodigoSituacao(new Short(form
				.getCodigoSituacao()));

		// ------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_SOLICITACAO_ACESSO_SITUACAO_ATUALIZAR,
				solicitacaoAcessoSituacao.getId(),
				solicitacaoAcessoSituacao.getId(),
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		registradorOperacao.registrarOperacao(solicitacaoAcessoSituacao);
		// ------------ REGISTRAR TRANSAÇÃO ----------------

		fachada.atualizar(solicitacaoAcessoSituacao);

		montarPaginaSucesso(httpServletRequest, "Situacao Solicitacao Acesso "
				+ form.getDescricao() + " atualizada com sucesso.",
				"Realizar outra Manutenção Situacao Solicitacao Acesso",
				"exibirFiltrarSolicitacaoAcessoSituacaoAction.do?menu=sim");

		return retorno;
	}

}
