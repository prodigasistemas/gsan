package gcom.relatorio.cobranca.spcserasa;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.NegativadorMovimentoRetornoResumoHelper;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesExecucaoRelatorios;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Yara Taciane
 * @created 23 de Abril de 2008
 * @version 1.0
 */

public class RelatorioNegativadorMovimentoRetornoResumo extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the RelatorioNegativadorMovimentoRetornoResumo object
	 */
	public RelatorioNegativadorMovimentoRetornoResumo(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_2_VIA_CONTA);
	}

	@Deprecated
	public RelatorioNegativadorMovimentoRetornoResumo() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param NegativadorRegistroTipoParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */

	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		// Recebe os parâmetros que serão utilizados no relatório		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		NegativadorMovimentoRetornoResumoHelper helper = (NegativadorMovimentoRetornoResumoHelper) getParametro("parametros");
	

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório

		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioNegativadorMovimentoRetornoResumoBean relatorioBean = null;

		// Cria adiciona os carregamentos dos objetos necessários para
		// a impressão do relatório
		

		// Nova consulta para trazer objeto completo		

	
			// coloca a coleção de parâmetros da analise no iterator
		
				
				// Faz as validações dos campos necessáriose e formata a String
				// para a forma como irá aparecer no relatório

		  
				String nomeNegativador = "";				
				if (helper.getNomeNegativador() != null) {
					nomeNegativador = helper.getNomeNegativador();
				}
				
			
				String dataProcessamento = "";				
				if (helper.getDataProcessamento() != null) {
					dataProcessamento = helper.getDataProcessamento();
				}
						
				String horaProcessamento = "";				
				if (helper.getHoraProcessamento() != null) {
					horaProcessamento = helper.getHoraProcessamento();
				}

		
				String numeroSequencialArquivo = "";				
				if (helper.getNumeroSequencialArquivo() != null) {
					numeroSequencialArquivo = helper.getNumeroSequencialArquivo();
				
				}
				
				String numeroRegistros = "";				
				if (helper.getNumeroRegistros() != null) {
					numeroRegistros = helper.getNumeroRegistros();
				
				}
				

				// Inicializa o construtor constituído dos campos
				// necessários para a impressão do relatorio
				relatorioBean = new RelatorioNegativadorMovimentoRetornoResumoBean(
					
						nomeNegativador,
						
						dataProcessamento,
						
						horaProcessamento,
					
						numeroSequencialArquivo,
						
						numeroRegistros);

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			
	

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());	

		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource((List) relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_2_VIA_CONTA, parametros, ds,
				tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_BOLETIM_CADASTRO,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = ConstantesExecucaoRelatorios.QUANTIDADE_NAO_INFORMADA;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioNegativadorMovimentoRetornoResumo", this);
	}

}
