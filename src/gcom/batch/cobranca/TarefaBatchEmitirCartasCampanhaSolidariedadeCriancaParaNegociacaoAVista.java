package gcom.batch.cobranca;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

/**
 * [UC0910] Emitir Cartas da Campanha de Solidariedade da criança para Negociação a Vista
 * 
 * @author: Vivianne Sousa
 * @date: 18/06/2009
 */
public class TarefaBatchEmitirCartasCampanhaSolidariedadeCriancaParaNegociacaoAVista extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchEmitirCartasCampanhaSolidariedadeCriancaParaNegociacaoAVista(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchEmitirCartasCampanhaSolidariedadeCriancaParaNegociacaoAVista() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		Integer idFaturamentoGrupo = new Integer((String) getParametro("faturamentoGrupo"));

		System.out.println("EMITIR CARTAS " + idFaturamentoGrupo + " *********************************************************");
		
		enviarMensagemControladorBatch(
				ConstantesJNDI.BATCH_EMITIR_CARTAS_CAMPANHA_SOLIDARIEDADE_CRIANCA_PARA_NEGOCIACAO_A_VISTA_MDB,
				new Object[]{idFaturamentoGrupo, this.getIdFuncionalidadeIniciada()});

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
		AgendadorTarefas.agendarTarefa("EmitirCartasCampanhaSolidariedadeCriancaParaNegociacaoAVistaBatch", this);
	}

}
