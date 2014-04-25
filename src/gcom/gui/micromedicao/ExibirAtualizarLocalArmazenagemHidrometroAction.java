package gcom.gui.micromedicao;


import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroLocalArmazenagem;
import gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem;
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
 * @date 06/08/2008
 */
public class ExibirAtualizarLocalArmazenagemHidrometroAction extends GcomAction {

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
				.findForward("localArmazenagemHidrometroAtualizar");

		AtualizarLocalArmazenagemHidrometroActionForm atualizarLocalArmazenagemHidrometroActionForm = (AtualizarLocalArmazenagemHidrometroActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();		

		HttpSession sessao = httpServletRequest.getSession(false);

		String id = null;
		
		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null){
			id = httpServletRequest.getParameter("idRegistroAtualizacao");
		}
		else{
			id = ((HidrometroLocalArmazenagem) sessao.getAttribute("hidrometroLocalArmazenagem")).getId().toString();
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

		HidrometroLocalArmazenagem hidrometroLocalArmazenagem= new HidrometroLocalArmazenagem();
						
		if (id != null && !id.trim().equals("")) {

			FiltroHidrometroLocalArmazenagem filtroHidrometroLocalArmazenagem= new FiltroHidrometroLocalArmazenagem();
			filtroHidrometroLocalArmazenagem.adicionarParametro(
				new ParametroSimples(FiltroHidrometroLocalArmazenagem.ID, id));
			
			Collection colecaoHidrometroLocalArmazenagem = fachada.pesquisar(
					filtroHidrometroLocalArmazenagem, HidrometroLocalArmazenagem.class.getName());
			
			if (colecaoHidrometroLocalArmazenagem != null && !colecaoHidrometroLocalArmazenagem.isEmpty()) {
				hidrometroLocalArmazenagem= (HidrometroLocalArmazenagem) Util.retonarObjetoDeColecao(colecaoHidrometroLocalArmazenagem);
			}

			if (id == null || id.trim().equals("")) {

				atualizarLocalArmazenagemHidrometroActionForm
						.setId(hidrometroLocalArmazenagem
								.getId().toString());

				atualizarLocalArmazenagemHidrometroActionForm
						.setDescricao(hidrometroLocalArmazenagem
								.getDescricao());

				atualizarLocalArmazenagemHidrometroActionForm
						.setDescricaoAbreviada(hidrometroLocalArmazenagem
								.getDescricaoAbreviada());
				
				atualizarLocalArmazenagemHidrometroActionForm
						.setIndicadorUso(hidrometroLocalArmazenagem
								.getIndicadorUso());

				atualizarLocalArmazenagemHidrometroActionForm
						.setIndicadorOficina(hidrometroLocalArmazenagem
								.getIndicadorOficina());
	

			}
			
			atualizarLocalArmazenagemHidrometroActionForm.setId(hidrometroLocalArmazenagem.getId().toString());
			atualizarLocalArmazenagemHidrometroActionForm.setDescricao(hidrometroLocalArmazenagem.getDescricao());
			atualizarLocalArmazenagemHidrometroActionForm.setIndicadorUso(hidrometroLocalArmazenagem.getIndicadorUso());
			atualizarLocalArmazenagemHidrometroActionForm.setIndicadorOficina(hidrometroLocalArmazenagem.getIndicadorOficina());
			atualizarLocalArmazenagemHidrometroActionForm.setDescricaoAbreviada(hidrometroLocalArmazenagem.getDescricaoAbreviada());

			
			sessao.setAttribute("atualizarLocalArmazenagemHidrometro", hidrometroLocalArmazenagem);

			if (sessao.getAttribute("colecaoHidrometroLocalArmazenagem") != null) {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/filtrarLocalArmazenagemHidrometroAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/exibirFiltrarLocalArmazenagemHidrometroAction.do");
			}

		}
		

		return retorno;
	}
}
