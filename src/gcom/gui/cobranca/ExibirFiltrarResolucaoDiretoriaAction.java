package gcom.gui.cobranca;

import gcom.gui.GcomAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Permite filtrar resoluções de diretoria [UC0219] Filtrar Resolução de
 * Diretoria
 * 
 * @author Rafael Corrêa
 * @since 31/03/2006
 */
public class ExibirFiltrarResolucaoDiretoriaAction extends GcomAction {

	/**
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

		FiltrarResolucaoDiretoriaActionForm filtrarResolucaoDiretoriaActionForm = (FiltrarResolucaoDiretoriaActionForm) actionForm;

		filtrarResolucaoDiretoriaActionForm.setAtualizar("1");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		sessao.removeAttribute("filtroResolucaoDiretoria");
		
		if (httpServletRequest.getParameter("paginacao") != null) {
		
			filtrarResolucaoDiretoriaActionForm.setNumero((String) sessao.getAttribute("numero"));
			filtrarResolucaoDiretoriaActionForm.setAssunto((String) sessao.getAttribute("assunto"));
			filtrarResolucaoDiretoriaActionForm.setDataInicio((String) sessao.getAttribute("dataInicio"));
			filtrarResolucaoDiretoriaActionForm.setDataFim((String) sessao.getAttribute("dataFim"));
			filtrarResolucaoDiretoriaActionForm.setIndicadorParcelamentoUnico((String) sessao.getAttribute("indicadorParcelamentoUnico"));
			filtrarResolucaoDiretoriaActionForm.setIndicadorUtilizacaoLivre((String) sessao.getAttribute("indicadorUtilizacaoLivre"));
			filtrarResolucaoDiretoriaActionForm.setIndicadorDescontoSancoes((String) sessao.getAttribute("indicadorDescontoSancoes"));
			
			filtrarResolucaoDiretoriaActionForm.setIndicadorParcelasEmAtraso((String) sessao.getAttribute("indicadorParcelasEmAtraso"));

			if(sessao.getAttribute("idParcelasEmAtraso") != null){
				filtrarResolucaoDiretoriaActionForm.setIdParcelasEmAtraso((String) 
						sessao.getAttribute("idParcelasEmAtraso"));
			}
			
			filtrarResolucaoDiretoriaActionForm.setIndicadorParcelamentoEmAndamento((String) sessao.getAttribute("indicadorParcelamentoEmAndamento"));
			
			if(sessao.getAttribute("idParcelamentoEmAndamento") != null){
				filtrarResolucaoDiretoriaActionForm.setIdParcelamentoEmAndamento((String) 
						sessao.getAttribute("idParcelamentoEmAndamento"));
			}
			
		}

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirFiltrarResolucaoDiretoria");

		return retorno;

	}

}
