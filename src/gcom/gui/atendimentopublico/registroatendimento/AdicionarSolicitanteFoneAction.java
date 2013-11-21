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

import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.FoneTipo;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * adiciona o telefona ao solicitante
 * 
 * @author Sávio Luiz
 * @date 31/08/2006
 */
public class AdicionarSolicitanteFoneAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirAdicionarSolicitante");

		HttpSession sessao = httpServletRequest.getSession(false);

		AdicionarSolicitanteRegistroAtendimentoActionForm adicionarSolicitanteRegistroAtendimentoActionForm = (AdicionarSolicitanteRegistroAtendimentoActionForm) actionForm;

		Collection colecaoFonesSolicitante = null;
		if (sessao.getAttribute("caminhoRetornoTelaAdicionarFone") != null){
			colecaoFonesSolicitante = (Collection) sessao
			.getAttribute("colecaoFonesAbaSolicitante");
		}
		else{
			colecaoFonesSolicitante = (Collection) sessao
			.getAttribute("colecaoFonesSolicitante");
		}
		
		
		boolean primeiro = false;
		if (colecaoFonesSolicitante == null
				|| colecaoFonesSolicitante.isEmpty()) {
			colecaoFonesSolicitante = new ArrayList();
			primeiro = true;
		}

		ClienteFone clienteFone = new ClienteFone();
		// seta o id e a descrição do fone tipo
		FoneTipo foneTipo = new FoneTipo();
		Object[] parmsFoneTipo = adicionarSolicitanteRegistroAtendimentoActionForm
				.getIdTipoTelefone().split(";");
		foneTipo.setId(new Integer((String) parmsFoneTipo[0]));
		foneTipo.setDescricao((String) parmsFoneTipo[1]);
		clienteFone.setFoneTipo(foneTipo);

		// seta o DDD
		clienteFone.setDdd(adicionarSolicitanteRegistroAtendimentoActionForm
				.getDddTelefone());

		// seta o número do telefone
		clienteFone
				.setTelefone(adicionarSolicitanteRegistroAtendimentoActionForm
						.getNumeroTelefone());
		// seta o ramal do telefone
		clienteFone.setRamal(adicionarSolicitanteRegistroAtendimentoActionForm
				.getRamal());
		
		if (primeiro) {
			clienteFone.setIndicadorTelefonePadrao(new Short("1"));
		}
		else{
			clienteFone.setIndicadorTelefonePadrao(new Short("2"));
		}
		
		//UltimaAlteracao para facilitar a identificacao do objeto
		clienteFone.setUltimaAlteracao(new Date());
		
		colecaoFonesSolicitante.add(clienteFone);
		
		
		//URL de retorno
		if (sessao.getAttribute("caminhoRetornoTelaAdicionarFone") != null){
			retorno = actionMapping.findForward("exibirAdicionarSolicitanteProcessoRA");
			httpServletRequest.setAttribute("telaRetorno", sessao.getAttribute("caminhoRetornoTelaAdicionarFone"));
			sessao.setAttribute("colecaoFonesAbaSolicitante", colecaoFonesSolicitante);
		
		}
		else if (sessao.getAttribute("caminhoRetornoTelaAdicionarFonePopUp") != null){
			retorno = actionMapping.findForward("exibirAdicionarSolicitanteProcessoRA");
			httpServletRequest.setAttribute("telaRetornoPopUp", sessao.getAttribute("caminhoRetornoTelaAdicionarFonePopUp"));
			sessao.setAttribute("colecaoFonesSolicitante", colecaoFonesSolicitante);
		}
		else if (sessao.getAttribute("caminhoRetornoTelaAdicionarFoneReiterar") != null){
			retorno = actionMapping.findForward("exibirAdicionarSolicitanteProcessoRA");
			httpServletRequest.setAttribute("telaRetorno", sessao.getAttribute("caminhoRetornoTelaAdicionarFoneReiterar"));
			sessao.setAttribute("colecaoFonesSolicitante", colecaoFonesSolicitante);
		}
		else{
			sessao.setAttribute("colecaoFonesSolicitante", colecaoFonesSolicitante);
		}

		
		return retorno;

	}

}
