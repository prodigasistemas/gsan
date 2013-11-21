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
package gcom.gui.cobranca.spcserasa;

import java.util.Collection;
import java.util.Iterator;

import gcom.cobranca.NegativadorResultadoSimulacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.spcserasa.FiltroNegativadorResultadoSimulacao;
import gcom.util.filtro.ParametroSimples;

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
public class FiltrarNegativadorResultadoSimulacaoAction extends GcomAction {
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


        ActionForward retorno = actionMapping.findForward("gerarRelatorioNegativadorResultadoSimulacao");
        
        FiltrarNegativadorResultadoSimulacaoActionForm form = (FiltrarNegativadorResultadoSimulacaoActionForm) actionForm;
       
        HttpSession sessao = httpServletRequest.getSession(false);
        
    	Fachada fachada = Fachada.getInstancia();        
      
        Boolean peloMenosUmParametroInformado = false;
        
   //-------------------------------------------------------------------------
        String id = null;
		if (form.getIdComando() != null
				&& !form.getIdComando().equals("-1")) {			
			id=form.getIdComando();
		} else {
			throw new ActionServletException("atencao.required", null,
					"Comando");
		}
     
     

		//[FS0003] Verificar preenchimento dos campos
		if (!peloMenosUmParametroInformado){
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		
        //Exibe na Tela o nome do Cliente Negativador
		FiltroNegativadorResultadoSimulacao filtro = new FiltroNegativadorResultadoSimulacao();
		filtro.adicionarParametro(new ParametroSimples(FiltroNegativadorResultadoSimulacao.ID,id));        
		filtro.adicionarCaminhoParaCarregamentoEntidade("negativacaoComando");
        Collection coll = fachada.pesquisar(filtro, NegativadorResultadoSimulacao.class.getName());
        
        NegativadorResultadoSimulacao negativadorResultadoSimulacao = null;
        Iterator it = coll.iterator();
        while(it.hasNext()){
        	  negativadorResultadoSimulacao = (NegativadorResultadoSimulacao)it.next();        	 
        }
        
        if(negativadorResultadoSimulacao != null && negativadorResultadoSimulacao.getNegativacaoComando() != null){
        	if (negativadorResultadoSimulacao.getNegativacaoComando().getIndicadorSimulacao() == 2){
        		throw new ActionServletException(
				"atencao.comando_nao_corresponde_simulacao");
        	}
        	
        	if (negativadorResultadoSimulacao.getNegativacaoComando().getDataHoraRealizacao() == null){
        		throw new ActionServletException(
        				"atencao.simulacao_nao_realizada", "exibirFiltrarNegativadorResultadoSimulacaoAction.do?menu=sim", new Exception());
        	}
        	
        }
        
        
    	
		sessao.setAttribute("filtroNegativadorResultadoSimulacao",filtro);
	
	
       return retorno;
    }
   
    

}
 