package gcom.gui.relatorio.atendimentopublico;

import gcom.atendimentopublico.ordemservico.Atividade;
import gcom.atendimentopublico.ordemservico.FiltroAtividade;
import gcom.gui.atendimentopublico.FiltrarAtividadeActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.atendimentopublico.RelatorioManterAtividade;
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
 * @author Vinicius Medeiros
 * @version 1.0
 */

public class GerarRelatorioAtividadeManterAction extends
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

		FiltrarAtividadeActionForm filtrarAtividadeActionForm = (FiltrarAtividadeActionForm) actionForm;

		FiltroAtividade filtroAtividade = (FiltroAtividade) sessao
				.getAttribute("filtroAtividade");

		// Inicio da parte que vai mandar os parametros para o relatório

		Atividade atividadeParametros = new Atividade();

		String id = null;

		String idAtividadePesquisar = (String) filtrarAtividadeActionForm
				.getId();

		if (idAtividadePesquisar != null && !idAtividadePesquisar.equals("")) {
			id = idAtividadePesquisar;
		}
		
		Short indicadorDeUso = null;

		if (filtrarAtividadeActionForm.getIndicadorUso() != null
				&& !filtrarAtividadeActionForm.getIndicadorUso().equals("")) {

			indicadorDeUso = new Short(""
					+ filtrarAtividadeActionForm.getIndicadorUso());
		}
		
		Short atividadeUnica = null;
		
		if(filtrarAtividadeActionForm.getIndicadorAtividadeUnica() != null && !filtrarAtividadeActionForm.getIndicadorAtividadeUnica().equals("")){
			
			atividadeUnica = new Short (""
					+ filtrarAtividadeActionForm.getIndicadorAtividadeUnica());
		}
		

		// seta os parametros que serão mostrados no relatório

		atividadeParametros.setId(id == null ? null : new Integer(
				id));
		atividadeParametros.setDescricao(""
				+ filtrarAtividadeActionForm.getDescricao());
		atividadeParametros.setDescricaoAbreviada(""
				+ filtrarAtividadeActionForm.getDescricaoAbreviada());
		atividadeParametros.setIndicadorAtividadeUnica(atividadeUnica);
		atividadeParametros.setIndicadorUso(indicadorDeUso);

		// Fim da parte que vai mandar os parametros para o relatório

		// cria uma instância da classe do relatório
		RelatorioManterAtividade relatorioManterAtividade = new RelatorioManterAtividade(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterAtividade.addParametro("filtroAtividade",
				filtroAtividade);
		relatorioManterAtividade.addParametro("atividadeParametros",
				atividadeParametros);

		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterAtividade.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterAtividade,
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
