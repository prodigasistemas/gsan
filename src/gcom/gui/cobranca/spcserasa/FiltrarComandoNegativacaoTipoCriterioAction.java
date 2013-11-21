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
* Thiago Vieira
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
package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.bean.ComandoNegativacaoTipoCriterioHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Thiago Vieira
 */
public class FiltrarComandoNegativacaoTipoCriterioAction extends GcomAction {

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

//    	 localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("exibirResultadoConsultarCriterio");
		HttpSession sessao = httpServletRequest.getSession(false);

        FiltrarComandoNegativacaoTipoCriterioActionForm form = (FiltrarComandoNegativacaoTipoCriterioActionForm) actionForm; 
        
        ComandoNegativacaoTipoCriterioHelper comandoNegativacaoTipoCriterioHelper = new ComandoNegativacaoTipoCriterioHelper();
        
    	if (form.getTitulo() != null && !form.getTitulo().equals("")){
    		comandoNegativacaoTipoCriterioHelper.setTitulo(form.getTitulo());
        }
    	
    	if (form.getIdNegativador() != null && !form.getIdNegativador().equals("-1")){
    		comandoNegativacaoTipoCriterioHelper.setIdNegativador(new Integer(form.getIdNegativador()));
        }
        
    	if (form.getTipoPesquisaTitulo() != null && !form.getTipoPesquisaTitulo().equals("")){
    		comandoNegativacaoTipoCriterioHelper.setTipoPesquisaTitulo(form.getTipoPesquisaTitulo());
        }
    	
    	if (form.getComandoSimulado() != null && !form.getComandoSimulado().equals("")){
    		comandoNegativacaoTipoCriterioHelper.setComandoSimulado(Short.parseShort(form.getComandoSimulado()));
        }
    	
    	if (form.getCodigoCliente() != null && !form.getCodigoCliente().equals("")){
    		comandoNegativacaoTipoCriterioHelper.setCodigoCliente(new Integer(form.getCodigoCliente()));
        } 	
    	
    	if (form.getIdTipoRelacao() != null && !form.getIdTipoRelacao().equals("-1")){
    		comandoNegativacaoTipoCriterioHelper.setIdTipoRelacao(new Integer(form.getIdTipoRelacao()));
        }
    	
    	if (form.getIdGrupoCobranca() != null && !form.getIdGrupoCobranca().equals("")){
    		comandoNegativacaoTipoCriterioHelper.setIdGrupoCobranca(new Integer(form.getIdGrupoCobranca()));
        }
    	
    	if (form.getIdGerenciaRegional() != null && !form.getIdGerenciaRegional().equals("")){
    		comandoNegativacaoTipoCriterioHelper.setIdGerenciaRegional(new Integer(form.getIdGerenciaRegional()));
        }
    	
    	if (form.getIdUnidadeNegocio() != null && !form.getIdUnidadeNegocio().equals("")){
    		comandoNegativacaoTipoCriterioHelper.setIdUnidadeNegocio(new Integer(form.getIdUnidadeNegocio()));
        }
    	
    	if (form.getIdEloPolo() != null && !form.getIdEloPolo().equals("")){
    		comandoNegativacaoTipoCriterioHelper.setIdEloPolo(new Integer(form.getIdEloPolo()));
        }
    	
    	if (form.getCodigoLocalidadeInicial() != null && !form.getCodigoLocalidadeInicial().equals("")){
    		comandoNegativacaoTipoCriterioHelper.setCodigoLocalidadeInicial(new Integer(form.getCodigoLocalidadeInicial()));
        }
    	
    	if (form.getCodigoSetorComercialInicial() != null && !form.getCodigoSetorComercialInicial().equals("")){
    		comandoNegativacaoTipoCriterioHelper.setCodigoSetorComercialInicial(new Integer(form.getCodigoSetorComercialInicial()));
        }
    	
    	if (form.getCodigoLocalidadeFinal() != null && !form.getCodigoLocalidadeFinal().equals("")){
    		comandoNegativacaoTipoCriterioHelper.setCodigoLocalidadeFinal(new Integer(form.getCodigoLocalidadeFinal()));
        }
    	
