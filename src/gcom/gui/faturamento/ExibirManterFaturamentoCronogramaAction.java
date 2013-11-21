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
package gcom.gui.faturamento;

import gcom.faturamento.FaturamentoGrupoCronogramaMensal;
import gcom.faturamento.FiltroFaturamentoGrupoCronogramaMensal;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Map;

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
public class ExibirManterFaturamentoCronogramaAction extends GcomAction {

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
                .findForward("manterFaturamentoCronograma");

       // Fachada fachada = Fachada.getInstancia();

        //Mudar isso quando tiver esquema de segurança
        HttpSession sessao = httpServletRequest.getSession(false);

        String identificadorAtualizar = (String)sessao.getAttribute("indicadorAtualizar");
        
        FiltroFaturamentoGrupoCronogramaMensal filtroFaturamentoGrupoCronogramaMensal = (FiltroFaturamentoGrupoCronogramaMensal) sessao
                .getAttribute("filtroFaturamentoGrupoCronogramaMensal");

//        FiltroFaturamentoGru
//        Collection faturamentoGrupos = fachada.pesquisar(
//               filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
//        //Coleçao q vai guardar os faturamentos grupos cronogramas mensais
//        // filtrados
      //  Collection faturamentoGrupoCronogramaMensaisFiltrados = new HashSet();

//        FiltroFaturamentoGrupoCronogramaMensal filtroFaturamentoGrupoCronogramaMensal = new FiltroFaturamentoGrupoCronogramaMensal();

//        if (!faturamentoGrupos.isEmpty()) {
//        	Iterator iteratorFaturamentoGrupo = faturamentoGrupos.iterator();
        	
        	
//            FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) faturamentoGrupos
//                    .iterator().next();
//
//            filtroFaturamentoGrupoCronogramaMensal
//                    .adicionarParametro(new ParametroSimples(
//                            FiltroFaturamentoGrupoCronogramaMensal.ID_FATURAMENTO_GRUPO,
//                            filtroFaturamentoGrupoCronogramaMensalSessao.getId()));
//            filtroFaturamentoGrupoCronogramaMensal
//                    .adicionarParametro(new MaiorQue(
//                            FiltroFaturamentoGrupoCronogramaMensal.ANO_MES_REFERENCIA,
//                            faturamentoGrupo.getAnoMesReferencia()));
//            
//            filtroFaturamentoGrupoCronogramaMensal.setCampoOrderBy(FiltroFaturamentoGrupoCronogramaMensal.REFERENCIA);
//            if(httpServletRequest.getAttribute("anoMes") != null && !httpServletRequest.getAttribute("anoMes").toString().trim().equalsIgnoreCase("")){
//            	filtroFaturamentoGrupoCronogramaMensal.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupoCronogramaMensal.ANO_MES_REFERENCIA, 
//            			httpServletRequest.getAttribute("anoMes").toString()));
//            }

   	
//        	 novo componente de paginação
        	filtroFaturamentoGrupoCronogramaMensal.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupo");
        	
        	filtroFaturamentoGrupoCronogramaMensal.setCampoOrderBy(FiltroFaturamentoGrupoCronogramaMensal.FATURAMENTO_GRUPO,
        			FiltroFaturamentoGrupoCronogramaMensal.REFERENCIA);
            Map resultado = controlarPaginacao(httpServletRequest, retorno,
            		filtroFaturamentoGrupoCronogramaMensal, FaturamentoGrupoCronogramaMensal.class.getName());
            Collection faturamentoGrupoCronogramaMensais = (Collection) resultado.get("colecaoRetorno");
    		retorno = (ActionForward) resultado.get("destinoActionForward");
    		

    		// [FS0004] Nenhum registro encontrado				
    		if (faturamentoGrupoCronogramaMensais == null || faturamentoGrupoCronogramaMensais.isEmpty()) {
    			// Nenhuma Localidade cadastrado
    			throw new ActionServletException("atencao.nao_cadastrado.faturamento_atividade_cronograma");
    		}
            
//---Fim
//	            Collection faturamentoGrupoCronogramaMensais = fachada.pesquisar(
//	                    filtroFaturamentoGrupoCronogramaMensal,
//	                    FaturamentoGrupoCronogramaMensal.class.getName());

	            
	            //verifica se a algum Faturamento Grupo Cronograma Mensal a ser
	            // retornado pelo filtro

