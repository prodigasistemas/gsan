package gcom.gui.cadastro.atualizacaocadastral;

import gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.FachadaException;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarImoveisEmLoteAction extends GcomAction {

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession sessao = request.getSession(false);
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> imoveis = (Collection<ConsultarMovimentoAtualizacaoCadastralHelper>) sessao.getAttribute("colecaoConsultarMovimentoAtualizacaoCadastralHelper");

		try {
			getFachada().aprovarImoveisEmLote(usuario, imoveis);
		} catch (FachadaException e) {
			throw new ActionServletException("erro.aprovacao.lote", "exibirFiltrarAlteracaoAtualizacaoCadastralAction.do", null, new String[] {});
		}

		montarPaginaSucesso(request, imoveis.size() + " imóveis foram aprovados com sucesso.", 
				"Consultar Movimento", 
				"exibirFiltrarAlteracaoAtualizacaoCadastralAction.do?menu=sim");
		
		return mapping.findForward("telaSucesso");

	}

}
