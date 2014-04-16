package gcom.batch.cobranca;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.CobrancaAcaoAtividadeCronograma;
import gcom.cobranca.CobrancaAtividade;
import gcom.cobranca.CobrancaCriterio;
import gcom.micromedicao.ControladorMicromedicaoLocal;
import gcom.micromedicao.ControladorMicromedicaoLocalHome;
import gcom.micromedicao.Rota;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ControladorUtilLocalHome;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.CreateException;

/**
 * Tarefa que manda para batch Gerar Atividade de Ação de Cobrança
 * 
 * @author Rodrigo Silveira
 * @created 17/11/2006
 */
public class TarefaBatchGerarAtividadeAcaoCobranca extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarAtividadeAcaoCobranca(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarAtividadeAcaoCobranca() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		try{
			
//			CobrancaGrupo grupoCobranca = (CobrancaGrupo) getParametro("grupoCobranca");
//			Integer anoMesReferenciaCicloCobranca = (Integer) getParametro("anoMesReferenciaCicloCobranca");
			CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma = (CobrancaAcaoAtividadeCronograma) getParametro("comandoAtividadeAcaoCobranca");
			CobrancaAcaoAtividadeComando comandoAtividadeAcaoComando = (CobrancaAcaoAtividadeComando) getParametro("comandoAtividadeAcaoComando");
			Collection colecaoRota = (Collection) getParametro("colecaoRotas");
			CobrancaAcao acaoCobranca = (CobrancaAcao) getParametro("acaoCobranca");
			CobrancaAtividade atividadeCobranca = (CobrancaAtividade) getParametro("atividadeCobranca");
			Short indicadorCriterio = (Short) getParametro("indicadorCriterio");

			CobrancaCriterio cobrancaCriterio = (CobrancaCriterio) getParametro("criterioCobranca");
			Cliente cliente = (Cliente) getParametro("cliente");
			Cliente clienteSuperior = (Cliente) getParametro("clienteSuperior");
			ClienteRelacaoTipo clienteRelacaoTipo = (ClienteRelacaoTipo) getParametro("clienteRelacaoTipo");

			Integer anoMesReferenciaInicial = (Integer) getParametro("anoMesReferenciaInicial");
			Integer anoMesReferenciaFinal = (Integer) getParametro("anoMesReferenciaFinal");
			Date dataVencimentoInicial = (Date) getParametro("dataVencimentoInicial");
			Date dataVencimentoFinal = (Date) getParametro("dataVencimentoFinal");
			Date dataAtual = (Date) getParametro("dataAtual");
			Integer idCobrancaDocumentoControleGeracao = (Integer)getParametro("idCobrancaDocumentoControleGeracao");

			//se o batch não for processado por rota,
			//não terá paralelismo 
			if (cliente != null || clienteSuperior != null ||
				(comandoAtividadeAcaoComando!=null && comandoAtividadeAcaoComando.getLogradouro()!=null)) {
				
				enviarMensagemControladorBatch(
						ConstantesJNDI.BATCH_GERAR_ATIVIDADE_ACAO_COBRANCA_MDB,
						new Object[]{
								cobrancaAcaoAtividadeCronograma,
								comandoAtividadeAcaoComando,
								null,//Rota
								acaoCobranca, 
								atividadeCobranca, 
								indicadorCriterio.intValue(),
								cobrancaCriterio, 
								cliente, 
								clienteRelacaoTipo,
								anoMesReferenciaInicial.toString(),
								anoMesReferenciaFinal.toString(),
								dataVencimentoInicial, 
								dataVencimentoFinal,
								dataAtual,
								this.getIdFuncionalidadeIniciada(),
								clienteSuperior,
								idCobrancaDocumentoControleGeracao});
				
				
			}else{
				//batch processado por rota, roda em paralelo
				
				if (colecaoRota == null || colecaoRota.isEmpty()) {

					SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();
					
					if (acaoCobranca.getId() != null && acaoCobranca.getId().equals(CobrancaAcao.CORTE_ADMINISTRATIVO)
						&& sistemaParametro.getCodigoEmpresaFebraban() != null && 
						sistemaParametro.getCodigoEmpresaFebraban().equals(Empresa.EMPRESA_FEBRABAN_COMPESA)) {
						// Obtém a coleção de todas as rotas do sistema com empresaCobranca igual a 1
						colecaoRota = getControladorMicromedicao().pesquisarListaRotasEspecificas();

					} else {
						// Obtém a coleção de todas as rotas do sistema
						colecaoRota = getControladorMicromedicao().pesquisarListaRotas();
					}
				}
				
				
				Iterator iteratorRotas = colecaoRota.iterator();
				
				while (iteratorRotas.hasNext()) {
					Rota rota = (Rota) iteratorRotas.next();
//					System.out.println("*************************");
//		            System.out.println("Gerar Documento Cobranca.ROTA:" + rota.getId());
//		            System.out.println("*************************");
							            
		            enviarMensagemControladorBatch(
							ConstantesJNDI.BATCH_GERAR_ATIVIDADE_ACAO_COBRANCA_MDB,
							new Object[]{
									cobrancaAcaoAtividadeCronograma,
									comandoAtividadeAcaoComando,
									rota,
									acaoCobranca, 
									atividadeCobranca, 
									indicadorCriterio.intValue(),
									cobrancaCriterio, 
									cliente, 
									clienteRelacaoTipo,
									anoMesReferenciaInicial.toString(),
									anoMesReferenciaFinal.toString(),
									dataVencimentoInicial, 
									dataVencimentoFinal,
									dataAtual,
									this.getIdFuncionalidadeIniciada(),
									clienteSuperior,idCobrancaDocumentoControleGeracao});
		            
					
				}
				
				
			}

			
		} catch (ControladorException e) {
			System.out.println("Erro no MDB");
			e.printStackTrace();
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
		AgendadorTarefas.agendarTarefa("GerarAtividadeAcaoCobrancaBatch", this);
	}
	
	
	private ControladorUtilLocal getControladorUtil() {

		ControladorUtilLocalHome localHome = null;
		ControladorUtilLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorUtilLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}

	private ControladorMicromedicaoLocal getControladorMicromedicao() {
		ControladorMicromedicaoLocalHome localHome = null;
		ControladorMicromedicaoLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorMicromedicaoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_MICROMEDICAO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

}
