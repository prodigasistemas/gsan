package gcom.gui.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class FiltroSessaoExpirada extends HttpServlet implements Filter {

	private FilterConfig filterConfig;

	private static final long serialVersionUID = 1L;

	public void init(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		try {
			// Faz um cast no request para recuperar a sessão do usuário
			HttpServletRequest requestPagina = ((HttpServletRequest) request);
			HttpSession sessao = requestPagina.getSession(false);

			// Recupera a url do request
			String enderecoURL = requestPagina.getServletPath();

			// Caso a sessão esteja nula(expirou) redireciona o usuário para a
			// página de sessão expirada
			// Caso contrário chama o próximo filtro do web.xml se existir
			if (sessao == null
					&& (!enderecoURL.contains("exibirEmitirSegundaViaContaInternetAcessoGeralAction") 
							&& !enderecoURL.contains("emitirSegundaViaContaInternetAcessoGeralAction")
							&& !enderecoURL.contains("enviarDadosBancosAcessoGeralAction") 
							&& !enderecoURL.contains("exibirSelecionarBancoAction") 
							&& !enderecoURL.contains("enviarDadosBancosAction")
							&& !enderecoURL.contains("exibirSelecionarBancoAcessoGeralAction") 
							&& !enderecoURL.contains("exibirLogTelaInicialAction")
							&& !enderecoURL.contains("exibirLogTelaFinalAction")
							&& !enderecoURL.contains("processarRequisicaoDipositivoMovelAction")
							&& !enderecoURL.contains("processarRequisicaoTelemetriaAction")
							&& !enderecoURL.contains("efetuarLoginAction")
							&& !enderecoURL.contains("processarRequisicaoGisAction")
							&& !enderecoURL.contains("processarCoordenadasGisAction")
							&& !enderecoURL.contains("processarRequisicaoDipositivoMovelImpressaoSimultaneaAction")
							&& !enderecoURL.contains("processarRequisicaoDispositivoMovelAcompanhamentoServicoAction") 
							&& !enderecoURL.contains("exibirInserirCadastroEmailClienteAction")
							&& !enderecoURL.contains("inserirCadastroEmailClienteAction")
							&& !enderecoURL.contains("gerarRelatorio2ViaContaAction")
							&& !enderecoURL.contains("exibirInserirCadastroContaBraileAction") 
							&& !enderecoURL.contains("inserirCadastroContaBraileAction")
							&& !enderecoURL.contains("portal") 
							&& !enderecoURL.contains("inserirCadastroContaBrailePortalAction")
							&& !enderecoURL.contains("exibirInserirSolicitacaoServicosPortalAction") 
							&& !enderecoURL.contains("inserirSolicitacaoServicosPortalAction")
							&& !enderecoURL.contains("exibirInserirCadastroContaBrailePortalAction") 
							&& !enderecoURL.contains("inserirCadastroEmailClientePortalAction")
							&& !enderecoURL.contains("exibirInserirCadastroEmailClientePortalAction") 
							&& !enderecoURL.contains("segunda-via-conta")
							&& !enderecoURL.contains("canais-de-atendimento") 
							&& !enderecoURL.contains("exibirQuestionarioSatisfacaoAction")
							&& !enderecoURL.contains("questionarioSatisfacaoAction") 
							&& !enderecoURL.contains("exibirInformacoesPortalAction")
							&& !enderecoURL.contains("exibirInformacoesTarifaSocialPortalCompesaAction")
							&& !enderecoURL.contains("exibirInformacoesNegociacaoDebitosPortalCompesaAction")
							&& !enderecoURL.contains("exibirNormasInstalacaoPortalCompesaAction") 
							&& !enderecoURL.contains("exibirCalendarioAbastecimentoPortalCompesaAction")
							&& !enderecoURL.contains("exibirEfetuarParcelamentoDebitosPortalAction") 
							&& !enderecoURL.contains("efetuarParcelamentoDebitosPortalAction")
							&& !enderecoURL.contains("lojas-de-atendimento") 
							&& !enderecoURL.contains("exibirConsultarEstruturaTarifariaPortalAction")
							&& !enderecoURL.contains("acessar-portal")
							&& !enderecoURL.contains("acessarPortalAction")
							&& !enderecoURL.contains("certidao-negativa-imovel")
							&& !enderecoURL.contains("gerarCertidaoNegativaImovelPortalAction")
							&& !enderecoURL.contains("certidao-negativa-cliente")
							&& !enderecoURL.contains("gerarCertidaoNegativaClientePortalAction")
					)) {

				RequestDispatcher rd = filterConfig.getServletContext().getRequestDispatcher("/jsp/util/sessao_expirada.jsp");
				rd.forward(request, response);
			} else {
				if (enderecoURL.contains("exibirEmitirSegundaViaContaInternetAcessoGeralAction")) {
					// Cria uma sessao temporaria para o usuario que entra no
					// EmitirSegundaViaContaInternet sem logar no sistema
					sessao = requestPagina.getSession(true);
				}
				filterChain.doFilter(request, response);
			}

		} catch (ServletException sx) {
			throw sx;
		} catch (IOException iox) {
			throw iox;
		}
	}

	public void destroy() {
	}
}