    	if (form.getCodigoSetorComercialFinal() != null && !form.getCodigoSetorComercialFinal().equals("")){
    		comandoNegativacaoTipoCriterioHelper.setCodigoSetorComercialFinal(new Integer( form.getCodigoSetorComercialFinal()));
        }
    	
    	   	
    	if ((form.getGeracaoComandoDataInicial() != null && !form.getGeracaoComandoDataInicial().equals("")) &&  
    			(form.getGeracaoComandoDataFinal() != null && !form.getGeracaoComandoDataFinal().equals(""))){
    		
    		Date geracaoComandoDataInicial = Util.converteStringParaDate(form.getGeracaoComandoDataInicial());
        	Date geracaoComandoDataFinal = Util.converteStringParaDate(form.getGeracaoComandoDataFinal());		
    		//Se data inicio maior que data fim
    		if(Util.compararData(geracaoComandoDataInicial, geracaoComandoDataFinal) == 1){
    			String dataInicio = Util.formatarData(geracaoComandoDataInicial);
    			String dataFim = Util.formatarData(geracaoComandoDataFinal);
    			throw new ActionServletException(
    					"atencao.data_inicial_maior_data_final", dataInicio,dataFim);
    		}
    			
    		comandoNegativacaoTipoCriterioHelper.setGeracaoComandoDataInicial(Util.converteStringParaDate(form.getGeracaoComandoDataInicial()));
    		comandoNegativacaoTipoCriterioHelper.setGeracaoComandoDataFinal(Util.converteStringParaDate(form.getGeracaoComandoDataFinal()));
        }
    	
    
   
    	
    	if ((form.getExecucaoComandoDataInicial() != null && !form.getExecucaoComandoDataInicial().equals(""))
    			&& (form.getExecucaoComandoDataFinal() != null && !form.getExecucaoComandoDataFinal().equals(""))){
    		
    	 	
        	Date execucaoComandoDataInicial = Util.converteStringParaDate(form.getExecucaoComandoDataInicial());
        	Date execucaoComandoDataFinal = Util.converteStringParaDate(form.getExecucaoComandoDataFinal());		
    		//Se data inicio maior que data fim
    		if(Util.compararData(execucaoComandoDataInicial, execucaoComandoDataFinal) == 1){
    			String dataInicio = Util.formatarData(execucaoComandoDataInicial);
    			String dataFim = Util.formatarData(execucaoComandoDataFinal);
    			throw new ActionServletException(
    					"atencao.data_inicial_maior_data_final", dataInicio,dataFim);
    		}
    		
    		comandoNegativacaoTipoCriterioHelper.setExecucaoComandoDataInicial(Util.converteStringParaDate(form.getExecucaoComandoDataInicial()));
    		comandoNegativacaoTipoCriterioHelper.setExecucaoComandoDataFinal(Util.converteStringParaDate(form.getExecucaoComandoDataFinal()));
        }
    	
    	
    	
    	if ((form.getReferenciaDebitoDataInicial() != null && !form.getReferenciaDebitoDataInicial().equals(""))
    			&&(form.getReferenciaDebitoDataFinal() != null && !form.getReferenciaDebitoDataFinal().equals(""))){

    		Integer referenciaDebitoDataInicial = Util.formatarMesAnoComBarraParaAnoMes(form.getReferenciaDebitoDataInicial());
    		Integer referenciaDebitoDataFinal = Util.formatarMesAnoComBarraParaAnoMes(form.getReferenciaDebitoDataFinal());
    		
    		if(referenciaDebitoDataFinal < referenciaDebitoDataInicial){
    			throw new ActionServletException(
    					"atencao.referencia_final_menor_referencia_inicial");
    		}
    			
    		
    		
    		comandoNegativacaoTipoCriterioHelper.setReferenciaDebitoDataInicial(new Integer(form.getReferenciaDebitoDataInicial()));
    		comandoNegativacaoTipoCriterioHelper.setReferenciaDebitoDataFinal(new Integer(form.getReferenciaDebitoDataFinal()));
    		
        }
    	
      	
    	if ((form.getVencimentoDebitoDataInicial() != null && !form.getVencimentoDebitoDataInicial().equals(""))
    			&& (form.getVencimentoDebitoDataFinal() != null && !form.getVencimentoDebitoDataFinal().equals(""))  ){
    		
    		Date vencimentoDebitoDataInicial = Util.converteStringParaDate(form.getVencimentoDebitoDataInicial());
        	Date vencimentoDebitoDataFinal = Util.converteStringParaDate(form.getVencimentoDebitoDataFinal());		
    		//Se data inicio maior que data fim
    		if(Util.compararData(vencimentoDebitoDataInicial, vencimentoDebitoDataFinal) == 1){
    			String dataInicio = Util.formatarData(vencimentoDebitoDataInicial);
    			String dataFim = Util.formatarData(vencimentoDebitoDataFinal);
    			throw new ActionServletException(
    					"atencao.data_inicial_maior_data_final", dataInicio,dataFim);
    		}	
    		
    		
    		comandoNegativacaoTipoCriterioHelper.setVencimentoDebitoDataInicial(Util.converteStringParaDate(form.getVencimentoDebitoDataInicial()));
    		comandoNegativacaoTipoCriterioHelper.setVencimentoDebitoDataFinal(Util.converteStringParaDate(form.getVencimentoDebitoDataFinal()));
        }
    	
    	
    	
