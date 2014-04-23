package gcom.gui.relatorio.cadastro;

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
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1076] - Gerar Relatorio Atualizacao Cadastral Via Internet.
 * 
 * @author Daniel Alves,Hugo Amorim
 *
 * @date 24/09/2010,01/10/2010
 */

public class ExibirGerarRelatorioAtualizacaoCadastralViaInternetAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirGerarRelatorioAtualizacaoCadastralViaInternet");
		
		HttpSession sessao = httpServletRequest.getSession(false);

		GerarRelatorioAtualizacaoCadastralViaInternetActionForm form = (GerarRelatorioAtualizacaoCadastralViaInternetActionForm) actionForm;
		
		// Verifica se entrou apartir
		// do menu
		//
		if(httpServletRequest.getParameter("menu")!=null
				&& httpServletRequest.getParameter("menu").toString().equalsIgnoreCase("sim")){
			
			this.limpar(form, sessao);
			
		}
		
		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest
				.getParameter("objetoConsulta");
		// Pesquisar Localidade
		if (objetoConsulta != null
				&& !objetoConsulta.trim().equals("")
				&& (objetoConsulta.trim().equals("1") || objetoConsulta.trim()
						.equals("3"))) {

			// Faz a consulta de Localidade
			this.pesquisarLocalidade(form, objetoConsulta);
		}
		
		this.pesquisarUnidadeNegocio(httpServletRequest, form);
		this.pesquisarGerenciaRegional(httpServletRequest);
		this.setaRequest(httpServletRequest, form);
			
		return retorno;
	}
	
	
	
	private void limpar(GerarRelatorioAtualizacaoCadastralViaInternetActionForm form,HttpSession sessao){

		form.setPeriodoReferenciaInicial("");
		form.setPeriodoReferenciaFinal("");
		form.setGerenciaRegional("-1");
		form.setUnidadeNegocio("-1");
		form.setLocalidadeInicial("");
		form.setNomeLocalidadeInicial("");
		form.setLocalidadeFinal("");
		form.setNomeLocalidadeFinal("");
		form.setOpcaoRelatorio("A");
		
		sessao.removeAttribute("enderecoFormatado");
		sessao.removeAttribute("inscricaoImovelEncontrada");
	}
	
	/**
	 * Pesquisa Gerencial Regional
	 * 
	 * @author Bruno Barros
	 * @date 28/11/2007
	 */
	private void pesquisarGerenciaRegional(HttpServletRequest httpServletRequest) {

		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();

		filtroGerenciaRegional.setConsultaSemLimites(true);
		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
		filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
				FiltroQuadra.INDICADORUSO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoGerenciaRegional = this.getFachada().pesquisar(
				filtroGerenciaRegional, GerenciaRegional.class.getName());

		if (colecaoGerenciaRegional == null
				|| colecaoGerenciaRegional.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Gerência Regional");
		} else {
			httpServletRequest.setAttribute("colecaoGerenciaRegional",
					colecaoGerenciaRegional);
		}
	}
	
	
	/**
	 * Pesquisa Unidade Negocio
	 * 
	 * @author Bruno Barros
	 * @date 28/11/2007
	 */
	private void pesquisarUnidadeNegocio(HttpServletRequest httpServletRequest,
			GerarRelatorioAtualizacaoCadastralViaInternetActionForm form) {

		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();

		filtroUnidadeNegocio.setConsultaSemLimites(true);
		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);

		if (form.getGerenciaRegional() != null
				&& !form.getGerenciaRegional().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
					FiltroUnidadeNegocio.ID_GERENCIA, form
							.getGerenciaRegional()));
		}

		filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
				FiltroUnidadeNegocio.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoUnidadeNegocio = this.getFachada().pesquisar(
				filtroUnidadeNegocio, UnidadeNegocio.class.getName());

		if (colecaoUnidadeNegocio == null || colecaoUnidadeNegocio.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Unidade de Negócio");
		} else {
			httpServletRequest.setAttribute("colecaoUnidadeNegocio",
					colecaoUnidadeNegocio);
		}
	}
	
	/**
	 * Pesquisa Localidade
	 * 
	 * @author Bruno Barros
	 * @date 28/11/2007
	 */
	private void pesquisarLocalidade(
			GerarRelatorioAtualizacaoCadastralViaInternetActionForm form,
			String objetoConsulta) {
		
		Object local = form.getLocalidadeInicial();

		if (!objetoConsulta.trim().equals("1")) {
			local = form.getLocalidadeFinal();
		}

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, local));

		// Recupera Localidade
		Collection colecaoLocalidade = this.getFachada().pesquisar(
				filtroLocalidade, Localidade.class.getName());

		if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {

			Localidade localidade = (Localidade) Util
					.retonarObjetoDeColecao(colecaoLocalidade);

			if (objetoConsulta.trim().equals("1")) {
				form.setLocalidadeInicial(localidade.getId().toString());
				form.setNomeLocalidadeInicial(localidade.getDescricao());
			}

			form.setLocalidadeFinal(localidade.getId().toString());
			form.setNomeLocalidadeFinal(localidade.getDescricao());

		} else {
			if (objetoConsulta.trim().equals("1")) {
				form.setLocalidadeInicial(null);
				form.setNomeLocalidadeInicial("Localidade Inicial inexistente");

				form.setLocalidadeFinal(null);
				form.setNomeLocalidadeFinal(null);
			} else {
				form.setLocalidadeFinal(null);
				form.setNomeLocalidadeFinal("Localidade Final inexistente");
			}
		}
	}
	
	private void setaRequest(HttpServletRequest httpServletRequest,
			GerarRelatorioAtualizacaoCadastralViaInternetActionForm form) {

		// Localidade Inicial
		if (form.getLocalidadeInicial() != null
				&& !form.getLocalidadeInicial().equals("")
				&& form.getNomeLocalidadeInicial() != null
				&& !form.getNomeLocalidadeInicial().equals("")) {

			httpServletRequest.setAttribute("localidadeInicialEncontrada", "true");
			httpServletRequest.setAttribute("localidadeFinalEncontrada", "true");
		} else if (form.getLocalidadeFinal() != null
					&& !form.getLocalidadeFinal().equals("")
					&& form.getNomeLocalidadeFinal() != null
					&& !form.getNomeLocalidadeFinal().equals("")) {

				httpServletRequest.setAttribute("localidadeFinalEncontrada", "true");
		}

		
	}
	
	
}
