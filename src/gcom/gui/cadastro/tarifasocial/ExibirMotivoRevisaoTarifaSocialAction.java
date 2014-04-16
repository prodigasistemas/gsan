package gcom.gui.cadastro.tarifasocial;

import gcom.cadastro.tarifasocial.FiltroTarifaSocialExclusaoMotivo;
import gcom.cadastro.tarifasocial.FiltroTarifaSocialRevisaoMotivo;
import gcom.cadastro.tarifasocial.TarifaSocialRevisaoMotivo;
import gcom.cadastro.tarifasocial.bean.TarifaSocialHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Rafael Corrêa
 * @since 16/01/2007
 */
public class ExibirMotivoRevisaoTarifaSocialAction extends GcomAction {

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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirMotivoRevisao");

		Fachada fachada = Fachada.getInstancia();

		// Pega uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);
		
		InserirMotivoRevisaoTarifaSocialActionForm inserirMotivoRevisaoTarifaSocialActionForm = (InserirMotivoRevisaoTarifaSocialActionForm) actionForm;

		FiltroTarifaSocialRevisaoMotivo filtroTarifaSocialRevisaoMotivo = new FiltroTarifaSocialRevisaoMotivo();
		filtroTarifaSocialRevisaoMotivo
				.adicionarParametro(new ParametroSimples(
						FiltroTarifaSocialExclusaoMotivo.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroTarifaSocialRevisaoMotivo.setCampoOrderBy(FiltroTarifaSocialExclusaoMotivo.DESCRICAO);

		Collection colecaoTarifaSocialRevisaoMotivo = fachada.pesquisar(
				filtroTarifaSocialRevisaoMotivo,
				TarifaSocialRevisaoMotivo.class.getName());

		sessao.setAttribute("colecaoTarifaSocialRevisaoMotivo",
				colecaoTarifaSocialRevisaoMotivo);
		
		Collection colecaoTarifaSocialHelper = (Collection) sessao.getAttribute("colecaoTarifaSocialHelper");
		
		Iterator colecaoTarifaSocialHelperIterator = colecaoTarifaSocialHelper.iterator();

		TarifaSocialHelper tarifaSocialHelper = null;
		
		// Múltiplas Economias
		if (httpServletRequest
				.getParameter("idTarifaSocial") != null) {
			
			String idTarifaSocialDadoEconomia = httpServletRequest
					.getParameter("idTarifaSocial");
			
			while (colecaoTarifaSocialHelperIterator.hasNext()) {

				tarifaSocialHelper = (TarifaSocialHelper) colecaoTarifaSocialHelperIterator
						.next();

				if (tarifaSocialHelper.getTarifaSocialDadoEconomia()
						.getId().toString().equals(
								idTarifaSocialDadoEconomia)) {
					break;
				}

			}
			
		} else {
			// Uma Economia
			tarifaSocialHelper = (TarifaSocialHelper) colecaoTarifaSocialHelperIterator
					.next();
		}
		
		if (tarifaSocialHelper.getTarifaSocialDadoEconomia().getTarifaSocialRevisaoMotivo() != null) {
			inserirMotivoRevisaoTarifaSocialActionForm.setIdMotivoRevisao(tarifaSocialHelper.getTarifaSocialDadoEconomia().getTarifaSocialRevisaoMotivo().getId().toString());
		}
		
		sessao.setAttribute("tarifaSocialHelperMotivoRevisao", tarifaSocialHelper);

		return retorno;

	}

}
