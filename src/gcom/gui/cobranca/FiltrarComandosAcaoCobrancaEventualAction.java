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

import java.util.Calendar;
import java.util.GregorianCalendar;

import gcom.cobranca.FiltroCobrancaAcaoAtividadeComando;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Permite consultar comandos de ação de cobrança 
 * [UC0326] Filtrar Comandos de Ação de Cobrança - Eventual
 * @author Rafael Santos
 * @since 12/05/2006
 */
public class FiltrarComandosAcaoCobrancaEventualAction  extends GcomAction{
	
	
	/**
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

        ActionForward retorno = actionMapping.findForward("retornarComandosAcaoCobrancaEventual");
        
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
        
        FiltrarComandosAcaoCobrancaEventualActionForm filtrarComandosAcaoCobrancaEventualActionForm = (FiltrarComandosAcaoCobrancaEventualActionForm)actionForm; 

        if(sessao.getAttribute("filtroCobrancaAcaoAtividadeComando") == null){
        	
        
	        if(!filtrarComandosAcaoCobrancaEventualActionForm.getIndicadorCriterio().equals("Comando")){
	        	filtrarComandosAcaoCobrancaEventualActionForm.setCriterioCobranca("");
	        }
	        
			if(httpServletRequest.getParameter("grupoCobranca")== null){
				filtrarComandosAcaoCobrancaEventualActionForm.setGrupoCobranca(null);	
			}
			if(httpServletRequest.getParameter("gerenciaRegional")== null){
				filtrarComandosAcaoCobrancaEventualActionForm.setGerenciaRegional(null);
			}
			if(httpServletRequest.getParameter("unidadeNegocio") == null){
				filtrarComandosAcaoCobrancaEventualActionForm.setUnidadeNegocio(null);
			}
			if(httpServletRequest.getParameter("clienteRelacaoTipo") == null){
				filtrarComandosAcaoCobrancaEventualActionForm.setClienteRelacaoTipo(null);
			}
			
			//[FS0014 - Validar período de emissão];
			String dataInicial = filtrarComandosAcaoCobrancaEventualActionForm.getDataEmissaoInicio();
			String dataFinal = filtrarComandosAcaoCobrancaEventualActionForm.getDataEmissaoFim();
		
			if ((dataInicial.trim().length() == 10)
					&& (dataFinal.trim().length() == 10)) {
				
				Calendar calendarInicio = new GregorianCalendar();
				Calendar calendarFim = new GregorianCalendar();
	            
	            calendarInicio.setTime( Util.converteStringParaDate( dataInicial ) );
	            calendarFim.setTime( Util.converteStringParaDate( dataFinal ) );

				if (calendarFim.compareTo(calendarInicio) < 0) {
					throw new ActionServletException(
							"atencao.data_fim_menor_inicio");
				}
			}
	        
	        
	        FiltroCobrancaAcaoAtividadeComando filtroCobrancaAcaoAtividadeComando = 
	        	fachada.construirFiltroCobrancaAcaoAtividadeEventual(
	        			filtrarComandosAcaoCobrancaEventualActionForm.getGrupoCobranca(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getAcaoCobranca(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getAtividadeCobranca(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getPeriodoReferenciaContasInicial(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getPeriodoReferenciaContasFinal(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getPeriodoComandoInicial(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getPeriodoComandoFinal(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getPeriodoRealizacaoComandoInicial(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getPeriodoRealizacaoComandoFinal(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getPeriodoVencimentoContasInicial(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getPeriodoVencimentoContasFinal(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getIntervaloValorDocumentosInicial(),        			
	        			filtrarComandosAcaoCobrancaEventualActionForm.getIntervaloValorDocumentosFinal(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getIntervaloQuantidadeDocumentosInicial(),        			
	        			filtrarComandosAcaoCobrancaEventualActionForm.getIntervaloQuantidadeDocumentosFinal(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getIntervaloQuantidadeItensDocumentosInicial(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getIntervaloQuantidadeItensDocumentosFinal(),        			
	        			filtrarComandosAcaoCobrancaEventualActionForm.getSituacaoComando(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getIndicadorCriterio(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getGerenciaRegional(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getLocalidadeOrigemID(),        			
	        			filtrarComandosAcaoCobrancaEventualActionForm.getLocalidadeDestinoID(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getSetorComercialOrigemCD(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getSetorComercialDestinoCD(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getRotaInicial(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getRotaFinal(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getIdCliente(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getClienteRelacaoTipo(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getCriterioCobranca(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getUnidadeNegocio(), 
	        			filtrarComandosAcaoCobrancaEventualActionForm.getIdCobrancaAcaoAtividadeComando(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getDataEmissaoInicio(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getDataEmissaoFim(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getConsumoMedioInicial(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getConsumoMedioFinal(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getTipoConsumo(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getPeriodoInicialFiscalizacao(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getPeriodoFinalFiscalizacao(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getSituacaoFiscalizacao(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getNumeroQuadraInicial(),
	        			filtrarComandosAcaoCobrancaEventualActionForm.getNumeroQuadraFinal());
	       
	       
	        sessao.setAttribute("filtroCobrancaAcaoAtividadeComando",
	        		filtroCobrancaAcaoAtividadeComando);
	        
	        sessao.setAttribute("filtrarComandosAcaoCobrancaEventualActionForm",filtrarComandosAcaoCobrancaEventualActionForm);    
        }
        
        return retorno;
    }

}
