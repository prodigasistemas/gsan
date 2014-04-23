package gcom.gui.cadastro.localidade;

import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.GerenciaRegional;
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
 * FiltrarManterTipoRetornoOrdemServicoReferidaAction ou ir para o
 * ExibirManterTipoRetornoOrdemServicoReferidaAction
 * 
 * @author Thiago Tenório
 * @since 31/10/2006
 */
public class ExibirManterGerenciaRegionalAction extends GcomAction {

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
				.findForward("exibirManterGerenciaRegional");

		// Obtém a instância da fachada
		// Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		// Recupera o filtro passado pelo FiltrarResolucaoDiretoriaAction para
		// ser efetuada pesquisa
		FiltroGerenciaRegional filtroGerenciaRegional = (FiltroGerenciaRegional) sessao
				.getAttribute("filtroGerenciaRegional");
		
		filtroGerenciaRegional
			.adicionarCaminhoParaCarregamentoEntidade("cliente");

		// Aciona o controle de paginação para que sejam pesquisados apenas
		// os registros que aparecem na página
		Collection colecaoGerenciaRegional = new ArrayList();
		if(filtroGerenciaRegional != null && !filtroGerenciaRegional.equals("")){
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroGerenciaRegional, GerenciaRegional.class.getName());
			colecaoGerenciaRegional = (Collection) resultado
				.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
		}
		// Verifica se a coleção retornada pela pesquisa é nula, em caso
		// afirmativo comunica ao usuário que não existe nenhuma resolução de
		// diretoria cadastrado para a pesquisa efetuada e em caso negativo e se
		// atender a algumas condições seta o retorno para o
		// ExibirAtualizarResolucaoDiretoriaAction, se não atender manda a
		// coleção pelo request para ser recuperado e exibido pelo jsp.
		if (colecaoGerenciaRegional != null
				&& !colecaoGerenciaRegional.isEmpty()) {

			// Verifica se a coleção contém apenas um objeto, se está retornando
			// da paginação (devido ao esquema de paginação de 10 em 10 faz uma
			// nova busca), evitando, assim, que caso haja 11 elementos no
			// retorno da pesquisa e o usuário selecione o link para ir para a
			// segunda página ele não vá para tela de atualizar.
			if (colecaoGerenciaRegional.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {
				// Verifica se o usuário marcou o checkbox de atualizar no jsp
				// resolucao_diretoria_filtrar. Caso todas as condições sejam
				// verdadeiras seta o retorno para o
				// ExibirAtualizarResolucaoDiretoriaAction e em caso negativo
				// manda a coleção pelo request.
				if (httpServletRequest.getParameter("indicadorAtualizar") != null) {
					retorno = actionMapping
							.findForward("exibirAtualizarGerenciaRegional");
					GerenciaRegional gerenciaRegional = (GerenciaRegional) colecaoGerenciaRegional
							.iterator().next();
					sessao.setAttribute("gerenciaRegional",
							gerenciaRegional);
				} else {
					httpServletRequest.setAttribute(
							"colecaoGerenciaRegional",
							colecaoGerenciaRegional);
					sessao.setAttribute("gerenciaRegional",
							colecaoGerenciaRegional);
				}
			} else {
				httpServletRequest.setAttribute("colecaoGerenciaRegional",
						colecaoGerenciaRegional);
			}
		} else {
			// Caso a pesquisa não retorne nenhum objeto comunica ao usuário;
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;

	}

}
