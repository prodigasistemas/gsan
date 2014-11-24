package gcom.gui.operacional;

import gcom.fachada.Fachada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;

public class AcessarOperacionalServlet extends HttpServlet {
	private static final long serialVersionUID = -4974087860261921547L;
	
	private Logger logger = Logger.getLogger(AcessarOperacionalServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Fachada fachada = Fachada.getInstancia();

			String nomeUsuario = getNomeUsuario(request);
			String token = geraCodigoToken(nomeUsuario);
			
			StringBuilder builder = new StringBuilder();
			builder.append("http://")
				.append(fachada.getInstancia().retornaIpServidorOperacional())
				.append("/gsan-operacional/")
				.append("?usuario=").append(nomeUsuario)
				.append("&token=").append(token);
			response.sendRedirect(builder.toString());
		} catch (Exception e) {
			logger.error("Erro ao acessar operacional", e);
		}
	}
	
	private String geraCodigoToken(String nomeUsuario) {
		String md5 = null;
		
		if (nomeUsuario != null){
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd-HH");
			md5 = Util.md5(nomeUsuario + formato.format(Calendar.getInstance().getTime()));
		}
		
		return md5;
	}
	
	public String getNomeUsuario(HttpServletRequest request){
		HttpSession session = ((HttpServletRequest) request).getSession(false);
		
		String nomeUsuario = null;
		if (session != null){
			Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
			
			if (usuarioLogado != null){
				nomeUsuario = String.valueOf(usuarioLogado.getId());
			}
		}
		
		return nomeUsuario;
	}
}