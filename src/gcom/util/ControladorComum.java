package gcom.util;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import gcom.arrecadacao.ControladorArrecadacaoLocal;
import gcom.arrecadacao.ControladorArrecadacaoLocalHome;
import gcom.atendimentopublico.ControladorAtendimentoPublicoLocal;
import gcom.atendimentopublico.ControladorAtendimentoPublicoLocalHome;
import gcom.atendimentopublico.ligacaoagua.ControladorLigacaoAguaLocal;
import gcom.atendimentopublico.ligacaoagua.ControladorLigacaoAguaLocalHome;
import gcom.atendimentopublico.ligacaoesgoto.ControladorLigacaoEsgotoLocal;
import gcom.atendimentopublico.ligacaoesgoto.ControladorLigacaoEsgotoLocalHome;
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
import gcom.faturamento.ControladorFaturamentoLocal;
import gcom.faturamento.ControladorFaturamentoLocalHome;
import gcom.faturamento.controladores.ControladorAnaliseGeracaoContaLocal;
import gcom.faturamento.controladores.ControladorAnaliseGeracaoContaLocalHome;
import gcom.faturamento.controladores.ControladorDebitoACobrarLocal;
import gcom.faturamento.controladores.ControladorDebitoACobrarLocalHome;
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

public abstract class ControladorComum implements SessionBean{
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
    
    protected ControladorImovelLocal getControladorImovel() {
        ControladorImovelLocalHome localHome = null;
        ControladorImovelLocal local = null;

        // pega a instância do ServiceLocator.

        ServiceLocator locator = null;

        try {
            locator = ServiceLocator.getInstancia();

            localHome = (ControladorImovelLocalHome) locator
                    .getLocalHome(ConstantesJNDI.CONTROLADOR_IMOVEL_SEJB);
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
    
    protected ControladorLocalidadeLocal getControladorLocalidade() {
        try {
            ServiceLocator locator = ServiceLocator.getInstancia();
            
            ControladorLocalidadeLocalHome localHome = (ControladorLocalidadeLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_LOCALIDADE_SEJB);
            
            ControladorLocalidadeLocal local = localHome.create();
            
            return local;
        } catch (CreateException e) {
            throw new SistemaException(e);
        } catch (ServiceLocatorException e) {
            throw new SistemaException(e);
        }
    }
    
    protected ControladorMicromedicaoLocal getControladorMicromedicao() {
        ControladorMicromedicaoLocalHome localHome = null;
        ControladorMicromedicaoLocal local = null;

        // pega a instância do ServiceLocator.
        ServiceLocator locator = null;
        try {
            locator = ServiceLocator.getInstancia();

            localHome = (ControladorMicromedicaoLocalHome) locator
                    .getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_MICROMEDICAO_SEJB);
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
    
    protected ControladorSpcSerasaLocal getControladorSpcSerasa() {
        try {
            ServiceLocator locator = ServiceLocator.getInstancia();
            
            ControladorSpcSerasaLocalHome localHome = (ControladorSpcSerasaLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_SPC_SERASA_SEJB);

            ControladorSpcSerasaLocal local = localHome.create();

            return local;
        } catch (CreateException e) {
            throw new SistemaException(e);
        } catch (ServiceLocatorException e) {
            throw new SistemaException(e);
        }
    }

    protected ControladorBatchLocal getControladorBatch() {
        try {
            ServiceLocator locator = ServiceLocator.getInstancia();

            ControladorBatchLocalHome localHome = (ControladorBatchLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_BATCH_SEJB);

            ControladorBatchLocal local = localHome.create();

            return local;
        } catch (CreateException e) {
            throw new SistemaException(e);
        } catch (ServiceLocatorException e) {
            throw new SistemaException(e);
        }
    }

    protected ControladorLigacaoAguaLocal getControladorLigacaoAgua() {
        try {
            ServiceLocator locator = ServiceLocator.getInstancia();
            
            ControladorLigacaoAguaLocalHome localHome = (ControladorLigacaoAguaLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_LIGACAO_AGUA_SEJB);

            ControladorLigacaoAguaLocal local = localHome.create();

            return local;
        } catch (CreateException e) {
            throw new SistemaException(e);
        } catch (ServiceLocatorException e) {
            throw new SistemaException(e);
        }
    }

    protected ControladorLigacaoEsgotoLocal getControladorLigacaoEsgoto() {
        try {
            ServiceLocator locator = ServiceLocator.getInstancia();
            
            ControladorLigacaoEsgotoLocalHome localHome = (ControladorLigacaoEsgotoLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_LIGACAO_ESGOTO_SEJB);

            ControladorLigacaoEsgotoLocal local = localHome.create();

            return local;
        } catch (CreateException e) {
            throw new SistemaException(e);
        } catch (ServiceLocatorException e) {
            throw new SistemaException(e);
        }
    }

    protected ControladorAtendimentoPublicoLocal getControladorAtendimentoPublico() {
        try {
            ServiceLocator locator = ServiceLocator.getInstancia();
            
            ControladorAtendimentoPublicoLocalHome localHome = (ControladorAtendimentoPublicoLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_ATENDIMENTO_PUBLICO_SEJB);

            ControladorAtendimentoPublicoLocal local = localHome.create();

            return local;
        } catch (CreateException e) {
            throw new SistemaException(e);
        } catch (ServiceLocatorException e) {
            throw new SistemaException(e);
        }
    }

    protected ControladorAcessoLocal getControladorAcesso() {
        try {
            ServiceLocator locator = ServiceLocator.getInstancia();

            ControladorAcessoLocalHome localHome = (ControladorAcessoLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_ACESSO_SEJB);

            ControladorAcessoLocal local = localHome.create();

            return local;
        } catch (CreateException e) {
            throw new SistemaException(e);
        } catch (ServiceLocatorException e) {
            throw new SistemaException(e);
        }
    }

    protected ControladorFinanceiroLocal getControladorFinanceiro() {
        try {
            ServiceLocator locator = ServiceLocator.getInstancia();

            ControladorFinanceiroLocalHome localHome = (ControladorFinanceiroLocalHome) locator.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FINANCEIRO_SEJB);

            ControladorFinanceiroLocal local = localHome.create();

            return local;
        } catch (CreateException e) {
            throw new SistemaException(e);
        } catch (ServiceLocatorException e) {
            throw new SistemaException(e);
        }
    }

    protected ControladorArrecadacaoLocal getControladorArrecadacao() {
        try {
            ServiceLocator locator = ServiceLocator.getInstancia();

            ControladorArrecadacaoLocalHome localHome = (ControladorArrecadacaoLocalHome) locator.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_ARRECADACAO_SEJB);

            ControladorArrecadacaoLocal local = localHome.create();

            return local;
        } catch (CreateException e) {
            throw new SistemaException(e);
        } catch (ServiceLocatorException e) {
            throw new SistemaException(e);
        }
    }

    protected ControladorCobrancaLocal getControladorCobranca() {
        try {
            ServiceLocator locator = ServiceLocator.getInstancia();

            ControladorCobrancaLocalHome localHome = (ControladorCobrancaLocalHome) locator.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_COBRANCA_SEJB);

            ControladorCobrancaLocal local = localHome.create();

            return local;
        } catch (CreateException e) {
            throw new SistemaException(e);
        } catch (ServiceLocatorException e) {
            throw new SistemaException(e);
        }
    }

