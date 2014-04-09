package gcom.gui.cadastro.localidade;

import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirPesquisarGerenciaRegionalAction extends GcomAction {

	/**
	 * Método responsavel por responder a requisicao
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

		// Inicializando Variaveis
		ActionForward retorno = actionMapping
				.findForward("gerenciaRegionalPesquisar");
		PesquisarGerenciaRegionalActionForm pesquisarGerenciaRegionalActionForm = (PesquisarGerenciaRegionalActionForm) actionForm;
		//Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		
		pesquisarGerenciaRegionalActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL
				.toString());
		
		pesquisarGerenciaRegionalActionForm.setId("");
		pesquisarGerenciaRegionalActionForm.setNome("");
		pesquisarGerenciaRegionalActionForm.setNomeAbreviado("");

		if ((httpServletRequest.getParameter("desfazer") != null && httpServletRequest
				.getParameter("desfazer").equalsIgnoreCase("S"))) {
			pesquisarGerenciaRegionalActionForm.setId("");
			pesquisarGerenciaRegionalActionForm.setNome("");
			pesquisarGerenciaRegionalActionForm.setNomeAbreviado("");

		}
		
		 //envia uma flag que será verificado no funcionario_resultado_pesquisa.jsp
        //para saber se irá usar o enviar dados ou o enviar dados parametros
        if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisaGerenciaRegional") != null) {
        	  sessao.setAttribute("caminhoRetornoTelaPesquisaGerenciaRegional",
        	          httpServletRequest
        	                  .getParameter("caminhoRetornoTelaPesquisaGerenciaRegional"));
        	}

		return retorno;

	}

}
