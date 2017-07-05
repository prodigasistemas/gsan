package gcom.util;

import gcom.arrecadacao.ControladorArrecadacaoLocal;
import gcom.arrecadacao.ControladorArrecadacaoLocalHome;
import gcom.atendimentopublico.ControladorAtendimentoPublicoLocal;
import gcom.atendimentopublico.ControladorAtendimentoPublicoLocalHome;
import gcom.atendimentopublico.ligacaoagua.ControladorLigacaoAguaLocal;
import gcom.atendimentopublico.ligacaoagua.ControladorLigacaoAguaLocalHome;
import gcom.atendimentopublico.ligacaoesgoto.ControladorLigacaoEsgotoLocal;
import gcom.atendimentopublico.ligacaoesgoto.ControladorLigacaoEsgotoLocalHome;
import gcom.atendimentopublico.ordemservico.ControladorOrdemServicoLocal;
import gcom.atendimentopublico.ordemservico.ControladorOrdemServicoLocalHome;
import gcom.atendimentopublico.registroatendimento.ControladorRegistroAtendimentoLocal;
import gcom.atendimentopublico.registroatendimento.ControladorRegistroAtendimentoLocalHome;
import gcom.atualizacaocadastral.ControladorAtualizacaoCadastralLocal;
import gcom.atualizacaocadastral.ControladorAtualizacaoCadastralLocalHome;
import gcom.batch.ControladorBatchLocal;
import gcom.batch.ControladorBatchLocalHome;
import gcom.cadastro.ControladorCadastroLocal;
import gcom.cadastro.ControladorCadastroLocalHome;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.cliente.ControladorClienteLocalHome;
import gcom.cadastro.controladores.ControladorAtualizacaoCadastroLocal;
import gcom.cadastro.controladores.ControladorAtualizacaoCadastroLocalHome;
import gcom.cadastro.endereco.ControladorEnderecoLocal;
import gcom.cadastro.endereco.ControladorEnderecoLocalHome;
import gcom.cadastro.geografico.ControladorGeograficoLocal;
import gcom.cadastro.geografico.ControladorGeograficoLocalHome;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.ControladorImovelLocalHome;
import gcom.cadastro.localidade.ControladorLocalidadeLocal;
import gcom.cadastro.localidade.ControladorLocalidadeLocalHome;
import gcom.cadastro.unidade.ControladorUnidadeLocal;
import gcom.cadastro.unidade.ControladorUnidadeLocalHome;
import gcom.cobranca.ControladorCobrancaLocal;
import gcom.cobranca.ControladorCobrancaLocalHome;
import gcom.cobranca.contratoparcelamento.ControladorContratoParcelamentoLocal;
import gcom.cobranca.contratoparcelamento.ControladorContratoParcelamentoLocalHome;
import gcom.cobranca.controladores.ControladorParcelamentoLocal;
import gcom.cobranca.controladores.ControladorParcelamentoLocalHome;
import gcom.faturamento.ControladorFaturamentoLocal;
import gcom.faturamento.ControladorFaturamentoLocalHome;
import gcom.faturamento.controladores.ControladorAnaliseGeracaoContaLocal;
import gcom.faturamento.controladores.ControladorAnaliseGeracaoContaLocalHome;
import gcom.faturamento.controladores.ControladorDebitoACobrarLocal;
import gcom.faturamento.controladores.ControladorDebitoACobrarLocalHome;
import gcom.faturamento.controladores.ControladorRetificarContaLocal;
import gcom.faturamento.controladores.ControladorRetificarContaLocalHome;
import gcom.financeiro.ControladorFinanceiroLocal;
import gcom.financeiro.ControladorFinanceiroLocalHome;
import gcom.micromedicao.ControladorMicromedicaoLocal;
import gcom.micromedicao.ControladorMicromedicaoLocalHome;
import gcom.relatorio.faturamento.ControladorRelatorioFaturamentoLocal;
import gcom.relatorio.faturamento.ControladorRelatorioFaturamentoLocalHome;
import gcom.seguranca.ControladorPermissaoEspecialLocal;
import gcom.seguranca.ControladorPermissaoEspecialLocalHome;
import gcom.seguranca.acesso.ControladorAcessoLocal;
import gcom.seguranca.acesso.ControladorAcessoLocalHome;
import gcom.seguranca.acesso.usuario.ControladorUsuarioLocal;
import gcom.seguranca.acesso.usuario.ControladorUsuarioLocalHome;
import gcom.seguranca.transacao.ControladorTransacaoLocal;
import gcom.seguranca.transacao.ControladorTransacaoLocalHome;
import gcom.spcserasa.ControladorSpcSerasaLocal;
import gcom.spcserasa.ControladorSpcSerasaLocalHome;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public abstract class ControladorComum implements SessionBean {
	private static final long serialVersionUID = -1018845073095941124L;

	protected SessionContext sessionContext;

	public void ejbActivate() throws EJBException, RemoteException {
	}

	public void ejbPassivate() throws EJBException, RemoteException {
	}

	public void ejbRemove() throws EJBException, RemoteException {
	}

	public void setSessionContext(SessionContext sessionContext) throws EJBException, RemoteException {
		this.sessionContext = sessionContext;
	}

	private ServiceLocator getLocator() {
		try {
			return ServiceLocator.getInstancia();
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	protected ControladorImovelLocal getControladorImovel() {
		try {
			ControladorImovelLocalHome localHome = (ControladorImovelLocalHome) getLocator().getLocalHome(ConstantesJNDI.CONTROLADOR_IMOVEL_SEJB);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	protected ControladorLocalidadeLocal getControladorLocalidade() {
		try {
			ControladorLocalidadeLocalHome localHome = (ControladorLocalidadeLocalHome) getLocator().getLocalHome(ConstantesJNDI.CONTROLADOR_LOCALIDADE_SEJB);

			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	protected ControladorMicromedicaoLocal getControladorMicromedicao() {
		try {
			ControladorMicromedicaoLocalHome localHome = (ControladorMicromedicaoLocalHome) getLocator().getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_MICROMEDICAO_SEJB);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	protected ControladorSpcSerasaLocal getControladorSpcSerasa() {
		try {
			ControladorSpcSerasaLocalHome localHome = (ControladorSpcSerasaLocalHome) getLocator().getLocalHome(ConstantesJNDI.CONTROLADOR_SPC_SERASA_SEJB);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	protected ControladorBatchLocal getControladorBatch() {
		try {
			ControladorBatchLocalHome localHome = (ControladorBatchLocalHome) getLocator().getLocalHome(ConstantesJNDI.CONTROLADOR_BATCH_SEJB);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	protected ControladorLigacaoAguaLocal getControladorLigacaoAgua() {
		try {
			ControladorLigacaoAguaLocalHome localHome = (ControladorLigacaoAguaLocalHome) getLocator().getLocalHome(ConstantesJNDI.CONTROLADOR_LIGACAO_AGUA_SEJB);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	protected ControladorLigacaoEsgotoLocal getControladorLigacaoEsgoto() {
		try {
			ControladorLigacaoEsgotoLocalHome localHome = (ControladorLigacaoEsgotoLocalHome) getLocator().getLocalHome(ConstantesJNDI.CONTROLADOR_LIGACAO_ESGOTO_SEJB);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	protected ControladorAtendimentoPublicoLocal getControladorAtendimentoPublico() {
		try {
			ControladorAtendimentoPublicoLocalHome localHome = (ControladorAtendimentoPublicoLocalHome) getLocator().getLocalHome(ConstantesJNDI.CONTROLADOR_ATENDIMENTO_PUBLICO_SEJB);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	protected ControladorAcessoLocal getControladorAcesso() {
		try {
			ControladorAcessoLocalHome localHome = (ControladorAcessoLocalHome) getLocator().getLocalHome(ConstantesJNDI.CONTROLADOR_ACESSO_SEJB);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	protected ControladorFinanceiroLocal getControladorFinanceiro() {
		try {
			ControladorFinanceiroLocalHome localHome = (ControladorFinanceiroLocalHome) getLocator().getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FINANCEIRO_SEJB);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	protected ControladorArrecadacaoLocal getControladorArrecadacao() {
		try {
			ControladorArrecadacaoLocalHome localHome = (ControladorArrecadacaoLocalHome) getLocator().getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_ARRECADACAO_SEJB);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	protected ControladorCobrancaLocal getControladorCobranca() {
		try {
			ControladorCobrancaLocalHome localHome = (ControladorCobrancaLocalHome) getLocator().getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_COBRANCA_SEJB);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	protected ControladorEnderecoLocal getControladorEndereco() {
		try {
			ControladorEnderecoLocalHome localHome = (ControladorEnderecoLocalHome) getLocator().getLocalHome(ConstantesJNDI.CONTROLADOR_ENDERECO_SEJB);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}

	protected ControladorClienteLocal getControladorCliente() {
		try {
			ControladorClienteLocalHome localHome = (ControladorClienteLocalHome) getLocator().getLocalHome(ConstantesJNDI.CONTROLADOR_CLIENTE_SEJB);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	protected ControladorGeograficoLocal getControladorGeografico() {
		try {
			ControladorGeograficoLocalHome localHome = (ControladorGeograficoLocalHome) getLocator().getLocalHome(ConstantesJNDI.CONTROLADOR_GEOGRAFICO_SEJB);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	protected ControladorRegistroAtendimentoLocal getControladorRegistroAtendimento() {
		try {
			ControladorRegistroAtendimentoLocalHome localHome = (ControladorRegistroAtendimentoLocalHome) getLocator().getLocalHome(ConstantesJNDI.CONTROLADOR_REGISTRO_ATENDIMENTO_SEJB);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	protected ControladorUtilLocal getControladorUtil() {
		try {
			ControladorUtilLocalHome localHome = (ControladorUtilLocalHome) getLocator().getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	protected ControladorAnaliseGeracaoContaLocal getControladorAnaliseGeracaoConta() {
		try {
			ControladorAnaliseGeracaoContaLocalHome localHome = (ControladorAnaliseGeracaoContaLocalHome) getLocator().getLocalHome(ConstantesJNDI.CONTROLADOR_ANALISE_GERACAO_CONTA);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	protected ControladorDebitoACobrarLocal getControladorDebitoACobrar() {
		try {
			ControladorDebitoACobrarLocalHome localHome = (ControladorDebitoACobrarLocalHome) getLocator().getLocalHome(ConstantesJNDI.CONTROLADOR_DEBITO_A_COBRAR);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	protected ControladorCadastroLocal getControladorCadastro() {
		try {
			ControladorCadastroLocalHome localHome = (ControladorCadastroLocalHome) getLocator().getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_CADASTRO_SEJB);

			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	protected ControladorPermissaoEspecialLocal getControladorPermissaoEspecial() {
		try {
			ControladorPermissaoEspecialLocalHome localHome = (ControladorPermissaoEspecialLocalHome) getLocator().getLocalHome(ConstantesJNDI.CONTROLADOR_PERMISSAO_ESPECIAL_SEJB);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}

	protected ControladorRelatorioFaturamentoLocal getControladorRelatorioFaturamento() {
		try {
			ControladorRelatorioFaturamentoLocalHome localHome = (ControladorRelatorioFaturamentoLocalHome) getLocator().getLocalHome(ConstantesJNDI.CONTROLADOR_RELATORIO_FATURAMENTO_SEJB);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	protected ControladorContratoParcelamentoLocal getControladorContratoParcelamento() {
		try {
			ControladorContratoParcelamentoLocalHome localHome = (ControladorContratoParcelamentoLocalHome) getLocator().getLocalHome(ConstantesJNDI.CONTROLADOR_CONTRATO_PARCELAMENTO_SEJB);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	protected ControladorAtualizacaoCadastralLocal getControladorAtualizacaoCadastral() {
		try {
			ControladorAtualizacaoCadastralLocalHome localHome = (ControladorAtualizacaoCadastralLocalHome) getLocator().getLocalHome(ConstantesJNDI.CONTROLADOR_ATUALIZACAO_CADASTRAL);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	protected ControladorTransacaoLocal getControladorTransacao() {
		try {
			ControladorTransacaoLocalHome localHome = (ControladorTransacaoLocalHome) getLocator().getLocalHome(ConstantesJNDI.CONTROLADOR_TRANSACAO_SEJB);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	protected ControladorFaturamentoLocal getControladorFaturamento() {
		try {
			ControladorFaturamentoLocalHome localHome = (ControladorFaturamentoLocalHome) getLocator().getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FATURAMENTO_SEJB);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	protected ControladorUsuarioLocal getControladorUsuario() {
		try {
			ControladorUsuarioLocalHome localHome = (ControladorUsuarioLocalHome) getLocator().getLocalHome(ConstantesJNDI.CONTROLADOR_USUARIO_SEJB);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	protected ControladorUnidadeLocal getControladorUnidade() {
		try {
			ControladorUnidadeLocalHome localHome = (ControladorUnidadeLocalHome) getLocator().getLocalHome(ConstantesJNDI.CONTROLADOR_UNIDADE_SEJB);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	protected ControladorAtualizacaoCadastroLocal getControladorAtualizacaoCadastro() {
		try {
			ControladorAtualizacaoCadastroLocalHome localHome = (ControladorAtualizacaoCadastroLocalHome) getLocator().getLocalHome(ConstantesJNDI.CONTROLADOR_ATUALIZACAO_CADASTRO);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	protected ControladorOrdemServicoLocal getControladorOrdemServico() {
		try {
			ControladorOrdemServicoLocalHome localHome = (ControladorOrdemServicoLocalHome) getLocator().getLocalHome(ConstantesJNDI.CONTROLADOR_ORDEM_SERVICO_SEJB);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	protected ControladorRetificarContaLocal getControladorRetificarConta() {
		try {
			ControladorRetificarContaLocalHome localHome = (ControladorRetificarContaLocalHome) getLocator().getLocalHome(ConstantesJNDI.CONTROLADOR_RETIFICAR_CONTA);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	protected ControladorParcelamentoLocal getControladorParcelamento() {
		try {
			ControladorParcelamentoLocalHome localHome = (ControladorParcelamentoLocalHome) getLocator().getLocalHome(ConstantesJNDI.CONTROLADOR_PARCELAMENTO);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
}