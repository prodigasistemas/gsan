package gcom.gui.relatorio.cadastro;

import java.util.Collection;

import gcom.atendimentopublico.registroatendimento.MeioSolicitacao;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.RelatorioAlteracoesCpfCnpj;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioAlteracoesCpfCnpjAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		
		GerarRelatorioAlteracoesCpfCnpjActionForm form = (GerarRelatorioAlteracoesCpfCnpjActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);

		String opcaoTipoRelatorio = form.getOpcaoTipoRelatorio();
		
		if (opcaoTipoRelatorio == null || opcaoTipoRelatorio.equalsIgnoreCase("")) {
			
			if (sessao.getAttribute("opcaoTipoRelatorio") == null) {
				throw new ActionServletException("atencao.required", null, "Opção de Tipo de Relatório ");
			} else {
				opcaoTipoRelatorio = (String) sessao.getAttribute("opcaoTipoRelatorio");
			}
		}
		
		String nomeRelatorio = "";

		if (opcaoTipoRelatorio.trim().equals("1")) {
			nomeRelatorio = ConstantesRelatorios.RELATORIO_ALTERACOES_CPF_CNPJ_USUARIO;
		} else if (opcaoTipoRelatorio.trim().equals("2")) {
			nomeRelatorio = ConstantesRelatorios.RELATORIO_ALTERACOES_CPF_CNPJ_LOCALIDADE;
		} else if (opcaoTipoRelatorio.trim().equals("3")) {
			nomeRelatorio = ConstantesRelatorios.RELATORIO_ALTERACOES_CPF_CNPJ_MEIO;
		}

		GerarRelatorioAlteracoesCpfCnpjHelper helper = this.criarHelperRelatorioAlteracoesSistemaColuna(form, sessao);
		
		// Parte que vai mandar o relatório para a tela

		// cria uma instância da classe do relatório
		RelatorioAlteracoesCpfCnpj relatorioAlteracoesCpfCnpj = new RelatorioAlteracoesCpfCnpj(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"),
				nomeRelatorio);
		relatorioAlteracoesCpfCnpj.addParametro("opcaoTipoRelatorio",
				opcaoTipoRelatorio);

		//Adiciona filtro escolhido pelo usuario ao relatorio
		relatorioAlteracoesCpfCnpj.addParametro("filtroHelper", helper);
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioAlteracoesCpfCnpj.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioAlteracoesCpfCnpj,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);

		} catch (RelatorioVazioException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");

		}

		return retorno;
	}

	private GerarRelatorioAlteracoesCpfCnpjHelper criarHelperRelatorioAlteracoesSistemaColuna(
			GerarRelatorioAlteracoesCpfCnpjActionForm form, HttpSession sessao) {
		
		GerarRelatorioAlteracoesCpfCnpjHelper retorno = null;
		
		//Caso tipo do relatorio a ser executado seja "USUARIO"
		if(form.getOpcaoTipoRelatorio().equals("1")){
			retorno = new GerarRelatorioAlteracoesCpfCnpjHelper(
					form.getOpcaoTipoRelatorio(),		
					form.getDataInicial(),
					form.getDataFinal(),
					form.getMeio(),
					(Collection<MeioSolicitacao>)sessao.getAttribute("colecaoMeiosSolicitacao"),
					form.getIdUnidadeSuperior(),
					(Collection<UnidadeOrganizacional>)sessao.getAttribute("colecaoUnidadeOrganizacional"),
					(Collection<Usuario>)sessao.getAttribute("colecaoUsuario"));
		}

		//Caso tipo do relatorio a ser executado seja "LOCALIDADE"
		if(form.getOpcaoTipoRelatorio().equals("2")){
			retorno = new GerarRelatorioAlteracoesCpfCnpjHelper(
					form.getOpcaoTipoRelatorio(),		
					form.getDataInicial(),
					form.getDataFinal(),
					form.getMeio(),
					(Collection<MeioSolicitacao>)sessao.getAttribute("colecaoMeiosSolicitacao"),
					form.getIdGerenciaRegional(),
					form.getIdGerenciaRegionalporLocalidade(),
					form.getIdUnidadeNegocio(),
					form.getIdLocalidade(),
					form.getOpcaoTotalizacao());
		}
		
		//Caso tipo do relatorio a ser executado seja "MEIO"
		if(form.getOpcaoTipoRelatorio().equals("3")){
			retorno = new GerarRelatorioAlteracoesCpfCnpjHelper(
					form.getOpcaoTipoRelatorio(),		
					form.getDataInicial(),
					form.getDataFinal(),
					form.getMeio(),
					(Collection<MeioSolicitacao>)sessao.getAttribute("colecaoMeiosSolicitacao"));
		}
		
		
		return retorno;
	}
}
