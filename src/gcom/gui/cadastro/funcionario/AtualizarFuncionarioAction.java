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
package gcom.gui.cadastro.funcionario;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.funcionario.FuncionarioCargo;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
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

/**
 * @author Rômulo Aurélio
 *
 */
public class AtualizarFuncionarioAction extends GcomAction{
	/**
	 * Este caso de uso permite atualizar um Funcionario
	 * 
	 * [UC0844] Manter Funcionário
	 * 
	 * @author Rômulo Aurélio
	 * @date 16/04/2007
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		AtualizarFuncionarioActionForm form = (AtualizarFuncionarioActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		Funcionario funcionario = (Funcionario)sessao.getAttribute("funcionarioAtualizar");
		
		Funcionario funcionarioAtualizar = this.getFuncionario(funcionario, form, fachada);

		fachada.atualizarFuncionario(funcionarioAtualizar, usuarioLogado);
		
		sessao.removeAttribute("idFuncionario");
		
		sessao.removeAttribute("funcionarioAtualizar");
		
		montarPaginaSucesso(httpServletRequest, "Funcionario de matricula "
				+ funcionario.getId() + " atualizado com sucesso.",
				"Manter outro Funcionário",
				"exibirFiltrarFuncionarioAction.do?menu=sim");
				
		
		return retorno;
	}


	/**
	 * [UC0844] Manter Funcionário
	 *
	 * Carregando os dados do funcionário a partir do que foi informado no formulário
	 *
	 * @author Raphael Rossiter
	 * @date 17/06/2009
	 *
	 * @param funcionario
	 * @param form
	 * @param fachada
	 * @return Funcionario
	 */
	private Funcionario getFuncionario(Funcionario funcionario, AtualizarFuncionarioActionForm form, 
		Fachada fachada){
		
		//NOME
		funcionario.setNome(form.getNome());
		
		//CPF
		if(form.getNumeroCpf() != null && !form.getNumeroCpf().equals("")){
			funcionario.setNumeroCpf(form.getNumeroCpf());
		}
		
		//DATA DE NASCIMENTO
		if(form.getDataNascimento() != null && !form.getDataNascimento().equals("")){
			funcionario.setDataNascimento(Util.converteStringParaDate(form.getDataNascimento()));
		}
		
		//CARGO
		String idFuncionarioCargo = form.getFuncionarioCargo();
		
		if (idFuncionarioCargo != null && !idFuncionarioCargo.trim().equals(
			"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			FuncionarioCargo funcionarioCargo = new FuncionarioCargo();

			funcionarioCargo.setId(new Integer(idFuncionarioCargo));

			funcionario.setFuncionarioCargo(funcionarioCargo);

		}
		
		//EMPRESA
		String idEmpresa = form.getEmpresa();

		if (idEmpresa != null && !idEmpresa.trim().equals(
			"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			Empresa empresa = new Empresa();

			empresa.setId(new Integer(idEmpresa));

			funcionario.setEmpresa(empresa);

		}
		
		//UNIDADE ORGANIZACIONAL
		String idUnidade = form.getIdUnidade();
		
		FiltroUnidadeOrganizacional filtroUnidadeEmpresa = new FiltroUnidadeOrganizacional();

    	filtroUnidadeEmpresa.adicionarParametro(new ParametroSimples(
    	FiltroUnidadeOrganizacional.ID, idUnidade));
		
		Collection<UnidadeOrganizacional> colecaoUnidadeEmpresa = fachada.pesquisar(filtroUnidadeEmpresa,
		UnidadeOrganizacional.class.getName());

		if(colecaoUnidadeEmpresa== null || colecaoUnidadeEmpresa.isEmpty()){
			throw new ActionServletException("atencao.unidade.organizacional.inexistente");
		} 
		
		UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
		unidadeOrganizacional.setId(new Integer(idUnidade));
		
		funcionario.setUnidadeOrganizacional(unidadeOrganizacional);
		
		//ÚLTIMA ALTERAÇÃO
		funcionario.setUltimaAlteracao(new Date());
		
		return funcionario;

	}
}
