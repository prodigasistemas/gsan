package gcom.gui.faturamento.debito;

import gcom.faturamento.debito.DebitoTipoVigencia;
import gcom.faturamento.debito.FiltroDebitoTipoVigencia;
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
 * @author Josenildo Neves - Hugo Leonardo	
 * @date 18/02/2010 - 16/04/2010
 */
public class ExibirManterDebitoTipoVigenciaAction extends GcomAction {

	/**
	 * [UC0986] Manter tipo de débito com vigência
	 * 
	 * Este caso de uso permite alterar ou excluir um debito tipo vigencia
	 * 
	 * @author Josenildo Neves
	 * @date 18/02/2010
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
				.findForward("exibirManterDebitoTipoVigenciaAction");

		// Obtém a instância da fachada
		// Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		// Limpa o atributo se o usuário voltou para o manter
		if (sessao.getAttribute("colecaoDebitoTipoVigenciaTela") != null) {
			sessao.removeAttribute("colecaoDebitoTipoVigenciaTela");
		}

		// Recupera o filtro passado pelo FiltrarValorCobrancaServicoAction para
		// ser efetuada pesquisa

		FiltroDebitoTipoVigencia filtroDebitoTipoVigencia = (FiltroDebitoTipoVigencia)sessao.getAttribute("filtroDebitoTipoVigencia");
		
		// Aciona o controle de paginação para que sejam pesquisados apenas
		// os registros que aparecem na página

		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroDebitoTipoVigencia, DebitoTipoVigencia.class.getName());
		
		Collection colecaoDebitoTipoVigencia = (Collection) resultado
				.get("colecaoRetorno");
		
		retorno = (ActionForward) resultado.get("destinoActionForward");

		// Verifica se a coleção retornada pela pesquisa é nula, em caso
		// afirmativo comunica ao usuário que não existe nenhum Debito Tipo Vigencia
		// cadastrada
		// para a pesquisa efetuada e em caso negativo e se
		// atender a algumas condições seta o retorno para o
		// ExibirAtualizarDebitoTipoVigenciaAction, se não atender manda a
		// coleção pelo request para ser recuperado e exibido pelo jsp.

		if (colecaoDebitoTipoVigencia != null
				&& !colecaoDebitoTipoVigencia.isEmpty()) {

			// Verifica se a coleção contém apenas um objeto, se está retornando
			// da paginação (devido ao esquema de paginação de 10 em 10 faz uma
			// nova busca), evitando, assim, que caso haja 11 elementos no
			// retorno da pesquisa e o usuário selecione o link para ir para a
			// segunda página ele não vá para tela de atualizar.
			
			if (colecaoDebitoTipoVigencia.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {

				// Verifica se o usuário marcou o checkbox de atualizar no jsp
				// valor_cobranca_servico_filtrar. Caso todas as condições sejam
				// verdadeiras seta o retorno para o
				// ExibirAtualizarDebitoTipoVigenciaAction e em caso negativo
				// manda a coleção pelo request.

				if (httpServletRequest.getParameter("atualizar") != null) {
					retorno = actionMapping
							.findForward("atualizarDebitoTipoVigencia");
					DebitoTipoVigencia debitoTipoVigencia = (DebitoTipoVigencia) colecaoDebitoTipoVigencia
							.iterator().next();
					sessao.setAttribute("objetoDebitoTipoVigencia",
							debitoTipoVigencia);

				} else {
					
					sessao.removeAttribute("objetoDebitoTipoVigencia");
					
					httpServletRequest.setAttribute(
							"colecaoDebitoTipoVigencia",
							colecaoDebitoTipoVigencia);
				}
			} else {
				httpServletRequest.setAttribute("colecaoDebitoTipoVigencia",
						colecaoDebitoTipoVigencia);
			}
		} else {
			// Nenhuma funcionalidade cadastrada
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;
	}
}
