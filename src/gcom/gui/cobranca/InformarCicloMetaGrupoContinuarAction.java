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

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.cobranca.CicloMeta;
import gcom.cobranca.CicloMetaGrupo;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.CriterioSituacaoLigacaoAgua;
import gcom.cobranca.FiltroCicloMetaGrupo;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.cobranca.FiltroCriterioSituacaoLigacaoAgua;
import gcom.cobranca.GrupoLocalidadeImovelHelper;
import gcom.cobranca.InformarCicloMetaGrupoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC00] Informar metas do ciclo
 * 
 * @author Francisco do Nascimento
 * 
 */

public class InformarCicloMetaGrupoContinuarAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("informarCicloMetaGrupo");

		CicloMetaGrupoActionForm cicloMetaForm = (CicloMetaGrupoActionForm) actionForm;
	
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = request.getSession(false);
		
		if (!((sessao.getAttribute("cicloMeta"))==null)) {
			sessao.removeAttribute("idCicloMeta");
			sessao.removeAttribute("cicloMeta");
			sessao.removeAttribute("helpers");
		}
		
		CicloMeta cicloMeta = (CicloMeta) sessao.getAttribute("cicloMeta");
		
		if (cicloMeta == null)	{
			
			cicloMeta = new CicloMeta();
			
			if (cicloMetaForm.getIdCobrancaAcao() != null){
				FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
				filtroCobrancaAcao.adicionarParametro(new ParametroSimples(FiltroCobrancaAcao.ID, 
					cicloMetaForm.getIdCobrancaAcao()));
				filtroCobrancaAcao.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcao.COBRANCAO_CRITERIO);
				filtroCobrancaAcao.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcao.LIGACAO_AGUA_SITUACAO);
				Collection colecaoCobrancaAcao = fachada.pesquisar(
						filtroCobrancaAcao, CobrancaAcao.class.getName());
				
				CobrancaAcao cobrancaAcao = (CobrancaAcao) Util.retonarObjetoDeColecao(
					colecaoCobrancaAcao);
				
				cicloMeta.setCobrancaAcao(cobrancaAcao);
			}
			
			cicloMeta.setAnoMesReferencia(Util.formatarMesAnoComBarraParaAnoMes(cicloMetaForm.getAnoMesCobranca()));
			
			cicloMeta.setMetaTotal(Integer.parseInt(cicloMetaForm.getMetaTotal()));
			
			if (cicloMetaForm.getValorLimite() != null && !cicloMetaForm.getValorLimite().trim().equals("")){
				cicloMetaForm.setValorLimite(cicloMetaForm.getValorLimite().replace(".","").replace(",","."));
				
				cicloMeta.setValorLimite(new BigDecimal (cicloMetaForm.getValorLimite()));
			}
														
			sessao.setAttribute("cicloMeta", cicloMeta);			
			
		} else {
			cicloMeta.setMetaTotal(Integer.parseInt(cicloMetaForm.getMetaTotal()));
			
			cicloMetaForm.setValorLimite(cicloMetaForm.getValorLimite().replace(".","").replace(",","."));
			cicloMeta.setValorLimite(new BigDecimal(cicloMetaForm.getValorLimite()));
			
		}
		
		if (cicloMetaForm.getIdCicloMeta() != null && !cicloMetaForm.getIdCicloMeta().equals("")
				&& !cicloMetaForm.getIdCicloMeta().equals("-1")){
			cicloMeta.setId(new Integer(cicloMetaForm.getIdCicloMeta()));
		}
		
		if (request.getParameter("regerarMetas") != null 
				&& !request.getParameter("regerarMetas").equals("")){
			if (cicloMetaForm.getIdCicloMeta() != null && !cicloMetaForm.getIdCicloMeta().equals("")
					&& !cicloMetaForm.getIdCicloMeta().equals("-1")){
				sessao.setAttribute("metasRegeradas", true);
			}
		}
		
		
		//TreeMap<String, InformarCicloMetaGrupoHelper> helpers = fachada.distribuirMetasCiclo(cicloMeta);
		TreeMap<String, InformarCicloMetaGrupoHelper> helpers = this.distribuirMetasCiclo(cicloMeta);
		
		int metaTotalCalculada = 0;
		int metaTotalAjustada = 0;
		// percorrer os helpers de gerencia para calcular o valor total das metas
		for (Iterator iter = helpers.values().iterator(); iter.hasNext();) {
			InformarCicloMetaGrupoHelper helperGerencia = (InformarCicloMetaGrupoHelper) iter.next();			
			metaTotalCalculada +=helperGerencia.getMetaOriginal();
			metaTotalAjustada += helperGerencia.getMetaAtual();
		}
		request.setAttribute("metaTotalCalculada", new Integer(metaTotalCalculada));
		request.setAttribute("metaTotalAjustada", new Integer(metaTotalAjustada));
		
		if (cicloMeta.getValorLimite() != null){
			request.setAttribute("valorLimiteEmissao", cicloMeta.getValorLimite());
		}else{
			request.setAttribute("valorLimiteEmissao", 0);
		}
		
		
		sessao.setAttribute("helpers", helpers.values());
		
		return retorno;

	}
	
