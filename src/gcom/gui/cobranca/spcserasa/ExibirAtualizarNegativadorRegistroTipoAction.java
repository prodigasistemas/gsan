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
 * Yara Taciane de Souza
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
package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.FiltroNegativadorRegistroTipo;
import gcom.cobranca.NegativadorRegistroTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action de Exibir Atualizar Negativador Registro Tipo
 * 
 * @author Yara Taciane
 * @created 08/01/2008
 */

public class ExibirAtualizarNegativadorRegistroTipoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Inicializando Variaveis
		ActionForward retorno = actionMapping.findForward("atualizarNegativadorRegistroTipo");
		AtualizarNegativadorRegistroTipoActionForm atualizarNegativadorRegistroTipoActionForm = (AtualizarNegativadorRegistroTipoActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);

		// volta da msg de Negativador Exclusao Motivo já utilizado, não pode ser
		// alterado nem excluído.
		String confirmado = httpServletRequest.getParameter("confirmado");

		String idNegativadorRegistroTipo = null;
		if (httpServletRequest.getParameter("reload") == null
				|| httpServletRequest.getParameter("reload").equalsIgnoreCase(
						"") && (confirmado == null || confirmado.equals(""))) {
			// Recupera o id do Negativador Exclusao Motivo que vai ser atualizado

			if (httpServletRequest.getParameter("idRegistroInseridoAtualizar") != null) {
				idNegativadorRegistroTipo = httpServletRequest.getParameter("idRegistroInseridoAtualizar");
				// Definindo a volta do botão Voltar p Filtrar Negativador Registro Tipo			
				httpServletRequest.setAttribute("voltar", "filtrar");
				sessao.setAttribute("idRegistroAtualizacao",idNegativadorRegistroTipo);
			} else if (httpServletRequest.getParameter("idRegistroAtualizacao") == null) {
				idNegativadorRegistroTipo = (String) sessao.getAttribute("idRegistroAtualizacao");
				// Definindo a volta do botão Voltar p Filtrar Negativador Exclusao Motivo				
				httpServletRequest.setAttribute("voltar", "filtrar");
			} else if (httpServletRequest.getParameter("idRegistroAtualizacao") != null) {
				idNegativadorRegistroTipo = httpServletRequest.getParameter("idRegistroAtualizacao");
				// Definindo a volta do botão Voltar p Manter Negativador Exclusao Motivo		
				httpServletRequest.setAttribute("voltar", "manter");
				sessao.setAttribute("idRegistroAtualizacao",idNegativadorRegistroTipo);
			}
		} else {
			idNegativadorRegistroTipo = (String) sessao.getAttribute("idRegistroAtualizacao");
		}

		// Verifica se o usuário está selecionando o Negativador Registro Tipo da
		// página de manter
		// Caso contrário o usuário está teclando enter na página de atualizar
		if ((idNegativadorRegistroTipo != null && !idNegativadorRegistroTipo.equals(""))
				&& (httpServletRequest.getParameter("desfazer") == null)
				&& (httpServletRequest.getParameter("reload") == null || httpServletRequest
						.getParameter("reload").equalsIgnoreCase(""))) {
			exibirNegativadorRegistroTipo(idNegativadorRegistroTipo,
					atualizarNegativadorRegistroTipoActionForm, fachada, sessao,
					httpServletRequest);

		}

		if (httpServletRequest.getParameter("desfazer") != null
				&& httpServletRequest.getParameter("desfazer")
						.equalsIgnoreCase("S")) {

			// -------------- bt DESFAZER ---------------

			exibirNegativadorRegistroTipo(idNegativadorRegistroTipo,
					atualizarNegativadorRegistroTipoActionForm, fachada, sessao,
					httpServletRequest);

		}

		return retorno;

	}

	private void exibirNegativadorRegistroTipo(
			String idNegativadorRegistroTipo,
			AtualizarNegativadorRegistroTipoActionForm atualizarNegativadorRegistroTipoActionForm,
			Fachada fachada, HttpSession sessao,
			HttpServletRequest httpServletRequest) {

		// Cria a variável que vai armazenar o negativador registro tipo para ser
		// atualizado
		NegativadorRegistroTipo negativadorRegistroTipo = null;

		// Cria o filtro de NegativadorRegistroTipo e seta o id do
		// NegativadorRegistroTipo para ser atualizado no filtro
		// e indica quais objetos devem ser retornados pela pesquisa
		FiltroNegativadorRegistroTipo filtroNegativadorRegistroTipo = new FiltroNegativadorRegistroTipo();
		filtroNegativadorRegistroTipo.adicionarParametro(new ParametroSimples(
				FiltroNegativadorRegistroTipo.ID, idNegativadorRegistroTipo));

		filtroNegativadorRegistroTipo.adicionarCaminhoParaCarregamentoEntidade("negativador.cliente");

		Collection<NegativadorRegistroTipo> collectionNegativadorRegistroTipo = fachada
				.pesquisar(filtroNegativadorRegistroTipo, NegativadorRegistroTipo.class
						.getName());

		// Caso a pesquisa tenha retornado o NegativadorRegistroTipo
		if (collectionNegativadorRegistroTipo != null
				&& !collectionNegativadorRegistroTipo.isEmpty()) {

			// Recupera da coleção o NegativadorRegistroTipo que vai ser atualizado
			negativadorRegistroTipo = (NegativadorRegistroTipo) Util
					.retonarObjetoDeColecao(collectionNegativadorRegistroTipo);
	
			// Seta no form os dados de NegativadorRegistroTipo
			if (negativadorRegistroTipo.getNegativador() != null
					&& !negativadorRegistroTipo.getNegativador().equals("")) {

				atualizarNegativadorRegistroTipoActionForm.setIdNegativador(""
						+ negativadorRegistroTipo.getNegativador());

				atualizarNegativadorRegistroTipoActionForm.setNegativadorCliente(""
						+ negativadorRegistroTipo.getNegativador().getCliente()
								.getNome());
			} else {
				atualizarNegativadorRegistroTipoActionForm
						.setNegativadorCliente("");
			}
			
			if (negativadorRegistroTipo.getCodigoRegistro() != null
					&& !negativadorRegistroTipo.getCodigoRegistro().equals("")) {

				atualizarNegativadorRegistroTipoActionForm.setCodigoRegistro(""
						+ negativadorRegistroTipo.getCodigoRegistro());

			} else {
				atualizarNegativadorRegistroTipoActionForm
						.setCodigoRegistro("");
			}
			
			if (negativadorRegistroTipo.getDescricaoRegistroTipo() != null
					&& !negativadorRegistroTipo.getDescricaoRegistroTipo().equals("")) {

				atualizarNegativadorRegistroTipoActionForm.setDescricaoRegistroTipo(""
						+ negativadorRegistroTipo.getDescricaoRegistroTipo());

			} else {
				atualizarNegativadorRegistroTipoActionForm
						.setDescricaoRegistroTipo("");
			}			
	
					
			atualizarNegativadorRegistroTipoActionForm.setTime(Long
					.toString(negativadorRegistroTipo.getUltimaAlteracao()
							.getTime()));

			sessao.setAttribute("negativadorRegistroTipo", negativadorRegistroTipo);

		}

	}

}