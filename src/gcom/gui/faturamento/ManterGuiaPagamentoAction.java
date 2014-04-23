package gcom.gui.faturamento;

import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelCobrancaSituacao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ManterGuiaPagamentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		Collection guiasPagamento = (Collection) sessao
				.getAttribute("guiasPagamentos");

		ManterGuiaPagamentoActionForm manterGuiaPagamentoActionForm = (ManterGuiaPagamentoActionForm) actionForm;

		String[] registrosRemocao = manterGuiaPagamentoActionForm
				.getIdRegistrosRemocao();
		String idImovel = manterGuiaPagamentoActionForm.getIdImovel();
		String idCliente = manterGuiaPagamentoActionForm.getCodigoCliente();

		GuiaPagamento guiaPagamento = new GuiaPagamento();

		Cliente cliente = new Cliente();

		if (idCliente != null && !idCliente.equals("")) {
			cliente.setId(new Integer(idCliente));

		}

		guiaPagamento.setCliente(cliente);

		Imovel imovel = new Imovel();

		ImovelCobrancaSituacao imovelCobrancaSituacao = null;

		if (idImovel != null && !idImovel.equals("")) {
			imovel.setId(new Integer(idImovel));
			imovelCobrancaSituacao = (ImovelCobrancaSituacao) sessao
					.getAttribute("imovelCobrancaSituacao");
		}

		guiaPagamento.setImovel(imovel);

        /** alterado por pedro alexandre dia 20/11/2006 
         * Recupera o usuário logado para passar no metódo de inserir guia de pagamento 
         * para verificar se o usuário tem abrangência para inserir a guia de pagamento
         * para o imóvel caso a guia de pagamentoseja para o imóvel.
         */
        Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
        guiaPagamento.setUsuario(usuarioLogado);
        fachada.manterGuiaPagamento(guiaPagamento, guiasPagamento,registrosRemocao, imovelCobrancaSituacao,usuarioLogado);
		//fachada.manterGuiaPagamento(guiaPagamento, guiasPagamento,registrosRemocao, imovelCobrancaSituacao);

		sessao.removeAttribute("imovelCobrancaSituacao");
		sessao.removeAttribute("guiasPagamentos");
		sessao.removeAttribute("ManterGuiaPagamentoActionForm");

		if (idImovel != null && !idImovel.equals("")) {

			montarPaginaSucesso(httpServletRequest, registrosRemocao.length
					+ " Guia(s) de Pagamento do imóvel " + idImovel
					+ " cancelada(s) com sucesso.",
					"Realizar outro Cancelamento de Guia de Pagamento",
					"exibirManterGuiaPagamentoAction.do?menu=sim");

		}

		if (idCliente != null && !idCliente.equals("")) {

			montarPaginaSucesso(httpServletRequest, registrosRemocao.length
					+ " Guia(s) de Pagamento do cliente " + idCliente
					+ " cancelada(s) com sucesso.",
					"Realizar outro Cancelamento de Guia de Pagamento",
					"exibirManterGuiaPagamentoAction.do?menu=sim");

		}

		return retorno;
	}
}
