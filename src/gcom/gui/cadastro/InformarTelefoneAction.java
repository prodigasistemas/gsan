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
package gcom.gui.cadastro;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroFoneTipo;
import gcom.cadastro.cliente.FoneTipo;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InformarTelefoneAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("informarTelefoneAction");

		HttpSession sessao = getSessao(httpServletRequest);
		
		// Obtém a instância da fachada
		sessao.removeAttribute("ConsultarImovelActionForm");

		InformarTelefoneActionForm informarTelefoneActionForm = 
			(InformarTelefoneActionForm) actionForm;
				
        String ddd = informarTelefoneActionForm.getDdd();
        
        if(this.isDddMunicipioValido(ddd)){
        	
        	ClienteFone clienteFone = new ClienteFone();
        	
        	Cliente cliente = (Cliente)this.pesquisarCliente(informarTelefoneActionForm.getIdCliente());
        	
        	clienteFone.setCliente(cliente);
        	clienteFone.setContato(informarTelefoneActionForm.getNomeContato());
        	clienteFone.setDdd(informarTelefoneActionForm.getDdd());

        	FoneTipo foneTipo = new FoneTipo();
        	foneTipo = (FoneTipo) this.pesquisarFoneTipo(informarTelefoneActionForm.getTipoTelefone());

        	clienteFone.setFoneTipo(foneTipo);
        	clienteFone.setIndicadorTelefonePadrao(Short.valueOf(informarTelefoneActionForm.getTelefonePrincipal()) );
        	clienteFone.setRamal(informarTelefoneActionForm.getRamal());
        	clienteFone.setTelefone(informarTelefoneActionForm.getNumeroTelefone());
        	clienteFone.setUltimaAlteracao(new Date());
        	
        	//fachada.inserir(clienteFone);
        	
        	//verifica se o id do imovel estiver sido informado
        	String idImovel = (String) informarTelefoneActionForm.getIdImovel();
        	if(idImovel != null & !idImovel.equals("")){
        		sessao.setAttribute("idImovel", idImovel);
    		}


        	sessao.setAttribute("clienteFoneAdicionar", clienteFone);
        	
        	
        }else{
        	throw new ActionServletException("atencao.ddd_municipio.inexistente");
        }        
	    
		return retorno;
	}
	
	
	private boolean isDddMunicipioValido(String ddd){
		
		boolean isValido = false;
		
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
		filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.DDD, ddd));
		
		Collection colecao = this.getFachada().pesquisar(filtroMunicipio, Municipio.class.getName());
		
		if(colecao != null && colecao.size() > 0){
			isValido = true;
		}
		
		return isValido;
	}
	
	private Cliente pesquisarCliente(String idCliente){
		
		FiltroCliente filtroCliente = new FiltroCliente();
		
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, idCliente));
		
		Collection colecaoCliente = this.getFachada().pesquisar(filtroCliente, Cliente.class.getName());
		
		return (Cliente)Util.retonarObjetoDeColecao(colecaoCliente);

	}
	
	private FoneTipo pesquisarFoneTipo(String idFoneTipo){
		
		FiltroFoneTipo filtroFoneTipo = new FiltroFoneTipo();
		
		filtroFoneTipo.adicionarParametro(new ParametroSimples(FiltroFoneTipo.ID, idFoneTipo) );
		
		Collection colecaoFoneTipo = (Collection)this.getFachada().pesquisar(filtroFoneTipo, FoneTipo.class.getName());
		
		return (FoneTipo) Util.retonarObjetoDeColecao(colecaoFoneTipo); 
		
	}
	
}