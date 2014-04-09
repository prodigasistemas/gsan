package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.RoteiroEmpresaHelper;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * Permite exibir uma lista com os roteiros da empresa retornadas do
 * FiltrarManterRoteiroEmpresaAction ou ir para o
 * ExibirManterRoteiroEmpresaReferidaAction
 * 
 * @author Thiago Tenório
 * @since 23/08/2007
 */
public class ExibirManterRoteiroEmpresaAction extends GcomAction {

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
			ActionForward retorno = actionMapping.findForward("exibirManterRoteiroEmpresa");

			// Obtém a instância da fachada
			Fachada fachada = Fachada.getInstancia();

			HttpSession sessao = httpServletRequest.getSession(false);
			
			sessao.removeAttribute("roteiroEmpresaAtualizar");
        	sessao.removeAttribute("colecaoQuadras");

			// Recupera os parâmetros da sessão para ser efetuada a pesquisa
			String empresa = (String) sessao.getAttribute("empresa");
			String idLocalidade = (String) sessao.getAttribute("idLocalidade");
			String idLeiturista = (String) sessao.getAttribute("idLeiturista");
			String idSetorComercial = (String) sessao.getAttribute("idSetorComercial");

			String indicadorUso = (String) sessao.getAttribute("indicadorUso");
		
			// Aciona o controle de paginação para que sejam pesquisados apenas
			// os registros que aparecem na página
			Integer totalRegistros = fachada.pesquisarRoteiroEmpresaCount(empresa, idLocalidade,
					idLeiturista, idSetorComercial, indicadorUso);

			retorno = this.controlarPaginacao(httpServletRequest, retorno,
					totalRegistros);

			Collection colecaoRoteiroEmpresas = fachada.pesquisarRoteiroEmpresa(empresa, idLocalidade,
					idSetorComercial, idLeiturista, indicadorUso, (Integer) httpServletRequest
							.getAttribute("numeroPaginasPesquisa"));

			
			// Verifica se a coleção retornada pela pesquisa é nula, em caso
			// afirmativo comunica ao usuário que não existe nenhuma roteiroEmpresa
			// cadastrada para a pesquisa efetuada e em caso negativo e se
			// atender a algumas condições seta o retorno para o
			// ExibirAtualizarRoteiroEmpresaAction, se não atender manda a
			// coleção pelo request para ser recuperado e exibido pelo jsp.
			if (colecaoRoteiroEmpresas != null && !colecaoRoteiroEmpresas.isEmpty()) {

				// Verifica se a coleção contém apenas um objeto, se está retornando
				// da paginação (devido ao esquema de paginação de 10 em 10 faz uma
				// nova busca), evitando, assim, que caso haja 11 elementos no
				// retorno da pesquisa e o usuário selecione o link para ir para a
				// segunda página ele não vá para tela de atualizar.
				if (colecaoRoteiroEmpresas.size() == 1
						&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
								.getParameter("page.offset").equals("1"))) {
					// Verifica se o usuário marcou o checkbox de atualizar no jsp
					// roteiroEmpresa_filtrar. Caso todas as condições sejam
					// verdadeiras seta o retorno para o
					// ExibirAtualizarRoteiroEmpresaAction e em caso negativo
					// manda a coleção pelo request.
					if (sessao.getAttribute("atualizar") != null) {
						retorno = actionMapping
								.findForward("exibirAtualizarRoteiroEmpresa");
						RoteiroEmpresaHelper roteiroEmpresa = (RoteiroEmpresaHelper) colecaoRoteiroEmpresas.iterator().next();
						sessao.setAttribute("idRegistroAtualizacao", roteiroEmpresa.getIdRoteiroEmpresa() + "");
					} else {
						httpServletRequest.setAttribute("colecaoRoteiroEmpresa",
								colecaoRoteiroEmpresas);
					}
				} else {
					httpServletRequest.setAttribute("colecaoRoteiroEmpresa",
							colecaoRoteiroEmpresas);
				}
			} else {
				// Caso a pesquisa não retorne nenhum objeto comunica ao usuário;
				throw new ActionServletException("atencao.pesquisa.nenhumresultado");
			}

			return retorno;

		}

	}
