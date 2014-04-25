package gcom.gui.cadastro.sistemaparametro;

import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 04/01/2007
 */
public class ExibirInformarParametrosSistemaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("informarParametrosSistemaDadosGeraisEmpresaAction");

		// obtém a instância da sessão
		HttpSession sessao = this.getSessao(httpServletRequest);
		
		// Monta o Status do Wizard
		StatusWizard statusWizard = new StatusWizard(
				"informarParametrosSistemaWizardAction",
				"informarParametrosSistemaAction",
				"cancelarInformarParametrosSistemaAction",
				"exibirInformarParametrosSistemaAction.do");
		
		statusWizard.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
			1,
			"DadosGeraisPrimeiraAbaA.gif",
			"DadosGeraisPrimeiraAbaD.gif",
			"exibirInformarParametrosSistemaDadosGeraisEmpresaAction",
			"informarParametrosSistemaDadosGeraisEmpresaAction"));

		statusWizard.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
			2,
			"FaturamentoTarifaSocialIntervaloA.gif",
			"FaturamentoTarifaSocialIntervaloD.gif",
			"exibirInformarParametrosSistemaFaturamentoTarifaSocialAction",
			"informarParametrosSistemaFaturamentoTarifaSocialAction"));

		statusWizard.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
			3,
			"ArrecadacaoFinanceiroIntervaloA.gif",
			"ArrecadacaoFinanceiroIntervaloD.gif",
			"exibirInformarParametrosSistemaArrecadacaoFinanceiroAction",
			"informarParametrosSistemaArrecadacaoFinanceiroAction"));

		statusWizard.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
			4,
			"MedicaoCobrancaIntervaloA.gif",
			"MedicaoCobrancaIntervaloD.gif",
			"exibirInformarParametrosSistemaMicromedicaoCobrancaAction",
			"informarParametrosSistemaMicromedicaoCobrancaAction"));

		statusWizard.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
			5,
			"AtendimentoSegurancaUltimaAbaA.gif",
			"AtendimentoSegurancaUltimaAbaD.gif",
			"exibirInformarParametrosSistemaAtendimentoPublicoSegurancaAction",
			"informarParametrosSistemaAtendimentoPublicoSegurancaAction"));

		// manda o statusWizard para a sessao
		sessao.setAttribute("statusWizard", statusWizard);

		FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();

		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouro");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("bairro");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("hidrometroCapacidade");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("cep");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("contaBancaria");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("clientePresidenteCompesa");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacionalIdPresidencia");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("clienteDiretorComercialCompesa");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("clienteFicticioParaAssociarOsPagamentosNaoIdentificados");

		Collection colecaoSistemaParametro = 
			this.getFachada().pesquisar(filtroSistemaParametro, 
				SistemaParametro.class.getName());

		SistemaParametro sistemaParametro = (SistemaParametro) colecaoSistemaParametro.iterator().next();

		sessao.setAttribute("sistemaParametro", sistemaParametro);
		sessao.setAttribute("paramId", sistemaParametro.getParmId());

		return retorno;
	}
}
