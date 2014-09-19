package gcom.gerencial.atendimentopublico.registroatendimento;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

public class TarefaBatchGerarResumoRegistroAtendimentoPorAno extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;
	
	public TarefaBatchGerarResumoRegistroAtendimentoPorAno(Usuario usuario,
		int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);		
	}
		
	@Deprecated
	public TarefaBatchGerarResumoRegistroAtendimentoPorAno() {
		super(null, 0);
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
	public Object executar() throws TarefaException {
		
		Collection colecaoIdsLocalidade = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);
		
		Integer anoMesFaturamentoSistemaParametro = (Integer) getParametro("anoMesFaturamentoSistemaParametro");
		

		Iterator iterator = colecaoIdsLocalidade.iterator();

		while (iterator.hasNext()) {

			Integer idLocalidade = ( (Integer) iterator.next() );

			System.out
					.println("Localidade GERAR RESUMO REGISTRO ATENDIMENTO POR ANO "
							+ (idLocalidade)
							+ "*********************************************************");

			enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_GERAR_RESUMO_REGISTRO_ATENDIMENTO_POR_ANO_MDB,
					new Object[]{idLocalidade,
							this.getIdFuncionalidadeIniciada(), anoMesFaturamentoSistemaParametro});

		}

		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("GerarResumoRegistroAtendimentoPorAnoBatch",
				this);

	}
}
