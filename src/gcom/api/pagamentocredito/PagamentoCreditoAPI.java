package gcom.api.pagamentocredito;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.ejb.CreateException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpStatus;

import com.google.gson.Gson;

import gcom.arrecadacao.ControladorArrecadacaoLocal;
import gcom.arrecadacao.ControladorArrecadacaoLocalHome;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.IRepositorioClienteImovel;
import gcom.cadastro.cliente.RepositorioClienteImovelHBM;
import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.fachada.Fachada;
import gcom.faturamento.ControladorFaturamentoLocal;
import gcom.faturamento.ControladorFaturamentoLocalHome;
import gcom.faturamento.conta.Conta;
import gcom.util.CodigoBarras;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;

/**
 * 
 * @author Kurt Matheus Sampaio de Matos
 * @date 22/06/2022
 * 	
 */
public class PagamentoCreditoAPI extends HttpServlet {
	
	
	
	private IRepositorioClienteImovel repositorioClienteImovel = RepositorioClienteImovelHBM.getInstancia();
	private Fachada fachada = Fachada.getInstancia();
	private HttpServletRequest request = null;
	private HttpServletResponse response = null;
	private Gson gson = new Gson();
	private String erro = "Erro na Requisição: ";
	PagamentoCreditoResponse pagCreditoResponse;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		this.pagCreditoResponse = new PagamentoCreditoResponse();
		
		validarMatriculaECadastro();
			
		String matricula = request.getParameter("id");
		String cadastroPessoa = request.getParameter("cadastroPessoa");
		String linhaDigitavel = request.getParameter("linha");
		
		Collection<ClienteImovel> colecaoClienteImovel = null;
		
		if (!isNuloOuVazio(matricula)) {			
			try {				
				colecaoClienteImovel = repositorioClienteImovel.pesquisarClienteImovelResponsavelConta(Integer.valueOf(matricula));
				
				if(colecaoClienteImovel == null) {
					pagCreditoResponse.setMensagem(erro + "Imóvel sem Cliente válidos.");
				} 
				
			} catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		}
		
