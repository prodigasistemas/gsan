package gcom.gui.cobranca;

import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.RelatorioAcompanhamentoAcoesCobranca;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioAcompanhamentoAcoesCobrancaAction extends ExibidorProcessamentoTarefaRelatorio {

	@Override
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm actionForm, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		Usuario usuario = (Usuario) request.getSession(false).getAttribute("usuarioLogado");
		
		ExibirRelatorioAcompanhamentoAcoesCobrancaActionForm form = (ExibirRelatorioAcompanhamentoAcoesCobrancaActionForm) actionForm;
		
		int tipoRelatorio = TarefaRelatorio.TIPO_PDF;
		try {
			tipoRelatorio = Integer.parseInt(request.getParameter("tipoRelatorio")); 
		} catch (NumberFormatException e) { }
		
		
		String idCobrancaAcao = form.getIdCobrancaAcao();
		String dataInicial = form.getDataInicial();
		String dataFinal = form.getDataFinal();
		String chkEstado = form.getChkEstado();
		String chkGerencia = form.getChkGerencia();
		String idGerenciaRegional = form.getIdGerenciaRegional();
		String chkUnidade = form.getChkUnidade();
		String idUnidadeNegocio = form.getIdUnidadeNegocio();
		String chkLocalidade = form.getChkLocalidade();
		String idLocalidade = form.getIdLocalidade();
		String idEmpresa = form.getIdEmpresa();				
		
		form.setChkEstado("");
		form.setChkGerencia("");
		form.setChkLocalidade("");
		form.setChkUnidade("");
		
		RelatorioAcompanhamentoAcoesCobranca relatorio = new RelatorioAcompanhamentoAcoesCobranca(usuario);
		relatorio.addParametro("idCobrancaAcao", idCobrancaAcao);
		relatorio.addParametro("dataInicial",dataInicial);
		relatorio.addParametro("dataFinal",dataFinal);
		relatorio.addParametro("chkEstado",chkEstado);
		relatorio.addParametro("chkGerencia",chkGerencia);
		relatorio.addParametro("idGerenciaRegional",idGerenciaRegional);
		relatorio.addParametro("chkUnidade",chkUnidade);
		relatorio.addParametro("idUnidadeNegocio",idUnidadeNegocio);
		relatorio.addParametro("chkLocalidade",chkLocalidade);
		relatorio.addParametro("idLocalidade",idLocalidade);
		relatorio.addParametro("idEmpresa",idEmpresa);
		relatorio.addParametro("tipoRelatorio", tipoRelatorio);
		
		return processarExibicaoRelatorio(
			relatorio, tipoRelatorio, request, response, mapping);
	
		
		
	}
}
