package gcom.gui.micromedicao.leitura;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Thiago Tenório
 * @date 05/08/2006
 */
public class FiltrarAnormalidadeLeituraAction extends GcomAction {

	/**
	 * Este caso de uso permite Pesquisar um Tipo de Servicço
	 * 
	 * [UC0437] Pesquisar Tipo de Serviço de Referência
	 * 
	 * 
	 * @author Thiago Tenório
	 * @date 31/07/2006
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
				.findForward("exibirManterAnormalidadeLeitura");

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarAnormalidadeLeituraActionForm filtrarAnormalidadeLeituraActionForm = (FiltrarAnormalidadeLeituraActionForm) actionForm;

		FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();

		// Fachada fachada = Fachada.getInstancia();

		boolean peloMenosUmParametroInformado = false;

		String descricao = filtrarAnormalidadeLeituraActionForm.getDescricao();

		String indicadorRelativoHidrometro = filtrarAnormalidadeLeituraActionForm
				.getIndicadorRelativoHidrometro();

		String indicadorImovelSemHidrometro = filtrarAnormalidadeLeituraActionForm
				.getIndicadorImovelSemHidrometro();

		String usoRestritoSistema = filtrarAnormalidadeLeituraActionForm
				.getUsoRestritoSistema();

		String perdaTarifaSocial = filtrarAnormalidadeLeituraActionForm
				.getPerdaTarifaSocial();

		String osAutomatico = filtrarAnormalidadeLeituraActionForm
				.getOsAutomatico();

		String tipoServico = filtrarAnormalidadeLeituraActionForm
				.getTipoServico();

		String consumoLeituraNaoInformado = filtrarAnormalidadeLeituraActionForm
				.getConsumoLeituraNaoInformado();

		String consumoLeituraInformado = filtrarAnormalidadeLeituraActionForm
				.getConsumoLeituraInformado();

		String leituraLeituraNaoturaInformado = filtrarAnormalidadeLeituraActionForm
				.getLeituraLeituraNaoturaInformado();

		String leituraLeituraInformado = filtrarAnormalidadeLeituraActionForm
				.getLeituraLeituraInformado();

		String tipoPesquisa = filtrarAnormalidadeLeituraActionForm
				.getTipoPesquisa();

		String indicadorUso = filtrarAnormalidadeLeituraActionForm
				.getIndicadorUso();

		/**
		 * TODO : COSANPA
		 * Pamela Gatinho - 13/03/2012
		 * Campo que identifica se a anormalidade será usada ou
		 * não no sistema de leitura e impressão simultanea.
		 */
		String indicadorImpressaoSimultanea = filtrarAnormalidadeLeituraActionForm.getIndicadorImpressaoSimultanea();
		
		// Verifica se o campo Descrição da Anormalidade de Leitura foi
		// informado

		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {
				filtroLeituraAnormalidade
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroLeituraAnormalidade.DESCRICAO, descricao));
			} else {
				filtroLeituraAnormalidade
						.adicionarParametro(new ComparacaoTexto(
								FiltroLeituraAnormalidade.DESCRICAO, descricao));
			}

		}

		// Verifica se o campo Anormalidade Relativa a Hidrômetro foi informado

		if (indicadorRelativoHidrometro != null
				&& !indicadorRelativoHidrometro.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.INDICADOR_RELATIVO_HIDROMETRO,
					indicadorRelativoHidrometro));

		}

		// Verifica se o campo Anormalidade Aceita para Ligação sem Hidrômetro
		// foi informado

		if (indicadorImovelSemHidrometro != null
				&& !indicadorImovelSemHidrometro.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.INDICADOR_IMOVEL_SEM_HIDROMETRO,
					indicadorImovelSemHidrometro));

		}

		// Verifica se o campo Anormalidade de Uso Restrito do Sistema foi
		// informado

		if (usoRestritoSistema != null
				&& !usoRestritoSistema.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.INDICADOR_USO_SISTEMA,
					usoRestritoSistema));

		}

		// Verifica se o campo Anormalidade Acarreta Perda Tarifa Social foi
		// informado

		if (perdaTarifaSocial != null
				&& !perdaTarifaSocial.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.INDICADOR_PERDA_TARIFA_SOCIAL,
					perdaTarifaSocial));

		}

		// Verifica se o campo Anormalidade Emite OS Automática foi informado

		if (perdaTarifaSocial != null
				&& !perdaTarifaSocial.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.INDICADOR_PERDA_TARIFA_SOCIAL,
					perdaTarifaSocial));

		}

		// Verifica se o campo Tipo de Serviço da OS Automática foi informado

		if (osAutomatico != null
				&& !osAutomatico.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.INDICADOR_EMISSAO_ORDEM_SERVICO,
					osAutomatico));

		}

		// Verifica se o campo Consumo a Ser Cobrado (leitura não informada) foi
		// informado

		if (tipoServico != null
				&& !tipoServico.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.ID_TIPO_SERVICO, tipoServico));

		}

		// Verifica se o campo Consumo a Ser Cobrado (leitura informada) foi
		// informado

		if (consumoLeituraNaoInformado != null
				&& !consumoLeituraNaoInformado.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.ID_CONSUMO_A_COBRAR_SEM_LEITURA,
					consumoLeituraNaoInformado));

		}

		// Verifica se o campo Leitura para faturamento (leitura não informada)
		// foi informado

		if (consumoLeituraInformado != null
				&& !consumoLeituraInformado.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.ID_CONSUMO_A_COBRAR_COM_LEITURA,
					consumoLeituraInformado));

		}

		// Verifica se o campo Referência do Tipo de Serviço foi informado

		if (leituraLeituraNaoturaInformado != null
				&& !leituraLeituraNaoturaInformado.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.ID_LEITURA_A_FATURAR_SEM_LEITURA,
					leituraLeituraNaoturaInformado));

		}

		// Verifica se o campo Leitura para faturamento ( leitura informada) foi
		// informado

		if (leituraLeituraInformado != null
				&& !leituraLeituraInformado.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.ID_LEITURA_A_FATURAR_COM_LEITURA,
					leituraLeituraInformado));

		}

		if (indicadorUso != null && !indicadorUso.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;

			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.INDICADOR_USO, indicadorUso));
		}

		if ( indicadorImpressaoSimultanea != null &&
				 !indicadorImpressaoSimultanea.trim().equalsIgnoreCase( "" )){
				peloMenosUmParametroInformado = true;

				filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
						FiltroLeituraAnormalidade.INDICADOR_IMPRESSAO_SIMULTANEA,
						indicadorImpressaoSimultanea));			
		}
		
		// Erro caso o usuário mandou Pesquisar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// filtroGerenciaRegional.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");

		sessao.setAttribute("filtroLeituraAnormalidade",
				filtroLeituraAnormalidade);

		return retorno;
	}

}
