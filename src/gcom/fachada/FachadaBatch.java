package gcom.fachada;

import gcom.arrecadacao.ControladorArrecadacaoLocal;
import gcom.arrecadacao.ControladorArrecadacaoLocalHome;
import gcom.atendimentopublico.ordemservico.ControladorOrdemServicoLocal;
import gcom.atendimentopublico.ordemservico.ControladorOrdemServicoLocalHome;
import gcom.cadastro.ControladorCadastroLocal;
import gcom.cadastro.ControladorCadastroLocalHome;
import gcom.cadastro.EnvioEmail;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.ControladorImovelLocalHome;
import gcom.cobranca.ControladorCobrancaLocal;
import gcom.cobranca.ControladorCobrancaLocalHome;
import gcom.faturamento.ControladorFaturamentoLocal;
import gcom.faturamento.ControladorFaturamentoLocalHome;
import gcom.faturamento.conta.Fatura;
import gcom.faturamento.conta.FiltroFatura;
import gcom.financeiro.ControladorFinanceiroLocal;
import gcom.financeiro.ControladorFinanceiroLocalHome;
import gcom.gerencial.micromedicao.ControladorGerencialMicromedicaoLocal;
import gcom.gerencial.micromedicao.ControladorGerencialMicromedicaoLocalHome;
import gcom.micromedicao.ControladorMicromedicaoLocal;
import gcom.micromedicao.ControladorMicromedicaoLocalHome;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ControladorUtilLocalHome;
import gcom.util.ErroRepositorioException;
import gcom.util.FachadaException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.email.ErroEmailException;
import gcom.util.email.ServicosEmail;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.ejb.CreateException;

/**
 * Fachada batch
 * 
 * @author Thiago Toscano
 * @created 19/04/2006
 */
public class FachadaBatch {

	private static FachadaBatch instancia;

	/**
	 * Construtor da classe Fachada
	 */
	private FachadaBatch() {

	}

	/**
	 * Retorna o valor de instancia
	 * 
	 * @return O valor de instancia
	 */
	public static FachadaBatch getInstancia() {
		if (instancia == null) {
			instancia = new FachadaBatch();
		}
		return instancia;
	}

	// *************----Métodos do SERVICE LOCATOR (CONTROLADORES)
	// ----*************//
	/**
	 * Retorna o valor de controladorUtil
	 * 
	 * @return O valor de controladorUtil
	 */
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

	/**
	 * Retorna o valor de controladorMicromedicao
	 * 
	 * @return O valor de controladorMicromedicao
	 */
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