		if (!isNuloOuVazio(cadastroPessoa) && !pagCreditoResponse.isMensagemPreenchida()) {			
			if (colecaoClienteImovel != null) {
				ClienteImovel clienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(colecaoClienteImovel);
				if (!clienteImovel.getCliente().getCpfOuCnpj().equalsIgnoreCase(cadastroPessoa)) {
					pagCreditoResponse.setMensagem(erro + "CPF ou CNPJ não corresponde ao Imóvel.");
				}
			} else {
				try {
					colecaoClienteImovel = repositorioClienteImovel.pesquisarClienteResponsavelContaPorCpfCnpj(cadastroPessoa);
				} catch (ErroRepositorioException e) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				}
			}
		}
				
		ArrayList<Object> resposta = new ArrayList<Object>();
		
		if (!pagCreditoResponse.isMensagemPreenchida() 
				&& isNuloOuVazio(linhaDigitavel)
				&& !isForbidden()) {
			try {
				for (ClienteImovel clienteImovel : colecaoClienteImovel) {
					Imovel imovel = clienteImovel.getImovel();
					Cliente cliente = clienteImovel.getCliente();				
					
					ObterDebitoImovelOuClienteHelper helper = (ObterDebitoImovelOuClienteHelper) fachada
							.obterDebitoImovelOuCliente(1, imovel.getId().toString(), cliente.getId().toString(), null, "000101",
									"999912", Util.converteStringParaDate("01/01/0001"),
									Util.converteStringParaDate("31/12/9999"), 1, 1, 1, 1, 1, 1, 1, null);
					for (ContaValoresHelper contaValores : helper.getColecaoContasValores()) {

						String representacaoNumericaCodBarraFormatada = "";

						if (cliente.getCpfOuCnpj() != null && imovel.getCodigoConvenio() != null) {
							representacaoNumericaCodBarraFormatada = preencherCodigoBarrasContaFichaCompensacao(
									contaValores.getConta(), imovel);
						} else {
							representacaoNumericaCodBarraFormatada = preencherCodigoBarrasConta(contaValores.getConta(),
									imovel);
						}

						PagamentoCreditoDTO pagamentoCreditoDTO = new PagamentoCreditoDTO();

						pagamentoCreditoDTO.setMatricula(imovel.getId().toString());
						pagamentoCreditoDTO.setCodigoBarras(representacaoNumericaCodBarraFormatada);
						pagamentoCreditoDTO.setDataVencimento(contaValores.getVencimentoConta());
						pagamentoCreditoDTO.setValorTotalConta(contaValores.getValorTotalConta());

						if (cliente.getCpfOuCnpj() != null) {
							pagamentoCreditoDTO.setCadastroCliente(cliente.getCpfOuCnpj());
						} else {
							pagamentoCreditoDTO.setCadastroCliente("");
						}

						pagamentoCreditoDTO.setIdentificadorConta(contaValores.getConta().getId().toString());

						String json = gson.toJson(pagamentoCreditoDTO);
						resposta.add(json);

					} 
				}
				response.getOutputStream().print(resposta.toString());
				response.setStatus(HttpServletResponse.SC_OK);

			} catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			} 
		} else if(!isNuloOuVazio(linhaDigitavel) && validarConta(linhaDigitavel)) {
			response.getOutputStream().print(gson.toJson("Sucesso: Conta Registrada!").toString());
			response.setStatus(HttpServletResponse.SC_OK);			
		} else if(isForbidden()) {
			pagCreditoResponse.setMensagem(erro + "Acesso Proibido!");
			response.getOutputStream().print(gson.toJson(pagCreditoResponse).toString());
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		} else {
			response.getOutputStream().print(gson.toJson(pagCreditoResponse).toString());
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
				
	}

	private boolean isForbidden() {
		return request.getAttribute("filtro") != null 
				&& Integer.valueOf(request.getAttribute("filtro").toString()) == HttpStatus.SC_FORBIDDEN;
	}

	private boolean isNuloOuVazio(String dado) {
		return (dado == null || dado.isEmpty());			
	}
	
	@SuppressWarnings("unchecked")
	private void validarMatriculaECadastro() {
		
		String matricula = this.request.getParameter("id");
		String cadastroPessoa = this.request.getParameter("cadastroPessoa");
		String idConta = this.request.getParameter("linha");
		
		if (isNuloOuVazio(matricula) && isNuloOuVazio(cadastroPessoa) && isNuloOuVazio(idConta)) {
			pagCreditoResponse.setMensagem(erro + "Matrícula ou CPF devem estar preenchidos.");
		}
		
		if (!isNuloOuVazio(matricula)) {
			if (!Util.isPositivo(matricula) || fachada.verificarExistenciaImovel(Integer.valueOf(matricula)) != 1) {
				pagCreditoResponse.setMensagem(erro + "Matrícula Inválida.");
			}
		}
		
		if (!isNuloOuVazio(cadastroPessoa)) {
			try {
				if (Util.retonarObjetoDeColecao(repositorioClienteImovel.pesquisarClienteResponsavelContaPorCpfCnpj(cadastroPessoa)) == null) {
					pagCreditoResponse.setMensagem(erro + "Cadastro de Pessoa em formato inválido.");		
				}
			} catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		}
	}
	
	private boolean validarConta(String linha) throws IOException {
		
		if (linha.length() != Integer.valueOf(47)) {
			pagCreditoResponse.setMensagem(erro + "Linha Digitável Inválida!");
			return false;
		}
		
		String conta = "1"+linha.substring(21, 29);		 
		
		if (isNuloOuVazio(conta) && !fachada.fichaCompensacaoExistente(Integer.valueOf(conta))) {
			pagCreditoResponse.setMensagem(erro + "Conta não registrada!");
			return false;
		}
		return true;
	}
	
	private String preencherCodigoBarrasConta(Conta conta, Imovel imovel)
			throws ControladorException {
		
		BigDecimal valorConta = conta.getValorTotalContaComRateioBigDecimal();

		String anoMesString = "" + conta.getAnoMesReferenciaConta();
		String mesAnoFormatado = anoMesString.substring(4, 6) + anoMesString.substring(0, 4);
		Integer digitoVerificadorConta = new Integer("" + conta.getDigitoVerificadorConta());

		String representacaoNumericaCodBarra = getControladorArrecadacao().obterRepresentacaoNumericaCodigoBarra(
				3, valorConta, imovel.getLocalidade().getId(), imovel.getId(), mesAnoFormatado,
				digitoVerificadorConta, null, null, null, null, null, null, null);

		return representacaoNumericaCodBarra;
	}
	
	private String preencherCodigoBarrasContaFichaCompensacao(Conta conta, Imovel imovel) throws ControladorException {
		
		
		StringBuilder nossoNumero = getControladorFaturamento().obterNossoNumeroFichaCompensacao("1", conta.getId().toString(), imovel.getCodigoConvenio());
		String nossoNumeroSemDV = nossoNumero.toString().substring(3, 20);

		Date dataVencimentoMais90 = Util.adicionarNumeroDiasDeUmaData(new Date(), 90);
		String fatorVencimento = CodigoBarras.obterFatorVencimento(dataVencimentoMais90);

			String especificacaoCodigoBarra = CodigoBarras.obterEspecificacaoCodigoBarraFichaCompensacao(
							ConstantesSistema.CODIGO_BANCO_FICHA_COMPENSACAO,
							ConstantesSistema.CODIGO_MOEDA_FICHA_COMPENSACAO,
							conta.getValorTotalContaComRateioBigDecimal(),
							nossoNumeroSemDV.toString(),
							ConstantesSistema.CARTEIRA_CONTA,
							fatorVencimento);

        String representacaoNumericaCodBarra = CodigoBarras.obterRepresentacaoNumericaCodigoBarraFichaCompensacao(especificacaoCodigoBarra);

		return representacaoNumericaCodBarra;
	}
	
	
	private ControladorFaturamentoLocal getControladorFaturamento() {
		ControladorFaturamentoLocalHome localHome = null;
		ControladorFaturamentoLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorFaturamentoLocalHome) locator.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FATURAMENTO_SEJB);
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

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorArrecadacaoLocalHome) locator.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_ARRECADACAO_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
}
