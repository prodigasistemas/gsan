package gcom.gui.arrecadacao.pagamento;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.fachada.Fachada;
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
 * 
 * [UC0247] Consultar Pagamentos
 * 
 * @author Sávio Luiz
 * @date 19/12/2007
 */
public class ExibirInformarExpurgoPagamentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("informarExpurgoPagamento");

		// Instacia a fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		InformarExpurgoPagamentoActionForm informarExpurgoPagamentoActionForm = (InformarExpurgoPagamentoActionForm) actionForm;
		
		String limpar = httpServletRequest
				.getParameter("limpar");

		if (limpar != null) {
			if(limpar.equals("cliente")){
				informarExpurgoPagamentoActionForm.setIdCliente("");
				informarExpurgoPagamentoActionForm.setNomeCliente("");	
			}
			if(limpar.equals("dataPagamento")){
				informarExpurgoPagamentoActionForm.setDataPagamento("");	
			}
			
			if(limpar.equals("sim")){
				informarExpurgoPagamentoActionForm.setIdCliente("");
				informarExpurgoPagamentoActionForm.setNomeCliente("");	
				informarExpurgoPagamentoActionForm.setDataPagamento("");	
				informarExpurgoPagamentoActionForm.setMesAnoReferencia("");	
			}
			
			informarExpurgoPagamentoActionForm.setQuantidadePagamentosExpurgados("");
			informarExpurgoPagamentoActionForm.setQuantidadePagamentosNaoExpurgados("");
			
			sessao.removeAttribute("colecaoExpurgado");
			sessao.removeAttribute("colecaoNaoExpurgado");
		}

		String botaoConsultar = httpServletRequest
				.getParameter("botaoConsultar");

		if (botaoConsultar != null) {
			
			Integer anoMesArrecadacao = Util.formatarMesAnoComBarraParaAnoMes(informarExpurgoPagamentoActionForm.getMesAnoReferencia());

			Object[] colecaoDadosPagamento = fachada
					.gerarColecaoDadosPagamentoPelaData(informarExpurgoPagamentoActionForm
							.getDataPagamento(),Util.converterStringParaInteger(informarExpurgoPagamentoActionForm.getIdCliente()),anoMesArrecadacao);

			Collection colecaoExpurgado = new ArrayList();

			Collection colecaoNaoExpurgado = new ArrayList();
			

			if (colecaoDadosPagamento != null
					&& !colecaoDadosPagamento.equals("")) {
				
				colecaoExpurgado = (Collection)colecaoDadosPagamento[0];
				
				colecaoNaoExpurgado = (Collection)colecaoDadosPagamento[1];

			}

			informarExpurgoPagamentoActionForm
					.setQuantidadePagamentosExpurgados(""+colecaoExpurgado.size());
			
			informarExpurgoPagamentoActionForm
			.setQuantidadePagamentosNaoExpurgados(""+colecaoNaoExpurgado.size());
			
			sessao.setAttribute("colecaoExpurgado",colecaoExpurgado);
			sessao.setAttribute("colecaoNaoExpurgado",colecaoNaoExpurgado);

		}

		String idCliente = informarExpurgoPagamentoActionForm.getIdCliente();
		String nomeCliente = informarExpurgoPagamentoActionForm
				.getNomeCliente();

		if (idCliente != null && !idCliente.equals("")
				&& (nomeCliente == null || nomeCliente.equals(""))) {

			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, idCliente));
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			Collection<Cliente> colecaoCliente = fachada.pesquisar(
					filtroCliente, Cliente.class.getName());
			if (colecaoCliente != null && !colecaoCliente.isEmpty()) {
				Cliente cliente = (Cliente) Util
						.retonarObjetoDeColecao(colecaoCliente);
				informarExpurgoPagamentoActionForm.setNomeCliente(cliente
						.getNome());
			} else {
				informarExpurgoPagamentoActionForm.setNomeCliente("");
				informarExpurgoPagamentoActionForm
						.setNomeCliente("CLIENTE INEXISTENTE");
				httpServletRequest.setAttribute("clienteInexistente", "sim");
			}
		}

		return retorno;
	}
}
