package gcom.gui.faturamento;

import java.util.Collection;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;
import gcom.operacional.FiltroSistemaAbastecimento;
import gcom.operacional.SistemaAbastecimento;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0596] Inserir Qualidade de Agua
 * 
 * @author Kássia Albuquerque
 * @date 24/07/2007
 */

public class ExibirInserirQualidadeAguaAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("inserirQualidadeAguaActionDadosAction");

		// obtém a instância da sessão
		HttpSession sessao = this.getSessao(httpServletRequest);
		
		// Monta o Status do Wizard
		StatusWizard statusWizard = new StatusWizard(
				"inserirQualidadeAguaWizardAction",
				"inserirQualidadeAguaAction",
				"cancelarInserirQualidadeAguaAction",
				"exibirInserirQualidadeAguaAction.do");
		
		statusWizard.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
			1,
			"DadosPrimeiraAbaA.gif",
			"DadosPrimeiraAbaD.gif",
			"exibirInserirQualidadeAguaDadosAction",
			"inserirQualidadeAguaDadosAction"));

		statusWizard.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
			2,
			"AnaliseUltimaAbaA.gif",
			"AnaliseUltimaAbaD.gif",
			"exibirInserirQualidadeAguaAnaliseAction",
			"inserirQualidadeAguaAnaliseAction"));
		
		//Sistema Abastecimento
		
		Fachada fachada = Fachada.getInstancia();
		
		FiltroSistemaAbastecimento filtroSistemaAbastecimento = new FiltroSistemaAbastecimento();

		// Verifica se os dados foram informados da tabela existem e joga numa
		// colecao

		Collection<SistemaAbastecimento> colecaoSistemaAbastecimento = fachada.pesquisar(filtroSistemaAbastecimento,
				SistemaAbastecimento.class.getName());

		if (colecaoSistemaAbastecimento == null || colecaoSistemaAbastecimento.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela Sistema Abastecimento");
		}
		
		//Manda valores do Sistema de Abastecimento para Sessão
		httpServletRequest.setAttribute("colecaoSistemaAbastecimento", colecaoSistemaAbastecimento);
		
		// manda o statusWizard para a sessao
		sessao.setAttribute("statusWizard", statusWizard);
	
		return retorno;
	}
}
