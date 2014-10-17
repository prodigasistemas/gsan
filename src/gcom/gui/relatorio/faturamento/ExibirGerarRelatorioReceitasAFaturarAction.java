package gcom.gui.relatorio.faturamento;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.micromedicao.ConsultarArquivoTextoLeituraActionForm;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirGerarRelatorioReceitasAFaturarAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {	
		
		ActionForward retorno = actionMapping.findForward("exibirGerarRelatorioReceitasAFaturar");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		RelatorioReceitasAFaturarActionForm form = (RelatorioReceitasAFaturarActionForm) actionForm;
		form.setGrupoFaturamentoID(-1);
		
		Fachada fachada = Fachada.getInstancia();
		
		FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
		filtroFaturamentoGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.ID);
		Collection colecaoFaturamentoGrupo = fachada.pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
		
		if (colecaoFaturamentoGrupo != null	&& !colecaoFaturamentoGrupo.isEmpty()) {
			sessao.setAttribute("colecaoFaturamentoGrupo", colecaoFaturamentoGrupo);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null, "Grupo de Faturamento");
		}
		
		return retorno;
	}

}
