package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.ProgramacaoRoteiro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 20/09/2006
 */
public class ExibirCalendarioElaboracaoAcompanhamentoRoteiroAction extends
		GcomAction {

	/**
	 * Este caso de uso elabora ou mantém uma programação de roteiro
	 * 
	 * 
	 * [UC0455] Exibir Calendário para Elaboração ou Acompanhamento de Roteiro
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 20/09/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("calendarioElaboracaoAcompanhamentoRoteiroAction");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		ExibirCalendarioElaboracaoAcompanhamentoRoteiroActionForm exibirCalendarioElaboracaoAcompanhamentoRoteiroActionForm = (ExibirCalendarioElaboracaoAcompanhamentoRoteiroActionForm) actionForm;

		sessao.removeAttribute("elaboracao");
		sessao.removeAttribute("acompanhamento");

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		String mesAnoReferencia = (String) httpServletRequest
				.getParameter("mesAnoReferencia");

		// Recebe o ano e o mes
		String ano = null;

		String mes = null;

		String anoCalendario = (String) httpServletRequest.getParameter("ano");

		String mesCalendario = (String) httpServletRequest.getParameter("mes");

		//String anoMesReferencia = null;

		// Verifica se o mes/ano foram mudados no calendario, senao continua o
		// fluxo verificando se o mesanoReferencia foi informado, caso
		// contrario, assume o corrente.
		if (anoCalendario != null && !anoCalendario.equalsIgnoreCase("")
				&& mesCalendario != null && !mesCalendario.equalsIgnoreCase("")) {

			Integer aux = new Integer(mesCalendario);

			aux = aux + 1;

			if (aux < 10) {

				mesCalendario = "0" + aux.toString();
			} else {
				mesCalendario = aux.toString();
			}

			// [FS0001] Validar mês e ano de referencia

			String dataAuxiliar = null;

			dataAuxiliar = "01/" + mesCalendario + "/" + anoCalendario;

			Date dataValidacao = Util.converteStringParaDate(dataAuxiliar);

			Date dataAtual = new Date();

			if (httpServletRequest.getParameter("elaboracao") != null) {

				Calendar dataAtualVerificacao = new GregorianCalendar();
				dataAtualVerificacao.setTime(dataAtual);

				Calendar dataValidacaoVerificacao = new GregorianCalendar();
				dataValidacaoVerificacao.setTime(dataValidacao);

				if (mesCalendario != null && anoCalendario != null) {

					String anoMesCalendario = anoCalendario + mesCalendario;

					Date dataCorrente = new Date();
					String dataCorrenteTexto = Util.formatarData(dataCorrente);
					ano = dataCorrenteTexto.substring(6, 10);
					mes = dataCorrenteTexto.substring(3, 5);

					String anoMesAtual = ano + mes;

					if (Util.compararAnoMesReferencia(new Integer(
							anoMesCalendario), new Integer(anoMesAtual), "<")) {
						throw new ActionServletException(
								"atencao.ano_mes_referencia.invalida");
					}

				} else {

					if ((dataValidacaoVerificacao
							.compareTo(dataAtualVerificacao)) < 0) {
						throw new ActionServletException(
								"atencao.ano_mes_referencia.invalida");
					}
				}
				/*
				 * Integer difMeses = Util.dataDiff(dataAtual, dataValidacao);
				 * 
				 * if (difMeses > 0) { throw new ActionServletException(
				 * "atencao.ano_mes_referencia.invalida"); }
				 */
			}

			httpServletRequest.setAttribute("ano", anoCalendario);
			httpServletRequest.setAttribute("mes", mesCalendario);

			ano = anoCalendario;
			mes = mesCalendario;

			//anoMesReferencia = ano + mes;

			// boolean mesAnoValido = Util.validarMesAno(mesAnoReferencia);
			//			
			// if (mesAnoValido == false) {
			// throw new ActionServletException(
			// "atencao.ano_mes_referencia.invalida");
			// }

		} else if (mesAnoReferencia == null || mesAnoReferencia.equals("")) {

			// Recebe mesAnoReferecencia (mm/aaaa) e retorna
			// anoMesReferencia(aaaamm), caso o mesAno nao seja informado,
			// assume o
			// corrente

			Date dataCorrente = new Date();
			String dataCorrenteTexto = Util.formatarData(dataCorrente);
			ano = dataCorrenteTexto.substring(6, 10);
			mes = dataCorrenteTexto.substring(3, 5);
			//String anoMesCorrente = ano + mes;
			//anoMesReferencia = anoMesCorrente;

		} else {

			boolean mesAnoValido = Util.validarMesAno(mesAnoReferencia);

			if (mesAnoValido == false) {
				throw new ActionServletException(
						"atencao.ano_mes_referencia.invalida");
			}

			// [FS0001] Validar mês e ano de referencia
			String dataAuxiliar = null;

			dataAuxiliar = "01/" + mesAnoReferencia;

			Date dataValidacao = Util.converteStringParaDate(dataAuxiliar);

			Date dataAtual = new Date();

			if (httpServletRequest.getParameter("elaboracao") != null) {

				Integer difMeses = Util.dataDiff(dataAtual, dataValidacao);

				if (difMeses > 0) {
					throw new ActionServletException(
							"atencao.ano_mes_referencia.invalida");

				}
			}

			mes = mesAnoReferencia.substring(0, 2);
			ano = mesAnoReferencia.substring(3, 7);

			//anoMesReferencia = ano + mes;

		}

		Calendar calendario = Calendar.getInstance();
		int ultimoDia = 0;

		httpServletRequest.setAttribute("ano", ano);
		httpServletRequest.setAttribute("mes", new Integer(mes) - 1);

		calendario.set(new Integer(ano), new Integer(mes), 1);
		// ultimoDia = calendario.getActualMaximum(Calendar.DAY_OF_MONTH);

		ultimoDia = 31;
		Integer[] vetorEstilos = new Integer[ultimoDia];

		mesAnoReferencia = null;

		mesAnoReferencia = mes + "/" + ano;

		// Pesquisa a Programacao de Roteiro
		Collection colecaoProgramacaoRoteiro = fachada
				.consultarProgramacaoRoteiro(mesAnoReferencia, usuario
						.getUnidadeOrganizacional().getId());

		if (httpServletRequest.getParameter("elaboracao") != null) {
			sessao.setAttribute("elaboracao", "elaboracao");
			exibirCalendarioElaboracaoAcompanhamentoRoteiroActionForm
					.setCabecalho("Elaboração");
			exibirCalendarioElaboracaoAcompanhamentoRoteiroActionForm
					.setTitulo("elaboração");
		} else {
			sessao.setAttribute("acompanhamento", "acompanhamento");
			exibirCalendarioElaboracaoAcompanhamentoRoteiroActionForm
					.setCabecalho("Acompanhamento");
			exibirCalendarioElaboracaoAcompanhamentoRoteiroActionForm
					.setTitulo("acompanhamento");
		}

		if (colecaoProgramacaoRoteiro != null) {

			Iterator iteratorRoteiro = colecaoProgramacaoRoteiro.iterator();
			Date dataRoteiro;
			ProgramacaoRoteiro programacaoRoteiro = null;

			// Integer indicador

			while (iteratorRoteiro.hasNext()) {

				programacaoRoteiro = (ProgramacaoRoteiro) iteratorRoteiro
						.next();

				dataRoteiro = programacaoRoteiro.getDataRoteiro();

				/*
				 * Caso exista um intervalo maior do que um dia no
				 * abastecimento, verifica a qtde de dias entre o inicio e o fim
				 * as e jogo num vetor informando o estilo deste dia para o
				 * calendário
				 * 
				 */

				Calendar obterDia = Calendar.getInstance();

				// FiltroOrdemServicoProgramacao filtroOrdemServicoProgramacao =
				// new FiltroOrdemServicoProgramacao();

				// Collection colecaoOrdemServicoProgramacao = null;

				int i = 0;

				// Date dataAtual = new Date();

				if (sessao.getAttribute("elaboracao") != null) {

					exibirCalendarioElaboracaoAcompanhamentoRoteiroActionForm
							.setCabecalho("Elaboração");
					exibirCalendarioElaboracaoAcompanhamentoRoteiroActionForm
							.setTitulo("elaboração");
					while (i < ultimoDia + 1) {

						/*
						 * Inicialmente todos os dias recebem o id da situacao
						 * em aberto, caso a situacao nao seja em aberto,
						 * situacao é alterada
						 */
						// vetorEstilos[i] =
						// ConstantesSistema.SITUACAO_ROTEIRO_ELABORACAO_ABERTO;
						/*
						 * Caso o dia possua Roteiro e Identificador de
						 * fechamento esteja preenchido.
						 */
						/*
						 * filtroOrdemServicoProgramacao .adicionarParametro(new
						 * ParametroSimples(
						 * FiltroOrdemServicoProgramacao.PROGRAMACAO_ROTEIRO,
						 * programacaoRoteiro.getId()));
						 * 
						 * filtroOrdemServicoProgramacao .adicionarParametro(new
						 * ParametroSimples(
						 * FiltroOrdemServicoProgramacao.INDICADOR_ATIVO,
						 * OrdemServicoProgramacao.INDICADOR_ATIVO));
						 * 
						 * colecaoOrdemServicoProgramacao = fachada.pesquisar(
						 * filtroOrdemServicoProgramacao,
						 * OrdemServicoProgramacao.class.getName());
						 */

						Integer qtdOrdemServicoProgramacao = fachada
								.verificarExistenciaOSProgramacao(programacaoRoteiro
										.getId());

						if (qtdOrdemServicoProgramacao == 0) {

							obterDia.setTime(dataRoteiro);
							vetorEstilos[obterDia.get(Calendar.DAY_OF_MONTH) - 1] = ConstantesSistema.SITUACAO_ROTEIRO_ELABORACAO_FECHAMENTO_NULO;

						} else {

							obterDia.setTime(dataRoteiro);
							vetorEstilos[obterDia.get(Calendar.DAY_OF_MONTH) - 1] = ConstantesSistema.SITUACAO_ROTEIRO_ELABORACAO_FECHAMENTO_PREENCHIDO;
						}

						/*
						 * if (colecaoOrdemServicoProgramacao == null) {
						 * 
						 * obterDia.setTime(dataRoteiro);
						 * vetorEstilos[obterDia.get(Calendar.DAY_OF_MONTH) - 1] =
						 * ConstantesSistema.SITUACAO_ROTEIRO_ELABORACAO_FECHAMENTO_NULO; }
						 * 
						 * if (colecaoOrdemServicoProgramacao != null) {
						 * 
						 * obterDia.setTime(dataRoteiro);
						 * vetorEstilos[obterDia.get(Calendar.DAY_OF_MONTH) - 1] =
						 * ConstantesSistema.SITUACAO_ROTEIRO_ELABORACAO_FECHAMENTO_PREENCHIDO; }
						 */

						i++;
					}

				} else {
					exibirCalendarioElaboracaoAcompanhamentoRoteiroActionForm
							.setCabecalho("Acompanhamento");
					exibirCalendarioElaboracaoAcompanhamentoRoteiroActionForm
							.setTitulo("acompanhamento");
					while (i < ultimoDia) {

						/*
						 * Inicialmente todos os dias recebem o id da situacao
						 * em aberto, caso a situacao nao seja em aberto,
						 * situacao é alterada
						 */
						// vetorEstilos[i] =
						// ConstantesSistema.SITUACAO_ROTEIRO_ACOMPANHAMENTO_ABERTO;
						/*
						 * Caso o dia possua Roteiro e Identificador de
						 * fechamento esteja preenchido.
						 */
						/*
						 * filtroOrdemServicoProgramacao .adicionarParametro(new
						 * ParametroSimples(
						 * FiltroOrdemServicoProgramacao.PROGRAMACAO_ROTEIRO,
						 * programacaoRoteiro.getId()));
						 * 
						 * filtroOrdemServicoProgramacao .adicionarParametro(new
						 * ParametroSimples(
						 * FiltroOrdemServicoProgramacao.INDICADOR_ATIVO,
						 * OrdemServicoProgramacao.INDICADOR_ATIVO));
						 * 
						 * colecaoOrdemServicoProgramacao = fachada.pesquisar(
						 * filtroOrdemServicoProgramacao,
						 * OrdemServicoProgramacao.class.getName());
						 */

						Integer qtdOrdemServicoProgramacao = fachada
								.verificarExistenciaOSProgramacao(programacaoRoteiro
										.getId());

						if (qtdOrdemServicoProgramacao == 0) {

							obterDia.setTime(dataRoteiro);
							vetorEstilos[obterDia.get(Calendar.DAY_OF_MONTH) - 1] = ConstantesSistema.SITUACAO_ROTEIRO_ACOMPANHAMENTO_FECHAMENTO_NULO;

						} else {

							obterDia.setTime(dataRoteiro);
							vetorEstilos[obterDia.get(Calendar.DAY_OF_MONTH) - 1] = ConstantesSistema.SITUACAO_ROTEIRO_ACOMPANHAMENTO_FECHAMENTO_PREENCHIDO;
						}

						/*
						 * if (colecaoOrdemServicoProgramacao.isEmpty()) {
						 * 
						 * obterDia.setTime(dataRoteiro);
						 * vetorEstilos[obterDia.get(Calendar.DAY_OF_MONTH) - 1] =
						 * ConstantesSistema.SITUACAO_ROTEIRO_ACOMPANHAMENTO_FECHAMENTO_NULO; }
						 * 
						 * if (!colecaoOrdemServicoProgramacao.isEmpty()) {
						 * 
						 * obterDia.setTime(dataRoteiro);
						 * vetorEstilos[obterDia.get(Calendar.DAY_OF_MONTH) - 1] =
						 * ConstantesSistema.SITUACAO_ROTEIRO_ACOMPANHAMENTO_FECHAMENTO_PREENCHIDO; }
						 */

						i++;
					}

				}
			}

		}

		int cont = 0;

		Date dataAtual = new Date();

		Integer dia = Util.getDiaMes(dataAtual);

		if (mesCalendario != null && anoCalendario != null && httpServletRequest.getParameter("elaboracao") != null) {

			String anoMesCalendario = anoCalendario + mesCalendario;

			Date dataCorrente = new Date();
			String dataCorrenteTexto = Util.formatarData(dataCorrente);
			ano = dataCorrenteTexto.substring(6, 10);
			mes = dataCorrenteTexto.substring(3, 5);

			String anoMesAtual = ano + mes;

			if (!Util.compararAnoMesReferencia(new Integer(anoMesCalendario),
					new Integer(anoMesAtual), "=")) {
				
				String diaAux = "01";
				dia = new Integer(diaAux);
			}

		}

		String situacoes = "";
		while (cont < vetorEstilos.length) {

			if ((cont + 2 <= dia.intValue())
					&& (sessao.getAttribute("elaboracao") != null)) {

				vetorEstilos[cont] = ConstantesSistema.SITUACAO_ROTEIRO_ACOMPANHAMENTO_ABERTO;

			}

			if ((vetorEstilos[cont] == null)
					&& (sessao.getAttribute("elaboracao") == null)) {

				vetorEstilos[cont] = ConstantesSistema.SITUACAO_ROTEIRO_ACOMPANHAMENTO_ABERTO;

			}

			if ((vetorEstilos[cont] == null)
					&& (sessao.getAttribute("elaboracao") != null)) {

				vetorEstilos[cont] = ConstantesSistema.SITUACAO_ROTEIRO_ELABORACAO_ABERTO;

			}

			if (situacoes.length() < 1) {
				situacoes = vetorEstilos[cont].toString();
			} else {
				situacoes = situacoes + "," + vetorEstilos[cont].toString();
			}

			cont++;
		}

		httpServletRequest.setAttribute("situacoes", situacoes);

		return retorno;

	}
}
