package gcom.gui.micromedicao.hidrometro;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.seguranca.acesso.usuario.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * [UC0081]	MANTER MARCA HIDROMETRO
 * 
 * @author Bruno Leonardo
 * @date 18/06/2007
 */


public class AtualizarHidrometroMarcaAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

			ActionForward retorno = actionMapping.findForward("telaSucesso");
			
			AtualizarHidrometroMarcaActionForm atualizarHidrometroMarcaActionForm =
					( AtualizarHidrometroMarcaActionForm ) actionForm;
			
			Fachada fachada = Fachada.getInstancia();
			
			HttpSession sessao = httpServletRequest.getSession(false);		
			
			Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
			
			HidrometroMarca hidrometroMarca = ( HidrometroMarca ) sessao.getAttribute("hidrometroMarca");
			
			//Atualiza a entidade com os valores do formulário						
			atualizarHidrometroMarcaActionForm.setFormValues(hidrometroMarca);
			
			fachada.atualizarHidrometroMarca( hidrometroMarca, usuarioLogado );
			
			//Monta a página de sucesso
			montarPaginaSucesso(httpServletRequest, "Marca de Hidrômetro de código "+ hidrometroMarca.getId() +" atualizado com sucesso.", "Realizar outra Manutenção de Marca de Hidrômetro",
					"exibirFiltrarHidrometroMarcaAction.do?menu=sim");
			
			return retorno;
	}
	
}	      
    



