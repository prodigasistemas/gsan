package gcom.gui.gerencial.cobranca;

import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cobranca.RelatorioResumoAcoesCobrancaEventuais;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



public class GerarRelatorioResumoAcoesCobrancaEventuaisAction extends ExibidorProcessamentoTarefaRelatorio{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		
		Collection colecaoResumoAcaoCobranca =
			(Collection) this.getSessao(httpServletRequest).getAttribute("colecaoResumoAcaoCobranca");
		
		String tipoRelatorio;		
		tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		String tipoAcaoCobranca = (String) httpServletRequest.getParameter("id");
				
		// Parte que vai mandar o relatório para a tela
		
		// cria uma instância da classe do relatório
		RelatorioResumoAcoesCobrancaEventuais  relatorioResumoAcoesCobrancaEventuais = new RelatorioResumoAcoesCobrancaEventuais(
				(Usuario) (httpServletRequest.getSession(false))
						.getAttribute("usuarioLogado"),"Relatório Resumo das Ações de Cobrança Eventuais");
		
		relatorioResumoAcoesCobrancaEventuais.addParametro("tipoRelatorio", tipoRelatorio);
		relatorioResumoAcoesCobrancaEventuais.addParametro("colecaoResumoAcaoCobranca", colecaoResumoAcaoCobranca);
		relatorioResumoAcoesCobrancaEventuais.addParametro("tipoAcaoCobranca", tipoAcaoCobranca);
		
		try {
			retorno = processarExibicaoRelatorio(
					relatorioResumoAcoesCobrancaEventuais, tipoRelatorio,
					httpServletRequest, httpServletResponse, actionMapping);
		} catch (RelatorioVazioException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}
		
		return retorno;
	}

}