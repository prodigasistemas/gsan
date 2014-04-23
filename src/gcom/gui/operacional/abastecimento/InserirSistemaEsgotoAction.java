package gcom.gui.operacional.abastecimento;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.operacional.SistemaEsgoto;
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

public class InserirSistemaEsgotoAction extends GcomAction {


	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

			ActionForward retorno = actionMapping.findForward("telaSucesso");
			
			HttpSession sessao = httpServletRequest.getSession(false);
			
			Fachada fachada = Fachada.getInstancia();
			
			InserirSistemaEsgotoActionForm inserirSistemaEsgotoActionForm = (InserirSistemaEsgotoActionForm) actionForm;
			
			Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
			
			SistemaEsgoto sistemaEsgoto = new SistemaEsgoto();
			
			//Atualiza a entidade com os valores do formulário
			inserirSistemaEsgotoActionForm.setFormValues(sistemaEsgoto);
			
			//Inserir na base de dados Sistema de Esgoto
			Integer idSistemaEsgoto = fachada.inserirSistemaEsgoto(sistemaEsgoto,usuarioLogado);
			
			sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarSistemaEsgotoAction.do");
			
			//Montar a página de sucesso
			montarPaginaSucesso(httpServletRequest,
					"Sistema de Esgoto de código "+ idSistemaEsgoto +" inserido com sucesso.",
					"Inserir outro Sistema de Esgoto","exibirInserirSistemaEsgotoAction.do?menu=sim",
					"exibirAtualizarSistemaEsgotoAction.do?idRegistroInseridoAtualizar="+ 
					idSistemaEsgoto,"Atualizar Sistema de Esgoto");
			
			sessao.removeAttribute("InserirSistemaEsgotoActionForm");
	
			return retorno;
	}
}
