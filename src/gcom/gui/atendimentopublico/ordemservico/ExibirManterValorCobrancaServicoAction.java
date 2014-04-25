package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroServicoCobrancaValor;
import gcom.atendimentopublico.ordemservico.ServicoCobrancaValor;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

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
 * @author Rômulo Aurélio de Melo
 * @date 30/10/2006
 */
public class ExibirManterValorCobrancaServicoAction extends GcomAction {

	/**
	 * [UC0393] Manter Valor de Cobrança do Serviço
	 * 
	 * Este caso de uso permite alterar ou excluir um valor de cobrança de
	 * serviço
	 * 
	 * @author Rômulo Aurélio  de Melo
	 * @date 30/10/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterValorCobrancaServicoAction");

		// Obtém a instância da fachada
		// Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		// Limpa o atributo se o usuário voltou para o manter
		if (sessao.getAttribute("colecaoServicoCobrancaValorTela") != null) {
			sessao.removeAttribute("colecaoServicoCobrancaValorTela");
		}

		// Recupera o filtro passado pelo FiltrarValorCobrancaServicoAction para
		// ser efetuada pesquisa

		FiltroServicoCobrancaValor filtroServicoCobrancaValor = (FiltroServicoCobrancaValor) sessao
				.getAttribute("filtroServicoCobrancaValor");

		// Aciona o controle de paginação para que sejam pesquisados apenas
		// os registros que aparecem na página

		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroServicoCobrancaValor, ServicoCobrancaValor.class
						.getName());
		Collection colecaoServicoCobrancaValor = (Collection) resultado
				.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");

		// Verifica se a coleção retornada pela pesquisa é nula, em caso
		// afirmativo comunica ao usuário que não existe nenhum valor de
		// cobranca do servico
		// cadastrada
		// para a pesquisa efetuada e em caso negativo e se
		// atender a algumas condições seta o retorno para o
		// ExibirAtualizarServicoCobrancaValorAction, se não atender manda a
		// coleção pelo request para ser recuperado e exibido pelo jsp.

		if (colecaoServicoCobrancaValor != null
				&& !colecaoServicoCobrancaValor.isEmpty()) {

			// Verifica se a coleção contém apenas um objeto, se está retornando
			// da paginação (devido ao esquema de paginação de 10 em 10 faz uma
			// nova busca), evitando, assim, que caso haja 11 elementos no
			// retorno da pesquisa e o usuário selecione o link para ir para a
			// segunda página ele não vá para tela de atualizar.
			
			//ServicoCobrancaValor servicoCobrancaValor1 = (ServicoCobrancaValor) colecaoServicoCobrancaValor
			//.iterator().next();
			
			//servicoCobrancaValor1.getServicoTipo().getDescricao();

			if (colecaoServicoCobrancaValor.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {

				// Verifica se o usuário marcou o checkbox de atualizar no jsp
				// valor_cobranca_servico_filtrar. Caso todas as condições sejam
				// verdadeiras seta o retorno para o
				// ExibirAtualizarServicoCobrancaValorAction e em caso negativo
				// manda a coleção pelo request.

				if (httpServletRequest.getParameter("atualizar") != null) {
					retorno = actionMapping
							.findForward("atualizarValorCobrancaServico");
					ServicoCobrancaValor servicoCobrancaValor = (ServicoCobrancaValor) colecaoServicoCobrancaValor
							.iterator().next();
					sessao.setAttribute("objetoservicoCobrancaValor",
							servicoCobrancaValor);
					httpServletRequest.setAttribute("manter", "sim");

				} else {
					
					sessao.removeAttribute("objetoservicoCobrancaValor");				
					sessao.removeAttribute("idRegistroAtualizar");
					
					httpServletRequest.setAttribute(
							"colecaoServicoCobrancaValor",
							colecaoServicoCobrancaValor);
				}
			} else {
				
				sessao.removeAttribute("objetoservicoCobrancaValor");
				sessao.removeAttribute("idRegistroAtualizar");
				
				httpServletRequest.setAttribute("colecaoServicoCobrancaValor",
						colecaoServicoCobrancaValor);
			}
		} else {
			// Nenhuma funcionalidade cadastrada
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;
	}
}
