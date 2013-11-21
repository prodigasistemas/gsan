/**
 * 
 */
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
package gcom.gui.atendimentopublico;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.EmitirContaHelper;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 22/01/2007
 */
public class ExibirSelecionarBancoAction extends GcomAction {
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirSelecionarBancoAction");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		String idConta = httpServletRequest.getParameter("idConta");

		sessao.setAttribute("idConta", idConta);

		Collection idsContaEP = new ArrayList();
		if (httpServletRequest.getParameter("idConta") != null
				&& !httpServletRequest.getParameter("idConta").equals("")) {
			idsContaEP.add(new Integer(""
					+ httpServletRequest.getParameter("idConta")));
		}

		Collection<EmitirContaHelper> colecaoEmitirContaHelper = fachada
				.emitir2ViaContas(idsContaEP, false,ConstantesSistema.NAO);

		EmitirContaHelper emitirContaHelper = colecaoEmitirContaHelper
				.iterator().next();
		
		/*
		 * Caso o documento selecionado seja uma ficha de compensação, valor maior que mil reais,
		 * o sistema irá chamar a tela inicial do banco.
		 */
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		
		if (sistemaParametro.getValorContaFichaComp() != null){
			
			if (emitirContaHelper.getValorConta().compareTo(sistemaParametro.getValorContaFichaComp()) <= 0){
				
				String representacaoNumericaCodBarraFormatada = emitirContaHelper
				.getRepresentacaoNumericaCodBarraFormatada();

				// Cortar a representacaoNumericaCodBarraFormatada separando de 12 em
				// 12, mas tirando os espacos e traços
			
				String cAux1 = representacaoNumericaCodBarraFormatada.substring(0, 10)
						+ representacaoNumericaCodBarraFormatada.substring(12, 13);
			
				String cAux2 = representacaoNumericaCodBarraFormatada.substring(14, 25)
						+ representacaoNumericaCodBarraFormatada.substring(26, 27);
			
				String cAux3 = representacaoNumericaCodBarraFormatada.substring(28, 39)
						+ representacaoNumericaCodBarraFormatada.substring(40, 41);
			
				String cAux4 = representacaoNumericaCodBarraFormatada.substring(42, 53)
						+ representacaoNumericaCodBarraFormatada.substring(54, 55);
				//		
				// String cAux2 =
				// String cAux3 =
				// String cAux4 =
				// codigo de barras passado pela 2. via de conta
				String codigo = cAux1 + cAux2 + cAux3 + cAux4;
			
				httpServletRequest.setAttribute("codigo", codigo);
				httpServletRequest.setAttribute("fichaCompensacao", "2");
			}
			else{
				
				httpServletRequest.setAttribute("fichaCompensacao", "1");
			}
		}
		else{
			
			String representacaoNumericaCodBarraFormatada = emitirContaHelper
			.getRepresentacaoNumericaCodBarraFormatada();

			// Cortar a representacaoNumericaCodBarraFormatada separando de 12 em
			// 12, mas tirando os espacos e traços
		
			String cAux1 = representacaoNumericaCodBarraFormatada.substring(0, 10)
					+ representacaoNumericaCodBarraFormatada.substring(12, 13);
		
			String cAux2 = representacaoNumericaCodBarraFormatada.substring(14, 25)
					+ representacaoNumericaCodBarraFormatada.substring(26, 27);
		
			String cAux3 = representacaoNumericaCodBarraFormatada.substring(28, 39)
					+ representacaoNumericaCodBarraFormatada.substring(40, 41);
		
			String cAux4 = representacaoNumericaCodBarraFormatada.substring(42, 53)
					+ representacaoNumericaCodBarraFormatada.substring(54, 55);
			//		
			// String cAux2 =
			// String cAux3 =
			// String cAux4 =
			// codigo de barras passado pela 2. via de conta
			String codigo = cAux1 + cAux2 + cAux3 + cAux4;
		
			httpServletRequest.setAttribute("codigo", codigo);
			httpServletRequest.setAttribute("fichaCompensacao", "2");
		}
		

		return retorno;

	}
}
