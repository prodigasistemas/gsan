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
package gcom.gui.operacional;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroProducaoAgua;
import gcom.operacional.ProducaoAgua;
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
 * 
 * @author Vinicius Medeiros
 * @date 11/06/2008
 */
public class ExibirAtualizarProducaoAguaAction extends GcomAction {

	
	private Collection colecaoPesquisa;

	
	/**
	 * Método responsavel por responder a requisicao
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o caminho do retorno
		ActionForward retorno = actionMapping.findForward("producaoAguaAtualizar");

		AtualizarProducaoAguaActionForm atualizarProducaoAguaActionForm =(AtualizarProducaoAguaActionForm) actionForm;

		// Mudar isso quando houver a implementação do esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		String id = httpServletRequest.getParameter("idRegistroAtualizacao");
		
		if (id == null) {
			
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") != null) {
			
				id = (String) httpServletRequest.getAttribute("idRegistroAtualizacao");
			
			}
		}
		
		FiltroProducaoAgua filtroProducaoAgua = new FiltroProducaoAgua();
		filtroProducaoAgua.adicionarCaminhoParaCarregamentoEntidade(FiltroProducaoAgua.LOCALIDADE);

		ProducaoAgua producaoAgua = new ProducaoAgua();
		
		if (id != null && !id.trim().equals("") && Integer.parseInt(id) > 0) {

			filtroProducaoAgua.adicionarParametro(
				new ParametroSimples(FiltroProducaoAgua.ID, id));
			
			Collection colecaoProducaoAgua = 
				this.getFachada().pesquisar(filtroProducaoAgua, ProducaoAgua.class.getName());
			
			if (colecaoProducaoAgua != null && !colecaoProducaoAgua.isEmpty()) {
		
				producaoAgua = (ProducaoAgua) Util.retonarObjetoDeColecao(colecaoProducaoAgua);
			
			}

			if (producaoAgua != null) {

				atualizarProducaoAguaActionForm.setId(producaoAgua.getId().toString());
				atualizarProducaoAguaActionForm.setAnoMesReferencia(
					Util.formatarAnoMesParaMesAno(producaoAgua.getAnoMesReferencia()));

				atualizarProducaoAguaActionForm.setVolumeProduzido(
					Util.formataBigDecimal(producaoAgua.getVolumeProduzido(),2,true));

				atualizarProducaoAguaActionForm.setLocalidadeID(
					producaoAgua.getLocalidade().getId().toString());

				atualizarProducaoAguaActionForm.setLocalidadeDescricao(
					producaoAgua.getLocalidade().getDescricao());

			}

		}
		
		sessao.setAttribute("atualizarProducaoAgua", producaoAgua);
		
		String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");
		
		if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("")) {

			switch (Integer.parseInt(objetoConsulta)) {

			// Localidade
			case 1:

				// Recebe o valor do campo localidadeID do formulário.
				String localidadeID = atualizarProducaoAguaActionForm.getLocalidadeID();

				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

				filtroLocalidade.adicionarParametro(
					new ParametroSimples(FiltroLocalidade.ID,localidadeID));

				filtroLocalidade.adicionarParametro(
					new ParametroSimples(FiltroLocalidade.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna Localidade
				colecaoPesquisa = 
					this.getFachada().pesquisar(filtroLocalidade,Localidade.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Localidade nao encontrado
					// Limpa o campo localidadeID do formulário
					atualizarProducaoAguaActionForm.setLocalidadeID("");
					atualizarProducaoAguaActionForm.setLocalidadeDescricao("Localidade inexistente.");
					
					httpServletRequest.setAttribute("corLocalidade","exception");
					httpServletRequest.setAttribute("nomeCampo","localidadeID");
			
				} else {
					
					Localidade objetoLocalidade = 
						(Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
					
					atualizarProducaoAguaActionForm.setLocalidadeID(
						String.valueOf(objetoLocalidade.getId()));
					
					atualizarProducaoAguaActionForm.setLocalidadeDescricao(
						objetoLocalidade.getDescricao());
					
					httpServletRequest.setAttribute("corLocalidade","valor");
					httpServletRequest.setAttribute("nomeCampo","localidadeID");
				}

				break;
			}
		}

		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}

		if (sessao.getAttribute("colecaoProducaoAgua") != null) {
			sessao.setAttribute("caminhoRetornoVoltar","/gsan/filtrarProducaoAguaAction.do");
		} else {
			sessao.setAttribute("caminhoRetornoVoltar","/gsan/exibirFiltrarProducaoAguaAction.do");
		}


		return retorno;
	}
}