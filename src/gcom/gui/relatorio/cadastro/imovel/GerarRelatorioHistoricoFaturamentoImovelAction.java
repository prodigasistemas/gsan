package gcom.gui.relatorio.cadastro.imovel;

import gcom.gui.cadastro.imovel.ConsultarImovelActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.imovel.RelatorioHistoricoFaturamentoImovel;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *Classe com a lógica de geração do relatorio de Histórico de Faturamento
 *do wizard de Consultar Imovel
 *
 * @author Marlon Patrick
 * @since 28/09/2009
 */
public class GerarRelatorioHistoricoFaturamentoImovelAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {


		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		HttpSession sessao = httpServletRequest.getSession(false);

		ConsultarImovelActionForm consultarImovelForm = (ConsultarImovelActionForm) actionForm;

		RelatorioHistoricoFaturamentoImovel relatorioHistoricoFaturamento = criarRelatorioComParametros(
				consultarImovelForm, sessao, tipoRelatorio);

		ActionForward retorno = null;

		try {
			retorno = processarExibicaoRelatorio(relatorioHistoricoFaturamento,
					tipoRelatorio, httpServletRequest, httpServletResponse,actionMapping);

		} catch (SistemaException ex) {
			reportarErros(httpServletRequest, "erro.sistema");
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			reportarErros(httpServletRequest, "erro.relatorio.vazio");
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		return retorno;
	}
	
	/**
	 *Esse método cria o objeto RelatorioDadosCadastraisImovel,
	 *adiciona os parametros necessários ao seu funcionamento e o retorna.
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private RelatorioHistoricoFaturamentoImovel criarRelatorioComParametros(ConsultarImovelActionForm consultarImovelForm,
			HttpSession sessao, String tipoRelatorio) {
		
		RelatorioHistoricoFaturamentoImovel relatorioHistoricoFaturamento = new RelatorioHistoricoFaturamentoImovel((Usuario)sessao.getAttribute("usuarioLogado"));
		
		relatorioHistoricoFaturamento.addParametro("colecaoContaImovel",sessao.getAttribute("colecaoContaImovel"));
		relatorioHistoricoFaturamento.addParametro("colecaoContaHistoricoImovel",sessao.getAttribute("colecaoContaHistoricoImovel"));
		relatorioHistoricoFaturamento.addParametro("colecaoDebitoACobrarImovel",sessao.getAttribute("colecaoDebitoACobrarImovel"));
		relatorioHistoricoFaturamento.addParametro("colecaoDebitoACobrarHistoricoImovel",sessao.getAttribute("colecaoDebitoACobrarHistoricoImovel"));
		relatorioHistoricoFaturamento.addParametro("colecaoCreditoARealizarImovel",sessao.getAttribute("colecaoCreditoARealizarImovel"));
		relatorioHistoricoFaturamento.addParametro("colecaoCreditoARealizarHistoricoImovel",sessao.getAttribute("colecaoCreditoARealizarHistoricoImovel"));
		relatorioHistoricoFaturamento.addParametro("colecaoGuiaPagamentoImovel",sessao.getAttribute("colecaoGuiaPagamentoImovel"));
		relatorioHistoricoFaturamento.addParametro("colecaoGuiaPagamentoHistoricoImovel",sessao.getAttribute("colecaoGuiaPagamentoHistoricoImovel"));
		
		relatorioHistoricoFaturamento.addParametro("consultarImovelForm",consultarImovelForm);
		relatorioHistoricoFaturamento.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		
		return relatorioHistoricoFaturamento;
	}


}
