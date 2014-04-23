package gcom.gui.cobranca;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cobranca.CobrancaCriterio;
import gcom.cobranca.CobrancaCriterioLinha;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.CriterioSituacaoCobranca;
import gcom.cobranca.CriterioSituacaoLigacaoAgua;
import gcom.cobranca.CriterioSituacaoLigacaoEsgoto;
import gcom.cobranca.FiltroCobrancaCriterio;
import gcom.cobranca.FiltroCobrancaCriterioLinha;
import gcom.cobranca.FiltroCobrancaDocumento;
import gcom.cobranca.FiltroCobrancaSituacao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Pre- processamento para atualizar o criterio da cobrança
 * 
 * @author Sávio Luiz
 * @date 08/05/2006
 */
public class ExibirAtualizarCriterioCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarCriterioCobranca");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		String reload = httpServletRequest.getParameter("chamarReload");
		
		//caso seja a primeira vez 
		if(httpServletRequest.getParameter("menu") != null){
		 sessao.removeAttribute("voltar");
		}

		// caso venha do adicionar só direciona a pagina
		// caso o reload seja nulo então processa
		if (reload == null || reload.equals("")) {

			CriterioCobrancaActionForm criterioCobrancaActionForm = (CriterioCobrancaActionForm) actionForm;
			// Cria a variável que vai armazenar o criterio cobrança para ser
			// atualizada
			CobrancaCriterio cobrancaCriterio = null;

			if (httpServletRequest.getParameter("limpaSessao") != null) {
				sessao.removeAttribute("colecaoCobrancaCriterioLinha");
			}

			if (sessao.getAttribute("colecaoCobrancaCriterioLinha") == null
					|| sessao.getAttribute("colecaoCobrancaCriterioLinha")
							.equals("")) {

				String idCriterioCobranca = null;

				if (httpServletRequest.getParameter("limpaSessao") == null) {

					if (httpServletRequest
							.getParameter("idRegistroAtualizacao") == null) {
						idCriterioCobranca = (String) sessao
								.getAttribute("idRegistroAtualizacao");
						// Definindo a volta do botão Voltar p Filtrar critério
						// cobrança
						sessao.setAttribute("voltar", "filtrar");
					} else {
						idCriterioCobranca = httpServletRequest
								.getParameter("idRegistroAtualizacao");
						// Definindo a volta do botão Voltar para Manter
						// critério
						// cobrança caso o retornoFiltrar seja igual a nulo
						// Caso o retornoFiltrar seja diferente de nulo (no caso
						// da tela
						// de sucesso de inserir, chamar o atalizar) então o
						// botão voltar
						// retorna para o Filtro critério cobrança
						if (httpServletRequest.getParameter("retornoFiltrar") != null) {
							sessao.setAttribute("voltar", "filtrar");
						} else {
							sessao.setAttribute("voltar", "manter");
						}
						sessao.setAttribute("idRegistroAtualizacao",
								idCriterioCobranca);
					}
				} else {
					idCriterioCobranca = (String) sessao
							.getAttribute("idRegistroAtualizacao");
				}
				FiltroCobrancaCriterio filtroCobrancaCriterio = new FiltroCobrancaCriterio();
				filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
						FiltroCobrancaCriterio.ID, idCriterioCobranca));
				filtroCobrancaCriterio.adicionarCaminhoParaCarregamentoEntidade(
						FiltroCobrancaCriterio.CRITERIOS_SITUACAO_COBRANCA);
				filtroCobrancaCriterio.adicionarCaminhoParaCarregamentoEntidade(
						FiltroCobrancaCriterio.CRITERIOS_SITUACAO_LIGACAO_AGUA);
				filtroCobrancaCriterio.adicionarCaminhoParaCarregamentoEntidade(
						FiltroCobrancaCriterio.CRITERIOS_SITUACAO_LIGACAO_ESGOTO);
				
				Collection<CobrancaCriterio> collectionCobrancaCriterio = fachada
						.pesquisar(filtroCobrancaCriterio,
								CobrancaCriterio.class.getName());

				// Caso a pesquisa tenha retornado o critério de cobrança
				if (collectionCobrancaCriterio != null
						&& !collectionCobrancaCriterio.isEmpty()) {

					// Recupera da coleção a rota que vai ser atualizada
					cobrancaCriterio = (CobrancaCriterio) Util
							.retonarObjetoDeColecao(collectionCobrancaCriterio);

					// verifica se existe a cobranca critério documento para a
					// cobranca critério escolhida
					FiltroCobrancaDocumento filtroCobrancaDocumento = new FiltroCobrancaDocumento();
					filtroCobrancaDocumento
							.adicionarParametro(new ParametroSimples(
									FiltroCobrancaDocumento.ID_COBRANCA_CRITERIO,
									cobrancaCriterio.getId()));
					Integer qtdCobrancaDocumento = fachada
							.totalRegistrosPesquisa(filtroCobrancaDocumento,
									CobrancaDocumento.class.getName());
					// caso exista alguma cobranca documento então desabilita
					// alguns
					// campos
					if (qtdCobrancaDocumento != null &&
							qtdCobrancaDocumento != 0) {
						sessao.setAttribute("desabilita", "1");
					}

					// Seta no form os dados do critério cobrança
					criterioCobrancaActionForm.setDescricaoCriterio(""
							+ cobrancaCriterio.getDescricaoCobrancaCriterio());
					criterioCobrancaActionForm.setDataInicioVigencia(Util
							.formatarData(cobrancaCriterio
									.getDataInicioVigencia()));
					criterioCobrancaActionForm.setNumeroAnoContaAntiga(""
							+ cobrancaCriterio.getNumeroContaAntiga());
					criterioCobrancaActionForm.setOpcaoAcaoImovelSitEspecial(""
							+ cobrancaCriterio
									.getIndicadorEmissaoImovelParalisacao());
					criterioCobrancaActionForm
							.setOpcaoAcaoImovelSit(""
									+ cobrancaCriterio
											.getIndicadorEmissaoImovelSituacaoCobranca());
					criterioCobrancaActionForm.setOpcaoContasRevisao(""
							+ cobrancaCriterio
									.getIndicadorEmissaoContaRevisao());
					criterioCobrancaActionForm
							.setOpcaoAcaoImovelDebitoMesConta(""
									+ cobrancaCriterio
											.getIndicadorEmissaoDebitoContaMes());
					criterioCobrancaActionForm
							.setOpcaoAcaoInquilinoDebitoMesConta(""
									+ cobrancaCriterio
											.getIndicadorEmissaoInquilinoDebitoContaMes());
					criterioCobrancaActionForm
							.setOpcaoAcaoImovelDebitoContasAntigas(""
									+ cobrancaCriterio
											.getIndicadorEmissaoDebitoContaAntiga());
					criterioCobrancaActionForm.setIndicadorUso(""
							+ cobrancaCriterio.getIndicadorUso());
					
					criterioCobrancaActionForm
							.setPercentualValorMinimoPagoParceladoCancelado(
									Util.formatarMoedaReal(
									cobrancaCriterio.getPercentualValorMinimoPagoParceladoCancelado()));
					
					
					criterioCobrancaActionForm
					.setPercentualQuantidadeMinimoPagoParceladoCancelado(
							Util.formatarMoedaReal(
							cobrancaCriterio.getPercentualQuantidadeMinimoPagoParceladoCancelado()));

					
					criterioCobrancaActionForm
					.setValorLimitePrioridade(
							Util.formatarMoedaReal(
							cobrancaCriterio.getValorLimitePrioridade()));

					
					

					// recupera a coleção de cobrança critério linha
					FiltroCobrancaCriterioLinha filtroCobrancaCriterioLinha = new FiltroCobrancaCriterioLinha();
					filtroCobrancaCriterioLinha
							.adicionarParametro(new ParametroSimples(
									FiltroCobrancaCriterioLinha.COBRANCA_CRITERIO_ID,
									cobrancaCriterio.getId()));
					filtroCobrancaCriterioLinha
							.adicionarCaminhoParaCarregamentoEntidade("imovelPerfil");
					filtroCobrancaCriterioLinha
							.adicionarCaminhoParaCarregamentoEntidade("categoria");

					Collection colecaoCobrancaCriterioLinha = fachada
							.pesquisar(filtroCobrancaCriterioLinha,
									CobrancaCriterioLinha.class.getName());
					if (colecaoCobrancaCriterioLinha != null
							&& !colecaoCobrancaCriterioLinha.isEmpty()) {
						sessao.setAttribute("colecaoCobrancaCriterioLinha",
								colecaoCobrancaCriterioLinha);
					}
					
					// consultar as situacoes de cobranca
			        FiltroCobrancaSituacao filtroCobrancaSituacao = new FiltroCobrancaSituacao();
			        
			        filtroCobrancaSituacao.adicionarParametro(new ParametroSimples(FiltroCobrancaSituacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			        filtroCobrancaSituacao.setCampoOrderBy(FiltroCobrancaSituacao.DESCRICAO);
			        
			        Collection colecaoCobrancaSituacao = fachada.pesquisar(filtroCobrancaSituacao, CobrancaSituacao.class.getName());
			        
			        sessao.setAttribute("colecaoCobrancaSituacao", colecaoCobrancaSituacao);
			        
			        // preencher situacoes de cobranca setados			        
			        if (cobrancaCriterio.getCriteriosSituacaoCobranca() != null &&
			        		!cobrancaCriterio.getCriteriosSituacaoCobranca().isEmpty()){
			        	String[] idsSituacaoCobranca = new String[cobrancaCriterio.getCriteriosSituacaoCobranca().size()];
			        	int i = 0;
				        for (Iterator iter = cobrancaCriterio.getCriteriosSituacaoCobranca().iterator(); 
				        		iter.hasNext();) {
				        	CriterioSituacaoCobranca critSitCob = (CriterioSituacaoCobranca) iter.next();
				        	idsSituacaoCobranca[i++] = critSitCob.getComp_id().getCobrancaSituacao().getId() + "";					
				        }
				        criterioCobrancaActionForm.setIdsCobrancaSituacao(idsSituacaoCobranca);
			        }

			        // consultar as situacoes de ligacao de agua
			        FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
			        
			        filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			        filtroLigacaoAguaSituacao.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);
			        
			        Collection colecaoLigacaoAguaSituacao = fachada.pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
			        
			        sessao.setAttribute("colecaoSituacaoLigacaoAgua", colecaoLigacaoAguaSituacao);

			        // preencher situacoes de ligacao agua setados
			        if (cobrancaCriterio.getCriteriosSituacaoLigacaoAgua() != null &&
			        		!cobrancaCriterio.getCriteriosSituacaoLigacaoAgua().isEmpty()){
			        	String[] idsSituacaoLigacaoAgua = new String[cobrancaCriterio
			        	    .getCriteriosSituacaoLigacaoAgua().size()];
			        	int i = 0;
				        for (Iterator iter = cobrancaCriterio.getCriteriosSituacaoLigacaoAgua().iterator(); 
				        		iter.hasNext();) {
				        	CriterioSituacaoLigacaoAgua critSitLigAgua = (CriterioSituacaoLigacaoAgua) iter.next();
				        	idsSituacaoLigacaoAgua[i++] = critSitLigAgua.getComp_id().getLigacaoAguaSituacao().getId() + "";					
				        }
			        	criterioCobrancaActionForm.setIdsSituacaoLigacaoAgua(idsSituacaoLigacaoAgua);
			        }
			        
			        // consultar as situacoes de ligacao de agua
			        FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
			        
			        filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoSituacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			        filtroLigacaoEsgotoSituacao.setCampoOrderBy(FiltroLigacaoEsgotoSituacao.DESCRICAO);
			        
			        Collection colecaoLigacaoEsgotoSituacao = fachada.pesquisar(filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());
			        
			        sessao.setAttribute("colecaoSituacaoLigacaoEsgoto", colecaoLigacaoEsgotoSituacao);
			        
			        // preencher situacoes de ligacao agua setados
			        if (cobrancaCriterio.getCriteriosSituacaoLigacaoEsgoto() != null &&
			        		!cobrancaCriterio.getCriteriosSituacaoLigacaoEsgoto().isEmpty()){
			        	String[] idsSituacaoLigacaoEsgoto = new String[cobrancaCriterio
			        	    .getCriteriosSituacaoLigacaoEsgoto().size()];
			        	int i = 0;
				        for (Iterator iter = cobrancaCriterio.getCriteriosSituacaoLigacaoEsgoto().iterator(); 
				        		iter.hasNext();) {
				        	CriterioSituacaoLigacaoEsgoto critSitLigEsgoto = (CriterioSituacaoLigacaoEsgoto) iter.next();
				        	idsSituacaoLigacaoEsgoto[i++] = critSitLigEsgoto.getComp_id().getLigacaoEsgotoSituacao().getId() + "";					
				        }
			        	criterioCobrancaActionForm.setIdsSituacaoLigacaoEsgoto(idsSituacaoLigacaoEsgoto);
			        }
			        
				}
			}
			sessao.setAttribute("cobrancaCriterio", cobrancaCriterio);
		}

		return retorno;
	}
}
