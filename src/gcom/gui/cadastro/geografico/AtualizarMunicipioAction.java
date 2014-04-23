package gcom.gui.cadastro.geografico;
import gcom.cadastro.geografico.Municipio;
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
 * [UC005] Manter Municipio [SB0001]Atualizar Municipio
 *
 * @author Kássia Albuquerque
 * @date 08/01/2007
 */


public class AtualizarMunicipioAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		AtualizarMunicipioActionForm atualizarMunicipioActionForm = (AtualizarMunicipioActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);		
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		Municipio municipio = (Municipio) sessao.getAttribute("municipio");
		
		String[] idMunicipio = new String[1];
		
		idMunicipio[0] = municipio.getId().toString();
		
		//Atualiza a entidade com os valores do formulário
		atualizarMunicipioActionForm.setFormValues(municipio);
		
		//atualiza na base de dados de Municipio
		 fachada.atualizarMunicipio(municipio,usuarioLogado);
		
		//Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Município de código "+ atualizarMunicipioActionForm.getCodigoMunicipio() +" atualizado com sucesso.", "Realizar outra Manutenção de Município",
				"exibirFiltrarMunicipioAction.do?menu=sim");
		
		return retorno;
	}
	
}	      
    



