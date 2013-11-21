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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Permite filtrar resoluções de diretoria [UC0219] Filtrar Resolução de
 * Diretoria
 * 
 * @author Rafael Corrêa
 * @since 31/03/2006
 */
public class ExibirFiltrarRelatorioGestaoRAAction extends GcomAction {

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

		FiltrarRelatorioGestaoRAActionForm filtrarRelatorioGestaoRAActionForm = (FiltrarRelatorioGestaoRAActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirFiltrarRelatorioGestaoRA");

		if (httpServletRequest.getParameter("menu") != null) {
			filtrarRelatorioGestaoRAActionForm.setSituacaoRA("");
		}

		// Carrega as coleções necessárias para a exibição da página
		FiltroSolicitacaoTipo filtroSolicitacaoTipo = new FiltroSolicitacaoTipo();

		filtroSolicitacaoTipo.setCampoOrderBy(FiltroSolicitacaoTipo.DESCRICAO);

		Collection<SolicitacaoTipo> colecaoSolicitacaoTipo = fachada.pesquisar(
				filtroSolicitacaoTipo, SolicitacaoTipo.class.getName());

		if (colecaoSolicitacaoTipo != null && !colecaoSolicitacaoTipo.isEmpty()) {
			httpServletRequest.setAttribute("colecaoSolicitacaoTipo",
					colecaoSolicitacaoTipo);
		}

		// FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao
		// = new FiltroSolicitacaoTipoEspecificacao();
		//
		// Collection colecaoSolicitacaoTipoEspecificacao = fachada.pesquisar(
		// filtroSolicitacaoTipoEspecificacao,
		// SolicitacaoTipoEspecificacao.class.getName());
		//
		// if (colecaoSolicitacaoTipoEspecificacao != null
		// && !colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
		// httpServletRequest.setAttribute(
		// "colecaoSolicitacaoTipoEspecificacao",
		// colecaoSolicitacaoTipoEspecificacao);
		// }

		String[] solicitacaoTipo = filtrarRelatorioGestaoRAActionForm.getSolicitacaoTipo();
		if (solicitacaoTipo != null) {
			filtrarRelatorioGestaoRAActionForm
					.setSelectedSolicitacaoTipoSize("" + solicitacaoTipo.length);
			if (solicitacaoTipo.length == 1) {
				// Filtra Solicitação Tipo Especificação
				FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
				filtroSolicitacaoTipoEspecificacao
						.adicionarParametro(new ParametroSimples(
								FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO_ID,
								solicitacaoTipo[0]));
				filtroSolicitacaoTipoEspecificacao
						.setCampoOrderBy(FiltroSolicitacaoTipoEspecificacao.DESCRICAO);

				Collection<SolicitacaoTipoEspecificacao> colecaoSolicitacaoTipoEspecificacao = fachada
						.pesquisar(filtroSolicitacaoTipoEspecificacao,
								SolicitacaoTipoEspecificacao.class.getName());

				if (colecaoSolicitacaoTipoEspecificacao != null
						&& !colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
					httpServletRequest.setAttribute(
							"colecaoSolicitacaoTipoEspecificacao",
							colecaoSolicitacaoTipoEspecificacao);
				} else {
					httpServletRequest.setAttribute(
							"colecaoSolicitacaoTipoEspecificacao",
							new ArrayList<SolicitacaoTipoEspecificacao>());
				}

			} else if (solicitacaoTipo.length == 2
					&& solicitacaoTipo[0].equals(""
							+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				// Filtra Solicitação Tipo Especificação
				FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
				filtroSolicitacaoTipoEspecificacao
						.adicionarParametro(new ParametroSimples(
								FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO_ID,
								solicitacaoTipo[1]));
				filtroSolicitacaoTipoEspecificacao
						.setCampoOrderBy(FiltroSolicitacaoTipoEspecificacao.DESCRICAO);

				Collection<SolicitacaoTipoEspecificacao> colecaoSolicitacaoTipoEspecificacao = fachada
						.pesquisar(filtroSolicitacaoTipoEspecificacao,
								SolicitacaoTipoEspecificacao.class.getName());

				if (colecaoSolicitacaoTipoEspecificacao != null
						&& !colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
					httpServletRequest.setAttribute(
							"colecaoSolicitacaoTipoEspecificacao",
							colecaoSolicitacaoTipoEspecificacao);
				} else {
					httpServletRequest.setAttribute(
							"colecaoSolicitacaoTipoEspecificacao",
							new ArrayList<SolicitacaoTipoEspecificacao>());
				}
			} else {
				httpServletRequest.setAttribute("colecaoSolicitacaoTipoEspecificacao",
						new ArrayList<SolicitacaoTipoEspecificacao>());
			}
		} else {
			filtrarRelatorioGestaoRAActionForm
					.setSelectedSolicitacaoTipoSize("0");
			httpServletRequest.setAttribute("colecaoSolicitacaoTipoEspecificacao",
					new ArrayList<SolicitacaoTipoEspecificacao>());
		}

		String idUnidade = filtrarRelatorioGestaoRAActionForm.getIdUnidade();
		String nomeUnidade = filtrarRelatorioGestaoRAActionForm
				.getNomeUnidade();

		// Verifica se o usuário solicitou a pesquisa de unidade organizacional
		if ((idUnidade != null && !idUnidade.trim().equals(""))
				&& (nomeUnidade == null || nomeUnidade.equals(""))) {
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

			filtroUnidadeOrganizacional
					.adicionarParametro(new ParametroSimples(
							FiltroUnidadeOrganizacional.ID, idUnidade));

			Collection<UnidadeOrganizacional> colecaoUnidade = fachada.pesquisar(
					filtroUnidadeOrganizacional, UnidadeOrganizacional.class
							.getName());

			if (colecaoUnidade != null && !colecaoUnidade.isEmpty()) {
				UnidadeOrganizacional unidade = (UnidadeOrganizacional) Util
						.retonarObjetoDeColecao(colecaoUnidade);

				filtrarRelatorioGestaoRAActionForm.setNomeUnidade(unidade
						.getDescricao());
				httpServletRequest.setAttribute("nomeCampo",
						"idUnidadeSuperior");

			} else {
				filtrarRelatorioGestaoRAActionForm.setIdUnidade("");
				filtrarRelatorioGestaoRAActionForm
						.setNomeUnidade("UNIDADE INEXISTENTE");

				httpServletRequest.setAttribute("nomeCampo", "idUnidade");
				httpServletRequest.setAttribute("idUnidadeNaoEncontrado", true);
			}
		} else if ((nomeUnidade != null && !nomeUnidade.trim().equals(""))
				&& (idUnidade == null || idUnidade.equals(""))) {
			filtrarRelatorioGestaoRAActionForm.setNomeUnidade("");
		}

		String idUnidadeSuperior = filtrarRelatorioGestaoRAActionForm
				.getIdUnidadeSuperior();
		String nomeUnidadeSuperior = filtrarRelatorioGestaoRAActionForm
				.getNomeUnidadeSuperior();

		// Verifica se o usuário solicitou a pesquisa de unidade superior
		if ((idUnidadeSuperior != null && !idUnidadeSuperior.trim().equals(""))
				&& (nomeUnidadeSuperior == null || nomeUnidadeSuperior
						.equals(""))) {
			FiltroUnidadeOrganizacional filtroUnidadeSuperior = new FiltroUnidadeOrganizacional();

			filtroUnidadeSuperior.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID_UNIDADE_SUPERIOR,
					idUnidadeSuperior));

			Collection<UnidadeOrganizacional> colecaoUnidadeSuperior = fachada.pesquisar(
					filtroUnidadeSuperior, UnidadeOrganizacional.class
							.getName());

			if (colecaoUnidadeSuperior != null
					&& !colecaoUnidadeSuperior.isEmpty()) {
				UnidadeOrganizacional unidadeSuperior = (UnidadeOrganizacional) Util
						.retonarObjetoDeColecao(colecaoUnidadeSuperior);

				filtrarRelatorioGestaoRAActionForm
						.setNomeUnidadeSuperior(unidadeSuperior.getDescricao());
				httpServletRequest.setAttribute("nomeCampo", "idMunicipio");

			} else {
				filtrarRelatorioGestaoRAActionForm.setIdUnidadeSuperior("");
				filtrarRelatorioGestaoRAActionForm
						.setNomeUnidadeSuperior("UNIDADE SUPERIOR INEXISTENTE");

				httpServletRequest.setAttribute("nomeCampo",
						"idUnidadeSuperior");
				httpServletRequest.setAttribute("unidadeSuperiorNaoEncontrada",
						true);
			}
		} else if ((nomeUnidadeSuperior != null && !nomeUnidadeSuperior.trim()
				.equals(""))
				&& (idUnidadeSuperior == null || idUnidadeSuperior.equals(""))) {
			filtrarRelatorioGestaoRAActionForm.setNomeUnidadeSuperior("");
		}

