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
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Filtro que limpa a sessão a cada vez que o usuário seleciona uma
 * funcionalidade no menu
 * 
 * @author Rafael Santos
 */
public class FiltroLimparSessao extends HttpServlet implements Filter {
	private FilterConfig filterConfig;
	private static final long serialVersionUID = 1L;
	// Handle the passed-in FilterConfig
	/**
	 * <<Descrição do método>>
	 * 
	 * @param filterConfig
	 *            Descrição do parâmetro
	 */
	public void init(FilterConfig filterConfig) {
		setFilterConfig(filterConfig);
	}

	// Process the request/response pair
	/**
	 * <<Descrição do método>>
	 * 
	 * @param request
	 *            Descrição do parâmetro
	 * @param response
	 *            Descrição do parâmetro
	 * @param filterChain
	 *            Descrição do parâmetro
	 * @throws ServletException 
	 * @throws IOException 
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {
		try {
			String url = ((HttpServletRequest) request).getServletPath();

			if (/*(url.startsWith("/Exibir") || url.startsWith("/exibir")) && */url.endsWith(".do")) {
				HttpServletRequest requestPagina = ((HttpServletRequest) request);
				HttpSession sessao = requestPagina.getSession(false);

				if (requestPagina.getParameter("menu") != null
						&& requestPagina.getParameter("menu").equals("sim")) {
					Enumeration parametrosSessao = requestPagina.getSession(false)
							.getAttributeNames();

					while (parametrosSessao.hasMoreElements()) {
						String nomeParametro = (String) parametrosSessao
								.nextElement();

						if (nomeParametro.equalsIgnoreCase("menuGCOM")
								|| nomeParametro
										.equalsIgnoreCase("arvoreFuncionalidades")
								|| nomeParametro
										.equalsIgnoreCase("usuarioLogado")
								|| nomeParametro.equalsIgnoreCase("dataAtual")
								|| nomeParametro
										.equalsIgnoreCase("dataUltimoAcesso")
								|| nomeParametro
										.equalsIgnoreCase("colecaoGruposUsuario")	
								|| nomeParametro
										.equalsIgnoreCase("ultimosAcessos")			
								|| nomeParametro
										.equalsIgnoreCase("mensagemExpiracao")		
								|| nomeParametro
										.equalsIgnoreCase("org.apache.struts.action.LOCALE")
								|| nomeParametro
										.equalsIgnoreCase("gisHelper")
								|| nomeParametro
										.equalsIgnoreCase("gis")) {

							continue;
						} else {
							sessao.removeAttribute(nomeParametro);
						}
					}
				}
			}

			filterChain.doFilter(request, response);
		} catch (ServletException sx) {
			throw sx;
		} catch (IOException iox) {
			throw iox;
		}
	}

	// Clean up resources
	/**
	 * <<Descrição do método>>
	 */
	public void destroy() {
	}

	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}
}
