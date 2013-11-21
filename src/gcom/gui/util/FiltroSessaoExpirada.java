/*
 * Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
 *
 * This file is part of GSAN, an integrated service management system for Sanitation
 *
 * GSAN is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License.
 *
 * GSAN is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
 */

/*
 * GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
 * Copyright (C) <2007> 
 * Adriano Britto Siqueira
 * Alexandre Santos Cabral
 * Ana Carolina Alves Breda
 * Ana Maria Andrade Cavalcante
 * Aryed Lins de Araújo
 * Bruno Leonardo Rodrigues Barros
 * Carlos Elmano Rodrigues Ferreira
 * Cláudio de Andrade Lira
 * Denys Guimarães Guenes Tavares
 * Eduardo Breckenfeld da Rosa Borges
 * Fabíola Gomes de Araújo
 * Flávio Leonardo Cavalcanti Cordeiro
 * Francisco do Nascimento Júnior
 * Homero Sampaio Cavalcanti
 * Ivan Sérgio da Silva Júnior
 * José Edmar de Siqueira
 * José Thiago Tenório Lopes
 * Kássia Regina Silvestre de Albuquerque
 * Leonardo Luiz Vieira da Silva
 * Márcio Roberto Batista da Silva
 * Maria de Fátima Sampaio Leite
 * Micaela Maria Coelho de Araújo
 * Nelson Mendonça de Carvalho
 * Newton Morais e Silva
 * Pedro Alexandre Santos da Silva Filho
 * Rafael Corrêa Lima e Silva
 * Rafael Francisco Pinto
 * Rafael Koury Monteiro
 * Rafael Palermo de Araújo
 * Raphael Veras Rossiter
 * Roberto Sobreira Barbalho
 * Rodrigo Avellar Silveira
 * Rosana Carvalho Barbosa
 * Sávio Luiz de Andrade Cavalcante
 * Tai Mu Shih
 * Thiago Augusto Souza do Nascimento
 * Tiago Moreno Rodrigues
 * Vivianne Barbosa Sousa
 *
 * Este programa é software livre; você pode redistribuí-lo e/ou
 * modificá-lo sob os termos de Licença Pública Geral GNU, conforme
 * publicada pela Free Software Foundation; versão 2 da
 * Licença.
 * Este programa é distribuído na expectativa de ser útil, mas SEM
 * QUALQUER GARANTIA; sem mesmo a garantia implícita de
 * COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
 * PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
 * detalhes.
 * Você deve ter recebido uma cópia da Licença Pública Geral GNU
 * junto com este programa; se não, escreva para Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307, USA.
 */
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

/**
 * Filtro responsável por verificar se a sessão do usuário expirou o tempo
 * 
 * @author Pedro Alexandre
 * @date 05/07/2006
 */
public class FiltroSessaoExpirada extends HttpServlet implements Filter {
	// Variável que vai armazenar a configuração do filtro
	private FilterConfig filterConfig;

	private static final long serialVersionUID = 1L;

	/**
	 * <Breve descrição sobre o caso de uso>
	 * 
	 * @author Pedro Alexandre
	 * @date 05/07/2006
	 * 
	 * @param filterConfig
	 */
	public void init(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	/**
	 * <Breve descrição sobre o caso de uso>
	 * 
	 * @author Pedro Alexandre
	 * @date 05/07/2006
	 * 
	 * @param request
	 * @param response
	 * @param filterChain
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {
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
					&& (!enderecoURL
							.contains("exibirEmitirSegundaViaContaInternetAcessoGeralAction")
							&& !enderecoURL
									.contains("emitirSegundaViaContaInternetAcessoGeralAction")
							&& !enderecoURL
									.contains("enviarDadosBancosAcessoGeralAction")
							&& !enderecoURL
									.contains("exibirSelecionarBancoAction")
							&& !enderecoURL.contains("enviarDadosBancosAction")
							&& !enderecoURL
									.contains("exibirSelecionarBancoAcessoGeralAction")
							&& !enderecoURL
									.contains("exibirLogTelaInicialAction")
							&& !enderecoURL
									.contains("exibirLogTelaFinalAction")
							&& !enderecoURL
									.contains("processarRequisicaoDipositivoMovelAction")
							&& !enderecoURL
									.contains("processarRequisicaoTelemetriaAction")
							&& !enderecoURL.contains("efetuarLoginAction")
							&& !enderecoURL
									.contains("processarRequisicaoGisAction")
							&& !enderecoURL
									.contains("processarCoordenadasGisAction")
							&& !enderecoURL
									.contains("processarRequisicaoDipositivoMovelImpressaoSimultaneaAction")
                            && !enderecoURL
                            		.contains ("processarRequisicaoDispositivoMovelAcompanhamentoServicoAction") 									
							&& !enderecoURL
									.contains("exibirInserirCadastroEmailClienteAction")
							&& !enderecoURL
									.contains("inserirCadastroEmailClienteAction")
							&& !enderecoURL
									.contains("gerarRelatorio2ViaContaAction")
							&& !enderecoURL
									.contains("exibirInserirCadastroContaBraileAction")
							&& !enderecoURL
									.contains("inserirCadastroContaBraileAction")
							&& !enderecoURL
									.contains("exibirServicosPortalCompesaAction")
							&& !enderecoURL
									.contains("inserirCadastroContaBrailePortalAction")
							&& !enderecoURL
									.contains("exibirInserirSolicitacaoServicosPortalAction")
							&& !enderecoURL
									.contains("inserirSolicitacaoServicosPortalAction")
							&& !enderecoURL
									.contains("exibirInserirCadastroContaBrailePortalAction")
							&& !enderecoURL
									.contains("inserirCadastroEmailClientePortalAction") 
							&& !enderecoURL
									.contains("exibirInserirCadastroEmailClientePortalAction")
							&& !enderecoURL
									.contains("emitirSegundaViaContaAction")
							&& !enderecoURL
									.contains("exibirCanaisAtendimentoCompesaAction")
							&& !enderecoURL
									.contains("exibirQuestionarioSatisfacaoAction")
							&& !enderecoURL
									.contains("questionarioSatisfacaoAction")
							&& !enderecoURL
									.contains("exibirInformacoesPortalCompesaAction")
							&& !enderecoURL
									.contains("exibirInformacoesTarifaSocialPortalCompesaAction")
							&& !enderecoURL
									.contains("exibirInformacoesNegociacaoDebitosPortalCompesaAction")
							&& !enderecoURL
									.contains("exibirNormasInstalacaoPortalCompesaAction")
							&& !enderecoURL
									.contains("exibirCalendarioAbastecimentoPortalCompesaAction")
							&& !enderecoURL.
									contains("exibirEfetuarParcelamentoDebitosPortalAction")
							&& !enderecoURL.
									contains("efetuarParcelamentoDebitosPortalAction")
							&& !enderecoURL.
									contains("exibirLojasAtendimentoPresencialPortalCompesaAction")
							&& !enderecoURL.
									contains("exibirConsultarEstruturaTarifariaPortalAction")

					)) {

				RequestDispatcher rd = filterConfig.getServletContext()
						.getRequestDispatcher("/jsp/util/sessao_expirada.jsp");
				rd.forward(request, response);
			} else {
				if (enderecoURL
						.contains("exibirEmitirSegundaViaContaInternetAcessoGeralAction")) {
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

	/**
	 * <Breve descrição sobre o caso de uso>
	 * 
	 * @author Pedro Alexandre
	 * @date 05/07/2006
	 * 
	 */
	public void destroy() {
	}
}