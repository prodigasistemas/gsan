package gcom.batch.faturamento;

import gcom.faturamento.FaturamentoGrupo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

/**
 * Tarefa que manda para batch Emitir Extrato Consumo Imovel Condominio
 * 
 * @author Rodrigo Silveira
 * @created 02/03/2007
 */
public class TarefaBatchEmitirExtratoConsumoImovelCondominio extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchEmitirExtratoConsumoImovelCondominio(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchEmitirExtratoConsumoImovelCondominio() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) getParametro("faturamentoGrupo");
		int anoMesFaturamentoGrupo  =(Integer) getParametro("anoMesFaturamentoGrupo");

		enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_EMITIR_EXTRATO_CONSUMO_IMOVEL_CONDOMINIO_MDB,
					new Object[]{""+anoMesFaturamentoGrupo, ""+faturamentoGrupo.getId(),	this.getIdFuncionalidadeIniciada()});



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
		AgendadorTarefas.agendarTarefa(
				"EmitirExtratoConsumoImovelCondominioBatch", this);
	}

}
