package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroMaterialUnidade;
import gcom.atendimentopublico.ordemservico.MaterialUnidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 28/07/2006
 */
public class ExibirPesquisarMaterialAction extends GcomAction {

	/**
	 * Este caso de uso efetua pesquisa do Material
	 * 
	 * [UC0384] Pesquisar Material
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 28/07/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("materialPesquisar");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltroMaterialUnidade filtroMaterialUnidade = new FiltroMaterialUnidade();

		// Verifica se os dados foram informados da tabela existem e joga numa
		// colecao

		Collection<MaterialUnidade> colecaoUnidadeMaterial = fachada.pesquisar(
				filtroMaterialUnidade, MaterialUnidade.class.getName());

		if (colecaoUnidadeMaterial == null || colecaoUnidadeMaterial.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela Material Unidade");
		}

		httpServletRequest.setAttribute("colecaoUnidadeMaterial",
				colecaoUnidadeMaterial);

		// envia uma flag que será verificado no material_resultado_pesquisa.jsp
		// para saber se irá usar o enviar dados ou o enviar dados parametros

		if (httpServletRequest
				.getParameter("caminhoRetornoTelaPesquisaMaterial") != null) {
			sessao
					.setAttribute(
							"caminhoRetornoTelaPesquisaMaterial",
							httpServletRequest
									.getParameter("caminhoRetornoTelaPesquisaMaterial"));
		}else if (httpServletRequest.getParameter("caminhoRetornoMaterialTipoServico") != null) {
			sessao.setAttribute("caminhoRetornoMaterialTipoServico",
							httpServletRequest.getParameter("caminhoRetornoMaterialTipoServico"));
		}	
		
		return retorno;
	}

}
