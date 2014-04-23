package gcom.gui.cadastro.imovel;

import gcom.cadastro.tarifasocial.FiltroRendaTipo;
import gcom.cadastro.tarifasocial.FiltroTarifaSocialCartaoTipo;
import gcom.cadastro.tarifasocial.FiltroTarifaSocialExclusaoMotivo;
import gcom.cadastro.tarifasocial.RendaTipo;
import gcom.cadastro.tarifasocial.TarifaSocialCartaoTipo;
import gcom.cadastro.tarifasocial.TarifaSocialExclusaoMotivo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirFiltrarDadosTarifaSocialAction extends GcomAction{

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("filtrarImovelDadosTarifaSocial");

		Fachada fachada = Fachada.getInstancia();

		// Instância do formulário que está sendo utilizado
		//ImovelOutrosCriteriosActionForm imovelOutrosCriteriosActionForm = (ImovelOutrosCriteriosActionForm) actionForm;

		FiltroTarifaSocialExclusaoMotivo filtroTarifaSocialExclusaoMotivo = new FiltroTarifaSocialExclusaoMotivo();
		filtroTarifaSocialExclusaoMotivo.setCampoOrderBy(FiltroTarifaSocialExclusaoMotivo.DESCRICAO);
		filtroTarifaSocialExclusaoMotivo
				.adicionarParametro(new ParametroSimples(
						FiltroTarifaSocialExclusaoMotivo.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection<TarifaSocialExclusaoMotivo> colecaoExclusaoMotivo = fachada
				.pesquisar(filtroTarifaSocialExclusaoMotivo,
						TarifaSocialExclusaoMotivo.class.getName());
		 if(colecaoExclusaoMotivo == null || colecaoExclusaoMotivo.isEmpty()){
				throw new ActionServletException("atencao.naocadastrado", null,
				"Motivo de Exclusão");			
		 }		
		

		FiltroTarifaSocialCartaoTipo filtroTarifaSocialCartaoTipo = new FiltroTarifaSocialCartaoTipo();
		filtroTarifaSocialCartaoTipo.setCampoOrderBy(FiltroTarifaSocialCartaoTipo.DESCRICAO_ABREVIADA);
		filtroTarifaSocialCartaoTipo.adicionarParametro(new ParametroSimples(
				FiltroTarifaSocialCartaoTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<TarifaSocialCartaoTipo> colecaoCartaoTipo = fachada
				.pesquisar(filtroTarifaSocialCartaoTipo,
						TarifaSocialCartaoTipo.class.getName());
		 if(colecaoCartaoTipo == null || colecaoCartaoTipo.isEmpty()){
				throw new ActionServletException("atencao.naocadastrado", null,
				"Tipo do Cartão");			
		 }		
		
		
		FiltroRendaTipo filtroRendaTipo = new FiltroRendaTipo();
		filtroRendaTipo.setCampoOrderBy(FiltroRendaTipo.DESCRICAO);
		filtroRendaTipo.adicionarParametro(new ParametroSimples(
				FiltroRendaTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<RendaTipo> colecaoRendaTipo = fachada.pesquisar(
				filtroRendaTipo, RendaTipo.class.getName());
		 if(colecaoRendaTipo == null || colecaoRendaTipo.isEmpty()){
				throw new ActionServletException("atencao.naocadastrado", null,
				"Tipo de Renda");			
		 }
		
		
		httpServletRequest.setAttribute("colecaoRendaTipo", colecaoRendaTipo);
		httpServletRequest.setAttribute("colecaoExclusaoMotivo", colecaoExclusaoMotivo);
		httpServletRequest.setAttribute("colecaoCartaoTipo", colecaoCartaoTipo);

		return retorno;
	}

}
