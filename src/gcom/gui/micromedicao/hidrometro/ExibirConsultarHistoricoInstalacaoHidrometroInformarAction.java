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

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometro;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExibirConsultarHistoricoInstalacaoHidrometroInformarAction extends
		GcomAction {
	/**
	 * < <Descrição do método>>
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

		// Seta a ação de retorno
		ActionForward retorno = actionMapping
				.findForward("consultarHidrometro");
		HttpSession sessao = httpServletRequest.getSession(false);
		// Obtém a facahda
		Fachada fachada = Fachada.getInstancia();

		ConsultarHistoricoInstalacaoHidrometroActionForm form = (ConsultarHistoricoInstalacaoHidrometroActionForm) actionForm;

		// Pega os codigos que o usuario digitou para a pesquisa direta de
		// imovel ou hidrometro
		String codigoImovel = "";
		String codigoHidrometro = "";

		/*
		 * if ((codigoImovel == null || codigoImovel.trim().equals("")) &&
		 * (codigoHidrometro == null || codigoHidrometro.trim().equals(""))){
		 * codigoImovel = "" + sessao.getAttribute("codigoImovel");
		 * codigoHidrometro = "" + sessao.getAttribute("codigoHidrometro"); }
		 */

		if (httpServletRequest.getParameter("menu") != null
				&& httpServletRequest.getParameter("menu").equalsIgnoreCase(
						"sim")) {
			httpServletRequest.setAttribute("nomeCampo", "codigoImovel");
		}

		if ((sessao.getAttribute("codigoImovel") != null && !sessao
				.getAttribute("codigoImovel").equals(""))
				|| (sessao.getAttribute("codigoHidrometro") != null && !sessao
						.getAttribute("codigoHidrometro").equals(""))) {
			codigoImovel = "" + sessao.getAttribute("codigoImovel");
			codigoHidrometro = "" + sessao.getAttribute("codigoHidrometro");
		} else {
			if (httpServletRequest.getParameter("codigoImovel") != null) {
				codigoImovel = httpServletRequest.getParameter("codigoImovel");
			} else {
				codigoImovel = form.getCodigoImovel();
			}
			if (httpServletRequest.getParameter("codigoHidrometro") != null) {

				codigoHidrometro = httpServletRequest
						.getParameter("codigoHidrometro");
			} else {
				codigoHidrometro = form.getCodigoHidrometro();
			}

		}

		/*
		 * if (httpServletRequest.getAttribute("limpar") != null &&
		 * httpServletRequest.getAttribute("limpar").equals("N")){ codigoImovel
		 * = "" + sessao.getAttribute("codigoImovel"); codigoHidrometro = "" +
		 * sessao.getAttribute("codigoHidrometro"); }else{ codigoImovel =
		 * httpServletRequest.getParameter("codigoImovel"); codigoHidrometro =
		 * httpServletRequest.getParameter("codigoHidrometro"); }
		 */

		if (codigoImovel != null && !codigoImovel.trim().equals("")) {
			// Pesquisa o imovel na base
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.ID, codigoImovel));

			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");

			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");

			/*
			 * TODO - COSANPA Adição dos filtros para perimetro inicial e
			 * perimetro final do endereço do imóvel Correção para o Mantis 206
			 */
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTipo");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTitulo");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTipo");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTitulo");

			Collection<Imovel> imovelPesquisado = (Collection<Imovel>) fachada
					.pesquisar(filtroImovel, Imovel.class.getName());

			// Se nenhum imovel for encontrado a mensagem é enviada para a
			// página
			if (imovelPesquisado != null && imovelPesquisado.isEmpty()) {
				httpServletRequest.setAttribute("codigoImovel", "");
				httpServletRequest.setAttribute("inscricaoImovel",
						"Matrícula Inexistente");
			}

			// obtem o imovel pesquisado
			if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {
				Imovel imovel = imovelPesquisado.iterator().next();
				form.setCodigoImovel(codigoImovel);
				form.setInscricaoImovel(imovel.getInscricaoFormatada());
				// Manda o Imovel pelo request
				httpServletRequest.setAttribute("inscricaoImovel",
						imovel.getInscricaoFormatada());
				httpServletRequest.setAttribute("enderecoFormatado",
						imovel.getEnderecoFormatado());
				httpServletRequest.setAttribute("codigoImovel", codigoImovel);
			}

		}

		if (codigoHidrometro != null && !codigoHidrometro.trim().equals("")) {
			codigoHidrometro = codigoHidrometro.toUpperCase();
			// Pesquisa o hidrometro na base
			FiltroHidrometro filtroHidrometro = new FiltroHidrometro();
			filtroHidrometro
					.adicionarCaminhoParaCarregamentoEntidade("hidrometroMarca");
			filtroHidrometro.adicionarParametro(new ParametroSimples(
					FiltroHidrometro.NUMERO_HIDROMETRO, codigoHidrometro));

			Collection<Hidrometro> hidrometroPesquisado = fachada.pesquisar(
					filtroHidrometro, Hidrometro.class.getName());

			// Se nenhum hidrometro for encontrado a mensagem é enviada para a
			// página
			if (hidrometroPesquisado != null && hidrometroPesquisado.isEmpty()) {
				httpServletRequest.setAttribute("descricaoHidrometro",
						"Hidrômetro Inexistente");
				form.setDescricaoHidrometro("Hidrômetro Inexistente");
				httpServletRequest.setAttribute("codigoHidrometro", "");

			}

			// obtem o hidrometro pesquisado
			if (hidrometroPesquisado != null && !hidrometroPesquisado.isEmpty()) {
				Hidrometro hidrometro = hidrometroPesquisado.iterator().next();
				form.setCodigoHidrometro(codigoHidrometro);
				// Manda o Hidrometro pelo request
				httpServletRequest.setAttribute("descricaoHidrometro",
						hidrometro.getHidrometroMarca().getDescricao().trim()
								+ " FAB.: " + hidrometro.getAnoFabricacao());
				form.setDescricaoHidrometro(hidrometro.getHidrometroMarca()
						.getDescricao().trim()
						+ " FAB.: " + hidrometro.getAnoFabricacao());
				httpServletRequest.setAttribute("codigoHidrometro",
						hidrometro.getNumero());
			}

		}

		sessao.removeAttribute("codigoImovel");
		sessao.removeAttribute("codigoHidrometro");

		return retorno;

	}

}
