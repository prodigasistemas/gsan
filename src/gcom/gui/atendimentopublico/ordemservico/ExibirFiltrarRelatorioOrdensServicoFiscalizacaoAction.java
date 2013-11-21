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
 * Ivan Sérgio Virginio da Silva Júnior
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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroOSReferidaRetornoTipo;
import gcom.atendimentopublico.ordemservico.OsReferidaRetornoTipo;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1213] Emitir Relatorio de Ordem de Sercico de Fiscalizacao
 * 
 * @author Paulo Diniz
 * @date 09/08/2011
 * 
 */
public class ExibirFiltrarRelatorioOrdensServicoFiscalizacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirFiltrar");

		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		ExibirFiltrarRelatorioOrdensServicoFiscalizacaoActionForm relatorioActionForm = (ExibirFiltrarRelatorioOrdensServicoFiscalizacaoActionForm) actionForm;

		// Gerencia Regional
		if (sessao.getAttribute("collGerenciaRegional") == null) {
			FiltroGerenciaRegional filtroGerencia = new FiltroGerenciaRegional(FiltroGerenciaRegional.NOME);
			filtroGerencia.adicionarParametro(new ParametroSimples(	FiltroGerenciaRegional.INDICADOR_USO,
																	"1"));
			Collection collGerenciaRegional = fachada.pesquisar(filtroGerencia, GerenciaRegional.class.getName());
			sessao.setAttribute("collGerenciaRegional", collGerenciaRegional);
		}

		// Unidade de Negocio
		if (sessao.getAttribute("collUnidadeNegocio") == null) {
			FiltroUnidadeNegocio filtroUnidade = new FiltroUnidadeNegocio(FiltroUnidadeNegocio.NOME);
			filtroUnidade.adicionarParametro(new ParametroSimples(	FiltroUnidadeNegocio.INDICADOR_USO,
																	"1"));
			Collection collUnidadeNegocio = fachada.pesquisar(filtroUnidade, UnidadeNegocio.class.getName());
			sessao.setAttribute("collUnidadeNegocio", collUnidadeNegocio);
		}

		// Tipo de Retorno
		if (sessao.getAttribute("collTipoRetorno") == null) {
			FiltroOSReferidaRetornoTipo filtroRetornoTipo = new FiltroOSReferidaRetornoTipo(FiltroOSReferidaRetornoTipo.DESCRICAO);
			filtroRetornoTipo.adicionarParametro(new ParametroSimples(	FiltroOSReferidaRetornoTipo.INDICADOR_USO,
																		"1"));
			Collection collTipoRetorno = fachada.pesquisar(filtroRetornoTipo, OsReferidaRetornoTipo.class.getName());
			sessao.setAttribute("collTipoRetorno", collTipoRetorno);
		}

		// Localidade Inicial
		String idLocalidadeInicio = pesquisarLocalidadeInicial(httpServletRequest, fachada, relatorioActionForm);

		// Localidade Final
		pesquisarLocalidadeFinal(httpServletRequest, fachada, relatorioActionForm, idLocalidadeInicio);

		return retorno;
	}

	private String pesquisarLocalidadeInicial(HttpServletRequest httpServletRequest, Fachada fachada,
			ExibirFiltrarRelatorioOrdensServicoFiscalizacaoActionForm form) {
		String idLocalidadeInicial = form.getLocalidadeInicial();
		Localidade localidadeInicial = null;

		if (idLocalidadeInicial != null && !idLocalidadeInicial.trim().equals("")) {
			FiltroLocalidade filtroLocalidadeInicial = new FiltroLocalidade();
			filtroLocalidadeInicial.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID,
																			idLocalidadeInicial));

			Collection colecaoLocalidadeInicial = fachada.pesquisar(filtroLocalidadeInicial, Localidade.class.getName());

			if (colecaoLocalidadeInicial != null && !colecaoLocalidadeInicial.isEmpty()) {
				localidadeInicial = (Localidade) colecaoLocalidadeInicial.iterator().next();
				form.setDescricaoLocalidadeInicial(localidadeInicial.getDescricao());
			} else {
				httpServletRequest.setAttribute("localidadeInicialInexistente", "true");
				form.setLocalidadeInicial("");
				form.setDescricaoLocalidadeInicial("LOCALIDADE INEXISTENTE");
			}
		} else {
			form.setDescricaoLocalidadeInicial("");
		}
		return idLocalidadeInicial;
	}

	private void pesquisarLocalidadeFinal(HttpServletRequest httpServletRequest, Fachada fachada,
			ExibirFiltrarRelatorioOrdensServicoFiscalizacaoActionForm form, String idLocalidadeInicial) {
		String idLocalidadeFinal = form.getLocalidadeFinal();
		Localidade localidadeFinal = null;

		if (idLocalidadeFinal != null && !idLocalidadeFinal.trim().equals("")) {
			if (idLocalidadeInicial != null && !idLocalidadeInicial.trim().equals("")) {
				if ((new Integer(idLocalidadeInicial)).compareTo(new Integer(idLocalidadeFinal)) > 0) {
					throw new ActionServletException("atencao.localidade.final.maior.localidade.inicial");
				}
			}

			FiltroLocalidade filtroLocalidadeFinal = new FiltroLocalidade();
			filtroLocalidadeFinal.adicionarParametro(new ParametroSimples(	FiltroLocalidade.ID,
																			idLocalidadeFinal));

			Collection colecaoLocalidadeFinal = fachada.pesquisar(filtroLocalidadeFinal, Localidade.class.getName());

			if (colecaoLocalidadeFinal != null && !colecaoLocalidadeFinal.isEmpty()) {
				localidadeFinal = (Localidade) colecaoLocalidadeFinal.iterator().next();
				form.setDescricaoLocalidadeFinal(localidadeFinal.getDescricao());
			} else {
				httpServletRequest.setAttribute("localidadeFinalInexistente", "true");
				form.setLocalidadeFinal("");
				form.setDescricaoLocalidadeFinal("LOCALIDADE INEXISTENTE");
			}
		} else {
			form.setDescricaoLocalidadeFinal("");
		}
	}

}