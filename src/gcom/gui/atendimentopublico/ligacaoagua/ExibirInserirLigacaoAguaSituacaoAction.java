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
package gcom.gui.atendimentopublico.ligacaoagua;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;


/**
 * @author Vinícius de Melo Medeiros
 * @created 14/05/2008
 */
public class ExibirInserirLigacaoAguaSituacaoAction extends GcomAction {
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

		// Mudar isso quando houver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("inserirLigacaoAguaSituacao");

		InserirLigacaoAguaSituacaoActionForm inserirLigacaoAguaSituacaoActionForm = (InserirLigacaoAguaSituacaoActionForm) actionForm;

		inserirLigacaoAguaSituacaoActionForm.setIndicadorAbastecimento("" + ConstantesSistema.INDICADOR_USO_ATIVO);
		inserirLigacaoAguaSituacaoActionForm.setIndicadorExistenciaLigacao("" + ConstantesSistema.INDICADOR_USO_ATIVO);
		inserirLigacaoAguaSituacaoActionForm.setIndicadorExistenciaRede("" + ConstantesSistema.INDICADOR_USO_ATIVO);
		inserirLigacaoAguaSituacaoActionForm.setIndicadorAguaAtiva("" + ConstantesSistema.INDICADOR_USO_ATIVO);
		inserirLigacaoAguaSituacaoActionForm.setIndicadorAguaCadastrada("" + ConstantesSistema.INDICADOR_USO_ATIVO);
		inserirLigacaoAguaSituacaoActionForm.setIndicadorAguaDesligada("" + ConstantesSistema.INDICADOR_USO_ATIVO);
		inserirLigacaoAguaSituacaoActionForm.setIndicadorAnalizeAgua("" + ConstantesSistema.INDICADOR_USO_ATIVO);
		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		if ((httpServletRequest.getParameter("desfazer") != null && httpServletRequest
				.getParameter("desfazer").equalsIgnoreCase("S"))) {

			inserirLigacaoAguaSituacaoActionForm.setDescricao("");
			inserirLigacaoAguaSituacaoActionForm.setDescricaoAbreviado("");
			inserirLigacaoAguaSituacaoActionForm.setConsumoMinimoFaturamento("");
			inserirLigacaoAguaSituacaoActionForm.setIndicadorExistenciaLigacao("");
			inserirLigacaoAguaSituacaoActionForm.setIndicadorExistenciaRede("");
			inserirLigacaoAguaSituacaoActionForm.setIndicadorFaturamentoSituacao("");
			inserirLigacaoAguaSituacaoActionForm.setIndicadorAbastecimento("");
			inserirLigacaoAguaSituacaoActionForm.setIndicadorAguaAtiva("");
			inserirLigacaoAguaSituacaoActionForm.setIndicadorAguaCadastrada("");
			inserirLigacaoAguaSituacaoActionForm.setIndicadorAguaDesligada("");
			inserirLigacaoAguaSituacaoActionForm.setIndicadorAnalizeAgua("");
			
			if (inserirLigacaoAguaSituacaoActionForm.getDescricao() == null
					|| inserirLigacaoAguaSituacaoActionForm.getDescricao().equals("")) {

				Collection colecaoPesquisa = null;

				FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();

				filtroLigacaoAguaSituacao.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);

				colecaoPesquisa = fachada.pesquisar(filtroLigacaoAguaSituacao,
						LigacaoAguaSituacao.class.getName());

				// Verifica se há registros na tabela
				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.nenhum_registro_tabela", null,
							"Situação de Ligação de Água");
				} else {
					sessao.setAttribute("colecaoLigacaoAguaSituacao", colecaoPesquisa);
				}

				// Coleção de Situacao de Ligacao de Agua
				FiltroLigacaoAguaSituacao filtroLigAguaSituacao = new FiltroLigacaoAguaSituacao();
				filtroLigAguaSituacao.setCampoOrderBy(FiltroLigacaoAguaSituacao.ID);

				Collection colecaoLigAguaSituacao = fachada.pesquisar(filtroLigAguaSituacao,
						LigacaoEsgotoSituacao.class.getName());
				sessao.setAttribute("colecaoLigAguaSituacao", colecaoLigAguaSituacao);

			}

		}
		return retorno;
	}
}
