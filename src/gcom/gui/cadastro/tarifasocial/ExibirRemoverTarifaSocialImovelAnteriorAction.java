package gcom.gui.cadastro.tarifasocial;

import gcom.cadastro.tarifasocial.FiltroTarifaSocialExclusaoMotivo;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.cadastro.tarifasocial.TarifaSocialExclusaoMotivo;
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
 * @author Rafael Corrêa
 * @since 16/01/2007
 */
public class ExibirRemoverTarifaSocialImovelAnteriorAction extends GcomAction {

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
		ActionForward retorno = actionMapping
				.findForward("remover");
		
		Fachada fachada = Fachada.getInstancia();
		
		// Pega uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);
		
		RemoverTarifaSocialImovelAnteriorActionForm removerTarifaSocialImovelAnteriorActionForm = (RemoverTarifaSocialImovelAnteriorActionForm) actionForm;
		
		FiltroTarifaSocialExclusaoMotivo filtroTarifaSocialExclusaoMotivo = new FiltroTarifaSocialExclusaoMotivo();
		filtroTarifaSocialExclusaoMotivo
				.adicionarParametro(new ParametroSimples(
						FiltroTarifaSocialExclusaoMotivo.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroTarifaSocialExclusaoMotivo.setCampoOrderBy(FiltroTarifaSocialExclusaoMotivo.DESCRICAO);
		
		Collection colecaoTarifaSocialExclusaoMotivo = fachada.pesquisar(
				filtroTarifaSocialExclusaoMotivo,
				TarifaSocialExclusaoMotivo.class.getName());
		
		sessao.setAttribute("colecaoTarifaSocialExclusaoMotivo", colecaoTarifaSocialExclusaoMotivo);
		
		TarifaSocialDadoEconomia tarifaSocialDadoEconomia = (TarifaSocialDadoEconomia) sessao
				.getAttribute("tarifaSocialDadoEconomiaImovelAnterior");
		
		removerTarifaSocialImovelAnteriorActionForm.setIdImovel(tarifaSocialDadoEconomia.getImovel().getId().toString());
		removerTarifaSocialImovelAnteriorActionForm.setMotivoRevisao(tarifaSocialDadoEconomia.getTarifaSocialRevisaoMotivo().getDescricao());
		
		// Verifica se é um recadastramento se for seta um atributo no request
		// para ser verificado posteriormente
		String manter = httpServletRequest.getParameter("manter");
		
		if (manter != null && !manter.trim().equals("")) {
			httpServletRequest.setAttribute("manter", true);
		}
		
		return retorno;

	}

}
