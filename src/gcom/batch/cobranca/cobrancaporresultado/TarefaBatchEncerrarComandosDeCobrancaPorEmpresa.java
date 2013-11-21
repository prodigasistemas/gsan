package gcom.batch.cobranca.cobrancaporresultado;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

/**
 * [UC1168] Encerrar Comandos de Cobrança por Empresa
 * Tarefa que manda para batch Encerrar Comandos de Cobrança por Empresa
 * 
 * @author Mariana Victor
 * @created 09/05/2011
 */
public class TarefaBatchEncerrarComandosDeCobrancaPorEmpresa extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchEncerrarComandosDeCobrancaPorEmpresa(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchEncerrarComandosDeCobrancaPorEmpresa() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		String idEmpresa = (String) getParametro("idEmpresa");
		Integer idRegistro = (Integer) getParametro("idRegistro");
		Usuario usuario = (Usuario) getParametro("usuario");
		Integer idCobrancaSituacao = (Integer) getParametro("idCobrancaSituacao");
		
		enviarMensagemControladorBatch(ConstantesJNDI.BATCH_ENCERRAR_COMANDO_DE_COBRANCA_POR_EMPRESA,
					new Object[] {
								this.getIdFuncionalidadeIniciada(),
								idEmpresa,
								usuario,
								idRegistro,
								idCobrancaSituacao});

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
		AgendadorTarefas.agendarTarefa("EncerrarComandosDeCobrancaPorEmpresaBatch", this);
	}

}
