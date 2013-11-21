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


import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade receber os parâmetros informados pelo usuário e realizar uma 
 * filtragem dos movimentos dos arrecadadores a partir dos mesmos 
 *
 * @author Raphael Rossiter, Pedro Alexandre
 * @date 23/02/2006, 06/07/2007
 */
public class FiltrarMovimentoArrecadadoresAction extends GcomAction {
    
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = null;
        
        Fachada fachada = Fachada.getInstancia();
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        FiltrarMovimentoArrecadadoresActionForm filtrarMovimentoArrecadadoresActionForm = 
        (FiltrarMovimentoArrecadadoresActionForm) actionForm;
        
        String codigoBanco = null;
		String codigoRemessa = null;
		String descricaoIdentificacaoServico = null;
		String numeroSequencialArquivo = null;
		Date dataGeracaoInicio = null;
		Date dataGeracaoFim = null;
		Date ultimaAlteracaoInicio = null;
		Date ultimaAlteracaoFim = null;
		String descricaoOcorrencia = null;
		String indicadorAceitacao = null; 
		String indicadorAbertoFechado = null;
		String codigoFormaArrecadacao = null;
		boolean peloMenosUmParametroInformado = false;
                
		 if (filtrarMovimentoArrecadadoresActionForm.getBanco() != null &&
        	!filtrarMovimentoArrecadadoresActionForm.getBanco().equalsIgnoreCase("")){
        	
			 codigoBanco = filtrarMovimentoArrecadadoresActionForm.getBanco().trim();
        	
        	peloMenosUmParametroInformado = true;
        }
        
        if (filtrarMovimentoArrecadadoresActionForm.getRemessa() != null &&
            !filtrarMovimentoArrecadadoresActionForm.getRemessa().equalsIgnoreCase("")){
            	
        	codigoRemessa = filtrarMovimentoArrecadadoresActionForm.getRemessa().trim();
            	
            peloMenosUmParametroInformado = true;
        }
        
        if (filtrarMovimentoArrecadadoresActionForm.getIdentificacaoServico() != null &&
            !filtrarMovimentoArrecadadoresActionForm.getIdentificacaoServico().equalsIgnoreCase("") &&
            !filtrarMovimentoArrecadadoresActionForm.getIdentificacaoServico().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	
        	descricaoIdentificacaoServico = filtrarMovimentoArrecadadoresActionForm.getIdentificacaoServico().trim();

        	peloMenosUmParametroInformado = true;
        }
        
        //Forma de arrecadação
        if (filtrarMovimentoArrecadadoresActionForm.getFormaArrecadacao() != null &&
           !filtrarMovimentoArrecadadoresActionForm.getFormaArrecadacao().equalsIgnoreCase("") &&
           !filtrarMovimentoArrecadadoresActionForm.getFormaArrecadacao().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
            	
        		codigoFormaArrecadacao = filtrarMovimentoArrecadadoresActionForm.getFormaArrecadacao().trim();

            	peloMenosUmParametroInformado = true;
            }
        
        if (filtrarMovimentoArrecadadoresActionForm.getNsa() != null &&
            !filtrarMovimentoArrecadadoresActionForm.getNsa().equalsIgnoreCase("")){
            	
        	numeroSequencialArquivo = filtrarMovimentoArrecadadoresActionForm.getNsa().trim();
            	
            peloMenosUmParametroInformado = true;
        }
        
        if (filtrarMovimentoArrecadadoresActionForm.getDataGeracaoMovimentoInicio() != null &&
            !filtrarMovimentoArrecadadoresActionForm.getDataGeracaoMovimentoInicio().equalsIgnoreCase("")){
    		
        	dataGeracaoInicio = Util.converteStringParaDate(filtrarMovimentoArrecadadoresActionForm.getDataGeracaoMovimentoInicio());
        	dataGeracaoFim = dataGeracaoInicio;
        	
        	peloMenosUmParametroInformado = true;
		
        }
        
        if (filtrarMovimentoArrecadadoresActionForm.getDataGeracaoMovimentoFim() != null &&
		        !filtrarMovimentoArrecadadoresActionForm.getDataGeracaoMovimentoFim().equalsIgnoreCase("")){
			
        	dataGeracaoFim = Util.converteStringParaDate(filtrarMovimentoArrecadadoresActionForm.getDataGeracaoMovimentoFim());
        	
        	peloMenosUmParametroInformado = true;
		}
        
        if (filtrarMovimentoArrecadadoresActionForm.getDataProcessamentoMovimentoInicio() != null &&
                !filtrarMovimentoArrecadadoresActionForm.getDataProcessamentoMovimentoInicio().equalsIgnoreCase("")){
        		
        	ultimaAlteracaoInicio = Util.converteStringParaDate(filtrarMovimentoArrecadadoresActionForm.getDataProcessamentoMovimentoInicio());
        	ultimaAlteracaoFim = ultimaAlteracaoInicio;
        	
        	peloMenosUmParametroInformado = true;
		
        }
        
