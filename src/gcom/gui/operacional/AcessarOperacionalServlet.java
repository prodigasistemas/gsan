package gcom.gui.operacional;

import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;

import br.com.prodigasistemas.gsan.relatorio.ProdutoReportDTO;
import br.com.prodigasistemas.gsan.relatorio.ReportDTO;
import br.com.prodigasistemas.gsan.relatorio.ReportItemDTO;

public class AcessarOperacionalServlet extends HttpServlet {
	private static final long serialVersionUID = -4974087860261921547L;
	
	private Logger logger = Logger.getLogger(AcessarOperacionalServlet.class);

	public void teste(HttpServletResponse response) throws Exception {
		List<ReportItemDTO> produtos = new ArrayList<ReportItemDTO>();
		
		ProdutoReportDTO p1 = new ProdutoReportDTO();
		p1.setDescricao("Profenid");
		p1.setUnidadeMedida("Kg");
		produtos.add(p1);
		
		ReportDTO report = new ReportDTO(ProdutoReportDTO.class);
		report.addLinhas(produtos);
		
		GcomAction action = new GcomAction();
		action.requestReport(response, report);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			teste(response);
			
//			Fachada fachada = Fachada.getInstancia();
//
//			String nomeUsuario = getNomeUsuario(request);
//			String token = geraCodigoToken(nomeUsuario);
//			
//			StringBuilder builder = new StringBuilder();
//			builder.append("http://")
//				.append(fachada.getInstancia().retornaIpServidorOperacional())
//				.append("/GoperaWeb/")
//				.append("?usuario=").append(nomeUsuario)
//				.append("&token=").append(token);
//			response.sendRedirect(builder.toString());
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