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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroUnidadeRepavimentadoraCustoPavimentoCalcada;
import gcom.atendimentopublico.ordemservico.FiltroUnidadeRepavimentadoraCustoPavimentoRua;
import gcom.cadastro.imovel.FiltroPavimentoCalcada;
import gcom.cadastro.imovel.FiltroPavimentoRua;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeRepavimentadoraCustoPavimentoCalcada;
import gcom.cadastro.unidade.UnidadeRepavimentadoraCustoPavimentoRua;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1106] Inserir Custo de Pavimento por Repavimentadora
 * 
 * @author Hugo Leonardo
 *
 * @date 20/12/2010
 */
public class ExibirInserirCustoPavimentoPorRepavimentadoraAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirInserirCustoPavimentoPorRepavimentadoraAction");
		
		HttpSession sessao = httpServletRequest.getSession(false);		
		
		// Form
		ExibirInserirCustoPavimentoPorRepavimentadoraActionForm form = 
			(ExibirInserirCustoPavimentoPorRepavimentadoraActionForm) actionForm;
		
		UnidadeRepavimentadoraCustoPavimentoRua unidadeRepavimentadoraCustoPavimentoRua = null;
		UnidadeRepavimentadoraCustoPavimentoCalcada unidadeRepavimentadoraCustoPavimentoCalcada = null;
		
		ArrayList<UnidadeRepavimentadoraCustoPavimentoRua> colecaoUnidadeRepavimentadoraCustoPavimentoRua = new ArrayList();
		ArrayList<UnidadeRepavimentadoraCustoPavimentoCalcada> colecaoUnidadeRepavimentadoraCustoPavimentoCalcada = new ArrayList();
		
		if (httpServletRequest.getParameter("menu") != null
				&& httpServletRequest.getParameter("menu").equals("sim")) {
			
			if(sessao.getAttribute("colecaoUnidadeRepavimentadora") == null){
				this.pesquisarUnidadeRepavimentadora(sessao);
			}
			
			if(sessao.getAttribute("colecaoPavimentoRua") == null){
				this.pesquisarTipoPavimentoRua(sessao);
			}
			
			if(sessao.getAttribute("colecaoPavimentoCalcada") == null){
				this.pesquisarTipoPavimentoCalcada(sessao);
			}
		}
		
		if(sessao.getAttribute("colecaoUnidadeRepavimentadoraCustoPavimentoRua") != null){
			
			colecaoUnidadeRepavimentadoraCustoPavimentoRua = (ArrayList<UnidadeRepavimentadoraCustoPavimentoRua>) sessao.getAttribute("colecaoUnidadeRepavimentadoraCustoPavimentoRua");
		}
		
		if(sessao.getAttribute("colecaoUnidadeRepavimentadoraCustoPavimentoCalcada") != null){
			
			colecaoUnidadeRepavimentadoraCustoPavimentoCalcada = (ArrayList<UnidadeRepavimentadoraCustoPavimentoCalcada>) sessao.getAttribute("colecaoUnidadeRepavimentadoraCustoPavimentoCalcada");
		}
		
        if ( httpServletRequest.getParameter("acao") != null && 
        	httpServletRequest.getParameter("acao").equals("adicionar") ) {
        	
        	// Adicionar UnidadeRepavimentadoraCustoPavimentoRua na Colecao
        	if ( httpServletRequest.getParameter("pavimento") != null && 
                	httpServletRequest.getParameter("pavimento").equals("rua") ) {
        		
        		if(Util.verificarIdNaoVazio(form.getIdUnidadeRepavimentadora()) 
            			&& Util.verificarIdNaoVazio(form.getIdTipoPavimentoRua())
            			&& Util.verificarNaoVazio(form.getValorPavimentoRua())
            			&& Util.verificarNaoVazio(form.getDataVigenciaInicialPavimentoRua()) ){
            		
            		unidadeRepavimentadoraCustoPavimentoRua = this.setaUnidadeRepavimentadoraCustoPavimentoRua(form);
            		
            		if(unidadeRepavimentadoraCustoPavimentoRua != null){
            			
            			UnidadeRepavimentadoraCustoPavimentoRua unidRepPavRua = null;
            			
            			// [FS0005] Validar Data de Vigência do Custo do Pavimento de Rua
            			FiltroUnidadeRepavimentadoraCustoPavimentoRua filtroCustoPavimentoRua = new FiltroUnidadeRepavimentadoraCustoPavimentoRua();
            			
            			filtroCustoPavimentoRua.adicionarParametro(new ParametroSimples(
            					FiltroUnidadeRepavimentadoraCustoPavimentoRua.UNIDADE_REPAVIMENTADORA_ID, form.getIdUnidadeRepavimentadora()));
            			
            			filtroCustoPavimentoRua.adicionarParametro(new ParametroSimples(
            					FiltroUnidadeRepavimentadoraCustoPavimentoRua.PAVIMENTO_RUA_ID, form.getIdTipoPavimentoRua()));
            			
            			Collection<UnidadeRepavimentadoraCustoPavimentoRua> colecaoCustoPavimentoRua = Fachada.getInstancia().pesquisar(
            					filtroCustoPavimentoRua, UnidadeRepavimentadoraCustoPavimentoRua.class.getName());
            			
            			if(!Util.isVazioOrNulo(colecaoCustoPavimentoRua)){
            				
            				Iterator it = colecaoCustoPavimentoRua.iterator();
            				
            				while(it.hasNext()){
            					unidRepPavRua = (UnidadeRepavimentadoraCustoPavimentoRua) it.next();
            					
            					// [FS0005] Validar Data de Vigência do Custo do Pavimento de Rua
            					if((unidRepPavRua.getDataVigenciaFinal() == null && 
            						Util.compararData(unidRepPavRua.getDataVigenciaInicial(), unidadeRepavimentadoraCustoPavimentoRua.getDataVigenciaInicial()) >= 0)
    								|| (unidRepPavRua.getDataVigenciaFinal() != null && 
    									Util.compararData(unidRepPavRua.getDataVigenciaFinal(), unidadeRepavimentadoraCustoPavimentoRua.getDataVigenciaInicial()) >= 0)){
            						
            						throw new ActionServletException("atencao.vigencia.pavimento_rua_existente");
            					}
            				}
            			}
            			
            			Iterator iterator = colecaoUnidadeRepavimentadoraCustoPavimentoRua.iterator();
            			
            			// [FS0003] Verificar pavimento de rua já existente na lista 
            			while(iterator.hasNext()){
            				unidRepPavRua = (UnidadeRepavimentadoraCustoPavimentoRua) iterator.next();
            				
            				if(unidRepPavRua.getPavimentoRua().getId().equals(unidadeRepavimentadoraCustoPavimentoRua.getPavimentoRua().getId())){
            					throw new ActionServletException("atencao.tipo_pavimento_rua.existente");
            				}
            			}
            			
            			colecaoUnidadeRepavimentadoraCustoPavimentoRua.add(unidadeRepavimentadoraCustoPavimentoRua);
            			
            			// O sistema classifica a lista de UnidadeRepavimentadoraCustoPavimentoRua
                		Collections.sort((List) colecaoUnidadeRepavimentadoraCustoPavimentoRua,
                				new Comparator() {
                					public int compare(Object a, Object b) {
                						String codigo1 = ((UnidadeRepavimentadoraCustoPavimentoRua) a)
                							.getPavimentoRua().getDescricao();
                						String codigo2 = ((UnidadeRepavimentadoraCustoPavimentoRua) b)
                							.getPavimentoRua().getDescricao();
                						
                						if (codigo1 == null || codigo1.equals("")) {
                							return -1;
                						} else {
                							return codigo1.compareTo(codigo2);
                						}
                					}
                				});
            			
            			sessao.setAttribute("colecaoUnidadeRepavimentadoraCustoPavimentoRua", colecaoUnidadeRepavimentadoraCustoPavimentoRua);
            		}
            		
            		form.setIdTipoPavimentoRua("");
            		form.setValorPavimentoRua("");
            		form.setDataVigenciaInicialPavimentoRua("");
            		form.setDataVigenciaFinalPavimentoRua("");
            	}
        	}
        	
        	// Adicionar UnidadeRepavimentadoraCustoPavimentoCalcada na Colecao
        	if ( httpServletRequest.getParameter("pavimento") != null && 
                	httpServletRequest.getParameter("pavimento").equals("calcada") ) {
        		
        		if(Util.verificarIdNaoVazio(form.getIdUnidadeRepavimentadora()) 
            			&& Util.verificarIdNaoVazio(form.getIdTipoPavimentoCalcada())
            			&& Util.verificarNaoVazio(form.getValorPavimentoCalcada())
            			&& Util.verificarNaoVazio(form.getDataVigenciaInicialPavimentoCalcada()) ){
            		
            		unidadeRepavimentadoraCustoPavimentoCalcada = this.setaUnidadeRepavimentadoraCustoPavimentoCalcada(form);
            		
            		if(unidadeRepavimentadoraCustoPavimentoCalcada != null){
            			
            			UnidadeRepavimentadoraCustoPavimentoCalcada unidRepPavCalcada = null;
            			
            			// [FS0007] Validar Data de Vigência do Custo do Pavimento de Calçada
            			FiltroUnidadeRepavimentadoraCustoPavimentoCalcada filtroCustoPavimentoCalcada = new FiltroUnidadeRepavimentadoraCustoPavimentoCalcada();
            			
            			filtroCustoPavimentoCalcada.adicionarParametro(new ParametroSimples(
            					FiltroUnidadeRepavimentadoraCustoPavimentoCalcada.UNIDADE_REPAVIMENTADORA_ID, form.getIdUnidadeRepavimentadora()));
            			
            			filtroCustoPavimentoCalcada.adicionarParametro(new ParametroSimples(
            					FiltroUnidadeRepavimentadoraCustoPavimentoCalcada.PAVIMENTO_CALCADA_ID, form.getIdTipoPavimentoCalcada()));
            			
            			Collection<UnidadeRepavimentadoraCustoPavimentoCalcada> colecaoCustoPavimentoCalcada = Fachada.getInstancia().pesquisar(
            					filtroCustoPavimentoCalcada, UnidadeRepavimentadoraCustoPavimentoCalcada.class.getName());
            			
            			if(!Util.isVazioOrNulo(colecaoCustoPavimentoCalcada)){
            				
            				Iterator it = colecaoCustoPavimentoCalcada.iterator();
            				
            				while(it.hasNext()){
            					unidRepPavCalcada = (UnidadeRepavimentadoraCustoPavimentoCalcada) it.next();
            					
            					// [FS0007] Validar Data de Vigência do Custo do Pavimento de Calçada
            					if((unidRepPavCalcada.getDataVigenciaFinal() == null && 
            						Util.compararData(unidRepPavCalcada.getDataVigenciaInicial(), unidadeRepavimentadoraCustoPavimentoCalcada.getDataVigenciaInicial()) >= 0)
            						|| (unidRepPavCalcada.getDataVigenciaFinal() != null && 
    									Util.compararData(unidRepPavCalcada.getDataVigenciaFinal(), unidadeRepavimentadoraCustoPavimentoCalcada.getDataVigenciaInicial()) >= 0)){
            						
            						throw new ActionServletException("atencao.vigencia.pavimento_calcada_existente");
            					}
            				}
            			}
            			
            			Iterator iterator = colecaoUnidadeRepavimentadoraCustoPavimentoCalcada.iterator();
            			
            			// [FS0008] Verificar pavimento de calçada já existente na lista 
            			while(iterator.hasNext()){
            				unidRepPavCalcada = (UnidadeRepavimentadoraCustoPavimentoCalcada) iterator.next();
            				
            				if(unidRepPavCalcada.getPavimentoCalcada().getId().equals(unidadeRepavimentadoraCustoPavimentoCalcada.getPavimentoCalcada().getId())){
            					throw new ActionServletException("atencao.tipo_pavimento_calcada.existente");
            				}
            			}
            			
            			colecaoUnidadeRepavimentadoraCustoPavimentoCalcada.add(unidadeRepavimentadoraCustoPavimentoCalcada);
            			
            			// O sistema classifica a lista de UnidadeRepavimentadoraCustoPavimentoCalcada
                		Collections.sort((List) colecaoUnidadeRepavimentadoraCustoPavimentoCalcada,
                				new Comparator() {
                					public int compare(Object a, Object b) {
                						String codigo1 = ((UnidadeRepavimentadoraCustoPavimentoCalcada) a)
                							.getPavimentoCalcada().getDescricao();
                						String codigo2 = ((UnidadeRepavimentadoraCustoPavimentoCalcada) b)
                							.getPavimentoCalcada().getDescricao();
                						
                						if (codigo1 == null || codigo1.equals("")) {
                							return -1;
                						} else {
                							return codigo1.compareTo(codigo2);
                						}
                					}
                				});
            			
            			sessao.setAttribute("colecaoUnidadeRepavimentadoraCustoPavimentoCalcada", colecaoUnidadeRepavimentadoraCustoPavimentoCalcada);
            		}
            		
            		form.setIdTipoPavimentoCalcada("");
            		form.setValorPavimentoCalcada("");
            		form.setDataVigenciaInicialPavimentoCalcada("");
            		form.setDataVigenciaFinalPavimentoCalcada("");
            	}
        	}

        }
        
        // Desfazer
        if ( httpServletRequest.getParameter("acao") != null && 
        	httpServletRequest.getParameter("acao").equals("desfazer") ) {
        	
        	sessao.removeAttribute("colecaoUnidadeRepavimentadoraCustoPavimentoRua");
        	sessao.removeAttribute("colecaoUnidadeRepavimentadoraCustoPavimentoCalcada");
        }
		
        // Remover Itens da Coleção
        if ( httpServletRequest.getParameter("acao") != null && 
        	httpServletRequest.getParameter("acao").equals("remover") ) {
        	
        	Integer indice = new Integer(httpServletRequest.getParameter("id")).intValue();
        	
        	// Remover UnidadeRepavimentadoraCustoPavimentoRua da Colecao
        	if ( httpServletRequest.getParameter("pavimento") != null && 
                	httpServletRequest.getParameter("pavimento").equals("rua") ) {
        		
            	if(sessao.getAttribute("colecaoUnidadeRepavimentadoraCustoPavimentoRua") != null){
            		colecaoUnidadeRepavimentadoraCustoPavimentoRua = 
            			(ArrayList<UnidadeRepavimentadoraCustoPavimentoRua>) sessao.getAttribute("colecaoUnidadeRepavimentadoraCustoPavimentoRua");
            		
            		if (colecaoUnidadeRepavimentadoraCustoPavimentoRua != null && 
    	        			!colecaoUnidadeRepavimentadoraCustoPavimentoRua.isEmpty() && 
    	        			colecaoUnidadeRepavimentadoraCustoPavimentoRua.size() > 0) {
    	        		
    	        		if (colecaoUnidadeRepavimentadoraCustoPavimentoRua.size() >= indice) {
    	            		
    	        			colecaoUnidadeRepavimentadoraCustoPavimentoRua.remove(indice-1);
    	            	}
    	        		
    	        		if(colecaoUnidadeRepavimentadoraCustoPavimentoRua.isEmpty()){
    	        			
    	        			sessao.removeAttribute("colecaoUnidadeRepavimentadoraCustoPavimentoRua");
    	        		}else{
    	        			
    	        			// O sistema classifica a lista de UnidadeRepavimentadoraCustoPavimentoRua
    	            		Collections.sort((List) colecaoUnidadeRepavimentadoraCustoPavimentoRua,
    	            				new Comparator() {
    	            					public int compare(Object a, Object b) {
    	            						String codigo1 = ((UnidadeRepavimentadoraCustoPavimentoRua) a)
    	            							.getPavimentoRua().getDescricao();
    	            						String codigo2 = ((UnidadeRepavimentadoraCustoPavimentoRua) b)
    	            							.getPavimentoRua().getDescricao();
    	            						
    	            						if (codigo1 == null || codigo1.equals("")) {
    	            							return -1;
    	            						} else {
    	            							return codigo1.compareTo(codigo2);
    	            						}
    	            					}
    	            				});
    	        			
    	        			sessao.setAttribute("colecaoUnidadeRepavimentadoraCustoPavimentoRua", colecaoUnidadeRepavimentadoraCustoPavimentoRua);
    	        		}
    				}
            	}
        	}
        	
        	// Remover UnidadeRepavimentadoraCustoPavimentoCalcada na Colecao
        	if ( httpServletRequest.getParameter("pavimento") != null && 
                	httpServletRequest.getParameter("pavimento").equals("calcada") ) {
        		
        		if(sessao.getAttribute("colecaoUnidadeRepavimentadoraCustoPavimentoCalcada") != null){
            		colecaoUnidadeRepavimentadoraCustoPavimentoCalcada = 
            			(ArrayList<UnidadeRepavimentadoraCustoPavimentoCalcada>) sessao.getAttribute("colecaoUnidadeRepavimentadoraCustoPavimentoCalcada");
            		
            		if (colecaoUnidadeRepavimentadoraCustoPavimentoCalcada != null && 
    	        			!colecaoUnidadeRepavimentadoraCustoPavimentoCalcada.isEmpty() && 
    	        			colecaoUnidadeRepavimentadoraCustoPavimentoCalcada.size() > 0) {
    	        		
    	        		if (colecaoUnidadeRepavimentadoraCustoPavimentoCalcada.size() >= indice) {
    	            		
    	        			colecaoUnidadeRepavimentadoraCustoPavimentoCalcada.remove(indice-1);
    	            	}
    	        		
    	        		if(colecaoUnidadeRepavimentadoraCustoPavimentoCalcada.isEmpty()){
    	        			
    	        			sessao.removeAttribute("colecaoUnidadeRepavimentadoraCustoPavimentoCalcada");
    	        		}else{
    	        			
    	        			// O sistema classifica a lista de UnidadeRepavimentadoraCustoPavimentoCalcada
    	            		Collections.sort((List) colecaoUnidadeRepavimentadoraCustoPavimentoCalcada,
    	            				new Comparator() {
    	            					public int compare(Object a, Object b) {
    	            						String codigo1 = ((UnidadeRepavimentadoraCustoPavimentoCalcada) a)
    	            							.getPavimentoCalcada().getDescricao();
    	            						String codigo2 = ((UnidadeRepavimentadoraCustoPavimentoCalcada) b)
    	            							.getPavimentoCalcada().getDescricao();
    	            						
    	            						if (codigo1 == null || codigo1.equals("")) {
    	            							return -1;
    	            						} else {
    	            							return codigo1.compareTo(codigo2);
    	            						}
    	            					}
    	            				});
    	        			
    	        			sessao.setAttribute("colecaoUnidadeRepavimentadoraCustoPavimentoCalcada", colecaoUnidadeRepavimentadoraCustoPavimentoCalcada);
    	        		}
    				}
            	}
        	}
        }
        
        form.setIdTipoPavimentoRua("");
		form.setValorPavimentoRua("");
		form.setDataVigenciaInicialPavimentoRua("");
		form.setDataVigenciaFinalPavimentoRua("");
		
		form.setIdTipoPavimentoCalcada("");
		form.setValorPavimentoCalcada("");
		form.setDataVigenciaInicialPavimentoCalcada("");
		form.setDataVigenciaFinalPavimentoCalcada("");
		
		return retorno;
	}
	
	/**
	 * Pesquisar Unidade Repavimentadora 
	 *
	 * @author Hugo Leonardo
	 * @date 20/12/2010
	 */
	private void pesquisarUnidadeRepavimentadora(HttpSession sessao) {

		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.INDICADOR_USO, ConstantesSistema.SIM));
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.UNIDADE_TIPO_CODIGO, "R"));
		filtroUnidadeOrganizacional.setCampoOrderBy(FiltroUnidadeOrganizacional.DESCRICAO);

		Collection<UnidadeOrganizacional> colecaoUnidadeRepavimentadora = Fachada.getInstancia().pesquisar(
				filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());

		if(!Util.isVazioOrNulo(colecaoUnidadeRepavimentadora)){
			
			sessao.setAttribute("colecaoUnidadeRepavimentadora", colecaoUnidadeRepavimentadora);
		}else{
			
			throw new ActionServletException("atencao.naocadastrado", null, "Unidade Repavimentadora");
		}
	}
	
	/**
	 * Pesquisar Tipo do Pavimento de Rua  
	 *
	 * @author Hugo Leonardo
	 * @date 20/12/2010
	 */
	private void pesquisarTipoPavimentoRua(HttpSession sessao) {

		FiltroPavimentoRua filtroPavimentoRua = new FiltroPavimentoRua();
		filtroPavimentoRua.adicionarParametro(new ParametroSimples(
				FiltroPavimentoRua.INDICADOR_USO, ConstantesSistema.SIM));
		filtroPavimentoRua.setConsultaSemLimites(true);
		
		filtroPavimentoRua.setCampoOrderBy(FiltroPavimentoRua.DESCRICAO);
		
		Collection<PavimentoRua> colecaoPavimentoRua = Fachada.getInstancia().pesquisar(
				filtroPavimentoRua, PavimentoRua.class.getName());

		if(!Util.isVazioOrNulo(colecaoPavimentoRua)){
			
			sessao.setAttribute("colecaoPavimentoRua", colecaoPavimentoRua);
		}else{
			
			throw new ActionServletException("atencao.naocadastrado", null, "Pavimento Rua");
		}
	}
	
	/**
	 * Pesquisar Tipo do Pavimento de Calçada
	 *
	 * @author Hugo Leonardo
	 * @date 20/12/2010
	 */
	private void pesquisarTipoPavimentoCalcada(HttpSession sessao) {

		FiltroPavimentoCalcada filtroPavimentoCalcada = new FiltroPavimentoCalcada();
		filtroPavimentoCalcada.adicionarParametro(new ParametroSimples(
				FiltroPavimentoCalcada.INDICADOR_USO, ConstantesSistema.SIM));
		filtroPavimentoCalcada.setConsultaSemLimites(true);
		
		filtroPavimentoCalcada.setCampoOrderBy(FiltroPavimentoCalcada.DESCRICAO);
		
		Collection<PavimentoCalcada> colecaoPavimentoCalcada = Fachada.getInstancia().pesquisar(
				filtroPavimentoCalcada, PavimentoCalcada.class.getName());

		if(!Util.isVazioOrNulo(colecaoPavimentoCalcada)){
			
			sessao.setAttribute("colecaoPavimentoCalcada", colecaoPavimentoCalcada);
		}
	}
	
	/**
	 * Setar objeto UnidadeRepavimentadoraCustoPavimentoRua
	 *
	 * @author Hugo Leonardo
	 * @date 20/12/2010
	 */
	private UnidadeRepavimentadoraCustoPavimentoRua setaUnidadeRepavimentadoraCustoPavimentoRua(
			ExibirInserirCustoPavimentoPorRepavimentadoraActionForm form){
		
		UnidadeRepavimentadoraCustoPavimentoRua unidadeRepavimentadoraCustoPavimentoRua = null;
		
		UnidadeOrganizacional unidadeRepavimentadora = this.pesquisarUnidadeOrganizacional(form.getIdUnidadeRepavimentadora());
		PavimentoRua pavimentoRua = this.pesquisarPavimentoRua(form.getIdTipoPavimentoRua());
		
		// Valor do Pavimento
		String valorSemPontos = form.getValorPavimentoRua().replace(".", "");
		BigDecimal valorPavimentoRua =  new BigDecimal(valorSemPontos.replace(",", "."));
		
		// Vigência do custo do pavimento.
		// Validar data da vigência inicial
		Date dataVigenciaInicial = null;
		//Date dataVigenciaFinal = null;
		if (form.getDataVigenciaInicialPavimentoRua() != null && !form.getDataVigenciaInicialPavimentoRua().equals("")){
			if (!Util.validarDiaMesAno(form.getDataVigenciaInicialPavimentoRua())) {

				dataVigenciaInicial = Util.converteStringParaDate(form.getDataVigenciaInicialPavimentoRua());			
			}else{
				throw new ActionServletException("atencao.data_vigencia_inicial_invalida");
			}
		}
		
		unidadeRepavimentadoraCustoPavimentoRua = new UnidadeRepavimentadoraCustoPavimentoRua(
					unidadeRepavimentadora, pavimentoRua, valorPavimentoRua, dataVigenciaInicial, new Date());
		
		return unidadeRepavimentadoraCustoPavimentoRua;
	}
	
	/**
	 * Setar objeto UnidadeRepavimentadoraCustoPavimentoCalcada
	 *
	 * @author Hugo Leonardo
	 * @date 21/12/2010
	 */
	private UnidadeRepavimentadoraCustoPavimentoCalcada setaUnidadeRepavimentadoraCustoPavimentoCalcada(
			ExibirInserirCustoPavimentoPorRepavimentadoraActionForm form){
		
		UnidadeRepavimentadoraCustoPavimentoCalcada unidadeRepavimentadoraCustoPavimentoCalcada = null;
		
		UnidadeOrganizacional unidadeRepavimentadora = this.pesquisarUnidadeOrganizacional(form.getIdUnidadeRepavimentadora());
		PavimentoCalcada pavimentoCalcada = this.pesquisarPavimentoCalcada(form.getIdTipoPavimentoCalcada());
		
		// Valor do Pavimento
		String valorSemPontos = form.getValorPavimentoCalcada().replace(".", "");
		BigDecimal valorPavimentoCalcada =  new BigDecimal(valorSemPontos.replace(",", "."));
		
		// Vigência do custo do pavimento.
		// Validar data da vigência inicial
		Date dataVigenciaInicial = null;
		//Date dataVigenciaFinal = null;
		if (form.getDataVigenciaInicialPavimentoCalcada() != null && !form.getDataVigenciaInicialPavimentoCalcada().equals("")){
			if (!Util.validarDiaMesAno(form.getDataVigenciaInicialPavimentoCalcada())) {

				dataVigenciaInicial = Util.converteStringParaDate(form.getDataVigenciaInicialPavimentoCalcada());			
			}else{
				throw new ActionServletException("atencao.data_vigencia_inicial_invalida");
			}
		}
		
		unidadeRepavimentadoraCustoPavimentoCalcada = new UnidadeRepavimentadoraCustoPavimentoCalcada(
					unidadeRepavimentadora, pavimentoCalcada, valorPavimentoCalcada, dataVigenciaInicial, new Date());
		
		return unidadeRepavimentadoraCustoPavimentoCalcada;
	}
	
	/**
	 * Pesquisar id Unidade Organizacional 
	 *
	 * @author Hugo Leonardo
	 * @date 20/12/2010
	 */
	private UnidadeOrganizacional pesquisarUnidadeOrganizacional(String idUnidadeRepavimentadora) {
		
		UnidadeOrganizacional unidadeOrganizacional = null;
		
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID, idUnidadeRepavimentadora));

		Collection<UnidadeOrganizacional> colecaoUnidadeRepavimentadora = Fachada.getInstancia().pesquisar(
				filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());

		if(!Util.isVazioOrNulo(colecaoUnidadeRepavimentadora)){
			
			unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeRepavimentadora);
		}
		
		return unidadeOrganizacional;
	}
	
	/**
	 * Pesquisar id Pavimento de Rua  
	 *
	 * @author Hugo Leonardo
	 * @date 20/12/2010
	 */
	private PavimentoRua pesquisarPavimentoRua(String idPavimentoRua) {
		
		PavimentoRua pavimentoRua = null; 
		
		FiltroPavimentoRua filtroPavimentoRua = new FiltroPavimentoRua();
		filtroPavimentoRua.adicionarParametro(new ParametroSimples(
				FiltroPavimentoRua.ID, idPavimentoRua));
		
		Collection<PavimentoRua> colecaoPavimentoRua = Fachada.getInstancia().pesquisar(
				filtroPavimentoRua, PavimentoRua.class.getName());

		if(!Util.isVazioOrNulo(colecaoPavimentoRua)){
			
			pavimentoRua = (PavimentoRua) Util.retonarObjetoDeColecao(colecaoPavimentoRua);
		}
		
		return pavimentoRua;
	}
	
	/**
	 * Pesquisar id Pavimento de Calçada  
	 *
	 * @author Hugo Leonardo
	 * @date 21/12/2010
	 */
	private PavimentoCalcada pesquisarPavimentoCalcada(String idPavimentoCalcada) {
		
		PavimentoCalcada pavimentoCalcada = null; 
		
		FiltroPavimentoCalcada filtroPavimentoCalcada = new FiltroPavimentoCalcada();
		filtroPavimentoCalcada.adicionarParametro(new ParametroSimples(
				FiltroPavimentoCalcada.ID, idPavimentoCalcada));
		
		Collection<PavimentoCalcada> colecaoPavimentoCalcada = Fachada.getInstancia().pesquisar(
				filtroPavimentoCalcada, PavimentoCalcada.class.getName());

		if(!Util.isVazioOrNulo(colecaoPavimentoCalcada)){
			
			pavimentoCalcada = (PavimentoCalcada) Util.retonarObjetoDeColecao(colecaoPavimentoCalcada);
		}
		
		return pavimentoCalcada;
	}
	
}