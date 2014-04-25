package gcom.relatorio.operacional;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.operacional.Bacia;
import gcom.operacional.FiltroBacia;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Arthur Carvalho
 * @version 1.0
 */

public class RelatorioManterBacia extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterBacia(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_BACIA_MANTER);
	}
	
	@Deprecated
	public RelatorioManterBacia() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param situacao pagamento
	 *            Description of the Parameter
	 * @param SituacaoPagamentoParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */

	public Object executar() throws TarefaException {

		// ------------------------------------
		//		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltroBacia filtroBacia = (FiltroBacia) getParametro("filtroBacia");
		Bacia baciaParametros = (Bacia) getParametro("baciaParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		filtroBacia.adicionarCaminhoParaCarregamentoEntidade(FiltroBacia.SISTEMA_ESGOTO);
		
		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioManterBaciaBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoBacia= fachada.pesquisar(filtroBacia,
				Bacia.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoBacia != null && !colecaoBacia.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator baciaIterator = colecaoBacia.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (baciaIterator.hasNext()) {

				Bacia bacia = (Bacia) baciaIterator.next();
				relatorioBean = new RelatorioManterBaciaBean(
						// ID
						bacia.getId().toString(), 
						
						// Descrição
						bacia.getDescricao(), 
						
						// Sistema Esgoto
						bacia.getSistemaEsgoto().getDescricao(),
						
						bacia.getIndicadorUso().toString());
						
				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}
		}

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("id",
				baciaParametros.getId() == null ? "" : ""
						+ baciaParametros.getId());

		parametros.put("descricao", baciaParametros.getDescricao());
		
		if (baciaParametros.getSistemaEsgoto() != null) {
			parametros.put("sistemaEsgoto", baciaParametros.getSistemaEsgoto().getDescricao());
		} else {
			parametros.put("sistemaEsgoto", "");	
		}
		
		
		String indicadorUso = "";

		if (baciaParametros.getIndicadorUso() != null
				&& !baciaParametros.getIndicadorUso().equals("")) {
			if (baciaParametros.getIndicadorUso().equals(new Short("1"))) {
				indicadorUso = "Ativo";
			} else if (baciaParametros.getIndicadorUso().equals(
					new Short("2"))) {
				indicadorUso = "Inativo";
			}
		}

		parametros.put("indicadorUso", indicadorUso);

		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_BACIA_MANTER, parametros,
				ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
//		try {
//			persistirRelatorioConcluido(retorno, Relatorio.MANTER_LOCALIDADE,
//					idFuncionalidadeIniciada);
//		} catch (ControladorException e) {
//			e.printStackTrace();
//			throw new TarefaException("Erro ao gravar relatório no sistema", e);
//		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

//		retorno = Fachada.getInstancia().totalRegistrosPesquisa(
//				(FiltroLocalidade) getParametro("filtroLocalidade"),
//				Localidade.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterBacia", this);
	}

}