    protected ControladorEnderecoLocal getControladorEndereco() {
        try {
            ServiceLocator locator = ServiceLocator.getInstancia();

            ControladorEnderecoLocalHome localHome = (ControladorEnderecoLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_ENDERECO_SEJB);

            ControladorEnderecoLocal local = localHome.create();

            return local;
        } catch (CreateException e) {
            throw new SistemaException(e);
        } catch (ServiceLocatorException e) {
            throw new SistemaException(e);
        }

    }

    protected ControladorClienteLocal getControladorCliente() {
        try {
            ServiceLocator locator = ServiceLocator.getInstancia();
            
            ControladorClienteLocalHome localHome = (ControladorClienteLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_CLIENTE_SEJB);
            
            ControladorClienteLocal local = localHome.create();
            
            return local;
        } catch (CreateException e) {
            throw new SistemaException(e);
        } catch (ServiceLocatorException e) {
            throw new SistemaException(e);
        }
    }

    protected ControladorGeograficoLocal getControladorGeografico() {
        try {
            ServiceLocator locator = ServiceLocator.getInstancia();
            
            ControladorGeograficoLocalHome localHome = (ControladorGeograficoLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_GEOGRAFICO_SEJB);
            
            ControladorGeograficoLocal local = localHome.create();
            
            return local;
        } catch (CreateException e) {
            throw new SistemaException(e);
        } catch (ServiceLocatorException e) {
            throw new SistemaException(e);
        }
    }

    protected ControladorRegistroAtendimentoLocal getControladorRegistroAtendimento() {
        try {
            ServiceLocator locator = ServiceLocator.getInstancia();
            
            ControladorRegistroAtendimentoLocalHome localHome = (ControladorRegistroAtendimentoLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_REGISTRO_ATENDIMENTO_SEJB);
            
            ControladorRegistroAtendimentoLocal local = localHome.create();

            return local;
        } catch (CreateException e) {
            throw new SistemaException(e);
        } catch (ServiceLocatorException e) {
            throw new SistemaException(e);
        }
    }
    
    protected ControladorUtilLocal getControladorUtil() {
        try {
            ServiceLocator locator = ServiceLocator.getInstancia();

            ControladorUtilLocalHome localHome = (ControladorUtilLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);

            ControladorUtilLocal local = localHome.create();

            return local;
        } catch (CreateException e) {
            throw new SistemaException(e);
        } catch (ServiceLocatorException e) {
            throw new SistemaException(e);
        }
    }    
    
