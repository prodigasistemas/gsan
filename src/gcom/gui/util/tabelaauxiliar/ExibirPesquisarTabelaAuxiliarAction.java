package gcom.gui.util.tabelaauxiliar;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExibirPesquisarTabelaAuxiliarAction extends GcomAction {
	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("pesquisarTabelaAuxiliar");

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Pega o parametro passado no request
		String tela = httpServletRequest.getParameter("tela");

		// Pega o parâmetro que indica se a pesquisa é do tipo que fecha o popup
		// de pesquisa e retorna os dados (indicado pela ausência de parâmetro)
		// ou do tipo que é acionado a partir de um popup
		String tipoPesquisa = httpServletRequest.getParameter("tipoPesquisa");
		
		String caminhoRetorno = httpServletRequest
				.getParameter("caminhoRetorno");

		// Repassa o tipo da pesquisa para a página
		httpServletRequest.setAttribute("tipoPesquisa", tipoPesquisa);

		// Repassa o caminho do retorno para a página
		httpServletRequest.setAttribute("caminhoRetorno", caminhoRetorno);

		// Busca na classe DadosTelaTabelaAuxiliarAbreviada pela configuração da
		// tela requisitada
		DadosTelaTabelaAuxiliar dados = DadosTelaTabelaAuxiliar.obterDadosTelaTabelaAuxiliar(tela);

		sessao.setAttribute("dados", dados);

		return retorno;

	}
}
