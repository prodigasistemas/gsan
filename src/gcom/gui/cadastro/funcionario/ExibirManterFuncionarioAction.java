package gcom.gui.cadastro.funcionario;

import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
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
 * @author Rômulo Aurélio
 *
 */
public class ExibirManterFuncionarioAction extends GcomAction{
	
	/**
	 * 
	 * [UC????] Manter Funcionario
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 08/04/2007
	 * 
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterFuncionarioAction");

		// Obtém a instância da fachada
		// Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		// Limpa o atributo se o usuário voltou para o manter
		if (sessao.getAttribute("colecaoFuncionarioTela") != null) {
			sessao.removeAttribute("colecaoFuncionarioTela");
		}

		// Recupera o filtro passado pelo FiltrarFuncionalidadeAction para
		// ser efetuada pesquisa
		FiltroFuncionario filtroFuncionario = (FiltroFuncionario) sessao
				.getAttribute("filtroFuncionario");
		
		filtroFuncionario.adicionarCaminhoParaCarregamentoEntidade("empresa");
		filtroFuncionario.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");

		// Aciona o controle de paginação para que sejam pesquisados apenas
		// os registros que aparecem na página
		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroFuncionario, Funcionario.class.getName());
		Collection colecaoFuncionario = (Collection) resultado
				.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");

		// Verifica se a coleção retornada pela pesquisa é nula, em caso
		// afirmativo comunica ao usuário que não existe nenhuma fuuncionalidade
		// cadastrada
		// para a pesquisa efetuada e em caso negativo e se
		// atender a algumas condições seta o retorno para o
		// ExibirAtualizarFuncionalidadeAction, se não atender manda a
		// coleção pelo request para ser recuperado e exibido pelo jsp.

		if (colecaoFuncionario != null && !colecaoFuncionario.isEmpty()) {

			// Verifica se a coleção contém apenas um objeto, se está retornando
			// da paginação (devido ao esquema de paginação de 10 em 10 faz uma
			// nova busca), evitando, assim, que caso haja 11 elementos no
			// retorno da pesquisa e o usuário selecione o link para ir para a
			// segunda página ele não vá para tela de atualizar.

			if (colecaoFuncionario.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {

				// Verifica se o usuário marcou o checkbox de atualizar no jsp
				// funcionario_filtrar. Caso todas as condições sejam
				// verdadeiras seta o retorno para o
				// ExibirAtualizarFuncionarioAction e em caso negativo
				// manda a coleção pelo request.

				if (httpServletRequest.getParameter("atualizar") != null) {
					retorno = actionMapping
							.findForward("atualizarFuncionario");
					Funcionario funcionario = (Funcionario) colecaoFuncionario
							.iterator().next();
					sessao.setAttribute("objetoFuncionario", funcionario);

				} else {
					httpServletRequest.setAttribute("colecaoFuncionario",
							colecaoFuncionario);
				}
			} else {
				httpServletRequest.setAttribute("colecaoFuncionario",
						colecaoFuncionario);
			}
		} else {
			// Nenhuma funcionario cadastrado
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;
	}
}
