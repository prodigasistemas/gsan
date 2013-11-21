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
package gcom.gui.cobranca;

import gcom.cobranca.CobrancaCriterioLinha;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Pre- processamento para adicionar a linha do criterio da cobrança
 * 
 * @author Sávio Luiz
 * @date 02/06/2006
 */
public class ExibirAtualizarCriterioCobrancaLinhaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarCriterioCobrancaLinha");

		CriterioCobrancaActionForm criterioCobrancaActionForm = (CriterioCobrancaActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		// parâmetro responsável pelo redirecionamento do adicionar criterio
		// cobrança linha
		// se retornarTela estiver o valor inserir retorna para o action de
		// inserir
		// se retornarTela estiver o valor atualizar retorna para o action de
		// atualizar
		String retornarTela = httpServletRequest.getParameter("retornarTela");
		sessao.setAttribute("retornarTela", retornarTela);

		String parmsImovelPerfil = httpServletRequest
				.getParameter("parmsImovelPerfilCobranca");
		String[] arrayImovelPerfilCategoria = parmsImovelPerfil.split(",");

		Integer idImovelPerfil = new Integer(arrayImovelPerfilCategoria[0]);
		Integer idCategoria = new Integer(arrayImovelPerfilCategoria[1]);

		if (sessao.getAttribute("colecaoCobrancaCriterioLinha") != null
				&& !sessao.getAttribute("colecaoCobrancaCriterioLinha").equals(
						"")) {
			Collection colecaoCobrancaCriterioLinha = (Collection) sessao
					.getAttribute("colecaoCobrancaCriterioLinha");
			// cria as variáveis para recuperar os parâmetros do request e jogar
			// no objeto
			// cobrança critério linha
			String vlMinDebito = "";
			String vlMaxDebito = "";
			String qtdMinContas = "";
			String qtdMaxContas = "";
			String vlMinDebCliente = "";
			String qtdMinConCliente = "";
			String vlMinConMes = "";
			String qdtParcelasMinimas = "";

			Iterator iteratorCobrancaCriterioLinha = colecaoCobrancaCriterioLinha
					.iterator();
			while (iteratorCobrancaCriterioLinha.hasNext()) {
				CobrancaCriterioLinha cobrancaCriterioLinha = (CobrancaCriterioLinha) iteratorCobrancaCriterioLinha
						.next();
				// se o id do imovel perfil que vem do request for igual ao id
				// imovel perfil a coleção de criterio linha e o id da categoria
				// que vem do request for igual ao id categoria a coleção de
				// criterio linha
				if (idImovelPerfil != null
						&& idImovelPerfil.equals(cobrancaCriterioLinha
								.getImovelPerfil().getId())) {
					if (idCategoria != null
							&& idCategoria.equals(cobrancaCriterioLinha
									.getCategoria().getId())) {
						// formata os valores para jogar no form
						if (cobrancaCriterioLinha.getValorMinimoDebito() != null
								&& !cobrancaCriterioLinha
										.getValorMinimoDebito().equals("")) {
							vlMinDebito = Util
									.formatarMoedaReal(cobrancaCriterioLinha
											.getValorMinimoDebito());
						}
						if (cobrancaCriterioLinha.getValorMaximoDebito() != null
								&& !cobrancaCriterioLinha
										.getValorMaximoDebito().equals("")) {
							vlMaxDebito = Util
									.formatarMoedaReal(cobrancaCriterioLinha
											.getValorMaximoDebito());
						}
						if (cobrancaCriterioLinha.getQuantidadeMinimaContas() != null
								&& !cobrancaCriterioLinha
										.getQuantidadeMinimaContas().equals("")) {
							qtdMinContas = ""
									+ cobrancaCriterioLinha
											.getQuantidadeMinimaContas();
						}
						if (cobrancaCriterioLinha.getQuantidadeMaximaContas() != null
								&& !cobrancaCriterioLinha
										.getQuantidadeMaximaContas().equals("")) {
							qtdMaxContas = ""
									+ cobrancaCriterioLinha
											.getQuantidadeMaximaContas();
						}
						if (cobrancaCriterioLinha
								.getValorMinimoDebitoDebitoAutomatico() != null
								&& !cobrancaCriterioLinha
										.getValorMinimoDebitoDebitoAutomatico()
										.equals("")) {
							vlMinDebCliente = Util
									.formatarMoedaReal(cobrancaCriterioLinha
											.getValorMinimoDebitoDebitoAutomatico());
						}
						if (cobrancaCriterioLinha
								.getQuantidadeMinimaContasDebitoAutomatico() != null
								&& !cobrancaCriterioLinha
										.getQuantidadeMinimaContasDebitoAutomatico()
										.equals("")) {
							qtdMinConCliente = ""
									+ cobrancaCriterioLinha
											.getQuantidadeMinimaContasDebitoAutomatico();
						}
						if (cobrancaCriterioLinha.getValorMinimoContaMes() != null
								&& !cobrancaCriterioLinha
										.getValorMinimoContaMes().equals("")) {
							vlMinConMes = Util
									.formatarMoedaReal(cobrancaCriterioLinha
											.getValorMinimoContaMes());
						}
						if (cobrancaCriterioLinha
								.getQuantidadeMinimaContasParcelamento() != null
								&& !cobrancaCriterioLinha
										.getQuantidadeMinimaContasParcelamento()
										.equals("")) {
							qdtParcelasMinimas = cobrancaCriterioLinha
									.getQuantidadeMinimaContasParcelamento()
									.toString();

						} else {
							qdtParcelasMinimas = "0";
						}
						// seta os valores da ultima linha da cobrança criterio
						criterioCobrancaActionForm
								.setValorDebitoMinimo(vlMinDebito);
						criterioCobrancaActionForm
								.setValorDebitoMaximo(vlMaxDebito);
						criterioCobrancaActionForm
								.setQtdContasMinima(qtdMinContas);
						criterioCobrancaActionForm
								.setQtdContasMaxima(qtdMaxContas);
						criterioCobrancaActionForm
								.setVlMinimoDebitoCliente(vlMinDebCliente);
						criterioCobrancaActionForm
								.setQtdMinContasCliente(qtdMinConCliente);
						criterioCobrancaActionForm
								.setVlMinimoContasMes(vlMinConMes);
						criterioCobrancaActionForm
								.setDescricaoImovelPerfil(cobrancaCriterioLinha
										.getImovelPerfil().getDescricao());
						criterioCobrancaActionForm
								.setDescricaoCategoria(cobrancaCriterioLinha
										.getCategoria().getDescricao());
						criterioCobrancaActionForm
								.setQuantidadeMinimaParcelasAtraso(qdtParcelasMinimas);

						sessao.setAttribute("cobrancaCriteriolinha",
								cobrancaCriterioLinha);
					}

				}

			}

			httpServletRequest.setAttribute("fechaPopup", "false");
		}

		return retorno;
	}
}
