package gcom.gui.faturamento.conta;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

public class ExibirFiltrarQtdeContaImpressaoTermicaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
		
        ActionForward retorno = actionMapping.findForward("exibirFiltrarQtdeContaImpressaoTermica");

        Fachada fachada = Fachada.getInstancia();
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        ConsultarQtdeContaImpressaoTermicaActionForm form = (ConsultarQtdeContaImpressaoTermicaActionForm) actionForm;
        
        if(sessao.getAttribute("faturamentoGrupos") == null){
	        FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
	        filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
	        filtroFaturamentoGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.DESCRICAO);
	        
	        Collection faturamentoGrupos = fachada.pesquisar( filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
	
	        if (faturamentoGrupos.isEmpty()) {
	            throw new ActionServletException("atencao.naocadastrado", null, "grupo de faturamento");
	        } else {
	            sessao.setAttribute("faturamentoGrupos", faturamentoGrupos);
	        }
        }
        
        return retorno;
		
	}
}
