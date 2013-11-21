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
package gcom.gui.faturamento;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Adicionar Guia Pagamento Item Popup
 * 
 * @author Flávio Leonardo
 * @created 25/04/2006
 */
public class ExibirAdicionarFaturaClienteResponsavelContaPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("adicionarFaturaClienteResponsavelContaPopup");

		Fachada fachada = Fachada.getInstancia();

		// Instância do formulário que está sendo utilizado
		FiltrarFaturaClienteResponsavelActionForm form = (FiltrarFaturaClienteResponsavelActionForm) actionForm;		

		if( ( form.getImovelId() != null && !form.getImovelId().equals("") ) ||
			( httpServletRequest.getParameter( "idCampoEnviarDados" ) != null &&
			  !httpServletRequest.getParameter( "idCampoEnviarDados" ).equals( "" )  )	){
			
			Imovel imovelEncontrado = 
				this.pesquisarImovel( 
						( form.getImovelId() != null ? 
						  form.getImovelId() : 
						  httpServletRequest.getParameter( "idCampoEnviarDados" ).toString() ) );
			
			if(imovelEncontrado != null){
				form.setInscricao(fachada.pesquisarInscricaoImovel(imovelEncontrado.getId()));
				form.setImovelId( imovelEncontrado.getId()+"" );
			}else{
				form.setInscricao("Imóvel Inexistente");
	    		httpServletRequest.setAttribute("imovelInexistente", "s");
			}
		}
		
		return retorno;
	}
	
	private Imovel pesquisarImovel(String idImovel){
		
		//Cria a variável que vai armazenar o cliente pesquisado
		Imovel imovelEncontrado = null;
		
		//Cria uma instância da fachada 
		Fachada fachada = Fachada.getInstancia();
		
		//Pesquisa o cliente informado pelo usuário no sistema 
		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));
		Collection<Imovel> colecaoImovel =  fachada.pesquisar(filtroImovel, Imovel.class.getName());
		
		//Caso exista o cliente no sistema 
		//Retorna para o usuário o cliente retornado pela pesquisa
		//Caso contrário retorna um objeto nulo 
		if(colecaoImovel != null && !colecaoImovel.isEmpty()){
			imovelEncontrado =(Imovel) Util.retonarObjetoDeColecao(colecaoImovel);
		}
		
		//Retorna o cliente encontrado ou nulo se não existir 
		return imovelEncontrado;
	}
	
}
