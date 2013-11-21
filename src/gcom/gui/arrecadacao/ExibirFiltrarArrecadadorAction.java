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
package gcom.gui.arrecadacao;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * [UC0536]FILTRAR FERIADO
 * 
 * @author Kássia Albuquerque
 * @date 19/01/2006
 */



public class ExibirFiltrarArrecadadorAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("filtrarArrecadador");

		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarArrecadadorActionForm filtrarArrecadadorActionForm = (FiltrarArrecadadorActionForm) actionForm;
		
		//	Código para checar ou não o ATUALIZAR
		
		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			filtrarArrecadadorActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
			
		}		

		String idCliente = filtrarArrecadadorActionForm.getIdCliente();
		String idImovel =  filtrarArrecadadorActionForm.getIdImovel();
		
		// Verificar se o número do cliente não está cadastrado
		if (idCliente != null && !idCliente.trim().equals("")) {

			// Filtro para descobrir id do Cliente
			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(
				new ParametroSimples(
						FiltroCliente.ID, 
						idCliente));

			Collection colecaoCliente = fachada.pesquisar(filtroCliente, Cliente.class.getName());
			
			if (colecaoCliente == null || colecaoCliente.isEmpty()) {
				filtrarArrecadadorActionForm.setIdCliente("");
				filtrarArrecadadorActionForm.setNomeCliente( "CLIENTE INEXISTENTE" );
				httpServletRequest.setAttribute("existeCliente","exception");
				httpServletRequest.setAttribute("nomeCampo","idCliente");
			}else{
				Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(colecaoCliente);
				filtrarArrecadadorActionForm.setIdCliente(cliente.getId().toString());
				filtrarArrecadadorActionForm.setNomeCliente(cliente.getNome());
				httpServletRequest.setAttribute("nomeCampo","idCliente");
			}
		}

		// Verifica se o id do imovel não está cadastrado
		if (idImovel != null && !idImovel.trim().equals("")) {

			// Filtro para descobrir id do Imovel
			FiltroImovel filtroImovel = new FiltroImovel();

			filtroImovel.adicionarParametro(
				new ParametroSimples(
						FiltroImovel.ID, 
						idImovel));

			Collection colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());
	
			if (colecaoImovel == null || colecaoImovel.isEmpty()) {
				filtrarArrecadadorActionForm.setIdImovel(""); 
				filtrarArrecadadorActionForm.setInscricaoImovel( "IMOVEL INEXISTENTE" );
				httpServletRequest.setAttribute("existeImovel","exception");
				httpServletRequest.setAttribute("nomeCampo","idImovel");
			}else{
				Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);
				filtrarArrecadadorActionForm.setIdImovel(imovel.getId().toString());
				String inscricaoImovel = fachada.pesquisarInscricaoImovel(imovel.getId()); 
				filtrarArrecadadorActionForm.setInscricaoImovel(inscricaoImovel);
				httpServletRequest.setAttribute("nomeCampo","idImovel");
			}
		}
		
		return retorno;
	}
}
