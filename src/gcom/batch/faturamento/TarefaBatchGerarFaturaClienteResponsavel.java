package gcom.batch.faturamento;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;


/**
 * Descrição da classe 
 *
 * @author Pedro Alexandre
 * @date 15/03/2007
 */
public class TarefaBatchGerarFaturaClienteResponsavel extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarFaturaClienteResponsavel(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarFaturaClienteResponsavel() {
		super(null, 0);
	}

    public Object executar() throws TarefaException {

            enviarMensagemControladorBatch(
                    ConstantesJNDI.BATCH_GERAR_FATURA_CLIENTE_RESPONSAVEL_MDB,
                    new Object[] { this.getIdFuncionalidadeIniciada() });

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
		AgendadorTarefas.agendarTarefa("GerarFaturaClienteResponsavelBatch",
				this);
	}

}
