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
package gcom.gui.cadastro.imovel;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.imovel.FiltroImovelSituacaoTipo;
import gcom.cadastro.imovel.ImovelSituacaoTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 26/03/2006
 */
public class ExibirInserirImovelSituacaoAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de uma nova situação do imóvel
	 * 
	 * [UC0224] Inserir Situação do Imovel
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 24/03/2006
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

		ActionForward retorno = actionMapping
				.findForward("situacaoImovelInserir");

		Fachada fachada = Fachada.getInstancia();

		FiltroImovelSituacaoTipo filtroImovelSituacaoTipo = new FiltroImovelSituacaoTipo();

		// Verifica se os dados foram informados da tabela existem e joga numa
		// colecao.
		// Seta a colecao no request

		Collection<ImovelSituacaoTipo> collectionImovelSituacaoTipo = fachada
				.pesquisar(filtroImovelSituacaoTipo, ImovelSituacaoTipo.class
						.getName());
		if (collectionImovelSituacaoTipo == null
				|| collectionImovelSituacaoTipo.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela Imóvel Situação Tipo");
		}
		httpServletRequest.setAttribute("collectionImovelSituacaoTipo",
				collectionImovelSituacaoTipo);

		// Parte referente ao combo Situação da Ligação de Água

		FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();

		filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
				FiltroLigacaoAguaSituacao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<LigacaoAguaSituacao> collectionLigacaoAguaSituacao = fachada
				.pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class
						.getName());

		if (collectionLigacaoAguaSituacao == null
				|| collectionLigacaoAguaSituacao.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela Ligação Água Situação");
		}
		httpServletRequest.setAttribute("collectionLigacaoAguaSituacao",
				collectionLigacaoAguaSituacao);

		// Parte referente ao combo Situação da Ligação de Esgoto

		FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();

		filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
				FiltroLigacaoEsgotoSituacao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<LigacaoEsgotoSituacao> collectionLigacaoEsgotoSituacao = fachada
				.pesquisar(filtroLigacaoEsgotoSituacao,
						LigacaoEsgotoSituacao.class.getName());

		if (collectionLigacaoEsgotoSituacao == null
				|| collectionLigacaoEsgotoSituacao.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela Ligação Esgoto Situação");

		}

		httpServletRequest.setAttribute("collectionLigacaoEsgotoSituacao",
				collectionLigacaoEsgotoSituacao);

		return retorno;
	}

}
