package gcom.batch.cobranca;

import gcom.cobranca.ComandoEmpresaCobrancaConta;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

public class TarefaBatchGerarMovimentoContasCobrancaPorEmpresa extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;
	
	public TarefaBatchGerarMovimentoContasCobrancaPorEmpresa(Usuario usuario,
		int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);		
	}
		
	@Deprecated
	public TarefaBatchGerarMovimentoContasCobrancaPorEmpresa() {
		super(null, 0);
	}		

	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoBatch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoReinicioBatch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object executar() throws TarefaException {
		Collection colecaoComandoEmpresaCobrancaConta = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);
		
		Iterator iterator = colecaoComandoEmpresaCobrancaConta.iterator();

		while (iterator.hasNext()) {

			ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta = ( (ComandoEmpresaCobrancaConta) iterator.next() );

			enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_GERAR_MOVIMENTO_CONTAS_COBRANCA_POR_EMPRESA,
					new Object[]{comandoEmpresaCobrancaConta,
							this.getIdFuncionalidadeIniciada()});

		}

		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("GerarMovimentoContasCobrancaPorEmpresaBatch",
				this);

	}
}
