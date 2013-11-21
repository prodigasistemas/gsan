package gcom.gui;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

public class SessaoHttpListener implements HttpSessionListener ,HttpSessionAttributeListener{

	private static Map<String, HttpSession> listaSessoesAtivas = new HashMap<String, HttpSession>();
	
	public static Map<String, HttpSession> listaSessoesAtivasGis = new HashMap<String, HttpSession>();
	
	public static Logger log;

	static { 
		      log = Logger.getLogger("GSAN");
	}

	
	public void sessionCreated(HttpSessionEvent e) {

	}

	public void sessionDestroyed(HttpSessionEvent e) {
		//if (!listaSessoesAtivas.isEmpty()) {

			HttpSession session = e.getSession();
			String loginUsuarioSessao = (String) session
					.getAttribute("loginUsuarioSessao");

			if (loginUsuarioSessao != null
					&& !loginUsuarioSessao
							.contentEquals("USUARIO_SEGUNDA_VIA_INTERNET")) {
				listaSessoesAtivas.remove(loginUsuarioSessao);
				
			}
			session = null;
		//}
	}

	public static void registrarAcessoUsuario(HttpSession session,
			SistemaParametro sistemaParametro) {
		if (sistemaParametro.getIndicadorLoginUnico() != null
				&& sistemaParametro.getIndicadorLoginUnico().equals(
						ConstantesSistema.SIM)) {

			String loginUsuarioSessao = (String) session
					.getAttribute("loginUsuarioSessao");

			if (loginUsuarioSessao != null
					&& !loginUsuarioSessao
							.contentEquals("USUARIO_SEGUNDA_VIA_INTERNET")) {
				HttpSession sessaoUsuarioJaExistente = listaSessoesAtivas
						.get(loginUsuarioSessao);

				if (sessaoUsuarioJaExistente != null) {
					// Derruba a sessão já existente
					listaSessoesAtivas.remove(loginUsuarioSessao);

					try {
						sessaoUsuarioJaExistente.invalidate();

					} catch (IllegalStateException e) {

					}

				}
			}

			listaSessoesAtivas.put(loginUsuarioSessao, session);
		}
	}

	public void attributeAdded(HttpSessionBindingEvent se) {	

			HttpSession session = se.getSession();
			
			String marcadorGis = se.getName();		
			
			if(marcadorGis != null && marcadorGis.equals("gis")){
				
				Usuario usuarioSessao = (Usuario) session
				.getAttribute("usuarioLogado");		

				listaSessoesAtivasGis.put(usuarioSessao.getLogin(), session);

			}		
		
			if (se.getValue() instanceof Collection && ((Collection)se.getValue()).size() > 10) {
				String mensagem = se.getName()+":"+((Collection)se.getValue()).size();
				log.debug(mensagem);
				
			}
	}

	public void attributeRemoved(HttpSessionBindingEvent se) {
		if (!listaSessoesAtivasGis.isEmpty()) {

			HttpSession session = se.getSession();

			String nomeAtributo = se.getName();

			Usuario usuarioSessao = null;
			try {
				usuarioSessao = (Usuario) session.getAttribute("usuarioLogado");
			} catch (IllegalStateException e) {

			}
	

			if (usuarioSessao != null && nomeAtributo.equals("gis")) {
				listaSessoesAtivasGis.remove(usuarioSessao.getLogin());
			}
		}
		
	}

	public void attributeReplaced(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		
	}

	
	

}
