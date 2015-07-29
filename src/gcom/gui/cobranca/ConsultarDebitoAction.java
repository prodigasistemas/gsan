package gcom.gui.cobranca;

import gcom.arrecadacao.pagamento.FiltroPagamentoSituacao;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoSituacao;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteRelacaoTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Fernanda Paiva
 */
public class ConsultarDebitoAction extends GcomAction {
	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta a a��o de retorno
		ActionForward retorno = null;

		// Mudar isso quando tiver esquema de seguran�a
		 HttpSession sessao = httpServletRequest.getSession(false);

		// Obt�m a inst�ncia da Fachada
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

		// Verifica se o usu�rio n�o digitou c�digo do cliente nem a matricula
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
			
			FiltroPagamentoSituacao filtroPagamentoSituacao = new FiltroPagamentoSituacao();
			filtroPagamentoSituacao.adicionarParametro(new ParametroSimples(FiltroPagamentoSituacao.DESCRICAO_ABREVIADA, "NCONF"));
			
			
			PagamentoSituacao pagamentoSituacao = (PagamentoSituacao) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroPagamentoSituacao, PagamentoSituacao.class.getName()));
			
			Object[] colecaoContasInconformes = fachada.pesquisarPagamentoInconformeImovel(codigoImovel.trim());
			Collection<Pagamento> colecaoPagamentosInconformesAtuais = (Collection<Pagamento>) colecaoContasInconformes[0];
			Collection<Pagamento> colecaoPagamentosInconformesPreteritas = (Collection<Pagamento>) colecaoContasInconformes[1];
			Collection<Pagamento> colecaoPagamentosImovelContaInconformes = new ArrayList<Pagamento>();
			colecaoPagamentosImovelContaInconformes.addAll(colecaoPagamentosInconformesAtuais);
			colecaoPagamentosImovelContaInconformes.addAll(colecaoPagamentosInconformesPreteritas);
			
			sessao.setAttribute("colecaoPagamentosImovelContaInconformes", colecaoPagamentosImovelContaInconformes);
			sessao.setAttribute("colecaoPagamentosInconformesAtuais", colecaoPagamentosInconformesAtuais);
			sessao.setAttribute("colecaoPagamentosInconformesPreteritos", colecaoPagamentosInconformesPreteritas);

			// Seta o retorno para a p�gina que vai detalhar o imovel
			retorno = actionMapping.findForward("exibirDebitoImovel");

			peloMenosUmParametroInformado = true;
		} else if ((codigoCliente != null && !codigoCliente.trim().equals(""))) {
			codigoCliente = codigoCliente.trim();

			// Seta o retorno para a p�gina que vai detalhar o cliente
			retorno = actionMapping.findForward("exibirDebitoCliente");

			peloMenosUmParametroInformado = true;

		} else if ((codigoClienteSuperior != null && !codigoClienteSuperior
				.trim().equals(""))) {
			
			codigoClienteSuperior = codigoClienteSuperior.trim();
			
			// Seta o retorno para a p�gina que vai detalhar o cliente
			retorno = actionMapping.findForward("exibirDebitoCliente");

			peloMenosUmParametroInformado = true;
			
		}

		// ClienteRelacaoTipo tipoRelacaoSelecionada = null;

		// Verifica o tipo de rela��o
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

		// Erro caso o usu�rio mandou filtrar sem nenhum par�metro
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
		
		return retorno;
	}
}
