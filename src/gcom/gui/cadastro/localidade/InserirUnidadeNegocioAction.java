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
 * Rômulo Aurélio de Melo Souza Filho
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

package gcom.gui.cadastro.localidade;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Rômulo Aurélio
 * @created 29/09/2008
 */
public class InserirUnidadeNegocioAction extends GcomAction {

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

		HttpSession sessao = httpServletRequest.getSession(false);

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirUnidadeNegocioActionForm form = (InserirUnidadeNegocioActionForm) actionForm;

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		Fachada fachada = Fachada.getInstancia();

		String nome = form.getNome();

		String nomeAbreviado = form.getNomeAbreviado();

		String numeroCnpj = form.getNumeroCnpj();

		String idCliente = form.getIdCliente();

		String idGerenciaRegional = form.getIdGerenciaRegional();

		UnidadeNegocio unidadeNegocio = new UnidadeNegocio();

		// Nome
		if (nome != null && !nome.equals("")) {

			unidadeNegocio.setNome(nome);

		}

		// Nome Abreviado
		if (nomeAbreviado != null && !nomeAbreviado.equals("")) {

			unidadeNegocio.setNomeAbreviado(nomeAbreviado);
		}

		// Cliente
		if (idCliente != null && !idCliente.trim().equals("")) {
			// Pesquisa o cliente na base
			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, idCliente));

			Collection colecaoCliente = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			if (colecaoCliente != null && !colecaoCliente.isEmpty()) {
				Cliente cliente = (Cliente) colecaoCliente.iterator().next();

				Integer clienteFuncionario = fachada.verificarClienteSelecionadoFuncionario(new Integer(idCliente));
				
				if(clienteFuncionario == null){
					throw new ActionServletException("atencao.cliente_selecionado_nao_e_funcionario");
				}
				
				unidadeNegocio.setCliente(cliente);

			} else {
				throw new ActionServletException("atencao.cliente.inexistente");
			}
		
		}


		// Gerencia Regional
		if (idGerenciaRegional != null && !idGerenciaRegional.trim().equals("")) {
			// Pesquisa o cliente na base
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();

			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
					FiltroGerenciaRegional.ID, idGerenciaRegional));

			Collection colecaoGerenciaRegional = fachada.pesquisar(
					filtroGerenciaRegional, GerenciaRegional.class.getName());

			if (colecaoGerenciaRegional != null
					&& !colecaoGerenciaRegional.isEmpty()) {

				GerenciaRegional gerenciaRegional = (GerenciaRegional) colecaoGerenciaRegional
						.iterator().next();

				unidadeNegocio.setGerenciaRegional(gerenciaRegional);

			} else {
				throw new ActionServletException(
						"atencao.gerenciaRegional_inexistente");
			}

		}

		// Cnpj

		if (numeroCnpj != null && !numeroCnpj.equals("")) {

			unidadeNegocio.setCnpj(numeroCnpj);

		}

		// Indicador de Uso

		unidadeNegocio.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);

		Integer idUnidadeNegocio = fachada.inserirUnidadeNegocio(
				unidadeNegocio, usuarioLogado);

		montarPaginaSucesso(httpServletRequest,
				"Unidade de Negócio de código  " + idUnidadeNegocio
						+ " inserida com sucesso.",
				"Inserir outra Unidade de Negócio",
				"exibirInserirUnidadeNegócioAction.do?menu=sim",
				"exibirAtualizarUnidadeNegocioAction.do?idRegistroAtualizacao="
						+ idUnidadeNegocio,
				"Atualizar Unidade de Negócio Inserida");

		return retorno;
	}
}
