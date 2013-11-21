package gcom.relatorio.atendimentopublico;

import gcom.atendimentopublico.bean.UnidadesFilhasHelper;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.bean.FiltrarGestaoRAHelper;
import gcom.atendimentopublico.registroatendimento.bean.GestaoRegistroAtendimentoHelper;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * [UC0497] Gerar Relatorio Resumo de Solicitacoes de RA por Unidade
 * 
 * @author Victor Cisneiros
 * @date 20/06/2008
 */
public class RelatorioGestaoSolicitacoesRAPorChefia extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;

	public RelatorioGestaoSolicitacoesRAPorChefia(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_GESTAO_SOLICITACOES_RA_POR_CHEFIA);
	}
	
	@Deprecated
	public RelatorioGestaoSolicitacoesRAPorChefia() {
		super(null, "");
	}

	@Override
	public Object executar() throws TarefaException {
		Fachada fachada = Fachada.getInstancia();
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		Short situacao = (Short) getParametro("situacao");
		Collection<Integer> idsSolicitacaoTipo = (Collection<Integer>) getParametro("idsSolicitacaoTipo");
		Collection<Integer> idsSolicitacaoTipoEspecificacao = (Collection<Integer>) getParametro("idsSolicitacaoTipoEspecificacao");
		Date dataAtendimentoInicial = (Date) getParametro("dataAtendimentoInicial");
		Date dataAtendimentoFinal = (Date) getParametro("dataAtendimentoFinal");
		Integer idUnidadeOrganizacional = (Integer) getParametro("idUnidadeOrganizacional");
		Integer idUnidadeSuperior = (Integer) getParametro("idUnidadeSuperior");
		Integer idMunicipio = (Integer) getParametro("idMunicipio");
		Integer codigoBairro = (Integer) getParametro("codigoBairro");
		Integer tipoRelatorio = (Integer) getParametro("tipoRelatorio");
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		UnidadeOrganizacional unidadeOrganizacional = null;
		UnidadeOrganizacional unidadeSuperior = null;
		Municipio municipio = null;
		Bairro bairro = null;
		
		// ------------------------------
		// -- Situação
		// ------------------------------
		String parametroSituacao = "AMBOS";
		if (situacao != null) {
			if (situacao == 1) parametroSituacao = "PENDENTES";
			if (situacao == 2) parametroSituacao = "ENCERRADOS";
			if (situacao == 3) parametroSituacao = "SEM LOCAL DE OCORRÊNCIA";
		}
		
		// ------------------------------
		// -- Unidade Organizacional
		// ------------------------------
		String parametroUnidadeOrganizacional = "";
		if (idUnidadeOrganizacional != null) {
			FiltroUnidadeOrganizacional filtroUnidade = new FiltroUnidadeOrganizacional();
			filtroUnidade.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, 
					idUnidadeOrganizacional));
			
			Collection pesquisa = fachada.pesquisar(filtroUnidade, UnidadeOrganizacional.class.getName());
			if (pesquisa == null || pesquisa.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Unidade Organizacional");
			}
			
			unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(pesquisa);
			parametroUnidadeOrganizacional = unidadeOrganizacional.getDescricao();
		}
		
		// ------------------------------
		// -- Unidade Superior
		// ------------------------------
		String parametroUnidadeSuperior = "";
		if (idUnidadeSuperior != null) {
			FiltroUnidadeOrganizacional filtroUnidade = new FiltroUnidadeOrganizacional();
			filtroUnidade.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, idUnidadeSuperior));
			
			Collection pesquisa = fachada.pesquisar(filtroUnidade, UnidadeOrganizacional.class.getName());
			if (pesquisa == null || pesquisa.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Unidade Superior");
			}
			
			unidadeSuperior = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(pesquisa);
			parametroUnidadeSuperior = unidadeSuperior.getDescricao();
		}
		
		// ------------------------------
		// -- Municipio
		// ------------------------------
		String parametroMunicipio = "";
		if (idMunicipio != null) {
			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
			filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, idMunicipio));
			
			Collection pesquisa = fachada.pesquisar(filtroMunicipio, Municipio.class.getName());
			if (pesquisa == null || pesquisa.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Municipio");
			}
			
			municipio = (Municipio) Util.retonarObjetoDeColecao(pesquisa);
			parametroMunicipio = municipio.getNome();
		}
		
		// ------------------------------
		// -- Bairro
		// ------------------------------
		String parametroBairro = "";
		if (codigoBairro != null) {
			FiltroBairro filtroBairro = new FiltroBairro();
			filtroBairro.adicionarParametro(new ParametroSimples(FiltroBairro.MUNICIPIO_ID, new Integer(idMunicipio)));
			filtroBairro.adicionarParametro(new ParametroSimples(FiltroBairro.CODIGO, new Integer(codigoBairro)));
			
			Collection pesquisa = fachada.pesquisar(filtroBairro, Bairro.class.getName());
			if (pesquisa == null || pesquisa.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Bairro");
			}
			
			bairro = (Bairro) Util.retonarObjetoDeColecao(pesquisa);
			parametroBairro = bairro.getNome();
		}
		
		// ------------------------------
		// -- Data atendimento
		// ------------------------------
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		String parametroData = "de " + f.format(dataAtendimentoInicial) + " a " + f.format(dataAtendimentoFinal);
		
		// ------------------------------
		// -- Solicitacoes/Especificacoes
		// ------------------------------
		String parametroEspecificacoes = "Solicitações: ";
		if (idsSolicitacaoTipo != null) {
			FiltroSolicitacaoTipo filtro = new FiltroSolicitacaoTipo();
			Collection<SolicitacaoTipo> pesquisa = fachada.pesquisar(idsSolicitacaoTipo, filtro, SolicitacaoTipo.class.getName());
			
			for (SolicitacaoTipo solicitacaoTipo : pesquisa) {
				parametroEspecificacoes += solicitacaoTipo.getDescricao() + "; ";
			}
		}
		else {
			if (idsSolicitacaoTipoEspecificacao != null) {
				parametroEspecificacoes = "Especificações: ";
				
				FiltroSolicitacaoTipoEspecificacao filtro = new FiltroSolicitacaoTipoEspecificacao();
				Collection<SolicitacaoTipoEspecificacao> pesquisa = fachada.pesquisar(idsSolicitacaoTipoEspecificacao, filtro, SolicitacaoTipoEspecificacao.class.getName());
				
				for (SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao : pesquisa) {
					parametroEspecificacoes += solicitacaoTipoEspecificacao.getDescricao() + "; ";
				}
			} else {
				parametroEspecificacoes += "TODAS";
			}
		}
		
		// ------------------------------
		// -- Parametros
		// ------------------------------
		parametros.put("situacao", parametroSituacao);
		parametros.put("dataAtendimento", parametroData);
		parametros.put("especificacoes", "TODOS");
		parametros.put("unidadeAtual", parametroUnidadeOrganizacional);
		parametros.put("unidadeSuperior", parametroUnidadeSuperior);
		parametros.put("municipio", parametroMunicipio);
		parametros.put("bairro", parametroBairro);
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("especificacoes", parametroEspecificacoes);
		parametros.put("tipoRelatorio", "R0499");
		
		
		
		// ------------------------------
		// -- Estruturas de Dados
		// ------------------------------
		
		/*
		 * Hash ordenada Id da UnidadeOrganizacional -> UnidadeOrganizacional
		 * Os ids estão ordenados de modo que uma unidade filha aparece sempre antes de sua superior
		 */
		LinkedHashMap<Integer, UnidadeOrganizacional> unidades = new LinkedHashMap<Integer, UnidadeOrganizacional>();
		
		/*
		 * Hash Id da UnidadeOrganizacional -> Colecao de Unidades Filhas dela
		 */
		Map<Integer, Collection<UnidadeOrganizacional>> filhosDaUnidade = new HashMap<Integer, Collection<UnidadeOrganizacional>>();
		
		/*
		 * Hash Id da UnidadeOrganizacional -> Colecao de OrdensDeServico cuja unidadeAtual possui o Id igual a chave
		 */
		Map<Integer, Collection<GestaoRegistroAtendimentoHelper>> registrosDaUnidade = new HashMap<Integer, Collection<GestaoRegistroAtendimentoHelper>>();
		
		
		
		// ------------------------------
		// -- Realizar Pesquisa
		// ------------------------------
		
		if (idUnidadeOrganizacional != null) {
			unidades.put(idUnidadeOrganizacional, unidadeOrganizacional);
			filhosDaUnidade.put(idUnidadeOrganizacional, null);
		}
		else {
			UnidadesFilhasHelper helper = fachada.pesquisarUnidadesFilhas(unidadeSuperior.getId());
			unidades = helper.getUnidades();
			filhosDaUnidade = helper.getFilhosDaUnidade();
		}
		
		Collection<Integer> idsUnidadeAtual = new ArrayList<Integer>();
		for (UnidadeOrganizacional u : unidades.values()) {
			idsUnidadeAtual.add(u.getId());
		}
		
		boolean unidadeUnica = false;
		if (unidades.size() == 1) {
			unidadeUnica = true;
		}

		FiltrarGestaoRAHelper filtro = new FiltrarGestaoRAHelper();
		if (situacao != null) filtro.setSituacao(situacao);
		filtro.setDataAtendimentoInicial(dataAtendimentoInicial);
		filtro.setDataAtendimentoFinal(dataAtendimentoFinal);
		filtro.setIdsUnidadeAtual(idsUnidadeAtual);
		if (idsSolicitacaoTipo != null) filtro.setIdsSolicitacaoTipo(idsSolicitacaoTipo);
		if (idsSolicitacaoTipoEspecificacao != null) filtro.setIdsSolicitacaoTipoEspecificacao(idsSolicitacaoTipoEspecificacao);
		if (idMunicipio != null) filtro.setIdMunicipio(idMunicipio);
		if (codigoBairro != null) filtro.setIdBairro(bairro.getId());
		
		List<GestaoRegistroAtendimentoHelper> pesquisa = fachada.filtrarGestaoRA(filtro);
		if (pesquisa == null || pesquisa.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente", null, "Registros de Atendimento");
		}
		
		// Separando os RegistrosAtendimento pesquisados por unidadeAtual
		for (GestaoRegistroAtendimentoHelper registro : pesquisa) {
			if (registro.getIdUnidadeAtual() != null) {
				Integer idUnidadeAtual = registro.getIdUnidadeAtual();
				if (registrosDaUnidade.containsKey(idUnidadeAtual)) {
					registrosDaUnidade.get(idUnidadeAtual).add(registro);
				} else {
					List<GestaoRegistroAtendimentoHelper> list = new ArrayList<GestaoRegistroAtendimentoHelper>();
					list.add(registro);
					registrosDaUnidade.put(idUnidadeAtual, list);
				}
			}
		}
		
		// ------------------------------
		// -- Processando Linhas do Relatório
		// ------------------------------
		
		List<RelatorioGestaoSolicitacoesRAPorChefiaBean> beans = new ArrayList<RelatorioGestaoSolicitacoesRAPorChefiaBean>();
		
		// hash id UnidadeOrganizacional -> um bean com a soma total dos beans dessa unidade
		Map<Integer, RelatorioGestaoSolicitacoesRAPorChefiaBean> totalBeansUnidades = 
			new HashMap<Integer, RelatorioGestaoSolicitacoesRAPorChefiaBean>();
		
		// ------------------------------
		// -- Processando Dados para Cada Unidade
		// ------------------------------
		
		for (int idUnidade : unidades.keySet()) {
			UnidadeOrganizacional unidade = unidades.get(idUnidade);
			
			// cria um bean para a soma total dessa unidade e armazena na hash
			RelatorioGestaoSolicitacoesRAPorChefiaBean totalUnidadeBean = new RelatorioGestaoSolicitacoesRAPorChefiaBean();
			totalUnidadeBean.setUnidade(unidade.getId() + " - " + unidade.getDescricao());
			totalUnidadeBean.setDescricaoUnidade(unidade.getId() + " - " + unidade.getDescricao());
			totalBeansUnidades.put(unidade.getId(), totalUnidadeBean);
			
			// se não existirem registros para essa unidade parar
			if (!registrosDaUnidade.containsKey(unidade.getId())) continue;
			
			// para cada registro de atendimento da unidade atual
			for (GestaoRegistroAtendimentoHelper registro : registrosDaUnidade.get(unidade.getId())) {
				
				// quantidade solicitada
				totalUnidadeBean.setQuantidadeSolicitada(totalUnidadeBean.getQuantidadeSolicitada() +1);
				if (registro.getFlag0800()) {
					totalUnidadeBean.setQuantidadeSolicitada0800(totalUnidadeBean.getQuantidadeSolicitada0800() +1);
				}
				
				// quantidade executada
				if (registro.getSituacao() == 2) {
					totalUnidadeBean.setQuantidadeExecutadaTotal(totalUnidadeBean.getQuantidadeExecutadaTotal() +1);
					if (registro.getDataEncerramento() == null) continue;
					
					if (registro.getDataEncerramento().getTime() <= registro.getDataPrevistaAtual().getTime()) {
						totalUnidadeBean.setQuantidadeExecutadaNoPrazo(totalUnidadeBean.getQuantidadeExecutadaNoPrazo() +1);
					} else {
						totalUnidadeBean.setQuantidadeExecutadaForaPrazo(totalUnidadeBean.getQuantidadeExecutadaForaPrazo() +1);
					}
					// 0800
					if (registro.getFlag0800()) {
						totalUnidadeBean.setQuantidadeExecutadaTotal0800(totalUnidadeBean.getQuantidadeExecutadaTotal0800() +1);
						if (registro.getDataEncerramento().getTime() <= registro.getDataPrevistaAtual().getTime()) {
							totalUnidadeBean.setQuantidadeExecutadaNoPrazo0800(totalUnidadeBean.getQuantidadeExecutadaNoPrazo0800() +1);
						} else {
							totalUnidadeBean.setQuantidadeExecutadaForaPrazo0800(totalUnidadeBean.getQuantidadeExecutadaForaPrazo0800() +1);
						}
					}
				}
				
				// quantidade atendida	
				if (registro.getIdAtendimentoMotivoEncerramento() != null &&
					registro.getIdAtendimentoMotivoEncerramento() == 2) {
					
					totalUnidadeBean.setQuantidadeAtendidaTotal(totalUnidadeBean.getQuantidadeAtendidaTotal() +1);
					if (registro.getDataEncerramento().getTime() <= registro.getDataPrevistaAtual().getTime()) {
						totalUnidadeBean.setQuantidadeAtendidaNoPrazo(totalUnidadeBean.getQuantidadeAtendidaNoPrazo() +1);
					} else {
						totalUnidadeBean.setQuantidadeAtendidaForaPrazo(totalUnidadeBean.getQuantidadeAtendidaForaPrazo() +1);
					}
					// 0800
					if (registro.getFlag0800()) {
						totalUnidadeBean.setQuantidadeAtendidaTotal0800(totalUnidadeBean.getQuantidadeAtendidaTotal0800() +1);
						if (registro.getDataEncerramento().getTime() <= registro.getDataPrevistaAtual().getTime()) {
							totalUnidadeBean.setQuantidadeAtendidaNoPrazo0800(totalUnidadeBean.getQuantidadeAtendidaNoPrazo0800() +1);
						} else {
							totalUnidadeBean.setQuantidadeAtendidaForaPrazo0800(totalUnidadeBean.getQuantidadeAtendidaForaPrazo0800() +1);
						}
					}
				}
				
				Date atual = new Date();
				
				Calendar calendario = Calendar.getInstance();
				calendario.setTime(atual);
				calendario.set(Calendar.HOUR, 0);
				calendario.set(Calendar.MINUTE, 0);
				calendario.set(Calendar.SECOND, 0);
				calendario.set(Calendar.MILLISECOND, 0);
				
				atual = calendario.getTime();
				
				// residual
				if (registro.getSituacao() != 2) {
					if (atual.getTime() <= registro.getDataPrevistaAtual().getTime()) {
						totalUnidadeBean.setResidualNoPrazo(totalUnidadeBean.getResidualNoPrazo() +1);
					} else {
						totalUnidadeBean.setResidualForaPrazo(totalUnidadeBean.getResidualForaPrazo() +1);
					}
					if (registro.getFlag0800()) {
						if (atual.getTime() <= registro.getDataPrevistaAtual().getTime()) {
							totalUnidadeBean.setResidualNoPrazo0800(totalUnidadeBean.getResidualNoPrazo0800() +1);
						} else {
							totalUnidadeBean.setResidualForaPrazo0800(totalUnidadeBean.getResidualForaPrazo0800() +1);
						}
					}
				}
			}
		}
		
		
		
		// ------------------------------
		// -- Criacao das Linhas do Relatorio
		// ------------------------------
		
		// criar o relatorio com as paginas das unidades começando da suas filhas, e pondo um 
		// total de resumo de atividades para cada unidade superior que aparecer no loop
		
		for (int idUnidade : unidades.keySet()) {
			UnidadeOrganizacional unidade = unidades.get(idUnidade);
			String descricaoUnidade = unidade.getDescricao();
			
			// adicionar soma total para essa unidade
			RelatorioGestaoSolicitacoesRAPorChefiaBean totalUnidade = totalBeansUnidades.get(idUnidade);
			
			// se a unidade tiver filhos, criar uma nova pagina com resumo das atividades
			Collection<UnidadeOrganizacional> filhos = filhosDaUnidade.get(idUnidade);
			if (filhos != null) {
				// criar uma linha para total dessa unidade
				RelatorioGestaoSolicitacoesRAPorChefiaBean cloneSuperior = totalUnidade.copy();
				cloneSuperior.setUnidade(descricaoUnidade);
				cloneSuperior.setSuperior(true);
				if (!cloneSuperior.isEmpty()) {
					beans.add(cloneSuperior);
				}
				
				// criar uma linha para cada unidade filha
				for (UnidadeOrganizacional filho : filhos) {
					RelatorioGestaoSolicitacoesRAPorChefiaBean totalFilhoBean = totalBeansUnidades.get(filho.getId());
					totalFilhoBean.setUnidade(descricaoUnidade);
					totalFilhoBean.setSuperior(true);
					if (!totalFilhoBean.isEmpty()) {
						beans.add(totalFilhoBean);
					}
					
					// somar os valores da unidade superior atual com os valores do filho
					totalUnidade.sum(totalFilhoBean);
				}
				
				// adicionar linha com soma total para essa unidade
				adicionarTotal(beans, totalUnidade.copy(), descricaoUnidade, !unidadeUnica);
			}
			if (unidadeUnica) {
				adicionarTotal(beans, totalUnidade.copy(), descricaoUnidade, !unidadeUnica);
			}
		}
		
		byte[] retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_GESTAO_SOLICITACOES_RA_POR_CHEFIA,
				parametros, new RelatorioDataSource(beans), tipoRelatorio);
		
