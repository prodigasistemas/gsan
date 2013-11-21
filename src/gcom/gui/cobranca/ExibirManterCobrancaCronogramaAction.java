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

import gcom.cobranca.CobrancaAcaoAtividadeCronograma;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.CobrancaGrupoCronogramaMes;
import gcom.cobranca.FiltroCobrancaAcaoAtividadeCronograma;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

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
public class ExibirManterCobrancaCronogramaAction extends GcomAction {

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

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("manterCobrancaCronograma");

        Fachada fachada = Fachada.getInstancia();
        
        CobrancaActionForm cobrancaActionForm = (CobrancaActionForm) actionForm;

        //Mudar isso quando tiver esquema de segurança
        HttpSession sessao = httpServletRequest.getSession(false);
        
        	FiltroCobrancaAcaoAtividadeCronograma filtroCobrancaAcaoAtividadeCronograma = (FiltroCobrancaAcaoAtividadeCronograma)sessao.getAttribute("filtroCobrancaAcaoAtividade");
        	
//        	 1º Passo - Pegar o total de registros através de um count da consulta que aparecerá na tela
    		Integer totalRegistros = fachada
    				.pesquisarCobrancaCronogramaCount(filtroCobrancaAcaoAtividadeCronograma);

    		// 2º Passo - Chamar a função de Paginação passando o total de registros
    		retorno = this.controlarPaginacao(httpServletRequest, retorno,
    				totalRegistros);
    		
    		FiltroCobrancaAcaoAtividadeCronograma filtroCobrancaAcaoAtividadeCronogramaDois = new FiltroCobrancaAcaoAtividadeCronograma();
    		filtroCobrancaAcaoAtividadeCronogramaDois = (FiltroCobrancaAcaoAtividadeCronograma)sessao.getAttribute("filtroCobrancaAcaoAtividade");
//    		filtroCobrancaAcaoAtividadeCronogramaDois.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoCronograma.cobrancaGrupoCronogramaMes.cobrancaGrupo");
    		
    		// 3º Passo - Obter a coleção da consulta que aparecerá na tela passando o numero de paginas
    		// da pesquisa que está no request
    		Collection colecaoCobrancaCronograma = fachada
    				.pesquisar(filtroCobrancaAcaoAtividadeCronogramaDois,
    				(Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa"),
    				CobrancaAcaoAtividadeCronograma.class.getName());
    		
    		Collection colecaoCronogramaNova = new ArrayList();
    		
    		
    		
    		if(!colecaoCobrancaCronograma.isEmpty()){
    			Iterator iteratorColecaoCobrancaCronograma = colecaoCobrancaCronograma.iterator();
        		Collection colecaoCobrancaGrupo = new ArrayList();
        		while(iteratorColecaoCobrancaCronograma.hasNext()){
        			CobrancaGrupoCronogramaMes cobrancaGrupoCronogramaMes = (CobrancaGrupoCronogramaMes)iteratorColecaoCobrancaCronograma.next();
        			FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();
        			filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(FiltroCobrancaGrupo.ID, cobrancaGrupoCronogramaMes.getCobrancaGrupo().getId()));
        			
        			colecaoCobrancaGrupo = fachada.pesquisar(filtroCobrancaGrupo, CobrancaGrupo.class.getName());
        			
        			cobrancaGrupoCronogramaMes.setCobrancaGrupo((CobrancaGrupo)colecaoCobrancaGrupo.iterator().next());
        			
        			colecaoCronogramaNova.add(cobrancaGrupoCronogramaMes);
        		}
    			//ordenação através do sort---Feita pelo mesAnoReferencia
	            Collections.sort((List) colecaoCronogramaNova, new Comparator() {
	                public int compare(Object a, Object b) {
	                        String cobrancaAcaoAtividadeCronograma1 = ((CobrancaGrupoCronogramaMes) a).getMesAno();
	                        String cobrancaAcaoAtividadeCronograma2 = ((CobrancaGrupoCronogramaMes) b).getMesAno();
	
	                        return cobrancaAcaoAtividadeCronograma1.compareTo(cobrancaAcaoAtividadeCronograma2);
	                }
	            });
    		}
        
////      Aciona o controle de paginação para que sejam pesquisados apenas
//		// os registros que aparecem na página
//		Map resultado = controlarPaginacao(httpServletRequest, retorno,
//				filtroCobrancaAcaoAtividadeCronograma, CobrancaAcaoAtividadeCronograma.class.getName());
//		Collection colecaoCobrancaCronograma = (Collection) resultado.get("colecaoRetorno");
//		retorno = (ActionForward) resultado.get("destinoActionForward");
//		
		String identificadorAtualizar = cobrancaActionForm.getIndicadorAtualizar();
		
		if (colecaoCobrancaCronograma.size()== 1 && identificadorAtualizar != null
				&& !identificadorAtualizar.trim().equals("2") ){
			// caso o resultado do filtro só retorne um registro 
			// e o check box Atualizar estiver selecionado
			//o sistema não exibe a tela de manter, exibe a de atualizar 
			retorno = actionMapping.findForward("atualizarCobrancaCronograma");
			CobrancaGrupoCronogramaMes cobrancaGrupoCronogramaMes = (CobrancaGrupoCronogramaMes)Util.retonarObjetoDeColecao(colecaoCobrancaCronograma);
			sessao.setAttribute("idRegistroAtualizacao", new Integer (cobrancaGrupoCronogramaMes.getId()).toString());
			sessao.setAttribute("voltaFiltro", true);
		}else if(colecaoCobrancaCronograma.size()== 0){
			throw new
			 ActionServletException("atencao.pesquisa.nenhumresultado");
    	}else{
			sessao.setAttribute("colecaoCobrancaCronograma", colecaoCronogramaNova);
			sessao.removeAttribute("voltaFiltro");
		}


        return retorno;

    }
}