    	if (form.getValorDebitoInicial() != null && !form.getValorDebitoInicial().equals("")){
    		comandoNegativacaoTipoCriterioHelper.setValorDebitoInicial(new BigDecimal(form.getValorDebitoInicial()));
        }
    	
    	if (form.getValorDebitoFinal() != null && !form.getValorDebitoFinal().equals("")){
    		comandoNegativacaoTipoCriterioHelper.setValorDebitoFinal(new BigDecimal(form.getValorDebitoFinal()));
        }
    	
    	if (form.getNumeroContasInicial() != null && !form.getNumeroContasInicial().equals("")){
    		comandoNegativacaoTipoCriterioHelper.setNumeroContasInicial(new Integer(form.getNumeroContasInicial()));
        }
    	
    	if (form.getNumeroContasFinal() != null && !form.getNumeroContasFinal().equals("")){
    		comandoNegativacaoTipoCriterioHelper.setNumeroContasFinal(new Integer(form.getNumeroContasFinal()));
        }
    	
    	if (form.getCartaParcelamentoAtraso() != null && !form.getCartaParcelamentoAtraso().equals("")){
    		comandoNegativacaoTipoCriterioHelper.setCartaParcelamentoAtraso(Short.parseShort(form.getCartaParcelamentoAtraso()));
        }
    	
    	if (form.getSituacaoComando() != null && !form.getSituacaoComando().equals("")){
    		comandoNegativacaoTipoCriterioHelper.setSituacaoComando(new Integer(form.getSituacaoComando()));
        }
  
    	if(form.getIndicadorContaNomeCliente() != null){
    		comandoNegativacaoTipoCriterioHelper.setIndicadorContaNomeCliente(
    				new Short(form.getIndicadorContaNomeCliente()));
    	}
    		

    	//Fachada fachada = Fachada.getInstancia();
    	//Collection collComandoNegativacao = fachada.pesquisarComandoNegativacaoTipoCriterio(comandoNegativacaoTipoCriterioHelper);

    	/*Collection collComandoNegativacao = fachada.pesquisarComandoNegativacaoTipoCriterio(
    			idNegativador, 
		    	titulo, 
		    	tipoPesquisaTitulo, 
		    	comandoSimulado, 
		    	codigoCliente, 
		    	idTipoRelacao, 
		    	idGrupoCobranca, 
		    	idGerenciaRegional, 
		    	idUnidadeNegocio, 
		    	idEloPolo, 
		    	codigoLocalidadeInicial, 
		    	codigoSetorComercialInicial, 
		    	codigoLocalidadeFinal, 
		    	codigoSetorComercialFinal, 
		    	geracaoComandoDataInicial, 
		    	geracaoComandoDataFinal, 
		    	execucaoComandoDataInicial, 
		    	execucaoComandoDataFinal, 
		    	referenciaDebitoDataInicial, 
		    	referenciaDebitoDataFinal, 
		    	vencimentoDebitoDataInicial, 
		    	vencimentoDebitoDataFinal, 
		    	valorDebitoInicial, 
		    	valorDebitoFinal, 
		    	numeroContasInicial, 
		    	numeroContasFinal, 
		    	cartaParcelamentoAtraso, 
		    	situacaoComando);*/


//    	Fachada fachada = Fachada.getInstancia();
//    	Collection collNegativacaoCriterio = fachada.pesquisarComandoNegativacaoTipoCriterio(comandoNegativacaoTipoCriterioHelper);
    	
//    	if (collNegativacaoCriterio == null || collNegativacaoCriterio.isEmpty()) {
//			throw new ActionServletException(
//					"atencao.pesquisa.nenhumresultado");
//		} else {
			sessao.setAttribute("comandoNegativacaoTipoCriterioHelper", comandoNegativacaoTipoCriterioHelper);
//		}
    	

    	return retorno;
        
    }
}