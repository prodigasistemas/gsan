package gcom.gui.faturamento;

import gcom.arrecadacao.ContratoDemanda;
import gcom.faturamento.FiltroContratoDemanda;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * Permite exibir uma lista com os contratos de demanda retornados do
 * FiltrarContratoDemandaAction ou ir para o
 * ExibirAtualizarContratoDemandaAction
 * 
 * @author Rafael Corrêa
 * @since 27/06/2007
 */
public class ExibirManterContratoDemandaAction extends GcomAction {

	/**
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirManterContratoDemanda");

		// Obtém a instância da fachada
		// Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		sessao.removeAttribute("contratoDemandaAtualizar");

		// Recupera o filtro passado pelo FiltrarResolucaoDiretoriaAction para
		// ser efetuada pesquisa
		FiltroContratoDemanda filtroContratoDemanda = (FiltroContratoDemanda) sessao
				.getAttribute("filtroContratoDemanda");
		filtroContratoDemanda.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
		filtroContratoDemanda.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
		filtroContratoDemanda.adicionarCaminhoParaCarregamentoEntidade("contratoMotivoCancelamento");

		// Aciona o controle de paginação para que sejam pesquisados apenas
		// os registros que aparecem na página
		Collection colecaoContratoDemanda = new ArrayList();
		if(filtroContratoDemanda != null && !filtroContratoDemanda.equals("")){
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroContratoDemanda, ContratoDemanda.class.getName());
			colecaoContratoDemanda = (Collection) resultado
				.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
		}
		// Verifica se a coleção retornada pela pesquisa é nula, em caso
		// afirmativo comunica ao usuário que não existe nenhuma resolução de
		// diretoria cadastrado para a pesquisa efetuada e em caso negativo e se
		// atender a algumas condições seta o retorno para o
		// ExibirAtualizarContratoDemandaAction, se não atender manda a
		// coleção pelo request para ser recuperado e exibido pelo jsp.
		if (colecaoContratoDemanda != null
				&& !colecaoContratoDemanda.isEmpty()) {

			// Verifica se a coleção contém apenas um objeto, se está retornando
			// da paginação (devido ao esquema de paginação de 10 em 10 faz uma
			// nova busca), evitando, assim, que caso haja 11 elementos no
			// retorno da pesquisa e o usuário selecione o link para ir para a
			// segunda página ele não vá para tela de atualizar.
			if (colecaoContratoDemanda.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {
				// Verifica se o usuário marcou o checkbox de atualizar no jsp
				// contrato_demanda_filtrar. Caso todas as condições sejam
				// verdadeiras seta o retorno para o
				// ExibirAtualizarContratoDemandaAction e em caso negativo
				// manda a coleção pelo request.
				if (sessao.getAttribute("atualizar") != null) {
					retorno = actionMapping
							.findForward("exibirAtualizarContratoDemanda");
					ContratoDemanda contratoDemanda = (ContratoDemanda) colecaoContratoDemanda
							.iterator().next();
					sessao.setAttribute("contratoDemanda",
							contratoDemanda);
				} else {
					httpServletRequest.setAttribute(
							"colecaoContratoDemanda",
							colecaoContratoDemanda);
				}
			} else {
				httpServletRequest.setAttribute("colecaoContratoDemanda",
						colecaoContratoDemanda);
			}
		} else {
			// Caso a pesquisa não retorne nenhum objeto comunica ao usuário;
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;

	}

}
