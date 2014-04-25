package gcom.gui.integracao;

import gcom.gui.GcomAction;
import gcom.gui.SessaoHttpListener;
import gcom.integracao.GisRetornoMotivo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action utilizado
 * 
 * 
 * 
 * @author Genival Barbosa
 * @since 06/05/2009
 */
public class ProcessarCoordenadasGisAction extends GcomAction {
	
	/**
	 * Action que captura as requisições vindas da Integração do Gis com o Gsan. 
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		
		ActionForward actionForward = null;			
		
		//RETORNO_MOTIVO GSAN			
		Integer retorno = GisRetornoMotivo.OPERACAO_SUCESSO;	
			
		String loginUsuario = request.getParameter("usur_nmlogin");
		
		//Flag usada para verificar se a requisição gis veio da tela correta. 
		//inserção de um R.A (Aba nº 02 - Dados do local de ocorrência
		HttpSession sessaoUsuario = SessaoHttpListener.listaSessoesAtivasGis.get(loginUsuario);
		
		if(sessaoUsuario == null){
			retorno = GisRetornoMotivo.LOGIN_USUARIO_INEXISTENTE;		
		}else{
			//Boolean marcadorGis = (Boolean) sessao.getAttribute("gis");
		     
		    GisHelper gisHelper = new GisHelper();
		     
				
				//Login do usuário
					
				if(loginUsuario==null){
					retorno = GisRetornoMotivo.LOGIN_USUARIO_INEXISTENTE;
				}		
				
				//Coordenada norte da ocorrência
				String nnCoordenadaNorte = request.getParameter("rgat_nncoordenadanorte");	
				if("".equals(nnCoordenadaNorte) || nnCoordenadaNorte == null){
					retorno = GisRetornoMotivo.COORDENADA_NORTE_INVALIDA;
				}else{
					gisHelper.setNnCoordenadaNorte(nnCoordenadaNorte);
				}
				
				//Coordenada leste da ocorrência
				String nnCoordenadaLeste = request.getParameter("rgat_nncoordenadaleste");
				if("".equals(nnCoordenadaLeste) || nnCoordenadaLeste == null){
					retorno = GisRetornoMotivo.COORDENADA_LESTE_INVALIDA;
				}else{
					gisHelper.setNnCoordenadaLeste(nnCoordenadaLeste);
				}							
				
				//Colocando o helper na sessão.
				sessaoUsuario.setAttribute("gisHelper",gisHelper);	
		}
		
	
			try{
			
		    PrintWriter pw = response.getWriter();	  
		    pw.println(retorno);	
		    pw.flush();
	        
		     } catch (IOException e) {
		           e.printStackTrace();
		     }

		
		return actionForward;
	}
}
