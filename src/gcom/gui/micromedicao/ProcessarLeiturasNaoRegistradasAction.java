package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroMovimentoRoteiroEmpresa;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ProcessarLeiturasNaoRegistradasAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		Fachada fachada = Fachada.getInstancia();
		
		ProcessarLeiturasNaoRegistradasActionForm form = (ProcessarLeiturasNaoRegistradasActionForm)actionForm;
		
		if(form.getFaturamentoGrupoID()!=null && !form.getFaturamentoGrupoID().equals("") 
				&& !form.getFaturamentoGrupoID().equals("-1")){
			
			FiltroFaturamentoGrupo filtroGrupo = new FiltroFaturamentoGrupo();
			filtroGrupo.adicionarParametro(new ParametroSimples(FiltroMovimentoRoteiroEmpresa.ID, form.getFaturamentoGrupoID()));
			
			Collection colecao = fachada.pesquisar(filtroGrupo,FaturamentoGrupo.class.getName());
			
			if(colecao!=null && !colecao.isEmpty()){
				FaturamentoGrupo grupo = (FaturamentoGrupo) colecao.iterator().next();
				
				fachada.processarLeiturasNaoResgistradas(grupo);
				
				montarPaginaSucesso(httpServletRequest, "Leituras Registradas e Consitidas com sucesso.",
						"Realizar outra Manutenção",
						"exibirProcessarLeiturasNaoRegistradasAction.do?menu=sim");
								
				
			}
			
			
			
			
		}
		
		return retorno;
	}

}
