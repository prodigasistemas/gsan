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

import gcom.cadastro.cliente.FiltroFoneTipo;
import gcom.cadastro.cliente.FoneTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usuário a tela que receberá os
 * parâmetros para realização da atualização de um R.A (Aba nº 01 - Dados
 * gerais)
 * 
 * @author Sávio Luiz
 * @date 31/08/2006
 */
public class ExibirAdicionarSolicitanteFoneAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("adicionarSolicitanteFone");

		HttpSession sessao = httpServletRequest.getSession(false);

		AdicionarSolicitanteRegistroAtendimentoActionForm adicionarSolicitanteRegistroAtendimentoActionForm = (AdicionarSolicitanteRegistroAtendimentoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		//URL de retorno
		sessao.removeAttribute("caminhoRetornoTelaAdicionarFone");
		sessao.removeAttribute("caminhoRetornoTelaAdicionarFonePopUp");
		String caminhoRetornoTelaAdicionarFone = httpServletRequest.getParameter("telaRetorno");
		String caminhoRetornoTelaAdicionarFonePopUp = httpServletRequest.getParameter("telaRetornoPopUp");
		String caminhoRetornoTelaAdicionarFoneReiterar = httpServletRequest.getParameter("telaRetornoReiterar");
		
		if (caminhoRetornoTelaAdicionarFone != null && !caminhoRetornoTelaAdicionarFone.equalsIgnoreCase("")){
			//Colocado por Raphael Rossiter em 30/09/2006
			//Facilitador para o controle da Aba nº 03 do processo de Inserir RA
			caminhoRetornoTelaAdicionarFone = caminhoRetornoTelaAdicionarFone + "&retornoFone=OK";
			sessao.setAttribute("caminhoRetornoTelaAdicionarFone", caminhoRetornoTelaAdicionarFone);
		}
		else if (caminhoRetornoTelaAdicionarFonePopUp != null && !caminhoRetornoTelaAdicionarFonePopUp.equalsIgnoreCase("")){
			
			caminhoRetornoTelaAdicionarFonePopUp = caminhoRetornoTelaAdicionarFonePopUp + "?retornoFone=OK";
			sessao.setAttribute("caminhoRetornoTelaAdicionarFonePopUp", caminhoRetornoTelaAdicionarFonePopUp);
			
		}else if (caminhoRetornoTelaAdicionarFoneReiterar != null && !caminhoRetornoTelaAdicionarFoneReiterar.equalsIgnoreCase("")){
			caminhoRetornoTelaAdicionarFoneReiterar = caminhoRetornoTelaAdicionarFoneReiterar + "?retornoFone=OK";
			sessao.setAttribute("caminhoRetornoTelaAdicionarFoneReiterar", caminhoRetornoTelaAdicionarFoneReiterar);
		}
		

		/*
		 * Tipo de Solicitação - Carregando a coleção que irá ficar disponível
		 * para escolha do usuário
		 * 
		 * [FS0003] - Verificar existência de dados
		 */
		Collection colecaoFoneTipo = (Collection) sessao
				.getAttribute("colecaoFoneTipo");

		if (colecaoFoneTipo == null) {

			FiltroFoneTipo filtroFoneTipo = new FiltroFoneTipo();

			colecaoFoneTipo = fachada.pesquisar(filtroFoneTipo,
					FoneTipo.class.getName());

			if (colecaoFoneTipo == null || colecaoFoneTipo.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"Tipo Telefone");
			} else {
				sessao.setAttribute("colecaoFoneTipo", colecaoFoneTipo);
			}
		}

		// limpa os campos no form
		adicionarSolicitanteRegistroAtendimentoActionForm.setIdTipoTelefone("");
		adicionarSolicitanteRegistroAtendimentoActionForm.setDddTelefone("");
		adicionarSolicitanteRegistroAtendimentoActionForm.setNumeroTelefone("");
		adicionarSolicitanteRegistroAtendimentoActionForm.setRamal("");
		
		httpServletRequest.setAttribute("nomeCampo", "idTipoTelefone");

		return retorno;

	}

}
