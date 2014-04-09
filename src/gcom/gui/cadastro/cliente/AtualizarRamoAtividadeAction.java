package gcom.gui.cadastro.cliente;
import gcom.cadastro.cliente.RamoAtividade;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * Atualizar Ramo de Atividade
 *
 * @author Fernando Fontelles Filho	
 * @date 02/12/2009
 */


public class AtualizarRamoAtividadeAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		AtualizarRamoAtividadeActionForm form = (AtualizarRamoAtividadeActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);		
		
		RamoAtividade ramoAtividade = (RamoAtividade) sessao.getAttribute("atualizarRamoAtividade");
		
		String[] ramoAtividadeId = new String[1];
		
		ramoAtividadeId[0] = "" + ramoAtividade.getCodigo();
		
		//Atualiza a entidade com os valores do formulário
		ramoAtividade.setCodigo(new Short(form.getCodigo()));
		ramoAtividade.setDescricao(form.getDescricao());
		ramoAtividade.setIndicadorUso(new Short(form.getIndicadorUso()));	
				
		//atualiza na base de dados de Ramo de Atividade
		 fachada.atualizar(ramoAtividade);
		
		//Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Ramo de Atividade de código " + ramoAtividadeId[0] + 
				" atualizado com sucesso.", 
				"Realizar outra Manutenção de Ramo de Atividade",
				"exibirFiltrarRamoAtividadeAction.do?menu=sim");
		
		return retorno;
	}
	
}	      
    



