package gcom.relatorio.arrecadacao;

import gcom.arrecadacao.ArrecadadorMovimento;
import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de bairro manter de água
 * 
 * @author Sávio Luiz
 * @created 11 de Julho de 2005
 */
public class RelatorioMovimentoArrecadador extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	/**
	 * Construtor da classe RelatorioAnaliseFisicoQuimicaAgua
	 */
	public RelatorioMovimentoArrecadador(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MOVIMENTO_ARRECADADOR);
	}

	@Deprecated
	public RelatorioMovimentoArrecadador() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param bairros
	 *            Description of the Parameter
	 * @param bairroParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */

	public Object executar() throws TarefaException {

		Fachada fachada = Fachada.getInstancia();

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		Collection arrecadadoresMovimentos = (Collection) getParametro("arrecadadoresMovimentos");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		RelatorioMovimentoArrecadadorBean relatorioMovimentoArrecadadorBean = null;
		// se a coleção de parâmetros da analise não for vazia
		if (arrecadadoresMovimentos != null
				&& !arrecadadoresMovimentos.isEmpty()) {

			Iterator iteArrecadadorMovimento = arrecadadoresMovimentos
					.iterator();

			while (iteArrecadadorMovimento.hasNext()) {

				ArrecadadorMovimento arrecadadorMovimento = (ArrecadadorMovimento) iteArrecadadorMovimento
						.next();
				relatorioMovimentoArrecadadorBean = new RelatorioMovimentoArrecadadorBean(
						"" + arrecadadorMovimento.getCodigoBanco(),
						arrecadadorMovimento.getNomeBanco(),
						"" + arrecadadorMovimento.getNumeroSequencialArquivo(),
						"" + arrecadadorMovimento.getNumeroRegistrosMovimento(),
						Util.formatarMoedaReal(arrecadadorMovimento
								.getValorTotalMovimento()),
						arrecadadorMovimento.getDescricaoIdentificacaoServico(),
						Util.formatarHoraSemData(arrecadadorMovimento
								.getUltimaAlteracao()));

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioMovimentoArrecadadorBean);
			}

		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();

		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_MOVIMENTO_ARRECADADOR,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.MOVIMENTO_ARRECADADOR, idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}

	public int calcularTotalRegistrosRelatorio() {

		int retorno = 1;
		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioMovimentoArrecadador", this);
	}

}
