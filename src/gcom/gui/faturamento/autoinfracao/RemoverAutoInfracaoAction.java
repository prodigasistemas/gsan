package gcom.gui.faturamento.autoinfracao;

import gcom.fachada.Fachada;
import gcom.faturamento.autoinfracao.AutosInfracao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RemoverAutoInfracaoAction extends GcomAction {

	/**
	 * 
	 * [UC0896] Manter Autos de Infração
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 05/05/2009
	 * 
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
//		 Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;

		String[] idsRegistrosRemocao = manutencaoRegistroActionForm
				.getIdRegistrosRemocao();

		for (int i = 0; i < idsRegistrosRemocao.length; i++) {
			if(fachada.validarExistenciaDeDebitosAutoInfracao(new Integer(idsRegistrosRemocao[i]))){
				throw new ActionServletException("atencao.auto_infracao_possui_debitos");
			}
		}
		
		fachada.remover(idsRegistrosRemocao, AutosInfracao.class.getName(),
				null, null);

		montarPaginaSucesso(httpServletRequest, idsRegistrosRemocao.length
				+ " Auto(s) de infração removido(s) com sucesso.",
				"Manter outro Auto de Infração ",
				"exibirFiltrarAutoInfracaoAction.do?menu=sim");

		return retorno;
	}

}
