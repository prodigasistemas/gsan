package gcom.gerencial.micromedicao;

import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

public class TarefaBatchGerarResumoHidrometro extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;
	
	public TarefaBatchGerarResumoHidrometro(Usuario usuario,
		int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);		
	}
		
	@Deprecated
	public TarefaBatchGerarResumoHidrometro() {
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
		Collection colecaoIdsHidrometrosMarcas = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);

		Iterator iterator = colecaoIdsHidrometrosMarcas.iterator();

		while (iterator.hasNext()) {

			Integer idMarca = ( (HidrometroMarca) iterator.next() ).getId();

			System.out
					.println("MARCA HIDROMETRO GERAR RESUMO HIDROMETRO "
							+ (idMarca)
							+ "*********************************************************");

			enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_GERAR_RESUMO_HIDROMETRO_MDB,
					new Object[]{idMarca,
							this.getIdFuncionalidadeIniciada()});

		}

		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("GerarResumoHidrometroBatch",
				this);

	}
}
