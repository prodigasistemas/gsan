package gcom.gui.relatorio.atendimentopublico;

import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.atendimentopublico.RelatorioRegistroAtendimentoPorUnidadePorUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioRegistroAtendimentoPorUnidadePorUsuarioAction extends ExibidorProcessamentoTarefaRelatorio {

	@Override
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm actionForm, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

		Usuario usuario = (Usuario) request.getSession(false).getAttribute("usuarioLogado");
		GerarRelatorioRegistroAtendimentoPorUnidadePorUsuarioActionForm form = (GerarRelatorioRegistroAtendimentoPorUnidadePorUsuarioActionForm) actionForm;
		
		// ------------------------------
		// -- Parametros
		// ------------------------------
		Short situacao = null;
		Date dataAtendimentoInicial = null;
		Date dataAtendimentoFinal = null;
		Integer idUnidadeAtendimento = null;
		
		// ------------------------------
		// -- Situacao
		// ------------------------------
		if (form.getSituacaoRA() != null && !form.getSituacaoRA().trim().equals("")) {
			situacao = new Short(form.getSituacaoRA());
		}
		
				
		// ------------------------------
		// -- Data de Atendimento Inicial
		// ------------------------------
		if (form.getDataAtendimentoInicial() != null && !form.getDataAtendimentoInicial().trim().equals("")) {
			dataAtendimentoInicial = Util.converteStringParaDate(form.getDataAtendimentoInicial());
		} else {
			throw new ActionServletException("atencao.required", null, "Data de Atendimento Inicial");
		}
		
		// ------------------------------
		// -- Data de Atendimento Final
		// ------------------------------
		if (form.getDataAtendimentoFinal() != null && !form.getDataAtendimentoFinal().trim().equals("")) {
			dataAtendimentoFinal = Util.converteStringParaDate(form.getDataAtendimentoFinal());
		} else {
			dataAtendimentoFinal = dataAtendimentoInicial;
		}
		
		if ((dataAtendimentoFinal.getTime() - dataAtendimentoInicial.getTime()) > 1000L * 60L * 60L * 24L * 31L) {
			throw new ActionServletException("atencao.filtrar_intervalo_limite", null, "Data de Atendimento");
		}
		
		dataAtendimentoFinal = Util.adaptarDataFinalComparacaoBetween(dataAtendimentoFinal);
		
		// ------------------------------
		// -- Unidade de Negócios
		// ------------------------------
		if (form.getIdUnidadeAtendimento() != null && !form.getIdUnidadeAtendimento().trim().equals("")) {
			idUnidadeAtendimento = new Integer(form.getIdUnidadeAtendimento());
		}
		
		// ------------------------------
		// -- Tipo do Relatorio
		// ------------------------------
		int tipoRelatorio = TarefaRelatorio.TIPO_PDF;
		try {
			tipoRelatorio = Integer.parseInt(request.getParameter("tipoRelatorio")); 
		} catch (NumberFormatException e) { }
		
		// ------------------------------
		// -- Geracao do Relatorio
		// ------------------------------
		RelatorioRegistroAtendimentoPorUnidadePorUsuario relatorio = new RelatorioRegistroAtendimentoPorUnidadePorUsuario(usuario);
		relatorio.addParametro("situacao", situacao);
		relatorio.addParametro("dataAtendimentoInicial", dataAtendimentoInicial);
		relatorio.addParametro("dataAtendimentoFinal", dataAtendimentoFinal);
		relatorio.addParametro("idUnidadeAtendimento", idUnidadeAtendimento);
		relatorio.addParametro("tipoRelatorio", tipoRelatorio);
		
		return processarExibicaoRelatorio(
				relatorio, tipoRelatorio, request, response, mapping);
	}
	
}
