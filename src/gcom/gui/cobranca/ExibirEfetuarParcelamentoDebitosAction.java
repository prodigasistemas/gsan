package gcom.gui.cobranca;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionFormClass;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Action inicial do caso de uso [UC0214] Efetuar Parcelamento de Débitos
 * 
 * @author Rodrigo
 */
public class ExibirEfetuarParcelamentoDebitosAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		// Vai para a primeira página do caso de uso

		ActionForward retorno = actionMapping
				.findForward("exibirEfetuarParcelamentoDebitosProcesso1Action");

		// obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		DynaValidatorForm efetuarParcelamentoDebitosActionForm = (DynaValidatorForm) actionForm;
		
		if (httpServletRequest.getParameter("guardarMatriculaImovel") != null && !httpServletRequest.getParameter("guardarMatriculaImovel").equals("")){
			String codigoImovel = (String)httpServletRequest.getParameter("guardarMatriculaImovel");
			
			if (!efetuarParcelamentoDebitosActionForm.get("matriculaImovel").equals(codigoImovel)){
				//limpa a sessão
				sessao.removeAttribute("EfetuarParcelamentoDebitosActionForm");
				
				//cria uma nova instancia do actionForm e seta apenas a matricula do imovel nele
				 ModuleConfig module = actionMapping.getModuleConfig();
				 FormBeanConfig formBeanConfig = module.findFormBeanConfig("EfetuarParcelamentoDebitosActionForm");
				 DynaActionFormClass dynaClass = DynaActionFormClass.createDynaActionFormClass(formBeanConfig);
				 DynaValidatorForm form = null;
				 try {
					form = (DynaValidatorForm) dynaClass.newInstance();
					form.set("matriculaImovel",codigoImovel);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				}

				sessao.setAttribute("EfetuarParcelamentoDebitosActionForm",form);
			}
			
		}else{
			//limpa a sessão
			sessao.removeAttribute("EfetuarParcelamentoDebitosActionForm");
		}
		
		if (httpServletRequest.getParameter("menu") != null && httpServletRequest.getParameter("menu").equals("sim")) {
			
			SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
			
			if (sistemaParametro.getIndicadorDividaAtiva() == 1) {
				sessao.setAttribute("empresaDividaAtiva", "sim");
			}
			
		}
			
		// Monta o Status do Wizard - Do tipo Valida Avançar e Valida Voltar
		StatusWizard statusWizard = new StatusWizard(
				"efetuarParcelamentoDebitosWizardAction",
				"concluirProcessoAction",
				"cancelarEfetuarParcelamentoDebitosAction",
				"",
				"",
				"exibirEfetuarParcelamentoDebitosAction.do?menu=sim",
				"");
        

        statusWizard
        .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                1, "ImovelPrimeiraAbaA.gif", "ImovelPrimeiraAbaD.gif",
                "exibirEfetuarParcelamentoDebitosProcesso1Action", "processarProcesso1Action"));
        statusWizard
        .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                2, "DebitosA.gif", "DebitosD.gif",
                "exibirEfetuarParcelamentoDebitosProcesso2Action", "processarProcesso2Action"));
        statusWizard
        .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                3, "NegociacaoA.gif", "NegociacaoD.gif",
                "exibirEfetuarParcelamentoDebitosProcesso3Action", "processarProcesso3Action"));
        statusWizard
        .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                4, "ConclusaoA.gif", "ConclusaoD.gif",
                "exibirEfetuarParcelamentoDebitosProcesso4Action", "processarProcesso4Action"));

		/*statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						1, "ImovelPrimeiraAbaA.gif", "ImovelPrimeiraAbaD.gif",
						"exibirProcesso1Action", "processarProcesso1Action"));
		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						2, "DebitosA.gif", "DebitosD.gif",
						"exibirProcesso2Action", "processarProcesso2Action"));
		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						3, "NegociacaoA.gif", "NegociacaoD.gif",
						"exibirProcesso3Action", "processarProcesso3Action"));
		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						4, "ConclusaoA.gif", "ConclusaoD.gif",
						"exibirProcesso4Action", "processarProcesso4Action"));
*/
		// manda o statusWizard para a sessao
		sessao.setAttribute("statusWizard", statusWizard);

		return retorno;
	}
}
