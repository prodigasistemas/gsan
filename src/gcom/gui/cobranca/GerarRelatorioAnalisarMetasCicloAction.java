package gcom.gui.cobranca;

import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.RelatorioAnalisarMetasCiclo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioAnalisarMetasCicloAction extends ExibidorProcessamentoTarefaRelatorio {

	@Override
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm actionForm, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		Usuario usuario = (Usuario) request.getSession(false).getAttribute("usuarioLogado");
		
		AnalisarMetasCicloActionForm form = (AnalisarMetasCicloActionForm) actionForm;
		
		int tipoRelatorio = TarefaRelatorio.TIPO_PDF;
		try {
			tipoRelatorio = Integer.parseInt(request.getParameter("tipoRelatorio")); 
		} catch (NumberFormatException e) { }
		
		
		String idCicloMeta = form.getIdCicloMeta();
		String idAcaoCobranca = form.getIdCobrancaAcao();
		String anoMesCobranca = form.getAnoMesCobranca();
		
//		if (cicloMeta == null) {
//			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
//		}
		
		RelatorioAnalisarMetasCiclo relatorio = new RelatorioAnalisarMetasCiclo(usuario);
		relatorio.addParametro("idCicloMeta", idCicloMeta);
		relatorio.addParametro("idAcaoCobranca",idAcaoCobranca);
		relatorio.addParametro("anoMesCobranca",anoMesCobranca);
		relatorio.addParametro("tipoRelatorio", tipoRelatorio);
		return processarExibicaoRelatorio(
			relatorio, tipoRelatorio, request, response, mapping);
	
		
		
	}
}
