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
package gcom.gui.cadastro.tarifasocial;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovelSimplificado;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.tarifasocial.FiltroTarifaSocialDadoEconomia;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author thiago toscano
 */
public class ExibirAtualizarDadosTarifaSocialAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	HttpSession sessao = httpServletRequest.getSession(false);
        ActionForward retorno = actionMapping.findForward("exibirAtualizarDadosTarifaSocial");
        ExibirAtualizarDadosTarifaSocialActionForm form = (ExibirAtualizarDadosTarifaSocialActionForm) actionForm;
        
        Collection tarifasocialDadoEconomeia = (Collection) sessao.getAttribute("tarifasocialDadoEconomeia");
        // caso na ja exista uma tarifa social dado economia na sessao consulta 
        if (tarifasocialDadoEconomeia == null) {
        	pesquisaTarifasocialDadoEconomeia(form,sessao);
        }
        
        
        // caso ja exista entao apresenta
        return retorno;
    }        
        
    /**
     * Metodo que pesquisa a Tarfia Social Dados Economia e o cliente de um imovel e os colocam na sessao
     *  
     * @author thiago toscano
     * @date 15/12/2005
     * @param form
     * @param sessao
     */
    public void pesquisaTarifasocialDadoEconomeia(ExibirAtualizarDadosTarifaSocialActionForm form,HttpSession sessao) { 
        
        String idRegistroAtualizacao = form.getIdRegistroAtualizacao();

        FiltroTarifaSocialDadoEconomia filtroTarifaSocialDadoEconomia = new FiltroTarifaSocialDadoEconomia();
        filtroTarifaSocialDadoEconomia.adicionarParametro(new ParametroSimples(FiltroTarifaSocialDadoEconomia.IMOVEL_ID, idRegistroAtualizacao));
        filtroTarifaSocialDadoEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroTarifaSocialDadoEconomia.TARIFA_SOCIAL_CARTAO_TIPO);
        filtroTarifaSocialDadoEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroTarifaSocialDadoEconomia.RENDA_TIPO);
        Collection tarifasocialDadoEconomeia  = 
        	this.getFachada().pesquisarTarifaSocialDadoEconomia(filtroTarifaSocialDadoEconomia);
        
        sessao.setAttribute("collTarifaSocialDadoEconomia", tarifasocialDadoEconomeia);

        FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
        filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, idRegistroAtualizacao));
        
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente.unidadeFederacao");
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente.orgaoExpedidorRg");

       Collection collClienteImovel = 
    	   this.getFachada().pesquisarClienteImovel(filtroClienteImovel);

        if (collClienteImovel != null && !collClienteImovel.isEmpty()) {
        	
        	ClienteImovelSimplificado clienteImovel = (ClienteImovelSimplificado) collClienteImovel.iterator().next();
        	
        	FiltroCliente filtroCliente = new FiltroCliente();
        	filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, clienteImovel.getCliente().getId()));
            filtroCliente.adicionarCaminhoParaCarregamentoEntidade("orgaoExpedidorRg");
            filtroCliente.adicionarCaminhoParaCarregamentoEntidade("unidadeFederacao");
            Collection collCliente = this.getFachada().pesquisarCliente(filtroCliente);
            
            if (collCliente != null && !collCliente.isEmpty()) {
            	Cliente cliente = ((Cliente) collCliente.iterator().next());
            	sessao.setAttribute("cliente", cliente);
            }	
        }
    }
}