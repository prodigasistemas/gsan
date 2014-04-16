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
 * [UC0001]	INSERIR MUNICIPIO
 * 
 * @author Kássia Albuquerque
 * @date 13/12/2006
 */

public class InserirMunicipioAction extends GcomAction {


	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		InserirMunicipioActionForm inserirMunicipioActionForm = (InserirMunicipioActionForm) actionForm;
		
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
		Municipio municipio = new Municipio();
		
		//Atualiza a entidade com os valores do formulário
		inserirMunicipioActionForm.setFormValues(municipio);
		
		//Inserir na base de dados Municipio
		Integer idMunicipio = fachada.inserirMunicipio(municipio,usuarioLogado);
		
		sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarMunicipioAction.do");
		
		//Montar a página de sucesso
		montarPaginaSucesso(httpServletRequest,
				"Município de código "+ inserirMunicipioActionForm.getCodigoMunicipio() +" inserido com sucesso.",
				"Inserir outro Município","exibirInserirMunicipioAction.do?menu=sim",
				"exibirAtualizarMunicipioAction.do?idRegistroInseridoAtualizar="+ 
				idMunicipio,"Atualizar Município Inserido");
		
		sessao.removeAttribute("InserirMunicipioActionForm");

		return retorno;
	}
}
