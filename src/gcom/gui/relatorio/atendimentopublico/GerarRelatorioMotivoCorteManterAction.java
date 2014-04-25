package gcom.gui.relatorio.atendimentopublico;

import gcom.atendimentopublico.ligacaoagua.FiltroMotivoCorte;
import gcom.atendimentopublico.ligacaoagua.MotivoCorte;
import gcom.gui.atendimentopublico.FiltrarMotivoCorteActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.atendimentopublico.RelatorioManterMotivoCorte;
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
 * @author Rafael Corrêa
 * @version 1.0
 */

public class GerarRelatorioMotivoCorteManterAction extends
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

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarMotivoCorteActionForm filtrarMotivoCorteActionForm = (FiltrarMotivoCorteActionForm) actionForm;

		FiltroMotivoCorte filtroMotivoCorte = (FiltroMotivoCorte) sessao
				.getAttribute("filtroMotivoCorte");

		// Inicio da parte que vai mandar os parametros para o relatório

		MotivoCorte motivoCorteParametros = new MotivoCorte();

		String idMotivoCorte = null;

		String idMotivoCortePesquisar = (String) filtrarMotivoCorteActionForm
				.getIdMotivoCorte();

		if (idMotivoCortePesquisar != null && !idMotivoCortePesquisar.equals("")) {
			idMotivoCorte = idMotivoCortePesquisar;
		}

		Short indicadorDeUso = null;

		if (filtrarMotivoCorteActionForm.getIndicadorUso() != null
				&& !filtrarMotivoCorteActionForm.getIndicadorUso().equals("")) {

			indicadorDeUso = new Short(""
					+ filtrarMotivoCorteActionForm.getIndicadorUso());
		}

		// seta os parametros que serão mostrados no relatório

		motivoCorteParametros.setId(idMotivoCorte == null ? null : new Integer(
				idMotivoCorte));
		motivoCorteParametros.setDescricao(""
				+ filtrarMotivoCorteActionForm.getDescricao());
		motivoCorteParametros.setIndicadorUso(indicadorDeUso);

		// Fim da parte que vai mandar os parametros para o relatório

		// cria uma instância da classe do relatório
		RelatorioManterMotivoCorte relatorioManterMotivoCorte = new RelatorioManterMotivoCorte(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterMotivoCorte.addParametro("filtroMotivoCorte",
				filtroMotivoCorte);
		relatorioManterMotivoCorte.addParametro("motivoCorteParametros",
				motivoCorteParametros);

		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterMotivoCorte.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterMotivoCorte,
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
