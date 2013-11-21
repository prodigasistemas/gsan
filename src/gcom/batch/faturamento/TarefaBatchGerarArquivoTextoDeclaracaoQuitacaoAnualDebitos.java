package gcom.batch.faturamento;

import gcom.cadastro.empresa.Empresa;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

/**
 * [UC1008] Gerar TXT declaração de quitação anual de débitos
 * 
 * 	Este caso de uso permite a geração do TXT da declaração de quitação de débitos.
 * 
 * @author Hugo Amorim
 * @date 23/03/2010
 */
public class TarefaBatchGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos extends
		TarefaBatch {
	
	private static final long serialVersionUID = 1L;
	
	public TarefaBatchGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos(Usuario usuario,
			int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos() {
		super(null, 0);
	}
	
	@Override
	public Object executar() throws TarefaException {
		

		Integer idGrupoFaturamento = (Integer) getParametro("idGrupoFaturamento");

		Collection colecaoEmpresasParaExecucao = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);
		
		Iterator iterator = colecaoEmpresasParaExecucao.iterator();
		
		while (iterator.hasNext()) {
			
			Empresa empresa = (Empresa) iterator.next();
		
			enviarMensagemControladorBatch(
                ConstantesJNDI.BATCH_GERAR_ARQUIVO_TEXTO_DECLARACAO_QUITACAO_ANUAL_DEBITOS,
                new Object[] { 
                		this.getIdFuncionalidadeIniciada(),          
                		idGrupoFaturamento,
                		empresa});
		}
		
		return null;
	}
	
	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoBatch() {
		return null;
	}

	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoReinicioBatch() {
		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa(
				"BatchGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos", this);

	}



}
