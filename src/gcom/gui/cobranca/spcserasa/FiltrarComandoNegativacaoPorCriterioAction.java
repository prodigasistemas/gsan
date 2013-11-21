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

import gcom.cobranca.bean.ComandoNegativacaoHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

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
public class FiltrarComandoNegativacaoPorCriterioAction extends GcomAction {

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
		ActionForward retorno = actionMapping.findForward("exibirManterComandoNegativacao");
		HttpSession sessao = httpServletRequest.getSession(false);

        FiltrarComandoNegativacaoPorCriterioActionForm form = (FiltrarComandoNegativacaoPorCriterioActionForm) actionForm; 

        String tituloComando = "";
    	Short tipoPesquisaTituloComando = null;
    	Short indicadorComandoSimulado = null;
    	Date geracaoComandoInicio = null;
    	Date geracaoComandoFim = null;
    	Integer idUsuarioResponsavel = null;
    	String checkAtualizar = "";
    	Integer idNegativador = null;
    	
        if (!form.getTituloComando().equals("") && form.getTituloComando() != null){
        	tituloComando = form.getTituloComando();
        }
        if (!form.getTipoBuscaTituloComando().equals("") && form.getTipoBuscaTituloComando() != null){
        	tipoPesquisaTituloComando = Short.parseShort(form.getTipoBuscaTituloComando());
        }
        if (!form.getComandoSimulado().equals("") && form.getComandoSimulado() != null){
        	indicadorComandoSimulado = Short.parseShort(form.getComandoSimulado());
        }
      
        Date dataGeracaoComandoInicial = null;
        Date dataGeracaoComandoFinal = null;      
        if((!form.getDataGeracaoComandoInicial().equals("") && form.getDataGeracaoComandoInicial() != null) &&  (!form.getDataGeracaoComandoFinal().equals("") && form.getDataGeracaoComandoFinal() != null)){
        	dataGeracaoComandoInicial = Util.converteStringParaDate(form.getDataGeracaoComandoInicial()); 
        	dataGeracaoComandoFinal =  Util.converteStringParaDate(form.getDataGeracaoComandoFinal()); 
        	
        	String dataInicio = Util.formatarData(dataGeracaoComandoInicial);
        	String dataFim = Util.formatarData(dataGeracaoComandoFinal);
        	
			//Se data inicio maior que data fim
    		if(Util.compararData(dataGeracaoComandoInicial, dataGeracaoComandoFinal) == 1){    			 
    			throw new ActionServletException(
    					"atencao.data_inicial_maior_data_final", dataInicio,dataFim);
    		}
    		
    		geracaoComandoInicio = Util.converteStringParaDate(form.getDataGeracaoComandoInicial());
    		geracaoComandoFim = Util.converteStringParaDate(form.getDataGeracaoComandoFinal());
        } 
        
            
        
        if (!form.getUsuarioResponsavelId().equals("") && form.getUsuarioResponsavelId() != null){
        	idUsuarioResponsavel = new Integer(form.getUsuarioResponsavelId());
        }
        if (!form.getCheckAtualizar().equals("") && form.getCheckAtualizar() != null){
        	checkAtualizar = form.getCheckAtualizar();
        }
        if (!form.getIdNegativador().equals("") && form.getIdNegativador() != null){
        	idNegativador = new Integer(form.getIdNegativador());
        }

        ComandoNegativacaoHelper comandoNegativacaoHelper = new ComandoNegativacaoHelper();
        comandoNegativacaoHelper.setTituloComando(tituloComando);
        comandoNegativacaoHelper.setTipoPesquisaTituloComando(tipoPesquisaTituloComando);
        comandoNegativacaoHelper.setIndicadorComandoSimulado(indicadorComandoSimulado);
        comandoNegativacaoHelper.setGeracaoComandoInicio(geracaoComandoInicio);
        comandoNegativacaoHelper.setGeracaoComandoFim(geracaoComandoFim);
    	comandoNegativacaoHelper.setIdUsuarioResponsavel(idUsuarioResponsavel);
    	comandoNegativacaoHelper.setIdNegativador(idNegativador);
    	
    	sessao.setAttribute("checkAtualizar",checkAtualizar);
    	sessao.setAttribute("comandoNegativacaoHelper",comandoNegativacaoHelper);
    	
		return retorno;
        
    }
}