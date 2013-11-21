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
package gcom.gui.operacional;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.DistritoOperacional;
import gcom.operacional.FiltroDistritoOperacional;
import gcom.operacional.FiltroZonaPressao;
import gcom.operacional.ZonaPressao;
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
 * Descrição da classe
 * 
 * @author Vinícius Medeiros
 * @date 20/05/2008
 */
public class InserirZonaPressaoAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de uma Zona de Pressao
	 * 
	 * [UC0796] Inserir Zona de Pressao
	 * 
	 * 
	 * @author Vinícius Medeiros
	 * @date 20/05/2008
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

		InserirZonaPressaoActionForm inserirZonaPressaoActionForm = (InserirZonaPressaoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		String descricao = inserirZonaPressaoActionForm.getDescricao();
		String descricaoAbreviada = inserirZonaPressaoActionForm.getDescricaoAbreviada();
		String distritoOperacionalID = inserirZonaPressaoActionForm.getDistritoOperacionalID();
		
		ZonaPressao zonaPressao = new ZonaPressao();
		Collection colecaoPesquisa = null;

		// Descrição
		if (!"".equals(inserirZonaPressaoActionForm.getDescricao())) {
			zonaPressao.setDescricaoZonaPressao(inserirZonaPressaoActionForm
					.getDescricao());
		} else {
			throw new ActionServletException("atencao.required", null,
					"descrição");
		}
		
		if (distritoOperacionalID != null
				&& !distritoOperacionalID.equalsIgnoreCase("")) {

			FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();

			filtroDistritoOperacional.adicionarParametro(new ParametroSimples(
					FiltroDistritoOperacional.ID, distritoOperacionalID));

			filtroDistritoOperacional.adicionarParametro(new ParametroSimples(
					FiltroDistritoOperacional.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna Distrito Operacional
			colecaoPesquisa = fachada.pesquisar(filtroDistritoOperacional,
					DistritoOperacional.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.distrito_operacional_inexistente");
			} else {
				DistritoOperacional objetoDistritoOperacional = (DistritoOperacional) Util
						.retonarObjetoDeColecao(colecaoPesquisa);
				zonaPressao.setDistritoOperacional(objetoDistritoOperacional);
			}
		}
		
		
		zonaPressao.setUltimaAlteracao(new Date());

		Short iu = 1;
		zonaPressao.setIndicadorUso(iu);

		FiltroZonaPressao filtroZonaPressao = new FiltroZonaPressao();

		filtroZonaPressao.adicionarParametro(new ParametroSimples(
				FiltroZonaPressao.DESCRICAO, zonaPressao.getDescricaoZonaPressao()));

		colecaoPesquisa = (Collection) fachada.pesquisar(
				filtroZonaPressao, ZonaPressao.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			// 
			throw new ActionServletException(
					"atencao.zona_pressao_ja_cadastrada", null,
					zonaPressao.getDescricaoZonaPressao());
		} else {
			zonaPressao.setDescricaoZonaPressao(descricao);
			
			if(inserirZonaPressaoActionForm.getDescricaoAbreviada() != null && !inserirZonaPressaoActionForm.getDescricaoAbreviada().equals("")){
				zonaPressao.setDescricaoAbreviada(descricaoAbreviada);
			} else {
				zonaPressao.setDescricaoAbreviada(null);
			}

			Integer idZonaPressao = (Integer) fachada
					.inserir(zonaPressao);

			montarPaginaSucesso(httpServletRequest,
					"Zona de Pressão " + descricao
							+ " inserida com sucesso.",
					"Inserir outra Zona de Pressão",
					"exibirInserirZonaPressaoAction.do?menu=sim",
					"exibirAtualizarZonaPressaoAction.do?idRegistroAtualizacao="
							+ idZonaPressao,
					"Atualizar Zona de Pressão Inserida");

			sessao.removeAttribute("InserirZonaPressaoActionForm");

			return retorno;
		}

	}
}