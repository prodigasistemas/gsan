package gcom.relatorio.financeiro;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.contasareceber.RelatorioParametrosContabeisContasAReceberBean;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Gerar Relatório dos Parametros Contabeis
 * 
 * @author Reinaldo Viana
 * @data 28/08/2015
 */

public class RelatorioParametrosContabeisContasAReceber extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioParametrosContabeisContasAReceber(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_PARAMETROS_CONTABEIS_CONTAS_A_RECEBER);
	}
	
	@Deprecated
	public RelatorioParametrosContabeisContasAReceber() {
		super(null, "");
	}
	
	/**
	 * Método que executa a tarefa
	 * 
	 * @return Object
	 */
	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

        String referenciaContabil = (String) getParametro("referenciaContabil");
        int tipoRelatorio = (Integer) getParametro("tipoRelatorio");
        
		// valor de retorno
		byte[] retorno = null;

		// Parâmetros do relatório
		Map parametros = new HashMap();

		Fachada fachada = Fachada.getInstancia();

		Collection<RelatorioParametrosContabeisContasAReceberBean> colecaoBean = 
            fachada.pesquisarDadosRelatorioParametrosContabeisContasAReceber( referenciaContabil );			

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// Não existem dados para a exibição do relatório.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
        parametros.put("mesAno", referenciaContabil);

		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_PARAMETROS_CONTABEIS_CONTAS_A_RECEBER, parametros,
				ds, tipoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_PARAMETROS_CONTABEIS, idFuncionalidadeIniciada);
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
		return 0;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioParametrosContabeisContasAReceber", this);
	}
}
