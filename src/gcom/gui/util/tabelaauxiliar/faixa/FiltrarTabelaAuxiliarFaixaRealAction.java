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
package gcom.gui.util.tabelaauxiliar.faixa;

import gcom.gui.ActionServletException;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.faixa.FiltroTabelaAuxiliarFaixaReal;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * @author Romulo Aurelio
 *
 */
public class FiltrarTabelaAuxiliarFaixaRealAction extends Action {
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

		//Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("retornarFiltroTabelaAuxiliarFaixaReal");

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

		//Recupera os parâmetros do form
		String id = (String) pesquisarActionForm.get("id");
		String volumeMenorFaixa = (String) pesquisarActionForm
				.get("volumeMenorFaixa");
		String volumeMaiorFaixa = (String) pesquisarActionForm
				.get("volumeMaiorFaixa");
		String atualizar = (String) pesquisarActionForm.get("atualizar");

		//cria o filtro para Tabela Auxiliar abreviada
		FiltroTabelaAuxiliarFaixaReal filtroTabelaAuxiliarFaixaReal = new FiltroTabelaAuxiliarFaixaReal();

		//Insere os parâmetros informados no filtro
		if (id != null && !id.trim().equalsIgnoreCase("")) {
			filtroTabelaAuxiliarFaixaReal
					.adicionarParametro(new ParametroSimples(
							FiltroTabelaAuxiliarFaixaReal.ID, id));
		}

		BigDecimal volumeMenorFaixaReal = new BigDecimal(0);
		
		if (volumeMenorFaixa != null
				&& !volumeMenorFaixa.trim().equalsIgnoreCase("")) {


			String valorAux = volumeMenorFaixa.toString().replace(".", "");

			valorAux = valorAux.replace(",", ".");

			volumeMenorFaixaReal = new BigDecimal(valorAux);

			filtroTabelaAuxiliarFaixaReal.adicionarParametro(new MaiorQue(
					FiltroTabelaAuxiliarFaixaReal.VOLUME_MENOR_FAIXA,
					volumeMenorFaixaReal));
		}

		if (volumeMaiorFaixa != null
				&& !volumeMaiorFaixa.trim().equalsIgnoreCase("")) {

			BigDecimal volumeMaiorFaixaReal = new BigDecimal(0);

			String valorAux = volumeMaiorFaixa.toString().replace(".", "");

			valorAux = valorAux.replace(",", ".");

			volumeMaiorFaixaReal = new BigDecimal(valorAux);
			
			
			if (volumeMaiorFaixaReal != null					
					&& volumeMenorFaixaReal != null) {
				if (volumeMenorFaixaReal.compareTo(volumeMaiorFaixaReal) > 0) {
					throw new ActionServletException("atencao.volume_menor_faixa_superior_maior_faixa");
				}
			}

			filtroTabelaAuxiliarFaixaReal.adicionarParametro(new MenorQue(
					FiltroTabelaAuxiliarFaixaReal.VOLUME_MAIOR_FAIXA,
					volumeMaiorFaixaReal));
		}

		

		if (atualizar != null && atualizar.equalsIgnoreCase("1")) {
			httpServletRequest.setAttribute("atualizar", atualizar);
		}

		//Manda o filtro pelo request para o
		// ExibirManterTabelaAuxiliarAbreviada
		httpServletRequest.setAttribute("filtroTabelaAuxiliarFaixaReal",
				filtroTabelaAuxiliarFaixaReal);

		//Devolve o mapeamento de retorno
		return retorno;
	}
}
