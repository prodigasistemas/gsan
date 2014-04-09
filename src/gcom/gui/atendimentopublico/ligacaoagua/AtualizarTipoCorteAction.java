package gcom.gui.atendimentopublico.ligacaoagua;
import gcom.atendimentopublico.ligacaoagua.CorteTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1103] Manter Tipo de Corte
 *
 * @author Ivan Sergio
 * @date 03/12/2010
 */
public class AtualizarTipoCorteAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		AtualizarTipoCorteActionForm atualizarTipoCorteActionForm = (AtualizarTipoCorteActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);		
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		CorteTipo corteTipo = (CorteTipo) sessao.getAttribute("corteTipo");
		
		atualizarTipoCorteActionForm.setFormValues(corteTipo);
		
		fachada.atualizarCorteTipo(corteTipo, usuarioLogado);
		
		
		//[FS0004]Verificar Sucesso da Atualização
		//Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Tipo de Corte "+ atualizarTipoCorteActionForm.getDescricao() +
				" atualizado com sucesso.", "Realizar outra Manutenção do Tipo de Corte",
				"exibirFiltrarTipoCorteAction.do?menu=sim");
		
		return retorno;
	}
	
}	      
    



