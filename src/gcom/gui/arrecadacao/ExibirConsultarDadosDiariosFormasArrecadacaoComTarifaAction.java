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

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.arrecadacao.FiltroConsultarDadosDiariosArrecadacao;
import gcom.arrecadacao.FiltroConsultarDadosDiariosArrecadacao.GROUP_BY;
import gcom.arrecadacao.bean.FiltrarDadosDiariosArrecadacaoHelper;
import gcom.batch.FiltroFuncionalidadeIniciada;
import gcom.batch.FuncionalidadeIniciada;
import gcom.batch.FuncionalidadeSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.util.Util;
import gcom.util.filtro.ConectorOr;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Fernanda Paiva
 * @created 25 de Maio de 2006
**/
public class ExibirConsultarDadosDiariosFormasArrecadacaoComTarifaAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirConsultarDadosDiariosFormasArrecadacaoComTarifa");
		
        /** filtro para verificar se a funcionalidade de gerar dados diários de arrecadação esta executando */
        FiltroFuncionalidadeIniciada filtroFuncionalidadeIniciada = new FiltroFuncionalidadeIniciada();
        filtroFuncionalidadeIniciada.adicionarParametro(new ParametroSimples(FiltroFuncionalidadeIniciada.FUNCIONALIDADE_ID,Funcionalidade.GERAR_DADOS_DIARIOS_ARRECADACAO));
        filtroFuncionalidadeIniciada.adicionarParametro(new ParametroSimples(FiltroFuncionalidadeIniciada.FUNCIONALIDADE_SITUACAO,FuncionalidadeSituacao.EM_ESPERA, ConectorOr.CONECTOR_OR, 2));
        filtroFuncionalidadeIniciada.adicionarParametro(new ParametroSimples(FiltroFuncionalidadeIniciada.FUNCIONALIDADE_SITUACAO,FuncionalidadeSituacao.EM_PROCESSAMENTO));
        
        Collection colecaoFuncionalidadeEmProcessamento = Fachada.getInstancia().pesquisar(filtroFuncionalidadeIniciada,FuncionalidadeIniciada.class.getName());
        
        /*
         * Caso a funcionalidade esteja emprocessamento ou em espera
         * envia uma mensagem ao usuário negando o acesso a consulta.  
         */
        if(colecaoFuncionalidadeEmProcessamento != null && !colecaoFuncionalidadeEmProcessamento.isEmpty()){
        	throw new ActionServletException("atencao.funcionalidade.processando");
        }
		
		String referencia = httpServletRequest.getParameter("referencia");
		
		String idArrecadadorPopup = httpServletRequest.getParameter("idArrecadadorPopup");
		httpServletRequest.setAttribute("idArrecadadorPopup", idArrecadadorPopup);
		
		Fachada fachada = Fachada.getInstancia();
		
		// Mudar isso quando implementar a parte de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltroConsultarDadosDiariosArrecadacao filtro = (FiltroConsultarDadosDiariosArrecadacao)
			sessao.getAttribute("filtroConsultarDadosDiariosArrecadacao");
		Integer periodoArrecadacaoInicial = (Integer) 
			sessao.getAttribute("periodoArrecadacaoInicial");
		Integer periodoArrecadacaoFinal = (Integer) 
			sessao.getAttribute("periodoArrecadacaoFinal");
		
		if (filtro != null){
			
			filtro = filtro.clone();
			
			filtro.setAgrupamento(GROUP_BY.DATA);
			
			filtro.setAnoMesArrecadacao(referencia);
			if (idArrecadadorPopup != null && !idArrecadadorPopup.equals("") 
				&& !idArrecadadorPopup.equals("-1")){
				filtro.setIdArrecadador(idArrecadadorPopup);
				
				//pesquisar na base o arrecadador
				FiltroArrecadador filtroArrecadador = new FiltroArrecadador();
				filtroArrecadador.adicionarParametro(new ParametroSimples(FiltroArrecadador.ID,
						idArrecadadorPopup));
				filtroArrecadador.adicionarCaminhoParaCarregamentoEntidade("cliente");
				
				Collection colecaoArrecadador = fachada.pesquisar(filtroArrecadador,
						Arrecadador.class.getName());
				
				Arrecadador arrecadador = (Arrecadador)Util.retonarObjetoDeColecao(colecaoArrecadador);
				httpServletRequest.setAttribute("nomeAgente", arrecadador.getCliente().getNome());
				
			}
			
			Map<Integer, Collection<FiltrarDadosDiariosArrecadacaoHelper>> 
			 mapDadosDiariosAnoMes = fachada.filtrarDadosDiariosFormasArrecadacaoComTarifa(
				periodoArrecadacaoInicial,
				periodoArrecadacaoFinal,
				filtro);
						
        	BigDecimal valorTotal = new BigDecimal(0.0);
        	
        	Collection<FiltrarDadosDiariosArrecadacaoHelper> colecaoDadosDiarios = 
        		mapDadosDiariosAnoMes.get(new Integer(referencia));
        	
        	for (FiltrarDadosDiariosArrecadacaoHelper helper : colecaoDadosDiarios){
        		valorTotal = valorTotal.add(helper.getValorArrecadacaoLiquida());
        	}
        	
	        sessao.setAttribute("colecaoDadosDiarios", colecaoDadosDiarios);
	        
			sessao.setAttribute("valorTotal", valorTotal);
			
			Date dataMesInformado = fachada.pesquisarDataProcessamentoMes(new Integer(referencia));
	    	Date dataAtual = fachada.pesquisarDataProcessamentoMes(this.getSistemaParametro().getAnoMesArrecadacao());

	    		
	    	if(dataMesInformado!=null){ 			
	    		httpServletRequest
					.setAttribute("dadosMesInformado", 
						Util.formatarDataComHora(dataMesInformado));
	    	}
	    	if(dataAtual!=null){   			
	    		httpServletRequest
					.setAttribute("dadosAtual", 
						Util.formatarDataComHora(dataAtual));
	    	}
						
		}
		
		sessao.setAttribute("referencia",referencia);
		return retorno;
	}
	
}