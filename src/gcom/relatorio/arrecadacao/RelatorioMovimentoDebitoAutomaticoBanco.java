package gcom.relatorio.arrecadacao;

import gcom.arrecadacao.bean.GerarMovimentoDebitoAutomaticoBancoHelper;
import gcom.batch.Relatorio;
import gcom.cadastro.geografico.FiltroBairro;
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
public class RelatorioMovimentoDebitoAutomaticoBanco extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	/**
	 * Construtor da classe RelatorioAnaliseFisicoQuimicaAgua
	 */
	public RelatorioMovimentoDebitoAutomaticoBanco(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MOVIMENTO_DEBITO_AUTOMATICO_BANCO);
	}
	
	@Deprecated
	public RelatorioMovimentoDebitoAutomaticoBanco() {
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

		Collection colecaoMovimentoDebitoAutomaticoBanco = (Collection) getParametro("colecaoGerarMovimentoDebitoAutomatico");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		RelatorioMovimentoDebitoAutomaticoBancoBean relatorioMovimentoDebitoAutomaticoBancoBean = null;
		// se a coleção de parâmetros da analise não for vazia
		if (colecaoMovimentoDebitoAutomaticoBanco != null
				&& !colecaoMovimentoDebitoAutomaticoBanco.isEmpty()) {
			// coloca a coleção de parâmetros do Movimento Debito Automatico
			// Banco no iterator
			Iterator movimentoDebitoAutomaticoBancoIterator = colecaoMovimentoDebitoAutomaticoBanco
					.iterator();

			FiltroBairro filtroBairro = new FiltroBairro();
			filtroBairro.adicionarCaminhoParaCarregamentoEntidade("municipio");

			// laço para criar a coleção de parâmetros da analise
			while (movimentoDebitoAutomaticoBancoIterator.hasNext()) {

				GerarMovimentoDebitoAutomaticoBancoHelper gerarMovimentoDebitoAutomaticoBancoHelper = (GerarMovimentoDebitoAutomaticoBancoHelper) movimentoDebitoAutomaticoBancoIterator
						.next();

				relatorioMovimentoDebitoAutomaticoBancoBean = new RelatorioMovimentoDebitoAutomaticoBancoBean(
						""
								+ gerarMovimentoDebitoAutomaticoBancoHelper
										.getBanco().getId(),
						gerarMovimentoDebitoAutomaticoBancoHelper.getBanco()
								.getDescricao(),
						""
								+ gerarMovimentoDebitoAutomaticoBancoHelper
										.getArrecadadorMovimento()
										.getNumeroSequencialArquivo(),
						""
								+ gerarMovimentoDebitoAutomaticoBancoHelper
										.getArrecadadorMovimento()
										.getNumeroRegistrosMovimento(),
						Util
								.formatarMoedaReal(gerarMovimentoDebitoAutomaticoBancoHelper
										.getArrecadadorMovimento()
										.getValorTotalMovimento()),
						gerarMovimentoDebitoAutomaticoBancoHelper
								.getDescricaoEmail(),
						gerarMovimentoDebitoAutomaticoBancoHelper
								.getSituacaoEnvioEmail());

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioMovimentoDebitoAutomaticoBancoBean);
			}
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_MOVIMENTO_DEBITO_AUTOMATICO_BANCO, parametros,
				ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.MOVIMENTO_DEBITO_AUTOMATICO_BANCO,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}

	public int calcularTotalRegistrosRelatorio() {

		int retorno = 0;

		if (getParametro("colecaoGerarMovimentoDebitoAutomatico") != null
				&& getParametro("colecaoGerarMovimentoDebitoAutomatico") instanceof Collection) {
			retorno = ((Collection) getParametro("colecaoGerarMovimentoDebitoAutomatico")).size();
		}
		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioMovimentoDebitoAutomaticoBanco", this);
	}

}
