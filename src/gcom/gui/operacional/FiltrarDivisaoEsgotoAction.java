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
import gcom.operacional.DivisaoEsgoto;
import gcom.operacional.FiltroDivisaoEsgoto;
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
 * [UC0774]FILTRAR Situacao de Pagamento
 * 
 * @author Arthur Carvalho
 * @date 10/06/08
 */

public class FiltrarDivisaoEsgotoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterDivisaoEsgoto");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltrarDivisaoEsgotoActionForm filtrarDivisaoEsgotoActionForm = (FiltrarDivisaoEsgotoActionForm) actionForm;

		FiltroDivisaoEsgoto filtroDivisaoEsgoto = new FiltroDivisaoEsgoto();

		boolean peloMenosUmParametroInformado = false;

		String id = filtrarDivisaoEsgotoActionForm.getId();
		String descricao = filtrarDivisaoEsgotoActionForm.getDescricao();
		String indicadorUso= filtrarDivisaoEsgotoActionForm.getIndicadorUso();
		String tipoPesquisa = filtrarDivisaoEsgotoActionForm.getTipoPesquisa();
		String idUnidadeOrganizacional = filtrarDivisaoEsgotoActionForm.getUnidadeOrganizacionalId();
		
		// ID
		if (id != null && !id.trim().equals("")) {
			boolean achou = fachada.verificarExistenciaAgente(new Integer(id));
			if (achou) {
				peloMenosUmParametroInformado = true;
				filtroDivisaoEsgoto.adicionarParametro(new ParametroSimples(
						FiltroDivisaoEsgoto.ID, id));
			}
		}
		
		//	Unidade organizacional
		if (idUnidadeOrganizacional != null && !idUnidadeOrganizacional.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroDivisaoEsgoto.adicionarParametro(new ParametroSimples(FiltroDivisaoEsgoto.UNIDADE_ORGANIZACIONAL_ID, idUnidadeOrganizacional));
		}
		
		// Descricao
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroDivisaoEsgoto
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroDivisaoEsgoto.DESCRICAO, descricao));
			} else {

				filtroDivisaoEsgoto.adicionarParametro(new ComparacaoTexto(
						FiltroDivisaoEsgoto.DESCRICAO, descricao));
			}
		}

		// Indicador uso
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroDivisaoEsgoto.adicionarParametro(new ParametroSimples(
					FiltroDivisaoEsgoto.INDICADOR_USO, indicadorUso));
		}
		
		Collection <DivisaoEsgoto> colecaoDivisaoEsgoto = fachada
				.pesquisar(filtroDivisaoEsgoto, DivisaoEsgoto.class
						.getName());

		// Verificar a existencia de um Grupo de cadastro
		if (colecaoDivisaoEsgoto.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa_inexistente", null,"Divisao de Esgoto");
		}

		// Filtragem sem parametros
		if (!peloMenosUmParametroInformado == true) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");

		}
		
		// Pesquisa sem registros

		if (colecaoDivisaoEsgoto == null
				|| colecaoDivisaoEsgoto.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "DivisaoEsgoto");
		} else {
			httpServletRequest.setAttribute("colecaoDivisaoEsgoto",
					colecaoDivisaoEsgoto);
			DivisaoEsgoto divisaoEsgoto = new DivisaoEsgoto();
			divisaoEsgoto = (DivisaoEsgoto) Util
					.retonarObjetoDeColecao(colecaoDivisaoEsgoto);
			String idRegistroAtualizacao = divisaoEsgoto.getId().toString();
			sessao.setAttribute("idRegistroAtualizacao", idRegistroAtualizacao);
		}

		sessao.setAttribute("filtroDivisaoEsgoto", filtroDivisaoEsgoto);

		httpServletRequest.setAttribute("filtroDivisaoEsgoto",
				filtroDivisaoEsgoto);

		
		return retorno;
	}
}
