package gcom.batch.financeiro;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class TarefaBatchRelatorioContasBaixadasContabilmente extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchRelatorioContasBaixadasContabilmente(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchRelatorioContasBaixadasContabilmente() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		Map parametros = (Map) getParametro(ConstantesSistema.PARAMETROS_BATCH);
        Short tipo = (Short)parametros.get("tipo");
        
        if(tipo.equals(ConstantesSistema.ANALITICO)){
        
    		Collection colecaoSetorComercialParaExecucao = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH); // (Collection<Rota>)
    		Iterator iterator = colecaoSetorComercialParaExecucao.iterator();
    
    		while (iterator.hasNext()) {
    
    			Integer idsSetorComercial = (Integer) iterator.next();
    
    			
    			System.out
    					.println("Setor Comercial RELATORIO CONTAS BAIXADAS CONTABILMENTE ANALÍTICO"
    							+ idsSetorComercial
    							+ "*********************************************************");
    
    			enviarMensagemControladorBatch(
    	                ConstantesJNDI.BATCH_RELATORIO_CONTAS_BAIXADAS_CONTABILMENTE,
    	                new Object[] { parametros, idsSetorComercial, this.getIdFuncionalidadeIniciada(),ConstantesSistema.FAIXA_1 });
                
                enviarMensagemControladorBatch(
                        ConstantesJNDI.BATCH_RELATORIO_CONTAS_BAIXADAS_CONTABILMENTE,
                        new Object[] { parametros, idsSetorComercial, this.getIdFuncionalidadeIniciada(),ConstantesSistema.FAIXA_2 });
                
                enviarMensagemControladorBatch(
                        ConstantesJNDI.BATCH_RELATORIO_CONTAS_BAIXADAS_CONTABILMENTE,
                        new Object[] { parametros, idsSetorComercial, this.getIdFuncionalidadeIniciada(),ConstantesSistema.FAIXA_3 });
    
    		}
            
        }else if (tipo.equals(ConstantesSistema.SINTETICO)){
            
            System.out
            .println("RELATORIO CONTAS BAIXADAS CONTABILMENTE SINTÉTICO"
                    + "*********************************************************");

            enviarMensagemControladorBatch(
                    ConstantesJNDI.BATCH_RELATORIO_CONTAS_BAIXADAS_CONTABILMENTE,
                    new Object[] { parametros, 0, this.getIdFuncionalidadeIniciada(),null });
            
           
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
		AgendadorTarefas.agendarTarefa("BatchRelatorioContasBaixadasContabilmente",
				this);
	}

}
