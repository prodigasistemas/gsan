package gcom.relatorio.micromedicao;

import gcom.batch.Relatorio;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

/**
 * [UC1054] Gerar Relatório Boletim de Medição
 * [SB0002 - Gerar TXT]
 * 
 * @autor Mariana Victor
 * @data 23/02/2011
 */
public class RelatorioBoletimMedicaoArquivoTxt extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioBoletimMedicaoArquivoTxt(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_BOLETIM_MEDICAO_ARQUIVO_TXT);
	}

	@Deprecated
	public RelatorioBoletimMedicaoArquivoTxt() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 */
	public Object executar() throws TarefaException {

		// valor de retorno
		byte[] retorno = null;

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltrarRelatorioBoletimMedicaoHelper relatorioHelper = 
			(FiltrarRelatorioBoletimMedicaoHelper) getParametro("filtrarRelatorioBoletimMedicaoHelper");
		
		String mesAno = (String)getParametro("mesAno");
		
		Fachada fachada = Fachada.getInstancia();

		Collection colecao = fachada.pesquisarRelatorioBoletimMedicao(relatorioHelper);
		
		retorno = fachada.emitirBoletimMedicao(colecao, mesAno);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_BOLETIM_MEDICAO_ARQUIVO_TXT,
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
		
		int retorno = 2;
   
		if (retorno == 0) {
			// Caso a pesquisa não retorne nenhum resultado comunica ao
			// usuário;
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}
		
		return retorno;		
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioBoletimMedicaoArquivoTxt", this);
	}

}
