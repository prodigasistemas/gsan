package gcom.gui.relatorio.cadastro.imovel;

import gcom.gui.cadastro.imovel.ConsultarRelacaoClienteImovelActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisRelacionadosCliente;
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
 *Classe contem a lógica do relatorio de imoveis relacionados com o cliente.
 *
 * @author Marlon Patrick
 * @since 11/09/2009
 */
public class GerarRelatorioImoveisRelacionadosClienteAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = String.valueOf(TarefaRelatorio.TIPO_PDF);
		}

		HttpSession sessao = httpServletRequest.getSession(false);

		ConsultarRelacaoClienteImovelActionForm consultarRelacaoClienteImovelActionForm = (ConsultarRelacaoClienteImovelActionForm) actionForm;

		RelatorioImoveisRelacionadosCliente relatorioImoveisRelacionadosCliente = criarRelatorioComParametros(
				consultarRelacaoClienteImovelActionForm, sessao, tipoRelatorio);

		ActionForward retorno = null;

		try {
			retorno = processarExibicaoRelatorio(relatorioImoveisRelacionadosCliente,
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
	 *Esse método cria o objeto RelatorioImoveisRelacionadosCliente,
	 *adciona os parametros necessários ao seu funcionamento e o retorna.
	 *
	 *@since 11/09/2009
	 *@author Marlon Patrick
	 *
	 * Adicionado o parametro com a quantidade de imovéis 
	 * diferentes que o cliente possui.
	 * 
	 * @since 29/07/2011
	 * @author Davi Menezes  
	 */
	private RelatorioImoveisRelacionadosCliente criarRelatorioComParametros(ConsultarRelacaoClienteImovelActionForm consultarRelacaoClienteImovelActionForm,
			HttpSession sessao, String tipoRelatorio) {
		
		RelatorioImoveisRelacionadosCliente relatorioImoveisRelacionadosCliente = new RelatorioImoveisRelacionadosCliente((Usuario)sessao.getAttribute("usuarioLogado"));
		
		relatorioImoveisRelacionadosCliente.addParametro("cliente",sessao.getAttribute("cliente"));
		relatorioImoveisRelacionadosCliente.addParametro("clienteEndereco",sessao.getAttribute("clienteEndereco"));
		relatorioImoveisRelacionadosCliente.addParametro("collClienteImovel",sessao.getAttribute("collClienteImovel"));
		relatorioImoveisRelacionadosCliente.addParametro("collClienteImovelEconomia",sessao.getAttribute("collClienteImovelEconomia"));
		relatorioImoveisRelacionadosCliente.addParametro("consultarRelacaoClienteImovelActionForm",consultarRelacaoClienteImovelActionForm);
		relatorioImoveisRelacionadosCliente.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		relatorioImoveisRelacionadosCliente.addParametro("quantidadeImoveis", sessao.getAttribute("qtdImoveis"));
		
		return relatorioImoveisRelacionadosCliente;
	}

}
