package gcom.batch.faturamento;

import java.util.Collection;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.SessionContext;

import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.IControladorFaturamento;
import gcom.faturamento.IRepositorioFaturamento;
import gcom.faturamento.RepositorioFaturamentoHBM;
import gcom.faturamento.conta.Conta;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ErroRepositorioException;
import gcom.util.agendadortarefas.AgendadorTarefas;


public class TarefaBatchRegistrarBoletos extends TarefaBatch{
	
	private static final long serialVersionUID = 1L;
	
	SessionContext sessionContext;
	
	protected IControladorFaturamento controladorFaturamento;
	
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
		Integer idFaturamentoGrupo = faturamentoGrupo.getId();
			
		enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_REGISTRAR_BOLETOS_MDB,
					new Object[] {idFaturamentoGrupo, anoMesFaturamentoGrupo,this.getIdFuncionalidadeIniciada()});

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
