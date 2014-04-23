package gcom.gui.relatorio.atendimentopublico.ordemservico;

import gcom.gui.GcomAction;
import gcom.gui.atendimentopublico.ordemservico.FiltrarOrdemProcessoRepavimentacaoActionForm;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action responsável pela exibição do relatório de ordem servico repavimentacao
 * 
 * @author Rafael Pinto
 * @created 30/03/2011
 */
public class ExibirImprimirRelacaoOrdemServicoRepavimentacaoAction extends GcomAction {

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
		retorno = actionMapping.findForward("exibirImprimirRelacao");
		
		FiltrarOrdemProcessoRepavimentacaoActionForm form = (FiltrarOrdemProcessoRepavimentacaoActionForm) actionForm;
		
		form.setIndicadorOsObservacaoRetorno(ConstantesSistema.NAO.toString());

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}



}
