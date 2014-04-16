package gcom.gui.atendimentopublico.ligacaoesgoto;

import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0789] Filtrar Situacao de Ligação de Esgoto
 * 
 * @author Bruno Barros
 *
 * @date 14/05/2008
 */
public class ExibirFiltrarLigacaoEsgotoSituacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirFiltrarLigacaoEsgotoSituacao");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarLigacaoEsgotoSituacaoActionForm filtrarLigacaoEsgotoSituacaoActionForm = (FiltrarLigacaoEsgotoSituacaoActionForm) actionForm;
		
		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			filtrarLigacaoEsgotoSituacaoActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());	
		}
		
		if(filtrarLigacaoEsgotoSituacaoActionForm.getIndicadorAtualizar()==null){
			filtrarLigacaoEsgotoSituacaoActionForm.setIndicadorAtualizar("1");
		}
        
        if (httpServletRequest.getParameter("desfazer") != null && 
        	httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {        	
        	filtrarLigacaoEsgotoSituacaoActionForm.setCodigo("");
        	filtrarLigacaoEsgotoSituacaoActionForm.setDescricao("");
        	filtrarLigacaoEsgotoSituacaoActionForm.setDescricaoAbreviada("");
        	filtrarLigacaoEsgotoSituacaoActionForm.setConsumoMinimoFaturamento("");
        	filtrarLigacaoEsgotoSituacaoActionForm.setIndicadorFaturamento("");
        	filtrarLigacaoEsgotoSituacaoActionForm.setIndicadorExistenciaLigacao("");
        	filtrarLigacaoEsgotoSituacaoActionForm.setIndicadorExistenciaRede("");
        	filtrarLigacaoEsgotoSituacaoActionForm.setIndicadorUso("");       	
        }		
		
		return retorno;
	}	
}
