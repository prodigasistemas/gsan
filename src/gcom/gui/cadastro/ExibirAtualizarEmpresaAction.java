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
package gcom.gui.cadastro;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.EmpresaCobrancaFaixa;
import gcom.cadastro.empresa.EmpresaContratoCobranca;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.empresa.FiltroEmpresaCobrancaFaixa;
import gcom.cadastro.empresa.FiltroEmpresaContratoCobranca;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Arthur Carvalho
 * @date 14/05/2008
 */
public class ExibirAtualizarEmpresaAction extends GcomAction {

	/**
	 * Método responsavel por responder a requisicao
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

		ActionForward retorno = actionMapping.findForward("empresaAtualizar");

		AtualizarEmpresaActionForm atualizarEmpresaActionForm = (AtualizarEmpresaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		String id = null;

		List<EmpresaCobrancaFaixa> colecaoEmpresaCobrancaFaixa = new ArrayList();
		List<EmpresaCobrancaFaixa> colecaoEmpresaCobrancaFaixaRemover = new ArrayList();

		if (sessao.getAttribute("colecaoEmpresaCobrancaFaixa") != null
				&& !sessao.getAttribute("colecaoEmpresaCobrancaFaixa").equals("")){
			colecaoEmpresaCobrancaFaixa = (List<EmpresaCobrancaFaixa>) sessao.getAttribute("colecaoEmpresaCobrancaFaixa");
		} 
		if (sessao.getAttribute("colecaoEmpresaCobrancaFaixaRemover") != null
				&& !sessao.getAttribute("colecaoEmpresaCobrancaFaixaRemover").equals("")){
			colecaoEmpresaCobrancaFaixaRemover = (List<EmpresaCobrancaFaixa>) sessao.getAttribute("colecaoEmpresaCobrancaFaixaRemover");
		}
		
		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null) {
			
			id = httpServletRequest.getParameter("idRegistroAtualizacao");
			
		} else if (sessao.getAttribute("empresa") != null
				&& !sessao.getAttribute("empresa").equals("")) {
			
			id = ((Empresa) sessao.getAttribute("empresa")).getId().toString();
			
		} else if (sessao.getAttribute("atualizarEmpresa") != null
				&& !sessao.getAttribute("atualizarEmpresa").equals("")){
			
			id = ((Empresa) sessao.getAttribute("atualizarEmpresa")).getId().toString();
		}

		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}

		if (id == null) {
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null) {
				id = (String) sessao.getAttribute("idRegistroAtualizacao");
			} else {
				id = (String) httpServletRequest.getAttribute(
						"idRegistroAtualizacao").toString();
			}
		} else {
			sessao.setAttribute("i", true);
		}

		Empresa empresa = new Empresa();

		if (id != null && !id.trim().equals("") && Integer.parseInt(id) > 0
				&& (httpServletRequest.getParameter("adicionarFaixa") == null
						|| !httpServletRequest.getParameter("adicionarFaixa").equalsIgnoreCase("sim"))
				&& (httpServletRequest.getParameter("removerEmpresaCobrancaFaixa") == null
						|| httpServletRequest.getParameter("removerEmpresaCobrancaFaixa").equals(""))
				&& (httpServletRequest.getParameter("limparFaixa") == null
						|| httpServletRequest.getParameter("limparFaixa").equals(""))) {
			
			sessao.removeAttribute("colecaoEmpresaCobrancaFaixaRemover");

			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(
					FiltroEmpresa.ID, id));
			Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa,
					Empresa.class.getName());
			if (colecaoEmpresa != null && !colecaoEmpresa.isEmpty()) {
				empresa = (Empresa) Util.retonarObjetoDeColecao(colecaoEmpresa);
			}

			if (id == null || id.trim().equals("")) {

				atualizarEmpresaActionForm.setId(empresa.getId().toString());

				atualizarEmpresaActionForm.setDescricao(empresa.getDescricao());

				atualizarEmpresaActionForm.setDescricaoAbreviada(empresa
						.getDescricaoAbreviada());

				atualizarEmpresaActionForm.setEmail(empresa.getEmail());

				atualizarEmpresaActionForm.setIndicadorEmpresaPrincipal(empresa
						.getIndicadorEmpresaPrincipal());

				atualizarEmpresaActionForm.setIndicadorUso(empresa
						.getIndicadorUso());

				atualizarEmpresaActionForm
				.setIndicadorEmpresaCobranca(empresa
						.getIndicadorEmpresaContratadaCobranca()
						.toString());
				
				atualizarEmpresaActionForm.setIndicadorLeitura( empresa.getIndicadorLeitura() );
				
				if (empresa.getIndicadorEmpresaContratadaCobranca()
								.intValue() == ConstantesSistema.INDICADOR_USO_ATIVO) {

					FiltroEmpresaContratoCobranca filtroEmpresaCobranca = new FiltroEmpresaContratoCobranca();

					filtroEmpresaCobranca
							.adicionarParametro(new ParametroSimples(
									FiltroEmpresaContratoCobranca.EMPRESA_ID, empresa
											.getId().toString()));

					Collection colecaoEmpresaCobranca = fachada.pesquisar(
							filtroEmpresaCobranca, EmpresaContratoCobranca.class
									.getName());

					if (colecaoEmpresaCobranca != null
							&& !colecaoEmpresaCobranca.isEmpty()) {

						EmpresaContratoCobranca empresaCobranca = (EmpresaContratoCobranca) colecaoEmpresaCobranca
								.iterator().next();

						atualizarEmpresaActionForm
								.setDataInicioContratoCobranca(Util
										.formatarData(empresaCobranca
												.getDataInicioContrato()));

						atualizarEmpresaActionForm
								.setDataFimContratoCobranca(Util
										.formatarData(empresaCobranca
												.getDataFinalContrato()));

						atualizarEmpresaActionForm.setPercentualPagamento(Util
								.formatarMoedaReal(empresaCobranca
										.getPercentualContratoCobranca()));
						
						FiltroEmpresaCobrancaFaixa filtro = new FiltroEmpresaCobrancaFaixa();
						filtro.adicionarParametro(new ParametroSimples(
								FiltroEmpresaCobrancaFaixa.EMPRESA_CONTRATO_COBRANCA_ID, empresaCobranca.getId()));
						filtro.setCampoOrderBy(FiltroEmpresaCobrancaFaixa.NUMERO_MAXIMO_CONTAS_FAIXA);
						
						colecaoEmpresaCobrancaFaixa = (List<EmpresaCobrancaFaixa>)fachada.pesquisar(
								filtro, EmpresaCobrancaFaixa.class.getName());

						if (colecaoEmpresaCobrancaFaixa != null
								&& !colecaoEmpresaCobrancaFaixa.isEmpty()) {
							atualizarEmpresaActionForm.setPercentualDaFaixaInformado("sim");
							sessao.setAttribute("colecaoEmpresaCobrancaFaixa", colecaoEmpresaCobrancaFaixa);
						} else {
							atualizarEmpresaActionForm.setPercentualDaFaixaInformado("");
							sessao.removeAttribute("colecaoEmpresaCobrancaFaixa");
						}

					}

				}

			}

			atualizarEmpresaActionForm.setId(empresa.getId().toString());

			atualizarEmpresaActionForm.setDescricao(empresa.getDescricao());

			atualizarEmpresaActionForm.setDescricaoAbreviada(empresa
					.getDescricaoAbreviada());

			atualizarEmpresaActionForm.setEmail(empresa.getEmail());

			atualizarEmpresaActionForm.setIndicadorEmpresaPrincipal(empresa
					.getIndicadorEmpresaPrincipal());
			
			atualizarEmpresaActionForm.setIndicadorLeitura( empresa.getIndicadorLeitura() );
			
			atualizarEmpresaActionForm.setIndicadorEmpresaCobranca(empresa
					.getIndicadorEmpresaContratadaCobranca().toString());
			if (empresa.getIndicadorEmpresaContratadaCobranca()
							.intValue() == ConstantesSistema.INDICADOR_USO_ATIVO) {

				FiltroEmpresaContratoCobranca filtroEmpresaCobranca = new FiltroEmpresaContratoCobranca();

				filtroEmpresaCobranca.adicionarParametro(new ParametroSimples(
						FiltroEmpresaContratoCobranca.EMPRESA_ID, empresa.getId()
								.toString()));

				Collection colecaoEmpresaCobranca = fachada.pesquisar(
						filtroEmpresaCobranca, EmpresaContratoCobranca.class.getName());

				if (colecaoEmpresaCobranca != null
						&& !colecaoEmpresaCobranca.isEmpty()) {

					EmpresaContratoCobranca empresaCobranca = (EmpresaContratoCobranca) colecaoEmpresaCobranca
							.iterator().next();

					atualizarEmpresaActionForm
							.setDataInicioContratoCobranca(Util
									.formatarData(empresaCobranca
											.getDataInicioContrato()));

					atualizarEmpresaActionForm.setDataFimContratoCobranca(Util
							.formatarData(empresaCobranca
									.getDataFinalContrato()));

					atualizarEmpresaActionForm.setPercentualPagamento(Util
							.formatarMoedaReal(empresaCobranca
									.getPercentualContratoCobranca()));
					
					FiltroEmpresaCobrancaFaixa filtro = new FiltroEmpresaCobrancaFaixa();
					filtro.adicionarParametro(new ParametroSimples(
							FiltroEmpresaCobrancaFaixa.EMPRESA_CONTRATO_COBRANCA_ID, empresaCobranca.getId()));
					filtro.setCampoOrderBy(FiltroEmpresaCobrancaFaixa.NUMERO_MAXIMO_CONTAS_FAIXA);
					
					colecaoEmpresaCobrancaFaixa = (List<EmpresaCobrancaFaixa>)fachada.pesquisar(
							filtro, EmpresaCobrancaFaixa.class.getName());

					if (colecaoEmpresaCobrancaFaixa != null
							&& !colecaoEmpresaCobrancaFaixa.isEmpty()) {
						atualizarEmpresaActionForm.setPercentualDaFaixaInformado("sim");
						sessao.setAttribute("colecaoEmpresaCobrancaFaixa", colecaoEmpresaCobrancaFaixa);
					} else {
						atualizarEmpresaActionForm.setPercentualDaFaixaInformado("");
						sessao.removeAttribute("colecaoEmpresaCobrancaFaixa");
					}
				}

			}

			atualizarEmpresaActionForm.setIndicadorUso(empresa
					.getIndicadorUso());

			sessao.setAttribute("atualizarEmpresa", empresa);

			if (sessao.getAttribute("colecaoEmpresa") != null) {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/filtrarEmpresaAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/exibirFiltrarEmpresaAction.do");
			}

		}


		// Adicionar EmpresaCobrancaFaixa
		if (httpServletRequest.getParameter("adicionarFaixa") != null
				&& httpServletRequest.getParameter("adicionarFaixa").equals("sim")
				&& atualizarEmpresaActionForm.getQuantidadeMinimaContas() != null
				&& !atualizarEmpresaActionForm.getQuantidadeMinimaContas().equals("")
				&& atualizarEmpresaActionForm.getPercentualDaFaixa() != null
				&& !atualizarEmpresaActionForm.getPercentualDaFaixa().equals("")) {

			Integer quantidadeMinimaContas = new Integer(atualizarEmpresaActionForm.getQuantidadeMinimaContas());
			BigDecimal percentualFaixa = Util.formatarMoedaRealparaBigDecimal(atualizarEmpresaActionForm.getPercentualDaFaixa());
			
			if (colecaoEmpresaCobrancaFaixa != null && !colecaoEmpresaCobrancaFaixa.isEmpty()) {
				Iterator iterator = colecaoEmpresaCobrancaFaixa.iterator();
				
				while(iterator.hasNext()) {
					EmpresaCobrancaFaixa empresaCobrancaFaixa = (EmpresaCobrancaFaixa) iterator.next();
					
					if (empresaCobrancaFaixa.getNumeroMinimoContasFaixa().compareTo(quantidadeMinimaContas) >= 0) {
						throw new ActionServletException(
								"atencao.quantidade.maior.que.quantidade.anterior", null, "Quantidade Mínima de Contas");
					}
				}
			}
			
			EmpresaCobrancaFaixa empresaCobrancaFaixa = new EmpresaCobrancaFaixa();
			empresaCobrancaFaixa.setNumeroMinimoContasFaixa(quantidadeMinimaContas);
			empresaCobrancaFaixa.setPercentualFaixa(percentualFaixa);
			
			colecaoEmpresaCobrancaFaixa.add(empresaCobrancaFaixa);
			sessao.setAttribute("colecaoEmpresaCobrancaFaixa", colecaoEmpresaCobrancaFaixa);
			atualizarEmpresaActionForm.setPercentualDaFaixaInformado("sim");

			atualizarEmpresaActionForm.setQuantidadeMinimaContas("");
			atualizarEmpresaActionForm.setPercentualDaFaixa("");
		}
		
		// Remover EmpresaCobrancaFaixa
		if (httpServletRequest.getParameter("removerEmpresaCobrancaFaixa") != null
				&& !httpServletRequest.getParameter("removerEmpresaCobrancaFaixa").equals("")) {
			
			Integer indice = new Integer(httpServletRequest.getParameter("removerEmpresaCobrancaFaixa"));
        	
        	if (colecaoEmpresaCobrancaFaixa != null
        			&& !colecaoEmpresaCobrancaFaixa.isEmpty()
        			&& colecaoEmpresaCobrancaFaixa.size() >= indice) {
        		EmpresaCobrancaFaixa empresaCobrancaFaixa = colecaoEmpresaCobrancaFaixa.get(indice - 1);
        		if (empresaCobrancaFaixa.getUltimaAlteracao() != null) {
        			colecaoEmpresaCobrancaFaixaRemover.add(empresaCobrancaFaixa);
    				sessao.setAttribute("colecaoEmpresaCobrancaFaixaRemover", colecaoEmpresaCobrancaFaixaRemover);
        		}
        		
        		colecaoEmpresaCobrancaFaixa.remove(indice-1);
        		if (colecaoEmpresaCobrancaFaixa != null
        				&& !colecaoEmpresaCobrancaFaixa.isEmpty()) {
        			sessao.setAttribute("colecaoEmpresaCobrancaFaixa", colecaoEmpresaCobrancaFaixa);
        			atualizarEmpresaActionForm.setPercentualDaFaixaInformado("sim");
        		} else {
        			sessao.removeAttribute("colecaoEmpresaCobrancaFaixa");
        			atualizarEmpresaActionForm.setPercentualDaFaixaInformado("");
        		}
        	}
        	
		}

		// Limpar Formulário
		if (httpServletRequest.getParameter("limparFaixa") != null
				&& httpServletRequest.getParameter("limparFaixa").equals("sim")) {
			
			sessao.removeAttribute("colecaoEmpresaCobrancaFaixa");
			atualizarEmpresaActionForm.setPercentualDaFaixaInformado("");
			
		}
		
		return retorno;
	}
}