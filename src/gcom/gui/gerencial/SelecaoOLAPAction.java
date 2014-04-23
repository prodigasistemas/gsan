package gcom.gui.gerencial;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Ivan Sérgio
 * @created 10/11/2008
 */
public class SelecaoOLAPAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("selecaoOLAP");
		
		// Recupera os Parametros do Sistema
		SistemaParametro parametros = Fachada.getInstancia().pesquisarParametrosDoSistema();
		
		httpServletRequest.setAttribute("ipServidor", parametros.getIpServidorModuloGerencial());
		httpServletRequest.setAttribute("tipoRelatorio", httpServletRequest.getParameter("tipoRelatorio"));
		httpServletRequest.setAttribute("selecionaAno", httpServletRequest.getParameter("selecionaAno"));
		httpServletRequest.setAttribute("empresa", parametros.getNomeAbreviadoEmpresa());
		httpServletRequest.setAttribute("logoMarcaEmpresa", parametros.getImagemLogomarca());

		return retorno;
	}
}
