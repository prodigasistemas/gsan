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
package gcom.gui.cadastro.sistemaparametro;

import java.util.Collection;

import gcom.cadastro.sistemaparametro.SistemaAlteracaoHistorico;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.abreviada.FiltroTabelaAuxiliarAbreviada;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action utilizado para inserir um Historico Alteração de Sistema no banco
 * 
 * [UC0217] Inserir Historico Alteração Sistema Permite inserir um
 * Historico Alteração do Sistema
 * 
 * @author Thiago Tenório
 * @since 30/03/2006
 */
public class InserirHistoricoAlteracaoSistemaAction extends GcomAction {

	/**
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		InserirHistoricoAlteracaoSistemaActionForm inserirHistoricoAlteracaoSistemaActionForm = (InserirHistoricoAlteracaoSistemaActionForm) actionForm;
		
		if ((inserirHistoricoAlteracaoSistemaActionForm.getIdFuncionalidade() != null && !inserirHistoricoAlteracaoSistemaActionForm.getIdFuncionalidade()
				.equals(""))) {

			FiltroTabelaAuxiliarAbreviada filtroTabelaAuxiliarAbreviada = new FiltroTabelaAuxiliarAbreviada();

			filtroTabelaAuxiliarAbreviada
					.adicionarParametro(new ParametroSimples(
							FiltroTabelaAuxiliarAbreviada.ID, inserirHistoricoAlteracaoSistemaActionForm
									.getIdFuncionalidade()));

			Collection colecaoFuncionalidade = fachada.pesquisar(
					filtroTabelaAuxiliarAbreviada, Funcionalidade.class
							.getName());

			if (colecaoFuncionalidade != null
					&& !colecaoFuncionalidade.isEmpty()) {

				Funcionalidade funcionalidade = (Funcionalidade) colecaoFuncionalidade
						.iterator().next();
				inserirHistoricoAlteracaoSistemaActionForm.setDescricaoFuncionalidade(funcionalidade.getDescricao());

			} else {
				httpServletRequest.setAttribute("funcionalidadeEncontrada",
						"exception");
				throw new ActionServletException("atencao.funcionalidade_inexistente");
			}

		}
	
		// Cria uma resolução de diretoria setando os valores informados pelo
		// usuário na pagina para ser inserido no banco
		SistemaAlteracaoHistorico sistemaAlteracaoHistorico = new SistemaAlteracaoHistorico();

		Funcionalidade funcionalidade = new Funcionalidade();
		funcionalidade.setId(new Integer(inserirHistoricoAlteracaoSistemaActionForm
				.getIdFuncionalidade()));
		
		sistemaAlteracaoHistorico
		.setFuncionalidade(funcionalidade);
		
		sistemaAlteracaoHistorico
				.setNome(inserirHistoricoAlteracaoSistemaActionForm
						.getTituloAlteracao());

		sistemaAlteracaoHistorico
				.setDescricao(inserirHistoricoAlteracaoSistemaActionForm
						.getDescricao());

		// verifica se a data de Alteração foi digitada em caso afirmativo
		// seta-a no
		// objeto
		if (inserirHistoricoAlteracaoSistemaActionForm.getDataAlteracao() != null
				&& !inserirHistoricoAlteracaoSistemaActionForm
						.getDataAlteracao().equals("")) {
			sistemaAlteracaoHistorico
					.setData(Util
							.converteStringParaDate(inserirHistoricoAlteracaoSistemaActionForm
									.getDataAlteracao()));
		}
		
		

		// Insere um Historico Alteração de Sistema na base, mas fazendo, antes,
		// algumas verificações no ControladorCadastroSEJB.
		 fachada
		 .inserirHistoricoAlteracaoSistema(sistemaAlteracaoHistorico);

		// Exibe a página de sucesso
		montarPaginaSucesso(httpServletRequest,
				"Histórico de Alterações do Sistema "
						+ sistemaAlteracaoHistorico.getDescricao()
						+ " inserido com sucesso.", "Inserir outro Histórico de Alterações do Sistema",
				"exibirInserirHistoricoAlteracaoSistemaAction.do?menu=sim");

		return retorno;

	}

}