package gcom.gui.seguranca.acesso;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroFuncionalidade;
import gcom.seguranca.acesso.FiltroOperacaoTipo;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.OperacaoTipo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de pesquisa de cliente
 * 
 * @author Thiago Tenório
 * @created 25 de Abril de 2005
 */
public class ExibirPesquisarOperacaoAction extends GcomAction {
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("pesquisarOperacao");
		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Parte que passa as coleções necessárias no jsp
		FiltroOperacaoTipo filtroOperacaoTipo = new FiltroOperacaoTipo();
		filtroOperacaoTipo.setCampoOrderBy(FiltroOperacaoTipo.DESCRICAO);
		Collection colecaoOperacaoTipo = fachada.pesquisar(
				filtroOperacaoTipo, OperacaoTipo.class.getName());

		if (colecaoOperacaoTipo != null && !colecaoOperacaoTipo.isEmpty()) {
			sessao.setAttribute("colecaoOperacaoTipo", colecaoOperacaoTipo);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Tipo da Operação");
		}
		
		PesquisarOperacaoActionForm pesquisarOperacaoActionForm = (PesquisarOperacaoActionForm) actionForm;
		
		String idFuncionalidade = pesquisarOperacaoActionForm.getIdFuncionalidade();
		
		if (idFuncionalidade != null && !idFuncionalidade.equals("")) {
			FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();
			filtroFuncionalidade.adicionarParametro(new ParametroSimples(FiltroFuncionalidade.ID, idFuncionalidade));
			Collection colecaoFuncionalidade = fachada.pesquisar(
					filtroFuncionalidade, Funcionalidade.class.getName());
			if (colecaoFuncionalidade != null && !colecaoFuncionalidade.isEmpty()){
				
				Funcionalidade funcionalidade = (Funcionalidade) colecaoFuncionalidade.iterator().next();
				pesquisarOperacaoActionForm.setIdFuncionalidade(funcionalidade.getId().toString());
				pesquisarOperacaoActionForm.setDescricaoFuncionalidade(funcionalidade.getDescricao());				
				
			} else {
				httpServletRequest.setAttribute("funcionalidadeInexistente", true);
				pesquisarOperacaoActionForm.setIdFuncionalidade("");
				pesquisarOperacaoActionForm.setDescricaoFuncionalidade("FUNCIONALIDADE INEXISTENTE");
			}
		}
		


		return retorno;
	}
}
