package gcom.gui.relatorio.cadastro;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.gui.cadastro.FiltrarEmpresaActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.RelatorioManterEmpresa;
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
 * @author Arthur Carvalho
 * @version 1.0
 */

public class GerarRelatorioEmpresaManterAction extends
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

		FiltrarEmpresaActionForm filtrarEmpresaActionForm = (FiltrarEmpresaActionForm) actionForm;

		FiltroEmpresa filtroEmpresa= (FiltroEmpresa) sessao
				.getAttribute("filtroEmpresa");

		// Inicio da parte que vai mandar os parametros para o relatório

		Empresa empresaParametros = new Empresa();

		String id = null;

		Short indicadorEmpresaPrincipal = null;

		if (filtrarEmpresaActionForm.getIndicadorEmpresaPrincipal() != null
				&& !filtrarEmpresaActionForm.getIndicadorEmpresaPrincipal().equals("")) {

			indicadorEmpresaPrincipal = new Short(""
					+ filtrarEmpresaActionForm.getIndicadorEmpresaPrincipal());
		}

		Short indicadordeUso = null;
		
		if(filtrarEmpresaActionForm.getIndicadorUso()!= null && !filtrarEmpresaActionForm.getIndicadorUso().equals("")){
			
			indicadordeUso = new Short ("" + filtrarEmpresaActionForm.getIndicadorUso());
		}
		
		
		// seta os parametros que serão mostrados no relatório

		empresaParametros.setId(id == null ? null : new Integer(
				id));
		empresaParametros.setDescricao(""
				+ filtrarEmpresaActionForm.getDescricao());
		empresaParametros.setIndicadorEmpresaPrincipal(indicadorEmpresaPrincipal);
		empresaParametros.setIndicadorUso(indicadordeUso);
		
		// Fim da parte que vai mandar os parametros para o relatório

		// cria uma instância da classe do relatório
		RelatorioManterEmpresa relatorioManterEmpresa= new RelatorioManterEmpresa(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		relatorioManterEmpresa.addParametro("filtroEmpresa",
				filtroEmpresa);
		relatorioManterEmpresa.addParametro("empresaParametros",
				empresaParametros);

		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterEmpresa.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterEmpresa,
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
