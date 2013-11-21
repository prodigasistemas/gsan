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
package gcom.gui;

import gcom.cadastro.DbVersaoBase;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.util.Internacionalizador;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.Util;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.PropertyMessageResources;

public class CarregarParametrosAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaLogin");

		Fachada fachada = Fachada.getInstancia();

		// instacia a variavel de aplicacao tituloPagina com o valor cadastrado
		// em sistema parametro.
		if ( !Util.verificarNaoVazio((String)servlet.getServletContext().getAttribute("tituloPagina"))) {

			// Recupera o objeto que contém os parâmetros do sistema
			SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
			
			servlet.getServletContext().setAttribute("tituloPagina",sistemaParametro.getTituloPagina());
			servlet.getServletContext().setAttribute("logoMarca",sistemaParametro.getImagemLogomarca());
			servlet.getServletContext().setAttribute("nomeEmpresa",sistemaParametro.getNomeAbreviadoEmpresa());

			if( Util.verificarNaoVazio(sistemaParametro.getUrlhelp())){
				servlet.getServletContext().setAttribute("urlHelp",sistemaParametro.getUrlhelp());
			}
			if( Util.verificarNaoVazio(sistemaParametro.getIndicadorSenhaForte().toString())){
				servlet.getServletContext().setAttribute("indicadorSenhaForte",sistemaParametro.getIndicadorSenhaForte().toString());
			}
		}

		if ( !Util.verificarNaoVazio((String)servlet.getServletContext().getAttribute("rodapePagina"))) {

			// Recupera o objeto que contém as datas de Implementacao e
			// alteracao do Banco			
			DbVersaoBase dbVersaoBase = fachada.pesquisarDbVersaoBase();

			if ( dbVersaoBase != null ) {

				String versaoDataBase = Util.formatarData(dbVersaoBase.getVersaoDataBase());

				servlet.getServletContext().setAttribute("versaoDataBase",versaoDataBase);
			}
			
			try {
				
				if (ServiceLocator.getResource("java:/BatchDS") != null) {
					
					servlet.getServletContext().setAttribute("versaoTipo", "Batch");
				}
				else{
					
					servlet.getServletContext().setAttribute("versaoTipo", "Online");
				}
			} 
			catch (ServiceLocatorException e) {
				
				e.printStackTrace();
			}
		}

		
		Internacionalizador.setLocale(
				(Locale)httpServletRequest.getSession(false).getAttribute(Globals.LOCALE_KEY));
		
		Internacionalizador.setProperties(
				(PropertyMessageResources)servlet.getServletContext().getAttribute(Globals.MESSAGES_KEY));

		return retorno;

	}
}
