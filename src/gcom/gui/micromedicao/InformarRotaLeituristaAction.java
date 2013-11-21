package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InformarRotaLeituristaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
//		 Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		InformarRotaLeituristaActionForm form = (InformarRotaLeituristaActionForm) actionForm;
		
		Fachada f = Fachada.getInstancia();
		
		if(form.getLeitursitaID()!= null && !form.getLeitursitaID().equals("") && 
				!form.getLeitursitaID().equals("-1") && form.getRotas()!=null && 
				form.getRotas().length>0){
						
			Integer[] idsRotas = new Integer[form.getRotas().length];
			for(int i =0; i< form.getRotas().length; i++){
				idsRotas[i] = new Integer(form.getRotas()[i]);
			}
			
			//Chamar Metodo da Fachada para atualizar 
			f.atualizarRelacaoRotaLeiturista(new Integer(form.getLeitursitaID()), idsRotas);
			
			montarPaginaSucesso(httpServletRequest, "Relação Rota X Leiturista Atualizada com sucesso.",
					"Realizar outra atualização de Rota X Leiturista",
					"exibirInformarRotaLeituristaAction.do?menu=sim");
			
		}else{
			//Fornecer os Leituristas e as rotas
		}
		
		return retorno;
	}
	
}
