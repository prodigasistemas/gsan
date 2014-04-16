package gcom.relatorio.faturamento;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Title: GCOM
 * Description: Sistema de Gestão Comercial
 * Copyright: Copyright (c) 2004
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * @author Arthur Carvalho
 * @version 1.0
 */

public class RelatorioAcrescimoPorImpontualidade extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioAcrescimoPorImpontualidade(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ACRESCIMOS_POR_IMPONTUALIDADE);
	}
	
	@Deprecated
	public RelatorioAcrescimoPorImpontualidade() {
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

		String valorMulta = (String) getParametro("valorMulta");
		String jurosDeMora = (String) getParametro("jurosDeMora");
		String atualizacaoMonetaria = (String) getParametro("atualizacaoMonetaria");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		
		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		Fachada fachada = Fachada.getInstancia();
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		//valor multa
		if (valorMulta != null
				&& !valorMulta.equals("")) {
			parametros.put("valorMulta", valorMulta);
		}
		
		//juros de mora
		if (jurosDeMora != null
				&& !jurosDeMora.equals("")) {
			parametros.put("jurosDeMora", jurosDeMora);
		}
		
		//AtualizacaoMonetaria
		if (atualizacaoMonetaria != null
				&& !atualizacaoMonetaria.equals("")) {
			parametros.put("atualizacaoMonetaria", atualizacaoMonetaria);
		}
		relatorioBeans.add(1);
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_ACRESCIMOS_POR_IMPONTUALIDADE, parametros,
				ds, tipoFormatoRelatorio);
		
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioAcrescimoPorImpontualidade", this);
	}

}
