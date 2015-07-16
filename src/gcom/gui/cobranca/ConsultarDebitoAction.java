package gcom.gui.cobranca;

import gcom.arrecadacao.pagamento.FiltroPagamentoSituacao;
import gcom.arrecadacao.pagamento.PagamentoSituacao;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteRelacaoTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Fernanda Paiva
 */
public class ConsultarDebitoAction extends GcomAction {
	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta a ação de retorno
		ActionForward retorno = null;

		// Mudar isso quando tiver esquema de segurança
		 HttpSession sessao = httpServletRequest.getSession(false);

		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();

		ConsultarDebitoActionForm consultarDebitoActionForm = (ConsultarDebitoActionForm) actionForm;

		String codigoImovel = null;

		if (httpServletRequest.getParameter("codigoImovel") != null) {

			codigoImovel = httpServletRequest
					.getParameter("codigoImovel");

		} else {

			codigoImovel = consultarDebitoActionForm.getCodigoImovel();

		}
        	
		String codigoClienteSuperior = consultarDebitoActionForm
			.getCodigoClienteSuperior();
		
		String codigoCliente = consultarDebitoActionForm
				.getCodigoCliente();

		Integer tipoRelacao = null;

		if (codigoCliente != null && !codigoCliente.trim().equals("")) {
		
		if (consultarDebitoActionForm.getTipoRelacao() != null
					&& !consultarDebitoActionForm.getTipoRelacao().trim()
							.equals("-1")) {
				tipoRelacao = new Integer(consultarDebitoActionForm
						.getTipoRelacao());
			} else {
				tipoRelacao = null;
			}

		}

		// Verifica se o usuário não digitou código do cliente nem a matricula
		// do imovel
		if ((codigoImovel == null || codigoImovel.equals(""))
				&& (codigoCliente == null || codigoCliente.equals(""))
				&& (codigoClienteSuperior == null || codigoClienteSuperior
						.equals(""))) {
			throw new ActionServletException(
					"atencao.verificar.informado.imovel_cliente");
		}

		boolean peloMenosUmParametroInformado = false;

		if (codigoImovel != null && !codigoImovel.trim().equals("")) {

			codigoImovel = codigoImovel.trim();

			// Seta o retorno para a página que vai detalhar o imovel
			retorno = actionMapping.findForward("exibirDebitoImovel");

			peloMenosUmParametroInformado = true;
		} else if ((codigoCliente != null && !codigoCliente.trim().equals(""))) {
			codigoCliente = codigoCliente.trim();

			// Seta o retorno para a página que vai detalhar o cliente
			retorno = actionMapping.findForward("exibirDebitoCliente");

			peloMenosUmParametroInformado = true;

		} else if ((codigoClienteSuperior != null && !codigoClienteSuperior
				.trim().equals(""))) {
			
			codigoClienteSuperior = codigoClienteSuperior.trim();
			
			// Seta o retorno para a página que vai detalhar o cliente
			retorno = actionMapping.findForward("exibirDebitoCliente");

			peloMenosUmParametroInformado = true;
			
		}

		// ClienteRelacaoTipo tipoRelacaoSelecionada = null;

		// Verifica o tipo de relação
		if (tipoRelacao != null && !tipoRelacao.equals("")
				&& tipoRelacao != ConstantesSistema.NUMERO_NAO_INFORMADO) {
			FiltroClienteRelacaoTipo filtroClienteRelacaoTipo = new FiltroClienteRelacaoTipo();

			filtroClienteRelacaoTipo.adicionarParametro(new ParametroSimples(
					FiltroClienteRelacaoTipo.CLIENTE_RELACAO_TIPO_ID,
					tipoRelacao));

			Collection<ClienteRelacaoTipo> collectionClienteRelacaoTipo = fachada
					.pesquisar(filtroClienteRelacaoTipo,
							ClienteRelacaoTipo.class.getName());

			if (collectionClienteRelacaoTipo != null
					&& collectionClienteRelacaoTipo.isEmpty()) {
				throw new ActionServletException(
						"atencao.collectionClienteRelacaoTipo_inexistente",
						null, "id");
			}

			/* tipoRelacaoSelecionada = */sessao.setAttribute("tipoRelacao", collectionClienteRelacaoTipo
					.iterator().next());
		} else {
			tipoRelacao = null;
		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.selecionar.nenhum_parametro_informado");
		}

		if (httpServletRequest.getParameter("ehPopup") != null) {
			sessao.setAttribute("ehPopup", "true");
		}

		if (httpServletRequest.getParameter("caminhoRetornoTelaConsultaDebito") != null) {

			httpServletRequest.setAttribute("caminhoRetornoTelaConsultaDebito",
					httpServletRequest
							.getParameter("caminhoRetornoTelaConsultaDebito"));

		}
		
		FiltroPagamentoSituacao filtroPagamentoSituacao = new FiltroPagamentoSituacao();
		filtroPagamentoSituacao.adicionarParametro(new ParametroSimples(FiltroPagamentoSituacao.DESCRICAO_ABREVIADA, "NCONF"));
		
		
		PagamentoSituacao pagamentoSituacao = (PagamentoSituacao) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroPagamentoSituacao, PagamentoSituacao.class.getName()));
		
		Collection colecaoContasInconformes = fachada.pesquisarPagamentoImovel(codigoImovel.trim(), null, null, null, null, 
				null, null, null, null, null, null, null, null, new String[]{pagamentoSituacao.getId().toString()}, null, null, null, null, null);
		
		sessao.setAttribute("colecaoPagamentosImovelContaInconformes", colecaoContasInconformes);
		sessao.setAttribute("totalContasInconformes", colecaoContasInconformes.size());

		return retorno;
	}
}
