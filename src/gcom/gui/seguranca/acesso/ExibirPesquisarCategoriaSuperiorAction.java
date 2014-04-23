package gcom.gui.seguranca.acesso;



import java.util.Collection;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroModulo;
import gcom.seguranca.acesso.Modulo;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de pesquisa Categoria da Superior
 * 
 * @author Fernando Fontelles
 * @created 25/08/2009
 */
public class ExibirPesquisarCategoriaSuperiorAction extends GcomAction {
	/**
	 * Description of the Method
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
		
		Fachada fachada = Fachada.getInstancia();
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirPesquisarCategoriaSuperior");

		
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		PesquisarCategoriaSuperiorActionForm pesquisarCategoriaSuperiorActionForm = 
				(PesquisarCategoriaSuperiorActionForm) actionForm;
		
		FiltroModulo filtroModulo = new FiltroModulo();

		// Verifica se os dados foram informados da tabela existem e joga numa
		// colecao

		Collection<Modulo> colecaoModulo = fachada.pesquisar(filtroModulo,
				Modulo.class.getName());

		if (colecaoModulo == null || colecaoModulo.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela Modulo");
		}
		
		if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisa") != null) {
			
			sessao.setAttribute("caminhoRetornoTelaPesquisa",
				httpServletRequest.getParameter("caminhoRetornoTelaPesquisa"));
			
		}
		
		if(httpServletRequest.getParameter("limparForm") != null){
			pesquisarCategoriaSuperiorActionForm.setDescricao("");
			pesquisarCategoriaSuperiorActionForm.setIdModulo("");
			pesquisarCategoriaSuperiorActionForm.setIndicadorUso("");
		}
		
		if(pesquisarCategoriaSuperiorActionForm.getTipoPesquisa() == null){
			pesquisarCategoriaSuperiorActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
		}
		
		httpServletRequest.setAttribute("colecaoModulo", colecaoModulo);
		
		return retorno;
	}
	
}
