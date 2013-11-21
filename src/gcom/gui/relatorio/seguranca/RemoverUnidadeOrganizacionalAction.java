package gcom.gui.relatorio.seguranca;

import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.gui.GcomAction;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * [UC1074] Gerar Relatório Alterações no Sistema por Coluna
 * @author Hugo Amorim
 * @date 13/09/2010
 *
 */
public class RemoverUnidadeOrganizacionalAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.
			findForward("exibirGerarRelatorioAlteracoesSistemaColunaAction");
	
		GerarRelatorioAlteracoesSistemaColunaForm form = 
			(GerarRelatorioAlteracoesSistemaColunaForm) actionForm;
				
		if(httpServletRequest.getParameter("id")!=null){
			
			String idUnidadeOrganizacinal = httpServletRequest.getParameter("id");
			
			UnidadeOrganizacional unidadeOrganizacionalRemover = new UnidadeOrganizacional();
			
			unidadeOrganizacionalRemover.setId(Integer.parseInt(idUnidadeOrganizacinal));
			
			Iterator<UnidadeOrganizacional> itera =
				form.getColecaoUnidadeOrganizacional().iterator();

			while (itera.hasNext()) {
				UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) itera.next();

				if(unidadeOrganizacional.getId().toString()
						.equals(httpServletRequest.getParameter("id"))){
					
						itera.remove();
						break;
						
				}
			}
		}
		
		if(!form.getColecaoUnidadeOrganizacional().isEmpty()){
			httpServletRequest.setAttribute("colecaoUnidadeVazia", "nao");
		}
		
		return retorno;
	}
}
