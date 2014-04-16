package gcom.gui.operacional;


import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.operacional.Bacia;
import gcom.operacional.FiltroBacia;
import gcom.operacional.FiltroSistemaEsgoto;
import gcom.operacional.SistemaEsgoto;
import gcom.util.ConstantesSistema;
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
 * 
 * @author Arthur Carvalho
 * @date 21/05/2008
 */
public class ExibirAtualizarBaciaAction extends GcomAction {

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
				.findForward("baciaAtualizar");

		AtualizarBaciaActionForm atualizarBaciaActionForm = (AtualizarBaciaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();		

		HttpSession sessao = httpServletRequest.getSession(false);

		String id = null;
		
		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null){
			id = httpServletRequest.getParameter("idRegistroAtualizacao");
		}
		else{
			id = ((Bacia) sessao.getAttribute("bacia")).getId().toString();
		}
		

		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}

		if (id == null) {
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null) {
				id = (String) sessao.getAttribute("idRegistroAtualizacao");
			} else {
				id = (String) httpServletRequest.getAttribute(
						"idRegistroAtualizacao").toString();
			}
		} else {
			sessao.setAttribute("i", true);
		}

		Bacia bacia= new Bacia();
						
		if (id != null && !id.trim().equals("") && Integer.parseInt(id) > 0) {

			FiltroBacia filtroBacia= new FiltroBacia();
			filtroBacia.adicionarParametro(
				new ParametroSimples(FiltroBacia.ID, id));
			filtroBacia.adicionarCaminhoParaCarregamentoEntidade("sistemaEsgoto");
			
			Collection colecaoBacia = fachada.pesquisar(
					filtroBacia, Bacia.class.getName());
			
			if (colecaoBacia != null && !colecaoBacia.isEmpty()) {
				bacia= (Bacia) Util.retonarObjetoDeColecao(colecaoBacia);
			}

			if (id == null || id.trim().equals("")) {

				atualizarBaciaActionForm.setId(bacia
						.getId().toString());

				atualizarBaciaActionForm
						.setDescricao(bacia.getDescricao());

				atualizarBaciaActionForm
						.setIndicadorUso(bacia
								.getIndicadorUso());


			}
			
			atualizarBaciaActionForm.setId(bacia.getId().toString());
			atualizarBaciaActionForm.setSistemaEsgoto(bacia.getSistemaEsgoto().getId().toString());
			atualizarBaciaActionForm.setDescricao(bacia.getDescricao());
			atualizarBaciaActionForm.setIndicadorUso(bacia.getIndicadorUso());
			

//			Filtro de sistema esgoto
            FiltroSistemaEsgoto filtroSistemaEsgoto = new FiltroSistemaEsgoto();
            filtroSistemaEsgoto.adicionarParametro(new ParametroSimples(FiltroSistemaEsgoto.INDICADORUSO,
            		ConstantesSistema.INDICADOR_USO_ATIVO));
            
            Collection colecaoSistemaEsgoto = fachada.pesquisar(filtroSistemaEsgoto, 
            					SistemaEsgoto.class.getName());
			
            sessao.setAttribute("colecaoSistemaEsgoto", colecaoSistemaEsgoto);
			
			
			sessao.setAttribute("atualizarBacia", bacia);

			if (sessao.getAttribute("colecaoBacia") != null) {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/filtrarBaciaAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/exibirFiltrarBaciaAction.do");
			}

		}
		

		return retorno;
	}
}
