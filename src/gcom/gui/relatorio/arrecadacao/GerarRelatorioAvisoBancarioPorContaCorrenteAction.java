package gcom.gui.relatorio.arrecadacao;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.relatorio.arrecadacao.RelatorioAvisoBancarioPorContaCorrente;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0829] Gerar Relatório Avisos Bancarios Por Conta Corrente
 * 
 * @author Victor Cisneiros
 * @date 21/08/2008
 */
public class GerarRelatorioAvisoBancarioPorContaCorrenteAction extends GcomAction {
	
	@Override
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm actionForm, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		Usuario usuario = (Usuario) request.getSession(false).getAttribute("usuarioLogado");
		GerarRelatorioAvisoBancarioPorContaCorrenteActionForm form = (GerarRelatorioAvisoBancarioPorContaCorrenteActionForm) actionForm;
		
		// ------------------------------
		// -- Parametros
		// ------------------------------
		Integer mesAno = null;
		Integer idBanco = null;
		Integer idContaBancaria = null;
		
		// ------------------------------
		// -- Mes/Ano
		// ------------------------------
		if (form.getMesAno() != null && !form.getMesAno().trim().equals("")) {
			mesAno = Integer.parseInt(form.getMesAno().substring(3, 7) + form.getMesAno().substring(0, 2));
		} else {
			throw new ActionServletException("atencao.required", null, "Mês/Ano da Arrecadação");
		}
		
		// ------------------------------
		// -- Banco
		// ------------------------------
		if (form.getIdBanco() != null && !form.getIdBanco().trim().equals("")) {
			int id = new Integer(form.getIdBanco());
			if (id != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				idBanco = id;
			}
		}
		
		// ------------------------------
		// -- Conta Bancária
		// ------------------------------
		if (form.getIdContaBancaria() != null && !form.getIdContaBancaria().trim().equals("")) {
			int id = new Integer(form.getIdContaBancaria());
			if (id != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				idContaBancaria = id;
			}
		}
		
		// ------------------------------
		// -- Tipo do Relatorio
		// ------------------------------
		int tipoRelatorio = TarefaRelatorio.TIPO_PDF;
		try {
			tipoRelatorio = Integer.parseInt(request.getParameter("tipoRelatorio")); 
		} catch (NumberFormatException e) { }
		
		// ------------------------------
		// -- Geração do Relatorio
		// ------------------------------
		RelatorioAvisoBancarioPorContaCorrente relatorio = new RelatorioAvisoBancarioPorContaCorrente(usuario);
		relatorio.addParametro("mesAno", mesAno);
		relatorio.addParametro("idBanco", idBanco);
		relatorio.addParametro("idContaBancaria", idContaBancaria);
		relatorio.addParametro("tipoRelatorio", tipoRelatorio);

		byte[] bytes = (byte[]) relatorio.executar();

		if (tipoRelatorio == TarefaRelatorio.TIPO_PDF) {
			response.addHeader("Content-Disposition", 
					"attachment; filename=relatorio.pdf");
			response.setContentType("application/pdf");
		} else if (tipoRelatorio == TarefaRelatorio.TIPO_RTF) {
			response.addHeader("Content-Disposition", 
					"attachment; filename=relatorio.rtf");
			response.setContentType("application/rtf");
		} else if (tipoRelatorio == TarefaRelatorio.TIPO_XLS) {
			response.addHeader("Content-Disposition", 
					"attachment; filename=relatorio.xls");
			response.setContentType("application/vnd.ms-excel");
		} else if (tipoRelatorio == TarefaRelatorio.TIPO_HTML) {
			response.addHeader("Content-Disposition", 
					"attachment; filename=relatorio.zip");
			response.setContentType("application/zip");
		}
		
		OutputStream out = response.getOutputStream();
		out.write(bytes);
		out.flush();
		out.close();
		
		return null;
	}

}
