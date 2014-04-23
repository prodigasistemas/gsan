package gcom.gui.cobranca;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action utilizado para inserir uma resolução de diretoria no banco
 * 
 * [UC0217] Inserir Resolução de Diretoria Permite inserir uma
 * ResolucaoDiretoria
 * 
 * @author Rafael Corrêa
 * @since 30/03/2006
 */
public class InformarUnidadeNegocioTestemunhaAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");


		// Recupera as unidades de negocio testemunha adicionadas e removidas

		// Informa as Unidade de Negócio Testemunha, fazendo
		// algumas validações no ControladorCobranca.
//		fachada.informarUnidadeNegocioTestemunha(
//				colecaoUnidadeNegocioTestemunhaAdicionadas,
//				colecaoUnidadeNegocioTestemunhaRemovidas, this
//						.getUsuarioLogado(httpServletRequest));

		// Monta a página de sucesso de acordo com o padrão do sistema.
		montarPaginaSucesso(httpServletRequest, "Unidade(s) de Negócio Testemunha(s) informada(s) com sucesso.",
				"Informar outra Unidade de Negócio Testemunha",
				"exibirInformarUnidadeNegocioTestemunhaAction.do?menu=sim");

		return retorno;

	}

}
