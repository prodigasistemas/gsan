package gcom.faturamento;

import gcom.arrecadacao.pagamento.FiltroGuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.cliente.ControladorClienteLocalHome;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.ControladorImovelLocalHome;
import gcom.relatorio.arrecadacao.pagamento.RelatorioGuiaPagamentoEmAtrasoHelper;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.SessionContext;


/**
 * Esta classe tem como finalidade encapsular o caso de uso [UC0877] - EmitirGuiaPagamentoEmAtraso, gerando
 * maior facilidade na manutenção do mesmo.  
 *
 * @author Flávio Leonardo
 * @date 27/01/2009
 */
public class UC00877EmitirGuiaPagamentoEmAtraso {
	
	private static UC00877EmitirGuiaPagamentoEmAtraso instancia;

	private IRepositorioFaturamento repositorioFaturamento;
	private SessionContext sessionContext;

	
	private UC00877EmitirGuiaPagamentoEmAtraso(IRepositorioFaturamento repositorioFaturamento, 
			SessionContext sessionContext) {

		this.repositorioFaturamento = repositorioFaturamento;
		this.sessionContext = sessionContext;
	}

	public static UC00877EmitirGuiaPagamentoEmAtraso getInstancia(IRepositorioFaturamento repositorioFaturamento,
			SessionContext sessionContext) {
		
		if (instancia == null) {
			instancia = new UC00877EmitirGuiaPagamentoEmAtraso(repositorioFaturamento, sessionContext);
		}
		return instancia;
	}
	
	private ControladorImovelLocal getControladorImovel() {

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
	
	private ControladorClienteLocal getControladorCliente() {

		ControladorClienteLocalHome localHome = null;
		ControladorClienteLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorClienteLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_CLIENTE_SEJB);
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
	
	public Collection<RelatorioGuiaPagamentoEmAtrasoHelper> pesquisarDadosRelatorioGuiaPagamentoEmAtraso(FiltroGuiaPagamento filtroGuiaPagamento)
		throws ControladorException{
		
		Collection retorno = new ArrayList();
		
		try {
			Collection colecaoRepositorio = repositorioFaturamento.pesquisarDadosRelatorioGuiaPagamentoEmAtraso(filtroGuiaPagamento);
			
			if(!colecaoRepositorio.isEmpty()){
				
				Iterator iteratorRepositorio = colecaoRepositorio.iterator();
				
				RelatorioGuiaPagamentoEmAtrasoHelper helper = null;
				
				Collection<RelatorioGuiaPagamentoEmAtrasoHelper> colecaoGuiaImovel = 
					new ArrayList<RelatorioGuiaPagamentoEmAtrasoHelper>();
				Collection<RelatorioGuiaPagamentoEmAtrasoHelper> colecaoGuiaCliente = 
					new ArrayList<RelatorioGuiaPagamentoEmAtrasoHelper>();
				
				
				
				while(iteratorRepositorio.hasNext()){
					GuiaPagamento guiaPagamento = (GuiaPagamento) iteratorRepositorio.next();
					helper = new RelatorioGuiaPagamentoEmAtrasoHelper();
					
					Cliente cliente = null;
					
					if(guiaPagamento.getImovel() != null){
						//Cliente
						cliente = getControladorImovel().pesquisarClienteUsuarioImovel(guiaPagamento.getImovel().getId());
						helper.setImovel(guiaPagamento.getImovel().getId().toString());
					}else{
						//Cliente
						cliente = guiaPagamento.getCliente();
					}
					cliente = getControladorCliente().pesquisarCliente(cliente.getId()); 
					//Cliente
					ClienteEndereco clienteEndereco = null;
					Collection colecaoEndereco = getControladorCliente().pesquisarEnderecosClienteAbreviado(cliente.getId());
					if(colecaoEndereco != null && !colecaoEndereco.isEmpty()){
						clienteEndereco = (ClienteEndereco)Util.retonarObjetoDeColecao(colecaoEndereco);
					}
					
					if(cliente.getCpf() != null){
						helper.setClienteCpf(cliente.getCpfFormatado());
					}else{
						helper.setClienteCpf(cliente.getCnpjFormatado());
					}
					
					helper.setClienteId(cliente.getId().toString());
					helper.setClienteNome(cliente.getNome().toUpperCase());
					helper.setClienteEndereco(clienteEndereco != null ? clienteEndereco.getEnderecoFormatadoAbreviado().toUpperCase() : "");
					//fim cliente
					
					helper.setEmissao(Util.formatarData(guiaPagamento.getDataEmissao()));
					helper.setVencimento(Util.formatarData(guiaPagamento.getDataVencimento()));
					helper.setValor(Util.formatarMoedaReal(guiaPagamento.getValorDebito()));
					helper.setReferencia(Util.formatarAnoMesParaMesAno(guiaPagamento.getAnoMesReferenciaContabil()));
					helper.setParcelas(guiaPagamento.getPrestacaoFormatada());
					
					if(guiaPagamento.getDebitoTipo() != null){
						helper.setDebitoTipoDescricao(guiaPagamento.getDebitoTipo().getDescricao());
					}
					
					if(guiaPagamento.getFinanciamentoTipo() != null){
						helper.setFinanciamentoTipoDescricao(guiaPagamento.getFinanciamentoTipo().getDescricao());
					}
					
					if(helper.getImovel() != null){
						colecaoGuiaImovel.add(helper);
					}else{
						colecaoGuiaCliente.add(helper);
					}
				}
				
				//organizar colecao retorno por cliente - alfabetica
				if(!colecaoGuiaImovel.isEmpty()){
					Collections.sort((List) colecaoGuiaImovel,
							new Comparator() {
								public int compare(Object a, Object b) {
									String posicao1 = ((RelatorioGuiaPagamentoEmAtrasoHelper) a)
											.getClienteNome();
									String posicao2 = ((RelatorioGuiaPagamentoEmAtrasoHelper) b)
											.getClienteNome();

									return posicao1.compareTo(posicao2);
								}
							});
				}
				if(!colecaoGuiaCliente.isEmpty()){
					Collections.sort((List) colecaoGuiaCliente,
							new Comparator() {
								public int compare(Object a, Object b) {
									String posicao1 = ((RelatorioGuiaPagamentoEmAtrasoHelper) a)
											.getClienteNome();
									String posicao2 = ((RelatorioGuiaPagamentoEmAtrasoHelper) b)
											.getClienteNome();

									return posicao1.compareTo(posicao2);
								}
							});
				}
				
				retorno.add(colecaoGuiaImovel);
				retorno.add(colecaoGuiaCliente);
				
				
			}
		} catch (ErroRepositorioException e) {
			
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		} catch (ControladorException e) {
			
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		
		return retorno;
	}
	
}
