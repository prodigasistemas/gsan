package gcom.seguranca;


import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.seguranca.RelatorioResultadoPesquisaConsultaCadastroCdl;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Rodrigo de Abreu Cabral
 * @version 1.0
 */

public class GerarRelatorioResultadoPesquisaConsultaCadastroCdlAction extends
		ExibidorProcessamentoTarefaRelatorio {

	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a variável de retorno
		ActionForward retorno = null;

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarConsultaCadastroCdlActionForm form = (FiltrarConsultaCadastroCdlActionForm) actionForm;
		
		FiltroConsultaCadastroCdl filtroConsultaCadastroCdl = 
			(FiltroConsultaCadastroCdl) sessao.getAttribute("filtroConsultaCadastroCdl");

		
		// Inicio da parte que vai mandar os parametros para o relatório

		// seta os parametros que serão mostrados no relatório
		String periodoAcessoInicial = "";
		
		if(form.getPeriodoAcessoInicial()!= null && !form.getPeriodoAcessoInicial().equals("")){
			
			periodoAcessoInicial = form.getPeriodoAcessoInicial();
		}
		
		String periodoAcessoFinal = "";
		
		if(form.getPeriodoAcessoFinal()!= null && !form.getPeriodoAcessoFinal().equals("")){
			
			periodoAcessoFinal = form.getPeriodoAcessoFinal();
		}
		
		
		
		// Fim da parte que vai mandar os parametros para o relatório

		// cria uma instância da classe do relatório
		RelatorioResultadoPesquisaConsultaCadastroCdl relatorioResultadoPesquisaConsultaCadastroCdl = new RelatorioResultadoPesquisaConsultaCadastroCdl(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioResultadoPesquisaConsultaCadastroCdl.addParametro("filtroConsultaCadastroCdl",
				filtroConsultaCadastroCdl);
		relatorioResultadoPesquisaConsultaCadastroCdl.addParametro("periodoAcessoInicial",
				periodoAcessoInicial);
		relatorioResultadoPesquisaConsultaCadastroCdl.addParametro("periodoAcessoFinal",
				periodoAcessoFinal);
		
		

		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioResultadoPesquisaConsultaCadastroCdl.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioResultadoPesquisaConsultaCadastroCdl,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);

		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
