package gcom.gui.cobranca.spcserasa;

import gcom.gui.WizardAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Esta classe tem por finalidade gerar as abas que serão responsáveis pelo processo de inserção de um
 * Comando de Negativação
 *
 * @author Ana Maria
 * @date 02/01/2008
 */
public class AtualizarComandoNegativacaPorCriterioWizardAction extends WizardAction {
		
	/*
	 * ABA Nº 01 - DADOS GERAIS
	 */
	public ActionForward exibirAtualizarComandoNegativacaoDadosGeraisAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		return new ExibirAtualizarComandoNegativacaoDadosGeraisAction().execute(
				actionMapping, actionForm, httpServletRequest,
				httpServletResponse);
	}
	
	public ActionForward atualizarComandoNegativacaoDadosGeraisAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		new AtualizarComandoNegativacaoDadosGeraisAction().execute(actionMapping,
				actionForm, httpServletRequest, httpServletResponse);
		
		return this.redirecionadorWizard(actionMapping, actionForm,
				httpServletRequest, httpServletResponse);
	}
	
	/*
	 * ABA Nº 02 - DADOS DO DÉBITO
	 */
	public ActionForward exibirAtualizarComandoNegativacaoDadosDebitoAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		return new ExibirAtualizarComandoNegativacaoDadosDebitoAction().execute(
				actionMapping, actionForm, httpServletRequest,
				httpServletResponse);
	}
	
	public ActionForward atualizarComandoNegativacaoDadosDebitoAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		new AtualizarComandoNegativacaoDadosDebitoAction().execute(actionMapping,
				actionForm, httpServletRequest, httpServletResponse);
		
		return this.redirecionadorWizard(actionMapping, actionForm,
				httpServletRequest, httpServletResponse);
	}

	/*
	 * ABA Nº 03 - DADOS DO IMOVEL
	 */
	public ActionForward exibirAtualizarComandoNegativacaoDadosImovelAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		return new ExibirAtualizarComandoNegativacaoDadosImovelAction().execute(
				actionMapping, actionForm, httpServletRequest,
				httpServletResponse);
	}
	
	public ActionForward atualizarComandoNegativacaoDadosImovelAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		new AtualizarComandoNegativacaoDadosImovelAction().execute(actionMapping,
				actionForm, httpServletRequest, httpServletResponse);
		
		return this.redirecionadorWizard(actionMapping, actionForm,
				httpServletRequest, httpServletResponse);
	}
	
	/*
	 * ABA Nº 04 - LOCALIZAÇÃO
	 */
	public ActionForward exibirAtualizarComandoNegativacaoLocalizacaoAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		return new ExibirAtualizarComandoNegativacaoLocalizacaoAction().execute(
				actionMapping, actionForm, httpServletRequest,
				httpServletResponse);
	}
	
	public ActionForward atualizarComandoNegativacaoLocalizacaoAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		new AtualizarComandoNegativacaoLocalizacaoAction().execute(actionMapping,
				actionForm, httpServletRequest, httpServletResponse);
		
		return this.redirecionadorWizard(actionMapping, actionForm,
				httpServletRequest, httpServletResponse);
	}
	
	/*
	 * ATUALIZAR
	 */
	public ActionForward atualizarComandoNegativacaoPorCriterioAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		return new AtualizarComandoNegativacaoPorCriterioAction().execute(actionMapping,
				actionForm, httpServletRequest, httpServletResponse);
	}
	
	/*
	 * ABA Nº 05 - EXCLUSÃO
	 */
	public ActionForward exibirAtualizarComandoNegativacaoExclusaoAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		return new ExibirAtualizarComandoNegativacaoExclusaoAction().execute(
				actionMapping, actionForm, httpServletRequest,
				httpServletResponse);
	}
	
	public ActionForward atualizarComandoNegativacaoExclusaoAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		new AtualizarComandoNegativacaoExclusaoAction().execute(actionMapping,
				actionForm, httpServletRequest, httpServletResponse);
		
		return this.redirecionadorWizard(actionMapping, actionForm,
				httpServletRequest, httpServletResponse);
	}

}
