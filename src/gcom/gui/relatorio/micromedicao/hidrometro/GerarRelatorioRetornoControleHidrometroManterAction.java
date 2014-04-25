package gcom.gui.relatorio.micromedicao.hidrometro;

import gcom.gui.micromedicao.hidrometro.FiltrarRetornoControleHidrometroActionForm;
import gcom.micromedicao.hidrometro.FiltroRetornoControleHidrometro;
import gcom.micromedicao.hidrometro.RetornoControleHidrometro;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.micromedicao.hidrometro.RelatorioManterRetornoControleHidrometro;
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
 * @author Wallace Thierre
 * @version 1.0
 */

public class GerarRelatorioRetornoControleHidrometroManterAction extends
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

		FiltrarRetornoControleHidrometroActionForm filtrarRetornoControleHidrometroActionForm = (FiltrarRetornoControleHidrometroActionForm) actionForm;

		FiltroRetornoControleHidrometro filtroRetornoControleHidrometro = (FiltroRetornoControleHidrometro) sessao
				.getAttribute("filtroRetornoControleHidrometro");

		// Inicio da parte que vai mandar os parametros para o relatório

		RetornoControleHidrometro retornoControleHidrometroParametros = new RetornoControleHidrometro();
		String id = null;

		String idRetornoControleHidrometroPesquisar = (String) filtrarRetornoControleHidrometroActionForm.getId();

		if (idRetornoControleHidrometroPesquisar != null && !idRetornoControleHidrometroPesquisar.equals("")) {
			id = idRetornoControleHidrometroPesquisar;
		}	
		
		Short indicadorDeGeracao = null;

		if (filtrarRetornoControleHidrometroActionForm.getIndicadorUso() != null
				&& !filtrarRetornoControleHidrometroActionForm.getIndicadorUso().equals("")) {

			indicadorDeGeracao = new Short(""
					+ filtrarRetornoControleHidrometroActionForm.getIndicadorUso());
		}
		
		Short indicadorDeUso = null;

		if (filtrarRetornoControleHidrometroActionForm.getIndicadorUso() != null
				&& !filtrarRetornoControleHidrometroActionForm.getIndicadorUso().equals("")) {

			indicadorDeUso = new Short(""
					+ filtrarRetornoControleHidrometroActionForm.getIndicadorUso());
		}
		
		// seta os parametros que serão mostrados no relatório

		retornoControleHidrometroParametros.setId(id == null ? null : new Integer(id));
		retornoControleHidrometroParametros.setDescricao(""
				+filtrarRetornoControleHidrometroActionForm.getDescricao());
		retornoControleHidrometroParametros.setIndicadorGeracao(indicadorDeGeracao);
		retornoControleHidrometroParametros.setIndicadorGeracao(indicadorDeUso);
	

		// Fim da parte que vai mandar os parametros para o relatório

		// cria uma instância da classe do relatório
		RelatorioManterRetornoControleHidrometro relatorioManterRetornoControleHidrometro = new RelatorioManterRetornoControleHidrometro(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterRetornoControleHidrometro.addParametro("filtroRetornoControleHidrometro",
				filtroRetornoControleHidrometro);
		relatorioManterRetornoControleHidrometro.addParametro("retornoControleHidrometroParametros",
				retornoControleHidrometroParametros);

		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterRetornoControleHidrometro.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterRetornoControleHidrometro,
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
