package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.bean.FiltrarImovelOutrosCriteriosHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarImovelOutrosCriteriosAction extends GcomAction {

	/**
	 * [UC0101] - Consistir Leituras e Calcular Consumos ----> (ID caso de uso)
	 * [SF0002] - Determinar Dados para Faturamento de Esgoto ----->(ID
	 * subfluxo) Autor: Rhawi Dantas, Flavio Data: 07/12/2006
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("gerarRelatorioManterImovel");
		// /gerarRelatorioImovelOutrosCriteriosManterAction
		ImovelOutrosCriteriosActionForm imovelLocalizacaoFiltrarActionForm = (ImovelOutrosCriteriosActionForm) actionForm;

		HttpSession session = httpServletRequest.getSession(false);
		// Recupera os parâmetros do form

		String tipoRelatorio = (String) session
				.getAttribute("parametroGerarRelatorio");

		// Fachada fachada = Fachada.getInstancia();

		boolean peloMenosUmParametroInformado = false;

		FiltrarImovelOutrosCriteriosHelper filtrarImovelOutrosCriteriosHelper = new FiltrarImovelOutrosCriteriosHelper();

		filtrarImovelOutrosCriteriosHelper.setTipoRelatorio(tipoRelatorio);

		filtrarImovelOutrosCriteriosHelper
				.setNomeCliente(imovelLocalizacaoFiltrarActionForm
						.getNomeCliente() == null ? ""
						: imovelLocalizacaoFiltrarActionForm.getNomeCliente());

		filtrarImovelOutrosCriteriosHelper
				.setId(imovelLocalizacaoFiltrarActionForm.getId() == null ? ""
						: imovelLocalizacaoFiltrarActionForm.getId());

		filtrarImovelOutrosCriteriosHelper
				.setDescricao(imovelLocalizacaoFiltrarActionForm.getDescricao() == null ? ""
						: imovelLocalizacaoFiltrarActionForm.getDescricao());

		filtrarImovelOutrosCriteriosHelper
				.setIndicadorUso(imovelLocalizacaoFiltrarActionForm
						.getIndicadorUso() == null ? ""
						: imovelLocalizacaoFiltrarActionForm.getIndicadorUso());

		filtrarImovelOutrosCriteriosHelper
				.setIntervaloMediaMinimaHidrometroInicio(imovelLocalizacaoFiltrarActionForm
						.getIntervaloMediaMinimaHidrometroInicio() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getIntervaloMediaMinimaHidrometroInicio());

		filtrarImovelOutrosCriteriosHelper
				.setIntervaloMediaMinimaHidrometroFinal(imovelLocalizacaoFiltrarActionForm
						.getIntervaloMediaMinimaHidrometroFinal() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getIntervaloMediaMinimaHidrometroFinal());

		filtrarImovelOutrosCriteriosHelper
				.setIntervaloMediaMinimaImovelInicio(imovelLocalizacaoFiltrarActionForm
						.getIntervaloMediaMinimaImovelInicio() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getIntervaloMediaMinimaImovelInicio());

		filtrarImovelOutrosCriteriosHelper
				.setIntervaloMediaMinimaImovelFinal(imovelLocalizacaoFiltrarActionForm
						.getIntervaloMediaMinimaImovelFinal() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getIntervaloMediaMinimaImovelFinal());

		filtrarImovelOutrosCriteriosHelper
				.setIndicadorMedicao(imovelLocalizacaoFiltrarActionForm
						.getIndicadorMedicao() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getIndicadorMedicao());

		filtrarImovelOutrosCriteriosHelper
				.setIntervaloPercentualEsgotoInicial(imovelLocalizacaoFiltrarActionForm
						.getIntervaloPercentualEsgotoInicial() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getIntervaloPercentualEsgotoInicial());

		filtrarImovelOutrosCriteriosHelper
				.setIntervaloPercentualEsgotoFinal(imovelLocalizacaoFiltrarActionForm
						.getIntervaloPercentualEsgotoFinal() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getIntervaloPercentualEsgotoFinal());

		filtrarImovelOutrosCriteriosHelper
				.setInscricaoTipo(imovelLocalizacaoFiltrarActionForm
						.getInscricaoTipo() == null ? "-1"
						: imovelLocalizacaoFiltrarActionForm.getInscricaoTipo());

		filtrarImovelOutrosCriteriosHelper
				.setConsumoMinimo(imovelLocalizacaoFiltrarActionForm
						.getConsumoMinimo() == null ? ""
						: imovelLocalizacaoFiltrarActionForm.getConsumoMinimo());

		filtrarImovelOutrosCriteriosHelper
				.setNomeMunicipio(imovelLocalizacaoFiltrarActionForm
						.getNomeMunicipio() == null ? ""
						: imovelLocalizacaoFiltrarActionForm.getNomeMunicipio());

		filtrarImovelOutrosCriteriosHelper
				.setIdNomeConta(imovelLocalizacaoFiltrarActionForm
						.getIdNomeConta() == null ? ""
						: imovelLocalizacaoFiltrarActionForm.getIdNomeConta());

		filtrarImovelOutrosCriteriosHelper
				.setIdImovelPrincipal(imovelLocalizacaoFiltrarActionForm
						.getIdImovelPrincipal() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getIdImovelPrincipal());

		filtrarImovelOutrosCriteriosHelper
				.setIdImovelCondominio(imovelLocalizacaoFiltrarActionForm
						.getIdImovelCondominio() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getIdImovelCondominio());

		filtrarImovelOutrosCriteriosHelper
				.setTipoRelacao(imovelLocalizacaoFiltrarActionForm
						.getTipoRelacao() == null ? ""
						: imovelLocalizacaoFiltrarActionForm.getTipoRelacao());

		filtrarImovelOutrosCriteriosHelper
				.setIdCliente(imovelLocalizacaoFiltrarActionForm.getIdCliente() == null ? ""
						: imovelLocalizacaoFiltrarActionForm.getIdCliente());

		filtrarImovelOutrosCriteriosHelper
				.setLoteDestino(imovelLocalizacaoFiltrarActionForm
						.getLoteDestino() == null ? ""
						: imovelLocalizacaoFiltrarActionForm.getLoteDestino());

		filtrarImovelOutrosCriteriosHelper
				.setQuadraDestinoNM(imovelLocalizacaoFiltrarActionForm
						.getQuadraDestinoNM() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getQuadraDestinoNM());

		filtrarImovelOutrosCriteriosHelper
				.setIdBairro(imovelLocalizacaoFiltrarActionForm.getIdBairro() == null ? ""
						: imovelLocalizacaoFiltrarActionForm.getIdBairro());

		filtrarImovelOutrosCriteriosHelper
				.setNomeBairro(imovelLocalizacaoFiltrarActionForm
						.getNomeBairro() == null ? ""
						: imovelLocalizacaoFiltrarActionForm.getNomeBairro());

		filtrarImovelOutrosCriteriosHelper
				.setLoteOrigem(imovelLocalizacaoFiltrarActionForm
						.getLoteOrigem() == null ? ""
						: imovelLocalizacaoFiltrarActionForm.getLoteOrigem());

		filtrarImovelOutrosCriteriosHelper
				.setIdLocalidade(imovelLocalizacaoFiltrarActionForm
						.getIdLocalidade() == null ? ""
						: imovelLocalizacaoFiltrarActionForm.getIdLocalidade());

		filtrarImovelOutrosCriteriosHelper
				.setNomeLocalidadeOrigem(imovelLocalizacaoFiltrarActionForm
						.getNomeLocalidadeOrigem() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getNomeLocalidadeOrigem());

		filtrarImovelOutrosCriteriosHelper
				.setNomeSetorComercialOrigem(imovelLocalizacaoFiltrarActionForm
						.getNomeSetorComercialOrigem() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getNomeSetorComercialOrigem());

		filtrarImovelOutrosCriteriosHelper
				.setQuadraOrigemNM(imovelLocalizacaoFiltrarActionForm
						.getQuadraOrigemNM() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getQuadraOrigemNM());

		filtrarImovelOutrosCriteriosHelper
				.setQuadraMensagemOrigem(imovelLocalizacaoFiltrarActionForm
						.getQuadraMensagemOrigem() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getQuadraMensagemOrigem());

		filtrarImovelOutrosCriteriosHelper
				.setNomeLocalidadeDestino(imovelLocalizacaoFiltrarActionForm
						.getNomeLocalidadeDestino() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getNomeLocalidadeDestino());

		filtrarImovelOutrosCriteriosHelper
				.setSetorComercialDestinoCD(imovelLocalizacaoFiltrarActionForm
						.getSetorComercialDestinoCD() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getSetorComercialDestinoCD());

		filtrarImovelOutrosCriteriosHelper
				.setSetorComercialOrigemCD(imovelLocalizacaoFiltrarActionForm
						.getSetorComercialOrigemCD() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getSetorComercialOrigemCD());

		filtrarImovelOutrosCriteriosHelper
				.setSetorComercialOrigemID(imovelLocalizacaoFiltrarActionForm
						.getSetorComercialOrigemID() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getSetorComercialOrigemID());

		filtrarImovelOutrosCriteriosHelper
				.setQuadraOrigemID(imovelLocalizacaoFiltrarActionForm
						.getQuadraOrigemID() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getQuadraOrigemID());

		filtrarImovelOutrosCriteriosHelper
				.setLocalidadeDestinoID(imovelLocalizacaoFiltrarActionForm
						.getLocalidadeDestinoID() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getLocalidadeDestinoID());

		filtrarImovelOutrosCriteriosHelper
				.setLocalidadeOrigemID(imovelLocalizacaoFiltrarActionForm
						.getLocalidadeOrigemID() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getLocalidadeOrigemID());

		filtrarImovelOutrosCriteriosHelper
				.setNomeSetorComercialDestino(imovelLocalizacaoFiltrarActionForm
						.getNomeSetorComercialDestino() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getNomeSetorComercialDestino());

		filtrarImovelOutrosCriteriosHelper
				.setSetorComercialDestinoID(imovelLocalizacaoFiltrarActionForm
						.getSetorComercialDestinoID() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getSetorComercialDestinoID());

		filtrarImovelOutrosCriteriosHelper
				.setQuadraMensagemDestino(imovelLocalizacaoFiltrarActionForm
						.getQuadraMensagemDestino() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getQuadraMensagemDestino());

		filtrarImovelOutrosCriteriosHelper
				.setIdGerenciaRegional(imovelLocalizacaoFiltrarActionForm
						.getIdGerenciaRegional() == null ? "-1"
						: imovelLocalizacaoFiltrarActionForm
								.getIdGerenciaRegional());

		filtrarImovelOutrosCriteriosHelper
				.setQuadraDestinoID(imovelLocalizacaoFiltrarActionForm
						.getQuadraDestinoID() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getQuadraDestinoID());

		filtrarImovelOutrosCriteriosHelper
				.setIdClienteTipo(imovelLocalizacaoFiltrarActionForm
						.getIdClienteTipo() == null ? "-1"
						: imovelLocalizacaoFiltrarActionForm.getDescricao());

		filtrarImovelOutrosCriteriosHelper
				.setIdMunicipio(imovelLocalizacaoFiltrarActionForm
						.getIdMunicipio() == null ? ""
						: imovelLocalizacaoFiltrarActionForm.getIdMunicipio());

		filtrarImovelOutrosCriteriosHelper
				.setConsumoMinimoInicial(imovelLocalizacaoFiltrarActionForm
						.getConsumoMinimoInicial() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getConsumoMinimoInicial());

		filtrarImovelOutrosCriteriosHelper
				.setConsumoMinimoFinal(imovelLocalizacaoFiltrarActionForm
						.getConsumoMinimoFinal() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getConsumoMinimoFinal());

		filtrarImovelOutrosCriteriosHelper
				.setSituacaoAgua(imovelLocalizacaoFiltrarActionForm
						.getSituacaoAgua() == null ? "-1"
						: imovelLocalizacaoFiltrarActionForm.getSituacaoAgua());

		filtrarImovelOutrosCriteriosHelper
				.setSituacaoLigacaoEsgoto(imovelLocalizacaoFiltrarActionForm
						.getSituacaoLigacaoEsgoto() == null ? "-1"
						: imovelLocalizacaoFiltrarActionForm
								.getSituacaoLigacaoEsgoto());

		filtrarImovelOutrosCriteriosHelper
				.setConsumoMinimoFixadoEsgotoInicial(imovelLocalizacaoFiltrarActionForm
						.getConsumoMinimoFixadoEsgotoInicial() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getConsumoMinimoFixadoEsgotoInicial());

		filtrarImovelOutrosCriteriosHelper
				.setConsumoMinimoFixadoEsgotoFinal(imovelLocalizacaoFiltrarActionForm
						.getConsumoMinimoFixadoEsgotoFinal() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getConsumoMinimoFixadoEsgotoFinal());

		filtrarImovelOutrosCriteriosHelper
				.setTipoMedicao(imovelLocalizacaoFiltrarActionForm
						.getTipoMedicao() == null ? "-1"
						: imovelLocalizacaoFiltrarActionForm.getTipoMedicao());

		filtrarImovelOutrosCriteriosHelper
				.setPerfilImovel(imovelLocalizacaoFiltrarActionForm
						.getPerfilImovel() == null ? "-1"
						: imovelLocalizacaoFiltrarActionForm.getPerfilImovel());

		filtrarImovelOutrosCriteriosHelper
				.setCategoriaImovel(imovelLocalizacaoFiltrarActionForm
						.getCategoriaImovel() == null ? "-1"
						: imovelLocalizacaoFiltrarActionForm
								.getCategoriaImovel());

		filtrarImovelOutrosCriteriosHelper
				.setSubcategoria(imovelLocalizacaoFiltrarActionForm
						.getSubcategoria() == null ? "-1"
						: imovelLocalizacaoFiltrarActionForm.getSubcategoria());

		filtrarImovelOutrosCriteriosHelper
				.setIndicadorCodigoBarra(imovelLocalizacaoFiltrarActionForm
						.getIndicadorCodigoBarra() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getIndicadorCodigoBarra());

		String parametroGerarRelatorio = (String) session
				.getAttribute("parametroGerarRelatorio");

		if (parametroGerarRelatorio.trim()
				.equalsIgnoreCase("RelatorioEconomia")) {
			filtrarImovelOutrosCriteriosHelper
					.setQuantidadeEconomiasInicial("2");

			filtrarImovelOutrosCriteriosHelper
					.setQuantidadeEconomiasFinal(imovelLocalizacaoFiltrarActionForm
							.getQuantidadeEconomiasFinal() == null ? "9999"
							: imovelLocalizacaoFiltrarActionForm
									.getQuantidadeEconomiasFinal());
		} else {
			filtrarImovelOutrosCriteriosHelper
					.setQuantidadeEconomiasInicial(imovelLocalizacaoFiltrarActionForm
							.getQuantidadeEconomiasInicial() == null ? ""
							: imovelLocalizacaoFiltrarActionForm
									.getQuantidadeEconomiasInicial());

			filtrarImovelOutrosCriteriosHelper
					.setQuantidadeEconomiasFinal(imovelLocalizacaoFiltrarActionForm
							.getQuantidadeEconomiasFinal() == null ? ""
							: imovelLocalizacaoFiltrarActionForm
									.getQuantidadeEconomiasFinal());
		}
		filtrarImovelOutrosCriteriosHelper
				.setNumeroPontosInicial(imovelLocalizacaoFiltrarActionForm
						.getNumeroPontosInicial() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getNumeroPontosInicial());

		filtrarImovelOutrosCriteriosHelper
				.setNumeroPontosFinal(imovelLocalizacaoFiltrarActionForm
						.getNumeroPontosFinal() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getNumeroPontosFinal());

		filtrarImovelOutrosCriteriosHelper
				.setNumeroMoradoresInicial(imovelLocalizacaoFiltrarActionForm
						.getNumeroMoradoresInicial() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getNumeroMoradoresInicial());

		filtrarImovelOutrosCriteriosHelper
				.setNumeroMoradoresFinal(imovelLocalizacaoFiltrarActionForm
						.getNumeroMoradoresFinal() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getNumeroMoradoresFinal());

		filtrarImovelOutrosCriteriosHelper
				.setAreaConstruidaInicial(imovelLocalizacaoFiltrarActionForm
						.getAreaConstruidaInicial() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getAreaConstruidaInicial());

		filtrarImovelOutrosCriteriosHelper
				.setAreaConstruidaFinal(imovelLocalizacaoFiltrarActionForm
						.getAreaConstruidaFinal() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getAreaConstruidaFinal());

		filtrarImovelOutrosCriteriosHelper
				.setAreaConstruidaFaixa(imovelLocalizacaoFiltrarActionForm
						.getAreaConstruidaFaixa() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getAreaConstruidaFaixa());

		filtrarImovelOutrosCriteriosHelper
				.setTipoPoco(imovelLocalizacaoFiltrarActionForm.getTipoPoco() == null ? "-1"
						: imovelLocalizacaoFiltrarActionForm.getTipoPoco());

		filtrarImovelOutrosCriteriosHelper
				.setTipoSituacaoEspecialFaturamento(imovelLocalizacaoFiltrarActionForm
						.getTipoSituacaoEspecialFaturamento() == null ? "-1"
						: imovelLocalizacaoFiltrarActionForm
								.getTipoSituacaoEspecialFaturamento());

		filtrarImovelOutrosCriteriosHelper
				.setTipoSituacaoEspecialCobranca(imovelLocalizacaoFiltrarActionForm
						.getTipoSituacaoEspecialCobranca() == null ? "-1"
						: imovelLocalizacaoFiltrarActionForm
								.getTipoSituacaoEspecialCobranca());

		filtrarImovelOutrosCriteriosHelper
				.setSituacaoCobranca(imovelLocalizacaoFiltrarActionForm
						.getSituacaoCobranca() == null ? "-1"
						: imovelLocalizacaoFiltrarActionForm
								.getSituacaoCobranca());

		filtrarImovelOutrosCriteriosHelper
				.setDiaVencimentoAlternativo(imovelLocalizacaoFiltrarActionForm
						.getDiaVencimentoAlternativo() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getDiaVencimentoAlternativo());

		filtrarImovelOutrosCriteriosHelper
				.setAnormalidadeElo(imovelLocalizacaoFiltrarActionForm
						.getAnormalidadeElo() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getAnormalidadeElo());

		filtrarImovelOutrosCriteriosHelper
				.setOcorrenciaCadastro(imovelLocalizacaoFiltrarActionForm
						.getOcorrenciaCadastro() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getOcorrenciaCadastro());

		filtrarImovelOutrosCriteriosHelper
				.setTarifaConsumo(imovelLocalizacaoFiltrarActionForm
						.getTarifaConsumo() == null ? "-1"
						: imovelLocalizacaoFiltrarActionForm.getTarifaConsumo());

		filtrarImovelOutrosCriteriosHelper
				.setCEP(imovelLocalizacaoFiltrarActionForm.getCEP() == null ? ""
						: imovelLocalizacaoFiltrarActionForm.getCEP());

		filtrarImovelOutrosCriteriosHelper
				.setIdLogradouro(imovelLocalizacaoFiltrarActionForm
						.getIdLogradouro() == null ? ""
						: imovelLocalizacaoFiltrarActionForm.getIdLogradouro());

		filtrarImovelOutrosCriteriosHelper
				.setTarifaSocialCartaoTipoId(imovelLocalizacaoFiltrarActionForm
						.getTarifaSocialCartaoTipoId() == null ? "-1"
						: imovelLocalizacaoFiltrarActionForm
								.getTarifaSocialCartaoTipoId());

		filtrarImovelOutrosCriteriosHelper
				.setTarifaSocialRendaTipoId(imovelLocalizacaoFiltrarActionForm
						.getTarifaSocialRendaTipoId() == null ? "-1"
						: imovelLocalizacaoFiltrarActionForm
								.getTarifaSocialRendaTipoId());

		filtrarImovelOutrosCriteriosHelper
				.setTarifaSocialExclusaoMotivoId(imovelLocalizacaoFiltrarActionForm
						.getTarifaSocialExclusaoMotivoId() == null ? "-1"
						: imovelLocalizacaoFiltrarActionForm
								.getTarifaSocialExclusaoMotivoId());

		filtrarImovelOutrosCriteriosHelper
				.setNomeLogradouro(imovelLocalizacaoFiltrarActionForm
						.getNomeLogradouro() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getNomeLogradouro());
		filtrarImovelOutrosCriteriosHelper
				.setCdRotaInicial(imovelLocalizacaoFiltrarActionForm
						.getCdRotaInicial() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getCdRotaInicial());

		filtrarImovelOutrosCriteriosHelper
				.setCdRotaFinal(imovelLocalizacaoFiltrarActionForm
						.getCdRotaFinal() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getCdRotaFinal());

		filtrarImovelOutrosCriteriosHelper
				.setSequencialRotaInicial(imovelLocalizacaoFiltrarActionForm
						.getSequencialRotaInicial() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getSequencialRotaInicial());

		filtrarImovelOutrosCriteriosHelper
				.setSequencialRotaFinal(imovelLocalizacaoFiltrarActionForm
						.getSequencialRotaFinal() == null ? ""
						: imovelLocalizacaoFiltrarActionForm
								.getSequencialRotaFinal());
		
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);

		if (tipoRelatorio != null
				&& tipoRelatorio.equalsIgnoreCase("RelatorioEconomia")) {
			retorno = actionMapping
					.findForward("gerarRelatorioImovelOutrosCriteriosEconomia");

		} else if (tipoRelatorio != null
				&& tipoRelatorio.equalsIgnoreCase("RelatorioTarifaSocial")) {
			retorno = actionMapping
					.findForward("gerarRelatorioImovelOutrosCriteriosTarifa");

			/*--<merge>--
			 * Collection<Imovel> collectionImoveis = fachada
			 .pesquisarImovelOutrosCriterios(filtrarImovelOutrosCriteriosHelper);*/
			Collection<Imovel> collectionImoveis = null;

			if (collectionImoveis == null || collectionImoveis.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}

			httpServletRequest.setAttribute("collectionImoveis",
					collectionImoveis);
		} else if (tipoRelatorio != null
				&& tipoRelatorio.trim().equalsIgnoreCase(
						"consultarTarifaExcluida")) {
			retorno = actionMapping.findForward("consultarTarifaExcluida");

			/*--<merge>--
			 * Collection<Imovel> collectionImoveis = fachada
			 .pesquisarImovelOutrosCriterios(filtrarImovelOutrosCriteriosHelper);*/
			Collection<Imovel> collectionImoveis = null;

			if (collectionImoveis == null || collectionImoveis.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}

			httpServletRequest.setAttribute("collectionImoveis",
					collectionImoveis);
		} else if (tipoRelatorio != null
				&& tipoRelatorio.trim().equalsIgnoreCase("GerarRelacaoDebito")) {
			retorno = actionMapping.findForward("gerarRelacaoDebitos");
		} else if (tipoRelatorio != null
				&& tipoRelatorio.trim().equalsIgnoreCase(
						"GerarRelatorioAcompanhamentoFaturamento")) {
			retorno = actionMapping
					.findForward("gerarRelatorioAcompanhamentoFaturamento");
		} else if (tipoRelatorio != null
				&& tipoRelatorio.equalsIgnoreCase("RelatorioImoveisEndereco")) {
			retorno = actionMapping
					.findForward("gerarRelatorioImovelOutrosCriteriosEndereco");
		} else if (tipoRelatorio != null
				&& tipoRelatorio
						.equalsIgnoreCase("RelatorioCadastroConsumidoresInscricao")) {
			retorno = actionMapping
					.findForward("gerarRelatorioCadastroConsumidoresInscricao");
		} else if (tipoRelatorio != null
				&& tipoRelatorio.equalsIgnoreCase("EmitirBoletimCadastro")) {
			retorno = actionMapping.findForward("gerarEmissaoBoletimCadastro");
		}
		
		if(imovelLocalizacaoFiltrarActionForm.getIndicadorCpfCnpjInformado()!=null 
				&& imovelLocalizacaoFiltrarActionForm.getIndicadorCpfCnpjInformado()
					.equals(ConstantesSistema.SIM.toString())
						&& (imovelLocalizacaoFiltrarActionForm.getCpfCnpj()==null ||
							 (imovelLocalizacaoFiltrarActionForm.getCpfCnpj()!=null
									&& imovelLocalizacaoFiltrarActionForm.getCpfCnpj().equals("")))){
			
			throw new ActionServletException("atencao.cpf_cpnf_obrigatorio");

		}
		
		// id da genrencia regional
		String idGerenciaRegional = imovelLocalizacaoFiltrarActionForm
				.getIdGerenciaRegional();
		// id da undiade negocio
		String idUnidadeNegocio = imovelLocalizacaoFiltrarActionForm
				.getUnidadeNegocio();

		// numero da quadra origem
		String quadraInicial = imovelLocalizacaoFiltrarActionForm
				.getQuadraOrigemNM();
		// numero quadra destino
		String quadraFinal = imovelLocalizacaoFiltrarActionForm
				.getQuadraDestinoNM();
		// lote origem
		String loteOrigem = imovelLocalizacaoFiltrarActionForm.getLoteOrigem();
		// lote destino
		String loteDestino = imovelLocalizacaoFiltrarActionForm
				.getLoteDestino();
		// cep
		String cep = imovelLocalizacaoFiltrarActionForm.getCEP();
		// id localidade origem
		String idLocalidadeInicial = imovelLocalizacaoFiltrarActionForm
				.getLocalidadeOrigemID();
		// id localidade destino
		String idLocalidadeFinal = imovelLocalizacaoFiltrarActionForm
				.getLocalidadeDestinoID();
		// setor comercial origem ID
		String setorComercialInicial = imovelLocalizacaoFiltrarActionForm
				.getSetorComercialOrigemCD();
		// setor comercial destino ID
		String setorComercialFinal = imovelLocalizacaoFiltrarActionForm
				.getSetorComercialDestinoCD();
		// cliente ID
		String idCliente = imovelLocalizacaoFiltrarActionForm.getIdCliente();
		// municipio ID
		String municipio = imovelLocalizacaoFiltrarActionForm.getIdMunicipio();
		// bairro ID
		String bairro = imovelLocalizacaoFiltrarActionForm.getIdBairro();
		// logradouro ID
		String logradouro = imovelLocalizacaoFiltrarActionForm
				.getIdLogradouro();

		// cliente tipo ID
		String idClienteTipo = imovelLocalizacaoFiltrarActionForm
				.getDescricao();

		// cliente relacao tipo ID
		String idClienteRelacaoTipo = imovelLocalizacaoFiltrarActionForm
				.getIndicadorUso();

		// imovel condominio ID
		String idImovelCondominio = imovelLocalizacaoFiltrarActionForm
				.getIdImovelCondominio();
		// imovel Principal ID
		String idImovelPrincipal = imovelLocalizacaoFiltrarActionForm
				.getIdImovelPrincipal();
		// nome Conta ID
		String idNomeConta = imovelLocalizacaoFiltrarActionForm
				.getIdNomeConta();
		// situacao ligacao Agua
		String idSituacaoLigacaoAgua = imovelLocalizacaoFiltrarActionForm
				.getSituacaoAgua();
		// consumo Minimo Inicial agua
		String consumoMinimoInicialAgua = imovelLocalizacaoFiltrarActionForm
				.getConsumoMinimoInicial();
		// consumo Minimo Final agua
		String consumoMinimoFinalAgua = imovelLocalizacaoFiltrarActionForm
				.getConsumoMinimoFinal();

		// situacao Ligacao Esgoto
		String idSituacaoLigacaoEsgoto = imovelLocalizacaoFiltrarActionForm
				.getSituacaoLigacaoEsgoto();
		// consumo Minimo Fixado Esgoto Inicial
		String consumoMinimoFixadoEsgotoInicial = imovelLocalizacaoFiltrarActionForm
				.getConsumoMinimoFixadoEsgotoInicial();
		// consumo Minimo Fixado Esgoto Final
		String consumoMinimoFixadoEsgotoFinal = imovelLocalizacaoFiltrarActionForm
				.getConsumoMinimoFixadoEsgotoFinal();

		// intervalo Percentual Esgoto Inicial
		String intervaloValorPercentualEsgotoInicial = imovelLocalizacaoFiltrarActionForm
				.getIntervaloPercentualEsgotoInicial();
		// intervalor Percentual Esgoto Final
		String intervaloValorPercentualEsgotoFinal = imovelLocalizacaoFiltrarActionForm
				.getIntervaloPercentualEsgotoFinal();
		// indicador Medicao
		String indicadorMedicao = imovelLocalizacaoFiltrarActionForm
				.getIndicadorMedicao();
		// tipo Medicao ID
		String tipoMedicaoID = imovelLocalizacaoFiltrarActionForm
				.getTipoMedicao();
		// intervalo Media Minima Imovel Inicial
		String intervaloMediaMinimaImovelInicial = imovelLocalizacaoFiltrarActionForm
				.getIntervaloMediaMinimaImovelInicio();
		// intervalo Media Minima Imovel Final
		String intervaloMediaMinimaImovelFinal = imovelLocalizacaoFiltrarActionForm
				.getIntervaloMediaMinimaImovelFinal();
		// intervalo Media Minima Hidrometro Inicial
		String intervaloMediaMinimaHidrometroInicial = imovelLocalizacaoFiltrarActionForm
				.getIntervaloMediaMinimaHidrometroInicio();
		// intervalo Media Minima Hidrometro Final
		String intervaloMediaMinimaHidrometroFinal = imovelLocalizacaoFiltrarActionForm
				.getIntervaloMediaMinimaHidrometroFinal();
		// perfil Imovel ID
		String idImovelPerfil = imovelLocalizacaoFiltrarActionForm
				.getPerfilImovel();
		// categoria Imovel ID
		String idCategoria = imovelLocalizacaoFiltrarActionForm
				.getCategoriaImovel();
		// sub categoria ID
		String idSubCategoria = imovelLocalizacaoFiltrarActionForm
				.getSubcategoria();
		// quantidade Economias Inicial
		String quantidadeEconomiasInicial = imovelLocalizacaoFiltrarActionForm
				.getQuantidadeEconomiasInicial();
		// quantidade Economias Final
		String quantidadeEconomiasFinal = imovelLocalizacaoFiltrarActionForm
				.getQuantidadeEconomiasFinal();
		// numero Pontos Inicial
		String numeroPontosInicial = imovelLocalizacaoFiltrarActionForm
				.getNumeroPontosInicial();
		// numero Pontos Final
		String numeroPontosFinal = imovelLocalizacaoFiltrarActionForm
				.getNumeroPontosFinal();
		// numero Moradores Inicial
		String numeroMoradoresInicial = imovelLocalizacaoFiltrarActionForm
				.getNumeroMoradoresInicial();
		// numero Moradoras Final
		String numeroMoradoresFinal = imovelLocalizacaoFiltrarActionForm
				.getNumeroMoradoresFinal();
		// area Construida Inicial
		String areaConstruidaInicial = imovelLocalizacaoFiltrarActionForm
				.getAreaConstruidaInicial();
		// area Construida Final
		String areaConstruidaFinal = imovelLocalizacaoFiltrarActionForm
				.getAreaConstruidaFinal();
		// area Construida Faixa
		String idAreaConstruidaFaixa = imovelLocalizacaoFiltrarActionForm
				.getAreaConstruidaFaixa();
		// poco Tipo ID
		String idPocoTipo = imovelLocalizacaoFiltrarActionForm.getTipoPoco();
		// tipo Situacao Faturamento ID
		String tipoSituacaoFaturamentoID = imovelLocalizacaoFiltrarActionForm
				.getTipoSituacaoEspecialFaturamento();
		// tipo Situacao Especial Cobranca ID
		String tipoSituacaoEspecialCobrancaID = imovelLocalizacaoFiltrarActionForm
				.getTipoSituacaoEspecialCobranca();
		// situacao Cobranca ID
		String situacaoCobrancaID = imovelLocalizacaoFiltrarActionForm
				.getSituacaoCobranca();
		// dia Vencimento Alternativo
		String diaVencimentoAlternativo = imovelLocalizacaoFiltrarActionForm
				.getDiaVencimentoAlternativo();
		// ocorrencia Cadastro
		String idCadastroOcorrencia = imovelLocalizacaoFiltrarActionForm
				.getOcorrenciaCadastro();
		// tarifa Consumo
		String idConsumoTarifa = imovelLocalizacaoFiltrarActionForm
				.getTarifaConsumo();
		// anormalidade Elo
		String idEloAnormalidade = imovelLocalizacaoFiltrarActionForm
				.getAnormalidadeElo();
		
		//indicador Codigo de Barra
		String indicadorCodigoBarra = imovelLocalizacaoFiltrarActionForm
				.getIndicadorCodigoBarra();
		
		if(imovelLocalizacaoFiltrarActionForm.getCpfCnpj()!=null
				&& !imovelLocalizacaoFiltrarActionForm.getCpfCnpj().equals("")){
			
			peloMenosUmParametroInformado = true;

		}
		
		// gerencia regional
		if (idGerenciaRegional != null
				&& !idGerenciaRegional.equals("")
				&& !idGerenciaRegional.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString())) {
			peloMenosUmParametroInformado = true;
		}
		// unidade Negocio
		if (idUnidadeNegocio != null
				&& !idUnidadeNegocio.equals("")
				&& !idUnidadeNegocio.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString())) {
			peloMenosUmParametroInformado = true;
		}

		// localidade inicial e final
		if (((idLocalidadeInicial != null && !idLocalidadeInicial.equals("") && !idLocalidadeInicial
				.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString())) && (idLocalidadeFinal != null
				&& !idLocalidadeFinal.equals("") && !idLocalidadeFinal.trim()
				.equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString())))) {
			peloMenosUmParametroInformado = true;
		}
		// setor comercial inicial e final
		if (((setorComercialInicial != null
				&& !setorComercialInicial.equals("") && !setorComercialInicial
				.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString())) && (setorComercialFinal != null
				&& !setorComercialFinal.equals("") && !setorComercialFinal
				.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString())))) {
			peloMenosUmParametroInformado = true;
		}
		// quadra inicial e final
		if ((quadraInicial != null && !quadraInicial.equals("") && !quadraInicial
				.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString()))
				&& (quadraFinal != null && !quadraFinal.equals("") && !quadraFinal
						.trim().equalsIgnoreCase(
								new Integer(
										ConstantesSistema.NUMERO_NAO_INFORMADO)
										.toString()))) {
			peloMenosUmParametroInformado = true;
		}
		// lote
		if ((loteOrigem != null && !loteOrigem.equals("") && !loteOrigem.trim()
				.equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString()))
				&& (loteDestino != null && !loteDestino.equals("") && !loteDestino
						.trim().equalsIgnoreCase(
								new Integer(
										ConstantesSistema.NUMERO_NAO_INFORMADO)
										.toString()))) {
			peloMenosUmParametroInformado = true;
		}
		// cep
		if (cep != null
				&& !cep.equals("")
				&& !cep.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString())) {
			peloMenosUmParametroInformado = true;
		}
		// logradouro
		if (logradouro != null
				&& !logradouro.equals("")
				&& !logradouro.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString())) {
			peloMenosUmParametroInformado = true;
		}
		// bairro
		if (bairro != null
				&& !bairro.equals("")
				&& !bairro.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString())) {
			peloMenosUmParametroInformado = true;
		}
		// municipio
		if (municipio != null
				&& !municipio.equals("")
				&& !municipio.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString())) {
			peloMenosUmParametroInformado = true;
		}
		// consumo minimo agua inicial e final
		if ((consumoMinimoInicialAgua != null
				&& !consumoMinimoInicialAgua.equals("") && !consumoMinimoInicialAgua
				.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString()))
				&& (consumoMinimoFinalAgua != null
						&& !consumoMinimoFinalAgua.equals("") && !consumoMinimoFinalAgua
						.trim().equalsIgnoreCase(
								new Integer(
										ConstantesSistema.NUMERO_NAO_INFORMADO)
										.toString()))) {
			peloMenosUmParametroInformado = true;
		}
		// consumo minimo esgoto inicial e final
		if ((consumoMinimoFixadoEsgotoInicial != null
				&& !consumoMinimoFixadoEsgotoInicial.equals("") && !consumoMinimoFixadoEsgotoInicial
				.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString()))
				&& (consumoMinimoFixadoEsgotoFinal != null
						&& !consumoMinimoFixadoEsgotoFinal.equals("") && !consumoMinimoFixadoEsgotoFinal
						.trim().equalsIgnoreCase(
								new Integer(
										ConstantesSistema.NUMERO_NAO_INFORMADO)
										.toString()))) {
			peloMenosUmParametroInformado = true;
		}
		// percentual esgoto inicial e final
		if ((intervaloValorPercentualEsgotoInicial != null
				&& !intervaloValorPercentualEsgotoInicial.equals("") && !intervaloValorPercentualEsgotoInicial
				.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString()))
				&& (intervaloValorPercentualEsgotoFinal != null
						&& !intervaloValorPercentualEsgotoFinal.equals("") && !intervaloValorPercentualEsgotoFinal
						.trim().equalsIgnoreCase(
								new Integer(
										ConstantesSistema.NUMERO_NAO_INFORMADO)
										.toString()))) {
			peloMenosUmParametroInformado = true;
		}
		// imovel condominio
		if (idImovelCondominio != null
				&& !idImovelCondominio.equals("")
				&& !idImovelCondominio.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString())) {
			peloMenosUmParametroInformado = true;
		}
		// imovel principal
		if (idImovelPrincipal != null
				&& !idImovelPrincipal.equals("")
				&& !idImovelPrincipal.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString())) {
			peloMenosUmParametroInformado = true;
		}

		// nome conta
		if (idNomeConta != null
				&& !idNomeConta.equals("")
				&& !idNomeConta.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString())) {
			peloMenosUmParametroInformado = true;
		}
		// Situacao Ligacao Agua
		if (idSituacaoLigacaoAgua != null
				&& !idSituacaoLigacaoAgua.equals("")
				&& !idSituacaoLigacaoAgua.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString())) {
			peloMenosUmParametroInformado = true;
		}

		// situação ligação de esgoto
		if (idSituacaoLigacaoEsgoto != null
				&& !idSituacaoLigacaoEsgoto.equals("")
				&& !idSituacaoLigacaoEsgoto.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString())) {
			peloMenosUmParametroInformado = true;
		}
		// imovel Perfil
		if (idImovelPerfil != null
				&& !idImovelPerfil.equals("")
				&& !idImovelPerfil.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString())) {
			peloMenosUmParametroInformado = true;
		}
		// poço tipo
		if (idPocoTipo != null
				&& !idPocoTipo.equals("")
				&& !idPocoTipo.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString())) {
			peloMenosUmParametroInformado = true;
		}
		// faturamento situacao tipo
		if (tipoSituacaoFaturamentoID != null
				&& !tipoSituacaoFaturamentoID.equals("")
				&& !tipoSituacaoFaturamentoID.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString())) {
			peloMenosUmParametroInformado = true;
		}
		// cobranca situacao tipo
		if (situacaoCobrancaID != null
				&& !situacaoCobrancaID.equals("")
				&& !situacaoCobrancaID.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString())) {
			peloMenosUmParametroInformado = true;
		}
		// Situacao Especial Cobranca
		if (tipoSituacaoEspecialCobrancaID != null
				&& !tipoSituacaoEspecialCobrancaID.equals("")
				&& !tipoSituacaoEspecialCobrancaID.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString())) {
			peloMenosUmParametroInformado = true;
		}

		// Indicador de Medição
		if (indicadorMedicao != null
				&& !indicadorMedicao.equals("")
				&& !indicadorMedicao.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString())) {
			peloMenosUmParametroInformado = true;
		}
		// Tipo de Medição
		if (tipoMedicaoID != null
				&& !tipoMedicaoID.equals("")
				&& !tipoMedicaoID.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString())) {
			peloMenosUmParametroInformado = true;
		}

		// Dia do Vencimento
		if (diaVencimentoAlternativo != null
				&& !diaVencimentoAlternativo.equals("")
				&& !diaVencimentoAlternativo.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString())) {
			peloMenosUmParametroInformado = true;
		}

		// elo anormalidade
		if (idEloAnormalidade != null
				&& !idEloAnormalidade.equals("")
				&& !idEloAnormalidade.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString())) {
			peloMenosUmParametroInformado = true;
		}
		// cadastro ocorrencia
		if (idCadastroOcorrencia != null
				&& !idCadastroOcorrencia.equals("")
				&& !idCadastroOcorrencia.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString())) {
			peloMenosUmParametroInformado = true;
		}
		// area construida inicial e final
		if ((areaConstruidaInicial != null && !areaConstruidaInicial.equals("") && !areaConstruidaInicial
				.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString()))
				&& (areaConstruidaFinal != null
						&& !areaConstruidaFinal.equals("") && !areaConstruidaFinal
						.trim().equalsIgnoreCase(
								new Integer(
										ConstantesSistema.NUMERO_NAO_INFORMADO)
										.toString()))) {
			peloMenosUmParametroInformado = true;
		}
		// consumo tarifa
		if (idConsumoTarifa != null
				&& !idConsumoTarifa.equals("")
				&& !idConsumoTarifa.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString())) {
			peloMenosUmParametroInformado = true;
		}
		// intervalo Media Minima Imovel Inicial e Final
		if ((intervaloMediaMinimaImovelInicial != null
				&& !intervaloMediaMinimaImovelInicial.equals("") && !intervaloMediaMinimaImovelInicial
				.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString()))

				&& (intervaloMediaMinimaImovelFinal != null
						&& !intervaloMediaMinimaImovelFinal.equals("") && !intervaloMediaMinimaImovelFinal
						.trim().equalsIgnoreCase(
								new Integer(
										ConstantesSistema.NUMERO_NAO_INFORMADO)
										.toString()))) {
			peloMenosUmParametroInformado = true;
		}
		// intervalo MediaMinima Hidrometro Inicial e Final
		if ((intervaloMediaMinimaHidrometroInicial != null
				&& !intervaloMediaMinimaHidrometroInicial.equals("") && !intervaloMediaMinimaHidrometroInicial
				.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString()))
				&& (intervaloMediaMinimaHidrometroFinal != null
						&& !intervaloMediaMinimaHidrometroFinal.equals("") && !intervaloMediaMinimaHidrometroFinal
						.trim().equalsIgnoreCase(
								new Integer(
										ConstantesSistema.NUMERO_NAO_INFORMADO)
										.toString()))) {
			if(tipoMedicaoID.equals("-1")){
				throw new ActionServletException("atencao.campo.informado", null,
				"Tipo de Medição");
			}else {
			peloMenosUmParametroInformado = true;
		}}

		// quantidade economias inicial e final
		if ((quantidadeEconomiasInicial != null
				&& !quantidadeEconomiasInicial.equals("") && !quantidadeEconomiasInicial
				.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString()))
				&& (quantidadeEconomiasFinal != null
						&& !quantidadeEconomiasFinal.equals("") && !quantidadeEconomiasFinal
						.trim().equalsIgnoreCase(
								new Integer(
										ConstantesSistema.NUMERO_NAO_INFORMADO)
										.toString()))) {
			peloMenosUmParametroInformado = true;
		}

		// categoria
		if (idCategoria != null
				&& !idCategoria.equals("")
				&& !idCategoria.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString())) {
			peloMenosUmParametroInformado = true;
		}

		// sub categoria
		if (idSubCategoria != null
				&& !idSubCategoria.equals("")
				&& !idSubCategoria.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString())) {
			peloMenosUmParametroInformado = true;
		}

		// numero prontos inicial e final
		if ((numeroPontosInicial != null && !numeroPontosInicial.equals("") && !numeroPontosInicial
				.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString()))
				&& (numeroPontosFinal != null && !numeroPontosFinal.equals("") && !numeroPontosFinal
						.trim().equalsIgnoreCase(
								new Integer(
										ConstantesSistema.NUMERO_NAO_INFORMADO)
										.toString()))) {
			peloMenosUmParametroInformado = true;
		}

		// numero moradores inicial e final
		if ((numeroMoradoresInicial != null
				&& !numeroMoradoresInicial.equals("") && !numeroMoradoresInicial
				.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString()))
				&& (numeroMoradoresFinal != null
						&& !numeroMoradoresFinal.equals("") && !numeroMoradoresFinal
						.trim().equalsIgnoreCase(
								new Integer(
										ConstantesSistema.NUMERO_NAO_INFORMADO)
										.toString()))) {
			peloMenosUmParametroInformado = true;
		}
		// area construida faixa
		if (idAreaConstruidaFaixa != null
				&& !idAreaConstruidaFaixa.equals("")
				&& !idAreaConstruidaFaixa.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString())) {
			peloMenosUmParametroInformado = true;
		}

		// cliente
		if (idCliente != null
				&& !idCliente.equals("")
				&& !idCliente.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString())) {
			peloMenosUmParametroInformado = true;
		}

		// cliente tipo
		if (idClienteTipo != null
				&& !idClienteTipo.equals("")
				&& !idClienteTipo.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString())) {
			peloMenosUmParametroInformado = true;
		}

		// cliente relacao tipo
		if (idClienteRelacaoTipo != null
				&& !idClienteRelacaoTipo.equals("")
				&& !idClienteRelacaoTipo.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString())) {
			peloMenosUmParametroInformado = true;
		}
		
		//Indicador Codigo de Barra
		if (indicadorCodigoBarra != null
				&& !indicadorCodigoBarra.equals("")
				&& !indicadorCodigoBarra.trim().equalsIgnoreCase(
						new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
								.toString())) {
			peloMenosUmParametroInformado = true;
		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		return retorno;
	}
}
