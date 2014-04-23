package gcom.gui.cobranca;

import gcom.cobranca.FiltroResolucaoDiretoria;
import gcom.cobranca.ResolucaoDiretoria;
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
 * Permite exibir uma lista com as resoluções de diretoria retornadas do
 * FiltrarResolucaoDiretoriaAction ou ir para o
 * ExibirAtualizarResolucaoDiretoriaAction
 * 
 * @author Rafael Corrêa
 * @since 31/03/2006
 */
public class ExibirManterResolucaoDiretoriaAction extends GcomAction {

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
				.findForward("exibirManterResolucaoDiretoria");

		// Obtém a instância da fachada
		// Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		// Recupera o filtro passado pelo FiltrarResolucaoDiretoriaAction para
		// ser efetuada pesquisa
		FiltroResolucaoDiretoria filtroResolucaoDiretoria = (FiltroResolucaoDiretoria) sessao
				.getAttribute("filtroResolucaoDiretoria");

		// Aciona o controle de paginação para que sejam pesquisados apenas
		// os registros que aparecem na página
		Collection colecaoResolucaoDiretoria = new ArrayList();
		if(filtroResolucaoDiretoria != null && !filtroResolucaoDiretoria.equals("")){
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroResolucaoDiretoria, ResolucaoDiretoria.class.getName());
			colecaoResolucaoDiretoria = (Collection) resultado
				.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
		}
		// Verifica se a coleção retornada pela pesquisa é nula, em caso
		// afirmativo comunica ao usuário que não existe nenhuma resolução de
		// diretoria cadastrado para a pesquisa efetuada e em caso negativo e se
		// atender a algumas condições seta o retorno para o
		// ExibirAtualizarResolucaoDiretoriaAction, se não atender manda a
		// coleção pelo request para ser recuperado e exibido pelo jsp.
		if (colecaoResolucaoDiretoria != null
				&& !colecaoResolucaoDiretoria.isEmpty()) {

			// Verifica se a coleção contém apenas um objeto, se está retornando
			// da paginação (devido ao esquema de paginação de 10 em 10 faz uma
			// nova busca), evitando, assim, que caso haja 11 elementos no
			// retorno da pesquisa e o usuário selecione o link para ir para a
			// segunda página ele não vá para tela de atualizar.
			if (colecaoResolucaoDiretoria.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {
				// Verifica se o usuário marcou o checkbox de atualizar no jsp
				// resolucao_diretoria_filtrar. Caso todas as condições sejam
				// verdadeiras seta o retorno para o
				// ExibirAtualizarResolucaoDiretoriaAction e em caso negativo
				// manda a coleção pelo request.
				if (httpServletRequest.getParameter("atualizar") != null) {
					retorno = actionMapping
							.findForward("exibirAtualizarResolucaoDiretoria");
					ResolucaoDiretoria resolucaoDiretoria = (ResolucaoDiretoria) colecaoResolucaoDiretoria
							.iterator().next();
					sessao.setAttribute("resolucaoDiretoria",
							resolucaoDiretoria);
				} else {
					httpServletRequest.setAttribute(
							"colecaoResolucaoDiretoria",
							colecaoResolucaoDiretoria);
				}
			} else {
				httpServletRequest.setAttribute("colecaoResolucaoDiretoria",
						colecaoResolucaoDiretoria);
			}
		} else {
			// Caso a pesquisa não retorne nenhum objeto comunica ao usuário;
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;

	}

}
