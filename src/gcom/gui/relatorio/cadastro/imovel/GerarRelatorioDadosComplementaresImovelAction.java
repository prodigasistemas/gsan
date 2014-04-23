package gcom.gui.relatorio.cadastro.imovel;

import gcom.gui.cadastro.imovel.ConsultarImovelActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.imovel.RelatorioDadosComplementaresImovel;
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
 *Classe com a lógica de geração do relatorio de Dados Adicionais
 *do wizard de Consultar Imovel
 *
 * @author Marlon Patrick
 * @since 23/09/2009
 */
public class GerarRelatorioDadosComplementaresImovelAction extends
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

		RelatorioDadosComplementaresImovel relatorioDadosComlpementaresImovel = criarRelatorioComParametros(
				consultarImovelForm, sessao, tipoRelatorio);

		ActionForward retorno = null;

		try {
			retorno = processarExibicaoRelatorio(relatorioDadosComlpementaresImovel,
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
	 *@since 23/09/2009
	 *@author Marlon Patrick
	 */
	private RelatorioDadosComplementaresImovel criarRelatorioComParametros(ConsultarImovelActionForm consultarImovelForm,
			HttpSession sessao, String tipoRelatorio) {
		
		RelatorioDadosComplementaresImovel relatorioDadosComlpementaresImovel = new RelatorioDadosComplementaresImovel((Usuario)sessao.getAttribute("usuarioLogado"));
		
		relatorioDadosComlpementaresImovel.addParametro("colecaoVencimentosAlternativos",sessao.getAttribute("colecaoVencimentosAlternativos"));
		relatorioDadosComlpementaresImovel.addParametro("colecaoDebitosAutomaticos",sessao.getAttribute("colecaoDebitosAutomaticos"));
		relatorioDadosComlpementaresImovel.addParametro("colecaoFaturamentosSituacaoHistorico",sessao.getAttribute("colecaoFaturamentosSituacaoHistorico"));
		relatorioDadosComlpementaresImovel.addParametro("colecaoCobrancasSituacaoHistorico",sessao.getAttribute("colecaoCobrancasSituacaoHistorico"));
		relatorioDadosComlpementaresImovel.addParametro("colecaoImovelCadastroOcorrencia",sessao.getAttribute("colecaoImovelCadastroOcorrencia"));
		relatorioDadosComlpementaresImovel.addParametro("colecaoImovelEloAnormalidade",sessao.getAttribute("colecaoImovelEloAnormalidade"));
		relatorioDadosComlpementaresImovel.addParametro("colecaoImovelRamosAtividade",sessao.getAttribute("colecaoImovelRamosAtividade"));
		relatorioDadosComlpementaresImovel.addParametro("colecaoSituacoesCobranca", sessao.getAttribute("colecaoDadosImovelCobrancaSituacao"));
		
		relatorioDadosComlpementaresImovel.addParametro("consultarImovelForm",consultarImovelForm);
		
		relatorioDadosComlpementaresImovel.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));

		relatorioDadosComlpementaresImovel.addParametro("caminhoIconeFoto", sessao.getServletContext().getRealPath("imagens/imgfolder.gif"));
		
		return relatorioDadosComlpementaresImovel;
	}


}
