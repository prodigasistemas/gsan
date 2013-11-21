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

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action responsável pela exibição da página de executar atividade de ação de cobrança
 * 
 * @author  pedro alexandre
 * @created 31 de Janeiro de 2006
 */
public class ExibirExecutarAtividadeAcaoCobrancaAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {
    	
    	//cria a variável de retorno e seta o mapeamento para a tela de executar atividade de ação de cobrança 
        ActionForward retorno = actionMapping.findForward("exibirExecutarAtividadeAcaoCobrancaAction");

        //cria uma instância da fachada
        Fachada fachada = Fachada.getInstancia();
                
        //pesquisa os parâmetros do sistema
		SistemaParametro sistemaParametros = fachada.pesquisarParametrosDoSistema();
		
		//recupera o Ano/Mês de referência do ciclo de cobrança
		Integer anoMesCicloCobranca = sistemaParametros.getAnoMesFaturamento();
        
		//manda o Ano/Mês de referência do ciclo de cobrança no request
		httpServletRequest.setAttribute("anoMesCicloCobranca",anoMesCicloCobranca);
		
        /*Pesquisar as atividades de cobrança do cronograma que foram previamente comandas
        =============================================================================== */
        //realizando a pesquisa das atividades de cobrança de cronograma
        Collection colecaoAtividadesCobrancaCronograma = fachada.pesquisarCobrancaAcaoAtividadeCronograma();	
        
        
        /*Pesquisar as atividades de cobrança eventuais que foram previamente comandas
        =========================================================================== */
        //realizando a pesquisa das atividades de cobrança eventuais	
        Collection colecaoAtividadesCobrancaEventuais = fachada.pesquisarCobrancaAcaoAtividadeComando();
        
        
        //[FS0002] - Verificar a existência de atividade do cronograma comandada
        if (colecaoAtividadesCobrancaCronograma == null || colecaoAtividadesCobrancaCronograma.isEmpty()){
        	//[FS0003] - Verificar existência de atividade eventual comandada
        	if (colecaoAtividadesCobrancaEventuais == null || colecaoAtividadesCobrancaEventuais.isEmpty()){
        		throw new ActionServletException("atencao.naocadastrado", null, "Atividade(s) de ação de cobrança");
        	}
        }

        //manda a coleção de atividades de cobrança de cronograma no request para a página
        httpServletRequest.setAttribute("colecaoAtividadesCobrancaCronograma",colecaoAtividadesCobrancaCronograma);
        
        //manda a coleção de atividades de cobrança eventuais no request para a página
        httpServletRequest.setAttribute("colecaoAtividadesCobrancaEventuais",colecaoAtividadesCobrancaEventuais);
        
        //retorna o mapeamento contido na variável retorno
        return retorno;
    }
}


