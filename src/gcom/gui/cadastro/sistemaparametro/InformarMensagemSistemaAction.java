package gcom.gui.cadastro.sistemaparametro;

import gcom.cadastro.sistemaparametro.SistemaParametro;
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
 * [UC__?__] Informar Mensagem do Sistema
 * 
 * @author Kassia Albuquerque
 * @created 27/02/2007
 */


public class InformarMensagemSistemaAction extends GcomAction {


		public ActionForward execute(ActionMapping actionMapping,
				ActionForm actionForm, HttpServletRequest httpServletRequest,
				HttpServletResponse httpServletResponse) {
			
			ActionForward retorno = actionMapping.findForward("telaSucesso");
			
			InformarMensagemSistemaActionForm informarMensagemSistemaActionForm = (InformarMensagemSistemaActionForm) actionForm;
			
			Fachada fachada = Fachada.getInstancia();
			
			HttpSession sessao = httpServletRequest.getSession(false);
			
			Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
			
			SistemaParametro sistemaParametro = null;
			
			if(sessao.getAttribute("sistemaParametro") != null){
			   sistemaParametro = (SistemaParametro) sessao.getAttribute("sistemaParametro");
			}
			// Atualiza a entidade com os valores do formulário
			String mensagemSistema = informarMensagemSistemaActionForm.getMensagemSistema();
			
			sistemaParametro.setMensagemSistema(mensagemSistema);
			
			//Atualizar na base de dados Mensagem Sistema
			fachada.atualizarMensagemSistema(sistemaParametro,usuarioLogado);
			
			// Monta a página de sucesso
			montarPaginaSucesso(httpServletRequest, "Mensagem do Sistema inserida com sucesso.", "Inserir outra Mensagem do Sistema",
					"exibirInformarMensagemSistemaAction.do");
			
			sessao.removeAttribute("InformarMensagemSistemaActionForm");
			
			return retorno;
		}}