    protected ControladorAnaliseGeracaoContaLocal getControladorAnaliseGeracaoConta() {
        try {
            ServiceLocator locator = ServiceLocator.getInstancia();

            ControladorAnaliseGeracaoContaLocalHome localHome = (ControladorAnaliseGeracaoContaLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_ANALISE_GERACAO_CONTA);

            ControladorAnaliseGeracaoContaLocal local = localHome.create();

            return local;
        } catch (CreateException e) {
            throw new SistemaException(e);
        } catch (ServiceLocatorException e) {
            throw new SistemaException(e);
        }
    }    

    protected ControladorDebitoACobrarLocal getControladorDebitoACobrar() {
        try {
            ServiceLocator locator = ServiceLocator.getInstancia();
            
            ControladorDebitoACobrarLocalHome localHome = (ControladorDebitoACobrarLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_DEBITO_A_COBRAR);
            
            ControladorDebitoACobrarLocal local = localHome.create();
            
            return local;
        } catch (CreateException e) {
            throw new SistemaException(e);
        } catch (ServiceLocatorException e) {
            throw new SistemaException(e);
        }
    }    
    
    protected ControladorCadastroLocal getControladorCadastro() {
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

    protected ControladorPermissaoEspecialLocal getControladorPermissaoEspecial() {
        try {
            ServiceLocator locator = ServiceLocator.getInstancia();

            ControladorPermissaoEspecialLocalHome localHome = (ControladorPermissaoEspecialLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_PERMISSAO_ESPECIAL_SEJB);

            ControladorPermissaoEspecialLocal local = localHome.create();

            return local;
        } catch (CreateException e) {
            throw new SistemaException(e);
        } catch (ServiceLocatorException e) {
            throw new SistemaException(e);
        }

    }

    protected ControladorRelatorioFaturamentoLocal getControladorRelatorioFaturamento() {
        try {
            ServiceLocator locator = ServiceLocator.getInstancia();

            ControladorRelatorioFaturamentoLocalHome localHome = (ControladorRelatorioFaturamentoLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_RELATORIO_FATURAMENTO_SEJB);

            ControladorRelatorioFaturamentoLocal local = localHome.create();

            return local;
        } catch (CreateException e) {
            throw new SistemaException(e);
        } catch (ServiceLocatorException e) {
            throw new SistemaException(e);
        }
    }

    protected ControladorContratoParcelamentoLocal getControladorContratoParcelamento() {
        try {
            ServiceLocator locator = ServiceLocator.getInstancia();

            ControladorContratoParcelamentoLocalHome localHome = (ControladorContratoParcelamentoLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_CONTRATO_PARCELAMENTO_SEJB);

            ControladorContratoParcelamentoLocal local = localHome.create();

            return local;
        } catch (CreateException e) {
            throw new SistemaException(e);
        } catch (ServiceLocatorException e) {
            throw new SistemaException(e);
        }
    }
    
    protected ControladorAtualizacaoCadastralLocal getControladorAtualizacaoCadastral() {
        try {
            ServiceLocator locator = ServiceLocator.getInstancia();

            ControladorAtualizacaoCadastralLocalHome localHome = (ControladorAtualizacaoCadastralLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_ATUALIZACAO_CADASTRAL);

            ControladorAtualizacaoCadastralLocal local = localHome.create();

            return local;
        } catch (CreateException e) {
            throw new SistemaException(e);
        } catch (ServiceLocatorException e) {
            throw new SistemaException(e);
        }
    }
    
    protected ControladorTransacaoLocal getControladorTransacao() {
        try {
            ServiceLocator locator = ServiceLocator.getInstancia();

            ControladorTransacaoLocalHome localHome = (ControladorTransacaoLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_TRANSACAO_SEJB);

            ControladorTransacaoLocal local = localHome.create();

            return local;
        } catch (CreateException e) {
            throw new SistemaException(e);
        } catch (ServiceLocatorException e) {
            throw new SistemaException(e);
        }
    }
    
    protected ControladorFaturamentoLocal getControladorFaturamento() {
        try {
            ServiceLocator locator = ServiceLocator.getInstancia();

            ControladorFaturamentoLocalHome localHome = (ControladorFaturamentoLocalHome) locator.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FATURAMENTO_SEJB);
            ControladorFaturamentoLocal local = localHome.create();

            return local;
        } catch (CreateException e) {
            throw new SistemaException(e);
        } catch (ServiceLocatorException e) {
            throw new SistemaException(e);
        }
    }
    
	protected ControladorUsuarioLocal getControladorUsuario() {
		try {
			ServiceLocator locator = ServiceLocator.getInstancia();

			ControladorUsuarioLocalHome localHome = (ControladorUsuarioLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_USUARIO_SEJB);

			ControladorUsuarioLocal local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	protected ControladorUnidadeLocal getControladorUnidade() {
		try {
			ServiceLocator locator = ServiceLocator.getInstancia();

			ControladorUnidadeLocalHome localHome = (ControladorUnidadeLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_UNIDADE_SEJB);

			ControladorUnidadeLocal local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
}