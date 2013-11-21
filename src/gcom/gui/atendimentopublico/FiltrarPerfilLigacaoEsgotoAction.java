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
package gcom.gui.atendimentopublico;


import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoPerfil;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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
 * [UC0861]FILTRAR Perfil da Ligacao de Esgoto
 * 
 * @author Arthur Carvalho
 * @date 17/10/08
 */

public class FiltrarPerfilLigacaoEsgotoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterPerfilLigacaoEsgoto");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltrarPerfilLigacaoEsgotoActionForm filtrarPerfilLigacaoEsgotoActionForm = (FiltrarPerfilLigacaoEsgotoActionForm) actionForm;

		FiltroLigacaoEsgotoPerfil filtroLigacaoEsgotoPerfil = new FiltroLigacaoEsgotoPerfil();

		boolean peloMenosUmParametroInformado = false;

		String id = filtrarPerfilLigacaoEsgotoActionForm.getId();
		String descricao = filtrarPerfilLigacaoEsgotoActionForm.getDescricao();
		String indicadorUso = filtrarPerfilLigacaoEsgotoActionForm.getIndicadorUso();
		String tipoPesquisa = filtrarPerfilLigacaoEsgotoActionForm.getTipoPesquisa();
		
		String percentualEsgotoConsumidaColetada = filtrarPerfilLigacaoEsgotoActionForm.getPercentualEsgotoConsumidaColetada().replace(",", ".");
		

		
		// ID
		if (id != null && !id.trim().equals("")) {
			boolean achou = fachada.verificarExistenciaAgente(new Integer(id));
			if (achou) {
				peloMenosUmParametroInformado = true;
				filtroLigacaoEsgotoPerfil.adicionarParametro(new ParametroSimples(
						FiltroLigacaoEsgotoPerfil.ID, id));
			}
		}
		// Descricao
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroLigacaoEsgotoPerfil
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroLigacaoEsgotoPerfil.DESCRICAO, descricao));
			} else {

				filtroLigacaoEsgotoPerfil.adicionarParametro(new ComparacaoTexto(
						FiltroLigacaoEsgotoPerfil.DESCRICAO, descricao));
			}
		}
		
		// Percentual de Esgoto
		if (percentualEsgotoConsumidaColetada != null
				&& !percentualEsgotoConsumidaColetada.trim().equals("")) {

			peloMenosUmParametroInformado = true;
			filtroLigacaoEsgotoPerfil
					.adicionarParametro(new ParametroSimples(
							FiltroLigacaoEsgotoPerfil.PERCENTUAL, percentualEsgotoConsumidaColetada));
		} 
	
		// Indicador uso
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroLigacaoEsgotoPerfil.adicionarParametro(new ParametroSimples(
					FiltroLigacaoEsgotoPerfil.INDICADOR_USO, indicadorUso));
		}
		
		Collection <LigacaoEsgotoPerfil> colecaoLigacaoEsgotoPerfil = fachada
				.pesquisar(filtroLigacaoEsgotoPerfil, LigacaoEsgotoPerfil.class
						.getName());

		// Verificar a existencia de um Grupo de cadastro
		if (colecaoLigacaoEsgotoPerfil.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa_inexistente", null,"Perfil da Ligação de Esgoto");
		}

		// Filtragem sem parametros
		if (!peloMenosUmParametroInformado == true) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");

		}
		
		// Pesquisa sem registros

		if (colecaoLigacaoEsgotoPerfil == null
				|| colecaoLigacaoEsgotoPerfil.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Perfil da Ligação de Esgoto");
		} else {
			httpServletRequest.setAttribute("colecaoLigacaoEsgotoPerfil",
					colecaoLigacaoEsgotoPerfil);
			LigacaoEsgotoPerfil ligacaoEsgotoPerfil = new LigacaoEsgotoPerfil();
			ligacaoEsgotoPerfil = (LigacaoEsgotoPerfil) Util
					.retonarObjetoDeColecao(colecaoLigacaoEsgotoPerfil);
			String idRegistroAtualizar = ligacaoEsgotoPerfil.getId().toString();
			sessao.setAttribute("idRegistroAtualizar", idRegistroAtualizar);
		}

		sessao.setAttribute("filtroLigacaoEsgotoPerfil", filtroLigacaoEsgotoPerfil);

		httpServletRequest.setAttribute("filtroLigacaoEsgotoPerfil",
				filtroLigacaoEsgotoPerfil);

		
		return retorno;
	}
}
