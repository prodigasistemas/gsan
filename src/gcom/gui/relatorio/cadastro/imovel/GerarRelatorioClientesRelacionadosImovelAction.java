package gcom.gui.relatorio.cadastro.imovel;

import gcom.gui.cadastro.imovel.ConsultarRelacaoClienteImovelActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.imovel.RelatorioClientesRelacionadosImovel;
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
 *Classe com a lógica de geração do relatorio de Clientes relacionados a Imovel.
 *
 * @author Marlon Patrick
 * @since 14/09/2009
 */
public class GerarRelatorioClientesRelacionadosImovelAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {


		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		HttpSession sessao = httpServletRequest.getSession(false);

		ConsultarRelacaoClienteImovelActionForm consultarRelacaoClienteImovelActionForm = (ConsultarRelacaoClienteImovelActionForm) actionForm;

		RelatorioClientesRelacionadosImovel relatorioImoveisRelacionadosCliente = criarRelatorioComParametros(
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
	 *Esse método cria o objeto RelatorioClientesRelacionadosImovel,
	 *adciona os parametros necessários ao seu funcionamento e o retorna.
	 *
	 *@since 11/09/2009
	 *@author Marlon Patrick
	 */
	private RelatorioClientesRelacionadosImovel criarRelatorioComParametros(ConsultarRelacaoClienteImovelActionForm consultarRelacaoClienteImovelActionForm,
			HttpSession sessao, String tipoRelatorio) {
		
		RelatorioClientesRelacionadosImovel relatorioClientesRelacionadosImovel = new RelatorioClientesRelacionadosImovel((Usuario)sessao.getAttribute("usuarioLogado"));
		
		relatorioClientesRelacionadosImovel.addParametro("imovel",sessao.getAttribute("imovel"));
		relatorioClientesRelacionadosImovel.addParametro("colecaoClienteImovel",sessao.getAttribute("collClienteImovel"));
		relatorioClientesRelacionadosImovel.addParametro("colecaoImovelSubCategoriaHelper",sessao.getAttribute("collImovelSubCategoriaHelper"));
		relatorioClientesRelacionadosImovel.addParametro("consultarRelacaoClienteImovelActionForm",consultarRelacaoClienteImovelActionForm);
		relatorioClientesRelacionadosImovel.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		
		return relatorioClientesRelacionadosImovel;
	}


}
