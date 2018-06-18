package gcom.batch.cobranca.cobrancaporresultado;

import gcom.cadastro.empresa.Empresa;
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
public class TarefaBatchEncerrarComandosDeCobrancaResultadoPorEmpresa extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchEncerrarComandosDeCobrancaResultadoPorEmpresa(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchEncerrarComandosDeCobrancaResultadoPorEmpresa() {
		super(null, 0);
	}

	@SuppressWarnings("unchecked")
	public Object executar() throws TarefaException {

		Collection<Empresa> empresas = (Collection<Empresa>) getParametro("empresas");
		Usuario usuario = (Usuario) getParametro("usuario");
		Integer idCobrancaSituacao = (Integer) getParametro("idCobrancaSituacao");
		
		enviarMensagemControladorBatch(ConstantesJNDI.BATCH_ENCERRAR_COMANDO_DE_COBRANCA_RESULTADO_POR_EMPRESA,
				new Object[] {
				this.getIdFuncionalidadeIniciada(),
				usuario,
				empresas,
				idCobrancaSituacao});

		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Collection pesquisarTodasUnidadeProcessamentoBatch() {
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Collection pesquisarTodasUnidadeProcessamentoReinicioBatch() {

		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("EncerrarComandosDeCobrancaResultadoPorEmpresaBatch", this);
	}

}
