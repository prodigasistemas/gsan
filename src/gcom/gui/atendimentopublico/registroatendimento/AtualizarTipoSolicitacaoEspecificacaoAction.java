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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoGrupo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 07/11/2006
 */
public class AtualizarTipoSolicitacaoEspecificacaoAction extends GcomAction {

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

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		AtualizarTipoSolicitacaoEspecificacaoActionForm atualizarTipoSolicitacaoEspecificacaoActionForm = (AtualizarTipoSolicitacaoEspecificacaoActionForm) actionForm;

		Collection colecaoSolicitacaoTipoEspecificacao = (Collection) sessao
				.getAttribute("colecaoSolicitacaoTipoEspecificacao");
		if (colecaoSolicitacaoTipoEspecificacao == null
				|| colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
			throw new ActionServletException("atencao.required", null,
					" Especificação do Tipo da Solicitação");
		}

		// Fachada fachada = Fachada.getInstancia();

		SolicitacaoTipo solicitacaoTipo = new SolicitacaoTipo();

		// id da solicitacao tipo
		solicitacaoTipo.setId(new Integer(
				atualizarTipoSolicitacaoEspecificacaoActionForm
						.getIdTipoSolicitacao()));

		// descrição da solicitação tipo
		if (atualizarTipoSolicitacaoEspecificacaoActionForm.getDescricao() != null
				&& !atualizarTipoSolicitacaoEspecificacaoActionForm
						.getDescricao().equals("")) {
			solicitacaoTipo
					.setDescricao(atualizarTipoSolicitacaoEspecificacaoActionForm
							.getDescricao());
		}
		// id do grupo de solicitação da descrição selecionada
		if (atualizarTipoSolicitacaoEspecificacaoActionForm
				.getIdgrupoTipoSolicitacao() != null
				&& !atualizarTipoSolicitacaoEspecificacaoActionForm
						.getIdgrupoTipoSolicitacao().equals("")) {
			SolicitacaoTipoGrupo solicitacaoTipoGrupo = new SolicitacaoTipoGrupo();
			solicitacaoTipoGrupo.setId(new Integer(
					atualizarTipoSolicitacaoEspecificacaoActionForm
							.getIdgrupoTipoSolicitacao()));
			solicitacaoTipo.setSolicitacaoTipoGrupo(solicitacaoTipoGrupo);
		}

		// indicativo de falta d'agua
		if (atualizarTipoSolicitacaoEspecificacaoActionForm
				.getIndicadorFaltaAgua() != null
				&& !atualizarTipoSolicitacaoEspecificacaoActionForm
						.getIndicadorFaltaAgua().equals("")) {
			solicitacaoTipo.setIndicadorFaltaAgua(new Short(
					atualizarTipoSolicitacaoEspecificacaoActionForm
							.getIndicadorFaltaAgua()));
		} else {
			throw new ActionServletException("atencao.indicador.selecionado",
					null, "Falta D'água");
		}
		// indicativo de tarifa social
		if (atualizarTipoSolicitacaoEspecificacaoActionForm
				.getIndicadorTarifaSocial() != null
				&& !atualizarTipoSolicitacaoEspecificacaoActionForm
						.getIndicadorTarifaSocial().equals("")) {
			solicitacaoTipo.setIndicadorTarifaSocial(new Short(
					atualizarTipoSolicitacaoEspecificacaoActionForm
							.getIndicadorTarifaSocial()));
		} else {
			throw new ActionServletException("atencao.indicador.selecionado",
					null, "Tarifa Social");

		}
		// indicativo de uso do sistema
		if (atualizarTipoSolicitacaoEspecificacaoActionForm
				.getIndicadorUsoSistema() != null
				&& !atualizarTipoSolicitacaoEspecificacaoActionForm
						.getIndicadorUsoSistema().equals("")) {
			solicitacaoTipo.setIndicadorUsoSistema(new Short(
					atualizarTipoSolicitacaoEspecificacaoActionForm
							.getIndicadorUsoSistema()));
		} else {
			throw new ActionServletException("atencao.indicador.selecionado",
					null, "Uso Sistema");

		}
		
		// data e hora correntes
		solicitacaoTipo.setUltimaAlteracao(new Date());

		// indicador uso
		if ( atualizarTipoSolicitacaoEspecificacaoActionForm.getIndicadorUso() != null ) {
			solicitacaoTipo.setIndicadorUso(new Short(atualizarTipoSolicitacaoEspecificacaoActionForm.getIndicadorUso()));	
		}
		

		Collection colecaoSolicitacaoTipoEspecificacaoRemovidos = null;

		if (sessao.getAttribute("colecaoSolicitacaoTipoEspecificacaoRemovidos") != null) {

			colecaoSolicitacaoTipoEspecificacaoRemovidos = (Collection) sessao
					.getAttribute("colecaoSolicitacaoTipoEspecificacaoRemovidos");
		}

		// atualiza o tipo de solicitação com especificações na base
		fachada.atualizarTipoSolicitacaoEspecificacao(solicitacaoTipo,
				colecaoSolicitacaoTipoEspecificacao, usuario,
				colecaoSolicitacaoTipoEspecificacaoRemovidos);

		// remove o parametro de retorno
		sessao.removeAttribute("retornarTela");
		sessao.removeAttribute("retornarTelaPopup");
		sessao.removeAttribute("colecaoImovelSituacao");
		sessao.removeAttribute("colecaoSolicitacaoTipoGrupo");

		montarPaginaSucesso(httpServletRequest,
				"Tipo de Solicitação com Especificações "
						+ solicitacaoTipo.getDescricao()
						+ " atualizado com sucesso!",
				"Realizar outra Manutenção Tipo Solicitação com Especificação",
				"exibirFiltrarTipoSolicitacaoEspecificacaoAction.do?menu=sim");

		return retorno;
	}
}
