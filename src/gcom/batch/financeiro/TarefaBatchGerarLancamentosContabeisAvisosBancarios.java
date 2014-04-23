package gcom.batch.financeiro;

import java.util.Collection;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

public class TarefaBatchGerarLancamentosContabeisAvisosBancarios extends TarefaBatch {

	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarLancamentosContabeisAvisosBancarios(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarLancamentosContabeisAvisosBancarios() {
		super(null, 0);
	}
	
	public Object executar() throws TarefaException {

        Integer anoMesArrecadacao = (Integer) getParametro("anoMesArrecadacao");
        
        enviarMensagemControladorBatch(ConstantesJNDI.BATCH_GERAR_LANCAMENTOS_CONTABEIS_AVISOS_BANCARIOS_MDB,
        new Object[] {anoMesArrecadacao, this.getIdFuncionalidadeIniciada()});
                            
        
        return null;
    }

	@Override
	public Collection pesquisarTodasUnidadeProcessamentoBatch() {
		return null;
	}

	@Override
	public Collection pesquisarTodasUnidadeProcessamentoReinicioBatch() {

		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("GerarLancamentosContabeisArrecadacaoBatch",this);
	}
}
