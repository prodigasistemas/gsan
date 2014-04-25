package gcom.gui.relatorio.cobranca;

import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.CobrancaGrupoCronogramaMes;
import gcom.cobranca.FiltroCobrancaAcaoAtividadeCronograma;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.fachada.Fachada;
import gcom.gui.cobranca.CobrancaActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cobranca.RelatorioManterCronogramaCobranca;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Rafael Corrêa
 * @version 1.0
 */

public class GerarRelatorioCronogramaCobrancaManterAction extends
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

		CobrancaActionForm cobrancaActionForm = (CobrancaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		FiltroCobrancaAcaoAtividadeCronograma filtroCobrancaAcaoAtividadeCronograma = new FiltroCobrancaAcaoAtividadeCronograma();

		// Inicio da parte que vai mandar os parametros para o relatório

		CobrancaGrupoCronogramaMes cobrancaGrupoCronogramaMesParametros = new CobrancaGrupoCronogramaMes();

		String idGrupoCobranca = (String) cobrancaActionForm.getIdGrupoCobranca();

		CobrancaGrupo cobrancaGrupo = new CobrancaGrupo();

		if (idGrupoCobranca != null
				&& !idGrupoCobranca.equals("")
				&& !idGrupoCobranca.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();

			filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(
					FiltroCobrancaGrupo.ID, idGrupoCobranca));

			Collection colecaoGruposCobrancas = fachada.pesquisar(
					filtroCobrancaGrupo, CobrancaGrupo.class.getName());

			if (colecaoGruposCobrancas != null
					&& !colecaoGruposCobrancas.isEmpty()) {

				filtroCobrancaAcaoAtividadeCronograma
						.adicionarParametro(new ParametroSimples(
								FiltroCobrancaAcaoAtividadeCronograma.COBRANCA_GRUPO_CRONOGRAMA_MES_COBRANCA_GRUPO_ID,
								idGrupoCobranca));

				Iterator colecaoGruposCobrancasIterator = colecaoGruposCobrancas
						.iterator();

				cobrancaGrupo = (CobrancaGrupo) colecaoGruposCobrancasIterator
						.next();

			}
		}

		int anoMes = 0;

		String mesAnoPesquisar = (String) cobrancaActionForm.getMesAno();

		if (mesAnoPesquisar != null && !mesAnoPesquisar.equals("")) {

			anoMes = new Integer(Util.formatarMesAnoParaAnoMesSemBarra(mesAnoPesquisar)).intValue();

			filtroCobrancaAcaoAtividadeCronograma
					.adicionarParametro(new ParametroSimples(
							FiltroCobrancaAcaoAtividadeCronograma.COBRANCA_GRUPO_CRONOGRAMA_MES_MES_ANO,
							anoMes));
		}

		// seta os parametros que serão mostrados no relatório

		cobrancaGrupoCronogramaMesParametros
				.setCobrancaGrupo(cobrancaGrupo);
		cobrancaGrupoCronogramaMesParametros.setAnoMesReferencia(anoMes);

		// Fim da parte que vai mandar os parametros para o relatório

		// cria uma instância da classe do relatório
		RelatorioManterCronogramaCobranca relatorioManterCronogramaCobranca = new RelatorioManterCronogramaCobranca(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterCronogramaCobranca.addParametro(
				"filtroCobrancaAcaoAtividadeCronograma",
				filtroCobrancaAcaoAtividadeCronograma);
		relatorioManterCronogramaCobranca.addParametro(
				"cobrancaGrupoCronogramaMesParametros",
				cobrancaGrupoCronogramaMesParametros);

		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterCronogramaCobranca.addParametro(
				"tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(
					relatorioManterCronogramaCobranca, tipoRelatorio,
					httpServletRequest, httpServletResponse, actionMapping);

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
