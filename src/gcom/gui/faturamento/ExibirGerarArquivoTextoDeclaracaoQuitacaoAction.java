package gcom.gui.faturamento;

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


public class ExibirGerarArquivoTextoDeclaracaoQuitacaoAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		Fachada fachada = Fachada.getInstancia();

	    //Mudar isso quando tiver esquema de segurança
	    HttpSession sessao = httpServletRequest.getSession(false);

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("exibirGerarArquivoTextoDeclaracaoQuitacaoAction");
		
		if(sessao.getAttribute("faturamentoGrupos") == null){
	        //Carrega Coleçao de Faturamento Grupos
	        FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
	        filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
	                FiltroFaturamentoGrupo.INDICADOR_USO,
	                ConstantesSistema.INDICADOR_USO_ATIVO));
	        filtroFaturamentoGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.ID);
	        Collection faturamentoGrupos = fachada.pesquisar(
	                filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
	
	        if (faturamentoGrupos.isEmpty()) {
	            throw new ActionServletException("atencao.naocadastrado", null,
	                    "grupo de faturamento");
	        } else {
	            sessao.setAttribute("faturamentoGrupos", faturamentoGrupos);
	        }
        }
		
		return retorno;
	}
}
