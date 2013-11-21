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

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Action ConsultarRelacaoClienteImovelAction
 *
 * @author thiago toscano
 * @date 10/03/2006
 */
public class ConsultarRelacaoClienteImovelAction  extends GcomAction {

	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse httpServletResponse) {

        ConsultarRelacaoClienteImovelActionForm form = (ConsultarRelacaoClienteImovelActionForm) actionForm;
        
        validarPeriodosInformados(form);

        setarAtributosNaSessao(request, form);

        if ( Util.verificarNaoVazio(form.getIdCliente()) ) {
        	return actionMapping.findForward("cliente");
        	
        }
        
    	return actionMapping.findForward("imovel");
    }

	/**
	 *Método coloca alguns atributos na sessão para uso posterior
	 *dos actions que vão exibir os dados da consulta.
	 *
	 *@since 11/09/2009
	 *@author Marlon Patrick
	 */
	private void setarAtributosNaSessao(HttpServletRequest request,
			ConsultarRelacaoClienteImovelActionForm form) {
		
		HttpSession sessao = request.getSession(false);

        sessao.setAttribute("manterDadosSesao", "true");
        sessao.setAttribute("idCliente", form.getIdCliente());
        sessao.setAttribute("idImovel", form.getIdImovel());
        sessao.setAttribute("periodoInicialDataInicioRelacao", form.getPeriodoInicialDataInicioRelacao());
        sessao.setAttribute("periodoFinalDataInicioRelacao", form.getPeriodoFinalDataInicioRelacao());
        sessao.setAttribute("periodoInicialDataFimRelacao", form.getPeriodoInicialDataFimRelacao());
        sessao.setAttribute("periodoFinalDataFimRelacao", form.getPeriodoFinalDataFimRelacao());
        sessao.setAttribute("idClienteImovelFimRelacaoMotivo", form.getIdClienteImovelFimRelacaoMotivo());
        sessao.setAttribute("idClienteRelacaoTipo", form.getIdClienteRelacaoTipo());
        sessao.setAttribute("situacaoRelacao", form.getSituacaoRelacao());
	}

	/**
	 *Este método valida os periodos de datas informados pelo usuario.
	 *Se alguma data inicial for maior que uma data final então uma exceção
	 *será lançada.
	 *
	 *@since 11/09/2009
	 *@author Marlon Patrick
	 */
	private void validarPeriodosInformados(ConsultarRelacaoClienteImovelActionForm form) {

		if ( Util.verificarNaoVazio(form.getPeriodoFinalDataInicioRelacao()) ) {
        	if ( Util.verificarNaoVazio(form.getPeriodoFinalDataInicioRelacao())) {
        		Date periodoInicialDataInicioRelacao = Util.converteStringParaDate(form.getPeriodoInicialDataInicioRelacao());
        		Date periodoFinalDataInicioRelacao = Util.converteStringParaDate(form.getPeriodoFinalDataInicioRelacao());

        		if (periodoInicialDataInicioRelacao.compareTo(periodoFinalDataInicioRelacao) > 0) {
        			throw new ActionServletException("atencao.data.inicio.posterior.data.fim", null, "Início");
        		}
        
        	}
        }
        
        if (Util.verificarNaoVazio(form.getPeriodoInicialDataFimRelacao())) {
        	if (Util.verificarNaoVazio(form.getPeriodoFinalDataFimRelacao())) {
        		Date periodoInicialDataFimRelacao = Util.converteStringParaDate(form.getPeriodoInicialDataFimRelacao());
        		Date periodoFinalDataFimRelacao = Util.converteStringParaDate(form.getPeriodoFinalDataFimRelacao());

        		if (periodoInicialDataFimRelacao.compareTo(periodoFinalDataFimRelacao) > 0) {
        			throw new ActionServletException("atencao.data.inicio.posterior.data.fim", null, "Fim");
        		}
        	
        	}
        }
	}
}