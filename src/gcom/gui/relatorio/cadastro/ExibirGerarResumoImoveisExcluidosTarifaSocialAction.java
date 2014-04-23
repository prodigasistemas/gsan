package gcom.gui.relatorio.cadastro;

import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Vivianne Sousa
 * @data 23/03/2011
 */
public class ExibirGerarResumoImoveisExcluidosTarifaSocialAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirGerarResumoImoveisExcluidosTarifaSocial");

		Fachada fachada = Fachada.getInstancia();
		GerarResumoImoveisExcluidosTarifaSocialActionForm form = (GerarResumoImoveisExcluidosTarifaSocialActionForm) actionForm;
		 
		if(httpServletRequest.getParameter("menu") != null){
			limparForm(form);
		}
		
		//pesquisa Gerência Regional
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		Collection<GerenciaRegional> gerenciasRegionais = fachada.pesquisar(
				filtroGerenciaRegional, GerenciaRegional.class.getName());
		httpServletRequest.setAttribute("colecaoGerenciaRegional",gerenciasRegionais);
		
		//pesquisa Unidade de Negócio
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);
		Collection<UnidadeNegocio> colecaoUnidadeNegocio = fachada.pesquisar(
				filtroUnidadeNegocio, UnidadeNegocio.class.getName());
		httpServletRequest.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
		
		//Pega os codigos que o usuario digitou para a pesquisa direta de localidade
		String codigoLocalidade = httpServletRequest.getParameter("codigoLocalidade");

		if (codigoLocalidade != null && !codigoLocalidade.trim().equals("")) {
			pesquisarLocalidade(codigoLocalidade, httpServletRequest);

		}
		
		if(form.getMotivoExclusao() != null && !form.getMotivoExclusao().equals("")){
			httpServletRequest.setAttribute("motivoExclusao", (new Integer(form.getMotivoExclusao())).intValue());
		}
		
		return retorno;
	}
	
	/**
	 * Pesquisa uma localidade informada e prepara os dados para exibição na tela
	 */
	private void pesquisarLocalidade(String idLocalidade,
			HttpServletRequest httpServletRequest) {

		Fachada fachada = Fachada.getInstancia();

		// Pesquisa a localidade na base
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, idLocalidade));

		Collection<Localidade> localidadePesquisada = fachada.pesquisar(
				filtroLocalidade, Localidade.class.getName());

		// Se nenhuma localidade for encontrada a mensagem é enviada para a página
		if (localidadePesquisada == null || localidadePesquisada.isEmpty()) {
			// [FS0001 - Verificar existência de dados]
			httpServletRequest.setAttribute("codigoLocalidade", "");
			httpServletRequest.setAttribute("descricaoLocalidade","Localidade Inexistente".toUpperCase());
		}

		// obtem o imovel pesquisado
		if (localidadePesquisada != null && !localidadePesquisada.isEmpty()) {
			Localidade localidade = localidadePesquisada.iterator().next();
			// Manda a Localidade pelo request
			httpServletRequest.setAttribute("codigoLocalidade", idLocalidade);
			httpServletRequest.setAttribute("descricaoLocalidade", localidade.getDescricao());
		}

	}
	
	private void limparForm(GerarResumoImoveisExcluidosTarifaSocialActionForm form) {

		form.setCodigoLocalidade("");
		
		form.setDescricaoLocalidade("");
		
		form.setUnidadeNegocioId("");
		
		form.setGerenciaRegionalId("");
		
	    form.setMotivoExclusao("");

	    form.setAnoMesPesquisaInicial("");
	    
	    form.setAnoMesPesquisaFinal("");

	}
}
