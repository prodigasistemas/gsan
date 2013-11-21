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
package gcom.gui.cadastro.imovel;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltroDadosTarifaSocial extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
		ActionForward retorno = actionMapping
        .findForward("retornarFiltroDadosTarifaSocial");
		
		 //obtendo uma instancia da sessao
        //HttpSession sessao = httpServletRequest.getSession(false);
        //DynaValidatorActionForm filtrarImovelActionForm = (DynaValidatorActionForm) actionForm;
        
        /*String tarifaSocialExclusaoMotivoId= (String) filtrarImovelActionForm
        .get("tarifaSocialExclusaoMotivo");
        String tarifaSocialTipoCartaoId= (String) filtrarImovelActionForm
        .get("tarifaSocialExclusaoMotivo");
        String tarifaSocialRendaTipoId= (String) filtrarImovelActionForm
        .get("tarifaSocialExclusaoMotivo");
        String periodoValidadeCartaoInicial= (String) filtrarImovelActionForm
        .get("periodoValidadeCartaoInicial");
        String periodoValidadeCartaoFinal= (String) filtrarImovelActionForm
        .get("periodoValidadeCartaoFinal");
        String periodoRecadastramentoInicial= (String) filtrarImovelActionForm
        .get("periodoRecadastramentoInicial");
        String periodoRecadastramentoFinal= (String) filtrarImovelActionForm
        .get("periodoRecadastramentoFinal");
        String rendaFamiliar= (String) filtrarImovelActionForm
        .get("rendaFamiliar");
        String numeroMesesAdesao= (String) filtrarImovelActionForm
        .get("numeroMesesAdesao");
        String numeroRecadastramentoInicial= (String) filtrarImovelActionForm
        .get("numeroRecadastramentoInicial");
        String numeroRecadastramentoFinal= (String) filtrarImovelActionForm
        .get("numeroRecadastramentoFinal");
        String consumoCelpe= (String) filtrarImovelActionForm
        .get("consumoCelpe");
        String implantacaoInicial= (String) filtrarImovelActionForm
        .get("implantacaoInicial");
        String implantacaoFinal= (String) filtrarImovelActionForm
        .get("implantacaoFinal");
        String periodoExclusaoInicial= (String) filtrarImovelActionForm
        .get("periodoExclusaoInicial");
        String periodoExclusaoFinal= (String) filtrarImovelActionForm
        .get("periodoExclusaoFinal");*/
        
        
        
        return retorno;
	}
}
       
       
