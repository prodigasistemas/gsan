package gcom.batch.micromedicao;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.faturamento.FaturamentoGrupo;
import gcom.micromedicao.Rota;
import gcom.micromedicao.leitura.LeituraTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;


/**
 * Descrição da classe 
 *
 * @author Pedro Alexandre, Pedro Alexandre
 * @date 12/09/2007, 15/10/2007
 */
public class TarefaBatchGerarArquivoTextoParaLeiturista extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarArquivoTextoParaLeiturista(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarArquivoTextoParaLeiturista() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {
		

		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) getParametro("faturamentoGrupo");
		Integer anoMesFaturamento = faturamentoGrupo.getAnoMesReferencia();
		
		Collection colecaoRoteirosEmpresa = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);
		Collection colecaoRotasParaExecucao = (Collection) getParametro("colecaoRotas");
		SistemaParametro sistemaParametro = (SistemaParametro) getParametro("sistemaParametro");
		Date dataComando = (Date) getParametro("dataComando");
		
		Iterator iterator = colecaoRotasParaExecucao.iterator();
		Collection<Rota> colecaoRotas = new ArrayList();
		
		while (iterator.hasNext()) {
			

			Object[] array = (Object[]) iterator.next();
			
			Rota rota = (Rota) array[1];
			
			if(rota.getLeituraTipo() != null &&
			   rota.getLeituraTipo().getId().equals(LeituraTipo.LEITURA_E_ENTRADA_SIMULTANEA)){
				
				enviarMensagemControladorBatch(ConstantesJNDI.BATCH_GERAR_ARQUIVO_TEXTO_PARA_LEITURISTA,
						new Object[] { colecaoRoteirosEmpresa,
						Collections.singletonList(rota), 
						anoMesFaturamento,
						faturamentoGrupo,sistemaParametro,dataComando,this.getIdFuncionalidadeIniciada() });
				
			}else{
				colecaoRotas.add(rota);	
			}
			
		}
		
		if(colecaoRotas != null && !colecaoRotas.isEmpty()){
		
		enviarMensagemControladorBatch(ConstantesJNDI.BATCH_GERAR_ARQUIVO_TEXTO_PARA_LEITURISTA,
				new Object[] { colecaoRoteirosEmpresa,
				colecaoRotas, 
				anoMesFaturamento,
				faturamentoGrupo,sistemaParametro,dataComando,this.getIdFuncionalidadeIniciada() });
		
		}
		
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
		AgendadorTarefas.agendarTarefa("GerarArquivoTextoParaLeituristaBatch", this);
	}

}
