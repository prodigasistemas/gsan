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
package gcom.gui.micromedicao;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ControladorException;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Action que define o pré-processamento da página de pesquisa de Localidade
 * 
 * @author Administrador
 * @created 27 de Maio de 2004
 */

public class ExibirAnaliseExcecaoConsumoResumoPopupAction extends GcomAction {
    /**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping
                .findForward("exibirAnaliseExcecaoConsumoResumoPopup");

        //obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);

       // LeituraConsumoActionForm leituraConsumoActionForm = (LeituraConsumoActionForm) actionForm;
        Fachada fachada = Fachada.getInstancia();

        String idImovel = httpServletRequest.getParameter("codigoImovel");
        
        FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
        filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, idImovel));
        filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO, "" + ClienteRelacaoTipo.USUARIO));
        
//        filtroClienteImovel.adicionarParametro( new ParametroSimples(FiltroClienteImovel.DATA_FIM_RELACAO, ""));
        
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");
        
//        Collection colecaoCliente = fachada.pesquisarClienteImovel(filtroClienteImovel);
        
        Collection colecaoCliente = fachada.pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
        
//        ClienteImovelSimplificado clienteImovel = (ClienteImovelSimplificado)colecaoCliente.iterator().next();
        
        ClienteImovel clienteImovel = new ClienteImovel();
        
        String nomeCliente = null;
        
        Iterator iteClieImovel = colecaoCliente.iterator();
        
        while ( iteClieImovel.hasNext() ){
        	
        	clienteImovel = (ClienteImovel)iteClieImovel.next();
        	
        	if ( clienteImovel.getDataFimRelacao() == null || clienteImovel.equals("") ){
        		
        		nomeCliente = clienteImovel.getCliente().getNome();
                sessao.setAttribute("nomeCliente", nomeCliente);
                       		
        	}
        
        }
        
//        ClienteImovel clienteImovel = (ClienteImovel)colecaoCliente.iterator().next();
        
        String imovelEndereco;
        
		try {
			imovelEndereco = fachada.pesquisarEnderecoFormatado(new Integer(idImovel));
	        sessao.setAttribute("imovelEndereco", imovelEndereco);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ControladorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return retorno;
    }

}
