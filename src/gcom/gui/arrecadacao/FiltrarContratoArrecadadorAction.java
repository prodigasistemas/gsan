package gcom.gui.arrecadacao;

import gcom.arrecadacao.ArrecadadorContrato;
import gcom.arrecadacao.FiltroArrecadadorContrato;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0536]FILTRAR ARRECADADOR
 * 
 * @author Marcio Roberto
 * @date 01/02/2007
 */

public class FiltrarContratoArrecadadorAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterContratoArrecadador");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarContratoArrecadadorActionForm filtrarContratoArrecadadorActionForm = (FiltrarContratoArrecadadorActionForm) actionForm;

		// Recupera todos os campos da página para ser colocada no filtro
		// posteriormente
		String idArrecadador = filtrarContratoArrecadadorActionForm
				.getIdArrecadador();
		String idCliente = filtrarContratoArrecadadorActionForm.getIdCliente();
		// String nomeCliente =
		// filtrarContratoArrecadadorActionForm.getNomeCliente();
		String numeroContrato = filtrarContratoArrecadadorActionForm
				.getNumeroContrato();
		String idConvenio = filtrarContratoArrecadadorActionForm
				.getIdConvenio();
		String idContaBancariaArrecadador = filtrarContratoArrecadadorActionForm
				.getIdContaBancariaArrecadador();
		String idContaBancariaTarifa = filtrarContratoArrecadadorActionForm
				.getIdContaBancariaTarifa();
		String indicadorCobranca = filtrarContratoArrecadadorActionForm
				.getIndicadorCobranca();
		String dtInicioContrato = filtrarContratoArrecadadorActionForm
				.getDtInicioContrato();
		String dtFimContrato = filtrarContratoArrecadadorActionForm
				.getDtFimContrato();
		String emailCliente = filtrarContratoArrecadadorActionForm
				.getEmailCliente();

		// Indicador Atualizar
		String indicadorAtualizar = httpServletRequest
				.getParameter("indicadorAtualizar");

		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {

			sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		} else {

			sessao.removeAttribute("indicadorAtualizar");
		}

		boolean peloMenosUmParametroInformado = false;
		FiltroArrecadadorContrato filtroArrecadadorContrato = new FiltroArrecadadorContrato();

		filtroArrecadadorContrato.adicionarCaminhoParaCarregamentoEntidade("arrecadador.cliente");
		filtroArrecadadorContrato.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroArrecadadorContrato.adicionarCaminhoParaCarregamentoEntidade("contaBancariaDepositoArrecadacao");
		filtroArrecadadorContrato.adicionarCaminhoParaCarregamentoEntidade("contaBancariaDepositoArrecadacao.agencia");
		// filtroArrecadadorContrato.adicionarCaminhoParaCarregamentoEntidade("contaBancariaDepositoTarifa");

		// Código do Arrecadador
		if (idArrecadador != null && !idArrecadador.trim().equals("")) {
			// [FS0003] - Verificando a existência do Agente
			boolean achou = fachada.verificarExistenciaArrecadador(new Integer(
					idArrecadador));
			if (achou) {
				peloMenosUmParametroInformado = true;
				filtroArrecadadorContrato
						.adicionarParametro(new ParametroSimples(
								FiltroArrecadadorContrato.ARRECADADOR_ID,
								idArrecadador));
			}
		}

		// Numero do Contrato
		if (numeroContrato != null && !numeroContrato.trim().equals("")) {
			boolean achou = fachada.verificarExistenciaContrato(numeroContrato);
			if (achou) {
				peloMenosUmParametroInformado = true;
				filtroArrecadadorContrato
						.adicionarParametro(new ParametroSimples(
								FiltroArrecadadorContrato.NUMEROCONTRATO,
								numeroContrato));
			}
		}

		// Cliente
		if (idCliente != null && !idCliente.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroArrecadadorContrato.adicionarParametro(new ParametroSimples(
					FiltroArrecadadorContrato.CLIENTE_ID, idCliente));
		}

		// Conta deposito Arrecadacao
		if (idContaBancariaArrecadador != null
				&& !idContaBancariaArrecadador.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroArrecadadorContrato.adicionarParametro(new ParametroSimples(
					FiltroArrecadadorContrato.ID_DEPOSITO_ARRECADACAO,
					idContaBancariaArrecadador));
		}

		// Conta deposito Tarifa
		if (idContaBancariaTarifa != null
				&& !idContaBancariaTarifa.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroArrecadadorContrato.adicionarParametro(new ParametroSimples(
					FiltroArrecadadorContrato.ID_DEPOSITO_TARIFA,
					idContaBancariaTarifa));
		}

		// Indicador de Cobranca de ISS
		if (indicadorCobranca != null
				&& !indicadorCobranca.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroArrecadadorContrato.adicionarParametro(new ParametroSimples(
					FiltroArrecadadorContrato.INDICADOR_COBRANCA,
					indicadorCobranca));

		}

		// Código do Convenio
		if (idConvenio != null && !idConvenio.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroArrecadadorContrato.adicionarParametro(new ParametroSimples(
					FiltroArrecadadorContrato.CODIGO_CONVENIO, idConvenio));
		}

		// Data de Inicio do Contrato
		if (dtInicioContrato != null
				&& !dtInicioContrato.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;

			Date dataInicio = Util.converteStringParaDate(dtInicioContrato);

			filtroArrecadadorContrato
					.adicionarParametro(new ParametroSimples(
							FiltroArrecadadorContrato.DATA_CONTRATO_INICIO,
							dataInicio));
		}

		// Data de Fim do Contrato
		if (dtFimContrato != null && !dtFimContrato.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;

			Date dataFim = Util.converteStringParaDate(dtFimContrato);

			filtroArrecadadorContrato.adicionarParametro(new ParametroSimples(
					FiltroArrecadadorContrato.DATA_CONTRATO_FIM, dataFim));
		}

		// E-mail
		if (emailCliente != null && !emailCliente.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroArrecadadorContrato.adicionarParametro(new ParametroSimples(
					FiltroArrecadadorContrato.DESCRICAO_EMAIL, emailCliente));
		}

		Collection<ArrecadadorContrato> colecaoArrecadadorContrato = fachada
				.pesquisar(filtroArrecadadorContrato, ArrecadadorContrato.class
						.getName());

		if (colecaoArrecadadorContrato == null
				|| colecaoArrecadadorContrato.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Contrato de Arrecadador");
		} else {
			httpServletRequest.setAttribute("colecaoArrecadadorContrato",
					colecaoArrecadadorContrato);
			ArrecadadorContrato arrecadadorContrato = new ArrecadadorContrato();
			arrecadadorContrato = (ArrecadadorContrato) Util
					.retonarObjetoDeColecao(colecaoArrecadadorContrato);
			String idRegistroAtualizacao = arrecadadorContrato.getId()
					.toString();
			sessao.setAttribute("idRegistroAtualizacao", idRegistroAtualizacao);
		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		sessao.setAttribute("filtroArrecadadorContrato",
				filtroArrecadadorContrato);

		httpServletRequest.setAttribute("filtroArrecadadorContrato",
				filtroArrecadadorContrato);

		return retorno;

	}
}