		String idMunicipio = filtrarRelatorioGestaoRAActionForm
				.getIdMunicipio();
		String nomeMunicipio = filtrarRelatorioGestaoRAActionForm
				.getNomeMunicipio();

		// Verifica se o usuário solicitou a pesquisa de município
		if ((idMunicipio != null && !idMunicipio.trim().equals(""))
				&& (nomeMunicipio == null || nomeMunicipio.equals(""))) {
			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.ID, idMunicipio));

			Collection<Municipio> colecaoMunicipio = fachada.pesquisar(filtroMunicipio,
					Municipio.class.getName());

			if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {
				Municipio municipio = (Municipio) Util
						.retonarObjetoDeColecao(colecaoMunicipio);

				filtrarRelatorioGestaoRAActionForm.setNomeMunicipio(municipio
						.getNome());
				httpServletRequest.setAttribute("nomeCampo", "idBairro");

			} else {
				filtrarRelatorioGestaoRAActionForm.setIdMunicipio("");
				filtrarRelatorioGestaoRAActionForm
						.setNomeMunicipio("MUNICIPIO INEXISTENTE");

				filtrarRelatorioGestaoRAActionForm.setIdBairro("");
				filtrarRelatorioGestaoRAActionForm.setNomeBairro("");

				httpServletRequest.setAttribute("nomeCampo", "idMunicipio");
				httpServletRequest.setAttribute("municipioNaoEncontrado", true);
			}
		} else if ((nomeMunicipio != null && !nomeMunicipio.trim().equals(""))
				&& (idMunicipio == null || idMunicipio.equals(""))) {
			filtrarRelatorioGestaoRAActionForm.setNomeMunicipio("");
		}

		String codigoBairro = filtrarRelatorioGestaoRAActionForm.getIdBairro();
		String nomeBairro = filtrarRelatorioGestaoRAActionForm.getNomeBairro();

		// Verifica se o usuário solicitou a pesquisa de bairro
		if ((codigoBairro != null && !codigoBairro.trim().equals(""))
				&& (nomeBairro == null || nomeBairro.equals(""))) {
			FiltroBairro filtroBairro = new FiltroBairro();
			filtroBairro.adicionarParametro(new ParametroSimples(
					FiltroBairro.CODIGO, codigoBairro));
			filtroBairro.adicionarParametro(new ParametroSimples(
					FiltroBairro.MUNICIPIO_ID, idMunicipio));

			Collection<Bairro> colecaoBairro = fachada.pesquisar(filtroBairro,
					Bairro.class.getName());

			if (colecaoBairro != null && !colecaoBairro.isEmpty()) {
				Bairro bairro = (Bairro) Util
						.retonarObjetoDeColecao(colecaoBairro);

				filtrarRelatorioGestaoRAActionForm.setNomeBairro(bairro
						.getNome());

			} else {
				filtrarRelatorioGestaoRAActionForm.setIdBairro("");
				filtrarRelatorioGestaoRAActionForm
						.setNomeBairro("BAIRRO INEXISTENTE");

				httpServletRequest.setAttribute("nomeCampo", "idBairro");
				httpServletRequest.setAttribute("bairroNaoEncontrado", true);
			}
		} else if ((nomeBairro != null && !nomeBairro.trim().equals(""))
				&& (codigoBairro == null || codigoBairro.equals(""))) {
			filtrarRelatorioGestaoRAActionForm.setNomeBairro("");
		}

		return retorno;

	}

}
