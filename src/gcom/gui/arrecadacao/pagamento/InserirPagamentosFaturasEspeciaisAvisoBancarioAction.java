/*
* Copyright (C) 2007-2007 the GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
* Foundation, Inc., 59 Temple Place – Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
package gcom.gui.arrecadacao.pagamento;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.FiltroArrecadacaoForma;
import gcom.arrecadacao.FiltroAvisoBancario;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que finaliza a primeira página do processo de inserir pagamentos
 * 
 * [UC0971] Inserir Pagamentos para Faturas Especiais
 * 
 * @author 	Vivianne Sousa
 * @created	21/12/2009
 */
public class InserirPagamentosFaturasEspeciaisAvisoBancarioAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {

    	//cria a variável que vai armazenar o retorno
    	ActionForward retorno = null;
    	
    	//cria uma instÂncia da sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        //recupera o form 
        PagamentosFaturasEspeciaisActionForm pagamentoActionForm = (PagamentosFaturasEspeciaisActionForm) actionForm;

        //cria uma instância da fachada
        Fachada fachada = Fachada.getInstancia();

        //recupera os dados informados na página de aviso bancário
        String dataPagamento =  pagamentoActionForm.getDataPagamento();
        String idAvisoBancario =  pagamentoActionForm.getIdAvisoBancario();  
        String idFormaArrecadacao = pagamentoActionForm.getIdFormaArrecadacao();

        //se a data de pagamento não for informada 
        if(dataPagamento == null || dataPagamento.trim().equalsIgnoreCase("")){
        	//levanta a exceção para a próxima camada
        	throw new ActionServletException("atencao.naoinformado",null, "Data de Pagamento");
        }
        
        //se o aviso bancário não for informado
        if(idAvisoBancario == null || idAvisoBancario.trim().equalsIgnoreCase("")){
        	//levanta a exceção para a próxima camada
        	throw new ActionServletException("atencao.naoinformado",null, "Aviso Bancário");
        } else {

        	FiltroAvisoBancario filtroAvisoBancario = new FiltroAvisoBancario();
        	filtroAvisoBancario.adicionarCaminhoParaCarregamentoEntidade(FiltroAvisoBancario.ARRECADADOR);
        	filtroAvisoBancario.adicionarParametro(new ParametroSimples(FiltroAvisoBancario.ID, idAvisoBancario));
        	
        	Collection colecaoAvisoBancario = fachada.pesquisar(filtroAvisoBancario, AvisoBancario.class.getName());
        	
        	if (colecaoAvisoBancario != null && !colecaoAvisoBancario.isEmpty()) {
        		AvisoBancario avisoBancario = (AvisoBancario) Util.retonarObjetoDeColecao(colecaoAvisoBancario);
        		
        		pagamentoActionForm.setCodigoAgenteArrecadador(avisoBancario.getArrecadador().getCodigoAgente().toString());
        		pagamentoActionForm.setDataLancamentoAviso(Util.formatarData(avisoBancario.getDataLancamento()));
        		pagamentoActionForm.setNumeroSequencialAviso(avisoBancario.getNumeroSequencial().toString());
        		
        	} else {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Aviso Bancário");
			}

		}
        
        // se a forma de arrecadação não for informada
        if(idFormaArrecadacao == null || idFormaArrecadacao.trim().equalsIgnoreCase("")){
        	//levanta a exceção para a próxima camada
        	throw new ActionServletException("atencao.naoinformado",null, "Forma de Arrecadação");
        }
        
        //cria o formato da data
        SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");

        try {
        	//tenta converter a data de apagamento
            dataFormato.parse(dataPagamento);

            //se não conseguir converter
        } catch (ParseException ex) {
        	//levanta a exceção para a próxima camada
        	throw new ActionServletException("atencao.data_pagamento_invalida");
        }
        
        //recupera o status atual do wizard
        StatusWizard statusWizard = (StatusWizard) sessao.getAttribute("statusWizard");
                
        //cria o filtro de forma de arrecadação
        FiltroArrecadacaoForma filtroArrecadacaoForma = new FiltroArrecadacaoForma();
        
        //seta o código da forma de arrecadação no filtro
        filtroArrecadacaoForma.adicionarParametro(new ParametroSimples(FiltroArrecadacaoForma.CODIGO, idFormaArrecadacao));
        
        //pesquisa a forma de arrecadação no sistema
        Collection colecaoFormaArrecadacao = fachada.pesquisar(filtroArrecadacaoForma, ArrecadacaoForma.class.getName());
        
        //se não existir a forma de arrecadação no sistema
        if(colecaoFormaArrecadacao == null || colecaoFormaArrecadacao.isEmpty()){
        	//levanta a exceção para a próxima camada
        	throw new ActionServletException("atencao.naocadastrado",null, "Forma de Arrecadação");
        	
        }else{
        	//recupera a forma de arrecadação da coleção
        	ArrecadacaoForma formaArrecadacao =(ArrecadacaoForma) Util.retonarObjetoDeColecao(colecaoFormaArrecadacao);
        	
        	//setaa descrição da forma de arrecadação no form
        	pagamentoActionForm.setDescricaoFormaArrecadacao(formaArrecadacao.getDescricao());
        }
        	
//        } else if(tipoInclusao != null && tipoInclusao.equalsIgnoreCase("caneta")){
            
        	//seta o status do wizard para a página de inclusão por caneta
        	statusWizard.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                    2, "TipoInclusaoA.gif", "TipoInclusaoD.gif",
                    "exibirInserirPagamentosFaturasEspeciaisTipoInclusaoCanetaAction",
                    "inserirPagamentosFaturasEspeciaisTipoInclusaoCanetaAction"));
        	
        	retorno = actionMapping.findForward("inserirPagamentosTipoInclusaoCaneta");
        	

//        }

 
    	
        //seta o form na sessão
//        sessao.setAttribute("PagamentosFaturasEspeciaisActionForm",pagamentoActionForm);
        
        //seta o status do wizard na sessão
        sessao.setAttribute("statusWizard",statusWizard);
        
        //retorna o mapeamento contido na variável retorno
        return retorno;
    }
}
