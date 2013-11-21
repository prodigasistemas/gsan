package gcom.gui.micromedicao;

import java.util.Collection;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirProcessarLeiturasNaoRegistradasAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("processarLeiturasNaoRealizadas");
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		ProcessarLeiturasNaoRegistradasActionForm form = (ProcessarLeiturasNaoRegistradasActionForm)actionForm;
		
//		Selecionar o Grupo de Faturamento
		FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
		filtroFaturamentoGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.ID);
		Collection colecaoFaturamentoGrupo = fachada.pesquisar(
				filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());

		if (colecaoFaturamentoGrupo != null && !colecaoFaturamentoGrupo.isEmpty()) {
			sessao.setAttribute("colecaoFaturamentoGrupo", colecaoFaturamentoGrupo);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Grupo de Faturamento");
		}
		
		if (httpServletRequest.getParameter("menu") != null) {
			form.setFaturamentoGrupoID("-1");
			form.setQntNaoProcessado("0");
		}else{
									
			
			
			if(form.getFaturamentoGrupoID()!=null && !form.getFaturamentoGrupoID().equals("")
					&& !form.getFaturamentoGrupoID().equals("-1")){
				
				FiltroFaturamentoGrupo filtro = new FiltroFaturamentoGrupo();
				filtro.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.ID,form.getFaturamentoGrupoID()));
				
				Collection colecao = fachada.pesquisar(filtro,FaturamentoGrupo.class.getName());
				
				if(colecao != null && !colecao.isEmpty()){
					Integer qnt = fachada.consultarQuantidadeLeiturasNaoResgistradas((FaturamentoGrupo)colecao.iterator().next());
					if(qnt!=null){
						form.setQntNaoProcessado(qnt.toString());
					}else{
						form.setQntNaoProcessado("0");
					}
				}
				
				
			}
			
			
		}
		
		return retorno;
	}
}
