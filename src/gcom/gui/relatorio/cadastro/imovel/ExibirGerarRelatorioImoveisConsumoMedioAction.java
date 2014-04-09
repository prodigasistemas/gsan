package gcom.gui.relatorio.cadastro.imovel;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
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
 * [UC0727] Gerar Relatório de Imóveis com Faturas em Atraso
 * 
 * @author Bruno Barros
 * 
 * @date 12/12/2007
 */

public class ExibirGerarRelatorioImoveisConsumoMedioAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirGerarRelatorioImoveisConsumoMedio");

		GerarRelatorioImoveisConsumoMedioActionForm form = (GerarRelatorioImoveisConsumoMedioActionForm) actionForm;

		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest
				.getParameter("objetoConsulta");

		// Verificamos se foi chamado do menu
		if (httpServletRequest.getParameter("menu") != null
				&& ((String) httpServletRequest.getParameter("menu"))
						.equals("sim")) {
			form.reset();
		}
		
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		String mesAno = Util.formatarAnoMesParaMesAno(sistemaParametro.getAnoMesFaturamento());
		form.setAnoMesReferencia(mesAno);

		// Pesquisar Localidade
		if (objetoConsulta != null
				&& !objetoConsulta.trim().equals("")
				&& (objetoConsulta.trim().equals("1") || objetoConsulta.trim()
						.equals("3"))) {

			// Faz a consulta de Localidade
			this.pesquisarLocalidade(form, objetoConsulta);
		}

		// Pesquisar Setor Comercial
		if (objetoConsulta != null
				&& !objetoConsulta.trim().equals("")
				&& (objetoConsulta.trim().equals("2") || objetoConsulta.trim()
						.equals("4"))) {

			// Faz a consulta de Setor Comercial
			this.pesquisarSetorComercial(form, objetoConsulta);
		}

		this.pesquisarGerenciaRegional(httpServletRequest);
		this.pesquisarUnidadeNegocio(httpServletRequest, form);
		this.pesquisarLigacaoAguaSituacao(httpServletRequest);
		this.pesquisarCategoria(httpServletRequest);
		this.pesquisarPerfisImovel(httpServletRequest);

		// Seta os request´s encontrados
		this.setaRequest(httpServletRequest, form);

		// manda o parametro que veio do validar enter
		// para ,se preciso, desabilitar os campos posterior ao intervalo, que
		// não
		// são iguais.
		if (httpServletRequest.getParameter("campoDesabilita") != null
				&& !httpServletRequest.getParameter("campoDesabilita").equals(
						"")) {
			httpServletRequest.setAttribute("campoDesabilita",
					httpServletRequest.getParameter("campoDesabilita"));
		}

		return retorno;
	}

	/**
	 * Pesquisa Localidade
	 * 
	 * @author Bruno Barros
	 * @date 28/11/2007
	 */
	private void pesquisarLocalidade(
			GerarRelatorioImoveisConsumoMedioActionForm form,
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

	/**
	 * Pesquisa Setor comercial
	 * 
	 * @author Bruno Barros
	 * @date 28/11/2007
	 */
	private void pesquisarSetorComercial(
			GerarRelatorioImoveisConsumoMedioActionForm form,
			String objetoConsulta) {

		Object local = form.getLocalidadeInicial();
		Object setor = form.getSetorComercialInicial();

		if (!objetoConsulta.trim().equals("2")) {
			local = form.getLocalidadeFinal();
			setor = form.getSetorComercialFinal();
		}

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, setor));

		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.LOCALIDADE, local));

		// Recupera Setor Comercial
		Collection colecaoSetorComercial = this.getFachada().pesquisar(
				filtroSetorComercial, SetorComercial.class.getName());

		if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {

			SetorComercial setorComercial = (SetorComercial) Util
					.retonarObjetoDeColecao(colecaoSetorComercial);

			if (objetoConsulta.trim().equals("2")) {
				form.setSetorComercialInicial("" + setorComercial.getCodigo());
				form
						.setNomeSetorComercialInicial(setorComercial
								.getDescricao());
			}

			form.setSetorComercialFinal("" + setorComercial.getCodigo());
			form.setNomeSetorComercialFinal(setorComercial.getDescricao());

		} else {

			if (objetoConsulta.trim().equals("2")) {
				form.setSetorComercialInicial(null);
				form
						.setNomeSetorComercialInicial("Setor Comercial Inicial inexistente");

				form.setSetorComercialFinal(null);
				form.setNomeSetorComercialFinal(null);
			} else {
				form.setSetorComercialFinal(null);
				form
						.setNomeSetorComercialFinal("Setor Comercial Final inexistente");
			}

		}
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
			GerarRelatorioImoveisConsumoMedioActionForm form) {

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
	 * Pesquisa Situacao Ligacao Agua
	 * 
	 * @author Bruno Barros
	 * @date 28/11/2007
	 */
	private void pesquisarLigacaoAguaSituacao(
			HttpServletRequest httpServletRequest) {

		FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();

		filtroLigacaoAguaSituacao.setConsultaSemLimites(true);
		filtroLigacaoAguaSituacao
				.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);
		filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
				FiltroLigacaoAguaSituacao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoSituacaoLigacaoAgua = this.getFachada().pesquisar(
				filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());

		if (colecaoSituacaoLigacaoAgua == null
				|| colecaoSituacaoLigacaoAgua.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Ligaçao de Água");
		} else {
			httpServletRequest.setAttribute("colecaoSituacaoLigacaoAgua",
					colecaoSituacaoLigacaoAgua);
		}
	}

	/**
	 * Seta os request com os id encontrados
	 * 
	 * @author Bruno Barros
	 * @date 28/11/2007
	 */
	private void setaRequest(HttpServletRequest httpServletRequest,
			GerarRelatorioImoveisConsumoMedioActionForm form) {

		// Localidade Inicial
		if (form.getLocalidadeInicial() != null
				&& !form.getLocalidadeInicial().equals("")
				&& form.getNomeLocalidadeInicial() != null
				&& !form.getNomeLocalidadeInicial().equals("")) {

			httpServletRequest.setAttribute("localidadeInicialEncontrada",
					"true");
			httpServletRequest
					.setAttribute("localidadeFinalEncontrada", "true");
		} else {

			if (form.getLocalidadeFinal() != null
					&& !form.getLocalidadeFinal().equals("")
					&& form.getNomeLocalidadeFinal() != null
					&& !form.getNomeLocalidadeFinal().equals("")) {

				httpServletRequest.setAttribute("localidadeFinalEncontrada",
						"true");
			}
		}

		// Setor Comercial Inicial
		if (form.getSetorComercialInicial() != null
				&& !form.getSetorComercialInicial().equals("")
				&& form.getNomeSetorComercialInicial() != null
				&& !form.getNomeSetorComercialInicial().equals("")) {

			httpServletRequest.setAttribute("setorComercialInicialEncontrado",
					"true");
			httpServletRequest.setAttribute("setorComercialFinalEncontrado",
					"true");
		} else {

			if (form.getSetorComercialFinal() != null
					&& !form.getSetorComercialFinal().equals("")
					&& form.getNomeSetorComercialFinal() != null
					&& !form.getNomeSetorComercialFinal().equals("")) {

				httpServletRequest.setAttribute(
						"setorComercialFinalEncontrado", "true");
			}
		}
	}

	/**
	 * Pesquisa Gerencial Regional
	 * 
	 * @author Bruno Barros
	 * @date 04/12/2007
	 */
	private void pesquisarCategoria(HttpServletRequest httpServletRequest) {

		FiltroCategoria filtroCategoria = new FiltroCategoria();

		filtroCategoria.setConsultaSemLimites(true);
		filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);
		filtroCategoria.adicionarParametro(new ParametroSimples(
				FiltroQuadra.INDICADORUSO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoCategoria = this.getFachada().pesquisar(
				filtroCategoria, Categoria.class.getName());

		if (colecaoCategoria == null || colecaoCategoria.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Categoria");
		} else {
			httpServletRequest.setAttribute("colecaoCategoria",
					colecaoCategoria);
		}
	}

	/**
	 * Pesquisar Perfil do Imóvel
	 * 
	 * @author Magno Gouveia
	 * @date 17/05/2011
	 * 
	 * @param httpServletRequest
	 */
	private void pesquisarPerfisImovel(HttpServletRequest httpServletRequest) {
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();

		filtroImovelPerfil.setConsultaSemLimites(true);
		filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(
				FiltroImovelPerfil.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoPerfisImovel = this.getFachada().pesquisar(
				filtroImovelPerfil, ImovelPerfil.class.getName());

		if (colecaoPerfisImovel == null || colecaoPerfisImovel.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Perfil do Imóvel");
		} else {
			httpServletRequest.setAttribute("colecaoPerfisImovel",
					colecaoPerfisImovel);
		}
	}
}
