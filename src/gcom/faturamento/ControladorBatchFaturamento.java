package gcom.faturamento;

import gcom.cadastro.ControladorCadastroLocal;
import gcom.cadastro.ControladorCadastroLocalHome;
import gcom.cadastro.EnvioEmail;
import gcom.cadastro.endereco.ControladorEnderecoLocal;
import gcom.cadastro.endereco.ControladorEnderecoLocalHome;
import gcom.cobranca.ControladorCobrancaLocal;
import gcom.cobranca.ControladorCobrancaLocalHome;
import gcom.faturamento.conta.ContaMotivoCancelamento;
import gcom.faturamento.conta.ContaMotivoRetificacao;
import gcom.faturamento.controladores.ControladorRetificarContaLocal;
import gcom.faturamento.controladores.ControladorRetificarContaLocalHome;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;
import gcom.util.MetodosBatch;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.email.ErroEmailException;
import gcom.util.email.ServicosEmail;

import java.util.Collection;
import java.util.Date;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * @ejb.bean name="ControladorBatchFaturamento" display-name="Name for
 *           ControladorBatchFaturamento" description="Description for
 *           ControladorBatchFaturamento" destination-type="javax.jms.Queue"
 *           acknowledge-mode="Auto-acknowledge"
 */
public class ControladorBatchFaturamento implements MessageDrivenBean,
		MessageListener {
	private static final long serialVersionUID = 1L;

	public ControladorBatchFaturamento() {
		super();
	}

	public void setMessageDrivenContext(MessageDrivenContext ctx)
			throws EJBException {

	}

	public void ejbRemove() throws EJBException {
	}

	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {
			Usuario usuarioLogado = null;
			String mensagemEmail = "";
			String assuntoEmail = "";
			
			ObjectMessage objectMessage = (ObjectMessage) message;
			try {
				switch (objectMessage.getIntProperty("nomeMetodo")) {
				case (MetodosBatch.ENDERECO_INSERIR_CEP_IMPORTADOS): {
					this.getControladorEndereco()
							.inserirCepImportados(
									(Collection) ((Object[]) objectMessage
											.getObject())[0]);
					break;

				}
				case (MetodosBatch.GERAR_BOLSA_AGUA):{
		/*			this.getControladorRetificarConta().chamarGerarBolsaAgua(
							(Collection) ((Object[]) objectMessage.getObject())[0],
							(Integer) ((Object[]) objectMessage.getObject())[1],
							(ContaMotivoRetificacao) ((Object[]) objectMessage.getObject())[2],
							(Collection) ((Object[]) objectMessage.getObject())[3], 
							(Usuario) ((Object[]) objectMessage.getObject())[4],
							(Date) ((Object[]) objectMessage.getObject())[5], 
							(Date) ((Object[]) objectMessage.getObject())[6],
							(Integer) ((Object[]) objectMessage.getObject())[7],
							(String) ((Object[]) objectMessage.getObject())[8]);
					usuarioLogado = (Usuario) ((Object[]) objectMessage.getObject())[4];
					assuntoEmail = 	"GERAR CREDITO BOLSA AGUA";  */
					break;
				}
				case (MetodosBatch.GERAR_RELATORIO_MAPA_CONTROLE_CONTA): {
					this.getControladorFaturamento()
							.filtrarMapaControleContaRelatorio(
									(Integer) ((Object[]) objectMessage.getObject())[0],
									(String) ((Object[]) objectMessage.getObject())[1],
									(Usuario) ((Object[]) objectMessage.getObject())[2],
									(String) ((Object[]) objectMessage.getObject())[3],
									(String) ((Object[]) objectMessage.getObject())[4]);
					break;

				}
				case (MetodosBatch.GERAR_ARQUIVO_TEXTO_FATURAMENTO): {
					this.getControladorFaturamento().chamarGerarArquivoTextoFaturamento(
									(Integer) ((Object[]) objectMessage
									.getObject())[0],(String) ((Object[]) objectMessage.getObject())[1],
									(Collection) ((Object[]) objectMessage.getObject())[2]);
					break;
				}
				
				case (MetodosBatch.ALTERAR_VENCIMENTO_CONJUNTO_CONTA_CLIENTE): {
					this.getControladorFaturamento().alterarVencimentoConjuntoContaCliente(
									(Integer) ((Object[]) objectMessage	.getObject())[0],
									(Short) ((Object[]) objectMessage.getObject())[1],
									(Date) ((Object[]) objectMessage.getObject())[2],
									(Integer) ((Object[]) objectMessage.getObject())[3],
									(Date) ((Object[]) objectMessage.getObject())[4],
									(Date) ((Object[]) objectMessage.getObject())[5],
									(Integer) ((Object[]) objectMessage.getObject())[6],
									(Usuario) ((Object[]) objectMessage.getObject())[7],
                                    (Integer) ((Object[]) objectMessage .getObject())[8],
									(Boolean) ((Object[]) objectMessage.getObject())[9]);
					usuarioLogado = (Usuario) ((Object[]) objectMessage.getObject())[7];
					assuntoEmail = 	"ALTERAR VENCIMENTO DE CONJUNTO DE CONTAS";			
					break;
				}
				
				case (MetodosBatch.ALTERAR_VENCIMENTO_CONJUNTO_CONTA): {
					this.getControladorFaturamento().alterarVencimentoConjuntoConta(
									(Integer) ((Object[]) objectMessage	.getObject())[0],
									(Date) ((Object[]) objectMessage.getObject())[1],
									(Integer) ((Object[]) objectMessage.getObject())[2],
									(Integer) ((Object[]) objectMessage.getObject())[3],
									(Date) ((Object[]) objectMessage.getObject())[4],
									(Date) ((Object[]) objectMessage.getObject())[5],
									(Usuario) ((Object[]) objectMessage.getObject())[6],
									(Boolean) ((Object[]) objectMessage.getObject())[7]);
					usuarioLogado = (Usuario) ((Object[]) objectMessage.getObject())[6];
					assuntoEmail = 	"ALTERAR VENCIMENTO DE CONJUNTO DE CONTAS";								
					break;
				}
				
				case (MetodosBatch.ALTERAR_VENCIMENTO_CONJUNTO_CONTA_COLECAO): {
					this.getControladorFaturamento().alterarVencimentoConjuntoConta(
									(Collection) ((Object[]) objectMessage	.getObject())[0],
									(Date) ((Object[]) objectMessage.getObject())[1],
									(Integer) ((Object[]) objectMessage.getObject())[2],
									(Date) ((Object[]) objectMessage.getObject())[3],
									(Date) ((Object[]) objectMessage.getObject())[4],
									(Integer) ((Object[]) objectMessage.getObject())[5],
									(Usuario) ((Object[]) objectMessage.getObject())[6],
									(String) ((Object[]) objectMessage.getObject())[7],
									(String[]) ((Object[]) objectMessage.getObject())[8],
									(Boolean) ((Object[]) objectMessage.getObject())[9]);
											
					usuarioLogado = (Usuario) ((Object[]) objectMessage.getObject())[6];
					assuntoEmail = 	"ALTERAR VENCIMENTO DE CONJUNTO DE CONTAS";	
					break;
				}
				case (MetodosBatch.CANCELAR_CONJUNTO_CONTA): {
					this.getControladorFaturamento().cancelarConjuntoConta(
							(Collection) ((Object[]) objectMessage.getObject())[0],
							(ContaMotivoCancelamento) ((Object[]) objectMessage.getObject())[1],
							(Integer) ((Object[]) objectMessage.getObject())[2],
							(Date) ((Object[]) objectMessage.getObject())[3], 
							(Date) ((Object[]) objectMessage.getObject())[4],
							(Integer) ((Object[]) objectMessage.getObject())[5],
							(Usuario) ((Object[]) objectMessage.getObject())[6],
							(String) ((Object[]) objectMessage.getObject())[7]);
					usuarioLogado = (Usuario) ((Object[]) objectMessage.getObject())[6];
					assuntoEmail = 	"CANCELAR CONJUNTO DE CONTAS";
					break;
				}
				case (MetodosBatch.CANCELAR_CONJUNTO_CONTA_CLIENTE): {
					this.getControladorFaturamento().cancelarConjuntoContaCliente(
							(Integer) ((Object[]) objectMessage.getObject())[0],
							(Short) ((Object[]) objectMessage.getObject())[1],
							(ContaMotivoCancelamento) ((Object[]) objectMessage.getObject())[2],
							(Integer) ((Object[]) objectMessage.getObject())[3],
							(Date) ((Object[]) objectMessage.getObject())[4], 
							(Date) ((Object[]) objectMessage.getObject())[5],
							(Integer) ((Object[]) objectMessage.getObject())[6],
							(Usuario) ((Object[]) objectMessage.getObject())[7]);
					usuarioLogado = (Usuario) ((Object[]) objectMessage.getObject())[7];
					assuntoEmail = 	"CANCELAR CONJUNTO DE CONTAS";
					break;
				}
				case (MetodosBatch.CANCELAR_CONJUNTO_CONTA_POR_GRUPO_FATURAMENTO): {
				
					this.getControladorFaturamento().cancelarConjuntoConta(
							(Integer) ((Object[]) objectMessage.getObject())[0],
							(ContaMotivoCancelamento) ((Object[]) objectMessage.getObject())[1],
							(Integer) ((Object[]) objectMessage.getObject())[2],
							(Date) ((Object[]) objectMessage.getObject())[3], 
							(Date) ((Object[]) objectMessage.getObject())[4],
							(Integer) ((Object[]) objectMessage.getObject())[5],
							(Usuario) ((Object[]) objectMessage.getObject())[6]);
					usuarioLogado = (Usuario) ((Object[]) objectMessage.getObject())[6];
					assuntoEmail = 	"CANCELAR CONJUNTO DE CONTAS";
					break;
				}
				case (MetodosBatch.RETIFICAR_CONJUNTO_CONTA_POR_GRUPO_FATURAMENTO):{
					this.getControladorRetificarConta().retificarConjuntoConta(
							(Integer) ((Object[]) objectMessage.getObject())[0],
							(Integer) ((Object[]) objectMessage.getObject())[1],
							(ContaMotivoRetificacao) ((Object[]) objectMessage.getObject())[2],
							(Collection) ((Object[]) objectMessage.getObject())[3], 
							(Usuario) ((Object[]) objectMessage.getObject())[4],
							(Date) ((Object[]) objectMessage.getObject())[5], 
							(Date) ((Object[]) objectMessage.getObject())[6],
							(Integer) ((Object[]) objectMessage.getObject())[7]);
					usuarioLogado = (Usuario) ((Object[]) objectMessage.getObject())[4];
					assuntoEmail = 	"RETIFICAR CONJUNTO DE CONTAS";
					break;
				}
				case (MetodosBatch.RETIFICAR_CONJUNTO_CONTA_COLECAO):{
					this.getControladorRetificarConta().retificarConjuntoConta(
							(Collection) ((Object[]) objectMessage.getObject())[0],
							(Integer) ((Object[]) objectMessage.getObject())[1],
							(ContaMotivoRetificacao) ((Object[]) objectMessage.getObject())[2],
							(Collection) ((Object[]) objectMessage.getObject())[3], 
							(Usuario) ((Object[]) objectMessage.getObject())[4],
							(Date) ((Object[]) objectMessage.getObject())[5], 
							(Date) ((Object[]) objectMessage.getObject())[6],
							(Integer) ((Object[]) objectMessage.getObject())[7],
							(String) ((Object[]) objectMessage.getObject())[8]);
					usuarioLogado = (Usuario) ((Object[]) objectMessage.getObject())[4];
					assuntoEmail = 	"RETIFICAR CONJUNTO DE CONTAS";
					break;
				}
				case (MetodosBatch.INSERIR_DEBITOS_PARA_CONTAS_VALOR_DIFERENTE):{
					this.getControladorFaturamento().inserirDebitosContasComValorFaixasErradas(
							(Integer) ((Object[]) objectMessage.getObject())[0],
							(Usuario) ((Object[]) objectMessage.getObject())[1]);
					usuarioLogado = (Usuario) ((Object[]) objectMessage.getObject())[1];
					assuntoEmail = 	"RETIFICAR CONJUNTO DE CONTAS";
					break;
				}
				case (MetodosBatch.GERAR_BOLETIM_MEDICAO):{
					this.getControladorCobranca().gerarBoletimMedicao(
							(Integer) ((Object[]) objectMessage.getObject())[0],
							(Integer) ((Object[]) objectMessage.getObject())[1],
							(Usuario) ((Object[]) objectMessage.getObject())[2]);
					usuarioLogado = null;
					assuntoEmail = 	"GERAR BOLETIM DE MEDIÇÃO";
					break;
				}
				}
				mensagemEmail = "ALTERADO COM SUCESSO.";
			} catch (JMSException e) {
				System.out.println("Erro no MDB");
				e.printStackTrace();
			} catch (ControladorException e) {
				System.out.println("Erro no MDB");
				mensagemEmail = "ALTERAÇÃO NÃO FOI REALIZADA COM SUCESSO.";
				e.printStackTrace();
			} finally {
				if(usuarioLogado != null){
					try {
						EnvioEmail envioEmail = getControladorCadastro().pesquisarEnvioEmail(EnvioEmail.AVISO_CONCLUSAO_BATCH_AVULSO);
						try {
							ServicosEmail.enviarMensagem(envioEmail.getEmailRemetente(), usuarioLogado.getDescricaoEmail(),
									assuntoEmail, mensagemEmail);
						} catch (ErroEmailException e) {
							throw new ControladorException("erro.envio.mensagem");
						}
					} catch (ControladorException e) {
						e.printStackTrace();
					}
				}									
			}
		}

	}

	private ControladorEnderecoLocal getControladorEndereco() {
		ControladorEnderecoLocalHome localHome = null;
		ControladorEnderecoLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorEnderecoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_ENDERECO_SEJB);
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
	
	private ControladorFaturamentoLocal getControladorFaturamento() {
		ControladorFaturamentoLocalHome localHome = null;
		ControladorFaturamentoLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorFaturamentoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FATURAMENTO_SEJB);
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

	public void ejbCreate() {
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
	
	private ControladorCobrancaLocal getControladorCobranca() {

		ControladorCobrancaLocalHome localHome = null;
		ControladorCobrancaLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorCobrancaLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_COBRANCA_SEJB);
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
	
	private ControladorRetificarContaLocal getControladorRetificarConta() {

		ControladorRetificarContaLocalHome localHome = null;
		ControladorRetificarContaLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorRetificarContaLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_RETIFICAR_CONTA);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}
	
}
