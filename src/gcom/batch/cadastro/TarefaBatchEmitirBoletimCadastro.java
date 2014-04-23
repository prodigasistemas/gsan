package gcom.batch.cadastro;

import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.CobrancaAcaoAtividadeCronograma;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Date;

/**
 * Tarefa que manda para batch Emitir Boletim de Cadastro
 * 
 * @author Rafael Corrêa
 * @created 30/05/2007
 */
public class TarefaBatchEmitirBoletimCadastro extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchEmitirBoletimCadastro(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchEmitirBoletimCadastro() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		CobrancaAcaoAtividadeCronograma comandoAtividadeAcaoCobranca = (CobrancaAcaoAtividadeCronograma) getParametro("comandoAtividadeAcaoCobranca");
		CobrancaAcaoAtividadeComando comandoAtividadeAcaoComando = (CobrancaAcaoAtividadeComando) getParametro("comandoAtividadeAcaoComando");
		CobrancaAcao acaoCobranca = (CobrancaAcao) getParametro("acaoCobranca");
		Date dataAtual = (Date) getParametro("dataAtual");

		enviarMensagemControladorBatch(
				ConstantesJNDI.BATCH_EMITIR_BOLETIM_CADASTRO_MDB, new Object[] {
						comandoAtividadeAcaoCobranca,
						comandoAtividadeAcaoComando, dataAtual,
						acaoCobranca,
						this.getIdFuncionalidadeIniciada()});

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
		AgendadorTarefas.agendarTarefa("EmitirBoletimCadastroBatch", this);
	}

}
