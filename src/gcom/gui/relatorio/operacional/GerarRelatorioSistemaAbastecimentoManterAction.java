package gcom.gui.relatorio.operacional;

import gcom.gui.operacional.FiltrarSistemaAbastecimentoActionForm;
import gcom.operacional.FiltroSistemaAbastecimento;
import gcom.operacional.FonteCaptacao;
import gcom.operacional.SistemaAbastecimento;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.operacional.RelatorioManterSistemaAbastecimento;
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
 * @author Fernando Fontelles Filho
 * @version 1.0
 */

public class GerarRelatorioSistemaAbastecimentoManterAction extends
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

		FiltrarSistemaAbastecimentoActionForm form = (FiltrarSistemaAbastecimentoActionForm) actionForm;

		FiltroSistemaAbastecimento filtroSistemaAbastecimento = (FiltroSistemaAbastecimento) sessao
				.getAttribute("filtroSistemaAbastecimento");

		// Inicio da parte que vai mandar os parametros para o relatório

		SistemaAbastecimento sistemaAbastecimentoParametros = new SistemaAbastecimento();

		String id = null;

		Short indicadordeUso = null;
		
		if(form.getIndicadorUso()!= null && !form.getIndicadorUso().equals("")){
			
			indicadordeUso = new Short ("" + form.getIndicadorUso());
		}
		
		
		// seta os parametros que serão mostrados no relatório

		sistemaAbastecimentoParametros.setId(id == null ? null : new Integer(
				id));
		
		sistemaAbastecimentoParametros.setDescricao(""
				+ form.getDescricao());
		
		sistemaAbastecimentoParametros.setDescricaoAbreviada(form.getDescricaoAbreviada());
		
		sistemaAbastecimentoParametros.setIndicadorUso(indicadordeUso);
		
		if(form.getIdFonteCaptacao() != null && !form.getIdFonteCaptacao().equals("")){
			
			FonteCaptacao fonteCaptacao = new FonteCaptacao();
			fonteCaptacao.setDescricao(form.getDescricaoFonteCaptacao());
			
			sistemaAbastecimentoParametros.setFonteCaptacao(fonteCaptacao);
			
		}
		
		// Fim da parte que vai mandar os parametros para o relatório

		// cria uma instância da classe do relatório
		RelatorioManterSistemaAbastecimento relatorioManterSistemaAbastecimento = new RelatorioManterSistemaAbastecimento(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterSistemaAbastecimento.addParametro("filtroSistemaAbastecimento",
				filtroSistemaAbastecimento);
		relatorioManterSistemaAbastecimento.addParametro("sistemaAbastecimentoParametros",
				sistemaAbastecimentoParametros);

		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterSistemaAbastecimento.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterSistemaAbastecimento,
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
