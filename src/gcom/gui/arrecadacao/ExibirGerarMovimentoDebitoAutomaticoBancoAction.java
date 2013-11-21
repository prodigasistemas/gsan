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
package gcom.gui.arrecadacao;

import gcom.arrecadacao.banco.Banco;
import gcom.arrecadacao.debitoautomatico.DebitoAutomaticoMovimento;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Pre- processamento para gerar movimento de débito automático para o banco
 * 
 * @author Sávio Luiz
 * @date 17/04/2006
 */
public class ExibirGerarMovimentoDebitoAutomaticoBancoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("gerarMovimentoDebitoAutomatico");

		GerarMovimentoDebitoAutomaticoBancoActionForm gerarMovimentoDebitoAutomaticoBancoActionForm = (GerarMovimentoDebitoAutomaticoBancoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		if (httpServletRequest.getParameter("criaColecaoBanco") != null
				&& !httpServletRequest.getParameter("criaColecaoBanco").equals(
						"")) {

			boolean mesAnoValido = Util
					.validarMesAno(gerarMovimentoDebitoAutomaticoBancoActionForm
							.getMesAnoFaturamento());
			if (!mesAnoValido) {
				throw new ActionServletException("atencao.ano_mes.invalido");
			}
			int ano = Integer
					.parseInt(gerarMovimentoDebitoAutomaticoBancoActionForm
							.getMesAnoFaturamento().substring(3, 7));
			if (ano < 2005) {
				throw new ActionServletException("atencao.movimento.maior.2005");
			}
			
			
			Integer anoMesReferencia = Util.formatarMesAnoComBarraParaAnoMes(
			gerarMovimentoDebitoAutomaticoBancoActionForm.getMesAnoFaturamento());
			
			
			/*
			 * Colocado por Raphael Rossiter em 03/07/2007 (Analista: Rosana Carvalgo)
			 * Objetivo: Selecionar um conjunto de grupos de faturamento
			 */
			
			//GRUPOS SELECIONADOS
			String dadosFaturamentoGrupo = httpServletRequest.getParameter("criaColecaoBanco");
			
			//GERANDO COLEÇÃO DE GRUPOS
			Collection colecaoIdsFaturamentoGrupo = this.obterGruposSelecionados(dadosFaturamentoGrupo, anoMesReferencia);
			
			//OBTENDO OS DÁBITOS AUTOMÁTICOS
			Map<Banco, Collection<DebitoAutomaticoMovimento>> debitosAutomaticoBancosMap = fachada
			.pesquisaDebitoAutomaticoMovimento(colecaoIdsFaturamentoGrupo, anoMesReferencia);
			

			if (debitosAutomaticoBancosMap != null
					&& !debitosAutomaticoBancosMap.isEmpty()) {
				sessao.setAttribute("debitosAutomaticoBancosMap",
						debitosAutomaticoBancosMap);
			} else {
				throw new ActionServletException(
						"atencao.nao.movimento.debito.automatico");
			}
			
			
		} else {
			sessao.removeAttribute("debitosAutomaticoBancosMap");
		}
		if (httpServletRequest.getParameter("limpaColecao") != null
				&& !httpServletRequest.getParameter("limpaColecao").equals("")) {
			sessao.removeAttribute("debitosAutomaticoBancosMap");
		}

		if (httpServletRequest.getParameter("menu") != null
				&& !httpServletRequest.getParameter("menu").equals("")) {
			gerarMovimentoDebitoAutomaticoBancoActionForm.reset(actionMapping,
					httpServletRequest);
			FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo(
					FiltroFaturamentoGrupo.DESCRICAO);

			filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
					FiltroFaturamentoGrupo.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoFaturamentoGrupo = fachada.pesquisar(
					filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());

			sessao.setAttribute("colecaoFaturamentoGrupo",
					colecaoFaturamentoGrupo);
		}

		return retorno;
	}
	
	
	private Collection obterGruposSelecionados(String faturamentoGrupoStringBuffer, Integer anoMesReferencia){
		
		Collection retorno = null;
		
		if (faturamentoGrupoStringBuffer != null && !faturamentoGrupoStringBuffer.equalsIgnoreCase("")){
			
			retorno = new ArrayList();
			
			String[] arrayFaturamentoGrupo = faturamentoGrupoStringBuffer.split(":");
			String[] arrayGrupoEReferencia = null;
			
			Integer idFaturamentoGrupo = null;
			Integer anoMesFaturamentoGrupo = null;
			
			for (int x=0; x < arrayFaturamentoGrupo.length; x++){
				
				arrayGrupoEReferencia = arrayFaturamentoGrupo[x].split(";");
				
				idFaturamentoGrupo = new Integer(arrayGrupoEReferencia[0]); 
				anoMesFaturamentoGrupo = new Integer(arrayGrupoEReferencia[1]);
			
				if (anoMesReferencia > anoMesFaturamentoGrupo) {
					throw new ActionServletException(
							"atencao.faturamento.posterior.faturamento.grupo");
				}
				
				retorno.add(idFaturamentoGrupo);
			}
		}
		
		return retorno;
	}
}
