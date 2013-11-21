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
* Yara Taciane de Souza
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

import gcom.atendimentopublico.ordemservico.bean.OSPavimentoHelper;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Realiza o filtro do Negativador Exclusao Motivo de acordo com os parâmetros informados
 * 
 * @author Yara Taciane de Souza
 * @created 03/01/2008
 */
public class FiltrarOrdemProcessoRepavimentacaoAction extends GcomAction {
	/**
	 * Este caso de uso permite o filtro de um Negativador Exclusao Motivo
	 * 
	 * [UC0670] Filtrar Motivo Exclusao Negativador
	 * 
	 * 
	 * @author Yara Taciane de Souza
	 * @date 03/01/2007
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {


        ActionForward retorno = actionMapping.findForward("retornarFiltroOrdemProcessoRepavimentacao");
        
        FiltrarOrdemProcessoRepavimentacaoActionForm form = (FiltrarOrdemProcessoRepavimentacaoActionForm) actionForm;
       
        HttpSession sessao = httpServletRequest.getSession(false);
        

        String idUnidadeResponsavel = null;
		if ( form.getIdUnidadeResponsavel() != null && !form.getIdUnidadeResponsavel().equals("-1") ) {			
			
			idUnidadeResponsavel= form.getIdUnidadeResponsavel();
		} else {
			throw new ActionServletException("atencao.required", null,
					"Unidade Responsavel");
		}
		Integer numeroOS = null;
		if(form.getNumeroOS() != null && !form.getNumeroOS().equals("") ){				
			numeroOS = new Integer(form.getNumeroOS());						
		}
		
		String situacaoRetorno = "0";
		if (form.getSituacaoRetorno() != null && 
			!form.getSituacaoRetorno().equals("") && 
			!form.getSituacaoRetorno().equals("-1") ) {			
			
			situacaoRetorno = form.getSituacaoRetorno();
		} else if(numeroOS == null){
				throw new ActionServletException("atencao.required", null,"Situação Retorno");
		}
		
		String periodoEncerramentoOsInicial = null;
		String periodoEncerramentoOsFinal = null;
		
		String periodoRetornoServicoInicial = null;
		String periodoRetornoServicoFinal = null;
		
		String periodoRejeicaoInicial = null;
		String periodoRejeicaoFinal = null;
		
		if(!form.getPeriodoEncerramentoOsInicial().equals("") && !form.getPeriodoEncerramentoOsFinal().equals("") ){				
				periodoEncerramentoOsInicial= form.getPeriodoEncerramentoOsInicial();						
				periodoEncerramentoOsFinal= form.getPeriodoEncerramentoOsFinal();	
				
		}else if(!form.getPeriodoRetornoServicoInicial().equals("") && !form.getPeriodoRetornoServicoFinal().equals("")){					
				periodoRetornoServicoInicial= form.getPeriodoRetornoServicoInicial();		
				periodoRetornoServicoFinal= form.getPeriodoRetornoServicoFinal();		
		
		}else if(!form.getPeriodoRejeicaoInicial().equals("") && !form.getPeriodoRejeicaoFinal().equals("")){
			periodoRejeicaoInicial = form.getPeriodoRejeicaoInicial();
			periodoRejeicaoFinal = form.getPeriodoRejeicaoFinal();
		}
		
		
		Date pRetornoServicoInicial = null;
		Date pRetornoServicoFinal = null; 
	
		if(periodoEncerramentoOsInicial == null && periodoEncerramentoOsFinal == null){
			
			if(periodoRetornoServicoInicial != null && periodoRetornoServicoFinal != null
					&& periodoRejeicaoInicial == null && periodoRejeicaoFinal == null){
	        	
	        	String inicio = Util.formatarData(periodoRetornoServicoInicial);
			    String fim = Util.formatarData(periodoRetornoServicoFinal);
			    
			    pRetornoServicoInicial = Util.converteStringParaDate(periodoRetornoServicoInicial);
			    pRetornoServicoFinal = Util.converteStringParaDate(periodoRetornoServicoFinal);
			    
			    //Se data inicio maior que data fim
	    		if(Util.compararData(pRetornoServicoInicial, pRetornoServicoFinal) == 1){
	    			 
	    			throw new ActionServletException(
	    					"atencao.data_inicial_maior_data_final", new Exception(),inicio,fim);
	    		}	
	    		
	    				
				Date dataAtualSemHora = Util.formatarDataSemHora(new Date());
				
				if(Util.compararData(pRetornoServicoInicial, dataAtualSemHora) == 1){
					String dataAtual = Util.formatarData(new Date());
					throw new ActionServletException(
							"atencao.data.superior.data.corrente", null,
							dataAtual);					
				}	
				
				if(Util.compararData(pRetornoServicoFinal, dataAtualSemHora) == 1){
					String dataAtual = Util.formatarData(new Date());
					throw new ActionServletException(
							"atencao.data.superior.data.corrente", null,
							dataAtual);					
				}	
			}
		}
	
		
		Date pEncerramentoOsInicial = null;
		Date pEncerramentoOsFinal = null; 
	
		if(periodoRetornoServicoInicial == null && periodoRetornoServicoFinal == null
				&& periodoRejeicaoInicial == null && periodoRejeicaoFinal == null ){
			
			if(periodoEncerramentoOsInicial != null && periodoEncerramentoOsFinal != null){
	        	
	        	String inicio = Util.formatarData(periodoEncerramentoOsInicial);
			    String fim = Util.formatarData(periodoEncerramentoOsFinal);
			    
			    pEncerramentoOsInicial = Util.converteStringParaDate(periodoEncerramentoOsInicial);
			    pEncerramentoOsFinal = Util.converteStringParaDate(periodoEncerramentoOsFinal);
			    
			    //Se data inicio maior que data fim
	    		if(Util.compararData(pEncerramentoOsInicial, pEncerramentoOsFinal) == 1){
	    			 
	    			throw new ActionServletException(
	    					"atencao.data_inicial_maior_data_final", new Exception(),inicio,fim);
	    		}		
	    		
				
				Date dataAtualSemHora = Util.formatarDataSemHora(new Date());
				
				if(Util.compararData(pEncerramentoOsInicial, dataAtualSemHora) == 1){
					String dataAtual = Util.formatarData(new Date());
					throw new ActionServletException(
							"atencao.data.superior.data.corrente", null,
							dataAtual);					
				}	
				
				if(Util.compararData(pEncerramentoOsFinal, dataAtualSemHora) == 1){
					String dataAtual = Util.formatarData(new Date());
					throw new ActionServletException(
							"atencao.data.superior.data.corrente", null,
							dataAtual);					
				}
	    		
			}else{
				pEncerramentoOsInicial = Util.formatarDataSemHora( new Date());
				pEncerramentoOsFinal =  Util.formatarDataSemHora( new Date()); 
			}
			
		}
		
		Date pRejeicaoInicial = null;
		Date pRejeicaoFinal = null; 
	
		if(periodoRetornoServicoInicial == null && periodoRetornoServicoFinal == null
				&& periodoEncerramentoOsInicial == null && periodoEncerramentoOsFinal == null){
			
			if(periodoRejeicaoInicial != null && periodoRejeicaoFinal != null){
	        	
	        	String inicio = Util.formatarData(periodoRejeicaoInicial);
			    String fim = Util.formatarData(periodoRejeicaoFinal);
			    
			    pRejeicaoInicial = Util.converteStringParaDate(periodoRejeicaoInicial);
			    pRejeicaoFinal = Util.converteStringParaDate(periodoRejeicaoFinal);
			    
			    //Se data inicio maior que data fim
	    		if(Util.compararData(pRejeicaoInicial, pRejeicaoFinal) == 1){
	    			 
	    			throw new ActionServletException(
	    					"atencao.data_inicial_maior_data_final", new Exception(),inicio,fim);
	    		}		
	    		
				Date dataAtualSemHora = Util.formatarDataSemHora(new Date());
				
				if(Util.compararData(pRejeicaoInicial, dataAtualSemHora) == 1){
					String dataAtual = Util.formatarData(new Date());
					throw new ActionServletException(
							"atencao.data.superior.data.corrente", null,
							dataAtual);					
				}	
				
				if(Util.compararData(pRejeicaoFinal, dataAtualSemHora) == 1){
					String dataAtual = Util.formatarData(new Date());
					throw new ActionServletException(
							"atencao.data.superior.data.corrente", null,
							dataAtual);					
				}
	    		
			}else{
				pRejeicaoInicial = Util.formatarDataSemHora( new Date());
				pRejeicaoFinal =  Util.formatarDataSemHora( new Date()); 
			}
			
		}
		
		Integer idMunicipio = null;
		if(form.getIdMunicipio() != null && !form.getIdMunicipio().equals("") ){				
			idMunicipio = new Integer(form.getIdMunicipio());						
		}

		Integer idBairro = null;
		if(form.getIdBairro() != null && !form.getIdBairro().equals("") ){
			idBairro = new Integer(form.getIdBairro());						
		
		}else if(form.getCodigoBairro() != null && !form.getCodigoBairro().equals("")){
			
			FiltroBairro filtroBairro = new FiltroBairro();
			filtroBairro.adicionarParametro(
				new ParametroSimples(
					FiltroBairro.CODIGO, 
					form.getCodigoBairro()));
			
			filtroBairro.adicionarParametro(
				new ParametroSimples(
					FiltroBairro.MUNICIPIO_ID, 
					form.getIdMunicipio()));
			
			
			Collection<Bairro> colecaoBairro = 
				this.getFachada().pesquisar(filtroBairro, Bairro.class.getName());
			
			if (colecaoBairro != null && !colecaoBairro.isEmpty()) {
				
				Bairro bairro = (Bairro) Util.retonarObjetoDeColecao(colecaoBairro);
				
				idBairro = bairro.getId();
			}
			
		}

		Integer idLogradouro = null;
		if(form.getIdLogradouro() != null && !form.getIdLogradouro().equals("") ){				
			idLogradouro = new Integer(form.getIdLogradouro());						
		}


		OSPavimentoHelper osPavimentoHelper = new OSPavimentoHelper();
		
		osPavimentoHelper.setIdUnidadeResponsavel(new Integer(idUnidadeResponsavel));
		 
		if(numeroOS != null){
			osPavimentoHelper.setNumeroOS(numeroOS);
			situacaoRetorno = "3";
		}else{
			osPavimentoHelper.setPeriodoEncerramentoOsInicial(periodoEncerramentoOsInicial);
			osPavimentoHelper.setPeriodoEncerramentoOsFinal(periodoEncerramentoOsFinal);	
			
			osPavimentoHelper.setPeriodoRetornoServicoInicial(periodoRetornoServicoInicial);
			osPavimentoHelper.setPeriodoRetornoServicoFinal(periodoRetornoServicoFinal);	
			
			osPavimentoHelper.setPeriodoRejeicaoInicial(periodoRejeicaoInicial);
			osPavimentoHelper.setPeriodoRejeicaoFinal(periodoRejeicaoFinal);
			
			osPavimentoHelper.setIdMunicipio(idMunicipio);
			osPavimentoHelper.setIdBairro(idBairro);
			osPavimentoHelper.setIdLogradouro(idLogradouro);

			if (situacaoRetorno != null && situacaoRetorno.equals("TODAS") ) {
				situacaoRetorno = "3";
			}
		}
		
		osPavimentoHelper.setSituacaoRetorno(new Integer(situacaoRetorno));
		
		sessao.setAttribute("osPavimentoHelper",osPavimentoHelper);
		
    
       return retorno;
    }
   
    

}
 