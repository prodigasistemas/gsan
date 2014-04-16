package gcom.gui.operacional;

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
 * [UC0521] INSERIR Distrito Operacional
 * 
 * @author Eduardo Bianchi
 * @date 26/01/2007
 */

public class InserirDistritoOperacionalAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		InserirDistritoOperacionalActionForm inserirDistritoOperacionalActionForm = (InserirDistritoOperacionalActionForm) actionForm;
		
		Usuario usuarioLogado = (Usuario) sessao
		.getAttribute(Usuario.USUARIO_LOGADO);
		
		// Atualiza a entidade com os valores do formulário
		// inserirDistritoOperacionalActionForm.setFormValues(distritoOperacional);
		
		// Inserir na base de dados DistritoOperacional
		Integer idDistritoOperacional = fachada.inserirDistritoOperacional(
				inserirDistritoOperacionalActionForm.getDescricao(),
				inserirDistritoOperacionalActionForm.getDescricaoAbreviada(),
				inserirDistritoOperacionalActionForm.getSetorAbastecimento(),
				usuarioLogado);
		
//		sessao.setAttribute("caminhoRetornoVoltar",
//		"/gsan/exibirFiltrarDistritoOperacionalAction.do");
		
		// Montar a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Distrito Operacional de código "
				+ idDistritoOperacional
				+ " inserido com sucesso.",
				"Inserir outro Distrito Operacional",
				"exibirInserirDistritoOperacionalAction.do?menu=sim",
				"exibirAtualizarDistritoOperacionalAction.do?idRegistroInseridoAtualizar="
				+ idDistritoOperacional,
		"Atualizar Distrito Operacional Inserido");
		
		sessao.removeAttribute("InserirDistritoOperacionalActionForm");
		
		return retorno;
	}
}
