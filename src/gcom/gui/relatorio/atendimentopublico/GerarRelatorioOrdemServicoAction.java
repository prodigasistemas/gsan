package gcom.gui.relatorio.atendimentopublico;

import java.util.StringTokenizer;

import gcom.gui.atendimentopublico.ordemservico.ConsultarDadosOrdemServicoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.atendimentopublico.RelatorioOrdemServico;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action responsável pela exibição do relatório de bairro manter
 * 
 * @author Sávio Luiz
 * @created 11 de Julho de 2005
 */
public class GerarRelatorioOrdemServicoAction extends
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

		String idOrdemServico = null;
		
		StringTokenizer idsOrdemServico = null;

		if (httpServletRequest.getParameter("idsOS") != null) {
			idOrdemServico = httpServletRequest.getParameter("idsOS");
			idsOrdemServico = new StringTokenizer(idOrdemServico, "$");
		} else {
			ConsultarDadosOrdemServicoActionForm consultarDadosOrdemServicoActionForm = (ConsultarDadosOrdemServicoActionForm) actionForm;
			idOrdemServico = consultarDadosOrdemServicoActionForm.getNumeroOS();
			idsOrdemServico = new StringTokenizer(idOrdemServico, "$");
		}
		

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioOrdemServico relatorioOrdemServico = new RelatorioOrdemServico(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioOrdemServico.addParametro("idsOrdemServico", idsOrdemServico);
		
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioOrdemServico.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		retorno = processarExibicaoRelatorio(relatorioOrdemServico,
				tipoRelatorio, httpServletRequest, httpServletResponse,
				actionMapping);

		// devolve o mapeamento contido na variável retorno
		return retorno;

	}

}
