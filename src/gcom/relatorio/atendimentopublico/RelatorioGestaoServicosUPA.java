package gcom.relatorio.atendimentopublico;

import gcom.atendimentopublico.bean.UnidadesFilhasHelper;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade;
import gcom.atendimentopublico.ordemservico.bean.PesquisarOrdemServicoHelper;
import gcom.batch.Relatorio;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
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
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * [UC0778] Gerar Relatório de Gestão de Serviços UPA
 * 
 * @see gcom.gui.atendimentopublico.ordemservico.ExibirGerarRelatorioGestaoServicosUPAAction
 * @see gcom.gui.atendimentopublico.ordemservico.GerarRelatorioGestaoServicosUPAActionForm
 * @see gcom.gui.relatorio.atendimentopublico.GerarRelatorioGestaoServicosUPAAction
 * 
 * @author Victor Cisneiros
 * @date 04/06/2008
 */
public class RelatorioGestaoServicosUPA extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioGestaoServicosUPA(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_GESTAO_SERVICOS_UPA);
	}
	
	@Deprecated
	public RelatorioGestaoServicosUPA() {
		super(null, "");
	}

	@Override
	public Object executar() throws TarefaException {
		Fachada fachada = Fachada.getInstancia();
		Map<String, Object> parametros = new HashMap<String, Object>();

		Short situacao = (Short) getParametro("situacao");
		Collection<Integer> collectionServicoTipo = (Collection<Integer>) getParametro("collectionServicoTipo");
		Date periodoGeracaoInicial = (Date) getParametro("periodoGeracaoInicial");
		Date periodoGeracaoFinal = (Date) getParametro("periodoGeracaoFinal");
		Integer idUnidadeOrganizacional = (Integer) getParametro("idUnidadeOrganizacional");
		Integer idUnidadeSuperior = (Integer) getParametro("idUnidadeSuperior");
		Integer idEmpresa = (Integer) getParametro("idEmpresa");
		Integer tipoRelatorio = (Integer) getParametro("tipoRelatorio");
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		UnidadeOrganizacional unidadeOrganizacional = null;
		UnidadeOrganizacional unidadeSuperior = null;
		Empresa empresa = null;
		
		// ------------------------------
		// -- Situação
		// ------------------------------
		String parametroSituacao = "AMBOS";
		if (situacao != null) {
			if (situacao == ConstantesSistema.SITUACAO_REFERENCIA_PENDENTE) parametroSituacao = "PENDENTES";
			if (situacao == ConstantesSistema.SITUACAO_REFERENCIA_ENCERRADA) parametroSituacao = "ENCERRADAS";
		}

		// ------------------------------
		// -- Unidade Organizacional
		// ------------------------------
		String parametroUnidadeOrganizacional = "";
		if (idUnidadeOrganizacional != null) {
			FiltroUnidadeOrganizacional filtroUnidade = new FiltroUnidadeOrganizacional();
			filtroUnidade.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, idUnidadeOrganizacional));
			
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
		// -- Empresa
		// ------------------------------
		String parametroEmpresa = "";
		if (idEmpresa != null) {
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, idEmpresa));
			
			Collection pesquisa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
			if (pesquisa == null || pesquisa.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Unidade Superior");
			}
			
			empresa = (Empresa) Util.retonarObjetoDeColecao(pesquisa);
			parametroEmpresa = empresa.getDescricao();
		}
		
		// ------------------------------
		// -- ServicoTipo
		// ------------------------------
		String parametroServicos = "TODOS";
		if (collectionServicoTipo != null) {
			FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
			Collection<ServicoTipo> pesquisa = fachada.pesquisar(collectionServicoTipo, filtroServicoTipo, ServicoTipo.class.getName());
			
			parametroServicos = "";
			for (ServicoTipo servicoTipo : pesquisa) {
				parametroServicos += servicoTipo.getDescricao() + "; ";
			}
			parametros.put("servicos", parametroServicos);
		}
		
		// ------------------------------
		// -- Periodo de Geracao
		// ------------------------------
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		String parametroPeriodo = "de " + f.format(periodoGeracaoInicial) + " a " + f.format(periodoGeracaoFinal);
		
		// ------------------------------
		// -- Parametros
		// ------------------------------
		parametros.put("situacao", parametroSituacao);
		parametros.put("periodo", parametroPeriodo);
		parametros.put("servicos", parametroServicos);
		parametros.put("unidadeOrganizacional", parametroUnidadeOrganizacional);
		parametros.put("unidadeSuperior", parametroUnidadeSuperior);
		parametros.put("empresa", parametroEmpresa);
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("tipoRelatorio", "RF0778");
		
		
		
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
		Map<Integer, Collection<OrdemServico>> ordensServicoDaUnidade = new HashMap<Integer, Collection<OrdemServico>>();
		
		
		
		// ------------------------------
		// -- Realizar Pesquisa
		// ------------------------------
		
		// Se os ServicoTipos nao foram restringidos, selecionar todos os ServicoTipo (mas somente os terceirizados)
		if (collectionServicoTipo == null) {
			collectionServicoTipo = new ArrayList<Integer>();
			
			FiltroServicoTipo filtroTipo = new FiltroServicoTipo();
			filtroTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.INDICADORTERCEIRIZADO, 1));
			filtroTipo.setConsultaSemLimites(true);
			
			Collection<ServicoTipo> pesquisa = fachada.pesquisar(filtroTipo, ServicoTipo.class.getName());
			for (ServicoTipo servicoTipo : pesquisa) {
				collectionServicoTipo.add(servicoTipo.getId());
			}
		}
		
		// Busca por unidadeOrganizacional
		if (unidadeOrganizacional != null) {
			unidades.put(unidadeOrganizacional.getId(), unidadeOrganizacional);
			filhosDaUnidade.put(unidadeOrganizacional.getId(), null);
		}
		
		// Busca por unidadeSuperior e suas filhas/descendentes
		else if (unidadeSuperior != null) {
			UnidadesFilhasHelper helper = fachada.pesquisarUnidadesFilhas(unidadeSuperior.getId());
			unidades = helper.getUnidades();
			filhosDaUnidade = helper.getFilhosDaUnidade();
		}
		
		// Busca por unidades da Empresa
		else if (empresa != null) {
			FiltroUnidadeOrganizacional filtroUnidade = new FiltroUnidadeOrganizacional();
			filtroUnidade.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.EMPRESA, empresa.getId()));
			
			// pesquisando as unidades desta empresa
			Collection<UnidadeOrganizacional> pesquisa = fachada.pesquisar(filtroUnidade, UnidadeOrganizacional.class.getName());
			if (pesquisa == null || pesquisa.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Unidades Organizacionais da Empresa");
			}
			
			// criando uma unidade temporaria desta empresa para o relatorio
			UnidadeOrganizacional unidadeEmpresa = new UnidadeOrganizacional();
			unidadeEmpresa.setId(empresa.getId());
			unidadeEmpresa.setDescricao(empresa.getDescricao());
			
			// adicionando todas as unidades encontradas na pesquisa como filhas da unidade temporaria da empresa
			List<UnidadeOrganizacional> empresaFilhos = new ArrayList<UnidadeOrganizacional>();
			for (UnidadeOrganizacional unidade : pesquisa) {
				unidades.put(unidade.getId(), unidade);
				filhosDaUnidade.put(unidade.getId(), null);
				empresaFilhos.add(unidade);
			}
			unidades.put(unidadeEmpresa.getId(), unidadeEmpresa);
			filhosDaUnidade.put(unidadeEmpresa.getId(), empresaFilhos);
		}
		
		// Criando Array com os Ids dos ServicoTipos
		int count = 0;
		Integer[] arrayTipoServiso = new Integer[collectionServicoTipo.size()];
		for (Integer idServicoTipo : collectionServicoTipo) {
			arrayTipoServiso[count++] = idServicoTipo;
		}

		// Criar um Filtro para pesquisar as OrdemServico
		PesquisarOrdemServicoHelper filtro = new PesquisarOrdemServicoHelper();
		if (situacao != null) filtro.setSituacaoOrdemServico(situacao);
		filtro.setTipoServicos(arrayTipoServiso);
		filtro.setDataGeracaoInicial(periodoGeracaoInicial);
		filtro.setDataGeracaoFinal(periodoGeracaoFinal);
		filtro.setIdsUnidadesAtuais(unidades.keySet());
		
		Collection<OrdemServico> pesquisa = fachada.pesquisarOrdemServico(filtro);
		if (pesquisa == null || pesquisa.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente", null, "Ordens de Serviços");
		}
		
		// Separando as OrdemServico pesquisadas por unidadeAtual
		for (OrdemServico ordem : pesquisa) {
			if (ordem.getUnidadeAtual() != null) {
				Integer idUnidadeAtual = ordem.getUnidadeAtual().getId();
				if (ordensServicoDaUnidade.containsKey(idUnidadeAtual)) {
					ordensServicoDaUnidade.get(idUnidadeAtual).add(ordem);
				} else {
					List<OrdemServico> list = new ArrayList<OrdemServico>();
					list.add(ordem);
					ordensServicoDaUnidade.put(idUnidadeAtual, list);
				}
			}
		}
		
		
		
		// ------------------------------
		// -- Processando Linhas do Relatório
		// ------------------------------
		
		List<RelatorioGestaoServicosUPABean> beans = new ArrayList<RelatorioGestaoServicosUPABean>();
		
		// hash id da UnidadeOrganizacional -> coleção de beans da sua pagina de relatorio
		Map<Integer, Collection<RelatorioGestaoServicosUPABean>> beansUnidades =
			new HashMap<Integer, Collection<RelatorioGestaoServicosUPABean>>();
		
		// hash id UnidadeOrganizacional -> um bean com a soma total dos beans dessa unidade
		Map<Integer, RelatorioGestaoServicosUPABean> totalBeansUnidades = 
			new HashMap<Integer, RelatorioGestaoServicosUPABean>();
		
		// ------------------------------
		// -- Processando Dados para Cada Unidade
		// ------------------------------
		
		// popular os dois hashs anteriores com os dados de cada unidade
		for (int idUnidade : unidades.keySet()) {
			UnidadeOrganizacional unidade = unidades.get(idUnidade);
			
			// Cria um Bean para a Soma Total dessa Unidade e armazena na hash
			RelatorioGestaoServicosUPABean totalUnidadeBean = new RelatorioGestaoServicosUPABean();
			totalUnidadeBean.setAtividade(unidade.getId() + " - " + unidade.getDescricao());
			totalBeansUnidades.put(unidade.getId(), totalUnidadeBean);
			
			// hash id ServicoTipo -> soma total para esse servico (dentro dessa unidade)
			Map<Integer, RelatorioGestaoServicosUPABean> atividadeBeans =
				new HashMap<Integer, RelatorioGestaoServicosUPABean>();
			
			// se não existirem OrdemServicos para essa unidade parar
			if (!ordensServicoDaUnidade.containsKey(unidade.getId())) continue;
			
			// para cada OrdemServico da unidade atual
			for (OrdemServico ordem : ordensServicoDaUnidade.get(unidade.getId())) {
				
				ServicoTipo servicoTipo = ordem.getServicoTipo();
				
				// Pega da hash o bean para a soma dos valores para esse serviço dentro dessa unidade
				RelatorioGestaoServicosUPABean atividadeBean = null;
				if (atividadeBeans.containsKey(servicoTipo.getId())) {
					atividadeBean = atividadeBeans.get(servicoTipo.getId());
				} else {
					atividadeBean = new RelatorioGestaoServicosUPABean();
					atividadeBean.setUnidade(unidade.getId() + " - " + unidade.getDescricao());
					atividadeBean.setAtividade(servicoTipo.getId() + " - " + servicoTipo.getDescricao());
					atividadeBeans.put(servicoTipo.getId(), atividadeBean);
				}
				
				// Verificar se a situação dessa OrdemServico é encerrada
				boolean encerrado = false;
				if (ordem.getSituacao() == ConstantesSistema.SITUACAO_REFERENCIA_ENCERRADA) {
					encerrado = true;
					// em alguns dados antigos do bd nao ha a data de encerramento, ignorar
					if (ordem.getDataEncerramento() == null) continue;
				}
				
				// Verificar se essa OrdemServico esta no prazo
				ServicoTipoPrioridade prioridade = ordem.getServicoTipoPrioridadeAtual();
				boolean noPrazo = false;
				// A data limite é a data de geração + o prazo em horas da prioridade
				Date limite = new Date(ordem.getDataGeracao().getTime() + 
						prioridade.getPrazoExecucaoFim() * 1000 * 60 * 60);
				if (encerrado) {
					// Se ordem é encerrado comparar a Data de Encerramento com o limite
					if (ordem.getDataEncerramento().getTime() < limite.getTime()) 
						noPrazo = true;
				} else {
					// Se ordem é pendente comparar a Data atual com o limite
					if (new Date().getTime() < limite.getTime())
						noPrazo = true;
				}

				// NIVEL 0
				if (prioridade.getId().equals(1)) {
					if (encerrado) {
						if (noPrazo) {
							atividadeBean.setEncerradoNoPrazoNivel0(atividadeBean.getEncerradoNoPrazoNivel0() +1);
							totalUnidadeBean.setEncerradoNoPrazoNivel0(totalUnidadeBean.getEncerradoNoPrazoNivel0() +1);
						} else {
							atividadeBean.setEncerradoForaPrazoNivel0(atividadeBean.getEncerradoForaPrazoNivel0() +1);
							totalUnidadeBean.setEncerradoForaPrazoNivel0(totalUnidadeBean.getEncerradoForaPrazoNivel0() +1);
						}
					} else {
						if (noPrazo) {
							atividadeBean.setPendenteNoPrazoNivel0(atividadeBean.getPendenteNoPrazoNivel0() +1);
							totalUnidadeBean.setPendenteNoPrazoNivel0(totalUnidadeBean.getPendenteNoPrazoNivel0() +1);
						} else {
							atividadeBean.setPendenteForaPrazoNivel0(atividadeBean.getPendenteForaPrazoNivel0() +1);
							totalUnidadeBean.setPendenteForaPrazoNivel0(totalUnidadeBean.getPendenteForaPrazoNivel0() +1);
						}
					}
				} 
				
				// NIVEL 1
				else if (prioridade.getId().equals(2)) {
					if (encerrado) {
						if (noPrazo) {
							atividadeBean.setEncerradoNoPrazoNivel1(atividadeBean.getEncerradoNoPrazoNivel1() +1);
							totalUnidadeBean.setEncerradoNoPrazoNivel1(totalUnidadeBean.getEncerradoNoPrazoNivel1() +1);
						} else {
							atividadeBean.setEncerradoForaPrazoNivel1(atividadeBean.getEncerradoForaPrazoNivel1() +1);
							totalUnidadeBean.setEncerradoForaPrazoNivel1(totalUnidadeBean.getEncerradoForaPrazoNivel1() +1);
						}
					} else {
						if (noPrazo) {
							atividadeBean.setPendenteNoPrazoNivel1(atividadeBean.getPendenteNoPrazoNivel1() +1);
							totalUnidadeBean.setPendenteNoPrazoNivel1(totalUnidadeBean.getPendenteNoPrazoNivel1() +1);
						} else {
							atividadeBean.setPendenteForaPrazoNivel1(atividadeBean.getPendenteForaPrazoNivel1() +1);
							totalUnidadeBean.setPendenteForaPrazoNivel1(totalUnidadeBean.getPendenteForaPrazoNivel1() +1);
						}
					}
					
				// NIVEL 2
				} else if (prioridade.getId().equals(3)) {
					if (encerrado) {
						if (noPrazo) {
							atividadeBean.setEncerradoNoPrazoNivel2(atividadeBean.getEncerradoNoPrazoNivel2() +1);
							totalUnidadeBean.setEncerradoNoPrazoNivel2(totalUnidadeBean.getEncerradoNoPrazoNivel2() +1);
						} else {
							atividadeBean.setEncerradoForaPrazoNivel2(atividadeBean.getEncerradoForaPrazoNivel2() +1);
							totalUnidadeBean.setEncerradoForaPrazoNivel2(totalUnidadeBean.getEncerradoForaPrazoNivel2() +1);
						}
					} else {
						if (noPrazo) {
							atividadeBean.setPendenteNoPrazoNivel2(atividadeBean.getPendenteNoPrazoNivel2() +1);
							totalUnidadeBean.setPendenteNoPrazoNivel2(totalUnidadeBean.getPendenteNoPrazoNivel2() +1);
						} else {
							atividadeBean.setPendenteForaPrazoNivel2(atividadeBean.getPendenteForaPrazoNivel2() +1);
							totalUnidadeBean.setPendenteForaPrazoNivel2(totalUnidadeBean.getPendenteForaPrazoNivel2() +1);
						}
					}
					
				// NIVEL 3
				} else if (prioridade.getId().equals(4)) {
					if (encerrado) {
						if (noPrazo) {
							atividadeBean.setEncerradoNoPrazoNivel3(atividadeBean.getEncerradoNoPrazoNivel3() +1);
							totalUnidadeBean.setEncerradoNoPrazoNivel3(totalUnidadeBean.getEncerradoNoPrazoNivel3() +1);
						} else {
							atividadeBean.setEncerradoForaPrazoNivel3(atividadeBean.getEncerradoForaPrazoNivel3() +1);
							totalUnidadeBean.setEncerradoForaPrazoNivel3(totalUnidadeBean.getEncerradoForaPrazoNivel3() +1);
						}
					} else {
						if (noPrazo) {
							atividadeBean.setPendenteNoPrazoNivel3(atividadeBean.getPendenteNoPrazoNivel3() +1);
							totalUnidadeBean.setPendenteNoPrazoNivel3(totalUnidadeBean.getPendenteNoPrazoNivel3() +1);
						} else {
							atividadeBean.setPendenteForaPrazoNivel3(atividadeBean.getPendenteForaPrazoNivel3() +1);
							totalUnidadeBean.setPendenteForaPrazoNivel3(totalUnidadeBean.getPendenteForaPrazoNivel3() +1);
						}
					}
				}
			} // fim do loop OrdemServicos da Unidade
			
			// adicionando os beans de cada servico na coleção de beans dessa unidade
			Collection<RelatorioGestaoServicosUPABean> beansUnidade = new ArrayList<RelatorioGestaoServicosUPABean>();
			for (RelatorioGestaoServicosUPABean bean : atividadeBeans.values()) {
				beansUnidade.add(bean);
			}
			beansUnidades.put(unidade.getId(), beansUnidade);
		}
		
		
		// ------------------------------
		// -- Criacao das Linhas do Relatorio
		// ------------------------------
		
		// criar o relatorio com as paginas das unidades começando da suas filhas, e pondo um 
		// total de resumo de atividades para cada unidade superior que aparecer no loop
		
		for (int idUnidade : unidades.keySet()) {
			UnidadeOrganizacional unidade = unidades.get(idUnidade);
			String descricaoUnidade = unidade.getId() + " - " + unidade.getDescricao();
			
			// adicionar as linhas da pagina da unidade
			Collection<RelatorioGestaoServicosUPABean> beansUnidade = beansUnidades.get(idUnidade);
			if (beansUnidade != null) {
				for (RelatorioGestaoServicosUPABean linha : beansUnidade) {
					linha.setSuperior(false);
					beans.add(linha);
				}
			}
			
			// adicionar soma total para essa unidade
			RelatorioGestaoServicosUPABean totalUnidade = totalBeansUnidades.get(idUnidade);
			adicionarTotal(beans, totalUnidade.copy(), descricaoUnidade, false);
			
			// se a unidade tiver filhos, criar uma nova pagina com resumo das atividades
			Collection<UnidadeOrganizacional> filhos = filhosDaUnidade.get(idUnidade);
			if (filhos != null) {
				descricaoUnidade += "."; // para diferenciar agrupamento do ireport
				
				// criar uma linha para total dessa unidade
				RelatorioGestaoServicosUPABean cloneSuperior = totalUnidade.copy();
				cloneSuperior.setUnidade(descricaoUnidade);
				cloneSuperior.setSuperior(true);
				if (!cloneSuperior.isEmpty()) {
					beans.add(cloneSuperior);
				}
				
				// criar uma linha para cada unidade filha
				for (UnidadeOrganizacional filho : filhos) {
					RelatorioGestaoServicosUPABean totalFilhoBean = totalBeansUnidades.get(filho.getId());
					totalFilhoBean.setUnidade(descricaoUnidade);
					totalFilhoBean.setSuperior(true);
					if (!totalFilhoBean.isEmpty()) {
						beans.add(totalFilhoBean);
					}
					
					// somar os valores da unidade superior atual com os valores do filho
					totalUnidade.sum(totalFilhoBean);
				}
				
				// adicionar linha com soma total para essa unidade
				adicionarTotal(beans, totalUnidade.copy(), descricaoUnidade, true);
			}
		}
		
		byte[] retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_GESTAO_SERVICOS_UPA,
				parametros, new RelatorioDataSource(beans), tipoRelatorio);
		
		try {
			persistirRelatorioConcluido(retorno, 
					Relatorio.RELATORIO_GESTAO_SERVICOS_UPA, getIdFuncionalidadeIniciada());
		} catch (Exception e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		
		return retorno;
	}
	
	/**
	 * Adiciona uma linha de soma total no relatorio, antes adicionando uma linha em branco
	 * 
	 * @param total Um bean com os valores totais a serem adicionados
	 * @param resumoSuperior Flag indicando se a linha vai ser para unidade superior ou nao
	 */
	public void adicionarTotal(
			List<RelatorioGestaoServicosUPABean> beans, 
			RelatorioGestaoServicosUPABean total,
			String descricaoUnidade,
			boolean resumoSuperior) {
		
		RelatorioGestaoServicosUPABean somaTotal = total.copy();
		somaTotal.setUnidade(descricaoUnidade);
		somaTotal.setSuperior(resumoSuperior);
		
		RelatorioGestaoServicosUPABean linhaEmBranco = new RelatorioGestaoServicosUPABean();
		linhaEmBranco.setUnidade(descricaoUnidade);
		linhaEmBranco.setAtividade("");
		linhaEmBranco.setNull();
		linhaEmBranco.setSuperior(resumoSuperior);
		
		if (resumoSuperior) {
			somaTotal.setAtividade("TOTAL POR UNIDADE SUPERIOR");
		} else {
			somaTotal.setAtividade("TOTAL POR UNIDADE");
		}
		
		if (!somaTotal.isEmpty()) {
			beans.add(linhaEmBranco);
			beans.add(somaTotal);
		}
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 1;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioGestaoServicosUPA", this);
	}
	
	
	
}
