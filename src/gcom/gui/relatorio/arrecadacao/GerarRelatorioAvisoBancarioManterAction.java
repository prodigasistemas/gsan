package gcom.gui.relatorio.arrecadacao;

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.ArrecadadorMovimento;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.arrecadacao.aviso.bean.AvisoBancarioHelper;
import gcom.arrecadacao.banco.Agencia;
import gcom.arrecadacao.banco.Banco;
import gcom.arrecadacao.banco.ContaBancaria;
import gcom.fachada.Fachada;
import gcom.gui.arrecadacao.aviso.FiltrarAvisoBancarioActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.arrecadacao.RelatorioManterAvisoBancario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Realiza o filtro do aviso bancario de acordo com os parâmetros informados
 * 
 * @author Vivianne Sousa
 * @created 13/03/2006
 */

public class GerarRelatorioAvisoBancarioManterAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		/**
		 * @author Rafael Corrêa
		 * @date 05/09/2006
		 * 
		 * @param actionMapping
		 * @param actionForm
		 * @param httpServletRequest
		 * @param httpServletResponse
		 * @return
		 */

		ActionForward retorno = null;

		Fachada fachada = Fachada.getInstancia();

		FiltrarAvisoBancarioActionForm filtrarAvisoBancarioActionForm = (FiltrarAvisoBancarioActionForm) actionForm;

		String arrecadadorCodAgente = filtrarAvisoBancarioActionForm
				.getArrecadadorCodAgente();
		String dataLancamentoInicioString = filtrarAvisoBancarioActionForm
				.getDataLancamentoInicio();
		String dataLancamentoFimString = filtrarAvisoBancarioActionForm
				.getDataLancamentoFim();
		String tipoAviso = filtrarAvisoBancarioActionForm.getTipoAviso();
		String idContaBancaria = filtrarAvisoBancarioActionForm
				.getIdContaBancaria();
		String idBancoConta = filtrarAvisoBancarioActionForm.getIdBanco();
		String codigoAgencia = filtrarAvisoBancarioActionForm.getCodAgencia();
		String numeroConta = filtrarAvisoBancarioActionForm.getNumeroConta();
		String idMovimento = filtrarAvisoBancarioActionForm.getIdMovimento();
		String codigoBancoArrecadador = filtrarAvisoBancarioActionForm
				.getCodigoBanco();
		String codigoRemessa = filtrarAvisoBancarioActionForm
				.getCodigoRemessa();
		String identificacaoServico = filtrarAvisoBancarioActionForm
				.getIdentificacaoServico();
		String numeroSequencial = filtrarAvisoBancarioActionForm
				.getNumeroSequencial();
		String periodoArrecadacaoInicio = filtrarAvisoBancarioActionForm
				.getPeriodoArrecadacaoInicio();
		String periodoArrecadacaoFim = filtrarAvisoBancarioActionForm
				.getPeriodoArrecadacaoFim();
		String dataPrevisaoCreditoDebitoInicioString = filtrarAvisoBancarioActionForm
				.getDataPrevisaoCreditoDebitoInicio();
		String dataPrevisaoCreditoDebitoFimString = filtrarAvisoBancarioActionForm
				.getDataPrevisaoCreditoDebitoFim();
		String dataRealizacaoCreditoDebitoInicioString = filtrarAvisoBancarioActionForm
				.getDataRealizacaoCreditoDebitoInicio();
		String dataRealizacaoCreditoDebitoFimString = filtrarAvisoBancarioActionForm
				.getDataRealizacaoCreditoDebitoFim();
		String intervaloValorRealizadoInicio = filtrarAvisoBancarioActionForm
				.getIntervaloValorRealizadoInicio();
		String intervaloValorRealizadoFim = filtrarAvisoBancarioActionForm
				.getIntervaloValorRealizadoFim();
		String avisoAbertoFechado = filtrarAvisoBancarioActionForm.getAviso();

		AvisoBancarioHelper avisoBancarioHelper = new AvisoBancarioHelper();

		AvisoBancarioHelper avisoBancarioHelperParametros = new AvisoBancarioHelper();

		Arrecadador arrecadadorParametros = null;

		ContaBancaria contaBancariaParametros = null;

		ArrecadadorMovimento arrecadadorMovimentoParametros = null;

		// Passando os Parâmetros para os filtros...

		// Arrecadador

		if ((arrecadadorCodAgente != null)
				&& (!arrecadadorCodAgente.trim().equals(""))) {

			avisoBancarioHelper.setCodigoAgenteArrecadador(new Short(
					arrecadadorCodAgente));

			FiltroArrecadador filtroArrecadador = new FiltroArrecadador();

			filtroArrecadador.adicionarParametro(new ParametroSimples(
					FiltroArrecadador.CODIGO_AGENTE, arrecadadorCodAgente));

			filtroArrecadador
					.adicionarCaminhoParaCarregamentoEntidade("cliente");

			Collection<Arrecadador> colecaoArrecadadores = fachada.pesquisar(
					filtroArrecadador, Arrecadador.class.getName());

			if (colecaoArrecadadores != null && !colecaoArrecadadores.isEmpty()) {
				arrecadadorParametros = colecaoArrecadadores.iterator().next();
			}
		}

		// Perído Lançamento

		if ((dataLancamentoInicioString != null)
				&& (!dataLancamentoInicioString.equals(""))) {

			Date dataLancamentoInicio = null;
			Date dataLancamentoFim = null;

			if ((dataLancamentoFimString == null)
					|| (dataLancamentoFimString.equals(""))) {
				dataLancamentoFimString = dataLancamentoInicioString;
			}

			dataLancamentoInicio = Util
					.converteStringParaDate(dataLancamentoInicioString);
			dataLancamentoFim = Util
					.converteStringParaDate(dataLancamentoFimString);

			avisoBancarioHelper.setDataLancamentoInicial(dataLancamentoInicio);
			avisoBancarioHelper.setDataLancamentoFinal(dataLancamentoFim);

			avisoBancarioHelperParametros
					.setDataLancamentoInicial(dataLancamentoInicio);
			avisoBancarioHelperParametros
					.setDataLancamentoFinal(dataLancamentoFim);
		}

		// Tipo Aviso

		if ((tipoAviso != null) && (!tipoAviso.trim().equals(""))
				&& (!tipoAviso.trim().equals("3"))) {
			avisoBancarioHelper.setIndicadorCreditoDebito(new Short(tipoAviso));

			avisoBancarioHelperParametros.setTipoAviso(tipoAviso);
		}

		// Conta Bancária

		if ((idContaBancaria != null) && (!idContaBancaria.trim().equals(""))) {
			avisoBancarioHelper
					.setIdContaBancaria(new Integer(idContaBancaria));

			Banco banco = new Banco();
			Agencia agencia = new Agencia();
			contaBancariaParametros = new ContaBancaria();

			banco.setId(new Integer(idBancoConta));
			agencia.setCodigoAgencia(new String(codigoAgencia));
			agencia.setBanco(banco);

			contaBancariaParametros.setNumeroConta(numeroConta);
			contaBancariaParametros.setAgencia(agencia);

		}

		// Movimento

		if ((idMovimento != null) && (!idMovimento.trim().equals(""))) {
			avisoBancarioHelper.setIdMovimentoArrecadador(new Integer(
					idMovimento));

			arrecadadorMovimentoParametros = new ArrecadadorMovimento();
			arrecadadorMovimentoParametros.setCodigoBanco(new Short(
					codigoBancoArrecadador));
			arrecadadorMovimentoParametros.setCodigoRemessa(new Short(
					codigoRemessa));
			arrecadadorMovimentoParametros
					.setDescricaoIdentificacaoServico(identificacaoServico);
			arrecadadorMovimentoParametros
					.setNumeroSequencialArquivo(new Integer(numeroSequencial));

		}

		// Período Previsão Crédito/Débito

		if ((dataPrevisaoCreditoDebitoInicioString != null)
				&& (!dataPrevisaoCreditoDebitoInicioString.trim().equals(""))) {

			Date dataPrevisaoCreditoDebitoInicio = null;
			Date dataPrevisaoCreditoDebitoFim = null;

			if ((dataPrevisaoCreditoDebitoFimString == null)
					|| (dataPrevisaoCreditoDebitoFimString.equals(""))) {
				dataPrevisaoCreditoDebitoFimString = dataPrevisaoCreditoDebitoInicioString;
			}

			dataPrevisaoCreditoDebitoInicio = Util
					.converteStringParaDate(dataPrevisaoCreditoDebitoInicioString);
			dataPrevisaoCreditoDebitoFim = Util
					.converteStringParaDate(dataPrevisaoCreditoDebitoFimString);

			avisoBancarioHelper
					.setDataPrevistaInicial(dataPrevisaoCreditoDebitoInicio);
			avisoBancarioHelper
					.setDataPrevistaFinal(dataPrevisaoCreditoDebitoFim);

			avisoBancarioHelperParametros
					.setDataPrevistaInicial(dataPrevisaoCreditoDebitoInicio);
			avisoBancarioHelperParametros
					.setDataPrevistaFinal(dataPrevisaoCreditoDebitoFim);
		}

		// Período Realização Crédito/Débito

		if ((dataRealizacaoCreditoDebitoInicioString != null)
				&& (!dataRealizacaoCreditoDebitoInicioString.trim().equals(""))) {

			Date dataRealizacaoCreditoDebitoInicio = null;
			Date dataRealizacaoCreditoDebitoFim = null;

			if ((dataRealizacaoCreditoDebitoFimString == null)
					|| (dataRealizacaoCreditoDebitoFimString.equals(""))) {
				dataRealizacaoCreditoDebitoFimString = dataRealizacaoCreditoDebitoInicioString;
			}

			dataRealizacaoCreditoDebitoInicio = Util
					.converteStringParaDate(dataRealizacaoCreditoDebitoInicioString);
			dataRealizacaoCreditoDebitoFim = Util
					.converteStringParaDate(dataRealizacaoCreditoDebitoFimString);

			avisoBancarioHelper
					.setDataRealizadaInicial(dataRealizacaoCreditoDebitoInicio);
			avisoBancarioHelper
					.setDataRealizadaFinal(dataRealizacaoCreditoDebitoFim);

			avisoBancarioHelperParametros
					.setDataRealizadaInicial(dataRealizacaoCreditoDebitoInicio);
			avisoBancarioHelperParametros
					.setDataRealizadaFinal(dataRealizacaoCreditoDebitoFim);
		}

		// Perído Arrecadação

		if ((periodoArrecadacaoInicio != null)
				&& (!periodoArrecadacaoInicio.trim().equals(""))) {

			int periodoArrecadacaoInicioTratado = Integer
					.parseInt(Util
							.formatarMesAnoParaAnoMesSemBarra(periodoArrecadacaoInicio));

			int periodoArrecadacaoFimTratado = Integer.parseInt(Util
					.formatarMesAnoParaAnoMesSemBarra(periodoArrecadacaoFim));

			avisoBancarioHelper
					.setAnoMesReferenciaArrecadacaoInicial(periodoArrecadacaoInicioTratado);
			avisoBancarioHelper
					.setAnoMesReferenciaArrecadacaoFinal(periodoArrecadacaoFimTratado);

			avisoBancarioHelperParametros
					.setAnoMesReferenciaArrecadacaoInicial(periodoArrecadacaoInicioTratado);
			avisoBancarioHelperParametros
					.setAnoMesReferenciaArrecadacaoFinal(periodoArrecadacaoFimTratado);
		}

		// Intervalo Valor Realizado

		if ((intervaloValorRealizadoInicio != null)
				&& (!intervaloValorRealizadoInicio.trim().equals(""))) {

			avisoBancarioHelper
					.setValorRealizadoInicial(Util
							.formatarMoedaRealparaBigDecimal(intervaloValorRealizadoInicio));
			avisoBancarioHelper
					.setValorRealizadoFinal(Util
							.formatarMoedaRealparaBigDecimal(intervaloValorRealizadoFim));

			avisoBancarioHelperParametros
					.setValorRealizadoInicial(Util
							.formatarMoedaRealparaBigDecimal(intervaloValorRealizadoInicio));
			avisoBancarioHelperParametros
					.setValorRealizadoFinal(Util
							.formatarMoedaRealparaBigDecimal(intervaloValorRealizadoFim));
		}

		// Aviso Abertos/Fechados

		if (avisoAbertoFechado != null && (!avisoAbertoFechado.equals("-1"))) {
			avisoBancarioHelper.setTipoAviso(avisoAbertoFechado);

			avisoBancarioHelperParametros.setTipoAviso(avisoAbertoFechado);
		}

		// cria uma instância da classe do relatório
		RelatorioManterAvisoBancario relatorioManterAvisoBancario = new RelatorioManterAvisoBancario(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterAvisoBancario.addParametro("avisoBancarioHelper",
				avisoBancarioHelper);
		relatorioManterAvisoBancario.addParametro(
				"avisoBancarioHelperParametros", avisoBancarioHelperParametros);
		relatorioManterAvisoBancario.addParametro("arrecadadorParametros",
				arrecadadorParametros);
		relatorioManterAvisoBancario.addParametro("contaBancariaParametros",
				contaBancariaParametros);
		relatorioManterAvisoBancario.addParametro(
				"arrecadadorMovimentoParametros",
				arrecadadorMovimentoParametros);

		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterAvisoBancario.addParametro("tipoFormatoRelatorio",
				Integer.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterAvisoBancario,
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

		return retorno;
	}
}
