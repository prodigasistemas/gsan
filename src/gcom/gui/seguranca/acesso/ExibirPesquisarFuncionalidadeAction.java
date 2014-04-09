package gcom.gui.seguranca.acesso;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroModulo;
import gcom.seguranca.acesso.Modulo;
import gcom.util.ConstantesSistema;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirPesquisarFuncionalidadeAction extends GcomAction {

	/**
	 * Este caso de uso efetua pesquisa de funcionalidade
	 * 
	 * [UC0282] Pesquisar Funcionalidade
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 10/04/2006
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
				.findForward("funcionalidadePesquisar");
		
		PesquisarFuncionalidadeActionForm form = (PesquisarFuncionalidadeActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroModulo filtroModulo = new FiltroModulo();

		// Verifica se os dados foram informados da tabela existem e joga numa
		// colecao
		Collection<Modulo> colecaoModulo = 
			this.getFachada().pesquisar(filtroModulo,
				Modulo.class.getName());

		if (colecaoModulo == null || colecaoModulo.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela Modulo");
		}

		httpServletRequest.setAttribute("colecaoModulo", colecaoModulo);

		// envia uma flag que será verificado no cliente_resultado_pesquisa.jsp
		// para saber se irá usar o enviar dados ou o enviar dados parametros
		if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisaFuncionalidade") != null) {
			
			sessao.setAttribute(
				"caminhoRetornoTelaPesquisaFuncionalidade",
				httpServletRequest.getParameter("caminhoRetornoTelaPesquisaFuncionalidade"));
		}
		
		
		if(httpServletRequest.getParameter("registraTransacao") != null && 
			!httpServletRequest.getParameter("registraTransacao").equals("")){
			
			form.setIndicadorRegistraTransacao(httpServletRequest.getParameter("registraTransacao"));
		}

		form.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
		return retorno;
	}

}
