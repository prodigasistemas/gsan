package gcom.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import gcom.fachada.Fachada;
import gcom.seguranca.FiltroSegurancaParametro;
import gcom.seguranca.SegurancaParametro;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroSimples;

public class GerenciadorSSO {
	private Cookie[] cookies;
	private String conteudoDoCookie;
	private HttpServletRequest httpRequest;
	private HttpClient http = new HttpClient();
	private String token;

	public GerenciadorSSO(HttpServletRequest httpRequest) throws IOException {
		this.httpRequest = httpRequest;
		this.cookies = httpRequest.getCookies();
		this.validarToken();
		this.setSessions();
	}

	public boolean isOk() throws IOException {
		return http.getStatusCode() == 200;
	}
	
	public boolean isLogado() throws IOException {
		return isOk();
	}

	public String getConteudoDoCookie() {
		return this.conteudoDoCookie;
	}

	public String getToken() {
		return this.token;
	}

	private void setSessions() throws IOException {
		if (this.isOk() && !usuarioAutenticado()) {
			
			HttpSession session = httpRequest.getSession(false);
			
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, this.getConteudoDoCookie()));
			filtroUsuario.adicionarParametro(new ParametroNaoNulo(FiltroUsuario.ID));
			
			Usuario usuario = (Usuario) Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName()).iterator().next();
			
			session.setAttribute("usuarioLogado", usuario);
			
			Fachada.getInstancia().montarMenuUsuario(session, httpRequest.getRemoteAddr());
		}
	}

	public boolean usuarioAutenticado() {
		HttpSession session = httpRequest.getSession();
		
		if (session != null) {
			Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
			if (usuario != null) {
				return true;
			}
		}

		return false;
	}

	private void validarToken() throws IOException {
		if (existeAlgumCookie())
			this.token = getCookie().getValue();
		
		FiltroSegurancaParametro filtro = new FiltroSegurancaParametro();
		filtro.adicionarParametro(new ParametroSimples(FiltroSegurancaParametro.NOME, SegurancaParametro.NOME_PARAMETRO_SEGURANCA.URL_SEGURANCA.name()));
		filtro.adicionarParametro(new ParametroNaoNulo(FiltroSegurancaParametro.NOME));
		
		SegurancaParametro parametro = (SegurancaParametro) Fachada.getInstancia().pesquisar(filtro, SegurancaParametro.class.getName()).iterator().next();
		
		this.conteudoDoCookie = http.GetPageContent(parametro.getValor() + "/authorization?token=" + token);
	}

	private boolean existeAlgumCookie() {
		return this.cookies != null && getCookie() != null;
	}

	private Cookie getCookie() {
		Cookie cookieGsan = null;

		for (Cookie cookie : cookies) {
			if (cookie != null) {
				if (cookie.getName().equals("gsan")) {
					cookieGsan = cookie;
				}
			}
		}

		return cookieGsan;
	}
}
