package gcom.gui.relatorio.atendimentopublico;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.gui.atendimentopublico.ligacaoagua.FiltrarLigacaoAguaSituacaoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.atendimentopublico.RelatorioManterLigacaoAguaSituacao;
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

public class GerarRelatorioLigacaoAguaSituacaoManterAction extends
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

		FiltrarLigacaoAguaSituacaoActionForm filtrarLigacaoAguaSituacaoActionForm = (FiltrarLigacaoAguaSituacaoActionForm) actionForm;

		FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = (FiltroLigacaoAguaSituacao) sessao
				.getAttribute("filtroLigacaoAguaSituacao");

		// Inicio da parte que vai mandar os parametros para o relatório

		LigacaoAguaSituacao ligacaoAguaSituacaoParametros = new LigacaoAguaSituacao();

		String id = null;

		String idLigacaoAguaSituacaoPesquisar = (String) filtrarLigacaoAguaSituacaoActionForm
				.getId();

		if (id != null && !id.equals("")) {
			id = idLigacaoAguaSituacaoPesquisar;
		}

		Short indicadorDeUso = null;

		if (filtrarLigacaoAguaSituacaoActionForm.getIndicadorUso() != null
				&& !filtrarLigacaoAguaSituacaoActionForm.getIndicadorUso().equals("")) {

			indicadorDeUso = new Short(""
					+ filtrarLigacaoAguaSituacaoActionForm.getIndicadorUso());
		}
		
		Integer consumoMinimo = null;
		
		if (filtrarLigacaoAguaSituacaoActionForm.getConsumoMinimoFaturamento() != null && !filtrarLigacaoAguaSituacaoActionForm.getConsumoMinimoFaturamento().equals("")){
			
			consumoMinimo = new Integer("" +
					filtrarLigacaoAguaSituacaoActionForm.getConsumoMinimoFaturamento());
		}

		// seta os parametros que serão mostrados no relatório

		ligacaoAguaSituacaoParametros.setId(id == null ? null : new Integer(
				id));
		ligacaoAguaSituacaoParametros.setDescricao(""
				+ filtrarLigacaoAguaSituacaoActionForm.getDescricao());
		ligacaoAguaSituacaoParametros.setDescricaoAbreviado(""
				+ filtrarLigacaoAguaSituacaoActionForm.getDescricaoAbreviada());
		ligacaoAguaSituacaoParametros.setConsumoMinimoFaturamento(consumoMinimo);
		ligacaoAguaSituacaoParametros.setIndicadorFaturamentoSituacao(new Short(
				filtrarLigacaoAguaSituacaoActionForm.getIndicadorFaturamentoSituacao()));
		ligacaoAguaSituacaoParametros.setIndicadorExistenciaRede(new Short(
				filtrarLigacaoAguaSituacaoActionForm.getIndicadorExistenciaRede()));
		ligacaoAguaSituacaoParametros.setIndicadorExistenciaLigacao(new Short(
				filtrarLigacaoAguaSituacaoActionForm.getIndicadorExistenciaLigacao()));
		ligacaoAguaSituacaoParametros.setIndicadorAguaAtiva(new Short(
				filtrarLigacaoAguaSituacaoActionForm.getIndicadorAguaAtiva()));
		ligacaoAguaSituacaoParametros.setIndicadorAguaCadastrada(new Short(
				filtrarLigacaoAguaSituacaoActionForm.getIndicadorAguaCadastrada()));
		ligacaoAguaSituacaoParametros.setIndicadorAguaDesligada(new Short(
				filtrarLigacaoAguaSituacaoActionForm.getIndicadorAguaDesligada()));
		ligacaoAguaSituacaoParametros.setIndicadorAnalizeAgua(new Short(
				filtrarLigacaoAguaSituacaoActionForm.getIndicadorAnalizeAgua()));
		ligacaoAguaSituacaoParametros.setIndicadorUso(indicadorDeUso);

		// Fim da parte que vai mandar os parametros para o relatório

		// cria uma instância da classe do relatório
		RelatorioManterLigacaoAguaSituacao relatorioManterLigacaoAguaSituacao = new RelatorioManterLigacaoAguaSituacao(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterLigacaoAguaSituacao.addParametro("filtroLigacaoAguaSituacao",
				filtroLigacaoAguaSituacao);
		relatorioManterLigacaoAguaSituacao.addParametro("ligacaoAguaSituacaoParametros",
				ligacaoAguaSituacaoParametros);

		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterLigacaoAguaSituacao.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterLigacaoAguaSituacao,
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
