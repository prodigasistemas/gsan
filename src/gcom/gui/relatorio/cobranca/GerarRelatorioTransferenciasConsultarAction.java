package gcom.gui.relatorio.cobranca;

import gcom.cobranca.bean.ConsultarTransferenciasDebitoHelper;
import gcom.cobranca.bean.TransferenciasDebitoHelper;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.RelatorioConsultarTransferencias;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action responsável pela exibição do relatório de bairro manter
 * 
 * @author Sávio Luiz
 * @created 11 de Julho de 2005
 */
public class GerarRelatorioTransferenciasConsultarAction extends
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
		
		ConsultarTransferenciasDebitoHelper consultarTransferenciasDebitoHelper = (ConsultarTransferenciasDebitoHelper) sessao.getAttribute("consultarTransferenciasDebitoHelper");
		Collection<TransferenciasDebitoHelper> colecaoContasTransferidas = (Collection<TransferenciasDebitoHelper>) sessao.getAttribute("colecaoContasTransferidas");
		Collection<TransferenciasDebitoHelper> colecaoDebitosACobrarTransferidos = (Collection<TransferenciasDebitoHelper>) sessao.getAttribute("colecaoDebitosACobrarTransferidos");
		Collection<TransferenciasDebitoHelper> colecaoCreditosARealizarTransferidos = (Collection<TransferenciasDebitoHelper>) sessao.getAttribute("colecaoCreditosARealizarTransferidos");
		Collection<TransferenciasDebitoHelper> colecaoGuiasPagamentoTransferidas = (Collection<TransferenciasDebitoHelper>) sessao.getAttribute("colecaoGuiasPagamentoTransferidas");

		// Fim da parte que vai mandar os parametros para o relatório
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioConsultarTransferencias relatorioConsultarTransferencias = new RelatorioConsultarTransferencias((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioConsultarTransferencias.addParametro("colecaoContasTransferidas",
				colecaoContasTransferidas);
		relatorioConsultarTransferencias.addParametro("colecaoDebitosACobrarTransferidos",
				colecaoDebitosACobrarTransferidos);
		relatorioConsultarTransferencias.addParametro("colecaoCreditosARealizarTransferidos",
				colecaoCreditosARealizarTransferidos);
		relatorioConsultarTransferencias.addParametro("colecaoGuiasPagamentoTransferidas",
				colecaoGuiasPagamentoTransferidas);
		relatorioConsultarTransferencias.addParametro("consultarTransferenciasDebitoHelper",
				consultarTransferenciasDebitoHelper);
		
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioConsultarTransferencias.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		retorno = processarExibicaoRelatorio(relatorioConsultarTransferencias, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
