package gcom.gui.relatorio.atendimentopublico;

import gcom.gui.atendimentopublico.ordemservico.AcompanharRoteiroProgramacaoOrdemServicoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.atendimentopublico.RelatorioRoteiroProgramacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;

import java.util.Date;
import java.util.HashMap;
import java.util.StringTokenizer;

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
public class GerarRelatorioRoteiroProgramacaoAction extends
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

		AcompanharRoteiroProgramacaoOrdemServicoActionForm acompanharRoteiroProgramacaoOrdemServicoActionForm = 
			(AcompanharRoteiroProgramacaoOrdemServicoActionForm) actionForm;

		String dataRoteiroParametro = acompanharRoteiroProgramacaoOrdemServicoActionForm.getDataRoteiro();
		String chaves = acompanharRoteiroProgramacaoOrdemServicoActionForm.getChavesRelatorio();

		HashMap mapEquipe = (HashMap) sessao.getAttribute("mapEquipe");

		StringTokenizer nomesEquipes = new StringTokenizer(chaves, "$");

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		Date dataRoteiro = Util.converteStringParaDate(dataRoteiroParametro);

		RelatorioRoteiroProgramacao relatorioRoteiroProgramacao = new RelatorioRoteiroProgramacao(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioRoteiroProgramacao.addParametro("dataRoteiro", dataRoteiro);
		relatorioRoteiroProgramacao.addParametro("mapEquipe", mapEquipe);
		relatorioRoteiroProgramacao.addParametro("nomesEquipes", nomesEquipes);

		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioRoteiroProgramacao.addParametro("tipoFormatoRelatorio",
				Integer.parseInt(tipoRelatorio));
		retorno = processarExibicaoRelatorio(relatorioRoteiroProgramacao,
				tipoRelatorio, httpServletRequest, httpServletResponse,
				actionMapping);

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
