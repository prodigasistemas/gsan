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
package gcom.gui.micromedicao.hidrometro;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroDiametro;
import gcom.micromedicao.hidrometro.HidrometroDiametro;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0792]Filtrar Diametro do Hidrometro
 * 
 * @author Vinicius Medeiros
 * @date 16/05/2008
 */

public class FiltrarHidrometroDiametroAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterHidrometroDiametro");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltrarHidrometroDiametroActionForm filtrarHidrometroDiametroActionForm = (FiltrarHidrometroDiametroActionForm) actionForm;

		FiltroHidrometroDiametro filtroHidrometroDiametro = new FiltroHidrometroDiametro();

		boolean peloMenosUmParametroInformado = false;

		String id = filtrarHidrometroDiametroActionForm.getId();
		String descricao = filtrarHidrometroDiametroActionForm.getDescricao();
		String descricaoAbreviada = filtrarHidrometroDiametroActionForm.getDescricaoAbreviada();
		String numeroOrdem = filtrarHidrometroDiametroActionForm
				.getNumeroOrdem();
		String indicadorUso = filtrarHidrometroDiametroActionForm
				.getIndicadorUso();
		String tipoPesquisa = filtrarHidrometroDiametroActionForm
				.getTipoPesquisa();

		// ID
		if (id != null && !id.trim().equals("")) {
				peloMenosUmParametroInformado = true;
				filtroHidrometroDiametro.adicionarParametro(new ParametroSimples(
						FiltroHidrometroDiametro.ID, id));	
		}
		// Descricao
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroHidrometroDiametro
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroHidrometroDiametro.DESCRICAO, descricao));
			} else {

				filtroHidrometroDiametro.adicionarParametro(new ComparacaoTexto(
						FiltroHidrometroDiametro.DESCRICAO, descricao));
			}
		}
		
		// Descricao Abreviada
		if (descricaoAbreviada != null
				&& !descricaoAbreviada.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;
			filtroHidrometroDiametro
					.adicionarParametro(new ComparacaoTextoCompleto(
							FiltroHidrometroDiametro.DESCRICAO_ABREVIADA,
							descricaoAbreviada));
		} else {

			filtroHidrometroDiametro.adicionarParametro(new ComparacaoTexto(
					FiltroHidrometroDiametro.DESCRICAO_ABREVIADA,
					descricaoAbreviada));
		}

		// Número da Ordem
		if (numeroOrdem != null
				&& !numeroOrdem.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;
			filtroHidrometroDiametro
					.adicionarParametro(new ParametroSimples(
							FiltroHidrometroDiametro.NUMERO_ORDEM,
							numeroOrdem));
		} 
		


		// Indicador uso
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroHidrometroDiametro.adicionarParametro(new ParametroSimples(
					FiltroHidrometroDiametro.INDICADOR_USO, indicadorUso));
		}
		
		Collection colecaoHidrometroDiametro = fachada
				.pesquisar(filtroHidrometroDiametro, HidrometroDiametro.class
						.getName());

		// Verificar a existencia de um Diametro do Hidrometro
		if (colecaoHidrometroDiametro.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa_inexistente", null,"Diâmetro do Hidrômetro");
		}

		// Filtragem sem parametros
		if (!peloMenosUmParametroInformado == true) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");

		}
		
		// Pesquisa sem registros

		if (colecaoHidrometroDiametro == null
				|| colecaoHidrometroDiametro.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Diâmetro do Hidrômetro");
		} else {
			httpServletRequest.setAttribute("colecaoHidrometroDiametro",
					colecaoHidrometroDiametro);
			HidrometroDiametro hidrometroDiametro = new HidrometroDiametro();
			hidrometroDiametro = (HidrometroDiametro) Util
					.retonarObjetoDeColecao(colecaoHidrometroDiametro);
			String idRegistroAtualizacao = hidrometroDiametro.getId().toString();
			sessao.setAttribute("idRegistroAtualizacao", idRegistroAtualizacao);
		}

		sessao.setAttribute("filtroHidrometroDiametro", filtroHidrometroDiametro);

		httpServletRequest.setAttribute("filtroHidrometroDiametro",
				filtroHidrometroDiametro);

		return retorno;

	}
}
