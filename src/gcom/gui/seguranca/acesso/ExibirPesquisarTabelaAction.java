package gcom.gui.seguranca.acesso;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Pesquisar Tabela
 * 
 * @date 12/05/2008
 * @author Vinicius Medeiros
 */
public class ExibirPesquisarTabelaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirPesquisarTabela");

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Pega o parâmetro que indica se a pesquisa é do tipo que fecha o popup
		// de pesquisa e retorna os dados (indicado pela ausência de parâmetro)
		// ou do tipo que é acionado a partir de um popup
		String tipoPesquisa = httpServletRequest.getParameter("tipoPesquisa");
		
		// Repassa o tipo da pesquisa para a página
		httpServletRequest.setAttribute("tipoPesquisa", tipoPesquisa);


        if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisaTabela") != null) {
			sessao.setAttribute("caminhoRetornoTelaPesquisaTabela",
							httpServletRequest.getParameter("caminhoRetornoTelaPesquisaTabela"));
		}

        //envia uma flag que será verificado no
        // localidade_resultado_pesquisa.jsp
        //para saber se irá usar o enviar dados ou o enviar dados parametros
        if(httpServletRequest.getParameter("caminhoRetornoTelaPesquisaTabela") != null){
        	sessao.setAttribute("caminhoRetornoTelaPesquisaTabela", httpServletRequest
                .getParameter("caminhoRetornoTelaPesquisaTabela"));
        }
				
		return retorno;

	}

}