//		try {
//			persistirRelatorioConcluido(retorno, 
//					Relatorio.RELATORIO_GESTAO_SERVICOS_UPA, getIdFuncionalidadeIniciada());
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new TarefaException("Erro ao gravar relatório no sistema", e);
//		}
		
		return retorno;
	}
	
	/**
	 * Adiciona uma linha de soma total no relatorio, antes adicionando uma linha em branco
	 * 
	 * @param total Um bean com os valores totais a serem adicionados
	 * @param resumoSuperior Flag indicando se a linha vai ser para unidade superior ou nao
	 */
	public void adicionarTotal(
			List<RelatorioGestaoSolicitacoesRAPorChefiaBean> beans, 
			RelatorioGestaoSolicitacoesRAPorChefiaBean total,
			String descricaoUnidade,
			boolean resumoSuperior) {
		
		RelatorioGestaoSolicitacoesRAPorChefiaBean somaTotal = total.copy();
		somaTotal.setUnidade(descricaoUnidade);
		somaTotal.setSuperior(resumoSuperior);
		
		RelatorioGestaoSolicitacoesRAPorChefiaBean linhaEmBranco = new RelatorioGestaoSolicitacoesRAPorChefiaBean();
		linhaEmBranco.setUnidade(descricaoUnidade);
		linhaEmBranco.setDescricaoUnidade("");
		linhaEmBranco.setNull();
		linhaEmBranco.setSuperior(resumoSuperior);
		
		if (resumoSuperior) {
			somaTotal.setDescricaoUnidade("TOTAL DA CHEFIA: " + descricaoUnidade);
		} else {
			somaTotal.setDescricaoUnidade("TOTAL DA UNIDADE: " + descricaoUnidade);
		}
		
		if (!somaTotal.isEmpty()) {
			beans.add(somaTotal);
			if (resumoSuperior) beans.add(linhaEmBranco);
		}
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 1;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioGestaoSolicitacoesRAPorChefia", this);
	}

}
