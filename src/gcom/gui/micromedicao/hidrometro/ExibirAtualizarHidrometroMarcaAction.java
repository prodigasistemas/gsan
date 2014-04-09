package gcom.gui.micromedicao.hidrometro;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroMarca;
import gcom.micromedicao.hidrometro.HidrometroMarca;
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
 * [UC0081]	MANTER MARCA HIDROMETRO
 * 
 * @author Bruno Leonardo
 * @date 18/06/2007
 */
 


public class ExibirAtualizarHidrometroMarcaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		
			ActionForward retorno = actionMapping.findForward("atualizarHidrometroMarca");	

			AtualizarHidrometroMarcaActionForm atualizarHidrometroMarcaActionForm = (AtualizarHidrometroMarcaActionForm)actionForm;
			
			HttpSession sessao = httpServletRequest.getSession(false);
			
			Fachada fachada = Fachada.getInstancia();
			
			HidrometroMarca hidrometroMarca = null;
			
			String idHidrometroMarca = null;
			
			if (httpServletRequest.getParameter("idHidrometroMarca") != null) {
				//tela do manter
				idHidrometroMarca = (String) httpServletRequest.getParameter("idHidrometroMarca");
				sessao.setAttribute("idHidrometroMarca", idHidrometroMarca);
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirManterHidrometroMarcaAction.do");
			} else if (sessao.getAttribute("idHidrometroMarca") != null) {
				//tela do filtrar
				idHidrometroMarca = (String) sessao.getAttribute("idHidrometroMarca");
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarHidrometroMarcaAction.do");
			}else if(httpServletRequest.getParameter("idRegistroInseridoAtualizar") != null){
				//link na tela de sucesso do inserir sistema esgoto
				idHidrometroMarca = (String)httpServletRequest.getParameter("idRegistroInseridoAtualizar");
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarHidrometroMarcaAction.do?menu=sim");
			}
			
			if (idHidrometroMarca == null){
				
				if (sessao.getAttribute("idRegistroAtualizar") != null){
					hidrometroMarca = (HidrometroMarca) sessao.getAttribute("idRegistroAtualizar");
				}else{
					idHidrometroMarca = (String) httpServletRequest.getParameter("idHidrometroMarca").toString();
				}
			}
			
			//------Inicio da parte que verifica se vem da página de sistema_esgoto_manter.jsp						
			if (hidrometroMarca == null){
			
				if (idHidrometroMarca != null && !idHidrometroMarca.equals("")) {
	
					FiltroHidrometroMarca filtroHidrometroMarca = new FiltroHidrometroMarca();
					
					filtroHidrometroMarca.adicionarParametro(new ParametroSimples(FiltroHidrometroMarca.ID, idHidrometroMarca));
	
					Collection colecaoHidrometroMarca = fachada.pesquisar(filtroHidrometroMarca, HidrometroMarca.class.getName());

					if (colecaoHidrometroMarca != null && !colecaoHidrometroMarca.isEmpty()) {
						
						hidrometroMarca = (HidrometroMarca) Util.retonarObjetoDeColecao(colecaoHidrometroMarca);
						
					}
				}
			}
			
			//  ------  A marca do Hidrometro foi encontrada
			atualizarHidrometroMarcaActionForm.setDescricaoMarcaHidrometro( hidrometroMarca.getDescricao() );
			atualizarHidrometroMarcaActionForm.setDescricaoAbreviada( hidrometroMarca.getDescricaoAbreviada() );
			atualizarHidrometroMarcaActionForm.setIndicadorUso( hidrometroMarca.getIndicadorUso().toString() );
			atualizarHidrometroMarcaActionForm.setMarcaHidrometro( hidrometroMarca.getCodigoHidrometroMarca() );
			atualizarHidrometroMarcaActionForm.setValidadeRevisao( hidrometroMarca.getIntervaloDiasRevisao() + "" );
			sessao.setAttribute("hidrometroMarca", hidrometroMarca);
			
			httpServletRequest.setAttribute("idHidrometroMarca", idHidrometroMarca);
			
			// ------ Fim da parte que verifica se vem da página de hidrometro_marca_manter.jsp			
			
			return retorno;
	}
}
