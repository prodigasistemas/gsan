package gcom.gui.relatorio.cobranca;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cobranca.FiltroCobrancaAcaoAtividadeComando;
import gcom.cobranca.FiltroCobrancaAcaoAtividadeCronograma;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.RelatorioComandosAcaoCobrancaCronograma;
import gcom.relatorio.cobranca.RelatorioComandosAcaoCobrancaEventual;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

/**
 * Descrição da classe
 * Classe responsável pela chamada
 * do Relatorio Acompanhamento de Comandos de Ação de Cobrança
 * 
 * @author Anderson Italo
 * @date 08/10/2009
 */
public class GerarRelatorioFiltrarComandoAcaoCobranca extends ExibidorProcessamentoTarefaRelatorio {
	
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

		TarefaRelatorio relatorio = null;
		
		if (session.getAttribute("colecaoCobrancaAcaoAtividadeEventual") != null){
			relatorio = new RelatorioComandosAcaoCobrancaEventual(usuario);
			FiltroCobrancaAcaoAtividadeComando filtroCobrancaAcaoAtividadeComando = (FiltroCobrancaAcaoAtividadeComando) session.getAttribute("filtroCobrancaAcaoAtividadeComando");
			relatorio.addParametro("filtroCobrancaAcaoAtividadeComando", filtroCobrancaAcaoAtividadeComando);
		}else if (session.getAttribute("colecaoCobrancaAcaoAtividadeCronograma") != null){
			relatorio = new RelatorioComandosAcaoCobrancaCronograma(usuario);
			FiltroCobrancaAcaoAtividadeCronograma filtroCobrancaAcaoAtividadeCronograma = (FiltroCobrancaAcaoAtividadeCronograma) session.getAttribute("filtroCobrancaAcaoAtividadeCronograma");
			relatorio.addParametro("filtroCobrancaAcaoAtividadeCronograma", filtroCobrancaAcaoAtividadeCronograma);
		}else{
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		relatorio.addParametro("tipoRelatorio", tipoRelatorio);
		return processarExibicaoRelatorio(
				relatorio, tipoRelatorio, request, response, mapping);
	}

}
