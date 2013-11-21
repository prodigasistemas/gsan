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
package gcom.gui.micromedicao.leitura;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.micromedicao.leitura.InserirLeituristaActionForm;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0588]Inserir Leiturista
 * 
 * @author Thiago Tenório
 * @date 22/07/2007
 */

public class ExibirInserirLeituristaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirInserirLeiturista");

		Fachada fachada = Fachada.getInstancia();

		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		InserirLeituristaActionForm form = (InserirLeituristaActionForm) actionForm;
		
		//Usuário
		if (form.getLoginUsuario() != null &&
			!form.getLoginUsuario().equals("")) {
			
			getUsuario(form, fachada,
					form.getLoginUsuario(), sessao);
		}

		if ((httpServletRequest.getParameter("desfazer") != null && httpServletRequest
				.getParameter("desfazer").equalsIgnoreCase("S"))) {
			// -------------- bt DESFAZER ---------------

			// Limpando o formulario
			form.setIdFuncionario("");
			form.setDescricaoFuncionario("");
			form.setEmpresaID("");
			form.setIdCliente("");
			form.setDescricaoCliente("");
			form.setDdd("");
			form.setTelefone("");
			form.setNumeroImei("");

		}
		
		// Bloquear o campo Empresa quando a empresa do usuario nao seja a empresa do sistema
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		if (usuario.getEmpresa() == null) {
			throw new ActionServletException("atencao.pesquisa_inexistente", null, "Empresa do usuário logado.");
		}
		if (!usuario.getEmpresa().getDescricao().equals(sistemaParametro.getNomeAbreviadoEmpresa())) {
			sessao.setAttribute("bloquearEmpresa", true);
			form.setEmpresaID(usuario.getEmpresa().getId().toString());
		}	

		// Verificar Existência do Leiturista Responsável(Funcionario)

		if ((form.getIdFuncionario() != null && !form.getIdFuncionario()
				.equals(""))) {

			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();

			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroFuncionario.ID, form.getIdFuncionario()));

			Collection colecaoFuncionario = fachada.pesquisar(
					filtroFuncionario, Funcionario.class.getName());

			if (colecaoFuncionario != null && !colecaoFuncionario.isEmpty()) {

				Funcionario funcionario = (Funcionario) colecaoFuncionario
						.iterator().next();
				form.setDescricaoFuncionario(funcionario.getNome());

			} else {
				httpServletRequest.setAttribute("funcionarioEncontrado",
						"exception");
				form.setIdFuncionario("");
				form.setDescricaoFuncionario("FUNCIONARIO INEXISTENTE");
			}

		}
		
		if (httpServletRequest.getParameter("menu") != null
				&& !httpServletRequest.getParameter("menu").equals("")) {
			httpServletRequest.setAttribute("nomeCampo", "idFuncionario");
		}

		// Verificar Existência do Leiturista Responsável(Cliente)

		if ((form.getIdCliente() != null && !form.getIdCliente().equals(""))) {

			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, form.getIdCliente()));

			Collection colecaoCliente = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			if (colecaoCliente != null && !colecaoCliente.isEmpty()) {

				Cliente cliente = (Cliente) colecaoCliente.iterator().next();
				form.setDescricaoCliente(cliente.getNome());

			} else {
				httpServletRequest.setAttribute("clienteEncontrado",
						"exception");
				form.setIdCliente("");
				form.setDescricaoCliente("CLIENTE INEXISTENTE");
			}

		}

		// Empresa

		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();

		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);

		filtroEmpresa.adicionarParametro(new ParametroSimples(
				FiltroEmpresa.INDICADORUSO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoPesquisa = null;

		// Retorna empresa
		colecaoPesquisa = fachada.pesquisar(filtroEmpresa, Empresa.class
				.getName());

		if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
			// Nenhum registro na tabela localidade_porte foi encontrado
			throw new ActionServletException(
					"atencao.pesquisa.nenhum_registro_tabela", null, "Empresa");
		} else {
			sessao.setAttribute("colecaoEmpresa", colecaoPesquisa);
		}

		// Constrói filtro para pesquisa da Empresa
		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.ID);
		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		sessao.setAttribute("colecaoEmpresa", fachada.pesquisar(filtroEmpresa,
				Empresa.class.getName(), "EMPRESA"));

		httpServletRequest.setAttribute("colecaoEmpresa", colecaoPesquisa);

		if (httpServletRequest.getParameter("menu") != null
				&& !httpServletRequest.getParameter("menu").equals("")) {
			httpServletRequest.setAttribute("nomeCampo", "idCliente");
		}

		return retorno;
	}
	
	/**
	 * Recupera o Usuário
	 *
	 * @author Bruno Barros
	 * @date 11/12/2006
	 *
	 * @param inserirLeituristaActionForm
	 * @param fachada
	 * @param idUsuario
	 * @return Descrição da Unidade Filtrada
	 */
	private void getUsuario(InserirLeituristaActionForm inserirLeituristaActionForm, 
			Fachada fachada, String idUsuario, HttpSession sessao) {
		
		// Filtra Usuario
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ComparacaoTexto(FiltroUsuario.LOGIN, idUsuario));		
		
		// Recupera Usuário
		Collection<Usuario> colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
		if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
			Usuario usuario = colecaoUsuario.iterator().next();
			
			sessao.setAttribute("usuarioEncontrado","true");
			inserirLeituristaActionForm.setNomeUsuario(usuario.getNomeUsuario());
		} else {
			
			sessao.removeAttribute("usuarioEncontrado");
			inserirLeituristaActionForm.setLoginUsuario("");
			inserirLeituristaActionForm.setNomeUsuario("Usuário Inexistente");
		}
	}	
}
