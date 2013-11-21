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
package gcom.gui.micromedicao;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1000] Informar Medidor de Energia por Rota
 * 
 * @author Hugo Leonardo
 * 
 * @date 10/03/2010
 * 
 */
public class ExibirInformarMedidorEnergiaRotaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("filtrarColetaMedidorEnergia");

		// Cria a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		String tela = null;
		String codigoSetorComercial = null;
		String idLocalidade = null;

		FiltrarColetaMedidorEnergiaActionForm form = (FiltrarColetaMedidorEnergiaActionForm) actionForm;

		if (httpServletRequest.getParameter("tela") != null) {
			tela = httpServletRequest.getParameter("tela");
			sessao.setAttribute("tela", tela);
		} else {
			if (sessao.getAttribute("tela") != null) {
				tela = (String) sessao.getAttribute("tela");
			}
		}

		// atribui os valores q vem pelo request as variaveis
		codigoSetorComercial = (String) form.getCodigoSetorComercial();
		idLocalidade = (String) form.getIdLocalidade();

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// seta o parametro para o controle de acesso a fucionalidade ou
		// operação
		httpServletRequest.setAttribute("tela", tela);

		// Pega os codigos que o usuario digitou para a pesquisa direta
		String objetoConsulta = (String) httpServletRequest
				.getParameter("objetoConsulta");

		form.setTipo("1");
		
		if (objetoConsulta != null
				&& !objetoConsulta.trim().equalsIgnoreCase("")) {

			switch (Integer.parseInt(objetoConsulta)) {

			// pesquisar Localidade
			case 1:
				pesquisarLocalidade(httpServletRequest, form);
				break;

            // pesquisar setor comercial
			case 2:
				if ((codigoSetorComercial != null && !codigoSetorComercial
						.toString().trim().equalsIgnoreCase(""))
						&& (idLocalidade != null && !idLocalidade.toString()
								.trim().equalsIgnoreCase(""))) {
					this.pesquisarSetorComercial(httpServletRequest, form, fachada, idLocalidade,
							codigoSetorComercial);
				}
				
				break;

			// pesquisar Rota
			default:
				pesquisarRota(httpServletRequest, form);
				break;
			}

		}

		return retorno;
	}

	private void pesquisarSetorComercial(HttpServletRequest httpServletRequest, 
			FiltrarColetaMedidorEnergiaActionForm form, Fachada fachada, 
			String idLocalidade, String codigoSetorComercial) {

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		if (idLocalidade != null
				&& !idLocalidade.toString().trim()
						.equalsIgnoreCase("")) {
			// coloca parametro no filtro
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.ID_LOCALIDADE, new Integer(
							idLocalidade)));
		}

		filtroSetorComercial
				.adicionarCaminhoParaCarregamentoEntidade("municipio");

		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, new Integer(
						codigoSetorComercial)));

		// pesquisa
		Collection setorComerciais = fachada.pesquisar(filtroSetorComercial,
				SetorComercial.class.getName());
		if (setorComerciais != null && !setorComerciais.isEmpty()) {
			
			// O cliente foi encontrado
			form.setCodigoSetorComercial(""
					+ ((SetorComercial) ((List) setorComerciais).get(0))
							.getCodigo());
			form.setSetorComercialDescricao(((SetorComercial) ((List) setorComerciais)
							.get(0)).getDescricao());

			form.setSetorComercialNaoEncontrado("true");
			
		} else {
			form.setCodigoSetorComercial("");
			form.setSetorComercialNaoEncontrado("exception");
			form.setSetorComercialDescricao("Setor Comercial inexistente");
		}

	}

	private void pesquisarLocalidade(HttpServletRequest httpServletRequest,
			FiltrarColetaMedidorEnergiaActionForm form) {

		Object local = form.getIdLocalidade();

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, local));

		// Recupera Localidade
		Collection colecaoLocalidade = this.getFachada().pesquisar(
				filtroLocalidade, Localidade.class.getName());

		if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {

			Localidade localidade = (Localidade) Util
					.retonarObjetoDeColecao(colecaoLocalidade);

			form.setIdLocalidade(localidade.getId().toString());
			form.setLocalidadeDescricao(localidade.getDescricao());
			form.setLocalidadeNaoEncontrada("true");

		} else {
			form.setIdLocalidade(null);
			form.setLocalidadeDescricao("Localidade inexistente");
			form.setLocalidadeNaoEncontrada("exception");
		}

	}

	private void pesquisarRota(HttpServletRequest httpServletRequest,
			FiltrarColetaMedidorEnergiaActionForm form) {

		String idRota = form.getRota();

		if (idRota != null && !idRota.trim().equals("")) {

			FiltroRota filtroRota = new FiltroRota();
			filtroRota.adicionarParametro(new ParametroSimples(
					FiltroRota.ID_ROTA, form.getRota()));

			Collection colecaoRotas = this.getFachada().pesquisar(filtroRota,
					Rota.class.getName());

			if (colecaoRotas != null && !colecaoRotas.isEmpty()) {
				Rota rota = (Rota) Util.retonarObjetoDeColecao(colecaoRotas);

				form.setRota(rota.getId().toString());
			}

		} else {
			form.setRota(null);
		}

	}

}
