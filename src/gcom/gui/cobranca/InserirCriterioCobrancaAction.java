package gcom.gui.cobranca;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cobranca.CobrancaCriterio;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.CriterioSituacaoCobranca;
import gcom.cobranca.CriterioSituacaoCobrancaPK;
import gcom.cobranca.CriterioSituacaoLigacaoAgua;
import gcom.cobranca.CriterioSituacaoLigacaoAguaPK;
import gcom.cobranca.CriterioSituacaoLigacaoEsgoto;
import gcom.cobranca.CriterioSituacaoLigacaoEsgotoPK;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processamento para inserir o critério da cobrança e as linhas do criterio da
 * cobrança
 * 
 * @author Sávio Luiz
 * @date 03/05/2006
 */
public class InserirCriterioCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		CriterioCobrancaActionForm criterioCobrancaActionForm = (CriterioCobrancaActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		Integer idCobrancaCriterio = null;

		// cria o objeto criterio cobrança para ser inserido
		CobrancaCriterio cobrancaCriterio = new CobrancaCriterio();

		if (criterioCobrancaActionForm.getDescricaoCriterio() != null
				&& !criterioCobrancaActionForm.getDescricaoCriterio()
						.equals("")) {
			cobrancaCriterio
					.setDescricaoCobrancaCriterio(criterioCobrancaActionForm
							.getDescricaoCriterio());
		} else {
			throw new ActionServletException("atencao.required", null,
					"Descrição do Critério de Cobrança");
		}
		Date dataInicio = null;
		if (criterioCobrancaActionForm.getDataInicioVigencia() != null
				&& !criterioCobrancaActionForm.getDataInicioVigencia().equals(
						"")) {
			String dataInicioVigencia = criterioCobrancaActionForm
					.getDataInicioVigencia();
			if (Util.validarDiaMesAno(dataInicioVigencia)) {
				throw new ActionServletException(
						"atencao.data.inicio.Vigencia.invalida");
			}

			dataInicio = Util.converteStringParaDate(dataInicioVigencia);
			Date dataAtualSemHora = Util.formatarDataSemHora(new Date());
			if (dataInicio.before(dataAtualSemHora)) {
				String dataAtual = Util.formatarData(new Date());
				throw new ActionServletException(
						"atencao.data.inicio.nao.superior.data.corrente", null,
						dataAtual);
			}
		} else {
			throw new ActionServletException("atencao.required", null,
					"Data de Início de Vigência do Critério");
		}
		cobrancaCriterio.setDataInicioVigencia(dataInicio);
		if (criterioCobrancaActionForm.getNumeroAnoContaAntiga() != null
				&& !criterioCobrancaActionForm.getNumeroAnoContaAntiga()
						.equals("")) {
			boolean valorNaoNumerico = Util
					.validarValorNaoNumerico(criterioCobrancaActionForm
							.getNumeroAnoContaAntiga());
			if (!valorNaoNumerico) {
				cobrancaCriterio.setNumeroContaAntiga(new Short(
						criterioCobrancaActionForm.getNumeroAnoContaAntiga()));
			} else {
				throw new ActionServletException("atencao.integer", null,
						"Número de Anos para Determinar Conta Antiga");
			}
		} else {
			throw new ActionServletException("atencao.required", null,
					"Número de Anos para Determinar Conta Antiga");
		}

		if (criterioCobrancaActionForm.getValorLimitePrioridade() != null
				&& !criterioCobrancaActionForm.getValorLimitePrioridade()
						.equals("")) {
			BigDecimal valorLimitePrioridade = new BigDecimal(
					criterioCobrancaActionForm.getValorLimitePrioridade().replace(".", "")
							.replace(",", "."));
			cobrancaCriterio.setValorLimitePrioridade(valorLimitePrioridade);
		} else {
			throw new ActionServletException("atencao.required", null,
					"Valor Limite para Prioridade");
		}

		if (criterioCobrancaActionForm
				.getPercentualValorMinimoPagoParceladoCancelado() != null
				&& !criterioCobrancaActionForm
						.getPercentualValorMinimoPagoParceladoCancelado()
						.equals("")) {
			BigDecimal percentualValorMinimoPagoParceladoCancelado = new BigDecimal(
					criterioCobrancaActionForm
							.getPercentualValorMinimoPagoParceladoCancelado()
							.replace(".", "").replace(",", "."));
			cobrancaCriterio
					.setPercentualValorMinimoPagoParceladoCancelado(percentualValorMinimoPagoParceladoCancelado);
		} else {
			throw new ActionServletException("atencao.required", null,
					"Percentual Valor");
		}

		if (criterioCobrancaActionForm
				.getPercentualQuantidadeMinimoPagoParceladoCancelado() != null
				&& !criterioCobrancaActionForm
						.getPercentualQuantidadeMinimoPagoParceladoCancelado()
						.equals("")) {
			BigDecimal percentualQuantidadeMinimoPagoParceladoCancelado = new BigDecimal(
					criterioCobrancaActionForm
							.getPercentualQuantidadeMinimoPagoParceladoCancelado().replace(".", "")
							.replace(",", "."));
			cobrancaCriterio
					.setPercentualQuantidadeMinimoPagoParceladoCancelado(percentualQuantidadeMinimoPagoParceladoCancelado);
		} else {
			throw new ActionServletException("atencao.required", null,
					"Percentual Quantidade de Itens");
		}

		if (criterioCobrancaActionForm.getOpcaoAcaoImovelSitEspecial() != null
				&& !criterioCobrancaActionForm.getOpcaoAcaoImovelSitEspecial()
						.equals("")) {
			cobrancaCriterio
					.setIndicadorEmissaoImovelParalisacao(new Short(
							criterioCobrancaActionForm
									.getOpcaoAcaoImovelSitEspecial()));
		} else {
			throw new ActionServletException("atencao.required", null,
					"Emissão da Ação para Imóvel com Sit. Especial de Cobrança");
		}
		if (criterioCobrancaActionForm.getOpcaoAcaoImovelSit() != null
				&& !criterioCobrancaActionForm.getOpcaoAcaoImovelSit().equals(
						"")) {
			cobrancaCriterio
					.setIndicadorEmissaoImovelSituacaoCobranca(new Short(
							criterioCobrancaActionForm.getOpcaoAcaoImovelSit()));
		} else {
			throw new ActionServletException("atencao.required", null,
					"Emissão da Ação para Imóvel com Sit. de Cobrança");
		}
		if (criterioCobrancaActionForm.getOpcaoContasRevisao() != null
				&& !criterioCobrancaActionForm.getOpcaoContasRevisao().equals(
						"")) {
			cobrancaCriterio
					.setIndicadorEmissaoContaRevisao(new Short(
							criterioCobrancaActionForm
									.getOpcaoAcaoImovelSitEspecial()));
		} else {
			throw new ActionServletException("atencao.required", null,
					"Considerar Contas em Revisão");
		}
		if (criterioCobrancaActionForm.getOpcaoAcaoImovelDebitoMesConta() != null
				&& !criterioCobrancaActionForm
						.getOpcaoAcaoImovelDebitoMesConta().equals("")) {
			cobrancaCriterio.setIndicadorEmissaoDebitoContaMes(new Short(
					criterioCobrancaActionForm
							.getOpcaoAcaoImovelDebitoMesConta()));
		} else {
			throw new ActionServletException("atencao.required", null,
					"Emissão da Ação para Imóvel com Débito só da Conta do Mês");
		}
		if (criterioCobrancaActionForm.getOpcaoAcaoInquilinoDebitoMesConta() != null
				&& !criterioCobrancaActionForm
						.getOpcaoAcaoInquilinoDebitoMesConta().equals("")) {
			cobrancaCriterio
					.setIndicadorEmissaoInquilinoDebitoContaMes(new Short(
							criterioCobrancaActionForm
									.getOpcaoAcaoInquilinoDebitoMesConta()));
		} else {
			throw new ActionServletException(
					"atencao.required",
					null,
					"Emissão da Ação para Inquilino Com Débito só da Conta do Mês Independentemente do Valor da Conta");
		}
		if (criterioCobrancaActionForm.getOpcaoAcaoImovelDebitoContasAntigas() != null
				&& !criterioCobrancaActionForm
						.getOpcaoAcaoImovelDebitoContasAntigas().equals("")) {
			cobrancaCriterio.setIndicadorEmissaoDebitoContaAntiga(new Short(
					criterioCobrancaActionForm
							.getOpcaoAcaoImovelDebitoContasAntigas()));
		} else {
			throw new ActionServletException("atencao.required", null,
					"Emissão da Ação para Imóvel com Débito só de Contas Antigas");
		}
				
		// Verificando se houve situacoes de cobranca escolhidas para o criterio
		if (criterioCobrancaActionForm.getIdsCobrancaSituacao() != null && 
				criterioCobrancaActionForm.getIdsCobrancaSituacao().length > 0){
			
			Collection criteriosSituacaoCobranca = new ArrayList();
			
			for (int i = 0; i < criterioCobrancaActionForm.getIdsCobrancaSituacao().length; i++) {
				CriterioSituacaoCobranca csc = new CriterioSituacaoCobranca();
				CriterioSituacaoCobrancaPK cscPK = new CriterioSituacaoCobrancaPK();
				CobrancaSituacao cobSit = new CobrancaSituacao();
				cobSit.setId(new Integer(criterioCobrancaActionForm.getIdsCobrancaSituacao()[i]));
				cscPK.setCobrancaSituacao(cobSit);
				csc.setComp_id(cscPK);
				criteriosSituacaoCobranca.add(csc);				
			}
			cobrancaCriterio.setCriteriosSituacaoCobranca(new HashSet(criteriosSituacaoCobranca));
		}
		
		// verificando se houveram situacoes de ligacao de agua para este criterio
		if (criterioCobrancaActionForm.getIdsSituacaoLigacaoAgua() != null && 
				criterioCobrancaActionForm.getIdsSituacaoLigacaoAgua().length > 0){
			
			Collection criteriosSituacaoLigacaoAgua = new ArrayList();
			
			for (int i = 0; i < criterioCobrancaActionForm.getIdsSituacaoLigacaoAgua().length; i++) {
				CriterioSituacaoLigacaoAgua csla = new CriterioSituacaoLigacaoAgua();
				CriterioSituacaoLigacaoAguaPK cslaPK = new CriterioSituacaoLigacaoAguaPK();
				LigacaoAguaSituacao ligAguaSit = new LigacaoAguaSituacao();
				ligAguaSit.setId(new Integer(criterioCobrancaActionForm.getIdsSituacaoLigacaoAgua()[i]));
				cslaPK.setLigacaoAguaSituacao(ligAguaSit);
				csla.setComp_id(cslaPK);
				criteriosSituacaoLigacaoAgua.add(csla);				
			}
			cobrancaCriterio.setCriteriosSituacaoLigacaoAgua(new HashSet(criteriosSituacaoLigacaoAgua));
		}

//		 verificando se houveram situacoes de ligacao de esgoto para este criterio
		if (criterioCobrancaActionForm.getIdsSituacaoLigacaoEsgoto() != null && 
				criterioCobrancaActionForm.getIdsSituacaoLigacaoEsgoto().length > 0){
			
			Collection criteriosSituacaoLigacaoEsgoto = new ArrayList();
			
			for (int i = 0; i < criterioCobrancaActionForm.getIdsSituacaoLigacaoEsgoto().length; i++) {
				CriterioSituacaoLigacaoEsgoto csle = new CriterioSituacaoLigacaoEsgoto();
				CriterioSituacaoLigacaoEsgotoPK cslePK = new CriterioSituacaoLigacaoEsgotoPK();
				LigacaoEsgotoSituacao ligEsgotoSit = new LigacaoEsgotoSituacao();
				ligEsgotoSit.setId(new Integer(criterioCobrancaActionForm.getIdsSituacaoLigacaoEsgoto()[i]));
				cslePK.setLigacaoEsgotoSituacao(ligEsgotoSit);
				csle.setComp_id(cslePK);
				criteriosSituacaoLigacaoEsgoto.add(csle);				
			}
			cobrancaCriterio.setCriteriosSituacaoLigacaoEsgoto(new HashSet(criteriosSituacaoLigacaoEsgoto));
		}
		
		cobrancaCriterio.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
		cobrancaCriterio.setUltimaAlteracao(new Date());
		Collection colecaoCobrancaCriterioLinha = (Collection) sessao
				.getAttribute("colecaoCobrancaCriterioLinha");

		if (colecaoCobrancaCriterioLinha != null
				&& !colecaoCobrancaCriterioLinha.isEmpty()) {
			cobrancaCriterio.setCobrancaCriterioLinhas(new HashSet(
					colecaoCobrancaCriterioLinha));

			idCobrancaCriterio = fachada
					.inserirCobrancaCriterio(cobrancaCriterio,this.getUsuarioLogado(httpServletRequest));
		} else {
			throw new ActionServletException(
					"atencao.informar.linha.criterio.cobranca");
		}
		sessao.removeAttribute("colecaoCobrancaCriterioLinha");

		montarPaginaSucesso(httpServletRequest, "Critério de Cobrança "
				+ idCobrancaCriterio + " inserido com sucesso.",
				"Inserir outro Critério de Cobrança",
				"exibirInserirCriterioCobrancaAction.do?menu=sim",
				"exibirAtualizarCriterioCobrancaAction.do?idRegistroAtualizacao="
						+ idCobrancaCriterio + "&retornoFiltrar=1",
				"Atualizar Critério de Cobrança inserido");
		return retorno;
	}

}
