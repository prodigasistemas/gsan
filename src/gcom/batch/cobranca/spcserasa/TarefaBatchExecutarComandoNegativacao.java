package gcom.batch.cobranca.spcserasa;

import gcom.cobranca.NegativacaoComando;
import gcom.cobranca.NegativacaoCriterio;
import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorContrato;
import gcom.cobranca.NegativadorMovimento;
import gcom.cobranca.NegativadorMovimentoReg;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.spcserasa.ControladorSpcSerasaLocal;
import gcom.spcserasa.ControladorSpcSerasaLocalHome;
import gcom.spcserasa.FiltroNegativadorMovimento;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ControladorUtilLocalHome;
import gcom.util.ErroRepositorioException;
import gcom.util.RepositorioUtilHBM;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.CreateException;

/**
 * Tarefa que manda para Executar Comando Negativacao
 * 
 * @author Ana Maria
 * @created 27/03/2007
 */
public class TarefaBatchExecutarComandoNegativacao extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchExecutarComandoNegativacao(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchExecutarComandoNegativacao() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		Collection rotas = null;
		if(getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH) != null){
			rotas = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH); 
		}

		NegativacaoCriterio nCriterio = (NegativacaoCriterio) getParametro("nCriterio");
		Negativador neg = (Negativador) getParametro("neg");
		NegativacaoComando nComando = (NegativacaoComando) getParametro("nComando");
		NegativadorContrato nContrato = (NegativadorContrato) getParametro("nContrato");
		NegativadorMovimento negMovimento = null;
		Integer idNegativacaoMovimento = null;
		NegativadorMovimentoReg.resetNumeroProximoRegistro();
		NegativacaoComando.resetQuantidadeImoveisJaIncluidos();
		
		try {
			
			if (nComando.getIndicadorSimulacao() == 2) {	
				
				//Inserindo o HEADER
				FiltroNegativadorMovimento fnm = new FiltroNegativadorMovimento();
				fnm.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimento.NEGATIVACAO_COMANDO, nComando.getId()));
				Collection colecaoNegativadorMovimento  = RepositorioUtilHBM.getInstancia().pesquisar(fnm,NegativadorMovimento.class.getName());
				
				if (colecaoNegativadorMovimento != null && !colecaoNegativadorMovimento.isEmpty()) {
					
					/*
					 * [UC0671] Gerar Movimento de Inclusão de Negativação
					 * [SB0014] – Desfazer Atualização da Execução Descontinuada
					 */
					getControladorSpcSerasa().desfazerAtualizacaoExecucaoDescontinuada(this.getIdFuncionalidadeIniciada(), nComando);
				
				}
					
				int saEnvio = nContrato.getNumeroSequencialEnvio() + 1;
				//1.2
				//gerando o movimento
					
				Date dataAtual = new Date();
					
				negMovimento = new NegativadorMovimento();
				negMovimento.setNegativador(neg);
				negMovimento.setNegativacaoComando(nComando);
				negMovimento.setNumeroSequencialEnvio(saEnvio);
				negMovimento.setCodigoMovimento(NegativadorMovimento.CODIGO_MOVIMENTO_INCLUSAO);
				negMovimento.setDataEnvio(dataAtual);
				negMovimento.setDataProcessamentoEnvio(dataAtual);
				negMovimento.setUltimaAlteracao(dataAtual);

				idNegativacaoMovimento = (Integer) getControladorUtil().inserir(negMovimento);
				negMovimento.setId(idNegativacaoMovimento);
					
				//1.3
				//[SB0008] - Gerar Registro do tipo Hearder
				getControladorSpcSerasa().gerarRegistroDeInclusaoTipoHeader(
					ConstantesSistema.TIPO_COMANDO_POR_CRITERIO, NegativadorMovimentoReg.getNumeroProximoRegistro(), 
					neg, nContrato,nComando,nCriterio,idNegativacaoMovimento);		
				
			}
			else {
				
				negMovimento = new NegativadorMovimento();
			
			}
			
			if(rotas != null){
				Iterator iterator = rotas.iterator();
				 while (iterator.hasNext()) {
					Integer id = (Integer) iterator.next();
					
					enviarMensagemControladorBatch(
							ConstantesJNDI.BATCH_EXECUTAR_COMANDO_NEGATIVACAO_MDB,
							new Object[] {id, 
									nCriterio, neg,
									nComando,
									nContrato, 
									negMovimento, this.getIdFuncionalidadeIniciada()});
				}
			}else{
				enviarMensagemControladorBatch(
						ConstantesJNDI.BATCH_EXECUTAR_COMANDO_NEGATIVACAO_MDB,
						new Object[] {0,
								nCriterio, neg,
								nComando,
								nContrato, 
								negMovimento, this.getIdFuncionalidadeIniciada()});
			}
		} catch (ErroRepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new TarefaException(e.getMessage());
		} catch (ControladorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new TarefaException(e.getMessage());
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
		AgendadorTarefas.agendarTarefa("BatchExecutarComandoNegativacao",
				this);
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
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}
	
	private ControladorSpcSerasaLocal getControladorSpcSerasa() {
		ControladorSpcSerasaLocalHome localHome = null;
		ControladorSpcSerasaLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorSpcSerasaLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_SPC_SERASA_SEJB);
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
