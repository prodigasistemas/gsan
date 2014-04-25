package gcom.gui.cobranca;

import gcom.cobranca.FiltroCobrancaCriterio;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * processamento para filtrar o criterio da cobrança
 * 
 * @author Sávio Luiz
 * @date 05/05/2006
 */
public class FiltrarCriterioCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterCriterioCobranca");

		HttpSession sessao = httpServletRequest.getSession(false);

		CriterioCobrancaFiltrarActionForm criterioCobrancaFiltrarActionForm = (CriterioCobrancaFiltrarActionForm) actionForm;

		// Recupera os parâmetros do form
		String descricaoCriterioCobranca = criterioCobrancaFiltrarActionForm
				.getDescricaoCriterio();
		String dataInicioVigencia = criterioCobrancaFiltrarActionForm
				.getDataInicioVigencia();
		String numeroAnosContaAntiga = criterioCobrancaFiltrarActionForm
				.getNumeroAnoContaAntiga();
		String opcaoAcaoImovelSitEspecial = criterioCobrancaFiltrarActionForm
				.getOpcaoAcaoImovelSitEspecial();
		String opcaoAcaoImovelSit = criterioCobrancaFiltrarActionForm
				.getOpcaoAcaoImovelSit();
		String opcaoContasRevisao = criterioCobrancaFiltrarActionForm
				.getOpcaoContasRevisao();
		String opcaoAcaoImovelDebitoMesConta = criterioCobrancaFiltrarActionForm
				.getOpcaoAcaoImovelDebitoMesConta();
		String opcaoAcaoInquilinoDebitoMesConta = criterioCobrancaFiltrarActionForm
				.getOpcaoAcaoInquilinoDebitoMesConta();
		String opcaoAcaoImovelDebitoContasAntigas = criterioCobrancaFiltrarActionForm
				.getOpcaoAcaoImovelDebitoContasAntigas();
		String indicadorUso = criterioCobrancaFiltrarActionForm
				.getIndicadorUso();
		String indicadorAtualizar = httpServletRequest
				.getParameter("indicadorAtualizar");

		if (indicadorAtualizar == null) {
			criterioCobrancaFiltrarActionForm.setIndicadorAtualizar("2");
		} else {
			criterioCobrancaFiltrarActionForm
					.setIndicadorAtualizar(indicadorAtualizar);
		}

		FiltroCobrancaCriterio filtroCobrancaCriterio = new FiltroCobrancaCriterio(
				FiltroCobrancaCriterio.ID);

		boolean peloMenosUmParametroInformado = false;

		// Insere os parâmetros informados no filtro

		if (descricaoCriterioCobranca != null
				&& !descricaoCriterioCobranca.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroCobrancaCriterio.adicionarParametro(new ComparacaoTexto(
					FiltroCobrancaCriterio.DESCRICAO_COBRANCA_CRITERIO,
					descricaoCriterioCobranca));
		}
		if (dataInicioVigencia != null
				&& !dataInicioVigencia.trim().equalsIgnoreCase("")) {
			Date dataVigencia = Util.converteStringParaDate(dataInicioVigencia);
			peloMenosUmParametroInformado = true;
			filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
					FiltroCobrancaCriterio.DATA_INICIO_VIGENCIA, dataVigencia));
		}
		if (numeroAnosContaAntiga != null
				&& !numeroAnosContaAntiga.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
					FiltroCobrancaCriterio.NUMERO_ANOS_CONTA_ANTIGA,
					numeroAnosContaAntiga));
		}

		if (opcaoAcaoImovelSitEspecial != null
				&& !opcaoAcaoImovelSitEspecial.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (!opcaoAcaoImovelSitEspecial.equalsIgnoreCase("3")) {
				filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
						FiltroCobrancaCriterio.INDICADOR_IMOVEL_PARALISACAO,
						opcaoAcaoImovelSitEspecial));
			}
		}
		if (opcaoAcaoImovelSit != null
				&& !opcaoAcaoImovelSit.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (!opcaoAcaoImovelSit.equalsIgnoreCase("3")) {
				filtroCobrancaCriterio
						.adicionarParametro(new ParametroSimples(
								FiltroCobrancaCriterio.INDICADOR_IMOVEL_SITUACAO_COBRANCA,
								opcaoAcaoImovelSit));
			}
		}
		if (opcaoContasRevisao != null
				&& !opcaoContasRevisao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (!opcaoContasRevisao.equalsIgnoreCase("3")) {
				filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
						FiltroCobrancaCriterio.INDICADOR_CONTA_REVISAO,
						opcaoContasRevisao));
			}
		}
		if (opcaoAcaoImovelDebitoMesConta != null
				&& !opcaoAcaoImovelDebitoMesConta.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (!opcaoAcaoImovelDebitoMesConta.equalsIgnoreCase("3")) {
				filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
						FiltroCobrancaCriterio.INDICADOR_DEBITO_CONTA_MES,
						opcaoAcaoImovelDebitoMesConta));
			}
		}
		if (opcaoAcaoInquilinoDebitoMesConta != null
				&& !opcaoAcaoInquilinoDebitoMesConta.trim()
						.equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (!opcaoAcaoInquilinoDebitoMesConta.equalsIgnoreCase("3")) {
				filtroCobrancaCriterio
						.adicionarParametro(new ParametroSimples(
								FiltroCobrancaCriterio.INDICADOR_INQUILINO_DEBITO_CONTA_MES,
								opcaoAcaoInquilinoDebitoMesConta));
			}
		}
		if (opcaoAcaoImovelDebitoContasAntigas != null
				&& !opcaoAcaoImovelDebitoContasAntigas.trim().equalsIgnoreCase(
						"")) {
			peloMenosUmParametroInformado = true;
			if (!opcaoAcaoImovelDebitoContasAntigas.equalsIgnoreCase("3")) {
				filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
						FiltroCobrancaCriterio.INDICADOR_DEBITO_CONTA_ANTIGA,
						opcaoAcaoImovelDebitoContasAntigas));
			}
		}

		if ((indicadorUso != null && !indicadorUso.equals(""
				+ ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& (!indicadorUso.equals("3"))) {

			filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
					FiltroCobrancaCriterio.INDICADOR_USO, indicadorUso));

			peloMenosUmParametroInformado = true;
		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// Manda o filtro pelo request para o ExibirManterClienteAction
		sessao.setAttribute("filtroCobrancaCriterio", filtroCobrancaCriterio);
		sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		return retorno;

	}
}
