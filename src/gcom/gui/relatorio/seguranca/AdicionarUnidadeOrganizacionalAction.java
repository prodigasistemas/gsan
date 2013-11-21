package gcom.gui.relatorio.seguranca;

import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

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
public class AdicionarUnidadeOrganizacionalAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.
			findForward("exibirGerarRelatorioAlteracoesSistemaColunaAction");
	
		GerarRelatorioAlteracoesSistemaColunaForm form = 
			(GerarRelatorioAlteracoesSistemaColunaForm) actionForm;
		
		if(Util.verificarNaoVazio(form.getIdUnidadeOrganizacional())){
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID, 
					new Integer(form.getIdUnidadeOrganizacional())));

			Collection colecaoUnidadeOrganizacional = this.getFachada().pesquisar(
					filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
		
			if (!Util.isVazioOrNulo(colecaoUnidadeOrganizacional)) {
				
				UnidadeOrganizacional unidadeOrganizacional = 
					(UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeOrganizacional);
				
				if(form.getColecaoUnidadeOrganizacional()==null){
					form.setColecaoUnidadeOrganizacional(new ArrayList<UnidadeOrganizacional>());
				}
				
				if(!form.getColecaoUnidadeOrganizacional().contains(unidadeOrganizacional)){
					form.getColecaoUnidadeOrganizacional().add(unidadeOrganizacional);
					httpServletRequest.setAttribute("ADICIONOU", true);
				}else{
					httpServletRequest.setAttribute("ADICIONOU", false);
				}
				
				
			}
		}
		
		if(!form.getColecaoUnidadeOrganizacional().isEmpty()){
			httpServletRequest.setAttribute("colecaoUnidadeVazia", "nao");
		}
		
		return retorno;
	}
}
