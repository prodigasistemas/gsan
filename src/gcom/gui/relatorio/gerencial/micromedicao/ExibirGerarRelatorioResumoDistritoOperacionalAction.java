package gcom.gui.relatorio.gerencial.micromedicao;

import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.DistritoOperacional;
import gcom.operacional.FiltroDistritoOperacional;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirGerarRelatorioResumoDistritoOperacionalAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirGerarRelatorioResumoDistritoOperacionalAction");

		GerarRelatorioResumoDistritoOperacionalActionForm form = (GerarRelatorioResumoDistritoOperacionalActionForm) actionForm;
		
		
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

		// Pesquisar Setor Comercial
		if (objetoConsulta != null
				&& !objetoConsulta.trim().equals("")
				&& (objetoConsulta.trim().equals("2") || objetoConsulta.trim()
						.equals("4"))) {

			// Faz a consulta de Setor Comercial
			this.pesquisarSetorComercial(form, objetoConsulta);
		}

		this.pesquisarUnidadeNegocio(httpServletRequest, form);
		this.pesquisarGerenciaRegional(httpServletRequest);
		this.pesquisarDistritoOperacional(httpServletRequest);
		this.setaRequest(httpServletRequest, form);

		return retorno;
	}

	/**
	 * Pesquisa Distrito Operacional
	 * 
	 * @author Hugo Amorim
	 * @date 08/04/2009
	 */
	private void pesquisarDistritoOperacional(
			HttpServletRequest httpServletRequest) {
		FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();

		filtroDistritoOperacional.setConsultaSemLimites(true);
		filtroDistritoOperacional
				.setCampoOrderBy(FiltroDistritoOperacional.DESCRICAO);

		Collection colecaoDistritoOperacional = this.getFachada().pesquisar(
				filtroDistritoOperacional, DistritoOperacional.class.getName());

		if (colecaoDistritoOperacional == null
				|| colecaoDistritoOperacional.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Distrito Operacional");
		} else {
			httpServletRequest.setAttribute("colecaoDistritoOperacional",
					colecaoDistritoOperacional);
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
			GerarRelatorioResumoDistritoOperacionalActionForm form) {

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
	 * Pesquisa Setor comercial
	 * 
	 * @author Bruno Barros
	 * @date 28/11/2007
	 */
	private void pesquisarSetorComercial(
			GerarRelatorioResumoDistritoOperacionalActionForm form,
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
	 * Pesquisa Localidade
	 * 
	 * @author Bruno Barros
	 * @date 28/11/2007
	 */
	private void pesquisarLocalidade(
			GerarRelatorioResumoDistritoOperacionalActionForm form,
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
			GerarRelatorioResumoDistritoOperacionalActionForm form) {
		
		if (form.getMesAno() != null && !form.getMesAno().equals("")) {
			httpServletRequest.setAttribute("mesAno", "true");
		}
		// Tipo
		if (form.getTipo() != null && !form.getTipo().equals("")) {
			httpServletRequest.setAttribute("tipo", "true");
		}
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
}
