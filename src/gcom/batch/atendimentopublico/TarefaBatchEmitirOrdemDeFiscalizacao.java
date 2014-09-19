package gcom.batch.atendimentopublico;

import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

/**
 * [UC0996] Emitir Ordem de Fiscalização para imóveis suprimidos
 * 
 *  Este caso de uso permite realizar a recuperação das informações dos imóveis que estejam com seus ramais surprimidos,
 *  parcialmente ou totalmente, por um período pré-determinado e os armazena em uma base de dados
 * 	para uma posterior geração de arquivo de texto.
 * 
 * 
 * @author Hugo Amorim
 * @date 08/03/2010
 * 
 */
public class TarefaBatchEmitirOrdemDeFiscalizacao extends TarefaBatch {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TarefaBatchEmitirOrdemDeFiscalizacao(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchEmitirOrdemDeFiscalizacao() {
		super(null, 0);
	}
	
	@Override
	public Object executar() throws TarefaException {
		Usuario usuarioLogado = this.getUsuario();
		
		Collection colecaoSetores = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);
		SistemaParametro sistemaParametro = (SistemaParametro) getParametro("SistemaParametros");
		
		Iterator iterator = colecaoSetores.iterator();
		
		while(iterator.hasNext()) {
			
			SetorComercial setor = (SetorComercial) iterator.next();
		
			enviarMensagemControladorBatch(
	                ConstantesJNDI.BATCH_EMITIR_ORDEM_FISCALIZACAO,
	                new Object[] { this.getIdFuncionalidadeIniciada(),usuarioLogado,setor,sistemaParametro});
		
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
		AgendadorTarefas.agendarTarefa("BatchEmitirOrdemDeFiscalizacao",this);

	}

}
