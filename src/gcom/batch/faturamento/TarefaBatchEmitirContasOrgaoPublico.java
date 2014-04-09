package gcom.batch.faturamento;

import gcom.faturamento.FaturamentoGrupo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

/**
 * Tarefa que manda para batch Gerar Resumo das Ações de Cobrança do Cronograma
 * 
 * @author Sávio Luiz
 * @created 15/06/2007
 */
public class TarefaBatchEmitirContasOrgaoPublico extends TarefaBatch {

	private static final long serialVersionUID = 1L;

	public TarefaBatchEmitirContasOrgaoPublico(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchEmitirContasOrgaoPublico() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		
		Integer anoMesFaturamento = (Integer) getParametro("anoMesFaturamento");
		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) getParametro("faturamentoGrupo");
		int tipoConta = 0;
		Integer idEmpresa = new Integer (1);
		short indicadorEmissaoExtratoFaturamento = 0;
		enviarMensagemControladorBatch(
				ConstantesJNDI.BATCH_EMITIR_CONTAS_ORGAO_PUBLICO,
				new Object[] { anoMesFaturamento,
						faturamentoGrupo, this.getIdFuncionalidadeIniciada(),
						tipoConta, idEmpresa, indicadorEmissaoExtratoFaturamento });

		

		// Falta verificar os nulos para o outro caso
		/*
		 * enviarMensagemControladorBatch(
		 * ConstantesJNDI.BATCH_GERAR_RESUMO_ACOES_COBRANCA_CRONOGRAMA_MDB, new
		 * Object[]{colecaoCobrancaGrupoCronogramaMes,
		 * this.getIdFuncionalidadeIniciada()});
		 */
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
				"EmitirContasOrgaoPublicoBatch", this);
	}

}
