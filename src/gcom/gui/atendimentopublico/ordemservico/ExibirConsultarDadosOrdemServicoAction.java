package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroOrdemServicoPavimento;
import gcom.atendimentopublico.ordemservico.FiltroOrdemServicoUnidade;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoPavimento;
import gcom.atendimentopublico.ordemservico.OrdemServicoProgramacao;
import gcom.atendimentopublico.ordemservico.OrdemServicoUnidade;
import gcom.atendimentopublico.ordemservico.bean.OSFiltroHelper;
import gcom.atendimentopublico.ordemservico.bean.ObterDadosAtividadeIdOSHelper;
import gcom.atendimentopublico.ordemservico.bean.ObterDadosAtividadesOSHelper;
import gcom.atendimentopublico.ordemservico.bean.ObterDescricaoSituacaoOSHelper;
import gcom.atendimentopublico.ordemservico.bean.PesquisarOrdemServicoHelper;
import gcom.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo;
import gcom.atendimentopublico.registroatendimento.bean.ObterDescricaoSituacaoRAHelper;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.projeto.FiltroProjeto;
import gcom.cadastro.projeto.Projeto;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de exibir consultar OS.
 * 
 * @author lms
 * @created 04/09/2006
 */
public class ExibirConsultarDadosOrdemServicoAction extends GcomAction {

