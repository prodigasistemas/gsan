package gcom.gui.cobranca;

import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0902] Analisar Metas do Ciclo
 * 
 * @author Genival Barbosa
 * 
 */

public class ExibirAnalisarMetasCicloAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("analisarMetasCiclo");

		AnalisarMetasCicloActionForm form = (AnalisarMetasCicloActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();

//		 Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		String voltaFiltro = httpServletRequest.getParameter("voltar");
		if (voltaFiltro != null && !voltaFiltro.trim().equals("")) {
			sessao.removeAttribute("idCicloMeta");
			sessao.removeAttribute("cicloMeta");
			sessao.removeAttribute("helpers");
			form.setAnoMesCobranca(null);
			form.setIdCobrancaAcao(null);
		}

		

			String idCicloMeta = "";

			if (httpServletRequest.getParameter("idRegistroAtualizacao") != null) {
				idCicloMeta = httpServletRequest
						.getParameter("idRegistroAtualizacao");

				sessao.setAttribute("idCicloMeta",
						idCicloMeta);
			} else {
				if (sessao.getAttribute("idRegistroAtualizacao") != null) {
					idCicloMeta = (String) sessao
							.getAttribute("idRegistroAtualizacao");
				} else {
					idCicloMeta = (String) sessao
							.getAttribute("idCicloMeta");
				}
			}
			
			form.setIdCicloMeta(idCicloMeta);
			

			FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
			filtroCobrancaAcao.adicionarParametro(new ParametroSimples(FiltroCobrancaAcao.INDICADOR_USO, 
				ConstantesSistema.SIM));
			filtroCobrancaAcao.setCampoOrderBy(FiltroCobrancaAcao.DESCRICAO);

			Collection colecaoCobrancaAcao = fachada.pesquisar(
					filtroCobrancaAcao, CobrancaAcao.class.getName());

			sessao.setAttribute("colecaoCobrancaAcao", colecaoCobrancaAcao);

		
		
		return retorno;

	}

}
