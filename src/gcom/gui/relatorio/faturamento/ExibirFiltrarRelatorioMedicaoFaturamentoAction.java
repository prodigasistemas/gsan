package gcom.gui.relatorio.faturamento;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

public class ExibirFiltrarRelatorioMedicaoFaturamentoAction extends GcomAction {

	/**
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
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirFiltrarRelatorioMedicaoFaturamento");
		
		Fachada fachada = Fachada.getInstancia();

		RelatorioMedicaoFaturamentoActionForm form = (RelatorioMedicaoFaturamentoActionForm) actionForm;
        
        //Mudar isso quando tiver esquema de segurança
        HttpSession sessao = httpServletRequest.getSession(false);
		
        //Carrega Coleçao de Faturamento Grupos
		FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
		
		filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
		                FiltroFaturamentoGrupo.INDICADOR_USO,
		                ConstantesSistema.INDICADOR_USO_ATIVO));
		
		filtroFaturamentoGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.DESCRICAO);
		        
		        
		Collection faturamentoGrupos = fachada.pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
		
		sessao.setAttribute("faturamentoGrupos", faturamentoGrupos);
		
		//Carrega Coleçao de Empresas
		FiltroEmpresa filtroEmpresas = new FiltroEmpresa();
		
		filtroEmpresas.adicionarParametro(new ParametroSimples(
		                FiltroEmpresa.INDICADORUSO,
		                ConstantesSistema.INDICADOR_USO_ATIVO));
		
		filtroEmpresas.setCampoOrderBy(FiltroFaturamentoGrupo.DESCRICAO);
		        
		        
		Collection empresas = fachada.pesquisar(filtroEmpresas, Empresa.class.getName());
		
		sessao.setAttribute("empresas", empresas);
        
		return retorno;
	}
}
