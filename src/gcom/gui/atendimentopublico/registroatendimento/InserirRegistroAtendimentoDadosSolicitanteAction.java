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
package gcom.gui.atendimentopublico.registroatendimento;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;

/**
 * Esta classe tem por finalidade validar as informações da terceira aba do processo de inserção
 * de um registro de atendimento
 *
 * @author Raphael Rossiter
 * @date 25/07/2006
 */
public class InserirRegistroAtendimentoDadosSolicitanteAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("");
        
        Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		sessao.removeAttribute("gis");	
		
		InserirRegistroAtendimentoActionForm form = 
	    (InserirRegistroAtendimentoActionForm) actionForm;
		
		String nomeSolicitante = null;
		if (form.getNomeSolicitante() != null && 
			!form.getNomeSolicitante().equalsIgnoreCase("")){
			nomeSolicitante = form.getNomeSolicitante();
		}
		
		Collection colecaoEndereco = null;
		if (sessao.getAttribute("colecaoEnderecosAbaSolicitante") != null){
			colecaoEndereco = (Collection) sessao.getAttribute("colecaoEnderecosAbaSolicitante");
		}
		
		Collection colecaoFone = null;
		if (sessao.getAttribute("colecaoFonesAbaSolicitante") != null){
			colecaoFone = (Collection) sessao.getAttribute("colecaoFonesAbaSolicitante");
		}
		
		Short indicadorClienteEspecificacao = null;
		if (form.getIndicadorClienteEspecificacao() != null && 
			!form.getIndicadorClienteEspecificacao().equalsIgnoreCase("")){
			indicadorClienteEspecificacao = new Short(form.getIndicadorClienteEspecificacao());
		}
		
		//[FS0030] - Verificar preenchimento dos dados de identificação do solicitante
		fachada.verificaDadosSolicitante(Util.converterStringParaInteger(form.getIdCliente()), 
		Util.converterStringParaInteger(form.getIdUnidadeSolicitante()),
		Util.converterStringParaInteger(form.getIdFuncionarioResponsavel()), nomeSolicitante,
		colecaoEndereco, colecaoFone, indicadorClienteEspecificacao, 
		Util.converterStringParaInteger(form.getIdImovel()), null,
		Util.converterStringParaInteger(form.getEspecificacao()),
		Util.converterStringParaInteger(form.getMeioSolicitacao()) );
		
        return retorno;
	}

}
