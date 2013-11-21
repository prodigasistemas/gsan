package gcom.gui.cobranca.parcelamento;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAdicionarPagamentoCartaoCreditoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirAdicionarPagamentoCartaoCredito");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		String pesquisaCliente = httpServletRequest.getParameter("pesquisaCliente");
		String validaData = httpServletRequest.getParameter("validaData");
		String inicio = httpServletRequest.getParameter("inicio");
		String pesquisaSubmit = httpServletRequest.getParameter("pesquisaSubmit");
		
		
		Fachada fachada = Fachada.getInstancia();

		ParcelamentoCartaoConfirmarForm form = (ParcelamentoCartaoConfirmarForm) actionForm;

		if(inicio != null && inicio.equalsIgnoreCase("sim")){
			
			form.setCartaoCredito("-1");
			form.setIdCliente("");
			form.setNomeCliente("");
			form.setAutorizacaoCartao("");
			form.setValidadeCartao("");
			form.setDocumentoCartao("");
			form.setNumeroIdentificacaoTransacao("");
			form.setNumeroCartao("");
			form.setValorTransacao("");
			form.setDataOperadora("");
			form.setQtdParcelas(form.getNumeroPrestacoes().toString());
		}
		
		if(pesquisaSubmit!=null && pesquisaSubmit.equalsIgnoreCase("sim")){
			httpServletRequest.setAttribute("pesquisaSubmit","sim");
			
		}

		// RECUPERA DADOS DO POPUP 
		if (httpServletRequest.getParameter("tipoConsulta") != null
				&& httpServletRequest.getParameter("tipoConsulta").equals(
						"cliente")) {

			form.setIdCliente(httpServletRequest
					.getParameter("idCampoEnviarDados"));

			form.setNomeCliente(httpServletRequest
					.getParameter("descricaoCampoEnviarDados"));

		}

		// PESQUISAR CLIENTE
		if (pesquisaCliente != null && pesquisaCliente.equalsIgnoreCase("sim")) {

			this.pesquisarCliente(form.getIdCliente(), form, fachada,
					httpServletRequest);
		}

		// PESQUISAR CARTOES DE CREDITO
		this.pesquisarCartoesDeCredito(sessao, fachada, form);

		if (validaData != null && validaData.equalsIgnoreCase("sim")) {
			if (form.getValidadeCartao() != null
					&& !form.getValidadeCartao().equalsIgnoreCase("")) {
				Date dataAtual = new Date(System.currentTimeMillis());
				Integer anoMesAtual = Util.getAnoMesComoInteger(dataAtual);
				Integer anoMesValidade = Util
						.formatarMesAnoComBarraParaAnoMes(form
								.getValidadeCartao());
				if (Util.compararAnoMesReferencia(anoMesValidade, anoMesAtual,
						"<")) {
					sessao.setAttribute("validadeCartaoinvalida", "true");
					form.setValidadeCartaoInvalida("true");

				} else {
					sessao.removeAttribute("validadeCartaoinvalida");
					form.setValidadeCartaoInvalida("false");
				}
			}

		}

		return retorno;
	}

	private void pesquisarCartoesDeCredito(HttpSession sessao, Fachada fachada,
			ParcelamentoCartaoConfirmarForm form) {

		Collection<Cliente> colecaoCartao = null;
		
		if (form.getModalidadeCartao().equals(ConstantesSistema.MODALIDADE_CARTAO_CREDITO.toString())){
			
			colecaoCartao = fachada.pesquisarCartoes(ArrecadacaoForma.CARTAO_CREDITO);
		}
		else{
			colecaoCartao = fachada.pesquisarCartoes(ArrecadacaoForma.CARTAO_DEBITO);
		}
		

		sessao.setAttribute("colecaoCartao", colecaoCartao);
	}

	/**
	 * Pesquisar Clientes
	 * 
	 * @param filtroCliente
	 * @param idCliente
	 * @param clientes
	 * @param filtrarImovelFiltrarActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	public void pesquisarCliente(String idCliente,
			ParcelamentoCartaoConfirmarForm filtro, Fachada fachada,
			HttpServletRequest httpServletRequest) {
		FiltroCliente filtroCliente = new FiltroCliente();

		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,
				idCliente));
		filtroCliente.adicionarParametro(new ParametroSimples(
				FiltroCliente.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection clienteEncontrado = fachada.pesquisar(filtroCliente,
				Cliente.class.getName());

		if (clienteEncontrado != null && !clienteEncontrado.isEmpty()) {
			// O municipio foi encontrado
			filtro.setIdCliente(""
					+ ((Cliente) ((List) clienteEncontrado).get(0)).getId());
			filtro.setNomeCliente(((Cliente) ((List) clienteEncontrado).get(0))
					.getNome());
			httpServletRequest.setAttribute("idClienteNaoEncontrado", "true");
			httpServletRequest.setAttribute("nomeCampo", "cepFiltro");

		} else {
			filtro.setIdCliente("");
			httpServletRequest.setAttribute("idClienteNaoEncontrado",
					"exception");
			filtro.setNomeCliente("Cliente inexistente");

			httpServletRequest.setAttribute("nomeCampo", "idClienteFiltro");

		}
	}

}
