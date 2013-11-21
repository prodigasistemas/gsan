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
package gcom.gui.faturamento.autoinfracao;

import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
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
 * Action que faz a exibição da tela para o usuário setar os campos e permitir
 * que ele gere o relatório de auto de infração 
 * [UC0857] Gerar Relatório de Arrecadação das Multas de Autos de Infração
 * 
 * @author Rafael Corrêa
 * @since 08/09/2008
 */
public class ExibirGerarRelatorioAutoInfracaoAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirGerarRelatorioAutoInfracao");
		
		GerarRelatorioAutoInfracaoActionForm gerarRelatorioAutoInfracaoActionForm = (GerarRelatorioAutoInfracaoActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Carrega as coleções que serão exibidas na tela
		
		if (sessao.getAttribute("colecaoUnidadeNegocio") == null) {
			
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
					FiltroUnidadeNegocio.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoUnidadeNegocio = fachada.pesquisar(
					filtroUnidadeNegocio, UnidadeNegocio.class.getName());

			if (colecaoUnidadeNegocio != null
					&& !colecaoUnidadeNegocio.isEmpty()) {
				sessao.setAttribute("colecaoUnidadeNegocio",
						colecaoUnidadeNegocio);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa.nenhum_registro_tabela", null,
						"UNIDADE_NEGOCIO");
			}
		}
		
		String idFuncionario = gerarRelatorioAutoInfracaoActionForm.getIdFuncionario();
		
		// Pesquisa o funcionário
		if (idFuncionario != null && !idFuncionario.trim().equals("")) {
			
			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
			filtroFuncionario.adicionarParametro(new ParametroSimples(FiltroFuncionario.ID, idFuncionario));
			
			Collection colecaoFuncionario = fachada.pesquisar(filtroFuncionario, Funcionario.class.getName());
			
			if (colecaoFuncionario != null && !colecaoFuncionario.isEmpty()) {
				Funcionario funcionario = (Funcionario) Util.retonarObjetoDeColecao(colecaoFuncionario);
				gerarRelatorioAutoInfracaoActionForm.setIdFuncionario(funcionario.getId().toString());
				gerarRelatorioAutoInfracaoActionForm.setNomeFuncionario(funcionario.getNome());
				
				httpServletRequest.setAttribute("nomeCampo", "dataPagamentoInicio");
			} else {
				gerarRelatorioAutoInfracaoActionForm.setIdFuncionario("");
				gerarRelatorioAutoInfracaoActionForm.setNomeFuncionario("FUNCIONÁRIO INEXISTENTE");
				
				httpServletRequest.setAttribute("funcionarioInexistente", true);
				httpServletRequest.setAttribute("nomeCampo", "idFuncionario");
			}
			
		} else {
			gerarRelatorioAutoInfracaoActionForm.setNomeFuncionario("");
		}

		return retorno;

	}

}
