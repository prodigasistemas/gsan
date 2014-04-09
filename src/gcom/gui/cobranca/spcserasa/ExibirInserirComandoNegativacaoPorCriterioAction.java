package gcom.gui.cobranca.spcserasa;

import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Esta classe tem por finalidade gerar as abas que serão responsáveis pelo processo de inserção de um
 * comando de negativação
 *
 * @author Ana Maria
 * @date 06/11/2007
 */
public class ExibirInserirComandoNegativacaoPorCriterioAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("inserirComandoNegativacaoPorCriterio");

        HttpSession sessao = httpServletRequest.getSession(false);
        
        httpServletRequest.setAttribute("entrou", "ok");

        //Removendo todos os objetos da sessão 
        // Aba 01
        sessao.removeAttribute("statusWizard");
        sessao.removeAttribute("InserirComandoNegativacaoActionForm");
        sessao.removeAttribute("colecaoCPFTipo");
        sessao.removeAttribute("colecaoNegativacaoCriterioCpfTipo");
        
        //Aba 03
        sessao.removeAttribute("colecaoClienteRelacaoTipo");
        sessao.removeAttribute("colecaoSubcategoria");
        sessao.removeAttribute("colecaoPerfilImovel");
        sessao.removeAttribute("colecaoTipoCliente");
        
        //Aba 04
        sessao.removeAttribute("colcaoCobrancaGrupo");
        sessao.removeAttribute("colecaoGerenciaRegional");        
        sessao.removeAttribute("colecaoUnidadeNegocio");
        sessao.removeAttribute("colecaoEloPolo");
        
        
        //Montando o Status do Wizard (Componente responsável pela geração das abas)
        StatusWizard statusWizard = new StatusWizard(
                "inserirComandoNegativacaPorCriterioWizardAction", "concluirInserirComandoNegativacaoPorCriterioAction",
                "cancelarInserirComandoNegativacaoAction", "exibirInserirComandoNegativacaoTipoComandoAction.do?menu=sim",
                "",
                "exibirInserirComandoNegativacaoTipoComandoAction.do?menu=sim",
                "");

        statusWizard
			.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
					    1, "DadosGeraisPrimeiraAbaA.gif", "DadosGeraisPrimeiraAbaD.gif",
					    "exibirInserirComandoNegativacaoDadosGeraisAction",
						"inserirComandoNegativacaoDadosGeraisAction"));
        
        statusWizard
        		.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
        				2, "DebitosA.gif", "DebitosD.gif",
        				"exibirInserirComandoNegativacaoDadosDebitoAction",
                		"inserirComandoNegativacaoDadosDebitoAction"));
        statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						3, "ImovelPrimeiraAbaA.gif", "ImovelPrimeiraAbaD.gif",
						"exibirInserirComandoNegativacaoDadosImovelAction",
        				"inserirComandoNegativacaoDadosImovelAction"));
        statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						4, "LocalizacaoA.gif", "LocalizacaoD.gif",
						"exibirInserirComandoNegativacaoLocalizacaoAction",
        				"inserirComandoNegativacaoLocalizacaoAction"));
        
        statusWizard
		.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
				5, "abaExclusaoA.gif", "abaExclusaoD.gif",
				"exibirInserirComandoNegativacaoExclusaoAction",
				"inserirComandoNegativacaoExclusaoAction"));
		      
        sessao.setAttribute("statusWizard", statusWizard);

      
        return retorno;
    }

}
