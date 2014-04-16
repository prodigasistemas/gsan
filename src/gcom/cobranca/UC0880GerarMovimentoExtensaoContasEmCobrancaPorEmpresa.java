package gcom.cobranca;

import gcom.batch.ControladorBatchLocal;
import gcom.batch.ControladorBatchLocalHome;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.ControladorImovelLocalHome;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;

import javax.ejb.CreateException;
import javax.ejb.SessionContext;

/**
 * Esta classe tem como finalidade encapsular o caso de uso [UC0880] - Gerar
 * Movimento de Extensao de Contas em Cobranca por Empresa , gerando maior
 * facilidade na manutenção do mesmo.
 * 
 * @author Rômulo Aurélio
 * @date 09/02/2009
 */
public class UC0880GerarMovimentoExtensaoContasEmCobrancaPorEmpresa {

	private static UC0880GerarMovimentoExtensaoContasEmCobrancaPorEmpresa instancia;

	private IRepositorioCobranca repositorioCobranca;

	private SessionContext sessionContext;

	private UC0880GerarMovimentoExtensaoContasEmCobrancaPorEmpresa(
			IRepositorioCobranca repositorioCobranca,
			SessionContext sessionContext) {

		this.repositorioCobranca = repositorioCobranca;
		this.sessionContext = sessionContext;
	}

	public static UC0880GerarMovimentoExtensaoContasEmCobrancaPorEmpresa getInstancia(
			IRepositorioCobranca repositorioCobranca,
			SessionContext sessionContext) {

		if (instancia == null) {
			instancia = new UC0880GerarMovimentoExtensaoContasEmCobrancaPorEmpresa(
					repositorioCobranca, sessionContext);
		}
		return instancia;
	}

	/**
	 * Controlador Imovel
	 * 
	 * @author Raphael Rossiter
	 * @date 30/04/2008
	 * 
	 * @return ControladorImovelLocal
	 */
	protected ControladorImovelLocal getControladorImovel() {
		ControladorImovelLocalHome localHome = null;
		ControladorImovelLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorImovelLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_IMOVEL_SEJB);
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

	/**
	 * Controlador Cobranca
	 * 
	 * @author Raphael Rossiter
	 * @date 30/04/2008
	 * 
	 * @return ControladorCobrancaLocal
	 */
	protected ControladorCobrancaLocal getControladorCobranca() {
		ControladorCobrancaLocalHome localHome = null;
		ControladorCobrancaLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorCobrancaLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_COBRANCA_SEJB);
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

	protected ControladorBatchLocal getControladorBatch() {
		ControladorBatchLocalHome localHome = null;
		ControladorBatchLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorBatchLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_BATCH_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}

	/**
	 * [UC0880] - Gerar Movimento de Extensao de Contas em Cobranca por Empresa
	 * 
	 * @author Rômulo Aurélio
	 * @param idPerfil 
	 * @date 09/02/2009
	 * 
	 * @param idRota
	 * @param anoMesReferencia
	 * @return boolean
	 * @throws ControladorException
	 */
	public void gerarMovimentoExtensaoContasEmCobranca(Integer idLocalidade, Integer idPerfil)
			throws ControladorException {
		try {

			repositorioCobranca
					.inserirMovimentoExtensaoContasEmCobranca(idLocalidade,idPerfil);

		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

	}

}
