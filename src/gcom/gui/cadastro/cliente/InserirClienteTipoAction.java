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
package gcom.gui.cadastro.cliente;

import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InserirClienteTipoAction extends GcomAction {

	/**
	 * Este caso de uso permite inserir um Cliente Tipo
	 * 
	 * [UC????] Inserir Cliente Tipo
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @author Thiago Tenório
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		// Usuario logado no sistema
		Usuario usuarioLogado = (Usuario) sessao
				.getAttribute(Usuario.USUARIO_LOGADO);

		InserirClienteTipoActionForm inserirClienteTipoActionForm = (InserirClienteTipoActionForm) actionForm;

		String descricao = inserirClienteTipoActionForm.getDescricao();
		String tipoPessoa = inserirClienteTipoActionForm.getTipoPessoa();
		// String esferaPoder = inserirClienteTipoActionForm.getEsferaPoder();

		ClienteTipo clienteTipoInserir = new ClienteTipo();

		sessao.removeAttribute("tipoPesquisaRetorno");

		// Esfera Poder

		if (Util.validarNumeroMaiorQueZERO(inserirClienteTipoActionForm
				.getEsferaPoder())) {
			// Constrói o filtro para pesquisa do serviço tipo referência
			FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder();
			filtroEsferaPoder.adicionarParametro(new ParametroSimples(
					FiltroEsferaPoder.ID, inserirClienteTipoActionForm
							.getEsferaPoder()));

			Collection colecaoEsferaPoder = (Collection) fachada.pesquisar(
					filtroEsferaPoder, EsferaPoder.class.getName());

			// setando
			clienteTipoInserir.setEsferaPoder((EsferaPoder) colecaoEsferaPoder
					.iterator().next());
		}

		// A Descrição é obrigatório.
		if (descricao == null || descricao.equalsIgnoreCase("")) {
			throw new ActionServletException("atencao.required", null,
					"Descrição");
		}

		// Esfera Poder é obrigatório.
		if (tipoPessoa == null || tipoPessoa.equals("")) {
			throw new ActionServletException("atencao.required", null,
					"Tipo de Pessoa");
		}

		//Setando os Objetos no Banco
		clienteTipoInserir.setDescricao(descricao);
		clienteTipoInserir.setIndicadorPessoaFisicaJuridica(new Short(
				tipoPessoa));

		// Ultima alteração
		clienteTipoInserir.setUltimaAlteracao(new Date());

		// Indicador de uso
		Short iu = 1;
		clienteTipoInserir.setIndicadorUso(iu);

		Integer idClienteTipo = null;

		idClienteTipo = fachada.inserirClienteTipo(clienteTipoInserir,
				usuarioLogado);

		montarPaginaSucesso(httpServletRequest, "Tipo de Cliente de código  "
				+ clienteTipoInserir.getId() + " inserida com sucesso.",
				"Inserir outro Tipo de Cliente",
				"exibirInserirClienteTipoAction.do?menu=sim",
				"exibirAtualizarClienteTipoAction.do?idRegistroAtualizacao="
						+ idClienteTipo, "Atualizar Tipo de Cliente");

		// devolve o mapeamento de retorno
		return retorno;
	}
}
