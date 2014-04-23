package gcom.gui.relatorio.gerencial.faturamento;

import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC00722] Gerar Relatório para Quadro de Metas Acumulado
 * 
 * @author Bruno Barros
 *
 * @date 19/12/2007
 */
public class EmissaoRelatorioQuadroMetasAcumuladoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirGerarQuadroMetasAcumulado");

		EmissaoRelatorioQuadroMetasAcumuladoActionForm form = 
			(EmissaoRelatorioQuadroMetasAcumuladoActionForm) actionForm;

		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");

		// Pesquisar Localidade
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				objetoConsulta.trim().equals("2")) {

			// Faz a consulta de Localidade
			this.pesquisarLocalidade(form);
		}
		
		
		this.pesquisarGerenciaRegional(httpServletRequest);
		this.pesquisarUnidadeNegocio(httpServletRequest,form);
		
		//Seta os request´s encontrados
		this.setaRequest(httpServletRequest,form);
		
		return retorno;
	}
	
	/**
	 * Pesquisa Localidade
	 *
	 * @author Bruno Barros
	 * @date 19/12/2007
	 */
	private void pesquisarLocalidade(EmissaoRelatorioQuadroMetasAcumuladoActionForm form) {


		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(
				new ParametroSimples(FiltroLocalidade.ID, 
				form.getLocalidade()));
		
		// Recupera Localidade
		Collection colecaoLocalidade = 
			this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
	
		if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {

			Localidade localidade = 
				(Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
			
			form.setLocalidade(localidade.getId().toString());
			form.setNomeLocalidade(localidade.getDescricao());
			
		} else {
			form.setLocalidade(null);
			form.setNomeLocalidade("Localidade inexistente");
		}
	}
	
	/**
	 * Pesquisa Gerencial Regional 
	 *
	 * @author Bruno Barros
	 * @date 19/12/2007
	 */
	private void pesquisarGerenciaRegional(HttpServletRequest httpServletRequest){
		
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		
		filtroGerenciaRegional.setConsultaSemLimites(true);
		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
		filtroGerenciaRegional.adicionarParametro(
				new ParametroSimples(FiltroQuadra.INDICADORUSO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoGerenciaRegional = 
			this.getFachada().pesquisar(filtroGerenciaRegional,GerenciaRegional.class.getName());


		if (colecaoGerenciaRegional == null || colecaoGerenciaRegional.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Gerência Regional");
		} else {
			httpServletRequest.setAttribute("colecaoGerenciaRegional",colecaoGerenciaRegional);
		}
	}
	
	
	/**
	 * Pesquisa Unidade Negocio
	 *
	 * @author Bruno Barros
	 * @date 19/12/2007
	 */
	private void pesquisarUnidadeNegocio(HttpServletRequest httpServletRequest,
			EmissaoRelatorioQuadroMetasAcumuladoActionForm form){
		
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		
		filtroUnidadeNegocio.setConsultaSemLimites(true);
		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);
		
		if(form.getGerenciaRegional() != null && 
			!form.getGerenciaRegional().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			filtroUnidadeNegocio.adicionarParametro(
				new ParametroSimples(FiltroUnidadeNegocio.ID_GERENCIA, 
					form.getGerenciaRegional()));		
		}

		filtroUnidadeNegocio.adicionarParametro(
				new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));		

		Collection colecaoUnidadeNegocio = 
			this.getFachada().pesquisar(filtroUnidadeNegocio,UnidadeNegocio.class.getName());


		if (colecaoUnidadeNegocio == null || colecaoUnidadeNegocio.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Unidade de Negócio");
		} else {
			httpServletRequest.setAttribute("colecaoUnidadeNegocio",colecaoUnidadeNegocio);
		}
	}
	
	/**
	 * Seta os request com os id encontrados 
	 *
	 * @author Bruno Barros
	 * @date 19/12/2007
	 */
	private void setaRequest(HttpServletRequest httpServletRequest,
			EmissaoRelatorioQuadroMetasAcumuladoActionForm form){
		
		//Localidade
		if(form.getLocalidade() != null && 
			!form.getLocalidade().equals("") && 
			form.getNomeLocalidade() != null && 
			!form.getNomeLocalidade().equals("")){
					
			httpServletRequest.setAttribute("localidadeEncontrada","true");
		}
		
	}
}
