package gcom.gui.cobranca;

import gcom.cadastro.empresa.Empresa;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * @author Arthur Carvalho, Mariana Victor
 * @created 14 de agosto de 2009
 * @date 14/08/2009, 18/11/2010
 */
public class ExibirInserirCobrancaGrupoAction extends GcomAction {
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

		HttpSession sessao = httpServletRequest.getSession(false);

		ActionForward retorno = actionMapping.findForward("inserirCobrancaGrupo");

		InserirCobrancaGrupoActionForm form = (InserirCobrancaGrupoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		List colecaoHelper = null;
		
		if (httpServletRequest.getParameter("menu") != null && httpServletRequest.getParameter("menu").equals("sim")) {
			form.setIndicadorUso("2");
		} else if (httpServletRequest.getParameter("empresa") != null && !httpServletRequest.getParameter("empresa").equals("-1")){
			String idEmpresa = httpServletRequest.getParameter("empresa");

			if(sessao.getAttribute("collectionContrato") == null){
				
				colecaoHelper = new ArrayList();
				colecaoHelper = fachada.obterDadosItensContratoServico(new Integer(idEmpresa));

				if(colecaoHelper != null && colecaoHelper.size() > 0){
					sessao.setAttribute("collectionContrato", colecaoHelper);
				}
			}else{
				colecaoHelper = (List) sessao.getAttribute("collectionContrato");
			}

		}

		if ((httpServletRequest.getParameter("desfazer") != null && httpServletRequest
				.getParameter("desfazer").equalsIgnoreCase("S"))) {

			form.setDescricao("");

			if (form.getDescricao() == null
					|| form.getDescricao().equals("")) {

				Collection colecaoPesquisa = null;

				FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();

				filtroCobrancaGrupo.setCampoOrderBy(FiltroCobrancaGrupo.ID);

				colecaoPesquisa = fachada.pesquisar(filtroCobrancaGrupo,
						CobrancaGrupo.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.nenhum_registro_tabela", null,
							"Grupo De Cobrança");
				} else {
					sessao.setAttribute("colecaoCobrancaGrupo", colecaoPesquisa);
				}

				// Coleção Grupo de Cobrança
				filtroCobrancaGrupo = new FiltroCobrancaGrupo();
				filtroCobrancaGrupo.setCampoOrderBy(FiltroCobrancaGrupo.ID);

				Collection colecaoCobrancaGrupo = fachada.pesquisar(filtroCobrancaGrupo,
						CobrancaGrupo.class.getName());
				sessao.setAttribute("colecaoCobrancaGrupo", colecaoCobrancaGrupo);

			}

		}
		
		Collection<Empresa> collectionEmpresa = fachada.pesquisarEmpresasContratoServico();
		httpServletRequest.setAttribute("collectionEmpresa", collectionEmpresa);
		
		return retorno;
	}
}
