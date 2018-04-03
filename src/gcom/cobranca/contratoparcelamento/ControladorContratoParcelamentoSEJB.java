package gcom.cobranca.contratoparcelamento;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.ArrecadadorContrato;
import gcom.arrecadacao.ControladorArrecadacaoLocal;
import gcom.arrecadacao.ControladorArrecadacaoLocalHome;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.arrecadacao.FiltroArrecadadorContrato;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamentoItem;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.cliente.ControladorClienteLocalHome;
import gcom.cadastro.cliente.FiltroClienteEndereco;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaDebitoSituacao;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.CobrancaDocumentoItem;
import gcom.cobranca.CobrancaForma;
import gcom.cobranca.ControladorCobrancaLocal;
import gcom.cobranca.ControladorCobrancaLocalHome;
import gcom.cobranca.DocumentoEmissaoForma;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.FiltroCobrancaDocumentoItem;
import gcom.cobranca.NegativadorMovimentoRegItem;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.cobranca.parcelamento.ParcelamentoSituacao;
import gcom.fachada.Fachada;
import gcom.faturamento.ControladorFaturamentoLocal;
import gcom.faturamento.ControladorFaturamentoLocalHome;
import gcom.faturamento.GuiaPagamentoGeral;
import gcom.faturamento.IRepositorioFaturamento;
import gcom.faturamento.RepositorioFaturamentoHBM;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.conta.ContaMotivoRevisao;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.DebitoTipo;
import gcom.financeiro.FinanciamentoTipo;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.gui.ActionServletException;
import gcom.gui.cobranca.contratoparcelamento.DebitosClienteHelper;
import gcom.interceptor.RegistradorOperacao;
import gcom.relatorio.cobranca.RelatorioEmitirContratoParcelamentoPorClienteBean;
import gcom.relatorio.cobranca.RelatorioEmitirContratoParcelamentoPorClienteSubCliBean;
import gcom.relatorio.cobranca.RelatorioEmitirContratoParcelamentoPorClienteSubContaBean;
import gcom.relatorio.cobranca.RelatorioEmitirContratoParcelamentoPorClienteSubDebitoACobrarBean;
import gcom.relatorio.cobranca.RelatorioEmitirContratoParcelamentoPorClienteSubParcBean;
import gcom.relatorio.cobranca.contratoparcelamento.EmitirExtratoContratoParcelamentoPorClienteHelper;
import gcom.relatorio.cobranca.contratoparcelamento.FiltrarRelatorioEmitirContratoParcelamentoPorClienteHelper;
import gcom.relatorio.cobranca.contratoparcelamento.RelatorioEmitirExtratoContratoParcelamentoPorClienteBean;
import gcom.relatorio.cobranca.contratoparcelamento.RelatorioManterContratoParcelamentoPorClienteBean;
import gcom.relatorio.cobranca.contratoparcelamento.RelatorioManterContratoParcelamentoRDBean;
import gcom.relatorio.cobranca.contratoparcelamento.SubRelatorioCondicoesPagtoManterContratoParcelamentoPorClienteBean;
import gcom.relatorio.cobranca.contratoparcelamento.SubRelatorioDadosPagtoManterContratoParcelamentoPorClienteBean;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.spcserasa.ControladorSpcSerasaLocal;
import gcom.spcserasa.ControladorSpcSerasaLocalHome;
import gcom.spcserasa.FiltroNegativadorMovimentoRegItem;
import gcom.util.CodigoBarras;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ControladorUtilLocalHome;
import gcom.util.ErroRepositorioException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoCampos;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


/**
 * Definição da lógica de negócio do Session Bean de ControladorContratoParcelamento
 * 
 * @author Paulo Diniz
 * @created 18 de março de 2011
 */
public class ControladorContratoParcelamentoSEJB implements SessionBean {
	private static final long serialVersionUID = 1L;
	
	private IRepositorioContratoParcelamento repositorioContratoParcelamento;
	
	protected IRepositorioFaturamento repositorioFaturamento;


	SessionContext sessionContext;

	/**
	 * < <Descrição do método>>
	 * 
	 * @exception CreateException
	 *                Descrição da exceção
	 */
	public void ejbCreate() throws CreateException {
		repositorioContratoParcelamento = RepositorioContratoParcelamentoHBM.getInstancia();
		
		repositorioFaturamento = RepositorioFaturamentoHBM.getInstancia();
	}

	/**
	 * < <Descrição do método>>
	 */
	public void ejbRemove() {
	}

	/**
	 * < <Descrição do método>>
	 */
	public void ejbActivate() {
	}

	/**
	 * < <Descrição do método>>
	 */
	public void ejbPassivate() {
	}

