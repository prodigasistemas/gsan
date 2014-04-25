package gcom.gui.relatorio.micromedicao.hidrometro;

import gcom.gui.micromedicao.hidrometro.FiltrarHidrometroDiametroActionForm;
import gcom.micromedicao.hidrometro.FiltroHidrometroDiametro;
import gcom.micromedicao.hidrometro.HidrometroDiametro;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.micromedicao.hidrometro.RelatorioManterHidrometroDiametro;
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

public class GerarRelatorioHidrometroDiametroManterAction extends
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

		FiltrarHidrometroDiametroActionForm filtrarHidrometroDiametroActionForm = (FiltrarHidrometroDiametroActionForm) actionForm;

		FiltroHidrometroDiametro filtroHidrometroDiametro = (FiltroHidrometroDiametro) sessao
				.getAttribute("filtroHidrometroDiametro");

		// Inicio da parte que vai mandar os parametros para o relatório

		HidrometroDiametro hidrometroDiametroParametros = new HidrometroDiametro();

		String id = null;

		String idHidrometroDiametroPesquisar = (String) filtrarHidrometroDiametroActionForm
				.getId();

		if (idHidrometroDiametroPesquisar != null && !idHidrometroDiametroPesquisar.equals("")) {
			id = idHidrometroDiametroPesquisar;
		}

		Short numeroOrdem = null;
		
		if (filtrarHidrometroDiametroActionForm.getNumeroOrdem() != null && !filtrarHidrometroDiametroActionForm.getNumeroOrdem().equals("")){
			
			numeroOrdem = new Short("" +
					filtrarHidrometroDiametroActionForm.getNumeroOrdem());
		}
		
		
		Short indicadorDeUso = null;

		if (filtrarHidrometroDiametroActionForm.getIndicadorUso() != null
				&& !filtrarHidrometroDiametroActionForm.getIndicadorUso().equals("")) {

			indicadorDeUso = new Short(""
					+ filtrarHidrometroDiametroActionForm.getIndicadorUso());
		}
		
		// seta os parametros que serão mostrados no relatório

		hidrometroDiametroParametros.setId(id == null ? null : new Integer(
				id));
		hidrometroDiametroParametros.setDescricao(""
				+ filtrarHidrometroDiametroActionForm.getDescricao());
		hidrometroDiametroParametros.setDescricaoAbreviada(""
				+ filtrarHidrometroDiametroActionForm.getDescricaoAbreviada());
		hidrometroDiametroParametros.setNumeroOrdem(numeroOrdem);
		hidrometroDiametroParametros.setIndicadorUso(indicadorDeUso);

		// Fim da parte que vai mandar os parametros para o relatório

		// cria uma instância da classe do relatório
		RelatorioManterHidrometroDiametro relatorioManterHidrometroDiametro = new RelatorioManterHidrometroDiametro(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterHidrometroDiametro.addParametro("filtroHidrometroDiametro",
				filtroHidrometroDiametro);
		relatorioManterHidrometroDiametro.addParametro("hidrometroDiametroParametros",
				hidrometroDiametroParametros);

		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterHidrometroDiametro.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterHidrometroDiametro,
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
