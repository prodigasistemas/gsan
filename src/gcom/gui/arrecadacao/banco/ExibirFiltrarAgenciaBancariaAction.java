package gcom.gui.arrecadacao.banco;
import gcom.arrecadacao.banco.Banco;
import gcom.arrecadacao.banco.FiltroBanco;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

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
public class ExibirFiltrarAgenciaBancariaAction extends GcomAction {
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
		
		
        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();
		
        HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarAgenciaBancariaActionForm filtrarAgenciaBancariaActionForm = (FiltrarAgenciaBancariaActionForm) actionForm;
		
		sessao.removeAttribute("agenciaAtualizar");
		sessao.removeAttribute("agencia");

		if (httpServletRequest.getParameter("menu") != null) {
			
			filtrarAgenciaBancariaActionForm.setAtualizar("1");
			filtrarAgenciaBancariaActionForm.setBancoID("");
			filtrarAgenciaBancariaActionForm.setCodigo("");
			filtrarAgenciaBancariaActionForm.setNome("");
			filtrarAgenciaBancariaActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());

		} else {

			if (httpServletRequest.getParameter("paginacao") == null && sessao.getAttribute("filtrar") == null) {

				String atualizar = httpServletRequest.getParameter("atualizar");

				if (atualizar != null && !atualizar.equals("")) {
					sessao.setAttribute("atualizar", atualizar);
				} else {
					sessao.removeAttribute("atualizar");
				}

			}
		}
		
		sessao.removeAttribute("equipeAtualizar");
		
		// Parte que passa as coleções necessárias no jsp
		FiltroBanco filtroBanco = new FiltroBanco();
		filtroBanco.setCampoOrderBy(FiltroBanco.ID);
		Collection colecaoBanco = fachada.pesquisar(
				filtroBanco, Banco.class.getName());

		if (colecaoBanco != null && !colecaoBanco.isEmpty()) {
			sessao.setAttribute("colecaoBanco", colecaoBanco);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Banco");
		}
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("filtrarAgenciaBancaria");
		
		return retorno;

	}
}

