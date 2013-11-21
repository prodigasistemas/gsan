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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;
import gcom.gui.integracao.GisHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Esta classe tem por finalidade gerar as abas que serão responsáveis pelo processo de inserção de um
 * registro de atendimento
 *
 * @author Raphael Rossiter
 * @date 24/07/2006
 */
public class ExibirInserirRegistroAtendimentoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("inserirRegistroAtendimento");
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        Boolean obj = (Boolean)sessao.getAttribute("origemGIS");
        boolean origemGIS = false;
        if(obj != null)
        	origemGIS = obj.booleanValue();
        	
        /*
         * verifica se a chamada é originada a partir do GIS. Caso sim,
         * requisita o objeto da chamada na sessão
         */
        GisHelper gisHelper = null;
        if(origemGIS){
        	gisHelper = (GisHelper) sessao.getAttribute("gisHelper");
        }
        
        
        //Removendo todos os objetos da sessão 
        sessao.removeAttribute("statusWizard");
        sessao.removeAttribute("InserirRegistroAtendimentoActionForm");
        sessao.removeAttribute("colecaoMeioSolicitacao");
        sessao.removeAttribute("colecaoSolicitacaoTipo");
        sessao.removeAttribute("colecaoSolicitacaoTipoEspecificacao");

        
        //Aba 02
        sessao.removeAttribute("colecaoDivisaoEsgoto");
        sessao.removeAttribute("colecaoLocalOcorrencia");
        sessao.removeAttribute("colecaoPavimentoRua");
        sessao.removeAttribute("colecaoPavimentoCalcada");
        sessao.removeAttribute("colecaoBairroArea");
        
        sessao.removeAttribute("solicitacaoTipoRelativoFaltaAgua");
        sessao.removeAttribute("solicitacaoTipoRelativoAreaEsgoto");
        
        sessao.removeAttribute("colecaoEnderecos");
        sessao.removeAttribute("habilitarAlteracaoEndereco");
        
        sessao.removeAttribute("desabilitarDivisaoEsgoto");
        sessao.removeAttribute("desabilitarPavimentoRua");
        sessao.removeAttribute("desabilitarPavimentoCalcada");
        sessao.removeAttribute("desabilitarDescricaoLocalOcorrencia");
        
        //Informar Solicitante
        sessao.removeAttribute("enderecoOcorrenciaRA");
        sessao.removeAttribute("colecaoEnderecosSolicitante");
        sessao.removeAttribute("colecaoFonesSolicitante");
        sessao.removeAttribute("desabilitarDadosSolicitanteUnidade");
		sessao.removeAttribute("desabilitarDadosSolicitanteFuncionario");
		sessao.removeAttribute("desabilitarDadosSolicitanteNome");
		sessao.removeAttribute("habilitarAlteracaoEnderecoSolicitante");
		sessao.removeAttribute("desabilitarDadosSolicitanteCliente");
		
		//Aba Nº04 - Anexos
		sessao.removeAttribute("colecaoRegistroAtendimentoAnexo");
        
        //Montando o Status do Wizard (Componente responsável pela geração das abas)
		StatusWizard statusWizard = null;
		
		//Se for originada de uma requisição GIS, botão de desfazer aponta para a página inicial da requisição
		
		if(origemGIS){
			statusWizard = new StatusWizard(
	                "inserirRegistroAtendimentoWizardAction", "concluirInserirRegistroAtendimentoAction",
	                "cancelarInserirRegistroAtendimentoAction", gisHelper.gerarURLChamada());
		}
		else{
			statusWizard = new StatusWizard(
	                "inserirRegistroAtendimentoWizardAction", "concluirInserirRegistroAtendimentoAction",
	                "cancelarInserirRegistroAtendimentoAction", "exibirInserirRegistroAtendimentoAction.do");
		}
		
        statusWizard
                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                        1, "DadosGeraisPrimeiraAbaA.gif", "DadosGeraisPrimeiraAbaD.gif",
                        "exibirInserirRegistroAtendimentoDadosGeraisAction",
                        "inserirRegistroAtendimentoDadosGeraisAction"));
        statusWizard
                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                        2, "LocalOcorrenciaIntervaloAbaA.gif", "LocalOcorrenciaIntervaloAbaD.gif",
                        "exibirInserirRegistroAtendimentoDadosLocalOcorrenciaAction",
                        "inserirRegistroAtendimentoDadosLocalOcorrenciaAction"));
        statusWizard
        		.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
        				3, "SolicitanteUltimaAbaA.gif", "SolicitanteUltimaAbaD.gif",
        				"exibirInserirRegistroAtendimentoDadosSolicitanteAction",
                		"inserirRegistroAtendimentoDadosSolicitanteAction"));
        
        statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						4, "Anexos02.gif", "Anexos.gif",
						"exibirInserirRegistroAtendimentoAnexosAction",
        				"inserirRegistroAtendimentoAnexosAction"));
      
        
        sessao.setAttribute("statusWizard", statusWizard);
        
        //OBTENDO PROTOCOLO DE ATENDIMENTO 
		if (sessao.getAttribute("protocoloAtendimento") == null){
			
			String protocoloAtendimento = this.getFachada().obterProtocoloAtendimento();
			sessao.setAttribute("protocoloAtendimento", protocoloAtendimento);
		}
        
		
		
		
        return retorno;
    }

}
