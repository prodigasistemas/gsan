package gcom.relatorio.atendimentopublico;

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

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**[UC1177] Gerar Relatório de Ordens de Serviço por Situação
 * 
 * Classe Responsável por gerar o Relatório Acompanhamento Boletim Medição
 * 
 * @author Diogo Peixoto
 * @since 17/06/2011
 *
 */
public class RelatorioAcompanhamentoBoletimMedicao extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;

	public RelatorioAcompanhamentoBoletimMedicao(Usuario usuario, String nomeRelatorio) {
		super(usuario, nomeRelatorio);
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 1;
	}

	@Override
	public Object executar() throws TarefaException {
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		
		RelatorioAcompanhamentoBoletimMedicaoHelper helperRelatorio = (RelatorioAcompanhamentoBoletimMedicaoHelper)getParametro("dadosRelatorio");
		
		if(helperRelatorio == null || Util.isVazioOrNulo(helperRelatorio.getBeans())){
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}
		
		Collection<RelatorioAcompanhamentoBoletimMedicaoBean> beans = helperRelatorio.getBeans();
		
		Integer tipoFormatoRelatorio = (Integer) getParametro("tipoRelatorio");
		String definitivoSimulacao = (String) getParametro("definitivoSimulacao");
		String nomeEmpresa = (String) getParametro("nomeEmpresa");
		String numeroContrato = (String) getParametro("numeroContrato");
		String dataReferencia = (String) getParametro("dataReferencia");
		
		// Parâmetros do relatório
		Map<String, Object> parametros = new HashMap<String, Object>();
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("numeroRelatorio", "R1178");
		
		parametros.put("tipoRelatorio", tipoFormatoRelatorio);
		parametros.put("definitivoSimulacao", definitivoSimulacao);
		parametros.put("nomeEmpresa", nomeEmpresa);
		parametros.put("numeroContrato", Integer.valueOf(numeroContrato));
		parametros.put("anoMesReferencia", dataReferencia);
		
		parametros.put("taxaSucesso", helperRelatorio.getTaxaSucesso());
		parametros.put("penalidadeOS", helperRelatorio.getPenalidadeOS());
		parametros.put("penalidadeFiscalizacao", helperRelatorio.getPenalidadeFiscalizacao());
		parametros.put("penalidadeNaoExecucao", helperRelatorio.getPenalidadeContratoNaoExecucao());
		parametros.put("penalidadeCorteSupressao", helperRelatorio.getPenalidadeCorteSupressaoIndevida());
		parametros.put("penalidadeNaoRealizacaoServicos", helperRelatorio.getPenalidadeNaoRealizacaoServico());
		parametros.put("valorDesconto", helperRelatorio.getValorDesconto());
		parametros.put("definitivoSimulacao", helperRelatorio.getTipoRelatorio());
		
		byte[] retorno = null;

		RelatorioDataSource ds = new RelatorioDataSource((List<RelatorioAcompanhamentoBoletimMedicaoBean>) beans);
		try {
			retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_BOLETIM_MEDICAO, parametros, ds, tipoFormatoRelatorio);
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_ACOMPANHAMENTO_BOLETIM_MEDICAO, idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioAcompanhamentoBoletimMedicao", this);
	}
}
