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
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cobranca.CobrancaCriterioLinha;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
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
 * Pre- processamento para adicionar a linha do criterio da cobrança
 * 
 * @author Sávio Luiz
 * @date 02/05/2006
 */
public class ExibirAdicionarCriterioCobrancaLinhaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("adicionarCriterioCobrancaLinha");

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

		Fachada fachada = Fachada.getInstancia();

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

		if (sessao.getAttribute("colecaoCobrancaCriterioLinha") != null
				&& !sessao.getAttribute("colecaoCobrancaCriterioLinha").equals(
						"")) {
			Collection colecaoCobrancaCriterioLinha = (Collection) sessao
					.getAttribute("colecaoCobrancaCriterioLinha");

			int tamanhoColecao = colecaoCobrancaCriterioLinha.size();

			if (tamanhoColecao != 0) {

				// recupera o ultimo criterio de cobrança linha
				CobrancaCriterioLinha cobrancaCriterioLinha = (CobrancaCriterioLinha) ((List) colecaoCobrancaCriterioLinha)
						.get(tamanhoColecao - 1);

				// formata os valores para jogar no form
				if (cobrancaCriterioLinha.getValorMinimoDebito() != null
						&& !cobrancaCriterioLinha.getValorMinimoDebito()
								.equals("")) {
					vlMinDebito = Util.formatarMoedaReal(cobrancaCriterioLinha
							.getValorMinimoDebito());
				}
				if (cobrancaCriterioLinha.getValorMaximoDebito() != null
						&& !cobrancaCriterioLinha.getValorMaximoDebito()
								.equals("")) {
					vlMaxDebito = Util.formatarMoedaReal(cobrancaCriterioLinha
							.getValorMaximoDebito());
				}
				if (cobrancaCriterioLinha.getQuantidadeMinimaContas() != null
						&& !cobrancaCriterioLinha.getQuantidadeMinimaContas()
								.equals("")) {
					qtdMinContas = ""
							+ cobrancaCriterioLinha.getQuantidadeMinimaContas();
				}
				if (cobrancaCriterioLinha.getQuantidadeMaximaContas() != null
						&& !cobrancaCriterioLinha.getQuantidadeMaximaContas()
								.equals("")) {
					qtdMaxContas = ""
							+ cobrancaCriterioLinha.getQuantidadeMaximaContas();
				}
				if (cobrancaCriterioLinha
						.getValorMinimoDebitoDebitoAutomatico() != null
						&& !cobrancaCriterioLinha
								.getValorMinimoDebitoDebitoAutomatico().equals(
										"")) {
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
						&& !cobrancaCriterioLinha.getValorMinimoContaMes()
								.equals("")) {
					vlMinConMes = Util.formatarMoedaReal(cobrancaCriterioLinha
							.getValorMinimoContaMes());
				}
				
				if (criterioCobrancaActionForm.getQuantidadeMinimaParcelasAtraso() != null
						&& !criterioCobrancaActionForm
								.getQuantidadeMinimaParcelasAtraso().equals("")) {
					qdtParcelasMinimas = cobrancaCriterioLinha
							.getQuantidadeMinimaContasParcelamento().toString();

				} else {
					qdtParcelasMinimas = "0";
				}

			}

		}

		
		if (httpServletRequest.getParameter("limparPopup") != null){
			criterioCobrancaActionForm.setValorDebitoMinimo("");
			criterioCobrancaActionForm.setValorDebitoMaximo("");
			criterioCobrancaActionForm.setQtdContasMinima("");
			criterioCobrancaActionForm.setQtdContasMaxima("");
			criterioCobrancaActionForm.setVlMinimoDebitoCliente("");
			criterioCobrancaActionForm.setQtdMinContasCliente("");
			criterioCobrancaActionForm.setVlMinimoContasMes("");
			criterioCobrancaActionForm.setQuantidadeMinimaParcelasAtraso("");
		}else{
			//seta os valores da ultima linha da cobrança criterio
			criterioCobrancaActionForm.setValorDebitoMinimo(vlMinDebito);
			criterioCobrancaActionForm.setValorDebitoMaximo(vlMaxDebito);
			criterioCobrancaActionForm.setQtdContasMinima(qtdMinContas);
			criterioCobrancaActionForm.setQtdContasMaxima(qtdMaxContas);
			criterioCobrancaActionForm.setVlMinimoDebitoCliente(vlMinDebCliente);
			criterioCobrancaActionForm.setQtdMinContasCliente(qtdMinConCliente);
			criterioCobrancaActionForm.setVlMinimoContasMes(vlMinConMes);
			criterioCobrancaActionForm.setQuantidadeMinimaParcelasAtraso(qdtParcelasMinimas);
		}
		
		

		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(
				FiltroImovelPerfil.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection colecaoImovelPerfil = fachada.pesquisar(filtroImovelPerfil,
				ImovelPerfil.class.getName());
		if (colecaoImovelPerfil == null || colecaoImovelPerfil.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Imovel Perfil");
		}

		FiltroCategoria filtroCategoria = new FiltroCategoria();
		filtroCategoria.adicionarParametro(new ParametroSimples(
				FiltroCategoria.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection colecaoCategoria = fachada.pesquisar(filtroCategoria,
				Categoria.class.getName());
		if (colecaoCategoria == null || colecaoCategoria.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Categoria");
		}

		
		sessao.setAttribute("colecaoImovelPerfil", colecaoImovelPerfil);
		sessao.setAttribute("colecaoCategoria", colecaoCategoria);

		httpServletRequest.setAttribute("fechaPopup", "false");

		return retorno;
	}
}
