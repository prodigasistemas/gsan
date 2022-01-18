package gcom.batch.faturamento;

import java.util.Collection;
import java.util.Iterator;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.conta.Conta;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;


public class TarefaBatchRegistrarBoletos extends TarefaBatch{
	
	private static final long serialVersionUID = 1L;
	
	public TarefaBatchRegistrarBoletos
		(Usuario usuario,int idFuncionalidadeIniciada) {
		
		super(usuario, idFuncionalidadeIniciada);
		
	}
	
	@Deprecated
	public TarefaBatchRegistrarBoletos() {
		super(null, 0);
	}
	
	@Override
	public Object executar() throws TarefaException {
		
		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) getParametro("faturamentoGrupo");
		Integer anoMesFaturamentoGrupo = (Integer) getParametro("anoMesFaturamentoGrupo");

		Collection<Conta> colecaoContas = (Collection<Conta>) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);

		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();

		   Iterator iterator = colecaoContas.iterator();

	    while (iterator.hasNext()) {
	    	
			enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_REGISTRAR_BOLETOS_MDB,
					new Object[]{this.getIdFuncionalidadeIniciada()});
			
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoBatch() {
		return null;
	}


	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoReinicioBatch() {
		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RegistrarBoletosBatch", this);

	}

}
