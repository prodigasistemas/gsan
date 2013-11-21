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

import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.cadastro.tarifasocial.TarifaSocialRevisaoMotivo;
import gcom.cadastro.tarifasocial.bean.TarifaSocialHelper;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Rafael Corrêa
 * @since 16/01/2007
 */
public class InserirMotivoRevisaoTarifaSocialAction extends GcomAction {

	/**
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
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("inserir");
		
		// Pega uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);
		
		InserirMotivoRevisaoTarifaSocialActionForm inserirMotivoRevisaoTarifaSocialActionForm = (InserirMotivoRevisaoTarifaSocialActionForm) actionForm;
		
		Imovel imovel = (Imovel) sessao.getAttribute("imovelTarifa");

		TarifaSocialHelper tarifaSocialHelperMotivoRevisao = (TarifaSocialHelper) sessao.getAttribute("tarifaSocialHelperMotivoRevisao");

		TarifaSocialDadoEconomia tarifaSocialDadoEconomia = tarifaSocialHelperMotivoRevisao.getTarifaSocialDadoEconomia();
		
		String idMotivoRevisao = inserirMotivoRevisaoTarifaSocialActionForm.getIdMotivoRevisao();
		
		if (idMotivoRevisao != null && !idMotivoRevisao.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
		
			TarifaSocialRevisaoMotivo tarifaSocialRevisaoMotivo = new TarifaSocialRevisaoMotivo();
		
			tarifaSocialRevisaoMotivo.setId(new Integer(idMotivoRevisao));
		
			tarifaSocialDadoEconomia.setTarifaSocialRevisaoMotivo(tarifaSocialRevisaoMotivo);
		
			tarifaSocialDadoEconomia.setDataRevisao(new Date());
			tarifaSocialDadoEconomia.setTarifaSocialExclusaoMotivo(null);
			tarifaSocialDadoEconomia.setDataExclusao(null);
			
		} else {
			tarifaSocialDadoEconomia.setTarifaSocialRevisaoMotivo(null);
			tarifaSocialDadoEconomia.setDataRevisao(null);
		}
		
		tarifaSocialDadoEconomia.setImovel(imovel);
		
		Collection colecaoTarifaSocialHelperAtualizar = null;
		
		if (sessao.getAttribute("colecaoTarifaSocialHelperAtualizar") != null) {
			colecaoTarifaSocialHelperAtualizar = (Collection) sessao.getAttribute("colecaoTarifaSocialHelperAtualizar"); 
		} else {
			colecaoTarifaSocialHelperAtualizar = new ArrayList();
		}
		
		colecaoTarifaSocialHelperAtualizar.add(tarifaSocialHelperMotivoRevisao);
		
		ArrayList colecaoTarifaSocialHelper = (ArrayList) sessao.getAttribute("colecaoTarifaSocialHelper");
		
		Iterator colecaoTarifaSocialHelperIterator = colecaoTarifaSocialHelper.iterator();
		
		int i = 0;
		
		while (colecaoTarifaSocialHelperIterator.hasNext()) {

			TarifaSocialHelper tarifaSocialHelper = (TarifaSocialHelper) colecaoTarifaSocialHelperIterator
					.next();

			// Uma Economia
			if (tarifaSocialHelperMotivoRevisao.getClienteImovel() != null) {
				colecaoTarifaSocialHelper.set(i, tarifaSocialHelperMotivoRevisao);
				break;
			} 
			
			// Múltiplas Economias
			else if (tarifaSocialHelperMotivoRevisao.getClienteImovelEconomia() != null) {
				if (tarifaSocialHelper.getClienteImovelEconomia()
						.getImovelEconomia().getId().equals(
								tarifaSocialHelperMotivoRevisao
										.getClienteImovelEconomia()
										.getImovelEconomia().getId())) {
					colecaoTarifaSocialHelper.set(i, tarifaSocialHelperMotivoRevisao);
					break;
				}
					
			}
			
			i++;

		}
		
		sessao.setAttribute("colecaoTarifaSocialHelperAtualizar", colecaoTarifaSocialHelperAtualizar);
		sessao.setAttribute("colecaoTarifaSocialHelper", colecaoTarifaSocialHelper);
		sessao.setAttribute("atualizar", true);
		
		sessao.removeAttribute("tarifaSocialHelperMotivoRevisao");
		
		httpServletRequest.setAttribute("fecharPopup", true);

		return retorno;

	}

}
