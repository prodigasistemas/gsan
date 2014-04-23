package gcom.gui.relatorio.gerencial.micromedicao;

import gcom.gerencial.bean.InformarDadosGeracaoRelatorioConsultaHelper;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.gerencial.micromedicao.RelatorioResumoAnormalidadeLeitura;
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
public class GerarRelatorioResumoAnormalidadeLeituraAction extends
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

		// ResumoAnormalidadeActionForm resumoAnormalidadeActionForm =
		// (ResumoAnormalidadeActionForm) actionForm;

		Collection colecaoResumosAnormalidadeLeitura = (Collection) sessao
				.getAttribute("colecaoResumoAnormalidadeRelatorio");
		InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper = (InformarDadosGeracaoRelatorioConsultaHelper) sessao
				.getAttribute("informarDadosGeracaoRelatorioConsultaHelper");

		// Inicio da parte que vai mandar os parametros para o relatório

		// Fim da parte que vai mandar os parametros para o relatório

		RelatorioResumoAnormalidadeLeitura relatorioResumoAnormalidadeLeitura = new RelatorioResumoAnormalidadeLeitura(
				(Usuario) (httpServletRequest.getSession(false))
						.getAttribute("usuarioLogado"));
		relatorioResumoAnormalidadeLeitura.addParametro("opcaoTotalizacao",
				informarDadosGeracaoRelatorioConsultaHelper
						.getOpcaoTotalizacao());

		relatorioResumoAnormalidadeLeitura.addParametro(
				"colecaoResumosAnormalidadeLeitura",
				colecaoResumosAnormalidadeLeitura);

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioResumoAnormalidadeLeitura.addParametro("tipoFormatoRelatorio",
				Integer.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(
					relatorioResumoAnormalidadeLeitura, tipoRelatorio,
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
