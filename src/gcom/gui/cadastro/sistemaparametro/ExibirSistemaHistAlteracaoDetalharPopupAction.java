package gcom.gui.cadastro.sistemaparametro;

import gcom.cadastro.sistemaparametro.FiltroSistemaAlteracaoHistorico;
import gcom.cadastro.sistemaparametro.SistemaAlteracaoHistorico;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC__?__] DETALHAR SISTEMA ALTERCAO HISTORICO
 * 
 * @author Kassia Albuquerque
 * @created 30/11/2006
 */


public class ExibirSistemaHistAlteracaoDetalharPopupAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("sistemaHistAlteracaoDetalharPopup");
		
		SistemaHistoricoAlteracaoDetalharPopupActionForm sistemaHistoricoAlteracaoDetalharPopupActionForm = 
			(SistemaHistoricoAlteracaoDetalharPopupActionForm)actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		FiltroSistemaAlteracaoHistorico filtroSistemaAlteracaoHistorico = new FiltroSistemaAlteracaoHistorico();
		

		SistemaAlteracaoHistorico sistemaAlteracaoHistorico = null;
		
		String idSistemaAlteracaoHistorico = null;
		
		if (httpServletRequest.getParameter("idSistemaAlteracaoHistorico") != null) {
			//tela do manter
			idSistemaAlteracaoHistorico = (String) httpServletRequest.getParameter("idSistemaAlteracaoHistorico");
			sessao.setAttribute("idSistemaAlteracaoHistorico", idSistemaAlteracaoHistorico);
		} 
		
		if (sistemaAlteracaoHistorico == null){
			
			if (idSistemaAlteracaoHistorico != null && !idSistemaAlteracaoHistorico.equals("")) {

				filtroSistemaAlteracaoHistorico.adicionarCaminhoParaCarregamentoEntidade("funcionalidade");
				
				filtroSistemaAlteracaoHistorico.adicionarParametro(new ParametroSimples(
						FiltroSistemaAlteracaoHistorico.ID, idSistemaAlteracaoHistorico));

				Collection colecaoSistemaAlteracaoHistorico = fachada.pesquisar(filtroSistemaAlteracaoHistorico, SistemaAlteracaoHistorico.class.getName());
				
				httpServletRequest.setAttribute("colecaoSistemaAlteracaoHistorico", colecaoSistemaAlteracaoHistorico);

				if (colecaoSistemaAlteracaoHistorico != null && !colecaoSistemaAlteracaoHistorico.isEmpty()) {
					
					sistemaAlteracaoHistorico = (SistemaAlteracaoHistorico) Util.retonarObjetoDeColecao(colecaoSistemaAlteracaoHistorico);
					
				}
			}
		}
		
				
						
			//  ------  O sistema alteracao historico foi encontrado
			
			sistemaHistoricoAlteracaoDetalharPopupActionForm.setTituloAlteracao(sistemaAlteracaoHistorico.getNome());

			sistemaHistoricoAlteracaoDetalharPopupActionForm.setDescricaoAlteracao(sistemaAlteracaoHistorico.getDescricao());

			sistemaHistoricoAlteracaoDetalharPopupActionForm.setDescricaoFuncionalidade(""+sistemaAlteracaoHistorico.getFuncionalidade().getDescricao());
			
			sistemaHistoricoAlteracaoDetalharPopupActionForm.setIdFuncionalidade(""+sistemaAlteracaoHistorico.getFuncionalidade().getId());

			sistemaHistoricoAlteracaoDetalharPopupActionForm.setDataAlteracao(Util.formatarData(sistemaAlteracaoHistorico.getData()));

			
			sessao.setAttribute("sistemaAlteracaoHistorico", sistemaAlteracaoHistorico);
			
			// ------ Fim da parte que verifica se vem da página de material_manter.jsp
		
			
			return retorno;
	}
}
