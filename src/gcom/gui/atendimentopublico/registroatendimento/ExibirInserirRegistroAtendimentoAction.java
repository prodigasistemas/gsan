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
