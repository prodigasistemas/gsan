package gcom.relatorio.arrecadacao.pagamento;

import gcom.arrecadacao.pagamento.FiltroPagamentoSituacao;
import gcom.arrecadacao.pagamento.PagamentoSituacao;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
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

public class RelatorioManterSituacaoPagamento extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterSituacaoPagamento(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_SITUACAO_PAGAMENTO_MANTER);
	}
	
	@Deprecated
	public RelatorioManterSituacaoPagamento() {
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

		FiltroPagamentoSituacao filtroPagamentoSituacao = (FiltroPagamentoSituacao) getParametro("filtroPagamentoSituacao");
		PagamentoSituacao pagamentoSituacaoParametros = (PagamentoSituacao) getParametro("pagamentoSituacaoParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioManterSituacaoPagamentoBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoPagamentoSituacao = fachada.pesquisar(filtroPagamentoSituacao,
				PagamentoSituacao.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoPagamentoSituacao != null && !colecaoPagamentoSituacao.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator pagamentoSituacaoIterator = colecaoPagamentoSituacao.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (pagamentoSituacaoIterator.hasNext()) {

				PagamentoSituacao pagamentoSituacao = (PagamentoSituacao) pagamentoSituacaoIterator.next();

				relatorioBean = new RelatorioManterSituacaoPagamentoBean(
						// Código
						pagamentoSituacao.getId().toString(), 
						
						// Descrição
						pagamentoSituacao.getDescricao(), 
						
						// Descrição Abreviada
						pagamentoSituacao.getDescricaoAbreviada(),
						
						// Indicador de Uso
						pagamentoSituacao.getIndicadorUso().toString());

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

		parametros.put("idSituacaoPagamento",
				pagamentoSituacaoParametros.getId() == null ? "" : ""
						+ pagamentoSituacaoParametros.getId());

		parametros.put("descricao", pagamentoSituacaoParametros.getDescricao());
		parametros.put("descricaoAbreviada", pagamentoSituacaoParametros.getDescricaoAbreviada());
		
		String indicadorUso = "";

		if (pagamentoSituacaoParametros.getIndicadorUso() != null
				&& !pagamentoSituacaoParametros.getIndicadorUso().equals("")) {
			if (pagamentoSituacaoParametros.getIndicadorUso().equals(new Short("1"))) {
				indicadorUso = "Ativo";
			} else if (pagamentoSituacaoParametros.getIndicadorUso().equals(
					new Short("2"))) {
				indicadorUso = "Inativo";
			}
		}

		parametros.put("indicadorUso", indicadorUso);

		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_SITUACAO_PAGAMENTO_MANTER, parametros,
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
		AgendadorTarefas.agendarTarefa("RelatorioManterSituacaoPagamento", this);
	}

}
