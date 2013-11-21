package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.FiltroImovelProgramaEspecial;
import gcom.cadastro.imovel.ImovelProgramaEspecial;
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

public class ExibirManterImovelProgramaEspecialAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		//Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("exibirManterImovelProgramaEspecialAction");
		
		HttpSession sessao = httpServletRequest.getSession(false);

		//Recupera os parâmetros da sessão para ser efetuada a pesquisa
		FiltroImovelProgramaEspecial filtro = (FiltroImovelProgramaEspecial) sessao.getAttribute("filtroImovelProgramaEspecial");
		
		// Aciona o controle de paginação para que sejam pesquisados apenas
		// os registros que aparecem na página
		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtro, ImovelProgramaEspecial.class.getName());
		
		Collection colecaoImoveisProgramas = (Collection) resultado
				.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");
		
		//Verifica se a coleção retornada pela pesquisa é nula, em caso
		// afirmativo comunica ao usuário que não existe nenhuma equipe
		// cadastrada para a pesquisa efetuada e em caso negativo e se
		// atender a algumas condições seta o retorno para o
		// ExibirAtualizarEquipeAction, se não atender manda a
		// coleção pelo request para ser recuperado e exibido pelo jsp.
		if (colecaoImoveisProgramas != null && !colecaoImoveisProgramas.isEmpty()) {

			// Verifica se a coleção contém apenas um objeto, se está retornando
			// da paginação (devido ao esquema de paginação de 10 em 10 faz uma
			// nova busca), evitando, assim, que caso haja 11 elementos no
			// retorno da pesquisa e o usuário selecione o link para ir para a
			// segunda página ele não vá para tela de atualizar.
			if (colecaoImoveisProgramas.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {
				// Verifica se o usuário marcou o checkbox de atualizar no jsp
				// equipe_filtrar. Caso todas as condições sejam
				// verdadeiras seta o retorno para o
				// ExibirAtualizarEquipeAction e em caso negativo
				// manda a coleção pelo request.
				if (sessao.getAttribute("atualizar") != null) {
					retorno = actionMapping
							.findForward("exibirAtualizarImovelProgramaEspecialAction");
					ImovelProgramaEspecial imovel = (ImovelProgramaEspecial) colecaoImoveisProgramas.iterator().next();
					sessao.setAttribute("imovelProgramaEspecial", imovel);
					sessao.setAttribute("idRegistroAtualizar", imovel.getId());
					sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarImovelProgramaEspecialAction.do");
				} else {
					sessao.removeAttribute("imovelProgramaEspecial");
					httpServletRequest.setAttribute("colecaoImoveisProgramasEspeciais",
							colecaoImoveisProgramas);
					sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirManterImovelProgramaEspecialAction.do");
				}
			} else {
				sessao.removeAttribute("imovelProgramaEspecial");
				httpServletRequest.setAttribute("colecaoImoveisProgramasEspeciais",
						colecaoImoveisProgramas);
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirManterImovelProgramaEspecialAction.do");
			}
		} else {
			// Caso a pesquisa não retorne nenhum objeto comunica ao usuário;
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		return retorno;
		
	}
}