	/**
	 * Retorna a interface remota de ControladorGerencial
	 * 
	 * @return A interface remota do controlador de parâmetro
	 */
	private ControladorCobrancaLocal getControladorCobranca() {
		ControladorCobrancaLocalHome localHome = null;
		ControladorCobrancaLocal local = null;

		// pega a instância do ServiceLocator.

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

	private ControladorCadastroLocal getControladorCadastro() {
		ControladorCadastroLocalHome localHome = null;
		ControladorCadastroLocal local = null;

		ServiceLocator locator = null;
		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorCadastroLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_CADASTRO_SEJB);

			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	/**
	 * Retorna a interface remota de ControladorGerencial
	 * 
	 * @return A interface remota do controlador de parâmetro
	 */
	/*private ControladorGerencialCadastroLocal getControladorGerencialCadastro() {
		ControladorGerencialCadastroLocalHome localHome = null;
		ControladorGerencialCadastroLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorGerencialCadastroLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_GERENCIAL_CADASTRO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}*/

	/**
	 * Retorna o controladorOrdemServico
	 * 
	 * @author Leonardo Regis
	 * @date 18/08/2006
	 * 
	 */
	private ControladorOrdemServicoLocal getControladorOrdemServico() {
		ControladorOrdemServicoLocalHome localHome = null;
		ControladorOrdemServicoLocal local = null;

		ServiceLocator locator = null;
		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorOrdemServicoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_ORDEM_SERVICO_SEJB);

			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	/**
	 * Retorna a interface remota de ControladorGerencial
	 * 
	 * @return A interface remota do controlador de parâmetro
	 */
	private ControladorFinanceiroLocal getControladorFinanceiro() {
		ControladorFinanceiroLocalHome localHome = null;
		ControladorFinanceiroLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorFinanceiroLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FINANCEIRO_SEJB);
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
	 * Retorna a interface remota de ControladorGerencial
	 * 
	 * @return A interface remota do controlador de parâmetro
	 */
	/*private ControladorGerencialFaturamentoLocal getControladorGerencialFaturamento() {
		ControladorGerencialFaturamentoLocalHome localHome = null;
		ControladorGerencialFaturamentoLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorGerencialFaturamentoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_GERENCIAL_FATURAMENTO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}*/

	/**
	 * Retorna a interface remota de ControladorGerencial
	 * 
	 * @return A interface remota do controlador de parâmetro
	 */
	/*private ControladorGerencialCobrancaLocal getControladorGerencialCobranca() {
		ControladorGerencialCobrancaLocalHome localHome = null;
		ControladorGerencialCobrancaLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorGerencialCobrancaLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_GERENCIAL_COBRANCA_SEJB);
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
*/
	/**
	 * Retorna o valor de controladorArrecadacao
	 * 
	 * @return O valor de controladorCliente
	 */
	private ControladorArrecadacaoLocal getControladorArrecadacao() {
		ControladorArrecadacaoLocalHome localHome = null;
		ControladorArrecadacaoLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorArrecadacaoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_ARRECADACAO_SEJB);
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
	 * Retorna o valor de controladorImovel
	 * 
	 * @return O valor de controladorImovel
	 */
	private ControladorImovelLocal getControladorImovel() {
		ControladorImovelLocalHome localHome = null;
		ControladorImovelLocal local = null;

		// pega a instância do ServiceLocator.

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
	 * Retorna o valor de controladorLocalidade
	 * 
	 * @return O valor de controladorLocalidade
	 */
	private ControladorFaturamentoLocal getControladorFaturamento() {
		ControladorFaturamentoLocalHome localHome = null;
		ControladorFaturamentoLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorFaturamentoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FATURAMENTO_SEJB);
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
	 * Retorna a interface remota de ControladorGerencial
	 * 
	 * @return A interface remota do controlador de parâmetro
	 */
	private ControladorGerencialMicromedicaoLocal getControladorGerencialMicromedicao() {
		ControladorGerencialMicromedicaoLocalHome localHome = null;
		ControladorGerencialMicromedicaoLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorGerencialMicromedicaoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_GERENCIAL_MICROMEDICAO_SEJB);
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
	 * [UC0319] Gerar Movimento de Débito Automático para o banco
	 * 
	 * pesquisa os movimentos de débito automático para o banco,referentes ao
	 * grupo e ano/mês de faturamento informados
	 * 
	 * [SB0002] - Carregar Lista de Bancos
	 * 
	 * @author Sávio Luiz
	 * @date 18/04/2006
	 * 
	 * @param idFaturamentoGrupo,anoMesReferenciaFaturamento,idBanco
	 * @return Coleção de DebitoAutomaticoMovimento
	 * @throws ControladorException
	 * 
	 * 
	 * public Collection<DebitoAutomaticoMovimento>
	 * pesquisaDebitoAutomaticoMovimento( Integer idFaturamentoGrupo, Integer
	 * anoMesReferenciaFaturamento, Integer idBanco){ try { return
	 * this.getControladorArrecadacao().pesquisaDebitoAutomaticoMovimento(idFaturamentoGrupo,anoMesReferenciaFaturamento,
	 * idBanco); } catch (ControladorException ex) { throw new
	 * FachadaException(ex.getMessage(), ex, ex .getParametroMensagem()); } }
	 * 
	 */

	/**
	 * Método que gera o resumo das ligações e economias
	 * 
	 * [UC0275] - Gerar Resumo das Ligações/Economias
	 * 
	 * @author Thiago Toscano
	 * @date 19/04/2006
	 */
	/*
	 * public void gerarResumoLigacoesEconomias() throws ErroEmailException {
	 * try { this.getControladorGerencialCadastro()
	 * .gerarResumoLigacoesEconomias();
	 * ServicosEmail.enviarMensagem("gcom@compesa.com.br",
	 * "aryed@compesa.com.br", "Projeto GCOM", "Aviso de Conclusão do Batch",
	 * "[UC0275] - gerarResumoLigacoesEconomias finalizou"); } catch (Exception
	 * ex) { ex.printStackTrace(); ServicosEmail
	 * .enviarMensagem("gcom@compesa.com.br", "aryed@compesa.com.br", "Projeto
	 * GCOM", "Aviso de Conclusão do Batch", "[UC0275] -
	 * gerarResumoLigacoesEconomias finalizou com FALHA"); } }
	 */
	/**
	 * Método que gera o resumo das situacoes especial de faturamento
	 * 
	 * [UC0341] -
	 * 
	 * @author Thiago Toscano
	 * @date 19/04/2006
	 */
	/*
	 * public void gerarResumoSituacaoEspecialFaturamento() throws
	 * ErroEmailException { try { this.getControladorGerencialFaturamento()
	 * .gerarResumoSituacaoEspecialFaturamento(); ServicosEmail
	 * .enviarMensagem("gcom@compesa.com.br", "aryed@compesa.com.br", "Projeto
	 * GCOM", "Aviso de Conclusão do Batch", "[UC0341] -
	 * gerarResumoSituacaoEspecialFaturamento finalizou"); } catch (Exception
	 * ex) { ex.printStackTrace(); ServicosEmail
	 * .enviarMensagem("gcom@compesa.com.br", "aryed@compesa.com.br", "Projeto
	 * GCOM", "Aviso de Conclusão do Batch", "[UC0341] -
	 * gerarResumoSituacaoEspecialFaturamento finalizou com FALHA"); } }
	 */
	/**
	 * Método que gera o resumo das situacoes especial de Cobranca
	 * 
	 * [UC0346] -
	 * 
	 * @author Thiago Toscano
	 * @throws ErroEmailException
	 * @date 19/04/2006
	 */
	public void gerarResumoSituacaoEspecialCobranca() throws ErroEmailException {
		try {
			EnvioEmail envioEmail = getControladorCadastro()
					.pesquisarEnvioEmail(
							EnvioEmail.GERAR_RESUMO_SITUACAO_ESPECIAL_COBRANCA);
			String remetente = envioEmail.getEmailRemetente();
			String receptor = envioEmail.getEmailReceptor();
			String titulo = envioEmail.getTituloMensagem();
			String corpoMensagem = envioEmail.getCorpoMensagem();
			/*
			 * this.getControladorGerencialCobranca()
			 * .gerarResumoSituacaoEspecialCobranca();
			 */ServicosEmail.enviarMensagem(remetente, receptor, titulo,
					corpoMensagem);

		} catch (Exception ex) {
			try {
				EnvioEmail envioEmail = getControladorCadastro()
						.pesquisarEnvioEmail(
								EnvioEmail.GERAR_RESUMO_SITUACAO_ESPECIAL_COBRANCA_FALHA);
				String remetente = envioEmail.getEmailRemetente();
				String receptor = envioEmail.getEmailReceptor();
				String titulo = envioEmail.getTituloMensagem();
				String corpoMensagem = envioEmail.getCorpoMensagem();
				ex.printStackTrace();
				ServicosEmail.enviarMensagem(remetente, receptor, titulo,
						corpoMensagem);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public Categoria obterPrincipalCategoriaImovel(Integer idImovel) {
		try {
			return this.getControladorImovel().obterPrincipalCategoriaImovel(
					idImovel);
		} catch (ControladorException ex) {
			throw new FachadaException(ex.getMessage(), ex, ex
					.getParametroMensagem());
		}
	}

	/**
	 * Este caso de uso fera o resumo da pendência
	 * 
	 * [UC0335] Gerar Resumo da Pendência
	 * 
	 * <Breve descrição sobre o subfluxo>
	 * 
	 * gerarResumoPendencia
	 * 
	 * @author Roberta Costa
	 * @date 15/05/2006
	 * 
	 * @throws ControladorException
	 */
	/*
	 * public void gerarResumoPendencia() throws ErroEmailException { try {
	 * this.getControladorGerencialCobranca().gerarResumoPendencia(890);
	 * ServicosEmail.enviarMensagem("gcom@compesa.com.br",
	 * "aryed@compesa.com.br", "Projeto GCOM", "Aviso de Conclusão do Batch",
	 * "[UC0335] - gerarResumoPendencia finalizou"); } catch (Exception ex) {
	 * ex.printStackTrace(); ServicosEmail.enviarMensagem("gcom@compesa.com.br",
	 * "aryed@compesa.com.br", "Projeto GCOM", "Aviso de Conclusão do Batch",
	 * "[UC0335] - gerarResumoPendencia finalizou com FALHA"); } }
	 */

	/**
	 * Este caso de uso fera o resumo anormalidade
	 * 
	 * [UC0343] Gerar Resumo de Anormalidade
	 * 
	 * <Breve descrição sobre o subfluxo>
	 * 
	 * 
	 * @author Flávio Cordeiro
	 * @throws ErroEmailException
	 * @date 15/05/2006
	 * 
	 * @throws ControladorException
	 */
	public void gerarResumoAnormalidadeConsumo() throws ErroEmailException {
		try {
			this.getControladorGerencialMicromedicao()
					.gerarResumoAnormalidadeConsumo();
			EnvioEmail envioEmail = getControladorCadastro()
					.pesquisarEnvioEmail(
							EnvioEmail.GERAR_RESUMO_ANORMALIDADE_CONSUMO);
			String remetente = envioEmail.getEmailRemetente();
			String receptor = envioEmail.getEmailReceptor();
			String titulo = envioEmail.getTituloMensagem();
			String corpoMensagem = envioEmail.getCorpoMensagem();
			ServicosEmail.enviarMensagem(remetente, receptor, titulo,
					corpoMensagem);

		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				EnvioEmail envioEmail = getControladorCadastro()
						.pesquisarEnvioEmail(
								EnvioEmail.GERAR_RESUMO_ANORMALIDADE_CONSUMO_FALHA);
				String remetente = envioEmail.getEmailRemetente();
				String receptor = envioEmail.getEmailReceptor();
				String titulo = envioEmail.getTituloMensagem();
				String corpoMensagem = envioEmail.getCorpoMensagem();
				ServicosEmail.enviarMensagem(remetente, receptor, titulo,
						corpoMensagem);
			} catch (Exception e) {
				ex.printStackTrace();
			}

		}
	}

	/**
	 * Este caso de uso fera o resumo da pendência
	 * 
	 * [UC0335] Gerar Resumo da Pendência
	 * 
	 * <Breve descrição sobre o subfluxo>
	 * 
	 * gerarResumoPendencia
	 * 
	 * @author Roberta Costa
	 * @date 15/05/2006
	 * 
	 * @throws ControladorException
	 */
	public void gerarResumoAnormalidadeLeitura() throws ErroEmailException {
		try {
			/*
			 * this.getControladorGerencialMicromedicao()
			 * .gerarResumoAnormalidadeLeitura();
			 */EnvioEmail envioEmail = getControladorCadastro()
					.pesquisarEnvioEmail(
							EnvioEmail.GERAR_RESUMO_ANORMALIDADE_LEITURA);
			String remetente = envioEmail.getEmailRemetente();
			String receptor = envioEmail.getEmailReceptor();
			String titulo = envioEmail.getTituloMensagem();
			String corpoMensagem = envioEmail.getCorpoMensagem();
			ServicosEmail.enviarMensagem(remetente, receptor, titulo,
					corpoMensagem);

		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				EnvioEmail envioEmail = getControladorCadastro()
						.pesquisarEnvioEmail(
								EnvioEmail.GERAR_RESUMO_ANORMALIDADE_LEITURA_FALHA);
				String remetente = envioEmail.getEmailRemetente();
				String receptor = envioEmail.getEmailReceptor();
				String titulo = envioEmail.getTituloMensagem();
				String corpoMensagem = envioEmail.getCorpoMensagem();
				ServicosEmail.enviarMensagem(remetente, receptor, titulo,
						corpoMensagem);
			} catch (Exception e) {
				ex.printStackTrace();
			}

		}
	}

	/**
	 * Metódo responsável por emitir os txts das contas.
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * @author Sávio Luiz
	 * @date 15/05/2006
	 * 
	 * @param colecaoConta
	 * @throws ControladorException
	 */
	/*
	 * public void emitirContas(Collection colecaoContas) throws
	 * ErroEmailException { try {
	 * this.getControladorFaturamento().emitirContas();
	 * ServicosEmail.enviarMensagem("gcom@compesa.com.br",
	 * "aryed@compesa.com.br", "Projeto GCOM", "Aviso de Conclusão do Batch",
	 * "[UC0348] - emitirContas finalizou"); } catch (Exception ex) {
	 * ex.printStackTrace(); ServicosEmail.enviarMensagem("gcom@compesa.com.br",
	 * "aryed@compesa.com.br", "Projeto GCOM", "Aviso de Conclusão do Batch",
	 * "[UC0348] - emitirContas finalizou com FALHA"); } }
	 */
	/**
	 * 
	 * Este caso de uso permite a emissão de um ou mais documentos de cobrança
	 * 
	 * [UC0349] Emitir Documento de Cobrança
	 * 
	 * @author Raphael Rossiter
	 * @data 26/05/2006
	 * 
	 * @param
	 * @return void
	 */
	/*
	 * public void emitirDocumentoCobranca() throws ErroEmailException { try { //
	 * this.getControladorCobranca().emitirDocumentoCobranca();
	 * ServicosEmail.enviarMensagem("gcom@compesa.com.br",
	 * "aryed@compesa.com.br", "Projeto GCOM", "Aviso de Conclusão do Batch",
	 * "[UC0349] - emitirDocumentoCobranca finalizou"); } catch (Exception ex) {
	 * ex.printStackTrace(); try{ EnvioEmail envioEmail =
	 * getControladorCadastro().pesquisarEnvioEmail(EnvioEmail.GERAR_RESUMO_ANORMALIDADE_LEITURA_FALHA);
	 * String remetente = envioEmail.getEmailRemetente(); String receptor =
	 * envioEmail.getEmailReceptor(); String titulo =
	 * envioEmail.getTituloMensagem(); String corpoMensagem =
	 * envioEmail.getCorpoMensagem(); ServicosEmail .enviarMensagem(remetente,
	 * receptor, titulo, corpoMensagem); } catch (Exception e) {
	 * ex.printStackTrace(); } } }
	 */

	/**
	 * Pesquisa todas as contas para testar o batch
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 02/06/2006
	 * 
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarIdsTodasConta() throws ErroEmailException {
		Collection retorno = null;
		try {
			retorno = this.getControladorFaturamento().pesquisarIdsTodasConta();
			EnvioEmail envioEmail = getControladorCadastro()
					.pesquisarEnvioEmail(EnvioEmail.PESQUISAR_IDS_TODAS_CONTAS);
			String remetente = envioEmail.getEmailRemetente();
			String receptor = envioEmail.getEmailReceptor();
			String titulo = envioEmail.getTituloMensagem();
			String corpoMensagem = envioEmail.getCorpoMensagem();
			ServicosEmail.enviarMensagem(remetente, receptor, titulo,
					corpoMensagem);

			return retorno;

		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				EnvioEmail envioEmail = getControladorCadastro()
						.pesquisarEnvioEmail(
								EnvioEmail.PESQUISAR_IDS_TODAS_CONTAS_FALHA);
				String remetente = envioEmail.getEmailRemetente();
				String receptor = envioEmail.getEmailReceptor();
				String titulo = envioEmail.getTituloMensagem();
				String corpoMensagem = envioEmail.getCorpoMensagem();
				ServicosEmail.enviarMensagem(remetente, receptor, titulo,
						corpoMensagem);
			} catch (Exception e) {
				ex.printStackTrace();
			}

		}
		return retorno;
	}

	/**
	 * Este caso de uso permite classificar os pagamentos e as devoluções no mês
	 * de arrecadação corrente
	 * 
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * @author Raphael Rossiter
	 * @date 18/04/2006
	 * 
	 * @param
	 * @return void
	 */
	public void classificarPagamentosDevolucoes() throws ErroEmailException {
			Collection<Integer> localidade = new ArrayList();
			
			localidade.add(123);
			
			try {
				this.getControladorArrecadacao().classificarPagamentosDevolucoes(localidade,0);
			} catch (ControladorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	/**
	 * Este caso de uso gera todas as faturas do cliente responsável pelo imóvel
	 * 
	 * [UC0320] Gerar Fatura de Cliente Responsável
	 * 
	 * @author Pedro Alexandre
	 * @date 02/06/2006
	 * 
	 */
	public void gerarFaturaClienteResponsavel(Integer idFuncionalidadeIniciada) throws ErroEmailException {
		try {
			this.getControladorFaturamento().gerarFaturaClienteResponsavel(idFuncionalidadeIniciada);
			EnvioEmail envioEmail = getControladorCadastro()
					.pesquisarEnvioEmail(
							EnvioEmail.GERAR_FATURAMENTO_CLIENTE_RESPONSAVEL);
			String remetente = envioEmail.getEmailRemetente();
			String receptor = envioEmail.getEmailReceptor();
			String titulo = envioEmail.getTituloMensagem();
			String corpoMensagem = envioEmail.getCorpoMensagem();
			ServicosEmail.enviarMensagem(remetente, receptor, titulo,
					corpoMensagem);

		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				EnvioEmail envioEmail = getControladorCadastro()
						.pesquisarEnvioEmail(
								EnvioEmail.GERAR_FATURAMENTO_CLIENTE_RESPONSAVEL_FALHA);
				String remetente = envioEmail.getEmailRemetente();
				String receptor = envioEmail.getEmailReceptor();
				String titulo = envioEmail.getTituloMensagem();
				String corpoMensagem = envioEmail.getCorpoMensagem();
				ServicosEmail.enviarMensagem(remetente, receptor, titulo,
						corpoMensagem);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * Este caso de uso emite(via txt) todas as faturas do cliente responsável
	 * pelo imóvel
	 * 
	 * [UC0321] Emitir Fatura de Cliente Responsável
	 * 
	 * @author Pedro Alexandre
	 * @date 02/06/2006
	 * 
	 */
	/*
	 * public void emitirFaturaClienteResponsavel() throws ErroEmailException {
	 * try { this.getControladorFaturamento().gerarFaturaClienteResponsavel();
	 * ServicosEmail.enviarMensagem("gcom@compesa.com.br",
	 * "aryed@compesa.com.br", "Projeto GCOM", "Aviso de Conclusão do Batch",
	 * "[UC0321] - emitirFaturaClienteResponsavel finalizou"); } catch
	 * (Exception ex) { ex.printStackTrace(); ServicosEmail
	 * .enviarMensagem("gcom@compesa.com.br", "aryed@compesa.com.br", "Projeto
	 * GCOM", "Aviso de Conclusão do Batch", "[UC0321] -
	 * emitirFaturaClienteResponsavel finalizou com FALHA"); } }
	 */

	/**
	 * Encerra a arrecadação do ano/mês atual
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 16/05/2006
	 * 
	 * @throws ControladorException
	 */
	public void encerrarArrecadacaoMes(
			Collection<Integer> colecaoIdsLocalidades,
			int idFuncionalidadeIniciada) throws ErroEmailException {
		try {
			this.getControladorArrecadacao().encerrarArrecadacaoMes(
					colecaoIdsLocalidades, idFuncionalidadeIniciada);
			EnvioEmail envioEmail = getControladorCadastro()
					.pesquisarEnvioEmail(EnvioEmail.ENCERRAR_ARRECADACAO_MES);
			String remetente = envioEmail.getEmailRemetente();
			String receptor = envioEmail.getEmailReceptor();
			String titulo = envioEmail.getTituloMensagem();
			String corpoMensagem = envioEmail.getCorpoMensagem();
			ServicosEmail.enviarMensagem(remetente, receptor, titulo,
					corpoMensagem);

		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				EnvioEmail envioEmail = getControladorCadastro()
						.pesquisarEnvioEmail(
								EnvioEmail.ENCERRAR_ARRECADACAO_MES_FALHA);
				String remetente = envioEmail.getEmailRemetente();
				String receptor = envioEmail.getEmailReceptor();
				String titulo = envioEmail.getTituloMensagem();
				String corpoMensagem = envioEmail.getCorpoMensagem();
				ServicosEmail.enviarMensagem(remetente, receptor, titulo,
						corpoMensagem);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * Gerar dados diários da arrecadação
	 * 
	 * [UC0301] Gerar Dados Diários da Arrecadação
	 * 
	 * @author Pedro Alexandre
	 * @date 16/05/2006
	 * 
	 * @throws ControladorException
	 */
	/*
	 * public void gerarDadosDiariosArrecadacao() throws ErroEmailException {
	 * try { // this.getControladorArrecadacao().gerarDadosDiariosArrecadacao();
	 * ServicosEmail.enviarMensagem("gcom@compesa.com.br",
	 * "aryed@compesa.com.br", "Projeto GCOM", "Aviso de Conclusão do Batch",
	 * "[UC0301] - gerarDadosDiariosArrecadacao finalizou"); } catch (Exception
	 * ex) { ex.printStackTrace(); ServicosEmail
	 * .enviarMensagem("gcom@compesa.com.br", "aryed@compesa.com.br", "Projeto
	 * GCOM", "Aviso de Conclusão do Batch", "[UC0301] -
	 * gerarDadosDiariosArrecadacao finalizou com FALHA"); } }
	 */

	/**
	 * Este caso de uso gera todas as faturas do cliente responsável pelo imóvel
	 * 
	 * @author Pedro
	 * @date 02/06/2006
	 * 
	 */
	public void gerarLancamentoContabeisArrecadacao(Integer anoMes)
			throws ErroEmailException {
		try {
			this.getControladorFinanceiro()
					.gerarLancamentoContabeisArrecadacao(anoMes,1,1);
			EnvioEmail envioEmail = getControladorCadastro()
					.pesquisarEnvioEmail(
							EnvioEmail.GERAR_MOVIMENTO_CONTABEIS_ARRECADACAO);
			String remetente = envioEmail.getEmailRemetente();
			String receptor = envioEmail.getEmailReceptor();
			String titulo = envioEmail.getTituloMensagem();
			String corpoMensagem = envioEmail.getCorpoMensagem();
			ServicosEmail.enviarMensagem(remetente, receptor, titulo,
					corpoMensagem);

		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				EnvioEmail envioEmail = getControladorCadastro()
						.pesquisarEnvioEmail(
								EnvioEmail.GERAR_MOVIMENTO_CONTABEIS_ARRECADACAO_FALHA);
				String remetente = envioEmail.getEmailRemetente();
				String receptor = envioEmail.getEmailReceptor();
				String titulo = envioEmail.getTituloMensagem();
				String corpoMensagem = envioEmail.getCorpoMensagem();
				ServicosEmail.enviarMensagem(remetente, receptor, titulo,
						corpoMensagem);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * [UC0213] Desfazer Parcelamentos Por Entrada Nao Paga
	 * 
	 * @author Fernanda Paiva
	 * @date 02/06/2006
	 * 
	 */
	public void desfazerParcelamentosPorEntradaNaoPaga()
			throws ErroEmailException {
		try {
			/*this.getControladorCobranca()
					.desfazerParcelamentosPorEntradaNaoPaga();*/
			EnvioEmail envioEmail = getControladorCadastro()
					.pesquisarEnvioEmail(
							EnvioEmail.DESFAZER_PARCELAMENTO_POR_ENTRADA_NAO_PAGA);
			String remetente = envioEmail.getEmailRemetente();
			String receptor = envioEmail.getEmailReceptor();
			String titulo = envioEmail.getTituloMensagem();
			String corpoMensagem = envioEmail.getCorpoMensagem();
			ServicosEmail.enviarMensagem(remetente, receptor, titulo,
					corpoMensagem);

		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				EnvioEmail envioEmail = getControladorCadastro()
						.pesquisarEnvioEmail(
								EnvioEmail.DESFAZER_PARCELAMENTO_POR_ENTRADA_NAO_PAGA_FALHA);
				String remetente = envioEmail.getEmailRemetente();
				String receptor = envioEmail.getEmailReceptor();
				String titulo = envioEmail.getTituloMensagem();
				String corpoMensagem = envioEmail.getCorpoMensagem();
				ServicosEmail.enviarMensagem(remetente, receptor, titulo,
						corpoMensagem);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 * 
	 * @author Rafael Pinto
	 * @date 22/11/2006
	 * 
	 * @param anoMesReferenciaContabil
	 * @throws ControladorException
	 */
//	public void gerarResumoDevedoresDuvidosos(int anoMesReferenciaContabil)
//			throws ErroEmailException {
//
//		try {
//
//			this.getControladorFinanceiro().gerarResumoDevedoresDuvidosos(
//					anoMesReferenciaContabil);
//
//			EnvioEmail envioEmail = getControladorCadastro()
//					.pesquisarEnvioEmail(
//							EnvioEmail.GERAR_RESUMO_DEVEDORES_DUVIDOSOS);
//			String remetente = envioEmail.getEmailRemetente();
//			String receptor = envioEmail.getEmailReceptor();
//			String titulo = envioEmail.getTituloMensagem();
//			String corpoMensagem = envioEmail.getCorpoMensagem();
//			ServicosEmail.enviarMensagem(remetente, receptor, titulo,
//					corpoMensagem);
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//
//			try {
//				EnvioEmail envioEmail = getControladorCadastro()
//						.pesquisarEnvioEmail(
//								EnvioEmail.GERAR_RESUMO_DEVEDORES_DUVIDOSOS_FALHA);
//				String remetente = envioEmail.getEmailRemetente();
//				String receptor = envioEmail.getEmailReceptor();
//				String titulo = envioEmail.getTituloMensagem();
//				String corpoMensagem = envioEmail.getCorpoMensagem();
//				ServicosEmail.enviarMensagem(remetente, receptor, titulo,
//						corpoMensagem);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//		}
//	}

	/**
	 * [UC0493] Emitir de Extrato de Consumo de Imóvel Condomínio
	 * 
	 * Flávio Cordeiro 08/01/2007
	 */
	/*
	 * public void emitirExtratoConsumoImovelCondominio(String
	 * anoMesFaturamento, String idFaturamento) {
	 * 
	 * this.getControladorFaturamento().emitirExtratoConsumoImovelCondominio(anoMesFaturamento,
	 * idFaturamento); }
	 */
	/**
	 * Rotina Batch que encerra todas as OS de um serviço tipo especifico que
	 * não tenha RA
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 23/02/2007
	 * 
	 * @throws ControladorException
	 */
	public void encerrarOSDoServicoTipoSemRA(Usuario usuarioLogado,
			Integer idServicoTipo) {
		try {
			this.getControladorOrdemServico().encerrarOSDoServicoTipoSemRA(
					usuarioLogado, idServicoTipo);
		} catch (Exception ex) {
			ex.printStackTrace();
			/*
			 * ServicosEmail.enviarMensagem("gcom@compesa.com.br",
			 * "aryed@compesa.com.br", "Projeto GCOM", "Aviso de Conclusão do
			 * Batch", "[UC0335] - gerarResumoPendencia finalizou com FALHA");
			 */
		}
	}

//	public void gerarResumoAcoesCobrancaCronograma() throws ErroEmailException {
//		try {
//			Collection colecaoCobrancaGrupoCronogramaMes = getControladorCobranca()
//					.pesquisarCobrancaGrupoCronogramaMes();
//
//			this.getControladorCobranca().gerarResumoAcoesCobrancaCronograma(
//					colecaoCobrancaGrupoCronogramaMes, 0);
//			/*
//			 * ServicosEmail.enviarMensagem("gcom@compesa.com.br",
//			 * "aryed@compesa.com.br", "Projeto GCOM", "Aviso de Conclusão do
//			 * Batch", "[UC0335] - gerarResumoPendencia finalizou");
//			 */
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			/*
//			 * ServicosEmail.enviarMensagem("gcom@compesa.com.br",
//			 * "aryed@compesa.com.br", "Projeto GCOM", "Aviso de Conclusão do
//			 * Batch", "[UC0335] - gerarResumoPendencia finalizou com FALHA");
//			 */
//		}
//	}

	/**
	 * Metodo criado para criar os debitos para os parcelamentos q tenham juros
	 * e nao tenha criado o debito dos juros DBTP_ID = 44
	 * 
	 * @author Flávio Cordeiro
	 * @date 23/02/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void gerarDebitoCobrarNaoCriados() {
		this.getControladorCobranca().gerarDebitoCobrarNaoCriados();
	}
	

	public void gerarHistorico() {
		try {
			Integer localidade = 12;
			Integer anomes = 200703;
			this.getControladorFaturamento().gerarHistoricoParaEncerrarFaturamento(anomes,localidade,0);
		} catch (ControladorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void encerrarArrecadacao() {
		try {
			Collection<Integer> localidade = new ArrayList();
			localidade.add(60);
			this.getControladorArrecadacao().encerrarArrecadacaoMes(localidade,0);
		} catch (ControladorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void emitirFaturaClienteResponsavel() {
		try {
			
			FiltroFatura filtroFatura = new FiltroFatura();
			filtroFatura.adicionarParametro(new ParametroSimples(FiltroFatura.ANO_MES_REFERENCIA,200703));
			
			Collection faturas = new ArrayList();
			
			faturas = getControladorUtil().pesquisar(filtroFatura, Fatura.class.getName());
			this.getControladorFaturamento().emitirFaturaClienteResponsavel(faturas, 200703);
		} catch (ControladorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void gerarLancamentosContabeisFaturamento() {
		try {
			System.out.println("Inicio " + new Date());
			Collection<Integer> idsLocalidade = this.getControladorFinanceiro().pesquisarIdsLocalidadesParaGerarLancamentosContabeisFaturamento(200703);
			
			for(Integer idLocalidade : idsLocalidade){
				this.getControladorFinanceiro().gerarLancamentosContabeisFaturamento(200702,idLocalidade,0);
			}
			
			System.out.println("Fim " + new Date());
		} catch (ControladorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void gerarLancamentosContabeisArrecadacao() {
		try {
			System.out.println("Inicio " + new Date());
			Collection<Integer> idsLocalidade = this.getControladorFinanceiro().pesquisarIdsLocalidadesParaGerarLancamentosContabeisArrecadacao(200702);
			
			for(Integer idLocalidade : idsLocalidade){
				this.getControladorFinanceiro().gerarLancamentoContabeisArrecadacao(200702,idLocalidade,0);
			}
			
			System.out.println("Fim " + new Date());
		} catch (ControladorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void efetuarRateioConsumo() {
		try {
			Collection rotas = new ArrayList();
			rotas.add(1150);
			
			this.getControladorMicromedicao().efetuarRateioDeConsumo(rotas,200705,0);
			
		} catch (ControladorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
