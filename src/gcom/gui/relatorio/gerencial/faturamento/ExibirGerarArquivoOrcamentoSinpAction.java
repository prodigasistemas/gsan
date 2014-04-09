package gcom.gui.relatorio.gerencial.faturamento;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC00750] Gerar Arquivo texto para orçamento e SINP
 * 
 * @author Sávio Luiz
 *
 * @date 12/02/2008
 */
public class ExibirGerarArquivoOrcamentoSinpAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("gerarArquivoOrcamentoSinp");
		
		
		return retorno;
	}	

}