	            if(identificadorAtualizar == null){
	            	identificadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");
	            }
	            
//	            if (!faturamentoGrupoCronogramaMensais.isEmpty()) {
//	
//	            	//ordenação através do sort---Feita pelo mesAnoReferencia
//	                Collections.sort((List) faturamentoGrupoCronogramaMensais, new Comparator() {
//	                    public int compare(Object a, Object b) {
//	                            String faturamentoGrupoCronogramaMensail1 = ((FaturamentoGrupoCronogramaMensal) a).getAnoMesReferencia().toString();
//	                            String faturamentoGrupoCronogramaMensail2 = ((FaturamentoGrupoCronogramaMensal) b).getAnoMesReferencia().toString();
//	
//	                            return faturamentoGrupoCronogramaMensail1.compareTo(faturamentoGrupoCronogramaMensail2);
//	                    }
//	                });
	            	
//	            	Iterator iteratorFaturamentoGrupoCronogramaMensal = faturamentoGrupoCronogramaMensais
//	                        .iterator();
//	                FiltroFaturamentoAtividadeCronograma filtroFaturamentoAtividadeCronograma = new FiltroFaturamentoAtividadeCronograma();
//	                FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal = null;
//	                Collection faturamentoAtividadeCronogramas = null;
	
//	                while (iteratorFaturamentoGrupoCronogramaMensal.hasNext()) {
//	                    //Procura Faturamento Atividade Cronogramas Associados a o
//	                    // Grupo Cronograma Mensal
//	                    faturamentoAtividadeCronogramas = null;
//	                    filtroFaturamentoAtividadeCronograma
//	                            .limparListaParametros();
//	                    faturamentoGrupoCronogramaMensal = (FaturamentoGrupoCronogramaMensal) iteratorFaturamentoGrupoCronogramaMensal
//	                            .next();
//	                    filtroFaturamentoAtividadeCronograma
//	                            .adicionarParametro(new ParametroSimples(
//	                                    FiltroFaturamentoAtividadeCronograma.FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL_ID,
//	                                    faturamentoGrupoCronogramaMensal.getId()));
//	                    faturamentoAtividadeCronogramas = fachada.pesquisar(
//	                            filtroFaturamentoAtividadeCronograma,
//	                            FaturamentoAtividadeCronograma.class.getName());
//	                    if (!faturamentoAtividadeCronogramas.isEmpty()) {
//	                        //Parte para testar se a alguma atividade em aberto
//	                        Iterator iteratorFaturamentoAtividadeCronograma = faturamentoAtividadeCronogramas
//	                                .iterator();
//	                        FaturamentoAtividadeCronograma faturamentoAtividadeCronograma = null;
//	
//	                        while (iteratorFaturamentoAtividadeCronograma.hasNext()) {
//	                            faturamentoAtividadeCronograma = (FaturamentoAtividadeCronograma) iteratorFaturamentoAtividadeCronograma
//	                                    .next();
//	                            if (faturamentoAtividadeCronograma
//	                                    .getDataRealizacao() == null) {
//	                                faturamentoGrupoCronogramaMensaisFiltrados
//	                                        .add(faturamentoGrupoCronogramaMensal);
//	                            }
//	                        }
//	                    }
//	                }
//	            }
//        }
        
        if (faturamentoGrupoCronogramaMensais.isEmpty()) {
            throw new ActionServletException("atencao.nao_cadastrado.faturamento_atividade_cronograma");
        } else {
        	if(faturamentoGrupoCronogramaMensais.size() == 1 && identificadorAtualizar != null && !identificadorAtualizar.trim().equals("")){
//        		 caso o resultado do filtro só retorne um registro 
				// e o check box Atualizar estiver selecionado
				//o sistema não exibe a tela de manter, exibe a de atualizar 
				retorno = actionMapping.findForward("exibirAtualizar");
				FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal = (FaturamentoGrupoCronogramaMensal)faturamentoGrupoCronogramaMensais.iterator().next();
				sessao.setAttribute("idRegistroAtualizacao", new Integer (faturamentoGrupoCronogramaMensal.getId()).toString());
				sessao.setAttribute("voltar", "filtrar");
        	}else{
        		sessao.setAttribute("faturamentoGruposCronogramaMensais",
        				faturamentoGrupoCronogramaMensais);
        		FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal = (FaturamentoGrupoCronogramaMensal)faturamentoGrupoCronogramaMensais.iterator().next();
        		sessao.setAttribute("idRegistroAtualizacao", new Integer (faturamentoGrupoCronogramaMensal.getId()).toString());
        		sessao.setAttribute("voltar", "manter");
        	}
        }

        return retorno;

    }
}
