package gcom.batch.cobranca;

import gcom.cobranca.RelatorioPagamentosContasCobrancaEmpresaHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

public class TarefaBatchGerarRelatorioPagamentosContasCobrancaEmpresa extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarRelatorioPagamentosContasCobrancaEmpresa(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarRelatorioPagamentosContasCobrancaEmpresa() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {
		
		RelatorioPagamentosContasCobrancaEmpresaHelper helper = (RelatorioPagamentosContasCobrancaEmpresaHelper) 
		getParametro("helper");
		
		/*Empresa empresa = (Empresa) getParametro("empresa");
		
		Integer referenciaInicialFormatada = (Integer) getParametro("referenciaInicialFormatada");

		Integer referenciaFinalFormatada = (Integer) getParametro("referenciaFinalFormatada");*/
		
		int opcaoRelatorio = (Integer) getParametro("opcaoRelatorio");
		
		enviarMensagemControladorBatch(ConstantesJNDI.BATCH_GERAR_RELATORIO_PAGAMENTOS_CONTAS_COBRANCA_POR_EMPRESA,
				new Object[] { helper,
				opcaoRelatorio,	this.getIdFuncionalidadeIniciada()});
		
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
		AgendadorTarefas.agendarTarefa("GerarRelatorioPagamentosContasCobrancaEmpresaBatch", this);
	}

}
