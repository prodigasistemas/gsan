package gcom.gui.faturamento.debito;

import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
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
 * @author Rômulo Aurélio
 * @date 13/03/2007
 */
public class ExibirManterTipoDebitoAction extends GcomAction {
	/**
	 * Este caso de uso permite alterar e remover um Tipo de Débito
	 * 
	 * [UC0530] Manter Tipo de Débito
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 13/03/2007
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
				.findForward("exibirManterTipoDebitoAction");

		// Obtém a instância da fachada
		// Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		// Limpa o atributo se o usuário voltou para o manter
		if (sessao.getAttribute("colecaoTipoDebitoTela") != null) {
			sessao.removeAttribute("colecaoTipoDebitoTela");
		}

		// Recupera o filtro passado pelo FiltrarFuncionalidadeAction para
		// ser efetuada pesquisa
		FiltroDebitoTipo filtroDebitoTipo = (FiltroDebitoTipo) sessao
				.getAttribute("filtroDebitoTipo");

		// Aciona o controle de paginação para que sejam pesquisados apenas
		// os registros que aparecem na página
		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroDebitoTipo, DebitoTipo.class.getName());
		Collection colecaoDebitoTipo = (Collection) resultado
				.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");

		// Verifica se a coleção retornada pela pesquisa é nula, em caso
		// afirmativo comunica ao usuário que não existe nenhuma fuuncionalidade
		// cadastrada
		// para a pesquisa efetuada e em caso negativo e se
		// atender a algumas condições seta o retorno para o
		// ExibirAtualizarFuncionalidadeAction, se não atender manda a
		// coleção pelo request para ser recuperado e exibido pelo jsp.

		if (colecaoDebitoTipo != null && !colecaoDebitoTipo.isEmpty()) {

			// Verifica se a coleção contém apenas um objeto, se está retornando
			// da paginação (devido ao esquema de paginação de 10 em 10 faz uma
			// nova busca), evitando, assim, que caso haja 11 elementos no
			// retorno da pesquisa e o usuário selecione o link para ir para a
			// segunda página ele não vá para tela de atualizar.

			if (colecaoDebitoTipo.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {

				// Verifica se o usuário marcou o checkbox de atualizar no jsp
				// funcionalidade_filtrar. Caso todas as condições sejam
				// verdadeiras seta o retorno para o
				// ExibirAtualizarFuncionalidadeAction e em caso negativo
				// manda a coleção pelo request.

				if (httpServletRequest.getParameter("atualizar") != null) {
					retorno = actionMapping
							.findForward("atualizarTipoDebitoAction");
					DebitoTipo debitoTipo = (DebitoTipo) colecaoDebitoTipo
							.iterator().next();
					sessao.setAttribute("objetoDebitoTipo", debitoTipo);

				} else {
					httpServletRequest.setAttribute("colecaoDebitoTipo",
							colecaoDebitoTipo);
				}
			} else {
				httpServletRequest.setAttribute("colecaoDebitoTipo",
						colecaoDebitoTipo);
			}
		} else {
			// Nenhuma funcionalidade cadastrada
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;
	}

}
