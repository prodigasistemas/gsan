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
package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.Negativador;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.spcserasa.FiltroNegativador;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que inicializa o pré-processamento da pagina de exibição do filtro
 * para pesquisa de comandos de negativação
 * 
 * @author: Thiago Vieira
 * @date: 27/12/2007
 */
public class ExibirFiltrarComandoNegativacaoPorCriterioAction extends
		GcomAction {

	/**
	 * Método de execução principal do action
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

		ActionForward retorno = actionMapping
				.findForward("filtrarComandoNegativacao");
		FiltrarComandoNegativacaoPorCriterioActionForm form = (FiltrarComandoNegativacaoPorCriterioActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = getSessao(httpServletRequest);

		form.setComandoSimulado(ConstantesSistema.COMANDO_SIMULADO_TODOS);
		form.setTipoBuscaTituloComando(ConstantesSistema.TIPO_PESQUISA_INICIAL
				.toString());
		form.setUsuarioOk("false");

		if (form.getCheckAtualizar() == null
				|| form.getCheckAtualizar().equals("")) {
			form.setCheckAtualizar("1");
			httpServletRequest.setAttribute("nomeCampo", "tituloComando");
		}

		String idDigitadoEnterUsuarioResponsavel = (String) form
				.getUsuarioResponsavelId();

		// verifica se o codigo do usuário responsável foi digitado foi digitado
		if (idDigitadoEnterUsuarioResponsavel != null
				&& !idDigitadoEnterUsuarioResponsavel.trim().equals("")
				&& Integer.parseInt(idDigitadoEnterUsuarioResponsavel) > 0) {

			FiltroUsuario filtroUsuario = new FiltroUsuario();

			filtroUsuario.adicionarParametro(new ParametroSimples(
					FiltroUsuario.ID, idDigitadoEnterUsuarioResponsavel));

			Collection usuarioEncontrado = fachada.pesquisar(filtroUsuario,
					Usuario.class.getName());

			if (usuarioEncontrado != null && !usuarioEncontrado.isEmpty()) {
				// O Usuário foi encontrado
				// if (((Usuario) ((List)
				// usuarioEncontrado).get(0)).getIndicadorUso().equals(ConstantesSistema.INDICADOR_USO_DESATIVO))
				// {
				// throw new ActionServletException("atencao.cliente.inativo",
				// null, ""
				// + ((Cliente) ((List) clienteEncontrado)
				// .get(0)).getId());
				// }

				form.setUsuarioResponsavelId(((Usuario) ((List) usuarioEncontrado)
						.get(0)).getId().toString());
				form.setUsuarioResponsavelNome(((Usuario) ((List) usuarioEncontrado)
						.get(0)).getNomeUsuario());
				form.setUsuarioOk("true");
			} else {
				httpServletRequest.setAttribute("corUsuario", "exception");
				form.setUsuarioResponsavelNome(ConstantesSistema.USUARIO_INEXISTENTE);
				form.setUsuarioResponsavelId("");
				form.setUsuarioOk("false");
			}
		}

		// carregar coleção de negativadores para select do form de filtro
		Collection colecaoNegativador = (Collection) sessao
				.getAttribute("colecaoNegativador");

		if (colecaoNegativador == null) {

			FiltroNegativador filtroNegativador = new FiltroNegativador();
			filtroNegativador.setCampoOrderBy(FiltroNegativador.CLIENTE);
			filtroNegativador
					.adicionarCaminhoParaCarregamentoEntidade("cliente");
			filtroNegativador.setConsultaSemLimites(true);

			colecaoNegativador = fachada.pesquisar(filtroNegativador,
					Negativador.class.getName());

			if (colecaoNegativador == null || colecaoNegativador.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"NEGATIVADOR");
			} else {
				sessao.setAttribute("colecaoNegativador", colecaoNegativador);
			}
		}

		return retorno;
	}
}
