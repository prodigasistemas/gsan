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
* Thiago Vieira
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
package gcom.gui.cobranca.spcserasa;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.spcserasa.FiltroNegativador;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Thiago Vieira
 */
public class FiltrarNegativadorAction extends GcomAction {

    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param actionForm
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     * @param httpServletResponse
     *            Description of the Parameter
     * @return Description of the Return Value
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

//    	 localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("exibirManterNegativador");
		HttpSession sessao = httpServletRequest.getSession(false);

		// Pega o form do cliente
        FiltrarNegativadorActionForm form = (FiltrarNegativadorActionForm) actionForm; 
        
        short codigoAgente = 0;
        Integer clienteId = null;
        Integer imovelId= null; 
        String inscricaoEstadual = null;
        short ativo = 0; 
        String checkAtualizar = "";
        
        if (!form.getCodigoAgente().equals("") && form.getCodigoAgente() != null){
        	codigoAgente = Short.parseShort(form.getCodigoAgente());
        }
        if (!form.getCodigoCliente().equals("") && form.getCodigoCliente() != null){
        	clienteId = new Integer(form.getCodigoCliente());
        }
        if (!form.getCodigoImovel().equals("") && form.getCodigoImovel() != null){
        	imovelId = new Integer(form.getCodigoImovel());
        }
        if (!form.getInscricaoEstadual().equals("") && form.getInscricaoEstadual() != null){
        	inscricaoEstadual = form.getInscricaoEstadual();
        }
        if (!form.getAtivo().equals("") && form.getAtivo() != null){
        	ativo = Short.parseShort(form.getAtivo());
        }
       	
        if (!form.getCheckAtualizar().equals("") && form.getCheckAtualizar() != null){
        	checkAtualizar = form.getCheckAtualizar();
        }
        
		FiltroNegativador filtroNegativador = new FiltroNegativador();
		boolean peloMenosUmParametroInformado = false; 
		
		if (!form.getCodigoAgente().equals("") && form.getCodigoAgente() != null){
			filtroNegativador.adicionarParametro(new ParametroSimples(FiltroNegativador.CODIGO_AGENTE, codigoAgente));
			peloMenosUmParametroInformado = true;
		}
		
		if (!form.getCodigoCliente().equals("") && form.getCodigoCliente() != null){
			filtroNegativador.adicionarParametro(new ParametroSimples(FiltroNegativador.NEGATIVADOR_CLIENTE, clienteId));
			peloMenosUmParametroInformado = true;
		}
		
		if (!form.getCodigoImovel().equals("") && form.getCodigoImovel() != null){
			filtroNegativador.adicionarParametro(new ParametroSimples(FiltroNegativador.NEGATIVADOR_IMOVEL, imovelId));
			peloMenosUmParametroInformado = true;
		}
		
		if (!form.getInscricaoEstadual().equals("") && form.getInscricaoEstadual() != null){
			filtroNegativador.adicionarParametro(new ParametroSimples(FiltroNegativador.INSCRICAO_ESTADUAL, inscricaoEstadual));
			peloMenosUmParametroInformado = true;
		}
		
		if (!form.getAtivo().equals("") && form.getAtivo() != null){
			filtroNegativador.adicionarParametro(new ParametroSimples(FiltroNegativador.INDICADOR_USO, ativo));
			peloMenosUmParametroInformado = true;
		}

		if (!peloMenosUmParametroInformado){
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		
		sessao.setAttribute("filtroNegativador",filtroNegativador);
		sessao.setAttribute("checkAtualizar",checkAtualizar);
		
		return retorno;
        
    }
}