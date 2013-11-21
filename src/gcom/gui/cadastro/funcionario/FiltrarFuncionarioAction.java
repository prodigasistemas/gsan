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

import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Rômulo Aurélio
 * @date 13/04/2007
 */
public class FiltrarFuncionarioAction extends GcomAction{

	/**
	 * 
	 * [UC????] Filtrar Funcionario
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 08/04/2007
	 * 
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
				.findForward("exibirManterFuncionarioAction");
		
		FiltrarFuncionarioActionForm form = (FiltrarFuncionarioActionForm) actionForm;

		FiltroFuncionario filtroFuncionario = new FiltroFuncionario();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		boolean peloMenosUmParametroInformado = false;

		String nome = form.getNome();

		String idEmpresa = form.getEmpresa();

		String idUnidade = form.getIdUnidade();

		String matricula = form.getMatricula();
		
		String idFuncionarioCargo = form.getFuncionarioCargo();
		
		String numeroCpf = form.getNumeroCpf();
		
		String dataNascimento = form.getDataNascimento();
		
		// Verifica se o campo matricula foi informado
		if (matricula != null && !matricula.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroFuncionario.ID, matricula));

		}
		
		// Verifica se o campo nome foi informado
		if (nome != null && !nome.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroFuncionario.adicionarParametro(new ComparacaoTexto(
					FiltroFuncionario.NOME, nome));

		}

		
		// Verifica se o campo cargo foi informado
		if (idFuncionarioCargo != null
				&& !idFuncionarioCargo.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroFuncionario.FUNCIONARIO_CARGO_ID, idFuncionarioCargo));

		}
		
		// Verifica se o campo empresa foi informado
		if (idEmpresa != null
				&& !idEmpresa.trim().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;
			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroFuncionario.UNIDADE_EMPRESA_ID, idEmpresa));

		}
		
		// Verifica se o campo descricaoCargo foi informado
		if (idUnidade != null && !idUnidade.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroFuncionario.adicionarParametro(new ComparacaoTexto(
					FiltroFuncionario.UNIDADE_ORGANIZACIONAL_ID, idUnidade));

		}
		
		// Verifica se o campo CPF foi informado
		if (numeroCpf != null && !numeroCpf.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroFuncionario.adicionarParametro(new ComparacaoTexto(
					FiltroFuncionario.NUMERO_CPF, numeroCpf));
		}
		
		// Verifica se o campo Data de nascimento foi informado
		if (dataNascimento != null && !dataNascimento.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroFuncionario.adicionarParametro(new ComparacaoTexto(
					FiltroFuncionario.DATA_NASCIMENTO, dataNascimento));
		}

		// Erro caso o usuário mandou Pesquisar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		// Verifica se o checkbox Atualizar está marcado e em caso afirmativo
		// manda pelo um request uma variável para o
		// ExibirManterFuncionarioAction e nele verificar se irá para o
		// atualizar ou para o manter
		if (form.getAtualizar() != null	&& form.getAtualizar()
						.equalsIgnoreCase("1")) {
			httpServletRequest.setAttribute("atualizar",form.getAtualizar());
			
		}
		
		filtroFuncionario.adicionarCaminhoParaCarregamentoEntidade("empresa");
		filtroFuncionario.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
		filtroFuncionario.adicionarCaminhoParaCarregamentoEntidade("funcionarioCargo");
		
		// Manda o filtro pelo sessao para o
		// ExibirFuncionalidadeAction
		sessao.setAttribute("filtroFuncionario", filtroFuncionario);

		httpServletRequest.setAttribute("filtroFuncionario", filtroFuncionario);


		return retorno;

	}



	
}
