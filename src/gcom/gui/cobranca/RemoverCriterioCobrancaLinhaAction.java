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
package gcom.gui.cobranca;

import gcom.cobranca.CobrancaCriterioLinha;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * processamento para remover a linha do criterio da cobrança
 * 
 * @author Sávio Luiz
 * @date 03/05/2006
 */
public class RemoverCriterioCobrancaLinhaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = null;

		HttpSession sessao = httpServletRequest.getSession(false);
		
		//CriterioCobrancaActionForm criterioCobrancaActionForm = (CriterioCobrancaActionForm) actionForm;


		String tipoRetorno = httpServletRequest.getParameter("tipoRetorno");
		if (tipoRetorno != null && !tipoRetorno.equals("")) {
			// se o tipo de retorno for igual a inserir
			// retorna para o jsp de inserir criterio cobranca
			if (tipoRetorno.equals("inserir")) {
				retorno = actionMapping.findForward("inserirCriterioCobranca");
			}
			// se o tipo de retorno for igual a atualizar
			// retorna para o jsp de atualizar criterio cobranca
			if (tipoRetorno.equals("atualizar")) {
				retorno = actionMapping
						.findForward("atualizarCriterioCobranca");
			}
		}

		Collection colecaoCobrancaCriterioLinha = (Collection) sessao
				.getAttribute("colecaoCobrancaCriterioLinha");

		Collection colecaoCobrancaCriterioLinhaRemovidas = null;
		if (sessao.getAttribute("colecaoCobrancaCriterioLinhaRemovidas") != null
				&& !sessao
						.getAttribute("colecaoCobrancaCriterioLinhaRemovidas")
						.equals("")) {
			colecaoCobrancaCriterioLinhaRemovidas = (Collection) sessao
					.getAttribute("colecaoCobrancaCriterioLinhaRemovidas");
		} else {
			colecaoCobrancaCriterioLinhaRemovidas = new ArrayList();
		}

		Iterator iteratorCobrancaCriterioLinha = colecaoCobrancaCriterioLinha
				.iterator();
		String codigoCriterioCobrancaLinha = httpServletRequest
				.getParameter("codigoCobrancaCriterioLinha");
		
		String[] arrayCodigo = codigoCriterioCobrancaLinha.split(",");
		String codigoImovelPerfil = arrayCodigo[0];
		String codigoCategoria = arrayCodigo[1];
		
		while (iteratorCobrancaCriterioLinha.hasNext()) {
			CobrancaCriterioLinha cobrancaCriterioLinha = (CobrancaCriterioLinha) iteratorCobrancaCriterioLinha
					.next();
			if (cobrancaCriterioLinha.getImovelPerfil().getId().equals(new Integer(codigoImovelPerfil))
					&& cobrancaCriterioLinha.getCategoria().getId().equals(new Integer(codigoCategoria))) {
				iteratorCobrancaCriterioLinha.remove();
				if (cobrancaCriterioLinha.getId() != null
						&& !cobrancaCriterioLinha.getId().equals("")) {
					colecaoCobrancaCriterioLinhaRemovidas
							.add(cobrancaCriterioLinha);
				}

			}
		}

		sessao.setAttribute("colecaoCobrancaCriterioLinha",
				colecaoCobrancaCriterioLinha);
		sessao.setAttribute("colecaoCobrancaCriterioLinhaRemovidas",
				colecaoCobrancaCriterioLinhaRemovidas);

		return retorno;
	}
}
