package gcom.gui.micromedicao.hidrometro;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;

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
 * FiltrarManterTipoRetornoOrdemServicoReferidaAction ou ir para o
 * ExibirManterTipoRetornoOrdemServicoReferidaAction
 * 
 * @author Thiago Tenório
 * @since 18/06/2007
 */
public class ExibirManterCapacidadeHidrometroAction extends GcomAction {

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
				.findForward("exibirManterCapacidadeHidrometro");

		// Obtém a instância da fachada
		// Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		// Recupera o filtro passado pelo FiltrarResolucaoDiretoriaAction para
		// ser efetuada pesquisa
		FiltroHidrometroCapacidade filtroHidrometroCapacidade = (FiltroHidrometroCapacidade) sessao
				.getAttribute("filtroHidrometroCapacidade");

		// Aciona o controle de paginação para que sejam pesquisados apenas
		// os registros que aparecem na página
		Collection colecaoCapacidadeHidrometro = new ArrayList();
		if(filtroHidrometroCapacidade != null && !filtroHidrometroCapacidade.equals("")){
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroHidrometroCapacidade, HidrometroCapacidade.class.getName());
			colecaoCapacidadeHidrometro = (Collection) resultado
				.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
		}
		// Verifica se a coleção retornada pela pesquisa é nula, em caso
		// afirmativo comunica ao usuário que não existe nenhuma resolução de
		// diretoria cadastrado para a pesquisa efetuada e em caso negativo e se
		// atender a algumas condições seta o retorno para o
		// ExibirAtualizarResolucaoDiretoriaAction, se não atender manda a
		// coleção pelo request para ser recuperado e exibido pelo jsp.
		if (colecaoCapacidadeHidrometro != null
				&& !colecaoCapacidadeHidrometro.isEmpty()) {

			// Verifica se a coleção contém apenas um objeto, se está retornando
			// da paginação (devido ao esquema de paginação de 10 em 10 faz uma
			// nova busca), evitando, assim, que caso haja 11 elementos no
			// retorno da pesquisa e o usuário selecione o link para ir para a
			// segunda página ele não vá para tela de atualizar.
			if (colecaoCapacidadeHidrometro.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {
				// Verifica se o usuário marcou o checkbox de atualizar no jsp
				// resolucao_diretoria_filtrar. Caso todas as condições sejam
				// verdadeiras seta o retorno para o
				// ExibirAtualizarResolucaoDiretoriaAction e em caso negativo
				// manda a coleção pelo request.
				if (httpServletRequest.getAttribute("atualizar") != null) {
					retorno = actionMapping
							.findForward("exibirAtualizarCapacidadeHidrometro");
					HidrometroCapacidade hidrometroCapacidade = (HidrometroCapacidade) colecaoCapacidadeHidrometro
							.iterator().next();
					sessao.setAttribute("hidrometroCapacidade",
							hidrometroCapacidade);
				} else {
					httpServletRequest.setAttribute(
							"colecaoCapacidadeHidrometro",
							colecaoCapacidadeHidrometro);
				}
			} else {
				httpServletRequest.setAttribute("colecaoCapacidadeHidrometro",
						colecaoCapacidadeHidrometro);
			}
		} else {
			// Caso a pesquisa não retorne nenhum objeto comunica ao usuário;
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;

	}

}
