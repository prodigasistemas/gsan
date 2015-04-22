package gcom.gui.batch;

import gcom.fachada.Fachada;
import gcom.gui.operacional.AcessarOperacionalServlet;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;

public class AcessarNovoBatchServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1208477970667658217L;

	private Logger logger = Logger.getLogger(AcessarOperacionalServlet.class);

	private static final String CONTENT_TYPE = "text/html";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);

		try {
			URL url;
			URLConnection    urlConn;
			DataOutputStream cgiInput;
	
			String nomeUsuario = getNomeUsuario(request);
			String token = geraCodigoToken(nomeUsuario);
			
			Fachada fachada = Fachada.getInstancia();
			
			StringBuilder builder = new StringBuilder();
			
			builder.append("http://")
					.append(fachada.getInstancia().retornaIpServidorNovoBatch())
					.append("/autenticar_usuario");
			
			url = new URL(builder.toString());
			urlConn = url.openConnection();
	
			urlConn.setDoInput(true);
			urlConn.setDoOutput(true);
			urlConn.setUseCaches(false);
			urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	
			cgiInput = new DataOutputStream(urlConn.getOutputStream());

			StringBuilder content = new StringBuilder();
			
			content.append("usuario=").append(nomeUsuario)
					.append("token=").append(token);
			
			cgiInput.writeBytes(content.toString());
			cgiInput.flush();
			cgiInput.close();
			
			response.sendRedirect(urlConn.getURL().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Fachada fachada = Fachada.getInstancia();

			String nomeUsuario = getNomeUsuario(request);
			String token = geraCodigoToken(nomeUsuario);
			
			StringBuilder builder = new StringBuilder();
			builder.append("http://")
					.append(fachada.getInstancia().retornaIpServidorNovoBatch())
					.append("/autenticar_usuario")
					.append("?usuario=").append(nomeUsuario)
					.append("&token=").append(token);
			
			System.out.println("Marcelo");
			
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
