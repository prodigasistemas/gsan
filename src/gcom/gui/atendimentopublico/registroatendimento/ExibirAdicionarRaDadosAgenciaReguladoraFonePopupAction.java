package gcom.gui.atendimentopublico.registroatendimento;

import gcom.cadastro.cliente.FiltroFoneTipo;
import gcom.cadastro.cliente.FoneTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0459] Adicionar Ra Dados Agencia Reguladora Fone Popup 
 * 
 * @author Kassia Albuquerque
 * @created 13/04/2006
 */


public class ExibirAdicionarRaDadosAgenciaReguladoraFonePopupAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

			ActionForward retorno = actionMapping.findForward("exibirAdicionarRaDadosAgenciaReguladoraFonePopup");
			
			AdicionarRaDadosAgenciaReguladoraFonePopupActionForm form = 
					(AdicionarRaDadosAgenciaReguladoraFonePopupActionForm)actionForm;
			
			HttpSession sessao = httpServletRequest.getSession(false);
			
			Fachada fachada = Fachada.getInstancia();
			
			httpServletRequest.setAttribute("nomeCampo", "foneTipo");
			
			
			 //------------- Tipo do Fone------------------
			
			 FiltroFoneTipo filtroFoneTipo = new FiltroFoneTipo();
			 
			 filtroFoneTipo.adicionarParametro(new ParametroSimples(FiltroFoneTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
				
			 Collection colecaoFoneTipo = fachada.pesquisar(filtroFoneTipo,FoneTipo.class.getName());
			 
			 httpServletRequest.setAttribute("colecaoFoneTipo", colecaoFoneTipo);
			 
			 sessao.setAttribute("colecaoFoneTipo", colecaoFoneTipo);
			 
			 
			
			//-------------- Indicador de Fone Padrao----------
			
			form.setIndicadorFonePadrao("1");	
			
			
			//-----Verificando se a coleçao de fones esta vazia------
			
			if(httpServletRequest.getParameter("limparForm") != null || httpServletRequest.getAttribute("limparForm") != null){
				form.setFoneTipo("");
				form.setIndicadorFonePadrao("1");
				form.setDdd("");
				form.setNumeroTelefone("");
				form.setRamal("");
			}
			
			
			return retorno;
		}
}