public TreeMap<String, InformarCicloMetaGrupoHelper> distribuirMetasCiclo(CicloMeta cicloMeta){
		
		// Distribuir as metas de grupo/localidade por gerencia/cas/localidade		
		TreeMap<String, InformarCicloMetaGrupoHelper> helpersGerencia = new TreeMap<String, InformarCicloMetaGrupoHelper>();
		Fachada fachada = Fachada.getInstancia();

		Collection colecaoCicloMetaGrupo = new ArrayList();
		
		if (cicloMeta.getId() != null && cicloMeta.getId().intValue() != -1){
			
			cicloMeta.setUltimaAlteracao(new Date());
			
			FiltroCicloMetaGrupo filtroCicloGrupo = new FiltroCicloMetaGrupo();
			filtroCicloGrupo.adicionarParametro(new ParametroSimples(FiltroCicloMetaGrupo.CICLO_META, 
				cicloMeta.getId()));
			
			colecaoCicloMetaGrupo = fachada.pesquisar(
					filtroCicloGrupo, CicloMetaGrupo.class.getName());			
			
			if (colecaoCicloMetaGrupo != null && !colecaoCicloMetaGrupo.isEmpty()){
				fachada.removerCicloMetaGrupo(cicloMeta.getId());
				colecaoCicloMetaGrupo.clear();
			}
			
		}
			
		Collection<GrupoLocalidadeImovelHelper> colecaoQuantidadeHelper = 
			new ArrayList<GrupoLocalidadeImovelHelper>();
		
		int quantidadeTotalImoveis = 0;
		
		// [3.1.2] Pesquisar as situações de ligação de agua permitidas para a ação		
		if (cicloMeta.getCobrancaAcao() != null && cicloMeta.getCobrancaAcao().getCobrancaCriterio() != null){

			Collection<Integer> colecaoIdsSituacaoLigacaoAgua = new ArrayList<Integer>();
			
			// pesquisar a colecao de criterios para situacao ligacao agua, esgoto e cobranca
			FiltroCriterioSituacaoLigacaoAgua filtroCritSitAgua = new FiltroCriterioSituacaoLigacaoAgua();
			filtroCritSitAgua.adicionarParametro(new ParametroSimples(
					FiltroCriterioSituacaoLigacaoAgua.COBRANCA_CRITERIO_ID,
					cicloMeta.getCobrancaAcao().getCobrancaCriterio().getId()));
			filtroCritSitAgua.adicionarCaminhoParaCarregamentoEntidade(FiltroCriterioSituacaoLigacaoAgua.LIGACAO_AGUA_SITUACAO);
			Collection colecaoCritSituacaoLigacaoAgua = fachada.pesquisar(filtroCritSitAgua, CriterioSituacaoLigacaoAgua.class.getName());				

			if (colecaoCritSituacaoLigacaoAgua == null || colecaoCritSituacaoLigacaoAgua.isEmpty()){
				if (cicloMeta.getCobrancaAcao().getLigacaoAguaSituacao() == null){
					throw new ActionServletException("atencao.cobranca_acao_sem_situacao_acao", null, "");
				}
				colecaoIdsSituacaoLigacaoAgua.add(cicloMeta.getCobrancaAcao().getLigacaoAguaSituacao().getId());
			} else {
				for (Iterator iter = colecaoCritSituacaoLigacaoAgua.iterator(); iter
						.hasNext();) {
					CriterioSituacaoLigacaoAgua clas = (CriterioSituacaoLigacaoAgua) iter.next();
					colecaoIdsSituacaoLigacaoAgua.add(clas.getComp_id().getLigacaoAguaSituacao().getId());
				}
			}
			
			Collection colecaoQuantidadeImoveis = null;
			
			colecaoQuantidadeImoveis 
			   = fachada.pesquisarQuantidadeImoveisPorGrupoLocalidade(
					   colecaoIdsSituacaoLigacaoAgua);
			
			
			if (colecaoQuantidadeImoveis != null){
				Iterator iter = colecaoQuantidadeImoveis.iterator(); 
				while (iter.hasNext()) {
					Object[] linha = (Object[]) iter.next();
					
					GrupoLocalidadeImovelHelper helper = new GrupoLocalidadeImovelHelper();
					helper.setIdGrupo((Integer) linha[0]);
					helper.setIdLocalidade((Integer) linha[1]);
					helper.setQuantidadeImoveis((Integer) linha[2]);
					
					quantidadeTotalImoveis += helper.getQuantidadeImoveis();
					
					colecaoQuantidadeHelper.add(helper);
				}
			}

			// antes do (double) veio 0.0
			double fator = ((double) cicloMeta.getMetaTotal()) / quantidadeTotalImoveis;
			
			int metaAcumulada = 0;
			boolean estouro = false;
			CicloMetaGrupo itemMaior = null;
			
			int indice = 0;
			
			// Gerando as metas proporcionais
			for (Iterator iter = colecaoQuantidadeHelper.iterator(); 
				iter.hasNext();) {
				indice++;
				GrupoLocalidadeImovelHelper helper = 
					(GrupoLocalidadeImovelHelper) iter.next();
				
				CicloMetaGrupo cicloMetaGrupo = 
					new CicloMetaGrupo();
				
				cicloMetaGrupo.setCicloMeta(cicloMeta);
				
				CobrancaGrupo cobGrupo = new CobrancaGrupo();
				cobGrupo.setId(helper.getIdGrupo());
				cicloMetaGrupo.setCobrancaGrupo(cobGrupo);
				
				Localidade localidade = new Localidade();
				localidade.setId(helper.getIdLocalidade());
				cicloMetaGrupo.setLocalidade(localidade);
				
				cicloMetaGrupo.setQuantidadeImoveisSituacaoAgua(
						helper.getQuantidadeImoveis());
				
				int metaCalculada = 0;
				
				if (!estouro){
					BigDecimal calculoMeta = new BigDecimal(fator).multiply(
							new BigDecimal(helper.getQuantidadeImoveis()));
					calculoMeta = calculoMeta.setScale(0, BigDecimal.ROUND_HALF_EVEN);
					metaCalculada = calculoMeta.intValue();
					if (itemMaior == null || itemMaior.getMetaCalculada() < metaCalculada){
						itemMaior = cicloMetaGrupo;
					}
					
					metaAcumulada += metaCalculada;
				}
				
				cicloMetaGrupo.setMetaCalculada(metaCalculada);
				cicloMetaGrupo.setMetaAjustada(metaCalculada);
				
				if (metaCalculada < 0){
					cicloMetaGrupo.setMetaCalculada(0);
					cicloMetaGrupo.setMetaAjustada(0);
				}
				
				//chegou ao fim
				if (indice == colecaoQuantidadeHelper.size()){
					// Alterando o item com maior meta para ficar exatamente o valor da meta total
					if (metaAcumulada > cicloMeta.getMetaTotal()){
						
						itemMaior.setMetaCalculada(itemMaior.getMetaCalculada() - (metaAcumulada - cicloMeta.getMetaTotal()));
						if (itemMaior.getMetaCalculada() < 0){
							itemMaior.setMetaCalculada(0);
						}
						
						itemMaior.setMetaAjustada(itemMaior.getMetaAjustada() - (metaAcumulada - cicloMeta.getMetaTotal()));
						if (itemMaior.getMetaAjustada() < 0){
							itemMaior.setMetaAjustada(0);
						}
						
						cicloMetaGrupo = itemMaior;
						colecaoCicloMetaGrupo.remove(cicloMetaGrupo);
					}else if (cicloMeta.getMetaTotal() > metaAcumulada){
						
						itemMaior.setMetaCalculada(itemMaior.getMetaCalculada() - (cicloMeta.getMetaTotal() - metaAcumulada));
						if (itemMaior.getMetaCalculada() < 0){
							itemMaior.setMetaCalculada(0);
						}
						
						itemMaior.setMetaAjustada(itemMaior.getMetaAjustada() - (cicloMeta.getMetaTotal() - metaAcumulada));
						if (itemMaior.getMetaAjustada() < 0){
							itemMaior.setMetaAjustada(0);
						}
						
						cicloMetaGrupo = itemMaior;
						colecaoCicloMetaGrupo.remove(cicloMetaGrupo);
					}
				}
				
				cicloMetaGrupo.setUltimaAlteracao(new Date());
				colecaoCicloMetaGrupo.add(cicloMetaGrupo);
				
			}

		}
			
		helpersGerencia = agruparCicloMetaGrupo(colecaoCicloMetaGrupo);
		
		colecaoQuantidadeHelper.clear();
			
		return helpersGerencia;
	}

	private TreeMap<String, InformarCicloMetaGrupoHelper> agruparCicloMetaGrupo(Collection colecaoCicloMetaGrupo){
		
		TreeMap<String, InformarCicloMetaGrupoHelper> helpersGerencia = new TreeMap<String, InformarCicloMetaGrupoHelper>();
		Fachada fachada = Fachada.getInstancia();
		
		if (colecaoCicloMetaGrupo != null) {
			for (Iterator iter = colecaoCicloMetaGrupo.iterator(); iter.hasNext();) {
				CicloMetaGrupo cicloMetaGrupo = (CicloMetaGrupo) iter.next();
				
				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, 
					cicloMetaGrupo.getLocalidade().getId()));
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade(FiltroLocalidade.GERENCIA);
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade(FiltroLocalidade.UNIDADE_NEGOCIO);
				
				Collection colecaoLocalidade = fachada.pesquisar(
						filtroLocalidade, Localidade.class.getName());
				
				Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
				
				Integer idGerencia = localidade.getGerenciaRegional().getId();
				Integer idUnidadeNegocio = localidade.getUnidadeNegocio().getId();
				
				InformarCicloMetaGrupoHelper helperGerenciaLoc = helpersGerencia.get(localidade.getGerenciaRegional().getNome());
				
				if (helperGerenciaLoc == null){
					helperGerenciaLoc = new InformarCicloMetaGrupoHelper();
					helperGerenciaLoc.setTipoItem("G");
					helperGerenciaLoc.setIdItem(idGerencia);
					helperGerenciaLoc.setNomeItem(localidade.getGerenciaRegional().getNome());
					helperGerenciaLoc.setMetaOriginal(cicloMetaGrupo.getMetaCalculada());
					helperGerenciaLoc.setMetaAtual(cicloMetaGrupo.getMetaAjustada());
					helperGerenciaLoc.setQtdImoveisSituacao(cicloMetaGrupo.getQuantidadeImoveisSituacaoAgua());
					
					/** Usado pelo analisar metas Ciclo **/
					if(cicloMetaGrupo.getQuantidadeRealizada() == null){
						helperGerenciaLoc.setQtdTotalRealizada(0);
					}else {
						helperGerenciaLoc.setQtdTotalRealizada(cicloMetaGrupo.getQuantidadeRealizada());
					}
					
					if(cicloMetaGrupo.getQuantidadeDocumentosRestantes() == null){
						helperGerenciaLoc.setQtdTotalRestante(0);
					}else {
						helperGerenciaLoc.setQtdTotalRestante(cicloMetaGrupo.getQuantidadeDocumentosRestantes());
					}
					
					if(cicloMetaGrupo.getValorTotalDocumentosRestantes() == null){
						helperGerenciaLoc.setValorTotalRestante(new BigDecimal(0));
					}else {
						helperGerenciaLoc.setValorTotalRestante(cicloMetaGrupo.getValorTotalDocumentosRestantes());
					}
					
					/** ------------------------------ **/
			
					helpersGerencia.put(localidade.getGerenciaRegional().getNome(), helperGerenciaLoc);
					
					TreeMap<String, InformarCicloMetaGrupoHelper> helpersUneg = new TreeMap<String, InformarCicloMetaGrupoHelper>();
					helperGerenciaLoc.setSubItens(helpersUneg);

				} else {
					helperGerenciaLoc.setMetaOriginal(helperGerenciaLoc.getMetaOriginal() 
							+ cicloMetaGrupo.getMetaCalculada());
					helperGerenciaLoc.setMetaAtual(helperGerenciaLoc.getMetaAtual() 
							+ cicloMetaGrupo.getMetaAjustada());
					helperGerenciaLoc.setQtdImoveisSituacao(helperGerenciaLoc.getQtdImoveisSituacao() + 
							cicloMetaGrupo.getQuantidadeImoveisSituacaoAgua());
					
					/** Usado pelo analisar metas Ciclo **/
					if(cicloMetaGrupo.getQuantidadeRealizada() == null){
						cicloMetaGrupo.setQuantidadeRealizada(0);
					}
					
					helperGerenciaLoc.setQtdTotalRealizada(helperGerenciaLoc.getQtdTotalRealizada() +
							cicloMetaGrupo.getQuantidadeRealizada());
					
					if(cicloMetaGrupo.getQuantidadeDocumentosRestantes() == null){
						cicloMetaGrupo.setQuantidadeDocumentosRestantes(0);
					}
					
					helperGerenciaLoc.setQtdTotalRestante(helperGerenciaLoc.getQtdTotalRestante() +
							cicloMetaGrupo.getQuantidadeDocumentosRestantes());
					
					if(cicloMetaGrupo.getValorTotalDocumentosRestantes() == null){
						cicloMetaGrupo.setValorTotalDocumentosRestantes(new BigDecimal(0));
					}
					
					helperGerenciaLoc.setValorTotalRestante(helperGerenciaLoc.getValorTotalRestante().
							add(cicloMetaGrupo.getValorTotalDocumentosRestantes()));
					/** ------------------------------ **/
					
					
				}
				
				InformarCicloMetaGrupoHelper helperUnegLoc = helperGerenciaLoc.getSubItens().get(localidade.getUnidadeNegocio().getNome());
				
				if (helperUnegLoc == null){
					
					helperUnegLoc = new InformarCicloMetaGrupoHelper();
					helperUnegLoc.setTipoItem("U");
					helperUnegLoc.setIdItem(idUnidadeNegocio);
					helperUnegLoc.setNomeItem(localidade.getUnidadeNegocio().getNome());
					helperUnegLoc.setMetaOriginal(cicloMetaGrupo.getMetaCalculada());
					helperUnegLoc.setMetaAtual(cicloMetaGrupo.getMetaAjustada());
					helperUnegLoc.setQtdImoveisSituacao(cicloMetaGrupo.getQuantidadeImoveisSituacaoAgua());
					
					/** Usado pelo analisar metas Ciclo **/
					helperUnegLoc.setQtdTotalRealizada(cicloMetaGrupo.getQuantidadeRealizada());
					helperUnegLoc.setQtdTotalRestante(cicloMetaGrupo.getQuantidadeDocumentosRestantes());
					helperUnegLoc.setValorTotalRestante(cicloMetaGrupo.getValorTotalDocumentosRestantes());
					/** ------------------------------ **/
					
					helperGerenciaLoc.getSubItens().put(localidade.getUnidadeNegocio().getNome(), helperUnegLoc);
										
					TreeMap<String, InformarCicloMetaGrupoHelper> helpersLoc = new TreeMap<String, InformarCicloMetaGrupoHelper>();
					helperUnegLoc.setSubItens(helpersLoc);

				} else {
					helperUnegLoc.setMetaOriginal(helperUnegLoc.getMetaOriginal() 
							+ cicloMetaGrupo.getMetaCalculada());
					helperUnegLoc.setMetaAtual(helperUnegLoc.getMetaAtual() 
							+ cicloMetaGrupo.getMetaAjustada());
					helperUnegLoc.setQtdImoveisSituacao(helperUnegLoc.getQtdImoveisSituacao() + 
							cicloMetaGrupo.getQuantidadeImoveisSituacaoAgua());
					
					/** Usado pelo analisar metas Ciclo **/
					helperUnegLoc.setQtdTotalRealizada(helperGerenciaLoc.getQtdTotalRealizada() +
							cicloMetaGrupo.getQuantidadeRealizada());
					helperUnegLoc.setQtdTotalRestante(helperGerenciaLoc.getQtdTotalRestante() +
							cicloMetaGrupo.getQuantidadeDocumentosRestantes());
					helperUnegLoc.setValorTotalRestante(helperGerenciaLoc.getValorTotalRestante().
							add(cicloMetaGrupo.getValorTotalDocumentosRestantes()));
					/** ------------------------------ **/
					
				}
				
				InformarCicloMetaGrupoHelper helperLoc = helperUnegLoc.getSubItens().get(localidade.getDescricao());
				
				if (helperLoc == null){
					helperLoc = new InformarCicloMetaGrupoHelper();
					helperLoc.setTipoItem("L");
					helperLoc.setIdItem(localidade.getId());
					helperLoc.setNomeItem(localidade.getDescricao());
					helperLoc.setMetaOriginal(cicloMetaGrupo.getMetaCalculada());
					helperLoc.setMetaAtual(cicloMetaGrupo.getMetaAjustada());
					helperLoc.setQtdImoveisSituacao(cicloMetaGrupo.getQuantidadeImoveisSituacaoAgua());
					
					/** Usado pelo analisar metas Ciclo **/
					helperLoc.setQtdTotalRealizada(cicloMetaGrupo.getQuantidadeRealizada());
					helperLoc.setQtdTotalRestante(cicloMetaGrupo.getQuantidadeDocumentosRestantes());
					helperLoc.setValorTotalRestante(cicloMetaGrupo.getValorTotalDocumentosRestantes());
					/** ------------------------------ **/
					
					helperUnegLoc.getSubItens().put(localidade.getDescricao(), helperLoc);		
				} else {
					helperLoc.setMetaOriginal(helperLoc.getMetaOriginal() 
							+ cicloMetaGrupo.getMetaCalculada());
					helperLoc.setMetaAtual(helperLoc.getMetaAtual() 
							+ cicloMetaGrupo.getMetaAjustada());
					helperLoc.setQtdImoveisSituacao(helperLoc.getQtdImoveisSituacao() + 
							cicloMetaGrupo.getQuantidadeImoveisSituacaoAgua());
					
					/** Usado pelo analisar metas Ciclo **/
					helperLoc.setQtdTotalRealizada(helperGerenciaLoc.getQtdTotalRealizada() +
							cicloMetaGrupo.getQuantidadeRealizada());
					helperLoc.setQtdTotalRestante(helperGerenciaLoc.getQtdTotalRestante() +
							cicloMetaGrupo.getQuantidadeDocumentosRestantes());
					helperLoc.setValorTotalRestante(helperGerenciaLoc.getValorTotalRestante().
							add(cicloMetaGrupo.getValorTotalDocumentosRestantes()));
					/** ------------------------------ **/
				}
			}			
		}
		
		return helpersGerencia;
		
	}


}