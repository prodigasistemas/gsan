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
 * [UC0524]	INSERIR SISTEMA ESGOTO
 * 
 * @author Kássia Albuquerque
 * @date 08/03/2007
 */

public class InserirHidrometroMarcaAction extends GcomAction {


	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

			ActionForward retorno = actionMapping.findForward("telaSucesso");
			
			HttpSession sessao = httpServletRequest.getSession(false);
			
			Fachada fachada = Fachada.getInstancia();
			
			InserirHidrometroMarcaActionForm inserirHidrometroMarcaActionForm = (InserirHidrometroMarcaActionForm) actionForm;
			
			HidrometroMarca hidrometroMarca = new HidrometroMarca();
			
			//Atualiza a entidade com os valores do formulário
			inserirHidrometroMarcaActionForm.setFormValues(hidrometroMarca);
			
			// Usuario logado no sistema
			Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");			
			
			//Inserir na base de dados Sistema de Esgoto
			Integer idHidrometroMarca = fachada.inserirHidrometroMarca(hidrometroMarca, usuarioLogado);
			
			sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirInserirHidrometroMarcaAction.do");
			
			//Montar a página de sucesso
			montarPaginaSucesso(httpServletRequest,
					"Marca de Hidrômetro de código "+ idHidrometroMarca +" inserido com sucesso.",
					"Inserir outra marcar de hidrômetro","exibirInserirHidrometroMarcaAction.do?menu=sim",
					"exibirAtualizarHidrometroMarcaAction.do?idRegistroInseridoAtualizar="+ 
					idHidrometroMarca,"Atualizar marca de hidrômetro");
			
			sessao.removeAttribute("InserirHidrometroMarcaActionForm");
	
			return retorno;
	}
}
