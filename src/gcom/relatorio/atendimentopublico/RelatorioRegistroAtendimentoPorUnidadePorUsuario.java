package gcom.relatorio.atendimentopublico;

import gcom.atendimentopublico.registroatendimento.bean.FiltrarRelatorioRegistroAtendimentoPorUnidadePorUsuarioHelper;
import gcom.atendimentopublico.registroatendimento.bean.RAPorUnidadePorUsuarioHelper;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
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

public class RelatorioRegistroAtendimentoPorUnidadePorUsuario extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;

	public RelatorioRegistroAtendimentoPorUnidadePorUsuario(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_REGISTRO_ATENDIMENTO_POR_UNIDADE_POR_USUARIO);
	}
	
	@Deprecated
	public RelatorioRegistroAtendimentoPorUnidadePorUsuario() {
		super(null, "");
	}

	@Override
	public Object executar() throws TarefaException {
		Fachada fachada = Fachada.getInstancia();
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		Short situacao = (Short) getParametro("situacao");
		Date dataAtendimentoInicial = (Date) getParametro("dataAtendimentoInicial");
		Date dataAtendimentoFinal = (Date) getParametro("dataAtendimentoFinal");
		Integer idUnidadeAtendimento = (Integer) getParametro("idUnidadeAtendimento");
		Integer tipoRelatorio = (Integer) getParametro("tipoRelatorio");
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		UnidadeOrganizacional unidadeOrganizacional = null;
		
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
		if (idUnidadeAtendimento != null) {
			FiltroUnidadeOrganizacional filtroUnidade = new FiltroUnidadeOrganizacional();
			filtroUnidade.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, idUnidadeAtendimento));
			
			Collection pesquisa = fachada.pesquisar(filtroUnidade, UnidadeOrganizacional.class.getName());
			if (pesquisa == null || pesquisa.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Unidade Organizacional");
			}
			
			unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(pesquisa);
			parametroUnidadeOrganizacional = unidadeOrganizacional.getDescricao();
		}
		
		// ------------------------------
		// -- Data atendimento
		// ------------------------------
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		String parametroData = "de " + f.format(dataAtendimentoInicial) + " a " + f.format(dataAtendimentoFinal);
		
		// ------------------------------
		// -- Parametros
		// ------------------------------
		parametros.put("situacao", parametroSituacao);
		parametros.put("dataAtendimento", parametroData);
		parametros.put("especificacoes", "TODOS");
		parametros.put("unidadeAtendimento", parametroUnidadeOrganizacional);
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("tipoRelatorio", "R0497");
		
		
		
		// ------------------------------
		// -- Estruturas de Dados
		// ------------------------------
		
		/*
		 * Hash ordenada Id da UnidadeOrganizacional -> UnidadeOrganizacional
		 * Os ids estão ordenados de modo que uma unidade filha aparece sempre antes de sua superior
		 */
		LinkedHashMap<Integer, Usuario> usuarios = new LinkedHashMap<Integer, Usuario>();
		
		/*
		 * Hash Id da UnidadeOrganizacional -> Colecao de Unidades Filhas dela
		 */
		Map<Integer, Collection<Usuario>> usuariosDaUnidade = new HashMap<Integer, Collection<Usuario>>();
		
		/*
		 * Hash Id da UnidadeOrganizacional -> Colecao de OrdensDeServico
		 *  cuja usuarioAtual possui o Id igual a chave
		 */
		Map<Integer, Collection<RAPorUnidadePorUsuarioHelper>> registrosDoUsuario = new HashMap<Integer, Collection<RAPorUnidadePorUsuarioHelper>>();
		
		
		
		// ------------------------------
		// -- Realizar Pesquisa
		// ------------------------------
		
		/*if (idUnidadeAtendimento != null) {
			usuarios.put(idUnidadeAtendimento, unidadeOrganizacional);
			//usuariosDaUnidade.put(idUnidadeAtendimento, null);
		}
		
		Collection<Integer> idsUnidadeAtual = new ArrayList<Integer>();
		for (UnidadeOrganizacional u : usuarios.values()) {
			idsUnidadeAtual.add(u.getId());
		}*/

		Collection<Integer> idsUnidadeAtual = new ArrayList<Integer>();
		/**TODO:COSANPA
		 * @author Adriana Muniz
		 * @Date 07/08/2013
		 * Verificação se o atributo é nulo antes de adicioná-lo ao array
		 * */
		if(idUnidadeAtendimento != null)
			idsUnidadeAtual.add(idUnidadeAtendimento);
		
		FiltrarRelatorioRegistroAtendimentoPorUnidadePorUsuarioHelper filtro = new FiltrarRelatorioRegistroAtendimentoPorUnidadePorUsuarioHelper();
		if (situacao != null) filtro.setSituacao(situacao);
		filtro.setDataAtendimentoInicial(dataAtendimentoInicial);
		filtro.setDataAtendimentoFinal(dataAtendimentoFinal);
		filtro.setIdsUnidadeAtual(idsUnidadeAtual);
		
		List<RAPorUnidadePorUsuarioHelper> pesquisa = fachada.filtrarRelatorioRegistroAtendimentoPorUnidadePorUsuario(filtro);
		if (pesquisa == null || pesquisa.isEmpty()) {
			throw new ActionServletException("atencao.relatorio.vazio");
		}
		
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		if(idUnidadeAtendimento != null)
			filtroUsuario.adicionarParametro( new ParametroSimples( FiltroUsuario.UNIDADE_ORGANIZACIONAL_ID, idUnidadeAtendimento));
		Collection<Usuario> colecaoUsuario = fachada.pesquisar(filtroUsuario, 
				Usuario.class.getName() );
		
		for (Usuario usur : colecaoUsuario) {
			usuarios.put(usur.getId(), usur);
		}
		
				
		// Separando os RegistrosAtendimento pesquisados por usuario
		for (RAPorUnidadePorUsuarioHelper registro : pesquisa) {
			if (registro.getIdUsuarioAtendimento() != null) {
				Integer idUsuarioAtendimento = registro.getIdUsuarioAtendimento();
				if (registrosDoUsuario.containsKey(idUsuarioAtendimento)) {
					registrosDoUsuario.get(idUsuarioAtendimento).add(registro);
				} else {
					List<RAPorUnidadePorUsuarioHelper> list = new ArrayList<RAPorUnidadePorUsuarioHelper>();
					list.add(registro);
					registrosDoUsuario.put(idUsuarioAtendimento, list);
				}				
			}
		}
		
		
		
		// ------------------------------
		// -- Processando Linhas do Relatório
		// ------------------------------
		
		List<RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean> beans = new ArrayList<RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean>();
		
		// hash id da UnidadeOrganizacional -> coleção de beans da sua pagina de relatorio
		Map<Integer, Collection<RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean>> beansUsuario =
			new HashMap<Integer, Collection<RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean>>();
		
		// hash id UnidadeOrganizacional -> um bean com a soma total dos beans dessa unidade
		Map<Integer, RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean> totalBeansUsuario = 
			new HashMap<Integer, RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean>();
		
		// ------------------------------
		// -- Processando Dados para Cada Unidade
		// ------------------------------
		
		for (int idUsuario : usuarios.keySet()) {
			Usuario usuario = usuarios.get(idUsuario);
			
			// cria um bean para a soma total dessa unidade e armazena na hash
			RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean totalUsuarioBean = new RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean();
			totalUsuarioBean.setEspecificacao("TOTAL DO USUÁRIO: ");
			totalBeansUsuario.put(usuario.getId(), totalUsuarioBean);
			
			// hash id SolicitacaoTipoEspecificacao -> soma total para essa especificacao (para esse usuario)
			Map<Integer, RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean> especificacaoBeans =
				new HashMap<Integer, RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean>();
			
			// se não existirem registros para esse usuario parar
			if (!registrosDoUsuario.containsKey(usuario.getId())) continue;
			
			// para cada registro de atendimento do usuario atual
			for (RAPorUnidadePorUsuarioHelper registro : registrosDoUsuario.get(usuario.getId())) {
				
				// Pega da hash o bean para a soma dos valores para essa especificacao desse Usuario
				RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean especificacaoBean = null;
				if (especificacaoBeans.containsKey(registro.getIdSolicitacaoTipoEspecificacao())) {
					especificacaoBean = especificacaoBeans.get(registro.getIdSolicitacaoTipoEspecificacao());
				} else {
					especificacaoBean = new RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean();
					especificacaoBean.setUsuario(usuario.getId() + " - " + usuario.getNomeUsuario());
					especificacaoBean.setUnidade(usuario.getId() + " - " + usuario.getNomeUsuario());
					especificacaoBean.setEspecificacao(registro.getDescricaoSolicitacaoTipoEspecificacao());
					especificacaoBeans.put(registro.getIdSolicitacaoTipoEspecificacao(), especificacaoBean);
				}
				
				// quantidade solicitada
				especificacaoBean.setQuantidadeSolicitada(especificacaoBean.getQuantidadeSolicitada() +1);
				totalUsuarioBean.setQuantidadeSolicitada(totalUsuarioBean.getQuantidadeSolicitada() +1);
				
			}
			
			// adicionando os beans de cada especificação na coleção desse usuario
			List<RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean> beansDoUsuario = new ArrayList<RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean>();
			for (RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean bean : especificacaoBeans.values()) {
				beansDoUsuario.add(bean);
			}
			//beansDoUsuario.add(totalUsuarioBean);
			beansUsuario.put(usuario.getId(), beansDoUsuario);
		}
		
		
		//******************************************************************************
		Map<Integer, RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean> especificacaoBeansUnidade =
			new HashMap<Integer, RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean>();
		
		RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean totalUnidadeBean = new RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean();
		totalUnidadeBean.setEspecificacao("TOTAL DA UNIDADE: ");
		totalBeansUsuario.put(0, totalUnidadeBean);
		
		for (int idUsuario : usuarios.keySet()) {
			Usuario usuario = usuarios.get(idUsuario);
			
			// cria um bean para a soma total dessa unidade e armazena na hash
			
			
			// hash id SolicitacaoTipoEspecificacao -> soma total para essa especificacao (para essa unidade)
			
			
			// se não existirem registros para esse usuario parar
			if (!registrosDoUsuario.containsKey(usuario.getId())) continue;
			
			// para cada registro de atendimento do usuario atual
			for (RAPorUnidadePorUsuarioHelper registro : registrosDoUsuario.get(usuario.getId())) {
				
				// Pega da hash o bean para a soma dos valores para essa especificacao desse Usuario
				RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean especificacaoBean = null;
				if (especificacaoBeansUnidade.containsKey(registro.getIdSolicitacaoTipoEspecificacao())) {
					especificacaoBean = especificacaoBeansUnidade.get(registro.getIdSolicitacaoTipoEspecificacao());
				} else {
					especificacaoBean = new RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean();
					especificacaoBean.setUsuario("Total da Unidade");
					especificacaoBean.setUnidade("Total da Unidade");
					especificacaoBean.setEspecificacao(registro.getDescricaoSolicitacaoTipoEspecificacao());
					especificacaoBeansUnidade.put(registro.getIdSolicitacaoTipoEspecificacao(), especificacaoBean);
				}
				
				// quantidade solicitada
				especificacaoBean.setQuantidadeSolicitada(especificacaoBean.getQuantidadeSolicitada() +1);
				totalUnidadeBean.setQuantidadeSolicitada(totalUnidadeBean.getQuantidadeSolicitada() +1);
				
			}
			
			// adicionando os beans de cada especificação na coleção desse usuario
			List<RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean> beansDoUsuario = new ArrayList<RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean>();
			for (RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean bean : especificacaoBeansUnidade.values()) {
				beansDoUsuario.add(bean);
			}
			//beansDoUsuario.add(totalUsuarioBean);
			beansUsuario.put(0, beansDoUsuario);
		}
		
		
		
		// ------------------------------
		// -- Criacao das Linhas do Relatorio
		// ------------------------------
		
		// criar o relatorio com as paginas das unidades começando da suas filhas, e pondo um 
		// total de resumo de atividades para cada unidade superior que aparecer no loop
		
		for (int idUsuario : usuarios.keySet()) {
			Usuario usuario = usuarios.get(idUsuario);
			String descricaoUsuario = usuario.getId() + " - " + usuario.getNomeUsuario();
			
			// adicionar as linhas da pagina da unidade
			Collection<RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean> beansUnidade = beansUsuario.get(idUsuario);
			if (beansUnidade != null) {
				for (RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean linha : beansUnidade) {
					//linha.setSuperior(false);
					beans.add(linha);}
			}
			
			// adicionar soma total para essa unidade
			RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean totalUnidade = totalBeansUsuario.get(idUsuario);
			adicionarTotal(beans, totalUnidade.copy(), descricaoUsuario, false);
			
			// se a unidade tiver filhos, criar uma nova pagina com resumo das atividades
			/*Collection<Usuario> filhos = usuariosDaUnidade.get(idUsuario);
			if (filhos != null) {
				descricaoUsuario += "."; // para diferenciar agrupamento do ireport
				
				// criar uma linha para total dessa unidade
				RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean cloneSuperior = totalUnidade.copy();
				cloneSuperior.setUsuario(descricaoUsuario);
				//cloneSuperior.setSuperior(true);
				if (!cloneSuperior.isEmpty()) {
					beans.add(cloneSuperior);
				}
				
				// criar uma linha para cada unidade filha
				for (Usuario filho : filhos) {
					RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean totalFilhoBean = totalBeansUsuario.get(filho.getId());
					totalFilhoBean.setUsuario(descricaoUsuario);
					//totalFilhoBean.setSuperior(true);
					if (!totalFilhoBean.isEmpty()) {
						beans.add(totalFilhoBean);
					}
					
					// somar os valores da unidade superior atual com os valores do filho
					totalUnidade.sum(totalFilhoBean);
				}
				
				// adicionar linha com soma total para essa unidade
				adicionarTotal(beans, totalUnidade.copy(), descricaoUsuario, true);
			}*/
		}
		
		String descricaoUsuario = "Total da Unidade";
		
		// adicionar as linhas da pagina da unidade
		Collection<RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean> beansUnidade = beansUsuario.get(new Integer("0"));
		if (beansUnidade != null) {
			for (RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean linha : beansUnidade) {
				//linha.setSuperior(false);
				beans.add(linha);}
		}
		
		adicionarTotal(beans, totalUnidadeBean.copy(), descricaoUsuario, true);
		
		byte[] retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_REGISTRO_ATENDIMENTO_POR_UNIDADE_POR_USUARIO,
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
			List<RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean> beans, 
			RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean total,
			String descricaoUsuario,
			boolean resumoSuperior) {
		
		RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean somaTotal = total.copy();
		somaTotal.setUsuario(descricaoUsuario);
		somaTotal.setUnidade(descricaoUsuario);
		//somaTotal.setSuperior(resumoSuperior);
		
		RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean linhaEmBranco = new RelatorioRegistroAtendimentoPorUnidadePorUsuarioBean();
		linhaEmBranco.setUsuario(descricaoUsuario);
		linhaEmBranco.setUnidade(descricaoUsuario);
		linhaEmBranco.setEspecificacao("");
		linhaEmBranco.setNull();
		
		//linhaEmBranco.setSuperior(resumoSuperior);
		
		if (resumoSuperior) {
			somaTotal.setEspecificacao("TOTAL DA UNIDADE: ");
		} else {
			somaTotal.setEspecificacao("TOTAL DO USUARIO: ");
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
		AgendadorTarefas.agendarTarefa("RelatorioRegistroAtendimentoPorUnidadePorUsuario", this);
	}


}
