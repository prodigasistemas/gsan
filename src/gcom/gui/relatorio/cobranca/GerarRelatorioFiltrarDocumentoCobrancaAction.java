package gcom.gui.relatorio.cobranca;

import gcom.cobranca.bean.FiltrarDocumentoCobrancaHelper;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.RelatorioFiltrarDocumentoCobranca;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * Classe responsável pela chamada
 * do Relatorio Filtrar Documento de Cobranca
 * 
 * @author Anderson Italo
 * @date 17/08/2009
 */
public class GerarRelatorioFiltrarDocumentoCobrancaAction extends ExibidorProcessamentoTarefaRelatorio {
	
	@Override
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm actionForm, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		Usuario usuario = (Usuario) request.getSession(false).getAttribute("usuarioLogado");
		HttpSession session = request.getSession();
		
		int tipoRelatorio = TarefaRelatorio.TIPO_PDF;
		try {
			tipoRelatorio = Integer.parseInt(request.getParameter("tipoRelatorio")); 
		} catch (NumberFormatException e) { }
		
		FiltrarDocumentoCobrancaHelper filtroCobrancaDocumento = (FiltrarDocumentoCobrancaHelper)session.getAttribute("filtrarDocumentoCobrancaHelper");
		
		if (filtroCobrancaDocumento == null) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		RelatorioFiltrarDocumentoCobranca relatorio = new RelatorioFiltrarDocumentoCobranca(usuario);
		relatorio.addParametro("filtroCobrancaDocumento", filtroCobrancaDocumento);
		relatorio.addParametro("tipoRelatorio", tipoRelatorio);
		return processarExibicaoRelatorio(
				relatorio, tipoRelatorio, request, response, mapping);
	
		
		
	}

}
