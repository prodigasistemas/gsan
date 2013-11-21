/**
 * 
 */
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
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Marcio Roberto
 * @date 25/01/2007
 */
public class InserirArrecadadorAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de um novo Arrecadador
	 * 
	 * [UC0381] Inserir Arrecadador
	 * 
	 * 
	 * @author Marcio Roberto
	 * @date 25/01/2007
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirArrecadadorActionForm inserirArrecadadorActionForm = (InserirArrecadadorActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		sessao.setAttribute("caminhoRetornoVoltar",
				"/gsan/exibirInserirArrecadadorAction.do");

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// Validamos o cliente
		FiltroCliente filtro = new FiltroCliente();
				
		filtro.adicionarParametro( new ParametroSimples(
				FiltroCliente.ID, inserirArrecadadorActionForm.getIdCliente() ) );
		
		Collection colCliente = fachada.pesquisar( filtro, Cliente.class.getName() );
		
		if ( colCliente == null || !colCliente.iterator().hasNext() ){
			// O cliente não existe
			throw new ActionServletException("atencao.cliente.inexistente",
					null, "Cliente");
		}
	
		String inscricaoEstadual = inserirArrecadadorActionForm.getInscricaoEstadual();
        
		
			//Inscricao Estadual
		if(!inserirArrecadadorActionForm.getInscricaoEstadual().trim().equals("") && inserirArrecadadorActionForm.getInscricaoEstadual() != null){
        	inserirArrecadadorActionForm.setInscricaoEstadual(inscricaoEstadual);
        	
        } else {
        	inscricaoEstadual = null;
        	inserirArrecadadorActionForm.setInscricaoEstadual(inscricaoEstadual);
        }
		
		if(inserirArrecadadorActionForm.getIdImovel() != null &&
				!inserirArrecadadorActionForm.getIdImovel().equals("")){
			// Validamos o imovel
			FiltroImovel filtroImovel = new FiltroImovel();
					
			filtroImovel.adicionarParametro( new ParametroSimples(
					FiltroImovel.ID, inserirArrecadadorActionForm.getIdImovel() ) );
			
			Collection colImovel = fachada.pesquisar( filtroImovel, Imovel.class.getName() );
			
			if ( colImovel == null || colImovel.isEmpty() ){
				// O Imovel não existe
				throw new ActionServletException("atencao.imovel.inexistente",
						null, "Imovel");
			}
           
		}
      
		
		Integer idArrecadador = fachada.inserirArrecadador(
				inserirArrecadadorActionForm.getIdAgente(), 
				inserirArrecadadorActionForm.getIdCliente(), 
				inserirArrecadadorActionForm.getInscricaoEstadual(), 
				inserirArrecadadorActionForm.getIdImovel(), usuario);
		
		String idRegistroAtualizacao = idArrecadador.toString();
		sessao.setAttribute("idRegistroAtualizacao",idRegistroAtualizacao);

		// Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Arrecadador de código " + idArrecadador
				+ " inserido com sucesso.", "Inserir outro Arrecadador",
				"exibirInserirArrecadadorAction.do?menu=sim",
				"exibirAtualizarArrecadadorAction.do?idRegistroInseridoAtualizar="
						+ idArrecadador, "Atualizar Arrecadador Inserido");

		sessao.removeAttribute("InserirArrecadadorActionForm");

		return retorno;
	}

}