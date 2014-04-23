package gcom.gui.cadastro;


import gcom.cadastro.imovel.FiltroFonteAbastecimento;
import gcom.cadastro.imovel.FonteAbastecimento;
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
 * 
 * @author Arthur Carvalho
 * @date 14/08/2008
 */
public class ExibirAtualizarFonteAbastecimentoAction extends GcomAction {

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
				.findForward("fonteAbastecimentoAtualizar");

		AtualizarFonteAbastecimentoActionForm atualizarFonteAbastecimentoActionForm = (AtualizarFonteAbastecimentoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();		

		HttpSession sessao = httpServletRequest.getSession(false);

		String id = null;
		
		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null){
			id = httpServletRequest.getParameter("idRegistroAtualizacao");
		}
		else{
			id = ((FonteAbastecimento) sessao.getAttribute("fonteAbastecimento")).getId().toString();
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

		FonteAbastecimento fonteAbastecimento = new FonteAbastecimento();
						
		if (id != null && !id.trim().equals("")) {

			FiltroFonteAbastecimento filtroFonteAbastecimento = new FiltroFonteAbastecimento();
			filtroFonteAbastecimento.adicionarParametro(
				new ParametroSimples(FiltroFonteAbastecimento.ID, id));
			
			Collection colecaoFonteAbastecimento = fachada.pesquisar(
					filtroFonteAbastecimento, FonteAbastecimento.class.getName());
			
			if (colecaoFonteAbastecimento != null && !colecaoFonteAbastecimento.isEmpty()) {
				fonteAbastecimento= (FonteAbastecimento) Util.retonarObjetoDeColecao(colecaoFonteAbastecimento);
			}

			if (id == null || id.trim().equals("")) {

				atualizarFonteAbastecimentoActionForm.setCodigo(fonteAbastecimento
						.getId().toString());

				atualizarFonteAbastecimentoActionForm
						.setDescricao(fonteAbastecimento.getDescricao());

				atualizarFonteAbastecimentoActionForm
						.setDescricaoAbreviada(fonteAbastecimento.getDescricaoAbreviada());
				
				atualizarFonteAbastecimentoActionForm
						.setIndicadorUso(fonteAbastecimento.getIndicadorUso());
				
				atualizarFonteAbastecimentoActionForm
						.setIndicadorCalcularVolumeFixo(fonteAbastecimento.getIndicadorCalcularVolumeFixo());
				
				atualizarFonteAbastecimentoActionForm
					.setIndicadorPermitePoco(fonteAbastecimento.getIndicadorPermitePoco() );

			}
			
			atualizarFonteAbastecimentoActionForm.setCodigo(fonteAbastecimento.getId().toString());
			atualizarFonteAbastecimentoActionForm.setDescricao(fonteAbastecimento.getDescricao());
			atualizarFonteAbastecimentoActionForm.setIndicadorUso(fonteAbastecimento.getIndicadorUso());
			atualizarFonteAbastecimentoActionForm.setIndicadorCalcularVolumeFixo(fonteAbastecimento.getIndicadorCalcularVolumeFixo());
			atualizarFonteAbastecimentoActionForm.setDescricaoAbreviada(fonteAbastecimento.getDescricaoAbreviada());
			atualizarFonteAbastecimentoActionForm.setIndicadorPermitePoco(fonteAbastecimento.getIndicadorPermitePoco() );
			
			
			sessao.setAttribute("atualizarFonteAbastecimento", fonteAbastecimento);

			if (sessao.getAttribute("colecaoFonteAbastecimento") != null) {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/filtrarFonteAbastecimentoAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/exibirFiltrarFonteAbastecimentoAction.do");
			}

		}
		

		return retorno;
	}
}