        if (filtrarMovimentoArrecadadoresActionForm.getDataProcessamentoMovimentoFim() != null &&
		        !filtrarMovimentoArrecadadoresActionForm.getDataProcessamentoMovimentoFim().equalsIgnoreCase("")){
			
        	ultimaAlteracaoFim = Util.converteStringParaDate(filtrarMovimentoArrecadadoresActionForm.getDataProcessamentoMovimentoFim());
        	
        	peloMenosUmParametroInformado = true;
		}
        
        if (filtrarMovimentoArrecadadoresActionForm.getMovimentoItemOcorrencia() != null &&
            !filtrarMovimentoArrecadadoresActionForm.getMovimentoItemOcorrencia().equalsIgnoreCase("") &&
            !filtrarMovimentoArrecadadoresActionForm.getMovimentoItemOcorrencia().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
            
        	descricaoOcorrencia = filtrarMovimentoArrecadadoresActionForm.getMovimentoItemOcorrencia().trim();
        	
            peloMenosUmParametroInformado = true;
        }
        
        if (filtrarMovimentoArrecadadoresActionForm.getMovimentoItemAceito() != null &&
            !filtrarMovimentoArrecadadoresActionForm.getMovimentoItemAceito().equalsIgnoreCase("") &&
            !filtrarMovimentoArrecadadoresActionForm.getMovimentoItemAceito().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
            	
        	indicadorAceitacao = filtrarMovimentoArrecadadoresActionForm.getMovimentoItemAceito().trim();
        	
            peloMenosUmParametroInformado = true;
        }
        
        if (filtrarMovimentoArrecadadoresActionForm.getMovimentoAbertoFechado() != null &&
            !filtrarMovimentoArrecadadoresActionForm.getMovimentoAbertoFechado().equalsIgnoreCase("") &&
            !filtrarMovimentoArrecadadoresActionForm.getMovimentoAbertoFechado().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
            	
        	indicadorAbertoFechado = filtrarMovimentoArrecadadoresActionForm.getMovimentoAbertoFechado();
        	
            peloMenosUmParametroInformado = true;
        }
        
        
        //Erro caso o usuário mandou filtrar sem nenhum parâmetro
        if (!peloMenosUmParametroInformado) {
            throw new ActionServletException(
            "atencao.filtro.nenhum_parametro_informado");
        }
		
		String indicadorRelatorio =  filtrarMovimentoArrecadadoresActionForm.getIndicadorRelatorio();
        
        if(indicadorRelatorio != null && indicadorRelatorio.equals("" + ConstantesSistema.SIM)){
        	retorno = actionMapping.findForward("gerarRelatorioAcompanhamentoMovimentoArrecadadoresPorNSA");
        	codigoFormaArrecadacao = filtrarMovimentoArrecadadoresActionForm.getFormaArrecadacao();
        	httpServletRequest.setAttribute("idFormaArrecadacao",codigoFormaArrecadacao);
        	
    		Collection colecaoMovimentoArrecadador = fachada.filtrarIdsMovimentoArrecadador(
   				 codigoBanco, codigoRemessa, descricaoIdentificacaoServico,
   	    		 numeroSequencialArquivo, dataGeracaoInicio,
   	    		 dataGeracaoFim, ultimaAlteracaoInicio, ultimaAlteracaoFim,
   	    		 descricaoOcorrencia, indicadorAceitacao,indicadorAbertoFechado);
   		
           
           sessao.setAttribute("colecaoMovimentoArrecadador", colecaoMovimentoArrecadador);
        }else{
        	retorno = actionMapping.findForward("efetuarAnaliseMovimentoArrecadadores");
        
        
		//1º Passo - Pegar o total de registros através de um count da consulta
		// que aparecerá na tela
		Integer totalRegistros = fachada.filtrarMovimentoArrecadadoresCount
				(codigoBanco, codigoRemessa, descricaoIdentificacaoServico,
	    		 numeroSequencialArquivo, dataGeracaoInicio,
	    		 dataGeracaoFim, ultimaAlteracaoInicio, ultimaAlteracaoFim,
	    		 descricaoOcorrencia, indicadorAceitacao,indicadorAbertoFechado);
        
		//2º Passo - Chamar a função de Paginação passando o total de registros
		retorno = this.controlarPaginacao(httpServletRequest, retorno,totalRegistros);
		
		//	3º Passo - Obter a coleção da consulta que aparecera na tela passando
		// o numero de paginas da pesquisa que está no request
		Collection colecaoMovimentoArrecadador = fachada.filtrarMovimentoArrecadadorParaPaginacao(
				 codigoBanco, codigoRemessa, descricaoIdentificacaoServico,
	    		 numeroSequencialArquivo, dataGeracaoInicio,
	    		 dataGeracaoFim, ultimaAlteracaoInicio, ultimaAlteracaoFim,
	    		 descricaoOcorrencia, indicadorAceitacao, 
	    		 ((Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa")),indicadorAbertoFechado);
		
        
        sessao.setAttribute("colecaoMovimentoArrecadador", colecaoMovimentoArrecadador);
        }
        
        return retorno;
    }
}