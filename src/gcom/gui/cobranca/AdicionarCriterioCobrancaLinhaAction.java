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

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cobranca.CobrancaCriterioLinha;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processamento para adicionar a linha do criterio da cobrança
 * 
 * @author Sávio Luiz
 * @date 02/05/2006
 */
public class AdicionarCriterioCobrancaLinhaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("adicionarCriterioCobrancaLinha");

		CriterioCobrancaActionForm criterioCobrancaActionForm = (CriterioCobrancaActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		// recupera o par imovel perfil que tem o id e a descricao do imovel
		// perfil
		// separado por ;
		String[] parImovelPerfil = criterioCobrancaActionForm
				.getParImovelPerfil();
		// String[] arrayImovelPerfil = parImovelPerfil.split(";");

		Collection colecaoCobrancaCriterioLinha = null;

		if (sessao.getAttribute("colecaoCobrancaCriterioLinha") != null
				&& !sessao.getAttribute("colecaoCobrancaCriterioLinha").equals(
						"")) {
			colecaoCobrancaCriterioLinha = (Collection) sessao
					.getAttribute("colecaoCobrancaCriterioLinha");
		} else {
			colecaoCobrancaCriterioLinha = new ArrayList();
		}

		if (parImovelPerfil != null && !parImovelPerfil.equals("")) {

			for (int i = 0; i < parImovelPerfil.length; i++) {
				// cria o imovel perfil para ser inserido na cobranca criterio
				// linha
				String[] arrayImovelPerfil = parImovelPerfil[i].split(";");
				ImovelPerfil imovelPerfil = new ImovelPerfil();
				imovelPerfil.setId(new Integer(arrayImovelPerfil[0]));
				imovelPerfil.setDescricao(arrayImovelPerfil[1]);

				// recupera o par categoria que tem o id e a descricao da
				// categoria
				// separado por ;
				String[] parCategoria = criterioCobrancaActionForm
						.getParCategoria();

				if (parCategoria != null && !parCategoria.equals("")) {

					for (int j = 0; j < parCategoria.length; j++) {
						// cria a categoria para ser inserido na cobranca
						// criterio
						// linha
						String[] arrayCategoria = parCategoria[j].split(";");
						Categoria categoria = new Categoria();
						categoria.setId(new Integer(arrayCategoria[0]));
						categoria.setDescricao(arrayCategoria[1]);

						// faz um iterator para verificar se existe na coleção o
						// imovel
						// perfil
						// e a categoria que foram escolhidos
						Iterator iteratorCobrancaCriterioLinha = colecaoCobrancaCriterioLinha
								.iterator();
						while (iteratorCobrancaCriterioLinha.hasNext()) {
							CobrancaCriterioLinha cobrancaCriterioLinha = (CobrancaCriterioLinha) iteratorCobrancaCriterioLinha
									.next();
							if (cobrancaCriterioLinha.getImovelPerfil().getId()
									.equals(imovelPerfil.getId())
									&& cobrancaCriterioLinha.getCategoria()
											.getId().equals(categoria.getId())) {
								throw new ActionServletException(
										"atencao.imovel.perfil.categoria.informados");
							}
						}

						// cria a cobranca criterio linha para ser exibido na
						// tela
						// de
						// inserir
						// verifica se o valor maximo é menor que o mínimo
						BigDecimal valorDebitoMinimo = null;
						if (criterioCobrancaActionForm.getValorDebitoMinimo() != null
								&& !criterioCobrancaActionForm
										.getValorDebitoMinimo().equals("")) {
							valorDebitoMinimo = Util
									.formatarMoedaRealparaBigDecimal(criterioCobrancaActionForm
											.getValorDebitoMinimo());
						}
						BigDecimal valorDebitoMaximo = null;
						if (criterioCobrancaActionForm.getValorDebitoMaximo() != null
								&& !criterioCobrancaActionForm
										.getValorDebitoMaximo().equals("")) {
							valorDebitoMaximo = Util
									.formatarMoedaRealparaBigDecimal(criterioCobrancaActionForm
											.getValorDebitoMaximo());
						}
						if (valorDebitoMinimo != null
								&& valorDebitoMaximo != null) {
							if (valorDebitoMinimo.compareTo(valorDebitoMaximo) == 1) {
								throw new ActionServletException(
										"atencao.valor.maximo.debito.menor.valor.minimo.debito");
							}
						}
						CobrancaCriterioLinha cobrancaCriterioLinha = new CobrancaCriterioLinha();
						cobrancaCriterioLinha.setImovelPerfil(imovelPerfil);
						cobrancaCriterioLinha.setCategoria(categoria);
						cobrancaCriterioLinha
								.setValorMinimoDebito(valorDebitoMinimo);
						cobrancaCriterioLinha
								.setValorMaximoDebito(valorDebitoMaximo);

						Short qtdContasMinima = null;
						if (criterioCobrancaActionForm.getQtdContasMinima() != null
								&& !criterioCobrancaActionForm
										.getQtdContasMinima().equals("")) {
							qtdContasMinima = new Short(
									criterioCobrancaActionForm
											.getQtdContasMinima());
						}
						Short qtdContasMaxima = null;
						if (criterioCobrancaActionForm.getQtdContasMaxima() != null
								&& !criterioCobrancaActionForm
										.getQtdContasMaxima().equals("")) {
							qtdContasMaxima = new Short(
									criterioCobrancaActionForm
											.getQtdContasMaxima());
						}
						if (qtdContasMinima != null && qtdContasMaxima != null) {
							if (qtdContasMinima > qtdContasMaxima) {
								throw new ActionServletException(
										"atencao.quantidade.maxima.contas.menor.quantidade.minima.contas");
							}
						}
						cobrancaCriterioLinha
								.setQuantidadeMinimaContas(qtdContasMinima);
						cobrancaCriterioLinha
								.setQuantidadeMaximaContas(qtdContasMaxima);

						if (criterioCobrancaActionForm
								.getVlMinimoDebitoCliente() != null
								&& !criterioCobrancaActionForm
										.getVlMinimoDebitoCliente().equals("")) {
							cobrancaCriterioLinha
									.setValorMinimoDebitoDebitoAutomatico(Util
											.formatarMoedaRealparaBigDecimal(criterioCobrancaActionForm
													.getVlMinimoDebitoCliente()));
						}
						if (criterioCobrancaActionForm.getQtdMinContasCliente() != null
								&& !criterioCobrancaActionForm
										.getQtdMinContasCliente().equals("")) {
							cobrancaCriterioLinha
									.setQuantidadeMinimaContasDebitoAutomatico(new Short(
											criterioCobrancaActionForm
													.getQtdMinContasCliente()));
						}
						if (criterioCobrancaActionForm.getVlMinimoContasMes() != null
								&& !criterioCobrancaActionForm
										.getVlMinimoContasMes().equals("")) {
							cobrancaCriterioLinha
									.setValorMinimoContaMes(Util
											.formatarMoedaRealparaBigDecimal(criterioCobrancaActionForm
													.getVlMinimoContasMes()));
						}
						if (criterioCobrancaActionForm
								.getQuantidadeMinimaParcelasAtraso() != null
								&& !criterioCobrancaActionForm
										.getQuantidadeMinimaParcelasAtraso()
										.equals("")) {
							cobrancaCriterioLinha
									.setQuantidadeMinimaContasParcelamento(new Short(
											criterioCobrancaActionForm
													.getQuantidadeMinimaParcelasAtraso()));

						} else {
							cobrancaCriterioLinha
									.setQuantidadeMinimaContasParcelamento(new Short(
											"0"));
						}
						cobrancaCriterioLinha.setUltimaAlteracao(new Date());

						// adiciona a cobranca criterio linha na colecao
						colecaoCobrancaCriterioLinha.add(cobrancaCriterioLinha);
					}
				} else {
					throw new ActionServletException("atencao.informe_campo",
							null, "Categoria");
				}
			}
		} else {
			throw new ActionServletException("atencao.informe_campo", null,
					"Perfil do Imóvel");
		}

		sessao.setAttribute("colecaoCobrancaCriterioLinha",
				colecaoCobrancaCriterioLinha);
		httpServletRequest.setAttribute("fechaPopup", "true");

		return retorno;
	}
}
