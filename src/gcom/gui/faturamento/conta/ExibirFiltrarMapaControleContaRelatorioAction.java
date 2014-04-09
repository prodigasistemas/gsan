package gcom.gui.faturamento.conta;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExibirFiltrarMapaControleContaRelatorioAction extends GcomAction {

    /**
     * < <Descrição do método>>
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

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("filtrarMapaControleContaRelatorio");
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        FiltrarMapaControleContaRelatorioActionForm filtrarMapaControleContaRelatorioActionForm = (FiltrarMapaControleContaRelatorioActionForm) actionForm;        
        
        filtrarMapaControleContaRelatorioActionForm.setIndicadorFichaCompensacao("1");
        
        if(httpServletRequest.getParameter("caminho") != null){
        	sessao.setAttribute("caminho", "ResumoContaLocalidade");
        	
        	FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
        	filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
        	Collection colecaoEmpresas = Fachada.getInstancia().pesquisar(filtroEmpresa, Empresa.class.getName());
        	
        	sessao.setAttribute("colecaoEmpresas", colecaoEmpresas);
        	
        	retorno = actionMapping
            	.findForward("filtrarResumoContaLocalidadeRelatorio");
        }

        Fachada fachada = Fachada.getInstancia();

        if(sessao.getAttribute("faturamentoGrupos") == null){
	        //Carrega Coleçao de Faturamento Grupos
	        FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
	        filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
	                FiltroFaturamentoGrupo.INDICADOR_USO,
	                ConstantesSistema.INDICADOR_USO_ATIVO));
	        filtroFaturamentoGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.DESCRICAO);
	        Collection faturamentoGrupos = fachada.pesquisar(
	                filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
	
	        if (faturamentoGrupos.isEmpty()) {
	            throw new ActionServletException("atencao.naocadastrado", null,
	                    "grupo de faturamento");
	        } else {
	            sessao.setAttribute("faturamentoGrupos", faturamentoGrupos);
	        }
        }
        
        if(filtrarMapaControleContaRelatorioActionForm.getTipoImpressao()==null){
        	filtrarMapaControleContaRelatorioActionForm.setTipoImpressao("1");
        }

        return retorno;

    }
}