	/**
	 * Execute do Consultar OS.
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return forward
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("consultarDadosOrdemServico");

		// Sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		ConsultarDadosOrdemServicoActionForm consultarDadosOrdemServicoActionForm = (ConsultarDadosOrdemServicoActionForm) actionForm;
		
		if ( sessao.getAttribute( "manterOs" ) != null && !sessao.getAttribute( "manterOs" ).equals( "" ) ){
			httpServletRequest.setAttribute("caminhoRetornoOS", "filtrarOrdemServicoAction.do?voltar=S");
		} else {
			httpServletRequest.setAttribute("caminhoRetornoOS", "exibirFiltrarOrdemServicoAction.do");
		}

		OrdemServico ordemServico = null;
		Integer idOrdemServico = null;
		
		if (httpServletRequest.getAttribute("numeroOS") != null) {
			idOrdemServico = (Integer) httpServletRequest.getAttribute("numeroOS");
		} else if (httpServletRequest.getParameter("numeroOSParametro") != null && 
				   !httpServletRequest.getParameter("numeroOSParametro").equals("")) {
			idOrdemServico = new Integer(httpServletRequest.getParameter("numeroOSParametro"));
		} else {
			idOrdemServico = new Integer(httpServletRequest.getParameter("numeroOS"));
		}
		
		if (sessao.getAttribute("colecaoOSHelper") != null) {
			PesquisarOrdemServicoHelper filtro = (PesquisarOrdemServicoHelper) sessao.getAttribute("pesquisarOrdemServicoHelper");
			List<OSFiltroHelper> colecao = (List<OSFiltroHelper>) sessao.getAttribute("colecaoOSHelper");
			
			Integer totalRegistros = (Integer) sessao.getAttribute("totalRegistros");
			Integer numeroPaginasPesquisa = (int) Math.ceil((double) totalRegistros / 10.0);
			Integer page = (Integer) sessao.getAttribute("page.offset");
			
			boolean anterior = (httpServletRequest.getParameter("osAnterior") != null) ? true : false;
			boolean proximo = (httpServletRequest.getParameter("proximoOS") != null) ? true : false;
			
			int index = obterIndexOSColecao(idOrdemServico, colecao);
			if (index != -1) {
				
				if (anterior) index--;
				if (proximo) index++;
				
				boolean mudaPagina = false;
				if (index > 9 && page < numeroPaginasPesquisa) {
					index = 0;
					page = page +1;
					mudaPagina = true;
				}
				else if (index < 0 && page > 1) {
					index = 9;
					page = page -1;
					mudaPagina = true;
				}
				if (mudaPagina) {
					filtro.setNumeroPaginasPesquisa(page -1);
					sessao.setAttribute("page.offset", page);
					
					Collection<OrdemServico> colecaoOS = fachada.pesquisarOrdemServico(filtro);
					if (colecaoOS != null && colecaoOS.size() > 0) {
						colecao = (List<OSFiltroHelper>) loadColecaoOSHelper(colecaoOS);
						sessao.setAttribute("colecaoOSHelper", colecao);
						idOrdemServico = colecao.get(index).getOrdemServico().getId();
					} else {
						httpServletRequest.setAttribute("desabilitaBotaoProximo", "true");
					}
				}
				
				if (page == 1 && index == 0) {
					httpServletRequest.setAttribute("desabilitaBotaoAnterior", "true");
				}
				if ((page >= numeroPaginasPesquisa) && index >= colecao.size() -1) {
					httpServletRequest.setAttribute("desabilitaBotaoProximo", "true");
				}
				
				if (index >= 0 && index <= 9) {
					if (index > colecao.size() -1) {
						httpServletRequest.setAttribute("desabilitaBotaoProximo", "true");
					}
					idOrdemServico = colecao.get(index).getOrdemServico().getId();
					
				}
				
			} else {
				httpServletRequest.setAttribute("desabilitaBotaoAnterior", "true");
				httpServletRequest.setAttribute("desabilitaBotaoProximo", "true");
			}
		} else {
			httpServletRequest.setAttribute("naoHabilitarNavegacao", "OK");
		}
		
		ordemServico = pesquisarOrdemServico(idOrdemServico);

		consultarDadosOrdemServicoActionForm.reset(actionMapping,
				httpServletRequest);

		// Dados Gerais da OS
		consultarDadosOrdemServicoActionForm.setNumeroOS(ordemServico.getId()
				+ "");
		consultarDadosOrdemServicoActionForm.setNumeroOSPesquisada(ordemServico
				.getId()
				+ "");
		consultarDadosOrdemServicoActionForm.setSituacaoOSId(ordemServico
				.getSituacao()
				+ "");
		// Pesquisar dados da programação
		OrdemServicoProgramacao ordemServicoProgramacao = fachada
				.pesquisarDataEquipeOSProgramacao(ordemServico.getId());
		if (ordemServicoProgramacao != null
				&& !ordemServicoProgramacao.equals("")) {
			if (ordemServicoProgramacao.getProgramacaoRoteiro()
					.getDataRoteiro() != null) {
				consultarDadosOrdemServicoActionForm.setDataProgramacao(Util
						.formatarData(ordemServicoProgramacao
								.getProgramacaoRoteiro().getDataRoteiro()));
			} else {
				consultarDadosOrdemServicoActionForm.setDataProgramacao("");
			}
			if (ordemServicoProgramacao.getEquipe().getNome() != null) {
				consultarDadosOrdemServicoActionForm
						.setEquipeProgramacao(ordemServicoProgramacao
								.getEquipe().getNome());
			} else {
				consultarDadosOrdemServicoActionForm.setEquipeProgramacao("");
			}
		} else {
			consultarDadosOrdemServicoActionForm.setDataProgramacao("");
			consultarDadosOrdemServicoActionForm.setEquipeProgramacao("");
		}

		// Pesquisar dados local de ocorrência
		if (ordemServico.getRegistroAtendimento() != null) {
			String enderecoOcorrencia = fachada
					.obterEnderecoOcorrenciaRA(ordemServico
							.getRegistroAtendimento().getId());
			consultarDadosOrdemServicoActionForm
					.setEnderecoOcorrencia(enderecoOcorrencia);
		} else if (ordemServico.getCobrancaDocumento() != null) {
			if (ordemServico.getCobrancaDocumento().getImovel() != null) {
				String enderecoOcorrencia = fachada
						.pesquisarEndereco(ordemServico.getCobrancaDocumento()
								.getImovel().getId());
				consultarDadosOrdemServicoActionForm
						.setEnderecoOcorrencia(enderecoOcorrencia);
			}
		} else {
			consultarDadosOrdemServicoActionForm.setEnderecoOcorrencia("");
		}
		Imovel imovel = ordemServico.getImovel();
		if (imovel != null) {

			consultarDadosOrdemServicoActionForm.setMatriculaImovel(""
					+ imovel.getId());
			consultarDadosOrdemServicoActionForm.setInscricaoImovel(imovel
					.getInscricaoFormatada());
			consultarDadosOrdemServicoActionForm.setRota(ordemServico
					.getImovel().getQuadra().getRota().getCodigo().toString());

			if (ordemServico.getImovel().getNumeroSequencialRota() != null) {
				consultarDadosOrdemServicoActionForm
						.setSequencialRota(ordemServico.getImovel()
								.getNumeroSequencialRota().toString());
			} else {
				consultarDadosOrdemServicoActionForm.setSequencialRota("");
			}
		} else {
			consultarDadosOrdemServicoActionForm.setMatriculaImovel("");
			consultarDadosOrdemServicoActionForm.setInscricaoImovel("");
			consultarDadosOrdemServicoActionForm.setRota("");
			consultarDadosOrdemServicoActionForm.setSequencialRota("");
		}

		// Caso de Uso [UC0454]
		ObterDescricaoSituacaoOSHelper situacaoOS = fachada
				.obterDescricaoSituacaoOS(ordemServico.getId());
		consultarDadosOrdemServicoActionForm.setSituacaoOS(situacaoOS
				.getDescricaoSituacao());

		if (ordemServico.getRegistroAtendimento() != null) {
			consultarDadosOrdemServicoActionForm.setNumeroRA(ordemServico
					.getRegistroAtendimento().getId()
					+ "");

			// Caso de Uso [UC0420]
			ObterDescricaoSituacaoRAHelper situacaoRA = fachada
					.obterDescricaoSituacaoRA(ordemServico
							.getRegistroAtendimento().getId());
			consultarDadosOrdemServicoActionForm.setSituacaoRA(situacaoRA
					.getDescricaoSituacao());
		}

		if (ordemServico.getCobrancaDocumento() != null) {
			consultarDadosOrdemServicoActionForm
					.setNumeroDocumentoCobranca(ordemServico
							.getCobrancaDocumento().getId()
							+ "");
		}

		consultarDadosOrdemServicoActionForm.setDataGeracao(Util
				.formatarData(ordemServico.getDataGeracao()));
		consultarDadosOrdemServicoActionForm.setTipoServicoId(ordemServico
				.getServicoTipo().getId()
				+ "");
		consultarDadosOrdemServicoActionForm
				.setTipoServicoDescricao(ordemServico.getServicoTipo()
						.getDescricao());

		if (ordemServico.getOsReferencia() != null) {
			consultarDadosOrdemServicoActionForm
					.setNumeroOSReferencia(ordemServico.getOsReferencia()
							.getId()
							+ "");
		} else {
			consultarDadosOrdemServicoActionForm.setNumeroOSReferencia(null);
		}

		if (ordemServico.getServicoTipoReferencia() != null) {
			consultarDadosOrdemServicoActionForm
					.setTipoServicoReferenciaId(ordemServico
							.getServicoTipoReferencia().getId()
							+ "");
			consultarDadosOrdemServicoActionForm
					.setTipoServicoReferenciaDescricao(ordemServico
							.getServicoTipoReferencia().getDescricao());
		} else {
			consultarDadosOrdemServicoActionForm
					.setTipoServicoReferenciaId(null);
		}

		if (ordemServico.getOsReferidaRetornoTipo() != null) {
			consultarDadosOrdemServicoActionForm
					.setRetornoOSReferida(ordemServico
							.getOsReferidaRetornoTipo().getDescricao());
		} else {
			consultarDadosOrdemServicoActionForm.setRetornoOSReferida(null);
		}

		consultarDadosOrdemServicoActionForm.setObservacao(ordemServico
				.getObservacao());

		String valorServicoOriginal = "";
		if (ordemServico.getValorOriginal() != null) {
			valorServicoOriginal = ordemServico.getValorOriginal() + "";
			consultarDadosOrdemServicoActionForm
					.setValorServicoOriginal(valorServicoOriginal.replace(".",
							","));
		} else {
			consultarDadosOrdemServicoActionForm.setValorServicoOriginal("");
		}

		String valorServicoAtual = "";
		if (ordemServico.getValorAtual() != null) {
			valorServicoAtual = ordemServico.getValorAtual() + "";
			consultarDadosOrdemServicoActionForm
					.setValorServicoAtual(valorServicoAtual.replace(".", ","));
		} else {
			consultarDadosOrdemServicoActionForm.setValorServicoAtual("");
		}

		consultarDadosOrdemServicoActionForm.setPrioridadeOriginal(ordemServico
				.getServicoTipoPrioridadeOriginal().getDescricao());
		consultarDadosOrdemServicoActionForm.setPrioridadeAtual(ordemServico
				.getServicoTipoPrioridadeAtual().getDescricao());

		OrdemServicoUnidade ordemServicoUnidade = consultarOrdemServicoUnidade(
				ordemServico.getId(), AtendimentoRelacaoTipo.ABRIR_REGISTRAR);

		if (ordemServicoUnidade != null) {
			consultarDadosOrdemServicoActionForm
					.setUnidadeGeracaoId(ordemServicoUnidade
							.getUnidadeOrganizacional().getId()
							+ "");
			consultarDadosOrdemServicoActionForm
					.setUnidadeGeracaoDescricao(ordemServicoUnidade
							.getUnidadeOrganizacional().getDescricao());
			consultarDadosOrdemServicoActionForm
					.setUsuarioGeracaoId(ordemServicoUnidade.getUsuario()
							.getId()
							+ "");
			consultarDadosOrdemServicoActionForm
					.setUsuarioGeracaoNome(ordemServicoUnidade.getUsuario()
							.getNomeUsuario());
		} else {
			consultarDadosOrdemServicoActionForm.setUnidadeGeracaoId("");
			consultarDadosOrdemServicoActionForm.setUnidadeGeracaoDescricao("");
			consultarDadosOrdemServicoActionForm.setUsuarioGeracaoId("");
			consultarDadosOrdemServicoActionForm.setUsuarioGeracaoNome("");
		}

		if (ordemServico.getDataEmissao() != null) {
			consultarDadosOrdemServicoActionForm.setDataUltimaEmissao(Util
					.formatarData(ordemServico.getDataEmissao()));
		} else {
			consultarDadosOrdemServicoActionForm.setDataUltimaEmissao("");
		}

		// Dados de Execução de OS
		if (new Short(ordemServico.getSituacao()).intValue() == OrdemServico.SITUACAO_ENCERRADO
				.intValue()) {
			consultarDadosOrdemServicoActionForm.setDataEncerramento(Util
					.formatarDataComHora(ordemServico.getDataEncerramento()));
			if (ordemServico.getDescricaoParecerEncerramento() != null
					&& !ordemServico.equals("")) {
				consultarDadosOrdemServicoActionForm
						.setParecerEncerramento(ordemServico
								.getDescricaoParecerEncerramento());
			}
			if (ordemServico.getAreaPavimento() != null) {
				String areaPavimentacao = ordemServico.getAreaPavimento() + "";
				consultarDadosOrdemServicoActionForm
						.setAreaPavimentacao(areaPavimentacao.replace(".", ","));
			} else {
				consultarDadosOrdemServicoActionForm.setAreaPavimentacao("");
			}
			if (new Short(ordemServico.getIndicadorComercialAtualizado())
					.intValue() == 1) {
				consultarDadosOrdemServicoActionForm
						.setComercialAtualizado("SIM");
			} else {
				consultarDadosOrdemServicoActionForm
						.setComercialAtualizado("NÃO");
			}
			if (ordemServico.getPercentualCobranca() != null) {
				String percentualCobrado = ordemServico.getPercentualCobranca()
						+ "";
				consultarDadosOrdemServicoActionForm
						.setPercentualCobranca(percentualCobrado.replace(".",
								","));
			} else {
				consultarDadosOrdemServicoActionForm
						.setPercentualCobranca("0,00");
			}
			if (ordemServico.getServicoNaoCobrancaMotivo() != null) {
				consultarDadosOrdemServicoActionForm
						.setMotivoNaoCobranca(ordemServico
								.getServicoNaoCobrancaMotivo().getDescricao());
				consultarDadosOrdemServicoActionForm.setServicoCobrado("NÃO");
			} else {
				consultarDadosOrdemServicoActionForm.setMotivoNaoCobranca(null);
				if (ordemServico.getValorAtual() != null
						&& ordemServico.getPercentualCobranca() != null) {
					BigDecimal valorAtual = new BigDecimal(Util
							.converterObjetoParaString(ordemServico
									.getValorAtual()));
					BigDecimal percentual = new BigDecimal(Util
							.converterObjetoParaString(ordemServico
									.getPercentualCobranca()));
					BigDecimal valorCobrado = valorAtual.multiply(percentual)
							.divide(new BigDecimal("100"), 2,
									BigDecimal.ROUND_HALF_UP);
					consultarDadosOrdemServicoActionForm.setValorCobrado(Util
							.formatarMoedaReal(valorCobrado)
							+ "");
				} else {
					consultarDadosOrdemServicoActionForm
							.setValorCobrado("0,00");
				}
				consultarDadosOrdemServicoActionForm.setServicoCobrado("SIM");
			}

			if (ordemServico.getAtendimentoMotivoEncerramento() != null) {
				consultarDadosOrdemServicoActionForm
						.setMotivoEncerramento(ordemServico
								.getAtendimentoMotivoEncerramento()
								.getDescricao());
			} else {
				consultarDadosOrdemServicoActionForm
						.setMotivoEncerramento(null);
			}

			OrdemServicoUnidade ordemServicoUnidadeEncerramento = consultarOrdemServicoUnidade(
					ordemServico.getId(), AtendimentoRelacaoTipo.ENCERRAR);
			if (ordemServicoUnidadeEncerramento != null) {
				consultarDadosOrdemServicoActionForm
						.setUnidadeEncerramentoId(ordemServicoUnidadeEncerramento
								.getUnidadeOrganizacional().getId()
								+ "");
				consultarDadosOrdemServicoActionForm
						.setUnidadeEncerramentoDescricao(ordemServicoUnidadeEncerramento
								.getUnidadeOrganizacional().getDescricao());
				consultarDadosOrdemServicoActionForm
						.setUsuarioEncerramentoId(ordemServicoUnidadeEncerramento
								.getUsuario().getId()
								+ "");
				consultarDadosOrdemServicoActionForm
						.setUsuarioEncerramentoNome(ordemServicoUnidadeEncerramento
								.getUsuario().getNomeUsuario());
			}
		}

		Collection<ObterDadosAtividadesOSHelper> colecaoObterDadosAtividadesOSHelper = fachada
				.obterDadosAtividadesOS(ordemServico.getId());
		Collection<ObterDadosAtividadeIdOSHelper> colecaoAtividade = new ArrayList();
		ObterDadosAtividadeIdOSHelper obterAtividadeIdHelper = null;
		if (colecaoObterDadosAtividadesOSHelper != null
				&& !colecaoObterDadosAtividadesOSHelper.isEmpty()) {
			for (ObterDadosAtividadesOSHelper dadosAtividade : colecaoObterDadosAtividadesOSHelper) {
				obterAtividadeIdHelper = new ObterDadosAtividadeIdOSHelper();
				obterAtividadeIdHelper.setIdOS(ordemServico.getId());
				if (dadosAtividade.isMaterial()) {
					if (!atividadePossuiMaterial(colecaoAtividade,
							dadosAtividade)) {
						obterAtividadeIdHelper.setMaterial(true);
						obterAtividadeIdHelper.setAtividade(dadosAtividade
								.getAtividade());
						colecaoAtividade.add(obterAtividadeIdHelper);
					}
				} else {
					if (!atividadePossuiMaterial(colecaoAtividade,
							dadosAtividade)) {
						obterAtividadeIdHelper.setPeriodo(true);
						obterAtividadeIdHelper.setAtividade(dadosAtividade
								.getAtividade());
						colecaoAtividade.add(obterAtividadeIdHelper);
					}
				}
			}
			consultarDadosOrdemServicoActionForm
					.setColecaoOSAtividade(colecaoAtividade);
		} else {
			consultarDadosOrdemServicoActionForm.setColecaoOSAtividade(null);
		}
		
		//Pesquisa Dados de Repavimentação
		this.montarDadosRepavimentacao(consultarDadosOrdemServicoActionForm);

		// Colocado por Raphael Rossiter em 26/10/2006
		consultarDadosOrdemServicoActionForm.setNumeroOSParametro("");
		httpServletRequest.setAttribute("nomeCampo", "numeroOSParametro");
		
		// Colocado por Hugo Amorim em 13/10/2009
		if(ordemServico.getProjeto()!=null){
			FiltroProjeto filtroProjeto = new FiltroProjeto();
			
			filtroProjeto.adicionarParametro(new ParametroSimples(FiltroProjeto.ID,ordemServico.getProjeto().getId()));
			
			Collection projetos = fachada.pesquisar(filtroProjeto,Projeto.class.getName());
			
			Projeto projeto = (Projeto) Util.retonarObjetoDeColecao(projetos);
			
			if(projeto!=null){
				consultarDadosOrdemServicoActionForm.setNomeProjeto(projeto.getNome());
				httpServletRequest.setAttribute("nomeProjeto",true);
			}	
		}
		

		if (sessao.getAttribute("manterOs") == null) {
			// Colocado por Sávio Luiz em 24/04/2007
			// Caso venha da consulta de documentos cobranças então não mostra
			// os butões de encerra nem atualizar ordem serviço
			if (sessao.getAttribute("caminhoRetornoOS") == null
					&& httpServletRequest.getParameter("caminhoRetornoOS") != null) {
				sessao.setAttribute("caminhoRetornoOS", httpServletRequest
						.getParameter("caminhoRetornoOS"));
			}else if (sessao.getAttribute("importarMovimentoACQUAGIS") != null
					&& sessao.getAttribute("importarMovimentoACQUAGIS").equals("sim")) {
				sessao.setAttribute("caminhoRetornoOS", "filtrarRegistroAtendimentoTramitacaoAction.do?importarMovimentoACQUAGIS=sim");
			}else {
				sessao.removeAttribute("caminhoRetornoOS");
			}

		} else {
			if (sessao.getAttribute("importarMovimentoACQUAGIS") != null &&
					sessao.getAttribute("importarMovimentoACQUAGIS").equals("sim")) {
				sessao.setAttribute("caminhoRetornoOS", "filtrarOrdemServicoAction.do?idRa=" + consultarDadosOrdemServicoActionForm.getNumeroRA());
			}else {
				sessao.removeAttribute("caminhoRetornoOS");
			}
		}

		return retorno;
	}

	/**
	 * Consulta a ordem de serviço pelo id informado
	 * 
	 * @author Leonardo Regis
	 * @created 14/08/2006
	 */
	private OrdemServico pesquisarOrdemServico(Integer id) {
		Fachada fachada = Fachada.getInstancia();
		OrdemServico retorno = fachada.consultarDadosOrdemServico(id);
		if (retorno == null) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Ordem de Serviço");
		}
		return retorno;
	}

	/**
	 * Consulta a Ordem Serviço Unidade pelo id do OS e Tipo (1=ABRIR/REGISTRAR
	 * e 3-ENCERRAR)
	 * 
	 * @author Leonardo Regis
	 * @date 15/08/2006
	 */
	private OrdemServicoUnidade consultarOrdemServicoUnidade(Integer idOS,
			Integer idAtendimentoTipo) {

		OrdemServicoUnidade retorno = null;

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoOrdemServicoUnidade = null;

		FiltroOrdemServicoUnidade filtroOrdemServicoUnidade = new FiltroOrdemServicoUnidade();
		filtroOrdemServicoUnidade.adicionarParametro(new ParametroSimples(
				FiltroOrdemServicoUnidade.ORDEM_SERVICO_ID, idOS));
		filtroOrdemServicoUnidade.adicionarParametro(new ParametroSimples(
				FiltroOrdemServicoUnidade.ATENDIMENTO_RELACAO_TIPO_ID,
				idAtendimentoTipo));

		filtroOrdemServicoUnidade
				.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
		filtroOrdemServicoUnidade
				.adicionarCaminhoParaCarregamentoEntidade("usuario");
		/**
		 * Alterado por Arthur Carvalho
		 * @date 21/12/2009
		 * Solicitado por Rossiter
		 */
		filtroOrdemServicoUnidade.setCampoOrderBy(FiltroOrdemServicoUnidade.ULTIMA_ALTERACAO);

		colecaoOrdemServicoUnidade = fachada.pesquisar(
				filtroOrdemServicoUnidade, OrdemServicoUnidade.class.getName());
		if (colecaoOrdemServicoUnidade != null
				&& !colecaoOrdemServicoUnidade.isEmpty()) {
			retorno = (OrdemServicoUnidade) Util
					.retonarObjetoDeColecao(colecaoOrdemServicoUnidade);
		}
		return retorno;
	}
	
	private int obterIndexOSColecao(Integer idOS, Collection<OSFiltroHelper> colecao) {
		int index = 0;
		for (OSFiltroHelper helper : colecao) {
			if (helper.getOrdemServico().getId().equals(idOS)) {
				return index;
			}
			index++;
		}
		return -1;
	}

	private boolean atividadePossuiMaterial(
			Collection<ObterDadosAtividadeIdOSHelper> colecaoAtividade,
			ObterDadosAtividadesOSHelper dadosAtividade) {
		boolean retorno = false;
		for (ObterDadosAtividadeIdOSHelper helper : colecaoAtividade) {
			if (helper.getAtividade().getId().intValue() == dadosAtividade
					.getAtividade().getId().intValue()) {
				if (!dadosAtividade.isMaterial()) {
					helper.setPeriodo(true);
				}
				retorno = true;
				break;
			}
		}
		return retorno;
	}
	
	/**
	 * Carrega coleção de ordem de servico, situação da unidade atual no 
	 * objeto facilitador 
	 *
	 * @author Rafael Pinto, Pedro Alexandre
	 * @date 18/08/2006, 14/02/2008
	 *
	 * @param colecaoRegistroAtendimento
	 * @return
	 */
	private Collection loadColecaoOSHelper(Collection<OrdemServico> colecaoOrdemServico) {
		
		Fachada fachada = Fachada.getInstancia();
		
		Collection colecaoOSHelper = new ArrayList();
		UnidadeOrganizacional unidadeAtual = null;
		ObterDescricaoSituacaoOSHelper situacao = null;
		Imovel imovel = null;
		OSFiltroHelper helper = null;
		
		for (Iterator iter = colecaoOrdemServico.iterator(); iter.hasNext();) {
			
			OrdemServico ordemServico = (OrdemServico) iter.next();
			
			situacao = fachada.obterDescricaoSituacaoOS(ordemServico.getId());
			
			if(ordemServico.getRegistroAtendimento() != null) {
				unidadeAtual = fachada.obterUnidadeAtualRA(ordemServico.getRegistroAtendimento().getId());
				imovel = ordemServico.getRegistroAtendimento().getImovel();
			}else if(ordemServico.getCobrancaDocumento() != null){
				imovel = ordemServico.getCobrancaDocumento().getImovel();
			}
			
			helper = new OSFiltroHelper();
			
			helper.setUnidadeAtual(ordemServico.getUnidadeAtual());
			helper.setOrdemServico(ordemServico);
			helper.setImovel(imovel);
			helper.setUnidadeAtual(unidadeAtual);
			helper.setSituacao(situacao.getDescricaoAbreviadaSituacao());
			
			colecaoOSHelper.add(helper);
		}
		
		return colecaoOSHelper;
	}	
	
	
	/**
	 * Monta os dados de repavimentação 
	 *
	 * @author Rafael Pinto
	 * @date 18/03/2011
	 *
	 * @param colecaoRegistroAtendimento
	 * @return void
	 */
	private void montarDadosRepavimentacao(ConsultarDadosOrdemServicoActionForm form){
		
		FiltroOrdemServicoPavimento filtro = new FiltroOrdemServicoPavimento();
		
		filtro.adicionarParametro(new ParametroSimples(
			FiltroOrdemServicoPavimento.ORDEM_SERVICO_ID, 
			form.getNumeroOS()));

		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServicoPavimento.PAVIMENTO_RUA);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServicoPavimento.PAVIMENTO_CALCADA);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServicoPavimento.PAVIMENTO_RUA_RETORNO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServicoPavimento.PAVIMENTO_CALCADA_RETORNO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServicoPavimento.UNIDADE_REPAVIMENTADORA);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServicoPavimento.MOTIVO_REJEICAO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServicoPavimento.USUARIO_ACEITE);

		Collection colecaoOrdemServicoPavimento = 
			this.getFachada().pesquisar(
				filtro, 
				OrdemServicoPavimento.class.getName());
		
		if (colecaoOrdemServicoPavimento != null && !colecaoOrdemServicoPavimento.isEmpty()) {
			OrdemServicoPavimento ordemServicoPavimento = 
				(OrdemServicoPavimento) Util.retonarObjetoDeColecao(colecaoOrdemServicoPavimento);
			
			
			if(ordemServicoPavimento.getUnidadeRepavimentadora() != null){
				form.setUnidadeRepavimentadoraId(ordemServicoPavimento.getUnidadeRepavimentadora().getId().toString());
				form.setUnidadeRepavimentadoraDescricao(ordemServicoPavimento.getUnidadeRepavimentadora().getDescricao());
			}

			if(ordemServicoPavimento.getPavimentoRua() != null){
				form.setTipoPavimentoRua(ordemServicoPavimento.getPavimentoRua().getDescricao());
			}

			if(ordemServicoPavimento.getAreaPavimentoRua() != null){
				form.setAreaPavimentoRua(Util.formataBigDecimal(ordemServicoPavimento.getAreaPavimentoRua(),2,true));
			}

			if(ordemServicoPavimento.getPavimentoRuaRetorno() != null){
				form.setTipoPavimentoRuaRet(ordemServicoPavimento.getPavimentoRuaRetorno().getDescricao());
			}
			
			if(ordemServicoPavimento.getAreaPavimentoRuaRetorno() != null){
				form.setAreaPavimentoRuaRet(Util.formataBigDecimal(ordemServicoPavimento.getAreaPavimentoRuaRetorno(),2,true));
			}
			
			if(ordemServicoPavimento.getPavimentoCalcada() != null){
				form.setTipoPavimentoCalcada(ordemServicoPavimento.getPavimentoCalcada().getDescricao());
			}
			
			if(ordemServicoPavimento.getAreaPavimentoCalcada() != null){
				form.setAreaPavimentoCalcada(Util.formataBigDecimal(ordemServicoPavimento.getAreaPavimentoCalcada(),2,true));
			}
			
			if(ordemServicoPavimento.getPavimentoCalcadaRetorno() != null){
				form.setTipoPavimentoCalcadaRet(ordemServicoPavimento.getPavimentoCalcadaRetorno().getDescricao());
			}

			if(ordemServicoPavimento.getAreaPavimentoCalcadaRetorno() != null){
				form.setAreaPavimentoCalcadaRet(Util.formataBigDecimal(ordemServicoPavimento.getAreaPavimentoCalcadaRetorno(),2,true));
			}
			
			if(ordemServicoPavimento.getDataExecucao() != null){
				form.setDataRetornoRepavimentacao(Util.formatarData(ordemServicoPavimento.getDataExecucao()));
			}

			if(ordemServicoPavimento.getObservacao() != null){
				form.setObservacaoRetornoRepavimentacao(ordemServicoPavimento.getObservacao());
			}
			
			if(ordemServicoPavimento.getDataRejeicao() != null){
				form.setDataRejeicaoRepavimentacao(Util.formatarData(ordemServicoPavimento.getDataRejeicao()));
			}
			
			if(ordemServicoPavimento.getMotivoRejeicao() != null){
				form.setMotivoRejeicaoRepavimentacao(ordemServicoPavimento.getMotivoRejeicao().getDescricao());
			}
			
			if(ordemServicoPavimento.getDescricaoRejeicao() != null){
				form.setDescricaoRejeicaoRepavimentacao(ordemServicoPavimento.getDescricaoRejeicao());
			}
			
			if(ordemServicoPavimento.getIndicadorAceite() != null){
				if(ordemServicoPavimento.getIndicadorAceite().equals(ConstantesSistema.SIM)){
					form.setSituacaoAceiteRepavimentacao("Aceita");
				}else{
					form.setSituacaoAceiteRepavimentacao("Não Aceita");
				}
			}else{
				form.setSituacaoAceiteRepavimentacao("Sem Aceite");
			}
			
			if(ordemServicoPavimento.getDataAceite() != null){
				form.setDataAceiteRepavimentacao(Util.formatarData(ordemServicoPavimento.getDataAceite()));
			}
			
			if(ordemServicoPavimento.getUsuarioAceite() != null){
				form.setUsuarioAceiteId(ordemServicoPavimento.getUsuarioAceite().getLogin());
				form.setUsuarioAceiteNome(ordemServicoPavimento.getUsuarioAceite().getNomeUsuario());
			}
			
			if(ordemServicoPavimento.getDescricaoMotivoAceite() != null){
				form.setDescricaoMotivoAceite(ordemServicoPavimento.getDescricaoMotivoAceite());
			}


		}
		
		
		
	}
}
