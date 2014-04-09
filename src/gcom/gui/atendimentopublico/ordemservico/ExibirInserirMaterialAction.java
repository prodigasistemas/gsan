package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroMaterialUnidade;
import gcom.atendimentopublico.ordemservico.MaterialUnidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rõmulo Aurélio
 * @date 31/07/2006
 */
public class ExibirInserirMaterialAction extends GcomAction {

	/**
	 * Este caso de uso efetua pesquisa do Material
	 * 
	 * [UC0381] Inserir Material
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 31/07/2006
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

		ActionForward retorno = actionMapping.findForward("materialInserir");

		Fachada fachada = Fachada.getInstancia();

		FiltroMaterialUnidade filtroMaterialUnidade = new FiltroMaterialUnidade();

		// Verifica se os dados foram informados da tabela existem e joga numa
		// colecao

		Collection<MaterialUnidade> colecaoUnidadeMaterial = fachada.pesquisar(
				filtroMaterialUnidade, MaterialUnidade.class.getName());

		if (colecaoUnidadeMaterial == null || colecaoUnidadeMaterial.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Material Unidade");
		}

		httpServletRequest.setAttribute("colecaoUnidadeMaterial",
				colecaoUnidadeMaterial);

		return retorno;
	}

}
