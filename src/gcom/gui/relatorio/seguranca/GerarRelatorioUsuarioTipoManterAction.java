package gcom.gui.relatorio.seguranca;

import gcom.gui.seguranca.FiltrarUsuarioTipoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.seguranca.RelatorioManterUsuarioTipo;
import gcom.seguranca.acesso.usuario.FiltroUsuarioTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioTipo;
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

public class GerarRelatorioUsuarioTipoManterAction extends
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

		FiltrarUsuarioTipoActionForm filtrarUsuarioTipoActionForm = (FiltrarUsuarioTipoActionForm) actionForm;

		FiltroUsuarioTipo filtroUsuarioTipo = (FiltroUsuarioTipo) sessao
				.getAttribute("filtroUsuarioTipo");

		// Inicio da parte que vai mandar os parametros para o relatório

		UsuarioTipo usuarioTipoParametros = new UsuarioTipo();

		String id = null;

		String idUsuarioTipoPesquisar = (String) filtrarUsuarioTipoActionForm.getId();

		if (idUsuarioTipoPesquisar != null && !idUsuarioTipoPesquisar.equals("")) {
			id = idUsuarioTipoPesquisar;
		}
		
		Short indicadorUso = null;
		
		if(filtrarUsuarioTipoActionForm.getIndicadorUso()!= null && !filtrarUsuarioTipoActionForm.getIndicadorUso().equals("")){
			
			indicadorUso = new Short ("" + filtrarUsuarioTipoActionForm.getIndicadorUso());
		}
		
		Short indicadorFuncionario = null;
		
		if(filtrarUsuarioTipoActionForm.getIndicadorFuncionario() != null && !filtrarUsuarioTipoActionForm.getIndicadorFuncionario().equals("")){
			
			indicadorFuncionario = new Short ("" + filtrarUsuarioTipoActionForm.getIndicadorFuncionario());
		}
		
		// seta os parametros que serão mostrados no relatório

		usuarioTipoParametros.setId(id == null ? null : new Integer(
				id));
		usuarioTipoParametros.setDescricao(filtrarUsuarioTipoActionForm.getDescricao());
		usuarioTipoParametros.setIndicadorUso(indicadorUso);
		usuarioTipoParametros.setIndicadorFuncionario(indicadorFuncionario);
		// Fim da parte que vai mandar os parametros para o relatório

		// cria uma instância da classe do relatório
	
		RelatorioManterUsuarioTipo relatorioManterUsuarioTipo = new RelatorioManterUsuarioTipo(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterUsuarioTipo.addParametro("filtroUsuarioTipo",
				filtroUsuarioTipo);
		relatorioManterUsuarioTipo.addParametro("usuarioTipoParametros",
				usuarioTipoParametros);

		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterUsuarioTipo.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterUsuarioTipo,
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
