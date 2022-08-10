package gcom.batch.faturamento;

import java.util.Collection;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

public class TarefaBatchAtualizarDadosBolsaAgua extends TarefaBatch {

	private static final long serialVersionUID = 1L;

	public TarefaBatchAtualizarDadosBolsaAgua(Usuario usuario, int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);
	}
	
	@Deprecated
	public TarefaBatchAtualizarDadosBolsaAgua() {
		super(null, 0);
	}

	@Override
	public Object executar() throws TarefaException {
		Collection<Rota> colecaoRotas= (Collection<Rota>) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);

		for (Rota rota : colecaoRotas) {
			enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_ATUALIZAR_DADOS_BOLSA_AGUA,
					new Object[]{rota.getId() ,this.getIdFuncionalidadeIniciada()});
		}
		return null;
	}
	
	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoBatch() {
		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) getParametro("faturamentoGrupo");
		FiltroRota filtroRota = new FiltroRota();
		filtroRota.adicionarParametro(new ParametroSimples(
				FiltroRota.FATURAMENTO_GRUPO_ID, faturamentoGrupo.getId()));

		return Fachada.getInstancia().pesquisar(filtroRota, Rota.class.getName());
	}
	
	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoReinicioBatch() {
		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa(
				"AtualizarDadosAguaParaBatch", this);
	}

}
