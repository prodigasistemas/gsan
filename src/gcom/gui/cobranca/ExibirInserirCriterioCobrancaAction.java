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

import java.util.Collection;
import java.util.Iterator;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.FiltroCobrancaSituacao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Pre- processamento para inserir o criterio da cobrança
 * 
 * @author Sávio Luiz
 * @date 17/04/2006
 */
public class ExibirInserirCriterioCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("inserirCriterioCobranca");

		HttpSession sessao = httpServletRequest.getSession(false);

		CriterioCobrancaActionForm criterioCobrancaActionForm = (CriterioCobrancaActionForm) actionForm;
		if (httpServletRequest.getParameter("limpaSessao") != null
				&& !httpServletRequest.getParameter("limpaSessao").equals("")) {
			criterioCobrancaActionForm.setDescricaoCriterio("");
			criterioCobrancaActionForm.setDataInicioVigencia("");
			criterioCobrancaActionForm.setNumeroAnoContaAntiga("");
			criterioCobrancaActionForm
					.setOpcaoAcaoImovelDebitoContasAntigas("");
			criterioCobrancaActionForm.setOpcaoAcaoImovelDebitoMesConta("");
			criterioCobrancaActionForm.setOpcaoAcaoImovelSit("");
			criterioCobrancaActionForm.setOpcaoAcaoImovelSitEspecial("");
			criterioCobrancaActionForm.setOpcaoAcaoInquilinoDebitoMesConta("");
			criterioCobrancaActionForm.setOpcaoContasRevisao("");
			criterioCobrancaActionForm.setPercentualQuantidadeMinimoPagoParceladoCancelado("");
			criterioCobrancaActionForm.setPercentualValorMinimoPagoParceladoCancelado("");
			criterioCobrancaActionForm.setValorLimitePrioridade("");

			sessao.removeAttribute("colecaoCobrancaCriterioLinha");

		}
		
		Fachada fachada = Fachada.getInstancia();
		
		// consultar as situacoes de cobranca
        FiltroCobrancaSituacao filtroCobrancaSituacao = new FiltroCobrancaSituacao();
        
        filtroCobrancaSituacao.adicionarParametro(new ParametroSimples(FiltroCobrancaSituacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
        filtroCobrancaSituacao.setCampoOrderBy(FiltroCobrancaSituacao.DESCRICAO);
        
        Collection colecaoCobrancaSituacao = fachada.pesquisar(filtroCobrancaSituacao, CobrancaSituacao.class.getName());
        sessao.setAttribute("colecaoCobrancaSituacao", colecaoCobrancaSituacao);
        
        // Verificando se ha algum item selecionado, caso nao, selecionar tudo
        if (criterioCobrancaActionForm.getIdsCobrancaSituacao() == null || 
        		criterioCobrancaActionForm.getIdsCobrancaSituacao().length == 0){
	        Iterator iterCobSit = colecaoCobrancaSituacao.iterator();
	        String[] idsCobSit = new String[colecaoCobrancaSituacao.size()];
	        int i = 0;
	        while (iterCobSit.hasNext()) {
				CobrancaSituacao cobSit = (CobrancaSituacao) iterCobSit.next();
				idsCobSit[i++] = cobSit.getId() + "";			
			}
	        criterioCobrancaActionForm.setIdsCobrancaSituacao(idsCobSit);
        }
        
        // consultar as situacoes de ligacao de agua
        FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
        
        filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
        filtroLigacaoAguaSituacao.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);
        
        Collection colecaoLigacaoAguaSituacao = fachada.pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
        sessao.setAttribute("colecaoSituacaoLigacaoAgua", colecaoLigacaoAguaSituacao);

        // Verificando se ha algum item selecionado, caso nao, selecionar tudo        
        if (criterioCobrancaActionForm.getIdsSituacaoLigacaoAgua() == null || 
        		criterioCobrancaActionForm.getIdsSituacaoLigacaoAgua().length == 0){
	        Iterator iterLigAguaSit = colecaoLigacaoAguaSituacao.iterator();
	        String[] idsLigAguaSit = new String[colecaoLigacaoAguaSituacao.size()];
	        int i = 0;
	        while (iterLigAguaSit.hasNext()) {
				LigacaoAguaSituacao ligAguaSit = (LigacaoAguaSituacao) iterLigAguaSit.next();
				idsLigAguaSit[i++] = ligAguaSit.getId() + "";			
			}
	        criterioCobrancaActionForm.setIdsSituacaoLigacaoAgua(idsLigAguaSit);
        }

        // consultar as situacoes de ligacao de agua
        FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
        
        filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoSituacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
        filtroLigacaoEsgotoSituacao.setCampoOrderBy(FiltroLigacaoEsgotoSituacao.DESCRICAO);
        
        Collection colecaoLigacaoEsgotoSituacao = fachada.pesquisar(filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());
        sessao.setAttribute("colecaoSituacaoLigacaoEsgoto", colecaoLigacaoEsgotoSituacao);
        
        // Verificando se ha algum item selecionado, caso nao, selecionar tudo        
        if (criterioCobrancaActionForm.getIdsSituacaoLigacaoEsgoto() == null || 
        		criterioCobrancaActionForm.getIdsSituacaoLigacaoEsgoto().length == 0){
        
	        Iterator iterLigEsgotoSit = colecaoLigacaoEsgotoSituacao.iterator();
	        String[] idsLigEsgotoSit = new String[colecaoLigacaoEsgotoSituacao.size()];
	        int i = 0;
	        while (iterLigEsgotoSit.hasNext()) {
				LigacaoEsgotoSituacao ligEsgotoSit = (LigacaoEsgotoSituacao) iterLigEsgotoSit.next();
				idsLigEsgotoSit[i++] = ligEsgotoSit.getId() + "";			
			}
	        criterioCobrancaActionForm.setIdsSituacaoLigacaoEsgoto(idsLigEsgotoSit);
        }


		return retorno;
	}
}
