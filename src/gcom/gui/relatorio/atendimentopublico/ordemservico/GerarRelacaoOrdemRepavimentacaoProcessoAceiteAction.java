package gcom.gui.relatorio.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.bean.OrdemRepavimentacaoProcessoAceiteHelper;
import gcom.gui.atendimentopublico.ordemservico.FiltrarOrdemRepavimentacaoProcessoAceiteActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.atendimentopublico.ordemservico.RelatorioRelacaoOrdemRepavimentacaoProcessoAceite;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela exibição da Relacao de Ordem de Repavimentacao em Processo de Aceite.
 * 
 * @author Hugo Leonardo
 * @created 20/05/2010
 */
public class GerarRelacaoOrdemRepavimentacaoProcessoAceiteAction extends
		ExibidorProcessamentoTarefaRelatorio {

	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a variável de retorno
		ActionForward retorno = null;

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarOrdemRepavimentacaoProcessoAceiteActionForm form = (FiltrarOrdemRepavimentacaoProcessoAceiteActionForm) actionForm;
		
		Collection parametros = (Collection) sessao.getAttribute("collOrdemServicoPavimentoAceite");
		// seta os parametros que serão mostrados no relatório
		OrdemRepavimentacaoProcessoAceiteHelper osPavimentoAceiteHelper = (OrdemRepavimentacaoProcessoAceiteHelper) sessao.getAttribute("osPavimentoAceiteHelper");	

		// cria uma instância da classe do relatório
		RelatorioRelacaoOrdemRepavimentacaoProcessoAceite relatorio = new RelatorioRelacaoOrdemRepavimentacaoProcessoAceite(
					(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

		relatorio.addParametro("parametros",parametros);
		relatorio.addParametro("osPavimentoAceiteHelper",osPavimentoAceiteHelper);
		relatorio.addParametro("periodoAceiteServicoInicial", form.getPeriodoAceiteServicoInicial());
		relatorio.addParametro("periodoAceiteServicoFinal", form.getPeriodoAceiteServicoFinal());
		
		relatorio.addParametro("retornoServicoInicial", form.getPeriodoRetornoServicoInicial());
		relatorio.addParametro("retornoServicoFinal", form.getPeriodoRetornoServicoFinal());
		
		relatorio.addParametro("situacaoAceiteDescricao", form.getSituacaoAceiteDescricao());
		
		relatorio.addParametro("descricaoUnidadeOrganizacional", form.getDescricaoUnidadeOrganizacional());
		
		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

			relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorio,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);

		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
