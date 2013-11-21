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

import gcom.fachada.Fachada;
import gcom.gui.WizardAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Fernanda Paiva
 */
public class ConsultarDadosDiariosArrecadacaoWizardAction extends WizardAction {
	
	@Override
	public ActionForward execute(
			ActionMapping arg0, 
			ActionForm arg1, 
			HttpServletRequest arg2, 
			HttpServletResponse arg3) throws Exception {
		
        // Verificamos se o processo de dados diários da arrecadação está rodando
        Fachada.getInstancia().verificarBatchDadosDiariosArracadacaoRodando();
        
		return super.execute(arg0, arg1, arg2, arg3);
	}

    /**
     * Description of the Method
     */
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
    public ActionForward exibirConsultarDadosDiariosParametrosAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        return new ExibirConsultarDadosDiariosParametrosAction().execute(
                actionMapping, actionForm, httpServletRequest,
                httpServletResponse);
    }

    public ActionForward exibirConsultarDadosDiariosGerenciaAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        return new ExibirConsultarDadosDiariosGerenciaAction().execute(
                actionMapping, actionForm, httpServletRequest,
                httpServletResponse);
    }

    public ActionForward exibirConsultarDadosDiariosArrecadadorAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        return new ExibirConsultarDadosDiariosArrecadadorAction().execute(
                actionMapping, actionForm, httpServletRequest,
                httpServletResponse);
    }

    public ActionForward exibirConsultarDadosDiariosCategoriaAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        return new ExibirConsultarDadosDiariosCategoriaAction().execute(
                actionMapping, actionForm, httpServletRequest,
                httpServletResponse);
    }

    public ActionForward exibirConsultarDadosDiariosPerfilAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        return new ExibirConsultarDadosDiariosPerfilAction().execute(
                actionMapping, actionForm, httpServletRequest,
                httpServletResponse);
    }

    public ActionForward exibirConsultarDadosDiariosDocumentoAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        return new ExibirConsultarDadosDiariosDocumentoAction().execute(
                actionMapping, actionForm, httpServletRequest,
                httpServletResponse);
    }
}
