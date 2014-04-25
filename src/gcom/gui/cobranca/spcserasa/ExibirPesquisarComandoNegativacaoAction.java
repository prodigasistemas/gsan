package gcom.gui.cobranca.spcserasa;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC 0653] Pesquisar Comando Negativação
 * 
 * @author Kássia Albuquerque
 * @date 22/10/2007
 */


public class ExibirPesquisarComandoNegativacaoAction extends GcomAction {

	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

			ActionForward retorno = actionMapping.findForward("pesquisarComandoNegativacao");	
			
			Fachada fachada = Fachada.getInstancia();
			
			HttpSession sessao = httpServletRequest.getSession(false);
			
			PesquisarComandoNegativacaoActionForm form = (PesquisarComandoNegativacaoActionForm) actionForm;
					
				
			// SETANDO OS CAMPOS QUE VÊM MARCADO INICIALMENTE NO FORM	
			if (httpServletRequest.getParameter("menu") != null && !httpServletRequest.getParameter("menu").equals("")) {
				
				form.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
				form.setIndicadorComandoSimulado(ConstantesSistema.TODOS.toString());
				
				if (httpServletRequest.getParameter("menu").equals("sim")) {
					form.setPopup(ConstantesSistema.NAO.toString());
					sessao.setAttribute("popup", ConstantesSistema.NAO.toString());
				} else if (httpServletRequest.getParameter("menu").equals("ok")) {
					form.setPopup(ConstantesSistema.SIM.toString());
					sessao.setAttribute("popup", ConstantesSistema.SIM.toString());
				}
			}				
			
			if (httpServletRequest.getParameter("APAGAR")!= null){
				
				sessao.removeAttribute("collectionComandoNegativacao");
				sessao.removeAttribute("totalRegistrosPrimeiraPaginacao");
				sessao.removeAttribute("numeroPaginasPesquisaPrimeiraPaginacao");
			}
			
			
			// FILTRAR USUARIO RESPONSAVEL
			
			if (form.getUsuarioResponsavelId()!= null && !form.getUsuarioResponsavelId().equals("")){
				
				FiltroUsuario filtroUsuarioResponsavel = new FiltroUsuario();
				filtroUsuarioResponsavel.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, form.getUsuarioResponsavelId()));
				Collection colecaoUsuarioResponsavel = fachada.pesquisar(filtroUsuarioResponsavel, Usuario.class.getName());

				if (colecaoUsuarioResponsavel != null && !colecaoUsuarioResponsavel.isEmpty()) {
					
					Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuarioResponsavel);
					form.setUsuarioResponsavelNome(usuario.getNomeUsuario());
					sessao.setAttribute("usuarioResponsavelEncontrada", "true");
					
				} else {
					
					sessao.removeAttribute("usuarioResponsavelEncontrada");
					form.setUsuarioResponsavelNome("Usuário inexistente");
					form.setUsuarioResponsavelId("");
				}
			}
			
			//Recupera os dados do popup de usuário responsável
			if (httpServletRequest.getParameter("tipoConsulta") != null
					&& !httpServletRequest.getParameter("tipoConsulta").equals("")) {
				//Recupera os dados do popup de usuário		
				if (httpServletRequest.getParameter("tipoConsulta").equals(
							"usuario")) {

					form.setUsuarioResponsavelId(httpServletRequest.getParameter("idCampoEnviarDados"));
					form.setUsuarioResponsavelNome(httpServletRequest
							.getParameter("descricaoCampoEnviarDados"));	
					sessao.setAttribute("usuarioResponsavelEncontrada", "true");

				}				
			}

	
			return retorno;
	}

}
