package gcom.gui.operacional;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroSetorAbastecimento;
import gcom.operacional.FiltroZonaAbastecimento;
import gcom.operacional.SetorAbastecimento;
import gcom.operacional.ZonaAbastecimento;
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
 * [UC0780] Pesquisar Distrito Operacional
 * 
 * @date 05/05/2008
 * @author Arthur Carvalho
 */
public class ExibirPesquisarDistritoOperacionalAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirPesquisarDistritoOperacional");

		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		PesquisarDistritoOperacionalActionForm pesquisarDistritoOperacionalActionForm = (PesquisarDistritoOperacionalActionForm) actionForm;
		
		if (httpServletRequest.getParameter("limparForm") != null && !httpServletRequest.getParameter("limparForm").equals("")) {
			pesquisarDistritoOperacionalActionForm.setNomeDistritoOperacional("");
			pesquisarDistritoOperacionalActionForm.setSetorAbastecimento("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
			pesquisarDistritoOperacionalActionForm.setZonaAbastecimento("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
			pesquisarDistritoOperacionalActionForm.setIndicadorUso("");
		}
		
		//[FS0001] Verificar Existencia de dados - Pesquisa de Sistema Abastecimento
		FiltroSetorAbastecimento filtroSetorAbastecimento = new FiltroSetorAbastecimento();
		
		filtroSetorAbastecimento.adicionarParametro(new ParametroSimples(FiltroSetorAbastecimento.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection colecaoSetorAbastecimento = (Collection)
			fachada.pesquisar(filtroSetorAbastecimento, SetorAbastecimento.class.getName());
		
		if (colecaoSetorAbastecimento == null || colecaoSetorAbastecimento.isEmpty()) {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "Setor Abastecimento");
		}
		
		//[FS0001] Verificar Existencia de dados - Pesquisa de Zona Abastecimento
		FiltroZonaAbastecimento filtroZonaAbastecimento = new FiltroZonaAbastecimento();
		
		filtroZonaAbastecimento.adicionarParametro(new ParametroSimples(FiltroZonaAbastecimento.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection colecaoZonaAbastecimento = (Collection)
			fachada.pesquisar(filtroZonaAbastecimento, ZonaAbastecimento.class.getName());
		
		if (colecaoZonaAbastecimento == null || colecaoZonaAbastecimento.isEmpty()) {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "Zona Abastecimento");
		}
		
		httpServletRequest.setAttribute("colecaoSetorAbastecimento", colecaoSetorAbastecimento);
		httpServletRequest.setAttribute("colecaoZonaAbastecimento", colecaoZonaAbastecimento);
		
		if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisaQuadraFace") != null) {
			
			sessao.setAttribute("caminhoRetornoTelaPesquisaQuadraFace",
			httpServletRequest.getParameter("caminhoRetornoTelaPesquisaQuadraFace"));

		}

		return retorno;

	}

}
