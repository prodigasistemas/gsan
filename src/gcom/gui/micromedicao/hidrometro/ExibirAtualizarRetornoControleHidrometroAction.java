package gcom.gui.micromedicao.hidrometro;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroRetornoControleHidrometro;
import gcom.micromedicao.hidrometro.RetornoControleHidrometro;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;


/**
 *  
 * @author Wallace Thierre
 * @date 04/08/2010
 * 
 */

public class ExibirAtualizarRetornoControleHidrometroAction extends GcomAction{
	
	/**
	 * Método responsavel por responder a requisicao
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
		
		ActionForward retorno = actionMapping
				.findForward("RetornoControleHidrometroAtualizar");
		
		AtualizarRetornoControleHidrometroActionForm atualizarRetornoControleHidrometroActionForm = 
				(AtualizarRetornoControleHidrometroActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String id = httpServletRequest.getParameter("idRegistroAtualizacao");
		//String id = (String) sessao.getAttribute("idRegistroAtualizacao");
		
		if (httpServletRequest.getParameter("manter") != null){
			sessao.setAttribute("manter", true);			
		} else if (httpServletRequest.getParameter("filtrar") != null){
			sessao.removeAttribute("manter");
		}
		
		if (id == null ){
			if(httpServletRequest.getAttribute("idRegistroAtualizacao") == null){
				id = (String) sessao.getAttribute("idRegistroAtualizacao");
			} else {
				id = (String) httpServletRequest.getAttribute(
				"idRegistroAtualizacao").toString();
			}
		} else {
			sessao.setAttribute("i", true);
		}
		
		if(id != null && !id.trim().equals("")){
			
			FiltroRetornoControleHidrometro filtroRetornoControleHidrometro = new FiltroRetornoControleHidrometro();
			filtroRetornoControleHidrometro.adicionarParametro
					(new ParametroSimples(FiltroRetornoControleHidrometro.ID, id));
			Collection colecaoRetornoControleHidrometro = fachada.pesquisar
				(filtroRetornoControleHidrometro, RetornoControleHidrometro.class.getName());
			
			if (colecaoRetornoControleHidrometro != null && 
						!colecaoRetornoControleHidrometro.isEmpty()){
				
				RetornoControleHidrometro retornoControleHidrometro = (RetornoControleHidrometro) Util
						.retonarObjetoDeColecao(colecaoRetornoControleHidrometro);		
				
				atualizarRetornoControleHidrometroActionForm.setId(retornoControleHidrometro.getId().toString());
				
				atualizarRetornoControleHidrometroActionForm.setDescricao(retornoControleHidrometro.getDescricao());
				
				atualizarRetornoControleHidrometroActionForm.setIndicadorUso(""
						+ retornoControleHidrometro.getIndicadorUso());
				
				atualizarRetornoControleHidrometroActionForm.setIndicadorGeracao("" 
						+ retornoControleHidrometro.getIndicadorGeracao());
				
				sessao.setAttribute("atualizarRetornoControleHidrometro", retornoControleHidrometro);
			}			
			
			if(sessao.getAttribute("colecaoRetornoControleHidrometro") != null){
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/filtrarRetornoControleHidrometroAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarRetornoControleHidrometroAction.do");
			}			
			
		}
		
		return retorno;
	}
		
	

}
