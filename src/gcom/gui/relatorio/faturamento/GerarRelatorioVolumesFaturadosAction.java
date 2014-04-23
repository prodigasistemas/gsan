package gcom.gui.relatorio.faturamento;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.faturamento.GerarRelatorioVolumesFaturadosActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.faturamento.RelatorioVolumesFaturados;
import gcom.relatorio.faturamento.RelatorioVolumesFaturadosResumido;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

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
public class GerarRelatorioVolumesFaturadosAction extends
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

		GerarRelatorioVolumesFaturadosActionForm gerarRelatorioVolumesFaturadosActionForm = (GerarRelatorioVolumesFaturadosActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Valida os parâmetro passados como consulta
		Localidade localidade = null;

		String idLocalidade = gerarRelatorioVolumesFaturadosActionForm
				.getIdLocalidade();

		if (idLocalidade != null && !idLocalidade.equals("")) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidade));

			Collection colecaoLocalidades = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoLocalidades != null && !colecaoLocalidades.isEmpty()) {
				localidade = (Localidade) Util
						.retonarObjetoDeColecao(colecaoLocalidades);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Localidade");
			}
		}

		Integer anoMes = null;
		String mesAnoDigitado = gerarRelatorioVolumesFaturadosActionForm
				.getMesAno();

		if (mesAnoDigitado != null && !mesAnoDigitado.equals("")) {
			anoMes = Util.formatarMesAnoComBarraParaAnoMes(mesAnoDigitado);
		}

		// seta os parametros que serão mostrados no relatório

		// Fim da parte que vai mandar os parametros para o relatório
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		String opcaoRelatorio = gerarRelatorioVolumesFaturadosActionForm
				.getOpcaoRelatorio();

		if (opcaoRelatorio != null && opcaoRelatorio.equals("resumido")) {

			RelatorioVolumesFaturadosResumido relatorioVolumesFaturadosResumido = new RelatorioVolumesFaturadosResumido(
					(Usuario) (httpServletRequest.getSession(false))
							.getAttribute("usuarioLogado"));
			relatorioVolumesFaturadosResumido.addParametro("idLocalidade",
					localidade.getId());
			relatorioVolumesFaturadosResumido.addParametro("anoMes", anoMes);
			if (tipoRelatorio == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}

			relatorioVolumesFaturadosResumido.addParametro(
					"tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));

			retorno = processarExibicaoRelatorio(
					relatorioVolumesFaturadosResumido, tipoRelatorio,
					httpServletRequest, httpServletResponse, actionMapping);

		} else {

			RelatorioVolumesFaturados relatorioVolumesFaturados = new RelatorioVolumesFaturados(
					(Usuario) (httpServletRequest.getSession(false))
							.getAttribute("usuarioLogado"));
			relatorioVolumesFaturados.addParametro("idLocalidade", localidade
					.getId());
			relatorioVolumesFaturados.addParametro("anoMes", anoMes);
			if (tipoRelatorio == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}

			relatorioVolumesFaturados.addParametro("tipoFormatoRelatorio",
					Integer.parseInt(tipoRelatorio));

			retorno = processarExibicaoRelatorio(relatorioVolumesFaturados,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);

		}

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