	/**
	 * Seta o valor de sessionContext
	 * 
	 * @param sessionContext
	 *            O novo valor de sessionContext
	 */
	public void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}
	
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
	 * Retorna o valor de controladorCliente
	 * 
	 * @return O valor de controladorCliente
	 */
	private ControladorClienteLocal getControladorCliente() {
		ControladorClienteLocalHome localHome = null;
		ControladorClienteLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorClienteLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_CLIENTE_SEJB);
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
	 * Author: Rafael Santos Data: 04/01/2006
	 * 
	 * Retorna o valor do Controlador de Cobranca
	 * 
	 * @return O valor de controladorCobrancaLocal
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

	protected ControladorSpcSerasaLocal getControladorSpcSerasa() {
		ControladorSpcSerasaLocalHome localHome = null;
		ControladorSpcSerasaLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorSpcSerasaLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_SPC_SERASA_SEJB);
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

	protected ControladorArrecadacaoLocal getControladorArrecadacao() {
		ControladorArrecadacaoLocalHome localHome = null;
		ControladorArrecadacaoLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorArrecadacaoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_ARRECADACAO_SEJB);
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
	 * Inseri um ContratoParcelamento e suas QuantidadesPrestacoes associadas
	 * 
	 * [UC1133] Inserir Resolução de Diretoria para Contratos de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 16/03/2011
	 * 
	 * @param contrato
	 * @param parcelas que o contrato possui
	 * @throws ControladorException
	 */
	public Integer inserirContratoParcelamentoRD(ContratoParcelamentoRD contrato, List<QuantidadePrestacoes> parcelas, Usuario usuarioLogado)
			throws ControladorException{
		Integer retorno = null;
		try{
			contrato.setUltimaAlteracao(new Date());
			retorno = (Integer) this.getControladorUtil().inserir(contrato);
			contrato.setId(retorno);
			for (QuantidadePrestacoes prestacoes : parcelas) {
				prestacoes.setContratoRD(contrato);
				this.getControladorUtil().inserir(prestacoes);
			}
			
//			 ------------ REGISTRAR TRANSAÇÃO ----------------
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				    Operacao.OPERACAO_INSERIR_CONTRATO_PARCELAMENTO_RD,
				    contrato.getId(), contrato.getId(),
				    new UsuarioAcaoUsuarioHelper(usuarioLogado,
				    UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

				registradorOperacao.registrarOperacao(contrato);
//				 ------------ REGISTRAR TRANSAÇÃO ----------------
				
		}catch (Exception ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
		return retorno;
	}
	
	/**
	 * Atualizar um ContratoParcelamento e suas QuantidadesPrestacoes associadas
	 * 
	 * [UC1133] Inserir Resolução de Diretoria para Contratos de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 16/03/2011
	 * 
	 * @param contrato
	 * @param parcelas que o contrato possui
	 * @throws ControladorException
	 */
	public void atualizarContratoParcelamentoRD(ContratoParcelamentoRD contrato, List<QuantidadePrestacoes> parcelasAtualizar, Usuario usuarioLogado)
		throws ControladorException{
		
		try{
			contrato.setUltimaAlteracao(new Date());
			this.getControladorUtil().atualizar(contrato);
			
			boolean atualizarPrestacoes = false;
			
//			 [FS0003] - Atualização realizada por outro usuário
			FiltroContratoParcelamentoRD filtro = new FiltroContratoParcelamentoRD();
			filtro.adicionarParametro(new ParametroSimples(
					FiltroContratoParcelamentoRD.CONTRATO_PARCELAMENTO_RD_ID, contrato.getId()));

			Collection colecao = getControladorUtil()
					.pesquisar(filtro,
							ContratoParcelamentoRD.class.getName());

			if (colecao == null
					|| colecao.isEmpty()) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.atualizacao.timestamp");
			}

			ContratoParcelamentoRD contratoBase = (ContratoParcelamentoRD) colecao
					.iterator().next();
			if (contratoBase.getUltimaAlteracao().after(
					contrato.getUltimaAlteracao())) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.atualizacao.timestamp");
			}
			
			FiltroQuantidadePrestacoes filtroParcelas = new FiltroQuantidadePrestacoes(FiltroQuantidadePrestacoes.QTD_FATURAS_PARCELADAS);
			filtroParcelas.adicionarParametro(new ComparacaoTexto(
					FiltroQuantidadePrestacoes.CONTRATO_PARCELAMENTO_RD_NUMERO, contrato.getNumero().toUpperCase()));
			List<QuantidadePrestacoes> parcelasAntes = new ArrayList<QuantidadePrestacoes>(this.getControladorUtil().pesquisar(filtroParcelas,QuantidadePrestacoes.class.getName()));
			Collections.sort(parcelasAntes, new ComparatorParcela());
			Collections.sort(parcelasAtualizar, new ComparatorParcela());
			
			if(parcelasAntes.size() != parcelasAtualizar.size()){
				atualizarPrestacoes = true;
			}else{
				for (int i = 0; i < parcelasAntes.size(); i++) {
					if(parcelasAntes.get(i).getQtdFaturasParceladas().intValue() != parcelasAtualizar.get(i).getQtdFaturasParceladas().intValue()
							|| parcelasAntes.get(i).getTaxaJuros().doubleValue() != parcelasAtualizar.get(i).getQtdFaturasParceladas().doubleValue()){
						atualizarPrestacoes = true;
					}
				}
			}
			
			if(atualizarPrestacoes){
				for (int i = 0; i < parcelasAntes.size(); i++) {
					this.getControladorUtil().remover(parcelasAntes.get(i));
				}
				
				for (QuantidadePrestacoes prestacoes : parcelasAtualizar) {
					prestacoes.setContratoRD(contrato);
					this.getControladorUtil().inserir(prestacoes);
				}
				
			}
			
//			 ------------ REGISTRAR TRANSAÇÃO ----------------
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				    Operacao.OPERACAO_ATUALIZAR_CONTRATO_PARCELAMENTO_RD,
				    contrato.getId(), contrato.getId(),
				    new UsuarioAcaoUsuarioHelper(usuarioLogado,
				    UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

				registradorOperacao.registrarOperacao(contrato);
//				 ------------ REGISTRAR TRANSAÇÃO ----------------
				
		}catch (Exception ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	
	/**
	 * Pesquisa ContratoParcelamentoRD por numero
	 * 
	 * [UC1133] Inserir Resolução de Diretoria para Contratos de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 16/03/2011
	 * 
	 * @param numero do contrato
	 * @throws ControladorException
	 */
	public ContratoParcelamentoRD pesquisarContratoParcelamentoRDPorNumero(String numero)
		throws ControladorException{
		
		try {
			return repositorioContratoParcelamento.pesquisarContratoParcelamentoRDPorNumero(numero);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * Atualizar um ContratoParcelamento e suas QuantidadesPrestacoes associadas
	 * 
	 * [UC1134] Manter Resolução de Diretoria para Contratos de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 16/03/2011
	 * 
	 * @param contrato
	 * @param parcelas que o contrato possui
	 * @throws ControladorException
	 */
	public boolean excluirContratosParcelamentoRD(String[] ids, Usuario usuarioLogado)
		throws ControladorException{
		boolean retorno = false;
		try {
			
			for (String id : ids) {
			
				FiltroContratoParcelamentoRD filtroContrato = new FiltroContratoParcelamentoRD();
				filtroContrato.adicionarParametro(new ComparacaoCampos(FiltroContratoParcelamentoRD.CONTRATO_PARCELAMENTO_RD_ID, id));
				ContratoParcelamentoRD contrato = this.pesquisarContratoParcelamentoRDPorID(Integer.parseInt(id));
				
				FiltroQuantidadePrestacoes filtroParcela = new FiltroQuantidadePrestacoes(FiltroQuantidadePrestacoes.QTD_FATURAS_PARCELADAS);
				filtroParcela.adicionarParametro(new ComparacaoTexto(
						FiltroQuantidadePrestacoes.CONTRATO_PARCELAMENTO_RD_NUMERO, contrato.getNumero().toUpperCase()));
				List<QuantidadePrestacoes> parcelas = new ArrayList<QuantidadePrestacoes>(this.getControladorUtil().pesquisar(filtroParcela,QuantidadePrestacoes.class.getName()));
				
				for (QuantidadePrestacoes prestacoes : parcelas) {
					this.getControladorUtil().remover(prestacoes);
				}
				
				this.getControladorUtil().remover(contrato);
				
	//			 ------------ REGISTRAR TRANSAÇÃO ----------------
				RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					    Operacao.OPERACAO_ATUALIZAR_CONTRATO_PARCELAMENTO_RD,
					    contrato.getId(), contrato.getId(),
					    new UsuarioAcaoUsuarioHelper(usuarioLogado,
					    UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
	
					registradorOperacao.registrarOperacao(contrato);
	//				 ------------ REGISTRAR TRANSAÇÃO ----------------
					
				retorno = true;
			}
			
		} catch (Exception ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
		return retorno;
	}
	
	/**
	 * Pesquisa ContratoParcelamentoRD por id
	 * 
	 * [UC1134] Manter Resolução de Diretoria para Contratos de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 16/03/2011
	 * 
	 * @param numero do contrato
	 * @throws ControladorException
	 */
	public ContratoParcelamentoRD pesquisarContratoParcelamentoRDPorID(Integer id)
		throws ControladorException{
		
		try {
			return repositorioContratoParcelamento.pesquisarContratoParcelamentoRDPorID(id);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * Verifica Resolução de Diretoria associada a um Contrato Parcelamento não Encerrado
	 * 
	 * [UC1134]  Atualizar Resolução de Diretoria para Contratos de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 10/04/2011
	 * 
	 */
	public boolean verificaResolucaoDiretoriaAssociadaContratoParcelamentoNaoEncerrado(Integer id)
		throws ControladorException{
		try {
			return repositorioContratoParcelamento.verificaResolucaoDiretoriaAssociadaContratoParcelamentoNaoEncerrado(id);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * Verifica Conta Vinculada a um Contrato Parcelamento por Cliente Item
	 * 
	 * [UC1143]  Consultar Dados do Contrato de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 10/05/2011
	 * 
	 */
	public boolean verificaContaVinculadaAContratoParcel(Integer idConta, Integer idContrato) throws ControladorException {
		try {
			return repositorioContratoParcelamento.verificaContaVinculadaAContratoParcel(idConta, idContrato);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * Gerar Relatorio dos ContratoParcelamentoRD selecionados
	 * 
	 * [UC1134] Manter Resolução de Diretoria para Contratos de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 16/03/2011
	 * 
	 * @param ids dos Contratos
	 * @param filtro da Busca
	 * @throws ControladorException
	 */
	public List<RelatorioManterContratoParcelamentoRDBean> gerarRelatorioManterContratoParcelamentoRD(FiltroContratoParcelamentoRD filtro)
		throws ControladorException{
		
		filtro.adicionarCaminhoParaCarregamentoEntidade("cobrancaForma");
		List<RelatorioManterContratoParcelamentoRDBean> beans = new ArrayList<RelatorioManterContratoParcelamentoRDBean>();
		List<ContratoParcelamentoRD> contratosPesquisa = new ArrayList<ContratoParcelamentoRD>(this.getControladorUtil().pesquisar(filtro, ContratoParcelamentoRD.class.getName()));
		
		for (ContratoParcelamentoRD contratoRD : contratosPesquisa) {
			
			FiltroQuantidadePrestacoes filtroParcela = new FiltroQuantidadePrestacoes(FiltroQuantidadePrestacoes.QTD_FATURAS_PARCELADAS);
			filtroParcela.adicionarParametro(new ComparacaoTexto(
					FiltroQuantidadePrestacoes.CONTRATO_PARCELAMENTO_RD_NUMERO, contratoRD.getNumero().toUpperCase()));
			List<QuantidadePrestacoes> parcelas = new ArrayList<QuantidadePrestacoes>(this.getControladorUtil().pesquisar(filtroParcela,QuantidadePrestacoes.class.getName()));
			if(parcelas != null && parcelas.size() > 0){
				for (QuantidadePrestacoes parcela : parcelas) {
					RelatorioManterContratoParcelamentoRDBean bean = new RelatorioManterContratoParcelamentoRDBean();
					
					bean.setNumero(contratoRD.getNumero());
					bean.setAssunto(contratoRD.getAssunto());
					bean.setQtdFaturasParceladas(contratoRD.getQtdFaturasParceladas().toString());
					bean.setFormaPagamento(contratoRD.getCobrancaForma().getDescricao());
					if(contratoRD.getIndicadorDebitoAcrescimo().intValue() == 1){
						bean.setIndicadorDebitoAcrescimo("Sim");
					}else{
						bean.setIndicadorDebitoAcrescimo("Não");
					}
					if(contratoRD.getIndicadorInformarParcela().intValue() == 1){
						bean.setIndicadorInformarParcela("Sim");
					}else{
						bean.setIndicadorInformarParcela("Não");
					}
					if(contratoRD.getIndicadorParcelamentoJuros().intValue() == 1){
						bean.setIndicadorParcelamentoJuros("Sim");
					}else{
						bean.setIndicadorParcelamentoJuros("Não");
					}
					
					bean.setVigenciaInicial(contratoRD.getDataVigenciaInicioFormatada());
					bean.setVigenciaFinal(contratoRD.getDataVigenciaFinalFormatada());
					
					bean.setParcelas(parcela.getQtdFaturasParceladas().toString());
					bean.setTaxaJuros(parcela.getTaxaJuros().toString());
					
					beans.add(bean);
				}
			}else{
				RelatorioManterContratoParcelamentoRDBean bean = new RelatorioManterContratoParcelamentoRDBean();
				
				bean.setNumero(contratoRD.getNumero());
				bean.setAssunto(contratoRD.getAssunto());
				bean.setQtdFaturasParceladas(contratoRD.getQtdFaturasParceladas().toString());
				bean.setFormaPagamento(contratoRD.getCobrancaForma().getDescricao());
				if(contratoRD.getIndicadorDebitoAcrescimo().intValue() == 1){
					bean.setIndicadorDebitoAcrescimo("Sim");
				}else{
					bean.setIndicadorDebitoAcrescimo("Não");
				}
				if(contratoRD.getIndicadorInformarParcela().intValue() == 1){
					bean.setIndicadorInformarParcela("Sim");
				}else{
					bean.setIndicadorInformarParcela("Não");
				}
				if(contratoRD.getIndicadorParcelamentoJuros().intValue() == 1){
					bean.setIndicadorParcelamentoJuros("Sim");
				}else{
					bean.setIndicadorParcelamentoJuros("Não");
				}
				
				bean.setVigenciaInicial(contratoRD.getDataVigenciaInicioFormatada());
				bean.setVigenciaFinal(contratoRD.getDataVigenciaFinalFormatada());
				
				bean.setParcelas("");
				bean.setTaxaJuros("");
				
				beans.add(bean);
			}
		}
		
		return beans;
	}
	
	/**
	 * Gerar Relatorio dos ContratoParcelamentoPorCLiente selecionados
	 * 
	 * [UC1137] Manter Contrato de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 16/03/2011
	 * 
	 * @param ids dos Contratos
	 * @param filtro da Busca
	 * @throws ControladorException
	 */
	public List<RelatorioManterContratoParcelamentoPorClienteBean> gerarRelatorioManterContratoParcelamentoPorCliente(FiltroContratoParcelamentoCliente filtroContrato) throws ControladorException
	{
		
		filtroContrato.adicionarCaminhoParaCarregamentoEntidade("contrato");
        filtroContrato.adicionarCaminhoParaCarregamentoEntidade("cliente");
        filtroContrato.adicionarCaminhoParaCarregamentoEntidade("contrato.contratoAnterior");
        filtroContrato.adicionarCaminhoParaCarregamentoEntidade("contrato.parcelamentoSituacao");
        filtroContrato.adicionarCaminhoParaCarregamentoEntidade("contrato.usuarioResponsavel");
        filtroContrato.adicionarCaminhoParaCarregamentoEntidade("contrato.relacaoAnterior");
        filtroContrato.adicionarCaminhoParaCarregamentoEntidade("contrato.relacaoCliente");
        filtroContrato.adicionarCaminhoParaCarregamentoEntidade("contrato.resolucaoDiretoria");
        filtroContrato.adicionarCaminhoParaCarregamentoEntidade("contrato.cobrancaForma");
        filtroContrato.adicionarCaminhoParaCarregamentoEntidade("contrato.motivoDesfazer");
        filtroContrato.adicionarCaminhoParaCarregamentoEntidade("contrato.qtdPrestacoesDaRDEscolhida");
        
		List<RelatorioManterContratoParcelamentoPorClienteBean> beans = new ArrayList<RelatorioManterContratoParcelamentoPorClienteBean>();
		List<ContratoParcelamentoCliente> contratosPesquisa = new ArrayList<ContratoParcelamentoCliente>(this.getControladorUtil().pesquisar(filtroContrato, ContratoParcelamentoCliente.class.getName()));
		
		for (ContratoParcelamentoCliente contratoCliente : contratosPesquisa) {
			
			RelatorioManterContratoParcelamentoPorClienteBean beanContrato = new RelatorioManterContratoParcelamentoPorClienteBean();
			beanContrato.setClienteDescricao(contratoCliente.getCliente().getNome());
			beanContrato.setNumeroContrato(contratoCliente.getContrato().getNumero());
			if(contratoCliente.getContrato().getContratoAnterior() != null){
				beanContrato.setNumeroContratoAnterior(contratoCliente.getContrato().getContratoAnterior().getNumero());
				beanContrato.setTipoRelacaoAnterior(contratoCliente.getContrato().getRelacaoAnterior().getDescricao());
			}else{
				beanContrato.setNumeroContratoAnterior("");
				beanContrato.setTipoRelacaoAnterior("");
			}
			beanContrato.setClienteDescricao(contratoCliente.getCliente().getDescricao());
			beanContrato.setUsuarioDescricao(contratoCliente.getContrato().getUsuarioResponsavel().getNomeUsuario());
			beanContrato.setDataContrato(contratoCliente.getContrato().getDataContratoFormatada());
			if(contratoCliente.getContrato().getDataImplantacao() != null){
				beanContrato.setDataImplantacao(Util.formatarData(contratoCliente.getContrato().getDataImplantacao()));
			}else{
				beanContrato.setDataImplantacao("");
			}
			
			if(contratoCliente.getContrato().getAnoMesDebitoInicio() != null){
				beanContrato.setDataReferenciaInicial(contratoCliente.getContrato().getAnoMesDebitoInicioFormatado());
			}else{
				beanContrato.setDataReferenciaInicial("");
			}
			
			if(contratoCliente.getContrato().getAnoMesDebitoFinal() != null){
				beanContrato.setDataReferenciaFinal(contratoCliente.getContrato().getAnoMesDebitoFinalFormatado());
			}else{
				beanContrato.setDataReferenciaFinal("");
			}
			
			if(contratoCliente.getContrato().getDataVencimentoInicio() != null){
				beanContrato.setDataVencimentoInicial(contratoCliente.getContrato().getDataVencimentoInicioFormatada());
			}else{
				beanContrato.setDataVencimentoInicial("");
			}
			
			if(contratoCliente.getContrato().getDataVencimentoFinal() != null){
				beanContrato.setDataVencimentoFinal(contratoCliente.getContrato().getDataVencimentoFinalFormatada());
			}else{
				beanContrato.setDataVencimentoFinal("");
			}
			
			if(contratoCliente.getContrato().getValorParcelamentoACobrar() != null 
					&& contratoCliente.getContrato().getValorParcelamentoACobrar().floatValue() > 0f){
				beanContrato.setSituacaoPagamento("Pendente");
			}else{
				beanContrato.setSituacaoPagamento("Pago");
			}
			
			if(contratoCliente.getContrato().getMotivoDesfazer() != null){
				beanContrato.setSituacaoCancelamento("Sim");
				beanContrato.setDataCancelamento(Util.formatarData(contratoCliente.getContrato().getDataCancelamento()));
				beanContrato.setMotivoCancelamento("");
			}else{
				beanContrato.setSituacaoCancelamento("Não");
				beanContrato.setDataCancelamento("");
				beanContrato.setMotivoCancelamento("");
			}
			
			beanContrato.setObservacao(contratoCliente.getContrato().getObservacao());
			
			BigDecimal totalDebito = contratoCliente.getContrato().getValorDebitoAtualizado();
			if(contratoCliente.getContrato().getValorDebitoAtualizado() != null &&
					contratoCliente.getContrato().getValorTotalAcrescImpontualidade() != null){
				totalDebito = contratoCliente.getContrato().getValorDebitoAtualizado().subtract(contratoCliente.getContrato().getValorTotalAcrescImpontualidade());
			}
			beanContrato.setTotalDebito(Util.formatarMoedaReal(totalDebito));
			beanContrato.setTotalDebitoAtualizado(Util.formatarMoedaReal(contratoCliente.getContrato().getValorDebitoAtualizado()));
			
			beanContrato.setTotalAcrescImpontualidade(Util.formatarMoedaReal(contratoCliente.getContrato().getValorTotalAcrescImpontualidade()));
			beanContrato.setTotalDebitoComAcresc(Util.formatarMoedaReal(contratoCliente.getContrato().getValorDebitoAtualizado()));
			beanContrato.setTaxaJuros(Util.formatarMoedaReal(contratoCliente.getContrato().getTaxaJuros())+"%");
			beanContrato.setValorParcelado(Util.formatarMoedaReal(contratoCliente.getContrato().getValorTotalParcelado()));
			if(contratoCliente.getContrato().getValorJurosParcelamento() != null){
				beanContrato.setTotalJuros(Util.formatarMoedaReal(contratoCliente.getContrato().getValorJurosParcelamento()));
			}else{
				beanContrato.setTotalJuros("");
			}
			
			if(contratoCliente.getContrato().getValorParcelamentoACobrar() != null){
				beanContrato.setValorParceladoACobrar(Util.formatarMoedaReal(contratoCliente.getContrato().getValorParcelamentoACobrar()));
			}else{
				beanContrato.setValorParceladoACobrar("");
			}

			FiltroPrestacaoContratoParcelamento filtroPrestacao = new FiltroPrestacaoContratoParcelamento();
	        filtroPrestacao.adicionarParametro(new ParametroSimples(FiltroPrestacaoContratoParcelamento.CONTRATO_PARCELAMENTO_ID, contratoCliente.getContrato().getId()));
	        
			Collection<PrestacaoContratoParcelamento> collParcelas = getControladorUtil().pesquisar(filtroPrestacao, PrestacaoContratoParcelamento.class.getName());
			List<SubRelatorioCondicoesPagtoManterContratoParcelamentoPorClienteBean> subRelatorioCondicoes = new ArrayList<SubRelatorioCondicoesPagtoManterContratoParcelamentoPorClienteBean>();
			List<SubRelatorioDadosPagtoManterContratoParcelamentoPorClienteBean> subRelatorioDadosPagto = new ArrayList<SubRelatorioDadosPagtoManterContratoParcelamentoPorClienteBean>();
			
	        for (PrestacaoContratoParcelamento prestacao : collParcelas) {
	        	SubRelatorioCondicoesPagtoManterContratoParcelamentoPorClienteBean beanCondicoes = new SubRelatorioCondicoesPagtoManterContratoParcelamentoPorClienteBean();
	        	
	        	beanCondicoes.setParcelaNumero(prestacao.getNumero()+ "/" + collParcelas.size());
	        	beanCondicoes.setDataVencParcel(prestacao.getDataVencimentoFormatada());
	        	beanCondicoes.setValorParcel(Util.formatarMoedaReal(prestacao.getValor()));
	        	
	        	if(this.verificaPrestacaoPaga(prestacao.getId())){
	        		beanCondicoes.setSituacaoParcel("Paga");
	        		SubRelatorioDadosPagtoManterContratoParcelamentoPorClienteBean beanDadosPagto = new SubRelatorioDadosPagtoManterContratoParcelamentoPorClienteBean();
	        		
	        		beanDadosPagto.setParcelaNumero(prestacao.getNumero()+ "/" + collParcelas.size());
	        		beanDadosPagto.setDataVenc(prestacao.getDataVencimentoFormatada());
	        		beanDadosPagto.setDataPagto(prestacao.getDataPagamentoFormatada());
	        		beanDadosPagto.setValParcel(Util.formatarMoedaReal(prestacao.getValor()));
	        		beanDadosPagto.setValPagto(Util.formatarMoedaReal(prestacao.getValorPagamento()));
	        		FiltroPrestacaoItemContratoParcelamento filtroPrestItem = new FiltroPrestacaoItemContratoParcelamento();
	        		filtroPrestItem.adicionarParametro(new ParametroSimples(FiltroPrestacaoItemContratoParcelamento.PRESTACAO_ID, prestacao.getId().intValue()));
	        		Collection<PrestacaoItemContratoParcelamento> itensPagos = getControladorUtil().pesquisar(filtroPrestItem, PrestacaoItemContratoParcelamento.class.getName());
	        		if(itensPagos != null && itensPagos.size() != 0){
	        			beanDadosPagto.setQtdItensPagos(itensPagos.size() +"");
	        		}else{
	        			beanDadosPagto.setQtdItensPagos("0");
	        		}
	        		
	        		subRelatorioDadosPagto.add(beanDadosPagto);
	        	}else{
	        		beanCondicoes.setSituacaoParcel("");
	        	}
	        	
	        	subRelatorioCondicoes.add(beanCondicoes);
	        }
	        
	        beanContrato.setArrayJRDadosCondicoesParcel(subRelatorioCondicoes);
	        beanContrato.setArrayJRDadosPagamento(subRelatorioDadosPagto);
	        beans.add(beanContrato);

		}
		
		return beans;
	}	
	
	/**
     * [UC1136] Inserir Contrato de Parcelamento por Cliente
     * 
     * [SB0014] - Inserir Contrato de Parcelamento Por Cliente
     * 
     * Permite a inclusão de contrato de parcelamento por cliente.
     * 
     * @author Paulo Diniz, Mariana Victor
     * @date 16/03/2011, 21/07/2011
     * 
     * @param contrato
     * @throws ControladorException
     */
    public Integer inserirContratoParcelamentoPorCliente(ContratoParcelamento contrato, Usuario usuarioLogado, 
            ContratoParcelamentoCliente clienteContrato, ContratoParcelamentoCliente clienteSuperiorContrato, List<DebitosClienteHelper> listaDebitos, List<PrestacaoContratoParcelamento> listaDeParcelas)
            throws ControladorException{
        
        Integer idContrato = null;
        try{
            contrato.setUltimaAlteracao(new Date());
            idContrato = (Integer) this.getControladorUtil().inserir(contrato);
            contrato.setId(idContrato);
            
            if(clienteContrato != null){
                clienteContrato.setContrato(contrato);
                clienteContrato.setIndicadorClienteSuperior(new Short("2"));
                clienteContrato.setUltimaAlteracao( new Date());
                clienteContrato.setClienteSuperior(null);
                Integer idClienteContratoInserido = (Integer) this.getControladorUtil().inserir(clienteContrato);
                clienteContrato.setId(idClienteContratoInserido);
            }else if(clienteSuperiorContrato != null){
                clienteSuperiorContrato.setContrato(contrato);
                clienteSuperiorContrato.setIndicadorClienteSuperior(new Short("1"));
                clienteSuperiorContrato.setUltimaAlteracao( new Date());
                clienteSuperiorContrato.setClienteSuperior(null);
                
                Integer idClienteContratoSuperiorInserido = (Integer) this.getControladorUtil().inserir(clienteSuperiorContrato);
                clienteSuperiorContrato.setId(idClienteContratoSuperiorInserido);
                
                Collection<Integer> clientesVinculados = this.getControladorCliente().pesquisarClientesAssociadosResponsavel(clienteSuperiorContrato.getCliente().getId().intValue());
                
                for (Integer idCliente : clientesVinculados) {
                    ContratoParcelamentoCliente contratoParcelCliente = new ContratoParcelamentoCliente();
                    contratoParcelCliente.setContrato(contrato);
                    Cliente cliente = new Cliente();
                    cliente.setId(idCliente);
                    contratoParcelCliente.setCliente(cliente);
                    contratoParcelCliente.setIndicadorClienteSuperior(new Short("2"));
                    contratoParcelCliente.setUltimaAlteracao( new Date());
                    contratoParcelCliente.setClienteSuperior(clienteSuperiorContrato);
                    this.getControladorUtil().inserir(contratoParcelCliente);
                }
            }
            
            for (DebitosClienteHelper debitosClienteHelper : listaDebitos) {
                ContratoParcelamentoItem parcelItem = new ContratoParcelamentoItem();
                parcelItem.setContrato(contrato);
                DocumentoTipo docTipo = new DocumentoTipo();
                docTipo.setId(debitosClienteHelper.getTipoDocumento());
                parcelItem.setDocumentoTipo(docTipo);
                parcelItem.setValorItem(debitosClienteHelper.getValorItem());
                parcelItem.setValarAcrescImpont(debitosClienteHelper.getValorAcrescImpont());
                parcelItem.setUltimaAlteracao(new Date());
                parcelItem.setIndicadorItemCancelado(new Short("2"));
                

                if (debitosClienteHelper.getTipoDocumento().compareTo(DocumentoTipo.CONTA) == 0) {
	                ContaGeral contaGeral = new ContaGeral();
	                contaGeral.setId(debitosClienteHelper.getIdentificadorItem());
	                parcelItem.setContaGeral(contaGeral);
	                
	                Collection colecaoContas = (Collection) this.getControladorFaturamento().obterConta(contaGeral.getId());
	                Conta conta = (Conta)colecaoContas.iterator().next();
	                if(conta.getDataRevisao() != null){
	                	getControladorFaturamento().retirarRevisaoConta(colecaoContas, conta.getId().intValue()+"", usuarioLogado, false, Funcionalidade.INSERIR_CONTRATO_PARCELAMENTO_POR_CLIENTE );
	                }
	                
	                ContaMotivoRevisao motivoRevisao = new ContaMotivoRevisao();
	                motivoRevisao.setId(ContaMotivoRevisao.CONTA_EM_CONTRATO_PARCELAMENTO);
	                getControladorFaturamento().colocarRevisaoConta(colecaoContas, conta.getId()+"", motivoRevisao, usuarioLogado);
	                
	            } else if (debitosClienteHelper.getTipoDocumento().compareTo(DocumentoTipo.DEBITO_A_COBRAR) == 0) {
	            	DebitoACobrarGeral debitoACobrarGeral = new DebitoACobrarGeral();
	            	debitoACobrarGeral.setId(debitosClienteHelper.getIdentificadorItem());
	            	parcelItem.setDebitoACobrarGeral(debitoACobrarGeral);
	                
	            	DebitoACobrar debitoACobrar = this.getControladorFaturamento().obterDebitoACobrar(debitoACobrarGeral.getId());
	            	Collection colecaoDebitoACobrar = new ArrayList();
	            	colecaoDebitoACobrar.add(debitoACobrar);
	                if(debitoACobrar.getDataRevisao() != null){
	                	getControladorFaturamento().retirarRevisaoDebitoACobrar(colecaoDebitoACobrar, usuarioLogado);
	                }
	                
	                ContaMotivoRevisao motivoRevisao = new ContaMotivoRevisao();
	                motivoRevisao.setId(ContaMotivoRevisao.DEBITO_A_COBRAR_EM_CONTRATO_PARCELAMENTO);
	                getControladorFaturamento().colocarRevisaoDebitoACobrar(colecaoDebitoACobrar, motivoRevisao, usuarioLogado);
	            }
                
                
                this.getControladorUtil().inserir(parcelItem);
                
                
            }
            
            int indicadorGuiaAcrecImpontualidade = 2;
            if(contrato.getIndicadorDebitosAcrescimos().intValue() == 1
                    && contrato.getValorTotalAcrescImpontualidade() != null && contrato.getValorTotalAcrescImpontualidade().floatValue() > 0.0){
                indicadorGuiaAcrecImpontualidade = 1;
            }
            
            int indicadorGuiaJurosSobreContratoParcel = 2;
            if(contrato.getIndicadorParcelamentoJuros().intValue() == 1
                    && contrato.getValorJurosParcelamento() != null && contrato.getValorJurosParcelamento().floatValue() > 0.0){
                indicadorGuiaJurosSobreContratoParcel = 1;
            }
            
            Date dataVencPacel = contrato.getDataVencimentoPrimParcela();
            
            List<PrestacaoContratoParcelamento> colecaoPrestacaoContratoParcelamento = new ArrayList<PrestacaoContratoParcelamento>();
            PrestacaoContratoParcelamento prestacaoContratoParcelamento = listaDeParcelas.get(0);
            prestacaoContratoParcelamento.setDataVencimento(dataVencPacel);
            prestacaoContratoParcelamento.setContratoParcelamento(contrato);
            prestacaoContratoParcelamento.setUltimaAlteracao(new Date());
            Integer idPrestacao = (Integer) this.getControladorUtil().inserir(prestacaoContratoParcelamento);
            prestacaoContratoParcelamento.setId(idPrestacao);
            colecaoPrestacaoContratoParcelamento.add(prestacaoContratoParcelamento);
            
            for (int i = 1; i < listaDeParcelas.size(); i++) {
                prestacaoContratoParcelamento = listaDeParcelas.get(i);
                Date dataParcel = Util.adicionarNumeroDiasDeUmaData(listaDeParcelas.get(i-1).getDataVencimento(), 30);
                prestacaoContratoParcelamento.setDataVencimento(dataParcel);
                prestacaoContratoParcelamento.setContratoParcelamento(contrato);
                prestacaoContratoParcelamento.setUltimaAlteracao(new Date());
                
                idPrestacao = (Integer) this.getControladorUtil().inserir(prestacaoContratoParcelamento);
                prestacaoContratoParcelamento.setId(idPrestacao);
                colecaoPrestacaoContratoParcelamento.add(prestacaoContratoParcelamento);
            }
            
            if(indicadorGuiaJurosSobreContratoParcel == 1){
                
                BigDecimal valorPrimeiraGuia = new BigDecimal("0");
                BigDecimal valorDaGuia = contrato.getValorJurosParcelamento().divide(new BigDecimal(listaDeParcelas.size()),2 , BigDecimal.ROUND_UP);
                
                BigDecimal totalSomaGuias = valorDaGuia.multiply(new BigDecimal(listaDeParcelas.size()));
                BigDecimal diferenca = contrato.getValorJurosParcelamento().subtract(totalSomaGuias);
                valorPrimeiraGuia = valorDaGuia.add(diferenca);
                
                for (int i = 0; i < listaDeParcelas.size(); i++) {
                    prestacaoContratoParcelamento  = listaDeParcelas.get(i);
                    GuiaPagamento guia = new GuiaPagamento();
                    guia.setDataEmissao(new Date());
                    
                    //Primeira guia com diferenca ajustada
                    if(i == 0){
                        guia.setValorDebito(valorPrimeiraGuia);
                    }else{
                        guia.setValorDebito(valorDaGuia);
                    }

                    DebitoTipo debitoTipo = this.repositorioFaturamento
                    	.obterDebitoTipoCodigoConstante(DebitoTipo.JUROS_SOBRE_CONTR_PARCELAMENTO);
                    
                    guia.setDebitoTipo(debitoTipo);
                    FinanciamentoTipo financiamentoTipo = new FinanciamentoTipo();
                    financiamentoTipo.setId(FinanciamentoTipo.JUROS_PARCELAMENTO);
                    guia.setFinanciamentoTipo(financiamentoTipo);
                    guia.setDataVencimento(prestacaoContratoParcelamento.getDataVencimento());
                    guia.setLocalidade(new Localidade());
                    guia.setImovel(new Imovel());
                    guia.setRegistroAtendimento(new RegistroAtendimento());
                    guia.setOrdemServico(new OrdemServico());
                    Cliente cliente = null;
                    if(clienteContrato != null){
                        cliente = clienteContrato.getCliente();
                    }else if(clienteSuperiorContrato != null){
                        cliente = clienteSuperiorContrato.getCliente();
                    }
                    guia.setCliente(cliente);
                    guia.setAnoMesReferenciaContabil(contrato.getAnoMesReferenciaFaturamento());
                    
                    LancamentoItemContabil lancamentoItemContabil = new LancamentoItemContabil(LancamentoItemContabil.JUROS_SOBRE_CONTRATO_PARCELAMENTO);
                    guia.setLancamentoItemContabil(lancamentoItemContabil);

                    guia.setDebitoCreditoSituacaoAnterior(null);
                    
                    DebitoCreditoSituacao debitoCreditoSituacaoAtual = new DebitoCreditoSituacao(DebitoCreditoSituacao.NORMAL);
                    guia.setDebitoCreditoSituacaoAtual(debitoCreditoSituacaoAtual);
                    
                    guia.setUsuario(contrato.getUsuarioResponsavel());
                    guia.setObservacao("GUIA GERADA NA IMPLANTAÇÃO DO CONTRATO DE PARCELAMENTO POR CLIENTE " + contrato.getNumero()+ " da tabela cobranca.CONTRATO_PARCEL");
                    guia.setIndicadorEmitirObservacao(new Short("2"));
                    
                    guia.setNumeroPrestacaoDebito(Short.parseShort("1"));
                    guia.setNumeroPrestacaoTotal(Short.parseShort("1"));
                    
                    Collection colecaoGuiaPagamentoItem = new ArrayList();
                    GuiaPagamentoItem guiaPagamentoItem = new GuiaPagamentoItem();
                    guiaPagamentoItem.setDebitoTipo(debitoTipo);
                    guiaPagamentoItem.setValorDebito(guia.getValorDebito());
                    guiaPagamentoItem.setUltimaAlteracao(new Date());
                    colecaoGuiaPagamentoItem.add(guiaPagamentoItem);
                    
                    //LOCA_ID da tabela LOCALIDADE com LOCA_ICSEDE = 1
                    Localidade localidadeSede = getControladorCobranca().pesquisarLocalidadeSede();
                    String ids[] = getControladorFaturamento().inserirGuiaPagamento(guia, contrato.getUsuarioResponsavel(), contrato.getNumeroDiasEntreVencimentoParcel(), colecaoGuiaPagamentoItem, localidadeSede, true);
                    
                    //[SB0017] Vincula Guia
                    GuiaPagamentoGeral guiaGeral = new GuiaPagamentoGeral();
                    guiaGeral.setId(Integer.parseInt(ids[0]));
                    ContratoParcelamentoItem contratoParcelItem = new ContratoParcelamentoItem();
                    contratoParcelItem.setIndicadorItemCancelado(new Short("2"));
                    contratoParcelItem.setContrato(contrato);
                    DocumentoTipo docTipo = new DocumentoTipo();
                    docTipo.setId(DocumentoTipo.GUIA_PAGAMENTO);
                    contratoParcelItem.setDocumentoTipo(docTipo);
                    contratoParcelItem.setDebitoACobrarGeral(null);
                    contratoParcelItem.setContaGeral(null);
                    contratoParcelItem.setGuiaPagamentoGeral(guiaGeral);
                    contratoParcelItem.setCreditoARealizarGeral(null);
                    if(i == 0){
                        contratoParcelItem.setValorItem(valorPrimeiraGuia);
                    }else{
                        contratoParcelItem.setValorItem(valorDaGuia);
                    }
                    contratoParcelItem.setValarAcrescImpont(null);
                    contratoParcelItem.setUltimaAlteracao(new Date());
                    
                    getControladorUtil().inserir(contratoParcelItem);
                }
            }
            
            if(indicadorGuiaAcrecImpontualidade == 1){
                
                BigDecimal valorPrimeiraGuia = new BigDecimal("0");
                BigDecimal valorDaGuia = contrato.getValorTotalAcrescImpontualidade().divide(new BigDecimal(listaDeParcelas.size()),2 , BigDecimal.ROUND_UP);
                
                BigDecimal totalSomaGuias = valorDaGuia.multiply(new BigDecimal(listaDeParcelas.size()));
                BigDecimal diferenca = contrato.getValorTotalAcrescImpontualidade().subtract(totalSomaGuias);
                valorPrimeiraGuia = valorDaGuia.add(diferenca);
                
                for (int i = 0; i < listaDeParcelas.size(); i++) {
                    prestacaoContratoParcelamento  = listaDeParcelas.get(i);
                    GuiaPagamento guia = new GuiaPagamento();
                    guia.setDataEmissao(new Date());
                    
                    //Primeira guia com diferenca ajustada
                    if(i == 0){
                        guia.setValorDebito(valorPrimeiraGuia);
                    }else{
                        guia.setValorDebito(valorDaGuia);
                    }
                    
                    DebitoTipo debitoTipo = new DebitoTipo();
                    debitoTipo.setId(DebitoTipo.ACRESCIMOS_POR_IMPONTUALIDADE);
                    guia.setDebitoTipo(debitoTipo);
                    FinanciamentoTipo financiamentoTipo = new FinanciamentoTipo();
                    financiamentoTipo.setId(FinanciamentoTipo.ENTRADA_PARCELAMENTO);
                    guia.setFinanciamentoTipo(financiamentoTipo);
                    guia.setDataVencimento(prestacaoContratoParcelamento.getDataVencimento());
                    guia.setLocalidade(new Localidade());
                    guia.setImovel(new Imovel());
                    guia.setRegistroAtendimento(new RegistroAtendimento());
                    guia.setOrdemServico(new OrdemServico());
                    Cliente cliente = null;
                    if(clienteContrato != null){
                        cliente = clienteContrato.getCliente();
                    }else if(clienteSuperiorContrato != null){
                        cliente = clienteSuperiorContrato.getCliente();
                    }
                    guia.setCliente(cliente);
                    guia.setAnoMesReferenciaContabil(contrato.getAnoMesReferenciaFaturamento());
                    
                    LancamentoItemContabil lancamentoItemContabil = new LancamentoItemContabil(LancamentoItemContabil.ACRESCIMOS_POR_IMPONTUALIDADE);
                    guia.setLancamentoItemContabil(lancamentoItemContabil);

                    guia.setDebitoCreditoSituacaoAnterior(null);
                    
                    DebitoCreditoSituacao debitoCreditoSituacaoAtual = new DebitoCreditoSituacao(DebitoCreditoSituacao.NORMAL);
                    guia.setDebitoCreditoSituacaoAtual(debitoCreditoSituacaoAtual);
                    
                    guia.setUsuario(contrato.getUsuarioResponsavel());
                    guia.setObservacao("GUIA GERADA NA IMPLANTAÇÃO DO CONTRATO DE PARCELAMENTO POR CLIENTE " + contrato.getNumero()+ " da tabela cobranca.CONTRATO_PARCEL");
                    guia.setIndicadorEmitirObservacao(new Short("2"));
                    
                    guia.setNumeroPrestacaoDebito(Short.parseShort("1"));
                    guia.setNumeroPrestacaoTotal(Short.parseShort("1"));
                    
                    Collection colecaoGuiaPagamentoItem = new ArrayList();
                    GuiaPagamentoItem guiaPagamentoItem = new GuiaPagamentoItem();
                    guiaPagamentoItem.setDebitoTipo(debitoTipo);
                    guiaPagamentoItem.setValorDebito(guia.getValorDebito());
                    guiaPagamentoItem.setUltimaAlteracao(new Date());
                    colecaoGuiaPagamentoItem.add(guiaPagamentoItem);
                    
                    //LOCA_ID da tabela LOCALIDADE com LOCA_ICSEDE = 1
                    Localidade localidadeSede = getControladorCobranca().pesquisarLocalidadeSede();
                    String ids[] = getControladorFaturamento().inserirGuiaPagamento(guia, contrato.getUsuarioResponsavel(), contrato.getNumeroDiasEntreVencimentoParcel(), colecaoGuiaPagamentoItem, localidadeSede, true);
                    
                    //[SB0017] Vincula Guia
                    GuiaPagamentoGeral guiaGeral = new GuiaPagamentoGeral();
                    guiaGeral.setId(Integer.parseInt(ids[0]));
                    ContratoParcelamentoItem contratoParcelItem = new ContratoParcelamentoItem();
                    contratoParcelItem.setIndicadorItemCancelado(new Short("2"));
                    contratoParcelItem.setContrato(contrato);
                    DocumentoTipo docTipo = new DocumentoTipo();
                    docTipo.setId(DocumentoTipo.GUIA_PAGAMENTO);
                    contratoParcelItem.setDocumentoTipo(docTipo);
                    contratoParcelItem.setDebitoACobrarGeral(null);
                    contratoParcelItem.setContaGeral(null);
                    contratoParcelItem.setGuiaPagamentoGeral(guiaGeral);
                    contratoParcelItem.setCreditoARealizarGeral(null);
                    if(i == 0){
                        contratoParcelItem.setValorItem(valorPrimeiraGuia);
                    }else{
                        contratoParcelItem.setValorItem(valorDaGuia);
                    }
                    contratoParcelItem.setValarAcrescImpont(null);
                    contratoParcelItem.setUltimaAlteracao(new Date());
                    
                    getControladorUtil().inserir(contratoParcelItem);
                }
            }
        
//             ------------ REGISTRAR TRANSAÇÃO ----------------
            RegistradorOperacao registradorOperacao = new RegistradorOperacao(
                    Operacao.OPERACAO_INSERIR_CONTRATO_PARCELAMENTO_POR_CLIENTE,
                    contrato.getId(), contrato.getId(),
                    new UsuarioAcaoUsuarioHelper(usuarioLogado,
                    UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

                registradorOperacao.registrarOperacao(contrato);
//                 ------------ REGISTRAR TRANSAÇÃO ----------------
                
             // 1.7. Caso a forma de pagamento seja cobrança por extrato
             if (contrato.getCobrancaForma() != null
            		 && contrato.getCobrancaForma().getId() != null
            		 && contrato.getCobrancaForma().getId().compareTo(CobrancaForma.COBRANCA_POR_EXTRATO) == 0) {
            	 
            	 // O sistema gera o extrato do contrato de parcelamento por cliente 
            	 //   (<<Inclui>> [UC1200 - Gerar Extrato de Contrato Parcelamento Por Cliente] 
            	 //   passando o cliente e as parcelas).
            	 Cliente cliente = null;
            	 if (clienteSuperiorContrato != null 
            			 && clienteSuperiorContrato.getCliente() != null
            			 && clienteSuperiorContrato.getCliente().getId() != null) {
            		 cliente = clienteSuperiorContrato.getCliente();
            	 } else if (clienteContrato != null 
            			 && clienteContrato.getCliente() != null
            			 && clienteContrato.getCliente().getId() != null) {
            		 cliente = clienteContrato.getCliente();
            	 } 
            	 
            	 this.gerarExtratoDeContratoParcelamentoPorCliente(cliente.getId(), colecaoPrestacaoContratoParcelamento);
             }
                
        }catch (Exception ex) {
            sessionContext.setRollbackOnly();
            throw new ControladorException("erro.sistema", ex);
        }
        
        return idContrato;
        
    }
    
    /**
	 * Consultar Contrato de Parcelamento por Cliente Com Pagamento jah efetuado
	 * 
	 * [UC1143]  Consultar Dados do Contrato de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 10/05/2011
	 * 
	 */
	public boolean verificaContratoParcelComPagamentoFeito(Integer idContrato) throws ControladorException{
		try {
			return repositorioContratoParcelamento.verificaContratoParcelComPagamentoFeito(idContrato);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * Consultar Se Prestacao Ja Esta Paga
	 * 
	 * [UC1143]  Consultar Dados do Contrato de Parcelamento por Cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 10/05/2011
	 * 
	 */
	public boolean verificaPrestacaoPaga(Integer idPrestacao) throws ControladorException{
		try {
			return repositorioContratoParcelamento.verificaPrestacaoPaga(idPrestacao);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * Pesquisa os dados do contrato de parcelamento
	 * [FS0003] - Validar contrato
	 * 
	 * @author Mariana Victor
	 * @data 03/06/2011
	 */
	public Object[] pesquisarDadosContratoParcelamento(String numeroContrato)
		throws ControladorException {
		try {
			return repositorioContratoParcelamento.pesquisarDadosContratoParcelamento(numeroContrato);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * Pesquisa os dados do contrato de parcelamento
	 * [FS0003] - Validar contrato
	 * 
	 * @author Mariana Victor
	 * @data 03/06/2011
	 */
	public Integer pesquisarDadosContratoParcelamentoNumeroParcelasPagas(Integer idContrato)
		throws ControladorException {
		try {
			return repositorioContratoParcelamento.pesquisarDadosContratoParcelamentoNumeroParcelasPagas(idContrato);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * Pesquisa os dados do contrato de parcelamento por cliente
	 * [FS0005] - Verificar existência de contratos para o cliente
	 * 
	 * @author Mariana Victor
	 * @data 03/06/2011
	 */
	public Collection<Object[]> pesquisarDadosContratoParcelamentoPorCliente(Integer idCliente)
		throws ControladorException {
		try {
			return repositorioContratoParcelamento.pesquisarDadosContratoParcelamentoPorCliente(idCliente);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}


	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * Pesquisa os dados do contrato de parcelamento por cliente
	 * [SB0002] - Informar Dados do Pagamento
	 * 
	 * @author Mariana Victor
	 * @data 06/06/2011
	 */
	public Object[] pesquisarDadosContratoParcelamentoPorClienteSelecionado(Integer idContrato)
		throws ControladorException {
		try {
			return repositorioContratoParcelamento.pesquisarDadosContratoParcelamentoPorClienteSelecionado(idContrato);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * Desvincula a guia de pagamento do contrato de parcelamento, 
	 * atualizando dados do contrato na tabela cobranca.CONTRATO_PARCEL_ITEM  
	 * 
	 * @author Mariana Victor
	 * @data 08/06/2011
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarContratoParcelamentoItemDesvincularGuiaContrato(String[] idsGuias)
		throws ControladorException {
		try {
			repositorioContratoParcelamento.atualizarContratoParcelamentoItemDesvincularGuiaContrato(idsGuias);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * [SB0003] - Efetuar Pagamento da Parcela do Contrato de Parcelamento Por Cliente
	 * 
	 * @author Mariana Victor
	 * @since 06/06/2011
	 * */
	public Object[] efetuarPagamentoParcelaContratoParcelamentoPorCliente(InformarPagamentoContratoParcelamentoHelper helper,
			Usuario usuarioLogado) throws ControladorException {

		Object[] retorno = new Object[2];
		AvisoBancario avisoBancario = null;
		
		BigDecimal valorCalculadoPagamento = BigDecimal.ZERO;
		Collection<Pagamento> colecaoPagamento = new ArrayList();
		Fachada fachada = Fachada.getInstancia();
		
		try {
			Date dataPagamento = Util.converteStringParaDate(helper.getDataPagamento());
			
			ContratoParcelamento contratoParcelamento = this.repositorioContratoParcelamento
					.pesquisarContratoParcelamento( new Integer(helper.getIdRegistro()));

			Object[] dadosPrestacao = this.repositorioContratoParcelamento
					.pesquisarDadosContratoParcelamentoPorClienteSelecionado(contratoParcelamento.getId());

			Integer numeroPrestacao = (Integer) dadosPrestacao[0];
			BigDecimal valorMinimo = (BigDecimal) dadosPrestacao[1];
			Date dataVencimento = (Date) dadosPrestacao[2];
			Integer idPrestacao = (Integer) dadosPrestacao[3];

			PrestacaoContratoParcelamento prestacaoContratoParcelamento = new PrestacaoContratoParcelamento();
			prestacaoContratoParcelamento.setId(idPrestacao);
			prestacaoContratoParcelamento.setValor(valorMinimo);
			prestacaoContratoParcelamento.setNumero(numeroPrestacao);
			prestacaoContratoParcelamento.setDataVencimento(dataVencimento);
			prestacaoContratoParcelamento.setContratoParcelamento(contratoParcelamento);
			
			if(helper.getIdArrecadador() != null && !helper.getIdArrecadador().trim().equals("")) {
				//1.2.	Gera o aviso bancário para o pagamento da parcela
				//[SB0004 - Gerar Aviso Bancário].
				avisoBancario = this.gerarAvisoBancario(helper, fachada, prestacaoContratoParcelamento);
			}
			
			//Primeira Etapa do Pagamento da Parcela
			//[SB0005 - Gerar Pagamento Guias Juros de Parcelamento e Guias de Acréscimos]
			retorno = this.gerarPagamentoGuiasJurosParcelamentoGuiasAcrescimos(helper, fachada, 
					contratoParcelamento, valorCalculadoPagamento, avisoBancario, prestacaoContratoParcelamento, 
					usuarioLogado, colecaoPagamento);

			valorCalculadoPagamento = (BigDecimal) retorno[0];
			colecaoPagamento = (Collection<Pagamento>) retorno[1];
			
			//Segunda Etapa do Pagamento da Parcela
			//[SB0006 - Gerar Pagamento para Itens de Débito do Contrato].
			retorno = this.gerarPagamentoItensDebitoContrato(helper, fachada, 
					contratoParcelamento, valorCalculadoPagamento, usuarioLogado, avisoBancario, 
					prestacaoContratoParcelamento, colecaoPagamento);

			valorCalculadoPagamento = (BigDecimal) retorno[0];
			colecaoPagamento = (Collection<Pagamento>) retorno[1];

			//Terceira Etapa do Pagamento da Parcela
			BigDecimal valorArrecadacaoInformado = null;
			BigDecimal valorArrecadacaoCalculado = null;
			if(avisoBancario != null) {
				//1.5. O sistema atualiza o aviso bancário com o valor do pagamento calculado
				avisoBancario.setValorArrecadacaoCalculado(valorCalculadoPagamento);
				avisoBancario.setUltimaAlteracao(new Date());
				
				fachada.atualizar(avisoBancario);

				valorArrecadacaoInformado = avisoBancario.getValorArrecadacaoInformado();
				valorArrecadacaoCalculado = avisoBancario.getValorArrecadacaoCalculado();
			} else {
				valorArrecadacaoInformado = prestacaoContratoParcelamento.getValor();
				valorArrecadacaoCalculado = valorCalculadoPagamento;
			}
			
			FiltroContratoParcelamento filtroContratoParcelamento = new FiltroContratoParcelamento();
			filtroContratoParcelamento.adicionarParametro(new ParametroSimples(
					FiltroContratoParcelamento.ID, contratoParcelamento.getId()));
			Collection colecaoContratoParcelamento = fachada.pesquisar(
					filtroContratoParcelamento, ContratoParcelamento.class.getName());
			
			contratoParcelamento = (ContratoParcelamento) Util.retonarObjetoDeColecao(colecaoContratoParcelamento);
			
			//1.6. O sistema atualiza os dados do contrato na tabela cobranca.CONTRATO_PARCEL
			// Caso não haja mais valor a cobrar
			if (valorArrecadacaoInformado.compareTo(valorArrecadacaoCalculado) > 0){
				contratoParcelamento.setValorParcelamentoACobrar(
						BigDecimal.ZERO);
			} else {
				contratoParcelamento.setValorParcelamentoACobrar(
						contratoParcelamento.getValorParcelamentoACobrar().subtract(prestacaoContratoParcelamento.getValor()));
			}
			
			// Caso não haja mais valor a cobrar 
			if (valorArrecadacaoInformado.compareTo(valorArrecadacaoCalculado) > 0
					|| prestacaoContratoParcelamento.getNumero().compareTo(contratoParcelamento.getNumeroPrestacoes()) == 0){
				
				ParcelamentoSituacao parcelamentoSituacao = new ParcelamentoSituacao();
				parcelamentoSituacao.setId(ParcelamentoSituacao.CONCLUIDO);
				contratoParcelamento.setParcelamentoSituacao(parcelamentoSituacao);
				
			}
			
			contratoParcelamento.setUltimaAlteracao(new Date());
			
			fachada.atualizar(contratoParcelamento);
			
			// 1.7.	O sistema atualiza os dados da parcela paga na tabela cobranca.CONTRATO_PARCEL_PREST
			prestacaoContratoParcelamento.setValorPagamento(valorCalculadoPagamento);
			prestacaoContratoParcelamento.setDataPagamento(dataPagamento);
			prestacaoContratoParcelamento.setUltimaAlteracao(new Date());

			fachada.atualizar(prestacaoContratoParcelamento);
			
			// 1.8. Caso não haja mais valor a cobrar
			if (valorArrecadacaoInformado.compareTo(valorArrecadacaoCalculado) > 0){
				
				// 1.8.1. O sistema cria um pagamento com o VALOR RESTANTE 
				//  (AVBC_VLARRECADACAOINFORMADO menos AVBC_VLARRECADACAOCALCULADO).
				Pagamento pagamento = new Pagamento();

				// Caso o ano/mês da "Data do Pagamento" seja maior que 
				//  a PARM_AMREFERENCIAARRECADACAO da tabela SISTEMA_PARAMETROS
				SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
				Integer anoMesSistemaParametro = sistemaParametro.getAnoMesArrecadacao();
				Integer anoMesDataLancamento = Util.formataAnoMes(dataPagamento);
				if (anoMesDataLancamento > anoMesSistemaParametro) {
					pagamento.setAnoMesReferenciaArrecadacao(anoMesDataLancamento);
				} else {
					pagamento.setAnoMesReferenciaArrecadacao(anoMesSistemaParametro);
				}
				
				// VALOR RESTANTE
				pagamento.setValorPagamento(valorArrecadacaoInformado.subtract(valorArrecadacaoCalculado));
				// "Data do Pagamento" informada
				pagamento.setDataPagamento(dataPagamento);

                // LOCA_ID da tabela cadastro.LOCALIDADE com LOCA_ICSEDE=1 (um)
                Localidade localidadeSede = getControladorCobranca().pesquisarLocalidadeSede();
				pagamento.setLocalidade(localidadeSede);
				
				// DOTP_ID da tabela cobranca.DOCUMENTO _TIPO para DOTP_DSDOCUMENTOTIPO = "CONTA"
				DocumentoTipo documentoTipo = new DocumentoTipo();
				documentoTipo.setId(DocumentoTipo.CONTA);
				pagamento.setDocumentoTipo(documentoTipo);
				
				// AVBC_ID da tabela arrecadação.AVISO_BANCARIO
				pagamento.setAvisoBancario(avisoBancario);
				
				// ARFM_ID da tabela arrecadação.AVISO_BANCARIO
				pagamento.setArrecadacaoForma(avisoBancario.getArrecadacaoForma());
				
				// Data e Hora corrente
				pagamento.setUltimaAlteracao(new Date());
				
				Cliente clienteContrato = this.repositorioContratoParcelamento.pesquisarClienteContrato(contratoParcelamento.getId()).getCliente();
				
				pagamento.setCliente(clienteContrato);

				// Inclui o pagamento na tabela arrecadacao.PAGAMENTO
				this.getControladorUtil().inserir(pagamento);
				
				// 1.8.2. E o sistema cancela as guias de juros e/ou acréscimos, caso existam
				// 1.8.2.1.	Seleciona as guias de pagamento do contrato, atuais e sem pagamento 
				Collection<Object[]> dadosGuias = this.repositorioContratoParcelamento
					.pesquisarGuiasPagamentoContratoAtuaisSemPagamento(contratoParcelamento.getId());

				GuiaPagamento guiaPagamento = new GuiaPagamento();
				Collection guiasPagamento = new ArrayList();
				String[] registrosRemocao = null;
				
				if (dadosGuias != null && !dadosGuias.isEmpty()) {
					Iterator iterator = dadosGuias.iterator();
					registrosRemocao = new String[dadosGuias.size()];
					int posicao = 0;
					// 1.8.2.2.	Para as guias de pagamento selecionadas:
					while(iterator.hasNext()) {
						Object[] dadosGuia = (Object[]) iterator.next();
						Integer idGuia = (Integer) dadosGuia[0];
						Integer anoMesContabil = (Integer) dadosGuia[3];
						Date dataEmissao = (Date) dadosGuia[5];
						Date dataVencimentoGuia = (Date) dadosGuia[6];
						BigDecimal valorDebito = (BigDecimal) dadosGuia[7];
						Short indicadoCobrancaMulta = (Short) dadosGuia[8];
						Short indicadorEmitirObservacao = (Short) dadosGuia[9];
						
						Cliente cliente = new Cliente();
						Imovel imovel = new Imovel();
						FinanciamentoTipo financiamentoTipo = new FinanciamentoTipo();
						Localidade localidade = new Localidade();
						DebitoTipo debitoTipo = new DebitoTipo();
						LancamentoItemContabil lancamentoItemContabil = new LancamentoItemContabil();
						
						if (dadosGuia[1] != null) {
							Integer idCliente = (Integer) dadosGuia[1];
							cliente.setId(idCliente);
						}
						
						if (dadosGuia[2] != null) {
							Integer idImovel = (Integer) dadosGuia[2];
							imovel.setId(idImovel);
						}
						
						if (dadosGuia[4] != null) {
							Integer idFinanciamentoTipo = (Integer) dadosGuia[4];
							financiamentoTipo.setId(idFinanciamentoTipo);
						}
						
						if (dadosGuia[10] != null) {
							Integer idLocalidade = (Integer) dadosGuia[10];
							localidade.setId(idLocalidade);
						}
						
						if (dadosGuia[11] != null) {
							Integer idDebitoTipo = (Integer) dadosGuia[11];
							debitoTipo.setId(idDebitoTipo);
						}
						
						if (dadosGuia[12] != null) {
							Integer idLancamentoItemContabil = (Integer) dadosGuia[12];
							lancamentoItemContabil.setId(idLancamentoItemContabil);
						}
						
						GuiaPagamento guia = new GuiaPagamento();
						guia.setId(idGuia);
						guia.setAnoMesReferenciaContabil(anoMesContabil);
						guia.setUltimaAlteracao(new Date());
						guia.setDataEmissao(dataEmissao);
						guia.setDataVencimento(dataVencimentoGuia);
						guia.setValorDebito(valorDebito);
						guia.setIndicadoCobrancaMulta(indicadoCobrancaMulta);
						guia.setIndicadorEmitirObservacao(indicadorEmitirObservacao);
						if (cliente != null) {
							guia.setCliente(cliente);
						}
						if (imovel != null) {
							guia.setImovel(imovel);
						}
						if (financiamentoTipo != null) {
							guia.setFinanciamentoTipo(financiamentoTipo);
						}
						if (localidade != null) {
							guia.setLocalidade(localidade);
						}
						if (debitoTipo != null) {
							guia.setDebitoTipo(debitoTipo);
						}
						if (lancamentoItemContabil != null) {
							guia.setLancamentoItemContabil(lancamentoItemContabil);
						}
						
						guiasPagamento.add(guia);
						registrosRemocao[posicao] = idGuia.toString();
						
						if (posicao == 0) {
							guiaPagamento.setCliente(cliente);
							guiaPagamento.setImovel(imovel);
						}
						
						posicao++; 
					}

					// 1.8.2.2.1. Cancela as guias de pagamento
					// 1.8.2.2.1.1.	<<Inclui>> [UC0188 - Manter Guia de Pagamento] - [SB0001 - Cancelar Guia de Pagamento]
					fachada.manterGuiaPagamento(guiaPagamento, guiasPagamento, registrosRemocao, null, usuarioLogado);
					
				}
				
			}
			
		} catch (Exception ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

		retorno[0] = avisoBancario;
		retorno[1] = colecaoPagamento;
		
		return retorno;
	}
	
	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * [SB0004] - Gerar Aviso Bancário
	 * 
	 * @author Mariana Victor
	 * @throws ErroRepositorioException 
	 * @since 06/06/2011
	 * */
	private AvisoBancario gerarAvisoBancario(InformarPagamentoContratoParcelamentoHelper helper, Fachada fachada, 
			PrestacaoContratoParcelamento prestacaoContratoParcelamento)
		throws ErroRepositorioException {
		
		AvisoBancario avisoBancario = new AvisoBancario();

		// Pesquisa o Arrecadador
		FiltroArrecadador filtroArrecadador = new FiltroArrecadador();
		filtroArrecadador.adicionarParametro(new ParametroSimples(
				FiltroArrecadador.CODIGO_AGENTE,helper.getIdArrecadador()));
		Collection colecaoArrecadador = fachada.pesquisar(
				filtroArrecadador, Arrecadador.class.getName());
		if (colecaoArrecadador == null || colecaoArrecadador.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente", new String[]{"Arrecadador"});
		}
		Arrecadador arrecadador = (Arrecadador) Util.retonarObjetoDeColecao(colecaoArrecadador);
		
		// Pesquisa o ArrecadadorContrato para pegar a conta bancária
		FiltroArrecadadorContrato filtroArrecadadorContrato = new FiltroArrecadadorContrato();
		filtroArrecadadorContrato.adicionarParametro(new ParametroSimples(
				FiltroArrecadadorContrato.ARRECADADOR_ID, arrecadador.getId()));
		Collection colecaoArrecadadorContrato = fachada.pesquisar(
				filtroArrecadadorContrato, ArrecadadorContrato.class.getName());
		if (colecaoArrecadadorContrato == null || colecaoArrecadadorContrato.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente", new String[]{"Contrato de Arrecadador"});
		}
		ArrecadadorContrato arrecadadorContrato = (ArrecadadorContrato) Util.retonarObjetoDeColecao(colecaoArrecadadorContrato);
		
		// Cria a ArrecadaçãoForma do tipo "Depósito"
		ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma();
		arrecadacaoForma.setId(ArrecadacaoForma.DEPOSITO);
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		Date dataPagamento = Util.converteStringParaDate(helper.getDataPagamento());
		Integer anoMesSistemaParametro = sistemaParametro.getAnoMesArrecadacao();
		Integer anoMesDataLancamento = Util.formataAnoMes(dataPagamento);
		Integer anoMes = null;
		
		//Caso o ano/mês da data do lançamento ("Data do Pagamento" informada) 
		// seja maior que o PARM_AMREFERENCIAARRECADACAO da tabela SISTEMA_PARAMETROS
		if (anoMesDataLancamento > anoMesSistemaParametro) {
			anoMes = anoMesDataLancamento;
		} else {
			anoMes = anoMesSistemaParametro;
		}
		
		//1. O sistema gera os dados do aviso bancário na tabela arrecadacao.AVISO_BANCARIO  com os seguintes valores
		avisoBancario.setArrecadador(arrecadador);
		avisoBancario.setDataLancamento(dataPagamento);
		avisoBancario.setNumeroSequencial(new Short("0"));
		avisoBancario.setDataPrevista(prestacaoContratoParcelamento.getDataVencimento());
		avisoBancario.setDataRealizada(dataPagamento);
		avisoBancario.setValorRealizado(prestacaoContratoParcelamento.getValor());
		avisoBancario.setValorArrecadacaoCalculado(BigDecimal.ZERO);
		avisoBancario.setValorDevolucaoCalculado(BigDecimal.ZERO);
		avisoBancario.setValorArrecadacaoInformado(prestacaoContratoParcelamento.getValor());
		avisoBancario.setValorDevolucaoInformado(BigDecimal.ZERO);
		avisoBancario.setValorContabilizado(BigDecimal.ZERO);
		avisoBancario.setAnoMesReferenciaArrecadacao(anoMes);
		avisoBancario.setIndicadorCreditoDebito(ConstantesSistema.SIM);
		avisoBancario.setNumeroDocumento(prestacaoContratoParcelamento.getId());
		avisoBancario.setUltimaAlteracao(new Date());
		avisoBancario.setContaBancaria(arrecadadorContrato.getContaBancariaDepositoArrecadacao());
		avisoBancario.setArrecadadorMovimento(null);
		avisoBancario.setArrecadacaoForma(arrecadacaoForma);
		
		Integer idAvisoBancario = (Integer) fachada.inserir(avisoBancario);
		
		avisoBancario.setId(idAvisoBancario);
		
		return avisoBancario; 
		
	}
	
	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * [SB0005] - Gerar Pagamento Guias Juros de Parcelamento e Guias de Acréscimos
	 * 
	 * @author Mariana Victor
	 * @throws ErroRepositorioException 
	 * @throws NumberFormatException 
	 * @throws ControladorException 
	 * @since 06/06/2011
	 * */
	private Object[] gerarPagamentoGuiasJurosParcelamentoGuiasAcrescimos(InformarPagamentoContratoParcelamentoHelper helper,
			Fachada fachada, ContratoParcelamento contratoParcelamento, BigDecimal valorCalculadoPagamento, AvisoBancario avisoBancario,
			PrestacaoContratoParcelamento prestacaoContratoParcelamento, Usuario usuarioLogado,
			Collection<Pagamento> colecaoPagamento) 
		throws NumberFormatException, ErroRepositorioException, ControladorException{
		
		Object[] retorno = new Object[2];
		
		//1.1.	Caso o parcelamento tenha sido realizado com juros 
		if (contratoParcelamento.getIndicadorParcelamentoJuros() != null
				&& contratoParcelamento.getIndicadorParcelamentoJuros().compareTo(ConstantesSistema.SIM) == 0
				&& contratoParcelamento.getValorJurosParcelamento() != null
				&& contratoParcelamento.getValorJurosParcelamento().compareTo(BigDecimal.ZERO) != 0) {

            DebitoTipo debitoTipo = this.repositorioFaturamento
            	.obterDebitoTipoCodigoConstante(DebitoTipo.JUROS_SOBRE_CONTR_PARCELAMENTO);
            
			Object[] dadosGuia = this.repositorioContratoParcelamento.pesquisarGuiaDeJurosOuComAcrescimos(
					contratoParcelamento.getId(), debitoTipo.getId(), prestacaoContratoParcelamento.getId());
			
			if (dadosGuia != null) {
				Integer idGuia = (Integer) dadosGuia[0];
				BigDecimal valorItem = (BigDecimal) dadosGuia[1];
				Integer idContratoParcItem = (Integer) dadosGuia[2];
				Integer idLoca = (Integer) dadosGuia[3];
				Integer idImovel = (Integer) dadosGuia[4];
				Integer idCliente = (Integer) dadosGuia[5];
				
				BigDecimal valorPrestacao = prestacaoContratoParcelamento.getValor();
				Integer numeroPrestacao = prestacaoContratoParcelamento.getNumero();

				//[FS0009 - Verificar valor da guia de juros superior ao valor restante do pagamento]
				if (valorItem.compareTo(
						valorPrestacao.subtract(valorCalculadoPagamento)) > 0) {

					throw new ActionServletException("atencao.contrato_parcelamento_por_cliente.guia_juros.superior_ao.valor_restante_pagamento",
							new String[] {"juros",
										idGuia.toString(),
										Util.formatarMoedaReal(valorItem),
										Util.formatarMoedaReal(valorPrestacao.subtract(valorCalculadoPagamento)),
										numeroPrestacao.toString(),
										contratoParcelamento.getNumero()
										});
				}

				DocumentoTipo documentoTipo = new DocumentoTipo();
				documentoTipo.setId(DocumentoTipo.GUIA_PAGAMENTO);
				
				Localidade localidade = new Localidade();
				localidade.setId(idLoca);
				
				Imovel imovel = new Imovel();
				imovel.setId(idImovel);
				
				Cliente cliente = new Cliente();
				cliente.setId(idCliente);
				
				GuiaPagamento guia = new GuiaPagamento();
				guia.setId(idGuia);
				guia.setLocalidade(localidade);
				guia.setImovel(imovel);
				guia.setCliente(cliente);
				
				GuiaPagamentoGeral guiaGeral = new GuiaPagamentoGeral();
				guiaGeral.setGuiaPagamento(guia);
				
				ContratoParcelamentoItem contratoParcelamentoItem = new ContratoParcelamentoItem();
				contratoParcelamentoItem.setId(idContratoParcItem);
				contratoParcelamentoItem.setContrato(contratoParcelamento);
				contratoParcelamentoItem.setValorItem(valorItem);
				contratoParcelamentoItem.setGuiaPagamentoGeral(guiaGeral);
				contratoParcelamentoItem.setDocumentoTipo(documentoTipo);

				//[SB0007 - Gerar Pagamento], passando Indicador Pagamento Complementar com o valor 2 (dois)
				retorno = this.gerarPagamento(helper, fachada, valorCalculadoPagamento, new Short("2"), 
						DocumentoTipo.GUIA_PAGAMENTO, debitoTipo.getId(),
						prestacaoContratoParcelamento, contratoParcelamentoItem, avisoBancario, usuarioLogado,
						colecaoPagamento);

				valorCalculadoPagamento = (BigDecimal) retorno[0];
				colecaoPagamento = (Collection<Pagamento>) retorno[1];
				
			}
			
		}
		
		//1.2.	Caso o parcelamento tenha sido realizado com acréscimos por impontualidade 
		if (contratoParcelamento.getIndicadorDebitosAcrescimos() != null
				&& contratoParcelamento.getIndicadorDebitosAcrescimos().compareTo(ConstantesSistema.SIM) == 0
				&& contratoParcelamento.getValorTotalAcrescImpontualidade() != null
				&& contratoParcelamento.getValorTotalAcrescImpontualidade().compareTo(BigDecimal.ZERO) != 0) {

			Object[] dadosGuia = this.repositorioContratoParcelamento.pesquisarGuiaDeJurosOuComAcrescimos(
					contratoParcelamento.getId(), DebitoTipo.ACRESCIMOS_POR_IMPONTUALIDADE, prestacaoContratoParcelamento.getId());
			
			if (dadosGuia != null) {
				Integer idGuia = (Integer) dadosGuia[0];
				BigDecimal valorItem = (BigDecimal) dadosGuia[1];
				Integer idContratoParcItem = (Integer) dadosGuia[2];
				Integer idLoca = (Integer) dadosGuia[3];
				Integer idImovel = null;
				Integer idCliente = null;
				
				if (dadosGuia[4] != null) {
					idImovel = (Integer) dadosGuia[4];
				}
				if (dadosGuia[5] != null) {
					idCliente = (Integer) dadosGuia[5];
				}
				
				BigDecimal valorPrestacao = prestacaoContratoParcelamento.getValor();
				Integer numeroPrestacao = prestacaoContratoParcelamento.getNumero();

				//[FS0010 - Verificar valor da guia de acréscimos superior ao valor restante do pagamento]
				if (valorItem.compareTo(
						valorPrestacao.subtract(valorCalculadoPagamento)) > 0) {

					throw new ActionServletException("atencao.contrato_parcelamento_por_cliente.guia_juros.superior_ao.valor_restante_pagamento",
							new String[] {"acréscimos",
										idGuia.toString(),
										Util.formatarMoedaReal(valorItem),
										Util.formatarMoedaReal(valorPrestacao.subtract(valorCalculadoPagamento)),
										numeroPrestacao.toString(),
										contratoParcelamento.getNumero()
										});
				}

				DocumentoTipo documentoTipo = new DocumentoTipo();
				documentoTipo.setId(DocumentoTipo.GUIA_PAGAMENTO);
				
				Localidade localidade = new Localidade();
				localidade.setId(idLoca);
				
				Imovel imovel = new Imovel();
				if (idImovel != null) {
					imovel.setId(idImovel);
				}
				
				Cliente cliente = new Cliente();
				if (idCliente != null) {
					cliente.setId(idCliente);
				}
				
				GuiaPagamento guia = new GuiaPagamento();
				guia.setId(idGuia);
				guia.setLocalidade(localidade);
				guia.setImovel(imovel);
				guia.setCliente(cliente);
				
				GuiaPagamentoGeral guiaGeral = new GuiaPagamentoGeral();
				guiaGeral.setGuiaPagamento(guia);
				
				ContratoParcelamentoItem contratoParcelamentoItem = new ContratoParcelamentoItem();
				contratoParcelamentoItem.setId(idContratoParcItem);
				contratoParcelamentoItem.setContrato(contratoParcelamento);
				contratoParcelamentoItem.setValorItem(valorItem);
				contratoParcelamentoItem.setGuiaPagamentoGeral(guiaGeral);
				contratoParcelamentoItem.setDocumentoTipo(documentoTipo);
				
				//[SB0007 - Gerar Pagamento], passando Indicador Pagamento Complementar com o valor 2 (dois).
				retorno = this.gerarPagamento(helper, fachada, valorCalculadoPagamento, new Short("2"), 
						DocumentoTipo.GUIA_PAGAMENTO, DebitoTipo.ACRESCIMOS_POR_IMPONTUALIDADE,
						prestacaoContratoParcelamento, contratoParcelamentoItem, avisoBancario, usuarioLogado,
						colecaoPagamento);
				
				valorCalculadoPagamento = (BigDecimal) retorno[0];
				colecaoPagamento = (Collection<Pagamento>) retorno[1];
				
			}
		}
		
		retorno[0] = valorCalculadoPagamento;
		retorno[1] = colecaoPagamento;
		
		return retorno;
	}
	
	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * [SB0006] - Gerar Pagamento para Itens de Débito do Contrato
	 * 
	 * @author Mariana Victor
	 * @throws ErroRepositorioException 
	 * @throws ControladorException 
	 * @since 06/06/2011
	 * */
	private Object[] gerarPagamentoItensDebitoContrato(InformarPagamentoContratoParcelamentoHelper helper, Fachada fachada, 
			ContratoParcelamento contratoParcelamento, BigDecimal valorCalculadoPagamento, Usuario usuarioLogado,
			AvisoBancario avisoBancario, PrestacaoContratoParcelamento prestacaoContratoParcelamento,
			Collection<Pagamento> colecaoPagamento) 
		throws ErroRepositorioException, ControladorException{

		Object[] retorno = new Object[2];
		
		// 1.1. Caso a parcela paga 
		if (helper.getNumeroParcela() != null && new Integer(helper.getNumeroParcela()).compareTo(new Integer(1)) >0) {
			
			Object[] dadosItem = this.repositorioContratoParcelamento
					.pesquisarItemDebitoValorPagoAMenor(contratoParcelamento.getId());
			
			//1.1.1. Caso exista item de débito com valor pago a menor 
			if (dadosItem != null) {
				Integer idItem = (Integer) dadosItem[0];
				Integer idDocTipo = (Integer) dadosItem[1];
				BigDecimal valorItem = (BigDecimal) dadosItem[2];
				
				//1.1.1.1. Caso o item de débito seja do tipo conta 
				if (idDocTipo.compareTo(DocumentoTipo.CONTA) == 0) {
					
					// seleciona a conta com valor pago a menor
					Object[] dadosConta = this.repositorioContratoParcelamento.pesquisarContaValorPagoAMenor(idItem);
					
					//1.1.1.1.1. Caso a conta tenha sido selecionada:
					if(dadosConta != null) {
						
						Integer idConta = (Integer) dadosConta[0];
						Integer anoMesConta = (Integer) dadosConta[1];
						Integer idLoca = (Integer) dadosConta[2];
						Integer idImovel = (Integer) dadosConta[3];
						
						Localidade localidade = new Localidade();
						localidade.setId(idLoca);
						
						Imovel imovel = new Imovel();
						imovel.setId(idImovel);
						
						Conta conta = new Conta();
						conta.setId(idConta);
						conta.setReferencia(anoMesConta);
						conta.setLocalidade(localidade);
						conta.setImovel(imovel);
						
						ContaGeral contaGeral = new ContaGeral();
						contaGeral.setConta(conta);
						contaGeral.setId(idConta);
						
						DocumentoTipo documentoTipo = new DocumentoTipo();
						documentoTipo.setId(DocumentoTipo.CONTA);
						
						ContratoParcelamentoItem contratoParcelamentoItem = new ContratoParcelamentoItem();
						contratoParcelamentoItem.setId(idItem);
						contratoParcelamentoItem.setValorItem(valorItem);
						contratoParcelamentoItem.setContaGeral(contaGeral);
						contratoParcelamentoItem.setContrato(contratoParcelamento);
						contratoParcelamentoItem.setDocumentoTipo(documentoTipo);
						
						// 1.1.1.1.1.1.	O sistema gera o pagamento complementar para a conta [SB0007 - Gerar Pagamento],
						//  passando Indicador Pagamento Complementar com o valor 1 (um).
						retorno = this.gerarPagamento(helper, fachada, valorCalculadoPagamento, ConstantesSistema.SIM, 
								DocumentoTipo.CONTA, null, prestacaoContratoParcelamento, contratoParcelamentoItem, 
								avisoBancario, usuarioLogado, colecaoPagamento);

						valorCalculadoPagamento = (BigDecimal) retorno[0];
						colecaoPagamento = (Collection<Pagamento>) retorno[1];
						
						Collection colecaoConta = new ArrayList();
						colecaoConta.add(conta);

						Date dataPagamento = Util.converteStringParaDate(helper.getDataPagamento());
						
						//<<Inclui>> [UC0151] Alterar Vencimento de Conta
						this.getControladorFaturamento().alterarVencimentoConta(colecaoConta,
						null, dataPagamento, usuarioLogado);
						
					}
				}
				
				// 1.1.1.2.	Caso o item de débito seja do tipo débito a cobrar 
				if (idDocTipo.compareTo(DocumentoTipo.DEBITO_A_COBRAR) == 0) {
					
					// seleciona o débito a cobrar com valor pago a menor
					Object[] dadosDebitoACobrar = this.repositorioContratoParcelamento.pesquisarDebitoACobrarValorPagoAMenor(idItem);
					
					// 1.1.1.2.1. Caso o débito a cobrar tenha sido selecionado
					if (dadosDebitoACobrar != null) {
						
						Integer idDebitoACobrar = (Integer) dadosDebitoACobrar[0];
						Integer anoMesDebito = (Integer) dadosDebitoACobrar[1];
						Integer idLoca = (Integer) dadosDebitoACobrar[2];
						Integer idImovel = (Integer) dadosDebitoACobrar[3];
						Integer idDebitoTipo = (Integer) dadosDebitoACobrar[4];
						
						Localidade localidade = new Localidade();
						localidade.setId(idLoca);
						
						Imovel imovel = new Imovel();
						imovel.setId(idImovel);
						
						DebitoACobrar debitoACobrar = new DebitoACobrar();
						debitoACobrar.setId(idDebitoACobrar);
						debitoACobrar.setAnoMesReferenciaDebito(anoMesDebito);
						debitoACobrar.setLocalidade(localidade);
						debitoACobrar.setImovel(imovel);
						
						DebitoACobrarGeral debitoACobrarGeral = new DebitoACobrarGeral();
						debitoACobrarGeral.setDebitoACobrar(debitoACobrar);
						debitoACobrarGeral.setId(idDebitoACobrar);
						
						DocumentoTipo documentoTipo = new DocumentoTipo();
						documentoTipo.setId(DocumentoTipo.DEBITO_A_COBRAR);
						
						ContratoParcelamentoItem contratoParcelamentoItem = new ContratoParcelamentoItem();
						contratoParcelamentoItem.setId(idItem);
						contratoParcelamentoItem.setValorItem(valorItem);
						contratoParcelamentoItem.setDebitoACobrarGeral(debitoACobrarGeral);
						contratoParcelamentoItem.setContrato(contratoParcelamento);
						contratoParcelamentoItem.setDocumentoTipo(documentoTipo);
						
						// 1.1.1.2.1.1.	O sistema gera o pagamento complementar para o débito a cobrar
						// [SB0007 - Gerar Pagamento], passando Indicador Pagamento Complementar com o valor 1 (um).
						retorno = this.gerarPagamento(helper, fachada, valorCalculadoPagamento, ConstantesSistema.SIM, 
								DocumentoTipo.DEBITO_A_COBRAR, idDebitoTipo, prestacaoContratoParcelamento, 
								contratoParcelamentoItem, avisoBancario, usuarioLogado, colecaoPagamento);

						valorCalculadoPagamento = (BigDecimal) retorno[0];
						colecaoPagamento = (Collection<Pagamento>) retorno[1];

					}
				}
			}
		}
		
		Collection<Object[]> dadosItensDebito = this.repositorioContratoParcelamento
			.pesquisarItensDebitoACobrar(contratoParcelamento.getId());
		// 1.2.	Caso existam itens de débito do tipo débito a cobrar para geração de pagamento
		if (dadosItensDebito != null && !dadosItensDebito.isEmpty()) {
			Iterator iteratorItens = dadosItensDebito.iterator();

			// 1.2.1. Para cada item de débito do tipo debito a cobrar selecionado:
			while(iteratorItens.hasNext()) {
				
				Object[] dadosItem = (Object[]) iteratorItens.next();
				Integer idItem = (Integer) dadosItem[0];
				BigDecimal valorItem = (BigDecimal) dadosItem[1];
				Integer idDebito = (Integer) dadosItem[2];
				Integer anoMesDebito = (Integer) dadosItem[3];
				Integer idLoca = (Integer) dadosItem[4];
				Integer idImovel = (Integer) dadosItem[5];
				Integer idDebitoTipo = (Integer) dadosItem[6];
				
				// 1.2.1.1.	Caso ainda exista saldo para completar o valor pago 
				if ((prestacaoContratoParcelamento.getValor().subtract(valorCalculadoPagamento))
						.compareTo(BigDecimal.ZERO) > 0) {
					Localidade localidade = new Localidade();
					localidade.setId(idLoca);
					
					Imovel imovel = new Imovel();
					imovel.setId(idImovel);
					
					DebitoACobrar debitoACobrar = new DebitoACobrar();
					debitoACobrar.setId(idDebito);
					debitoACobrar.setAnoMesCobrancaDebito(anoMesDebito);
					debitoACobrar.setLocalidade(localidade);
					debitoACobrar.setImovel(imovel);
					
					DebitoACobrarGeral debitoACobrarGeral = new DebitoACobrarGeral();
					debitoACobrarGeral.setDebitoACobrar(debitoACobrar);
					debitoACobrarGeral.setId(idDebito);
					
					DocumentoTipo documentoTipo = new DocumentoTipo();
					documentoTipo.setId(DocumentoTipo.DEBITO_A_COBRAR);
					
					ContratoParcelamentoItem contratoParcelamentoItem = new ContratoParcelamentoItem();
					contratoParcelamentoItem.setId(idItem);
					contratoParcelamentoItem.setValorItem(valorItem);
					contratoParcelamentoItem.setDebitoACobrarGeral(debitoACobrarGeral);
					contratoParcelamentoItem.setContrato(contratoParcelamento);
					contratoParcelamentoItem.setDocumentoTipo(documentoTipo);
					
					// 1.2.1.1.1. O sistema gera o pagamento para debito a cobrar
					// [SB0007 - Gerar Pagamento], passando Indicador Pagamento Complementar com o valor 2 (dois).
					retorno = this.gerarPagamento(helper, fachada, valorCalculadoPagamento, ConstantesSistema.NAO, 
							DocumentoTipo.DEBITO_A_COBRAR, idDebitoTipo, prestacaoContratoParcelamento, 
							contratoParcelamentoItem, avisoBancario, usuarioLogado, colecaoPagamento);

					valorCalculadoPagamento = (BigDecimal) retorno[0];
					colecaoPagamento = (Collection<Pagamento>) retorno[1];
				}
			}
		}
		
		Collection<Object[]> dadosItensConta = this.repositorioContratoParcelamento
			.pesquisarItensDebitoConta(contratoParcelamento.getId());
		
		// 1.3. Caso existam itens de débito do tipo conta para geração de pagamento 
		if (dadosItensConta != null && !dadosItensConta.isEmpty()) {
			Iterator iteratorItens = dadosItensConta.iterator();

			while(iteratorItens.hasNext()) {
				Object[] dadosItem = (Object[]) iteratorItens.next();
				
				Integer idItem = (Integer) dadosItem[0];
				BigDecimal valorItem = (BigDecimal) dadosItem[1];
				Integer idConta = (Integer) dadosItem[2];
				Integer anoMesConta = (Integer) dadosItem[3];
				Integer idLoca = (Integer) dadosItem[4];
				Integer idImovel = (Integer) dadosItem[5];
				
				if ((prestacaoContratoParcelamento.getValor().subtract(valorCalculadoPagamento))
						.compareTo(BigDecimal.ZERO) > 0) {
				
					Localidade localidade = new Localidade();
					localidade.setId(idLoca);
					
					Imovel imovel = new Imovel();
					imovel.setId(idImovel);
					
					Conta conta = new Conta();
					conta.setId(idConta);
					conta.setReferencia(anoMesConta);
					conta.setLocalidade(localidade);
					conta.setImovel(imovel);
					
					ContaGeral contaGeral = new ContaGeral();
					contaGeral.setConta(conta);
					contaGeral.setId(idConta);
					
					DocumentoTipo documentoTipo = new DocumentoTipo();
					documentoTipo.setId(DocumentoTipo.CONTA);
					
					ContratoParcelamentoItem contratoParcelamentoItem = new ContratoParcelamentoItem();
					contratoParcelamentoItem.setId(idItem);
					contratoParcelamentoItem.setValorItem(valorItem);
					contratoParcelamentoItem.setContaGeral(contaGeral);
					contratoParcelamentoItem.setContrato(contratoParcelamento);
					contratoParcelamentoItem.setDocumentoTipo(documentoTipo);
					
					retorno = this.gerarPagamento(helper, fachada, valorCalculadoPagamento, ConstantesSistema.NAO, 
							DocumentoTipo.CONTA, null, prestacaoContratoParcelamento, 
							contratoParcelamentoItem, avisoBancario, usuarioLogado, colecaoPagamento);

					valorCalculadoPagamento = (BigDecimal) retorno[0];
					colecaoPagamento = (Collection<Pagamento>) retorno[1];
					
					Collection colecaoConta = new ArrayList();
					colecaoConta.add(conta);

					Date dataPagamento = Util.converteStringParaDate(helper.getDataPagamento());
					
					//<<Inclui>> [UC0151] Alterar Vencimento de Conta
					this.getControladorFaturamento().alterarVencimentoConta(colecaoConta,
					null, dataPagamento, usuarioLogado);
					
				}
			}
		}
		
		retorno[0] = valorCalculadoPagamento;
		retorno[1] = colecaoPagamento;
		
		return retorno;
		
	}
	
	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * [SB0007] - Gerar Pagamento
	 * 
	 * @author Mariana Victor
	 * @throws ErroRepositorioException 
	 * @throws ControladorException 
	 * @since 06/06/2011
	 * */
	private Object[] gerarPagamento(InformarPagamentoContratoParcelamentoHelper helper,
			Fachada fachada, BigDecimal valorCalculadoPagamento,
			Short indicadorPagamentoComplementar, Integer documentoTipo, Integer debitoTipo,
			PrestacaoContratoParcelamento prestacaoContratoParcelamento, 
			ContratoParcelamentoItem contratoParcelamentoItem, AvisoBancario avisoBancario,
			Usuario usuarioLogado, Collection<Pagamento> colecaoPagamento) 
		throws ErroRepositorioException, ControladorException {
		
		Object[] retorno = new Object[2];
		
		ContratoParcelamento contratoParcelamento = contratoParcelamentoItem.getContrato();
		
		BigDecimal valorPagamento = null;
		
		// 1.1.1. Caso o pagamento seja complementar (Indicador Pagamento Complementar com o valor 1 (um)):
		if (indicadorPagamentoComplementar.compareTo(ConstantesSistema.SIM) == 0) {
			
			BigDecimal valorPagoItem = this.repositorioContratoParcelamento.pesquisarValorPagoItem(
					contratoParcelamento.getId(), contratoParcelamentoItem.getId());
			
			// 1.1.1.1.	Caso o valor do item menos valor já pago para o item 
			//  seja superior ao valor restante da parcela
			if (contratoParcelamentoItem.getValorItem().subtract(valorPagoItem).compareTo(
					prestacaoContratoParcelamento.getValor().subtract(valorCalculadoPagamento)) > 0){
				valorPagamento = prestacaoContratoParcelamento.getValor().subtract(valorCalculadoPagamento);
			} else {
				// 1.1.1.2.	Caso contrário, ou seja, o valor do item não seja superior 
				//  ao valor restante da parcela:
				valorPagamento = contratoParcelamentoItem.getValorItem().subtract(valorPagoItem);
			}
			
		// 1.1.2. Caso contrário, ou seja, o pagamento não é complementar:
		} else if (indicadorPagamentoComplementar.compareTo(ConstantesSistema.NAO) == 0) {
			// 1.1.2.1.	Caso o valor do item seja superior ao valor restante da parcela  
			if (contratoParcelamentoItem.getValorItem().compareTo(
					prestacaoContratoParcelamento.getValor().subtract(valorCalculadoPagamento)) > 0){
				valorPagamento = prestacaoContratoParcelamento.getValor().subtract(valorCalculadoPagamento);
			} else {
				// 1.1.2.2.	Caso contrário, ou seja, o valor do item não seja superior ao valor restante da parcela
				valorPagamento = contratoParcelamentoItem.getValorItem();
			}
		}
		
		// 1.2.1. Caso o item de débito seja do tipo conta 
		if (documentoTipo.compareTo(DocumentoTipo.CONTA) == 0) {
			
			//[SB0008 - Gerar Pagamento para Conta]
			retorno = this.gerarPagamentoConta(helper, fachada, valorPagamento, 
					contratoParcelamentoItem, avisoBancario, usuarioLogado, colecaoPagamento);
			
			valorPagamento = (BigDecimal) retorno[0];
			colecaoPagamento = (Collection<Pagamento>) retorno[1];
			
		// 1.2.2.	Caso o item de débito seja do tipo guia de pagamento 
		} else if (documentoTipo.compareTo(DocumentoTipo.GUIA_PAGAMENTO) == 0) {
			
			//[SB0009 - Gerar Pagamento para Guia de Pagamento]. 
			retorno = this.gerarPagamentoGuiaPagamento(helper, fachada, valorPagamento, 
					debitoTipo, contratoParcelamentoItem, avisoBancario, usuarioLogado, colecaoPagamento);

			valorPagamento = (BigDecimal) retorno[0];
			colecaoPagamento = (Collection<Pagamento>) retorno[1];
			
		// 1.2.3. Caso o item de débito seja do tipo débito a cobrar
		} else if (documentoTipo.compareTo(DocumentoTipo.DEBITO_A_COBRAR) == 0) {
			// [SB0010 - Gerar Pagamento para Débito a Cobrar]. 
			retorno = this.gerarPagamentoDebitoACobrar(helper, fachada, valorPagamento, 
					debitoTipo, contratoParcelamentoItem, avisoBancario, usuarioLogado, colecaoPagamento);

			valorPagamento = (BigDecimal) retorno[0];
			colecaoPagamento = (Collection<Pagamento>) retorno[1];
		}
		
		//1.4.	O sistema acumula o valor calculado do pagamento 
		valorCalculadoPagamento = valorCalculadoPagamento.add(valorPagamento);
		
		//1.5.	O sistema vincula o item de débito pago à parcela paga
		PrestacaoItemContratoParcelamento prestacaoItemContratoParcelamento = new PrestacaoItemContratoParcelamento();
		
		prestacaoItemContratoParcelamento.setPrestacao(prestacaoContratoParcelamento);
		prestacaoItemContratoParcelamento.setItem(contratoParcelamentoItem);
		prestacaoItemContratoParcelamento.setValorPago(valorPagamento);
		prestacaoItemContratoParcelamento.setUltimaAlteracao(new Date());
		
		fachada.inserir(prestacaoItemContratoParcelamento);
		
		retorno[0] = valorCalculadoPagamento;
		retorno[1] = colecaoPagamento;
		
		return retorno;
	}
	
	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * [SB0008] - Gerar Pagamento para Conta
	 * 
	 * @author Mariana Victor
	 * @throws ControladorException 
	 * @throws ErroRepositorioException 
	 * @since 06/06/2011
	 * */
	private Object[] gerarPagamentoConta(InformarPagamentoContratoParcelamentoHelper helper, Fachada fachada, BigDecimal valorPagamento, 
			ContratoParcelamentoItem contratoParcelamentoItem, AvisoBancario avisoBancario, Usuario usuarioLogado,
			Collection<Pagamento> colecaoPagamento) 
		throws ControladorException, ErroRepositorioException{

		Object[] retorno = new Object[2];
		
		Date dataPagamento = Util.converteStringParaDate(helper.getDataPagamento());
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		Integer anoMesSistemaParametro = sistemaParametro.getAnoMesArrecadacao();
		Integer anoMesDataPagamento = Util.formataAnoMes(dataPagamento);
		Integer anoMes = null;
		if (anoMesDataPagamento > anoMesSistemaParametro) {
			anoMes = anoMesDataPagamento;
		} else {
			anoMes = anoMesSistemaParametro;
		}
		
		Conta conta = contratoParcelamentoItem.getContaGeral().getConta();
		
		Pagamento pagamento = new Pagamento();

		pagamento.setAnoMesReferenciaPagamento(conta.getReferencia());
		pagamento.setAnoMesReferenciaArrecadacao(anoMes);
		pagamento.setValorPagamento(valorPagamento);
		pagamento.setDataPagamento(dataPagamento);
		pagamento.setPagamentoSituacaoAtual(null);
		pagamento.setPagamentoSituacaoAnterior(null);
		pagamento.setDebitoTipo(null);
		pagamento.setContaGeral(contratoParcelamentoItem.getContaGeral());
		pagamento.setGuiaPagamento(null);
		pagamento.setDebitoACobrarGeral(null);
		pagamento.setLocalidade(conta.getLocalidade());
		pagamento.setDocumentoTipo(contratoParcelamentoItem.getDocumentoTipo());
		pagamento.setAvisoBancario(avisoBancario);
		pagamento.setImovel(conta.getImovel());
		pagamento.setArrecadadorMovimentoItem(null);
		if (avisoBancario != null){
			pagamento.setArrecadacaoForma(avisoBancario.getArrecadacaoForma());
		} else {
			pagamento.setArrecadacaoForma(helper.getArrecadacaoForma());
		}
		pagamento.setUltimaAlteracao(new Date());
		pagamento.setCliente(null);

		if (avisoBancario != null){
			
//			------------ REGISTRAR TRANSAÇÃO ----------------
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_INFORMAR_PAGAMENTO_CONTRATO_PARCELAMENTO_POR_CLIENTE,pagamento.getId(),
					pagamento.getId(),
					new UsuarioAcaoUsuarioHelper(usuarioLogado,
							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
			
			registradorOperacao.registrarOperacao(pagamento);
//			------------ REGISTRAR TRANSAÇÃO ----------------
			
			//1. O sistema inclui o pagamento na tabela arrecadacao.PAGAMENTO 
			Integer idPagamento = (Integer) fachada.inserir(pagamento);
			
			pagamento.setId(idPagamento);
		}
		
		// 2. O sistema verifica se existe item de documento de cobrança associado a esta conta 
		// 3. Caso existam itens de documento de cobrança associados a esta conta, o sistema atualiza a situação
		//  do item do documento de cobrança referente à Conta na tabela cobranca.COBRANCA_DOCUMENTO_ITEM
        getControladorCobranca().atualizarSituacaoCobrancaDocumentoItemAPartirPagamento(pagamento,
              CobrancaDebitoSituacao.PAGO, dataPagamento);
        
        // 4. O sistema verifica se existem itens de negativação associados a esta conta 
		// <<Inclui>> [UC0937 - Obter Itens de Negativação Associados à Conta] 
		Collection colecaoNegativadorMovimentoRegItem = this.getControladorSpcSerasa()
				.obterItensNegativacaoAssociadosAConta(pagamento.getImovel().getId(), conta.getReferencia());

		// 5. Caso existam itens de negativação associados a esta conta
		if(colecaoNegativadorMovimentoRegItem != null && !colecaoNegativadorMovimentoRegItem.isEmpty()){
			// 5.1. Para cada item de negativação retornado, o sistema atualiza o item de negativação com os dados do pagamento
			Iterator iterNmri = colecaoNegativadorMovimentoRegItem.iterator();
			
			while (iterNmri.hasNext()) {
				Integer idItemNegativacao = (Integer) iterNmri.next();
				
				//[SB0011 - Atualizar Item da Negativação]
				this.atualizarItemNegativacao(fachada, idItemNegativacao,pagamento);
			}
		}
		
		colecaoPagamento.add(pagamento);
		
		retorno[0] = valorPagamento;
		retorno[1] = colecaoPagamento;
		
		
		return retorno;
	}
	
	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * [SB0009] - Gerar Pagamento para Guia de Pagamento
	 * 
	 * @author Mariana Victor
	 * @throws ControladorException 
	 * @throws ErroRepositorioException 
	 * @since 06/06/2011
	 * */
	private Object[] gerarPagamentoGuiaPagamento(InformarPagamentoContratoParcelamentoHelper helper, Fachada fachada,
			BigDecimal valorPagamento, Integer idDebitoTipo, ContratoParcelamentoItem contratoParcelamentoItem,
			AvisoBancario avisoBancario, Usuario usuarioLogado, Collection<Pagamento> colecaoPagamento) throws ControladorException, ErroRepositorioException{

		Object[] retorno = new Object[2];
		
		Date dataPagamento = Util.converteStringParaDate(helper.getDataPagamento());
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		Integer anoMesSistemaParametro = sistemaParametro.getAnoMesArrecadacao();
		Integer anoMesDataLancamento = Util.formataAnoMes(dataPagamento);
		Integer anoMes = null;
		if (anoMesDataLancamento > anoMesSistemaParametro) {
			anoMes = anoMesDataLancamento;
		} else {
			anoMes = anoMesSistemaParametro;
		}
		
		DebitoTipo debitoTipo = new DebitoTipo();
		debitoTipo.setId(idDebitoTipo);
		
		GuiaPagamento guiaPagamento = new GuiaPagamento(contratoParcelamentoItem.getGuiaPagamentoGeral().getGuiaPagamento().getId());
		GuiaPagamentoGeral guiaGeral = new GuiaPagamentoGeral(contratoParcelamentoItem.getGuiaPagamentoGeral().getGuiaPagamento().getId());
		
		guiaGeral.setGuiaPagamento(guiaPagamento);

		Pagamento pagamento = new Pagamento();
		
		pagamento.setAnoMesReferenciaArrecadacao(anoMes);
		pagamento.setValorPagamento(valorPagamento);
		pagamento.setDataPagamento(dataPagamento);
		pagamento.setDebitoTipo(debitoTipo);
		pagamento.setGuiaPagamento(guiaGeral);
		pagamento.setLocalidade(contratoParcelamentoItem.getGuiaPagamentoGeral().getGuiaPagamento().getLocalidade());
		pagamento.setDocumentoTipo(contratoParcelamentoItem.getDocumentoTipo());
		pagamento.setAvisoBancario(avisoBancario);
		pagamento.setImovel(contratoParcelamentoItem.getGuiaPagamentoGeral().getGuiaPagamento().getImovel());
		if (avisoBancario != null){
			pagamento.setArrecadacaoForma(avisoBancario.getArrecadacaoForma());
		} else {
			pagamento.setArrecadacaoForma(helper.getArrecadacaoForma());
		}
		pagamento.setUltimaAlteracao(new Date());
		pagamento.setCliente(contratoParcelamentoItem.getGuiaPagamentoGeral().getGuiaPagamento().getCliente());

		if (avisoBancario != null){
//			------------ REGISTRAR TRANSAÇÃO ----------------
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_INFORMAR_PAGAMENTO_CONTRATO_PARCELAMENTO_POR_CLIENTE,pagamento.getId(),
					pagamento.getId(),
					new UsuarioAcaoUsuarioHelper(usuarioLogado,
							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
			
			registradorOperacao.registrarOperacao(pagamento);
//			------------ REGISTRAR TRANSAÇÃO ----------------
			
			//1. O sistema inclui o pagamento na tabela arrecadacao.PAGAMENTO 
			Integer idPagamento = (Integer) fachada.inserir(pagamento);
			
			pagamento.setId(idPagamento);
		}
		
		//2. O sistema verifica se existe item de documento de cobrança associado a esta guia de pagamento
		//3. Caso existam itens de documento de cobrança associados a esta guia de pagamento, o sistema atualiza a situação 
		//   do item do documento de cobrança referente à Guia de Pagamento na tabela cobranca.COBRANCA_DOCUMENTO_ITEM
        getControladorCobranca().atualizarSituacaoCobrancaDocumentoItemAPartirPagamento(pagamento,
              CobrancaDebitoSituacao.PAGO, dataPagamento); 
		
		//4. O sistema verifica se existem itens de negativação associados a esta guia de pagamento 
		// <<Inclui>> [UC1009 - Obter Itens de Negativação Associados à Guia]
		Collection colecaoNegativadorMovimentoRegItem = this.getControladorSpcSerasa().
				obterNegativadorMovimentoRegItemAssociadosAGuiaPagamento(
						contratoParcelamentoItem.getGuiaPagamentoGeral().getGuiaPagamento().getId());

		//5.	Caso existam itens de negativação associados a esta guia de pagamento
		if(colecaoNegativadorMovimentoRegItem != null && !colecaoNegativadorMovimentoRegItem.isEmpty()){
			//5.1.	Para cada item de negativação retornado, o sistema atualiza o item de negativação com os dados do pagamento
			Iterator iterNmri = colecaoNegativadorMovimentoRegItem.iterator();
			
			while (iterNmri.hasNext()) {
				Integer idItemNegativacao = (Integer) iterNmri.next();
				
				//[SB0011 - Atualizar Item da Negativação].
				this.atualizarItemNegativacao(fachada, idItemNegativacao,pagamento);
			}
		}
		
		colecaoPagamento.add(pagamento);
		
		retorno[0] = valorPagamento;
		retorno[1] = colecaoPagamento;
		
		return retorno;
	}
	
	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * [SB0011] - Atualizar Item da Negativação
	 * 
	 * @author Mariana Victor
	 * @throws ErroRepositorioException 
	 * @since 06/06/2011
	 * */
	private void atualizarItemNegativacao(Fachada fachada, Integer idItemNegativacao, Pagamento pagamento) 
		throws ErroRepositorioException{
		
		Integer codigoExclusao = this.repositorioContratoParcelamento.pesquisarCodigoExclusaoNegativacao(idItemNegativacao);
		
		FiltroNegativadorMovimentoRegItem filtroNegativadorMovimentoRegItem = new FiltroNegativadorMovimentoRegItem();
		filtroNegativadorMovimentoRegItem.adicionarParametro(new ParametroSimples(
				FiltroNegativadorMovimentoRegItem.ID, idItemNegativacao));
		Collection colecaoNegativadorMovimentoRegItem = fachada.pesquisar(
				filtroNegativadorMovimentoRegItem, NegativadorMovimentoRegItem.class.getName());
		
		NegativadorMovimentoRegItem negativadorMovimentoRegItem = (NegativadorMovimentoRegItem) Util.retonarObjetoDeColecao(colecaoNegativadorMovimentoRegItem);
		
		negativadorMovimentoRegItem.setValorPago(pagamento.getValorPagamento());
		
		CobrancaDebitoSituacao cobrancaDebitoSituacao = new CobrancaDebitoSituacao();
		cobrancaDebitoSituacao.setId(CobrancaDebitoSituacao.PAGO);
		
		if (codigoExclusao == null) {
			//1. Caso a negativação não esteja excluída 
			
			negativadorMovimentoRegItem.setCobrancaDebitoSituacao(cobrancaDebitoSituacao);
			negativadorMovimentoRegItem.setDataSituacaoDebito(pagamento.getDataPagamento());
			
		} else {
			//2. Caso contrário, atualiza o item da negativação 

			negativadorMovimentoRegItem.setCobrancaDebitoSituacaoAposExclusao(cobrancaDebitoSituacao);
			negativadorMovimentoRegItem.setDataSituacaoDebitoAposExclusao(pagamento.getDataPagamento());
			
		}
		
		negativadorMovimentoRegItem.setUltimaAlteracao(new Date());
		
		fachada.atualizar(negativadorMovimentoRegItem);
		
	}
	
	/**
	 * [UC1141] Emitir Contrato de Parcelamento por Cliente
	 * 
	 * Retorna os registros com os dados a serem impressos no relatório 
	 * 
	 * @author Mariana Victor
	 * @throws ControladorException 
	 * @since 10/06/2011
	 * */
	public List<RelatorioEmitirContratoParcelamentoPorClienteBean> pesquisarDadosRelatorioContratoParcelamentoPorCliente(
			FiltrarRelatorioEmitirContratoParcelamentoPorClienteHelper helper) throws ControladorException {
		try {
			List<RelatorioEmitirContratoParcelamentoPorClienteBean> colecaoBean = new ArrayList();
			
			RelatorioEmitirContratoParcelamentoPorClienteBean bean = new RelatorioEmitirContratoParcelamentoPorClienteBean();
			
			// [SB0001] - Emitir Dados Gerais do Contrato
			// 1.2.1. Número do Contrato
			if (helper.getContratoParcelamento() != null
					&& helper.getContratoParcelamento().getNumero() != null) {
				bean.setNumeroContrato(helper.getContratoParcelamento().getNumero());
			}
	
			// 1.2.2.1. No. Contrato Anterior
			if (helper.getContratoParcelamento().getContratoAnterior() != null) {
				bean.setNumeroContratoAnt(helper.getContratoParcelamento().getContratoAnterior().getNumero());
			}
	
			// 1.2.2.2. Tipo Relação
			if (helper.getContratoParcelamento().getContratoAnterior() != null && 
					helper.getContratoParcelamento().getRelacaoAnterior() != null
					&& helper.getContratoParcelamento().getRelacaoAnterior().getDescricao() != null) {
				bean.setTipoRelacao(helper.getContratoParcelamento().getRelacaoAnterior().getDescricao());
			}
			
			// 1.3.1. Data do Contrato 
			if (helper.getContratoParcelamento().getDataContrato() != null) {
				bean.setDataContrato(helper.getContratoParcelamento().getDataContrato());
	
				// 1.3.2. Momento da Implantação do Contrato 
				bean.setDataImplContrato(helper.getContratoParcelamento().getDataImplantacao());
			}
	
			// 1.4.1. Usuário Responsável
			if (helper.getUsuarioResponsavel() != null) {
				bean.setUsResponsavel(helper.getUsuarioResponsavel());
			}
	
			if (helper.getCliente() != null) {
				
				FiltroContratoParcelamentoCliente filtroContratoParcelamentoCliente = new FiltroContratoParcelamentoCliente();
				filtroContratoParcelamentoCliente.adicionarParametro(new ParametroSimples(
						FiltroContratoParcelamentoCliente.ID_CONTRATO, helper.getContratoParcelamento().getId()));
				filtroContratoParcelamentoCliente.adicionarCaminhoParaCarregamentoEntidade(
						FiltroContratoParcelamentoCliente.CLIENTE);
				filtroContratoParcelamentoCliente.adicionarCaminhoParaCarregamentoEntidade(
						FiltroContratoParcelamentoCliente.CLIENTE_SUPERIOR);
				
				Collection colecaoContratoParcelamentoCliente = Fachada.getInstancia()
					.pesquisar(filtroContratoParcelamentoCliente, ContratoParcelamentoCliente.class.getName());
				
				if (colecaoContratoParcelamentoCliente != null
						&& !colecaoContratoParcelamentoCliente.isEmpty()) {
					
					ContratoParcelamentoCliente contratoParcelamentoCliente = this.retornaClienteSuperior(colecaoContratoParcelamentoCliente);
					
					// 1.5.1. Caso o contrato tenha sido implantado para um cliente superior,
					//  emitir o cliente superior e os clientes a ele vinculados:
					if (contratoParcelamentoCliente != null) {
						// 1.5.1.1.	Cliente Superior:
						bean.setTipoCliente("Cliente Superior");
						
						// 1.5.1.1.1. código 
						bean.setCodigoCliente(contratoParcelamentoCliente.getCliente().getId().toString());
						
						// 1.5.1.1.2. nome
						bean.setNomeCliente(contratoParcelamentoCliente.getCliente().getNome());
						
						// 1.5.1.1.3. CNPJ
						bean.setCnpjCliente(contratoParcelamentoCliente.getCliente().getCnpjFormatado());
						
						// 1.5.1.2. Relação dos Clientes Vinculados ao Cliente
						FiltroContratoParcelamentoCliente filtroClientesVinculados = new FiltroContratoParcelamentoCliente();
						filtroClientesVinculados.adicionarParametro(new ParametroSimples(
								FiltroContratoParcelamentoCliente.ID_CONTRATO, helper.getContratoParcelamento().getId()));
						filtroClientesVinculados.adicionarParametro(new ParametroSimples(
								FiltroContratoParcelamentoCliente.INDICADOR_CLIENTE_SUPERIOR, ConstantesSistema.NAO));
						filtroClientesVinculados.adicionarCaminhoParaCarregamentoEntidade(
								FiltroContratoParcelamentoCliente.CLIENTE);
						
						Collection colecaoClientesVinculados = Fachada.getInstancia()
							.pesquisar(filtroClientesVinculados, ContratoParcelamentoCliente.class.getName());
						
						if (colecaoClientesVinculados != null
								&& !colecaoClientesVinculados.isEmpty()) {
							
							Iterator iterator = colecaoClientesVinculados.iterator();
							List<RelatorioEmitirContratoParcelamentoPorClienteSubCliBean> colecaoSubClientesVinculados = new ArrayList();
							
							//1.5.1.2.1. Para cada cliente vinculado ao cliente superior 
							while(iterator.hasNext()) {
								RelatorioEmitirContratoParcelamentoPorClienteSubCliBean subBean = new RelatorioEmitirContratoParcelamentoPorClienteSubCliBean();
								
								ContratoParcelamentoCliente clientesVinculados = (ContratoParcelamentoCliente) 
									iterator.next();
								
								// 1.5.1.2.1.1.	código
								subBean.setIdClienteVinculado(clientesVinculados.getCliente().getId().toString());
								
								// 1.5.1.2.1.2. nome
								subBean.setNomeClienteVinculado(clientesVinculados.getCliente().getNome());
								
								// 1.5.1.2.1.3.	CNPJ 
								if (clientesVinculados.getCliente().getCnpj() != null) {
									subBean.setCnpjClienteVinculado(clientesVinculados.getCliente().getCnpjFormatado());
								} else if (clientesVinculados.getCliente().getCpf() != null) {
									// ou CPF 
									subBean.setCnpjClienteVinculado(clientesVinculados.getCliente().getCpfFormatado());
								}
								
								colecaoSubClientesVinculados.add(subBean);
							}
							
							bean.setArrayJRDadosClientesVinculados(colecaoSubClientesVinculados);
						}
						
					} else {
						// 1.5.2. Caso contrário, ou seja, o contrato não foi implantado para um cliente superior,
						//  emitir o cliente do contrato
						
						contratoParcelamentoCliente = (ContratoParcelamentoCliente) 
							Util.retonarObjetoDeColecao(colecaoContratoParcelamentoCliente);
 						
						// 1.5.2.1.	Cliente:
						bean.setTipoCliente("Cliente");
						
						// 1.5.2.1.1. código 
						bean.setCodigoCliente(contratoParcelamentoCliente.getCliente().getId().toString());
						
						// 1.5.2.1.2. nome
						bean.setNomeCliente(contratoParcelamentoCliente.getCliente().getNome());
						
						// 1.5.2.1.3. CNPJ
						bean.setCnpjCliente(contratoParcelamentoCliente.getCliente().getCnpjFormatado());
						
						// 1.5.2.1.4. tipo da relação
						if (helper.getContratoParcelamento().getRelacaoCliente() != null) {
							bean.setTipoRelacao(helper.getContratoParcelamento().getRelacaoCliente().getDescricao());
						}
					}
				}
			}
	
			// 1.6.1. Débito selecionado para negociação
			if (helper.getContratoParcelamento().getIndicadorResponsavel() != null) {
				// caso o débito selecionado tenha sido o indicado na conta 
				if (helper.getContratoParcelamento().getIndicadorResponsavel().compareTo(new Short("1")) == 0) {
					bean.setDebitoSelecionado("Indicado na Conta");	
				}
	
				// caso o débito selecionado tenha sido o atual do imóvel 
				if (helper.getContratoParcelamento().getIndicadorResponsavel().compareTo(new Short("2")) == 0) {
					bean.setDebitoSelecionado("Atual no Imóvel");	
				}
	
				// caso o débito selecionado tenha sido o indicado na conta e o atual do imóvel 
				if (helper.getContratoParcelamento().getIndicadorResponsavel().compareTo(new Short("3")) == 0) {
					bean.setDebitoSelecionado("Indicado na Conta e Atual do Imóvel");	
				}
			}
	
			// 1.7.1. Período de Referência do Débito 
			if (helper.getContratoParcelamento().getAnoMesDebitoInicioFormatado() != null
					&& helper.getContratoParcelamento().getAnoMesDebitoFinalFormatado() != null){
				
				bean.setPeriodoReferenciaDebito(
						helper.getContratoParcelamento().getAnoMesDebitoInicioFormatado() + " a " 
						+ helper.getContratoParcelamento().getAnoMesDebitoFinalFormatado());
				
			}
	
			// 1.8.1. Período de Vencimento do Débito 
			if (helper.getContratoParcelamento().getDataVencimentoInicioFormatada() != null
					&& helper.getContratoParcelamento().getDataVencimentoFinalFormatada() != null){
				
				bean.setPeriodoVencimentoDebito(
						helper.getContratoParcelamento().getDataVencimentoInicioFormatada() + " a " 
						+ helper.getContratoParcelamento().getDataVencimentoFinalFormatada());
				
			}
	
			// 1.9.1. Observação
			if (helper.getContratoParcelamento().getObservacao() != null) {
				bean.setObservacaoDebito(helper.getContratoParcelamento().getObservacao());
			} else {
				bean.setObservacaoDebito("-");
			}
	
			// 1.10.1. Situação de Cancelamento
			// caso o contrato esteja cancelado 
			if (helper.getContratoParcelamento().getMotivoDesfazer() != null) {
				bean.setSituacaoCancelamento("Cancelado");
			} else {
				// caso o contrato não esteja cancelado 
				bean.setSituacaoCancelamento("Não Cancelado");
			}
	
			// 1.11.1. Situação de Pagamento
			// caso o contrato esteja pendente 
			if (helper.getContratoParcelamento().getValorParcelamentoACobrar() != null 
					&& helper.getContratoParcelamento().getValorParcelamentoACobrar().compareTo(BigDecimal.ZERO) > 0) {
				bean.setSituacaoPagamento("Pendente");
			} else {
				// caso o contrato esteja pago 
				bean.setSituacaoPagamento("Pago");
			}
			
			// [SB0002] - Emitir Débitos do Cliente
			if ((helper.getColecaoContaItens() != null 
					&& !helper.getColecaoContaItens().isEmpty())) {
				
				Iterator iteratorConta = helper.getColecaoContaItens().iterator();
				ArrayList<RelatorioEmitirContratoParcelamentoPorClienteSubContaBean> colecaoSubContaBean = new ArrayList();

				// 1.2.1. Contas
				while(iteratorConta.hasNext()) {
					
					ContratoParcelamentoItem contratoParcelamentoItem = (ContratoParcelamentoItem) iteratorConta.next();
					
					RelatorioEmitirContratoParcelamentoPorClienteSubContaBean subBean = new RelatorioEmitirContratoParcelamentoPorClienteSubContaBean();
					
					ContaGeral contaGeral = contratoParcelamentoItem.getContaGeral();
					Conta conta = contaGeral.getConta();
					
					// 1.2.1.1. Matrícula 
					subBean.setMatricula(conta.getImovel().getId().toString());
					
					// 1.2.1.2.	Mês/Ano de Referência
					subBean.setAnoMesConta(conta.getFormatarAnoMesParaMesAno());
					
					// 1.2.1.3.	Vencimento 
					subBean.setVencimentoConta(Util.formatarData(conta.getDataVencimentoConta()));
					
					// 1.2.1.4.	Valor Água 
					subBean.setValorAgua(conta.getValorAgua());
					
					// 1.2.1.5.	Valor Esgoto 
					subBean.setValorEsgoto(conta.getValorEsgoto());
					
					// 1.2.1.6.	Valor Débitos 
					subBean.setValorDebitos(conta.getDebitos());
					
					// 1.2.1.7.	Valor Créditos 
					subBean.setValorCreditos(conta.getValorCreditos());
					
					// 1.2.1.8.	Valor Impostos 
					subBean.setValorImpostos(conta.getValorImposto());
					
					// 1.2.1.9.	Valor Conta 
					BigDecimal valorConta = BigDecimal.ZERO;
					if (conta.getValorAgua() != null) {
						valorConta = valorConta.add(conta.getValorAgua());
					}
					if (conta.getValorEsgoto() != null) {
						valorConta = valorConta.add(conta.getValorEsgoto());
					}
					if (conta.getDebitos() != null) {
						valorConta = valorConta.add(conta.getDebitos());
					}
					if (conta.getValorCreditos() != null) {
						valorConta = valorConta.subtract(conta.getValorCreditos());
					}
					if (conta.getValorImposto() != null) {
						valorConta = valorConta.subtract(conta.getValorImposto());
					}
					subBean.setValorConta(valorConta);
					
					// 1.2.1.10. Acrésc. Impont. 
					if (contratoParcelamentoItem.getValarAcrescImpont() != null
							&& contratoParcelamentoItem.getValarAcrescImpont().compareTo(BigDecimal.ZERO) != 0) {
						subBean.setAcrescImpont(contratoParcelamentoItem.getValarAcrescImpont());
					} else {
						subBean.setAcrescImpont(contratoParcelamentoItem.getValorTotalContaValores());
					}
					
					// 1.2.1.11. Situação 
					subBean.setSituacaoConta(conta.getDebitoCreditoSituacaoAtual().getDescricaoAbreviada());
					
					// 1.2.1.12. Desvinculada do Contrato? 
					if (contratoParcelamentoItem.getIndicadorItemCancelado() != null
							&& contratoParcelamentoItem.getIndicadorItemCancelado().compareTo(ConstantesSistema.SIM) == 0) {
						subBean.setDesvinculadaContrato("SIM");
					} else {
						subBean.setDesvinculadaContrato("NÃO");
					}
					
					colecaoSubContaBean.add(subBean);
						
				}
				bean.setArrayJRDadosContas(colecaoSubContaBean);
				
			}
				
			if(helper.getColecaoItensDebitoACobrar() != null && !helper.getColecaoItensDebitoACobrar().isEmpty()){
				Iterator iteratorDebitoACobrar = helper.getColecaoItensDebitoACobrar().iterator();
				ArrayList<RelatorioEmitirContratoParcelamentoPorClienteSubDebitoACobrarBean> colecaoSubDebitoACobrarBean = new ArrayList();
				BigDecimal totalDebitoACobrar = new BigDecimal("0");
				
				//1.3.1 Debito a Cobrar
				while(iteratorDebitoACobrar.hasNext()){
					RelatorioEmitirContratoParcelamentoPorClienteSubDebitoACobrarBean subBean = new RelatorioEmitirContratoParcelamentoPorClienteSubDebitoACobrarBean();
					
					ContratoParcelamentoItem contratoParcelamentoItem = (ContratoParcelamentoItem) iteratorDebitoACobrar.next();
					
					DebitoACobrarGeral debitoGeral = contratoParcelamentoItem.getDebitoACobrarGeral();
					
					//1.3.1.1
					subBean.setMatricula(debitoGeral.getDebitoACobrar().getImovel().getMatriculaFormatada());
					
					//1.3.1.2
					subBean.setTipoDebito(debitoGeral.getDebitoACobrar().getDebitoTipo().getDescricao());
					
					//1.3.1.3
					subBean.setMesAnoReferencia(debitoGeral.getDebitoACobrar().getFormatarAnoMesReferenciaDebito());
					
					//1.3.1.4
					subBean.setMesAnoCobranca(debitoGeral.getDebitoACobrar().getFormatarAnoMesCobrancaDebito());
					
					//1.3.1.5
					String parcelasACobrar = (debitoGeral.getDebitoACobrar().getNumeroPrestacaoDebitoMenosBonus()-debitoGeral.getDebitoACobrar().getNumeroPrestacaoCobradas()) + "";
					subBean.setParcelasACobrar(parcelasACobrar);
					
					//1.3.1.6
					subBean.setValorACobrar(Util.formatarBigDecimalParaStringComVirgula(debitoGeral.getDebitoACobrar().getValorDebito()));
					if(debitoGeral.getDebitoACobrar().getValorDebito() != null){
						totalDebitoACobrar = totalDebitoACobrar.add(debitoGeral.getDebitoACobrar().getValorDebito());
					}
					
					colecaoSubDebitoACobrarBean.add(subBean);
				}
				bean.setArrayJRDadosDebitosACobrar(colecaoSubDebitoACobrarBean);
				bean.setTotalDocsDebitoACobrar(colecaoSubDebitoACobrarBean.size() + " doc(s)");
				bean.setTotalValorACobrar(Util.formatarBigDecimalParaStringComVirgula(totalDebitoACobrar));
			}else{
				ArrayList<RelatorioEmitirContratoParcelamentoPorClienteSubDebitoACobrarBean> colecaoSubDebitoACobrarBean = new ArrayList();
				colecaoSubDebitoACobrarBean.add(new RelatorioEmitirContratoParcelamentoPorClienteSubDebitoACobrarBean());
				bean.setArrayJRDadosDebitosACobrar(colecaoSubDebitoACobrarBean);
				bean.setTotalDocsDebitoACobrar("0 doc(s)");
				bean.setTotalValorACobrar("0,00");
			}
				
			// 1.4.1. Total do Débito
			BigDecimal totalDebito = BigDecimal.ZERO;
			if (helper.getContratoParcelamento().getValorDebitoAtualizado() != null) {
				totalDebito = totalDebito.add(
						helper.getContratoParcelamento().getValorDebitoAtualizado());
			}
			if (helper.getContratoParcelamento().getValorTotalAcrescImpontualidade() != null) {
				totalDebito = totalDebito.subtract(
						helper.getContratoParcelamento().getValorTotalAcrescImpontualidade());
			}
			bean.setTotalDebito(totalDebito);
			
			// 1.4.2. Total do Débito Atualizado
			BigDecimal totalDebitoAtualizado = BigDecimal.ZERO; 
			if (helper.getContratoParcelamento().getValorDebitoAtualizado() != null) {
				totalDebitoAtualizado = helper.getContratoParcelamento().getValorDebitoAtualizado();
			}
			bean.setTotalDebitoAtualizado(totalDebitoAtualizado);
				
			
			
			// [SB0003] - Emitir Dados do Parcelamento
			// 1.2.	Linha 2:
			// 1.2.1. Resolução de Diretoria 
			if (helper.getContratoParcelamento().getResolucaoDiretoria() != null) {
				bean.setResolucaoDiretoria(helper.getContratoParcelamento().getResolucaoDiretoria().getNumero());
			} else {
				bean.setResolucaoDiretoria("-");
			}
			
			// 1.3.1. Débito com Acréscimos? 
			if (helper.getContratoParcelamento().getIndicadorDebitosAcrescimos() != null
					&& helper.getContratoParcelamento().getIndicadorDebitosAcrescimos().compareTo(ConstantesSistema.SIM) == 0) {
				bean.setDebitoAcrescimos("SIM");
			} else {
				bean.setDebitoAcrescimos("NÃO");
			}
			
			// 1.4.1. Parcelamento com Juros? 
			if (helper.getContratoParcelamento().getIndicadorParcelamentoJuros() != null
					&& helper.getContratoParcelamento().getIndicadorParcelamentoJuros().compareTo(ConstantesSistema.SIM) == 0) {
				bean.setParcelamentoJuros("SIM");
			} else {
				bean.setParcelamentoJuros("NÃO");
			}
			
			// 1.5.1. Permite Informar o Valor da Parcela? 
			if (helper.getContratoParcelamento().getIndicadorPermiteInformarValorParcel() != null
					&& helper.getContratoParcelamento().getIndicadorPermiteInformarValorParcel().compareTo(ConstantesSistema.SIM) == 0) {
				bean.setInformarValorParcela("SIM");
			} else {
				bean.setInformarValorParcela("NÃO");
			}
			
			// 1.6.1. Forma de Pagamento 
			if (helper.getContratoParcelamento().getCobrancaForma() != null) {
				bean.setFormaPagamento(helper.getContratoParcelamento().getCobrancaForma().getDescricao());
			} 
			
			// 1.7.1. Valor do Débito 
			if (helper.getContratoParcelamento().getValorDebitoAtualizado() != null) {
				BigDecimal valorDebito = helper.getContratoParcelamento().getValorDebitoAtualizado();
				if (helper.getContratoParcelamento().getValorTotalAcrescImpontualidade() != null) {
					valorDebito.subtract(helper.getContratoParcelamento().getValorTotalAcrescImpontualidade());
				}
				
				bean.setValorDebito(valorDebito);
			} else {
				bean.setValorDebito(BigDecimal.ZERO);
			}
	
			// 1.8.1. Valor dos Acréscimos por Impontualidade 
			if (helper.getContratoParcelamento().getValorTotalAcrescImpontualidade() != null) {
				bean.setValorAcrescImpontualidade(helper.getContratoParcelamento().getValorTotalAcrescImpontualidade());
			} else {
				bean.setValorAcrescImpontualidade(BigDecimal.ZERO);
			}
	
			// 1.9.1. Taxa de Juros (%) 
			if (helper.getContratoParcelamento().getTaxaJuros() != null) {
				bean.setTaxaJuros(helper.getContratoParcelamento().getTaxaJuros());
			} else {
				bean.setTaxaJuros(BigDecimal.ZERO);
			}
	
			// 1.10.1. Valor dos Juros de Parcelamento 
			if (helper.getContratoParcelamento().getValorJurosParcelamento() != null) {
				bean.setValorJurosParc(helper.getContratoParcelamento().getValorJurosParcelamento());
			} else {
				bean.setValorJurosParc(BigDecimal.ZERO);
			}
	
			// 1.11.1. Valor Parcelado 
			if (helper.getContratoParcelamento().getValorTotalParcelado() != null) {
				bean.setValorParcelado(helper.getContratoParcelamento().getValorTotalParcelado());
			} else {
				bean.setValorParcelado(BigDecimal.ZERO);
			}
	
			// 1.12.1. Valor Parcelado a Cobrar 
			if (helper.getContratoParcelamento().getValorParcelamentoACobrar() != null) {
				bean.setValorParceladoACobrar(helper.getContratoParcelamento().getValorParcelamentoACobrar());
			} else {
				bean.setValorParceladoACobrar(BigDecimal.ZERO);
			}
	
			// 1.13.1. Parcelas 
			Collection<PrestacaoContratoParcelamento> colecaoParcelas = helper.getColecaoParcelas();
			if (colecaoParcelas != null
					&& !colecaoParcelas.isEmpty()) {
				Iterator iterator = colecaoParcelas.iterator();
				ArrayList<RelatorioEmitirContratoParcelamentoPorClienteSubParcBean> colecaoSubBean = new ArrayList();
				
				while(iterator.hasNext()) {
					RelatorioEmitirContratoParcelamentoPorClienteSubParcBean subBean = new RelatorioEmitirContratoParcelamentoPorClienteSubParcBean();
					
					PrestacaoContratoParcelamento prestacaoContratoParcelamento = (PrestacaoContratoParcelamento) iterator.next();
	
					subBean.setParcelaPrestacoes(prestacaoContratoParcelamento.getNumero().toString()
							+ "/" + helper.getContratoParcelamento().getNumeroPrestacoes());
					
					subBean.setDataVencimento(prestacaoContratoParcelamento.getDataVencimentoFormatada());
					
					subBean.setValorParcela(Util.formatarMoedaReal(prestacaoContratoParcelamento.getValor()));
					
					if (this.repositorioContratoParcelamento.pesquisarParcelaPaga(prestacaoContratoParcelamento.getId())) {
						subBean.setSituacao("Paga");
					} else {
						subBean.setSituacao("-");
					}
					
					colecaoSubBean.add(subBean);
					
				}
				
				bean.setArrayJRDadosParcelas(colecaoSubBean);
				
			}
			
			colecaoBean.add(bean);
			
			return colecaoBean;
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	public ContratoParcelamentoCliente retornaClienteSuperior(Collection<ContratoParcelamentoCliente> colecaoContratoParcelamentoCliente) {
		Iterator iterator = colecaoContratoParcelamentoCliente.iterator();
		
		while(iterator.hasNext()) {
			ContratoParcelamentoCliente contratoParcelamentoCliente = (ContratoParcelamentoCliente) iterator.next();
			if (contratoParcelamentoCliente.getIndicadorClienteSuperior() != null
					&& contratoParcelamentoCliente.getIndicadorClienteSuperior().compareTo(ConstantesSistema.SIM) == 0) {
				return contratoParcelamentoCliente;
			}
		}
		return null;
	}
	
	/**
     * [UC1139] Atualizar Contrato de Parcelamento por Cliente
     * 
     * Permite a atualização de contrato de parcelamento por cliente.
     * 
     * @author Paulo Diniz, Mariana Victor
     * @date 16/03/2011, 27/07/2011
     * 
     * @param contrato
     * @throws ControladorException
     */
    public void atualizaContratoParcelamentoPorCliente(ContratoParcelamento contrato, Usuario usuarioLogado, 
            ContratoParcelamentoCliente clienteContrato, ContratoParcelamentoCliente clienteSuperiorContrato, List<DebitosClienteHelper> listaDebitos, List<DebitosClienteHelper> listaDebitosAnterior, List<PrestacaoContratoParcelamento> listaDeParcelas)
            throws ControladorException {
        
        try{
            contrato.setUltimaAlteracao(new Date());
            this.getControladorUtil().atualizar(contrato);
            
            repositorioContratoParcelamento.removerClientesVinculadosAContratoParcelamento(contrato.getId());
            
            if (clienteContrato != null) {
                clienteContrato.setContrato(contrato);
                clienteContrato.setIndicadorClienteSuperior(new Short("2"));
                clienteContrato.setUltimaAlteracao( new Date());
                clienteContrato.setClienteSuperior(null);
                Integer idClienteContratoInserido = (Integer) this.getControladorUtil().inserir(clienteContrato);
                clienteContrato.setId(idClienteContratoInserido);
            } else if (clienteSuperiorContrato != null) {
                clienteSuperiorContrato.setContrato(contrato);
                clienteSuperiorContrato.setIndicadorClienteSuperior(new Short("1"));
                clienteSuperiorContrato.setUltimaAlteracao( new Date());
                clienteSuperiorContrato.setClienteSuperior(null);
                
                Integer idClienteContratoSuperiorInserido = (Integer) this.getControladorUtil().inserir(clienteSuperiorContrato);
                clienteSuperiorContrato.setId(idClienteContratoSuperiorInserido);
                
                Collection<Integer> clientesVinculados = this.getControladorCliente().pesquisarClientesAssociadosResponsavel(clienteSuperiorContrato.getCliente().getId().intValue());
                
                for (Integer idCliente : clientesVinculados) {
                    ContratoParcelamentoCliente contratoParcelCliente = new ContratoParcelamentoCliente();
                    contratoParcelCliente.setContrato(contrato);
                    Cliente cliente = new Cliente();
                    cliente.setId(idCliente);
                    contratoParcelCliente.setCliente(cliente);
                    contratoParcelCliente.setIndicadorClienteSuperior(new Short("2"));
                    contratoParcelCliente.setUltimaAlteracao( new Date());
                    contratoParcelCliente.setClienteSuperior(clienteSuperiorContrato);
                    this.getControladorUtil().inserir(contratoParcelCliente);
                }
            }
            
            
            //1.3.1 - Remover os Itens de Debio atuais
            for (DebitosClienteHelper debitosClienteHelper : listaDebitosAnterior) {
            	if (debitosClienteHelper.getTipoDocumento().compareTo(DocumentoTipo.CONTA) == 0) {
	            	ContaGeral contaGeral = new ContaGeral();
	                contaGeral.setId(debitosClienteHelper.getIdentificadorItem());
	            	Collection colecaoContas = (Collection) this.getControladorFaturamento().obterConta(contaGeral.getId());
	                Conta conta = (Conta)colecaoContas.iterator().next();
	                if(conta.getDataRevisao() != null){
	                    getControladorFaturamento().retirarRevisaoConta(colecaoContas, conta.getId().intValue()+"", usuarioLogado, false, Funcionalidade.MANTER_CONTRATO_PARCELAMENTO_POR_CLIENTE );
	                }
            	} else if (debitosClienteHelper.getTipoDocumento().compareTo(DocumentoTipo.DEBITO_A_COBRAR) == 0) {
            		DebitoACobrarGeral debitoACobrarGeral = new DebitoACobrarGeral();
	            	debitoACobrarGeral.setId(debitosClienteHelper.getIdentificadorItem());
	                
	            	DebitoACobrar debitoACobrar = this.getControladorFaturamento().obterDebitoACobrar(debitoACobrarGeral.getId());
	            	Collection colecaoDebitoACobrar = new ArrayList();
	            	colecaoDebitoACobrar.add(debitoACobrar);
	                if(debitoACobrar.getDataRevisao() != null){
	                	getControladorFaturamento().retirarRevisaoDebitoACobrar(colecaoDebitoACobrar, usuarioLogado);
	                }
            	}
            }
            repositorioContratoParcelamento.removerItensDebitosVinculadosAContratoParcelamento(contrato.getId());
            
            for (DebitosClienteHelper debitosClienteHelper : listaDebitos) {
            	
            	ContratoParcelamentoItem parcelItem = new ContratoParcelamentoItem();
            	
            	parcelItem.setContrato(contrato);
            	parcelItem.setValorItem(debitosClienteHelper.getValorItem());
            	parcelItem.setValarAcrescImpont(debitosClienteHelper.getValorAcrescImpont());
            	parcelItem.setUltimaAlteracao(new Date());
            	parcelItem.setIndicadorItemCancelado(new Short("2"));
            	
            	if (debitosClienteHelper.getTipoDocumento().compareTo(DocumentoTipo.CONTA) == 0) {
	                DocumentoTipo docTipo = new DocumentoTipo();
	                docTipo.setId(DocumentoTipo.CONTA);
	                
	                ContaGeral contaGeral = new ContaGeral();
	                contaGeral.setId(debitosClienteHelper.getIdentificadorItem());

	                parcelItem.setDocumentoTipo(docTipo);
	                parcelItem.setContaGeral(contaGeral);
	                
	                Collection colecaoContas = (Collection) this.getControladorFaturamento().obterConta(contaGeral.getId());
	                Conta conta = (Conta)colecaoContas.iterator().next();
	                if(conta.getDataRevisao() != null){
	                    getControladorFaturamento().retirarRevisaoConta(colecaoContas, conta.getId().intValue()+"", usuarioLogado, false, Funcionalidade.MANTER_CONTRATO_PARCELAMENTO_POR_CLIENTE );
	                }
	                
	                ContaMotivoRevisao motivoRevisao = new ContaMotivoRevisao();
	                motivoRevisao.setId(ContaMotivoRevisao.CONTA_EM_CONTRATO_PARCELAMENTO);
	                getControladorFaturamento().colocarRevisaoConta(colecaoContas, conta.getId()+"", motivoRevisao, usuarioLogado);
	                
            	} else if (debitosClienteHelper.getTipoDocumento().compareTo(DocumentoTipo.DEBITO_A_COBRAR) == 0) {
            		DocumentoTipo docTipo = new DocumentoTipo();
	                docTipo.setId(DocumentoTipo.DEBITO_A_COBRAR);
	                
	                DebitoACobrarGeral debitoACobrarGeral = new DebitoACobrarGeral();
	                debitoACobrarGeral.setId(debitosClienteHelper.getIdentificadorItem());

	                parcelItem.setDocumentoTipo(docTipo);
	                parcelItem.setDebitoACobrarGeral(debitoACobrarGeral);
	                
	                DebitoACobrar debitoACobrar = this.getControladorFaturamento().obterDebitoACobrar(debitoACobrarGeral.getId());
	            	Collection colecaoDebitoACobrar = new ArrayList();
	            	colecaoDebitoACobrar.add(debitoACobrar);
	                if(debitoACobrar.getDataRevisao() != null){
	                	getControladorFaturamento().retirarRevisaoDebitoACobrar(colecaoDebitoACobrar, usuarioLogado);
	                }
	                
	                ContaMotivoRevisao motivoRevisao = new ContaMotivoRevisao();
	                motivoRevisao.setId(ContaMotivoRevisao.DEBITO_A_COBRAR_EM_CONTRATO_PARCELAMENTO);
	                getControladorFaturamento().colocarRevisaoDebitoACobrar(colecaoDebitoACobrar, motivoRevisao, usuarioLogado);
            	}	
                
            	this.getControladorUtil().inserir(parcelItem);
            }
            
            int indicadorGuiaAcrecImpontualidade = 2;
            if(contrato.getIndicadorDebitosAcrescimos().intValue() == 1
                    && contrato.getValorTotalAcrescImpontualidade() != null && contrato.getValorTotalAcrescImpontualidade().floatValue() > 0.0){
                indicadorGuiaAcrecImpontualidade = 1;
            }
            
            int indicadorGuiaJurosSobreContratoParcel = 2;
            if(contrato.getIndicadorParcelamentoJuros().intValue() == 1
                    && contrato.getValorJurosParcelamento() != null && contrato.getValorJurosParcelamento().floatValue() > 0.0){
                indicadorGuiaJurosSobreContratoParcel = 1;
            }
            
            FiltroCobrancaDocumentoItem filtroCobrancaDocumentoItem = new FiltroCobrancaDocumentoItem();
            filtroCobrancaDocumentoItem.adicionarParametro(new ParametroSimples(
            		FiltroCobrancaDocumentoItem.PRESTACAO_CONTRATO_PARCELAMENTO_ID, contrato.getId()));
            
            Collection colecaoItens = this.getControladorUtil().pesquisar(filtroCobrancaDocumentoItem, 
            		CobrancaDocumentoItem.class.getName());
            
            Integer idDocumentoCobranca = null;
            
            if (colecaoItens != null && !colecaoItens.isEmpty()) {
            	Iterator iterator = colecaoItens.iterator();
            	
            	while(iterator.hasNext()) {
	            	CobrancaDocumentoItem item = (CobrancaDocumentoItem) iterator.next();
	            	idDocumentoCobranca = item.getCobrancaDocumento().getId();
	            	
	            	repositorioContratoParcelamento.removerItemDocumentoCobrancaVinculadoAContratoParcelamento(item.getId());
            	}
            }

            if (idDocumentoCobranca != null) {
            	repositorioContratoParcelamento.removerDocumentoCobrancaVinculadoAContratoParcelamento(idDocumentoCobranca);
            }
            
            //1.6.1 Remover Parcelas
            repositorioContratoParcelamento.removerPrestacoesVinculadasAContratoParcelamento(contrato.getId());
            
            Date dataVencPacel = contrato.getDataVencimentoPrimParcela();

            List<PrestacaoContratoParcelamento> colecaoPrestacaoContratoParcelamento = new ArrayList<PrestacaoContratoParcelamento>();
            PrestacaoContratoParcelamento prestacaoContratoParcelamento = listaDeParcelas.get(0);
            prestacaoContratoParcelamento.setDataVencimento(dataVencPacel);
            prestacaoContratoParcelamento.setContratoParcelamento(contrato);
            prestacaoContratoParcelamento.setUltimaAlteracao(new Date());
            Integer idPrestacao = (Integer) this.getControladorUtil().inserir(prestacaoContratoParcelamento);
            prestacaoContratoParcelamento.setId(idPrestacao);
            colecaoPrestacaoContratoParcelamento.add(prestacaoContratoParcelamento);
            
            for (int i = 1; i < listaDeParcelas.size(); i++) {
                prestacaoContratoParcelamento = listaDeParcelas.get(i);
                Date dataParcel = Util.adicionarNumeroDiasDeUmaData(listaDeParcelas.get(i-1).getDataVencimento(), 30);
                prestacaoContratoParcelamento.setDataVencimento(dataParcel);
                prestacaoContratoParcelamento.setContratoParcelamento(contrato);
                prestacaoContratoParcelamento.setUltimaAlteracao(new Date());
                
                idPrestacao = (Integer) this.getControladorUtil().inserir(prestacaoContratoParcelamento);
                prestacaoContratoParcelamento.setId(idPrestacao);
                colecaoPrestacaoContratoParcelamento.add(prestacaoContratoParcelamento);
            }
            
            FiltroContratoParcelamentoItem filtroGuiaJuros = new FiltroContratoParcelamentoItem();
            filtroGuiaJuros.adicionarCaminhoParaCarregamentoEntidade(FiltroContratoParcelamentoItem.GUIA_PAGAMENTO_GERAL_GUIA_PAGAMENTO);
            filtroGuiaJuros.adicionarCaminhoParaCarregamentoEntidade(FiltroContratoParcelamentoItem.GUIA_PAGAMENTO_GERAL_GUIA_PAGAMENTO_CLIENTE);
            filtroGuiaJuros.adicionarCaminhoParaCarregamentoEntidade(FiltroContratoParcelamentoItem.GUIA_PAGAMENTO_GERAL_GUIA_PAGAMENTO_IMOVEL);
            filtroGuiaJuros.adicionarCaminhoParaCarregamentoEntidade(FiltroContratoParcelamentoItem.GUIA_PAGAMENTO_GERAL_GUIA_PAGAMENTO_IMOVEL_LOCALIDADE);
            filtroGuiaJuros.adicionarParametro(new ParametroSimples(FiltroContratoParcelamentoItem.CONTRATO_ID, contrato.getId().intValue()));
            filtroGuiaJuros.adicionarParametro(new ParametroSimples(FiltroContratoParcelamentoItem.DOCUMENTO_TIPO_ID, DocumentoTipo.GUIA_PAGAMENTO));
            filtroGuiaJuros.adicionarParametro(new ParametroSimples(FiltroContratoParcelamentoItem.GUIA_PAGAMENTO_GERAL_GUIA_PAGAMENTO_DEBITO_TIPO_COD_CONST, DebitoTipo.JUROS_SOBRE_CONTR_PARCELAMENTO));
           
            Collection<ContratoParcelamentoItem> contratoItensManter = (Collection<ContratoParcelamentoItem>) getControladorUtil().pesquisar(filtroGuiaJuros, ContratoParcelamentoItem.class.getName());
        
            if(contratoItensManter != null && !contratoItensManter.isEmpty()){
            	GuiaPagamento guiaPagamentoManter = new GuiaPagamento();
            	Collection<GuiaPagamento> guiasPagamentoManter = new ArrayList<GuiaPagamento>();
            	String[] registrosRemocao = new String[contratoItensManter.size()];
            	int posicao = 0;
            	for (ContratoParcelamentoItem contratoParcelamentoItem : contratoItensManter) {
            	
    				Integer anoMesContabil = (Integer) contratoParcelamentoItem.getGuiaPagamentoGeral().getGuiaPagamento().getAnoMesReferenciaContabil();
    				Cliente cliente = new Cliente();
    				Imovel imovel = new Imovel();
    				
    				if (contratoParcelamentoItem.getGuiaPagamentoGeral().getGuiaPagamento().getCliente() != null) {
    					Integer idCliente = (Integer) contratoParcelamentoItem.getGuiaPagamentoGeral().getGuiaPagamento().getCliente().getId();
    					cliente.setId(idCliente);
    				}
    				
    				if (contratoParcelamentoItem.getGuiaPagamentoGeral().getGuiaPagamento().getImovel() != null) {
    					Integer idImovel = (Integer) contratoParcelamentoItem.getGuiaPagamentoGeral().getGuiaPagamento().getImovel().getId();
    					imovel.setId(idImovel);
    				}
    				
    				GuiaPagamento guia = contratoParcelamentoItem.getGuiaPagamentoGeral().getGuiaPagamento();
    				guia.setAnoMesReferenciaContabil(anoMesContabil);
    				if (cliente != null) {
    					guia.setCliente(cliente);
    				}
    				if (imovel != null) {
    					guia.setImovel(imovel);
    				}
    				
    				guiasPagamentoManter.add(guia);
    				registrosRemocao[posicao] = guia.getId().toString();
    				
    				if (posicao == 0) {
    					guiaPagamentoManter.setCliente(cliente);
    					guiaPagamentoManter.setImovel(imovel);
    				}
    				
    				posicao++;
    			}

    			// 1.6.3.1.1	Cancela a guia de pagamento de Juros sobre Contrato
    			// 1.6.3.1.1.1 <<Inclui>> [UC0188 - Manter Guia de Pagamento] - [SB0001 - Cancelar Guia de Pagamento]
    			getControladorFaturamento().manterGuiaPagamento(guiaPagamentoManter, guiasPagamentoManter, registrosRemocao, null, usuarioLogado);
                
            }	
				
            if(indicadorGuiaJurosSobreContratoParcel == 1){
                
                BigDecimal valorPrimeiraGuia = new BigDecimal("0");
                BigDecimal valorDaGuia = contrato.getValorJurosParcelamento().divide(new BigDecimal(listaDeParcelas.size()),2 , BigDecimal.ROUND_UP);
                
                BigDecimal totalSomaGuias = valorDaGuia.multiply(new BigDecimal(listaDeParcelas.size()));
                BigDecimal diferenca = contrato.getValorJurosParcelamento().subtract(totalSomaGuias);
                valorPrimeiraGuia = valorDaGuia.add(diferenca);
                
                for (int i = 0; i < listaDeParcelas.size(); i++) {
                    prestacaoContratoParcelamento  = listaDeParcelas.get(i);
                    GuiaPagamento guia = new GuiaPagamento();
                    guia.setDataEmissao(new Date());
                    
                    //Primeira guia com diferenca ajustada
                    if(i == 0){
                        guia.setValorDebito(valorPrimeiraGuia);
                    }else{
                        guia.setValorDebito(valorDaGuia);
                    }


                    DebitoTipo debitoTipo = this.repositorioFaturamento
                    	.obterDebitoTipoCodigoConstante(DebitoTipo.JUROS_SOBRE_CONTR_PARCELAMENTO);
                    guia.setDebitoTipo(debitoTipo);
                    FinanciamentoTipo financiamentoTipo = new FinanciamentoTipo();
                    financiamentoTipo.setId(FinanciamentoTipo.JUROS_PARCELAMENTO);
                    guia.setFinanciamentoTipo(financiamentoTipo);
                    guia.setDataVencimento(prestacaoContratoParcelamento.getDataVencimento());
                    guia.setLocalidade(new Localidade());
                    guia.setImovel(new Imovel());
                    guia.setRegistroAtendimento(new RegistroAtendimento());
                    guia.setOrdemServico(new OrdemServico());
                    Cliente cliente = null;
                    if(clienteContrato != null){
                        cliente = clienteContrato.getCliente();
                    }else if(clienteSuperiorContrato != null){
                        cliente = clienteSuperiorContrato.getCliente();
                    }
                    guia.setCliente(cliente);
                    guia.setAnoMesReferenciaContabil(contrato.getAnoMesReferenciaFaturamento());
                    
                    LancamentoItemContabil lancamentoItemContabil = new LancamentoItemContabil(LancamentoItemContabil.JUROS_SOBRE_CONTRATO_PARCELAMENTO);
                    guia.setLancamentoItemContabil(lancamentoItemContabil);

                    guia.setDebitoCreditoSituacaoAnterior(null);
                    
                    DebitoCreditoSituacao debitoCreditoSituacaoAtual = new DebitoCreditoSituacao(DebitoCreditoSituacao.NORMAL);
                    guia.setDebitoCreditoSituacaoAtual(debitoCreditoSituacaoAtual);
                    
                    guia.setUsuario(contrato.getUsuarioResponsavel());
                    guia.setObservacao("GUIA GERADA NA IMPLANTAÇÃO DO CONTRATO DE PARCELAMENTO POR CLIENTE " + contrato.getNumero()+ " da tabela cobranca.CONTRATO_PARCEL");
                    guia.setIndicadorEmitirObservacao(new Short("2"));
                    
                    guia.setNumeroPrestacaoDebito(Short.parseShort("1"));
                    guia.setNumeroPrestacaoTotal(Short.parseShort("1"));
                    
                    Collection colecaoGuiaPagamentoItem = new ArrayList();
                    GuiaPagamentoItem guiaPagamentoItem = new GuiaPagamentoItem();
                    guiaPagamentoItem.setDebitoTipo(debitoTipo);
                    guiaPagamentoItem.setValorDebito(guia.getValorDebito());
                    guiaPagamentoItem.setUltimaAlteracao(new Date());
                    colecaoGuiaPagamentoItem.add(guiaPagamentoItem);
                    
                    //LOCA_ID da tabela LOCALIDADE com LOCA_ICSEDE = 1
                    Localidade localidadeSede = getControladorCobranca().pesquisarLocalidadeSede();
                    String ids[] = getControladorFaturamento().inserirGuiaPagamento(guia, contrato.getUsuarioResponsavel(), contrato.getNumeroDiasEntreVencimentoParcel(), colecaoGuiaPagamentoItem, localidadeSede, true);
                    
                    //[SB0017] Vincula Guia
                    GuiaPagamentoGeral guiaGeral = new GuiaPagamentoGeral();
                    guiaGeral.setId(Integer.parseInt(ids[0]));
                    ContratoParcelamentoItem contratoParcelItem = new ContratoParcelamentoItem();
                    contratoParcelItem.setIndicadorItemCancelado(new Short("2"));
                    contratoParcelItem.setContrato(contrato);
                    DocumentoTipo docTipo = new DocumentoTipo();
                    docTipo.setId(DocumentoTipo.GUIA_PAGAMENTO);
                    contratoParcelItem.setDocumentoTipo(docTipo);
                    contratoParcelItem.setDebitoACobrarGeral(null);
                    contratoParcelItem.setContaGeral(null);
                    contratoParcelItem.setGuiaPagamentoGeral(guiaGeral);
                    contratoParcelItem.setCreditoARealizarGeral(null);
                    if(i == 0){
                        contratoParcelItem.setValorItem(valorPrimeiraGuia);
                    }else{
                        contratoParcelItem.setValorItem(valorDaGuia);
                    }
                    contratoParcelItem.setValarAcrescImpont(null);
                    contratoParcelItem.setUltimaAlteracao(new Date());
                    
                    getControladorUtil().inserir(contratoParcelItem);
                }
            }
            
            FiltroContratoParcelamentoItem filtroGuiaAcrescImpont = new FiltroContratoParcelamentoItem();
            filtroGuiaAcrescImpont.adicionarCaminhoParaCarregamentoEntidade(FiltroContratoParcelamentoItem.GUIA_PAGAMENTO_GERAL_GUIA_PAGAMENTO);
            filtroGuiaAcrescImpont.adicionarCaminhoParaCarregamentoEntidade(FiltroContratoParcelamentoItem.GUIA_PAGAMENTO_GERAL_GUIA_PAGAMENTO_CLIENTE);
            filtroGuiaAcrescImpont.adicionarCaminhoParaCarregamentoEntidade(FiltroContratoParcelamentoItem.GUIA_PAGAMENTO_GERAL_GUIA_PAGAMENTO_IMOVEL);
            filtroGuiaAcrescImpont.adicionarCaminhoParaCarregamentoEntidade(FiltroContratoParcelamentoItem.GUIA_PAGAMENTO_GERAL_GUIA_PAGAMENTO_IMOVEL_LOCALIDADE);
            filtroGuiaAcrescImpont.adicionarParametro(new ParametroSimples(FiltroContratoParcelamentoItem.CONTRATO_ID, contrato.getId().intValue()));
            filtroGuiaAcrescImpont.adicionarParametro(new ParametroSimples(FiltroContratoParcelamentoItem.DOCUMENTO_TIPO_ID, DocumentoTipo.GUIA_PAGAMENTO));
            filtroGuiaAcrescImpont.adicionarParametro(new ParametroSimples(FiltroContratoParcelamentoItem.GUIA_PAGAMENTO_GERAL_GUIA_PAGAMENTO_DEBITO_TIPO_ID, DebitoTipo.ACRESCIMOS_POR_IMPONTUALIDADE));
           
            contratoItensManter = (Collection<ContratoParcelamentoItem>) getControladorUtil().pesquisar(filtroGuiaAcrescImpont, ContratoParcelamentoItem.class.getName());
        
            if(contratoItensManter != null && !contratoItensManter.isEmpty()){
            	GuiaPagamento guiaPagamentoManter = new GuiaPagamento();
            	Collection<GuiaPagamento> guiasPagamentoManter = new ArrayList<GuiaPagamento>();
            	String[] registrosRemocao = new String[contratoItensManter.size()];
            	int posicao = 0;
            	for (ContratoParcelamentoItem contratoParcelamentoItem : contratoItensManter) {
            	
    				Integer anoMesContabil = (Integer) contratoParcelamentoItem.getGuiaPagamentoGeral().getGuiaPagamento().getAnoMesReferenciaContabil();
    				Cliente cliente = new Cliente();
    				Imovel imovel = new Imovel();
    				
    				if (contratoParcelamentoItem.getGuiaPagamentoGeral().getGuiaPagamento().getCliente() != null) {
    					Integer idCliente = (Integer) contratoParcelamentoItem.getGuiaPagamentoGeral().getGuiaPagamento().getCliente().getId();
    					cliente.setId(idCliente);
    				}
    				
    				if (contratoParcelamentoItem.getGuiaPagamentoGeral().getGuiaPagamento().getImovel() != null) {
    					Integer idImovel = (Integer) contratoParcelamentoItem.getGuiaPagamentoGeral().getGuiaPagamento().getImovel().getId();
    					imovel.setId(idImovel);
    				}
    				
    				GuiaPagamento guia = contratoParcelamentoItem.getGuiaPagamentoGeral().getGuiaPagamento();
    				guia.setAnoMesReferenciaContabil(anoMesContabil);
    				if (cliente != null) {
    					guia.setCliente(cliente);
    				}
    				if (imovel != null) {
    					guia.setImovel(imovel);
    				}
    				
    				guiasPagamentoManter.add(guia);
    				registrosRemocao[posicao] = guia.getId().toString();
    				
    				if (posicao == 0) {
    					guiaPagamentoManter.setCliente(cliente);
    					guiaPagamentoManter.setImovel(imovel);
    				}
    				
    				posicao++;
    			}

    			// 1.6.2.1.1	Cancela a guia de pagamento de Juros sobre Contrato
    			// 1.6.2.1.1.1 <<Inclui>> [UC0188 - Manter Guia de Pagamento] - [SB0001 - Cancelar Guia de Pagamento]
    			getControladorFaturamento().manterGuiaPagamento(guiaPagamentoManter, guiasPagamentoManter, registrosRemocao, null, usuarioLogado);
            }
            
            if(indicadorGuiaAcrecImpontualidade == 1){
                
                BigDecimal valorPrimeiraGuia = new BigDecimal("0");
                BigDecimal valorDaGuia = contrato.getValorTotalAcrescImpontualidade().divide(new BigDecimal(listaDeParcelas.size()),2 , BigDecimal.ROUND_UP);
                
                BigDecimal totalSomaGuias = valorDaGuia.multiply(new BigDecimal(listaDeParcelas.size()));
                BigDecimal diferenca = contrato.getValorTotalAcrescImpontualidade().subtract(totalSomaGuias);
                valorPrimeiraGuia = valorDaGuia.add(diferenca);
                
                for (int i = 0; i < listaDeParcelas.size(); i++) {
                    prestacaoContratoParcelamento  = listaDeParcelas.get(i);
                    GuiaPagamento guia = new GuiaPagamento();
                    guia.setDataEmissao(new Date());
                    
                    //Primeira guia com diferenca ajustada
                    if(i == 0){
                        guia.setValorDebito(valorPrimeiraGuia);
                    }else{
                        guia.setValorDebito(valorDaGuia);
                    }
                    
                    DebitoTipo debitoTipo = new DebitoTipo();
                    debitoTipo.setId(DebitoTipo.ACRESCIMOS_POR_IMPONTUALIDADE);
                    guia.setDebitoTipo(debitoTipo);
                    FinanciamentoTipo financiamentoTipo = new FinanciamentoTipo();
                    financiamentoTipo.setId(FinanciamentoTipo.ENTRADA_PARCELAMENTO);
                    guia.setFinanciamentoTipo(financiamentoTipo);
                    guia.setDataVencimento(prestacaoContratoParcelamento.getDataVencimento());
                    guia.setLocalidade(new Localidade());
                    guia.setImovel(new Imovel());
                    guia.setRegistroAtendimento(new RegistroAtendimento());
                    guia.setOrdemServico(new OrdemServico());
                    Cliente cliente = null;
                    if(clienteContrato != null){
                        cliente = clienteContrato.getCliente();
                    }else if(clienteSuperiorContrato != null){
                        cliente = clienteSuperiorContrato.getCliente();
                    }
                    guia.setCliente(cliente);
                    guia.setAnoMesReferenciaContabil(contrato.getAnoMesReferenciaFaturamento());
                    
                    LancamentoItemContabil lancamentoItemContabil = new LancamentoItemContabil(LancamentoItemContabil.ACRESCIMOS_POR_IMPONTUALIDADE);
                    guia.setLancamentoItemContabil(lancamentoItemContabil);

                    guia.setDebitoCreditoSituacaoAnterior(null);
                    
                    DebitoCreditoSituacao debitoCreditoSituacaoAtual = new DebitoCreditoSituacao(DebitoCreditoSituacao.NORMAL);
                    guia.setDebitoCreditoSituacaoAtual(debitoCreditoSituacaoAtual);
                    
                    guia.setUsuario(contrato.getUsuarioResponsavel());
                    guia.setObservacao("GUIA GERADA NA IMPLANTAÇÃO DO CONTRATO DE PARCELAMENTO POR CLIENTE " + contrato.getNumero()+ " da tabela cobranca.CONTRATO_PARCEL");
                    guia.setIndicadorEmitirObservacao(new Short("2"));
                    
                    guia.setNumeroPrestacaoDebito(Short.parseShort("1"));
                    guia.setNumeroPrestacaoTotal(Short.parseShort("1"));
                    
                    Collection colecaoGuiaPagamentoItem = new ArrayList();
                    GuiaPagamentoItem guiaPagamentoItem = new GuiaPagamentoItem();
                    guiaPagamentoItem.setDebitoTipo(debitoTipo);
                    guiaPagamentoItem.setValorDebito(guia.getValorDebito());
                    guiaPagamentoItem.setUltimaAlteracao(new Date());
                    colecaoGuiaPagamentoItem.add(guiaPagamentoItem);
                    
                    //LOCA_ID da tabela LOCALIDADE com LOCA_ICSEDE = 1
                    Localidade localidadeSede = getControladorCobranca().pesquisarLocalidadeSede();
                    String ids[] = getControladorFaturamento().inserirGuiaPagamento(guia, contrato.getUsuarioResponsavel(), contrato.getNumeroDiasEntreVencimentoParcel(), colecaoGuiaPagamentoItem, localidadeSede, true);
                    
                    //[SB0017] Vincula Guia
                    GuiaPagamentoGeral guiaGeral = new GuiaPagamentoGeral();
                    guiaGeral.setId(Integer.parseInt(ids[0]));
                    ContratoParcelamentoItem contratoParcelItem = new ContratoParcelamentoItem();
                    contratoParcelItem.setIndicadorItemCancelado(new Short("2"));
                    contratoParcelItem.setContrato(contrato);
                    DocumentoTipo docTipo = new DocumentoTipo();
                    docTipo.setId(DocumentoTipo.GUIA_PAGAMENTO);
                    contratoParcelItem.setDocumentoTipo(docTipo);
                    contratoParcelItem.setDebitoACobrarGeral(null);
                    contratoParcelItem.setContaGeral(null);
                    contratoParcelItem.setGuiaPagamentoGeral(guiaGeral);
                    contratoParcelItem.setCreditoARealizarGeral(null);
                    if(i == 0){
                        contratoParcelItem.setValorItem(valorPrimeiraGuia);
                    }else{
                        contratoParcelItem.setValorItem(valorDaGuia);
                    }
                    contratoParcelItem.setValarAcrescImpont(null);
                    contratoParcelItem.setUltimaAlteracao(new Date());
                    
                    getControladorUtil().inserir(contratoParcelItem);
                }
            }
        
//             ------------ REGISTRAR TRANSAÇÃO ----------------
            RegistradorOperacao registradorOperacao = new RegistradorOperacao(
                    Operacao.OPERACAO_INSERIR_CONTRATO_PARCELAMENTO_POR_CLIENTE,
                    contrato.getId(), contrato.getId(),
                    new UsuarioAcaoUsuarioHelper(usuarioLogado,
                    UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

                registradorOperacao.registrarOperacao(contrato);
//                 ------------ REGISTRAR TRANSAÇÃO ----------------
                
            // 1.7. Caso a forma de pagamento seja cobrança por extrato
            if (contrato.getCobrancaForma() != null
           		 && contrato.getCobrancaForma().getId() != null
           		 && contrato.getCobrancaForma().getId().compareTo(CobrancaForma.COBRANCA_POR_EXTRATO) == 0) {
           	 
				 // O sistema gera o extrato do contrato de parcelamento por cliente 
				 //   (<<Inclui>> [UC1200 - Gerar Extrato de Contrato Parcelamento Por Cliente] 
				 //   passando o cliente e as parcelas).
				 Cliente cliente = null;
				 if (clienteSuperiorContrato != null 
						 && clienteSuperiorContrato.getCliente() != null
						 && clienteSuperiorContrato.getCliente().getId() != null) {
					 cliente = clienteSuperiorContrato.getCliente();
				 } else if (clienteContrato != null 
						 && clienteContrato.getCliente() != null
						 && clienteContrato.getCliente().getId() != null) {
					 cliente = clienteContrato.getCliente();
				 } 
				 
				 this.gerarExtratoDeContratoParcelamentoPorCliente(cliente.getId(), colecaoPrestacaoContratoParcelamento);
            }
        }catch (Exception ex) {
            sessionContext.setRollbackOnly();
            throw new ControladorException("erro.sistema", ex);
        }
        
    }
    
    /**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Consulta o id da RD relacionada ao contrato de parcelamento
	 * 
	 * @author Mariana Victor
	 * @data 29/06/2011
	 */
	public Integer pesquisarRDContratoParcelamento(Integer idContratoParcelamento) throws ControladorException {
		try {
			return repositorioContratoParcelamento.pesquisarRDContratoParcelamento(idContratoParcelamento);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Consulta os dados das contas vinculadas ao contrato de parcelamento
	 * 
	 * @author Mariana Victor
	 * @data 02/07/2011
	 */
	public Collection<ContaValoresHelper> pesquisarDadosDasContasContratoParcelamento(Integer idContratoParcelamento) throws ControladorException {
		try {
			return repositorioContratoParcelamento.pesquisarDadosDasContasContratoParcelamento(idContratoParcelamento);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * [FS0008] - Verificar existência do contrato anterior
	 * 
	 * @author Mariana Victor
	 * @data 08/07/2011
	 */
	public Boolean verificarExistenciaContratoAnterior(String numeroContratoAnterior) throws ControladorException {
		try {
			return repositorioContratoParcelamento.verificarExistenciaContratoAnterior(numeroContratoAnterior);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * [FS0037] - Verificar situação do contrato anterior
	 * 
	 * Retorna a situação de parcelamento do contrato
	 * 
	 * @author Mariana Victor
	 * @data 08/07/2011
	 */
	public Boolean verificarSituacaoContratoAnterior(String numeroContratoAnterior) throws ControladorException {
		try {
			return repositorioContratoParcelamento.verificarSituacaoContratoAnterior(numeroContratoAnterior);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * 
	 * [SB0010] - Gerar Pagamento para Débito a Cobrar
	 * 
	 * @author Mariana Victor
	 * @throws ControladorException 
	 * @throws ErroRepositorioException 
	 * @since 22/07/2011
	 * */
	private Object[] gerarPagamentoDebitoACobrar(InformarPagamentoContratoParcelamentoHelper helper, Fachada fachada,
			BigDecimal valorPagamento, Integer idDebitoTipo, ContratoParcelamentoItem contratoParcelamentoItem,
			AvisoBancario avisoBancario, Usuario usuarioLogado, Collection<Pagamento> colecaoPagamento) 
		throws ControladorException, ErroRepositorioException{
		
		Object[] retorno = new Object[2];

		Date dataPagamento = Util.converteStringParaDate(helper.getDataPagamento());
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		Integer anoMesSistemaParametro = sistemaParametro.getAnoMesArrecadacao();
		Integer anoMesDataLancamento = Util.formataAnoMes(dataPagamento);
		Integer anoMes = null;
		if (anoMesDataLancamento > anoMesSistemaParametro) {
			anoMes = anoMesDataLancamento;
		} else {
			anoMes = anoMesSistemaParametro;
		}
		
		DebitoTipo debitoTipo = new DebitoTipo();
		debitoTipo.setId(idDebitoTipo);
		
		DebitoACobrarGeral debitoACobrarGeral = contratoParcelamentoItem.getDebitoACobrarGeral();
		debitoACobrarGeral.setId(contratoParcelamentoItem.getDebitoACobrarGeral().getDebitoACobrar().getId());
		
		Pagamento pagamento = new Pagamento();
		
		pagamento.setAnoMesReferenciaArrecadacao(anoMes);
		pagamento.setValorPagamento(valorPagamento);
		pagamento.setDataPagamento(dataPagamento);
		pagamento.setDebitoTipo(debitoTipo);
		pagamento.setDebitoACobrarGeral(contratoParcelamentoItem.getDebitoACobrarGeral());
		pagamento.setLocalidade(contratoParcelamentoItem.getDebitoACobrarGeral().getDebitoACobrar().getLocalidade());
		pagamento.setDocumentoTipo(contratoParcelamentoItem.getDocumentoTipo());
		pagamento.setAvisoBancario(avisoBancario);
		pagamento.setImovel(contratoParcelamentoItem.getDebitoACobrarGeral().getDebitoACobrar().getImovel());
		if (avisoBancario != null){
			pagamento.setArrecadacaoForma(avisoBancario.getArrecadacaoForma());
		} else {
			pagamento.setArrecadacaoForma(helper.getArrecadacaoForma());
		}
		pagamento.setUltimaAlteracao(new Date());
 		
		if (avisoBancario != null){
			
			// ------------ REGISTRAR TRANSAÇÃO ----------------
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_INFORMAR_PAGAMENTO_CONTRATO_PARCELAMENTO_POR_CLIENTE,pagamento.getId(),
					pagamento.getId(),
					new UsuarioAcaoUsuarioHelper(usuarioLogado,
							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
			
			registradorOperacao.registrarOperacao(pagamento);
			// ------------ REGISTRAR TRANSAÇÃO ----------------
			
			// 1. O sistema inclui o pagamento na tabela arrecadacao.PAGAMENTO com os seguintes valores:
			Integer idPagamento = (Integer) fachada.inserir(pagamento);
			
			pagamento.setId(idPagamento);
		}
		
		// 2. O sistema verifica se existe item de documento de cobrança associado a este débito a cobrar 
		// 3. Caso existam itens de documento de cobrança associados a este débito a cobrar, 
		//   o sistema atualiza a situação do item do documento de cobrança referente ao Débito a Cobrar 
		//   na tabela cobranca.COBRANCA_DOCUMENTO_ITEM
        getControladorCobranca().atualizarSituacaoCobrancaDocumentoItemAPartirPagamento(pagamento,
              CobrancaDebitoSituacao.PAGO, dataPagamento); 
		
        colecaoPagamento.add(pagamento);
        
        retorno[0] = valorPagamento;
        retorno[1] = colecaoPagamento;
        
		return retorno;
	}
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Consulta os dados dos débitos a cobrar vinculados ao contrato de parcelamento
	 * 
	 * @author Mariana Victor
	 * @data 26/07/2011
	 */
	public Collection<DebitoACobrar> pesquisarDadosDosDebitosACobrarContratoParcelamento(
			Integer idContratoParcelamento) throws ControladorException {
		try {
			return repositorioContratoParcelamento.pesquisarDadosDosDebitosACobrarContratoParcelamento(idContratoParcelamento);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC0184] Manter Débito A Cobrar
	 * 
	 * Verifica se o débito a cobrar está vinculado a um Contrato Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @date 28/07/2011
	 */
	public boolean verificaDebitoACobrarVinculadoContratoParcelamentoCliente(
			Integer idDebitoACobrar) throws ControladorException {
		try {
			return repositorioContratoParcelamento.
				verificaDebitoACobrarVinculadoContratoParcelamentoCliente(idDebitoACobrar);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	
	/**
	 * [UC1200] Gerar Extrato de Contrato Parcelamento por Cliente
	 * 
	 * Este caso de uso permite gerar o extrato do contrato de parcelamento do cliente.
	 * 
	 * @author Mariana Victor
	 * @date 28/07/2011
	 */
	public void gerarExtratoDeContratoParcelamentoPorCliente (
			Integer idCliente, 
			List<PrestacaoContratoParcelamento> colecaoPrestacaoContratoParcelamento) 
		throws ControladorException {
		
		// 2. O sistema Gera o extrato do contrato de 
		// [SB0001] - Gerar Documento de Cobrança
		Cliente cliente = new Cliente();
		cliente.setId(idCliente);
		
		DocumentoTipo documentoTipo = new DocumentoTipo();
		documentoTipo.setId(DocumentoTipo.EXTRATO_CONTRATO_PARCELAMENTO);
		
		BigDecimal valorTotalPrestacoes = BigDecimal.ZERO;
		
		Iterator iterator = colecaoPrestacaoContratoParcelamento.iterator();
		while (iterator.hasNext()) {
			PrestacaoContratoParcelamento prestacao = (PrestacaoContratoParcelamento) iterator.next();
			valorTotalPrestacoes = valorTotalPrestacoes.add(prestacao.getValor());
		}
		
		DocumentoEmissaoForma documentoEmissaoForma = new DocumentoEmissaoForma();
		documentoEmissaoForma.setId(DocumentoEmissaoForma.INDIVIDUAL);
		
		CobrancaDocumento cobrancaDocumento = new CobrancaDocumento();
		cobrancaDocumento.setCliente(cliente);
		cobrancaDocumento.setDocumentoTipo(documentoTipo);
		
		// Seta com qualquer valor para inserir, depois da inserção pegar o id
		// do documentoCobranca gerado para atualizar o mesmo registro
		cobrancaDocumento.setSequencialImpressao(new Integer("1"));
		
		cobrancaDocumento.setEmissao(new Date());
		cobrancaDocumento.setValorDocumento(valorTotalPrestacoes);
		cobrancaDocumento.setValorAcrescimos(BigDecimal.ZERO);
		cobrancaDocumento.setDocumentoEmissaoForma(documentoEmissaoForma);
		cobrancaDocumento.setUltimaAlteracao(new Date());
		
		// 1. O sistema gera o documento de cobrança 
		Integer idCobrancaDocumento = (Integer) this.getControladorUtil().inserir(cobrancaDocumento);
		
		// Atualiza o número do sequencial do documento com o código do
		// documento de cobrança gerado
		cobrancaDocumento.setId(idCobrancaDocumento);
		cobrancaDocumento.setNumeroSequenciaDocumento(idCobrancaDocumento);
		this.getControladorUtil().atualizar(cobrancaDocumento);
		
		// 2. Para cada prestação da lista recebida, o sistema gera os itens do documento de cobrança 
		iterator = colecaoPrestacaoContratoParcelamento.iterator();
		while (iterator.hasNext()) {
			PrestacaoContratoParcelamento prestacao = (PrestacaoContratoParcelamento) iterator.next();
			
			DocumentoTipo documentoTipoPrestacao = new DocumentoTipo();
			documentoTipoPrestacao.setId(DocumentoTipo.EXTRATO_CONTRATO_PARCELAMENTO);
			
			CobrancaDocumentoItem cobrancaDocumentoItem = new CobrancaDocumentoItem();
			cobrancaDocumentoItem.setCobrancaDocumento(cobrancaDocumento);
			cobrancaDocumentoItem.setDocumentoTipo(documentoTipoPrestacao);
			cobrancaDocumentoItem.setValorItemCobrado(prestacao.getValor());
			cobrancaDocumentoItem.setPrestacaoContratoParcelamento(prestacao);
			cobrancaDocumentoItem.setUltimaAlteracao(new Date());
			
			this.getControladorUtil().inserir(cobrancaDocumentoItem);
		}
		
		
	}

	/**
	 * [UC1201] Emitir Extrato de Contrato de Parcelamento por Cliente
	 * 
	 * 1. O sistema obtém os dados do contrato de parcelamento por cliente
	 * 
	 * @author Mariana Victor
	 * @data 30/07/2011
	 */
	public ContratoParcelamento obterDadosContratoParcelamento(String numeroContratoParcelamento) throws ControladorException {
		try {
			return repositorioContratoParcelamento.
				obterDadosContratoParcelamento(numeroContratoParcelamento);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC1201] Emitir Extrato de Contrato de Parcelamento por Cliente
	 * 
	 * 2.2.	Dados do Parcelamento 
	 * 
	 * @author Mariana Victor
	 * @data 30/07/2011
	 */
	public Collection<PrestacaoContratoParcelamento> obterDadosPrestacoesContratoParcelamento(Integer idContratoParcelamento)
		throws ControladorException {
		try {
			return repositorioContratoParcelamento.
				obterDadosPrestacoesContratoParcelamento(idContratoParcelamento);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 *  [UC1201] Emitir Extrato de Contrato de Parcelamento por Cliente
	 * 
	 * Retorna os registros com os dados a serem impressos no relatório 
	 * 
	 * @author Mariana Victor
	 * @throws ControladorException 
	 * @data 30/07/2011
	 * */
	public List<RelatorioEmitirExtratoContratoParcelamentoPorClienteBean> pesquisarDadosRelatorioEmitirExtratoContratoParcelamentoPorCliente(
			EmitirExtratoContratoParcelamentoPorClienteHelper helper,
			Collection<PrestacaoContratoParcelamentoHelper> colecaoHelper) throws ControladorException {
		
		List<RelatorioEmitirExtratoContratoParcelamentoPorClienteBean> colecaoBean = new ArrayList();
		
		try {
			
			SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
			
			// [SB0001] - Emitir Extrato de Débito do Cliente em Formato PDF
			
			String numeroContrato = helper.getNumeroContrato();
			String totalParcelas = "" + colecaoHelper.size();
			String nomeCliente = helper.getNomeCliente();
			String codigoCliente = helper.getCodigoCliente();
			String enderecoCliente = "";
			
			FiltroClienteEndereco filtroClienteEndereco = new FiltroClienteEndereco();
			filtroClienteEndereco.adicionarParametro(new ParametroSimples(
					FiltroClienteEndereco.CLIENTE_ID, codigoCliente));
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTipo");
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTitulo");
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTipo");
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTitulo");
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("enderecoTipo");

			Collection colecaoEnderecoCliente = this.getControladorUtil().pesquisar(
					filtroClienteEndereco, ClienteEndereco.class.getName());

			if (colecaoEnderecoCliente != null
					&& !colecaoEnderecoCliente.isEmpty()) {
				ClienteEndereco clienteEndereco = (ClienteEndereco) ((List) colecaoEnderecoCliente).get(0);
				enderecoCliente = clienteEndereco.getEnderecoFormatado();
			}
			
			Integer parcelaIncial = null;
			Integer parcelaFinal = null;
			boolean todasParcelas = false;
			
			if (helper.getParcelaEmissao() != null 
					&& !helper.getParcelaEmissao().toString().trim().equals("")
					&& !helper.getParcelaEmissao().toString().trim().equals("-1")) {
				
				if (helper.getParcelaEmissao().toString().trim().equalsIgnoreCase("todas")) {
					todasParcelas = true;
				} else {
					parcelaIncial = new Integer(helper.getParcelaEmissao());
					parcelaFinal = new Integer(helper.getParcelaEmissao());
				}
			} else {
				parcelaIncial = helper.getInicioParcelas();
				parcelaFinal = helper.getFimParcelas();
			}
			
			Iterator iterator = colecaoHelper.iterator();
			
			while (iterator.hasNext()) {
				PrestacaoContratoParcelamentoHelper prestacao = (PrestacaoContratoParcelamentoHelper) iterator.next();
				
				if (todasParcelas
						|| (new Integer(prestacao.getNumeroParcela()).compareTo(parcelaIncial) >= 0
								&& new Integer(prestacao.getNumeroParcela()).compareTo(parcelaFinal) <= 0)) {
					
					CobrancaDocumentoItem cobrancaDocumentoItem = this.repositorioContratoParcelamento.
						obterDocumentoCobrancaPrestacao(prestacao.getIdParcela());
					
					CobrancaDocumento cobrancaDocumento = cobrancaDocumentoItem.getCobrancaDocumento();
					
					// 1. Para cada prestação, o sistema emite um extrato de débito:
					RelatorioEmitirExtratoContratoParcelamentoPorClienteBean bean = new RelatorioEmitirExtratoContratoParcelamentoPorClienteBean();
					
					// 1.1.	Linha 1:
					// 1.1.1. Nome do cliente 
					bean.setNomeCliente1(nomeCliente);
					// 1.1.2. Código do cliente 
					bean.setCodigoClienteResponsavel1(codigoCliente);
					
					// 1.2.	Linha 2:
					// 1.2.1. Endereço
					bean.setEnderecoCliente1(enderecoCliente);
					// 1.2.2. Sequencial
					bean.setSeqDocCobranca1("" + cobrancaDocumento.getNumeroSequenciaDocumento());
					// 1.2.3. Nº Parcelamento 
					bean.setNumeroParcelamento1(numeroContrato);
					
					// 1.3. Linha 3:
					// 1.3.1.1.	Parcela
					bean.setNumeroParcela1(prestacao.getNumeroParcela());
					bean.setTotalParcelas(totalParcelas);
					// 1.3.1.2.	Vencimento
					bean.setVencimentoFatura1(prestacao.getDataVencimento());
					// 1.3.1.3.	Valor R$ 
					bean.setValorFatura1(prestacao.getValorParcela());
					
					// 1.4.	Linha 7:
					// 1.4.1. Data de Emissão 
					bean.setDataEmissao1(Util.formatarData(new Date()));
					// 1.4.2. Valor total débitos 
					bean.setValorFatura1(prestacao.getValorParcela());

					String valorParcela = prestacao.getValorParcela().replace(".", "");
					valorParcela = valorParcela.replace(",", ".");
					BigDecimal valorParcelaBigDec = new BigDecimal(valorParcela);
					
					// 1.5.	Caso a empresa tenha estabelecido um valor padrão para emissão do extrato no formato de ficha de compensação
					// 1.5.1. Caso o valor a pagar seja maior ou igual ao valor padrão para emissão do extrato no formato de ficha de compensação
					//  emitir no formato ficha de compensação
					if (sistemaParametro.getValorExtratoFichaComp() != null
							&& sistemaParametro.getValorExtratoFichaComp().compareTo(BigDecimal.ZERO) > 0
							&& valorParcelaBigDec.compareTo(sistemaParametro.getValorExtratoFichaComp()) > 0) {

						// [SB0002] - Formatar Linhas para Emissão de Ficha de Compensação - Cliente
						bean.setSubRelatorio1("relatorioEmitirExtratoContratoParcelamentoClienteFichaCompensacao.jasper");
						
						// [SB0004 - Obter Representação numérica do Nosso Número da Ficha de Compensação]
						StringBuilder nossoNumero = Fachada.getInstancia().obterNossoNumeroFichaCompensacao(
								DocumentoTipo.EXTRATO_CONTRATO_PARCELAMENTO.toString(),
								prestacao.getIdParcela().toString()) ;
						
						String nossoNumeroSemDV = nossoNumero.toString().substring(0,nossoNumero.length() - 2);

						String fatorVencimento = CodigoBarras.obterFatorVencimento(Util.converteStringParaDate(prestacao.getDataVencimento()));
						
						String especificacaoCodigoBarra = CodigoBarras.
							obterEspecificacaoCodigoBarraFichaCompensacao(
						    ConstantesSistema.CODIGO_BANCO_FICHA_COMPENSACAO, 
						    ConstantesSistema.CODIGO_MOEDA_FICHA_COMPENSACAO, 
						    valorParcelaBigDec, nossoNumeroSemDV.toString(),
							ConstantesSistema.CARTEIRA_FICHA_COMPENSACAO, fatorVencimento);
					                                
						String representacaoNumericaCodigoBarraFichaCompensacao = CodigoBarras.obterRepresentacaoNumericaCodigoBarraFichaCompensacao(especificacaoCodigoBarra);
						
						// 1.1.2. Representação numérica do código de barras
						// 1.16.1. Código de Barras 
						bean.setRepresentacaoNumericaCodBarraFormatada1(representacaoNumericaCodigoBarraFichaCompensacao);
						bean.setRepresentacaoNumericaCodBarraSemDigito1(especificacaoCodigoBarra);
						
						//1.7. Linha 11,5:
						//1.7.1. Nosso Número
						bean.setNossoNumero(nossoNumeroSemDV);
						
						// 1.14. Linha 17:
						// 1.14.1. Sacado ? parte 1 
						bean.setSacadoParte01(nomeCliente);
						// 1.14.3. Sacado ? parte 3 
						bean.setSacadoParte03(codigoCliente);
						
					} else {
						// 1.5.2. Caso contrário, emitir no formato padrão
						// 1.6.	Caso contrário, emitir no formato padrão
						bean.setSubRelatorio1("relatorioEmitirExtratoContratoParcelamentoClienteEmissaoPadrao.jasper");

						// representação numérica do código de barras
						String representacaoNumericaCodBarra = "";

						// Obtém a representação numérica do
						// códigode
						// barra
						representacaoNumericaCodBarra = this
								.getControladorArrecadacao()
								.obterRepresentacaoNumericaCodigoBarra(
										8, // 1.3.1.1. Tipo de pagamento 
										valorParcelaBigDec, // 1.3.1.2. Valor do código de barras 
										0, // 1.3.1.3. Código da localidade 
										null, // 1.3.1.4. Matrícula do imóvel 
										null, // 1.3.1.5. Mês e Ano de Referência das Contas 
										null, // 1.3.1.6. Dígito verificador da referência da conta no módulo 10 
										null, // 1.3.1.7. Código do Tipo do Débito 
										null, // 1.3.1.8. Ano da Emissão da Guia de Pagamento 
										String
												.valueOf(prestacao.getIdParcela()), // 1.3.1.9. Seqüencial do Documento de Cobrança 
										DocumentoTipo.EXTRATO_CONTRATO_PARCELAMENTO, // 1.3.1.10.	Código do Tipo de Documento 
										new Integer(codigoCliente), //1.3.1.11.	Código do Cliente Responsável 
										null, // 1.3.1.12. Sequencial do documento de cobrança  
										null);
						
						// Formata a representação númerica do
						// código de
						// barras
						String representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarra
								.substring(0, 11)
								+ "-"
								+ representacaoNumericaCodBarra.substring(
										11, 12)
								+ " "
								+ representacaoNumericaCodBarra.substring(
										12, 23)
								+ "-"
								+ representacaoNumericaCodBarra.substring(
										23, 24)
								+ " "
								+ representacaoNumericaCodBarra.substring(
										24, 35)
								+ "-"
								+ representacaoNumericaCodBarra.substring(
										35, 36)
								+ " "
								+ representacaoNumericaCodBarra.substring(
										36, 47)
								+ "-"
								+ representacaoNumericaCodBarra.substring(
										47, 48);
						
						String representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarra
						.substring(0, 11)
						+ representacaoNumericaCodBarra.substring(12, 23)
						+ representacaoNumericaCodBarra.substring(24, 35)
						+ representacaoNumericaCodBarra.substring(36, 47);
						
						// 1.3.1. Representação numérica do código de barras
						// 1.4.1. Código de barras do documento de cobrança
						bean.setRepresentacaoNumericaCodBarraFormatada1(representacaoNumericaCodBarraFormatada);
						bean.setRepresentacaoNumericaCodBarraSemDigito1(representacaoNumericaCodBarraSemDigito);
						
					}
					
					ArrayList aux = new ArrayList();
					aux.add(bean);
					
					bean.setArrayJRSubrelatorioBean1(new JRBeanCollectionDataSource(aux));
					
					colecaoBean.add(bean);
					
				}
				
			}
			
			
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
		return colecaoBean;
	}
	
	/**
	 * [UC1136] Inserir Contrato de Parcelamento por Cliente
	 * 
	 * Retorna os dados do débito a cobrar caso exista algum pagamento para o mesmo.
	 * 
	 * @author Mariana Victor
	 * @data 03/08/2011
	 */
	public Object[] obterDadosDebitoACobrarPagoAMenor(Integer idDebitoACobrar) 
		throws ControladorException {
		try {
			return repositorioContratoParcelamento.
				obterDadosDebitoACobrarPagoAMenor(idDebitoACobrar);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 05/08/2011
	 */
	public ContratoParcelamento pesquisarContratoParcelamentoCompleto(Integer idContrato, String numeroContrato) 
		throws ControladorException {
		try {
			return repositorioContratoParcelamento.
				pesquisarContratoParcelamentoCompleto(idContrato, numeroContrato);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC1201] Emitir Extrato de Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 06/08/2011
	 */
	public Integer pesquisarIdClientecontrato(Integer idContratoParcelamento) 
		throws ControladorException {
		try {
			return repositorioContratoParcelamento.
				pesquisarIdClientecontrato(idContratoParcelamento);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC1140] Cancelar Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 06/08/2011
	 */
	public ContratoParcelamentoCliente pesquisarClienteContrato(Integer idContratoParcelamento) 
		throws ControladorException {
		try {
			return repositorioContratoParcelamento.
			pesquisarClienteContrato(idContratoParcelamento);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 12/08/2011
	 */
	public ContratoParcelamentoCliente pesquisarClienteContrato(
			Integer idContratoParcelamento, 
			Integer idCliente)
		throws ControladorException {
		try {
			return repositorioContratoParcelamento.
				pesquisarClienteContrato(idContratoParcelamento, idCliente);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 15/08/2011
	 */
	public Collection<ContratoParcelamentoCliente> pesquisarClienteContrato(
			Integer idContratoParcelamento,
			Short indicadorClienteSuperior) 
		throws ControladorException {
		try {
			return repositorioContratoParcelamento.
				pesquisarClienteContrato(idContratoParcelamento, indicadorClienteSuperior);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC1141] Emitir Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @created 29/08/2011
	 * 
	 * @param idContratoParcelamento
	 * @exception ErroRepositorioException
	 */
	public Collection<ContratoParcelamentoItem> pesquisarContratoParcelamentoItem(
			Integer idContratoParcelamento, Integer idDocumentoTipo) throws ControladorException {
		try {
			return repositorioContratoParcelamento.
			pesquisarContratoParcelamentoItem(idContratoParcelamento, idDocumentoTipo);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	

	/**
	 * [UC1136] Inserir Contrato de Parcelamento por Cliente
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Calcula e retorna os valores das parcelas do contrato de parcelamento.
	 * 
	 * @author Mariana Victor
	 * @data 29/08/2011
	 */
	public InserirContratoParcelamentoValoresParcelasHelper calcularValoresParcelasContratoParcelamento(
			BigDecimal valorContaSelecaoTotal, BigDecimal acrescimo, String indicadorDebitoAcresc,
			String indicadorParcelJuros, BigDecimal jurosBigDec, int numeroParcelInicial, int numeroParcelFinal) 
		throws ControladorException {
			InserirContratoParcelamentoValoresParcelasHelper helper = new InserirContratoParcelamentoValoresParcelasHelper();
			
			SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();
			
			List<PrestacaoContratoParcelamento> listaDeParcelas = new ArrayList();
			List<Float> listaValoresDistintos = new ArrayList<Float>();
			
			BigDecimal valorParcelado = BigDecimal.ZERO;
			BigDecimal valorEntrada = BigDecimal.ZERO;
			
			BigDecimal valorTotalServico = null;
			
			if(Integer.parseInt(indicadorDebitoAcresc) == ContratoParcelamento.DEBITO_ACRESCIMO_SIM.intValue()) {
				valorTotalServico = acrescimo;
			} else {
				valorTotalServico = valorContaSelecaoTotal;
			}
			
			if(new Short(indicadorParcelJuros).compareTo(ConstantesSistema.SIM) == 0) {

				BigDecimal taxaJuros = new BigDecimal("0.00");
				BigDecimal valorPrestacao = new BigDecimal("0.00");
				
				taxaJuros = Util.dividirArredondando(jurosBigDec, new BigDecimal( 100 ));
				
				if (sistemaParametro.getIndicadorTabelaPrice() != null 
						&& sistemaParametro.getIndicadorTabelaPrice().equals( ConstantesSistema.SIM ) ){

					valorPrestacao = this.getControladorFaturamento().calcularPrestacao( 
							taxaJuros.multiply( new BigDecimal( 100 ) ), 
							numeroParcelFinal,
							valorTotalServico,
							valorEntrada );
	
					valorPrestacao = valorPrestacao.setScale(
							2, BigDecimal.ROUND_HALF_DOWN);
					
					listaValoresDistintos.add(valorPrestacao.floatValue());
					
					for(;numeroParcelInicial <= numeroParcelFinal; numeroParcelInicial++){
						
						PrestacaoContratoParcelamento prestacao = new PrestacaoContratoParcelamento();
						prestacao.setNumero(numeroParcelInicial);
						prestacao.setValor(valorPrestacao);
						listaDeParcelas.add(prestacao);
						
						valorParcelado = valorParcelado.add(valorPrestacao);
						
					}
					
				} else {

					BigDecimal valorUm = new BigDecimal("1.00");
					
					BigDecimal fatorPrestacao = valorUm.add(taxaJuros.setScale(4, BigDecimal.ROUND_HALF_UP));
					
					fatorPrestacao = fatorPrestacao.setScale(4, BigDecimal.ROUND_HALF_UP).pow(numeroParcelFinal);

					BigDecimal valorFuturo = (valorTotalServico.multiply(fatorPrestacao)).setScale(4, BigDecimal.ROUND_HALF_UP);

					valorPrestacao = Util.dividirArredondando(valorFuturo.setScale(
						Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO),
						new BigDecimal(numeroParcelFinal));

					listaValoresDistintos.add(valorPrestacao.floatValue());
					
					for(;numeroParcelInicial <= numeroParcelFinal; numeroParcelInicial++){
						
						PrestacaoContratoParcelamento prestacao = new PrestacaoContratoParcelamento();
						prestacao.setNumero(numeroParcelInicial);
						prestacao.setValor(valorPrestacao);
						listaDeParcelas.add(prestacao);
						
						valorParcelado = valorParcelado.add(valorPrestacao);
						
					}
					
				}
				
				
			} else {
				
				if(Integer.parseInt(indicadorDebitoAcresc) == ContratoParcelamento.DEBITO_ACRESCIMO_SIM.intValue()) {
					valorParcelado = acrescimo;
				} else {
					valorParcelado = valorContaSelecaoTotal;
				}
				
				BigDecimal valorParcela = valorParcelado.divide(new BigDecimal(numeroParcelFinal),2 , BigDecimal.ROUND_UP);
				BigDecimal totalSomaParcelas = valorParcela.multiply(new BigDecimal(numeroParcelFinal));
				listaValoresDistintos.add(valorParcela.floatValue());
				for(;numeroParcelInicial < numeroParcelFinal; numeroParcelInicial++){
					PrestacaoContratoParcelamento prestacao = new PrestacaoContratoParcelamento();
					prestacao.setNumero(numeroParcelInicial);
					prestacao.setValor(valorParcela);
					listaDeParcelas.add(prestacao);
				}
				
				BigDecimal diferenca = valorParcelado.subtract(totalSomaParcelas);
				if(diferenca.floatValue() != 0.0){
					listaValoresDistintos.add(valorParcela.add(diferenca).floatValue());
				}
				PrestacaoContratoParcelamento prestacao = new PrestacaoContratoParcelamento();
				prestacao.setNumero(numeroParcelInicial);
				prestacao.setValor(valorParcela.add(diferenca));
				listaDeParcelas.add(prestacao);
				
			}
			
			helper.setValorParcelado(valorParcelado);
			helper.setListaDeParcelas(listaDeParcelas);
			helper.setListaValoresDistintos(listaValoresDistintos);
			
			return helper;
	}

	/**
	 * [UC1136] Inserir Contrato de Parcelamento por Cliente
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * Calcula e retorna os valores das parcelas do contrato de parcelamento a partir de uma RD.
	 * 
	 * @author Mariana Victor
	 * @data 29/08/2011
	 */
	public InserirContratoParcelamentoValoresParcelasHelper calcularValoresParcelasContratoParcelamentoRD(
			BigDecimal valorContaSelecaoTotal, BigDecimal valorContaComAcrescimo, String indicadorDebitoAcresc,
			String indicadorParcelJuros, ContratoParcelamento contratoParcelamento, 
			QuantidadePrestacoes quantidadePrestacoes) 
		throws ControladorException {
			InserirContratoParcelamentoValoresParcelasHelper helper = 
				new InserirContratoParcelamentoValoresParcelasHelper();
			List<PrestacaoContratoParcelamento> listaDeParcelas = new ArrayList();
			BigDecimal valorParcelado = BigDecimal.ZERO;
			SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();
			
			contratoParcelamento.setIndicadorParcelaInformadaPeloUsuario(new Short("2"));
			contratoParcelamento.setQtdPrestacoesDaRDEscolhida(quantidadePrestacoes);
			
			BigDecimal valorJurosParcelamento = null;
			BigDecimal valorPrestacao = new BigDecimal("0.00");
			if(contratoParcelamento.getIndicadorParcelamentoJuros().intValue() == 1){
				
				if(new Short(indicadorParcelJuros).compareTo(ConstantesSistema.SIM) == 0){

					BigDecimal taxaJuros = new BigDecimal("0.00");
					BigDecimal valorEntrada = BigDecimal.ZERO;
					BigDecimal valorTotalServico = null;
					
					if(Integer.parseInt(indicadorDebitoAcresc) == ContratoParcelamento.DEBITO_ACRESCIMO_SIM.intValue()) {
						valorTotalServico = valorContaComAcrescimo;
					} else {
						valorTotalServico = valorContaSelecaoTotal;
					}
					
					taxaJuros = Util.dividirArredondando(quantidadePrestacoes.getTaxaJuros(), new BigDecimal( 100 ));
					
					if (sistemaParametro.getIndicadorTabelaPrice() != null 
						&& sistemaParametro.getIndicadorTabelaPrice().equals( ConstantesSistema.SIM ) ) {
						
						valorPrestacao = this.getControladorFaturamento().calcularPrestacao( 
								taxaJuros.multiply( new BigDecimal( 100 ) ), 
								quantidadePrestacoes.getQtdFaturasParceladas(),
								valorTotalServico,
								valorEntrada );
	
						valorPrestacao = valorPrestacao.setScale(
								2, BigDecimal.ROUND_HALF_DOWN);
	
						for(int i = 1;i <= quantidadePrestacoes.getQtdFaturasParceladas(); i++){
							valorParcelado = valorParcelado.add(valorPrestacao);
						}
						
						valorJurosParcelamento = valorParcelado.subtract(valorTotalServico);
						
					} else {

						BigDecimal valorUm = new BigDecimal("1.00");
						
						BigDecimal fatorPrestacao = valorUm.add(taxaJuros.setScale(4, BigDecimal.ROUND_HALF_UP));
						
						fatorPrestacao = fatorPrestacao.setScale(4, BigDecimal.ROUND_HALF_UP).pow(quantidadePrestacoes.getQtdFaturasParceladas());

						BigDecimal valorFuturo = (valorTotalServico.multiply(fatorPrestacao)).setScale(4, BigDecimal.ROUND_HALF_UP);

						valorPrestacao = Util.dividirArredondando(valorFuturo.setScale(
							Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO),
							new BigDecimal(quantidadePrestacoes.getQtdFaturasParceladas()));

						for(int i = 1;i <= quantidadePrestacoes.getQtdFaturasParceladas(); i++){
							valorParcelado = valorParcelado.add(valorPrestacao);
						}
						
						valorJurosParcelamento = valorParcelado.subtract(valorTotalServico);
						
					}
					
				} else {
					if(contratoParcelamento.getIndicadorDebitosAcrescimos().intValue() == ContratoParcelamento.DEBITO_ACRESCIMO_SIM.intValue()){
						valorParcelado = valorContaComAcrescimo;
					}else{
						valorParcelado = valorContaSelecaoTotal;
					}
					valorPrestacao = valorParcelado.divide(new BigDecimal(""+quantidadePrestacoes.getQtdFaturasParceladas()),2, BigDecimal.ROUND_HALF_UP);
				}
				
				if(valorJurosParcelamento != null && valorJurosParcelamento.compareTo(BigDecimal.ZERO) > 0){
					contratoParcelamento.setValorDebitoAtualizado(valorParcelado.subtract(valorJurosParcelamento));
				}else{
					contratoParcelamento.setValorDebitoAtualizado(valorParcelado);
				}
				contratoParcelamento.setValorJurosParcelamento(valorJurosParcelamento);
				contratoParcelamento.setTaxaJuros(quantidadePrestacoes.getTaxaJuros());
				
			}else{
				if(Integer.parseInt(indicadorDebitoAcresc) == ContratoParcelamento.DEBITO_ACRESCIMO_SIM.intValue()){
					valorParcelado = valorContaComAcrescimo;
				}else{
					valorParcelado = valorContaSelecaoTotal;
				}
				contratoParcelamento.setValorDebitoAtualizado(valorParcelado);
				
				contratoParcelamento.setValorJurosParcelamento(null);
				contratoParcelamento.setTaxaJuros(null);
			}
			
			
			contratoParcelamento.setValorTotalParcelado(valorParcelado);
			contratoParcelamento.setValorParcelamentoACobrar(valorParcelado);
			
			listaDeParcelas = new ArrayList<PrestacaoContratoParcelamento>();
			
			BigDecimal diferenca = valorParcelado.subtract( valorPrestacao.multiply(new BigDecimal(""+quantidadePrestacoes.getQtdFaturasParceladas())));
			PrestacaoContratoParcelamento prestacao = new PrestacaoContratoParcelamento();
			prestacao.setNumero(1);
			prestacao.setValor(valorPrestacao.add(diferenca));
			listaDeParcelas.add(prestacao);
			
			for(int i = 2; i <= quantidadePrestacoes.getQtdFaturasParceladas(); i++){
				prestacao = new PrestacaoContratoParcelamento();
				prestacao.setNumero(i);
				prestacao.setValor(valorPrestacao);
				listaDeParcelas.add(prestacao);
			}
			
			helper.setValorParcelado(valorParcelado);
			helper.setListaDeParcelas(listaDeParcelas);
			helper.setContratoParcelamento(contratoParcelamento);
			
			return helper;
	}
	
	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 13/09/2011
	 */
	public CobrancaForma pesquisarFormaPagamentoRD(
			Integer idRD) throws ControladorException {
			try {
				return repositorioContratoParcelamento.
					pesquisarFormaPagamentoRD(idRD);
			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", ex);
			}
		}
   
}
