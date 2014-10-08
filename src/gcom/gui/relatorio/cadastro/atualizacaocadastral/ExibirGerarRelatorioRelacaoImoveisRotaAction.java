package gcom.gui.relatorio.cadastro.atualizacaocadastral;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.gui.cadastro.atualizacaocadastral.ExibirFiltrarAlteracaoAtualizacaoCadastralAction;
import gcom.gui.cadastro.atualizacaocadastral.FilterClassParameters;
import gcom.gui.cadastro.atualizacaocadastral.FiltrarAlteracaoAtualizacaoCadastralActionForm;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.DescriptorEntity;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.lang.reflect.Method;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jboss.logging.Logger;

public class ExibirGerarRelatorioRelacaoImoveisRotaAction extends GcomAction {
	
	private static Logger logger = Logger.getLogger(ExibirGerarRelatorioRelacaoImoveisRotaAction.class);
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("exibirGerarRelatorioRelacaoImoveisRota");
		RelatorioRelacaoImoveisRotaActionForm form = (RelatorioRelacaoImoveisRotaActionForm) actionForm;
		
		try {
			if (StringUtils.isNotEmpty(httpServletRequest.getParameter("filterClass"))){
				preencherCampoDescricao(form, httpServletRequest);
			}

			HttpSession sessao = httpServletRequest.getSession(false);
		} catch (Exception e) {
			logger.error("Erro ao filtrar Cadastro", e);
		}
		
		return retorno;
	}
	
	private void preencherCampoDescricao(RelatorioRelacaoImoveisRotaActionForm form, HttpServletRequest httpServletRequest)
			throws Exception {
		
		String filterClass = "gcom.cadastro.localidade."+ httpServletRequest.getParameter("filterClass");
		
		FilterClassParameters filter = null;
		String fieldName = null;
		String fieldLocalidade = httpServletRequest.getParameter("fieldLocalidade");
		if (filterClass.contains("FiltroLocalidade")) {
			fieldName = fieldLocalidade;
			filter = buildFiltroLocalidade(form, filterClass, fieldLocalidade);			
		} else if (filterClass.contains("FiltroSetorComercial")){
			fieldName = httpServletRequest.getParameter("fieldSetorComercial");
			filter = buildFiltroSetorComercial(form, httpServletRequest, filterClass, fieldName, fieldLocalidade);
		}
		
		DescriptorEntity entidade = pesquisarEntidade(filter);

		Method setNome = RelatorioRelacaoImoveisRotaActionForm.class.getMethod("setNome" + fieldName, String.class);
		if (entidade == null) {
			setNome.invoke(form, filter.getInvalidMessage());
			httpServletRequest.setAttribute("cor" + filter.getFieldName(), "#FF0000");
		} else {
			setNome.invoke(form, entidade.getDescricao());
			httpServletRequest.setAttribute("cor" + filter.getFieldName(), "#000000");
		}
	}
	
	private FilterClassParameters buildFiltroLocalidade(RelatorioRelacaoImoveisRotaActionForm form, String filterClass, String fieldLocalidade)
			throws Exception {
		Filtro filtro = (Filtro) Class.forName(filterClass).newInstance();
		FilterClassParameters filter;
		filter = new FilterClassParameters(filtro, new Localidade(), "Localidade inexistente", fieldLocalidade);
		filtro.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, recuperaValorCampo(form, "Id" + fieldLocalidade)));
		filtro.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		return filter;
	}
	
	private FilterClassParameters buildFiltroSetorComercial(RelatorioRelacaoImoveisRotaActionForm form, HttpServletRequest httpServletRequest,
			String filterClass, String fieldName, String fieldLocalidade) throws Exception {
		Filtro filtro = (Filtro) Class.forName(filterClass).newInstance();
		FilterClassParameters filter;
		filter = new FilterClassParameters(filtro, new SetorComercial(), "Setor comercial inexistente", fieldName);
		filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, (String) recuperaValorCampo(form, "Cd"+ fieldName)));
		filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, new Integer(recuperaValorCampo(form, "Id"+ fieldLocalidade))));
		httpServletRequest.setAttribute("cor" + fieldLocalidade, "#000000");
		return filter;
	}
	
	private DescriptorEntity pesquisarEntidade(FilterClassParameters parameters) {
		Collection colecaoPesquisa = Fachada.getInstancia().pesquisar(parameters.getFilter(), parameters.getEntity().getClass().getName());

		if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
			return null;
		} else {
			return (DescriptorEntity) Util.retonarObjetoDeColecao(colecaoPesquisa);
		}
	}
	
	private String recuperaValorCampo(RelatorioRelacaoImoveisRotaActionForm form, String fieldName) throws Exception{
		Method getMethod = RelatorioRelacaoImoveisRotaActionForm.class.getMethod("get" + fieldName);
		return (String) getMethod.invoke(form);
		
	}
}
