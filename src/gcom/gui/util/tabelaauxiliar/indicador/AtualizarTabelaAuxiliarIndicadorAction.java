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
package gcom.gui.util.tabelaauxiliar.indicador;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.tabelaauxiliar.indicador.TabelaAuxiliarIndicador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Rômulo Aurélio
 *
 */
public class AtualizarTabelaAuxiliarIndicadorAction extends GcomAction {
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

		//Pega o action form
		TabelaAuxiliarIndicadorActionForm form = (TabelaAuxiliarIndicadorActionForm) actionForm;

		//Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		//Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		//Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		//Recupera o ponto de coleta da sessão
		TabelaAuxiliarIndicador tabelaAuxiliarIndicador = (TabelaAuxiliarIndicador) sessao
				.getAttribute("tabelaAuxiliarIndicador");
		
		String tela = (String) sessao.getAttribute("tela");
		
		String indicadorNegocio = null ;
		
		if (tela.equalsIgnoreCase("quadraPerfil")) {
			
			indicadorNegocio = form.getIndicadorBaixaRenda();
			
		}
		
		

		//Atualiza a tabela auxiliar com os dados inseridos pelo usuário
		//A data de última alteração não é alterada, pois será usada na
		//verificação de atualização

		tabelaAuxiliarIndicador.setDescricao(form.getDescricao());
		
		if(form.getIndicadorBaixaRenda()!=null)
		tabelaAuxiliarIndicador.setIndicadorBaixaRenda(new Short(indicadorNegocio));

		if (form.getIndicadorUso() != null) {
			tabelaAuxiliarIndicador.setIndicadorUso(new Short(form
					.getIndicadorUso()));
		}
		
		//Atualiza os dados
		fachada.atualizarTabelaAuxiliar(tabelaAuxiliarIndicador);

		//Monta a página de sucesso
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {

			montarPaginaSucesso(
					httpServletRequest,
					((String) sessao.getAttribute("titulo")) + " "
							+ tabelaAuxiliarIndicador.getId().toString()
							+ " atualizado(a) com sucesso.",
					"Realizar outra manutenção de "
							+ ((String) sessao.getAttribute("titulo")),
					((String) sessao
							.getAttribute("funcionalidadeTabelaAuxiliarIndicadorFiltrar")));

		}

		//Remove os objetos da sessão
		sessao.removeAttribute("funcionalidadeTabelaAuxiliarIndicadorManter");
		sessao.removeAttribute("titulo");
		sessao.removeAttribute("tabelaAuxiliarIndicador");
		sessao.removeAttribute("tamMaxCampoDescricao");


		//devolve o mapeamento de retorno
		return retorno;
	}

}
